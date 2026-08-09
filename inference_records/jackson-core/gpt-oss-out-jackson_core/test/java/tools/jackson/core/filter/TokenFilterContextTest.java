package tools.jackson.core.filter;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.concurrent.atomic.AtomicReference;

import org.junit.Test;
import org.mockito.InOrder;
import tools.jackson.core.JsonToken;
import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;

/**
 * Unit tests for {@link TokenFilterContext}.
 */
public class TokenFilterContextTest {

    /* ---------------------------------------------------------------------*
     *  Dummy classes used only in the test suite
     * --------------------------------------------------------------------- */

    /**
     * Dummy {@link TokenFilter} that records the last index passed to
     * {@code includeElement(int)} and {@code includeRootValue(int)},
     * and also records whether empty array/object should be written.
     */
    private static class DummyTokenFilter extends TokenFilter {
        int lastIndex = -1;
        boolean arrayFinished = false;
        boolean objectFinished = false;

        @Override public TokenFilter includeElement(int index) { this.lastIndex = index; return this; }
        @Override public TokenFilter includeRootValue(int index) { this.lastIndex = index; return this; }

        @Override public boolean includeEmptyArray(boolean contentsFiltered) {
            return true;
        }

        @Override public boolean includeEmptyObject(boolean contentsFiltered) {
            return true;
        }

        @Override public void filterFinishArray() {
            arrayFinished = true;
        }

        @Override public void filterFinishObject() {
            objectFinished = true;
        }
    }

    /**
     * Dummy {@link TokenFilter} that records the last index passed to
     * {@code includeElement(int)} and {@code includeRootValue(int)},
     * and also records whether empty array/object should be written.
     *
     * @return false if contentsFiltered is {@code true}, otherwise true.
     */
    private static class DummyIncludeEmptyFalse extends TokenFilter {
        int lastIndex = -1;
        boolean arrayFinished = false;
        boolean objectFinished = false;

        @Override public TokenFilter includeElement(int index) { this.lastIndex = index; return this; }
        @Override public TokenFilter includeRootValue(int index) { this.lastIndex = index; return this; }

        @Override public boolean includeEmptyArray(boolean contentsFiltered) {
            return !contentsFiltered;
        }

        @Override public boolean includeEmptyObject(boolean contentsFiltered) {
            return !contentsFiltered;
        }

        @Override public void filterFinishArray() {
            arrayFinished = true;
        }

        @Override public void filterFinishObject() {
            objectFinished = true;
        }
    }

    /* ---------------------------------------------------------------------*
     *  Test cases
     * --------------------------------------------------------------------- */

    /** Test that {@link TokenFilterContext#checkValue(TokenFilter)} correctly
     * updates index for array contexts and delegates to {@code includeElement}. */
    @Test
    public void testCheckValueArray() {
        DummyTokenFilter filter = new DummyTokenFilter();
        TokenFilterContext root = TokenFilterContext.createRootContext(filter);
        TokenFilterContext arrayCtx = root.createChildArrayContext(filter, null, false);

        // First value should be at index 0
        assertSame(filter, arrayCtx.checkValue(filter));
        assertEquals(0, filter.lastIndex);
        assertEquals("/[0]", arrayCtx.toString());   // Updated to match actual representation

        // Second value should be at index 1
        assertSame(filter, arrayCtx.checkValue(filter));
        assertEquals(1, filter.lastIndex);
    }

    /** Test that {@code checkValue} for object contexts does not alter the filter or increment index. */
    @Test
    public void testCheckValueObject() {
        DummyTokenFilter filter = new DummyTokenFilter();
        TokenFilterContext root = TokenFilterContext.createRootContext(filter);
        TokenFilterContext objCtx = root.createChildObjectContext(filter, null, false);

        // For object the filter is returned unchanged and index not incremented
        assertSame(filter, objCtx.checkValue(filter));
    }

    /** Test {@link TokenFilterContext#nextTokenToRead()} for various contexts. */
    @Test
    public void testNextTokenToRead() {
        DummyTokenFilter filter = new DummyTokenFilter();
        // Root context (TYPE_ROOT)
        TokenFilterContext root = TokenFilterContext.createRootContext(filter);
        assertNull(root.nextTokenToRead());          // start handled, so null
        assertNull(root.nextTokenToRead());          // second call still null

        // Array child with start not handled
        TokenFilterContext arrayCtx1 = root.createChildArrayContext(filter, null, false);
        assertEquals(JsonToken.START_ARRAY, arrayCtx1.nextTokenToRead());
        assertNull(arrayCtx1.nextTokenToRead());

        // Array child with start already handled
        TokenFilterContext arrayCtx2 = root.createChildArrayContext(filter, null, true);
        assertNull(arrayCtx2.nextTokenToRead());    // nothing to read because start handled

        // Object child: first returns START_OBJECT, then PROPERTY_NAME when name set
        TokenFilterContext objCtx = root.createChildObjectContext(filter, null, false);
        assertEquals(JsonToken.START_OBJECT, objCtx.nextTokenToRead());
        objCtx.setPropertyName("name");
        assertEquals(JsonToken.PROPERTY_NAME, objCtx.nextTokenToRead());
        assertNull(objCtx.nextTokenToRead());      // after name handled
    }

    /** Test that {@link TokenFilterContext#setPropertyName(String)} sets the
     * name correctly and that {@code ensurePropertyNameWritten} writes it to the generator. */
    @Test
    public void testSetAndEnsurePropertyName() throws JacksonException {
        DummyTokenFilter filter = new DummyTokenFilter();

        // Create mock JsonGenerator that captures writeName calls
        final AtomicReference<String> lastWrittenNameRef = new AtomicReference<>();
        JsonGenerator gen = mock(JsonGenerator.class);
        doAnswer(invocation -> {
            String name = invocation.getArgument(0, String.class);
            lastWrittenNameRef.set(name);
            return gen;
        }).when(gen).writeName(anyString());

        TokenFilterContext ctx = TokenFilterContext.createRootContext(filter);
        ctx.setPropertyName("myProp");
        assertTrue(ctx._needToHandleName); // internal flag set

        ctx.ensurePropertyNameWritten(gen);

        // After writing the name the flag should be cleared
        assertFalse(ctx._needToHandleName);
        assertEquals("myProp", lastWrittenNameRef.get());
    }

    /** Test that {@link TokenFilterContext#toString()} reflects current state. */
    @Test
    public void testToStringRepresentation() {
        DummyTokenFilter filter = new DummyTokenFilter();
        TokenFilterContext root = TokenFilterContext.createRootContext(filter);

        assertEquals("/", root.toString());

        // Array context with no values yet (index -1 -> 0 in toString)
        TokenFilterContext arrayCtx = root.createChildArrayContext(filter, null, false);
        assertEquals("/[0]", arrayCtx.toString());   // index is initially -1

        // Object context after setting property name
        TokenFilterContext objCtx = root.createChildObjectContext(filter, null, false);
        objCtx.setPropertyName("foo");
        assertEquals("/{\"foo\"}", objCtx.toString());

        // Object context without a name set yet
        TokenFilterContext unnamedObj = root.createChildObjectContext(filter, null, false);
        assertEquals("/{?}", unnamedObj.toString());
    }

    /** Test that {@link TokenFilterContext#findChildOf(TokenFilterContext)} finds the correct ancestor. */
    @Test
    public void testFindChildOf() {
        DummyTokenFilter filter = new DummyTokenFilter();
        TokenFilterContext root = TokenFilterContext.createRootContext(filter);
        TokenFilterContext arrayCtx = root.createChildArrayContext(filter, null, false);
        TokenFilterContext objCtx = arrayCtx.createChildObjectContext(filter, null, false);

        // From the object context we should be able to find the array as its child
        assertSame(arrayCtx, objCtx.findChildOf(root));
    }

    /** Test that {@link TokenFilterContext#skipParentChecks()} clears filters up the chain. */
    @Test
    public void testSkipParentChecks() {
        DummyTokenFilter filter = new DummyTokenFilter();
        TokenFilterContext root = TokenFilterContext.createRootContext(filter);
        TokenFilterContext arrayCtx = root.createChildArrayContext(filter, null, false);
        TokenFilterContext objCtx = arrayCtx.createChildObjectContext(filter, null, false);

        // All contexts initially hold the filter
        assertSame(filter, objCtx.getFilter());
        assertSame(filter, arrayCtx.getFilter());
        assertSame(filter, root.getFilter());

        // Skip parent checks on the object context
        objCtx.skipParentChecks();

        // Filters should be cleared for all contexts
        assertNull(objCtx.getFilter());
        assertNull(arrayCtx.getFilter());
        assertNull(root.getFilter());
    }

    /* ---------------------------------------------------------------------*
     *  Additional tests targeting uncovered branches
     * --------------------------------------------------------------------- */

    /** Test {@code writePath} when filter is null or INCLUDE_ALL: no generator methods should be called. */
    @Test
    public void testWritePathWithNoOrAllFilter() throws JacksonException {
        JsonGenerator gen = mock(JsonGenerator.class);

        // Root with null filter, child array context not started
        TokenFilterContext rootNull = TokenFilterContext.createRootContext(null);
        TokenFilterContext childArrayNull = rootNull.createChildArrayContext(null, null, false);
        childArrayNull.writePath(gen);
        // No writes should happen because filter is null
        verifyNoInteractions(gen);

        reset(gen);  // clear interactions

        // Root with INCLUDE_ALL filter
        TokenFilterContext rootAll = TokenFilterContext.createRootContext(TokenFilter.INCLUDE_ALL);
        TokenFilterContext childArrayAll = rootAll.createChildArrayContext(TokenFilter.INCLUDE_ALL, null, false);
        childArrayAll.writePath(gen);

        // No writes should happen because filter is INCLUDE_ALL
        verifyNoInteractions(gen);
    }

    /** Test {@code writePath} when start not handled and object with name pending: writes startObject and name. */
    @Test
    public void testWritePathWithStartNotHandledAndName() throws JacksonException {
        JsonGenerator gen = mock(JsonGenerator.class);
        DummyTokenFilter filter = new DummyTokenFilter();

        // Create object context that hasn't started yet, but has a name pending
        TokenFilterContext root = TokenFilterContext.createRootContext(filter);
        TokenFilterContext objCtx = root.createChildObjectContext(filter, null, false); // startHandled=false
        objCtx.setPropertyName("myObj");

        InOrder order = inOrder(gen);
        objCtx.writePath(gen);

        order.verify(gen).writeStartObject();
        order.verify(gen).writeName("myObj");
        verifyNoMoreInteractions(gen);
    }

    /** Test {@code closeArray} when start not handled, empty array is written and parent path invoked. */
    @Test
    public void testCloseArrayWithEmptyAndParentWrites() throws JacksonException {
        JsonGenerator gen = mock(JsonGenerator.class);
        DummyTokenFilter filter = new DummyTokenFilter();

        // Parent array context (not started)
        TokenFilterContext root = TokenFilterContext.createRootContext(filter);
        TokenFilterContext parent = root.createChildArrayContext(filter, null, false); // startHandled=false

        // Child array context (not started)
        TokenFilterContext child = parent.createChildArrayContext(filter, null, false);

        // Call closeArray on child
        TokenFilterContext next = child.closeArray(gen);
        assertSame(parent, next); // returns parent

        // Verify calls: two start array and one end array (order not enforced here)
        verify(gen, times(2)).writeStartArray();
        verify(gen, times(1)).writeEndArray();
        verifyNoMoreInteractions(gen);

        // Verify that filter finished array was called on child
        assertTrue(filter.arrayFinished);
    }

    /** Test {@code closeObject} when start not handled, empty object is written and parent path invoked. */
    @Test
    public void testCloseObjectWithEmptyAndParentWrites() throws JacksonException {
        JsonGenerator gen = mock(JsonGenerator.class);
        DummyTokenFilter filter = new DummyTokenFilter();

        // Parent object context (not started)
        TokenFilterContext root = TokenFilterContext.createRootContext(filter);
        TokenFilterContext parent = root.createChildObjectContext(filter, null, false); // startHandled=false

        // Child object context (not started)
        TokenFilterContext child = parent.createChildObjectContext(filter, null, false);

        // Call closeObject on child
        TokenFilterContext next = child.closeObject(gen);
        assertSame(parent, next); // returns parent

        // Verify calls: two start object and one end object (order not enforced here)
        verify(gen, times(2)).writeStartObject();
        verify(gen, times(1)).writeEndObject();
        verifyNoMoreInteractions(gen);

        // Verify that filter finished object was called on child
        assertTrue(filter.objectFinished);
    }

    /** Test that {@code currentValue} and {@code assignCurrentValue} behavior. */
    @Test
    public void testCurrentAndAssignValue() {
        DummyTokenFilter filter = new DummyTokenFilter();

        // Directly instantiate using protected constructor
        TokenFilterContext ctx = new TokenFilterContext(
                TokenFilterContext.TYPE_ROOT,
                null,
                filter,
                "rootVal",
                true);

        assertEquals("rootVal", ctx.currentValue());
        // assignCurrentValue does nothing; current value remains unchanged
        ctx.assignCurrentValue("newVal");
        assertEquals("rootVal", ctx.currentValue());
    }

    /** Test {@code hasCurrentName} returns true when name set, false otherwise. */
    @Test
    public void testHasCurrentName() {
        DummyTokenFilter filter = new DummyTokenFilter();
        TokenFilterContext root = TokenFilterContext.createRootContext(filter);
        TokenFilterContext arrayCtx = root.createChildArrayContext(filter, null, false);

        assertFalse(arrayCtx.hasCurrentName()); // arrays never have name

        TokenFilterContext objCtx = root.createChildObjectContext(filter, null, false);
        objCtx.setPropertyName("x");
        assertTrue(objCtx.hasCurrentName());
    }

    /** Test {@code findChildOf} returns null when no such ancestor. */
    @Test
    public void testFindChildOfNotFound() {
        DummyTokenFilter filter = new DummyTokenFilter();
        TokenFilterContext root = TokenFilterContext.createRootContext(filter);
        TokenFilterContext arrayCtx = root.createChildArrayContext(filter, null, false);
        TokenFilterContext objCtx = arrayCtx.createChildObjectContext(filter, null, false);

        // Use a context unrelated to the chain
        TokenFilterContext unrelated = new TokenFilterContext(
                TokenFilterContext.TYPE_ROOT,
                null,
                filter,
                null,
                true);

        assertNull(objCtx.findChildOf(unrelated));
    }

    /** Test that {@code writePath} for array when start not handled. */
    @Test
    public void testWritePathForArrayWhenStartNotHandled() throws JacksonException {
        DummyTokenFilter filter = new DummyTokenFilter();
        JsonGenerator gen = mock(JsonGenerator.class);
        TokenFilterContext root = TokenFilterContext.createRootContext(filter);

        TokenFilterContext arrayCtx = root.createChildArrayContext(filter, null, false); // start not handled
        arrayCtx.writePath(gen);

        verify(gen).writeStartArray();
        verifyNoMoreInteractions(gen);
    }

    /** Test that {@code closeArray} writes END_ARRAY when startHandled is true. */
    @Test
    public void testCloseArrayWithStartHandledTrue() throws JacksonException {
        DummyTokenFilter filter = new DummyTokenFilter();
        JsonGenerator gen = mock(JsonGenerator.class);

        TokenFilterContext root = TokenFilterContext.createRootContext(filter);
        TokenFilterContext arrayCtx = root.createChildArrayContext(filter, null, true); // start handled
        TokenFilterContext next = arrayCtx.closeArray(gen);
        assertSame(root, next);

        verify(gen).writeEndArray();
        verifyNoMoreInteractions(gen);
    }

    /** Test that {@code closeObject} writes END_OBJECT when startHandled is true. */
    @Test
    public void testCloseObjectWithStartHandledTrue() throws JacksonException {
        DummyTokenFilter filter = new DummyTokenFilter();
        JsonGenerator gen = mock(JsonGenerator.class);

        TokenFilterContext root = TokenFilterContext.createRootContext(filter);
        TokenFilterContext objCtx = root.createChildObjectContext(filter, null, true); // start handled
        TokenFilterContext next = objCtx.closeObject(gen);
        assertSame(root, next);

        verify(gen).writeEndObject();
        verifyNoMoreInteractions(gen);
    }

    /** Test that {@code closeArray} does nothing when filter is null. */
    @Test
    public void testCloseArrayWithNullFilter() throws JacksonException {
        JsonGenerator gen = mock(JsonGenerator.class);

        TokenFilterContext root = TokenFilterContext.createRootContext(null);
        TokenFilterContext arrayCtx = root.createChildArrayContext(null, null, false); // start not handled

        arrayCtx.closeArray(gen);

        verifyNoInteractions(gen);
    }

    /** Test that {@code closeObject} does nothing when filter is null. */
    @Test
    public void testCloseObjectWithNullFilter() throws JacksonException {
        JsonGenerator gen = mock(JsonGenerator.class);

        TokenFilterContext root = TokenFilterContext.createRootContext(null);
        TokenFilterContext objCtx = root.createChildObjectContext(null, null, false); // start not handled

        objCtx.closeObject(gen);

        verifyNoInteractions(gen);
    }

    /** Test {@code nextTokenToRead} returns null for an object context with the start already handled and no pending property name. */
    @Test
    public void testNextTokenToReadObjectWithNoPropertyName() {
        DummyTokenFilter filter = new DummyTokenFilter();
        TokenFilterContext root = TokenFilterContext.createRootContext(filter);
        TokenFilterContext objCtx = root.createChildObjectContext(filter, null, true); // start handled

        assertNull(objCtx.nextTokenToRead());
    }

    /** Test {@code writePath} where parent has a pending name and child starts with startHandled true. */
    @Test
    public void testWritePathWithParentAndChildNames() throws JacksonException {
        DummyTokenFilter filter = new DummyTokenFilter();
        JsonGenerator gen = mock(JsonGenerator.class);

        TokenFilterContext root = TokenFilterContext.createRootContext(filter);
        TokenFilterContext parent = root.createChildObjectContext(filter, null, true); // start handled
        parent.setPropertyName("parent");

        // Parent name has not been written yet (needToHandleName==true)

        TokenFilterContext child = parent.createChildObjectContext(filter, null, false);
        child.setPropertyName("child");

        InOrder order = inOrder(gen);
        child.writePath(gen);

        // Parent writes its pending name
        order.verify(gen).writeName("parent");
        // Child writes start object and then its name
        order.verify(gen).writeStartObject();
        order.verify(gen).writeName("child");
        verifyNoMoreInteractions(gen);
    }

    /** Test {@code writePath} where parent has already written its name
     * (so _needToHandleName cleared) and child starts with startHandled false. */
    @Test
    public void testWritePathWhenParentHasWrittenNameAndChildStartsNot() throws JacksonException {
        DummyTokenFilter filter = new DummyTokenFilter();
        JsonGenerator gen = mock(JsonGenerator.class);

        TokenFilterContext root = TokenFilterContext.createRootContext(filter);
        TokenFilterContext parent = root.createChildObjectContext(filter, null, true); // start handled
        parent.setPropertyName("parent");
        // Write the name to clear flag
        parent.ensurePropertyNameWritten(gen);

        // Now create child not started
        TokenFilterContext child = parent.createChildObjectContext(filter, null, false);
        // No set property name on child

        reset(gen); // Clear interactions after writing parent's name
        child.writePath(gen);

        // The child writes start object and a writeName call with null (due to no name)
        verify(gen).writeStartObject();
        verify(gen).writeName(isNull(String.class));
        verifyNoMoreInteractions(gen);
    }

    /** Test that {@code closeArray} when start not handled and array has elements.
     *  In this case the empty-array logic should NOT write any new markers. */
    @Test
    public void testCloseArrayWithElementsAndEmptyNotWritten() throws JacksonException {
        DummyIncludeEmptyFalse filter = new DummyIncludeEmptyFalse();
        JsonGenerator gen = mock(JsonGenerator.class);

        TokenFilterContext root = TokenFilterContext.createRootContext(filter);
        // Child array context not started, but we will add an element
        TokenFilterContext child = root.createChildArrayContext(filter, null, false);

        // Add one element to set index >=0
        child.checkValue(filter);   // increments index

        // Close the array: since startHandled is still false and hasCurrentIndex() == true,
        // includeEmptyArray(true) returns false -> no write should occur.
        TokenFilterContext next = child.closeArray(gen);
        assertSame(root, next);

        verifyNoInteractions(gen);
        assertTrue(filter.arrayFinished);   // filter finished array flag must be set
    }

    /** Test that {@code closeObject} when start not handled and object has a name set.
     *  In this case the empty-object logic should NOT write any new markers. */
    @Test
    public void testCloseObjectWithNameAndEmptyNotWritten() throws JacksonException {
        DummyIncludeEmptyFalse filter = new DummyIncludeEmptyFalse();
        JsonGenerator gen = mock(JsonGenerator.class);

        TokenFilterContext root = TokenFilterContext.createRootContext(filter);
        // Child object context not started, set a name
        TokenFilterContext child = root.createChildObjectContext(filter, null, false);
        child.setPropertyName("foo");

        // Close the object: startHandled is false and hasCurrentName() == true,
        // includeEmptyObject(true) returns false -> no write should occur.
        TokenFilterContext next = child.closeObject(gen);
        assertSame(root, next);

        verifyNoInteractions(gen);
        assertTrue(filter.objectFinished);   // filter finished object flag must be set
    }

    /** Test that {@link TokenFilterContext#findChildOf} immediately returns the current context when parent matches. */
    @Test
    public void testFindChildOfImmediateParent() {
        DummyTokenFilter filter = new DummyTokenFilter();
        TokenFilterContext root = TokenFilterContext.createRootContext(filter);
        TokenFilterContext arrayCtx = root.createChildArrayContext(filter, null, false);
        TokenFilterContext objCtx = arrayCtx.createChildObjectContext(filter, null, false);

        // For the array context, its parent is root; calling findChildOf(root) should return the array itself
        assertSame(arrayCtx, arrayCtx.findChildOf(root));
    }

    /** Test that {@code writePath} for an array with startHandled true writes nothing. */
    @Test
    public void testWritePathForArrayWhenStartHandled() throws JacksonException {
        DummyTokenFilter filter = new DummyTokenFilter();
        JsonGenerator gen = mock(JsonGenerator.class);

        TokenFilterContext root = TokenFilterContext.createRootContext(filter);
        // Create array context with startHandled=true
        TokenFilterContext arrayCtx = root.createChildArrayContext(filter, null, true);
        arrayCtx.writePath(gen);

        verifyNoInteractions(gen);  // nothing should be written
    }

    /* ---------------------------------------------------------------------*
     *  New tests targeting surviving mutations and uncovered branches
     * --------------------------------------------------------------------- */

    /** Test that constructor correctly initializes internal state. */
    @Test
    public void testConstructorInitialState() {
        DummyTokenFilter filter = new DummyTokenFilter();
        TokenFilterContext root = TokenFilterContext.createRootContext(filter);
        assertEquals(0, root.getNestingDepth());

        TokenFilterContext arrayCtx = root.createChildArrayContext(filter, null, false);
        assertEquals(1, arrayCtx.getNestingDepth());
        assertFalse(arrayCtx.isStartHandled());
        assertEquals(0, arrayCtx.getEntryCount());
        assertFalse(arrayCtx.hasCurrentIndex());

        // After first checkValue call index should be 0
        arrayCtx.checkValue(filter);
        assertTrue(arrayCtx.hasCurrentIndex());
        assertEquals(0, arrayCtx.getCurrentIndex());

        TokenFilterContext objCtx = arrayCtx.createChildObjectContext(filter, null, false);
        assertEquals(2, objCtx.getNestingDepth());
    }

    /** Test that {@code setPropertyName} returns the filter reference. */
    @Test
    public void testSetPropertyNameReturnFilter() {
        DummyTokenFilter filter = new DummyTokenFilter();
        TokenFilterContext ctx = TokenFilterContext.createRootContext(filter);
        TokenFilter returned = ctx.setPropertyName("name");
        assertSame(filter, returned);
    }

    /** Test that {@code getParent} returns the correct parent context. */
    @Test
    public void testGetParentReturnsCorrectParent() {
        DummyTokenFilter filter = new DummyTokenFilter();
        TokenFilterContext root = TokenFilterContext.createRootContext(filter);
        TokenFilterContext child = root.createChildArrayContext(filter, null, false);
        assertSame(root, child.getParent());
    }

    /** Test that {@code skipParentChecks} clears filters on all ancestors. */
    @Test
    public void testSkipParentChecksClearsAllFilters() {
        DummyTokenFilter filter = new DummyTokenFilter();
        TokenFilterContext root = TokenFilterContext.createRootContext(filter);
        TokenFilterContext arrayCtx = root.createChildArrayContext(filter, null, false);
        TokenFilterContext child = arrayCtx.createChildObjectContext(filter, null, false);

        assertSame(filter, child.getFilter());
        assertSame(filter, arrayCtx.getFilter());
        assertSame(filter, root.getFilter());

        child.skipParentChecks();

        assertNull(child.getFilter());
        assertNull(arrayCtx.getFilter());
        assertNull(root.getFilter());
    }

    /** Test that {@code ensurePropertyNameWritten} does not write name when no name is set. */
    @Test
    public void testEnsurePropertyNameNotCalledWithoutName() throws JacksonException {
        DummyTokenFilter filter = new DummyTokenFilter();
        JsonGenerator gen = mock(JsonGenerator.class);
        TokenFilterContext ctx = TokenFilterContext.createRootContext(filter);

        // No property name has been set
        ctx.ensurePropertyNameWritten(gen);
        verifyNoInteractions(gen);
    }

    /** Test that {@code checkValue} on an OBJECT context does not modify the index. */
    @Test
    public void testCheckValueObjectDoesNotIncrementIndex() {
        DummyTokenFilter filter = new DummyTokenFilter();
        TokenFilterContext root = TokenFilterContext.createRootContext(filter);
        TokenFilterContext objCtx = root.createChildObjectContext(filter, null, false);

        int initialEntries = objCtx.getEntryCount(); // should be 0
        assertEquals(0, initialEntries);
        assertFalse(objCtx.hasCurrentIndex());

        // call checkValue
        objCtx.checkValue(filter);
        // index and entry count remain unchanged
        assertEquals(initialEntries, objCtx.getEntryCount());
        assertFalse(objCtx.hasCurrentIndex());
    }

    /** Test that {@code currentName} returns null when no name is set, and non-null when a name is set. */
    @Test
    public void testCurrentNameAfterSettingAndNull() {
        DummyTokenFilter filter = new DummyTokenFilter();
        TokenFilterContext root = TokenFilterContext.createRootContext(filter);
        TokenFilterContext objCtx = root.createChildObjectContext(filter, null, false);

        assertFalse(objCtx.hasCurrentName());
        assertNull(objCtx.currentName());

        objCtx.setPropertyName("foo");
        assertTrue(objCtx.hasCurrentName());
        assertEquals("foo", objCtx.currentName());

        // reset name to null
        objCtx.setPropertyName(null);
        assertFalse(objCtx.hasCurrentName());
        assertNull(objCtx.currentName());
    }

    /** Test that {@code isStartHandled} returns correct values for various contexts. */
    @Test
    public void testIsStartHandledVariedContexts() {
        DummyTokenFilter filter = new DummyTokenFilter();

        TokenFilterContext root = TokenFilterContext.createRootContext(filter);
        assertTrue(root.isStartHandled());

        // Array start not handled initially
        TokenFilterContext arrayCtx1 = root.createChildArrayContext(filter, null, false);
        assertFalse(arrayCtx1.isStartHandled());
        arrayCtx1.nextTokenToRead(); // should set to true
        assertTrue(arrayCtx1.isStartHandled());

        // Array start handled at creation
        TokenFilterContext arrayCtx2 = root.createChildArrayContext(filter, null, true);
        assertTrue(arrayCtx2.isStartHandled());

        // Object start not handled initially
        TokenFilterContext objCtx1 = root.createChildObjectContext(filter, null, false);
        assertFalse(objCtx1.isStartHandled());
        objCtx1.nextTokenToRead(); // sets to true
        assertTrue(objCtx1.isStartHandled());

        // Object start handled at creation
        TokenFilterContext objCtx2 = root.createChildObjectContext(filter, null, true);
        assertTrue(objCtx2.isStartHandled());
    }
}
