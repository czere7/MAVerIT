package tools.jackson.core.json;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import org.junit.Test;

import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonEncoding;
import tools.jackson.core.io.IOContext;

public class ByteSourceJsonBootstrapperTest
{
    private IOContext context(byte[] buffer)
    {
        IOContext ctxt = mock(IOContext.class);
        final JsonEncoding[] encoding = new JsonEncoding[] {
                JsonEncoding.UTF8
        };

        when(ctxt.allocReadIOBuffer()).thenReturn(buffer);
        when(ctxt.setEncoding(
                org.mockito.ArgumentMatchers.any(JsonEncoding.class)))
                .thenAnswer(invocation -> {
                    encoding[0] = invocation.getArgument(0);
                    return ctxt;
                });
        when(ctxt.getEncoding()).thenAnswer(invocation -> encoding[0]);
        return ctxt;
    }

    private ByteSourceJsonBootstrapper bootstrap(byte[] input)
    {
        return new ByteSourceJsonBootstrapper(
                context(new byte[Math.max(8192, input.length)]),
                input, 0, input.length);
    }

    private String readAll(Reader reader) throws IOException
    {
        StringBuilder result = new StringBuilder();
        char[] buffer = new char[64];
        int count;
        while ((count = reader.read(buffer)) >= 0) {
            result.append(buffer, 0, count);
        }
        return result.toString();
    }

    @Test
    public void detectsUtf8ByDefault() throws Exception
    {
        IOContext ctxt = context(new byte[8192]);
        ByteSourceJsonBootstrapper bootstrapper =
                new ByteSourceJsonBootstrapper(
                        ctxt, new byte[] { '{', '"', 'a', '"' }, 0, 4);

        assertEquals(JsonEncoding.UTF8, bootstrapper.detectEncoding());
        verify(ctxt).setEncoding(JsonEncoding.UTF8);
    }

    @Test
    public void detectsUtf8BomAndReaderSkipsBom() throws Exception
    {
        byte[] input = {
                ByteSourceJsonBootstrapper.UTF8_BOM_1,
                ByteSourceJsonBootstrapper.UTF8_BOM_2,
                ByteSourceJsonBootstrapper.UTF8_BOM_3,
                '{', '}'
        };
        IOContext ctxt = context(new byte[8192]);
        ByteSourceJsonBootstrapper bootstrapper =
                new ByteSourceJsonBootstrapper(ctxt, input, 0, input.length);

        assertEquals(JsonEncoding.UTF8, bootstrapper.detectEncoding());
        assertEquals("{}", readAll(bootstrapper.constructReader()));
    }

    @Test
    public void detectsUtf16BigEndianWithBom() throws Exception
    {
        byte[] input = {
                (byte) 0xFE, (byte) 0xFF, 0, '{', 0, '}'
        };
        IOContext ctxt = context(new byte[8192]);
        ByteSourceJsonBootstrapper bootstrapper =
                new ByteSourceJsonBootstrapper(ctxt, input, 0, input.length);

        assertEquals(JsonEncoding.UTF16_BE, bootstrapper.detectEncoding());
        assertEquals("{}", readAll(bootstrapper.constructReader()));
    }

    @Test
    public void detectsUtf16LittleEndianWithoutBom() throws Exception
    {
        byte[] input = { '{', 0, '}', 0 };
        IOContext ctxt = context(new byte[8192]);
        ByteSourceJsonBootstrapper bootstrapper =
                new ByteSourceJsonBootstrapper(ctxt, input, 0, input.length);

        assertEquals(JsonEncoding.UTF16_LE, bootstrapper.detectEncoding());
        assertEquals("{}", readAll(bootstrapper.constructReader()));
    }

    @Test
    public void detectsUtf32BigEndianWithoutBom() throws Exception
    {
        byte[] input = { 0, 0, 0, '{', 0, 0, 0, '}' };
        IOContext ctxt = context(new byte[8192]);
        ByteSourceJsonBootstrapper bootstrapper =
                new ByteSourceJsonBootstrapper(ctxt, input, 0, input.length);

        assertEquals(JsonEncoding.UTF32_BE, bootstrapper.detectEncoding());
        assertEquals("{}", readAll(bootstrapper.constructReader()));
    }

    @Test
    public void detectsUtf32LittleEndianWithBom() throws Exception
    {
        byte[] input = {
                (byte) 0xFF, (byte) 0xFE, 0, 0,
                '{', 0, 0, 0, '}', 0, 0, 0
        };
        IOContext ctxt = context(new byte[8192]);
        ByteSourceJsonBootstrapper bootstrapper =
                new ByteSourceJsonBootstrapper(ctxt, input, 0, input.length);

        assertEquals(JsonEncoding.UTF32_LE, bootstrapper.detectEncoding());
        assertEquals("{}", readAll(bootstrapper.constructReader()));
    }

    @Test
    public void detectsEncodingFromInputStreamAndPreservesBufferedContent()
            throws Exception
    {
        byte[] input = "{\"x\":1}".getBytes(StandardCharsets.UTF_8);
        IOContext ctxt = context(new byte[8192]);
        ByteSourceJsonBootstrapper bootstrapper =
                new ByteSourceJsonBootstrapper(
                        ctxt, new ByteArrayInputStream(input));

        assertEquals(JsonEncoding.UTF8, bootstrapper.detectEncoding());
        assertEquals("{\"x\":1}", readAll(bootstrapper.constructReader()));
    }

    @Test
    public void shortInputDefaultsToUtf8WhenNotUnicode() throws Exception
    {
        IOContext ctxt = context(new byte[8192]);
        ByteSourceJsonBootstrapper bootstrapper =
                new ByteSourceJsonBootstrapper(
                        ctxt, new byte[] { '{' }, 0, 1);

        assertEquals(JsonEncoding.UTF8, bootstrapper.detectEncoding());
    }

    @Test
    public void supportsShortUtf16Input() throws Exception
    {
        IOContext ctxt = context(new byte[8192]);
        ByteSourceJsonBootstrapper bootstrapper =
                new ByteSourceJsonBootstrapper(
                        ctxt, new byte[] { 0, '{' }, 0, 2);

        assertEquals(JsonEncoding.UTF16_BE, bootstrapper.detectEncoding());
    }

    @Test
    public void rejectsUnsupportedUcs4Endianness() throws Exception
    {
        try {
            bootstrap(new byte[] { 0, 0, (byte) 0xFF, (byte) 0xFE })
                    .detectEncoding();
            fail("Expected unsupported UCS-4 endianness failure");
        } catch (JacksonException e) {
            assertTrue(e.getMessage().contains(
                    "Unsupported UCS-4 endianness"));
        }
    }

    @Test
    public void rejectsSecondUnsupportedUcs4Endianness() throws Exception
    {
        try {
            bootstrap(new byte[] { (byte) 0xFE, (byte) 0xFF, 0, 0 })
                    .detectEncoding();
            fail("Expected unsupported UCS-4 endianness failure");
        } catch (JacksonException e) {
            assertTrue(e.getMessage().contains("3412"));
        }
    }

    @Test
    public void rejectsUnsupportedUcs4EndiannessDetectedWithoutBom()
            throws Exception
    {
        try {
            bootstrap(new byte[] { 0, '{', 0, 0 }).detectEncoding();
            fail("Expected UCS-4 endianness failure");
        } catch (JacksonException e) {
            assertTrue(e.getMessage().contains(
                    "Unsupported UCS-4 endianness"));
            assertTrue(e.getMessage().contains("3412"));
            assertTrue(e.getCause() instanceof IOException);
        }
    }

    @Test
    public void wrapsInputStreamReadFailure() throws Exception
    {
        InputStream failing = new InputStream() {
            @Override
            public int read(byte[] b, int off, int len) throws IOException
            {
                throw new IOException("read failure");
            }

            @Override
            public int read() throws IOException
            {
                throw new IOException("read failure");
            }
        };

        try {
            new ByteSourceJsonBootstrapper(
                    context(new byte[8192]), failing).detectEncoding();
            fail("Expected wrapped I/O failure");
        } catch (JacksonException e) {
            assertTrue(e.getMessage().contains("read failure"));
            assertTrue(e.getCause() instanceof IOException);
        }
    }

    @Test
    public void skipUtf8BomReturnsFirstContentByte() throws Exception
    {
        DataInputStream input = new DataInputStream(
                new ByteArrayInputStream(new byte[] {
                        ByteSourceJsonBootstrapper.UTF8_BOM_1,
                        ByteSourceJsonBootstrapper.UTF8_BOM_2,
                        ByteSourceJsonBootstrapper.UTF8_BOM_3,
                        'x'
                }));

        assertEquals('x', ByteSourceJsonBootstrapper.skipUTF8BOM(input));
    }

    @Test
    public void skipUtf8BomReturnsFirstByteWithoutBom() throws Exception
    {
        DataInputStream input = new DataInputStream(
                new ByteArrayInputStream(new byte[] { 'x', 'y' }));

        assertEquals('x', ByteSourceJsonBootstrapper.skipUTF8BOM(input));
        assertEquals('y', input.readUnsignedByte());
    }

    @Test
    public void skipUtf8BomRejectsInvalidSecondByte() throws Exception
    {
        try {
            ByteSourceJsonBootstrapper.skipUTF8BOM(new DataInputStream(
                    new ByteArrayInputStream(new byte[] {
                            (byte) 0xEF, 0, (byte) 0xBF, 'x'
                    })));
            fail("Expected invalid UTF-8 BOM failure");
        } catch (JacksonException e) {
            assertTrue(e.getMessage().contains("0x0"));
        }
    }

    @Test
    public void skipUtf8BomRejectsInvalidThirdByte() throws Exception
    {
        try {
            ByteSourceJsonBootstrapper.skipUTF8BOM(new DataInputStream(
                    new ByteArrayInputStream(new byte[] {
                            (byte) 0xEF, (byte) 0xBB, 0, 'x'
                    })));
            fail("Expected invalid UTF-8 BOM failure");
        } catch (JacksonException e) {
            assertTrue(e.getMessage().contains("0x0"));
        }
    }

    @Test
    public void skipUtf8BomWrapsUnexpectedEndOfInput() throws Exception
    {
        try {
            ByteSourceJsonBootstrapper.skipUTF8BOM(new DataInputStream(
                    new ByteArrayInputStream(new byte[] {
                            (byte) 0xEF, (byte) 0xBB
                    })));
            fail("Expected unexpected end-of-input failure");
        } catch (JacksonException e) {
            assertTrue(e.getCause() instanceof IOException);
        }
    }

    @Test
    public void constructReaderUsesOnlySpecifiedArrayRange()
            throws Exception
    {
        byte[] input = { 'x', 'a', 'b', 'y' };
        IOContext ctxt = context(new byte[8192]);
        when(ctxt.getEncoding()).thenReturn(JsonEncoding.UTF8);

        assertEquals("ab", readAll(new ByteSourceJsonBootstrapper(
                ctxt, input, 1, 2).constructReader()));
    }

    @Test
    public void constructReaderUsesStringReaderAtExactLimit()
            throws Exception
    {
        byte[] input = new byte[8192];
        Arrays.fill(input, (byte) 'a');

        IOContext ctxt = context(new byte[8192]);
        when(ctxt.getEncoding()).thenReturn(JsonEncoding.UTF8);

        Reader reader = new ByteSourceJsonBootstrapper(
                ctxt, input, 0, input.length).constructReader();

        assertTrue(reader instanceof StringReader);
        assertFalse(reader instanceof InputStreamReader);
        assertEquals(8192, readAll(reader).length());
    }

    @Test
    public void constructReaderUsesStreamAboveLimit()
            throws Exception
    {
        byte[] input = new byte[8193];
        Arrays.fill(input, (byte) 'b');

        IOContext ctxt = context(new byte[8192]);
        when(ctxt.getEncoding()).thenReturn(JsonEncoding.UTF8);

        Reader reader = new ByteSourceJsonBootstrapper(
                ctxt, input, 0, input.length).constructReader();

        assertTrue(reader instanceof InputStreamReader);
        assertEquals(8193, readAll(reader).length());
    }

    @Test
    public void emptyInputStreamDefaultsToUtf8() throws Exception
    {
        IOContext ctxt = context(new byte[8192]);
        ByteSourceJsonBootstrapper bootstrapper =
                new ByteSourceJsonBootstrapper(
                        ctxt, new ByteArrayInputStream(new byte[0]));

        assertEquals(JsonEncoding.UTF8, bootstrapper.detectEncoding());
        verify(ctxt).setEncoding(JsonEncoding.UTF8);
    }

    @Test
    public void constructReaderPreservesBufferedStreamContent()
            throws Exception
    {
        IOContext ctxt = context(new byte[8192]);
        ByteSourceJsonBootstrapper bootstrapper =
                new ByteSourceJsonBootstrapper(
                        ctxt,
                        new ByteArrayInputStream(
                                "tail".getBytes(StandardCharsets.UTF_8)));

        bootstrapper.detectEncoding();
        assertEquals("tail", readAll(bootstrapper.constructReader()));
    }

    @Test
    public void detectsUtf32FromInputStreamContainingFourBytes()
            throws Exception
    {
        IOContext ctxt = context(new byte[8192]);
        ByteSourceJsonBootstrapper bootstrapper =
                new ByteSourceJsonBootstrapper(
                        ctxt,
                        new ByteArrayInputStream(
                                new byte[] { 0, 0, 0, '{' }));

        assertEquals(JsonEncoding.UTF32_BE,
                bootstrapper.detectEncoding());
        assertEquals(JsonEncoding.UTF32_BE, ctxt.getEncoding());
    }

    @Test
    public void detectsUtf16FromInputStreamContainingTwoBytes()
            throws Exception
    {
        IOContext ctxt = context(new byte[8192]);
        ByteSourceJsonBootstrapper bootstrapper =
                new ByteSourceJsonBootstrapper(
                        ctxt,
                        new ByteArrayInputStream(new byte[] { '{', 0 }));

        assertEquals(JsonEncoding.UTF16_LE,
                bootstrapper.detectEncoding());
        assertEquals(JsonEncoding.UTF16_LE, ctxt.getEncoding());
    }

    @Test
    public void constructReaderPreservesUtf16ContentAfterDetection()
            throws Exception
    {
        byte[] input = {
                '{', 0, '"', 0, 'x', 0, '"', 0, '}', 0
        };
        IOContext ctxt = context(new byte[8192]);
        ByteSourceJsonBootstrapper bootstrapper =
                new ByteSourceJsonBootstrapper(ctxt,
                        new ByteArrayInputStream(input));

        assertEquals(JsonEncoding.UTF16_LE,
                bootstrapper.detectEncoding());
        assertEquals("{\"x\"}", readAll(bootstrapper.constructReader()));
    }
}
