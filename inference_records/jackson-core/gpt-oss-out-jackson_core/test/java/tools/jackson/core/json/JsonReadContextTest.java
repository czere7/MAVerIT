package tools.jackson.core.json;

import static org.junit.Assert.*;

import org.junit.Test;
import tools.jackson.core.TokenStreamContext;
import tools.jackson.core.exc.StreamReadException;
import tools.jackson.core.io.ContentReference;
import tools.jackson.core.json.DupDetector;
import tools.jackson.core.TokenStreamLocation;

/**
 * Unit tests for {@link JsonReadContext}.
 */
public class JsonReadContextTest {

    @Test
    public void testConstructorAndReset() {
        // create a non-root context (object) at line 5 col 3, no dup detector
        JsonReadContext ctxt = new JsonReadContext(
                null,
                0,
                null,
                TokenStreamContext.TYPE_OBJECT,
                5,
                3);

        // basic properties
        assertTrue(ctxt.inObject());
        assertEquals(0, ctxt.getNestingDepth());
        assertNull(ctxt.getParent());

        // index starts at -1 (no entries)
        assertFalse(ctxHasCurrentIndex(ctxt));

        // reset with new type and location
        JsonReadContext same = ctxt.reset(
                TokenStreamContext.TYPE_ARRAY,
                10,
                2);

        assertSame(ctxt, same);
        assertTrue(ctxt.inArray());
        assertEquals(0, ctxt.getEntryCount()); // index set to -1 => count 0
        assertEquals(-1, getIndexViaReflection(ctxt));
        // verify typeDesc changes
        String desc = ctxt.typeDesc();
        assertTrue(desc.equals("Array") || desc.equals("Object") || desc.equals("root"));
    }

    @Test
    public void testExpectCommaBehavior() {
        JsonReadContext objCtx = new JsonReadContext(
                null,
                0,
                null,
                TokenStreamContext.TYPE_OBJECT,
                1,
                1);

        // first call: should not expect comma (index becomes 0)
        assertFalse(objCtx.expectComma());
        assertEquals(0, getIndexViaReflection(objCtx));

        // second call: should expect comma
        assertTrue(objCtx.expectComma());
        assertEquals(1, getIndexViaReflection(objCtx));

        // third call: still true
        assertTrue(objCtx.expectComma());

        JsonReadContext rootCtx = new JsonReadContext(
                null,
                0,
                null,
                TokenStreamContext.TYPE_ROOT,
                1,
                1);

        // root always false
        for (int i = 0; i < 3; ++i) {
            assertFalse(rootCtx.expectComma());
        }
    }

    @Test
    public void testSetCurrentNameWithDupDetector() throws Exception {
        java.lang.reflect.Constructor<DupDetector> ctor =
                DupDetector.class.getDeclaredConstructor(Object.class);
        ctor.setAccessible(true);
        DupDetector dup = ctor.newInstance((Object) null);

        JsonReadContext ctxt = new JsonReadContext(
                null,
                0,
                dup,
                TokenStreamContext.TYPE_OBJECT,
                1,
                1);

        // first set: should succeed
        try {
            ctxt.setCurrentName("foo");
        } catch (StreamReadException e) {
            fail("Unexpected exception on first name set");
        }
        assertEquals("foo", ctxt.currentName());

        // second set with same name triggers duplicate exception
        try {
            ctxt.setCurrentName("foo");
            fail("Expected StreamReadException for duplicate property name");
        } catch (StreamReadException e) {
            assertTrue(e.getMessage().contains("Duplicate Object property \"foo\""));
        }

        // reset should clear dup detector state; after reset, same name can be set again
        ctxt.reset(TokenStreamContext.TYPE_OBJECT, 2, 3);
        try {
            ctxt.setCurrentName("foo");
        } catch (StreamReadException e) {
            fail("Duplicate check was not reset correctly");
        }
    }

    @Test
    public void testCreateChildReuse() {
        JsonReadContext parent = new JsonReadContext(
                null,
                0,
                null,
                TokenStreamContext.TYPE_ARRAY,
                1, 1);

        // first child creation
        JsonReadContext child1 = parent.createChildArrayContext(5, 7);
        assertSame(parent, child1.getParent());
        assertTrue(child1.inArray());

        // second call reuses same instance but resets state
        JsonReadContext child2 = parent.createChildArrayContext(9, 11);
        assertSame(child1, child2); // same instance reused

        // ensure type and location updated
        assertTrue(child2.inArray());
        assertEquals(9, getLineNumberViaReflection(child2));
        assertEquals(11, getColumnNumberViaReflection(child2));
    }

    @Test
    public void testClearAndGetParent() {
        JsonReadContext parent = new JsonReadContext(
                null,
                0,
                null,
                TokenStreamContext.TYPE_ROOT,
                1,
                1);
        JsonReadContext child = parent.createChildObjectContext(3, 4);

        // assign a value to child and clear
        child.assignCurrentValue("value");
        assertEquals("value", child.currentValue());

        JsonReadContext backToParent = child.clearAndGetParent();
        assertSame(parent, backToParent);
        // current value should be cleared
        assertNull(child.currentValue());
    }

    /* Helper methods for accessing protected fields via reflection */

    private boolean ctxHasCurrentIndex(JsonReadContext ctxt) {
        return getIndexViaReflection(ctxt) >= 0;
    }

    private int getIndexViaReflection(JsonReadContext ctxt) {
        try {
            java.lang.reflect.Field f = null;
            try {
                f = JsonReadContext.class.getDeclaredField("_index");
            } catch (NoSuchFieldException e) {
                // inherited from TokenStreamContext
                f = TokenStreamContext.class.getDeclaredField("_index");
            }
            f.setAccessible(true);
            return f.getInt(ctxt);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private int getLineNumberViaReflection(JsonReadContext ctxt) {
        try {
            java.lang.reflect.Field f = JsonReadContext.class.getDeclaredField("_lineNr");
            f.setAccessible(true);
            return f.getInt(ctxt);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private int getColumnNumberViaReflection(JsonReadContext ctxt) {
        try {
            java.lang.reflect.Field f = JsonReadContext.class.getDeclaredField("_columnNr");
            f.setAccessible(true);
            return f.getInt(ctxt);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /* Additional tests to increase branch coverage */

    @Test
    public void testCreateRootContextWithDupDetector() throws Exception {
        java.lang.reflect.Constructor<DupDetector> ctor =
                DupDetector.class.getDeclaredConstructor(Object.class);
        ctor.setAccessible(true);
        DupDetector dup = ctor.newInstance((Object) null);

        JsonReadContext root = JsonReadContext.createRootContext(dup);
        assertNull(root.getParent());
        assertTrue(root.inRoot());
        assertEquals("root", root.typeDesc());
        // verify that duplicate detector is set
        assertSame(dup, root.getDupDetector());
    }

    @Test
    public void testSetCurrentNameNoDupDetector() throws Exception {
        JsonReadContext ctxt = new JsonReadContext(
                null,
                0,
                null,
                TokenStreamContext.TYPE_OBJECT,
                1,
                2);
        // no dup detector, should not throw
        ctxt.setCurrentName("abc");
        assertEquals("abc", ctxt.currentName());
    }

    @Test
    public void testHasCurrentNameFalseInitially() {
        JsonReadContext ctxt = new JsonReadContext(
                null,
                0,
                null,
                TokenStreamContext.TYPE_OBJECT,
                1,
                2);
        // current name not set yet
        assertFalse(ctxt.hasCurrentName());
    }

    @Test
    public void testStartLocation() {
        JsonReadContext ctxt = new JsonReadContext(
                null,
                0,
                null,
                TokenStreamContext.TYPE_OBJECT,
                5,
                10);
        ContentReference ref = ContentReference.rawReference("dummy");
        TokenStreamLocation loc = ctxt.startLocation(ref);

        assertNotNull(loc);
        // Verify location fields via reflection
        int line = getIntField(loc, "_lineNr");
        int col = getIntField(loc, "_columnNr");
        Object contentRef = getObjectField(loc, "_contentReference");

        assertEquals(5, line);
        assertEquals(10, col);

        // Content reference should be the same as passed
        assertSame(ref, contentRef);

        // And its rawContent should be the original string "dummy"
        Object raw = getObjectField(contentRef, "_rawContent");
        assertEquals("dummy", raw);
    }

    @Test
    public void testGetDupDetector() throws Exception {
        JsonReadContext ctxt = new JsonReadContext(
                null,
                0,
                null,
                TokenStreamContext.TYPE_OBJECT,
                1,
                1);
        assertNull(ctxt.getDupDetector());

        java.lang.reflect.Constructor<DupDetector> ctor =
                DupDetector.class.getDeclaredConstructor(Object.class);
        ctor.setAccessible(true);
        DupDetector dup = ctor.newInstance((Object) null);

        ctxt.withDupDetector(dup);
        assertSame(dup, ctxt.getDupDetector());
    }

    @Test
    public void testChildContextDupDetectorPropagation() throws Exception {
        java.lang.reflect.Constructor<DupDetector> ctor =
                DupDetector.class.getDeclaredConstructor(Object.class);
        ctor.setAccessible(true);
        DupDetector parentDup = ctor.newInstance((Object) null);

        JsonReadContext parent = new JsonReadContext(
                null,
                0,
                parentDup,
                TokenStreamContext.TYPE_OBJECT,
                1,
                2);
        JsonReadContext child = parent.createChildObjectContext(3, 4);

        DupDetector childDup = getDupDetectorViaReflection(child);
        assertNotNull(childDup);
        // Child should have a duplicate detector distinct from the parent's
        assertNotSame(parentDup, childDup);
        // and should share the same source object
        assertSame(parentDup.getSource(), childDup.getSource());
    }

    /* Reflection helpers for the new tests */

    private int getIntField(Object obj, String fieldName) {
        try {
            java.lang.reflect.Field f = obj.getClass().getDeclaredField(fieldName);
            f.setAccessible(true);
            return f.getInt(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Object getObjectField(Object obj, String fieldName) {
        try {
            java.lang.reflect.Field f = obj.getClass().getDeclaredField(fieldName);
            f.setAccessible(true);
            return f.get(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private DupDetector getDupDetectorViaReflection(JsonReadContext ctxt) {
        try {
            java.lang.reflect.Field f = JsonReadContext.class.getDeclaredField("_dups");
            f.setAccessible(true);
            return (DupDetector) f.get(ctxt);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /* New tests added to cover missing branches */

    @Test
    public void testHasCurrentNameTrueAndReset() throws Exception {
        JsonReadContext ctxt = new JsonReadContext(
                null,
                0,
                null,
                TokenStreamContext.TYPE_OBJECT,
                1,
                1);
        assertFalse(ctxt.hasCurrentName());
        ctxt.setCurrentName("test");
        assertTrue(ctxt.hasCurrentName());
        assertEquals("test", ctxt.currentName());

        // reset context to clear state
        ctxt.reset(TokenStreamContext.TYPE_OBJECT, 2, 3);
        assertFalse(ctxt.hasCurrentName());
    }

    @Test
    public void testCreateChildObjectContextReuseAndReset() {
        JsonReadContext parent = new JsonReadContext(
                null,
                0,
                null,
                TokenStreamContext.TYPE_OBJECT,
                1,
                1);

        JsonReadContext child1 = parent.createChildObjectContext(3, 4);
        assertSame(parent, child1.getParent());
        assertEquals(3, getLineNumberViaReflection(child1));
        assertEquals(4, getColumnNumberViaReflection(child1));

        JsonReadContext child2 = parent.createChildObjectContext(5, 6);
        assertSame(child1, child2); // same instance reused
        assertEquals(5, getLineNumberViaReflection(child2));
        assertEquals(6, getColumnNumberViaReflection(child2));
    }

    @Test
    public void testChildContextHasCurrentName() throws Exception {
        JsonReadContext parent = new JsonReadContext(
                null,
                0,
                null,
                TokenStreamContext.TYPE_OBJECT,
                1,
                1);
        JsonReadContext child = parent.createChildObjectContext(2, 3);
        child.setCurrentName("bar");
        assertTrue(child.hasCurrentName());
    }

    /* New test for createRootContext with line and column without DupDetector */
    @Test
    public void testCreateRootContextWithoutDupDetector() {
        JsonReadContext root = new JsonReadContext(
                null,
                0,
                null,
                TokenStreamContext.TYPE_ROOT,
                4,
                7);
        assertNull(root.getParent());
        assertTrue(root.inRoot());
        assertEquals("root", root.typeDesc());

        // verify that line and column numbers are set correctly via reflection
        int line = getIntField(root, "_lineNr");
        int col = getIntField(root, "_columnNr");
        assertEquals(4, line);
        assertEquals(7, col);

        // No duplicate detector should be present
        assertNull(root.getDupDetector());
    }

    /* Additional tests to cover static factory methods and nesting depth */

    @Test
    public void testCreateRootContextWithLineAndColAndDupDetector() throws Exception {
        java.lang.reflect.Constructor<DupDetector> ctor =
                DupDetector.class.getDeclaredConstructor(Object.class);
        ctor.setAccessible(true);
        DupDetector dup = ctor.newInstance((Object) null);

        JsonReadContext root = JsonReadContext.createRootContext(10, 20, dup);
        assertNotNull(root);
        assertTrue(root.inRoot());
        assertSame(dup, root.getDupDetector());

        // verify line and column via reflection
        int line = getIntField(root, "_lineNr");
        int col = getIntField(root, "_columnNr");
        assertEquals(10, line);
        assertEquals(20, col);
    }

    @Test
    public void testChildNestingDepthAfterArrayCreation() {
        JsonReadContext parent = new JsonReadContext(
                null,
                0,
                null,
                TokenStreamContext.TYPE_ARRAY,
                1, 1);

        JsonReadContext child = parent.createChildArrayContext(5, 7);
        assertEquals(parent.getNestingDepth() + 1, child.getNestingDepth());
    }

    @Test
    public void testChildNestingDepthAfterObjectCreation() {
        JsonReadContext parent = new JsonReadContext(
                null,
                0,
                null,
                TokenStreamContext.TYPE_OBJECT,
                2, 3);

        JsonReadContext child = parent.createChildObjectContext(6, 8);
        assertEquals(parent.getNestingDepth() + 1, child.getNestingDepth());
    }

    @Test
    public void testWithDupDetectorReturnsSameInstance() throws Exception {
        java.lang.reflect.Constructor<DupDetector> ctor =
                DupDetector.class.getDeclaredConstructor(Object.class);
        ctor.setAccessible(true);
        DupDetector dup = ctor.newInstance((Object) null);

        JsonReadContext ctxt = new JsonReadContext(
                null,
                0,
                null,
                TokenStreamContext.TYPE_OBJECT,
                1,
                2);

        JsonReadContext returned = ctxt.withDupDetector(dup);
        assertSame(ctxt, returned);
        assertSame(dup, returned.getDupDetector());
    }

    /* New test to verify independent duplicate detection in child contexts */
    @Test
    public void testIndependentDuplicateDetectionInChildContext() throws Exception {
        java.lang.reflect.Constructor<DupDetector> ctor =
                DupDetector.class.getDeclaredConstructor(Object.class);
        ctor.setAccessible(true);
        DupDetector parentDup = ctor.newInstance((Object) null);

        JsonReadContext parent = new JsonReadContext(
                null, 0, parentDup,
                TokenStreamContext.TYPE_OBJECT, 1, 1);

        // First set in parent
        parent.setCurrentName("foo");

        JsonReadContext child = parent.createChildObjectContext(2, 3);

        // Child should accept the same name without duplicate error
        try {
            child.setCurrentName("foo");
        } catch (StreamReadException e) {
            fail("Unexpected exception on child set of first name");
        }

        // Second set with same name in child triggers duplicate exception
        try {
            child.setCurrentName("foo");
            fail("Expected StreamReadException for duplicate property name in child");
        } catch (StreamReadException e) {
            assertTrue(e.getMessage().contains("Duplicate Object property \"foo\""));
        }

        // Reset child and verify that it can accept the same name again
        child.reset(TokenStreamContext.TYPE_OBJECT, 4, 5);
        try {
            child.setCurrentName("foo");
        } catch (StreamReadException e) {
            fail("Unexpected exception after resetting child context");
        }

        // Parent duplicate should still throw as before
        try {
            parent.setCurrentName("foo");
            fail("Expected StreamReadException for duplicate property name in parent");
        } catch (StreamReadException e) {
            assertTrue(e.getMessage().contains("Duplicate Object property \"foo\""));
        }
    }
}
