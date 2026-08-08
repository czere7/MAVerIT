package tools.jackson.core.util;

import static org.junit.Assert.*;

import java.lang.reflect.Constructor;

import org.junit.Test;

import tools.jackson.core.*;
import tools.jackson.core.exc.StreamWriteException;
import tools.jackson.core.json.DupDetector;

public class SimpleStreamWriteContextTest {

    @Test
    public void testCreateRootContext() {
        SimpleStreamWriteContext root = SimpleStreamWriteContext.createRootContext(null);

        assertNotNull(root);
        assertTrue(root.inRoot());
        assertNull(root.getParent());
        assertEquals(0, root.getNestingDepth());
        assertNull(root.getDupDetector());
        assertNull(root.currentValue());
        assertFalse(root.hasCurrentName());
        assertNull(root.currentName());
    }

    @Test
    public void testCreateRootContextWithNullDupDetector() {
        SimpleStreamWriteContext root = SimpleStreamWriteContext.createRootContext(null);

        assertNotNull(root);
        assertNull(root.getDupDetector());
    }

    @Test
    public void testCreateChildArrayContext() {
        SimpleStreamWriteContext root = SimpleStreamWriteContext.createRootContext(null);
        Object currentValue = new Object();

        SimpleStreamWriteContext child = root.createChildArrayContext(currentValue);

        assertNotNull(child);
        assertTrue(child.inArray());
        assertSame(root, child.getParent());
        assertEquals(1, child.getNestingDepth());
        assertSame(currentValue, child.currentValue());
        assertEquals(0, child.getCurrentIndex());
        assertFalse(child.hasCurrentName());
    }

    @Test
    public void testCreateChildObjectContext() {
        SimpleStreamWriteContext root = SimpleStreamWriteContext.createRootContext(null);
        Object currentValue = new Object();

        SimpleStreamWriteContext child = root.createChildObjectContext(currentValue);

        assertNotNull(child);
        assertTrue(child.inObject());
        assertSame(root, child.getParent());
        assertEquals(1, child.getNestingDepth());
        assertSame(currentValue, child.currentValue());
        assertEquals(0, child.getCurrentIndex());
        assertFalse(child.hasCurrentName());
    }

    @Test
    public void testChildContextWithNullParentDupDetector() {
        SimpleStreamWriteContext root = SimpleStreamWriteContext.createRootContext(null);
        SimpleStreamWriteContext child = root.createChildArrayContext(null);

        assertNull(child.getDupDetector());
    }

    @Test
    public void testContextRecyclingForArray() {
        SimpleStreamWriteContext root = SimpleStreamWriteContext.createRootContext(null);
        Object value1 = new Object();
        Object value2 = new Object();

        SimpleStreamWriteContext child1 = root.createChildArrayContext(value1);
        SimpleStreamWriteContext child2 = root.createChildArrayContext(value2);

        assertSame(child1, child2);
        assertSame(value2, child2.currentValue());
        assertEquals(0, child2.getCurrentIndex());
        assertNull(child2.currentName());
        assertFalse(child2.hasCurrentName());
    }

    @Test
    public void testContextRecyclingForObject() {
        SimpleStreamWriteContext root = SimpleStreamWriteContext.createRootContext(null);
        Object value1 = new Object();
        Object value2 = new Object();

        SimpleStreamWriteContext child1 = root.createChildObjectContext(value1);
        SimpleStreamWriteContext child2 = root.createChildObjectContext(value2);

        assertSame(child1, child2);
        assertSame(value2, child2.currentValue());
        assertEquals(0, child2.getCurrentIndex());
        assertNull(child2.currentName());
        assertFalse(child2.hasCurrentName());
    }

    @Test
    public void testWriteNameInObjectContext() {
        SimpleStreamWriteContext root = SimpleStreamWriteContext.createRootContext(null);
        SimpleStreamWriteContext obj = root.createChildObjectContext(null);

        boolean result = obj.writeName("propertyName");

        assertTrue(result);
        assertTrue(obj.hasCurrentName());
        assertEquals("propertyName", obj.currentName());
    }

    @Test
    public void testWriteNameInArrayContextReturnsFalse() {
        SimpleStreamWriteContext root = SimpleStreamWriteContext.createRootContext(null);
        SimpleStreamWriteContext arr = root.createChildArrayContext(null);

        boolean result = arr.writeName("propertyName");

        assertFalse(result);
        assertFalse(arr.hasCurrentName());
        assertNull(arr.currentName());
    }

    @Test
    public void testWriteNameInRootContextReturnsFalse() {
        SimpleStreamWriteContext root = SimpleStreamWriteContext.createRootContext(null);

        boolean result = root.writeName("propertyName");

        assertFalse(result);
        assertFalse(root.hasCurrentName());
        assertNull(root.currentName());
    }

    @Test
    public void testWriteNameTwiceWithoutValueReturnsFalse() {
        SimpleStreamWriteContext root = SimpleStreamWriteContext.createRootContext(null);
        SimpleStreamWriteContext obj = root.createChildObjectContext(null);

        obj.writeName("first");
        boolean result = obj.writeName("second");

        assertFalse(result);
        assertEquals("first", obj.currentName());
    }

    @Test
    public void testWriteValueInObjectContextAfterName() {
        SimpleStreamWriteContext root = SimpleStreamWriteContext.createRootContext(null);
        SimpleStreamWriteContext obj = root.createChildObjectContext(null);

        obj.writeName("prop");
        boolean result = obj.writeValue();

        assertTrue(result);
        assertFalse(obj.hasCurrentName());
        assertEquals(0, obj.getCurrentIndex());
    }

    @Test
    public void testWriteValueInObjectContextWithoutNameReturnsFalse() {
        SimpleStreamWriteContext root = SimpleStreamWriteContext.createRootContext(null);
        SimpleStreamWriteContext obj = root.createChildObjectContext(null);

        boolean result = obj.writeValue();

        assertFalse(result);
        assertEquals(0, obj.getCurrentIndex());
    }

    @Test
    public void testWriteValueInArrayContext() {
        SimpleStreamWriteContext root = SimpleStreamWriteContext.createRootContext(null);
        SimpleStreamWriteContext arr = root.createChildArrayContext(null);

        boolean result1 = arr.writeValue();
        boolean result2 = arr.writeValue();
        boolean result3 = arr.writeValue();

        assertTrue(result1);
        assertTrue(result2);
        assertTrue(result3);
        assertEquals(2, arr.getCurrentIndex());
    }

    @Test
    public void testWriteValueInRootContext() {
        SimpleStreamWriteContext root = SimpleStreamWriteContext.createRootContext(null);

        boolean result1 = root.writeValue();
        boolean result2 = root.writeValue();

        assertTrue(result1);
        assertTrue(result2);
        assertEquals(1, root.getCurrentIndex());
    }

    @Test
    public void testWriteValueIncrementsIndex() {
        SimpleStreamWriteContext root = SimpleStreamWriteContext.createRootContext(null);
        SimpleStreamWriteContext arr = root.createChildArrayContext(null);

        arr.writeValue();
        assertEquals(0, arr.getCurrentIndex());
        assertEquals(1, arr.getEntryCount());

        arr.writeValue();
        assertEquals(1, arr.getCurrentIndex());
        assertEquals(2, arr.getEntryCount());
    }

    @Test
    public void testCurrentValueAndAssignCurrentValue() {
        SimpleStreamWriteContext root = SimpleStreamWriteContext.createRootContext(null);
        Object value1 = new Object();
        Object value2 = new Object();

        assertNull(root.currentValue());

        root.assignCurrentValue(value1);
        assertSame(value1, root.currentValue());

        root.assignCurrentValue(value2);
        assertSame(value2, root.currentValue());

        root.assignCurrentValue(null);
        assertNull(root.currentValue());
    }

    @Test
    public void testClearAndGetParentReturnsParentAndClearsValue() {
        SimpleStreamWriteContext root = SimpleStreamWriteContext.createRootContext(null);
        Object childValue = new Object();
        SimpleStreamWriteContext child = root.createChildObjectContext(childValue);

        child.assignCurrentValue(new Object());
        SimpleStreamWriteContext parent = child.clearAndGetParent();

        assertSame(root, parent);
        assertNull(child.currentValue());
    }

    @Test
    public void testClearAndGetParentOnRootReturnsNull() {
        SimpleStreamWriteContext root = SimpleStreamWriteContext.createRootContext(null);
        root.assignCurrentValue(new Object());

        SimpleStreamWriteContext parent = root.clearAndGetParent();

        assertNull(parent);
        assertNull(root.currentValue());
    }

    @Test
    public void testTypeDesc() {
        SimpleStreamWriteContext root1 = SimpleStreamWriteContext.createRootContext(null);
        SimpleStreamWriteContext arr = root1.createChildArrayContext(null);
        
        SimpleStreamWriteContext root2 = SimpleStreamWriteContext.createRootContext(null);
        SimpleStreamWriteContext obj = root2.createChildObjectContext(null);

        assertEquals("root", root1.typeDesc());
        assertEquals("Array", arr.typeDesc());
        assertEquals("Object", obj.typeDesc());
    }

    @Test
    public void testInRootInArrayInObject() {
        SimpleStreamWriteContext root1 = SimpleStreamWriteContext.createRootContext(null);
        SimpleStreamWriteContext arr = root1.createChildArrayContext(null);
        
        SimpleStreamWriteContext root2 = SimpleStreamWriteContext.createRootContext(null);
        SimpleStreamWriteContext obj = root2.createChildObjectContext(null);

        assertTrue(root1.inRoot());
        assertFalse(root1.inArray());
        assertFalse(root1.inObject());

        assertFalse(arr.inRoot());
        assertTrue(arr.inArray());
        assertFalse(arr.inObject());

        assertFalse(obj.inRoot());
        assertFalse(obj.inArray());
        assertTrue(obj.inObject());
    }

    @Test
    public void testGetParent() {
        SimpleStreamWriteContext root = SimpleStreamWriteContext.createRootContext(null);
        SimpleStreamWriteContext child = root.createChildArrayContext(null);
        SimpleStreamWriteContext grandChild = child.createChildObjectContext(null);

        assertNull(root.getParent());
        assertSame(root, child.getParent());
        assertSame(child, grandChild.getParent());
    }

    @Test
    public void testNestingDepth() {
        SimpleStreamWriteContext root = SimpleStreamWriteContext.createRootContext(null);
        SimpleStreamWriteContext child1 = root.createChildArrayContext(null);
        SimpleStreamWriteContext child2 = child1.createChildObjectContext(null);
        SimpleStreamWriteContext child3 = child2.createChildArrayContext(null);

        assertEquals(0, root.getNestingDepth());
        assertEquals(1, child1.getNestingDepth());
        assertEquals(2, child2.getNestingDepth());
        assertEquals(3, child3.getNestingDepth());
    }

    @Test
    public void testGetCurrentIndexAndHasCurrentIndex() {
        SimpleStreamWriteContext root = SimpleStreamWriteContext.createRootContext(null);
        SimpleStreamWriteContext arr = root.createChildArrayContext(null);

        assertEquals(0, root.getCurrentIndex());
        assertFalse(root.hasCurrentIndex());

        assertEquals(0, arr.getCurrentIndex());
        assertFalse(arr.hasCurrentIndex());

        root.writeValue();
        assertEquals(0, root.getCurrentIndex());
        assertTrue(root.hasCurrentIndex());

        arr.writeValue();
        assertEquals(0, arr.getCurrentIndex());
        assertTrue(arr.hasCurrentIndex());

        arr.writeValue();
        assertEquals(1, arr.getCurrentIndex());
        assertTrue(arr.hasCurrentIndex());
    }

    @Test
    public void testHasPathSegment() {
        SimpleStreamWriteContext root = SimpleStreamWriteContext.createRootContext(null);
        SimpleStreamWriteContext arr = root.createChildArrayContext(null);
        // Use separate root to avoid context recycling interference
        SimpleStreamWriteContext root2 = SimpleStreamWriteContext.createRootContext(null);
        SimpleStreamWriteContext obj = root2.createChildObjectContext(null);

        assertFalse(root.hasPathSegment());
        assertFalse(arr.hasPathSegment());
        assertFalse(obj.hasPathSegment());

        arr.writeValue();
        assertTrue(arr.hasPathSegment());

        obj.writeName("prop");
        assertTrue(obj.hasPathSegment());

        obj.writeValue();
        // After writeValue() in object context, _gotPropertyId becomes false,
        // so hasPathSegment() returns false (hasCurrentName() returns _gotPropertyId)
        assertFalse(obj.hasPathSegment());
    }

    @Test
    public void testToString() {
        SimpleStreamWriteContext root = SimpleStreamWriteContext.createRootContext(null);
        SimpleStreamWriteContext arr = root.createChildArrayContext(null);
        // Use separate root to avoid context recycling interference
        SimpleStreamWriteContext root2 = SimpleStreamWriteContext.createRootContext(null);
        SimpleStreamWriteContext obj = root2.createChildObjectContext(null);

        assertEquals("/", root.toString().trim());

        arr.writeValue();
        String arrStr = arr.toString();
        assertTrue(arrStr.contains("["));
        assertTrue(arrStr.contains("]"));
        assertTrue(arrStr.contains("0"));

        obj.writeName("testProp");
        String objStr = obj.toString();
        assertTrue(objStr.contains("{"));
        assertTrue(objStr.contains("}"));
        assertTrue(objStr.contains("testProp"));
    }

    @Test
    public void testPathAsPointer() {
        SimpleStreamWriteContext root = SimpleStreamWriteContext.createRootContext(null);
        SimpleStreamWriteContext arr = root.createChildArrayContext(null);
        SimpleStreamWriteContext obj = arr.createChildObjectContext(null);

        obj.writeName("property");

        JsonPointer pointer = obj.pathAsPointer();
        assertNotNull(pointer);

        JsonPointer pointerWithRoot = obj.pathAsPointer(true);
        assertNotNull(pointerWithRoot);
    }

    @Test
    public void testStartLocationReturnsNA() {
        SimpleStreamWriteContext root = SimpleStreamWriteContext.createRootContext(null);

        TokenStreamLocation location = root.startLocation(null);
        assertSame(TokenStreamLocation.NA, location);
    }

    @Test
    public void testMultipleChildrenFromSameParent() {
        SimpleStreamWriteContext root = SimpleStreamWriteContext.createRootContext(null);

        SimpleStreamWriteContext arr1 = root.createChildArrayContext("arr1");
        SimpleStreamWriteContext obj1 = root.createChildObjectContext("obj1");
        SimpleStreamWriteContext arr2 = root.createChildArrayContext("arr2");

        // Single recycling slot: arr1 recycled to obj1, then obj1 recycled to arr2
        // All three reference the same context object
        assertSame(arr1, obj1);
        assertSame(obj1, arr2);
        assertEquals("arr2", arr2.currentValue());
        // obj1 was recycled to arr2, so its currentValue is now "arr2"
        assertEquals("arr2", obj1.currentValue());
    }

    @Test
    public void testWriteNameWithNullName() {
        SimpleStreamWriteContext root = SimpleStreamWriteContext.createRootContext(null);
        SimpleStreamWriteContext obj = root.createChildObjectContext(null);

        boolean result = obj.writeName(null);

        assertTrue(result);
        assertTrue(obj.hasCurrentName());
        assertNull(obj.currentName());
    }

    @Test
    public void testWriteValueAfterNameResetsGotPropertyId() {
        SimpleStreamWriteContext root = SimpleStreamWriteContext.createRootContext(null);
        SimpleStreamWriteContext obj = root.createChildObjectContext(null);

        obj.writeName("prop1");
        assertTrue(obj.hasCurrentName());

        obj.writeValue();
        assertFalse(obj.hasCurrentName());

        obj.writeName("prop2");
        assertTrue(obj.hasCurrentName());
        assertEquals("prop2", obj.currentName());
    }

    @Test
    public void testAssignCurrentValueInChildContext() {
        SimpleStreamWriteContext root = SimpleStreamWriteContext.createRootContext(null);
        Object parentValue = "parent";
        Object childValue = "child";

        SimpleStreamWriteContext child = root.createChildArrayContext(childValue);

        root.assignCurrentValue(parentValue);
        child.assignCurrentValue(childValue);

        assertEquals(parentValue, root.currentValue());
        assertEquals(childValue, child.currentValue());
    }

    @Test
    public void testClearAndGetParentPreservesParentStructure() {
        SimpleStreamWriteContext root = SimpleStreamWriteContext.createRootContext(null);
        SimpleStreamWriteContext child1 = root.createChildArrayContext(null);
        SimpleStreamWriteContext child2 = child1.createChildObjectContext(null);

        SimpleStreamWriteContext parent = child2.clearAndGetParent();

        assertSame(child1, parent);
        assertSame(root, parent.getParent());
        assertNull(child2.currentValue());
    }

    @Test
    public void testRootContextWriteValue() {
        SimpleStreamWriteContext root = SimpleStreamWriteContext.createRootContext(null);

        assertTrue(root.writeValue());
        assertEquals(0, root.getCurrentIndex());

        assertTrue(root.writeValue());
        assertEquals(1, root.getCurrentIndex());
    }

    @Test
    public void testObjectContextWriteValueAfterName() {
        SimpleStreamWriteContext root = SimpleStreamWriteContext.createRootContext(null);
        SimpleStreamWriteContext obj = root.createChildObjectContext(null);

        obj.writeName("prop");
        assertTrue(obj.writeValue());
        assertEquals(0, obj.getCurrentIndex());
        assertFalse(obj.hasCurrentName());
    }

    @Test
    public void testArrayContextWriteValueMultipleTimes() {
        SimpleStreamWriteContext root = SimpleStreamWriteContext.createRootContext(null);
        SimpleStreamWriteContext arr = root.createChildArrayContext(null);

        for (int i = 0; i < 5; i++) {
            assertTrue(arr.writeValue());
            assertEquals(i, arr.getCurrentIndex());
        }
    }

    @Test
    public void testCurrentNameBeforeAndAfterWriteName() {
        SimpleStreamWriteContext root = SimpleStreamWriteContext.createRootContext(null);
        SimpleStreamWriteContext obj = root.createChildObjectContext(null);

        assertNull(obj.currentName());
        assertFalse(obj.hasCurrentName());

        obj.writeName("testProperty");

        assertEquals("testProperty", obj.currentName());
        assertTrue(obj.hasCurrentName());
    }

    // ==================== TESTS FOR DUP DETECTOR (using null detector only, as constructor is private) ====================

    @Test
    public void testWithDupDetectorReturnsThis() {
        SimpleStreamWriteContext root = SimpleStreamWriteContext.createRootContext(null);
        
        // withDupDetector accepts null and returns this for chaining
        SimpleStreamWriteContext result = root.withDupDetector(null);
        assertSame(root, result);
        assertNull(root.getDupDetector());
    }

    @Test
    public void testWithDupDetectorAllowsChainingWithNull() {
        SimpleStreamWriteContext root = SimpleStreamWriteContext.createRootContext(null);
        
        // Verify chaining works with null
        SimpleStreamWriteContext result = root.withDupDetector(null).withDupDetector(null);
        assertSame(root, result);
        assertNull(root.getDupDetector());
    }

    @Test
    public void testGetDupDetectorReturnsNullWhenNotSet() {
        SimpleStreamWriteContext root = SimpleStreamWriteContext.createRootContext(null);
        
        // Default detector is null
        assertNull(root.getDupDetector());
    }

    @Test
    public void testChildContextInheritsNullDupDetector() {
        SimpleStreamWriteContext root = SimpleStreamWriteContext.createRootContext(null);
        SimpleStreamWriteContext child = root.createChildArrayContext(null);
        
        // Child should also have null detector when parent has null
        assertNull(child.getDupDetector());
    }

    @Test
    public void testWriteNameWithNullDetectorNoDuplicateCheck() {
        SimpleStreamWriteContext root = SimpleStreamWriteContext.createRootContext(null);
        SimpleStreamWriteContext obj = root.createChildObjectContext(null);

        // With null detector, writeName should not throw and should succeed
        boolean result = obj.writeName("prop1");
        assertTrue(result);
        assertTrue(obj.hasCurrentName());
        assertEquals("prop1", obj.currentName());
        
        // Writing same name again without writeValue should return false (not throw)
        obj.writeValue();
        result = obj.writeName("prop1");
        assertTrue(result); // No detector, so no duplicate check
    }

    @Test
    public void testWriteNameInArrayContextDoesNotCallCheckDup() {
        SimpleStreamWriteContext root = SimpleStreamWriteContext.createRootContext(null);
        SimpleStreamWriteContext arr = root.createChildArrayContext(null);

        boolean result = arr.writeName("prop1");
        assertFalse(result);
        assertFalse(arr.hasCurrentName());
        assertNull(arr.currentName());
    }

    @Test
    public void testWriteNameInRootContextDoesNotCallCheckDup() {
        SimpleStreamWriteContext root = SimpleStreamWriteContext.createRootContext(null);

        boolean result = root.writeName("prop1");
        assertFalse(result);
        assertFalse(root.hasCurrentName());
        assertNull(root.currentName());
    }

    @Test
    public void testDupDetectorChildCreationWithNullParent() {
        // When parent detector is null, child also gets null
        SimpleStreamWriteContext root = SimpleStreamWriteContext.createRootContext(null);
        SimpleStreamWriteContext child = root.createChildArrayContext(null);
        SimpleStreamWriteContext grandChild = child.createChildObjectContext(null);
        
        assertNull(root.getDupDetector());
        assertNull(child.getDupDetector());
        assertNull(grandChild.getDupDetector());
    }

    // ==================== NEW TESTS FOR DUP DETECTOR WITH REAL INSTANCE ====================

    /**
     * Creates a real DupDetector instance using reflection since constructor is private.
     */
    private DupDetector createDupDetector() throws Exception {
        Constructor<DupDetector> constructor = DupDetector.class.getDeclaredConstructor(Object.class);
        constructor.setAccessible(true);
        return constructor.newInstance(this); // use test instance as source
    }

    @Test
    public void testGetDupDetectorReturnsSetDetector() throws Exception {
        DupDetector detector = createDupDetector();
        SimpleStreamWriteContext root = SimpleStreamWriteContext.createRootContext(detector);
        
        assertSame(detector, root.getDupDetector());
    }

    @Test
    public void testWithDupDetectorSetsDetector() throws Exception {
        SimpleStreamWriteContext root = SimpleStreamWriteContext.createRootContext(null);
        assertNull(root.getDupDetector());
        
        DupDetector detector = createDupDetector();
        SimpleStreamWriteContext result = root.withDupDetector(detector);
        
        assertSame(root, result);
        assertSame(detector, root.getDupDetector());
    }

    @Test
    public void testChildContextInheritsDupDetector() throws Exception {
        DupDetector detector = createDupDetector();
        SimpleStreamWriteContext root = SimpleStreamWriteContext.createRootContext(detector);
        SimpleStreamWriteContext child = root.createChildArrayContext("value");
        SimpleStreamWriteContext grandChild = child.createChildObjectContext("value2");
        
        assertSame(detector, root.getDupDetector());
        // Child should get detector.child() - a new instance with same source
        assertNotNull(child.getDupDetector());
        assertNotSame(detector, child.getDupDetector());
        // Grandchild should get child of child
        assertNotNull(grandChild.getDupDetector());
        assertNotSame(child.getDupDetector(), grandChild.getDupDetector());
    }

    @Test
    public void testWriteNameWithDupDetectorDetectsDuplicate() throws Exception {
        DupDetector detector = createDupDetector();
        SimpleStreamWriteContext root = SimpleStreamWriteContext.createRootContext(detector);
        SimpleStreamWriteContext obj = root.createChildObjectContext(null);
        
        // First writeName should succeed
        boolean result = obj.writeName("prop");
        assertTrue(result);
        assertEquals("prop", obj.currentName());
        assertTrue(obj.hasCurrentName());
        
        // Write value to reset _gotPropertyId
        obj.writeValue();
        assertFalse(obj.hasCurrentName());
        
        // Second writeName with same name should throw StreamWriteException
        try {
            obj.writeName("prop");
            fail("Expected StreamWriteException for duplicate property");
        } catch (StreamWriteException e) {
            assertTrue(e.getMessage().contains("Duplicate Object property"));
            assertTrue(e.getMessage().contains("prop"));
        }
    }

    @Test
    public void testWriteNameWithDupDetectorAllowsNonDuplicate() throws Exception {
        DupDetector detector = createDupDetector();
        SimpleStreamWriteContext root = SimpleStreamWriteContext.createRootContext(detector);
        SimpleStreamWriteContext obj = root.createChildObjectContext(null);
        
        obj.writeName("prop1");
        obj.writeValue();
        
        // Different name should succeed
        boolean result = obj.writeName("prop2");
        assertTrue(result);
        assertEquals("prop2", obj.currentName());
        assertTrue(obj.hasCurrentName());
    }

    @Test
    public void testWriteNameInArrayContextWithDupDetectorDoesNotCallCheckDup() throws Exception {
        DupDetector detector = createDupDetector();
        SimpleStreamWriteContext root = SimpleStreamWriteContext.createRootContext(detector);
        SimpleStreamWriteContext arr = root.createChildArrayContext(null);
        
        // writeName in array context should return false and not call _checkDup
        boolean result = arr.writeName("prop");
        assertFalse(result);
        assertFalse(arr.hasCurrentName());
        assertNull(arr.currentName());
    }

    @Test
    public void testWriteNameInRootContextWithDupDetectorDoesNotCallCheckDup() throws Exception {
        DupDetector detector = createDupDetector();
        SimpleStreamWriteContext root = SimpleStreamWriteContext.createRootContext(detector);
        
        // writeName in root context should return false and not call _checkDup
        boolean result = root.writeName("prop");
        assertFalse(result);
        assertFalse(root.hasCurrentName());
        assertNull(root.currentName());
    }

    @Test
    public void testResetCallsDupDetectorResetOnRecycle() throws Exception {
        DupDetector detector = createDupDetector();
        SimpleStreamWriteContext root = SimpleStreamWriteContext.createRootContext(detector);
        
        // Create first child - creates new context with detector.child()
        SimpleStreamWriteContext child1 = root.createChildObjectContext("value1");
        DupDetector childDetector = child1.getDupDetector();
        assertNotNull(childDetector);
        assertNotSame(detector, childDetector);
        
        // Write a name so detector sees it
        child1.writeName("testProp");
        child1.writeValue();
        
        // Create second child of same type - should recycle and call reset() on detector
        SimpleStreamWriteContext child2 = root.createChildObjectContext("value2");
        assertSame(child1, child2); // Same recycled instance
        
        // After recycle, detector should be reset (no names seen)
        // Writing same name should NOT throw because detector was reset
        child2.writeName("testProp"); // Should not throw
        assertTrue(child2.hasCurrentName());
        assertEquals("testProp", child2.currentName());
    }

    @Test
    public void testWriteNameThrowsOnDuplicateInRecycledContext() throws Exception {
        DupDetector detector = createDupDetector();
        SimpleStreamWriteContext root = SimpleStreamWriteContext.createRootContext(detector);
        
        // Create first child
        SimpleStreamWriteContext child1 = root.createChildObjectContext("value1");
        child1.writeName("prop");
        child1.writeValue();
        
        // Create second child (recycles) - detector should be reset
        SimpleStreamWriteContext child2 = root.createChildObjectContext("value2");
        
        // Write name in recycled context
        child2.writeName("prop");
        child2.writeValue();
        
        // Write same name again in same context - should throw
        try {
            child2.writeName("prop");
            fail("Expected StreamWriteException for duplicate property in recycled context");
        } catch (StreamWriteException e) {
            assertTrue(e.getMessage().contains("Duplicate Object property"));
        }
    }

    @Test
    public void testDupDetectorChildHasSameSource() throws Exception {
        DupDetector detector = createDupDetector();
        SimpleStreamWriteContext root = SimpleStreamWriteContext.createRootContext(detector);
        SimpleStreamWriteContext child = root.createChildArrayContext(null);
        
        DupDetector childDetector = child.getDupDetector();
        assertNotNull(childDetector);
        assertNotSame(detector, childDetector);
        // Both should have same source (this test instance)
        assertSame(detector.getSource(), childDetector.getSource());
    }

    @Test
    public void testMultipleDupDetectorsInHierarchy() throws Exception {
        DupDetector detector = createDupDetector();
        SimpleStreamWriteContext root = SimpleStreamWriteContext.createRootContext(detector);
        SimpleStreamWriteContext arr = root.createChildArrayContext(null);
        SimpleStreamWriteContext obj = arr.createChildObjectContext(null);
        
        // Each level should have its own detector instance
        assertSame(detector, root.getDupDetector());
        assertNotSame(detector, arr.getDupDetector());
        assertNotSame(arr.getDupDetector(), obj.getDupDetector());
        
        // But all should have same source
        assertSame(detector.getSource(), arr.getDupDetector().getSource());
        assertSame(detector.getSource(), obj.getDupDetector().getSource());
    }

    @Test
    public void testWriteNameWithNullDetectorAllowsDuplicateNames() {
        SimpleStreamWriteContext root = SimpleStreamWriteContext.createRootContext(null);
        SimpleStreamWriteContext obj = root.createChildObjectContext(null);

        obj.writeName("prop");
        obj.writeValue();
        
        // With null detector, duplicate should be allowed (no check)
        boolean result = obj.writeName("prop");
        assertTrue(result);
        assertEquals("prop", obj.currentName());
    }
}
