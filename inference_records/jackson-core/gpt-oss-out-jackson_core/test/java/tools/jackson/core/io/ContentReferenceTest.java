package tools.jackson.core.io;

import org.junit.Test;
import static org.junit.Assert.*;
import java.io.File;
import java.net.URI;
import java.net.URL;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;
import tools.jackson.core.ErrorReportConfiguration;

/**
 * Test suite for {@link ContentReference}.  Existing tests are preserved
 * and additional focused tests added to kill surviving mutations.
 */
public class ContentReferenceTest {

    @Test
    public void testUnknownAndRedactedDifferentReferences() {
        ContentReference unknown = ContentReference.unknown();
        ContentReference redacted = ContentReference.redacted();

        assertNotSame(unknown, redacted);

        String unkDesc = unknown.buildSourceDescription();
        String redDesc = redacted.buildSourceDescription();

        assertEquals("UNKNOWN", unkDesc.trim());
        assertTrue(redDesc.startsWith(
            "REDACTED (`StreamReadFeature.INCLUDE_SOURCE_IN_LOCATION` disabled)"));
    }

    @Test
    public void testRawReferenceWithString() {
        String text = "hello";
        ContentReference cr = ContentReference.rawReference(true, text);
        String desc = cr.buildSourceDescription();

        assertTrue(desc.contains("(String)"));
        assertTrue(desc.contains("\"hello\""));
    }

    @Test
    public void testTruncationForCharSequence() {
        ErrorReportConfiguration cfg = ErrorReportConfiguration.defaults();
        int maxLen = cfg.getMaxRawContentLength();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < maxLen + 10; ++i) {
            sb.append('a');
        }
        char[] chars = sb.toString().toCharArray();

        ContentReference cr = ContentReference.construct(true, chars, 0, chars.length, cfg);
        String desc = cr.buildSourceDescription();

        // Expected snippet is first maxLen characters
        assertTrue(desc.contains("\"" + sb.substring(0, maxLen) + "\""));

        int truncatedCount = chars.length - maxLen;
        assertTrue(desc.contains("[truncated " + truncatedCount + " chars]"));
    }

    @Test
    public void testBuildSourceDescriptionForByteArray() {
        byte[] data = {1, 2, 3, 4, 5};
        ContentReference cr = ContentReference.construct(false, data, -1, -1,
                ErrorReportConfiguration.defaults());
        String desc = cr.buildSourceDescription();

        assertTrue(desc.contains("(byte[])"));
        assertTrue(desc.contains("[5 bytes]"));
    }

    @Test
    public void testEqualsWithSameRawContent() {
        String txt = "same";
        ContentReference cr1 = ContentReference.construct(true, txt, -1, -1,
                ErrorReportConfiguration.defaults());
        ContentReference cr2 = ContentReference.construct(true, txt, -1, -1,
                ErrorReportConfiguration.defaults());

        assertEquals(cr1, cr2);
        assertEquals(cr1.hashCode(), cr2.hashCode());
    }

    @Test
    public void testEqualsWithDifferentOffsets() {
        String txt = "text";
        ContentReference cr1 = ContentReference.construct(true, txt, 0, 2,
                ErrorReportConfiguration.defaults());
        ContentReference cr2 = ContentReference.construct(true, txt, 1, 2,
                ErrorReportConfiguration.defaults());

        assertNotEquals(cr1, cr2);
    }

    @Test
    public void testEqualsWithFiles() {
        File f1 = new File("src/test/java/tools/jackson/core/io/file.txt");
        File f2 = new File("src/test/java/tools/jackson/core/io/file.txt");

        ContentReference cr1 = ContentReference.construct(false, f1, -1, -1,
                ErrorReportConfiguration.defaults());
        ContentReference cr2 = ContentReference.construct(false, f2, -1, -1,
                ErrorReportConfiguration.defaults());

        assertEquals(cr1, cr2);
    }

    @Test
    public void testEqualsWithUrlsAndUris() throws Exception {
        URL url1 = new URL("http://example.com/test");
        URL url2 = new URL("http://example.com/test");

        ContentReference urcr1 = ContentReference.construct(false, url1, -1, -1,
                ErrorReportConfiguration.defaults());
        ContentReference urcr2 = ContentReference.construct(false, url2, -1, -1,
                ErrorReportConfiguration.defaults());

        assertEquals(urcr1, urcr2);

        URI uri1 = new URI("http://example.com/test");
        URI uri2 = new URI("http://example.com/test");

        ContentReference uc1 = ContentReference.construct(false, uri1, -1, -1,
                ErrorReportConfiguration.defaults());
        ContentReference uc2 = ContentReference.construct(false, uri2, -1, -1,
                ErrorReportConfiguration.defaults());

        assertEquals(uc1, uc2);
    }

    @Test
    public void testControlCharEscaping() {
        String text = "a\u0001b";
        ContentReference cr = ContentReference.construct(true, text, -1, -1,
                ErrorReportConfiguration.defaults());
        String desc = cr.buildSourceDescription();

        // Expected escaped sequence is \u0001 inside quoted string
        assertTrue(desc.contains("\"a\\u0001b\""));
    }

    /* ==============================
     * NEW TESTS ADDING BRANCH COVERAGE
     * ============================== */

    @Test
    public void testRawReferenceWithExistingContentReference() {
        ContentReference original = ContentReference.construct(true, "test", -1, -1,
                ErrorReportConfiguration.defaults());
        // This should return the same instance (branch where rawContent instanceof ContentReference)
        ContentReference result = ContentReference.rawReference(original);
        assertSame("rawReference should return the same instance when passed a ContentReference",
                   original, result);
    }

    @Test
    public void testTruncateOffsetsNegativeStart() {
        ErrorReportConfiguration cfg = ErrorReportConfiguration.defaults();
        char[] arr = new char[] {'a', 'b', 'c'};
        // Negative start should be clamped to 0, length truncated to maxRawContentLength if needed
        ContentReference cr = ContentReference.construct(true, arr, -5, 10, cfg);
        String desc = cr.buildSourceDescription();
        assertTrue("Description should contain full content when start negative",
                   desc.contains("\"abc\""));
    }

    @Test
    public void testTruncateOffsetsStartBeyondActualLength() {
        ErrorReportConfiguration cfg = ErrorReportConfiguration.defaults();
        char[] arr = new char[] {'a', 'b', 'c'};
        // Start beyond actual length -> empty snippet
        ContentReference cr = ContentReference.construct(true, arr, 10, 5, cfg);
        String desc = cr.buildSourceDescription();
        assertTrue("Description should contain empty string when start beyond length",
                   desc.contains("\"\""));
    }

    @Test
    public void testTruncateOffsetsNegativeLength() {
        ErrorReportConfiguration cfg = ErrorReportConfiguration.defaults();
        char[] arr = new char[] {'a', 'b', 'c', 'd'};
        // Negative length should be treated as full content up to maxRawContentLength
        ContentReference cr = ContentReference.construct(true, arr, 0, -5, cfg);
        String desc = cr.buildSourceDescription();
        assertTrue("Description should contain full content when length negative",
                   desc.contains("\"abcd\""));
    }

    @Test
    public void testAppendSourceDescriptionEscapedControls() {
        ErrorReportConfiguration cfg = ErrorReportConfiguration.defaults();
        String textWithNewline = "Line1\nLine2\rLine3";
        ContentReference cr = ContentReference.construct(true, textWithNewline, -1, -1, cfg);
        String desc = cr.buildSourceDescription();

        // Should contain actual newline and carriage return characters without escape sequences
        assertTrue("Desc should contain literal newline",
                   desc.contains("\n"));
        assertTrue("Desc should contain literal carriage return",
                   desc.contains("\r"));
        assertFalse("Desc should not escape newline or carriage return",
                    desc.contains("\\u000a") || desc.contains("\\u000d"));
    }

    @Test
    public void testAppendSourceDescriptionWithClassType() {
        ErrorReportConfiguration cfg = ErrorReportConfiguration.defaults();
        ContentReference cr = ContentReference.construct(false, String.class, -1, -1, cfg);
        String desc = cr.buildSourceDescription();

        assertTrue(desc.startsWith("(String)"));
        // No content snippet expected for class type
        assertFalse(desc.contains("\""));
    }

    @Test
    public void testByteArrayTextualTruncation() {
        ErrorReportConfiguration cfg = ErrorReportConfiguration.defaults();
        int maxLen = cfg.getMaxRawContentLength();

        byte[] bytes = new byte[maxLen + 20];
        for (int i = 0; i < bytes.length; ++i) {
            bytes[i] = (byte) 'c';
        }

        ContentReference cr = ContentReference.construct(true, bytes, 0, bytes.length, cfg);
        String desc = cr.buildSourceDescription();

        // Snippet should be first maxLen characters
        assertTrue(desc.contains("\"" + repeat('c', maxLen) + "\""));

        int truncatedCount = bytes.length - maxLen;
        assertTrue(desc.contains("[truncated " + truncatedCount + " bytes]"));
    }

    @Test
    public void testAppendSourceDescriptionEscapesTab() {
        ErrorReportConfiguration cfg = ErrorReportConfiguration.defaults();
        String text = "a\tb";
        ContentReference cr = ContentReference.construct(true, text, -1, -1, cfg);
        String desc = cr.buildSourceDescription();

        assertTrue(desc.contains("\"a\\u0009b\""));
    }

    @Test
    public void testBuildSourceDescriptionForCharArrayNonTextual() {
        char[] data = {'x', 'y'};
        ContentReference cr = ContentReference.construct(false, data, -1, -1,
                ErrorReportConfiguration.defaults());
        String desc = cr.buildSourceDescription();
        assertEquals("(char[])", desc);
    }

    @Test
    public void testBuildSourceDescriptionWithNonTextualObject() {
        Integer num = 123;
        ContentReference cr = ContentReference.construct(true, num, -1, -1,
                ErrorReportConfiguration.defaults());
        String desc = cr.buildSourceDescription();
        assertEquals("(Integer)", desc);
    }

    @Test
    public void testEqualityForNullRawContentInstances() {
        ContentReference cr1 = ContentReference.construct(false, (Object) null, -1, -1,
                ErrorReportConfiguration.defaults());
        ContentReference cr2 = ContentReference.construct(false, (Object) null, -1, -1,
                ErrorReportConfiguration.defaults());

        assertEquals(cr1, cr2);
    }

    @Test
    public void testEqualsWithNullAndNonNull() {
        ContentReference cr1 = ContentReference.construct(false, (Object) null, -1, -1,
                ErrorReportConfiguration.defaults());
        ContentReference cr2 = ContentReference.construct(true, "foo", -1, -1,
                ErrorReportConfiguration.defaults());

        assertNotEquals(cr1, cr2);
    }

    @Test
    public void testEqualityWithDifferentStringReferences() {
        ContentReference cr1 = ContentReference.construct(true, new String("foo"), -1, -1,
                ErrorReportConfiguration.defaults());
        ContentReference cr2 = ContentReference.construct(true, new String("foo"), -1, -1,
                ErrorReportConfiguration.defaults());

        assertNotEquals(cr1, cr2);
    }

    @Test
    public void testAppendMethodReturnLength() throws Exception {
        // Test that protected _append returns actual content length (not 0)
        Method append = ContentReference.class.getDeclaredMethod("_append",
                StringBuilder.class, String.class);
        append.setAccessible(true);

        ContentReference cr = ContentReference.construct(true, "abc", -1, -1,
                ErrorReportConfiguration.defaults());
        StringBuilder sb = new StringBuilder();
        int len = (Integer) append.invoke(cr, sb, "abc");
        assertEquals(3, len);
        assertEquals("\"abc\"", sb.toString());
    }

    @Test
    public void testAppendEscapedControlCharShiftMutation() throws Exception {
        // Verify that control character TAB is escaped correctly,
        // exposing any shift-mutated implementation.
        Method append = ContentReference.class.getDeclaredMethod("_append",
                StringBuilder.class, String.class);
        append.setAccessible(true);

        ContentReference cr = ContentReference.construct(true, "a\tb", -1, -1,
                ErrorReportConfiguration.defaults());
        StringBuilder sb = new StringBuilder();
        int len = (Integer) append.invoke(cr, sb, "a\tb");
        assertEquals(3, len);
        assertEquals("\"a\\u0009b\"", sb.toString());
    }

    @Test
    public void testHashCodeNotZeroForNonNullRawContent() {
        // Use a non-null raw content whose hash code is guaranteed to be non-zero.
        Integer num = 123;
        ContentReference cr = ContentReference.construct(true, num, -1, -1,
                ErrorReportConfiguration.defaults());
        int hc = cr.hashCode();
        assertNotEquals(0, hc);
    }

    @Test
    public void testTruncateOffsetsLengthExceedsActualAndMax() {
        // Use a configuration with a very small max raw content length to force truncation.
        ErrorReportConfiguration cfg = createSmallConfig(5);

        char[] arr = "abcdefghij".toCharArray(); // length 10
        ContentReference cr = ContentReference.construct(true, arr, 0, 15, cfg); // length > actual
        String desc = cr.buildSourceDescription();

        assertTrue(desc.contains("\"abcde\"")); // first 5 chars due to maxLen
        assertTrue(desc.contains("[truncated 5 chars]"));
    }

    // helper method to repeat a character
    private static String repeat(char c, int count) {
        StringBuilder sb = new StringBuilder(count);
        for (int i = 0; i < count; ++i) {
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     * Creates an {@link ErrorReportConfiguration} with a small maximum raw content length
     * using reflection to access the package‑private {@code Builder} constructor.
     */
    private static ErrorReportConfiguration createSmallConfig(int maxLen) {
        try {
            Constructor<ErrorReportConfiguration.Builder> ctor =
                    ErrorReportConfiguration.Builder.class.getDeclaredConstructor();
            ctor.setAccessible(true);
            return ctor.newInstance()
                    .maxRawContentLength(maxLen)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
