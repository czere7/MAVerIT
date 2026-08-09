package tools.jackson.core.json;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;

import tools.jackson.core.JsonGenerator;
import tools.jackson.core.exc.StreamWriteException;

public class JsonWriteContextTest {

    @Test
    public void rootContextHasExpectedInitialStateAndValueHandling() {
        JsonWriteContext root = JsonWriteContext.createRootContext(null);

        assertTrue(root.inRoot());
        assertFalse(root.inArray());
        assertFalse(root.inObject());
        assertNull(root.getParent());
        assertEquals(0, root.getNestingDepth());
        assertEquals(0, root.getEntryCount());
        assertEquals(0, root.getCurrentIndex());
        assertFalse(root.hasCurrentIndex());
        assertFalse(root.hasCurrentName());
        assertNull(root.currentName());
        assertNull(root.currentValue());
        assertEquals("/", root.toString());

        root.assignCurrentValue("root-value");
        assertEquals("root-value", root.currentValue());

        assertEquals(JsonWriteContext.STATUS_OK_AS_IS, root.writeValue());
        assertEquals(1, root.getEntryCount());
        assertEquals(0, root.getCurrentIndex());
        assertTrue(root.hasCurrentIndex());

        assertEquals(JsonWriteContext.STATUS_OK_AFTER_SPACE, root.writeValue());
        assertEquals(2, root.getEntryCount());
        assertEquals(1, root.getCurrentIndex());
    }

    @Test
    public void arrayContextTracksValuesAndParent() {
        JsonWriteContext root = JsonWriteContext.createRootContext(null);
        Object arrayValue = new Object();
        JsonWriteContext array = root.createChildArrayContext(arrayValue);

        assertTrue(array.inArray());
        assertFalse(array.inRoot());
        assertFalse(array.inObject());
        assertSame(root, array.getParent());
        assertEquals(1, array.getNestingDepth());
        assertSame(arrayValue, array.currentValue());
        assertEquals(0, array.getEntryCount());
        assertEquals(0, array.getCurrentIndex());
        assertFalse(array.hasCurrentIndex());
        assertFalse(array.hasCurrentName());

        assertEquals(JsonWriteContext.STATUS_OK_AS_IS, array.writeValue());
        assertEquals(1, array.getEntryCount());
        assertEquals(0, array.getCurrentIndex());
        assertTrue(array.hasCurrentIndex());

        assertEquals(JsonWriteContext.STATUS_OK_AFTER_COMMA, array.writeValue());
        assertEquals(2, array.getEntryCount());
        assertEquals(1, array.getCurrentIndex());

        assertEquals(JsonWriteContext.STATUS_OK_AFTER_COMMA, array.writeValue());
        assertEquals(3, array.getEntryCount());
        assertEquals(2, array.getCurrentIndex());
    }

    @Test
    public void objectContextEnforcesNameValueAlternation() {
        JsonWriteContext root = JsonWriteContext.createRootContext(null);
        JsonWriteContext object = root.createChildObjectContext("object-value");

        assertTrue(object.inObject());
        assertEquals(1, object.getNestingDepth());
        assertEquals(JsonWriteContext.STATUS_EXPECT_NAME, object.writeValue());
        assertEquals(0, object.getEntryCount());

        assertEquals(JsonWriteContext.STATUS_OK_AS_IS, object.writeName("first"));
        assertEquals("first", object.currentName());
        assertTrue(object.hasCurrentName());
        assertEquals(JsonWriteContext.STATUS_EXPECT_VALUE, object.writeName("second"));

        assertEquals(JsonWriteContext.STATUS_OK_AFTER_COLON, object.writeValue());
        assertEquals(1, object.getEntryCount());
        assertEquals(0, object.getCurrentIndex());

        assertEquals(JsonWriteContext.STATUS_OK_AFTER_COMMA, object.writeName("second"));
        assertEquals("second", object.currentName());
        assertEquals(JsonWriteContext.STATUS_OK_AFTER_COLON, object.writeValue());
        assertEquals(2, object.getEntryCount());
        assertEquals(1, object.getCurrentIndex());

        assertEquals(JsonWriteContext.STATUS_OK_AFTER_COMMA, object.writeName("third"));
    }

    @Test
    public void writeNameInNonObjectContextExpectsValue() {
        JsonWriteContext root = JsonWriteContext.createRootContext(null);
        JsonWriteContext array = root.createChildArrayContext(null);

        assertEquals(JsonWriteContext.STATUS_EXPECT_VALUE, root.writeName("name"));
        assertEquals(JsonWriteContext.STATUS_EXPECT_VALUE, array.writeName("name"));
        assertNull(root.currentName());
        assertNull(array.currentName());
    }

    @Test
    public void childContextsAreReusedAndReset() {
        JsonWriteContext root = JsonWriteContext.createRootContext(null);
        JsonWriteContext child = root.createChildObjectContext("old-value");

        child.writeName("old-name");
        child.writeValue();
        assertEquals("old-name", child.currentName());
        assertEquals(1, child.getEntryCount());

        JsonWriteContext reused = root.createChildArrayContext("new-value");

        assertSame(child, reused);
        assertTrue(reused.inArray());
        assertFalse(reused.inObject());
        assertEquals(1, reused.getNestingDepth());
        assertSame(root, reused.getParent());
        assertSame("new-value", reused.currentValue());
        assertNull(reused.currentName());
        assertEquals(0, reused.getEntryCount());
        assertFalse(reused.hasCurrentIndex());
        assertEquals(JsonWriteContext.STATUS_OK_AS_IS, reused.writeValue());
    }

    @Test
    public void clearAndGetParentClearsCurrentValue() {
        JsonWriteContext root = JsonWriteContext.createRootContext(null);
        JsonWriteContext child = root.createChildObjectContext("value");

        child.writeName("name");
        assertSame(root, child.clearAndGetParent());
        assertNull(child.currentValue());
        assertEquals("name", child.currentName());

        assertNull(root.clearAndGetParent());
        assertNull(root.currentValue());
    }

    @Test
    public void duplicateNamesAreRejectedWhenDetectorReportsDuplicate() throws Exception {
        DupDetector detector = mock(DupDetector.class);
        when(detector.isDup("duplicate")).thenReturn(true);
        when(detector.getSource()).thenReturn(null);

        JsonWriteContext object = JsonWriteContext.createRootContext(null)
                .createChildObjectContext(null)
                .withDupDetector(detector);

        try {
            object.writeName("duplicate");
            throw new AssertionError("Expected duplicate property exception");
        } catch (StreamWriteException e) {
            assertTrue(e.getMessage().contains("Duplicate Object property \"duplicate\""));
        }

        verify(detector).isDup("duplicate");
    }

    @Test
    public void duplicateDetectorIsResetWhenReusingChild() {
        DupDetector detector = mock(DupDetector.class);
        DupDetector childDetector = mock(DupDetector.class);
        when(detector.child()).thenReturn(childDetector);

        JsonWriteContext root = JsonWriteContext.createRootContext(detector);
        JsonWriteContext child = root.createChildObjectContext(null);

        JsonWriteContext reused = root.createChildArrayContext(null);

        assertSame(child, reused);
        verify(childDetector).reset();
    }

    @Test
    public void currentValueCanBeReassigned() {
        JsonWriteContext context = JsonWriteContext.createRootContext(null);
        Object first = new Object();
        Object second = new Object();

        context.assignCurrentValue(first);
        assertSame(first, context.currentValue());

        context.assignCurrentValue(second);
        assertSame(second, context.currentValue());
    }

    @Test
    public void duplicateDetectorCanBeAccessedWhenAbsentOrPresent() {
        JsonWriteContext withoutDetector = JsonWriteContext.createRootContext(null);
        assertNull(withoutDetector.getDupDetector());

        DupDetector detector = mock(DupDetector.class);
        JsonWriteContext withDetector = JsonWriteContext.createRootContext(detector);
        assertSame(detector, withDetector.getDupDetector());
    }

    @Test
    public void childArrayContextCreatesChildWithDuplicateDetector() {
        DupDetector detector = mock(DupDetector.class);
        DupDetector childDetector = mock(DupDetector.class);
        when(detector.child()).thenReturn(childDetector);

        JsonWriteContext root = JsonWriteContext.createRootContext(detector);
        JsonWriteContext child = root.createChildArrayContext("array");

        assertTrue(child.inArray());
        assertSame(childDetector, child.getDupDetector());
        verify(detector).child();
    }

    @Test
    public void childObjectContextCreatesChildWithDuplicateDetector() {
        DupDetector detector = mock(DupDetector.class);
        DupDetector childDetector = mock(DupDetector.class);
        when(detector.child()).thenReturn(childDetector);

        JsonWriteContext root = JsonWriteContext.createRootContext(detector);
        JsonWriteContext child = root.createChildObjectContext("object");

        assertTrue(child.inObject());
        assertSame(childDetector, child.getDupDetector());
        verify(detector).child();
    }

    @Test
    public void reusedObjectContextResetsItsDuplicateDetector() {
        DupDetector detector = mock(DupDetector.class);
        DupDetector childDetector = mock(DupDetector.class);
        when(detector.child()).thenReturn(childDetector);

        JsonWriteContext root = JsonWriteContext.createRootContext(detector);
        JsonWriteContext child = root.createChildArrayContext("old");
        child.writeValue();

        JsonWriteContext reused = root.createChildObjectContext("new");

        assertSame(child, reused);
        assertTrue(reused.inObject());
        assertEquals(0, reused.getEntryCount());
        assertFalse(reused.hasCurrentIndex());
        verify(childDetector).reset();
    }

    @Test
    public void nonDuplicateNameUsesDetectorWithoutThrowing() throws Exception {
        DupDetector detector = mock(DupDetector.class);
        when(detector.isDup("name")).thenReturn(false);

        JsonWriteContext object = JsonWriteContext.createRootContext(null)
                .createChildObjectContext(null)
                .withDupDetector(detector);

        assertEquals(JsonWriteContext.STATUS_OK_AS_IS, object.writeName("name"));
        assertEquals("name", object.currentName());
        verify(detector).isDup("name");
    }

    @Test
    public void duplicateExceptionUsesJsonGeneratorSource() throws Exception {
        DupDetector detector = mock(DupDetector.class);
        JsonGenerator generator = mock(JsonGenerator.class);
        when(detector.isDup("duplicate")).thenReturn(true);
        when(detector.getSource()).thenReturn(generator);

        JsonWriteContext object = JsonWriteContext.createRootContext(null)
                .createChildObjectContext(null)
                .withDupDetector(detector);

        try {
            object.writeName("duplicate");
            throw new AssertionError("Expected duplicate property exception");
        } catch (StreamWriteException e) {
            assertNotNull(e);
            assertTrue(e.getMessage().contains("Duplicate Object property \"duplicate\""));
        }

        verify(detector).getSource();
    }

    @Test
    public void nonDuplicateNameDoesNotEnterDuplicateExceptionBranch() throws Exception {
        DupDetector detector = mock(DupDetector.class);
        when(detector.isDup("unique")).thenReturn(false);

        JsonWriteContext object = JsonWriteContext.createRootContext(null)
                .createChildObjectContext(null)
                .withDupDetector(detector);

        int status = object.writeName("unique");

        assertEquals(JsonWriteContext.STATUS_OK_AS_IS, status);
        assertEquals("unique", object.currentName());
        assertTrue(object.hasCurrentName());
        assertEquals(0, object.getEntryCount());
        verify(detector).isDup("unique");
    }
}
