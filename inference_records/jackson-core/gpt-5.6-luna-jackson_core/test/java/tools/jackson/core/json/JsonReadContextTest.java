package tools.jackson.core.json;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mockito;

import tools.jackson.core.JsonParser;
import tools.jackson.core.TokenStreamContext;
import tools.jackson.core.TokenStreamLocation;
import tools.jackson.core.exc.StreamReadException;
import tools.jackson.core.io.ContentReference;

public class JsonReadContextTest {

    @Test
    public void rootContextHasExpectedInitialState() {
        JsonReadContext context = JsonReadContext.createRootContext(null);

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
        assertEquals("root", context.typeDesc());
        assertEquals("/", context.toString());
    }

    @Test
    public void factoryOverloadUsesDefaultRootLocation() {
        JsonReadContext context = JsonReadContext.createRootContext(null);

        TokenStreamLocation location = context.startLocation(
                ContentReference.rawReference("source"));

        assertNotNull(location);
    }

    @Test
    public void childContextsHaveCorrectTypeParentAndDepth() {
        JsonReadContext root = JsonReadContext.createRootContext(3, 4, null);

        JsonReadContext array = root.createChildArrayContext(5, 6);
        JsonReadContext object = array.createChildObjectContext(7, 8);

        assertSame(root, array.getParent());
        assertSame(array, object.getParent());
        assertTrue(array.inArray());
        assertFalse(array.inObject());
        assertTrue(object.inObject());
        assertEquals(1, array.getNestingDepth());
        assertEquals(2, object.getNestingDepth());
        assertEquals("Array", array.typeDesc());
        assertEquals("Object", object.typeDesc());
        assertEquals("[0]", array.toString());
        assertEquals("{?}", object.toString());
    }

    @Test
    public void childContextIsReusedAndResetWhenCreatedAgain() throws Exception {
        JsonReadContext root = JsonReadContext.createRootContext(null);
        JsonReadContext child = root.createChildObjectContext(1, 2);

        child.setCurrentName("name");
        child.assignCurrentValue("value");
        assertFalse(child.expectComma());

        JsonReadContext reused = root.createChildArrayContext(10, 20);

        assertSame(child, reused);
        assertTrue(reused.inArray());
        assertFalse(reused.inObject());
        assertEquals(0, reused.getEntryCount());
        assertFalse(reused.hasCurrentIndex());
        assertNull(reused.currentName());
        assertNull(reused.currentValue());
        assertEquals("[0]", reused.toString());
    }

    @Test
    public void resetChangesStateAndClearsCurrentValues() {
        JsonReadContext context = JsonReadContext.createRootContext(null)
                .createChildArrayContext(1, 2);

        context.assignCurrentValue("value");
        context.expectComma();
        context.expectComma();

        JsonReadContext result = context.reset(TokenStreamContext.TYPE_OBJECT, 9, 10);

        assertSame(context, result);
        assertTrue(context.inObject());
        assertEquals(0, context.getEntryCount());
        assertEquals(0, context.getCurrentIndex());
        assertFalse(context.hasCurrentIndex());
        assertNull(context.currentName());
        assertNull(context.currentValue());
    }

    @Test
    public void expectCommaAdvancesIndexAndDependsOnContextType() {
        JsonReadContext root = JsonReadContext.createRootContext(null);

        assertFalse(root.expectComma());
        assertFalse(root.expectComma());
        assertEquals(2, root.getEntryCount());
        assertEquals(1, root.getCurrentIndex());

        JsonReadContext array = JsonReadContext.createRootContext(null)
                .createChildArrayContext(1, 1);

        assertFalse(array.expectComma());
        assertTrue(array.expectComma());
        assertTrue(array.expectComma());
        assertEquals(3, array.getEntryCount());
        assertEquals(2, array.getCurrentIndex());
        assertTrue(array.hasCurrentIndex());
    }

    @Test
    public void currentNameAndCurrentValueCanBeAssignedAndCleared() throws Exception {
        JsonReadContext root = JsonReadContext.createRootContext(null);
        JsonReadContext context = root.createChildObjectContext(1, 1);

        context.setCurrentName("property");
        context.assignCurrentValue(Integer.valueOf(42));

        assertEquals("property", context.currentName());
        assertTrue(context.hasCurrentName());
        assertEquals(Integer.valueOf(42), context.currentValue());
        assertTrue(context.hasPathSegment());

        JsonReadContext parent = context.clearAndGetParent();

        assertSame(root, parent);
        assertNull(context.currentValue());
        assertEquals("property", context.currentName());
    }

    @Test
    public void duplicateDetectorIsInheritedByChildrenAndCanBeReplaced() {
        DupDetector detector = DupDetector.rootDetector((JsonParser) null);
        JsonReadContext root = JsonReadContext.createRootContext(detector);

        JsonReadContext child = root.createChildObjectContext(1, 1);

        assertNotNull(root.getDupDetector());
        assertNotNull(child.getDupDetector());
        assertNotSame(detector, child.getDupDetector());
        assertSame(child, child.withDupDetector(detector));
        assertSame(detector, child.getDupDetector());
    }

    @Test
    public void duplicateNamesAreRejectedWhenDetectionIsEnabled() throws Exception {
        DupDetector detector = DupDetector.rootDetector((JsonParser) null);
        JsonReadContext context = JsonReadContext.createRootContext(detector)
                .createChildObjectContext(1, 1);

        context.setCurrentName("first");

        try {
            context.setCurrentName("first");
            fail("Expected duplicate property name to be rejected");
        } catch (StreamReadException e) {
            assertTrue(e.getMessage().startsWith(
                    "Duplicate Object property \"first\""));
        }

        assertEquals("first", context.currentName());
    }

    @Test
    public void differentNamesAreAcceptedAndResetAllowsPreviouslySeenName()
            throws Exception {
        DupDetector detector = DupDetector.rootDetector((JsonParser) null);
        JsonReadContext context = JsonReadContext.createRootContext(detector)
                .createChildObjectContext(1, 1);

        context.setCurrentName("first");
        context.setCurrentName("second");
        context.reset(TokenStreamContext.TYPE_OBJECT, 2, 2);
        context.setCurrentName("first");

        assertEquals("first", context.currentName());
    }

    @Test
    public void objectPathSegmentDependsOnCurrentName() throws Exception {
        JsonReadContext object = JsonReadContext.createRootContext(null)
                .createChildObjectContext(1, 1);

        assertFalse(object.hasPathSegment());

        object.setCurrentName("a/b");

        assertTrue(object.hasPathSegment());
        assertEquals("{\"a\\/b\"}", object.toString());
    }

    @Test
    public void arrayPathSegmentDependsOnHavingAnEntry() {
        JsonReadContext array = JsonReadContext.createRootContext(null)
                .createChildArrayContext(1, 1);

        assertFalse(array.hasPathSegment());

        array.expectComma();

        assertTrue(array.hasPathSegment());
        assertEquals("[0]", array.toString());
    }

    @Test
    public void startLocationIsAvailableForConfiguredCoordinates() {
        JsonReadContext context = new JsonReadContext(
                null, 0, null, TokenStreamContext.TYPE_ROOT, 12, 34);

        TokenStreamLocation location = context.startLocation(
                ContentReference.rawReference("input"));

        assertNotNull(location);
    }

    @Test
    public void childFactoriesCreateAndReuseContextsWithDuplicateDetection()
            throws Exception {
        DupDetector detector = DupDetector.rootDetector((JsonParser) null);
        JsonReadContext root = JsonReadContext.createRootContext(detector);

        JsonReadContext array = root.createChildArrayContext(11, 12);
        assertNotNull(array.getDupDetector());
        array.setCurrentName("ignored");

        JsonReadContext object = root.createChildObjectContext(13, 14);

        assertSame(array, object);
        assertTrue(object.inObject());
        assertSame(root, object.getParent());
        assertNotNull(object.getDupDetector());

        object.setCurrentName("name");
        assertEquals("name", object.currentName());

        JsonReadContext reusedArray = root.createChildArrayContext(15, 16);

        assertSame(object, reusedArray);
        assertTrue(reusedArray.inArray());
        assertFalse(reusedArray.hasCurrentName());
        assertEquals(0, reusedArray.getEntryCount());
        assertNotNull(reusedArray.getDupDetector());
    }

    @Test
    public void duplicateNameExceptionRetainsParserSourceWhenDetectorUsesParser()
            throws Exception {
        JsonParser parser = Mockito.mock(JsonParser.class);
        DupDetector detector = DupDetector.rootDetector(parser);
        JsonReadContext context = JsonReadContext.createRootContext(detector)
                .createChildObjectContext(1, 1);

        context.setCurrentName("duplicate");

        try {
            context.setCurrentName("duplicate");
            fail("Expected duplicate property name to be rejected");
        } catch (StreamReadException e) {
            assertTrue(e.getMessage().startsWith(
                    "Duplicate Object property \"duplicate\""));
        }
    }

    @Test
    public void duplicateCheckAcceptsFirstNameAndRejectsOnlyRepeatedName()
            throws Exception {
        DupDetector detector = DupDetector.rootDetector((JsonParser) null);
        JsonReadContext context = JsonReadContext.createRootContext(detector)
                .createChildObjectContext(1, 1);

        context.setCurrentName("first");
        assertEquals("first", context.currentName());

        context.setCurrentName("second");
        assertEquals("second", context.currentName());

        try {
            context.setCurrentName("first");
            fail("Expected the repeated first property name to be rejected");
        } catch (StreamReadException e) {
            assertTrue(e.getMessage().startsWith(
                    "Duplicate Object property \"first\""));
        }

        assertEquals("first", context.currentName());
    }

    @Test
    public void duplicateCheckRejectsRepeatedThirdNameAfterDistinctNames()
            throws Exception {
        DupDetector detector = DupDetector.rootDetector((JsonParser) null);
        JsonReadContext context = JsonReadContext.createRootContext(detector)
                .createChildObjectContext(1, 1);

        context.setCurrentName("one");
        context.setCurrentName("two");
        context.setCurrentName("three");

        try {
            context.setCurrentName("three");
            fail("Expected repeated third property name to be rejected");
        } catch (StreamReadException e) {
            assertTrue(e.getMessage().startsWith(
                    "Duplicate Object property \"three\""));
        }

        assertEquals("three", context.currentName());
    }
}
