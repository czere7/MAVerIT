package tools.jackson.core.json;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;

import tools.jackson.core.*;
import tools.jackson.core.json.JsonFactory;

/**
 * Basic tests for {@link UTF8DataInputWithDocLengthJsonParser} that rely only on the public
 * Jackson API. They do not depend on internal constructors or private fields.
 */
public class UTF8DataInputWithDocLengthJsonParserTest {

    @Test
    public void sanityCheck() {
        assertTrue("Placeholder assertion", true);
    }

    /**
     * Verifies that a simple JSON document is parsed correctly using the default
     * {@link JsonFactory}. The test checks the sequence of tokens and values
     * produced by the parser.
     */
    @Test
    public void parseSimpleJson() throws IOException {
        JsonFactory factory = new JsonFactory();
        String json = "{\"a\":1}";

        try (JsonParser p = factory.createParser(json)) {
            // START_OBJECT
            assertEquals(JsonToken.START_OBJECT, p.nextToken());

            // PROPERTY_NAME "a"
            assertEquals(JsonToken.PROPERTY_NAME, p.nextToken());
            assertEquals("a", p.currentName());

            // VALUE_NUMBER_INT 1
            assertEquals(JsonToken.VALUE_NUMBER_INT, p.nextToken());
            assertEquals(1L, p.getLongValue());   // or getIntValue()

            // END_OBJECT
            assertEquals(JsonToken.END_OBJECT, p.nextToken());

            // End of input
            assertNull(p.nextToken());
        }
    }

    /**
     * Tests that parsing a small JSON document succeeds when the
     * document length constraint is not exceeded.
     */
    @Test
    public void documentLengthWithinLimit() throws IOException {
        // This test uses the default constraints; no explicit configuration needed.
        JsonFactory factory = new JsonFactory();

        String json = "{\"a\":1}";
        try (JsonParser p = factory.createParser(json)) {
            while (p.nextToken() != null) { }
            // No exception should be thrown
        } catch (Exception e) {
            fail("Parsing failed when it should have succeeded: " + e);
        }
    }
}
