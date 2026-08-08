package tools.jackson.core.json;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;

import org.junit.Before;
import org.junit.Test;

import tools.jackson.core.*;
import tools.jackson.core.io.*;
import tools.jackson.core.json.UTF8StreamJsonParser;
import tools.jackson.core.json.ReaderBasedJsonParser;
import tools.jackson.core.sym.ByteQuadsCanonicalizer;
import tools.jackson.core.sym.CharsToNameCanonicalizer;
import tools.jackson.core.util.BufferRecycler;
import tools.jackson.core.util.VersionUtil;

public class ByteSourceJsonBootstrapperTest {

    private IOContext context;
    private BufferRecycler bufferRecycler;
    private JsonFactory jsonFactory;
    private ByteQuadsCanonicalizer rootByteSymbols;
    private CharsToNameCanonicalizer rootCharSymbols;

    @Before
    public void setUp() {
        bufferRecycler = new BufferRecycler();
        context = new IOContext(
            StreamReadConstraints.defaults(),
            StreamWriteConstraints.defaults(),
            ErrorReportConfiguration.defaults(),
            bufferRecycler,
            null,
            false,
            JsonEncoding.UTF8
        );
        jsonFactory = new JsonFactory();
        rootByteSymbols = ByteQuadsCanonicalizer.createRoot();
        rootCharSymbols = CharsToNameCanonicalizer.createRoot(jsonFactory);
    }

    @Test
    public void testDetectEncoding_UTF8WithBOM() throws JacksonException {
        byte[] input = new byte[] {
            (byte) 0xEF, (byte) 0xBB, (byte) 0xBF, 
            '{', '"', 't', 'e', 's', 't', '"', ':', '1', '}'
        };
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, input, 0, input.length);
        JsonEncoding encoding = bootstrapper.detectEncoding();
        
        assertEquals(JsonEncoding.UTF8, encoding);
        assertEquals(JsonEncoding.UTF8, context.getEncoding());
    }

    @Test
    public void testDetectEncoding_UTF16BEWithBOM() throws JacksonException {
        byte[] input = new byte[] {
            (byte) 0xFE, (byte) 0xFF, 
            0x00, 0x7B, 0x00, 0x22, 0x00, 0x74, 0x00, 0x65, 0x00, 0x73, 0x00, 0x74, 0x00, 0x22, 0x00, 0x3A, 0x00, 0x31, 0x00, 0x7D 
        };
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, input, 0, input.length);
        JsonEncoding encoding = bootstrapper.detectEncoding();
        
        assertEquals(JsonEncoding.UTF16_BE, encoding);
        assertEquals(JsonEncoding.UTF16_BE, context.getEncoding());
    }

    @Test
    public void testDetectEncoding_UTF16LEWithBOM() throws JacksonException {
        byte[] input = new byte[] {
            (byte) 0xFF, (byte) 0xFE, 
            0x7B, 0x00, 0x22, 0x00, 0x74, 0x00, 0x65, 0x00, 0x73, 0x00, 0x74, 0x00, 0x22, 0x00, 0x3A, 0x00, 0x31, 0x00, 0x7D, 0x00 
        };
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, input, 0, input.length);
        JsonEncoding encoding = bootstrapper.detectEncoding();
        
        assertEquals(JsonEncoding.UTF16_LE, encoding);
        assertEquals(JsonEncoding.UTF16_LE, context.getEncoding());
    }

    @Test
    public void testDetectEncoding_UTF32BEWithBOM() throws JacksonException {
        byte[] input = new byte[] {
            0x00, 0x00, (byte) 0xFE, (byte) 0xFF, 
            0x00, 0x00, 0x00, 0x7B, 0x00, 0x00, 0x00, 0x22, 0x00, 0x00, 0x00, 0x74, 0x00, 0x00, 0x00, 0x65, 0x00, 0x00, 0x00, 0x73, 0x00, 0x00, 0x00, 0x74, 0x00, 0x00, 0x00, 0x22, 0x00, 0x00, 0x00, 0x3A, 0x00, 0x00, 0x00, 0x31, 0x00, 0x00, 0x00, 0x7D 
        };
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, input, 0, input.length);
        JsonEncoding encoding = bootstrapper.detectEncoding();
        
        assertEquals(JsonEncoding.UTF32_BE, encoding);
        assertEquals(JsonEncoding.UTF32_BE, context.getEncoding());
    }

    @Test
    public void testDetectEncoding_UTF32LEWithBOM() throws JacksonException {
        byte[] input = new byte[] {
            (byte) 0xFF, (byte) 0xFE, 0x00, 0x00, 
            0x7B, 0x00, 0x00, 0x00, 0x22, 0x00, 0x00, 0x00, 0x74, 0x00, 0x00, 0x00, 0x65, 0x00, 0x00, 0x00, 0x73, 0x00, 0x00, 0x00, 0x74, 0x00, 0x00, 0x00, 0x22, 0x00, 0x00, 0x00, 0x3A, 0x00, 0x00, 0x00, 0x31, 0x00, 0x00, 0x00, 0x7D, 0x00, 0x00, 0x00 
        };
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, input, 0, input.length);
        JsonEncoding encoding = bootstrapper.detectEncoding();
        
        assertEquals(JsonEncoding.UTF32_LE, encoding);
        assertEquals(JsonEncoding.UTF32_LE, context.getEncoding());
    }

    @Test
    public void testDetectEncoding_UTF32BEWithoutBOM() throws JacksonException {
        byte[] input = new byte[] {
            0x00, 0x00, 0x00, 0x7B, 0x00, 0x00, 0x00, 0x22, 0x00, 0x00, 0x00, 0x74 
        };
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, input, 0, input.length);
        JsonEncoding encoding = bootstrapper.detectEncoding();
        
        assertEquals(JsonEncoding.UTF32_BE, encoding);
        assertEquals(JsonEncoding.UTF32_BE, context.getEncoding());
    }

    @Test
    public void testDetectEncoding_UTF32LEWithoutBOM() throws JacksonException {
        byte[] input = new byte[] {
            0x7B, 0x00, 0x00, 0x00, 0x22, 0x00, 0x00, 0x00, 0x74, 0x00, 0x00, 0x00 
        };
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, input, 0, input.length);
        JsonEncoding encoding = bootstrapper.detectEncoding();
        
        assertEquals(JsonEncoding.UTF32_LE, encoding);
        assertEquals(JsonEncoding.UTF32_LE, context.getEncoding());
    }

    @Test
    public void testDetectEncoding_UTF16BEWithoutBOM() throws JacksonException {
        byte[] input = new byte[] {
            0x00, 0x7B, 0x00, 0x22, 0x00, 0x74, 0x00, 0x65 
        };
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, input, 0, input.length);
        JsonEncoding encoding = bootstrapper.detectEncoding();
        
        assertEquals(JsonEncoding.UTF16_BE, encoding);
        assertEquals(JsonEncoding.UTF16_BE, context.getEncoding());
    }

    @Test
    public void testDetectEncoding_UTF16LEWithoutBOM() throws JacksonException {
        byte[] input = new byte[] {
            0x7B, 0x00, 0x22, 0x00, 0x74, 0x00, 0x65, 0x00 
        };
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, input, 0, input.length);
        JsonEncoding encoding = bootstrapper.detectEncoding();
        
        assertEquals(JsonEncoding.UTF16_LE, encoding);
        assertEquals(JsonEncoding.UTF16_LE, context.getEncoding());
    }

    @Test
    public void testDetectEncoding_UTF8WithoutBOM() throws JacksonException {
        byte[] input = "{\"test\":1}".getBytes(StandardCharsets.UTF_8);
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, input, 0, input.length);
        JsonEncoding encoding = bootstrapper.detectEncoding();
        
        assertEquals(JsonEncoding.UTF8, encoding);
        assertEquals(JsonEncoding.UTF8, context.getEncoding());
    }

    @Test
    public void testDetectEncoding_UTF8WithOffset() throws JacksonException {
        byte[] input = new byte[20];
        System.arraycopy("{\"test\":1}".getBytes(StandardCharsets.UTF_8), 0, input, 5, 10);
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, input, 5, 10);
        JsonEncoding encoding = bootstrapper.detectEncoding();
        
        assertEquals(JsonEncoding.UTF8, encoding);
    }

    @Test
    public void testDetectEncoding_EmptyInput() throws JacksonException {
        byte[] input = new byte[0];
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, input, 0, 0);
        JsonEncoding encoding = bootstrapper.detectEncoding();
        
        assertEquals(JsonEncoding.UTF8, encoding);
    }

    @Test
    public void testDetectEncoding_InsufficientBytesForBOM() throws JacksonException {
        byte[] input = new byte[] { 0x7B }; 
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, input, 0, input.length);
        JsonEncoding encoding = bootstrapper.detectEncoding();
        
        assertEquals(JsonEncoding.UTF8, encoding);
    }

    @Test
    public void testDetectEncoding_OnlyTwoBytes() throws JacksonException {
        byte[] input = new byte[] { 0x00, 0x7B }; 
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, input, 0, input.length);
        JsonEncoding encoding = bootstrapper.detectEncoding();
        
        assertEquals(JsonEncoding.UTF16_BE, encoding);
    }

    @Test
    public void testConstructReader_UTF8_SmallInput_UsesStringReader() throws JacksonException, IOException {
        byte[] input = "{\"test\":1}".getBytes(StandardCharsets.UTF_8);
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, input, 0, input.length);
        bootstrapper.detectEncoding(); 
        
        Reader reader = bootstrapper.constructReader();
        
        assertNotNull(reader);
        assertTrue(reader instanceof StringReader);
        reader.close();
    }

    @Test
    public void testConstructReader_UTF8_LargeInput_UsesInputStreamReader() throws JacksonException, IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb.append("{\"test\":").append(i).append("},");
        }
        sb.append("{\"test\":1000}");
        byte[] input = sb.toString().getBytes(StandardCharsets.UTF_8);
        
        assertTrue(input.length > 8192);
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, input, 0, input.length);
        bootstrapper.detectEncoding(); 
        
        Reader reader = bootstrapper.constructReader();
        
        assertNotNull(reader);
        assertTrue(reader instanceof InputStreamReader);
        reader.close();
    }

    @Test
    public void testConstructReader_UTF16BE() throws JacksonException, IOException {
        byte[] input = new byte[] {
            (byte) 0xFE, (byte) 0xFF, 
            0x00, 0x7B, 0x00, 0x22, 0x00, 0x74, 0x00, 0x65, 0x00, 0x73, 0x00, 0x74, 0x00, 0x22, 0x00, 0x3A, 0x00, 0x31, 0x00, 0x7D
        };
        InputStream inputStream = new ByteArrayInputStream(input);
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, inputStream);
        bootstrapper.detectEncoding(); 
        
        Reader reader = bootstrapper.constructReader();
        
        assertNotNull(reader);
        assertTrue(reader instanceof InputStreamReader);
        reader.close();
    }

    @Test
    public void testConstructReader_UTF16LE() throws JacksonException, IOException {
        byte[] input = new byte[] {
            (byte) 0xFF, (byte) 0xFE, 
            0x7B, 0x00, 0x22, 0x00, 0x74, 0x00, 0x65, 0x00, 0x73, 0x00, 0x74, 0x00, 0x22, 0x00, 0x3A, 0x00, 0x31, 0x00, 0x7D, 0x00
        };
        InputStream inputStream = new ByteArrayInputStream(input);
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, inputStream);
        bootstrapper.detectEncoding(); 
        
        Reader reader = bootstrapper.constructReader();
        
        assertNotNull(reader);
        assertTrue(reader instanceof InputStreamReader);
        reader.close();
    }

    @Test
    public void testConstructReader_UTF32BE() throws JacksonException, IOException {
        byte[] input = new byte[] {
            0x00, 0x00, (byte) 0xFE, (byte) 0xFF, 
            0x00, 0x00, 0x00, 0x7B, 0x00, 0x00, 0x00, 0x22
        };
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, input, 0, input.length);
        bootstrapper.detectEncoding(); 
        
        Reader reader = bootstrapper.constructReader();
        
        assertNotNull(reader);
        assertTrue(reader.getClass().getName().contains("UTF32Reader"));
        reader.close();
    }

    @Test
    public void testConstructReader_UTF32LE() throws JacksonException, IOException {
        byte[] input = new byte[] {
            (byte) 0xFF, (byte) 0xFE, 0x00, 0x00, 
            0x7B, 0x00, 0x00, 0x00, 0x22, 0x00, 0x00, 0x00
        };
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, input, 0, input.length);
        bootstrapper.detectEncoding(); 
        
        Reader reader = bootstrapper.constructReader();
        
        assertNotNull(reader);
        assertTrue(reader.getClass().getName().contains("UTF32Reader"));
        reader.close();
    }

    @Test
    public void testConstructReader_WithInputStream() throws JacksonException, IOException {
        byte[] input = "{\"test\":1}".getBytes(StandardCharsets.UTF_8);
        InputStream inputStream = new ByteArrayInputStream(input);
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, inputStream);
        bootstrapper.detectEncoding();
        
        Reader reader = bootstrapper.constructReader();
        
        assertNotNull(reader);
        assertTrue(reader instanceof InputStreamReader);
        reader.close();
    }

    @Test
    public void testConstructParser_UTF8_WithCanonicalization() throws JacksonException, IOException {
        byte[] input = "{\"test\":1}".getBytes(StandardCharsets.UTF_8);
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, input, 0, input.length);
        
        ObjectReadContext readContext = ObjectReadContext.empty();
        
        JsonParser parser = bootstrapper.constructParser(
            readContext,
            0, 
            0, 
            rootByteSymbols,
            rootCharSymbols,
            JsonFactory.Feature.CANONICALIZE_PROPERTY_NAMES.getMask() 
        );
        
        assertNotNull(parser);
        assertTrue(parser instanceof UTF8StreamJsonParser);
        parser.close();
    }

    @Test
    public void testConstructParser_UTF8_WithoutCanonicalization() throws JacksonException, IOException {
        byte[] input = "{\"test\":1}".getBytes(StandardCharsets.UTF_8);
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, input, 0, input.length);
        
        ObjectReadContext readContext = ObjectReadContext.empty();
        
        JsonParser parser = bootstrapper.constructParser(
            readContext,
            0, 
            0, 
            rootByteSymbols,
            rootCharSymbols,
            0 
        );
        
        assertNotNull(parser);
        assertTrue(parser instanceof ReaderBasedJsonParser);
        parser.close();
    }

    @Test
    public void testConstructParser_NonUTF8_UsesReaderBasedParser() throws JacksonException, IOException {
        byte[] input = new byte[] {
            (byte) 0xFE, (byte) 0xFF, 
            0x00, 0x7B, 0x00, 0x22, 0x00, 0x74, 0x00, 0x65, 0x00, 0x73, 0x00, 0x74, 0x00, 0x22, 0x00, 0x3A, 0x00, 0x31, 0x00, 0x7D
        };
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, input, 0, input.length);
        
        ObjectReadContext readContext = ObjectReadContext.empty();
        
        JsonParser parser = bootstrapper.constructParser(
            readContext,
            0, 0,
            rootByteSymbols,
            rootCharSymbols,
            JsonFactory.Feature.CHARSET_DETECTION.getMask() | JsonFactory.Feature.CANONICALIZE_PROPERTY_NAMES.getMask()
        );
        
        assertNotNull(parser);
        assertTrue(parser instanceof ReaderBasedJsonParser);
        parser.close();
    }

    @Test
    public void testSkipUTF8BOM_ValidBOM() throws JacksonException {
        byte[] input = new byte[] {
            (byte) 0xEF, (byte) 0xBB, (byte) 0xBF, 
            0x7B 
        };
        DataInput dataInput = new DataInputStream(new ByteArrayInputStream(input));
        
        int result = ByteSourceJsonBootstrapper.skipUTF8BOM(dataInput);
        
        assertEquals(0x7B, result); 
    }

    @Test
    public void testSkipUTF8BOM_NoBOM() throws JacksonException {
        byte[] input = new byte[] { 0x7B, 0x22 }; 
        DataInput dataInput = new DataInputStream(new ByteArrayInputStream(input));
        
        int result = ByteSourceJsonBootstrapper.skipUTF8BOM(dataInput);
        
        assertEquals(0x7B, result); 
    }

    @Test
    public void testSkipUTF8BOM_InvalidSecondByte() throws JacksonException {
        byte[] input = new byte[] { (byte) 0xEF, 0x00 }; 
        DataInput dataInput = new DataInputStream(new ByteArrayInputStream(input));
        
        try {
            ByteSourceJsonBootstrapper.skipUTF8BOM(dataInput);
            fail("Expected JacksonException");
        } catch (JacksonException e) {
            assertTrue(e.getMessage().contains("Unexpected byte 0x0 following 0xEF"));
        }
    }

    @Test
    public void testSkipUTF8BOM_InvalidThirdByte() throws JacksonException {
        byte[] input = new byte[] { (byte) 0xEF, (byte) 0xBB, 0x00 }; 
        DataInput dataInput = new DataInputStream(new ByteArrayInputStream(input));
        
        try {
            ByteSourceJsonBootstrapper.skipUTF8BOM(dataInput);
            fail("Expected JacksonException");
        } catch (JacksonException e) {
            assertTrue(e.getMessage().contains("Unexpected byte 0x0 following 0xEF 0xBB"));
        }
    }

    @Test
    public void testConstructor_WithInputStream() {
        InputStream inputStream = new ByteArrayInputStream(new byte[10]);
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, inputStream);
        
        assertNotNull(bootstrapper);
    }

    @Test
    public void testConstructor_WithByteArray() {
        byte[] input = new byte[100];
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, input, 10, 50);
        
        assertNotNull(bootstrapper);
    }

    @Test
    public void testConstructor_WithByteArrayAndOffset() throws JacksonException {
        byte[] input = new byte[20];
        System.arraycopy("{\"test\":1}".getBytes(StandardCharsets.UTF_8), 0, input, 5, 10);
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, input, 5, 10);
        JsonEncoding encoding = bootstrapper.detectEncoding();
        
        assertEquals(JsonEncoding.UTF8, encoding);
    }

    @Test
    public void testDetectEncoding_WeirdUCS4_2143() throws JacksonException {
        byte[] input = new byte[] { 0x00, 0x00, (byte) 0xFF, (byte) 0xFE };
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, input, 0, input.length);
        
        try {
            bootstrapper.detectEncoding();
            fail("Expected JacksonException for weird UCS-4");
        } catch (JacksonException e) {
            assertTrue(e.getMessage().contains("Unsupported UCS-4 endianness (2143)"));
        }
    }

    @Test
    public void testDetectEncoding_WeirdUCS4_3412() throws JacksonException {
        byte[] input = new byte[] { (byte) 0xFE, (byte) 0xFF, 0x00, 0x00 };
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, input, 0, input.length);
        
        try {
            bootstrapper.detectEncoding();
            fail("Expected JacksonException for weird UCS-4");
        } catch (JacksonException e) {
            assertTrue(e.getMessage().contains("Unsupported UCS-4 endianness (3412)"));
        }
    }

    @Test
    public void testDetectEncoding_UTF32_InOrder_2143() throws JacksonException {
        byte[] input = new byte[] { 0x00, 0x7B, 0x00, 0x00 }; 
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, input, 0, input.length);
        
        try {
            bootstrapper.detectEncoding();
            fail("Expected JacksonException for weird UCS-4");
        } catch (JacksonException e) {
            assertTrue(e.getMessage().contains("Unsupported UCS-4 endianness (3412)"));
        }
    }

    @Test
    public void testDetectEncoding_UTF32_InOrder_3412() throws JacksonException {
        byte[] input = new byte[] { 0x00, 0x00, 0x7B, 0x00 }; 
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, input, 0, input.length);
        
        try {
            bootstrapper.detectEncoding();
            fail("Expected JacksonException for weird UCS-4");
        } catch (JacksonException e) {
            assertTrue(e.getMessage().contains("Unsupported UCS-4 endianness (2143)"));
        }
    }

    @Test
    public void testConstants() {
        assertEquals((byte) 0xEF, ByteSourceJsonBootstrapper.UTF8_BOM_1);
        assertEquals((byte) 0xBB, ByteSourceJsonBootstrapper.UTF8_BOM_2);
        assertEquals((byte) 0xBF, ByteSourceJsonBootstrapper.UTF8_BOM_3);
    }

    @Test
    public void testConstructReader_UTF8_WithPreReadBytes_UsesMergedStream() throws JacksonException, IOException {
        byte[] input = "{\"test\":1}".getBytes(StandardCharsets.UTF_8);
        InputStream inputStream = new ByteArrayInputStream(new byte[0]); 
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, inputStream);
        bootstrapper.detectEncoding();
        
        Reader reader = bootstrapper.constructReader();
        
        assertNotNull(reader);
        reader.close();
    }

    @Test
    public void testConstructParser_DetectsEncodingIfNotAlreadyDetected() throws JacksonException, IOException {
        byte[] input = "{\"test\":1}".getBytes(StandardCharsets.UTF_8);
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, input, 0, input.length);
        
        ObjectReadContext readContext = ObjectReadContext.empty();
        
        JsonParser parser = bootstrapper.constructParser(
            readContext,
            0, 0,
            rootByteSymbols,
            rootCharSymbols,
            JsonFactory.Feature.CHARSET_DETECTION.getMask()
        );
        
        assertNotNull(parser);
        assertEquals(JsonEncoding.UTF8, context.getEncoding());
        parser.close();
    }

    @Test
    public void testDetectEncoding_InputStream_ReadsFromStream() throws JacksonException {
        byte[] input = new byte[] {
            (byte) 0xEF, (byte) 0xBB, (byte) 0xBF, 
            '{', '"', 't', 'e', 's', 't', '"', ':', '1', '}'
        };
        InputStream inputStream = new ByteArrayInputStream(input);
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, inputStream);
        JsonEncoding encoding = bootstrapper.detectEncoding();
        
        assertEquals(JsonEncoding.UTF8, encoding);
    }

    @Test
    public void testDetectEncoding_InputStream_UTF16BE() throws JacksonException {
        byte[] input = new byte[] {
            (byte) 0xFE, (byte) 0xFF, 
            0x00, 0x7B, 0x00, 0x22
        };
        InputStream inputStream = new ByteArrayInputStream(input);
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, inputStream);
        JsonEncoding encoding = bootstrapper.detectEncoding();
        
        assertEquals(JsonEncoding.UTF16_BE, encoding);
    }

    @Test
    public void testDetectEncoding_InputStream_InsufficientData() throws JacksonException {
        InputStream inputStream = new ByteArrayInputStream(new byte[] { 0x7B });
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, inputStream);
        JsonEncoding encoding = bootstrapper.detectEncoding();
        
        assertEquals(JsonEncoding.UTF8, encoding);
    }

    @Test
    public void testDetectEncoding_InputStream_TwoBytes() throws JacksonException {
        InputStream inputStream = new ByteArrayInputStream(new byte[] { 0x00, 0x7B }); 
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, inputStream);
        JsonEncoding encoding = bootstrapper.detectEncoding();
        
        assertEquals(JsonEncoding.UTF16_BE, encoding);
    }

    @Test
    public void testDetectEncoding_InputStream_ThreeBytes() throws JacksonException {
        InputStream inputStream = new ByteArrayInputStream(new byte[] { 0x00, 0x7B, 0x00 }); 
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, inputStream);
        JsonEncoding encoding = bootstrapper.detectEncoding();
        
        assertEquals(JsonEncoding.UTF16_BE, encoding);
    }

    @Test
    public void testDetectEncoding_InputStream_UTF8BOM_OnlyThreeBytes() throws JacksonException {
        InputStream inputStream = new ByteArrayInputStream(new byte[] { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF });
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, inputStream);
        JsonEncoding encoding = bootstrapper.detectEncoding();
        
        assertEquals(JsonEncoding.UTF8, encoding);
    }

    @Test
    public void testEnsureLoaded_MultipleReads_SingleByteAtATime() throws JacksonException {
        InputStream inputStream = new InputStream() {
            private final byte[] data = "{\"test\":1}".getBytes(StandardCharsets.UTF_8);
            private int pos = 0;
            
            @Override
            public int read(byte[] b, int off, int len) {
                if (pos >= data.length) return -1;
                int toRead = Math.min(1, data.length - pos); 
                b[off] = data[pos++];
                return toRead;
            }
            
            @Override
            public int read() {
                if (pos >= data.length) return -1;
                return data[pos++] & 0xFF;
            }
        };
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, inputStream);
        JsonEncoding encoding = bootstrapper.detectEncoding();
        
        assertEquals(JsonEncoding.UTF8, encoding);
    }

    @Test
    public void testEnsureLoaded_MultipleReads_TwoBytesAtATime() throws JacksonException {
        InputStream inputStream = new InputStream() {
            private final byte[] data = "{\"test\":123}".getBytes(StandardCharsets.UTF_8);
            private int pos = 0;
            
            @Override
            public int read(byte[] b, int off, int len) {
                if (pos >= data.length) return -1;
                int toRead = Math.min(2, data.length - pos); 
                System.arraycopy(data, pos, b, off, toRead);
                pos += toRead;
                return toRead;
            }
            
            @Override
            public int read() {
                if (pos >= data.length) return -1;
                return data[pos++] & 0xFF;
            }
        };
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, inputStream);
        JsonEncoding encoding = bootstrapper.detectEncoding();
        
        assertEquals(JsonEncoding.UTF8, encoding);
    }

    @Test
    public void testConstructReader_MergedStream_ExplicitBufferRemaining() throws JacksonException, IOException {
        byte[] input = "{\"test\":12345}".getBytes(StandardCharsets.UTF_8); 
        InputStream inputStream = new ByteArrayInputStream(input);
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, inputStream);
        bootstrapper.detectEncoding(); 
        
        Reader reader = bootstrapper.constructReader();
        
        assertNotNull(reader);
        assertTrue(reader instanceof InputStreamReader);
        reader.close();
    }

    @Test
    public void testConstructReader_UTF32BE_InputStream() throws JacksonException, IOException {
        byte[] input = new byte[] {
            0x00, 0x00, (byte) 0xFE, (byte) 0xFF, 
            0x00, 0x00, 0x00, 0x7B, 0x00, 0x00, 0x00, 0x22
        };
        InputStream inputStream = new ByteArrayInputStream(input);
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, inputStream);
        bootstrapper.detectEncoding(); 
        
        Reader reader = bootstrapper.constructReader();
        
        assertNotNull(reader);
        assertTrue(reader.getClass().getName().contains("UTF32Reader"));
        reader.close();
    }

    @Test
    public void testConstructReader_UTF32LE_InputStream() throws JacksonException, IOException {
        byte[] input = new byte[] {
            (byte) 0xFF, (byte) 0xFE, 0x00, 0x00, 
            0x7B, 0x00, 0x00, 0x00, 0x22, 0x00, 0x00, 0x00
        };
        InputStream inputStream = new ByteArrayInputStream(input);
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, inputStream);
        bootstrapper.detectEncoding(); 
        
        Reader reader = bootstrapper.constructReader();
        
        assertNotNull(reader);
        assertTrue(reader.getClass().getName().contains("UTF32Reader"));
        reader.close();
    }

    @Test
    public void testConstructParser_UTF32BE_UsesReaderBasedParser() throws JacksonException, IOException {
        byte[] input = new byte[] {
            0x00, 0x00, (byte) 0xFE, (byte) 0xFF, 
            0x00, 0x00, 0x00, 0x7B, 0x00, 0x00, 0x00, 0x22
        };
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, input, 0, input.length);
        
        ObjectReadContext readContext = ObjectReadContext.empty();
        
        JsonParser parser = bootstrapper.constructParser(
            readContext,
            0, 0,
            rootByteSymbols,
            rootCharSymbols,
            JsonFactory.Feature.CHARSET_DETECTION.getMask()
        );
        
        assertNotNull(parser);
        assertTrue(parser instanceof ReaderBasedJsonParser);
        parser.close();
    }

    @Test
    public void testConstructParser_UTF32LE_UsesReaderBasedParser() throws JacksonException, IOException {
        byte[] input = new byte[] {
            (byte) 0xFF, (byte) 0xFE, 0x00, 0x00, 
            0x7B, 0x00, 0x00, 0x00, 0x22, 0x00, 0x00, 0x00
        };
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, input, 0, input.length);
        
        ObjectReadContext readContext = ObjectReadContext.empty();
        
        JsonParser parser = bootstrapper.constructParser(
            readContext,
            0, 0,
            rootByteSymbols,
            rootCharSymbols,
            JsonFactory.Feature.CHARSET_DETECTION.getMask()
        );
        
        assertNotNull(parser);
        assertTrue(parser instanceof ReaderBasedJsonParser);
        parser.close();
    }

    @Test
    public void testDetectEncoding_InputStream_UTF32BE() throws JacksonException {
        byte[] input = new byte[] {
            0x00, 0x00, (byte) 0xFE, (byte) 0xFF, 
            0x00, 0x00, 0x00, 0x7B
        };
        InputStream inputStream = new ByteArrayInputStream(input);
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, inputStream);
        JsonEncoding encoding = bootstrapper.detectEncoding();
        
        assertEquals(JsonEncoding.UTF32_BE, encoding);
    }

    @Test
    public void testDetectEncoding_InputStream_UTF32LE() throws JacksonException {
        byte[] input = new byte[] {
            (byte) 0xFF, (byte) 0xFE, 0x00, 0x00, 
            0x7B, 0x00, 0x00, 0x00
        };
        InputStream inputStream = new ByteArrayInputStream(input);
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, inputStream);
        JsonEncoding encoding = bootstrapper.detectEncoding();
        
        assertEquals(JsonEncoding.UTF32_LE, encoding);
    }

    @Test
    public void testDetectEncoding_InputStream_UTF32BE_NoBOM() throws JacksonException {
        byte[] input = new byte[] {
            0x00, 0x00, 0x00, 0x7B, 0x00, 0x00, 0x00, 0x22
        };
        InputStream inputStream = new ByteArrayInputStream(input);
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, inputStream);
        JsonEncoding encoding = bootstrapper.detectEncoding();
        
        assertEquals(JsonEncoding.UTF32_BE, encoding);
    }

    @Test
    public void testDetectEncoding_InputStream_UTF32LE_NoBOM() throws JacksonException {
        byte[] input = new byte[] {
            0x7B, 0x00, 0x00, 0x00, 0x22, 0x00, 0x00, 0x00
        };
        InputStream inputStream = new ByteArrayInputStream(input);
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, inputStream);
        JsonEncoding encoding = bootstrapper.detectEncoding();
        
        assertEquals(JsonEncoding.UTF32_LE, encoding);
    }

    @Test
    public void testConstructReader_UTF8_ExactlyAtLimit() throws JacksonException, IOException {
        String base = "{\"a\":1}"; 
        int repeat = 8192 / base.length(); 
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < repeat; i++) {
            sb.append(base);
        }
        sb.append("12"); 
        String json = sb.toString();
        byte[] input = json.getBytes(StandardCharsets.UTF_8);
        
        assertEquals(8192, input.length);
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, input, 0, input.length);
        bootstrapper.detectEncoding();
        
        Reader reader = bootstrapper.constructReader();
        
        assertNotNull(reader);
        assertTrue(reader instanceof StringReader); 
        reader.close();
    }

    @Test
    public void testConstructReader_UTF8_OverLimitByOne() throws JacksonException, IOException {
        StringBuilder sb = new StringBuilder();
        String base = "{\"a\":1}";
        int repeat = (8193 / base.length()) + 1;
        for (int i = 0; i < repeat; i++) {
            sb.append(base);
        }
        String json = sb.toString().substring(0, 8193);
        byte[] input = json.getBytes(StandardCharsets.UTF_8);
        
        assertTrue(input.length > 8192);
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, input, 0, input.length);
        bootstrapper.detectEncoding();
        
        Reader reader = bootstrapper.constructReader();
        
        assertNotNull(reader);
        assertTrue(reader instanceof InputStreamReader); 
        reader.close();
    }

    @Test
    public void testConstructParser_UTF8_WithByteArray_Canonicalization() throws JacksonException, IOException {
        byte[] input = "{\"test\":1}".getBytes(StandardCharsets.UTF_8);
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, input, 0, input.length);
        
        ObjectReadContext readContext = ObjectReadContext.empty();
        
        JsonParser parser = bootstrapper.constructParser(
            readContext,
            0, 0,
            rootByteSymbols,
            rootCharSymbols,
            JsonFactory.Feature.CANONICALIZE_PROPERTY_NAMES.getMask()
        );
        
        assertNotNull(parser);
        assertTrue(parser instanceof UTF8StreamJsonParser);
        parser.close();
    }

    @Test
    public void testConstructParser_UTF8_WithInputStream_Canonicalization() throws JacksonException, IOException {
        byte[] input = "{\"test\":1}".getBytes(StandardCharsets.UTF_8);
        InputStream inputStream = new ByteArrayInputStream(input);
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, inputStream);
        
        ObjectReadContext readContext = ObjectReadContext.empty();
        
        JsonParser parser = bootstrapper.constructParser(
            readContext,
            0, 0,
            rootByteSymbols,
            rootCharSymbols,
            JsonFactory.Feature.CANONICALIZE_PROPERTY_NAMES.getMask() | JsonFactory.Feature.CHARSET_DETECTION.getMask()
        );
        
        assertNotNull(parser);
        assertTrue(parser instanceof UTF8StreamJsonParser);
        parser.close();
    }

    @Test
    public void testConstructParser_UTF16_UsesReaderBasedParser() throws JacksonException, IOException {
        byte[] input = new byte[] {
            (byte) 0xFE, (byte) 0xFF, 
            0x00, 0x7B, 0x00, 0x22, 0x00, 0x74, 0x00, 0x65, 0x00, 0x73, 0x00, 0x74, 0x00, 0x22, 0x00, 0x3A, 0x00, 0x31, 0x00, 0x7D
        };
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, input, 0, input.length);
        
        ObjectReadContext readContext = ObjectReadContext.empty();
        
        JsonParser parser = bootstrapper.constructParser(
            readContext,
            0, 0,
            rootByteSymbols,
            rootCharSymbols,
            JsonFactory.Feature.CHARSET_DETECTION.getMask() | JsonFactory.Feature.CANONICALIZE_PROPERTY_NAMES.getMask()
        );
        
        assertNotNull(parser);
        assertTrue(parser instanceof ReaderBasedJsonParser);
        parser.close();
    }

    @Test
    public void testSkipUTF8BOM_AtEndOfStream() throws JacksonException {
        byte[] input = new byte[] { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF }; 
        DataInput dataInput = new DataInputStream(new ByteArrayInputStream(input));
        
        try {
            ByteSourceJsonBootstrapper.skipUTF8BOM(dataInput);
            fail("Expected JacksonException for incomplete BOM");
        } catch (JacksonException e) {
            assertNotNull(e);
            assertNotNull(e.getCause());
        }
    }

    @Test
    public void testDetectEncoding_InputStream_PartialReadThenEOF() throws JacksonException {
        InputStream inputStream = new InputStream() {
            private final byte[] data = new byte[] { 0x00, 0x7B }; 
            private int pos = 0;
            private boolean firstRead = true;
            
            @Override
            public int read(byte[] b, int off, int len) {
                if (pos >= data.length) return -1;
                if (firstRead) {
                    firstRead = false;
                    int toRead = Math.min(len, data.length - pos);
                    System.arraycopy(data, pos, b, off, toRead);
                    pos += toRead;
                    return toRead;
                }
                return -1; 
            }
            
            @Override
            public int read() {
                if (pos >= data.length) return -1;
                return data[pos++] & 0xFF;
            }
        };
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, inputStream);
        JsonEncoding encoding = bootstrapper.detectEncoding();
        
        assertEquals(JsonEncoding.UTF16_BE, encoding);
    }

    @Test
    public void testDetectEncoding_ByteArray_ThreeBytes_UTF8BOM() throws JacksonException {
        byte[] input = new byte[] { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF }; 
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, input, 0, input.length);
        JsonEncoding encoding = bootstrapper.detectEncoding();
        
        assertEquals(JsonEncoding.UTF8, encoding);
    }

    @Test
    public void testDetectEncoding_ByteArray_TwoBytes_UTF16BE() throws JacksonException {
        byte[] input = new byte[] { 0x00, 0x7B }; 
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, input, 0, input.length);
        JsonEncoding encoding = bootstrapper.detectEncoding();
        
        assertEquals(JsonEncoding.UTF16_BE, encoding);
    }

    @Test
    public void testDetectEncoding_ByteArray_ThreeBytes_UTF16BE() throws JacksonException {
        byte[] input = new byte[] { 0x00, 0x7B, 0x00 }; 
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, input, 0, input.length);
        JsonEncoding encoding = bootstrapper.detectEncoding();
        
        assertEquals(JsonEncoding.UTF16_BE, encoding);
    }

    @Test
    public void testDetectEncoding_InputStream_WeirdUCS4_2143() throws JacksonException {
        byte[] input = new byte[] { 0x00, 0x00, (byte) 0xFF, (byte) 0xFE };
        InputStream inputStream = new ByteArrayInputStream(input);
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, inputStream);
        
        try {
            bootstrapper.detectEncoding();
            fail("Expected JacksonException for weird UCS-4");
        } catch (JacksonException e) {
            assertTrue(e.getMessage().contains("Unsupported UCS-4 endianness (2143)"));
        }
    }

    @Test
    public void testDetectEncoding_InputStream_WeirdUCS4_3412() throws JacksonException {
        byte[] input = new byte[] { (byte) 0xFE, (byte) 0xFF, 0x00, 0x00 };
        InputStream inputStream = new ByteArrayInputStream(input);
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, inputStream);
        
        try {
            bootstrapper.detectEncoding();
            fail("Expected JacksonException for weird UCS-4");
        } catch (JacksonException e) {
            assertTrue(e.getMessage().contains("Unsupported UCS-4 endianness (3412)"));
        }
    }

    @Test
    public void testDetectEncoding_InputStream_UTF32_InOrder_2143() throws JacksonException {
        byte[] input = new byte[] { 0x00, 0x7B, 0x00, 0x00 }; 
        InputStream inputStream = new ByteArrayInputStream(input);
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, inputStream);
        
        try {
            bootstrapper.detectEncoding();
            fail("Expected JacksonException for weird UCS-4");
        } catch (JacksonException e) {
            assertTrue(e.getMessage().contains("Unsupported UCS-4 endianness (3412)"));
        }
    }

    @Test
    public void testDetectEncoding_InputStream_UTF32_InOrder_3412() throws JacksonException {
        byte[] input = new byte[] { 0x00, 0x00, 0x7B, 0x00 }; 
        InputStream inputStream = new ByteArrayInputStream(input);
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, inputStream);
        
        try {
            bootstrapper.detectEncoding();
            fail("Expected JacksonException for weird UCS-4");
        } catch (JacksonException e) {
            assertTrue(e.getMessage().contains("Unsupported UCS-4 endianness (2143)"));
        }
    }

    @Test
    public void testConstructReader_MergedStream_UTF8_WithBufferedData() throws JacksonException, IOException {
        byte[] input = "{\"test\":123456789}".getBytes(StandardCharsets.UTF_8); 
        InputStream inputStream = new ByteArrayInputStream(input);
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, inputStream);
        bootstrapper.detectEncoding(); 
        
        Reader reader = bootstrapper.constructReader();
        
        assertNotNull(reader);
        assertTrue(reader instanceof InputStreamReader);
        reader.close();
    }

    @Test
    public void testConstructReader_MergedStream_UTF16BE_WithBufferedData() throws JacksonException, IOException {
        byte[] input = new byte[] {
            (byte) 0xFE, (byte) 0xFF, 
            0x00, 0x7B, 0x00, 0x22, 0x00, 0x74, 0x00, 0x65, 0x00, 0x73, 0x00, 0x74, 0x00, 0x22, 0x00, 0x3A, 0x00, 0x31, 0x00, 0x7D, 0x00, 0x32, 0x00, 0x33 
        };
        InputStream inputStream = new ByteArrayInputStream(input);
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, inputStream);
        bootstrapper.detectEncoding(); 
        
        Reader reader = bootstrapper.constructReader();
        
        assertNotNull(reader);
        assertTrue(reader instanceof InputStreamReader);
        reader.close();
    }

    @Test
    public void testConstructReader_MergedStream_UTF16LE_WithBufferedData() throws JacksonException, IOException {
        byte[] input = new byte[] {
            (byte) 0xFF, (byte) 0xFE, 
            0x7B, 0x00, 0x22, 0x00, 0x74, 0x00, 0x65, 0x00, 0x73, 0x00, 0x74, 0x00, 0x22, 0x00, 0x3A, 0x00, 0x31, 0x00, 0x7D, 0x00, 0x32, 0x00, 0x33, 0x00 
        };
        InputStream inputStream = new ByteArrayInputStream(input);
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, inputStream);
        bootstrapper.detectEncoding(); 
        
        Reader reader = bootstrapper.constructReader();
        
        assertNotNull(reader);
        assertTrue(reader instanceof InputStreamReader);
        reader.close();
    }

    @Test
    public void testConstructReader_MergedStream_UTF32BE_WithBufferedData() throws JacksonException, IOException {
        byte[] input = new byte[] {
            0x00, 0x00, (byte) 0xFE, (byte) 0xFF, 
            0x00, 0x00, 0x00, 0x7B, 0x00, 0x00, 0x00, 0x22, 0x00, 0x00, 0x00, 0x74 
        };
        InputStream inputStream = new ByteArrayInputStream(input);
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, inputStream);
        bootstrapper.detectEncoding(); 
        
        Reader reader = bootstrapper.constructReader();
        
        assertNotNull(reader);
        assertTrue(reader.getClass().getName().contains("UTF32Reader"));
        reader.close();
    }

    @Test
    public void testConstructReader_MergedStream_UTF32LE_WithBufferedData() throws JacksonException, IOException {
        byte[] input = new byte[] {
            (byte) 0xFF, (byte) 0xFE, 0x00, 0x00, 
            0x7B, 0x00, 0x00, 0x00, 0x22, 0x00, 0x00, 0x00, 0x74, 0x00, 0x00, 0x00 
        };
        InputStream inputStream = new ByteArrayInputStream(input);
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, inputStream);
        bootstrapper.detectEncoding(); 
        
        Reader reader = bootstrapper.constructReader();
        
        assertNotNull(reader);
        assertTrue(reader.getClass().getName().contains("UTF32Reader"));
        reader.close();
    }

    @Test
    public void testEnsureLoaded_EOF_AfterPartialRead() throws JacksonException {
        InputStream inputStream = new InputStream() {
            private final byte[] data = new byte[] { 0x00, 0x7B }; 
            private int pos = 0;
            private boolean firstRead = true;
            
            @Override
            public int read(byte[] b, int off, int len) {
                if (pos >= data.length) return -1;
                if (firstRead) {
                    firstRead = false;
                    int toRead = Math.min(1, data.length - pos); 
                    b[off] = data[pos++];
                    return toRead;
                }
                int toRead = Math.min(len, data.length - pos);
                System.arraycopy(data, pos, b, off, toRead);
                pos += toRead;
                return toRead;
            }
            
            @Override
            public int read() {
                if (pos >= data.length) return -1;
                return data[pos++] & 0xFF;
            }
        };
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, inputStream);
        JsonEncoding encoding = bootstrapper.detectEncoding();
        
        assertEquals(JsonEncoding.UTF16_BE, encoding);
    }

    @Test
    public void testEnsureLoaded_EOF_Immediately() throws JacksonException {
        InputStream inputStream = new ByteArrayInputStream(new byte[0]);
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, inputStream);
        JsonEncoding encoding = bootstrapper.detectEncoding();
        
        assertEquals(JsonEncoding.UTF8, encoding);
    }

    @Test
    public void testConstructReader_UTF8_ByteArray_NoInputStream_NoBufferedData() throws JacksonException, IOException {
        byte[] input = "{\"test\":1}".getBytes(StandardCharsets.UTF_8);
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, input, 0, input.length);
        bootstrapper.detectEncoding(); 
        
        Reader reader = bootstrapper.constructReader();
        
        assertNotNull(reader);
        assertTrue(reader instanceof StringReader); 
        reader.close();
    }

    @Test
    public void testConstructReader_UTF8_ByteArray_LargeInput() throws JacksonException, IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 2000; i++) {
            sb.append("{\"test\":").append(i).append("},");
        }
        sb.append("{\"test\":2000}");
        byte[] input = sb.toString().getBytes(StandardCharsets.UTF_8);
        
        assertTrue(input.length > 8192);
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, input, 0, input.length);
        bootstrapper.detectEncoding();
        
        Reader reader = bootstrapper.constructReader();
        
        assertNotNull(reader);
        assertTrue(reader instanceof InputStreamReader); 
        reader.close();
    }

    @Test
    public void testDetectEncoding_InputStream_UTF8BOM_WithExtraData() throws JacksonException {
        byte[] input = new byte[] {
            (byte) 0xEF, (byte) 0xBB, (byte) 0xBF, 
            '{', '"', 't', 'e', 's', 't', '"', ':', '1', '}'
        };
        InputStream inputStream = new ByteArrayInputStream(input);
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, inputStream);
        JsonEncoding encoding = bootstrapper.detectEncoding();
        
        assertEquals(JsonEncoding.UTF8, encoding);
    }

    @Test
    public void testDetectEncoding_InputStream_UTF16BOM_WithExtraData() throws JacksonException {
        byte[] input = new byte[] {
            (byte) 0xFE, (byte) 0xFF, 
            0x00, 0x7B, 0x00, 0x22, 0x00, 0x74, 0x00, 0x65, 0x00, 0x73, 0x00, 0x74, 0x00, 0x22, 0x00, 0x3A, 0x00, 0x31, 0x00, 0x7D
        };
        InputStream inputStream = new ByteArrayInputStream(input);
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, inputStream);
        JsonEncoding encoding = bootstrapper.detectEncoding();
        
        assertEquals(JsonEncoding.UTF16_BE, encoding);
    }

    @Test
    public void testDetectEncoding_InputStream_UTF32BOM_WithExtraData() throws JacksonException {
        byte[] input = new byte[] {
            0x00, 0x00, (byte) 0xFE, (byte) 0xFF, 
            0x00, 0x00, 0x00, 0x7B, 0x00, 0x00, 0x00, 0x22
        };
        InputStream inputStream = new ByteArrayInputStream(input);
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, inputStream);
        JsonEncoding encoding = bootstrapper.detectEncoding();
        
        assertEquals(JsonEncoding.UTF32_BE, encoding);
    }

    @Test
    public void testConstructParser_UTF32BE_NoBOM_InputStream() throws JacksonException, IOException {
        byte[] input = new byte[] {
            0x00, 0x00, 0x00, 0x7B, 0x00, 0x00, 0x00, 0x22
        };
        InputStream inputStream = new ByteArrayInputStream(input);
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, inputStream);
        
        ObjectReadContext readContext = ObjectReadContext.empty();
        
        JsonParser parser = bootstrapper.constructParser(
            readContext,
            0, 0,
            rootByteSymbols,
            rootCharSymbols,
            JsonFactory.Feature.CHARSET_DETECTION.getMask()
        );
        
        assertNotNull(parser);
        assertTrue(parser instanceof ReaderBasedJsonParser);
        parser.close();
    }

    @Test
    public void testConstructParser_UTF32LE_NoBOM_InputStream() throws JacksonException, IOException {
        byte[] input = new byte[] {
            0x7B, 0x00, 0x00, 0x00, 0x22, 0x00, 0x00, 0x00
        };
        InputStream inputStream = new ByteArrayInputStream(input);
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, inputStream);
        
        ObjectReadContext readContext = ObjectReadContext.empty();
        
        JsonParser parser = bootstrapper.constructParser(
            readContext,
            0, 0,
            rootByteSymbols,
            rootCharSymbols,
            JsonFactory.Feature.CHARSET_DETECTION.getMask()
        );
        
        assertNotNull(parser);
        assertTrue(parser instanceof ReaderBasedJsonParser);
        parser.close();
    }

    @Test
    public void testSkipUTF8BOM_PartialBOM_ThenValidByte() throws JacksonException {
        byte[] input = new byte[] { (byte) 0xEF, (byte) 0xBB, 0x7B }; 
        DataInput dataInput = new DataInputStream(new ByteArrayInputStream(input));
        
        try {
            ByteSourceJsonBootstrapper.skipUTF8BOM(dataInput);
            fail("Expected JacksonException for invalid UTF-8 BOM");
        } catch (JacksonException e) {
            assertTrue(e.getMessage().contains("Unexpected byte 0x7b following 0xEF 0xBB"));
        }
    }

    @Test
    public void testSkipUTF8BOM_FirstByteNotBOM() throws JacksonException {
        byte[] input = new byte[] { 0x7B, 0x22, 0x74 }; 
        DataInput dataInput = new DataInputStream(new ByteArrayInputStream(input));
        
        int result = ByteSourceJsonBootstrapper.skipUTF8BOM(dataInput);
        
        assertEquals(0x7B, result); 
    }

    @Test
    public void testDetectEncoding_InputStream_SingleByte_ThenEOF() throws JacksonException {
        InputStream inputStream = new ByteArrayInputStream(new byte[] { 0x7B });
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, inputStream);
        JsonEncoding encoding = bootstrapper.detectEncoding();
        
        assertEquals(JsonEncoding.UTF8, encoding);
    }

    @Test
    public void testConstructReader_UTF16_InputStream_NoBufferedData() throws JacksonException, IOException {
        byte[] input = new byte[] {
            (byte) 0xFE, (byte) 0xFF, 
            0x00, 0x7B 
        };
        InputStream inputStream = new ByteArrayInputStream(input);
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, inputStream);
        bootstrapper.detectEncoding(); 
        
        Reader reader = bootstrapper.constructReader();
        
        assertNotNull(reader);
        assertTrue(reader instanceof InputStreamReader);
        reader.close();
    }

    @Test
    public void testConstructReader_UTF32_InputStream_NoBufferedData() throws JacksonException, IOException {
        byte[] input = new byte[] {
            0x00, 0x00, (byte) 0xFE, (byte) 0xFF, 
            0x00, 0x00, 0x00, 0x7B 
        };
        InputStream inputStream = new ByteArrayInputStream(input);
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, inputStream);
        bootstrapper.detectEncoding(); 
        
        Reader reader = bootstrapper.constructReader();
        
        assertNotNull(reader);
        assertTrue(reader.getClass().getName().contains("UTF32Reader"));
        reader.close();
    }

    @Test
    public void testConstructParser_UTF8_InputStream_NoCanonicalization() throws JacksonException, IOException {
        byte[] input = "{\"test\":1}".getBytes(StandardCharsets.UTF_8);
        InputStream inputStream = new ByteArrayInputStream(input);
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, inputStream);
        
        ObjectReadContext readContext = ObjectReadContext.empty();
        
        JsonParser parser = bootstrapper.constructParser(
            readContext,
            0, 0,
            rootByteSymbols,
            rootCharSymbols,
            JsonFactory.Feature.CHARSET_DETECTION.getMask() 
        );
        
        assertNotNull(parser);
        assertTrue(parser instanceof ReaderBasedJsonParser); 
        parser.close();
    }

    @Test
    public void testDetectEncoding_InputStream_FourBytes_UTF32BE_NoBOM() throws JacksonException {
        byte[] input = new byte[] { 0x00, 0x00, 0x00, 0x7B }; 
        InputStream inputStream = new ByteArrayInputStream(input);
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, inputStream);
        JsonEncoding encoding = bootstrapper.detectEncoding();
        
        assertEquals(JsonEncoding.UTF32_BE, encoding);
    }

    @Test
    public void testDetectEncoding_InputStream_FourBytes_UTF32LE_NoBOM() throws JacksonException {
        byte[] input = new byte[] { 0x7B, 0x00, 0x00, 0x00 }; 
        InputStream inputStream = new ByteArrayInputStream(input);
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, inputStream);
        JsonEncoding encoding = bootstrapper.detectEncoding();
        
        assertEquals(JsonEncoding.UTF32_LE, encoding);
    }

    @Test
    public void testDetectEncoding_InputStream_FourBytes_UTF16BE_NoBOM() throws JacksonException {
        byte[] input = new byte[] { 0x00, 0x7B, 0x00, 0x22 }; 
        InputStream inputStream = new ByteArrayInputStream(input);
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, inputStream);
        JsonEncoding encoding = bootstrapper.detectEncoding();
        
        assertEquals(JsonEncoding.UTF16_BE, encoding);
    }

    @Test
    public void testDetectEncoding_InputStream_FourBytes_UTF16LE_NoBOM() throws JacksonException {
        byte[] input = new byte[] { 0x7B, 0x00, 0x22, 0x00 }; 
        InputStream inputStream = new ByteArrayInputStream(input);
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, inputStream);
        JsonEncoding encoding = bootstrapper.detectEncoding();
        
        assertEquals(JsonEncoding.UTF16_LE, encoding);
    }

    @Test
    public void testConstructReader_UTF8_InputStream_ExactlyAtLimit_WithBufferedData() throws JacksonException, IOException {
        String base = "{\"a\":1}"; 
        int repeat = 8192 / base.length(); 
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < repeat; i++) {
            sb.append(base);
        }
        sb.append("12"); 
        String json = sb.toString();
        byte[] input = json.getBytes(StandardCharsets.UTF_8);
        
        assertEquals(8192, input.length);
        InputStream inputStream = new ByteArrayInputStream(input);
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, inputStream);
        bootstrapper.detectEncoding(); 
        
        Reader reader = bootstrapper.constructReader();
        
        assertNotNull(reader);
        assertTrue(reader instanceof InputStreamReader);
        reader.close();
    }

    @Test
    public void testConstructReader_UTF8_InputStream_OverLimit_WithBufferedData() throws JacksonException, IOException {
        StringBuilder sb = new StringBuilder();
        String base = "{\"a\":1}";
        int repeat = (8193 / base.length()) + 1;
        for (int i = 0; i < repeat; i++) {
            sb.append(base);
        }
        String json = sb.toString().substring(0, 8193);
        byte[] input = json.getBytes(StandardCharsets.UTF_8);
        
        assertTrue(input.length > 8192);
        InputStream inputStream = new ByteArrayInputStream(input);
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, inputStream);
        bootstrapper.detectEncoding(); 
        
        Reader reader = bootstrapper.constructReader();
        
        assertNotNull(reader);
        assertTrue(reader instanceof InputStreamReader);
        reader.close();
    }

    @Test
    public void testEnsureLoaded_MultipleReads_VariableChunkSize() throws JacksonException {
        InputStream inputStream = new InputStream() {
            private final byte[] data = "{\"test\":12345}".getBytes(StandardCharsets.UTF_8);
            private int pos = 0;
            private int readCall = 0;
            
            @Override
            public int read(byte[] b, int off, int len) {
                if (pos >= data.length) return -1;
                readCall++;
                int toRead = Math.min(readCall, data.length - pos);
                System.arraycopy(data, pos, b, off, toRead);
                pos += toRead;
                return toRead;
            }
            
            @Override
            public int read() {
                if (pos >= data.length) return -1;
                return data[pos++] & 0xFF;
            }
        };
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, inputStream);
        JsonEncoding encoding = bootstrapper.detectEncoding();
        
        assertEquals(JsonEncoding.UTF8, encoding);
    }

    @Test
    public void testDetectEncoding_ByteArray_UTF8BOM_Exactly3Bytes() throws JacksonException {
        byte[] input = new byte[] { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF };
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, input, 0, input.length);
        JsonEncoding encoding = bootstrapper.detectEncoding();
        
        assertEquals(JsonEncoding.UTF8, encoding);
    }

    @Test
    public void testDetectEncoding_ByteArray_UTF16BE_Exactly2Bytes() throws JacksonException {
        byte[] input = new byte[] { 0x00, 0x7B };
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, input, 0, input.length);
        JsonEncoding encoding = bootstrapper.detectEncoding();
        
        assertEquals(JsonEncoding.UTF16_BE, encoding);
    }

    @Test
    public void testDetectEncoding_ByteArray_UTF16LE_Exactly2Bytes() throws JacksonException {
        byte[] input = new byte[] { 0x7B, 0x00 };
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, input, 0, input.length);
        JsonEncoding encoding = bootstrapper.detectEncoding();
        
        assertEquals(JsonEncoding.UTF16_LE, encoding);
    }

    @Test
    public void testConstructParser_DetectsEncoding_UTF16BE_InputStream() throws JacksonException, IOException {
        byte[] input = new byte[] {
            (byte) 0xFE, (byte) 0xFF, 
            0x00, 0x7B, 0x00, 0x22
        };
        InputStream inputStream = new ByteArrayInputStream(input);
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, inputStream);
        
        ObjectReadContext readContext = ObjectReadContext.empty();
        
        JsonParser parser = bootstrapper.constructParser(
            readContext,
            0, 0,
            rootByteSymbols,
            rootCharSymbols,
            JsonFactory.Feature.CHARSET_DETECTION.getMask()
        );
        
        assertNotNull(parser);
        assertEquals(JsonEncoding.UTF16_BE, context.getEncoding());
        assertTrue(parser instanceof ReaderBasedJsonParser);
        parser.close();
    }

    @Test
    public void testConstructParser_DetectsEncoding_UTF32BE_InputStream() throws JacksonException, IOException {
        byte[] input = new byte[] {
            0x00, 0x00, (byte) 0xFE, (byte) 0xFF, 
            0x00, 0x00, 0x00, 0x7B
        };
        InputStream inputStream = new ByteArrayInputStream(input);
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, inputStream);
        
        ObjectReadContext readContext = ObjectReadContext.empty();
        
        JsonParser parser = bootstrapper.constructParser(
            readContext,
            0, 0,
            rootByteSymbols,
            rootCharSymbols,
            JsonFactory.Feature.CHARSET_DETECTION.getMask()
        );
        
        assertNotNull(parser);
        assertEquals(JsonEncoding.UTF32_BE, context.getEncoding());
        assertTrue(parser instanceof ReaderBasedJsonParser);
        parser.close();
    }

    @Test
    public void testConstructParser_DetectsEncoding_UTF32LE_InputStream() throws JacksonException, IOException {
        byte[] input = new byte[] {
            (byte) 0xFF, (byte) 0xFE, 0x00, 0x00, 
            0x7B, 0x00, 0x00, 0x00
        };
        InputStream inputStream = new ByteArrayInputStream(input);
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, inputStream);
        
        ObjectReadContext readContext = ObjectReadContext.empty();
        
        JsonParser parser = bootstrapper.constructParser(
            readContext,
            0, 0,
            rootByteSymbols,
            rootCharSymbols,
            JsonFactory.Feature.CHARSET_DETECTION.getMask()
        );
        
        assertNotNull(parser);
        assertEquals(JsonEncoding.UTF32_LE, context.getEncoding());
        assertTrue(parser instanceof ReaderBasedJsonParser);
        parser.close();
    }

    @Test
    public void testConstructParser_DetectsEncoding_UTF8_NoBOM_InputStream() throws JacksonException, IOException {
        byte[] input = "{\"test\":1}".getBytes(StandardCharsets.UTF_8);
        InputStream inputStream = new ByteArrayInputStream(input);
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, inputStream);
        
        ObjectReadContext readContext = ObjectReadContext.empty();
        
        JsonParser parser = bootstrapper.constructParser(
            readContext,
            0, 0,
            rootByteSymbols,
            rootCharSymbols,
            JsonFactory.Feature.CHARSET_DETECTION.getMask() | JsonFactory.Feature.CANONICALIZE_PROPERTY_NAMES.getMask()
        );
        
        assertNotNull(parser);
        assertEquals(JsonEncoding.UTF8, context.getEncoding());
        assertTrue(parser instanceof UTF8StreamJsonParser);
        parser.close();
    }

    @Test
    public void testHandleBOM_UTF32BE_BOM() throws JacksonException {
        byte[] input = new byte[] {
            0x00, 0x00, (byte) 0xFE, (byte) 0xFF, 
            0x00, 0x00, 0x00, 0x7B
        };
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, input, 0, input.length);
        JsonEncoding encoding = bootstrapper.detectEncoding();
        
        assertEquals(JsonEncoding.UTF32_BE, encoding);
    }

    @Test
    public void testHandleBOM_UTF32LE_BOM() throws JacksonException {
        byte[] input = new byte[] {
            (byte) 0xFF, (byte) 0xFE, 0x00, 0x00, 
            0x7B, 0x00, 0x00, 0x00
        };
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, input, 0, input.length);
        JsonEncoding encoding = bootstrapper.detectEncoding();
        
        assertEquals(JsonEncoding.UTF32_LE, encoding);
    }

    @Test
    public void testHandleBOM_UTF16BE_BOM() throws JacksonException {
        byte[] input = new byte[] {
            (byte) 0xFE, (byte) 0xFF, 
            0x00, 0x7B
        };
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, input, 0, input.length);
        JsonEncoding encoding = bootstrapper.detectEncoding();
        
        assertEquals(JsonEncoding.UTF16_BE, encoding);
    }

    @Test
    public void testHandleBOM_UTF16LE_BOM() throws JacksonException {
        byte[] input = new byte[] {
            (byte) 0xFF, (byte) 0xFE, 
            0x7B, 0x00
        };
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, input, 0, input.length);
        JsonEncoding encoding = bootstrapper.detectEncoding();
        
        assertEquals(JsonEncoding.UTF16_LE, encoding);
    }

    @Test
    public void testHandleBOM_UTF8_BOM() throws JacksonException {
        byte[] input = new byte[] {
            (byte) 0xEF, (byte) 0xBB, (byte) 0xBF, 
            0x7B
        };
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, input, 0, input.length);
        JsonEncoding encoding = bootstrapper.detectEncoding();
        
        assertEquals(JsonEncoding.UTF8, encoding);
    }

    @Test
    public void testHandleBOM_ReturnsFalse_WhenNoBOM() throws JacksonException {
        byte[] input = new byte[] { 0x7B, 0x22, 0x74, 0x65 }; 
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, input, 0, input.length);
        JsonEncoding encoding = bootstrapper.detectEncoding();
        
        assertEquals(JsonEncoding.UTF8, encoding);
    }

    @Test
    public void testHandleBOM_ReturnsFalse_ForUTF32NoBOM() throws JacksonException {
        byte[] input = new byte[] { 0x00, 0x00, 0x00, 0x7B, 0x00, 0x00, 0x00, 0x22 }; 
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, input, 0, input.length);
        JsonEncoding encoding = bootstrapper.detectEncoding();
        
        assertEquals(JsonEncoding.UTF32_BE, encoding);
    }

    @Test
    public void testHandleBOM_ReturnsFalse_ForUTF16NoBOM() throws JacksonException {
        byte[] input = new byte[] { 0x00, 0x7B, 0x00, 0x22 }; 
        
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, input, 0, input.length);
        JsonEncoding encoding = bootstrapper.detectEncoding();
        
        assertEquals(JsonEncoding.UTF16_BE, encoding);
    }
}
