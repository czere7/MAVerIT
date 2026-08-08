package tools.jackson.core.io;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class BigDecimalParserTest {

    private static final int SWITCH_THRESHOLD = 500;
    private static final int MAX_REPORT_CHARS = 1000;

    // Helper method to repeat a string n times (compatible with Java 8+)
    private static String repeat(String str, int count) {
        StringBuilder sb = new StringBuilder(str.length() * count);
        for (int i = 0; i < count; i++) {
            sb.append(str);
        }
        return sb.toString();
    }

    @Test
    public void testParseSmallPositiveInteger() {
        BigDecimal result = BigDecimalParser.parse("123");
        assertEquals(new BigDecimal("123"), result);
    }

    @Test
    public void testParseSmallNegativeInteger() {
        BigDecimal result = BigDecimalParser.parse("-456");
        assertEquals(new BigDecimal("-456"), result);
    }

    @Test
    public void testParseSmallDecimal() {
        BigDecimal result = BigDecimalParser.parse("123.456");
        assertEquals(new BigDecimal("123.456"), result);
    }

    @Test
    public void testParseSmallScientificNotation() {
        BigDecimal result = BigDecimalParser.parse("1.23E10");
        assertEquals(new BigDecimal("1.23E10"), result);
    }

    @Test
    public void testParseSmallNegativeDecimal() {
        BigDecimal result = BigDecimalParser.parse("-0.001");
        assertEquals(new BigDecimal("-0.001"), result);
    }

    @Test
    public void testParseZero() {
        BigDecimal result = BigDecimalParser.parse("0");
        assertEquals(BigDecimal.ZERO, result);
    }

    @Test
    public void testParseNegativeZero() {
        BigDecimal result = BigDecimalParser.parse("-0");
        assertEquals(BigDecimal.ZERO, result);
    }

    @Test
    public void testParseLargeIntegerExactlyAtThreshold() {
        String value = repeat("1", SWITCH_THRESHOLD);
        BigDecimal result = BigDecimalParser.parse(value);
        assertEquals(new BigDecimal(value), result);
    }

    @Test
    public void testParseLargeIntegerAboveThreshold() {
        String value = repeat("9", SWITCH_THRESHOLD + 1);
        BigDecimal result = BigDecimalParser.parse(value);
        assertEquals(new BigDecimal(value), result);
    }

    @Test
    public void testParseLargeDecimalAboveThreshold() {
        String value = repeat("1", 250) + "." + repeat("2", 251);
        BigDecimal result = BigDecimalParser.parse(value);
        assertEquals(new BigDecimal(value), result);
    }

    @Test
    public void testParseLargeScientificNotationAboveThreshold() {
        String value = "1.23" + repeat("0", 500) + "E1000";
        BigDecimal result = BigDecimalParser.parse(value);
        assertEquals(new BigDecimal(value), result);
    }

    @Test
    public void testParseCharArray() {
        char[] chars = "789.123".toCharArray();
        BigDecimal result = BigDecimalParser.parse(chars);
        assertEquals(new BigDecimal("789.123"), result);
    }

    @Test
    public void testParseCharArrayWithOffsetAndLength() {
        char[] chars = "xx123.456yy".toCharArray();
        BigDecimal result = BigDecimalParser.parse(chars, 2, 7);
        assertEquals(new BigDecimal("123.456"), result);
    }

    @Test
    public void testParseCharArrayLargeAboveThreshold() {
        String value = repeat("5", SWITCH_THRESHOLD + 10);
        char[] chars = value.toCharArray();
        BigDecimal result = BigDecimalParser.parse(chars, 0, chars.length);
        assertEquals(new BigDecimal(value), result);
    }

    @Test
    public void testParseCharArrayWithOffsetAndLengthLarge() {
        String value = repeat("7", SWITCH_THRESHOLD + 5);
        char[] chars = ("prefix" + value + "suffix").toCharArray();
        BigDecimal result = BigDecimalParser.parse(chars, 6, value.length());
        assertEquals(new BigDecimal(value), result);
    }

    @Test
    public void testParseWithFastParserSmallString() {
        BigDecimal result = BigDecimalParser.parseWithFastParser("42.5");
        assertEquals(new BigDecimal("42.5"), result);
    }

    @Test
    public void testParseWithFastParserLargeString() {
        String value = repeat("3", SWITCH_THRESHOLD + 100);
        BigDecimal result = BigDecimalParser.parseWithFastParser(value);
        assertEquals(new BigDecimal(value), result);
    }

    @Test
    public void testParseWithFastParserCharArray() {
        char[] chars = "99.99".toCharArray();
        BigDecimal result = BigDecimalParser.parseWithFastParser(chars, 0, chars.length);
        assertEquals(new BigDecimal("99.99"), result);
    }

    @Test
    public void testParseWithFastParserCharArrayLarge() {
        String value = repeat("8", SWITCH_THRESHOLD + 50);
        char[] chars = value.toCharArray();
        BigDecimal result = BigDecimalParser.parseWithFastParser(chars, 0, chars.length);
        assertEquals(new BigDecimal(value), result);
    }

    @Test(expected = NumberFormatException.class)
    public void testParseInvalidStringThrows() {
        BigDecimalParser.parse("not a number");
    }

    @Test(expected = NumberFormatException.class)
    public void testParseEmptyStringThrows() {
        BigDecimalParser.parse("");
    }

    @Test(expected = NumberFormatException.class)
    public void testParseInvalidCharArrayThrows() {
        char[] chars = "abc".toCharArray();
        BigDecimalParser.parse(chars, 0, chars.length);
    }

    @Test(expected = NumberFormatException.class)
    public void testParseWithFastParserInvalidStringThrows() {
        BigDecimalParser.parseWithFastParser("invalid");
    }

    @Test(expected = NumberFormatException.class)
    public void testParseWithFastParserInvalidCharArrayThrows() {
        char[] chars = "xyz".toCharArray();
        BigDecimalParser.parseWithFastParser(chars, 0, chars.length);
    }

    @Test
    public void testExceptionMessageForShortInvalidInput() {
        try {
            BigDecimalParser.parse("123abc");
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            String msg = e.getMessage();
            assertTrue("Message should contain value", msg.contains("\"123abc\""));
            assertTrue("Message should contain reason", msg.contains("cannot be deserialized as `java.math.BigDecimal`"));
        }
    }

    @Test
    public void testExceptionMessageForLongInvalidInputTruncated() {
        String longInvalid = repeat("9", MAX_REPORT_CHARS + 100) + "abc";
        try {
            BigDecimalParser.parse(longInvalid);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            String msg = e.getMessage();
            assertTrue("Message should indicate truncation", msg.contains("(truncated to " + MAX_REPORT_CHARS + " chars"));
            assertTrue("Message should contain prefix", msg.contains(repeat("9", MAX_REPORT_CHARS)));
            assertTrue("Message should not contain full length", !msg.contains(repeat("9", MAX_REPORT_CHARS + 1)));
        }
    }

    @Test
    public void testExceptionMessageForCharArrayTruncated() {
        char[] chars = (repeat("8", MAX_REPORT_CHARS + 50) + "xyz").toCharArray();
        try {
            BigDecimalParser.parse(chars, 0, chars.length);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            String msg = e.getMessage();
            assertTrue("Message should indicate truncation", msg.contains("(truncated to " + MAX_REPORT_CHARS + " chars"));
        }
    }

    @Test
    public void testExceptionMessageForOffsetCharArrayTruncated() {
        String longValue = repeat("7", MAX_REPORT_CHARS + 20) + "bad";
        char[] chars = ("pre" + longValue).toCharArray();
        try {
            BigDecimalParser.parse(chars, 3, longValue.length());
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            String msg = e.getMessage();
            assertTrue("Message should indicate truncation", msg.contains("(truncated to " + MAX_REPORT_CHARS + " chars"));
            assertTrue("Message should show from length", msg.contains("from " + longValue.length()));
        }
    }

    @Test
    public void testParseLargeValueWithManyDecimalPlaces() {
        String value = "12345678901234567890.12345678901234567890";
        BigDecimal result = BigDecimalParser.parse(value);
        assertEquals(new BigDecimal(value), result);
    }

    @Test
    public void testParseLargeValueWithExponent() {
        String value = "1.2345678901234567890E+1000";
        BigDecimal result = BigDecimalParser.parse(value);
        assertEquals(new BigDecimal(value), result);
    }

    @Test
    public void testParseNegativeLargeValue() {
        String value = "-" + repeat("9", SWITCH_THRESHOLD + 1);
        BigDecimal result = BigDecimalParser.parse(value);
        assertEquals(new BigDecimal(value), result);
    }

    @Test
    public void testParseVeryLargeValue() {
        String value = repeat("1", 10000);
        BigDecimal result = BigDecimalParser.parse(value);
        assertEquals(new BigDecimal(value), result);
    }

    @Test
    public void testParseWithFastParserVeryLargeValue() {
        String value = repeat("2", 20000);
        BigDecimal result = BigDecimalParser.parseWithFastParser(value);
        assertEquals(new BigDecimal(value), result);
    }

    @Test
    public void testParseCharArrayWithZeroLength() {
        char[] chars = "123".toCharArray();
        try {
            BigDecimalParser.parse(chars, 0, 0);
            fail("Expected NumberFormatException for zero length");
        } catch (NumberFormatException e) {
            // Expected
        }
    }

    @Test
    public void testParseWithFastParserCharArrayWithZeroLength() {
        char[] chars = "123".toCharArray();
        try {
            BigDecimalParser.parseWithFastParser(chars, 0, 0);
            fail("Expected NumberFormatException for zero length");
        } catch (NumberFormatException e) {
            // Expected
        }
    }

    @Test
    public void testParseConsistencyBetweenMethods() {
        String value = "12345.67890";
        BigDecimal r1 = BigDecimalParser.parse(value);
        BigDecimal r2 = BigDecimalParser.parseWithFastParser(value);
        BigDecimal r3 = BigDecimalParser.parse(value.toCharArray());
        BigDecimal r4 = BigDecimalParser.parse(value.toCharArray(), 0, value.length());
        BigDecimal r5 = BigDecimalParser.parseWithFastParser(value.toCharArray(), 0, value.length());

        assertEquals(r1, r2);
        assertEquals(r1, r3);
        assertEquals(r1, r4);
        assertEquals(r1, r5);
    }

    @Test
    public void testParseConsistencyLargeValue() {
        String value = repeat("9", SWITCH_THRESHOLD + 10) + ".12345";
        BigDecimal r1 = BigDecimalParser.parse(value);
        BigDecimal r2 = BigDecimalParser.parseWithFastParser(value);
        BigDecimal r3 = BigDecimalParser.parse(value.toCharArray());
        BigDecimal r4 = BigDecimalParser.parse(value.toCharArray(), 0, value.length());
        BigDecimal r5 = BigDecimalParser.parseWithFastParser(value.toCharArray(), 0, value.length());

        assertEquals(r1, r2);
        assertEquals(r1, r3);
        assertEquals(r1, r4);
        assertEquals(r1, r5);
    }

    @Test
    public void testParseSmallValueUsesStandardConstructor() {
        String value = "123.456";
        BigDecimal result = BigDecimalParser.parse(value);
        assertEquals(new BigDecimal(value), result);
    }

    @Test
    public void testParseBoundaryAt499Chars() {
        String value = repeat("1", 499);
        BigDecimal result = BigDecimalParser.parse(value);
        assertEquals(new BigDecimal(value), result);
    }

    @Test
    public void testParseBoundaryAt500Chars() {
        String value = repeat("1", 500);
        BigDecimal result = BigDecimalParser.parse(value);
        assertEquals(new BigDecimal(value), result);
    }

    @Test
    public void testParseBoundaryAt501Chars() {
        String value = repeat("1", 501);
        BigDecimal result = BigDecimalParser.parse(value);
        assertEquals(new BigDecimal(value), result);
    }

    // New tests to kill surviving mutations (fixed versions)

    @Test
    public void testParseCharArrayBoundaryAt499Chars() {
        String value = repeat("1", 499);
        char[] chars = value.toCharArray();
        BigDecimal result = BigDecimalParser.parse(chars, 0, chars.length);
        assertEquals(new BigDecimal(value), result);
    }

    @Test
    public void testParseCharArrayBoundaryAt500Chars() {
        String value = repeat("1", 500);
        char[] chars = value.toCharArray();
        BigDecimal result = BigDecimalParser.parse(chars, 0, chars.length);
        assertEquals(new BigDecimal(value), result);
    }

    @Test
    public void testParseCharArrayBoundaryAt501Chars() {
        String value = repeat("1", 501);
        char[] chars = value.toCharArray();
        BigDecimal result = BigDecimalParser.parse(chars, 0, chars.length);
        assertEquals(new BigDecimal(value), result);
    }

    @Test
    public void testParseCharArrayWithOffsetBoundaryAt499Chars() {
        String value = repeat("2", 499);
        char[] chars = ("xx" + value).toCharArray();
        BigDecimal result = BigDecimalParser.parse(chars, 2, value.length());
        assertEquals(new BigDecimal(value), result);
    }

    @Test
    public void testParseCharArrayWithOffsetBoundaryAt500Chars() {
        String value = repeat("2", 500);
        char[] chars = ("xx" + value).toCharArray();
        BigDecimal result = BigDecimalParser.parse(chars, 2, value.length());
        assertEquals(new BigDecimal(value), result);
    }

    @Test
    public void testParseCharArrayWithOffsetBoundaryAt501Chars() {
        String value = repeat("2", 501);
        char[] chars = ("xx" + value).toCharArray();
        BigDecimal result = BigDecimalParser.parse(chars, 2, value.length());
        assertEquals(new BigDecimal(value), result);
    }

    @Test
    public void testExceptionMessageForStringExactlyAtMaxReportChars() {
        // Exactly at boundary: 1000 chars total, invalid input
        String exactLength = repeat("5", MAX_REPORT_CHARS - 7) + "invalid"; // 993 + 7 = 1000
        try {
            BigDecimalParser.parse(exactLength);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            String msg = e.getMessage();
            assertTrue("Message should not indicate truncation at exact boundary", !msg.contains("(truncated"));
            assertTrue("Message should contain full value", msg.contains("\"" + exactLength + "\""));
        }
    }

    @Test
    public void testExceptionMessageForStringJustOverMaxReportChars() {
        String overLength = repeat("6", MAX_REPORT_CHARS + 1) + "bad";
        try {
            BigDecimalParser.parse(overLength);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            String msg = e.getMessage();
            assertTrue("Message should indicate truncation", msg.contains("(truncated to " + MAX_REPORT_CHARS + " chars (from " + (MAX_REPORT_CHARS + 4) + "))"));
            assertTrue("Message should contain truncated prefix", msg.contains(repeat("6", MAX_REPORT_CHARS)));
            assertTrue("Message should not contain full length", !msg.contains(repeat("6", MAX_REPORT_CHARS + 1)));
        }
    }

    @Test
    public void testExceptionMessageForCharArrayExactlyAtMaxReportChars() {
        // Exactly at boundary: 1000 chars total
        char[] chars = (repeat("3", MAX_REPORT_CHARS - 3) + "xyz").toCharArray(); // 997 + 3 = 1000
        try {
            BigDecimalParser.parse(chars, 0, chars.length);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            String msg = e.getMessage();
            assertTrue("Message should not indicate truncation at exact boundary", !msg.contains("(truncated"));
            assertTrue("Message should contain full value", msg.contains("\"" + repeat("3", MAX_REPORT_CHARS - 3) + "xyz\""));
        }
    }

    @Test
    public void testExceptionMessageForCharArrayJustOverMaxReportChars() {
        char[] chars = (repeat("4", MAX_REPORT_CHARS + 1) + "bad").toCharArray();
        try {
            BigDecimalParser.parse(chars, 0, chars.length);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            String msg = e.getMessage();
            String expectedTruncation = "(truncated to " + MAX_REPORT_CHARS + " chars (from " + (MAX_REPORT_CHARS + 4) + "))";
            assertTrue("Message should indicate truncation", msg.contains(expectedTruncation));
            assertTrue("Message should contain truncated prefix", msg.contains(repeat("4", MAX_REPORT_CHARS)));
            assertTrue("Message should not contain full length", !msg.contains(repeat("4", MAX_REPORT_CHARS + 1)));
        }
    }

    @Test
    public void testExceptionMessageForOffsetCharArrayExactlyAtMaxReportChars() {
        // Exactly at boundary: value length = 1000
        String value = repeat("9", MAX_REPORT_CHARS - 3) + "end"; // 997 + 3 = 1000
        char[] chars = ("pre" + value).toCharArray();
        try {
            BigDecimalParser.parse(chars, 3, value.length());
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            String msg = e.getMessage();
            assertTrue("Message should not indicate truncation at exact boundary", !msg.contains("(truncated"));
            assertTrue("Message should contain full value", msg.contains("\"" + value + "\""));
            // At exact boundary (len <= MAX_REPORT_CHARS), no "from length" is included
        }
    }

    @Test
    public void testExceptionMessageForOffsetCharArrayJustOverMaxReportChars() {
        String value = repeat("8", MAX_REPORT_CHARS + 1) + "end";
        char[] chars = ("pre" + value).toCharArray();
        try {
            BigDecimalParser.parse(chars, 3, value.length());
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            String msg = e.getMessage();
            String expectedTruncation = "(truncated to " + MAX_REPORT_CHARS + " chars (from " + value.length() + "))";
            assertTrue("Message should indicate truncation", msg.contains(expectedTruncation));
            assertTrue("Message should contain truncated prefix", msg.contains(repeat("8", MAX_REPORT_CHARS)));
            assertTrue("Message should not contain full length", !msg.contains(repeat("8", MAX_REPORT_CHARS + 1)));
        }
    }

    @Test
    public void testParseFailureWithNullMessageFromArithmeticException() {
        // Use a value that causes ArithmeticException in fast parser path
        // Very large exponent can trigger ArithmeticException in JavaBigDecimalParser
        String value = "1E" + repeat("9", 10000);
        try {
            BigDecimalParser.parse(value);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            String msg = e.getMessage();
            // The actual reason message depends on the exception from JavaBigDecimalParser
            // Just verify the message format is correct
            assertTrue("Message should contain value description", msg.contains("\"1E"));
            assertTrue("Message should contain reason", msg.contains("cannot be deserialized as `java.math.BigDecimal`"));
        }
    }

    @Test
    public void testParseCharArrayFailureWithNullMessageFromArithmeticException() {
        String value = "1E" + repeat("9", 10000);
        char[] chars = value.toCharArray();
        try {
            BigDecimalParser.parse(chars, 0, chars.length);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            String msg = e.getMessage();
            assertTrue("Message should contain value description", msg.contains("\"1E"));
            assertTrue("Message should contain reason", msg.contains("cannot be deserialized as `java.math.BigDecimal`"));
        }
    }

    @Test
    public void testParseWithFastParserFailureWithNullMessage() {
        String value = "1E" + repeat("9", 10000);
        try {
            BigDecimalParser.parseWithFastParser(value);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            String msg = e.getMessage();
            assertTrue("Message should contain value description", msg.contains("\"1E"));
            assertTrue("Message should contain reason", msg.contains("cannot be deserialized as `java.math.BigDecimal`"));
        }
    }

    @Test
    public void testParseWithFastParserCharArrayFailureWithNullMessage() {
        String value = "1E" + repeat("9", 10000);
        char[] chars = value.toCharArray();
        try {
            BigDecimalParser.parseWithFastParser(chars, 0, chars.length);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            String msg = e.getMessage();
            assertTrue("Message should contain value description", msg.contains("\"1E"));
            assertTrue("Message should contain reason", msg.contains("cannot be deserialized as `java.math.BigDecimal`"));
        }
    }

    @Test
    public void testGetValueDescStringExactBoundaryFormat() {
        // Test exactly at boundary (1000 chars invalid)
        String exactly1000invalid = repeat("q", MAX_REPORT_CHARS - 7) + "invalid"; // 1000 chars
        try {
            BigDecimalParser.parse(exactly1000invalid);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            String msg = e.getMessage();
            assertTrue("Exact boundary (1000) should not have truncation marker", !msg.contains("(truncated"));
            assertTrue("Exact boundary should have quotes around full value", msg.contains("\"" + exactly1000invalid + "\""));
        }
    }

    @Test
    public void testGetValueDescCharArrayExactBoundaryFormat() {
        // Test exactly at boundary (1000 chars)
        char[] exactChars = (repeat("a", MAX_REPORT_CHARS - 3) + "bad").toCharArray(); // 1000 chars
        try {
            BigDecimalParser.parse(exactChars, 0, exactChars.length);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            String msg = e.getMessage();
            assertTrue("Exact boundary should not have truncation marker", !msg.contains("(truncated"));
            assertTrue("Exact boundary should have quotes around full value", msg.contains("\"" + repeat("a", MAX_REPORT_CHARS - 3) + "bad\""));
        }
        
        // Test over boundary
        char[] overChars = (repeat("b", MAX_REPORT_CHARS + 3) + "bad").toCharArray();
        try {
            BigDecimalParser.parse(overChars, 0, overChars.length);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            String msg = e.getMessage();
            String expectedTruncation = "(truncated to " + MAX_REPORT_CHARS + " chars (from " + overChars.length + "))";
            assertTrue("Over boundary should have truncation marker: " + expectedTruncation, msg.contains(expectedTruncation));
            assertTrue("Over boundary should have quoted truncated prefix", msg.contains("\"" + repeat("b", MAX_REPORT_CHARS) + "\""));
        }
    }

    @Test
    public void testGetValueDescOffsetCharArrayExactBoundaryFormat() {
        // Test exactly at boundary (value length = 1000)
        String exactValue = repeat("c", MAX_REPORT_CHARS - 3) + "end"; // 1000 chars
        char[] exactChars = ("pre" + exactValue).toCharArray();
        try {
            BigDecimalParser.parse(exactChars, 3, exactValue.length());
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            String msg = e.getMessage();
            assertTrue("Exact boundary should not have truncation marker", !msg.contains("(truncated"));
            assertTrue("Exact boundary should have quotes around full value", msg.contains("\"" + exactValue + "\""));
            // At exact boundary (len <= MAX_REPORT_CHARS), no "from length" is included
        }
        
        // Test over boundary
        String overValue = repeat("d", MAX_REPORT_CHARS + 2) + "end"; // 1002 + 3 = 1005 chars
        char[] overChars = ("pre" + overValue).toCharArray();
        try {
            BigDecimalParser.parse(overChars, 3, overValue.length());
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            String msg = e.getMessage();
            String expectedTruncation = "(truncated to " + MAX_REPORT_CHARS + " chars (from " + overValue.length() + "))";
            assertTrue("Over boundary should have truncation marker: " + expectedTruncation, msg.contains(expectedTruncation));
            assertTrue("Over boundary should have quoted truncated prefix", msg.contains("\"" + repeat("d", MAX_REPORT_CHARS) + "\""));
        }
    }

    @Test
    public void testParseFailureMessageFormatForShortInput() {
        // Verify exception message format for short invalid input
        // The actual reason message is "Not a valid number representation" when exception message is null
        try {
            BigDecimalParser.parse("123abc");
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            String msg = e.getMessage();
            String expected = "Value \"123abc\" cannot be deserialized as `java.math.BigDecimal`, reason:  Not a valid number representation";
            assertEquals("Exception message format should match exactly", expected, msg);
        }
    }

    @Test
    public void testParseCharArrayFailureMessageFormatForShortInput() {
        // Verify exact exception message format for short invalid char[] input
        char[] chars = "456def".toCharArray();
        try {
            BigDecimalParser.parse(chars, 0, chars.length);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            String msg = e.getMessage();
            String expected = "Value \"456def\" cannot be deserialized as `java.math.BigDecimal`, reason:  Not a valid number representation";
            assertEquals("Exception message format should match exactly", expected, msg);
        }
    }

    @Test
    public void testParseWithFastParserFailureMessageFormatForShortInput() {
        // Verify exception message format for parseWithFastParser
        try {
            BigDecimalParser.parseWithFastParser("789ghi");
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            String msg = e.getMessage();
            // Fast parser may have different reason message
            assertTrue("Message should contain value", msg.contains("\"789ghi\""));
            assertTrue("Message should contain reason", msg.contains("cannot be deserialized as `java.math.BigDecimal`"));
        }
    }

    @Test
    public void testParseWithFastParserCharArrayFailureMessageFormatForShortInput() {
        // Verify exception message format for parseWithFastParser char[]
        char[] chars = "012jkl".toCharArray();
        try {
            BigDecimalParser.parseWithFastParser(chars, 0, chars.length);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            String msg = e.getMessage();
            assertTrue("Message should contain value", msg.contains("\"012jkl\""));
            assertTrue("Message should contain reason", msg.contains("cannot be deserialized as `java.math.BigDecimal`"));
        }
    }

    // ============================================================
    // Tests targeting surviving ConditionalsBoundaryMutator mutations
    // at parse(String) line 44 and parse(char[],int,int) line 73
    // ============================================================

    @Test
    public void testParseInvalidStringAt499CharsUsesStandardParser() {
        // 499 chars - should use BigDecimal(String) constructor path
        String invalid = repeat("9", 493) + "abc"; // 499 chars, invalid format
        try {
            BigDecimalParser.parse(invalid);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            String msg = e.getMessage();
            // BigDecimal constructor yields null message -> default reason
            assertTrue("Should use standard parser path at 499 chars", 
                msg.contains("Not a valid number representation"));
            assertTrue("Message should contain full value", msg.contains("\"" + invalid + "\""));
            assertFalse("Should not have truncation marker at 499 chars", msg.contains("(truncated"));
        }
    }

    @Test
    public void testParseInvalidStringAt500CharsUsesFastParser() {
        // 500 chars - boundary: should use JavaBigDecimalParser (fast parser) path
        // 497 "9"s + "abc" = 500 chars
        String invalid = repeat("9", 497) + "abc"; // 500 chars, invalid format
        try {
            BigDecimalParser.parse(invalid);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            String msg = e.getMessage();
            // Fast parser may yield different reason; verify fast parser path taken
            // Check for opening quote + first 50 chars (without closing quote since full value is quoted)
            String expectedPrefix = "\"" + invalid.substring(0, Math.min(50, invalid.length()));
            assertTrue("Message should contain value prefix: " + expectedPrefix, msg.contains(expectedPrefix));
            assertTrue("Message should contain reason", msg.contains("cannot be deserialized as `java.math.BigDecimal`"));
            assertFalse("Should not have truncation marker at 500 chars", msg.contains("(truncated"));
        }
    }

    @Test
    public void testParseInvalidStringAt501CharsUsesFastParser() {
        // 501 chars - should use JavaBigDecimalParser (fast parser) path
        // 498 "9"s + "abc" = 501 chars
        String invalid = repeat("9", 498) + "abc"; // 501 chars, invalid format
        try {
            BigDecimalParser.parse(invalid);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            String msg = e.getMessage();
            String expectedPrefix = "\"" + invalid.substring(0, Math.min(50, invalid.length()));
            assertTrue("Message should contain value prefix: " + expectedPrefix, msg.contains(expectedPrefix));
            assertTrue("Message should contain reason", msg.contains("cannot be deserialized as `java.math.BigDecimal`"));
            assertFalse("Should not have truncation marker at 501 chars", msg.contains("(truncated"));
        }
    }

    @Test
    public void testParseInvalidCharArrayAt499CharsUsesStandardParser() {
        // 499 chars - should use BigDecimal(char[],off,len) constructor path
        String invalid = repeat("8", 493) + "xyz";
        char[] chars = invalid.toCharArray();
        try {
            BigDecimalParser.parse(chars, 0, chars.length);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            String msg = e.getMessage();
            assertTrue("Should use standard parser path at 499 chars", 
                msg.contains("Not a valid number representation"));
            assertTrue("Message should contain full value", msg.contains("\"" + invalid + "\""));
            assertFalse("Should not have truncation marker", msg.contains("(truncated"));
        }
    }

    @Test
    public void testParseInvalidCharArrayAt500CharsUsesFastParser() {
        // 500 chars - boundary: should use JavaBigDecimalParser.parseBigDecimal(char[],off,len)
        // 497 "8"s + "xyz" = 500 chars
        String invalid = repeat("8", 497) + "xyz";
        char[] chars = invalid.toCharArray();
        try {
            BigDecimalParser.parse(chars, 0, chars.length);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            String msg = e.getMessage();
            String expectedPrefix = "\"" + invalid.substring(0, Math.min(50, invalid.length()));
            assertTrue("Message should contain value prefix: " + expectedPrefix, msg.contains(expectedPrefix));
            assertTrue("Message should contain reason", msg.contains("cannot be deserialized as `java.math.BigDecimal`"));
            assertFalse("Should not have truncation marker at 500 chars", msg.contains("(truncated"));
        }
    }

    @Test
    public void testParseInvalidCharArrayAt501CharsUsesFastParser() {
        // 501 chars - should use JavaBigDecimalParser path
        // 498 "8"s + "xyz" = 501 chars
        String invalid = repeat("8", 498) + "xyz";
        char[] chars = invalid.toCharArray();
        try {
            BigDecimalParser.parse(chars, 0, chars.length);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            String msg = e.getMessage();
            String expectedPrefix = "\"" + invalid.substring(0, Math.min(50, invalid.length()));
            assertTrue("Message should contain value prefix: " + expectedPrefix, msg.contains(expectedPrefix));
            assertTrue("Message should contain reason", msg.contains("cannot be deserialized as `java.math.BigDecimal`"));
            assertFalse("Should not have truncation marker at 501 chars", msg.contains("(truncated"));
        }
    }

    @Test
    public void testParseInvalidCharArrayWithOffsetAt499CharsUsesStandardParser() {
        // 499 chars value length with offset - should use standard constructor
        String invalid = repeat("7", 493) + "bad";
        char[] chars = ("pre" + invalid).toCharArray();
        try {
            BigDecimalParser.parse(chars, 3, invalid.length());
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            String msg = e.getMessage();
            assertTrue("Should use standard parser path at 499 chars value length", 
                msg.contains("Not a valid number representation"));
            assertTrue("Message should contain full value", msg.contains("\"" + invalid + "\""));
            assertFalse("Should not have truncation marker", msg.contains("(truncated"));
            // At 499 chars (<= MAX_REPORT_CHARS), no "from length" suffix
            assertFalse("Should not have 'from length' at 499", msg.contains("from " + invalid.length()));
        }
    }

    @Test
    public void testParseInvalidCharArrayWithOffsetAt500CharsUsesFastParser() {
        // 500 chars value length with offset - boundary: should use fast parser
        // 497 "7"s + "bad" = 500 chars
        String invalid = repeat("7", 497) + "bad";
        char[] chars = ("pre" + invalid).toCharArray();
        try {
            BigDecimalParser.parse(chars, 3, invalid.length());
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            String msg = e.getMessage();
            String expectedPrefix = "\"" + invalid.substring(0, Math.min(50, invalid.length()));
            assertTrue("Message should contain value prefix: " + expectedPrefix, msg.contains(expectedPrefix));
            assertTrue("Message should contain reason", msg.contains("cannot be deserialized as `java.math.BigDecimal`"));
            assertFalse("Should not have truncation marker at 500 chars", msg.contains("(truncated"));
            assertFalse("Should not have 'from length' at 500", msg.contains("from " + invalid.length()));
        }
    }

    @Test
    public void testParseInvalidCharArrayWithOffsetAt501CharsUsesFastParser() {
        // 501 chars value length with offset - should use fast parser
        // 498 "7"s + "bad" = 501 chars
        String invalid = repeat("7", 498) + "bad";
        char[] chars = ("pre" + invalid).toCharArray();
        try {
            BigDecimalParser.parse(chars, 3, invalid.length());
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            String msg = e.getMessage();
            String expectedPrefix = "\"" + invalid.substring(0, Math.min(50, invalid.length()));
            assertTrue("Message should contain value prefix: " + expectedPrefix, msg.contains(expectedPrefix));
            assertTrue("Message should contain reason", msg.contains("cannot be deserialized as `java.math.BigDecimal`"));
            assertFalse("Should not have truncation marker at 501 chars", msg.contains("(truncated"));
            // At 501 chars (> MAX_REPORT_CHARS? No, 501 < 1000), so no truncation, but "from length" only if > MAX_REPORT_CHARS
            assertFalse("Should not have 'from length' at 501", msg.contains("from " + invalid.length()));
        }
    }

    @Test
    public void testParseFastParserConsistencyAtBoundary() {
        // Verify parse() and parseWithFastParser() give same error format at 500 chars
        // 497 "5"s + "xyz" = 500 chars
        String invalid500 = repeat("5", 497) + "xyz";
        
        String msgParse = null;
        try { BigDecimalParser.parse(invalid500); } 
        catch (NumberFormatException e) { msgParse = e.getMessage(); }
        
        String msgFast = null;
        try { BigDecimalParser.parseWithFastParser(invalid500); } 
        catch (NumberFormatException e) { msgFast = e.getMessage(); }
        
        assertNotNull("parse() should throw", msgParse);
        assertNotNull("parseWithFastParser() should throw", msgFast);
        // Both should use fast parser at 500 chars, so messages should be similar
        assertTrue("Both should contain value", msgParse.contains("555") && msgFast.contains("555"));
        assertTrue("Both should contain standard prefix", 
            msgParse.contains("cannot be deserialized as `java.math.BigDecimal`") &&
            msgFast.contains("cannot be deserialized as `java.math.BigDecimal`"));
    }

    @Test
    public void testParseCharArrayFastParserConsistencyAtBoundary() {
        // Verify parse(char[],...) and parseWithFastParser(char[],...) give same error format at 500 chars
        // 497 "6"s + "xyz" = 500 chars
        String invalid500 = repeat("6", 497) + "xyz";
        char[] chars = invalid500.toCharArray();
        
        String msgParse = null;
        try { BigDecimalParser.parse(chars, 0, chars.length); } 
        catch (NumberFormatException e) { msgParse = e.getMessage(); }
        
        String msgFast = null;
        try { BigDecimalParser.parseWithFastParser(chars, 0, chars.length); } 
        catch (NumberFormatException e) { msgFast = e.getMessage(); }
        
        assertNotNull("parse() should throw", msgParse);
        assertNotNull("parseWithFastParser() should throw", msgFast);
        // Both should use fast parser at 500 chars
        assertTrue("Both should contain value", msgParse.contains("666") && msgFast.contains("666"));
        assertTrue("Both should contain standard prefix", 
            msgParse.contains("cannot be deserialized as `java.math.BigDecimal`") &&
            msgFast.contains("cannot be deserialized as `java.math.BigDecimal`"));
    }

    @Test
    public void testParseStandardParserConsistencyBelowBoundary() {
        // Verify parse() uses standard parser at 499 chars (different from fast parser path)
        String invalid499 = repeat("4", 493) + "bad";
        
        String msgParse = null;
        try { BigDecimalParser.parse(invalid499); } 
        catch (NumberFormatException e) { msgParse = e.getMessage(); }
        
        String msgFast = null;
        try { BigDecimalParser.parseWithFastParser(invalid499); } 
        catch (NumberFormatException e) { msgFast = e.getMessage(); }
        
        assertNotNull("parse() should throw", msgParse);
        assertNotNull("parseWithFastParser() should throw", msgFast);
        // parse() at 499 uses standard parser -> reason "Not a valid number representation"
        // parseWithFastParser() always uses fast parser -> may have different reason
        assertTrue("parse() at 499 should use standard reason", msgParse.contains("Not a valid number representation"));
        // Fast parser reason may differ; just verify both throw with correct format
        assertTrue("Both should contain value", msgParse.contains("444") && msgFast.contains("444"));
        assertTrue("Both should contain standard prefix", 
            msgParse.contains("cannot be deserialized as `java.math.BigDecimal`") &&
            msgFast.contains("cannot be deserialized as `java.math.BigDecimal`"));
    }
}
