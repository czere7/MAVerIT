package org.apache.commons.cli;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.Map;

import org.junit.Test;

public class TypeHandlerTest {

    @Test
    public void testCreateDefaultMapContainsBuiltInConverters() {
        Map<Class<?>, Converter<?, ? extends Throwable>> map = TypeHandler.createDefaultMap();

        assertTrue(map.containsKey(Object.class));
        assertTrue(map.containsKey(Class.class));
        assertTrue(map.containsKey(Date.class));
        assertTrue(map.containsKey(File.class));
        assertTrue(map.containsKey(Path.class));
        assertTrue(map.containsKey(Number.class));
        assertTrue(map.containsKey(URL.class));
        assertTrue(map.containsKey(FileInputStream.class));
        assertTrue(map.containsKey(Long.class));
        assertTrue(map.containsKey(Integer.class));
        assertTrue(map.containsKey(Character.class));
        assertTrue(map.containsKey(BigInteger.class));
        assertTrue(map.containsKey(BigDecimal.class));
    }

    @Test
    public void testGetConverterReturnsRegisteredConverter() {
        Converter<String, RuntimeException> converter = value -> value.toUpperCase();
        TypeHandler handler = new TypeHandler();

        handler = new TypeHandler(TypeHandler.createDefaultMap());
        handler.getConverter(String.class);

        Map<Class<?>, Converter<?, ? extends Throwable>> map = TypeHandler.createDefaultMap();
        map.put(String.class, converter);
        handler = new TypeHandler(map);

        assertSame(converter, handler.getConverter(String.class));
    }

    @Test
    public void testGetConverterReturnsDefaultForUnknownClass() {
        TypeHandler handler = new TypeHandler();

        assertSame(Converter.DEFAULT, handler.getConverter(String.class));
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorRejectsNullConverterMap() {
        new TypeHandler(null);
    }

    @Test
    public void testCreateValueConvertsPrimitiveWrapperTypes() throws Exception {
        assertEquals(Long.valueOf(42), TypeHandler.createValue("42", Long.class));
        assertEquals(Integer.valueOf(42), TypeHandler.createValue("42", Integer.class));
        assertEquals(Short.valueOf("7"), TypeHandler.createValue("7", Short.class));
        assertEquals(Byte.valueOf("3"), TypeHandler.createValue("3", Byte.class));
        assertEquals(Double.valueOf("3.5"), TypeHandler.createValue("3.5", Double.class));
        assertEquals(Float.valueOf("2.5"), TypeHandler.createValue("2.5", Float.class));
        assertEquals(new BigInteger("12345678901234567890"),
                TypeHandler.createValue("12345678901234567890", BigInteger.class));
        assertEquals(new BigDecimal("10.25"),
                TypeHandler.createValue("10.25", BigDecimal.class));
    }

    @Test
    public void testCreateNumberChoosesLongOrDouble() throws Exception {
        assertEquals(Long.valueOf(12), TypeHandler.createNumber("12"));
        assertEquals(Double.valueOf(12.5), TypeHandler.createNumber("12.5"));
    }

    @Test
    public void testCreateCharacterSupportsLiteralAndUnicodeValues() throws Exception {
        assertEquals(Character.valueOf('a'), TypeHandler.createValue("a", Character.class));
        assertEquals(Character.valueOf('A'), TypeHandler.createValue("\\u0041", Character.class));
    }

    @Test(expected = ParseException.class)
    public void testCreateValueRejectsInvalidNumber() throws Exception {
        TypeHandler.createValue("not-a-number", Integer.class);
    }

    @Test(expected = ParseException.class)
    public void testCreateNumberRejectsInvalidNumber() throws Exception {
        TypeHandler.createNumber("12x");
    }

    @Test(expected = ParseException.class)
    public void testCreateValueRejectsUnicodeCodePointOutsideBmp() throws Exception {
        TypeHandler.createValue("\\u10000", Character.class);
    }

    @Test(expected = ParseException.class)
    public void testCreateValueRejectsEmptyCharacter() throws Exception {
        TypeHandler.createValue("", Character.class);
    }

    @Test
    public void testCreateFileAndPath() throws Exception {
        File file = TypeHandler.createFile("example.txt");
        Path path = TypeHandler.createValue("example.txt", Path.class);

        assertEquals("example.txt", file.getPath());
        assertEquals("example.txt", path.toString());
    }

    @Test
    public void testCreateClass() throws Exception {
        assertEquals(String.class, TypeHandler.createClass("java.lang.String"));
    }

    @Test(expected = ParseException.class)
    public void testCreateClassRejectsUnknownClass() throws Exception {
        TypeHandler.createClass("no.such.Class");
    }

    @Test
    public void testCreateObjectUsesPublicNoArgumentConstructor() throws Exception {
        Object value = TypeHandler.createObject("java.lang.StringBuilder");

        assertNotNull(value);
        assertTrue(value instanceof StringBuilder);
    }

    @Test
    public void testCreateUrl() throws Exception {
        URL url = TypeHandler.createURL("https://example.com/path");

        assertEquals("https", url.getProtocol());
        assertEquals("example.com", url.getHost());
        assertEquals("/path", url.getPath());
    }

    @Test(expected = ParseException.class)
    public void testCreateUrlRejectsMalformedUrl() throws Exception {
        TypeHandler.createURL("://invalid");
    }

    @Test
    public void testCreateDateParsesDocumentedFormat() {
        Date date = TypeHandler.createDate("Thu Jan 01 00:00:00 UTC 1970");

        assertNotNull(date);
        assertEquals(0L, date.getTime());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateDateRejectsInvalidDate() {
        TypeHandler.createDate("not a date");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateDateRejectsTrailingText() {
        TypeHandler.createDate("Thu Jan 01 00:00:00 UTC 1970 trailing");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testCreateFilesIsNotImplemented() {
        TypeHandler.createFiles("example.txt");
    }

    @Test
    public void testCreateValueUsesIdentityConverterForUnknownType() throws Exception {
        Object value = TypeHandler.createValue("unchanged", String.class);

        assertEquals("unchanged", value);
    }

    @Test
    public void testCreateValueAcceptsNullForIdentityConverter() throws Exception {
        assertEquals(null, TypeHandler.createValue(null, String.class));
    }

    @Test
    public void testCreateValueObjectOverload() throws Exception {
        assertEquals(Integer.valueOf(9), TypeHandler.createValue("9", Integer.class));
    }

    @Test
    public void testDefaultTypeHandlerIsSingleton() {
        assertSame(TypeHandler.getDefault(), TypeHandler.getDefault());
        assertNotNull(TypeHandler.getDefault());
    }

    @Test(expected = ParseException.class)
    public void testOpenFileRejectsMissingFile() throws Exception {
        TypeHandler.openFile("file-that-does-not-exist-commons-cli-test");
    }

    @Test
    public void testDefaultConverterMapCanBeCustomized() throws Exception {
        Map<Class<?>, Converter<?, ? extends Throwable>> map = TypeHandler.createDefaultMap();
        map.put(String.class, value -> "converted:" + value);

        TypeHandler handler = new TypeHandler(map);

        assertEquals("converted:value", handler.getConverter(String.class).apply("value"));
        assertFalse(handler.getConverter(String.class) == Converter.DEFAULT);
    }

    @Test
    public void testCreateValueReturnsConvertedValue() throws Exception {
        String value = TypeHandler.createValue("converted", String.class);

        assertNotNull(value);
        assertEquals("converted", value);
    }

    @Test
    public void testCreateValueReturnsConcreteValueForSeveralConverterTypes() throws Exception {
        File file = TypeHandler.createValue("result.txt", File.class);
        Class<?> type = TypeHandler.createValue("java.lang.Integer", Class.class);
        URL url = TypeHandler.createValue("https://example.com/result", URL.class);
        Number number = TypeHandler.createValue("17", Number.class);

        assertNotNull(file);
        assertEquals("result.txt", file.getPath());
        assertSame(Integer.class, type);
        assertNotNull(url);
        assertEquals("https://example.com/result", url.toString());
        assertNotNull(number);
        assertEquals(Long.valueOf(17), number);
    }

    @Test
    public void testCreateValueObjectOverloadReturnsConvertedObject() throws Exception {
        Object value = TypeHandler.createValue("java.lang.StringBuilder", Object.class);

        assertNotNull(value);
        assertTrue(value instanceof StringBuilder);
    }

    @Test
    public void testOpenFileReturnsReadableFileInputStream() throws Exception {
        File file = File.createTempFile("commons-cli-type-handler", ".txt");
        try {
            Files.write(file.toPath(), new byte[] { 'x' });

            FileInputStream input = TypeHandler.openFile(file.getAbsolutePath());

            try {
                assertNotNull(input);
                assertEquals('x', input.read());
            } finally {
                input.close();
            }
        } finally {
            assertTrue(file.delete());
        }
    }
}
