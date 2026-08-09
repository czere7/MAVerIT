package tools.jackson.core.json;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.junit.Test;

import tools.jackson.core.Base64Variant;
import tools.jackson.core.JsonToken;
import tools.jackson.core.sym.PropertyNameMatcher;
import tools.jackson.core.util.TextBuffer;
import tools.jackson.core.io.IOContext;
import tools.jackson.core.StreamReadConstraints;
import tools.jackson.core.sym.ByteQuadsCanonicalizer;
import tools.jackson.core.io.SerializedString;
import tools.jackson.core.json.JsonFactory;

public class UTF8StreamJsonParserTest {

    private static final String BASE64_ALPHABET =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

    private UTF8StreamJsonParser createParser(byte[] json) throws IOException {
        ByteArrayInputStream in = new ByteArrayInputStream(json);
        JsonFactory factory = new JsonFactory();
        return (UTF8StreamJsonParser) factory.createParser(in);
    }

    @Test
    public void testReleaseBufferedWritesAllBytes() throws Exception {
        byte[] data = "{\"a\":1}".getBytes("UTF-8");
        UTF8StreamJsonParser parser = createParser(data);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        int written = parser.releaseBuffered(out);
        assertEquals(data.length, written);
        assertArrayEquals(data, out.toByteArray());

        int after = parser.releaseBuffered(new ByteArrayOutputStream());
        assertEquals(0, after);
    }

    @Test
    public void testSimpleParsingAndGetString() throws Exception {
        String json = "{\"msg\":\"hello\"}";
        UTF8StreamJsonParser parser = createParser(json.getBytes("UTF-8"));

        assertEquals(JsonToken.START_OBJECT, parser.nextToken());

        assertEquals(JsonToken.PROPERTY_NAME, parser.nextToken());
        assertEquals("msg", parser.currentName());

        assertEquals(JsonToken.VALUE_STRING, parser.nextToken());

        String val = parser.getString();
        assertEquals("hello", val);

        String asStr = parser.getValueAsString();
        assertEquals("hello", asStr);
    }

    @Test
    public void testBinaryDecoding() throws Exception {
        String base64Hello = "SGVsbG8=";
        String json = "{\"data\":\"" + base64Hello + "\"}";
        UTF8StreamJsonParser parser = createParser(json.getBytes("UTF-8"));

        assertEquals(JsonToken.START_OBJECT, parser.nextToken());
        assertEquals(JsonToken.PROPERTY_NAME, parser.nextToken());
        assertEquals(JsonToken.VALUE_STRING, parser.nextToken());

        Base64Variant var = new Base64Variant(
                "BASE64",
                BASE64_ALPHABET,
                true,
                '=',
                76);
        byte[] decoded = parser.getBinaryValue(var);

        assertArrayEquals("Hello".getBytes("UTF-8"), decoded);
    }

    @Test
    public void testNextNameWithSerializableString() throws Exception {
        String json = "{\"foo\":42}";
        UTF8StreamJsonParser parser = createParser(json.getBytes("UTF-8"));

        assertEquals(JsonToken.START_OBJECT, parser.nextToken());
        assertTrue(parser.currentToken().isStructStart());

        SerializedString sstr = new SerializedString("foo");

        boolean found = parser.nextName(sstr);
        assertTrue(found);

        // After calling nextName, the parser is positioned at the property name.
        // Advance to the value token.
        JsonToken t = parser.nextToken();
        assertEquals(JsonToken.VALUE_NUMBER_INT, t);

        int num = parser.getIntValue();
        assertEquals(42, num);
    }
}
