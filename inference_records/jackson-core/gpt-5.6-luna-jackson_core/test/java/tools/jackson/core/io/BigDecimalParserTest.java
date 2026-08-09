package tools.jackson.core.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;

import org.junit.Test;

public class BigDecimalParserTest {

    @Test
    public void parseStringUsesStandardParserForValuesBelowThreshold() {
        String value = "12345.678900";
        BigDecimal result = BigDecimalParser.parse(value);

        assertEquals(new BigDecimal(value), result);
    }

    @Test
    public void parseStringHandlesFastParserThreshold() {
        String belowThreshold = repeated('7', 499);
        String atThreshold = repeated('8', 500);

        assertEquals(new BigDecimal(belowThreshold), BigDecimalParser.parse(belowThreshold));
        assertEquals(new BigDecimal(atThreshold), BigDecimalParser.parse(atThreshold));
    }

    @Test
    public void parseStringHandlesVeryLargeDecimal() {
        String value = "1234567890" + repeated('9', 1490) + ".125";

        BigDecimal result = BigDecimalParser.parse(value);

        assertEquals(new BigDecimal(value), result);
    }

    @Test
    public void parseCharArrayWithOffsetAndLength() {
        char[] buffer = "prefix-12345.6789-suffix".toCharArray();

        BigDecimal result = BigDecimalParser.parse(buffer, 7, 10);

        assertEquals(new BigDecimal("12345.6789"), result);
    }

    @Test
    public void parseCharArrayWithoutOffsetParsesEntireArray() {
        char[] value = "987654321.00100".toCharArray();

        assertEquals(new BigDecimal("987654321.00100"), BigDecimalParser.parse(value));
    }

    @Test
    public void parseWithFastParserParsesStringAndCharArray() {
        String value = "42." + repeated('3', 600);
        char[] chars = ("ignored" + value + "ignored").toCharArray();

        assertEquals(new BigDecimal(value), BigDecimalParser.parseWithFastParser(value));
        assertEquals(new BigDecimal(value),
                BigDecimalParser.parseWithFastParser(chars, 7, value.length()));
    }

    @Test
    public void parseReportsInvalidShortString() {
        try {
            BigDecimalParser.parse("12x34");
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            assertTrue(e.getMessage().startsWith(
                    "Value \"12x34\" cannot be deserialized as `java.math.BigDecimal`, reason:"));
        }
    }

    @Test
    public void parseReportsInvalidLongStringWithTruncation() {
        String value = repeated('1', 1000) + "x";

        try {
            BigDecimalParser.parse(value);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            assertTrue(e.getMessage().contains(
                    "\""
                    + repeated('1', 1000)
                    + "\" (truncated to 1000 chars (from 1001))"));
            assertTrue(e.getMessage().startsWith(
                    "Value \"" + repeated('1', 1000)));
        }
    }

    @Test
    public void parseCharArrayReportsOnlyRequestedInvalidRange() {
        char[] buffer = ("prefix" + repeated('2', 1000) + "x" + "suffix").toCharArray();

        try {
            BigDecimalParser.parse(buffer, 6, 1001);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            assertTrue(e.getMessage().contains(
                    "\""
                    + repeated('2', 1000)
                    + "\" (truncated to 1000 chars (from 1001))"));
            assertTrue(e.getMessage().startsWith(
                    "Value \"" + repeated('2', 1000)));
            assertTrue(!e.getMessage().contains("prefix"));
            assertTrue(!e.getMessage().contains("suffix"));
        }
    }

    @Test
    public void parseWithFastParserReportsInvalidString() {
        try {
            BigDecimalParser.parseWithFastParser("3.14e+");
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            assertTrue(e.getMessage().contains("\"3.14e+\""));
            assertTrue(e.getMessage().startsWith(
                    "Value \"3.14e+\" cannot be deserialized as `java.math.BigDecimal`, reason:"));
        }
    }

    @Test
    public void parseWithFastParserReportsInvalidCharArrayRange() {
        char[] buffer = "before-9.9e+-after".toCharArray();

        try {
            BigDecimalParser.parseWithFastParser(buffer, 7, 5);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            assertTrue(e.getMessage().contains("\"9.9e+\""));
            assertTrue(!e.getMessage().contains("before"));
            assertTrue(!e.getMessage().contains("after"));
        }
    }

    @Test
    public void parseCharArrayUsesFastParserAtThreshold() {
        String value = repeated('6', 500);
        char[] buffer = ("prefix" + value + "suffix").toCharArray();

        assertEquals(new BigDecimal(value), BigDecimalParser.parse(buffer, 6, value.length()));
    }

    @Test
    public void parseReportsInvalidEmptyString() {
        try {
            BigDecimalParser.parse("");
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            assertTrue(e.getMessage().startsWith(
                    "Value \"\" cannot be deserialized as `java.math.BigDecimal`, reason:"));
        }
    }

    @Test
    public void stringParseFailureUsesFallbackForNullExceptionMessage() {
        NumberFormatException failure = invokeStringParseFailure(
                new NumberFormatException(), "invalid");

        assertEquals(
                "Value \"invalid\" cannot be deserialized as `java.math.BigDecimal`, reason:  "
                        + "Not a valid number representation",
                failure.getMessage());
    }

    @Test
    public void charArrayParseFailureUsesFallbackForNullExceptionMessage() {
        NumberFormatException failure = invokeCharArrayParseFailure(
                new NumberFormatException(), "prefixinvalidsuffix".toCharArray(), 6, 7);

        assertEquals(
                "Value \"invalid\" cannot be deserialized as `java.math.BigDecimal`, reason:  "
                        + "Not a valid number representation",
                failure.getMessage());
    }

    @Test
    public void parseStringUsesStandardParserForInvalidValueJustBelowThreshold() {
        String value = repeated('4', 498) + "x";
        String expectedMessage;

        try {
            new BigDecimal(value);
            fail("Expected NumberFormatException");
            return;
        } catch (NumberFormatException e) {
            expectedMessage = "Value \"" + value
                    + "\" cannot be deserialized as `java.math.BigDecimal`, reason:  "
                    + e.getMessage();
        }

        try {
            BigDecimalParser.parse(value);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            assertEquals(expectedMessage, e.getMessage());
        }
    }

    @Test
    public void parseStringUsesFastParserAtExactThresholdForInvalidValue() {
        String value = repeated('5', 499) + "x";
        String expectedMessage;

        try {
            BigDecimalParser.parseWithFastParser(value);
            fail("Expected NumberFormatException");
            return;
        } catch (NumberFormatException e) {
            expectedMessage = e.getMessage();
        }

        try {
            BigDecimalParser.parse(value);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            assertEquals(expectedMessage, e.getMessage());
        }
    }

    @Test
    public void parseCharArrayUsesStandardParserForInvalidValueJustBelowThreshold() {
        String value = repeated('6', 498) + "x";
        char[] chars = ("prefix" + value + "suffix").toCharArray();
        String expectedMessage;

        try {
            new BigDecimal(chars, 6, value.length());
            fail("Expected NumberFormatException");
            return;
        } catch (NumberFormatException e) {
            expectedMessage = "Value \"" + value
                    + "\" cannot be deserialized as `java.math.BigDecimal`, reason:  "
                    + e.getMessage();
        }

        try {
            BigDecimalParser.parse(chars, 6, value.length());
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            assertEquals(expectedMessage, e.getMessage());
        }
    }

    @Test
    public void parseCharArrayUsesFastParserAtExactThresholdForInvalidValue() {
        String value = repeated('7', 499) + "x";
        char[] chars = ("prefix" + value + "suffix").toCharArray();
        String expectedMessage;

        try {
            BigDecimalParser.parseWithFastParser(chars, 6, value.length());
            fail("Expected NumberFormatException");
            return;
        } catch (NumberFormatException e) {
            expectedMessage = e.getMessage();
        }

        try {
            BigDecimalParser.parse(chars, 6, value.length());
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            assertEquals(expectedMessage, e.getMessage());
        }
    }

    @Test
    public void stringValueDescriptionKeepsExactlyOneThousandCharacters() {
        String value = repeated('a', 1000);

        assertEquals("\"" + value + "\"", invokeStringValueDescription(value));
    }

    @Test
    public void stringValueDescriptionTruncatesAtOneThousandCharacters() {
        String value = repeated('b', 1001);

        assertEquals(
                "\"" + repeated('b', 1000) + "\" (truncated to 1000 chars (from 1001))",
                invokeStringValueDescription(value));
    }

    @Test
    public void charArrayValueDescriptionKeepsExactlyOneThousandCharacters() {
        String value = repeated('c', 1000);
        char[] chars = ("prefix" + value + "suffix").toCharArray();

        assertEquals("\"" + value + "\"",
                invokeCharArrayValueDescription(chars, 6, value.length()));
    }

    @Test
    public void charArrayValueDescriptionTruncatesAtOneThousandCharacters() {
        String value = repeated('d', 1001);
        char[] chars = ("prefix" + value + "suffix").toCharArray();

        assertEquals(
                "\"" + repeated('d', 1000) + "\" (truncated to 1000 chars (from 1001))",
                invokeCharArrayValueDescription(chars, 6, value.length()));
    }

    private static NumberFormatException invokeStringParseFailure(
            Exception cause, String value) {
        try {
            Method method = BigDecimalParser.class.getDeclaredMethod(
                    "_parseFailure", Exception.class, String.class);
            method.setAccessible(true);
            return (NumberFormatException) method.invoke(null, cause, value);
        } catch (InvocationTargetException e) {
            throw new AssertionError(e.getCause());
        } catch (Exception e) {
            throw new AssertionError(e);
        }
    }

    private static NumberFormatException invokeCharArrayParseFailure(
            Exception cause, char[] value, int offset, int length) {
        try {
            Method method = BigDecimalParser.class.getDeclaredMethod(
                    "_parseFailure", Exception.class, char[].class, int.class, int.class);
            method.setAccessible(true);
            return (NumberFormatException) method.invoke(
                    null, cause, value, offset, length);
        } catch (InvocationTargetException e) {
            throw new AssertionError(e.getCause());
        } catch (Exception e) {
            throw new AssertionError(e);
        }
    }

    private static String invokeStringValueDescription(String value) {
        try {
            Method method = BigDecimalParser.class.getDeclaredMethod(
                    "_getValueDesc", String.class);
            method.setAccessible(true);
            return (String) method.invoke(null, value);
        } catch (InvocationTargetException e) {
            throw new AssertionError(e.getCause());
        } catch (Exception e) {
            throw new AssertionError(e);
        }
    }

    private static String invokeCharArrayValueDescription(
            char[] value, int offset, int length) {
        try {
            Method method = BigDecimalParser.class.getDeclaredMethod(
                    "_getValueDesc", char[].class, int.class, int.class);
            method.setAccessible(true);
            return (String) method.invoke(null, value, offset, length);
        } catch (InvocationTargetException e) {
            throw new AssertionError(e.getCause());
        } catch (Exception e) {
            throw new AssertionError(e);
        }
    }

    private static String repeated(char character, int count) {
        StringBuilder builder = new StringBuilder(count);
        for (int i = 0; i < count; i++) {
            builder.append(character);
        }
        return builder.toString();
    }
}
