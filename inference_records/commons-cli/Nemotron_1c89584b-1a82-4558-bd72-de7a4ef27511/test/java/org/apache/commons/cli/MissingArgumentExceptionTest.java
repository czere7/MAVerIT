package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;

public class MissingArgumentExceptionTest {

    @Test
    public void testConstructorWithOption() {
        Option option = new Option("f", "file", true, "input file");
        MissingArgumentException exception = new MissingArgumentException(option);

        assertEquals("Missing argument for option: f", exception.getMessage());
        assertSame(option, exception.getOption());
    }

    @Test
    public void testConstructorWithOptionLongOnly() {
        Option option = new Option(null, "file", true, "input file");
        MissingArgumentException exception = new MissingArgumentException(option);

        assertEquals("Missing argument for option: file", exception.getMessage());
        assertSame(option, exception.getOption());
    }

    @Test
    public void testConstructorWithOptionUsingBuilder() {
        Option option = Option.builder("o")
                .longOpt("output")
                .hasArg()
                .desc("output file")
                .build();
        MissingArgumentException exception = new MissingArgumentException(option);

        assertEquals("Missing argument for option: o", exception.getMessage());
        assertSame(option, exception.getOption());
    }

    @Test
    public void testConstructorWithStringMessage() {
        String message = "Custom error message";
        MissingArgumentException exception = new MissingArgumentException(message);

        assertEquals(message, exception.getMessage());
        assertNull(exception.getOption());
    }

    @Test
    public void testConstructorWithOptionStoresOptionReference() {
        Option option = new Option("v", "verbose", false, "verbose mode");
        MissingArgumentException exception = new MissingArgumentException(option);

        assertSame(option, exception.getOption());
    }

    @Test
    public void testInheritanceFromParseException() {
        Option option = new Option("t", "test", true, "test option");
        MissingArgumentException exception = new MissingArgumentException(option);

        assertTrue(exception instanceof ParseException);
        assertTrue(exception instanceof Exception);
    }

    @Test
    public void testGetOptionReturnsNullWhenConstructedWithString() {
        MissingArgumentException exception = new MissingArgumentException("test message");

        assertNull(exception.getOption());
    }

    @Test
    public void testSerialization() throws Exception {
        Option option = new Option("s", "serial", true, "serializable option");
        MissingArgumentException original = new MissingArgumentException(option);

        java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
        java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(baos);
        oos.writeObject(original);
        oos.close();

        java.io.ByteArrayInputStream bais = new java.io.ByteArrayInputStream(baos.toByteArray());
        java.io.ObjectInputStream ois = new java.io.ObjectInputStream(bais);
        MissingArgumentException deserialized = (MissingArgumentException) ois.readObject();
        ois.close();

        assertEquals(original.getMessage(), deserialized.getMessage());
        assertNotNull(deserialized.getOption());
        assertEquals(option.getKey(), deserialized.getOption().getKey());
        assertEquals(option.getDescription(), deserialized.getOption().getDescription());
    }

    @Test
    public void testExceptionCanBeThrownAndCaught() {
        Option option = new Option("x", "example", true, "example option");

        try {
            throw new MissingArgumentException(option);
        } catch (MissingArgumentException e) {
            assertEquals("Missing argument for option: x", e.getMessage());
            assertSame(option, e.getOption());
        } catch (ParseException e) {
            fail("Should be caught as MissingArgumentException");
        }
    }

    @Test
    public void testExceptionCanBeCaughtAsParseException() {
        Option option = new Option("y", "yopt", true, "y option");

        try {
            throw new MissingArgumentException(option);
        } catch (ParseException e) {
            assertEquals("Missing argument for option: y", e.getMessage());
        }
    }

    @Test
    public void testMessageFormatWithOptionContainingSpecialCharacters() {
        Option option = new Option("d", "dash", true, "dash option");
        MissingArgumentException exception = new MissingArgumentException(option);

        assertEquals("Missing argument for option: d", exception.getMessage());
    }

    @Test
    public void testOptionWithLongOptOnly() {
        Option option = new Option(null, "long-only", true, "long only option");
        MissingArgumentException exception = new MissingArgumentException(option);

        assertEquals("Missing argument for option: long-only", exception.getMessage());
        assertSame(option, exception.getOption());
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorWithNullOptionThrowsNPE() {
        new MissingArgumentException((Option) null);
    }

    @Test
    public void testMessageFormatWithUnicodeCharacters() {
        Option option = new Option("ü", "ünicode", true, "unicode option");
        MissingArgumentException exception = new MissingArgumentException(option);

        assertEquals("Missing argument for option: ü", exception.getMessage());
    }

    @Test
    public void testMessageFormatWithLongOptContainingSpecialCharacters() {
        Option option = new Option(null, "long-opt_with.special~chars", true, "special long opt");
        MissingArgumentException exception = new MissingArgumentException(option);

        assertEquals("Missing argument for option: long-opt_with.special~chars", exception.getMessage());
    }

    @Test
    public void testGetOptionReturnsSameInstance() {
        Option option = new Option("z", "zeta", true, "zeta option");
        MissingArgumentException exception = new MissingArgumentException(option);

        assertSame(option, exception.getOption());
        assertSame(option, exception.getOption());
    }

    @Test
    public void testConstructorWithStringDoesNotSetOptionField() {
        MissingArgumentException exception = new MissingArgumentException("test");

        assertNull(exception.getOption());
        assertEquals("test", exception.getMessage());
    }

    @Test
    public void testOptionWithBothOptAndLongOptUsesShortOpt() {
        Option option = new Option("short", "long", true, "both options");
        MissingArgumentException exception = new MissingArgumentException(option);

        assertEquals("Missing argument for option: short", exception.getMessage());
        assertSame(option, exception.getOption());
    }
}
