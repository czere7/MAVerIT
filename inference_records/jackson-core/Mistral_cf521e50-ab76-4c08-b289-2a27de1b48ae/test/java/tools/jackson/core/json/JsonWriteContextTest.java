package tools.jackson.core.json;

import org.junit.Test;
import static org.junit.Assert.*;
import tools.jackson.core.exc.StreamWriteException;

public class JsonWriteContextTest {

    @Test
    public void testWriteNameInObjectContext() {
        JsonWriteContext context = JsonWriteContext.createRootContext(null)
            .createChildObjectContext(null);
        int status = context.writeName("testName");
        assertEquals(JsonWriteContext.STATUS_OK_AS_IS, status);
        assertEquals("testName", context._currentName);
        assertTrue(context._gotName);
    }

    @Test
    public void testWriteNameInArrayContext() {
        JsonWriteContext context = JsonWriteContext.createRootContext(null)
            .createChildArrayContext(null);
        int status = context.writeName("testName");
        assertEquals(JsonWriteContext.STATUS_EXPECT_VALUE, status);
        assertNull(context._currentName);
        assertFalse(context._gotName);
    }

    @Test
    public void testClearAndGetParent() {
        JsonWriteContext parent = JsonWriteContext.createRootContext(null);
        JsonWriteContext child = parent.createChildObjectContext(null);
        JsonWriteContext result = child.clearAndGetParent();
        assertSame(parent, result);
        assertNull(child._currentValue);
    }
}
