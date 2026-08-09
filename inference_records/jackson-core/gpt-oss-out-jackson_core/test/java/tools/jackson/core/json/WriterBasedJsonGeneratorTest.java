package tools.jackson.core.json;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.io.StringReader;
import java.io.StringWriter;

import org.junit.Test;

import tools.jackson.core.ObjectWriteContext;
import tools.jackson.core.io.IOContext;
import tools.jackson.core.StreamWriteConstraints;
import tools.jackson.core.io.CharTypes;
import tools.jackson.core.exc.StreamWriteException;

public class WriterBasedJsonGeneratorTest {

    private static IOContext createMockIOContext(int bufferSize) {
        IOContext ctx = mock(IOContext.class);
        when(ctx.allocConcatBuffer()).thenReturn(new char[bufferSize]);
        doNothing().when(ctx).releaseConcatBuffer(any(char[].class));
        when(ctx.allocNameCopyBuffer(anyInt())).thenReturn(new char[bufferSize]);
        doNothing().when(ctx).releaseNameCopyBuffer(any(char[].class));

        when(ctx.streamWriteConstraints()).thenReturn(StreamWriteConstraints.defaults());
        return ctx;
    }

    @Test
    public void testWriteObjectWithQuotedNameAndString() throws Exception {
        StringWriter sw = new StringWriter();
        IOContext ioCtx = createMockIOContext(2000);

        WriterBasedJsonGenerator gen =
                new WriterBasedJsonGenerator(
                        ObjectWriteContext.empty(),
                        ioCtx,
                        0,
                        tools.jackson.core.json.JsonWriteFeature.collectDefaults(),
                        sw,
                        null,
                        null,
                        null,
                        0,
                        '"'
                );

        gen.writeStartObject()
           .writeName("foo")
           .writeString("bar")
           .writeEndObject()
           .flush();

        assertEquals("{\"foo\":\"bar\"}", sw.toString());
    }

    @Test
    public void testUnquotedPropertyNames() throws Exception {
        StringWriter sw = new StringWriter();
        IOContext ioCtx = createMockIOContext(2000);

        WriterBasedJsonGenerator gen =
                new WriterBasedJsonGenerator(
                        ObjectWriteContext.empty(),
                        ioCtx,
                        0,
                        0,
                        sw,
                        null,
                        null,
                        null,
                        0,
                        '"'
                );

        gen.writeStartObject()
           .writeName("foo")
           .writeString("bar")
           .writeEndObject()
           .flush();

        assertEquals("{foo:\"bar\"}", sw.toString());
    }

    @Test
    public void testNullStringValue() throws Exception {
        StringWriter sw = new StringWriter();
        IOContext ioCtx = createMockIOContext(2000);

        WriterBasedJsonGenerator gen =
                new WriterBasedJsonGenerator(
                        ObjectWriteContext.empty(),
                        ioCtx,
                        0,
                        tools.jackson.core.json.JsonWriteFeature.collectDefaults(),
                        sw,
                        null,
                        null,
                        null,
                        0,
                        '"'
                );

        gen.writeStartObject()
           .writeName("x")
           .writeString((String) null)
           .writeEndObject()
           .flush();

        assertEquals("{\"x\":null}", sw.toString());
    }

    @Test
    public void testEscapingInString() throws Exception {
        StringWriter sw = new StringWriter();
        IOContext ioCtx = createMockIOContext(2000);

        WriterBasedJsonGenerator gen =
                new WriterBasedJsonGenerator(
                        ObjectWriteContext.empty(),
                        ioCtx,
                        0,
                        tools.jackson.core.json.JsonWriteFeature.collectDefaults(),
                        sw,
                        null,
                        null,
                        null,
                        0,
                        '"'
                );

        String value = "He said \"Hello\" and \\backslash\\";
        gen.writeStartObject()
           .writeName("msg")
           .writeString(value)
           .writeEndObject()
           .flush();

        String result = sw.toString();
        assertTrue(result.contains("\"msg\":\"He said \\\"Hello\\\" and \\\\backslash\\\\\""));
    }

    @Test
    public void testBooleanOutputTrueFalse() throws Exception {
        StringWriter swTrue = new StringWriter();
        IOContext ioCtx = createMockIOContext(2000);
        WriterBasedJsonGenerator genTrue =
                new WriterBasedJsonGenerator(
                        ObjectWriteContext.empty(),
                        ioCtx,
                        0,
                        tools.jackson.core.json.JsonWriteFeature.collectDefaults(),
                        swTrue,
                        null,
                        null,
                        null,
                        0,
                        '"'
                );
        genTrue.writeBoolean(true).flush();
        assertEquals("true", swTrue.toString());

        StringWriter swFalse = new StringWriter();
        WriterBasedJsonGenerator genFalse =
                new WriterBasedJsonGenerator(
                        ObjectWriteContext.empty(),
                        ioCtx,
                        0,
                        tools.jackson.core.json.JsonWriteFeature.collectDefaults(),
                        swFalse,
                        null,
                        null,
                        null,
                        0,
                        '"'
                );
        genFalse.writeBoolean(false).flush();
        assertEquals("false", swFalse.toString());
    }

    @Test(expected = StreamWriteException.class)
    public void testWriteNameInInvalidContextThrows() throws Exception {
        StringWriter sw = new StringWriter();
        IOContext ioCtx = createMockIOContext(2000);

        WriterBasedJsonGenerator gen =
                new WriterBasedJsonGenerator(
                        ObjectWriteContext.empty(),
                        ioCtx,
                        0,
                        tools.jackson.core.json.JsonWriteFeature.collectDefaults(),
                        sw,
                        null,
                        null,
                        null,
                        0,
                        '"'
                );

        gen.writeName("invalid");
    }

    @Test
    public void testWriteStringFromReader() throws Exception {
        StringWriter sw = new StringWriter();
        IOContext ioCtx = createMockIOContext(2000);

        WriterBasedJsonGenerator gen =
                new WriterBasedJsonGenerator(
                        ObjectWriteContext.empty(),
                        ioCtx,
                        0,
                        tools.jackson.core.json.JsonWriteFeature.collectDefaults(),
                        sw,
                        null,
                        null,
                        null,
                        0,
                        '"'
                );

        StringReader reader = new StringReader("hello world");
        gen.writeStartObject()
           .writeName("msg")
           .writeString(reader, "hello world".length())
           .writeEndObject()
           .flush();

        assertEquals("{\"msg\":\"hello world\"}", sw.toString());
    }

    @Test
    public void testControlCharacterEscaping() throws Exception {
        StringWriter sw = new StringWriter();
        IOContext ioCtx = createMockIOContext(2000);

        WriterBasedJsonGenerator gen =
                new WriterBasedJsonGenerator(
                        ObjectWriteContext.empty(),
                        ioCtx,
                        0,
                        tools.jackson.core.json.JsonWriteFeature.collectDefaults(),
                        sw,
                        null,
                        null,
                        null,
                        0,
                        '"'
                );

        String input = "A\u0001B\nC\rD\tE\bF\fG";
        gen.writeStartObject()
           .writeName("data")
           .writeString(input)
           .writeEndObject()
           .flush();

        String result = sw.toString();
        assertTrue(result.contains("\\u0001"));
        assertTrue(result.contains("\\n"));
        assertTrue(result.contains("\\r"));
        assertTrue(result.contains("\\t"));
        assertTrue(result.contains("\\b"));
        assertTrue(result.contains("\\f"));
    }

    @Test
    public void testNumbersWrittenAsStrings() throws Exception {
        StringWriter sw = new StringWriter();
        IOContext ioCtx = createMockIOContext(2000);

        int formatFeatures =
                tools.jackson.core.json.JsonWriteFeature.collectDefaults()
                        | tools.jackson.core.json.JsonWriteFeature.WRITE_NUMBERS_AS_STRINGS.getMask();

        WriterBasedJsonGenerator gen =
                new WriterBasedJsonGenerator(
                        ObjectWriteContext.empty(),
                        ioCtx,
                        0,
                        formatFeatures,
                        sw,
                        null,
                        null,
                        null,
                        0,
                        '"'
                );

        gen.writeNumber(123).flush();

        assertEquals("\"123\"", sw.toString());
    }
}
