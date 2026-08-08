package tools.jackson.core.io;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.junit.Test;

import tools.jackson.core.ErrorReportConfiguration;

public class ContentReferenceTest {

    private static final ErrorReportConfiguration DEFAULT_CONFIG = ErrorReportConfiguration.defaults();
    private static final int DEFAULT_MAX_RAW_CONTENT_LENGTH = DEFAULT_CONFIG.getMaxRawContentLength();
    private static final int DEFAULT_MAX_ERROR_TOKEN_LENGTH = DEFAULT_CONFIG.getMaxErrorTokenLength();

    @Test
    public void testUnknown() {
        ContentReference ref = ContentReference.unknown();
        assertSame(ContentReference.UNKNOWN_CONTENT, ref);
        assertFalse(ref.hasTextualContent());
        assertNull(ref.getRawContent());
        assertEquals(-1, ref.contentOffset());
        assertEquals(-1, ref.contentLength());
    }

    @Test
    public void testRedacted() {
        ContentReference ref = ContentReference.redacted();
        assertSame(ContentReference.REDACTED_CONTENT, ref);
        assertFalse(ref.hasTextualContent());
        assertNull(ref.getRawContent());
    }

    @Test
    public void testConstructWithOffsets() {
        String content = "test content";
        ContentReference ref = ContentReference.construct(true, content, 2, 5, DEFAULT_CONFIG);
        assertTrue(ref.hasTextualContent());
        assertSame(content, ref.getRawContent());
        assertEquals(2, ref.contentOffset());
        assertEquals(5, ref.contentLength());
        assertEquals(DEFAULT_MAX_RAW_CONTENT_LENGTH, ref.maxRawContentLength());
    }

    @Test
    public void testConstructWithoutOffsets() {
        String content = "test content";
        ContentReference ref = ContentReference.construct(true, content, DEFAULT_CONFIG);
        assertTrue(ref.hasTextualContent());
        assertSame(content, ref.getRawContent());
        assertEquals(-1, ref.contentOffset());
        assertEquals(-1, ref.contentLength());
    }

    @Test
    public void testRawReferenceWithBoolean() {
        byte[] data = new byte[] {1, 2, 3};
        ContentReference ref = ContentReference.rawReference(false, data);
        assertFalse(ref.hasTextualContent());
        assertSame(data, ref.getRawContent());
        assertEquals(-1, ref.contentOffset());
        assertEquals(-1, ref.contentLength());
    }

    @Test
    public void testRawReferenceWithoutBoolean() {
        char[] data = new char[] {'a', 'b', 'c'};
        ContentReference ref = ContentReference.rawReference(data);
        assertFalse(ref.hasTextualContent());
        assertSame(data, ref.getRawContent());
    }

    @Test
    public void testRawReferenceWithContentReference() {
        ContentReference original = ContentReference.construct(true, "test", DEFAULT_CONFIG);
        ContentReference ref = ContentReference.rawReference(true, original);
        assertSame(original, ref);
    }

    @Test
    public void testBuildSourceDescriptionUnknown() {
        ContentReference ref = ContentReference.unknown();
        String desc = ref.buildSourceDescription();
        assertEquals("UNKNOWN", desc);
    }

    @Test
    public void testBuildSourceDescriptionRedacted() {
        ContentReference ref = ContentReference.redacted();
        String desc = ref.buildSourceDescription();
        assertTrue(desc.contains("REDACTED"));
        assertTrue(desc.contains("StreamReadFeature.INCLUDE_SOURCE_IN_LOCATION"));
    }

    @Test
    public void testBuildSourceDescriptionString() {
        String content = "Hello World";
        ContentReference ref = ContentReference.construct(true, content, DEFAULT_CONFIG);
        String desc = ref.buildSourceDescription();
        assertTrue(desc.contains("(String)"));
        assertTrue(desc.contains("\"Hello World\""));
    }

    @Test
    public void testBuildSourceDescriptionCharSequence() {
        StringBuilder sb = new StringBuilder("Hello World");
        ContentReference ref = ContentReference.construct(true, sb, DEFAULT_CONFIG);
        String desc = ref.buildSourceDescription();
        assertTrue(desc.contains("(StringBuilder)"));
        assertTrue(desc.contains("\"Hello World\""));
    }

    @Test
    public void testBuildSourceDescriptionCharArray() {
        char[] content = "Hello World".toCharArray();
        ContentReference ref = ContentReference.construct(true, content, DEFAULT_CONFIG);
        String desc = ref.buildSourceDescription();
        assertTrue(desc.contains("(char[])"));
        assertTrue(desc.contains("\"Hello World\""));
    }

    @Test
    public void testBuildSourceDescriptionByteArrayTextual() {
        byte[] content = "Hello World".getBytes(StandardCharsets.UTF_8);
        ContentReference ref = ContentReference.construct(true, content, DEFAULT_CONFIG);
        String desc = ref.buildSourceDescription();
        assertTrue(desc.contains("(byte[])"));
        assertTrue(desc.contains("\"Hello World\""));
    }

    @Test
    public void testBuildSourceDescriptionByteArrayBinary() {
        byte[] content = new byte[] {1, 2, 3, 4, 5};
        ContentReference ref = ContentReference.construct(false, content, DEFAULT_CONFIG);
        String desc = ref.buildSourceDescription();
        assertTrue(desc.contains("(byte[])"));
        assertTrue(desc.contains("[5 bytes]"));
    }

    @Test
    public void testBuildSourceDescriptionFile() throws Exception {
        File file = File.createTempFile("test", ".tmp");
        try {
            ContentReference ref = ContentReference.construct(false, file, DEFAULT_CONFIG);
            String desc = ref.buildSourceDescription();
            assertTrue(desc.contains("(File)"));
        } finally {
            file.delete();
        }
    }

    @Test
    public void testBuildSourceDescriptionURL() throws Exception {
        URL url = new URL("file:///tmp/test.txt");
        ContentReference ref = ContentReference.construct(false, url, DEFAULT_CONFIG);
        String desc = ref.buildSourceDescription();
        assertTrue(desc.contains("(URL)"));
    }

    @Test
    public void testBuildSourceDescriptionURI() {
        URI uri = URI.create("file:///tmp/test.txt");
        ContentReference ref = ContentReference.construct(false, uri, DEFAULT_CONFIG);
        String desc = ref.buildSourceDescription();
        assertTrue(desc.contains("(URI)"));
    }

    @Test
    public void testBuildSourceDescriptionWithOffsetAndLength() {
        String content = "Hello World";
        ContentReference ref = ContentReference.construct(true, content, 6, 5, DEFAULT_CONFIG);
        String desc = ref.buildSourceDescription();
        assertTrue(desc.contains("\"World\""));
    }

    @Test
    public void testBuildSourceDescriptionWithNegativeOffset() {
        String content = "Hello World";
        ContentReference ref = ContentReference.construct(true, content, -1, 5, DEFAULT_CONFIG);
        String desc = ref.buildSourceDescription();
        assertTrue(desc.contains("\"Hello\""));
    }

    @Test
    public void testBuildSourceDescriptionWithOffsetBeyondLength() {
        String content = "Hello";
        ContentReference ref = ContentReference.construct(true, content, 10, 5, DEFAULT_CONFIG);
        String desc = ref.buildSourceDescription();
        assertTrue(desc.contains("\"\""));
    }

    @Test
    public void testBuildSourceDescriptionWithLengthExceedingRemaining() {
        String content = "Hello";
        ContentReference ref = ContentReference.construct(true, content, 2, 10, DEFAULT_CONFIG);
        String desc = ref.buildSourceDescription();
        assertTrue(desc.contains("\"llo\""));
    }

    @Test
    public void testBuildSourceDescriptionBinaryWithOffsetAndLength() {
        byte[] content = new byte[] {1, 2, 3, 4, 5, 6, 7, 8};
        ContentReference ref = ContentReference.construct(false, content, 2, 3, DEFAULT_CONFIG);
        String desc = ref.buildSourceDescription();
        assertTrue(desc.contains("[3 bytes]"));
    }

    @Test
    public void testBuildSourceDescriptionBinaryWithNegativeLength() {
        byte[] content = new byte[] {1, 2, 3, 4, 5};
        ContentReference ref = ContentReference.construct(false, content, 0, -1, DEFAULT_CONFIG);
        String desc = ref.buildSourceDescription();
        assertTrue(desc.contains("[5 bytes]"));
    }

    @Test
    public void testBuildSourceDescriptionNonTextualString() {
        String content = "Hello World";
        ContentReference ref = ContentReference.construct(false, content, DEFAULT_CONFIG);
        String desc = ref.buildSourceDescription();
        assertTrue(desc.contains("(String)"));
        assertFalse(desc.contains("\"Hello World\""));
    }

    @Test
    public void testAppendSourceDescription() {
        String content = "test";
        ContentReference ref = ContentReference.construct(true, content, DEFAULT_CONFIG);
        StringBuilder sb = new StringBuilder();
        ref.appendSourceDescription(sb);
        assertTrue(sb.toString().contains("\"test\""));
    }

    @Test
    public void testHasTextualContent() {
        assertTrue(ContentReference.construct(true, "test", DEFAULT_CONFIG).hasTextualContent());
        assertFalse(ContentReference.construct(false, "test", DEFAULT_CONFIG).hasTextualContent());
        assertFalse(ContentReference.unknown().hasTextualContent());
        assertFalse(ContentReference.redacted().hasTextualContent());
    }

    @Test
    public void testGetRawContent() {
        String content = "test";
        ContentReference ref = ContentReference.construct(true, content, DEFAULT_CONFIG);
        assertSame(content, ref.getRawContent());
        assertNull(ContentReference.unknown().getRawContent());
        assertNull(ContentReference.redacted().getRawContent());
    }

    @Test
    public void testContentOffsetAndLength() {
        ContentReference ref = ContentReference.construct(true, "test", 2, 3, DEFAULT_CONFIG);
        assertEquals(2, ref.contentOffset());
        assertEquals(3, ref.contentLength());
        
        ContentReference ref2 = ContentReference.construct(true, "test", DEFAULT_CONFIG);
        assertEquals(-1, ref2.contentOffset());
        assertEquals(-1, ref2.contentLength());
    }

    @Test
    public void testMaxRawContentLength() {
        ContentReference ref = ContentReference.construct(true, "test", DEFAULT_CONFIG);
        assertEquals(DEFAULT_MAX_RAW_CONTENT_LENGTH, ref.maxRawContentLength());
    }

    @Test
    public void testEqualsSameInstance() {
        ContentReference ref = ContentReference.construct(true, "test", DEFAULT_CONFIG);
        assertTrue(ref.equals(ref));
    }

    @Test
    public void testEqualsNull() {
        ContentReference ref = ContentReference.construct(true, "test", DEFAULT_CONFIG);
        assertFalse(ref.equals(null));
    }

    @Test
    public void testEqualsDifferentType() {
        ContentReference ref = ContentReference.construct(true, "test", DEFAULT_CONFIG);
        assertFalse(ref.equals("not a ContentReference"));
    }

    @Test
    public void testEqualsDifferentOffset() {
        ContentReference ref1 = ContentReference.construct(true, "test", 0, 4, DEFAULT_CONFIG);
        ContentReference ref2 = ContentReference.construct(true, "test", 1, 4, DEFAULT_CONFIG);
        assertFalse(ref1.equals(ref2));
    }

    @Test
    public void testEqualsDifferentLength() {
        ContentReference ref1 = ContentReference.construct(true, "test", 0, 4, DEFAULT_CONFIG);
        ContentReference ref2 = ContentReference.construct(true, "test", 0, 3, DEFAULT_CONFIG);
        assertFalse(ref1.equals(ref2));
    }

    @Test
    public void testEqualsBothNullRawContent() {
        assertTrue(ContentReference.unknown().equals(ContentReference.unknown()));
        assertTrue(ContentReference.redacted().equals(ContentReference.redacted()));
    }

    @Test
    public void testEqualsOneNullRawContent() {
        ContentReference ref1 = ContentReference.unknown();
        ContentReference ref2 = ContentReference.construct(true, "test", DEFAULT_CONFIG);
        assertFalse(ref1.equals(ref2));
    }

    @Test
    public void testEqualsFile() throws Exception {
        File file1 = File.createTempFile("test1", ".tmp");
        File file2 = File.createTempFile("test2", ".tmp");
        try {
            ContentReference ref1 = ContentReference.construct(false, file1, DEFAULT_CONFIG);
            ContentReference ref2 = ContentReference.construct(false, file1, DEFAULT_CONFIG);
            ContentReference ref3 = ContentReference.construct(false, file2, DEFAULT_CONFIG);
            
            assertTrue(ref1.equals(ref2));
            assertFalse(ref1.equals(ref3));
        } finally {
            file1.delete();
            file2.delete();
        }
    }

    @Test
    public void testEqualsURL() throws Exception {
        URL url1 = new URL("file:///tmp/test1.txt");
        URL url2 = new URL("file:///tmp/test2.txt");
        ContentReference ref1 = ContentReference.construct(false, url1, DEFAULT_CONFIG);
        ContentReference ref2 = ContentReference.construct(false, url1, DEFAULT_CONFIG);
        ContentReference ref3 = ContentReference.construct(false, url2, DEFAULT_CONFIG);
        
        assertTrue(ref1.equals(ref2));
        assertFalse(ref1.equals(ref3));
    }

    @Test
    public void testEqualsURI() {
        URI uri1 = URI.create("file:///tmp/test1.txt");
        URI uri2 = URI.create("file:///tmp/test2.txt");
        ContentReference ref1 = ContentReference.construct(false, uri1, DEFAULT_CONFIG);
        ContentReference ref2 = ContentReference.construct(false, uri1, DEFAULT_CONFIG);
        ContentReference ref3 = ContentReference.construct(false, uri2, DEFAULT_CONFIG);
        
        assertTrue(ref1.equals(ref2));
        assertFalse(ref1.equals(ref3));
    }

    @Test
    public void testEqualsSameReference() {
        String content = "test";
        ContentReference ref1 = ContentReference.construct(true, content, DEFAULT_CONFIG);
        ContentReference ref2 = ContentReference.construct(true, content, DEFAULT_CONFIG);
        assertTrue(ref1.equals(ref2));
    }

    @Test
    public void testEqualsDifferentReferenceSameContent() {
        ContentReference ref1 = ContentReference.construct(true, "test", DEFAULT_CONFIG);
        ContentReference ref2 = ContentReference.construct(true, new String("test"), DEFAULT_CONFIG);
        assertFalse(ref1.equals(ref2));
    }

    @Test
    public void testHashCode() {
        ContentReference ref1 = ContentReference.construct(true, "test", DEFAULT_CONFIG);
        ContentReference ref2 = ContentReference.construct(true, "test", DEFAULT_CONFIG);
        assertEquals(ref1.hashCode(), ref2.hashCode());
        
        ContentReference ref3 = ContentReference.unknown();
        ContentReference ref4 = ContentReference.unknown();
        assertEquals(ref3.hashCode(), ref4.hashCode());
    }

    @Test
    public void testSerialization() throws IOException, ClassNotFoundException {
        String content = "test content";
        ContentReference original = ContentReference.construct(true, content, 2, 5, DEFAULT_CONFIG);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(original);
        oos.close();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        ContentReference deserialized = (ContentReference) ois.readObject();
        
        assertSame(ContentReference.UNKNOWN_CONTENT, deserialized);
    }

    @Test
    public void testSerializationOfUnknown() throws IOException, ClassNotFoundException {
        ContentReference original = ContentReference.unknown();
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(original);
        oos.close();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        ContentReference deserialized = (ContentReference) ois.readObject();
        
        assertSame(ContentReference.UNKNOWN_CONTENT, deserialized);
    }

    @Test
    public void testSerializationOfRedacted() throws IOException, ClassNotFoundException {
        ContentReference original = ContentReference.redacted();
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(original);
        oos.close();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        ContentReference deserialized = (ContentReference) ois.readObject();
        
        assertSame(ContentReference.UNKNOWN_CONTENT, deserialized);
    }

    @Test
    public void testAppendEscapedControlCharacters() {
        String content = "Hello\tWorld\nTest\rEnd";
        ContentReference ref = ContentReference.construct(true, content, DEFAULT_CONFIG);
        String desc = ref.buildSourceDescription();
        assertTrue(desc.contains("\\u0009"));
        assertTrue(desc.contains("\n"));
        assertTrue(desc.contains("\r"));
    }

    @Test
    public void testAppendEscapedOtherControlCharacters() {
        String content = "Hello\u0001World\u0002Test";
        ContentReference ref = ContentReference.construct(true, content, DEFAULT_CONFIG);
        String desc = ref.buildSourceDescription();
        assertTrue(desc.contains("\\u0001"));
        assertTrue(desc.contains("\\u0002"));
    }

    @Test
    public void testBuildSourceDescriptionClassAsRawContent() {
        ContentReference ref = ContentReference.construct(false, String.class, DEFAULT_CONFIG);
        String desc = ref.buildSourceDescription();
        assertTrue(desc.contains("(String)"));
    }

    @Test
    public void testBuildSourceDescriptionOtherObject() {
        ContentReference ref = ContentReference.construct(false, new java.util.ArrayList<>(), DEFAULT_CONFIG);
        String desc = ref.buildSourceDescription();
        assertTrue(desc.contains("(ArrayList)"));
    }

    @Test
    public void testTruncateOffsetsNegativeStart() {
        String content = "Hello World";
        ContentReference ref = ContentReference.construct(true, content, -5, 5, DEFAULT_CONFIG);
        String desc = ref.buildSourceDescription();
        assertTrue(desc.contains("\"Hello\""));
    }

    @Test
    public void testTruncateOffsetsStartBeyondLength() {
        String content = "Hello";
        ContentReference ref = ContentReference.construct(true, content, 10, 5, DEFAULT_CONFIG);
        String desc = ref.buildSourceDescription();
        assertTrue(desc.contains("\"\""));
    }

    @Test
    public void testTruncateOffsetsLengthNegative() {
        String content = "Hello World";
        ContentReference ref = ContentReference.construct(true, content, 3, -1, DEFAULT_CONFIG);
        String desc = ref.buildSourceDescription();
        assertTrue(desc.contains("\"lo World\""));
    }

    @Test
    public void testTruncateOffsetsLengthExceedsRemaining() {
        String content = "Hello World";
        ContentReference ref = ContentReference.construct(true, content, 6, 10, DEFAULT_CONFIG);
        String desc = ref.buildSourceDescription();
        assertTrue(desc.contains("\"World\""));
    }

    @Test
    public void testUnknownAndRedactedAreDifferent() {
        assertNotSame(ContentReference.unknown(), ContentReference.redacted());
        assertTrue(ContentReference.unknown().equals(ContentReference.redacted()));
    }

    @Test
    public void testBuildSourceDescriptionByteArrayWithOffsetAndLengthTextual() {
        byte[] content = "Hello World".getBytes(StandardCharsets.UTF_8);
        ContentReference ref = ContentReference.construct(true, content, 6, 5, DEFAULT_CONFIG);
        String desc = ref.buildSourceDescription();
        assertTrue(desc.contains("\"World\""));
    }

    @Test
    public void testBuildSourceDescriptionCharArrayWithOffsetAndLength() {
        char[] content = "Hello World".toCharArray();
        ContentReference ref = ContentReference.construct(true, content, 6, 5, DEFAULT_CONFIG);
        String desc = ref.buildSourceDescription();
        assertTrue(desc.contains("\"World\""));
    }
    
    @Test
    public void testBuildSourceDescriptionByteArrayTextualTruncated() {
        byte[] content = new byte[1000];
        for (int i = 0; i < content.length; i++) {
            content[i] = (byte) ('A' + (i % 26));
        }
        ContentReference ref = ContentReference.construct(true, content, DEFAULT_CONFIG);
        String desc = ref.buildSourceDescription();
        assertTrue(desc.contains("(byte[])"));
        assertTrue(desc.contains("[truncated"));
        assertTrue(desc.contains(" bytes]"));
    }
    
    @Test
    public void testBuildSourceDescriptionByteArrayWithOffsetAndLengthTextualTruncated() {
        byte[] content = new byte[1000];
        for (int i = 0; i < content.length; i++) {
            content[i] = (byte) ('A' + (i % 26));
        }
        ContentReference ref = ContentReference.construct(true, content, 100, 900, DEFAULT_CONFIG);
        String desc = ref.buildSourceDescription();
        assertTrue(desc.contains("(byte[])"));
        assertTrue(desc.contains("[truncated"));
        assertTrue(desc.contains(" bytes]"));
    }

    // Additional tests to improve branch coverage

    @Test
    public void testBuildSourceDescriptionTextualWithUnhandledType() {
        // Tests textual content with a type not handled by _truncate (not CharSequence, char[], byte[])
        // This covers the branch where hasTextualContent() is true but trimmed remains null
        CustomTextualObject obj = new CustomTextualObject("custom content");
        ContentReference ref = ContentReference.construct(true, obj, DEFAULT_CONFIG);
        String desc = ref.buildSourceDescription();
        // Inner class name includes outer class and $ separator
        assertTrue(desc.contains("CustomTextualObject"));
        // Content should not be appended since type is not handled
        assertFalse(desc.contains("custom content"));
    }

    @Test
    public void testBuildSourceDescriptionBinaryWithUnhandledType() {
        // Tests binary content with a type not byte[] (covers line 278 false branch)
        CustomBinaryObject obj = new CustomBinaryObject(new byte[]{1,2,3});
        ContentReference ref = ContentReference.construct(false, obj, DEFAULT_CONFIG);
        String desc = ref.buildSourceDescription();
        assertTrue(desc.contains("CustomBinaryObject"));
        // No byte length appended for non-byte[] binary types
        assertFalse(desc.contains("bytes]"));
    }

    @Test
    public void testAppendSourceDescriptionWithNullRawContentViaConstructor() {
        // Tests the srcRef == null branch with a non-singleton instance (not UNKNOWN_CONTENT or REDACTED_CONTENT)
        // This covers the branch at line 253 where srcRef == null but this != REDACTED_CONTENT
        ContentReference ref = new ContentReference(true, null, DEFAULT_CONFIG);
        StringBuilder sb = new StringBuilder();
        ref.appendSourceDescription(sb);
        String desc = sb.toString();
        assertEquals("UNKNOWN", desc);
    }

    @Test
    public void testEqualsWithCustomObjectSameReference() {
        // Tests equals for non-File/URL/URI types with same object reference (identity true)
        // Covers line 399 false branch and line 400 true return
        CustomObject obj = new CustomObject("test");
        ContentReference ref1 = new ContentReference(true, obj, DEFAULT_CONFIG);
        ContentReference ref2 = new ContentReference(true, obj, DEFAULT_CONFIG);
        assertTrue(ref1.equals(ref2));
        assertEquals(ref1.hashCode(), ref2.hashCode());
    }

    @Test
    public void testEqualsWithCustomObjectDifferentReference() {
        // Tests equals for non-File/URL/URI types with different object references (identity false)
        // Covers line 399 false branch and line 400 false return
        ContentReference ref1 = new ContentReference(true, new CustomObject("test"), DEFAULT_CONFIG);
        ContentReference ref2 = new ContentReference(true, new CustomObject("test"), DEFAULT_CONFIG);
        assertFalse(ref1.equals(ref2));
    }

    @Test
    public void testEqualsWithCustomObjectVsNull() {
        // Tests equals where one has custom object and other has null rawContent
        ContentReference ref1 = new ContentReference(true, new CustomObject("test"), DEFAULT_CONFIG);
        ContentReference ref2 = ContentReference.unknown();
        assertFalse(ref1.equals(ref2));
        assertFalse(ref2.equals(ref1));
    }

    @Test
    public void testBuildSourceDescriptionTextualCharArrayTruncated() {
        // Tests textual char[] with truncation message
        char[] content = new char[1000];
        for (int i = 0; i < content.length; i++) {
            content[i] = (char) ('A' + (i % 26));
        }
        ContentReference ref = ContentReference.construct(true, content, DEFAULT_CONFIG);
        String desc = ref.buildSourceDescription();
        assertTrue(desc.contains("(char[])"));
        assertTrue(desc.contains("[truncated"));
        assertTrue(desc.contains(" chars]"));
    }

    @Test
    public void testBuildSourceDescriptionTextualCharSequenceTruncated() {
        // Tests textual CharSequence (StringBuilder) with truncation message
        StringBuilder sb = new StringBuilder(1000);
        for (int i = 0; i < 1000; i++) {
            sb.append((char) ('A' + (i % 26)));
        }
        ContentReference ref = ContentReference.construct(true, sb, DEFAULT_CONFIG);
        String desc = ref.buildSourceDescription();
        assertTrue(desc.contains("(StringBuilder)"));
        assertTrue(desc.contains("[truncated"));
        assertTrue(desc.contains(" chars]"));
    }

    @Test
    public void testBuildSourceDescriptionTextualStringTruncated() {
        // Tests textual String with truncation message
        StringBuilder sb = new StringBuilder(1000);
        for (int i = 0; i < 1000; i++) {
            sb.append((char) ('A' + (i % 26)));
        }
        String content = sb.toString();
        ContentReference ref = ContentReference.construct(true, content, DEFAULT_CONFIG);
        String desc = ref.buildSourceDescription();
        assertTrue(desc.contains("(String)"));
        assertTrue(desc.contains("[truncated"));
        assertTrue(desc.contains(" chars]"));
    }

    @Test
    public void testRawReferenceWithNull() {
        // Tests rawReference with null content (binary by default)
        ContentReference ref = ContentReference.rawReference(false, null);
        assertFalse(ref.hasTextualContent());
        assertNull(ref.getRawContent());
        String desc = ref.buildSourceDescription();
        assertEquals("UNKNOWN", desc);
    }

    @Test
    public void testConstructWithNullRawContent() {
        // Tests construct with null rawContent (should be allowed by constructor)
        ContentReference ref = ContentReference.construct(true, null, DEFAULT_CONFIG);
        assertTrue(ref.hasTextualContent());
        assertNull(ref.getRawContent());
        String desc = ref.buildSourceDescription();
        assertEquals("UNKNOWN", desc);
    }

    // ===== FIXED TESTS FOR MUTATION TESTING (call _appendEscaped directly via reflection) =====

    @Test
    public void testAppendReturnsContentLength() {
        // Tests _append return value (kills PrimitiveReturnsMutator on _append line 354)
        String content = "Hello World";
        ContentReference ref = ContentReference.construct(true, content, DEFAULT_CONFIG);
        StringBuilder sb = new StringBuilder();
        // Access _append via reflection since it's protected
        int result = invokeAppend(ref, sb, content);
        assertEquals(content.length(), result);
        assertTrue(sb.toString().contains("Hello World"));
    }

    @Test
    public void testAppendEscapedShiftRightOperations() {
        // Tests _appendEscaped shift right operations (kills MathMutator on lines 364, 365, 366)
        // Using reflection to call _appendEscaped directly with values that exercise all 4 nibbles
        ContentReference ref = ContentReference.construct(true, "test", DEFAULT_CONFIG);
        
        // Test with 0x1234 (4660) - exercises all 4 shift operations
        // >>12 = 0x1, >>8&0xF = 0x2, >>4&0xF = 0x3, &0xF = 0x4
        StringBuilder sb1 = new StringBuilder();
        boolean result1 = invokeAppendEscaped(ref, sb1, 0x1234);
        assertTrue(result1); // Should return true for escaped char
        assertEquals("\\u1234", sb1.toString());
        
        // Test with 0xABCD (43981) - exercises all 4 nibbles with non-zero high bits
        // >>12 = 0xA, >>8&0xF = 0xB, >>4&0xF = 0xC, &0xF = 0xD
        StringBuilder sb2 = new StringBuilder();
        boolean result2 = invokeAppendEscaped(ref, sb2, 0xABCD);
        assertTrue(result2);
        assertEquals("\\uABCD", sb2.toString());
        
        // Test with 0xFFFF (65535) - max char value, all nibbles = 0xF
        StringBuilder sb3 = new StringBuilder();
        boolean result3 = invokeAppendEscaped(ref, sb3, 0xFFFF);
        assertTrue(result3);
        assertEquals("\\uFFFF", sb3.toString());
    }

    @Test
    public void testAppendEscapedReturnsTrueForEscapedChars() {
        // Tests _appendEscaped returns true for escaped characters (kills BooleanFalseReturnValsMutator line 368)
        String content = "Test\u0001\u0002End"; // Control chars that get escaped (not \r or \n)
        ContentReference ref = ContentReference.construct(true, content, DEFAULT_CONFIG);
        String desc = ref.buildSourceDescription();
        // Both control chars should be escaped and method returns true
        assertTrue(desc.contains("\\u0001"));
        assertTrue(desc.contains("\\u0002"));
    }

    @Test
    public void testAppendEscapedReturnsFalseForCrLf() {
        // Tests _appendEscaped returns false for \r and \n (they are appended directly, not escaped)
        String content = "Line1\nLine2\rEnd";
        ContentReference ref = ContentReference.construct(true, content, DEFAULT_CONFIG);
        String desc = ref.buildSourceDescription();
        // \n and \r should NOT be escaped (method returns false for them)
        assertTrue(desc.contains("\n"));
        assertTrue(desc.contains("\r"));
        assertFalse(desc.contains("\\u000a")); // \n not escaped
        assertFalse(desc.contains("\\u000d")); // \r not escaped
    }

    @Test
    public void testTruncateOffsetsBoundaryConditions() {
        // Tests _truncateOffsets boundary mutations (lines 326, 328, 336)
        // Test: start < 0 -> becomes 0
        String content = "Hello World";
        ContentReference ref = ContentReference.construct(true, content, -1, 5, DEFAULT_CONFIG);
        String desc = ref.buildSourceDescription();
        assertTrue(desc.contains("\"Hello\"")); // start adjusted to 0, length 5

        // Test: start >= actualLength -> becomes actualLength (empty string)
        ref = ContentReference.construct(true, content, 20, 5, DEFAULT_CONFIG);
        desc = ref.buildSourceDescription();
        assertTrue(desc.contains("\"\"")); // start adjusted to length (11), empty

        // Test: length < 0 -> becomes maxLength (remaining from start)
        ref = ContentReference.construct(true, content, 6, -1, DEFAULT_CONFIG);
        desc = ref.buildSourceDescription();
        assertTrue(desc.contains("\"World\"")); // length adjusted to 5 (11-6)

        // Test: length > maxLength -> becomes maxLength
        ref = ContentReference.construct(true, content, 6, 100, DEFAULT_CONFIG);
        desc = ref.buildSourceDescription();
        assertTrue(desc.contains("\"World\"")); // length capped at 5
    }

    @Test
    public void testTruncateOffsetsExactBoundaryValues() {
        // Tests exact boundary values for _truncateOffsets conditionals
        // start == -1 (boundary for start < 0)
        String content = "ABC";
        ContentReference ref = ContentReference.construct(true, content, -1, 2, DEFAULT_CONFIG);
        String desc = ref.buildSourceDescription();
        assertTrue(desc.contains("\"AB\"")); // start becomes 0

        // start == actualLength (boundary for start >= actualLength)
        ref = ContentReference.construct(true, content, 3, 2, DEFAULT_CONFIG);
        desc = ref.buildSourceDescription();
        assertTrue(desc.contains("\"\"")); // start becomes 3 (length), empty

        // length == -1 (boundary for length < 0)
        ref = ContentReference.construct(true, content, 1, -1, DEFAULT_CONFIG);
        desc = ref.buildSourceDescription();
        assertTrue(desc.contains("\"BC\"")); // length becomes 2 (3-1)

        // length == maxLength (boundary for length > maxLength)
        ref = ContentReference.construct(true, content, 1, 2, DEFAULT_CONFIG);
        desc = ref.buildSourceDescription();
        assertTrue(desc.contains("\"BC\"")); // length stays 2
    }

    @Test
    public void testAppendSourceDescriptionBoundaryConditions() {
        // Tests appendSourceDescription conditional boundary mutations (lines 280, 289)
        // and math mutation (line 281: subtraction replaced with addition)
        
        // Test with offset == -1 (boundary for offset < 0 check)
        String content = "Hello World";
        ContentReference ref = ContentReference.construct(true, content, -1, 5, DEFAULT_CONFIG);
        String desc = ref.buildSourceDescription();
        assertTrue(desc.contains("\"Hello\""));

        // Test with offset == content.length() (boundary for offset >= length)
        ref = ContentReference.construct(true, content, 11, 5, DEFAULT_CONFIG);
        desc = ref.buildSourceDescription();
        assertTrue(desc.contains("\"\""));

        // Test with length == -1 (boundary for length < 0)
        ref = ContentReference.construct(true, content, 6, -1, DEFAULT_CONFIG);
        desc = ref.buildSourceDescription();
        assertTrue(desc.contains("\"World\""));

        // Test with length > remaining (tests the subtraction: actualLength - start)
        // If subtraction is mutated to addition, length would be wrong
        ref = ContentReference.construct(true, content, 6, 100, DEFAULT_CONFIG);
        desc = ref.buildSourceDescription();
        assertTrue(desc.contains("\"World\"")); // Should be "World" (5 chars), not something else
        assertFalse(desc.contains("World\"World")); // Would happen if addition instead of subtraction
    }

    @Test
    public void testHashCodeNotZeroForNonNullContent() {
        // Tests hashCode return value (kills PrimitiveReturnsMutator on hashCode line 417)
        ContentReference ref = ContentReference.construct(true, "test content", DEFAULT_CONFIG);
        int hash = ref.hashCode();
        assertNotEquals(0, hash); // Mutation would return 0
        assertEquals("test content".hashCode(), hash); // Objects.hashCode(_rawContent)
    }

    @Test
    public void testHashCodeZeroForNullContent() {
        // Tests hashCode for null content (UNKNOWN_CONTENT)
        ContentReference ref = ContentReference.unknown();
        int hash = ref.hashCode();
        assertEquals(0, hash); // Objects.hashCode(null) returns 0
    }

    @Test
    public void testHashCodeConsistencyWithEquals() {
        // hashCode contract: equal objects must have equal hashCodes
        String content = "same content";
        ContentReference ref1 = ContentReference.construct(true, content, DEFAULT_CONFIG);
        ContentReference ref2 = ContentReference.construct(true, content, DEFAULT_CONFIG);
        assertTrue(ref1.equals(ref2));
        assertEquals(ref1.hashCode(), ref2.hashCode());
        
        // Different content -> different hashCode (not required but likely)
        ContentReference ref3 = ContentReference.construct(true, "different", DEFAULT_CONFIG);
        assertFalse(ref1.equals(ref3));
        // hashCode can be same for different objects, but we verify it's not mutated to 0
        assertNotEquals(0, ref3.hashCode());
    }

    @Test
    public void testAppendEscapedWithHighUnicodeChars() {
        // Tests _appendEscaped with characters requiring all 4 shift operations
        // 0xABCD = 43981: >>12=0xA, >>8=0xAB&0xF=0xB, >>4=0xABC&0xF=0xC, &0xF=0xD
        ContentReference ref = ContentReference.construct(true, "test", DEFAULT_CONFIG);
        StringBuilder sb = new StringBuilder();
        boolean result = invokeAppendEscaped(ref, sb, 0xABCD);
        assertTrue(result);
        assertEquals("\\uABCD", sb.toString()); // Verifies all 4 shift-right operations work correctly
    }

    @Test
    public void testAppendEscapedWithMaxControlChar() {
        // Tests _appendEscaped with 0xFFFF (max char value) - all 4 nibbles = 0xF
        ContentReference ref = ContentReference.construct(true, "test", DEFAULT_CONFIG);
        StringBuilder sb = new StringBuilder();
        boolean result = invokeAppendEscaped(ref, sb, 0xFFFF);
        assertTrue(result);
        assertEquals("\\uFFFF", sb.toString());
    }

    @Test
    public void testTruncateOffsetsWithZeroLength() {
        // Tests _truncateOffsets with length = 0 (boundary for length > maxLength when maxLength = 0)
        String content = "Hello";
        ContentReference ref = ContentReference.construct(true, content, 5, 0, DEFAULT_CONFIG);
        String desc = ref.buildSourceDescription();
        assertTrue(desc.contains("\"\"")); // Empty string at end
    }

    @Test
    public void testTruncateOffsetsWithZeroStartAndZeroLength() {
        // Tests _truncateOffsets with start=0, length=0
        String content = "Hello";
        ContentReference ref = ContentReference.construct(true, content, 0, 0, DEFAULT_CONFIG);
        String desc = ref.buildSourceDescription();
        assertTrue(desc.contains("\"\""));
    }

    // ===== NEW TESTS TO KILL SURVIVING MUTATIONS =====

    @Test
    public void testTruncateOffsetsStartZeroBoundary() {
        // Tests start == 0 boundary for "start < 0" conditional (line 326)
        // If mutated to "start <= 0", start=0 would be incorrectly set to 0 (no-op but different path)
        // Content "ABC", start=0, length=2 -> should give "AB"
        String content = "ABC";
        ContentReference ref = ContentReference.construct(true, content, 0, 2, DEFAULT_CONFIG);
        String desc = ref.buildSourceDescription();
        assertTrue(desc.contains("\"AB\""));
    }

    @Test
    public void testTruncateOffsetsStartEqualsActualLengthBoundary() {
        // Tests start == actualLength boundary for "start >= actualLength" conditional (line 328)
        // If mutated to "start > actualLength", start=3 would not be adjusted for content length 3
        String content = "ABC";
        ContentReference ref = ContentReference.construct(true, content, 3, 2, DEFAULT_CONFIG);
        String desc = ref.buildSourceDescription();
        assertTrue(desc.contains("\"\"")); // start adjusted to 3 (actualLength), empty result
    }

    @Test
    public void testTruncateOffsetsLengthZeroBoundary() {
        // Tests length == 0 boundary for "length < 0" conditional (line 336)
        // If mutated to "length <= 0", length=0 would be incorrectly adjusted to maxLength
        String content = "ABC";
        ContentReference ref = ContentReference.construct(true, content, 1, 0, DEFAULT_CONFIG);
        String desc = ref.buildSourceDescription();
        assertTrue(desc.contains("\"\"")); // length=0 should give empty string
    }

    @Test
    public void testTruncateOffsetsLengthEqualsMaxLengthBoundary() {
        // Tests length == maxLength boundary for "length > maxLength" conditional (line 336)
        // If mutated to "length >= maxLength", length=2 (maxLength=2) would be incorrectly adjusted
        String content = "ABCD";
        ContentReference ref = ContentReference.construct(true, content, 1, 3, DEFAULT_CONFIG);
        String desc = ref.buildSourceDescription();
        assertTrue(desc.contains("\"BCD\"")); // length=3, maxLength=3 (4-1), should stay 3
    }

    @Test
    public void testAppendSourceDescriptionTruncationBoundary() {
        // Tests offsets[1] == maxLen boundary for truncation message (line ~280)
        // If "offsets[1] > maxLen" mutated to "offsets[1] >= maxLen", truncation message would appear when equal
        // Use content where length exactly equals maxRawContentLength
        // We can't easily change maxRawContentLength, so test with small content where no truncation occurs
        String content = "ABCDEF";
        ContentReference ref = ContentReference.construct(true, content, 0, 6, DEFAULT_CONFIG);
        String desc = ref.buildSourceDescription();
        assertTrue(desc.contains("\"ABCDEF\""));
        assertFalse(desc.contains("[truncated"));
    }

    @Test
    public void testTruncateOffsetsStartNegativeOneBoundary() {
        // Tests start == -1 for "start < 0" (line 326)
        // Boundary value -1 should be adjusted to 0
        String content = "Hello";
        ContentReference ref = ContentReference.construct(true, content, -1, 3, DEFAULT_CONFIG);
        String desc = ref.buildSourceDescription();
        assertTrue(desc.contains("\"Hel\""));
    }

    @Test
    public void testTruncateOffsetsLengthNegativeOneBoundary() {
        // Tests length == -1 for "length < 0" (line 336)
        // Boundary value -1 should be adjusted to maxLength
        String content = "Hello World";
        ContentReference ref = ContentReference.construct(true, content, 6, -1, DEFAULT_CONFIG);
        String desc = ref.buildSourceDescription();
        assertTrue(desc.contains("\"World\"")); // From index 6 to end (5 chars)
    }

    @Test
    public void testAppendSourceDescriptionOffsetNegativeOneBoundary() {
        // Tests offset == -1 boundary in appendSourceDescription (line ~280)
        String content = "Hello World";
        ContentReference ref = ContentReference.construct(true, content, -1, 5, DEFAULT_CONFIG);
        String desc = ref.buildSourceDescription();
        assertTrue(desc.contains("\"Hello\""));
    }

    @Test
    public void testAppendSourceDescriptionOffsetEqualsLengthBoundary() {
        // Tests offset == content.length() boundary (line ~280)
        String content = "Hello";
        ContentReference ref = ContentReference.construct(true, content, 5, 3, DEFAULT_CONFIG);
        String desc = ref.buildSourceDescription();
        assertTrue(desc.contains("\"\"")); // offset at end gives empty
    }

    @Test
    public void testAppendSourceDescriptionLengthNegativeOneBoundary() {
        // Tests length == -1 boundary in appendSourceDescription (line ~289)
        String content = "Hello World";
        ContentReference ref = ContentReference.construct(true, content, 6, -1, DEFAULT_CONFIG);
        String desc = ref.buildSourceDescription();
        assertTrue(desc.contains("\"World\""));
    }

    @Test
    public void testTruncateOffsetsWithStartAtZeroAndLengthAtMax() {
        // Combined boundary test: start=0 (boundary for <), length=maxLength (boundary for >)
        String content = "ABCDEF";
        ContentReference ref = ContentReference.construct(true, content, 0, 6, DEFAULT_CONFIG);
        String desc = ref.buildSourceDescription();
        assertTrue(desc.contains("\"ABCDEF\""));
    }

    @Test
    public void testTruncateOffsetsWithStartAtActualLengthMinusOne() {
        // Tests start = actualLength - 1 (one before boundary)
        String content = "ABC";
        ContentReference ref = ContentReference.construct(true, content, 2, 2, DEFAULT_CONFIG);
        String desc = ref.buildSourceDescription();
        assertTrue(desc.contains("\"C\"")); // Only 1 char available from index 2
    }

    // Helper method to invoke protected _append method
    private int invokeAppend(ContentReference ref, StringBuilder sb, String content) {
        try {
            Method method = ContentReference.class.getDeclaredMethod("_append", StringBuilder.class, String.class);
            method.setAccessible(true);
            return (Integer) method.invoke(ref, sb, content);
        } catch (Exception e) {
            throw new RuntimeException("Failed to invoke _append", e);
        }
    }

    // Helper method to invoke protected _appendEscaped method
    private boolean invokeAppendEscaped(ContentReference ref, StringBuilder sb, int ctrlChar) {
        try {
            Method method = ContentReference.class.getDeclaredMethod("_appendEscaped", StringBuilder.class, int.class);
            method.setAccessible(true);
            return (Boolean) method.invoke(ref, sb, ctrlChar);
        } catch (Exception e) {
            throw new RuntimeException("Failed to invoke _appendEscaped", e);
        }
    }

    // Helper classes for testing unhandled types
    private static class CustomTextualObject {
        private final String content;
        CustomTextualObject(String content) { this.content = content; }
        @Override public String toString() { return content; }
    }

    private static class CustomBinaryObject {
        private final byte[] data;
        CustomBinaryObject(byte[] data) { this.data = data; }
    }

    private static class CustomObject {
        private final String value;
        CustomObject(String value) { this.value = value; }
        @Override public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof CustomObject)) return false;
            return value.equals(((CustomObject)o).value);
        }
        @Override public int hashCode() { return value.hashCode(); }
    }
}
