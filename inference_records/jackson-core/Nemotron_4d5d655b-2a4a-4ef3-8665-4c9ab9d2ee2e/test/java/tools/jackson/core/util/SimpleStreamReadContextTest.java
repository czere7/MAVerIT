package tools.jackson.core.util;

import org.junit.Test;

import tools.jackson.core.*;
import tools.jackson.core.exc.StreamReadException;
import tools.jackson.core.io.ContentReference;
import tools.jackson.core.json.DupDetector;

import static org.junit.Assert.*;

public class SimpleStreamReadContextTest {

    @Test
    public void testCreateRootContext() {
        SimpleStreamReadContext root = SimpleStreamReadContext.createRootContext(1, 0, null);
        assertNotNull(root);
        assertTrue(root.inRoot());
        assertNull(root.getParent());
        assertEquals(0, root.getNestingDepth());
        assertEquals(0, root.getEntryCount());
        assertFalse(root.hasCurrentIndex());
        assertNull(root.currentName());
        assertNull(root.currentValue());
        assertEquals(1, root._lineNr);
        assertEquals(0, root._columnNr);
    }

    @Test
    public void testCreateRootContextWithLineAndColumn() {
        SimpleStreamReadContext root = SimpleStreamReadContext.createRootContext(10, 5, null);
        assertNotNull(root);
        assertEquals(10, root._lineNr);
        assertEquals(5, root._columnNr);
    }

    @Test
    public void testCreateChildArrayContext() {
        SimpleStreamReadContext root = SimpleStreamReadContext.createRootContext(1, 0, null);
        SimpleStreamReadContext child = root.createChildArrayContext(10, 5);
        
        assertNotNull(child);
        assertSame(root, child.getParent());
        assertTrue(child.inArray());
        assertEquals(1, child.getNestingDepth());
        assertEquals(10, child._lineNr);
        assertEquals(5, child._columnNr);
        assertEquals(0, child.getEntryCount());
        assertFalse(child.hasCurrentIndex());
        assertNull(child.currentName());
    }

    @Test
    public void testCreateChildObjectContext() {
        SimpleStreamReadContext root = SimpleStreamReadContext.createRootContext(1, 0, null);
        SimpleStreamReadContext child = root.createChildObjectContext(20, 15);
        
        assertNotNull(child);
        assertSame(root, child.getParent());
        assertTrue(child.inObject());
        assertEquals(1, child.getNestingDepth());
        assertEquals(20, child._lineNr);
        assertEquals(15, child._columnNr);
        assertEquals(0, child.getEntryCount());
        assertFalse(child.hasCurrentIndex());
        assertNull(child.currentName());
    }

    @Test
    public void testChildContextReuse() {
        SimpleStreamReadContext root = SimpleStreamReadContext.createRootContext(1, 0, null);
        SimpleStreamReadContext child1 = root.createChildArrayContext(10, 5);
        SimpleStreamReadContext child2 = root.createChildArrayContext(20, 10);
        
        assertSame(child1, child2);
        assertEquals(20, child2._lineNr);
        assertEquals(10, child2._columnNr);
        assertEquals(0, child2.getEntryCount());
        assertFalse(child2.hasCurrentIndex());
        assertNull(child2.currentName());
    }

    @Test
    public void testChildContextReuseForObject() {
        SimpleStreamReadContext root = SimpleStreamReadContext.createRootContext(1, 0, null);
        SimpleStreamReadContext child1 = root.createChildObjectContext(10, 5);
        SimpleStreamReadContext child2 = root.createChildObjectContext(20, 10);
        
        assertSame(child1, child2);
        assertEquals(20, child2._lineNr);
        assertEquals(10, child2._columnNr);
    }

    @Test
    public void testValueRead() {
        SimpleStreamReadContext ctx = SimpleStreamReadContext.createRootContext(1, 0, null);
        assertEquals(0, ctx.getEntryCount());
        assertFalse(ctx.hasCurrentIndex());
        
        int idx1 = ctx.valueRead();
        assertEquals(0, idx1);
        assertEquals(1, ctx.getEntryCount());
        assertEquals(0, ctx.getCurrentIndex());
        assertTrue(ctx.hasCurrentIndex());
        
        int idx2 = ctx.valueRead();
        assertEquals(1, idx2);
        assertEquals(2, ctx.getEntryCount());
        assertEquals(1, ctx.getCurrentIndex());
    }

    @Test
    public void testRollbackValueRead() {
        SimpleStreamReadContext ctx = SimpleStreamReadContext.createRootContext(1, 0, null);
        ctx.valueRead();
        ctx.valueRead();
        assertEquals(2, ctx.getEntryCount());
        assertEquals(1, ctx.getCurrentIndex());
        
        int idx1 = ctx.rollbackValueRead();
        assertEquals(0, idx1);
        assertEquals(1, ctx.getEntryCount());
        assertEquals(0, ctx.getCurrentIndex());
        
        int idx2 = ctx.rollbackValueRead();
        assertEquals(-1, idx2);
        assertEquals(0, ctx.getEntryCount());
        assertFalse(ctx.hasCurrentIndex());
        
        int idx3 = ctx.rollbackValueRead();
        assertEquals(-1, idx3);
        assertEquals(0, ctx.getEntryCount());
        assertFalse(ctx.hasCurrentIndex());
    }

    @Test
    public void testCurrentNameAndHasCurrentName() {
        SimpleStreamReadContext ctx = SimpleStreamReadContext.createRootContext(1, 0, null);
        assertNull(ctx.currentName());
        assertFalse(ctx.hasCurrentName());
        
        ctx.setCurrentName("testProperty");
        assertEquals("testProperty", ctx.currentName());
        assertTrue(ctx.hasCurrentName());
    }

    @Test
    public void testSetCurrentNameWithoutDupDetector() {
        SimpleStreamReadContext ctx = SimpleStreamReadContext.createRootContext(1, 0, null);
        ctx.setCurrentName("property1");
        assertEquals("property1", ctx.currentName());
        
        ctx.setCurrentName("property2");
        assertEquals("property2", ctx.currentName());
    }

    @Test
    public void testCurrentValueAndAssignCurrentValue() {
        SimpleStreamReadContext ctx = SimpleStreamReadContext.createRootContext(1, 0, null);
        assertNull(ctx.currentValue());
        
        Object testValue = new Object();
        ctx.assignCurrentValue(testValue);
        assertSame(testValue, ctx.currentValue());
        
        ctx.assignCurrentValue(null);
        assertNull(ctx.currentValue());
    }

    @Test
    public void testClearAndGetParent() {
        SimpleStreamReadContext root = SimpleStreamReadContext.createRootContext(1, 0, null);
        SimpleStreamReadContext child = root.createChildArrayContext(10, 5);
        child.assignCurrentValue("testValue");
        child.setCurrentName("testName");
        
        SimpleStreamReadContext parent = child.clearAndGetParent();
        assertSame(root, parent);
        assertNull(child.currentValue());
        assertEquals("testName", child.currentName());
    }

    @Test
    public void testClearAndGetParentForRoot() {
        SimpleStreamReadContext root = SimpleStreamReadContext.createRootContext(1, 0, null);
        root.assignCurrentValue("rootValue");
        
        SimpleStreamReadContext parent = root.clearAndGetParent();
        assertNull(parent);
        assertNull(root.currentValue());
    }

    @Test
    public void testGetDupDetectorNull() {
        SimpleStreamReadContext ctxNoDups = SimpleStreamReadContext.createRootContext(1, 0, null);
        assertNull(ctxNoDups.getDupDetector());
    }

    @Test
    public void testStartLocation() {
        SimpleStreamReadContext ctx = SimpleStreamReadContext.createRootContext(10, 5, null);
        TokenStreamLocation loc = ctx.startLocation(null);
        
        assertNotNull(loc);
        assertEquals(10, loc.getLineNr());
        assertEquals(5, loc.getColumnNr());
    }

    @Test
    public void testNestingDepthInheritance() {
        SimpleStreamReadContext root = SimpleStreamReadContext.createRootContext(1, 0, null);
        assertEquals(0, root.getNestingDepth());
        
        SimpleStreamReadContext child1 = root.createChildArrayContext(10, 5);
        assertEquals(1, child1.getNestingDepth());
        
        SimpleStreamReadContext child2 = child1.createChildObjectContext(20, 10);
        assertEquals(2, child2.getNestingDepth());
        
        SimpleStreamReadContext child3 = child2.createChildArrayContext(30, 15);
        assertEquals(3, child3.getNestingDepth());
    }

    @Test
    public void testNoDupDetectorInheritanceWhenRootHasNone() {
        SimpleStreamReadContext root = SimpleStreamReadContext.createRootContext(1, 0, null);
        
        SimpleStreamReadContext child1 = root.createChildArrayContext(10, 5);
        assertNull(child1.getDupDetector());
        
        SimpleStreamReadContext child2 = child1.createChildObjectContext(20, 10);
        assertNull(child2.getDupDetector());
    }

    @Test
    public void testTypeDesc() {
        SimpleStreamReadContext root = SimpleStreamReadContext.createRootContext(1, 0, null);
        assertEquals("root", root.typeDesc());
        
        SimpleStreamReadContext arrayCtx = root.createChildArrayContext(10, 5);
        assertEquals("Array", arrayCtx.typeDesc());
        
        SimpleStreamReadContext objectCtx = root.createChildObjectContext(10, 5);
        assertEquals("Object", objectCtx.typeDesc());
    }

    @Test
    public void testInArrayInObjectInRoot() {
        SimpleStreamReadContext root = SimpleStreamReadContext.createRootContext(1, 0, null);
        assertTrue(root.inRoot());
        assertFalse(root.inArray());
        assertFalse(root.inObject());
        
        SimpleStreamReadContext arrayCtx = root.createChildArrayContext(10, 5);
        assertTrue(arrayCtx.inArray());
        assertFalse(arrayCtx.inRoot());
        assertFalse(arrayCtx.inObject());
        
        SimpleStreamReadContext objectCtx = root.createChildObjectContext(10, 5);
        assertTrue(objectCtx.inObject());
        assertFalse(objectCtx.inRoot());
        assertFalse(objectCtx.inArray());
    }

    @Test
    public void testGetEntryCountAndCurrentIndex() {
        SimpleStreamReadContext ctx = SimpleStreamReadContext.createRootContext(1, 0, null);
        assertEquals(0, ctx.getEntryCount());
        assertEquals(0, ctx.getCurrentIndex());
        assertFalse(ctx.hasCurrentIndex());
        
        ctx.valueRead();
        assertEquals(1, ctx.getEntryCount());
        assertEquals(0, ctx.getCurrentIndex());
        assertTrue(ctx.hasCurrentIndex());
        
        ctx.valueRead();
        assertEquals(2, ctx.getEntryCount());
        assertEquals(1, ctx.getCurrentIndex());
    }

    @Test
    public void testHasPathSegment() {
        SimpleStreamReadContext root = SimpleStreamReadContext.createRootContext(1, 0, null);
        assertFalse(root.hasPathSegment());
        
        SimpleStreamReadContext arrayCtx = root.createChildArrayContext(10, 5);
        assertFalse(arrayCtx.hasPathSegment());
        arrayCtx.valueRead();
        assertTrue(arrayCtx.hasPathSegment());
        
        SimpleStreamReadContext objectCtx = root.createChildObjectContext(10, 5);
        assertFalse(objectCtx.hasPathSegment());
        objectCtx.setCurrentName("prop");
        assertTrue(objectCtx.hasPathSegment());
    }

    @Test
    public void testToString() {
        SimpleStreamReadContext root = SimpleStreamReadContext.createRootContext(1, 0, null);
        assertEquals("/", root.toString());
        
        SimpleStreamReadContext arrayCtx = root.createChildArrayContext(10, 5);
        arrayCtx.valueRead();
        assertEquals("[0]", arrayCtx.toString());
        
        arrayCtx.valueRead();
        assertEquals("[1]", arrayCtx.toString());
        
        SimpleStreamReadContext objectCtx = root.createChildObjectContext(10, 5);
        String objStr = objectCtx.toString();
        assertTrue(objStr.startsWith("{"));
        assertTrue(objStr.endsWith("}"));
        assertTrue(objStr.contains("?"));
        
        objectCtx.setCurrentName("testProp");
        objStr = objectCtx.toString();
        assertTrue(objStr.contains("testProp"));
        assertTrue(objStr.startsWith("{"));
        assertTrue(objStr.endsWith("}"));
    }

    @Test
    public void testReset() {
        SimpleStreamReadContext ctx = SimpleStreamReadContext.createRootContext(1, 0, null);
        ctx.valueRead();
        ctx.valueRead();
        ctx.assignCurrentValue("test");
        ctx.setCurrentName("prop");
        
        ctx.reset(TokenStreamContext.TYPE_ARRAY, 20, 10);
        
        assertTrue(ctx.inArray());
        assertEquals(20, ctx._lineNr);
        assertEquals(10, ctx._columnNr);
        assertEquals(0, ctx.getEntryCount());
        assertFalse(ctx.hasCurrentIndex());
        assertNull(ctx.currentName());
        assertNull(ctx.currentValue());
    }

    @Test
    public void testConstructorWithAllParameters() {
        SimpleStreamReadContext parent = SimpleStreamReadContext.createRootContext(1, 0, null);
        
        SimpleStreamReadContext ctx = new SimpleStreamReadContext(
            TokenStreamContext.TYPE_OBJECT, parent, 2, null, 15, 8);
        
        assertSame(parent, ctx.getParent());
        assertEquals(2, ctx.getNestingDepth());
        assertNull(ctx.getDupDetector());
        assertTrue(ctx.inObject());
        assertEquals(15, ctx._lineNr);
        assertEquals(8, ctx._columnNr);
        assertEquals(0, ctx.getEntryCount());
        assertFalse(ctx.hasCurrentIndex());
        assertNull(ctx.currentName());
        assertNull(ctx.currentValue());
    }

    @Test
    public void testGetParentReturnsCorrectType() {
        SimpleStreamReadContext root = SimpleStreamReadContext.createRootContext(1, 0, null);
        SimpleStreamReadContext child = root.createChildArrayContext(10, 5);
        
        TokenStreamContext parent = child.getParent();
        assertSame(root, parent);
        assertTrue(parent instanceof SimpleStreamReadContext);
    }

    @Test
    public void testSetCurrentNameWithNullName() {
        SimpleStreamReadContext ctx = SimpleStreamReadContext.createRootContext(1, 0, null);
        ctx.setCurrentName(null);
        assertNull(ctx.currentName());
        assertFalse(ctx.hasCurrentName());
    }

    @Test
    public void testRollbackValueReadAtZero() {
        SimpleStreamReadContext ctx = SimpleStreamReadContext.createRootContext(1, 0, null);
        assertEquals(-1, ctx.rollbackValueRead());
        assertEquals(0, ctx.getEntryCount());
        assertFalse(ctx.hasCurrentIndex());
    }
}
