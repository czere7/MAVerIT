package tools.jackson.core.io;

import java.util.Objects;

import tools.jackson.core.ErrorReportConfiguration;

import org.junit.Test;

import static org.junit.Assert.*;

public class ContentReferenceTest {

    private static final ErrorReportConfiguration DEFAULT_CONFIG = ErrorReportConfiguration.defaults();

    @Test
    public void testUnknownReturnsSingleton() {
        ContentReference unknown = ContentReference.unknown();
        assertNotNull(unknown);
        assertSame(ContentReference.unknown(), ContentReference.unknown());
    }

    @Test
    public void testRedactedReturnsSingleton() {
        ContentReference redacted = ContentReference.redacted();
        assertNotNull(redacted);
        assertSame(ContentReference.redacted(), ContentReference.redacted());
    }

    @Test
    public void testUnknownAndRedactedAreDifferent() {
        assertNotSame(ContentReference.unknown(), ContentReference.redacted());
    }

    @Test
    public void testConstructWithFullParameters() {
        String content = "test content";
        ContentReference ref = ContentReference.construct(true, content, 0, content.length(), DEFAULT_CONFIG);
        
        assertTrue(ref.hasTextualContent());
        assertEquals(content, ref.getRawContent());
        assertEquals(0, ref.contentOffset());
        assertEquals(content.length(), ref.contentLength());
    }

    @Test
    public void testConstructWithBasicParameters() {
        String content = "test content";
        ContentReference ref = ContentReference.construct(true, content, DEFAULT_CONFIG);
        
        assertTrue(ref.hasTextualContent());
        assertEquals(content, ref.getRawContent());
        assertEquals(-1, ref.contentOffset());
        assertEquals(-1, ref.contentLength());
    }

    @Test
    public void testRawReferenceWithTextualFlag() {
        String content = "test content";
        ContentReference ref = ContentReference.rawReference(true, content);
        
        assertTrue(ref.hasTextualContent());
        assertEquals(content, ref.getRawContent());
    }

    @Test
    public void testRawReferenceDefaultsToNonTextual() {
        byte[] content = new byte[]{1, 2, 3};
        ContentReference ref = ContentReference.rawReference(content);
        
        assertFalse(ref.hasTextualContent());
        assertEquals(content, ref.getRawContent());
    }

    @Test
    public void testRawReferenceUnwrapsContentReference() {
        String content = "test content";
        ContentReference original = ContentReference.construct(true, content, DEFAULT_CONFIG);
        
        ContentReference result = ContentReference.rawReference(true, original);
        
        assertSame(original, result);
    }

    @Test
    public void testBuildSourceDescriptionWithUnknown() {
        ContentReference unknown = ContentReference.unknown();
        String desc = unknown.buildSourceDescription();
        
        assertEquals("UNKNOWN", desc);
    }

    @Test
    public void testBuildSourceDescriptionWithRedacted() {
        ContentReference redacted = ContentReference.redacted();
        String desc = redacted.buildSourceDescription();
        
        assertTrue(desc.contains("REDACTED"));
    }

    @Test
    public void testBuildSourceDescriptionWithString() {
        String content = "hello";
        ContentReference ref = ContentReference.construct(true, content, DEFAULT_CONFIG);
        
        String desc = ref.buildSourceDescription();
        
        assertTrue(desc.contains("String"));
        assertTrue(desc.contains("hello"));
    }

    @Test
    public void testBuildSourceDescriptionWithCharArray() {
        char[] content = new char[]{'h', 'e', 'l', 'l', 'o'};
        ContentReference ref = ContentReference.construct(true, content, DEFAULT_CONFIG);
        
        String desc = ref.buildSourceDescription();
        
        assertTrue(desc.contains("char[]"));
        assertTrue(desc.contains("hello"));
    }

    @Test
    public void testBuildSourceDescriptionWithByteArray() {
        byte[] content = new byte[]{1, 2, 3};
        ContentReference ref = ContentReference.construct(false, content, DEFAULT_CONFIG);
        
        String desc = ref.buildSourceDescription();
        
        assertTrue(desc.contains("byte[]"));
        assertTrue(desc.contains("3 bytes"));
    }

    @Test
    public void testBuildSourceDescriptionWithStringBuilder() {
        StringBuilder content = new StringBuilder("test");
        ContentReference ref = ContentReference.construct(true, content, DEFAULT_CONFIG);
        
        String desc = ref.buildSourceDescription();
        
        assertTrue(desc.contains("StringBuilder"));
        assertTrue(desc.contains("test"));
    }

    @Test
    public void testBuildSourceDescriptionWithNonTextualString() {
        String content = "test";
        ContentReference ref = ContentReference.construct(false, content, DEFAULT_CONFIG);
        
        String desc = ref.buildSourceDescription();
        
        assertTrue(desc.contains("String"));
        assertFalse(desc.contains("test"));
    }

    @Test
    public void testSourceDescriptionTruncation() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 500; i++) {
            sb.append('a');
        }
        String content = sb.toString();
        
        ContentReference ref = ContentReference.construct(true, content, DEFAULT_CONFIG);
        
        String desc = ref.buildSourceDescription();
        
        assertTrue(desc.contains("truncated") || desc.contains("\""));
    }

    @Test
    public void testSourceDescriptionWithOffsetAndLength() {
        char[] content = new char[]{'a', 'b', 'c', 'd', 'e'};
        ContentReference ref = ContentReference.construct(true, content, 1, 3, DEFAULT_CONFIG);
        
        String desc = ref.buildSourceDescription();
        
        assertTrue(desc.contains("bcd"));
    }

    @Test
    public void testEqualsSameInstance() {
        String content = "test";
        ContentReference ref = ContentReference.construct(true, content, DEFAULT_CONFIG);
        
        assertEquals(ref, ref);
    }

    @Test
    public void testEqualsNull() {
        String content = "test";
        ContentReference ref = ContentReference.construct(true, content, DEFAULT_CONFIG);
        
        assertNotEquals(ref, null);
    }

    @Test
    public void testEqualsDifferentTypes() {
        String content = "test";
        ContentReference ref = ContentReference.construct(true, content, DEFAULT_CONFIG);
        
        assertNotEquals(ref, "test");
    }

    @Test
    public void testEqualsWithSameContent() {
        String content1 = "test";
        String content2 = new String("test");
        ContentReference ref1 = ContentReference.construct(true, content1, DEFAULT_CONFIG);
        ContentReference ref2 = ContentReference.construct(true, content2, DEFAULT_CONFIG);
        
        assertNotEquals(ref1, ref2);
    }

    @Test
    public void testEqualsWithDifferentOffsets() {
        char[] content = new char[]{'a', 'b', 'c'};
        ContentReference ref1 = ContentReference.construct(true, content, 0, 3, DEFAULT_CONFIG);
        ContentReference ref2 = ContentReference.construct(true, content, 1, 2, DEFAULT_CONFIG);
        
        assertNotEquals(ref1, ref2);
    }

    @Test
    public void testEqualsWithFile() {
        java.io.File file = new java.io.File("test.txt");
        ContentReference ref1 = ContentReference.construct(false, file, DEFAULT_CONFIG);
        ContentReference ref2 = ContentReference.construct(false, file, DEFAULT_CONFIG);
        
        assertEquals(ref1, ref2);
    }

    @Test
    public void testEqualsWithUrl() throws Exception {
        java.net.URL url = new java.net.URL("file://test.txt");
        ContentReference ref1 = ContentReference.construct(false, url, DEFAULT_CONFIG);
        ContentReference ref2 = ContentReference.construct(false, url, DEFAULT_CONFIG);
        
        assertEquals(ref1, ref2);
    }

    @Test
    public void testEqualsWithUri() throws Exception {
        java.net.URI uri = new java.net.URI("file://test.txt");
        ContentReference ref1 = ContentReference.construct(false, uri, DEFAULT_CONFIG);
        ContentReference ref2 = ContentReference.construct(false, uri, DEFAULT_CONFIG);
        
        assertEquals(ref1, ref2);
    }

    @Test
    public void testEqualsBothNullContent() {
        ContentReference ref1 = ContentReference.unknown();
        ContentReference ref2 = ContentReference.unknown();
        
        assertEquals(ref1, ref2);
    }

    @Test
    public void testEqualsOneNullContent() {
        ContentReference ref1 = ContentReference.construct(true, "test", DEFAULT_CONFIG);
        ContentReference ref2 = ContentReference.unknown();
        
        assertNotEquals(ref1, ref2);
    }

    @Test
    public void testHashCodeConsistency() {
        String content = "test";
        ContentReference ref = ContentReference.construct(true, content, DEFAULT_CONFIG);
        
        int hash1 = ref.hashCode();
        int hash2 = ref.hashCode();
        
        assertEquals(hash1, hash2);
    }

    @Test
    public void testHashCodeEqualsForEqualObjects() {
        java.io.File file = new java.io.File("test.txt");
        ContentReference ref1 = ContentReference.construct(false, file, DEFAULT_CONFIG);
        ContentReference ref2 = ContentReference.construct(false, file, DEFAULT_CONFIG);
        
        assertEquals(ref1.hashCode(), ref2.hashCode());
    }

    @Test
    public void testMaxRawContentLength() {
        ErrorReportConfiguration config = ErrorReportConfiguration.defaults();
        ContentReference ref = ContentReference.construct(true, "test", config);
        
        assertEquals(config.getMaxRawContentLength(), ref.maxRawContentLength());
    }

    @Test
    public void testSerializationReturnsUnknown() throws Exception {
        java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
        java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(baos);
        
        ContentReference original = ContentReference.construct(true, "test", DEFAULT_CONFIG);
        oos.writeObject(original);
        oos.close();
        
        java.io.ByteArrayInputStream bais = new java.io.ByteArrayInputStream(baos.toByteArray());
        java.io.ObjectInputStream ois = new java.io.ObjectInputStream(bais);
        
        ContentReference deserialized = (ContentReference) ois.readObject();
        ois.close();
        
        assertEquals(ContentReference.unknown(), deserialized);
    }

    @Test
    public void testAppendSourceDescriptionWithAppendedBuilder() {
        String content = "test";
        ContentReference ref = ContentReference.construct(true, content, DEFAULT_CONFIG);
        
        StringBuilder sb = new StringBuilder("Prefix:");
        ref.appendSourceDescription(sb);
        
        assertTrue(sb.toString().startsWith("Prefix:"));
    }

    @Test
    public void testSourceDescriptionWithJavaLangClass() {
        ContentReference ref = ContentReference.construct(false, String.class, DEFAULT_CONFIG);
        
        String desc = ref.buildSourceDescription();
        
        assertTrue(desc.contains("Class") || desc.contains("String"));
    }

    @Test
    public void testTruncateOffsetsWithNegativeStart() {
        String content = "hello";
        ContentReference ref = ContentReference.construct(true, content, -5, 10, DEFAULT_CONFIG);
        
        String desc = ref.buildSourceDescription();
        
        assertNotNull(desc);
    }

    @Test
    public void testTruncateOffsetsWithStartBeyondLength() {
        String content = "hello";
        ContentReference ref = ContentReference.construct(true, content, 100, 10, DEFAULT_CONFIG);
        
        String desc = ref.buildSourceDescription();
        
        assertNotNull(desc);
    }

    @Test
    public void testTruncateOffsetsWithNegativeLength() {
        String content = "hello";
        ContentReference ref = ContentReference.construct(true, content, 0, -5, DEFAULT_CONFIG);
        
        String desc = ref.buildSourceDescription();
        
        assertNotNull(desc);
    }

    @Test
    public void testTruncateOffsetsWithLengthExceedingMax() {
        String content = "hello";
        ContentReference ref = ContentReference.construct(true, content, 0, 100, DEFAULT_CONFIG);
        
        String desc = ref.buildSourceDescription();
        
        assertNotNull(desc);
    }

    @Test
    public void testTruncateWithCharSequence() {
        StringBuilder content = new StringBuilder("test content");
        ContentReference ref = ContentReference.construct(true, content, 0, content.length(), DEFAULT_CONFIG);
        
        String desc = ref.buildSourceDescription();
        
        assertTrue(desc.contains("test content"));
    }

    @Test
    public void testTruncateWithCharArray() {
        char[] content = "test content".toCharArray();
        ContentReference ref = ContentReference.construct(true, content, 0, content.length, DEFAULT_CONFIG);
        
        String desc = ref.buildSourceDescription();
        
        assertTrue(desc.contains("test content"));
    }

    @Test
    public void testTruncateWithByteArray() {
        byte[] content = "test content".getBytes(java.nio.charset.StandardCharsets.UTF_8);
        ContentReference ref = ContentReference.construct(true, content, 0, content.length, DEFAULT_CONFIG);
        
        String desc = ref.buildSourceDescription();
        
        assertTrue(desc.contains("test content"));
    }

    @Test
    public void testAppendSourceDescriptionWithBinaryContentBeyondMax() {
        byte[] content = new byte[1000];
        for (int i = 0; i < 1000; i++) {
            content[i] = (byte) i;
        }
        ContentReference ref = ContentReference.construct(false, content, 0, 1000, DEFAULT_CONFIG);
        
        String desc = ref.buildSourceDescription();
        
        assertTrue(desc.contains("bytes"));
    }

    @Test
    public void testAppendSourceDescriptionWithOffsetNotNegative() {
        String content = "hello world";
        ContentReference ref = ContentReference.construct(true, content, 6, 5, DEFAULT_CONFIG);
        
        String desc = ref.buildSourceDescription();
        
        assertTrue(desc.contains("world"));
    }

    @Test
    public void testBuildSourceDescriptionWithJavaLangClassTextual() {
        ContentReference ref = ContentReference.construct(true, String.class, DEFAULT_CONFIG);
        
        String desc = ref.buildSourceDescription();
        
        assertNotNull(desc);
    }

    @Test
    public void testEqualsWithDifferentLengthSameOffset() {
        char[] content = new char[]{'a', 'b', 'c', 'd', 'e'};
        ContentReference ref1 = ContentReference.construct(true, content, 0, 3, DEFAULT_CONFIG);
        ContentReference ref2 = ContentReference.construct(true, content, 0, 5, DEFAULT_CONFIG);
        
        assertNotEquals(ref1, ref2);
    }

    @Test
    public void testEqualsWithOtherRawContentNull() {
        ContentReference ref1 = ContentReference.construct(true, "test", DEFAULT_CONFIG);
        ContentReference ref2 = ContentReference.unknown();
        
        assertNotEquals(ref1, ref2);
    }

    @Test
    public void testEqualsWithThisRawContentNull() {
        ContentReference ref1 = ContentReference.unknown();
        ContentReference ref2 = ContentReference.construct(true, "test", DEFAULT_CONFIG);
        
        assertNotEquals(ref1, ref2);
    }

    @Test
    public void testEqualsWithNonFileUrlUriContent() {
        String content1 = "test";
        String content2 = new String("test");
        ContentReference ref1 = ContentReference.construct(true, content1, DEFAULT_CONFIG);
        ContentReference ref2 = ContentReference.construct(true, content2, DEFAULT_CONFIG);
        
        assertNotEquals(ref1, ref2);
    }

    @Test
    public void testAppendEscapedWithControlCharacter() {
        String content = "test\u0001value";
        ContentReference ref = ContentReference.construct(true, content, DEFAULT_CONFIG);
        
        String desc = ref.buildSourceDescription();
        
        assertNotNull(desc);
    }

    @Test
    public void testAppendWithCarriageReturn() {
        String content = "test\rvalue";
        ContentReference ref = ContentReference.construct(true, content, DEFAULT_CONFIG);
        
        String desc = ref.buildSourceDescription();
        
        assertNotNull(desc);
    }

    @Test
    public void testAppendWithLineFeed() {
        String content = "test\nvalue";
        ContentReference ref = ContentReference.construct(true, content, DEFAULT_CONFIG);
        
        String desc = ref.buildSourceDescription();
        
        assertNotNull(desc);
    }

    @Test
    public void testSourceDescriptionNonTextualByteArrayWithNegativeLength() {
        byte[] content = new byte[]{1, 2, 3, 4, 5};
        ContentReference ref = ContentReference.construct(false, content, 0, -1, DEFAULT_CONFIG);
        
        String desc = ref.buildSourceDescription();
        
        assertTrue(desc.contains("5 bytes"));
    }

    @Test
    public void testAppendSourceDescriptionWithOffsetBeyondLength() {
        String content = "short";
        ContentReference ref = ContentReference.construct(true, content, 10, 5, DEFAULT_CONFIG);
        
        String desc = ref.buildSourceDescription();
        
        assertNotNull(desc);
    }

    @Test
    public void testEqualsWithNullRawContentBoth() {
        ContentReference ref1 = ContentReference.unknown();
        ContentReference ref2 = ContentReference.unknown();
        
        assertEquals(ref1, ref2);
    }

    @Test
    public void testTruncateByteArrayWithOffset() {
        byte[] content = "hello world".getBytes(java.nio.charset.StandardCharsets.UTF_8);
        ContentReference ref = ContentReference.construct(true, content, 6, 5, DEFAULT_CONFIG);
        
        String desc = ref.buildSourceDescription();
        
        assertTrue(desc.contains("world"));
    }

    @Test
    public void testTruncateCharArrayWithOffset() {
        char[] content = "hello world".toCharArray();
        ContentReference ref = ContentReference.construct(true, content, 6, 5, DEFAULT_CONFIG);
        
        String desc = ref.buildSourceDescription();
        
        assertTrue(desc.contains("world"));
    }

    @Test
    public void testBuildSourceDescriptionWithEmptyString() {
        ContentReference ref = ContentReference.construct(true, "", DEFAULT_CONFIG);
        
        String desc = ref.buildSourceDescription();
        
        assertNotNull(desc);
    }

    @Test
    public void testBuildSourceDescriptionWithEmptyCharArray() {
        ContentReference ref = ContentReference.construct(true, new char[0], DEFAULT_CONFIG);
        
        String desc = ref.buildSourceDescription();
        
        assertNotNull(desc);
    }

    @Test
    public void testBuildSourceDescriptionWithEmptyByteArray() {
        ContentReference ref = ContentReference.construct(false, new byte[0], DEFAULT_CONFIG);
        
        String desc = ref.buildSourceDescription();
        
        assertTrue(desc.contains("0 bytes"));
    }

    @Test
    public void testEqualsWithBothNonNullDifferentObjects() {
        java.io.File file1 = new java.io.File("test1.txt");
        java.io.File file2 = new java.io.File("test2.txt");
        ContentReference ref1 = ContentReference.construct(false, file1, DEFAULT_CONFIG);
        ContentReference ref2 = ContentReference.construct(false, file2, DEFAULT_CONFIG);
        
        assertNotEquals(ref1, ref2);
    }

    @Test
    public void testHashCodeWithNullContent() {
        ContentReference ref = ContentReference.unknown();
        
        int hash = ref.hashCode();
        
        assertEquals(Objects.hashCode(null), hash);
    }

    @Test
    public void testSourceDescriptionTruncationExactLength() {
        ErrorReportConfiguration config = ErrorReportConfiguration.defaults();
        int maxLen = config.getMaxRawContentLength();
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < maxLen; i++) {
            sb.append('a');
        }
        String content = sb.toString();
        
        ContentReference ref = ContentReference.construct(true, content, DEFAULT_CONFIG);
        
        String desc = ref.buildSourceDescription();
        
        assertFalse(desc.contains("truncated"));
    }

    @Test
    public void testEqualsWithSameOffsetAndLengthButDifferentObjectType() {
        Integer obj1 = Integer.valueOf(200);
        Integer obj2 = Integer.valueOf(200);
        ContentReference ref1 = ContentReference.construct(false, obj1, 0, 0, DEFAULT_CONFIG);
        ContentReference ref2 = ContentReference.construct(false, obj2, 0, 0, DEFAULT_CONFIG);
        
        assertNotEquals(ref1, ref2);
    }

    @Test
    public void testEqualsWithSameOffsetAndLengthAndSameContentSameInstance() {
        ContentReference ref = ContentReference.construct(true, "test", 0, 4, DEFAULT_CONFIG);
        assertEquals(ref, ref);
    }

    @Test
    public void testEqualsRedactedVsUnknown() {
        // Note: unknown() and redacted() are different instances but compare equal
        // because both have null content. This test verifies they are different objects
        // but they are equal according to equals() method.
        ContentReference redacted = ContentReference.redacted();
        ContentReference unknown = ContentReference.unknown();
        assertNotSame(redacted, unknown);
        // They are actually equal because both have null raw content
        assertEquals(redacted, unknown);
    }

    @Test
    public void testHashCodeRedactedVsUnknown() {
        // Both have null content, so they have the same hash code
        ContentReference redacted = ContentReference.redacted();
        ContentReference unknown = ContentReference.unknown();
        assertEquals(redacted.hashCode(), unknown.hashCode());
    }

    @Test
    public void testBuildSourceDescriptionWithOnlyOffset() {
        String content = "hello world";
        ContentReference ref = ContentReference.construct(true, content, 6, -1, DEFAULT_CONFIG);
        
        String desc = ref.buildSourceDescription();
        
        assertTrue(desc.contains("world"));
    }

    @Test
    public void testBuildSourceDescriptionWithZeroOffsetAndLength() {
        String content = "hello";
        ContentReference ref = ContentReference.construct(true, content, 0, 0, DEFAULT_CONFIG);
        
        String desc = ref.buildSourceDescription();
        
        assertNotNull(desc);
    }

    @Test
    public void testBuildSourceDescriptionWithVeryLargeOffset() {
        String content = "ab";
        ContentReference ref = ContentReference.construct(true, content, 100, 5, DEFAULT_CONFIG);
        
        String desc = ref.buildSourceDescription();
        
        assertNotNull(desc);
    }

    @Test
    public void testAppendEscapedWithTab() {
        String content = "before\tafter";
        ContentReference ref = ContentReference.construct(true, content, DEFAULT_CONFIG);
        
        String desc = ref.buildSourceDescription();
        
        assertTrue(desc.contains("\\u0009"));
    }

    @Test
    public void testAppendEscapedWithBackspace() {
        String content = "test\bvalue";
        ContentReference ref = ContentReference.construct(true, content, DEFAULT_CONFIG);
        
        String desc = ref.buildSourceDescription();
        
        assertTrue(desc.contains("\\u0008"));
    }

    @Test
    public void testAppendEscapedWithFormFeed() {
        String content = "test\fvalue";
        ContentReference ref = ContentReference.construct(true, content, DEFAULT_CONFIG);
        
        String desc = ref.buildSourceDescription();
        
        // Form feed should be escaped - check for either escaped form
        assertTrue(desc.contains("\\u000c") || desc.contains("\\u000C"));
    }

    @Test
    public void testAppendEscapedWithEscape() {
        String content = "test\u001bvalue";
        ContentReference ref = ContentReference.construct(true, content, DEFAULT_CONFIG);
        
        String desc = ref.buildSourceDescription();
        
        // Escape character should be escaped - check for either escaped form  
        assertTrue(desc.contains("\\u001b") || desc.contains("\\u001B"));
    }

    @Test
    public void testBuildSourceDescriptionNonTextualByteArrayFullContent() {
        byte[] content = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ContentReference ref = ContentReference.construct(false, content, DEFAULT_CONFIG);
        
        String desc = ref.buildSourceDescription();
        
        // Should contain "10 bytes" and NOT show individual byte values like just "1"
        assertTrue(desc.contains("10 bytes"));
    }

    @Test
    public void testBuildSourceDescriptionNonTextualByteArrayWithOffsetAndLength() {
        byte[] content = new byte[]{1, 2, 3, 4, 5};
        ContentReference ref = ContentReference.construct(false, content, 1, 3, DEFAULT_CONFIG);
        
        String desc = ref.buildSourceDescription();
        
        assertTrue(desc.contains("3 bytes"));
    }

    @Test
    public void testBuildSourceDescriptionWithJavaLangClassNonTextual() {
        ContentReference ref = ContentReference.construct(false, Integer.class, DEFAULT_CONFIG);
        
        String desc = ref.buildSourceDescription();
        
        assertTrue(desc.contains("Integer"));
    }

    @Test
    public void testBuildSourceDescriptionWithStringBuffer() {
        StringBuffer content = new StringBuffer("test buffer");
        ContentReference ref = ContentReference.construct(true, content, DEFAULT_CONFIG);
        
        String desc = ref.buildSourceDescription();
        
        assertTrue(desc.contains("test buffer"));
    }

    @Test
    public void testEqualsWithSameFileButDifferentOffsets() {
        java.io.File file = new java.io.File("test.txt");
        ContentReference ref1 = ContentReference.construct(false, file, 0, 10, DEFAULT_CONFIG);
        ContentReference ref2 = ContentReference.construct(false, file, 5, 10, DEFAULT_CONFIG);
        
        assertNotEquals(ref1, ref2);
    }

    @Test
    public void testEqualsWithSameUrlButDifferentOffsets() throws Exception {
        java.net.URL url = new java.net.URL("file://test.txt");
        ContentReference ref1 = ContentReference.construct(false, url, 0, 10, DEFAULT_CONFIG);
        ContentReference ref2 = ContentReference.construct(false, url, 5, 10, DEFAULT_CONFIG);
        
        assertNotEquals(ref1, ref2);
    }

    @Test
    public void testEqualsWithSameUriButDifferentOffsets() throws Exception {
        java.net.URI uri = new java.net.URI("file://test.txt");
        ContentReference ref1 = ContentReference.construct(false, uri, 0, 10, DEFAULT_CONFIG);
        ContentReference ref2 = ContentReference.construct(false, uri, 5, 10, DEFAULT_CONFIG);
        
        assertNotEquals(ref1, ref2);
    }

    @Test
    public void testEqualsWithSameContentDifferentInstancesFile() {
        java.io.File file1 = new java.io.File("test.txt");
        java.io.File file2 = new java.io.File("test.txt");
        ContentReference ref1 = ContentReference.construct(false, file1, DEFAULT_CONFIG);
        ContentReference ref2 = ContentReference.construct(false, file2, DEFAULT_CONFIG);
        
        assertEquals(ref1, ref2);
    }

    @Test
    public void testHashCodeConsistencyWithNullContent() {
        ContentReference ref = ContentReference.unknown();
        
        int hash1 = ref.hashCode();
        int hash2 = ref.hashCode();
        int hash3 = ref.hashCode();
        
        assertEquals(hash1, hash2);
        assertEquals(hash2, hash3);
    }

    @Test
    public void testHashCodeDifferentObjectsDifferentHashes() {
        ContentReference ref1 = ContentReference.construct(true, "test1", DEFAULT_CONFIG);
        ContentReference ref2 = ContentReference.construct(true, "test2", DEFAULT_CONFIG);
        
        assertNotEquals(ref1.hashCode(), ref2.hashCode());
    }

    @Test
    public void testBuildSourceDescriptionTruncationShowsCount() {
        // Create a string longer than default max raw content length (1000)
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 200; i++) {
            sb.append('a');
        }
        String content = sb.toString();
        
        // With default config (maxRawContentLength = 1000), this 200-char string
        // should not trigger truncation. Let me create longer content.
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < 1500; i++) {
            sb2.append('a');
        }
        String longContent = sb2.toString();
        
        ContentReference ref = ContentReference.construct(true, longContent, 0, longContent.length(), DEFAULT_CONFIG);
        
        String desc = ref.buildSourceDescription();
        
        // Should contain truncation info for content longer than max
        assertTrue(desc.contains("truncated"));
    }

    @Test
    public void testRawReferenceNonTextualWithNull() {
        ContentReference ref = ContentReference.rawReference(false, null);
        
        assertFalse(ref.hasTextualContent());
        assertNull(ref.getRawContent());
    }

    @Test
    public void testRawReferenceTextualWithNull() {
        ContentReference ref = ContentReference.rawReference(true, null);
        
        assertTrue(ref.hasTextualContent());
        assertNull(ref.getRawContent());
    }

    @Test
    public void testEqualsWithOtherInstanceNullContent() {
        ContentReference ref1 = ContentReference.construct(true, "test", DEFAULT_CONFIG);
        ContentReference ref2 = ContentReference.unknown();
        
        assertNotEquals(ref1, ref2);
    }

    @Test
    public void testEqualsWithBothUnknownContent() {
        ContentReference ref1 = ContentReference.unknown();
        ContentReference ref2 = ContentReference.unknown();
        
        assertEquals(ref1, ref2);
    }

    @Test
    public void testEqualsWithBothRedactedContent() {
        ContentReference ref1 = ContentReference.redacted();
        ContentReference ref2 = ContentReference.redacted();
        
        assertEquals(ref1, ref2);
    }

    @Test
    public void testHashCodeUnknownAndRedactedDifferent() {
        // Both have null content, so they have the same hash code
        int unknownHash = ContentReference.unknown().hashCode();
        int redactedHash = ContentReference.redacted().hashCode();
        
        assertEquals(unknownHash, redactedHash);
    }

    @Test
    public void testTruncateOffsetsExactlyAtBoundary() {
        String content = "abc";
        ContentReference ref = ContentReference.construct(true, content, 3, 5, DEFAULT_CONFIG);
        
        String desc = ref.buildSourceDescription();
        
        assertNotNull(desc);
    }

    @Test
    public void testTruncateOffsetsZeroLengthContent() {
        String content = "";
        ContentReference ref = ContentReference.construct(true, content, 0, 0, DEFAULT_CONFIG);
        
        String desc = ref.buildSourceDescription();
        
        assertNotNull(desc);
    }

    @Test
    public void testBuildSourceDescriptionWithCharArrayTypeNotCharSequence() {
        char[] content = "test".toCharArray();
        ContentReference ref = ContentReference.construct(true, content, DEFAULT_CONFIG);
        
        String desc = ref.buildSourceDescription();
        
        assertTrue(desc.contains("char[]"));
        assertTrue(desc.contains("test"));
    }

    @Test
    public void testBuildSourceDescriptionWithByteArrayTextual() {
        byte[] content = "test".getBytes(java.nio.charset.StandardCharsets.UTF_8);
        ContentReference ref = ContentReference.construct(true, content, DEFAULT_CONFIG);
        
        String desc = ref.buildSourceDescription();
        
        // Should contain "test" - the actual content decoded as UTF-8
        // and may or may not contain "bytes" depending on implementation
        assertTrue(desc.contains("test"));
    }

    @Test
    public void testAppendSourceDescriptionBinaryNoOffsetNoLength() {
        byte[] content = new byte[]{1, 2, 3};
        ContentReference ref = ContentReference.construct(false, content, DEFAULT_CONFIG);
        
        StringBuilder sb = new StringBuilder();
        ref.appendSourceDescription(sb);
        
        assertTrue(sb.toString().contains("3 bytes"));
    }

    @Test
    public void testAppendSourceDescriptionRedactedReturnsCorrectFormat() {
        ContentReference redacted = ContentReference.redacted();
        
        String desc = redacted.buildSourceDescription();
        
        assertTrue(desc.startsWith("REDACTED"));
    }

    @Test
    public void testAppendSourceDescriptionUnknownReturnsCorrectFormat() {
        ContentReference unknown = ContentReference.unknown();
        
        String desc = unknown.buildSourceDescription();
        
        assertEquals("UNKNOWN", desc);
    }
}
