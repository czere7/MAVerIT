package tools.jackson.core.json;

import org.junit.Test;
import static org.junit.Assert.*;
import tools.jackson.core.exc.StreamReadException;
import tools.jackson.core.json.DupDetector;

public class JsonReadContextTest {

    @Test
    public void testCurrentValue() {
        JsonReadContext context = JsonReadContext.createRootContext(1, 0, null);
        assertNull(context.currentValue());

        Object testValue = new Object();
        context.assignCurrentValue(testValue);
        assertSame(testValue, context.currentValue());
    }

    @Test
    public void testSetCurrentName() throws StreamReadException {
        JsonReadContext context = JsonReadContext.createRootContext(1, 0, null);
        assertNull(context.currentName());

        String testName = "testName";
        context.setCurrentName(testName);
        assertEquals(testName, context.currentName());
        assertTrue(context.hasCurrentName());
    }

    @Test
    public void testClearAndGetParent() {
        JsonReadContext parent = JsonReadContext.createRootContext(1, 0, null);
        JsonReadContext child = parent.createChildArrayContext(2, 5);

        Object testValue = new Object();
        child.assignCurrentValue(testValue);
        assertSame(testValue, child.currentValue());

        JsonReadContext returnedParent = child.clearAndGetParent();
        assertSame(parent, returnedParent);
        assertNull(child.currentValue());
    }

    @Test
    public void testStartLocation() {
        JsonReadContext context = JsonReadContext.createRootContext(1, 0, null);
        assertNotNull(context.startLocation(null));
    }
}
