package tools.jackson.core.type;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import tools.jackson.core.JsonToken;

public class WritableTypeIdTest {

    @Test
    public void defaultConstructorInitializesOptionalState() {
        WritableTypeId typeId = new WritableTypeId();

        assertNull(typeId.forValue);
        assertNull(typeId.forValueType);
        assertNull(typeId.id);
        assertNull(typeId.asProperty);
        assertNull(typeId.include);
        assertNull(typeId.valueShape);
        assertFalse(typeId.wrapperWritten);
        assertNull(typeId.extra);
    }

    @Test
    public void valueAndShapeConstructorStoresValueAndShape() {
        Object value = new Object();

        WritableTypeId typeId = new WritableTypeId(value, JsonToken.START_OBJECT);

        assertSame(value, typeId.forValue);
        assertSame(JsonToken.START_OBJECT, typeId.valueShape);
        assertNull(typeId.forValueType);
        assertNull(typeId.id);
        assertFalse(typeId.wrapperWritten);
    }

    @Test
    public void valueShapeAndIdConstructorStoresExplicitId() {
        Object value = new Object();
        Object id = new Object();

        WritableTypeId typeId = new WritableTypeId(value, JsonToken.VALUE_STRING, id);

        assertSame(value, typeId.forValue);
        assertSame(JsonToken.VALUE_STRING, typeId.valueShape);
        assertSame(id, typeId.id);
        assertNull(typeId.forValueType);
    }

    @Test
    public void valueTypeConstructorStoresEffectiveType() {
        Object value = new Object();

        WritableTypeId typeId = new WritableTypeId(
                value, CharSequence.class, JsonToken.VALUE_STRING);

        assertSame(value, typeId.forValue);
        assertSame(CharSequence.class, typeId.forValueType);
        assertSame(JsonToken.VALUE_STRING, typeId.valueShape);
        assertNull(typeId.id);
    }

    @Test
    public void constructorsAllowNullValuesAndShapes() {
        WritableTypeId valueOnly = new WritableTypeId(null, (JsonToken) null);
        WritableTypeId withType = new WritableTypeId(null, String.class, (JsonToken) null);
        WritableTypeId withId = new WritableTypeId(
                null, (JsonToken) null, (Object) null);

        assertNull(valueOnly.forValue);
        assertNull(valueOnly.valueShape);

        assertNull(withType.forValue);
        assertSame(String.class, withType.forValueType);
        assertNull(withType.valueShape);

        assertNull(withId.forValue);
        assertNull(withId.valueShape);
        assertNull(withId.id);
    }

    @Test
    public void inclusionRequiresObjectContextOnlyForPropertyModes() {
        assertFalse(WritableTypeId.Inclusion.WRAPPER_ARRAY.requiresObjectContext());
        assertFalse(WritableTypeId.Inclusion.WRAPPER_OBJECT.requiresObjectContext());
        assertTrue(WritableTypeId.Inclusion.METADATA_PROPERTY.requiresObjectContext());
        assertTrue(WritableTypeId.Inclusion.PAYLOAD_PROPERTY.requiresObjectContext());
        assertFalse(WritableTypeId.Inclusion.PARENT_PROPERTY.requiresObjectContext());
    }

    @Test
    public void publicCommunicationFieldsAreMutable() {
        WritableTypeId typeId = new WritableTypeId();

        Object value = new Object();
        Object id = new Object();
        Object extra = new Object();

        typeId.forValue = value;
        typeId.forValueType = Number.class;
        typeId.id = id;
        typeId.asProperty = "type";
        typeId.include = WritableTypeId.Inclusion.WRAPPER_OBJECT;
        typeId.valueShape = JsonToken.START_ARRAY;
        typeId.wrapperWritten = true;
        typeId.extra = extra;

        assertSame(value, typeId.forValue);
        assertSame(Number.class, typeId.forValueType);
        assertSame(id, typeId.id);
        assertSame("type", typeId.asProperty);
        assertSame(WritableTypeId.Inclusion.WRAPPER_OBJECT, typeId.include);
        assertSame(JsonToken.START_ARRAY, typeId.valueShape);
        assertTrue(typeId.wrapperWritten);
        assertSame(extra, typeId.extra);
    }

    @Test
    public void inclusionDefinesAllSupportedModes() {
        assertSame(
                WritableTypeId.Inclusion.WRAPPER_ARRAY,
                WritableTypeId.Inclusion.valueOf("WRAPPER_ARRAY"));
        assertSame(
                WritableTypeId.Inclusion.WRAPPER_OBJECT,
                WritableTypeId.Inclusion.valueOf("WRAPPER_OBJECT"));
        assertSame(
                WritableTypeId.Inclusion.METADATA_PROPERTY,
                WritableTypeId.Inclusion.valueOf("METADATA_PROPERTY"));
        assertSame(
                WritableTypeId.Inclusion.PAYLOAD_PROPERTY,
                WritableTypeId.Inclusion.valueOf("PAYLOAD_PROPERTY"));
        assertSame(
                WritableTypeId.Inclusion.PARENT_PROPERTY,
                WritableTypeId.Inclusion.valueOf("PARENT_PROPERTY"));
    }

    @Test
    public void requiresObjectContextReturnsFalseForEachNonPropertyMode() {
        assertFalse(WritableTypeId.Inclusion.WRAPPER_ARRAY.requiresObjectContext());
        assertFalse(WritableTypeId.Inclusion.WRAPPER_OBJECT.requiresObjectContext());
        assertFalse(WritableTypeId.Inclusion.PARENT_PROPERTY.requiresObjectContext());
    }

    @Test
    public void requiresObjectContextReturnsTrueForEachPropertyMode() {
        assertTrue(WritableTypeId.Inclusion.METADATA_PROPERTY.requiresObjectContext());
        assertTrue(WritableTypeId.Inclusion.PAYLOAD_PROPERTY.requiresObjectContext());
    }

    @Test
    public void valueTypeConstructorLeavesIdAndOtherCommunicationStateUnset() {
        Object value = new Object();

        WritableTypeId typeId = new WritableTypeId(
                value, Object.class, JsonToken.START_ARRAY);

        assertSame(value, typeId.forValue);
        assertSame(Object.class, typeId.forValueType);
        assertSame(JsonToken.START_ARRAY, typeId.valueShape);
        assertNull(typeId.id);
        assertNull(typeId.asProperty);
        assertNull(typeId.include);
        assertFalse(typeId.wrapperWritten);
        assertNull(typeId.extra);
    }

    @Test
    public void explicitIdConstructorPreservesIdentityOfNullAndNonNullArguments() {
        Object value = new Object();
        Object id = new Object();

        WritableTypeId typeId = new WritableTypeId(value, null, id);

        assertSame(value, typeId.forValue);
        assertNull(typeId.valueShape);
        assertSame(id, typeId.id);
        assertNull(typeId.forValueType);
    }

    @Test
    public void valueAndShapeConstructorLeavesAllOptionalCommunicationFieldsUnset() {
        Object value = new Object();

        WritableTypeId typeId = new WritableTypeId(value, JsonToken.START_ARRAY);

        assertSame(value, typeId.forValue);
        assertNull(typeId.forValueType);
        assertNull(typeId.id);
        assertNull(typeId.asProperty);
        assertNull(typeId.include);
        assertSame(JsonToken.START_ARRAY, typeId.valueShape);
        assertFalse(typeId.wrapperWritten);
        assertNull(typeId.extra);
    }

    @Test
    public void explicitIdConstructorStoresEverySuppliedArgumentIndependently() {
        Object value = new Object();
        Object id = new Object();

        WritableTypeId typeId = new WritableTypeId(value, JsonToken.VALUE_NUMBER_INT, id);

        assertSame(value, typeId.forValue);
        assertSame(JsonToken.VALUE_NUMBER_INT, typeId.valueShape);
        assertSame(id, typeId.id);
        assertNull(typeId.forValueType);
        assertNull(typeId.asProperty);
        assertNull(typeId.include);
        assertFalse(typeId.wrapperWritten);
        assertNull(typeId.extra);
    }

    @Test
    public void valueTypeConstructorAcceptsNullEffectiveTypeWithoutChangingOtherState() {
        Object value = new Object();

        WritableTypeId typeId = new WritableTypeId(
                value, (Class<?>) null, JsonToken.VALUE_FALSE);

        assertSame(value, typeId.forValue);
        assertNull(typeId.forValueType);
        assertSame(JsonToken.VALUE_FALSE, typeId.valueShape);
        assertNull(typeId.id);
        assertNull(typeId.asProperty);
        assertNull(typeId.include);
        assertFalse(typeId.wrapperWritten);
        assertNull(typeId.extra);
    }

    @Test
    public void eachWritableTypeIdHasIndependentMutableState() {
        Object firstValue = new Object();
        Object secondValue = new Object();

        WritableTypeId first = new WritableTypeId(firstValue, JsonToken.START_OBJECT);
        WritableTypeId second = new WritableTypeId(secondValue, JsonToken.START_ARRAY);

        first.id = "first";
        first.wrapperWritten = true;
        first.extra = "extra";

        assertSame(firstValue, first.forValue);
        assertSame(JsonToken.START_OBJECT, first.valueShape);
        assertSame("first", first.id);
        assertTrue(first.wrapperWritten);
        assertSame("extra", first.extra);

        assertSame(secondValue, second.forValue);
        assertSame(JsonToken.START_ARRAY, second.valueShape);
        assertNull(second.id);
        assertFalse(second.wrapperWritten);
        assertNull(second.extra);
    }

    @Test
    public void inclusionValuesAreDistinctAndOrderedForAllSupportedModes() {
        WritableTypeId.Inclusion[] values = WritableTypeId.Inclusion.values();

        assertTrue(values.length == 5);
        assertSame(WritableTypeId.Inclusion.WRAPPER_ARRAY, values[0]);
        assertSame(WritableTypeId.Inclusion.WRAPPER_OBJECT, values[1]);
        assertSame(WritableTypeId.Inclusion.METADATA_PROPERTY, values[2]);
        assertSame(WritableTypeId.Inclusion.PAYLOAD_PROPERTY, values[3]);
        assertSame(WritableTypeId.Inclusion.PARENT_PROPERTY, values[4]);
    }
}
