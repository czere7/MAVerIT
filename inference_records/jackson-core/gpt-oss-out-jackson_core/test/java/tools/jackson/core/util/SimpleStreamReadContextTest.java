package tools.jackson.core.util;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Field;
import java.lang.reflect.Constructor;

import org.junit.Test;

import tools.jackson.core.JsonParser;
import tools.jackson.core.exc.StreamReadException;
import tools.jackson.core.io.ContentReference;
import tools.jackson.core.json.DupDetector;
import tools.jackson.core.TokenStreamLocation;

/**
 * Unit tests for {@link SimpleStreamReadContext}.
 */
public class SimpleStreamReadContextTest {

    @Test
    public void testRootConstruction() {
        DupDetector dups = mock(DupDetector.class);
        SimpleStreamReadContext ctxt = SimpleStreamReadContext.createRootContext(10, 5, dups);

        assertNull(ctxt.getParent());
        assertTrue(ctxt.inRoot());
        assertFalse(ctxt.inObject());
        assertFalse(ctxt.inArray());

        assertEquals(0, ctxt.getNestingDepth());
        // Index is reported as 0 for a fresh context
        assertEquals(0, ctxt.getCurrentIndex());
        assertFalse(ctxt.hasCurrentName());
        assertNull(ctxt.currentValue());
    }

    @Test
    public void testChildCreationReuse() throws Exception {
        SimpleStreamReadContext root = SimpleStreamReadContext.createRootContext(1, 0, null);

        // Create array child twice and verify reuse
        SimpleStreamReadContext array1 = root.createChildArrayContext(2, 3);
        assertTrue(array1.inArray());
        assertEquals(1, array1.getNestingDepth());

        SimpleStreamReadContext array2 = root.createChildArrayContext(4, 5);
        assertSame("child context should be reused", array1, array2);
        // state should have been reset
        assertEquals(0, array2.getCurrentIndex());
        // use reflection to access private field _lineNr
        Field lineField = array2.startLocation(null).getClass().getDeclaredField("_lineNr");
        lineField.setAccessible(true);
        int line = (int) lineField.get(array2.startLocation(null));
        assertEquals(4, line);

        // Create object child twice and verify reuse
        SimpleStreamReadContext obj1 = root.createChildObjectContext(6, 7);
        assertTrue(obj1.inObject());
        assertEquals(1, obj1.getNestingDepth());

        SimpleStreamReadContext obj2 = root.createChildObjectContext(8, 9);
        assertSame("child context should be reused", obj1, obj2);
        assertEquals(0, obj2.getCurrentIndex());
    }

    @Test
    public void testValueIndexing() {
        SimpleStreamReadContext ctx = SimpleStreamReadContext.createRootContext(0, 0, null);
        // first read -> 0 (index after increment)
        assertEquals(0, ctx.valueRead());
        assertEquals(0, ctx.getCurrentIndex()); // still 0 after increment
        // second read -> 1
        assertEquals(1, ctx.valueRead());
        assertEquals(1, ctx.getCurrentIndex());
    }

    @Test
    public void testRollbackValueRead() {
        SimpleStreamReadContext ctx = SimpleStreamReadContext.createRootContext(0, 0, null);
        // first read -> 0 (index after increment)
        ctx.valueRead(); // index becomes 0
        // second read -> 1 (index becomes 1)
        ctx.valueRead();
        // rollback should return current index and decrement it
        assertEquals(0, ctx.rollbackValueRead()); // decrement to -1
        assertEquals(-1, ctx.rollbackValueRead()); // stays negative
        assertEquals(-1, ctx.rollbackValueRead()); // stays negative
    }

    @Test
    public void testClearAndGetParent() {
        SimpleStreamReadContext root = SimpleStreamReadContext.createRootContext(0, 0, null);
        SimpleStreamReadContext child = root.createChildArrayContext(1, 2);

        child.assignCurrentValue("foo");
        assertEquals("foo", child.currentValue());

        SimpleStreamReadContext parent = child.clearAndGetParent();
        assertSame(root, parent);
        // current value should have been cleared
        assertNull(child.currentValue());
    }

    @Test
    public void testSetCurrentNameDuplicationException() {
        DupDetector dups = mock(DupDetector.class);
        when(dups.isDup(anyString())).thenReturn(false).thenReturn(true); // first call false, second true
        when(dups.getSource()).thenReturn(null);

        SimpleStreamReadContext ctx = SimpleStreamReadContext.createRootContext(0, 0, dups);

        try {
            ctx.setCurrentName("foo");
            // no exception on first set
        } catch (Exception e) {
            fail("No exception expected for first property name");
        }

        try {
            ctx.setCurrentName("foo"); // duplicate should trigger
            fail("Expected StreamReadException due to duplicate property name");
        } catch (StreamReadException e) {
            assertTrue(e.getMessage().contains("Duplicate Object property \"foo\""));
        }
    }

    @Test
    public void testStartLocationLineCol() throws Exception {
        SimpleStreamReadContext ctx = SimpleStreamReadContext.createRootContext(5, 10, null);
        TokenStreamLocation loc = ctx.startLocation(null);

        // Using reflection to access private fields of TokenStreamLocation
        Field lineField = loc.getClass().getDeclaredField("_lineNr");
        Field colField = loc.getClass().getDeclaredField("_columnNr");
        Field totalField = loc.getClass().getDeclaredField("_totalChars");

        lineField.setAccessible(true);
        colField.setAccessible(true);
        totalField.setAccessible(true);

        int line = (int) lineField.get(loc);
        int col = (int) colField.get(loc);
        long total = (long) totalField.get(loc);

        assertEquals(5, line);
        assertEquals(10, col);
        // _totalChars is set to -1 in constructor
        assertEquals(-1L, total);
    }

    /* ----------------------------------------------------------------------- */
    /* Additional tests added to cover remaining branch coverage gaps             */

    /**
     * Test that {@link SimpleStreamReadContext#createRootContext(DupDetector)} works and
     * defaults line/column numbers.
     */
    @Test
    public void testCreateRootContextWithDupDetectorOnly() {
        DupDetector dups = mock(DupDetector.class);
        SimpleStreamReadContext ctx = SimpleStreamReadContext.createRootContext(dups);

        // The initial index is 0 for a fresh context (no values read yet)
        assertEquals(0, ctx.getCurrentIndex());
        assertNull(ctx.getParent());
        assertTrue(ctx.inRoot());
        assertFalse(ctx.inArray());
        assertFalse(ctx.inObject());

        // Verify that the created context has the supplied DupDetector
        assertSame(dups, ctx.getDupDetector());
    }

    /**
     * Test {@link SimpleStreamReadContext#getDupDetector()} for both null and non‑null values.
     */
    @Test
    public void testGetDupDetectorBranchCoverage() {
        // Null dup detector case
        SimpleStreamReadContext rootNull = SimpleStreamReadContext.createRootContext(0, 0, null);
        assertNull(rootNull.getDupDetector());

        // Non‑null dup detector case
        DupDetector dups = mock(DupDetector.class);
        SimpleStreamReadContext rootNotNull = SimpleStreamReadContext.createRootContext(1, 2, dups);
        assertSame(dups, rootNotNull.getDupDetector());
    }

    /**
     * Test that {@link SimpleStreamReadContext#setCurrentName(String)} correctly
     * skips duplicate detection when no DupDetector is present.
     */
    @Test
    public void testSetCurrentNameWithoutDups() {
        SimpleStreamReadContext ctx = SimpleStreamReadContext.createRootContext(0, 0, null);
        // No exception should be thrown and name set
        ctx.setCurrentName("bar");
        assertEquals("bar", ctx.currentName());
        assertTrue(ctx.hasCurrentName());
    }

    /**
     * Test that {@link SimpleStreamReadContext#currentName()} and
     * {@link SimpleStreamReadContext#hasCurrentName()} cover both branches.
     */
    @Test
    public void testHasCurrentNameAndCurrentNameBranches() {
        SimpleStreamReadContext ctx = SimpleStreamReadContext.createRootContext(0, 0, null);

        // Before setting a name
        assertNull(ctx.currentName());
        assertFalse(ctx.hasCurrentName());

        // After setting a name
        ctx.setCurrentName("baz");
        assertEquals("baz", ctx.currentName());
        assertTrue(ctx.hasCurrentName());
    }

    /**
     * Test that when a child context is reused, its {@link DupDetector}
     * receives a reset call via {@link SimpleStreamReadContext#reset(int, int, int)}.
     */
    @Test
    public void testDuplicateDetectorResetOnReusedChild() {
        // Mock parent and child DupDetectors
        DupDetector rootDups = mock(DupDetector.class);
        DupDetector childDups = mock(DupDetector.class);
        when(rootDups.child()).thenReturn(childDups);

        SimpleStreamReadContext root = SimpleStreamReadContext.createRootContext(1, 0, rootDups);

        // First creation: no reset should have occurred yet
        SimpleStreamReadContext array1 = root.createChildArrayContext(2, 3);
        assertSame("child DupDetector should be passed to child", childDups,
                array1.getDupDetector());
        verify(childDups, never()).reset();

        // Reuse the same child context: reset should be called
        SimpleStreamReadContext array2 = root.createChildArrayContext(4, 5);
        assertSame(array1, array2);
        verify(childDups, times(1)).reset();
    }

    /**
     * Test that {@link SimpleStreamReadContext#createChildObjectContext} correctly
     * creates a child context with a non‑null {@link DupDetector}
     * when the parent has one, and that the reuse path calls reset on it.
     */
    @Test
    public void testCreateChildObjectContextWithDupDetector() {
        // Root with a real (mocked) DupDetector
        DupDetector rootDups = mock(DupDetector.class);
        DupDetector childDups = mock(DupDetector.class);

        when(rootDups.child()).thenReturn(childDups);

        SimpleStreamReadContext root = SimpleStreamReadContext.createRootContext(1, 0, rootDups);

        // First creation should use the child's duplicate detector
        SimpleStreamReadContext obj1 = root.createChildObjectContext(10, 20);
        assertSame("child DupDetector should be passed to child", childDups,
                obj1.getDupDetector());
        verify(rootDups, times(1)).child();

        // Second creation reuses the same context; reset must be called on child detector
        SimpleStreamReadContext obj2 = root.createChildObjectContext(30, 40);
        assertSame(obj1, obj2);
        verify(childDups, times(1)).reset();
    }

    /**
     * Test that the true branch of {@link SimpleStreamReadContext#_checkDup} is executed
     * when {@link DupDetector#getSource()} returns a {@link JsonParser}.
     */
    @Test
    public void testCheckDupWithJsonParserSource() {
        // Mock dup detector that reports a duplicate and supplies a JsonParser as source
        DupDetector dups = mock(DupDetector.class);
        JsonParser parserMock = mock(JsonParser.class);

        when(dups.isDup(anyString())).thenReturn(true);   // always trigger duplicate
        when(dups.getSource()).thenReturn(parserMock);    // source is a JsonParser

        SimpleStreamReadContext ctx = SimpleStreamReadContext.createRootContext(0, 0, dups);
        try {
            ctx.setCurrentName("dup");
            fail("Expected StreamReadException due to duplicate property name");
        } catch (StreamReadException e) {
            // Exception is expected; the true branch of the ternary in _checkDup
            // was executed because the source is a JsonParser.
            assertNotNull(e);
        }
    }

    /**
     * Verify that {@link SimpleStreamReadContext#getParent()} returns the correct parent
     * for non‑root contexts, ensuring it does not always return null.
     */
    @Test
    public void testGetParentNonRoot() {
        SimpleStreamReadContext root = SimpleStreamReadContext.createRootContext(0, 0, null);
        SimpleStreamReadContext arrayChild = root.createChildArrayContext(1, 2);
        SimpleStreamReadContext objectChild = root.createChildObjectContext(3, 4);

        assertNotNull(arrayChild.getParent());
        assertSame(root, arrayChild.getParent());

        assertNotNull(objectChild.getParent());
        assertSame(root, objectChild.getParent());
    }

    /**
     * Test the duplicate detection logic using a real {@link DupDetector}
     * (not a mock) to confirm that duplicate property names are detected
     * and cause {@link StreamReadException} as expected.
     */
    @Test
    public void testCheckDupWithRealDuplicateDetector() throws Exception {
        // Create real DupDetector via reflection because constructor is private
        Constructor<DupDetector> ctor = DupDetector.class.getDeclaredConstructor(Object.class);
        ctor.setAccessible(true);
        DupDetector dups = ctor.newInstance((Object) null);

        SimpleStreamReadContext ctx = SimpleStreamReadContext.createRootContext(0, 0, dups);

        // First property name should not trigger exception
        try {
            ctx.setCurrentName("bar");
        } catch (Exception e) {
            fail("No exception expected for first property name");
        }

        // Duplicate should throw
        try {
            ctx.setCurrentName("bar");
            fail("Expected StreamReadException due to duplicate property name");
        } catch (StreamReadException e) {
            assertTrue(e.getMessage().contains("Duplicate Object property \"bar\""));
        }
    }
}
