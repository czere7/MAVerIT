package tools.jackson.core.json;

import org.junit.Test;
import static org.junit.Assert.*;

import tools.jackson.core.JsonGenerator;
import tools.jackson.core.TokenStreamContext;
import tools.jackson.core.exc.StreamWriteException;
import tools.jackson.core.json.DupDetector;

public class JsonWriteContextTest {

    @Test
    public void testCreateRootContext() {
        JsonWriteContext root = JsonWriteContext.createRootContext(null);
        
        assertNotNull(root);
        assertEquals("root", root.typeDesc());
        assertNull(root.getParent());
        assertEquals(0, root.getNestingDepth());
        assertEquals(0, root.getCurrentIndex());
        assertNull(root.currentName());
        assertFalse(root.hasCurrentName());
        assertNull(root.currentValue());
        assertNull(root.getDupDetector());
    }

    @Test
    public void testCreateChildArrayContext() {
        JsonWriteContext root = JsonWriteContext.createRootContext(null);
        Object currentValue = new Object();
        
        JsonWriteContext child = root.createChildArrayContext(currentValue);
        
        assertNotNull(child);
        assertEquals("Array", child.typeDesc());
        assertSame(root, child.getParent());
        assertEquals(1, child.getNestingDepth());
        assertEquals(0, child.getCurrentIndex());
        assertSame(currentValue, child.currentValue());
        assertNull(child.currentName());
        assertFalse(child.hasCurrentName());
        assertNull(child.getDupDetector());
    }

    @Test
    public void testCreateChildArrayContextReusesInstance() {
        JsonWriteContext root = JsonWriteContext.createRootContext(null);
        Object value1 = new Object();
        Object value2 = new Object();
        
        JsonWriteContext child1 = root.createChildArrayContext(value1);
        JsonWriteContext child2 = root.createChildArrayContext(value2);
        
        assertSame(child1, child2);
        assertSame(value2, child2.currentValue());
        assertEquals(0, child2.getCurrentIndex());
        assertNull(child2.currentName());
        assertFalse(child2.hasCurrentName());
    }

    @Test
    public void testCreateChildObjectContext() {
        JsonWriteContext root = JsonWriteContext.createRootContext(null);
        Object currentValue = new Object();
        
        JsonWriteContext child = root.createChildObjectContext(currentValue);
        
        assertNotNull(child);
        assertEquals("Object", child.typeDesc());
        assertSame(root, child.getParent());
        assertEquals(1, child.getNestingDepth());
        assertEquals(0, child.getCurrentIndex());
        assertSame(currentValue, child.currentValue());
        assertNull(child.currentName());
        assertFalse(child.hasCurrentName());
    }

    @Test
    public void testCreateChildObjectContextReusesInstance() {
        JsonWriteContext root = JsonWriteContext.createRootContext(null);
        Object value1 = new Object();
        Object value2 = new Object();
        
        JsonWriteContext child1 = root.createChildObjectContext(value1);
        JsonWriteContext child2 = root.createChildObjectContext(value2);
        
        assertSame(child1, child2);
        assertSame(value2, child2.currentValue());
    }

    @Test
    public void testWriteNameInObjectContextFirstName() {
        JsonWriteContext ctx = JsonWriteContext.createRootContext(null)
                .createChildObjectContext(null);
        
        int status = ctx.writeName("prop1");
        
        assertEquals(JsonWriteContext.STATUS_OK_AS_IS, status);
        assertEquals("prop1", ctx.currentName());
        assertTrue(ctx.hasCurrentName());
        assertEquals(0, ctx.getCurrentIndex());
    }

    @Test
    public void testWriteNameInObjectContextSubsequentNames() {
        JsonWriteContext ctx = JsonWriteContext.createRootContext(null)
                .createChildObjectContext(null);
        
        ctx.writeName("prop1");
        ctx.writeValue(); // Must write value before next name
        int status = ctx.writeName("prop2");
        
        assertEquals(JsonWriteContext.STATUS_OK_AFTER_COMMA, status);
        assertEquals("prop2", ctx.currentName());
        assertTrue(ctx.hasCurrentName());
        // Index increments on writeValue, not writeName. After first writeValue, index is 0.
        assertEquals(0, ctx.getCurrentIndex());
    }

    @Test
    public void testWriteNameInNonObjectContextReturnsExpectValue() {
        JsonWriteContext arrayCtx = JsonWriteContext.createRootContext(null)
                .createChildArrayContext(null);
        JsonWriteContext rootCtx = JsonWriteContext.createRootContext(null);
        
        assertEquals(JsonWriteContext.STATUS_EXPECT_VALUE, arrayCtx.writeName("name"));
        assertEquals(JsonWriteContext.STATUS_EXPECT_VALUE, rootCtx.writeName("name"));
    }

    @Test
    public void testWriteNameWhenAlreadyGotNameReturnsExpectValue() {
        JsonWriteContext ctx = JsonWriteContext.createRootContext(null)
                .createChildObjectContext(null);
        
        ctx.writeName("prop1");
        int status = ctx.writeName("prop2");
        
        assertEquals(JsonWriteContext.STATUS_EXPECT_VALUE, status);
    }

    @Test
    public void testWriteValueInObjectContextAfterName() {
        JsonWriteContext ctx = JsonWriteContext.createRootContext(null)
                .createChildObjectContext(null);
        
        ctx.writeName("prop");
        int status = ctx.writeValue();
        
        assertEquals(JsonWriteContext.STATUS_OK_AFTER_COLON, status);
        // Name persists after writeValue until next writeName call
        assertTrue(ctx.hasCurrentName());
        assertEquals("prop", ctx.currentName());
        assertEquals(0, ctx.getCurrentIndex());
    }

    @Test
    public void testWriteValueInObjectContextWithoutNameReturnsExpectName() {
        JsonWriteContext ctx = JsonWriteContext.createRootContext(null)
                .createChildObjectContext(null);
        
        int status = ctx.writeValue();
        
        assertEquals(JsonWriteContext.STATUS_EXPECT_NAME, status);
        assertEquals(0, ctx.getCurrentIndex());
    }

    @Test
    public void testWriteValueInArrayContextFirstElement() {
        JsonWriteContext ctx = JsonWriteContext.createRootContext(null)
                .createChildArrayContext(null);
        
        int status = ctx.writeValue();
        
        assertEquals(JsonWriteContext.STATUS_OK_AS_IS, status);
        assertEquals(0, ctx.getCurrentIndex());
    }

    @Test
    public void testWriteValueInArrayContextSubsequentElements() {
        JsonWriteContext ctx = JsonWriteContext.createRootContext(null)
                .createChildArrayContext(null);
        
        ctx.writeValue();
        int status = ctx.writeValue();
        
        assertEquals(JsonWriteContext.STATUS_OK_AFTER_COMMA, status);
        assertEquals(1, ctx.getCurrentIndex());
    }

    @Test
    public void testWriteValueInRootContextFirstValue() {
        JsonWriteContext ctx = JsonWriteContext.createRootContext(null);
        
        int status = ctx.writeValue();
        
        assertEquals(JsonWriteContext.STATUS_OK_AS_IS, status);
        assertEquals(0, ctx.getCurrentIndex());
    }

    @Test
    public void testWriteValueInRootContextSubsequentValues() {
        JsonWriteContext ctx = JsonWriteContext.createRootContext(null);
        
        ctx.writeValue();
        int status = ctx.writeValue();
        
        assertEquals(JsonWriteContext.STATUS_OK_AFTER_SPACE, status);
        assertEquals(1, ctx.getCurrentIndex());
    }

    @Test
    public void testReset() {
        JsonWriteContext ctx = JsonWriteContext.createRootContext(null)
                .createChildObjectContext("originalValue");
        
        ctx.writeName("prop");
        ctx.writeValue();
        
        Object newValue = new Object();
        JsonWriteContext reset = ctx.reset(TokenStreamContext.TYPE_ARRAY, newValue);
        
        assertSame(ctx, reset);
        assertEquals("Array", ctx.typeDesc());
        assertEquals(0, ctx.getCurrentIndex());
        assertNull(ctx.currentName());
        assertFalse(ctx.hasCurrentName());
        assertSame(newValue, ctx.currentValue());
    }

    @Test
    public void testWithDupDetector() {
        JsonWriteContext ctx = JsonWriteContext.createRootContext(null);
        
        // Cannot test with actual DupDetector due to private constructor
        // Test verifies method exists and returns self
        JsonWriteContext result = ctx.withDupDetector(null);
        
        assertSame(ctx, result);
        assertNull(ctx.getDupDetector());
    }

    @Test
    public void testClearAndGetParent() {
        JsonWriteContext root = JsonWriteContext.createRootContext(null);
        JsonWriteContext child = root.createChildObjectContext("childValue");
        
        child.assignCurrentValue("assignedValue");
        JsonWriteContext parent = child.clearAndGetParent();
        
        assertSame(root, parent);
        assertNull(child.currentValue());
    }

    @Test
    public void testClearAndGetParentOnRootReturnsNull() {
        JsonWriteContext root = JsonWriteContext.createRootContext(null);
        root.assignCurrentValue("value");
        
        JsonWriteContext parent = root.clearAndGetParent();
        
        assertNull(parent);
        assertNull(root.currentValue());
    }

    @Test
    public void testCurrentValueAndAssignCurrentValue() {
        JsonWriteContext ctx = JsonWriteContext.createRootContext(null);
        Object value = new Object();
        
        assertNull(ctx.currentValue());
        
        ctx.assignCurrentValue(value);
        assertSame(value, ctx.currentValue());
        
        ctx.assignCurrentValue(null);
        assertNull(ctx.currentValue());
    }

    @Test
    public void testCurrentNameAndHasCurrentName() {
        JsonWriteContext ctx = JsonWriteContext.createRootContext(null)
                .createChildObjectContext(null);
        
        assertNull(ctx.currentName());
        assertFalse(ctx.hasCurrentName());
        
        ctx.writeName("property");
        assertEquals("property", ctx.currentName());
        assertTrue(ctx.hasCurrentName());
        
        ctx.writeValue();
        assertEquals("property", ctx.currentName());
        assertTrue(ctx.hasCurrentName());
    }

    @Test
    public void testGetParent() {
        JsonWriteContext root = JsonWriteContext.createRootContext(null);
        JsonWriteContext child = root.createChildArrayContext(null);
        JsonWriteContext grandChild = child.createChildObjectContext(null);
        
        assertNull(root.getParent());
        assertSame(root, child.getParent());
        assertSame(child, grandChild.getParent());
    }

    @Test
    public void testNestingDepth() {
        JsonWriteContext root = JsonWriteContext.createRootContext(null);
        assertEquals(0, root.getNestingDepth());
        
        JsonWriteContext child1 = root.createChildArrayContext(null);
        assertEquals(1, child1.getNestingDepth());
        
        JsonWriteContext child2 = child1.createChildObjectContext(null);
        assertEquals(2, child2.getNestingDepth());
        
        JsonWriteContext child3 = child2.createChildArrayContext(null);
        assertEquals(3, child3.getNestingDepth());
    }

    @Test
    public void testTypeDesc() {
        // Use separate roots because child contexts are reused per parent
        JsonWriteContext root1 = JsonWriteContext.createRootContext(null);
        JsonWriteContext root2 = JsonWriteContext.createRootContext(null);
        JsonWriteContext root3 = JsonWriteContext.createRootContext(null);
        
        JsonWriteContext array = root1.createChildArrayContext(null);
        JsonWriteContext object = root2.createChildObjectContext(null);
        
        assertEquals("root", root3.typeDesc());
        assertEquals("Array", array.typeDesc());
        assertEquals("Object", object.typeDesc());
    }

    @Test
    public void testInRootInArrayInObject() {
        // Use separate roots because child contexts are reused per parent
        JsonWriteContext root = JsonWriteContext.createRootContext(null);
        JsonWriteContext array = JsonWriteContext.createRootContext(null).createChildArrayContext(null);
        JsonWriteContext object = JsonWriteContext.createRootContext(null).createChildObjectContext(null);
        
        assertTrue(root.inRoot());
        assertFalse(root.inArray());
        assertFalse(root.inObject());
        
        assertFalse(array.inRoot());
        assertTrue(array.inArray());
        assertFalse(array.inObject());
        
        assertFalse(object.inRoot());
        assertFalse(object.inArray());
        assertTrue(object.inObject());
    }

    @Test
    public void testGetEntryCount() {
        JsonWriteContext ctx = JsonWriteContext.createRootContext(null)
                .createChildArrayContext(null);
        
        assertEquals(0, ctx.getEntryCount());
        
        ctx.writeValue();
        assertEquals(1, ctx.getEntryCount());
        
        ctx.writeValue();
        assertEquals(2, ctx.getEntryCount());
    }

    @Test
    public void testGetCurrentIndex() {
        JsonWriteContext ctx = JsonWriteContext.createRootContext(null)
                .createChildArrayContext(null);
        
        assertEquals(0, ctx.getCurrentIndex());
        
        ctx.writeValue();
        assertEquals(0, ctx.getCurrentIndex());
        
        ctx.writeValue();
        assertEquals(1, ctx.getCurrentIndex());
    }

    @Test
    public void testHasCurrentIndex() {
        JsonWriteContext ctx = JsonWriteContext.createRootContext(null)
                .createChildArrayContext(null);
        
        assertFalse(ctx.hasCurrentIndex());
        
        ctx.writeValue();
        assertTrue(ctx.hasCurrentIndex());
    }

    @Test
    public void testHasPathSegment() {
        // Use separate roots because child contexts are reused per parent
        JsonWriteContext root = JsonWriteContext.createRootContext(null);
        JsonWriteContext array = JsonWriteContext.createRootContext(null).createChildArrayContext(null);
        JsonWriteContext object = JsonWriteContext.createRootContext(null).createChildObjectContext(null);
        
        assertFalse(root.hasPathSegment());
        assertFalse(array.hasPathSegment());
        assertFalse(object.hasPathSegment());
        
        array.writeValue();
        assertTrue(array.hasPathSegment());
        
        object.writeName("prop");
        assertTrue(object.hasPathSegment());
    }

    @Test
    public void testToString() {
        JsonWriteContext root = JsonWriteContext.createRootContext(null);
        assertEquals("/", root.toString());
        
        JsonWriteContext array = root.createChildArrayContext(null);
        array.writeValue();
        assertEquals("[0]", array.toString());
        
        JsonWriteContext object = root.createChildObjectContext(null);
        object.writeName("test");
        String str = object.toString();
        assertTrue(str.contains("{"));
        assertTrue(str.contains("test"));
        assertTrue(str.contains("}"));
    }

    @Test
    public void testPathAsPointer() {
        JsonWriteContext root = JsonWriteContext.createRootContext(null);
        JsonWriteContext array = root.createChildArrayContext(null);
        array.writeValue();
        JsonWriteContext object = array.createChildObjectContext(null);
        object.writeName("property");
        
        assertNotNull(root.pathAsPointer());
        assertNotNull(array.pathAsPointer());
        assertNotNull(object.pathAsPointer());
    }

    @Test
    public void testMultipleLevelsOfNesting() {
        JsonWriteContext root = JsonWriteContext.createRootContext(null);
        JsonWriteContext arr1 = root.createChildArrayContext("arr1");
        JsonWriteContext obj1 = arr1.createChildObjectContext("obj1");
        JsonWriteContext arr2 = obj1.createChildArrayContext("arr2");
        JsonWriteContext obj2 = arr2.createChildObjectContext("obj2");
        
        assertEquals(4, obj2.getNestingDepth());
        assertSame(arr2, obj2.getParent());
        assertSame(obj1, arr2.getParent());
        assertSame(arr1, obj1.getParent());
        assertSame(root, arr1.getParent());
    }

    @Test
    public void testWriteNameUpdatesIndexCorrectly() {
        JsonWriteContext ctx = JsonWriteContext.createRootContext(null)
                .createChildObjectContext(null);
        
        // Index starts at 0 (displayed) but internal _index is -1
        assertEquals(0, ctx.getCurrentIndex());
        
        ctx.writeName("a");
        // writeName doesn't increment index
        assertEquals(0, ctx.getCurrentIndex());
        
        ctx.writeValue();
        // writeValue increments index to 0
        ctx.writeName("b");
        // writeName doesn't increment index
        assertEquals(0, ctx.getCurrentIndex());
        
        ctx.writeValue();
        // writeValue increments index to 1
        ctx.writeName("c");
        // writeName doesn't increment index
        assertEquals(1, ctx.getCurrentIndex());
    }

    @Test
    public void testWriteValueInObjectIncrementsIndex() {
        JsonWriteContext ctx = JsonWriteContext.createRootContext(null)
                .createChildObjectContext(null);
        
        ctx.writeName("a");
        assertEquals(0, ctx.getCurrentIndex());
        
        ctx.writeValue();
        // After first writeValue, index is 0
        assertEquals(0, ctx.getCurrentIndex());
        
        ctx.writeName("b");
        ctx.writeValue();
        // After second writeValue, index is 1
        assertEquals(1, ctx.getCurrentIndex());
    }

    @Test
    public void testWriteValueInArrayIncrementsIndex() {
        JsonWriteContext ctx = JsonWriteContext.createRootContext(null)
                .createChildArrayContext(null);
        
        assertEquals(0, ctx.getCurrentIndex());
        
        ctx.writeValue();
        assertEquals(0, ctx.getCurrentIndex());
        
        ctx.writeValue();
        assertEquals(1, ctx.getCurrentIndex());
        
        ctx.writeValue();
        assertEquals(2, ctx.getCurrentIndex());
    }

    @Test
    public void testRootContextWriteValueIncrementsIndex() {
        JsonWriteContext ctx = JsonWriteContext.createRootContext(null);
        
        assertEquals(0, ctx.getCurrentIndex());
        
        ctx.writeValue();
        assertEquals(0, ctx.getCurrentIndex());
        
        ctx.writeValue();
        assertEquals(1, ctx.getCurrentIndex());
    }

    @Test
    public void testStatusConstants() {
        assertEquals(0, JsonWriteContext.STATUS_OK_AS_IS);
        assertEquals(1, JsonWriteContext.STATUS_OK_AFTER_COMMA);
        assertEquals(2, JsonWriteContext.STATUS_OK_AFTER_COLON);
        assertEquals(3, JsonWriteContext.STATUS_OK_AFTER_SPACE);
        assertEquals(4, JsonWriteContext.STATUS_EXPECT_VALUE);
        assertEquals(5, JsonWriteContext.STATUS_EXPECT_NAME);
    }

    @Test
    public void testTypeConstants() {
        assertEquals(0, TokenStreamContext.TYPE_ROOT);
        assertEquals(1, TokenStreamContext.TYPE_ARRAY);
        assertEquals(2, TokenStreamContext.TYPE_OBJECT);
    }

    @Test
    public void testWriteNameAfterReset() {
        JsonWriteContext ctx = JsonWriteContext.createRootContext(null)
                .createChildObjectContext(null);
        
        ctx.writeName("first");
        ctx.writeValue();
        
        ctx.reset(TokenStreamContext.TYPE_OBJECT, null);
        
        int status = ctx.writeName("second");
        assertEquals(JsonWriteContext.STATUS_OK_AS_IS, status);
        assertEquals("second", ctx.currentName());
        assertEquals(0, ctx.getCurrentIndex());
    }

    // New tests to improve branch coverage (without Mockito)

    @Test
    public void testCreateChildArrayContextNewInstance() {
        // Explicitly test the branch where _child is null (new instance creation)
        // This covers line 130 branch where ctxt == null is true
        JsonWriteContext root = JsonWriteContext.createRootContext(null);
        // Root has no _child initially
        assertNull(root.getDupDetector()); // just to use root
        
        JsonWriteContext child = root.createChildArrayContext("testValue");
        
        assertNotNull(child);
        assertEquals("Array", child.typeDesc());
        assertEquals("testValue", child.currentValue());
        assertEquals(0, child.getCurrentIndex());
    }

    @Test
    public void testCreateChildObjectContextNewInstance() {
        // Explicitly test the branch where _child is null (new instance creation)
        // This covers line 140 branch where ctxt == null is true
        JsonWriteContext root = JsonWriteContext.createRootContext(null);
        
        JsonWriteContext child = root.createChildObjectContext("testValue");
        
        assertNotNull(child);
        assertEquals("Object", child.typeDesc());
        assertEquals("testValue", child.currentValue());
        assertEquals(0, child.getCurrentIndex());
    }

    @Test
    public void testResetClearsGotNameFlag() {
        // Verify reset clears _gotName flag (covers reset behavior)
        JsonWriteContext ctx = JsonWriteContext.createRootContext(null)
                .createChildObjectContext(null);
        
        ctx.writeName("prop");
        assertTrue(ctx.hasCurrentName());
        
        ctx.reset(TokenStreamContext.TYPE_OBJECT, null);
        
        assertFalse(ctx.hasCurrentName());
        assertNull(ctx.currentName());
    }

    @Test
    public void testResetClearsCurrentName() {
        // Verify reset clears _currentName
        JsonWriteContext ctx = JsonWriteContext.createRootContext(null)
                .createChildObjectContext(null);
        
        ctx.writeName("prop");
        assertEquals("prop", ctx.currentName());
        
        ctx.reset(TokenStreamContext.TYPE_OBJECT, null);
        
        assertNull(ctx.currentName());
    }

    @Test
    public void testResetPreservesParent() {
        // Verify reset preserves parent link (parent is final)
        JsonWriteContext root = JsonWriteContext.createRootContext(null);
        JsonWriteContext child = root.createChildObjectContext("value");
        
        child.writeName("prop");
        child.writeValue();
        
        JsonWriteContext reset = child.reset(TokenStreamContext.TYPE_ARRAY, "newValue");
        
        assertSame(child, reset);
        assertSame(root, child.getParent());
    }

    @Test
    public void testWriteNameWithoutDetectorDoesNotThrow() {
        // Verify writeName works without detector (null _dups)
        JsonWriteContext ctx = JsonWriteContext.createRootContext(null)
                .createChildObjectContext(null);
        
        int status = ctx.writeName("property");
        
        assertEquals(JsonWriteContext.STATUS_OK_AS_IS, status);
        assertEquals("property", ctx.currentName());
    }

    @Test
    public void testWriteNameInArrayContextWithoutDetector() {
        // Verify writeName in array context returns STATUS_EXPECT_VALUE without detector
        JsonWriteContext arrayCtx = JsonWriteContext.createRootContext(null)
                .createChildArrayContext(null);
        
        int status = arrayCtx.writeName("name");
        
        assertEquals(JsonWriteContext.STATUS_EXPECT_VALUE, status);
    }

    @Test
    public void testWriteNameInRootContextWithoutDetector() {
        // Verify writeName in root context returns STATUS_EXPECT_VALUE without detector
        JsonWriteContext rootCtx = JsonWriteContext.createRootContext(null);
        
        int status = rootCtx.writeName("name");
        
        assertEquals(JsonWriteContext.STATUS_EXPECT_VALUE, status);
    }

    @Test
    public void testWriteNameAfterGotNameWithoutDetector() {
        // Verify writeName when _gotName is true returns STATUS_EXPECT_VALUE without detector
        JsonWriteContext ctx = JsonWriteContext.createRootContext(null)
                .createChildObjectContext(null);
        
        ctx.writeName("first");
        int status = ctx.writeName("second");
        
        assertEquals(JsonWriteContext.STATUS_EXPECT_VALUE, status);
        // First name should still be there
        assertEquals("first", ctx.currentName());
    }

    @Test
    public void testWithDupDetectorReturnsSelfForChaining() {
        // Verify withDupDetector returns this for method chaining
        JsonWriteContext ctx = JsonWriteContext.createRootContext(null);
        
        JsonWriteContext result = ctx.withDupDetector(null);
        
        assertSame(ctx, result);
        assertNull(ctx.getDupDetector());
    }

    @Test
    public void testCreateRootContextWithNullDetector() {
        // Test createRootContext with null detector
        JsonWriteContext root = JsonWriteContext.createRootContext(null);
        
        assertNull(root.getDupDetector());
        assertEquals("root", root.typeDesc());
    }

    @Test
    public void testResetOnChildContextWithoutDetector() {
        // Tests reset on child context without detector
        JsonWriteContext root = JsonWriteContext.createRootContext(null);
        JsonWriteContext child = root.createChildArrayContext("value");
        
        child.writeValue();
        child.reset(TokenStreamContext.TYPE_OBJECT, "newValue");
        
        assertEquals("Object", child.typeDesc());
        assertEquals("newValue", child.currentValue());
        assertEquals(0, child.getCurrentIndex());
    }

    @Test
    public void testCreateChildContextInheritsNullDetector() {
        // Tests that child contexts receive null detector when parent has null
        JsonWriteContext root = JsonWriteContext.createRootContext(null);
        JsonWriteContext child = root.createChildObjectContext("childValue");
        
        // Verify child has null detector
        assertNull(child.getDupDetector());
        
        // Verify writeName works
        child.writeName("prop");
        assertEquals("prop", child.currentName());
    }
}
