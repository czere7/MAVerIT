package tools.jackson.core.filter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import tools.jackson.core.JsonGenerator;
import tools.jackson.core.JsonToken;

public class TokenFilterContextTest {

    @Test
    public void rootContextHasExpectedInitialState() {
        TokenFilter filter = new TokenFilter() { };
        TokenFilterContext root = TokenFilterContext.createRootContext(filter);

        assertSame(filter, root.getFilter());
        assertNull(root.getParent());
        assertTrue(root.inRoot());
        assertFalse(root.inArray());
        assertFalse(root.inObject());
        assertEquals(0, root.getNestingDepth());
        assertEquals(0, root.getCurrentIndex());
        assertEquals(0, root.getEntryCount());
        assertFalse(root.hasCurrentIndex());
        assertFalse(root.hasCurrentName());
        assertNull(root.currentName());
        assertNull(root.currentValue());
        assertTrue(root.isStartHandled());
        assertEquals("/", root.toString());
    }

    @Test
    public void childContextsExposeParentTypeDepthAndCurrentValue() {
        TokenFilter filter = new TokenFilter() { };
        TokenFilterContext root = TokenFilterContext.createRootContext(filter);
        Object arrayValue = new Object();
        TokenFilterContext array = root.createChildArrayContext(filter, arrayValue, false);
        Object objectValue = new Object();
        TokenFilterContext object = array.createChildObjectContext(filter, objectValue, true);

        assertSame(root, array.getParent());
        assertSame(array, object.getParent());
        assertTrue(array.inArray());
        assertTrue(object.inObject());
        assertEquals(1, array.getNestingDepth());
        assertEquals(2, object.getNestingDepth());
        assertSame(arrayValue, array.currentValue());
        assertSame(objectValue, object.currentValue());
        assertFalse(array.isStartHandled());
        assertTrue(object.isStartHandled());
    }

    @Test
    public void childArrayContextIsCreatedAndNotNullOnFirstUse() {
        TokenFilter filter = new TokenFilter() { };
        TokenFilterContext root = TokenFilterContext.createRootContext(filter);

        TokenFilterContext child =
                root.createChildArrayContext(filter, "value", false);

        assertNotNull(child);
        assertSame(root, child.getParent());
        assertTrue(child.inArray());
        assertSame(filter, child.getFilter());
        assertEquals("value", child.currentValue());
        assertFalse(child.isStartHandled());
    }

    @Test
    public void childContextIsReusedAndReset() {
        TokenFilter firstFilter = new TokenFilter() { };
        TokenFilter secondFilter = new TokenFilter() { };
        TokenFilterContext root = TokenFilterContext.createRootContext(firstFilter);
        TokenFilterContext child =
                root.createChildArrayContext(firstFilter, "first", true);

        child.setPropertyName("stale");
        child.checkValue(firstFilter);

        TokenFilterContext reused =
                root.createChildObjectContext(secondFilter, "second", false);

        assertSame(child, reused);
        assertTrue(reused.inObject());
        assertSame(secondFilter, reused.getFilter());
        assertEquals("second", reused.currentValue());
        assertFalse(reused.isStartHandled());
        assertFalse(reused.hasCurrentName());
        assertEquals(0, reused.getEntryCount());
        assertEquals(0, reused.getCurrentIndex());
    }

    @Test
    public void checkValueAdvancesArrayAndRootIndexes() {
        TokenFilter arrayFilter = Mockito.mock(TokenFilter.class);
        TokenFilter first = new TokenFilter() { };
        TokenFilter second = new TokenFilter() { };
        when(arrayFilter.includeElement(0)).thenReturn(first);
        when(arrayFilter.includeElement(1)).thenReturn(second);

        TokenFilterContext array =
                TokenFilterContext.createRootContext(arrayFilter)
                        .createChildArrayContext(arrayFilter, null, true);

        assertSame(first, array.checkValue(arrayFilter));
        assertSame(second, array.checkValue(arrayFilter));
        assertEquals(2, array.getEntryCount());
        assertEquals(1, array.getCurrentIndex());
        verify(arrayFilter).includeElement(0);
        verify(arrayFilter).includeElement(1);

        TokenFilter rootFilter = Mockito.mock(TokenFilter.class);
        TokenFilter rootResult = new TokenFilter() { };
        when(rootFilter.includeRootValue(0)).thenReturn(rootResult);
        TokenFilterContext root =
                TokenFilterContext.createRootContext(rootFilter);

        assertSame(rootResult, root.checkValue(rootFilter));
        assertEquals(1, root.getEntryCount());
        verify(rootFilter).includeRootValue(0);
    }

    @Test
    public void objectCheckValueDoesNotAdvanceIndex() {
        TokenFilter filter = Mockito.mock(TokenFilter.class);
        TokenFilterContext object =
                TokenFilterContext.createRootContext(filter)
                        .createChildObjectContext(filter, null, false);

        assertSame(filter, object.checkValue(filter));
        assertEquals(0, object.getEntryCount());
        assertEquals(0, object.getCurrentIndex());
        verifyNoInteractions(filter);
    }

    @Test
    public void propertyNameIsStoredAndWrittenOnlyOnce() throws Exception {
        TokenFilter filter = new TokenFilter() { };
        TokenFilterContext object =
                TokenFilterContext.createRootContext(filter)
                        .createChildObjectContext(filter, null, true);
        JsonGenerator generator = Mockito.mock(JsonGenerator.class);

        assertSame(filter, object.setPropertyName("name"));
        assertEquals("name", object.currentName());
        assertTrue(object.hasCurrentName());

        object.ensurePropertyNameWritten(generator);
        object.ensurePropertyNameWritten(generator);

        verify(generator).writeName("name");
    }

    @Test
    public void nextTokenToReadReturnsStructuralAndPropertyTokens() {
        TokenFilter filter = new TokenFilter() { };
        TokenFilterContext object =
                TokenFilterContext.createRootContext(filter)
                        .createChildObjectContext(filter, null, false);

        assertEquals(JsonToken.START_OBJECT, object.nextTokenToRead());
        assertTrue(object.isStartHandled());
        assertNull(object.nextTokenToRead());

        object.setPropertyName("value");
        assertEquals(JsonToken.PROPERTY_NAME, object.nextTokenToRead());
        assertNull(object.nextTokenToRead());

        TokenFilterContext array =
                TokenFilterContext.createRootContext(filter)
                        .createChildArrayContext(filter, null, false);

        assertEquals(JsonToken.START_ARRAY, array.nextTokenToRead());
        assertNull(array.nextTokenToRead());
    }

    @Test
    public void writePathWritesParentObjectAndChildArrayPath() throws Exception {
        TokenFilter filter = new TokenFilter() { };
        TokenFilterContext root = TokenFilterContext.createRootContext(filter);
        TokenFilterContext object =
                root.createChildObjectContext(filter, null, false);
        object.setPropertyName("items");
        TokenFilterContext array =
                object.createChildArrayContext(filter, null, false);
        JsonGenerator generator = Mockito.mock(JsonGenerator.class);

        array.writePath(generator);

        InOrder order = inOrder(generator);
        order.verify(generator).writeStartObject();
        order.verify(generator).writeName("items");
        order.verify(generator).writeStartArray();
        assertTrue(object.isStartHandled());
        assertTrue(array.isStartHandled());
    }

    @Test
    public void writePathWritesPendingNameForAlreadyStartedObject()
            throws Exception {
        TokenFilter filter = new TokenFilter() { };
        TokenFilterContext object =
                TokenFilterContext.createRootContext(filter)
                        .createChildObjectContext(filter, null, true);
        object.setPropertyName("name");
        JsonGenerator generator = Mockito.mock(JsonGenerator.class);

        object.writePath(generator);

        verify(generator).writeName("name");
        assertTrue(object.isStartHandled());
    }

    @Test
    public void writePathPrivateParentPathWritesPendingNameBeforeChildStart()
            throws Exception {
        TokenFilter filter = new TokenFilter() { };
        TokenFilterContext root = TokenFilterContext.createRootContext(filter);
        TokenFilterContext object =
                root.createChildObjectContext(filter, null, true);
        object.setPropertyName("children");
        TokenFilterContext array =
                object.createChildArrayContext(filter, null, false);
        JsonGenerator generator = Mockito.mock(JsonGenerator.class);

        array.writePath(generator);

        InOrder order = inOrder(generator);
        order.verify(generator).writeName("children");
        order.verify(generator).writeStartArray();
        assertTrue(array.isStartHandled());
    }

    @Test
    public void writePathRetainsPendingLeafNameAcrossRepeatedWrites()
            throws Exception {
        TokenFilter filter = new TokenFilter() { };
        TokenFilterContext root = TokenFilterContext.createRootContext(filter);
        TokenFilterContext parent =
                root.createChildObjectContext(filter, null, false);
        parent.setPropertyName("parent");
        TokenFilterContext child =
                parent.createChildObjectContext(filter, null, false);
        child.setPropertyName("child");
        JsonGenerator generator = Mockito.mock(JsonGenerator.class);

        child.writePath(generator);
        child.writePath(generator);

        InOrder order = inOrder(generator);
        order.verify(generator).writeStartObject();
        order.verify(generator).writeName("parent");
        order.verify(generator).writeStartObject();
        order.verify(generator, Mockito.times(2)).writeName("child");

        verify(generator, Mockito.times(1)).writeName("parent");
        verify(generator, Mockito.times(2)).writeName("child");
        assertTrue(parent.isStartHandled());
        assertTrue(child.isStartHandled());
    }

    @Test
    public void writePathDoesNothingForNullOrIncludeAllFilter()
            throws Exception {
        JsonGenerator generator = Mockito.mock(JsonGenerator.class);

        TokenFilterContext nullFilter =
                TokenFilterContext.createRootContext(null)
                        .createChildArrayContext(null, null, false);
        nullFilter.writePath(generator);

        TokenFilterContext includeAll =
                TokenFilterContext.createRootContext(TokenFilter.INCLUDE_ALL)
                        .createChildObjectContext(TokenFilter.INCLUDE_ALL, null,
                                false);
        includeAll.setPropertyName("ignored");
        includeAll.writePath(generator);

        verifyNoInteractions(generator);
        assertFalse(nullFilter.isStartHandled());
        assertFalse(includeAll.isStartHandled());
    }

    @Test
    public void closeHandledContainersWritesEndTokensAndFinishesFilters()
            throws Exception {
        TokenFilter filter = Mockito.mock(TokenFilter.class);
        JsonGenerator generator = Mockito.mock(JsonGenerator.class);
        TokenFilterContext root = TokenFilterContext.createRootContext(filter);
        TokenFilterContext array =
                root.createChildArrayContext(filter, null, true);
        TokenFilterContext object =
                root.createChildObjectContext(filter, null, true);

        assertSame(root, array.closeArray(generator));
        assertSame(root, object.closeObject(generator));

        verify(generator).writeEndArray();
        verify(generator).writeEndObject();
        verify(filter).filterFinishArray();
        verify(filter).filterFinishObject();
    }

    @Test
    public void closeUnstartedArrayIncludesEmptyArrayWhenRequested()
            throws Exception {
        TokenFilter filter = Mockito.mock(TokenFilter.class);
        when(filter.includeEmptyArray(false)).thenReturn(true);
        JsonGenerator generator = Mockito.mock(JsonGenerator.class);
        TokenFilterContext root = TokenFilterContext.createRootContext(filter);
        TokenFilterContext array =
                root.createChildArrayContext(filter, null, false);

        assertSame(root, array.closeArray(generator));

        InOrder order = inOrder(generator);
        order.verify(generator).writeStartArray();
        order.verify(generator).writeEndArray();
        verify(filter).includeEmptyArray(false);
        verify(filter).filterFinishArray();
    }

    @Test
    public void closeUnstartedNestedArrayWritesParentPath() throws Exception {
        TokenFilter parentFilter = Mockito.mock(TokenFilter.class);
        TokenFilter childFilter = Mockito.mock(TokenFilter.class);
        when(childFilter.includeEmptyArray(false)).thenReturn(true);
        JsonGenerator generator = Mockito.mock(JsonGenerator.class);

        TokenFilterContext root =
                TokenFilterContext.createRootContext(parentFilter);
        TokenFilterContext object =
                root.createChildObjectContext(parentFilter, null, false);
        object.setPropertyName("values");
        TokenFilterContext array =
                object.createChildArrayContext(childFilter, null, false);

        assertSame(object, array.closeArray(generator));

        InOrder order = inOrder(generator);
        order.verify(generator).writeStartObject();
        order.verify(generator).writeName("values");
        order.verify(generator).writeStartArray();
        order.verify(generator).writeEndArray();
        verify(childFilter).filterFinishArray();
        assertTrue(object.isStartHandled());
    }

    @Test
    public void closeUnstartedObjectWritesParentPath() throws Exception {
        TokenFilter parentFilter = Mockito.mock(TokenFilter.class);
        TokenFilter childFilter = Mockito.mock(TokenFilter.class);
        when(childFilter.includeEmptyObject(false)).thenReturn(true);
        JsonGenerator generator = Mockito.mock(JsonGenerator.class);

        TokenFilterContext root =
                TokenFilterContext.createRootContext(parentFilter);
        TokenFilterContext parent =
                root.createChildObjectContext(parentFilter, null, false);
        parent.setPropertyName("nested");
        TokenFilterContext child =
                parent.createChildObjectContext(childFilter, null, false);

        assertSame(parent, child.closeObject(generator));

        InOrder order = inOrder(generator);
        order.verify(generator).writeStartObject();
        order.verify(generator).writeName("nested");
        order.verify(generator).writeStartObject();
        order.verify(generator).writeEndObject();
        verify(childFilter).filterFinishObject();
        assertTrue(parent.isStartHandled());
    }

    @Test
    public void closeUnstartedObjectDoesNotWriteWhenExcluded()
            throws Exception {
        TokenFilter filter = Mockito.mock(TokenFilter.class);
        when(filter.includeEmptyObject(false)).thenReturn(false);
        JsonGenerator generator = Mockito.mock(JsonGenerator.class);
        TokenFilterContext root = TokenFilterContext.createRootContext(filter);
        TokenFilterContext object =
                root.createChildObjectContext(filter, null, false);

        assertSame(root, object.closeObject(generator));

        verify(filter).includeEmptyObject(false);
        verify(filter).filterFinishObject();
        verify(generator, never()).writeStartObject();
        verify(generator, never()).writeEndObject();
    }

    @Test
    public void closeUnstartedUnnamedObjectIncludesObjectWhenRequested()
            throws Exception {
        TokenFilter filter = Mockito.mock(TokenFilter.class);
        when(filter.includeEmptyObject(false)).thenReturn(true);
        JsonGenerator generator = Mockito.mock(JsonGenerator.class);
        TokenFilterContext object =
                TokenFilterContext.createRootContext(filter)
                        .createChildObjectContext(filter, null, false);

        object.closeObject(generator);

        verify(filter).includeEmptyObject(false);
        verify(filter).filterFinishObject();
        verify(generator).writeStartObject();
        verify(generator).writeEndObject();
        verify(generator, never()).writeName((String) null);
    }

    @Test
    public void skipParentChecksClearsAllFilters() {
        TokenFilter filter = new TokenFilter() { };
        TokenFilterContext root = TokenFilterContext.createRootContext(filter);
        TokenFilterContext object =
                root.createChildObjectContext(filter, null, true);
        TokenFilterContext array =
                object.createChildArrayContext(filter, null, true);

        array.skipParentChecks();

        assertNull(array.getFilter());
        assertNull(object.getFilter());
        assertNull(root.getFilter());
    }

    @Test
    public void findChildOfReturnsDirectAndNestedDescendants() {
        TokenFilter filter = new TokenFilter() { };
        TokenFilterContext root = TokenFilterContext.createRootContext(filter);
        TokenFilterContext object =
                root.createChildObjectContext(filter, null, true);
        TokenFilterContext array =
                object.createChildArrayContext(filter, null, true);
        TokenFilterContext leaf =
                array.createChildObjectContext(filter, null, true);

        assertSame(object, array.findChildOf(root));
        assertSame(array, leaf.findChildOf(object));
        assertSame(object, leaf.findChildOf(root));
        assertNull(array.findChildOf(leaf));
    }

    @Test
    public void toStringContainsCompleteContextPath() {
        TokenFilter filter = new TokenFilter() { };
        TokenFilterContext root = TokenFilterContext.createRootContext(filter);
        TokenFilterContext object =
                root.createChildObjectContext(filter, null, true);
        object.setPropertyName("quoted");
        TokenFilterContext array =
                object.createChildArrayContext(filter, null, true);
        array.checkValue(filter);

        assertEquals("/{\"quoted\"}[0]", array.toString());
    }

    @Test
    public void toStringUsesQuestionMarkForUnnamedObject() {
        TokenFilterContext object =
                TokenFilterContext.createRootContext(new TokenFilter() { })
                        .createChildObjectContext(new TokenFilter() { }, null,
                                true);

        assertEquals("/{?}", object.toString());
    }

    @Test
    public void assignCurrentValueIsIntentionallyNoOp() {
        TokenFilter filter = new TokenFilter() { };
        TokenFilterContext context =
                TokenFilterContext.createRootContext(filter)
                        .createChildArrayContext(filter, "original", true);

        context.assignCurrentValue("replacement");

        assertEquals("original", context.currentValue());
    }

    @Test
    public void findChildOfReturnsRootForNullParent() {
        TokenFilterContext root =
                TokenFilterContext.createRootContext(new TokenFilter() { });

        assertSame(root, root.findChildOf(null));
    }

    @Test
    public void rootCheckValueUsesSuccessiveIndexes() {
        TokenFilter filter = Mockito.mock(TokenFilter.class);
        TokenFilter first = new TokenFilter() { };
        TokenFilter second = new TokenFilter() { };
        when(filter.includeRootValue(0)).thenReturn(first);
        when(filter.includeRootValue(1)).thenReturn(second);

        TokenFilterContext root = TokenFilterContext.createRootContext(filter);

        assertSame(first, root.checkValue(filter));
        assertSame(second, root.checkValue(filter));
        assertEquals(2, root.getEntryCount());
        assertEquals(1, root.getCurrentIndex());
        verify(filter).includeRootValue(0);
        verify(filter).includeRootValue(1);
    }
}
