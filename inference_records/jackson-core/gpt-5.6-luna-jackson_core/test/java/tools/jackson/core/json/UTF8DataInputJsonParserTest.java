package tools.jackson.core.json;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

import org.junit.Before;
import org.junit.Test;

import tools.jackson.core.Base64Variant;
import tools.jackson.core.JsonToken;
import tools.jackson.core.ObjectReadContext;
import tools.jackson.core.StreamReadConstraints;
import tools.jackson.core.exc.InputCoercionException;
import tools.jackson.core.exc.StreamReadException;
import tools.jackson.core.io.ContentReference;
import tools.jackson.core.io.IOContext;
import tools.jackson.core.sym.ByteQuadsCanonicalizer;
import tools.jackson.core.util.BufferRecycler;
import tools.jackson.core.util.TextBuffer;

public class UTF8DataInputJsonParserTest {
    private IOContext ioContext;
    private ByteQuadsCanonicalizer symbols;

    @Before
    public void setUp() {
        ioContext = mock(IOContext.class);
        when(ioContext.constructReadConstrainedTextBuffer())
                .thenReturn(new TextBuffer((BufferRecycler) null));
        when(ioContext.streamReadConstraints())
                .thenReturn(StreamReadConstraints.defaults());
        when(ioContext.contentReference())
                .thenReturn(ContentReference.unknown());
        when(ioContext.allocBase64Buffer()).thenReturn(new byte[1024]);

        symbols = mock(ByteQuadsCanonicalizer.class);
        when(symbols.willInternStrings()).thenReturn(false);
        when(symbols.isCanonicalizing()).thenReturn(false);
        when(symbols.addName(anyString(), anyInt()))
                .thenAnswer(invocation -> invocation.getArgument(0));
        when(symbols.addName(anyString(), anyInt(), anyInt()))
                .thenAnswer(invocation -> invocation.getArgument(0));
        when(symbols.addName(anyString(), anyInt(), anyInt(), anyInt()))
                .thenAnswer(invocation -> invocation.getArgument(0));
        when(symbols.addName(anyString(), any(int[].class), anyInt()))
                .thenAnswer(invocation -> invocation.getArgument(0));
    }

    private UTF8DataInputJsonParser parser(String json) {
        return parser(json, 0);
    }

    private UTF8DataInputJsonParser parser(String json, int features) {
        return new UTF8DataInputJsonParser(
                ObjectReadContext.empty(), ioContext, 0, features,
                new DataInputStream(new ByteArrayInputStream(
                        json.getBytes(StandardCharsets.UTF_8))),
                symbols, -1);
    }

    private Base64Variant base64(boolean padding) {
        return new Base64Variant(
                "test",
                "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/",
                padding, '=', 76);
    }

    @Test
    public void parsesArrayWithStringsNumbersBooleansAndNull() throws Exception {
        UTF8DataInputJsonParser p =
                parser("[\"hello\\n\\u263A\",-12,3.5,true,false,null]");

        assertEquals(JsonToken.START_ARRAY, p.nextToken());

        assertEquals(JsonToken.VALUE_STRING, p.nextToken());
        assertEquals("hello\n\u263A", p.getString());
        assertEquals(7, p.getStringLength());

        assertEquals(JsonToken.VALUE_NUMBER_INT, p.nextToken());
        assertEquals(-12, p.getIntValue());
        assertEquals(-12, p.getValueAsInt());

        assertEquals(JsonToken.VALUE_NUMBER_FLOAT, p.nextToken());
        assertEquals(3.5, p.getDoubleValue(), 0.0);

        assertEquals(JsonToken.VALUE_TRUE, p.nextToken());
        assertTrue(p.getValueAsBoolean(false));

        assertEquals(JsonToken.VALUE_FALSE, p.nextToken());
        assertFalse(p.getValueAsBoolean(true));

        assertEquals(JsonToken.VALUE_NULL, p.nextToken());
        assertEquals("null", p.getString());

        assertEquals(JsonToken.END_ARRAY, p.nextToken());
        assertNull(p.nextToken());
        assertTrue(p.isClosed());
    }

    @Test
    public void parsesObjectNamesAndUsesConvenienceAccessors() throws Exception {
        UTF8DataInputJsonParser p =
                parser("{\"name\":\"value\",\"count\":42,\"enabled\":true}");

        assertEquals(JsonToken.START_OBJECT, p.nextToken());
        assertEquals(JsonToken.PROPERTY_NAME, p.nextToken());
        assertEquals("name", p.currentName());
        assertEquals("name", p.getValueAsString());

        assertEquals(JsonToken.VALUE_STRING, p.nextToken());
        assertEquals("value", p.getString());

        assertEquals(JsonToken.PROPERTY_NAME, p.nextToken());
        assertEquals(42, p.nextIntValue(-1));

        assertEquals(JsonToken.PROPERTY_NAME, p.nextToken());
        assertEquals(Boolean.TRUE, p.nextBooleanValue());

        assertEquals(JsonToken.END_OBJECT, p.nextToken());
        assertNull(p.nextToken());
    }

    @Test
    public void parsesUtf8StringAndStreamsItsContents() throws Exception {
        UTF8DataInputJsonParser p =
                parser("[\"caf\u00E9 \uD83D\uDE00\"]");

        assertEquals(JsonToken.START_ARRAY, p.nextToken());
        assertEquals(JsonToken.VALUE_STRING, p.nextToken());

        StringWriter writer = new StringWriter();
        assertEquals(7L, p.readString(writer));
        assertEquals("café 😀", writer.toString());

        assertEquals(JsonToken.END_ARRAY, p.nextToken());
    }

    @Test
    public void supportsConfiguredNonStandardFeatures() throws Exception {
        int features = JsonReadFeature.ALLOW_SINGLE_QUOTES.getMask()
                | JsonReadFeature.ALLOW_UNQUOTED_PROPERTY_NAMES.getMask()
                | JsonReadFeature.ALLOW_LEADING_PLUS_SIGN_FOR_NUMBERS.getMask()
                | JsonReadFeature.ALLOW_LEADING_DECIMAL_POINT_FOR_NUMBERS.getMask();

        UTF8DataInputJsonParser p =
                parser("{'first':+7,second:.5}", features);

        assertEquals(JsonToken.START_OBJECT, p.nextToken());
        assertEquals("first", p.nextName());
        assertEquals(7, p.nextIntValue(-1));

        assertEquals("second", p.nextName());
        assertEquals(-1, p.nextIntValue(-1));
        assertEquals(JsonToken.VALUE_NUMBER_FLOAT, p.currentToken());
        assertEquals(0.5, p.getDoubleValue(), 0.0);

        assertEquals(JsonToken.END_OBJECT, p.nextToken());
    }

    @Test
    public void decodesBase64StringAndCanWriteDecodedBytes() throws Exception {
        UTF8DataInputJsonParser p = parser("[\"SGVsbG8=\"]");
        p.nextToken();
        p.nextToken();

        assertArrayEquals("Hello".getBytes(StandardCharsets.UTF_8),
                p.getBinaryValue(base64(true)));

        UTF8DataInputJsonParser streaming = parser("[\"SGVsbG8=\"]");
        streaming.nextToken();
        streaming.nextToken();

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        assertEquals(5, streaming.readBinaryValue(base64(true), output));
        assertArrayEquals("Hello".getBytes(StandardCharsets.UTF_8),
                output.toByteArray());
    }

    @Test
    public void decodesBase64WithWhitespaceBetweenUnits() throws Exception {
        UTF8DataInputJsonParser p = parser("[\"TWFu \\n\"]");
        p.nextToken();
        p.nextToken();

        assertArrayEquals(new byte[] {'M', 'a', 'n'},
                p.getBinaryValue(base64(false)));
    }

    @Test
    public void supportsTrailingCommasAndMissingValues() throws Exception {
        UTF8DataInputJsonParser p =
                parser("[1,]", JsonReadFeature.ALLOW_TRAILING_COMMA.getMask());

        assertEquals(JsonToken.START_ARRAY, p.nextToken());
        assertEquals(1, p.nextIntValue(-1));
        assertEquals(JsonToken.END_ARRAY, p.nextToken());

        p = parser("[1,,2]", JsonReadFeature.ALLOW_MISSING_VALUES.getMask());
        assertEquals(JsonToken.START_ARRAY, p.nextToken());
        assertEquals(1, p.nextIntValue(-1));
        assertEquals(JsonToken.VALUE_NULL, p.nextToken());
        assertEquals(JsonToken.VALUE_NUMBER_INT, p.nextToken());
        assertEquals(2, p.getIntValue());
        assertEquals(JsonToken.END_ARRAY, p.nextToken());
    }

    @Test
    public void convertsFloatingPointAndLongValuesToIntegerAccessors()
            throws Exception {
        UTF8DataInputJsonParser p = parser("[1.0,2147483648]");

        assertEquals(JsonToken.START_ARRAY, p.nextToken());
        assertEquals(JsonToken.VALUE_NUMBER_FLOAT, p.nextToken());
        assertEquals(1, p.getValueAsInt());
        assertEquals(1, p.getValueAsInt(-1));

        assertEquals(JsonToken.VALUE_NUMBER_INT, p.nextToken());
        assertEquals(2147483648L, p.getLongValue());

        try {
            p.getValueAsInt();
            fail("Expected an integer overflow exception");
        } catch (InputCoercionException expected) {
            assertTrue(expected.getMessage().contains("out of range"));
        }
    }

    @Test
    public void exposesInputSourceAndBufferedReleaseContract()
            throws Exception {
        DataInputStream input = new DataInputStream(new ByteArrayInputStream(
                "true ".getBytes(StandardCharsets.UTF_8)));

        UTF8DataInputJsonParser p = new UTF8DataInputJsonParser(
                ObjectReadContext.empty(), ioContext, 0, 0, input, symbols, -1);

        assertSame(input, p.streamReadInputSource());
        assertEquals(0, p.releaseBuffered(new ByteArrayOutputStream()));
        assertFalse(p.willInternPropertyNames());
    }

    @Test
    public void closesMatchingArrayAndObjectScopesWithCorrectTokens()
            throws Exception {
        UTF8DataInputJsonParser p = parser("[{}]");

        assertEquals(JsonToken.START_ARRAY, p.nextToken());
        assertEquals(JsonToken.START_OBJECT, p.nextToken());
        assertEquals(JsonToken.END_OBJECT, p.nextToken());
        assertEquals(JsonToken.END_ARRAY, p.nextToken());
        assertNull(p.nextToken());

        p = parser("{\"a\":[]}");
        assertEquals(JsonToken.START_OBJECT, p.nextToken());
        assertEquals(JsonToken.PROPERTY_NAME, p.nextToken());
        assertEquals(JsonToken.START_ARRAY, p.nextToken());
        assertEquals(JsonToken.END_ARRAY, p.nextToken());
        assertEquals(JsonToken.END_OBJECT, p.nextToken());
        assertNull(p.nextToken());
    }

    @Test
    public void rejectsMissingCommaBetweenArrayValues() throws Exception {
        UTF8DataInputJsonParser p = parser("[1 2]");
        p.nextToken();
        p.nextToken();

        try {
            p.nextToken();
            fail("Expected a missing comma exception");
        } catch (StreamReadException expected) {
            assertTrue(expected.getMessage().contains("comma"));
        }
    }

    @Test
    public void rejectsMismatchedEndMarker() throws Exception {
        UTF8DataInputJsonParser p = parser("{]");
        assertEquals(JsonToken.START_OBJECT, p.nextToken());

        try {
            p.nextToken();
            fail("Expected mismatched end marker");
        } catch (StreamReadException expected) {
            assertTrue(expected.getMessage().contains("Unexpected close marker"));
            assertTrue(expected.getMessage().contains("expected '}'"));
            assertNotNull(expected.getLocation());
        }
    }

    @Test
    public void rejectsInvalidBase64Character() throws Exception {
        UTF8DataInputJsonParser p = parser("[\"!!!!\"]");
        p.nextToken();
        p.nextToken();

        try {
            p.getBinaryValue(base64(true));
            fail("Expected invalid base64 exception");
        } catch (StreamReadException expected) {
            assertTrue(expected.getMessage().contains("base64"));
        }
    }

    @Test
    public void rejectsInvalidUtf8InsideString() throws Exception {
        byte[] input = new byte[] {'[', '"', (byte) 0xC3, ']', ']'};
        UTF8DataInputJsonParser p = new UTF8DataInputJsonParser(
                ObjectReadContext.empty(), ioContext, 0, 0,
                new DataInputStream(new ByteArrayInputStream(input)),
                symbols, -1);

        p.nextToken();
        p.nextToken();

        try {
            p.getString();
            fail("Expected invalid UTF-8 exception");
        } catch (StreamReadException expected) {
            assertTrue(expected.getMessage().contains("UTF-8"));
        }
    }
}
