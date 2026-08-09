package tools.jackson.core.json;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import org.mockito.Mockito;
import static org.mockito.Mockito.*;

import tools.jackson.core.*;
import tools.jackson.core.exc.StreamWriteException;
import tools.jackson.core.io.IOContext;
import tools.jackson.core.ObjectWriteContext;
import tools.jackson.core.SerializableString;
import tools.jackson.core.Base64Variant;
import tools.jackson.core.json.JsonWriteFeature;
import tools.jackson.core.io.SerializedString;
import tools.jackson.core.io.CharacterEscapes;
import tools.jackson.core.util.DefaultPrettyPrinter;

/**
 * Tests for {@link UTF8JsonGenerator}.
 *
 * The original test class missed some closing calls (e.g. {@code writeEndObject()})
 * which caused the output to be incomplete and the tests to fail.
 */
public class UTF8JsonGeneratorTest {

    private IOContext ioCtxt;
    private ObjectWriteContext objectCtx;

    @Before
    public void setUp() {
        ioCtxt = mock(IOContext.class);
        when(ioCtxt.allocWriteEncodingBuffer()).thenReturn(new byte[128]);
        when(ioCtxt.allocConcatBuffer()).thenReturn(new char[256]);

        when(ioCtxt.allocBase64Buffer()).thenReturn(new byte[8192]);

        StreamWriteConstraints swc = mock(StreamWriteConstraints.class);
        doNothing().when(swc).validateNestingDepth(anyInt());
        when(ioCtxt.streamWriteConstraints()).thenReturn(swc);

        objectCtx = mock(ObjectWriteContext.class);
        when(objectCtx.getRootValueSeparator(any(SerializableString.class))).thenReturn(null);
    }

    private UTF8JsonGenerator newGenerator(ByteArrayOutputStream out) {
        int streamFeatures = StreamWriteFeature.collectDefaults();
        int formatFeatures = JsonWriteFeature.collectDefaults();
        return new UTF8JsonGenerator(
                objectCtx,
                ioCtxt,
                streamFeatures,
                formatFeatures,
                out,
                null,
                null,
                null,
                0,
                '"');
    }

    @Test
    public void testWriteNameWithPrettyPrinter() throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int streamFeatures = StreamWriteFeature.collectDefaults();
        int formatFeatures = JsonWriteFeature.collectDefaults();

        UTF8JsonGenerator gen = new UTF8JsonGenerator(
                objectCtx,
                ioCtxt,
                streamFeatures,
                formatFeatures,
                out,
                null,
                null,
                new DefaultPrettyPrinter(),
                0,
                '"');

        gen.writeStartObject()
           .writeName("foo")
           .writeString("bar")
           .writeEndObject()      // <--- missing closing call
           .flush();

        String result = out.toString(StandardCharsets.UTF_8.name());
        result = result.replaceAll("\\s+", "");
        assertEquals("{\"foo\":\"bar\"}", result);
    }

    @Test
    public void testMultiplePropertiesWithPrettyPrinter() throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int streamFeatures = StreamWriteFeature.collectDefaults();
        int formatFeatures = JsonWriteFeature.collectDefaults();

        UTF8JsonGenerator gen = new UTF8JsonGenerator(
                objectCtx,
                ioCtxt,
                streamFeatures,
                formatFeatures,
                out,
                null,
                null,
                new DefaultPrettyPrinter(),
                0,
                '"');

        gen.writeStartObject()
           .writeName("first")
           .writeString("A")
           .writeName("second")
           .writeString("B")
           .writeEndObject()      // <--- missing closing call
           .flush();

        String result = out.toString(StandardCharsets.UTF_8.name());
        result = result.replaceAll("\\s+", "");
        assertEquals("{\"first\":\"A\",\"second\":\"B\"}", result);
    }

    @Test
    public void testWriteLargeStringTriggersSegmented() throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        UTF8JsonGenerator gen = newGenerator(out);

        String large = "x".repeat(200);
        gen.writeStartObject()
           .writeName("data")
           .writeString(large)
           .writeEndObject()      // <--- missing closing call
           .flush();

        String expected = "{\"data\":\"" + large + "\"}";
        assertEquals(expected, out.toString(StandardCharsets.UTF_8.name()));
    }

    @Test
    public void testWriteRawWithLargeCharArraySegmented() throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        UTF8JsonGenerator gen = newGenerator(out);

        char[] cbuf = new char[300];
        Arrays.fill(cbuf, 'y');

        gen.writeStartArray()
           .writeRaw(cbuf, 0, cbuf.length)
           .writeEndArray()
           .flush();

        String expected = "[" + new String(cbuf) + "]";
        assertEquals(expected, out.toString(StandardCharsets.UTF_8.name()));
    }
}
