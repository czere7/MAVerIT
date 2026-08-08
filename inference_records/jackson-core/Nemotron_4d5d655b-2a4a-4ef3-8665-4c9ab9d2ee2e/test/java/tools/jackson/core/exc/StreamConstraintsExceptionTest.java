package tools.jackson.core.exc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;

import tools.jackson.core.TokenStreamLocation;
import tools.jackson.core.JacksonException;

public class StreamConstraintsExceptionTest {

    @Test
    public void testConstructorWithMessageOnly() {
        String msg = "Test constraint violation";
        StreamConstraintsException ex = new StreamConstraintsException(msg);
        
        assertTrue(ex.getMessage().startsWith(msg));
        assertNull(ex.getLocation());
    }

    @Test
    public void testConstructorWithMessageAndLocation() {
        String msg = "Test constraint violation with location";
        TokenStreamLocation loc = new TokenStreamLocation(null, 100L, 10, 5);
        
        StreamConstraintsException ex = new StreamConstraintsException(msg, loc);
        
        assertTrue(ex.getMessage().startsWith(msg));
        assertSame(loc, ex.getLocation());
    }

    @Test
    public void testConstructorWithNullLocation() {
        String msg = "Test with null location";
        StreamConstraintsException ex = new StreamConstraintsException(msg, null);
        
        assertTrue(ex.getMessage().startsWith(msg));
        assertNotNull(ex.getLocation());
    }

    @Test
    public void testInheritanceFromJacksonException() {
        StreamConstraintsException ex = new StreamConstraintsException("test");
        
        assertTrue(ex instanceof JacksonException);
        assertTrue(ex instanceof Exception);
    }

    @Test
    public void testSerialization() throws IOException, ClassNotFoundException {
        String msg = "Serialization test";
        TokenStreamLocation loc = new TokenStreamLocation(null, 50L, 200L, 5, 15);
        
        StreamConstraintsException original = new StreamConstraintsException(msg, loc);
        
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(original);
        oos.close();
        
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        StreamConstraintsException deserialized = (StreamConstraintsException) ois.readObject();
        ois.close();
        
        assertTrue(deserialized.getMessage().startsWith(msg));
        assertNotNull(deserialized.getLocation());
        assertEquals(loc.getLineNr(), deserialized.getLocation().getLineNr());
        assertEquals(loc.getColumnNr(), deserialized.getLocation().getColumnNr());
    }

    @Test
    public void testSerializationWithMessageOnly() throws IOException, ClassNotFoundException {
        String msg = "Message only serialization";
        StreamConstraintsException original = new StreamConstraintsException(msg);
        
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(original);
        oos.close();
        
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        StreamConstraintsException deserialized = (StreamConstraintsException) ois.readObject();
        ois.close();
        
        assertTrue(deserialized.getMessage().startsWith(msg));
        assertNull(deserialized.getLocation());
    }

    @Test
    public void testLocationWithVariousValues() {
        TokenStreamLocation loc1 = new TokenStreamLocation(null, -1L, 1000L, 100, 50);
        StreamConstraintsException ex1 = new StreamConstraintsException("test1", loc1);
        assertEquals(100, ex1.getLocation().getLineNr());
        assertEquals(50, ex1.getLocation().getColumnNr());

        TokenStreamLocation loc2 = new TokenStreamLocation(null, 500L, 2000L, 1, 1);
        StreamConstraintsException ex2 = new StreamConstraintsException("test2", loc2);
        assertEquals(1, ex2.getLocation().getLineNr());
        assertEquals(1, ex2.getLocation().getColumnNr());
    }

    @Test
    public void testThrowableCauseNotSetInConstructors() {
        StreamConstraintsException ex1 = new StreamConstraintsException("msg only");
        assertNull(ex1.getCause());

        TokenStreamLocation loc = new TokenStreamLocation(null, 0L, 1, 1);
        StreamConstraintsException ex2 = new StreamConstraintsException("msg with loc", loc);
        assertNull(ex2.getCause());
    }

    @Test
    public void testConstructorWithEmptyMessage() {
        StreamConstraintsException ex = new StreamConstraintsException("");
        
        assertTrue(ex.getMessage().startsWith(""));
        assertNull(ex.getLocation());
    }

    @Test
    public void testExceptionThrownAndCaught() {
        try {
            throw new StreamConstraintsException("thrown exception");
        } catch (StreamConstraintsException e) {
            assertTrue(e.getMessage().startsWith("thrown exception"));
            assertNull(e.getLocation());
        }
    }

    @Test
    public void testExceptionThrownAndCaughtWithLocation() {
        TokenStreamLocation loc = new TokenStreamLocation(null, 50L, 100L, 5, 10);
        try {
            throw new StreamConstraintsException("thrown with location", loc);
        } catch (StreamConstraintsException e) {
            assertTrue(e.getMessage().startsWith("thrown with location"));
            assertSame(loc, e.getLocation());
        }
    }

    @Test
    public void testConstructorWithMaxIntLineAndColumn() {
        TokenStreamLocation loc = new TokenStreamLocation(null, Long.MAX_VALUE, Long.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
        StreamConstraintsException ex = new StreamConstraintsException("max values", loc);
        
        assertEquals(Integer.MAX_VALUE, ex.getLocation().getLineNr());
        assertEquals(Integer.MAX_VALUE, ex.getLocation().getColumnNr());
    }

    @Test
    public void testConstructorWithNegativeLineAndColumn() {
        TokenStreamLocation loc = new TokenStreamLocation(null, -1L, -1L, -10, -20);
        StreamConstraintsException ex = new StreamConstraintsException("negative values", loc);
        
        assertEquals(-10, ex.getLocation().getLineNr());
        assertEquals(-20, ex.getLocation().getColumnNr());
    }
}
