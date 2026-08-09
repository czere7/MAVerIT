package tools.jackson.core.util;

import org.junit.Test;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import static org.junit.Assert.*;

public class BufferRecyclersTest {

    @Test
    public void testClassIsDeprecated() {
        Annotation deprecated = BufferRecyclers.class.getAnnotation(Deprecated.class);
        assertNotNull("BufferRecyclers should be annotated with @Deprecated", deprecated);
    }

    @Test
    public void testHasPublicDefaultConstructor() {
        Constructor<?>[] constructors = BufferRecyclers.class.getDeclaredConstructors();
        boolean found = false;
        for (Constructor<?> ctor : constructors) {
            if (ctor.getParameterCount() == 0 &&
                Modifier.isPublic(ctor.getModifiers())) {
                found = true;
                break;
            }
        }
        assertTrue("BufferRecyclers should have a public default constructor", found);
    }

    @Test
    public void testInstantiationSucceeds() {
        BufferRecyclers instance = new BufferRecyclers();
        assertNotNull("Instance of BufferRecyclers should not be null", instance);
    }

    /**
     * Verify that the class does not declare any fields.
     */
    @Test
    public void testHasNoDeclaredFields() {
        Field[] fields = BufferRecyclers.class.getDeclaredFields();
        assertEquals("BufferRecyclers should have no declared fields", 0, fields.length);
    }

    /**
     * Verify that the class resides in the expected package.
     */
    @Test
    public void testClassInExpectedPackage() {
        String pkgName = BufferRecyclers.class.getPackage().getName();
        assertEquals("BufferRecyclers should be in 'tools.jackson.core.util' package",
                     "tools.jackson.core.util", pkgName);
    }

    /**
     * Verify that all declared constructors are public and have zero parameters.
     */
    @Test
    public void testPublicConstructorsAreDefault() {
        Constructor<?>[] constructors = BufferRecyclers.class.getDeclaredConstructors();
        assertTrue("BufferRecyclers should declare at least one constructor", constructors.length > 0);
        for (Constructor<?> ctor : constructors) {
            assertTrue("All constructors should be public",
                       Modifier.isPublic(ctor.getModifiers()));
            assertEquals("All constructors should have zero parameters",
                         0, ctor.getParameterCount());
        }
    }

    /**
     * Verify that attempting to instantiate via reflection works and produces a non-null instance.
     */
    @Test
    public void testReflectionInstantiationSucceeds() throws Exception {
        Constructor<BufferRecyclers> ctor = BufferRecyclers.class.getDeclaredConstructor();
        assertTrue("Default constructor should be accessible", Modifier.isPublic(ctor.getModifiers()));
        BufferRecyclers instance = ctor.newInstance();
        assertNotNull("Instance created via reflection should not be null", instance);
    }

    /**
     * Verify that two distinct instances are not equal.
     */
    @Test
    public void testDistinctInstancesNotEqual() {
        BufferRecyclers a = new BufferRecyclers();
        BufferRecyclers b = new BufferRecyclers();
        assertNotEquals("Two different instances should not be equal", a, b);
    }

    /**
     * Verify the canonical name of the class.
     */
    @Test
    public void testCanonicalName() {
        String canonical = BufferRecyclers.class.getCanonicalName();
        assertEquals("tools.jackson.core.util.BufferRecyclers", canonical);
    }
}
