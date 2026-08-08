package tools.jackson.core.type;

import org.junit.Test;
import tools.jackson.core.JsonToken;

import static org.junit.Assert.*;

public class WritableTypeIdTest {

    @Test
    public void testDefaultConstructor() {
        WritableTypeId writableTypeId = new WritableTypeId();

        assertNull(writableTypeId.forValue);
        assertNull(writableTypeId.forValueType);
        assertNull(writableTypeId.id);
        assertNull(writableTypeId.asProperty);
        assertNull(writableTypeId.include);
        assertNull(writableTypeId.valueShape);
        assertFalse(writableTypeId.wrapperWritten);
        assertNull(writableTypeId.extra);
    }

    @Test
    public void testConstructorWithValueAndValueShape() {
        Object testValue = "test";
        JsonToken testShape = JsonToken.VALUE_STRING;

        WritableTypeId writableTypeId = new WritableTypeId(testValue, testShape);

        assertSame(testValue, writableTypeId.forValue);
        assertSame(testShape, writableTypeId.valueShape);
        assertNull(writableTypeId.id);
        assertNull(writableTypeId.forValueType);
        assertNull(writableTypeId.asProperty);
        assertNull(writableTypeId.include);
        assertFalse(writableTypeId.wrapperWritten);
        assertNull(writableTypeId.extra);
    }

    @Test
    public void testConstructorWithValueValueTypeAndValueShape() {
        Object testValue = "test";
        Class<?> testType = String.class;
        JsonToken testShape = JsonToken.VALUE_STRING;

        WritableTypeId writableTypeId = new WritableTypeId(testValue, testType, testShape);

        assertSame(testValue, writableTypeId.forValue);
        assertSame(testType, writableTypeId.forValueType);
        assertSame(testShape, writableTypeId.valueShape);
        assertNull(writableTypeId.id);
        assertNull(writableTypeId.asProperty);
        assertNull(writableTypeId.include);
        assertFalse(writableTypeId.wrapperWritten);
        assertNull(writableTypeId.extra);
    }

    @Test
    public void testConstructorWithValueValueShapeAndId() {
        Object testValue = "test";
        JsonToken testShape = JsonToken.VALUE_STRING;
        Object testId = "type-id-123";

        WritableTypeId writableTypeId = new WritableTypeId(testValue, testShape, testId);

        assertSame(testValue, writableTypeId.forValue);
        assertSame(testShape, writableTypeId.valueShape);
        assertSame(testId, writableTypeId.id);
        assertNull(writableTypeId.forValueType);
        assertNull(writableTypeId.asProperty);
        assertNull(writableTypeId.include);
        assertFalse(writableTypeId.wrapperWritten);
        assertNull(writableTypeId.extra);
    }

    @Test
    public void testConstructorWithNullValue() {
        JsonToken testShape = JsonToken.VALUE_STRING;

        WritableTypeId writableTypeId = new WritableTypeId(null, testShape);

        assertNull(writableTypeId.forValue);
        assertSame(testShape, writableTypeId.valueShape);
    }

    @Test
    public void testConstructorWithNullValueShape() {
        Object testValue = "test";

        WritableTypeId writableTypeId = new WritableTypeId(testValue, null);

        assertSame(testValue, writableTypeId.forValue);
        assertNull(writableTypeId.valueShape);
    }

    @Test
    public void testConstructorWithNullId() {
        Object testValue = "test";
        JsonToken testShape = JsonToken.VALUE_STRING;

        WritableTypeId writableTypeId = new WritableTypeId(testValue, testShape, null);

        assertSame(testValue, writableTypeId.forValue);
        assertSame(testShape, writableTypeId.valueShape);
        assertNull(writableTypeId.id);
    }

    @Test
    public void testAllFieldsMutable() {
        WritableTypeId writableTypeId = new WritableTypeId();

        Object newValue = new Object();
        Class<?> newType = Integer.class;
        Object newId = 42;
        String newProperty = "typeProperty";
        WritableTypeId.Inclusion newInclude = WritableTypeId.Inclusion.WRAPPER_ARRAY;
        JsonToken newShape = JsonToken.START_OBJECT;
        boolean newWrapperWritten = true;
        Object newExtra = "extraInfo";

        writableTypeId.forValue = newValue;
        writableTypeId.forValueType = newType;
        writableTypeId.id = newId;
        writableTypeId.asProperty = newProperty;
        writableTypeId.include = newInclude;
        writableTypeId.valueShape = newShape;
        writableTypeId.wrapperWritten = newWrapperWritten;
        writableTypeId.extra = newExtra;

        assertSame(newValue, writableTypeId.forValue);
        assertSame(newType, writableTypeId.forValueType);
        assertSame(newId, writableTypeId.id);
        assertSame(newProperty, writableTypeId.asProperty);
        assertSame(newInclude, writableTypeId.include);
        assertSame(newShape, writableTypeId.valueShape);
        assertTrue(writableTypeId.wrapperWritten);
        assertSame(newExtra, writableTypeId.extra);
    }

    @Test
    public void testInclusionEnumValues() {
        WritableTypeId.Inclusion[] values = WritableTypeId.Inclusion.values();

        assertEquals(5, values.length);
        assertSame(WritableTypeId.Inclusion.WRAPPER_ARRAY, values[0]);
        assertSame(WritableTypeId.Inclusion.WRAPPER_OBJECT, values[1]);
        assertSame(WritableTypeId.Inclusion.METADATA_PROPERTY, values[2]);
        assertSame(WritableTypeId.Inclusion.PAYLOAD_PROPERTY, values[3]);
        assertSame(WritableTypeId.Inclusion.PARENT_PROPERTY, values[4]);
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
    public void testInclusionValueOf() {
        assertSame(WritableTypeId.Inclusion.WRAPPER_ARRAY,
                WritableTypeId.Inclusion.valueOf("WRAPPER_ARRAY"));
        assertSame(WritableTypeId.Inclusion.WRAPPER_OBJECT,
                WritableTypeId.Inclusion.valueOf("WRAPPER_OBJECT"));
        assertSame(WritableTypeId.Inclusion.METADATA_PROPERTY,
                WritableTypeId.Inclusion.valueOf("METADATA_PROPERTY"));
        assertSame(WritableTypeId.Inclusion.PAYLOAD_PROPERTY,
                WritableTypeId.Inclusion.valueOf("PAYLOAD_PROPERTY"));
        assertSame(WritableTypeId.Inclusion.PARENT_PROPERTY,
                WritableTypeId.Inclusion.valueOf("PARENT_PROPERTY"));
    }

    @Test
    public void testWithDifferentValueShapes() {
        Object testValue = new Object();

        WritableTypeId arrayTypeId = new WritableTypeId(testValue, JsonToken.START_ARRAY);
        assertSame(JsonToken.START_ARRAY, arrayTypeId.valueShape);
        assertTrue(arrayTypeId.valueShape.isStructStart());

        WritableTypeId objectTypeId = new WritableTypeId(testValue, JsonToken.START_OBJECT);
        assertSame(JsonToken.START_OBJECT, objectTypeId.valueShape);
        assertTrue(objectTypeId.valueShape.isStructStart());

        WritableTypeId stringTypeId = new WritableTypeId(testValue, JsonToken.VALUE_STRING);
        assertSame(JsonToken.VALUE_STRING, stringTypeId.valueShape);
        assertTrue(stringTypeId.valueShape.isScalarValue());

        WritableTypeId numberIntTypeId = new WritableTypeId(testValue, JsonToken.VALUE_NUMBER_INT);
        assertSame(JsonToken.VALUE_NUMBER_INT, numberIntTypeId.valueShape);
        assertTrue(numberIntTypeId.valueShape.isNumeric());

        WritableTypeId numberFloatTypeId = new WritableTypeId(testValue, JsonToken.VALUE_NUMBER_FLOAT);
        assertSame(JsonToken.VALUE_NUMBER_FLOAT, numberFloatTypeId.valueShape);
        assertTrue(numberFloatTypeId.valueShape.isNumeric());

        WritableTypeId booleanTypeId = new WritableTypeId(testValue, JsonToken.VALUE_TRUE);
        assertSame(JsonToken.VALUE_TRUE, booleanTypeId.valueShape);
        assertTrue(booleanTypeId.valueShape.isBoolean());

        WritableTypeId nullTypeId = new WritableTypeId(testValue, JsonToken.VALUE_NULL);
        assertSame(JsonToken.VALUE_NULL, nullTypeId.valueShape);
        assertTrue(nullTypeId.valueShape.isScalarValue());
    }

    @Test
    public void testWithComplexIdTypes() {
        String stringId = "string-id";
        Integer integerId = 123;
        Object objectId = new Object();

        WritableTypeId stringIdType = new WritableTypeId("value", JsonToken.VALUE_STRING, stringId);
        assertSame(stringId, stringIdType.id);

        WritableTypeId integerIdType = new WritableTypeId("value", JsonToken.VALUE_STRING, integerId);
        assertSame(integerId, integerIdType.id);

        WritableTypeId objectIdType = new WritableTypeId("value", JsonToken.VALUE_STRING, objectId);
        assertSame(objectId, objectIdType.id);
    }

    @Test
    public void testWrapperWrittenFlagDefaultsToFalse() {
        WritableTypeId writableTypeId = new WritableTypeId("value", JsonToken.VALUE_STRING);
        assertFalse(writableTypeId.wrapperWritten);

        WritableTypeId writableTypeId2 = new WritableTypeId();
        assertFalse(writableTypeId2.wrapperWritten);
    }

    @Test
    public void testExtraFieldDefaultsToNull() {
        WritableTypeId writableTypeId = new WritableTypeId("value", JsonToken.VALUE_STRING);
        assertNull(writableTypeId.extra);

        WritableTypeId writableTypeId2 = new WritableTypeId();
        assertNull(writableTypeId2.extra);
    }

    @Test
    public void testAsPropertyDefaultsToNull() {
        WritableTypeId writableTypeId = new WritableTypeId("value", JsonToken.VALUE_STRING);
        assertNull(writableTypeId.asProperty);

        WritableTypeId writableTypeId2 = new WritableTypeId();
        assertNull(writableTypeId2.asProperty);
    }

    @Test
    public void testIncludeDefaultsToNull() {
        WritableTypeId writableTypeId = new WritableTypeId("value", JsonToken.VALUE_STRING);
        assertNull(writableTypeId.include);

        WritableTypeId writableTypeId2 = new WritableTypeId();
        assertNull(writableTypeId2.include);
    }

    @Test
    public void testForValueTypeDefaultsToNull() {
        WritableTypeId writableTypeId = new WritableTypeId("value", JsonToken.VALUE_STRING);
        assertNull(writableTypeId.forValueType);

        WritableTypeId writableTypeId2 = new WritableTypeId("value", JsonToken.VALUE_STRING, "id");
        assertNull(writableTypeId2.forValueType);

        WritableTypeId writableTypeId3 = new WritableTypeId();
        assertNull(writableTypeId3.forValueType);
    }

    @Test
    public void testForValueTypeSetByThirdConstructor() {
        Object testValue = "test";
        Class<?> testType = Number.class;
        JsonToken testShape = JsonToken.VALUE_NUMBER_INT;

        WritableTypeId writableTypeId = new WritableTypeId(testValue, testType, testShape);

        assertSame(testValue, writableTypeId.forValue);
        assertSame(testType, writableTypeId.forValueType);
        assertSame(testShape, writableTypeId.valueShape);
        assertNull(writableTypeId.id);
    }

    @Test
    public void testIdFieldCanBeSetAfterConstruction() {
        WritableTypeId writableTypeId = new WritableTypeId("value", JsonToken.VALUE_STRING);
        assertNull(writableTypeId.id);

        writableTypeId.id = "assigned-later";
        assertEquals("assigned-later", writableTypeId.id);
    }

    @Test
    public void testAllInclusionEnumsHaveRequiresObjectContextMethod() {
        for (WritableTypeId.Inclusion inclusion : WritableTypeId.Inclusion.values()) {
            boolean result = inclusion.requiresObjectContext();
            assertNotNull("requiresObjectContext should return a boolean for " + inclusion, result);
        }
    }

    // --- New tests for branch coverage of Inclusion.requiresObjectContext() ---

    @Test
    public void testRequiresObjectContext_MetadataProperty_ReturnsTrue_FirstBranch() {
        boolean result = WritableTypeId.Inclusion.METADATA_PROPERTY.requiresObjectContext();
        assertTrue("METADATA_PROPERTY should require object context", result);
    }

    @Test
    public void testRequiresObjectContext_PayloadProperty_ReturnsTrue_SecondBranch() {
        boolean result = WritableTypeId.Inclusion.PAYLOAD_PROPERTY.requiresObjectContext();
        assertTrue("PAYLOAD_PROPERTY should require object context", result);
    }

    @Test
    public void testRequiresObjectContext_WrapperArray_ReturnsFalse_BothFalse() {
        boolean result = WritableTypeId.Inclusion.WRAPPER_ARRAY.requiresObjectContext();
        assertFalse("WRAPPER_ARRAY should not require object context", result);
    }

    @Test
    public void testRequiresObjectContext_WrapperObject_ReturnsFalse_BothFalse() {
        boolean result = WritableTypeId.Inclusion.WRAPPER_OBJECT.requiresObjectContext();
        assertFalse("WRAPPER_OBJECT should not require object context", result);
    }

    @Test
    public void testRequiresObjectContext_ParentProperty_ReturnsFalse_BothFalse() {
        boolean result = WritableTypeId.Inclusion.PARENT_PROPERTY.requiresObjectContext();
        assertFalse("PARENT_PROPERTY should not require object context", result);
    }

    // --- New tests for constructor delegation paths ---

    @Test
    public void testTwoArgConstructorDelegatesToThreeArgWithNullId() {
        Object testValue = "testValue";
        JsonToken testShape = JsonToken.START_ARRAY;

        WritableTypeId writableTypeId = new WritableTypeId(testValue, testShape);

        assertSame(testValue, writableTypeId.forValue);
        assertSame(testShape, writableTypeId.valueShape);
        assertNull("id should be null when delegated from two-arg constructor", writableTypeId.id);
        assertNull(writableTypeId.forValueType);
    }

    @Test
    public void testThreeArgConstructorWithValueTypeDelegatesToThreeArgWithNullId() {
        Object testValue = "testValue";
        Class<?> testType = Number.class;
        JsonToken testShape = JsonToken.VALUE_NUMBER_INT;

        WritableTypeId writableTypeId = new WritableTypeId(testValue, testType, testShape);

        assertSame(testValue, writableTypeId.forValue);
        assertSame(testType, writableTypeId.forValueType);
        assertSame(testShape, writableTypeId.valueShape);
        assertNull("id should be null when delegated from three-arg valueType constructor", writableTypeId.id);
    }

    @Test
    public void testThreeArgConstructorWithId_SetsAllFieldsDirectly() {
        Object testValue = "testValue";
        JsonToken testShape = JsonToken.VALUE_STRING;
        Object testId = "custom-id-456";

        WritableTypeId writableTypeId = new WritableTypeId(testValue, testShape, testId);

        assertSame(testValue, writableTypeId.forValue);
        assertSame(testShape, writableTypeId.valueShape);
        assertSame(testId, writableTypeId.id);
        assertNull(writableTypeId.forValueType);
    }

    @Test
    public void testConstructorWithNullValueAndNullShape() {
        WritableTypeId writableTypeId = new WritableTypeId(null, null);

        assertNull(writableTypeId.forValue);
        assertNull(writableTypeId.valueShape);
        assertNull(writableTypeId.id);
    }

    @Test
    public void testConstructorWithNullValueType() {
        Object testValue = "test";
        JsonToken testShape = JsonToken.VALUE_STRING;

        WritableTypeId writableTypeId = new WritableTypeId(testValue, (Class<?>) null, testShape);

        assertSame(testValue, writableTypeId.forValue);
        assertNull(writableTypeId.forValueType);
        assertSame(testShape, writableTypeId.valueShape);
    }

    // --- New tests for enum edge cases ---

    @Test
    public void testInclusionValueOf_CaseSensitive() {
        assertSame(WritableTypeId.Inclusion.WRAPPER_ARRAY,
                WritableTypeId.Inclusion.valueOf("WRAPPER_ARRAY"));
        
        try {
            WritableTypeId.Inclusion.valueOf("wrapper_array");
            fail("valueOf should throw IllegalArgumentException for wrong case");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    @Test
    public void testInclusionValuesOrderIsStable() {
        WritableTypeId.Inclusion[] values = WritableTypeId.Inclusion.values();
        assertEquals(5, values.length);
        assertSame(WritableTypeId.Inclusion.WRAPPER_ARRAY, values[0]);
        assertSame(WritableTypeId.Inclusion.WRAPPER_OBJECT, values[1]);
        assertSame(WritableTypeId.Inclusion.METADATA_PROPERTY, values[2]);
        assertSame(WritableTypeId.Inclusion.PAYLOAD_PROPERTY, values[3]);
        assertSame(WritableTypeId.Inclusion.PARENT_PROPERTY, values[4]);
    }

    @Test
    public void testInclusionEnumOrdinalValues() {
        assertEquals(0, WritableTypeId.Inclusion.WRAPPER_ARRAY.ordinal());
        assertEquals(1, WritableTypeId.Inclusion.WRAPPER_OBJECT.ordinal());
        assertEquals(2, WritableTypeId.Inclusion.METADATA_PROPERTY.ordinal());
        assertEquals(3, WritableTypeId.Inclusion.PAYLOAD_PROPERTY.ordinal());
        assertEquals(4, WritableTypeId.Inclusion.PARENT_PROPERTY.ordinal());
    }

    // --- New tests for JsonToken valueShape interactions ---

    @Test
    public void testValueShapeWithAllStructStartTokens() {
        WritableTypeId arrayStart = new WritableTypeId("v", JsonToken.START_ARRAY);
        assertTrue(arrayStart.valueShape.isStructStart());
        assertFalse(arrayStart.valueShape.isStructEnd());
        assertFalse(arrayStart.valueShape.isScalarValue());

        WritableTypeId objectStart = new WritableTypeId("v", JsonToken.START_OBJECT);
        assertTrue(objectStart.valueShape.isStructStart());
        assertFalse(objectStart.valueShape.isStructEnd());
        assertFalse(objectStart.valueShape.isScalarValue());
    }

    @Test
    public void testValueShapeWithStructEndTokens() {
        WritableTypeId arrayEnd = new WritableTypeId("v", JsonToken.END_ARRAY);
        assertFalse(arrayEnd.valueShape.isStructStart());
        assertTrue(arrayEnd.valueShape.isStructEnd());
        assertFalse(arrayEnd.valueShape.isScalarValue());

        WritableTypeId objectEnd = new WritableTypeId("v", JsonToken.END_OBJECT);
        assertFalse(objectEnd.valueShape.isStructStart());
        assertTrue(objectEnd.valueShape.isStructEnd());
        assertFalse(objectEnd.valueShape.isScalarValue());
    }

    @Test
    public void testValueShapeWithPropertyNameToken() {
        WritableTypeId propName = new WritableTypeId("v", JsonToken.PROPERTY_NAME);
        assertFalse(propName.valueShape.isStructStart());
        assertFalse(propName.valueShape.isStructEnd());
        assertFalse(propName.valueShape.isScalarValue());
        assertFalse(propName.valueShape.isNumeric());
        assertFalse(propName.valueShape.isBoolean());
    }

    @Test
    public void testValueShapeWithNotAvailableToken() {
        WritableTypeId notAvail = new WritableTypeId("v", JsonToken.NOT_AVAILABLE);
        assertFalse(notAvail.valueShape.isStructStart());
        assertFalse(notAvail.valueShape.isStructEnd());
        assertFalse(notAvail.valueShape.isScalarValue());
    }

    @Test
    public void testValueShapeWithEmbeddedObjectToken() {
        WritableTypeId embedded = new WritableTypeId("v", JsonToken.VALUE_EMBEDDED_OBJECT);
        assertFalse(embedded.valueShape.isStructStart());
        assertFalse(embedded.valueShape.isStructEnd());
        assertTrue(embedded.valueShape.isScalarValue());
        assertFalse(embedded.valueShape.isNumeric());
        assertFalse(embedded.valueShape.isBoolean());
    }

    // --- New tests for boolean field edge cases ---

    @Test
    public void testWrapperWrittenCanBeSetToTrue() {
        WritableTypeId writableTypeId = new WritableTypeId("v", JsonToken.START_ARRAY);
        assertFalse(writableTypeId.wrapperWritten);

        writableTypeId.wrapperWritten = true;
        assertTrue(writableTypeId.wrapperWritten);
    }

    @Test
    public void testWrapperWrittenCanBeToggled() {
        WritableTypeId writableTypeId = new WritableTypeId();
        assertFalse(writableTypeId.wrapperWritten);

        writableTypeId.wrapperWritten = true;
        assertTrue(writableTypeId.wrapperWritten);

        writableTypeId.wrapperWritten = false;
        assertFalse(writableTypeId.wrapperWritten);
    }

    // --- New tests for extra field with various types ---

    @Test
    public void testExtraFieldAcceptsVariousTypes() {
        WritableTypeId writableTypeId = new WritableTypeId();

        writableTypeId.extra = "string";
        assertEquals("string", writableTypeId.extra);

        writableTypeId.extra = 123;
        assertEquals(123, writableTypeId.extra);

        writableTypeId.extra = new Object();
        assertNotNull(writableTypeId.extra);

        writableTypeId.extra = null;
        assertNull(writableTypeId.extra);
    }

    // --- New tests for asProperty with various string values ---

    @Test
    public void testAsPropertyCanBeSetToVariousStrings() {
        WritableTypeId writableTypeId = new WritableTypeId();

        writableTypeId.asProperty = "@type";
        assertEquals("@type", writableTypeId.asProperty);

        writableTypeId.asProperty = "";
        assertEquals("", writableTypeId.asProperty);

        writableTypeId.asProperty = "custom.type.property";
        assertEquals("custom.type.property", writableTypeId.asProperty);

        writableTypeId.asProperty = null;
        assertNull(writableTypeId.asProperty);
    }

    // --- New tests for include field with all enum values ---

    @Test
    public void testIncludeFieldCanBeSetToAllEnumValues() {
        WritableTypeId writableTypeId = new WritableTypeId();

        writableTypeId.include = WritableTypeId.Inclusion.WRAPPER_ARRAY;
        assertSame(WritableTypeId.Inclusion.WRAPPER_ARRAY, writableTypeId.include);

        writableTypeId.include = WritableTypeId.Inclusion.WRAPPER_OBJECT;
        assertSame(WritableTypeId.Inclusion.WRAPPER_OBJECT, writableTypeId.include);

        writableTypeId.include = WritableTypeId.Inclusion.METADATA_PROPERTY;
        assertSame(WritableTypeId.Inclusion.METADATA_PROPERTY, writableTypeId.include);

        writableTypeId.include = WritableTypeId.Inclusion.PAYLOAD_PROPERTY;
        assertSame(WritableTypeId.Inclusion.PAYLOAD_PROPERTY, writableTypeId.include);

        writableTypeId.include = WritableTypeId.Inclusion.PARENT_PROPERTY;
        assertSame(WritableTypeId.Inclusion.PARENT_PROPERTY, writableTypeId.include);

        writableTypeId.include = null;
        assertNull(writableTypeId.include);
    }

    // --- New tests for forValueType with various class types ---

    @Test
    public void testForValueTypeAcceptsVariousClassTypes() {
        WritableTypeId writableTypeId = new WritableTypeId();

        writableTypeId.forValueType = String.class;
        assertSame(String.class, writableTypeId.forValueType);

        writableTypeId.forValueType = Integer.class;
        assertSame(Integer.class, writableTypeId.forValueType);

        writableTypeId.forValueType = Object.class;
        assertSame(Object.class, writableTypeId.forValueType);

        writableTypeId.forValueType = null;
        assertNull(writableTypeId.forValueType);
    }

    // --- New test for id field with various object types ---

    @Test
    public void testIdFieldAcceptsVariousObjectTypes() {
        WritableTypeId writableTypeId = new WritableTypeId("v", JsonToken.VALUE_STRING);

        writableTypeId.id = "string-id";
        assertEquals("string-id", writableTypeId.id);

        writableTypeId.id = 42;
        assertEquals(42, writableTypeId.id);

        writableTypeId.id = 3.14;
        assertEquals(3.14, writableTypeId.id);

        writableTypeId.id = true;
        assertEquals(true, writableTypeId.id);

        writableTypeId.id = new int[]{1, 2, 3};
        assertNotNull(writableTypeId.id);

        writableTypeId.id = null;
        assertNull(writableTypeId.id);
    }

    // --- New tests for mutation testing: boolean logic boundary conditions ---

    @Test
    public void testRequiresObjectContext_ExplicitBooleanLogic_FirstConditionTrue() {
        // Tests the first part of: (this == METADATA_PROPERTY) || (this == PAYLOAD_PROPERTY)
        WritableTypeId.Inclusion inclusion = WritableTypeId.Inclusion.METADATA_PROPERTY;
        boolean result = inclusion.requiresObjectContext();
        assertTrue("First condition (METADATA_PROPERTY) should return true", result);
    }

    @Test
    public void testRequiresObjectContext_ExplicitBooleanLogic_SecondConditionTrue() {
        // Tests the second part of: (this == METADATA_PROPERTY) || (this == PAYLOAD_PROPERTY)
        WritableTypeId.Inclusion inclusion = WritableTypeId.Inclusion.PAYLOAD_PROPERTY;
        boolean result = inclusion.requiresObjectContext();
        assertTrue("Second condition (PAYLOAD_PROPERTY) should return true", result);
    }

    @Test
    public void testRequiresObjectContext_ExplicitBooleanLogic_BothConditionsFalse() {
        // Tests both conditions false for WRAPPER_ARRAY, WRAPPER_OBJECT, PARENT_PROPERTY
        WritableTypeId.Inclusion[] falseCases = {
            WritableTypeId.Inclusion.WRAPPER_ARRAY,
            WritableTypeId.Inclusion.WRAPPER_OBJECT,
            WritableTypeId.Inclusion.PARENT_PROPERTY
        };
        for (WritableTypeId.Inclusion inclusion : falseCases) {
            boolean result = inclusion.requiresObjectContext();
            assertFalse("Inclusion " + inclusion + " should return false (both conditions false)", result);
        }
    }

    // --- New tests for mutation testing: constructor field initialization verification ---

    @Test
    public void testTwoArgConstructor_SetsForValueAndValueShape_LeavesOthersNull() {
        Object value = "test";
        JsonToken shape = JsonToken.VALUE_NUMBER_FLOAT;
        
        WritableTypeId id = new WritableTypeId(value, shape);
        
        assertSame(value, id.forValue);
        assertSame(shape, id.valueShape);
        assertNull(id.id);
        assertNull(id.forValueType);
        assertNull(id.asProperty);
        assertNull(id.include);
        assertFalse(id.wrapperWritten);
        assertNull(id.extra);
    }

    @Test
    public void testThreeArgValueTypeConstructor_SetsForValueType_LeavesIdNull() {
        Object value = "test";
        Class<?> type = Long.class;
        JsonToken shape = JsonToken.VALUE_NUMBER_INT;
        
        WritableTypeId id = new WritableTypeId(value, type, shape);
        
        assertSame(value, id.forValue);
        assertSame(type, id.forValueType);
        assertSame(shape, id.valueShape);
        assertNull(id.id);
        assertNull(id.asProperty);
        assertNull(id.include);
        assertFalse(id.wrapperWritten);
        assertNull(id.extra);
    }

    @Test
    public void testThreeArgIdConstructor_SetsId_DoesNotSetForValueType() {
        Object value = "test";
        JsonToken shape = JsonToken.VALUE_TRUE;
        Object idValue = "type-id";
        
        WritableTypeId id = new WritableTypeId(value, shape, idValue);
        
        assertSame(value, id.forValue);
        assertSame(shape, id.valueShape);
        assertSame(idValue, id.id);
        assertNull(id.forValueType);
        assertNull(id.asProperty);
        assertNull(id.include);
        assertFalse(id.wrapperWritten);
        assertNull(id.extra);
    }

    // --- New tests for mutation testing: JsonToken boolean method boundaries ---

    @Test
    public void testValueShapeIsStructStart_OnlyForStartTokens() {
        assertTrue(new WritableTypeId("v", JsonToken.START_ARRAY).valueShape.isStructStart());
        assertTrue(new WritableTypeId("v", JsonToken.START_OBJECT).valueShape.isStructStart());
        
        assertFalse(new WritableTypeId("v", JsonToken.END_ARRAY).valueShape.isStructStart());
        assertFalse(new WritableTypeId("v", JsonToken.END_OBJECT).valueShape.isStructStart());
        assertFalse(new WritableTypeId("v", JsonToken.VALUE_STRING).valueShape.isStructStart());
        assertFalse(new WritableTypeId("v", JsonToken.VALUE_NUMBER_INT).valueShape.isStructStart());
        assertFalse(new WritableTypeId("v", JsonToken.VALUE_NUMBER_FLOAT).valueShape.isStructStart());
        assertFalse(new WritableTypeId("v", JsonToken.VALUE_TRUE).valueShape.isStructStart());
        assertFalse(new WritableTypeId("v", JsonToken.VALUE_FALSE).valueShape.isStructStart());
        assertFalse(new WritableTypeId("v", JsonToken.VALUE_NULL).valueShape.isStructStart());
        assertFalse(new WritableTypeId("v", JsonToken.PROPERTY_NAME).valueShape.isStructStart());
        assertFalse(new WritableTypeId("v", JsonToken.VALUE_EMBEDDED_OBJECT).valueShape.isStructStart());
        assertFalse(new WritableTypeId("v", JsonToken.NOT_AVAILABLE).valueShape.isStructStart());
    }

    @Test
    public void testValueShapeIsStructEnd_OnlyForEndTokens() {
        assertTrue(new WritableTypeId("v", JsonToken.END_ARRAY).valueShape.isStructEnd());
        assertTrue(new WritableTypeId("v", JsonToken.END_OBJECT).valueShape.isStructEnd());
        
        assertFalse(new WritableTypeId("v", JsonToken.START_ARRAY).valueShape.isStructEnd());
        assertFalse(new WritableTypeId("v", JsonToken.START_OBJECT).valueShape.isStructEnd());
        assertFalse(new WritableTypeId("v", JsonToken.VALUE_STRING).valueShape.isStructEnd());
        assertFalse(new WritableTypeId("v", JsonToken.VALUE_NUMBER_INT).valueShape.isStructEnd());
        assertFalse(new WritableTypeId("v", JsonToken.VALUE_NUMBER_FLOAT).valueShape.isStructEnd());
        assertFalse(new WritableTypeId("v", JsonToken.VALUE_TRUE).valueShape.isStructEnd());
        assertFalse(new WritableTypeId("v", JsonToken.VALUE_FALSE).valueShape.isStructEnd());
        assertFalse(new WritableTypeId("v", JsonToken.VALUE_NULL).valueShape.isStructEnd());
        assertFalse(new WritableTypeId("v", JsonToken.PROPERTY_NAME).valueShape.isStructEnd());
        assertFalse(new WritableTypeId("v", JsonToken.VALUE_EMBEDDED_OBJECT).valueShape.isStructEnd());
        assertFalse(new WritableTypeId("v", JsonToken.NOT_AVAILABLE).valueShape.isStructEnd());
    }

    @Test
    public void testValueShapeIsScalarValue_ForAllValueTokens() {
        assertTrue(new WritableTypeId("v", JsonToken.VALUE_STRING).valueShape.isScalarValue());
        assertTrue(new WritableTypeId("v", JsonToken.VALUE_NUMBER_INT).valueShape.isScalarValue());
        assertTrue(new WritableTypeId("v", JsonToken.VALUE_NUMBER_FLOAT).valueShape.isScalarValue());
        assertTrue(new WritableTypeId("v", JsonToken.VALUE_TRUE).valueShape.isScalarValue());
        assertTrue(new WritableTypeId("v", JsonToken.VALUE_FALSE).valueShape.isScalarValue());
        assertTrue(new WritableTypeId("v", JsonToken.VALUE_NULL).valueShape.isScalarValue());
        assertTrue(new WritableTypeId("v", JsonToken.VALUE_EMBEDDED_OBJECT).valueShape.isScalarValue());
        
        assertFalse(new WritableTypeId("v", JsonToken.START_ARRAY).valueShape.isScalarValue());
        assertFalse(new WritableTypeId("v", JsonToken.START_OBJECT).valueShape.isScalarValue());
        assertFalse(new WritableTypeId("v", JsonToken.END_ARRAY).valueShape.isScalarValue());
        assertFalse(new WritableTypeId("v", JsonToken.END_OBJECT).valueShape.isScalarValue());
        assertFalse(new WritableTypeId("v", JsonToken.PROPERTY_NAME).valueShape.isScalarValue());
        assertFalse(new WritableTypeId("v", JsonToken.NOT_AVAILABLE).valueShape.isScalarValue());
    }

    @Test
    public void testValueShapeIsNumeric_OnlyForNumberTokens() {
        assertTrue(new WritableTypeId("v", JsonToken.VALUE_NUMBER_INT).valueShape.isNumeric());
        assertTrue(new WritableTypeId("v", JsonToken.VALUE_NUMBER_FLOAT).valueShape.isNumeric());
        
        assertFalse(new WritableTypeId("v", JsonToken.VALUE_STRING).valueShape.isNumeric());
        assertFalse(new WritableTypeId("v", JsonToken.VALUE_TRUE).valueShape.isNumeric());
        assertFalse(new WritableTypeId("v", JsonToken.VALUE_FALSE).valueShape.isNumeric());
        assertFalse(new WritableTypeId("v", JsonToken.VALUE_NULL).valueShape.isNumeric());
        assertFalse(new WritableTypeId("v", JsonToken.START_ARRAY).valueShape.isNumeric());
        assertFalse(new WritableTypeId("v", JsonToken.START_OBJECT).valueShape.isNumeric());
        assertFalse(new WritableTypeId("v", JsonToken.END_ARRAY).valueShape.isNumeric());
        assertFalse(new WritableTypeId("v", JsonToken.END_OBJECT).valueShape.isNumeric());
        assertFalse(new WritableTypeId("v", JsonToken.PROPERTY_NAME).valueShape.isNumeric());
        assertFalse(new WritableTypeId("v", JsonToken.VALUE_EMBEDDED_OBJECT).valueShape.isNumeric());
        assertFalse(new WritableTypeId("v", JsonToken.NOT_AVAILABLE).valueShape.isNumeric());
    }

    @Test
    public void testValueShapeIsBoolean_OnlyForBooleanTokens() {
        assertTrue(new WritableTypeId("v", JsonToken.VALUE_TRUE).valueShape.isBoolean());
        assertTrue(new WritableTypeId("v", JsonToken.VALUE_FALSE).valueShape.isBoolean());
        
        assertFalse(new WritableTypeId("v", JsonToken.VALUE_STRING).valueShape.isBoolean());
        assertFalse(new WritableTypeId("v", JsonToken.VALUE_NUMBER_INT).valueShape.isBoolean());
        assertFalse(new WritableTypeId("v", JsonToken.VALUE_NUMBER_FLOAT).valueShape.isBoolean());
        assertFalse(new WritableTypeId("v", JsonToken.VALUE_NULL).valueShape.isBoolean());
        assertFalse(new WritableTypeId("v", JsonToken.START_ARRAY).valueShape.isBoolean());
        assertFalse(new WritableTypeId("v", JsonToken.START_OBJECT).valueShape.isBoolean());
        assertFalse(new WritableTypeId("v", JsonToken.END_ARRAY).valueShape.isBoolean());
        assertFalse(new WritableTypeId("v", JsonToken.END_OBJECT).valueShape.isBoolean());
        assertFalse(new WritableTypeId("v", JsonToken.PROPERTY_NAME).valueShape.isBoolean());
        assertFalse(new WritableTypeId("v", JsonToken.VALUE_EMBEDDED_OBJECT).valueShape.isBoolean());
        assertFalse(new WritableTypeId("v", JsonToken.NOT_AVAILABLE).valueShape.isBoolean());
    }

    // --- New tests for mutation testing: enum valueOf with invalid input ---

    @Test(expected = IllegalArgumentException.class)
    public void testInclusionValueOf_InvalidName_ThrowsException() {
        WritableTypeId.Inclusion.valueOf("INVALID_NAME");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInclusionValueOf_EmptyString_ThrowsException() {
        WritableTypeId.Inclusion.valueOf("");
    }

    @Test(expected = NullPointerException.class)
    public void testInclusionValueOf_Null_ThrowsException() {
        WritableTypeId.Inclusion.valueOf(null);
    }

    // --- New tests for mutation testing: field mutation verification ---

    @Test
    public void testAllFields_CanBeSetToNull_AfterInitialization() {
        WritableTypeId id = new WritableTypeId("value", JsonToken.START_OBJECT, "id");
        id.forValueType = String.class;
        id.asProperty = "@type";
        id.include = WritableTypeId.Inclusion.WRAPPER_ARRAY;
        id.wrapperWritten = true;
        id.extra = "extra";
        
        id.forValue = null;
        id.valueShape = null;
        id.id = null;
        id.forValueType = null;
        id.asProperty = null;
        id.include = null;
        id.wrapperWritten = false;
        id.extra = null;
        
        assertNull(id.forValue);
        assertNull(id.valueShape);
        assertNull(id.id);
        assertNull(id.forValueType);
        assertNull(id.asProperty);
        assertNull(id.include);
        assertFalse(id.wrapperWritten);
        assertNull(id.extra);
    }

    @Test
    public void testWrapperWritten_DefaultsToFalse_InAllConstructors() {
        assertFalse(new WritableTypeId().wrapperWritten);
        assertFalse(new WritableTypeId("v", JsonToken.VALUE_STRING).wrapperWritten);
        assertFalse(new WritableTypeId("v", String.class, JsonToken.VALUE_STRING).wrapperWritten);
        assertFalse(new WritableTypeId("v", JsonToken.VALUE_STRING, "id").wrapperWritten);
    }

    @Test
    public void testExtra_DefaultsToNull_InAllConstructors() {
        assertNull(new WritableTypeId().extra);
        assertNull(new WritableTypeId("v", JsonToken.VALUE_STRING).extra);
        assertNull(new WritableTypeId("v", String.class, JsonToken.VALUE_STRING).extra);
        assertNull(new WritableTypeId("v", JsonToken.VALUE_STRING, "id").extra);
    }

    // --- New tests for mutation testing: Inclusion enum ordinal and name ---

    @Test
    public void testInclusionEnum_NameAndOrdinalConsistency() {
        WritableTypeId.Inclusion[] values = WritableTypeId.Inclusion.values();
        for (int i = 0; i < values.length; i++) {
            assertEquals(i, values[i].ordinal());
            assertEquals(values[i].name(), values[i].toString());
        }
    }

    // --- New tests for mutation testing: constructor chaining verification ---

    @Test
    public void testConstructorChaining_TwoArgCallsThreeArgWithNullId() {
        Object value = "test";
        JsonToken shape = JsonToken.START_ARRAY;
        
        WritableTypeId id = new WritableTypeId(value, shape);
        
        // Verify delegation by checking id is null (set by three-arg constructor)
        assertNull(id.id);
        assertSame(value, id.forValue);
        assertSame(shape, id.valueShape);
    }

    @Test
    public void testConstructorChaining_ThreeArgValueTypeCallsThreeArgWithNullIdThenSetsForValueType() {
        Object value = "test";
        Class<?> type = Double.class;
        JsonToken shape = JsonToken.VALUE_NUMBER_FLOAT;
        
        WritableTypeId id = new WritableTypeId(value, type, shape);
        
        // Verify delegation: id should be null from three-arg constructor, then forValueType set
        assertNull(id.id);
        assertSame(type, id.forValueType);
        assertSame(value, id.forValue);
        assertSame(shape, id.valueShape);
    }
}
