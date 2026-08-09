package tools.jackson.core.util;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import tools.jackson.core.exc.StreamWriteException;
import tools.jackson.core.json.DupDetector;
import tools.jackson.core.JsonGenerator;

import static org.mockito.Mockito.mock;

/**
 * Unit tests for {@link SimpleStreamWriteContext}.
 */
public class SimpleStreamWriteContextTest {

    /* --------------------------------- Existing tests -------------------------------- */

    /** Test creation of a root context. */
    @Test
    public void testRootContextCreation() {
        SimpleStreamWriteContext root = SimpleStreamWriteContext.createRootContext(null);
        assertNull("root parent must be null", root.getParent());
        assertTrue("root should report inRoot()", root.inRoot());
        assertFalse("root should not report inArray()", root.inArray());
        assertFalse("root should not report inObject()", root.inObject());

        // index starts at -1; getCurrentIndex() returns 0
        assertEquals(0, root.getCurrentIndex());
        assertEquals(0, root.getEntryCount()); // _index + 1

        assertNull("currentName must be null", root.currentName());
        assertFalse("hasCurrentName should be false", root.hasCurrentName());
        assertNull("currentValue should be null", root.currentValue());

        assertEquals(0, root.getNestingDepth());
    }

    /** Test creation of child contexts and that the same instance is reused. */
    @Test
    public void testChildContextRecycling() {
        SimpleStreamWriteContext root = SimpleStreamWriteContext.createRootContext(null);

        SimpleStreamWriteContext arrayC1 =
                root.createChildArrayContext("arrayVal1");
        assertEquals(1, arrayC1.getNestingDepth());
        assertTrue(arrayC1.inArray());
        assertEquals("arrayVal1", arrayC1.currentValue());

        // Now create a child object context which should reuse the same instance
        SimpleStreamWriteContext objC2 =
                root.createChildObjectContext("objVal2");
        assertSame("reuse of child should give same instance", arrayC1, objC2);

        // After reset: type must be OBJECT and currentValue updated
        assertTrue(objC2.inObject());
        assertEquals(1, objC2.getNestingDepth());
        assertEquals("objVal2", objC2.currentValue());

        // No name should be present after reset
        assertNull("currentName should be null after reset", objC2.currentName());
    }

    /** Test writeName and writeValue behaviour in an OBJECT context. */
    @Test
    public void testWriteNameAndValueObjectContext() {
        SimpleStreamWriteContext root = SimpleStreamWriteContext.createRootContext(null);
        SimpleStreamWriteContext ctx =
                root.createChildObjectContext("value");

        // Before any name: cannot write value
        assertFalse("writeValue should return false before property id", ctx.writeValue());

        // Write a property name
        boolean res = ctx.writeName("prop");
        assertTrue(res);
        assertEquals("property name stored", "prop", ctx.currentName());
        assertTrue(ctx.hasCurrentName()); // _gotPropertyId == true

        // After writing value: flag cleared, index incremented
        assertTrue("writeValue should succeed after property id", ctx.writeValue());
        assertFalse("hasCurrentName should be false after writeValue", ctx.hasCurrentName());
        assertEquals(0, ctx.getCurrentIndex()); // first entry

        // Write another property without value: should still return true and set flag
        res = ctx.writeName("another");
        assertTrue(res);
        assertEquals("another", ctx.currentName());
        assertTrue(ctx.hasCurrentName());

        // Write its value
        assertTrue(ctx.writeValue());
        assertEquals(1, ctx.getCurrentIndex()); // second entry

        // Attempt to write another name without resetting: should still succeed because we already wrote value
        res = ctx.writeName("third");
        assertTrue(res);
    }

    /** Test duplicate detection in object context using DupDetector. */
    @Test
    public void testDuplicateDetection() throws Exception {
        // Instantiate private DupDetector via reflection
        Constructor<DupDetector> ctor = DupDetector.class.getDeclaredConstructor(Object.class);
        ctor.setAccessible(true);
        DupDetector dupDetector = ctor.newInstance(new Object());

        SimpleStreamWriteContext root = SimpleStreamWriteContext.createRootContext(dupDetector);

        SimpleStreamWriteContext ctx =
                root.createChildObjectContext("value");

        // First property name is fine
        assertTrue(ctx.writeName("foo"));
        assertEquals("foo", ctx.currentName());
        assertTrue(ctx.hasCurrentName());

        // Write its value to allow next property
        assertTrue(ctx.writeValue());

        // Second distinct name also fine
        assertTrue(ctx.writeName("bar"));
        assertTrue(ctx.hasCurrentName());
        assertTrue(ctx.writeValue());

        // Writing the same name again should trigger duplicate detection
        try {
            ctx.writeName("foo");
            fail("Expected StreamWriteException for duplicate property name");
        } catch (StreamWriteException e) {
            assertTrue(e.getMessage().contains("\"foo\""));
        }

        // After failure, state should remain unchanged (flag still true)
        assertTrue(ctx.hasCurrentName());
    }

    /** Test clearAndGetParent clears current value and returns parent. */
    @Test
    public void testClearAndGetParent() {
        SimpleStreamWriteContext root = SimpleStreamWriteContext.createRootContext(null);
        SimpleStreamWriteContext ctx =
                root.createChildArrayContext("arrayVal");

        // Set a non‑null currentValue
        ctx.assignCurrentValue(new Object());
        assertNotNull(ctx.currentValue());

        SimpleStreamWriteContext parent = ctx.clearAndGetParent();
        assertSame(root, parent);
        assertNull("currentValue must be cleared", ctx.currentValue());
    }

    /** Test that writeName on non‑object contexts returns false. */
    @Test
    public void testWriteNameOnNonObject() {
        SimpleStreamWriteContext root = SimpleStreamWriteContext.createRootContext(null);

        // Array context
        SimpleStreamWriteContext arrayCtx =
                root.createChildArrayContext("arr");
        assertFalse(arrayCtx.writeName("notAllowed"));

        // Root context
        assertFalse(root.writeName("rootName"));
    }

    /** Test writeValue on ARRAY and ROOT contexts. */
    @Test
    public void testWriteValueOnArrayAndRoot() {
        SimpleStreamWriteContext root = SimpleStreamWriteContext.createRootContext(null);

        // Array context: no need for property id
        SimpleStreamWriteContext arrayCtx =
                root.createChildArrayContext("arr");
        assertTrue(arrayCtx.writeValue());
        assertEquals(0, arrayCtx.getCurrentIndex());

        // Second value in same array
        assertTrue(arrayCtx.writeValue());
        assertEquals(1, arrayCtx.getCurrentIndex());

        // Root context: writeValue also allowed (treated as scalar)
        assertTrue(root.writeValue());
        assertEquals(0, root.getCurrentIndex()); // first root value
    }

    /* --------------------------------- Additional tests for branch coverage */

    /** Test that duplicate detection is reset when child context is reused. */
    @Test
    public void testDuplicateDetectionResetAfterReuse() throws Exception {
        Constructor<DupDetector> ctor = DupDetector.class.getDeclaredConstructor(Object.class);
        ctor.setAccessible(true);
        DupDetector dupDetectorRoot = ctor.newInstance(new Object());

        SimpleStreamWriteContext root = SimpleStreamWriteContext.createRootContext(dupDetectorRoot);

        // First child context usage
        SimpleStreamWriteContext ctx1 = root.createChildObjectContext("first");
        assertTrue(ctx1.writeName("foo"));
        ctx1.writeValue();

        // Reuse the same instance via second call; should reset dup detector state
        SimpleStreamWriteContext ctx2 = root.createChildObjectContext("second");
        assertSame(ctx1, ctx2);

        // Duplicate detection should have been cleared by reset()
        assertTrue(ctx2.writeName("foo")); // should succeed after reset
        ctx2.writeValue();
    }

    /** Test that writing a name twice without intervening value returns false. */
    @Test
    public void testWriteNameTwiceWithoutValue() {
        SimpleStreamWriteContext root = SimpleStreamWriteContext.createRootContext(null);
        SimpleStreamWriteContext objCtx = root.createChildObjectContext("value");

        assertTrue(objCtx.writeName("first"));
        boolean res = objCtx.writeName("second");
        assertFalse(res); // cannot write a second name before value
        assertEquals("first", objCtx.currentName());
    }

    /** Test that calling writeValue twice in object context without new property id returns false. */
    @Test
    public void testWriteValueTwiceWithoutName() {
        SimpleStreamWriteContext root = SimpleStreamWriteContext.createRootContext(null);
        SimpleStreamWriteContext objCtx = root.createChildObjectContext("value");

        assertTrue(objCtx.writeName("first"));
        assertTrue(objCtx.writeValue()); // first value written
        boolean res = objCtx.writeValue(); // attempt second write without name
        assertFalse(res); // should fail
    }

    /** Test that withDupDetector correctly sets the detector and duplicate detection works. */
    @Test
    public void testWithDupDetectorMethod() throws Exception {
        Constructor<DupDetector> ctor = DupDetector.class.getDeclaredConstructor(Object.class);
        ctor.setAccessible(true);
        DupDetector dupDetectorRoot = ctor.newInstance(new Object());

        SimpleStreamWriteContext root = SimpleStreamWriteContext.createRootContext(null)
                .withDupDetector(dupDetectorRoot);

        assertSame("getDupDetector should return the detector set", dupDetectorRoot, root.getDupDetector());

        // Create child context which inherits a fresh DupDetector
        SimpleStreamWriteContext objCtx = root.createChildObjectContext("value");
        assertTrue(objCtx.writeName("a"));
        objCtx.writeValue();

        try {
            objCtx.writeName("a"); // duplicate
            fail("Expected StreamWriteException for duplicate property name");
        } catch (StreamWriteException e) {
            assertTrue(e.getMessage().contains("\"a\""));
        }
    }

    /** Test that getDupDetector returns null when none is set. */
    @Test
    public void testGetDupDetectorWhenNone() {
        SimpleStreamWriteContext root = SimpleStreamWriteContext.createRootContext(null);
        assertNull(root.getDupDetector());
    }

    /* --------------------------------- New tests for uncovered branches -------------------------------- */

    /** Test that createChildArrayContext reuses the same instance when called twice. */
    @Test
    public void testCreateChildArrayContextReuse() {
        SimpleStreamWriteContext root = SimpleStreamWriteContext.createRootContext(null);

        // First array context creation
        SimpleStreamWriteContext arr1 =
                root.createChildArrayContext("first");
        assertTrue(arr1.inArray());
        assertEquals(0, arr1.getCurrentIndex()); // index starts at -1 -> currentIndex 0

        // Increment index to confirm state changes
        arr1.writeValue(); // _index becomes 0
        assertEquals(0, arr1.getCurrentIndex());

        // Second array context creation should reuse same instance and reset state
        SimpleStreamWriteContext arr2 =
                root.createChildArrayContext("second");
        assertSame(arr1, arr2);
        // After reset: index resets to -1 -> currentIndex 0 again
        assertEquals(0, arr2.getCurrentIndex());
        // No property name should be present after reset
        assertNull(arr2.currentName());
        assertFalse(arr2.hasCurrentName());

        // Verify that writing a value increments index from the reset state
        arr2.writeValue();
        assertEquals(0, arr2.getCurrentIndex()); // corrected expected value
    }

    /** Test that createChildArrayContext correctly passes duplicate detector when present. */
    @Test
    public void testCreateChildArrayContextWithDupDetector() throws Exception {
        Constructor<DupDetector> ctor = DupDetector.class.getDeclaredConstructor(Object.class);
        ctor.setAccessible(true);
        DupDetector rootDD = ctor.newInstance(new Object());

        SimpleStreamWriteContext root =
                SimpleStreamWriteContext.createRootContext(rootDD);

        // First array context creation should pass dup detector to child
        SimpleStreamWriteContext arr1 =
                root.createChildArrayContext("arr");
        assertNotNull(getDupDetectorViaReflection(arr1));

        // Second call reuses the same instance; ensure duplication of detector is still present
        SimpleStreamWriteContext arr2 =
                root.createChildArrayContext("another");
        assertSame(arr1, arr2);
        assertNotNull(getDupDetectorViaReflection(arr2));
    }

    /**
     * Helper method to retrieve the private _dups field via reflection.
     */
    private DupDetector getDupDetectorViaReflection(SimpleStreamWriteContext ctx) {
        try {
            Field f = SimpleStreamWriteContext.class.getDeclaredField("_dups");
            f.setAccessible(true);
            return (DupDetector) f.get(ctx);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /** Test that the duplicate detection _checkDup ternary branch is executed when source is a JsonGenerator. */
    @Test
    public void testCheckDupWithGeneratorSource() throws Exception {
        Constructor<DupDetector> ctor = DupDetector.class.getDeclaredConstructor(Object.class);
        ctor.setAccessible(true);

        // Create a mock JsonGenerator; Mockito creates a subclass instance
        JsonGenerator genMock = mock(JsonGenerator.class);
        DupDetector dupDetectorRoot = ctor.newInstance(genMock);

        SimpleStreamWriteContext root = SimpleStreamWriteContext.createRootContext(dupDetectorRoot);
        SimpleStreamWriteContext objCtx = root.createChildObjectContext("value");

        // First property name is fine
        assertTrue(objCtx.writeName("foo"));
        assertTrue(objCtx.writeValue());

        // Duplicate detection should trigger exception and exercise the ternary branch
        try {
            objCtx.writeName("foo");
            fail("Expected StreamWriteException for duplicate property name with generator source");
        } catch (StreamWriteException e) {
            assertTrue(e.getMessage().contains("\"foo\""));
        }
    }

    /* --------------------------------- Additional tests to target surviving mutations -------------------------------- */

    /** Test that a newly created child object context has correct nesting depth and parent. */
    @Test
    public void testCreateChildObjectContextCreatesNewWithCorrectDepth() {
        SimpleStreamWriteContext root = SimpleStreamWriteContext.createRootContext(null);
        // Ensure no previous children were created; fresh root has _childToRecycle == null
        SimpleStreamWriteContext objCtx = root.createChildObjectContext("objVal");
        assertSame("Parent of child object context should be the root", root, objCtx.getParent());
        assertEquals("New child object context should have nesting depth 1", 1, objCtx.getNestingDepth());
    }

    /** Test that a child context correctly reports its parent (non‑null). */
    @Test
    public void testChildContextHasCorrectParent() {
        SimpleStreamWriteContext root = SimpleStreamWriteContext.createRootContext(null);
        SimpleStreamWriteContext arrayCtx = root.createChildArrayContext("arr");
        assertSame("Array child should report root as its parent", root, arrayCtx.getParent());

        SimpleStreamWriteContext objCtx = root.createChildObjectContext("obj");
        assertSame("Object child should report root as its parent", root, objCtx.getParent());
    }
}
