package tools.jackson.core.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import org.junit.Test;

import tools.jackson.core.ErrorReportConfiguration;

public class ContentReferenceTest {

    @Test
    public void unknownAndRedactedReferencesHaveExpectedDescriptions() {
        assertSame(ContentReference.unknown(), ContentReference.unknown());
        assertSame(ContentReference.redacted(), ContentReference.redacted());
        assertEquals("UNKNOWN", ContentReference.unknown().buildSourceDescription());
        assertEquals(
                "REDACTED (`StreamReadFeature.INCLUDE_SOURCE_IN_LOCATION` disabled)",
                ContentReference.redacted().buildSourceDescription());
    }

    @Test
    public void accessorsExposeConstructionValues() {
        Object raw = new Object();
        ContentReference reference = ContentReference.construct(
                true, raw, 3, 7, ErrorReportConfiguration.defaults());

        assertTrue(reference.hasTextualContent());
        assertSame(raw, reference.getRawContent());
        assertEquals(3, reference.contentOffset());
        assertEquals(7, reference.contentLength());
    }

    @Test
    public void rawReferenceAvoidsNestingExistingContentReference() {
        Object raw = new Object();
        ContentReference reference = ContentReference.rawReference(true, raw);

        assertTrue(reference.hasTextualContent());
        assertSame(raw, reference.getRawContent());
        assertEquals(-1, reference.contentOffset());
        assertEquals(-1, reference.contentLength());
        assertSame(reference, ContentReference.rawReference(false, reference));
    }

    @Test
    public void textualDescriptionsIncludeTypeAndContent() {
        assertEquals(
                "(String)\"hello\"",
                ContentReference.construct(
                        true, "hello", ErrorReportConfiguration.defaults())
                        .buildSourceDescription());

        assertEquals(
                "(char[])\"2345\"",
                ContentReference.construct(
                        true, "0123456789".toCharArray(), 2, 4,
                        ErrorReportConfiguration.defaults())
                        .buildSourceDescription());

        byte[] content = "abcdef".getBytes(StandardCharsets.UTF_8);
        assertEquals(
                "(byte[])\"bcd\"",
                ContentReference.construct(
                        true, content, 1, 3,
                        ErrorReportConfiguration.defaults())
                        .buildSourceDescription());
    }

    @Test
    public void binaryByteArrayDescriptionIncludesLength() {
        byte[] content = { 1, 2, 3, 4 };

        assertEquals(
                "(byte[])[4 bytes]",
                ContentReference.construct(
                        false, content, ErrorReportConfiguration.defaults())
                        .buildSourceDescription());
        assertEquals(
                "(byte[])[2 bytes]",
                ContentReference.construct(
                        false, content, 1, 2,
                        ErrorReportConfiguration.defaults())
                        .buildSourceDescription());
    }

    @Test
    public void textualDescriptionTruncatesLongContent() {
        StringBuilder source = new StringBuilder();
        for (int i = 0; i < 600; ++i) {
            source.append('x');
        }

        String description = ContentReference.construct(
                true, source.toString(), ErrorReportConfiguration.defaults())
                .buildSourceDescription();

        assertTrue(description.startsWith("(String)\""));
        assertTrue(description.contains("[truncated "));
        assertTrue(description.endsWith(" chars]"));
        assertTrue(description.length() < source.length() + 30);
    }

    @Test
    public void controlCharactersAreEscapedExceptLineBreaks() {
        ContentReference reference = ContentReference.construct(
                true, "a\tb\u0001c\nd\re",
                ErrorReportConfiguration.defaults());

        assertEquals(
                "(String)\"a\\u0009b\\u0001c\nd\re\"",
                reference.buildSourceDescription());
    }

    @Test
    public void invalidOffsetsAreClampedToActualContent() {
        ContentReference negativeOffset = ContentReference.construct(
                true, "abc", -5, -1, ErrorReportConfiguration.defaults());
        ContentReference excessiveOffset = ContentReference.construct(
                true, "abc", 20, 4, ErrorReportConfiguration.defaults());

        assertEquals("(String)\"abc\"", negativeOffset.buildSourceDescription());
        assertEquals("(String)\"\"", excessiveOffset.buildSourceDescription());
    }

    @Test
    public void nonContentSourcesOnlyIncludeTheirType() {
        assertEquals(
                "(Object)",
                ContentReference.construct(
                        true, new Object(), ErrorReportConfiguration.defaults())
                        .buildSourceDescription());
        assertEquals(
                "(Object)",
                ContentReference.construct(
                        false, new Object(), ErrorReportConfiguration.defaults())
                        .buildSourceDescription());
        assertEquals(
                "(String)",
                ContentReference.construct(
                        true, String.class, ErrorReportConfiguration.defaults())
                        .buildSourceDescription());
        assertEquals(
                "(File)",
                ContentReference.construct(
                        true, new File("input.txt"),
                        ErrorReportConfiguration.defaults())
                        .buildSourceDescription());
    }

    @Test
    public void appendSourceDescriptionReturnsProvidedBuilder() {
        ContentReference reference = ContentReference.construct(
                true, "value", ErrorReportConfiguration.defaults());
        StringBuilder builder = new StringBuilder("prefix:");

        assertSame(builder, reference.appendSourceDescription(builder));
        assertEquals("prefix:(String)\"value\"", builder.toString());

        builder.setLength(0);
        builder.append("prefix:");
        assertSame(builder, ContentReference.unknown().appendSourceDescription(builder));
        assertEquals("prefix:UNKNOWN", builder.toString());
    }

    @Test
    public void equalityUsesIdentityForOrdinaryRawContent() {
        String first = new String("same");
        String second = new String("same");

        ContentReference firstReference = ContentReference.construct(
                true, first, 1, 2, ErrorReportConfiguration.defaults());
        ContentReference sameRawReference = ContentReference.construct(
                false, first, 1, 2, ErrorReportConfiguration.defaults());
        ContentReference differentRawReference = ContentReference.construct(
                true, second, 1, 2, ErrorReportConfiguration.defaults());
        ContentReference differentRange = ContentReference.construct(
                true, first, 1, 3, ErrorReportConfiguration.defaults());

        assertEquals(firstReference, sameRawReference);
        assertFalse(firstReference.equals(differentRawReference));
        assertFalse(firstReference.equals(differentRange));
        assertFalse(firstReference.equals(null));
        assertFalse(firstReference.equals("not a content reference"));
        assertEquals(firstReference.hashCode(), sameRawReference.hashCode());
    }

    @Test
    public void equalityUsesValueEqualityForSpecialSources() throws Exception {
        assertEquals(
                ContentReference.rawReference(new File("sample-input.txt")),
                ContentReference.rawReference(new File("sample-input.txt")));
        assertEquals(
                ContentReference.rawReference(URI.create("file:///tmp/sample-input.txt")),
                ContentReference.rawReference(URI.create("file:///tmp/sample-input.txt")));
        assertEquals(
                ContentReference.rawReference(new URL("file:/tmp/sample-input.txt")),
                ContentReference.rawReference(new URL("file:/tmp/sample-input.txt")));

        assertFalse(
                ContentReference.rawReference(new File("one.txt"))
                        .equals(ContentReference.rawReference(new File("two.txt"))));
        assertFalse(
                ContentReference.rawReference(URI.create("file:///tmp/one.txt"))
                        .equals(ContentReference.rawReference(URI.create("file:///tmp/two.txt"))));
        assertFalse(
                ContentReference.rawReference(new URL("file:/tmp/one.txt"))
                        .equals(ContentReference.rawReference(new URL("file:/tmp/two.txt"))));
    }

    @Test
    public void serializationResolvesToUnknownReference()
            throws IOException, ClassNotFoundException {
        ContentReference original = ContentReference.construct(
                true, "sensitive content", 0, 7,
                ErrorReportConfiguration.defaults());

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        ObjectOutputStream output = new ObjectOutputStream(bytes);
        output.writeObject(original);
        output.close();

        ObjectInputStream input = new ObjectInputStream(
                new ByteArrayInputStream(bytes.toByteArray()));
        Object restored = input.readObject();

        assertSame(ContentReference.unknown(), restored);
        assertNotSame(original, restored);
    }

    @Test
    public void equalityHandlesSelfNullAndDifferentRanges() {
        ContentReference reference = ContentReference.construct(
                false, new Object(), ErrorReportConfiguration.defaults());
        ContentReference nullRaw = ContentReference.construct(
                false, null, ErrorReportConfiguration.defaults());
        ContentReference anotherNullRaw = ContentReference.construct(
                true, null, ErrorReportConfiguration.defaults());
        String raw = "value";
        ContentReference ranged = ContentReference.construct(
                true, raw, 1, 2, ErrorReportConfiguration.defaults());
        ContentReference differentOffset = ContentReference.construct(
                true, raw, 2, 2, ErrorReportConfiguration.defaults());

        assertTrue(reference.equals(reference));
        assertTrue(nullRaw.equals(anotherNullRaw));
        assertFalse(nullRaw.equals(reference));
        assertFalse(reference.equals(nullRaw));
        assertFalse(ranged.equals(differentOffset));
    }

    @Test
    public void classAndCharSequenceDescriptionsUseExpectedNames() {
        assertEquals(
                "(tools.jackson.core.io.ContentReferenceTest)",
                ContentReference.construct(
                        true, ContentReferenceTest.class,
                        ErrorReportConfiguration.defaults())
                        .buildSourceDescription());

        StringBuilder content = new StringBuilder("builder-content");
        assertEquals(
                "(StringBuilder)\"content\"",
                ContentReference.construct(
                        true, content, 8, 7,
                        ErrorReportConfiguration.defaults())
                        .buildSourceDescription());
    }

    @Test
    public void truncatedByteArrayReportsByteCount() {
        byte[] content = new byte[600];
        Arrays.fill(content, (byte) 'x');

        String description = ContentReference.construct(
                true, content, ErrorReportConfiguration.defaults())
                .buildSourceDescription();

        assertTrue(description.startsWith("(byte[])\""));
        assertTrue(description.contains("[truncated "));
        assertTrue(description.endsWith(" bytes]"));
    }

    @Test
    public void binaryByteArrayWithNegativeOffsetUsesConfiguredLength() {
        byte[] content = { 1, 2, 3, 4, 5 };
        assertEquals(
                "(byte[])[2 bytes]",
                ContentReference.construct(
                        false, content, -3, 2,
                        ErrorReportConfiguration.defaults())
                        .buildSourceDescription());
    }

    @Test
    public void appendReturnsNumberOfCharactersAppended() {
        ExposedContentReference reference = new ExposedContentReference();
        StringBuilder builder = new StringBuilder("prefix");

        assertEquals(3, reference.append(builder, "abc"));
        assertEquals("prefix\"abc\"", builder.toString());
    }

    @Test
    public void escapedCharactersUseFourHexadecimalDigits() {
        ExposedContentReference reference = new ExposedContentReference();
        StringBuilder builder = new StringBuilder();

        assertTrue(reference.appendEscaped(builder, 0x1234));
        assertEquals("\\u1234", builder.toString());
    }

    @Test
    public void carriageReturnAndLineFeedAreNotEscaped() {
        ExposedContentReference reference = new ExposedContentReference();
        StringBuilder carriageReturn = new StringBuilder();
        StringBuilder lineFeed = new StringBuilder();

        assertFalse(reference.appendEscaped(carriageReturn, '\r'));
        assertFalse(reference.appendEscaped(lineFeed, '\n'));
        assertEquals("", carriageReturn.toString());
        assertEquals("", lineFeed.toString());
    }

    @Test
    public void truncationClampsLengthBeyondActualContent() {
        ExposedContentReference reference = new ExposedContentReference();
        int[] offsets = { 1, 20 };

        assertEquals("bcd", reference.truncate("abcd", offsets, 20));
        assertEquals(1, offsets[0]);
        assertEquals(3, offsets[1]);
    }

    @Test
    public void truncationClampsNegativeOffsetAndLength() {
        ExposedContentReference reference = new ExposedContentReference();
        int[] offsets = { -2, -1 };

        assertEquals("abcd", reference.truncate("abcd", offsets, 20));
        assertEquals(0, offsets[0]);
        assertEquals(4, offsets[1]);
    }

    @Test
    public void configuredMaximumControlsTruncation() {
        ErrorReportConfiguration configuration = ErrorReportConfiguration.builder()
                .maxRawContentLength(5)
                .build();

        assertEquals(
                "(String)\"12345\"",
                ContentReference.construct(true, "12345", configuration)
                        .buildSourceDescription());
        assertEquals(
                "(String)\"12345\"[truncated 2 chars]",
                ContentReference.construct(true, "1234567", configuration)
                        .buildSourceDescription());
    }

    @Test
    public void hashCodeUsesRawContentHashCode() {
        Object raw = new Object();
        assertEquals(
                raw.hashCode(),
                ContentReference.rawReference(raw).hashCode());
    }

    @Test
    public void truncationPreservesExactBoundaryValues() {
        ExposedContentReference reference = new ExposedContentReference();

        int[] zeroLength = { 1, 0 };
        assertEquals("", reference.truncate("abcd", zeroLength, 20));
        assertEquals(1, zeroLength[0]);
        assertEquals(0, zeroLength[1]);

        int[] exactPhysicalLength = { 1, 3 };
        assertEquals("bcd", reference.truncate("abcd", exactPhysicalLength, 20));
        assertEquals(1, exactPhysicalLength[0]);
        assertEquals(3, exactPhysicalLength[1]);

        int[] exactEndOffset = { 4, 0 };
        assertEquals("", reference.truncate("abcd", exactEndOffset, 20));
        assertEquals(4, exactEndOffset[0]);
        assertEquals(0, exactEndOffset[1]);
    }

    @Test
    public void truncationUsesMaximumAtExactAndJustBeyondBoundaries() {
        ErrorReportConfiguration configuration = ErrorReportConfiguration.builder()
                .maxRawContentLength(3)
                .build();

        assertEquals(
                "(String)\"abc\"",
                ContentReference.construct(true, "abc", configuration)
                        .buildSourceDescription());
        assertEquals(
                "(String)\"abc\"[truncated 1 chars]",
                ContentReference.construct(true, "abcd", configuration)
                        .buildSourceDescription());

        assertEquals(
                "(char[])\"bcd\"",
                ContentReference.construct(
                        true, "abcd".toCharArray(), 1, 3, configuration)
                        .buildSourceDescription());

        byte[] bytes = "abcd".getBytes(StandardCharsets.UTF_8);
        assertEquals(
                "(byte[])\"bcd\"",
                ContentReference.construct(true, bytes, 1, 3, configuration)
                        .buildSourceDescription());
    }

    @Test
    public void truncationAdjustsOffsetsForEverySupportedContentKind() {
        ExposedContentReference reference = new ExposedContentReference();

        int[] chars = { -1, -1 };
        assertEquals("abcd", reference.truncate("abcd", chars, 20));
        assertEquals(0, chars[0]);
        assertEquals(4, chars[1]);

        int[] charArray = { 2, 99 };
        assertEquals("cd", reference.truncate("abcd".toCharArray(), charArray, 20));
        assertEquals(2, charArray[0]);
        assertEquals(2, charArray[1]);

        int[] bytes = { 0, 99 };
        assertEquals("abcd", reference.truncate(
                "abcd".getBytes(StandardCharsets.UTF_8), bytes, 20));
        assertEquals(0, bytes[0]);
        assertEquals(4, bytes[1]);
    }

    @Test
    public void truncationKeepsAllInclusiveOffsetBoundariesUnchanged() {
        ExposedContentReference reference = new ExposedContentReference();

        int[] startAtZero = { 0, 4 };
        assertEquals("abcd", reference.truncate("abcd", startAtZero, 20));
        assertEquals(0, startAtZero[0]);
        assertEquals(4, startAtZero[1]);

        int[] startAtPhysicalEnd = { 4, 0 };
        assertEquals("", reference.truncate("abcd", startAtPhysicalEnd, 20));
        assertEquals(4, startAtPhysicalEnd[0]);
        assertEquals(0, startAtPhysicalEnd[1]);

        int[] lengthAtZero = { 2, 0 };
        assertEquals("", reference.truncate("abcd", lengthAtZero, 20));
        assertEquals(2, lengthAtZero[0]);
        assertEquals(0, lengthAtZero[1]);

        int[] lengthAtRemainingContent = { 2, 2 };
        assertEquals("cd", reference.truncate("abcd", lengthAtRemainingContent, 20));
        assertEquals(2, lengthAtRemainingContent[0]);
        assertEquals(2, lengthAtRemainingContent[1]);
    }

    @Test
    public void appendSourceDescriptionDoesNotMarkExactMaximumAsTruncated() {
        ErrorReportConfiguration configuration = ErrorReportConfiguration.builder()
                .maxRawContentLength(3)
                .build();

        assertEquals(
                "(String)\"abc\"",
                ContentReference.construct(true, "abc", configuration)
                        .buildSourceDescription());
        assertEquals(
                "(String)\"\"[truncated 3 chars]",
                ContentReference.construct(true, "abc", 0, 3,
                        ErrorReportConfiguration.builder()
                                .maxRawContentLength(0)
                                .build())
                        .buildSourceDescription());
    }

    @Test
    public void appendSourceDescriptionMarksOnlyContentBeyondMaximum() {
        ErrorReportConfiguration configuration = ErrorReportConfiguration.builder()
                .maxRawContentLength(2)
                .build();

        assertEquals(
                "(String)\"ab\"",
                ContentReference.construct(true, "ab", configuration)
                        .buildSourceDescription());
        assertEquals(
                "(String)\"ab\"[truncated 1 chars]",
                ContentReference.construct(true, "abc", configuration)
                        .buildSourceDescription());
    }

    private static class ExposedContentReference extends ContentReference {
        ExposedContentReference() {
            super(true, null, ErrorReportConfiguration.defaults());
        }

        int append(StringBuilder builder, String content) {
            return _append(builder, content);
        }

        boolean appendEscaped(StringBuilder builder, int character) {
            return _appendEscaped(builder, character);
        }

        String truncate(CharSequence content, int[] offsets, int maximum) {
            return _truncate(content, offsets, maximum);
        }

        String truncate(char[] content, int[] offsets, int maximum) {
            return _truncate(content, offsets, maximum);
        }

        String truncate(byte[] content, int[] offsets, int maximum) {
            return _truncate(content, offsets, maximum);
        }
    }
}
