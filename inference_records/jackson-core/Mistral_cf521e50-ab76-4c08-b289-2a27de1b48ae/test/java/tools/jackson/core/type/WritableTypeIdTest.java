package tools.jackson.core.type;

import org.junit.Test;
import static org.junit.Assert.*;
import tools.jackson.core.JsonToken;

public class WritableTypeIdTest {

    @Test
    public void testDefaultConstructor() {
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
    public void testConstructorWithValueAndShape() {
        Object testValue = new Object();
        JsonToken testShape = JsonToken.VALUE_STRING;

        WritableTypeId typeId = new WritableTypeId(testValue, testShape);
        assertSame(testValue, typeId.forValue);
        assertNull(typeId.forValueType);
        assertNull(typeId.id);
        assertNull(typeId.asProperty);
        assertNull(typeId.include);
        assertSame(testShape, typeId.valueShape);
        assertFalse(typeId.wrapperWritten);
        assertNull(typeId.extra);
    }

    @Test
    public void testConstructorWithValueTypeAndShape() {
        Object testValue = new Object();
        Class<?> testType = String.class;
        JsonToken testShape = JsonToken.VALUE_STRING;

        WritableTypeId typeId = new WritableTypeId(testValue, testType, testShape);
        assertSame(testValue, typeId.forValue);
        assertSame(testType, typeId.forValueType);
        assertNull(typeId.id);
        assertNull(typeId.asProperty);
        assertNull(typeId.include);
        assertSame(testShape, typeId.valueShape);
        assertFalse(typeId.wrapperWritten);
        assertNull(typeId.extra);
    }

    @Test
    public void testConstructorWithValueShapeAndId() {
        Object testValue = new Object();
        JsonToken testShape = JsonToken.VALUE_STRING;
        Object testId = "testId";

        WritableTypeId typeId = new WritableTypeId(testValue, testShape, testId);
        assertSame(testValue, typeId.forValue);
        assertNull(typeId.forValueType);
        assertSame(testId, typeId.id);
        assertNull(typeId.asProperty);
        assertNull(typeId.include);
        assertSame(testShape, typeId.valueShape);
        assertFalse(typeId.wrapperWritten);
        assertNull(typeId.extra);
    }

    @Test
    public void testInclusionRequiresObjectContext() {
        assertTrue(WritableTypeId.Inclusion.METADATA_PROPERTY.requiresObjectContext());
        assertTrue(WritableTypeId.Inclusion.PAYLOAD_PROPERTY.requiresObjectContext());
        assertFalse(WritableTypeId.Inclusion.WRAPPER_ARRAY.requiresObjectContext());
        assertFalse(WritableTypeId.Inclusion.WRAPPER_OBJECT.requiresObjectContext());
        assertFalse(WritableTypeId.Inclusion.PARENT_PROPERTY.requiresObjectContext());
    }

    @Test
    public void testWrapperWrittenFlag() {
        WritableTypeId typeId = new WritableTypeId();
        assertFalse(typeId.wrapperWritten);

        typeId.wrapperWritten = true;
        assertTrue(typeId.wrapperWritten);
    }

    @Test
    public void testExtraField() {
        Object testExtra = new Object();
        WritableTypeId typeId = new WritableTypeId();
        assertNull(typeId.extra);

        typeId.extra = testExtra;
        assertSame(testExtra, typeId.extra);
    }

    @Test
    public void testConstructorWithAllFields() {
        Object testValue = new Object();
        Class<?> testType = String.class;
        JsonToken testShape = JsonToken.VALUE_STRING;
        Object testId = "testId";
        String testProperty = "testProperty";
        WritableTypeId.Inclusion testInclusion = WritableTypeId.Inclusion.WRAPPER_OBJECT;
        Object testExtra = new Object();

        WritableTypeId typeId = new WritableTypeId(testValue, testType, testShape);
        typeId.id = testId;
        typeId.asProperty = testProperty;
        typeId.include = testInclusion;
        typeId.wrapperWritten = true;
        typeId.extra = testExtra;

        assertSame(testValue, typeId.forValue);
        assertSame(testType, typeId.forValueType);
        assertSame(testShape, typeId.valueShape);
        assertSame(testId, typeId.id);
        assertSame(testProperty, typeId.asProperty);
        assertSame(testInclusion, typeId.include);
        assertTrue(typeId.wrapperWritten);
        assertSame(testExtra, typeId.extra);
    }
}
