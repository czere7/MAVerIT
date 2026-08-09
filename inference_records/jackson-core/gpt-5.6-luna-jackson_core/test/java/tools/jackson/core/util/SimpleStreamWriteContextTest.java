package tools.jackson.core.util;

import org.junit.Test;

import tools.jackson.core.JsonGenerator;
import tools.jackson.core.exc.StreamWriteException;
import tools.jackson.core.json.DupDetector;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class SimpleStreamWriteContextTest {

    @Test
    public void rootContextStartsWithExpectedState() {
        SimpleStreamWriteContext context =
                SimpleStreamWriteContext.createRootContext(null);

        assertTrue(context.inRoot());
        assertFalse(context.inArray());
        assertFalse(context.inObject());
        assertNull(context.getParent());
        assertEquals(0, context.getNestingDepth());
        assertEquals(0, context.getEntryCount());
        assertEquals(0, context.getCurrentIndex());
        assertFalse(context.hasCurrentIndex());
        assertFalse(context.hasPathSegment());
        assertNull(context.currentName());
        assertFalse(context.hasCurrentName());
        assertNull(context.currentValue());
        assertEquals("/", context.toString());
    }

    @Test
    public void rootAndArrayContextsAcceptValuesAndAdvanceIndexes() {
        SimpleStreamWriteContext root =
                SimpleStreamWriteContext.createRootContext(null);

        assertTrue(root.writeValue());
        assertEquals(1, root.getEntryCount());
        assertEquals(0, root.getCurrentIndex());
        assertTrue(root.hasCurrentIndex());

        SimpleStreamWriteContext array = root.createChildArrayContext("array");
        assertSame(root, array.getParent());
        assertTrue(array.inArray());
        assertEquals(1, array.getNestingDepth());
        assertEquals("array", array.currentValue());
        assertFalse(array.hasPathSegment());

        assertTrue(array.writeValue());
        assertEquals(1, array.getEntryCount());
        assertEquals(0, array.getCurrentIndex());
        assertTrue(array.hasPathSegment());
        assertEquals("[0]", array.toString());

        assertTrue(array.writeValue());
        assertEquals(2, array.getEntryCount());
        assertEquals(1, array.getCurrentIndex());
        assertEquals("[1]", array.toString());

        assertFalse(array.writeName("not-a-property"));
    }

    @Test
    public void objectRequiresPropertyNameBeforeValue() throws Exception {
        SimpleStreamWriteContext object =
                SimpleStreamWriteContext.createRootContext(null)
                        .createChildObjectContext(null);

        assertTrue(object.inObject());
        assertFalse(object.writeValue());
        assertEquals(0, object.getEntryCount());
        assertFalse(object.hasCurrentName());

        assertTrue(object.writeName("first"));
        assertTrue(object.hasCurrentName());
        assertEquals("first", object.currentName());
        assertEquals("{\"first\"}", object.toString());

        assertFalse(object.writeName("second"));
        assertTrue(object.writeValue());
        assertFalse(object.hasCurrentName());
        assertEquals("first", object.currentName());
        assertEquals(1, object.getEntryCount());
        assertEquals(0, object.getCurrentIndex());

        assertTrue(object.writeName("second"));
        assertTrue(object.writeValue());
        assertEquals(2, object.getEntryCount());
        assertEquals(1, object.getCurrentIndex());
        assertEquals("second", object.currentName());
    }

    @Test
    public void writeNameIsRejectedOutsideObjectContext() throws Exception {
        SimpleStreamWriteContext root =
                SimpleStreamWriteContext.createRootContext(null);
        SimpleStreamWriteContext array = root.createChildArrayContext(null);

        assertFalse(root.writeName("name"));
        assertFalse(array.writeName("name"));
        assertNull(root.currentName());
        assertNull(array.currentName());
    }

    @Test
    public void duplicateNamesAreRejectedWhenDetectorReportsDuplicate() throws Exception {
        DupDetector detector = mock(DupDetector.class);
        when(detector.child()).thenReturn(detector);
        when(detector.isDup("name")).thenReturn(false, true);

        SimpleStreamWriteContext object =
                SimpleStreamWriteContext.createRootContext(detector)
                        .createChildObjectContext(null);

        assertTrue(object.writeName("name"));
        assertTrue(object.writeValue());

        try {
            object.writeName("name");
            fail("Expected duplicate property exception");
        } catch (StreamWriteException e) {
            assertTrue(e.getMessage(),
                    e.getMessage().startsWith("Duplicate Object property \"name\""));
        }

        assertEquals("name", object.currentName());
        assertTrue(object.hasCurrentName());
    }

    @Test
    public void nonDuplicateDetectorResultAllowsNameAndDoesNotThrow() throws Exception {
        DupDetector detector = mock(DupDetector.class);
        when(detector.child()).thenReturn(detector);
        when(detector.isDup("unique")).thenReturn(false);

        SimpleStreamWriteContext object =
                SimpleStreamWriteContext.createRootContext(detector)
                        .createChildObjectContext(null);

        assertTrue(object.writeName("unique"));
        assertEquals("unique", object.currentName());
        assertTrue(object.hasCurrentName());
        assertEquals("{\"unique\"}", object.toString());
        verify(detector).isDup("unique");
    }

    @Test
    public void recycledChildIsResetAndDetectorIsReset() {
        DupDetector rootDetector = mock(DupDetector.class);
        DupDetector childDetector = mock(DupDetector.class);
        when(rootDetector.child()).thenReturn(childDetector);

        SimpleStreamWriteContext root =
                SimpleStreamWriteContext.createRootContext(rootDetector);

        SimpleStreamWriteContext first = root.createChildArrayContext("first");
        assertSame(childDetector, first.getDupDetector());
        assertTrue(first.inArray());
        assertEquals("first", first.currentValue());
        assertTrue(first.writeValue());

        SimpleStreamWriteContext reused = root.createChildObjectContext("second");

        assertSame(first, reused);
        assertTrue(reused.inObject());
        assertFalse(reused.inArray());
        assertEquals("second", reused.currentValue());
        assertEquals(0, reused.getEntryCount());
        assertFalse(reused.hasCurrentName());
        assertNull(reused.currentName());
        verify(childDetector).reset();
    }

    @Test
    public void currentValueCanBeAssignedAndClearedWhenClosing() {
        SimpleStreamWriteContext root =
                SimpleStreamWriteContext.createRootContext(null);
        SimpleStreamWriteContext child = root.createChildArrayContext("initial");

        child.assignCurrentValue("assigned");
        assertEquals("assigned", child.currentValue());

        assertSame(root, child.clearAndGetParent());
        assertNull(child.currentValue());
    }

    @Test
    public void withDupDetectorReplacesDetectorAndReturnsSameContext() {
        SimpleStreamWriteContext context =
                SimpleStreamWriteContext.createRootContext(null);
        DupDetector detector = mock(DupDetector.class);

        assertSame(context, context.withDupDetector(detector));
        assertSame(detector, context.getDupDetector());
    }

    @Test
    public void nullPropertyNameIsStoredWhilePropertyIsMarkedPending() throws Exception {
        SimpleStreamWriteContext object =
                SimpleStreamWriteContext.createRootContext(null)
                        .createChildObjectContext(null);

        assertTrue(object.writeName(null));
        assertTrue(object.hasCurrentName());
        assertNull(object.currentName());
        assertTrue(object.writeValue());
        assertFalse(object.hasCurrentName());
    }

    @Test
    public void recycledArrayChildIsResetWhenNoDuplicateDetectorExists() {
        SimpleStreamWriteContext root =
                SimpleStreamWriteContext.createRootContext(null);

        SimpleStreamWriteContext first = root.createChildArrayContext("first");
        assertTrue(first.writeValue());
        assertEquals(1, first.getEntryCount());

        SimpleStreamWriteContext reused = root.createChildArrayContext("second");

        assertSame(first, reused);
        assertTrue(reused.inArray());
        assertFalse(reused.inObject());
        assertEquals("second", reused.currentValue());
        assertEquals(0, reused.getEntryCount());
        assertEquals(0, reused.getCurrentIndex());
        assertFalse(reused.hasCurrentIndex());
        assertFalse(reused.hasPathSegment());
        assertNull(reused.getDupDetector());
    }

    @Test
    public void duplicateExceptionUsesJsonGeneratorSourceWhenDetectorReportsDuplicate()
            throws Exception {
        JsonGenerator generator = mock(JsonGenerator.class);
        DupDetector detector = mock(DupDetector.class);

        when(detector.child()).thenReturn(detector);
        when(detector.isDup("name")).thenReturn(false, true);
        when(detector.getSource()).thenReturn(generator);

        SimpleStreamWriteContext object =
                SimpleStreamWriteContext.createRootContext(detector)
                        .createChildObjectContext(null);

        assertTrue(object.writeName("name"));
        assertTrue(object.writeValue());

        try {
            object.writeName("name");
            fail("Expected duplicate property exception");
        } catch (StreamWriteException e) {
            assertTrue(e.getMessage(),
                    e.getMessage().startsWith("Duplicate Object property \"name\""));
        }
    }

    @Test
    public void nestedChildContextsHaveIncreasingNestingDepth() {
        SimpleStreamWriteContext root =
                SimpleStreamWriteContext.createRootContext(null);

        SimpleStreamWriteContext object = root.createChildObjectContext("object");
        assertEquals(1, object.getNestingDepth());
        assertSame(root, object.getParent());

        SimpleStreamWriteContext array = object.createChildArrayContext("array");
        assertEquals(2, array.getNestingDepth());
        assertSame(object, array.getParent());

        SimpleStreamWriteContext nestedObject =
                array.createChildObjectContext("nestedObject");
        assertEquals(3, nestedObject.getNestingDepth());
        assertSame(array, nestedObject.getParent());
    }

    @Test
    public void nonDuplicateNameIsAcceptedAndDuplicateNameIsRejectedAfterward()
            throws Exception {
        DupDetector detector = mock(DupDetector.class);
        when(detector.child()).thenReturn(detector);
        when(detector.isDup("first")).thenReturn(false);
        when(detector.isDup("second")).thenReturn(false, true);

        SimpleStreamWriteContext object =
                SimpleStreamWriteContext.createRootContext(detector)
                        .createChildObjectContext(null);

        assertTrue(object.writeName("first"));
        assertTrue(object.writeValue());

        assertTrue(object.writeName("second"));
        assertEquals("second", object.currentName());
        assertTrue(object.writeValue());

        try {
            object.writeName("second");
            fail("Expected duplicate property exception");
        } catch (StreamWriteException e) {
            assertTrue(e.getMessage(),
                    e.getMessage().startsWith("Duplicate Object property \"second\""));
        }

        verify(detector).isDup("first");
        verify(detector, times(2)).isDup("second");
    }
}
