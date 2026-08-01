package tools.jackson.core.util;

import org.junit.Test;
import static org.junit.Assert.*;
import tools.jackson.core.exc.StreamReadException;
import tools.jackson.core.json.DupDetector;

public class SimpleStreamReadContextTest {

    @Test
    public void testCurrentName() {
        SimpleStreamReadContext context = SimpleStreamReadContext.createRootContext(1, 0, null);
        context.setCurrentName("testName");
        assertEquals("testName", context.currentName());
    }

    @Test
    public void testHasCurrentName() {
        SimpleStreamReadContext context = SimpleStreamReadContext.createRootContext(1, 0, null);
        assertFalse(context.hasCurrentName());
        context.setCurrentName("testName");
        assertTrue(context.hasCurrentName());
    }

    @Test
    public void testCurrentValue() {
        SimpleStreamReadContext context = SimpleStreamReadContext.createRootContext(1, 0, null);
        assertNull(context.currentValue());
        context.assignCurrentValue("testValue");
        assertEquals("testValue", context.currentValue());
    }

    @Test
    public void testAssignCurrentValue() {
        SimpleStreamReadContext context = SimpleStreamReadContext.createRootContext(1, 0, null);
        context.assignCurrentValue("testValue");
        assertEquals("testValue", context.currentValue());
    }
}
