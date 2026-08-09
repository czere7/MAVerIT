package tools.jackson.core.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;

import tools.jackson.core.JsonParser;
import tools.jackson.core.TokenStreamContext;
import tools.jackson.core.TokenStreamLocation;
import tools.jackson.core.exc.StreamReadException;
import tools.jackson.core.json.DupDetector;

public class SimpleStreamReadContextTest {

    @Test
    public void rootContextHasExpectedInitialState() {
        SimpleStreamReadContext root =
                SimpleStreamReadContext.createRootContext(3, 7, null);

        assertTrue(root.inRoot());
        assertFalse(root.inArray());
        assertFalse(root.inObject());
        assertNull(root.getParent());
        assertEquals(0, root.getNestingDepth());
        assertEquals(0, root.getEntryCount());
        assertEquals(0, root.getCurrentIndex());
        assertFalse(root.hasCurrentIndex());
        assertFalse(root.hasPathSegment());
        assertNull(root.currentName());
        assertFalse(root.hasCurrentName());
        assertNull(root.currentValue());
        assertEquals("/", root.toString());
        assertEquals("root", root.typeDesc());
    }

    @Test
    public void defaultRootFactoryUsesStandardLocation() {
        SimpleStreamReadContext root =
                SimpleStreamReadContext.createRootContext(null);

        TokenStreamLocation location = root.startLocation(null);

        assertNotNull(location);
        assertTrue(root.inRoot());
        assertEquals(0, root.getNestingDepth());
    }

    @Test
    public void valueReadAdvancesAndRollbackDoesNotGoBelowInitialState() {
        SimpleStreamReadContext root =
                SimpleStreamReadContext.createRootContext(null);

        assertEquals(0, root.valueRead());
        assertEquals(1, root.valueRead());
        assertEquals(2, root.getEntryCount());
        assertEquals(1, root.getCurrentIndex());
        assertTrue(root.hasCurrentIndex());

        assertEquals(0, root.rollbackValueRead());
        assertEquals(-1, root.rollbackValueRead());
        assertEquals(-1, root.rollbackValueRead());
        assertEquals(0, root.getEntryCount());
        assertEquals(0, root.getCurrentIndex());
        assertFalse(root.hasCurrentIndex());
    }

    @Test
    public void childContextsHaveCorrectTypeParentAndDepth() {
        SimpleStreamReadContext root =
                SimpleStreamReadContext.createRootContext(null);

        SimpleStreamReadContext array = root.createChildArrayContext(10, 4);
        SimpleStreamReadContext object = array.createChildObjectContext(11, 2);

        assertTrue(array.inArray());
        assertFalse(array.inRoot());
        assertEquals(root, array.getParent());
        assertEquals(1, array.getNestingDepth());
        assertEquals("Array", array.typeDesc());
        assertEquals("[0]", array.toString());

        assertTrue(object.inObject());
        assertFalse(object.inArray());
        assertEquals(array, object.getParent());
        assertEquals(2, object.getNestingDepth());
        assertEquals("Object", object.typeDesc());
        assertEquals("{?}", object.toString());
        assertFalse(object.hasPathSegment());
    }

    @Test
    public void currentValueCanBeAssignedAndClearedWithParent() {
        SimpleStreamReadContext root =
                SimpleStreamReadContext.createRootContext(null);
        SimpleStreamReadContext child = root.createChildObjectContext(1, 0);
        Object value = new Object();

        child.assignCurrentValue(value);
        assertSame(value, child.currentValue());

        assertSame(root, child.clearAndGetParent());
        assertNull(child.currentValue());
    }

    @Test
    public void currentNameControlsObjectPathStateAndRepresentation() throws Exception {
        SimpleStreamReadContext object =
                SimpleStreamReadContext.createRootContext(null)
                        .createChildObjectContext(2, 3);

        assertFalse(object.hasCurrentName());
        object.setCurrentName("a\"b");

        assertEquals("a\"b", object.currentName());
        assertTrue(object.hasCurrentName());
        assertTrue(object.hasPathSegment());
        assertEquals("{\"a\\\"b\"}", object.toString());

        object.clearAndGetParent();
        assertEquals("a\"b", object.currentName());
    }

    @Test
    public void childContextIsReusedAndReset() throws Exception {
        DupDetector rootDups = mock(DupDetector.class);
        DupDetector childDups = mock(DupDetector.class);
        when(rootDups.child()).thenReturn(childDups);

        SimpleStreamReadContext root =
                SimpleStreamReadContext.createRootContext(rootDups);
        SimpleStreamReadContext child = root.createChildArrayContext(5, 6);

        child.valueRead();
        child.setCurrentName("old");
        child.assignCurrentValue("retained");

        SimpleStreamReadContext reused = root.createChildObjectContext(8, 9);

        assertSame(child, reused);
        assertTrue(reused.inObject());
        assertEquals(1, reused.getNestingDepth());
        assertEquals(0, reused.getEntryCount());
        assertFalse(reused.hasCurrentIndex());
        assertNull(reused.currentName());
        assertNull(reused.currentValue());
        assertSame(root, reused.getParent());
        verify(childDups).reset();
    }

    @Test
    public void duplicateNameRaisesStreamReadException() throws Exception {
        DupDetector detector = mock(DupDetector.class);
        when(detector.isDup("name")).thenReturn(true);

        SimpleStreamReadContext object = new SimpleStreamReadContext(
                TokenStreamContext.TYPE_OBJECT, null, 1, detector, 1, 0);

        try {
            object.setCurrentName("name");
        } catch (StreamReadException e) {
            assertTrue(e.getMessage().startsWith(
                    "Duplicate Object property \"name\""));
            return;
        }

        throw new AssertionError("Expected StreamReadException");
    }

    @Test
    public void duplicateDetectorIsExposed() {
        DupDetector detector = mock(DupDetector.class);
        SimpleStreamReadContext root =
                SimpleStreamReadContext.createRootContext(1, 0, detector);

        assertSame(detector, root.getDupDetector());
    }

    @Test
    public void nullDuplicateDetectorAllowsRepeatedNames() throws Exception {
        SimpleStreamReadContext object =
                SimpleStreamReadContext.createRootContext(null)
                        .createChildObjectContext(1, 0);

        object.setCurrentName("same");
        object.setCurrentName("same");

        assertEquals("same", object.currentName());
    }

    @Test
    public void objectContextCanBeCreatedFreshWithoutDuplicateDetector() {
        SimpleStreamReadContext root =
                SimpleStreamReadContext.createRootContext(null);

        SimpleStreamReadContext object = root.createChildObjectContext(12, 13);

        assertTrue(object.inObject());
        assertSame(root, object.getParent());
        assertEquals(1, object.getNestingDepth());
        assertNull(object.getDupDetector());
        assertEquals("{?}", object.toString());
    }

    @Test
    public void arrayContextReuseResetsStateWithoutDuplicateDetector() throws Exception {
        SimpleStreamReadContext root =
                SimpleStreamReadContext.createRootContext(null);
        SimpleStreamReadContext first = root.createChildArrayContext(1, 2);

        first.valueRead();
        first.assignCurrentValue("value");
        first.setCurrentName("name");

        SimpleStreamReadContext reused = root.createChildArrayContext(3, 4);

        assertSame(first, reused);
        assertTrue(reused.inArray());
        assertEquals(0, reused.getEntryCount());
        assertFalse(reused.hasCurrentIndex());
        assertNull(reused.currentName());
        assertNull(reused.currentValue());
        assertNull(reused.getDupDetector());
    }

    @Test
    public void freshObjectContextCreatesChildDuplicateDetector() {
        DupDetector rootDups = mock(DupDetector.class);
        DupDetector childDups = mock(DupDetector.class);
        when(rootDups.child()).thenReturn(childDups);

        SimpleStreamReadContext root =
                SimpleStreamReadContext.createRootContext(rootDups);

        SimpleStreamReadContext object = root.createChildObjectContext(5, 6);

        assertTrue(object.inObject());
        assertSame(childDups, object.getDupDetector());
        verify(rootDups).child();
    }

    @Test
    public void duplicateNameFromParserSourceUsesParserInException() throws Exception {
        DupDetector detector = mock(DupDetector.class);
        JsonParser parser = mock(JsonParser.class);
        when(detector.isDup("name")).thenReturn(true);
        when(detector.getSource()).thenReturn(parser);

        SimpleStreamReadContext object = new SimpleStreamReadContext(
                TokenStreamContext.TYPE_OBJECT, null, 1, detector, 1, 0);

        try {
            object.setCurrentName("name");
        } catch (StreamReadException e) {
            assertTrue(e.getMessage().startsWith(
                    "Duplicate Object property \"name\""));
            return;
        }

        throw new AssertionError("Expected StreamReadException");
    }

    @Test
    public void nonDuplicateNameWithDetectorIsAccepted() throws Exception {
        DupDetector detector = mock(DupDetector.class);
        when(detector.isDup("first")).thenReturn(false);

        SimpleStreamReadContext object = new SimpleStreamReadContext(
                TokenStreamContext.TYPE_OBJECT, null, 1, detector, 1, 0);

        object.setCurrentName("first");

        assertEquals("first", object.currentName());
        assertTrue(object.hasCurrentName());
        assertTrue(object.hasPathSegment());
        verify(detector).isDup("first");
    }
}
