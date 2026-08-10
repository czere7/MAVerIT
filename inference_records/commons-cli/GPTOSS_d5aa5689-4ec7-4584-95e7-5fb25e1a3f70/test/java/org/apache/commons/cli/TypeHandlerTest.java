package org.apache.commons.cli;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.Converter;

public class TypeHandlerTest {

    /* ------------------------------ Helper methods ------------------------------ */

    private String validDateString() {
        // "Sun Mar 01 12:00:00 UTC 2020"
        return "Sun Mar 01 12:00:00 UTC 2020";
    }

    /* ------------------------------ Tests for createClass ------------------------------ */

    @Test
    public void testCreateClassValid() throws ParseException {
        Class<?> clazz = TypeHandler.createClass("java.lang.String");
        assertEquals(String.class, clazz);
    }

    @Test(expected = ParseException.class)
    public void testCreateClassInvalid() throws ParseException {
        TypeHandler.createClass("com.nonexistent.Class");
    }

    /* ------------------------------ Tests for createURL ------------------------------ */

    @Test
    public void testCreateURLValid() throws ParseException {
        URL url = TypeHandler.createURL("http://example.com");
        assertEquals("http://example.com", url.toString());
    }

    @Test(expected = ParseException.class)
    public void testCreateURLInvalid() throws ParseException {
        TypeHandler.createURL("ht!tp://invalid");
    }

    /* ------------------------------ Tests for createFile ------------------------------ */

    @Test
    public void testCreateFile() {
        File file = TypeHandler.createFile("some/path");
        assertEquals("some" + File.separator + "path", file.getPath());
    }

    /* ------------------------------ Tests for createValue ------------------------------ */

    @Test
    public void testCreateValueLong() throws ParseException {
        Long l = TypeHandler.createValue("123", Long.class);
        assertEquals(Long.valueOf(123L), l);
    }

    @Test
    public void testCreateValueInteger() throws ParseException {
        Integer i = TypeHandler.createValue("456", Integer.class);
        assertEquals(Integer.valueOf(456), i);
    }

    @Test
    public void testCreateValueShort() throws ParseException {
        Short s = TypeHandler.createValue("7", Short.class);
        assertEquals(Short.valueOf((short)7), s);
    }

    @Test
    public void testCreateValueByte() throws ParseException {
        Byte b = TypeHandler.createValue("8", Byte.class);
        assertEquals(Byte.valueOf((byte)8), b);
    }

    @Test
    public void testCreateValueDouble() throws ParseException {
        Double d = TypeHandler.createValue("3.14", Double.class);
        assertEquals(Double.valueOf(3.14), d);
    }

    @Test
    public void testCreateValueFloat() throws ParseException {
        Float f = TypeHandler.createValue("2.71", Float.class);
        assertEquals(Float.valueOf(2.71f), f);
    }

    @Test
    public void testCreateValueBigInteger() throws ParseException {
        BigInteger bi = TypeHandler.createValue("12345678901234567890", BigInteger.class);
        assertEquals(new BigInteger("12345678901234567890"), bi);
    }

    @Test
    public void testCreateValueBigDecimal() throws ParseException {
        BigDecimal bd = TypeHandler.createValue("123.456", BigDecimal.class);
        assertEquals(new BigDecimal("123.456"), bd);
    }

    @Test
    public void testCreateValueCharacterNormal() throws ParseException {
        Character c = TypeHandler.createValue("x", Character.class);
        assertEquals(Character.valueOf('x'), c);
    }

    @Test
    public void testCreateValueCharacterUnicode() throws ParseException {
        Character c = TypeHandler.createValue("\\u0041", Character.class);
        assertEquals(Character.valueOf('A'), c);
    }

    @Test
    public void testCreateValueCharacterUnicodeInvalid() throws ParseException {
        Character c = TypeHandler.createValue("\\uD800", Character.class);
        assertEquals(Character.valueOf('\uD800'), c);
    }

    @Test
    public void testCreateValueCharacterMultiple() throws ParseException {
        Character c = TypeHandler.createValue("ab", Character.class);
        assertEquals(Character.valueOf('a'), c);
    }

    @Test
    public void testCreateValueNumberWithDot() throws ParseException {
        Number n = TypeHandler.createValue("1.0", Number.class);
        assertTrue(n instanceof Double);
        assertEquals(1.0, n.doubleValue(), 0.00001);
    }

    @Test
    public void testCreateValueNumberWithoutDot() throws ParseException {
        Number n = TypeHandler.createValue("42", Number.class);
        assertTrue(n instanceof Long);
        assertEquals(42L, n.longValue());
    }

    @Test(expected = ParseException.class)
    public void testCreateValueNumberInvalid() throws ParseException {
        TypeHandler.createValue("abc", Number.class);
    }

    /* ------------------------------ Tests for getConverter ------------------------------ */

    @Test
    public void testGetConverterDefault() {
        Converter<Object, ?> converter = TypeHandler.getDefault().getConverter(Object.class);
        assertSame(Converter.OBJECT, converter);
    }

    @Test
    public void testGetConverterCustomMap() {
        Map<Class<?>, Converter<?, ?>> map = new HashMap<>();
        map.put(String.class, (Converter<String, ?>) String::toUpperCase);
        TypeHandler handler = new TypeHandler(map);
        @SuppressWarnings("unchecked")
        Converter<String, RuntimeException> conv = (Converter<String, RuntimeException>) handler.getConverter(String.class);
        assertEquals("ABC", conv.apply("abc"));
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorNullMap() {
        new TypeHandler((Map<Class<?>, Converter<?, ?>>) null);
    }

    /* ------------------------------ Tests for createObject ------------------------------ */

    @Test
    public void testCreateObjectValid() throws ParseException {
        Object o = TypeHandler.createObject("java.lang.String");
        assertTrue(o instanceof String);
        assertEquals("", o);
    }

    @Test(expected = ParseException.class)
    public void testCreateObjectInvalid() throws ParseException {
        TypeHandler.createObject("com.nonexistent.Class");
    }

    /* ------------------------------ Tests for createNumber ------------------------------ */

    @Test
    public void testCreateNumberValid() throws ParseException {
        Number n1 = TypeHandler.createNumber("123");
        assertTrue(n1 instanceof Long);
        assertEquals(123L, n1.longValue());

        Number n2 = TypeHandler.createNumber("1.23");
        assertTrue(n2 instanceof Double);
        assertEquals(1.23, n2.doubleValue(), 0.00001);
    }

    @Test(expected = ParseException.class)
    public void testCreateNumberInvalid() throws ParseException {
        TypeHandler.createNumber("abc");
    }

    /* ------------------------------ Tests for createDate ------------------------------ */

    @Test
    public void testCreateDateValid() {
        Date date = TypeHandler.createDate(validDateString());
        assertNotNull(date);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateDateInvalid() {
        TypeHandler.createDate("invalid-date");
    }

    /* ------------------------------ Tests for openFile ------------------------------ */

    @Test
    public void testOpenFile() throws IOException, ParseException {
        File temp = File.createTempFile("typehandler", ".tmp");
        temp.deleteOnExit();
        try (FileInputStream fis = TypeHandler.openFile(temp.getAbsolutePath())) {
            assertNotNull(fis);
        } finally {
            temp.delete();
        }
    }

    /* ------------------------------ Tests for default map contents ------------------------------ */

    @Test
    public void testDefaultMapContainsExpectedConverters() {
        TypeHandler handler = TypeHandler.getDefault();
        assertNotSame(Converter.DEFAULT, handler.getConverter(Long.class));
        assertNotSame(Converter.DEFAULT, handler.getConverter(Integer.class));
        assertNotSame(Converter.DEFAULT, handler.getConverter(Short.class));
        assertNotSame(Converter.DEFAULT, handler.getConverter(Byte.class));
        assertNotSame(Converter.DEFAULT, handler.getConverter(Double.class));
        assertNotSame(Converter.DEFAULT, handler.getConverter(Float.class));
        assertNotSame(Converter.DEFAULT, handler.getConverter(BigInteger.class));
        assertNotSame(Converter.DEFAULT, handler.getConverter(BigDecimal.class));
        assertNotSame(Converter.DEFAULT, handler.getConverter(Path.class));
        assertNotSame(Converter.DEFAULT, handler.getConverter(FileInputStream.class));
    }

    /* ------------------------------ Additional tests for uncovered branches ------------------------------ */

    @Test(expected = UnsupportedOperationException.class)
    public void testCreateFilesThrowsUnsupportedOperationException() {
        TypeHandler.createFiles("any");
    }

    @Test
    public void testCreateValueDateValid() throws ParseException {
        Date date = TypeHandler.createValue(validDateString(), Date.class);
        assertNotNull(date);
    }

    @Test(expected = ParseException.class)
    public void testCreateValueDateInvalid() throws ParseException {
        TypeHandler.createValue("invalid-date", Date.class);
    }

    @Test(expected = ParseException.class)
    public void testCreateValueCharacterInvalidUnicodeCodePoint() throws ParseException {
        TypeHandler.createValue("\\u110000", Character.class);
    }

    @Test(expected = ParseException.class)
    public void testCreateValueCharacterInvalidHexFormat() throws ParseException {
        TypeHandler.createValue("\\uXYZ", Character.class);
    }

    /* ------------------------------ New tests to kill surviving mutations ------------------------------ */

    @Test
    public void testCreateDefaultMapIsPopulated() {
        Map<Class<?>, Converter<?, ? extends Throwable>> defaultMap = TypeHandler.createDefaultMap();
        assertFalse("Default map should not be empty", defaultMap.isEmpty());
        assertEquals("Default map should contain 17 entries", 17, defaultMap.size());

        Class<?>[] expectedKeys = {
                Object.class, Class.class, Date.class, File.class,
                Path.class, Number.class, URL.class, FileInputStream.class,
                Long.class, Integer.class, Short.class, Byte.class,
                Character.class, Double.class, Float.class,
                BigInteger.class, BigDecimal.class
        };
        for (Class<?> key : expectedKeys) {
            assertTrue("Missing key: " + key, defaultMap.containsKey(key));
            assertNotNull("Converter for key " + key + " should not be null", defaultMap.get(key));
        }

        assertNotEquals("Default map should not be Collections.emptyMap()", Collections.emptyMap(), defaultMap);
    }

    @Test
    public void testCreateDefaultMapDateConverter() {
        Map<Class<?>, Converter<?, ? extends Throwable>> defaultMap = TypeHandler.createDefaultMap();
        @SuppressWarnings("unchecked")
        Converter<Date, ?> dateConverter = (Converter<Date, ?>) defaultMap.get(Date.class);
        assertNotNull("Date converter should not be null", dateConverter);
        try {
            Date parsed = (Date) dateConverter.apply(validDateString());
            assertNotNull("Parsed date should not be null", parsed);
        } catch (Exception e) {
            fail("Date converter threw exception: " + e);
        }
    }

    /* ------------------------------ Tests for overloaded createValue(String, Object) ------------------------------ */

    @Test
    public void testCreateValueOverloadedValid() throws ParseException {
        Object obj = TypeHandler.createValue("123", Integer.class);
        assertTrue(obj instanceof Integer);
        assertEquals(Integer.valueOf(123), obj);
    }

    @Test(expected = ParseException.class)
    public void testCreateValueOverloadedInvalid() throws ParseException {
        TypeHandler.createValue("abc", Integer.class);
    }

    @Test(expected = ParseException.class)
    public void testCreateValueOverloadedNullString() throws ParseException {
        TypeHandler.createValue(null, Integer.class);
    }

    @Test(expected = ParseException.class)
    public void testCreateValueOverloadedObjectClass() throws ParseException {
        TypeHandler.createValue("ignored", Object.class);
    }
}
