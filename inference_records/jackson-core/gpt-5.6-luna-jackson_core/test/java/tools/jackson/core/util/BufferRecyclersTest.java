package tools.jackson.core.util;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import org.junit.Test;

public class BufferRecyclersTest {

    @Test
    public void canBeInstantiated() {
        BufferRecyclers recyclers = new BufferRecyclers();

        assertNotNull(recyclers);
    }

    @Test
    public void eachInstantiationCreatesAnIndependentInstance() {
        BufferRecyclers first = new BufferRecyclers();
        BufferRecyclers second = new BufferRecyclers();

        assertNotNull(first);
        assertNotNull(second);
        assertNotSame(first, second);
    }

    @Test
    public void constructorCreatesAnObjectOfTheExpectedType() {
        BufferRecyclers recyclers = new BufferRecyclers();

        assertNotNull(recyclers);
        assertSame(BufferRecyclers.class, recyclers.getClass());
    }

    @Test
    public void constructorDoesNotReturnNullAcrossRepeatedInvocations() {
        for (int i = 0; i < 10; i++) {
            assertNotNull(new BufferRecyclers());
        }
    }

    private static void assertSame(Object expected, Object actual) {
        if (expected != actual) {
            throw new AssertionError("Expected the same reference");
        }
    }
}
