package tools.jackson.core.util;

import org.junit.Test;
import static org.junit.Assert.*;

public class BufferRecyclersTest {

    @Test
    public void testClassIsDeprecated() {
        assertTrue(BufferRecyclers.class.isAnnotationPresent(Deprecated.class));
    }

    @Test
    public void testConstructor() {
        // The constructor is empty, so we just need to verify it can be instantiated
        BufferRecyclers recyclers = new BufferRecyclers();
        assertNotNull(recyclers);
    }
}
