package org.apache.commons.cli;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;

import org.junit.Test;

public class UnrecognizedOptionExceptionTest {

    @Test
    public void testConstructorWithMessageOnly() {
        final String message = "Unrecognized option: --foo";
        final UnrecognizedOptionException exception = new UnrecognizedOptionException(message);

        assertEquals(message, exception.getMessage());
        assertNull(exception.getOption());
    }

    @Test
    public void testConstructorWithMessageAndOption() {
        final String message = "Unrecognized option: --foo";
        final String option = "--foo";
        final UnrecognizedOptionException exception = new UnrecognizedOptionException(message, option);

        assertEquals(message, exception.getMessage());
        assertEquals(option, exception.getOption());
    }

    @Test
    public void testConstructorWithNullOption() {
        final String message = "Unrecognized option";
        final UnrecognizedOptionException exception = new UnrecognizedOptionException(message, null);

        assertEquals(message, exception.getMessage());
        assertNull(exception.getOption());
    }

    @Test
    public void testConstructorWithEmptyStringOption() {
        final String message = "Unrecognized option";
        final String option = "";
        final UnrecognizedOptionException exception = new UnrecognizedOptionException(message, option);

        assertEquals(message, exception.getMessage());
        assertEquals(option, exception.getOption());
    }

    @Test
    public void testGetOptionReturnsSameReference() {
        final String option = "--test-option";
        final UnrecognizedOptionException exception = new UnrecognizedOptionException("message", option);

        assertSame(option, exception.getOption());
    }

    @Test
    public void testInheritanceFromParseException() {
        final String message = "Test message";
        final UnrecognizedOptionException exception = new UnrecognizedOptionException(message);

        assertTrue(exception instanceof ParseException);
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void testSerialVersionUID() throws NoSuchFieldException, IllegalAccessException {
        final Field field = UnrecognizedOptionException.class.getDeclaredField("serialVersionUID");
        field.setAccessible(true);
        assertEquals(-252504690284625623L, field.getLong(null));
    }

    @Test
    public void testExceptionCanBeThrownAndCaught() {
        final String message = "Unrecognized option: --bar";
        final String option = "--bar";

        try {
            throw new UnrecognizedOptionException(message, option);
        } catch (final UnrecognizedOptionException e) {
            assertEquals(message, e.getMessage());
            assertEquals(option, e.getOption());
        } catch (final ParseException e) {
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public void testExceptionWithLongOptionFormat() {
        final String message = "Unrecognized option: --very-long-option-name";
        final String option = "--very-long-option-name";
        final UnrecognizedOptionException exception = new UnrecognizedOptionException(message, option);

        assertEquals(message, exception.getMessage());
        assertEquals(option, exception.getOption());
    }

    @Test
    public void testExceptionWithShortOptionFormat() {
        final String message = "Unrecognized option: -x";
        final String option = "-x";
        final UnrecognizedOptionException exception = new UnrecognizedOptionException(message, option);

        assertEquals(message, exception.getMessage());
        assertEquals(option, exception.getOption());
    }

    @Test
    public void testSerializationRoundTrip() throws Exception {
        final String message = "Unrecognized option: --serialize-me";
        final String option = "--serialize-me";
        final UnrecognizedOptionException original = new UnrecognizedOptionException(message, option);

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(original);
        }

        final ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        UnrecognizedOptionException deserialized;
        try (ObjectInputStream ois = new ObjectInputStream(bais)) {
            deserialized = (UnrecognizedOptionException) ois.readObject();
        }

        assertNotNull(deserialized);
        assertEquals(message, deserialized.getMessage());
        assertEquals(option, deserialized.getOption());
        assertTrue(deserialized instanceof ParseException);
    }

    @Test
    public void testSerializationWithNullOption() throws Exception {
        final String message = "Unrecognized option with null";
        final UnrecognizedOptionException original = new UnrecognizedOptionException(message, null);

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(original);
        }

        final ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        UnrecognizedOptionException deserialized;
        try (ObjectInputStream ois = new ObjectInputStream(bais)) {
            deserialized = (UnrecognizedOptionException) ois.readObject();
        }

        assertNotNull(deserialized);
        assertEquals(message, deserialized.getMessage());
        assertNull(deserialized.getOption());
    }

    @Test
    public void testOptionFieldIsFinalAndImmutable() {
        final String option = "--immutable";
        final UnrecognizedOptionException exception = new UnrecognizedOptionException("msg", option);
        
        final String firstCall = exception.getOption();
        final String secondCall = exception.getOption();
        
        assertSame(firstCall, secondCall);
        assertSame(option, firstCall);
    }

    @Test
    public void testConstructorDelegationFromMessageOnlyToTwoArg() {
        final String message = "Delegated construction";
        final UnrecognizedOptionException exception = new UnrecognizedOptionException(message);
        
        assertEquals(message, exception.getMessage());
        assertNull(exception.getOption());
    }

    @Test
    public void testExceptionWithOptionContainingEqualsSign() {
        final String message = "Unrecognized option: --option=value";
        final String option = "--option=value";
        final UnrecognizedOptionException exception = new UnrecognizedOptionException(message, option);

        assertEquals(message, exception.getMessage());
        assertEquals(option, exception.getOption());
    }

    @Test
    public void testExceptionWithOptionContainingSpecialCharacters() {
        final String message = "Unrecognized option: --option_with.dots";
        final String option = "--option_with.dots";
        final UnrecognizedOptionException exception = new UnrecognizedOptionException(message, option);

        assertEquals(message, exception.getMessage());
        assertEquals(option, exception.getOption());
    }

    @Test
    public void testCaughtAsParseExceptionPreservesMessage() {
        final String message = "Caught as parent type";
        final String option = "--parent-catch";
        
        try {
            throw new UnrecognizedOptionException(message, option);
        } catch (ParseException e) {
            assertEquals(message, e.getMessage());
            assertTrue(e instanceof UnrecognizedOptionException);
            
            final UnrecognizedOptionException uoe = (UnrecognizedOptionException) e;
            assertEquals(option, uoe.getOption());
        }
    }

    @Test
    public void testGetOptionReturnsNullWhenConstructedWithSingleArg() {
        final UnrecognizedOptionException exception = new UnrecognizedOptionException("single arg constructor");
        
        assertNull(exception.getOption());
    }
}
