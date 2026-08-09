package tools.jackson.core.json;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.Test;

import tools.jackson.core.Base64Variants;
import tools.jackson.core.ErrorReportConfiguration;
import tools.jackson.core.JsonEncoding;
import tools.jackson.core.ObjectWriteContext;
import tools.jackson.core.SerializableString;
import tools.jackson.core.StreamReadConstraints;
import tools.jackson.core.StreamWriteConstraints;
import tools.jackson.core.StreamWriteFeature;
import tools.jackson.core.exc.StreamWriteException;
import tools.jackson.core.io.ContentReference;
import tools.jackson.core.io.IOContext;
import tools.jackson.core.io.SerializedString;
import tools.jackson.core.util.BufferRecycler;

public class WriterBasedJsonGeneratorTest {
    private static final int DEFAULT_STREAM_FEATURES =
            StreamWriteFeature.collectDefaults();
    private static final int DEFAULT_FORMAT_FEATURES =
            JsonWriteFeature.collectDefaults();

    private static final class Fixture {
        final StringWriter writer;
        final WriterBasedJsonGenerator generator;

        Fixture(int streamFeatures, int formatFeatures) {
            this(streamFeatures, formatFeatures, 0, '"');
        }

        Fixture(int streamFeatures, int formatFeatures, int maxNonEscaped,
                char quoteChar) {
            writer = new StringWriter();
            IOContext ioContext = new IOContext(
                    StreamReadConstraints.defaults(),
                    StreamWriteConstraints.defaults(),
                    ErrorReportConfiguration.defaults(),
                    new BufferRecycler(),
                    ContentReference.rawReference(writer),
                    false,
                    JsonEncoding.UTF8);
            generator = new WriterBasedJsonGenerator(
                    ObjectWriteContext.empty(),
                    ioContext,
                    streamFeatures,
                    formatFeatures,
                    writer,
                    null,
                    null,
                    null,
                    maxNonEscaped,
                    quoteChar);
        }

        String closeAndGet() {
            generator.close();
            return writer.toString();
        }

        String flushAndGet() {
            generator.flush();
            return writer.toString();
        }
    }

    private Fixture fixture() {
        return new Fixture(DEFAULT_STREAM_FEATURES, DEFAULT_FORMAT_FEATURES);
    }

    @Test
    public void writesNestedObjectAndArrayWithSeparators() {
        Fixture f = fixture();

        f.generator.writeStartObject();
        f.generator.writeStringProperty("name", "Ada");
        f.generator.writeNumberProperty("age", 37);
        f.generator.writeName("values");
        f.generator.writeStartArray();
        f.generator.writeBoolean(true);
        f.generator.writeNull();
        f.generator.writeEndArray();
        f.generator.writeEndObject();

        assertEquals("{\"name\":\"Ada\",\"age\":37,\"values\":[true,null]}",
                f.closeAndGet());
    }

    @Test
    public void escapesStringContentAndSupportsSerializableNames() {
        Fixture f = fixture();
        SerializableString name = new SerializedString("a\"b");

        f.generator.writeStartObject();
        f.generator.writeName(name);
        f.generator.writeString("line\nslash/quote\"");
        f.generator.writeEndObject();

        assertEquals("{\"a\\\"b\":\"line\\nslash/quote\\\"\"}",
                f.closeAndGet());
    }

    @Test
    public void escapesForwardSlashesWhenFeatureIsEnabled() {
        int formatFeatures = DEFAULT_FORMAT_FEATURES
                | JsonWriteFeature.ESCAPE_FORWARD_SLASHES.getMask();
        Fixture f = new Fixture(DEFAULT_STREAM_FEATURES, formatFeatures);

        f.generator.writeString("a/b");

        assertEquals("\"a\\/b\"", f.closeAndGet());
    }

    @Test
    public void writesNumbersAsStringsWhenConfigured() {
        int formatFeatures = DEFAULT_FORMAT_FEATURES
                | JsonWriteFeature.WRITE_NUMBERS_AS_STRINGS.getMask();
        Fixture f = new Fixture(DEFAULT_STREAM_FEATURES, formatFeatures);

        f.generator.writeStartArray();
        f.generator.writeNumber(-12);
        f.generator.writeNumber(1234567890123L);
        f.generator.writeNumber(new BigDecimal("1.25"));
        f.generator.writeEndArray();

        assertEquals("[\"-12\",\"1234567890123\",\"1.25\"]",
                f.closeAndGet());
    }

    @Test
    public void writesBigDecimalAsPlainWhenConfigured() {
        int streamFeatures = DEFAULT_STREAM_FEATURES
                | StreamWriteFeature.WRITE_BIGDECIMAL_AS_PLAIN.getMask();
        Fixture f = new Fixture(streamFeatures, DEFAULT_FORMAT_FEATURES);

        f.generator.writeNumber(new BigDecimal("1E+3"));

        assertEquals("1000", f.closeAndGet());
    }

    @Test
    public void writesReaderContentAndNullString() {
        Fixture f = fixture();

        f.generator.writeStartArray();
        f.generator.writeString(new StringReader("reader\nvalue"), 12);
        f.generator.writeString((String) null);
        f.generator.writeEndArray();

        assertEquals("[\"reader\\nvalue\",null]", f.closeAndGet());
    }

    @Test
    public void rejectsReaderThatProvidesTooFewCharacters() {
        Fixture f = fixture();

        try {
            f.generator.writeString(new StringReader("abc"), 4);
            fail("Expected an exception");
        } catch (StreamWriteException e) {
            assertTrue(e.getMessage().contains("Didn't read enough"));
        }
    }

    @Test
    public void writesBinaryDataUsingDefaultBase64Variant() {
        Fixture f = fixture();
        byte[] data = new byte[] {0, 1, 2, 3, 4};

        f.generator.writeBinary(Base64Variants.getDefaultVariant(),
                data, 0, data.length);

        assertEquals("\"" + Base64Variants.getDefaultVariant().encode(data) + "\"",
                f.closeAndGet());
    }

    @Test
    public void writesLargeRawTextAcrossBufferBoundaries() {
        Fixture f = fixture();
        char[] chars = new char[10000];
        Arrays.fill(chars, 'x');
        String value = new String(chars);

        f.generator.writeRaw(value);

        assertEquals(value, f.closeAndGet());
    }

    @Test
    public void closeAutomaticallyCompletesOpenContent() {
        Fixture f = fixture();

        f.generator.writeStartObject();
        f.generator.writeStringProperty("open", "content");

        assertEquals("{\"open\":\"content\"}", f.closeAndGet());
    }

    @Test
    public void reportsValueWrittenWherePropertyNameIsRequired() {
        Fixture f = fixture();

        f.generator.writeStartObject();

        try {
            f.generator.writeString("value");
            fail("Expected an exception");
        } catch (StreamWriteException e) {
            assertTrue(e.getMessage().contains("expecting a property name"));
        }
    }

    @Test
    public void reportsIncorrectEndContext() {
        Fixture f = fixture();

        try {
            f.generator.writeEndArray();
            fail("Expected an exception");
        } catch (StreamWriteException e) {
            assertTrue(e.getMessage().contains("Current context not Array"));
        }
    }

    @Test
    public void validatesRawStringRanges() {
        Fixture f = fixture();

        try {
            f.generator.writeRaw("abc", 2, 2);
            fail("Expected an exception");
        } catch (StreamWriteException e) {
            assertTrue(e.getMessage().contains("Invalid"));
        }
    }

    @Test
    public void unsupportedUtf8OperationsAreRejected() {
        Fixture f = fixture();

        try {
            f.generator.writeUTF8String(new byte[] {1, 2}, 0, 2);
            fail("Expected an exception");
        } catch (UnsupportedOperationException e) {
            assertTrue(e.getMessage().contains("not supported"));
        }
    }

    @Test
    public void reportsBufferedCharactersAndFlushesWriter() {
        Fixture f = fixture();

        f.generator.writeRaw("buffered");
        assertTrue(f.generator.streamWriteOutputBuffered() > 0);
        assertEquals("buffered", f.flushAndGet());
        assertEquals(0, f.generator.streamWriteOutputBuffered());
    }

    @Test
    public void writesCharacterArrayWithOffsetAndLength() {
        Fixture f = fixture();
        char[] chars = "prefix-value-suffix".toCharArray();

        f.generator.writeString(chars, 7, 5);

        assertEquals("\"value\"", f.closeAndGet());
    }

    @Test
    public void writesMultipleRootValuesUsingConfiguredSeparator() {
        StringWriter writer = new StringWriter();
        IOContext ioContext = new IOContext(
                StreamReadConstraints.defaults(),
                StreamWriteConstraints.defaults(),
                ErrorReportConfiguration.defaults(),
                new BufferRecycler(),
                ContentReference.rawReference(writer),
                false,
                JsonEncoding.UTF8);
        WriterBasedJsonGenerator generator = new WriterBasedJsonGenerator(
                ObjectWriteContext.empty(),
                ioContext,
                DEFAULT_STREAM_FEATURES,
                DEFAULT_FORMAT_FEATURES,
                writer,
                new SerializedString("|"),
                null,
                null,
                0,
                '"');

        generator.writeNumber(1);
        generator.writeNumber(2);
        generator.flush();

        assertEquals("1|2", writer.toString());
        generator.close();
    }
}
