package tools.jackson.core.type;

import org.junit.Test;
import static org.junit.Assert.*;

import tools.jackson.core.JsonToken;

/**
 * Unit tests for {@link WritableTypeId}.
 */
public class WritableTypeIdTest {

    @Test
    public void testDefaultConstructor() {
        WritableTypeId w = new WritableTypeId();
        assertNull("forValue should be null", w.forValue);
        assertNull("forValueType should be null", w.forValueType);
        assertNull("id should be null", w.id);
        assertNull("asProperty should be null", w.asProperty);
        assertNull("include should be null", w.include);
        assertNull("valueShape should be null", w.valueShape);
        assertFalse("wrapperWritten should default to false", w.wrapperWritten);
        assertNull("extra should be null", w.extra);
    }

    @Test
    public void testConstructorValueAndShape() {
        WritableTypeId w = new WritableTypeId("foo", JsonToken.VALUE_STRING);
        assertEquals("forValue should match constructor arg", "foo", w.forValue);
        assertNull("id should be null", w.id);
        assertEquals("valueShape should match constructor arg",
                JsonToken.VALUE_STRING, w.valueShape);
    }

    @Test
    public void testConstructorValueTypeAndShape() {
        WritableTypeId w = new WritableTypeId("bar", String.class, JsonToken.START_OBJECT);
        assertEquals("forValue should match constructor arg", "bar", w.forValue);
        assertNull("id should be null", w.id);
        assertEquals("valueShape should match constructor arg",
                JsonToken.START_OBJECT, w.valueShape);
        assertSame("forValueType should be set to supplied type",
                String.class, w.forValueType);
    }

    @Test
    public void testConstructorWithId() {
        WritableTypeId w = new WritableTypeId(123, JsonToken.VALUE_NUMBER_INT, "ID");
        assertEquals("forValue should match constructor arg", 123, w.forValue);
        assertEquals("id should match constructor arg", "ID", w.id);
        assertEquals("valueShape should match constructor arg",
                JsonToken.VALUE_NUMBER_INT, w.valueShape);
    }

    @Test
    public void testNullArguments() {
        WritableTypeId w = new WritableTypeId(null, (JsonToken) null);
        assertNull("forValue should be null", w.forValue);
        assertNull("valueShape should be null", w.valueShape);
        // All other fields remain default
        assertNull("id should be null", w.id);
    }

    @Test
    public void testInclusionRequiresObjectContext() {
        for (WritableTypeId.Inclusion inc : WritableTypeId.Inclusion.values()) {
            boolean expected = (inc == WritableTypeId.Inclusion.METADATA_PROPERTY)
                    || (inc == WritableTypeId.Inclusion.PAYLOAD_PROPERTY);
            assertEquals("requiresObjectContext should be correct for " + inc,
                    expected, inc.requiresObjectContext());
        }
    }

    @Test
    public void testWrapperWrittenFlag() {
        WritableTypeId w = new WritableTypeId();
        assertFalse(w.wrapperWritten);
        w.wrapperWritten = true;
        assertTrue(w.wrapperWritten);
    }

    /* --------------------------------------------------------------------- */
    /* Additional tests added to increase branch coverage                 */
    /* --------------------------------------------------------------------- */

    @Test
    public void testConstructorWithNullValue() {
        // Value is null but shape provided; should still construct without error
        WritableTypeId w = new WritableTypeId(null, JsonToken.VALUE_STRING);
        assertNull("forValue should be null", w.forValue);
        assertEquals("valueShape should match constructor arg",
                JsonToken.VALUE_STRING, w.valueShape);
    }

    @Test
    public void testConstructorWithNullId() {
        // id is explicitly set to null
        WritableTypeId w = new WritableTypeId("foo", JsonToken.VALUE_STRING, null);
        assertEquals("forValue should match constructor arg", "foo", w.forValue);
        assertNull("id should be null as passed", w.id);
        assertEquals("valueShape should match constructor arg",
                JsonToken.VALUE_STRING, w.valueShape);
    }

    @Test
    public void testMutableFields() {
        WritableTypeId w = new WritableTypeId();
        w.forValue = "test";
        w.forValueType = Integer.class;
        w.id = 42;
        w.asProperty = "propName";
        w.include = WritableTypeId.Inclusion.WRAPPER_ARRAY;
        w.valueShape = JsonToken.START_OBJECT;
        w.wrapperWritten = true;
        w.extra = new Object();

        assertEquals("forValue should be mutated", "test", w.forValue);
        assertSame("forValueType should be mutated", Integer.class, w.forValueType);
        assertEquals("id should be mutated", 42, w.id);
        assertEquals("asProperty should be mutated", "propName", w.asProperty);
        assertEquals("include should be mutated", WritableTypeId.Inclusion.WRAPPER_ARRAY, w.include);
        assertEquals("valueShape should be mutated", JsonToken.START_OBJECT, w.valueShape);
        assertTrue("wrapperWritten should be true", w.wrapperWritten);
        assertNotNull("extra should be set", w.extra);
    }

    @Test
    public void testAsPropertyAndExtra() {
        WritableTypeId w = new WritableTypeId();
        // Initially null
        assertNull(w.asProperty);
        assertNull(w.extra);

        w.asProperty = "customProp";
        Object extraObj = new Object();
        w.extra = extraObj;

        assertEquals("asProperty should be set", "customProp", w.asProperty);
        assertSame("extra should refer to the same object", extraObj, w.extra);
    }

    /* --------------------------------------------------------------------- */
    /* New tests targeting edge‑cases and constructor variations           */
    /* --------------------------------------------------------------------- */

    @Test
    public void testConstructorWithNullValueType() {
        WritableTypeId w = new WritableTypeId("someValue", (Class<?>) null, JsonToken.VALUE_STRING);
        assertEquals("forValue should match constructor arg", "someValue", w.forValue);
        assertEquals("valueShape should match constructor argument",
                JsonToken.VALUE_STRING, w.valueShape);
        assertNull("forValueType should be null when passed as null", w.forValueType);
        assertNull("id should be null by default", w.id);
    }

    @Test
    public void testConstructorWithNonNullValueTypeAndNullShape() {
        WritableTypeId w = new WritableTypeId("someValue", String.class, null);
        assertEquals("forValue should match constructor arg", "someValue", w.forValue);
        assertSame("forValueType should be set to supplied type",
                String.class, w.forValueType);
        assertNull("valueShape should be null when passed as null", w.valueShape);
    }

    @Test
    public void testConstructorWithAllNulls() {
        WritableTypeId w = new WritableTypeId(null, JsonToken.VALUE_STRING, null);
        assertNull("forValue should be null", w.forValue);
        assertEquals("valueShape should match constructor argument",
                JsonToken.VALUE_STRING, w.valueShape);
        assertNull("id should be null", w.id);
    }

    @Test
    public void testEnumRequiresObjectContextAllValues() {
        for (WritableTypeId.Inclusion inc : WritableTypeId.Inclusion.values()) {
            boolean result = inc.requiresObjectContext();
            if (inc == WritableTypeId.Inclusion.METADATA_PROPERTY ||
                inc == WritableTypeId.Inclusion.PAYLOAD_PROPERTY) {
                assertTrue("Expected true for " + inc, result);
            } else {
                assertFalse("Expected false for " + inc, result);
            }
        }
    }

    @Test
    public void testMutableAfterConstructor() {
        WritableTypeId w = new WritableTypeId(10, JsonToken.VALUE_NUMBER_INT, null);
        // Verify initial state
        assertEquals(10, w.forValue);
        assertNull(w.id);
        assertEquals(JsonToken.VALUE_NUMBER_INT, w.valueShape);

        // Mutate fields
        w.id = "ID123";
        w.asProperty = "prop";
        w.include = WritableTypeId.Inclusion.PARENT_PROPERTY;
        w.wrapperWritten = true;

        assertEquals("ID123", w.id);
        assertEquals("prop", w.asProperty);
        assertEquals(WritableTypeId.Inclusion.PARENT_PROPERTY, w.include);
        assertTrue(w.wrapperWritten);
    }
}
