package tools.jackson.core.json;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

import org.junit.Test;

import tools.jackson.core.JsonParser;
import tools.jackson.core.JsonToken;
import tools.jackson.core.exc.StreamReadException;
import tools.jackson.core.json.JsonFactory;

/**
 * Additional tests to exercise uncovered mutation points
 * in {@link UTF8DataInputJsonParser}.
 */
public class UTF8DataInputJsonParserTest {

    private JsonParser createParser(byte[] jsonBytes) throws IOException {
        return new JsonFactory().createParser(jsonBytes);
    }

    @Test
    public void testParseSimpleString() throws Exception {
        byte[] json = "\"hello\"".getBytes(StandardCharsets.UTF_8);
        JsonParser parser = createParser(json);

        JsonToken token = parser.nextToken();
        assertEquals(JsonToken.VALUE_STRING, token);

        String value = parser.getString();
        assertEquals("hello", value);

        StringWriter writer = new StringWriter();
        int len = parser.getString(writer);
        assertEquals(5, len);
        assertEquals("hello", writer.toString());
    }

    @Test
    public void testParseNumber() throws Exception {
        byte[] json = "123 ".getBytes(StandardCharsets.UTF_8);
        JsonParser parser = createParser(json);

        JsonToken token = parser.nextToken();
        assertEquals(JsonToken.VALUE_NUMBER_INT, token);

        int intVal = parser.getIntValue();
        assertEquals(123, intVal);
    }

    @Test
    public void testInvalidTokenThrows() throws Exception {
        byte[] json = "@".getBytes(StandardCharsets.UTF_8);
        JsonParser parser = createParser(json);

        try {
            parser.nextToken();
            fail("Expected exception");
        } catch (StreamReadException e) {
            assertNotNull(e.getMessage());
        }
    }

    @Test
    public void testGetStringForVariousTokens() throws Exception {
        byte[] json = ("{"
                + "\"foo\":true,"
                + "\"bar\":null,"
                + "\"baz\":\"qux\","
                + "\"num\":123"
                + "}").getBytes(StandardCharsets.UTF_8);
        JsonParser parser = createParser(json);

        String[] expectedStrings = new String[]{
                "{",
                "foo",
                "true",
                "bar",
                "null",
                "baz",
                "qux",
                "num",
                "123",
                "}"
        };

        int i = 0;
        JsonToken token;
        while ((token = parser.nextToken()) != null) {
            assertEquals(expectedStrings[i++], parser.getString());
        }
        assertEquals(expectedStrings.length, i);
    }

    @Test
    public void testNumberParsingBranches() throws Exception {
        byte[] json = "12345".getBytes(StandardCharsets.UTF_8);
        JsonParser parser = createParser(json);

        JsonToken token = parser.nextToken();
        assertEquals(JsonToken.VALUE_NUMBER_INT, token);

        int intVal = parser.getIntValue();
        assertEquals(12345, intVal);

        long longVal = parser.getLongValue();
        assertEquals(12345L, longVal);

        Number numObj = parser.getNumberValue();
        assertTrue(numObj instanceof Integer);
        assertEquals(12345, numObj.intValue());
    }

    /**
     * Ensure that mismatched end markers are detected.
     */
    @Test
    public void testMismatchEndMarkerThrows() throws IOException {
        byte[] json = "{}]".getBytes(StandardCharsets.UTF_8); // object closed by a ']'
        JsonParser parser = createParser(json);
        try {
            while (parser.nextToken() != null) {
                // just consume tokens
            }
            fail("Expected StreamReadException due to mismatched end marker");
        } catch (StreamReadException e) {
            assertTrue(e.getMessage().contains("Unexpected close marker"));
        }
    }

    /**
     * Verify that an unexpected character after a keyword such as 'true'
     * triggers the _checkMatchEnd path.
     */
    @Test
    public void testCheckMatchEndInvalidToken() throws IOException {
        byte[] json = "truex".getBytes(StandardCharsets.UTF_8); // trailing 'x' after true
        JsonParser parser = createParser(json);
        try {
            while (parser.nextToken() != null) {
                // consume tokens
            }
            fail("Expected StreamReadException due to invalid token");
        } catch (StreamReadException e) {
            assertTrue(e.getMessage().contains("Unrecognized token"));
        }
    }

    /**
     * Test that binary values are decoded correctly from a string.
     */
    @Test
    public void testBase64Decoding() throws Exception {
        // "YWJj" is Base64 for "abc"
        byte[] json = "\"YWJj\"".getBytes(StandardCharsets.UTF_8);
        JsonParser parser = createParser(json);

        assertEquals(JsonToken.VALUE_STRING, parser.nextToken());
        byte[] bytes = parser.getBinaryValue(); // uses default variant
        assertArrayEquals(new byte[]{97, 98, 99}, bytes);
    }

    /**
     * Calling getBinaryValue on a non-string token should raise an exception.
     */
    @Test
    public void testGetBinaryValueOnNonStringThrows() throws IOException {
        byte[] json = "123".getBytes(StandardCharsets.UTF_8);
        JsonParser parser = createParser(json);

        assertEquals(JsonToken.VALUE_NUMBER_INT, parser.nextToken());
        try {
            parser.getBinaryValue();
            fail("Expected StreamReadException due to non-string binary access");
        } catch (StreamReadException e) {
            assertTrue(e.getMessage().contains("Current token"));
        }
    }
}
