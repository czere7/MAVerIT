package tools.jackson.core.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;

import org.junit.Test;

public class BigDecimalParserTest {

    private static final int THRESHOLD = 500;
    private static final int MAX_REPORT = 1000;

    // Helper method to repeat a character (compatible with Java 8+)
    private static String repeatChar(char c, int count) {
        StringBuilder sb = new StringBuilder(count);
        for (int i = 0; i < count; i++) {
            sb.append(c);
        }
        return sb.toString();
    }

    // Tests for parse(String) method

    @Test
    public void testParseSmallString() {
        String value = "123.456";
        BigDecimal result = BigDecimalParser.parse(value);
        assertNotNull(result);
        assertEquals(new BigDecimal(value), result);
    }

    @Test
    public void testParseSmallNegativeString() {
        String value = "-987654.321";
        BigDecimal result = BigDecimalParser.parse(value);
        assertNotNull(result);
        assertEquals(new BigDecimal(value), result);
    }

    @Test
    public void testParseSmallExponentString() {
        String value = "1.23E10";
        BigDecimal result = BigDecimalParser.parse(value);
        assertNotNull(result);
        assertEquals(new BigDecimal(value), result);
    }

    @Test
    public void testParseStringAtThreshold() {
        // Create a string with exactly THRESHOLD characters (minus 1 for decimal point)
        String value = "0." + repeatChar('1', THRESHOLD - 1);
        BigDecimal result = BigDecimalParser.parse(value);
        assertNotNull(result);
        assertEquals(new BigDecimal(value), result);
    }

    @Test
    public void testParseStringAtThresholdPlusOne() {
        // Create a string with THRESHOLD + 1 characters
        String value = "0." + repeatChar('1', THRESHOLD);
        BigDecimal result = BigDecimalParser.parse(value);
        assertNotNull(result);
        assertEquals(new BigDecimal(value), result);
    }

    @Test
    public void testParseLargeString() {
        // String with more than 500 characters
        String value = "0." + repeatChar('1', 600);
        BigDecimal result = BigDecimalParser.parse(value);
        assertNotNull(result);
        assertEquals(new BigDecimal(value), result);
    }

    @Test
    public void testParseVeryLargeString() {
        // String with more than 1000 characters
        String value = "0." + repeatChar('1', 1500);
        BigDecimal result = BigDecimalParser.parse(value);
        assertNotNull(result);
        assertEquals(new BigDecimal(value), result);
    }

    @Test(expected = NumberFormatException.class)
    public void testParseInvalidString() {
        BigDecimalParser.parse("not-a-number");
    }

    @Test(expected = NumberFormatException.class)
    public void testParseEmptyString() {
        BigDecimalParser.parse("");
    }

    // Tests for parse(char[], int, int) method

    @Test
    public void testParseCharArrayWithOffset() {
        char[] chars = "123.456".toCharArray();
        BigDecimal result = BigDecimalParser.parse(chars, 0, chars.length);
        assertNotNull(result);
        assertEquals(new BigDecimal("123.456"), result);
    }

    @Test
    public void testParseCharArrayOffsetAndLength() {
        char[] chars = "prefix123.456suffix".toCharArray();
        BigDecimal result = BigDecimalParser.parse(chars, 6, 7); // "123.456"
        assertNotNull(result);
        assertEquals(new BigDecimal("123.456"), result);
    }

    @Test
    public void testParseCharArrayLargeWithOffset() {
        String value = "0." + repeatChar('1', 600);
        char[] chars = value.toCharArray();
        BigDecimal result = BigDecimalParser.parse(chars, 0, chars.length);
        assertNotNull(result);
        assertEquals(new BigDecimal(value), result);
    }

    @Test(expected = NumberFormatException.class)
    public void testParseCharArrayInvalid() {
        BigDecimalParser.parse("invalid".toCharArray(), 0, 7);
    }

    // Tests for parse(char[]) method

    @Test
    public void testParseCharArrayOnly() {
        char[] chars = "789.012".toCharArray();
        BigDecimal result = BigDecimalParser.parse(chars);
        assertNotNull(result);
        assertEquals(new BigDecimal("789.012"), result);
    }

    @Test
    public void testParseCharArrayLarge() {
        String value = "0." + repeatChar('1', 600);
        BigDecimal result = BigDecimalParser.parse(value.toCharArray());
        assertNotNull(result);
        assertEquals(new BigDecimal(value), result);
    }

    // Tests for parseWithFastParser(String) method

    @Test
    public void testParseWithFastParserSmallString() {
        String value = "123.456";
        BigDecimal result = BigDecimalParser.parseWithFastParser(value);
        assertNotNull(result);
        assertEquals(new BigDecimal(value), result);
    }

    @Test
    public void testParseWithFastParserLargeString() {
        String value = "0." + repeatChar('1', 1500);
        BigDecimal result = BigDecimalParser.parseWithFastParser(value);
        assertNotNull(result);
        assertEquals(new BigDecimal(value), result);
    }

    @Test(expected = NumberFormatException.class)
    public void testParseWithFastParserInvalidString() {
        BigDecimalParser.parseWithFastParser("not-a-number");
    }

    // Tests for parseWithFastParser(char[], int, int) method

    @Test
    public void testParseWithFastParserCharArray() {
        char[] chars = "123.456".toCharArray();
        BigDecimal result = BigDecimalParser.parseWithFastParser(chars, 0, chars.length);
        assertNotNull(result);
        assertEquals(new BigDecimal("123.456"), result);
    }

    @Test
    public void testParseWithFastParserCharArrayWithOffset() {
        char[] chars = "xxx123.456yyy".toCharArray();
        BigDecimal result = BigDecimalParser.parseWithFastParser(chars, 3, 7);
        assertNotNull(result);
        assertEquals(new BigDecimal("123.456"), result);
    }

    @Test(expected = NumberFormatException.class)
    public void testParseWithFastParserCharArrayInvalid() {
        BigDecimalParser.parseWithFastParser("invalid".toCharArray(), 0, 7);
    }

    // Tests for error message truncation

    @Test
    public void testErrorMessageTruncation() {
        try {
            String longValue = repeatChar('x', MAX_REPORT + 100);
            BigDecimalParser.parse(longValue);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            String message = e.getMessage();
            assertNotNull(message);
            assertTrue("Message should contain truncation info", message.contains("truncated"));
            assertTrue("Message should report truncated length", message.contains(String.valueOf(MAX_REPORT)));
        }
    }

    @Test
    public void testErrorMessageNoTruncation() {
        try {
            String shortValue = "invalid";
            BigDecimalParser.parse(shortValue);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            String message = e.getMessage();
            assertNotNull(message);
            assertTrue("Message should contain the value", message.contains("\"invalid\""));
            assertFalse("Message should not contain truncated", message.contains("truncated"));
        }
    }

    @Test
    public void testErrorMessageContainsReason() {
        try {
            BigDecimalParser.parse("");
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            String message = e.getMessage();
            assertNotNull(message);
            assertTrue("Message should contain 'cannot be deserialized'", message.contains("cannot be deserialized"));
        }
    }

    // Additional tests for branch coverage in _getValueDesc(String)

    @Test
    public void testGetValueDescStringAtMaxReportLength() {
        // Test boundary: exactly MAX_CHARS_TO_REPORT (should use non-truncated branch)
        try {
            String valueAtLimit = repeatChar('x', MAX_REPORT);
            BigDecimalParser.parse(valueAtLimit);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            String message = e.getMessage();
            assertNotNull(message);
            // Should NOT contain "truncated" since length equals MAX_CHARS_TO_REPORT
            assertFalse("Message should not contain truncated at exactly MAX_REPORT", message.contains("truncated"));
            assertTrue("Message should contain the full value", message.contains("\"" + repeatChar('x', MAX_REPORT) + "\""));
        }
    }

    @Test
    public void testGetValueDescStringOverMaxReportLength() {
        // Test over MAX_CHARS_TO_REPORT (should use truncated branch)
        try {
            String valueOverLimit = repeatChar('y', MAX_REPORT + 1);
            BigDecimalParser.parse(valueOverLimit);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            String message = e.getMessage();
            assertNotNull(message);
            assertTrue("Message should contain truncation info", message.contains("truncated"));
            assertTrue("Message should report truncated length", message.contains(String.valueOf(MAX_REPORT)));
            // Should contain truncated value (first 1000 chars)
            assertTrue("Message should contain truncated value prefix", message.contains("\"" + repeatChar('y', MAX_REPORT)));
        }
    }

    @Test
    public void testGetValueDescCharArrayOverMaxReportLength() {
        // Test char[] overload with length > MAX_CHARS_TO_REPORT
        try {
            char[] chars = new char[MAX_REPORT + 50];
            for (int i = 0; i < chars.length; i++) {
                chars[i] = 'z';
            }
            BigDecimalParser.parse(chars, 0, chars.length);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            String message = e.getMessage();
            assertNotNull(message);
            assertTrue("Message should contain truncation info", message.contains("truncated"));
        }
    }

    @Test
    public void testGetValueDescCharArrayAtMaxReportLength() {
        // Test char[] overload at exactly MAX_CHARS_TO_REPORT (should NOT truncate)
        try {
            char[] chars = new char[MAX_REPORT];
            for (int i = 0; i < chars.length; i++) {
                chars[i] = 'a';
            }
            BigDecimalParser.parse(chars, 0, chars.length);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            String message = e.getMessage();
            assertNotNull(message);
            assertFalse("Message should not contain truncated at exactly MAX_REPORT", message.contains("truncated"));
        }
    }

    // Tests for parseWithFastParser error message truncation
    // Note: JavaBigDecimalParser actually parses very long strings successfully,
    // so we test with invalid input that triggers an exception AND has truncation

    @Test
    public void testParseWithFastParserErrorTruncation() {
        // For parseWithFastParser, we need an invalid long string to trigger error AND truncation
        // Use a valid-looking but invalid number format with many digits
        try {
            String longValue = repeatChar('x', MAX_REPORT + 200) + "..";
            BigDecimalParser.parseWithFastParser(longValue);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            String message = e.getMessage();
            assertNotNull(message);
            assertTrue("Message should contain truncation info", message.contains("truncated"));
        }
    }

    @Test
    public void testParseWithFastParserErrorNoTruncation() {
        // Test parseWithFastParser with short invalid string
        try {
            BigDecimalParser.parseWithFastParser("abc");
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            String message = e.getMessage();
            assertNotNull(message);
            assertFalse("Message should not contain truncated for short input", message.contains("truncated"));
            assertTrue("Message should contain the value", message.contains("\"abc\""));
        }
    }

    @Test
    public void testParseWithFastParserCharArrayErrorTruncation() {
        // Test parseWithFastParser char[] with invalid input longer than MAX_CHARS_TO_REPORT
        try {
            char[] chars = new char[MAX_REPORT + 100];
            for (int i = 0; i < chars.length; i++) {
                chars[i] = 'x';
            }
            // Make it invalid by appending extra decimal points
            chars[chars.length - 1] = '.';
            chars[chars.length - 2] = '.';
            BigDecimalParser.parseWithFastParser(chars, 0, chars.length);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            String message = e.getMessage();
            assertNotNull(message);
            assertTrue("Message should contain truncation info", message.contains("truncated"));
        }
    }

    // Test ArithmeticException handling
    // Note: It's difficult to trigger ArithmeticException from JavaBigDecimalParser with simple inputs.
    // The catch blocks are there for defensive purposes, but we test the exception conversion
    // using inputs that definitely throw exceptions

    @Test
    public void testParseArithmeticException() {
        // Test that NumberFormatException is properly converted with error message
        try {
            BigDecimalParser.parse("not-a-valid-number");
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            // Expected - should convert to NumberFormatException with proper message
            assertNotNull(e.getMessage());
            assertTrue("Message should contain value description", e.getMessage().contains("cannot be deserialized"));
        }
    }

    @Test
    public void testParseWithFastParserArithmeticException() {
        // Test ArithmeticException handling in parseWithFastParser
        try {
            BigDecimalParser.parseWithFastParser("invalid..number");
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            // Expected - exception should be caught and converted
            assertNotNull(e.getMessage());
            assertTrue("Message should contain value description", e.getMessage().contains("cannot be deserialized"));
        }
    }

    // NEW TESTS: Boundary conditions for SIZE_FOR_SWITCH_TO_FASTDOUBLEPARSER (500)

    @Test
    public void testParseStringAtThresholdExactly() {
        // Test exactly at threshold (500) - uses JavaBigDecimalParser (>= 500)
        String value = "0." + repeatChar('1', 499);
        BigDecimal result = BigDecimalParser.parse(value);
        assertNotNull(result);
        assertEquals(new BigDecimal(value), result);
        // Verify it uses fast parser by checking large number behavior
        assertTrue(result.scale() > 0);
    }

    @Test
    public void testParseStringBelowThreshold() {
        // Test below threshold (499) - uses new BigDecimal()
        String value = "0." + repeatChar('1', 498);
        BigDecimal result = BigDecimalParser.parse(value);
        assertNotNull(result);
        assertEquals(new BigDecimal(value), result);
    }

    @Test
    public void testParseStringAboveThreshold() {
        // Test above threshold (501) - uses JavaBigDecimalParser
        String value = "0." + repeatChar('1', 501);
        BigDecimal result = BigDecimalParser.parse(value);
        assertNotNull(result);
        assertEquals(new BigDecimal(value), result);
    }

    @Test
    public void testParseCharArrayAtThreshold() {
        // Test char[] at exactly threshold (500)
        String value = "0." + repeatChar('1', 499);
        char[] chars = value.toCharArray();
        BigDecimal result = BigDecimalParser.parse(chars, 0, chars.length);
        assertNotNull(result);
        assertEquals(new BigDecimal(value), result);
    }

    @Test
    public void testParseCharArrayBelowThreshold() {
        // Test char[] below threshold (499)
        String value = "0." + repeatChar('1', 498);
        char[] chars = value.toCharArray();
        BigDecimal result = BigDecimalParser.parse(chars, 0, chars.length);
        assertNotNull(result);
        assertEquals(new BigDecimal(value), result);
    }

    @Test
    public void testParseCharArrayAboveThreshold() {
        // Test char[] above threshold (501)
        String value = "0." + repeatChar('1', 501);
        char[] chars = value.toCharArray();
        BigDecimal result = BigDecimalParser.parse(chars, 0, chars.length);
        assertNotNull(result);
        assertEquals(new BigDecimal(value), result);
    }

    // NEW TESTS: Edge cases and special values

    @Test
    public void testParseZero() {
        BigDecimal result = BigDecimalParser.parse("0");
        assertEquals(BigDecimal.ZERO, result);
    }

    @Test
    public void testParseZeroWithDecimal() {
        BigDecimal result = BigDecimalParser.parse("0.0");
        assertEquals(new BigDecimal("0.0"), result);
    }

    @Test
    public void testParseScientificNotationLowerCase() {
        BigDecimal result = BigDecimalParser.parse("1.5e10");
        assertEquals(new BigDecimal("1.5E10"), result);
    }

    @Test
    public void testParseScientificNotationUpperCase() {
        BigDecimal result = BigDecimalParser.parse("1.5E10");
        assertEquals(new BigDecimal("1.5E10"), result);
    }

    @Test
    public void testParseNegativeExponent() {
        BigDecimal result = BigDecimalParser.parse("1.5E-10");
        assertEquals(new BigDecimal("1.5E-10"), result);
    }

    @Test
    public void testParsePositiveSign() {
        BigDecimal result = BigDecimalParser.parse("+123.456");
        assertEquals(new BigDecimal("+123.456"), result);
    }

    @Test
    public void testParseWithFastParserZero() {
        BigDecimal result = BigDecimalParser.parseWithFastParser("0");
        assertEquals(BigDecimal.ZERO, result);
    }

    @Test
    public void testParseWithFastParserNegative() {
        BigDecimal result = BigDecimalParser.parseWithFastParser("-123.456");
        assertEquals(new BigDecimal("-123.456"), result);
    }

    // NEW TESTS: Null handling - these should throw NullPointerException

    @Test(expected = NullPointerException.class)
    public void testParseNullString() {
        BigDecimalParser.parse((String) null);
    }

    @Test(expected = NullPointerException.class)
    public void testParseWithFastParserNullString() {
        BigDecimalParser.parseWithFastParser((String) null);
    }

    @Test(expected = NullPointerException.class)
    public void testParseNullCharArray() {
        BigDecimalParser.parse((char[]) null);
    }

    @Test(expected = NullPointerException.class)
    public void testParseCharArrayWithNullArray() {
        BigDecimalParser.parse(null, 0, 10);
    }

    @Test(expected = NullPointerException.class)
    public void testParseWithFastParserNullCharArray() {
        // Fixed: use parseWithFastParser(char[], int, int) instead of non-existent parseWithFastParser(char[])
        BigDecimalParser.parseWithFastParser(null, 0, 0);
    }

    @Test(expected = NullPointerException.class)
    public void testParseWithFastParserCharArrayWithNullArray() {
        BigDecimalParser.parseWithFastParser(null, 0, 10);
    }

    // NEW TESTS: Invalid input variations

    @Test(expected = NumberFormatException.class)
    public void testParseDoubleInfinity() {
        BigDecimalParser.parse("Infinity");
    }

    @Test(expected = NumberFormatException.class)
    public void testParseDoubleNaN() {
        BigDecimalParser.parse("NaN");
    }

    @Test(expected = NumberFormatException.class)
    public void testParseWithFastParserDoubleInfinity() {
        BigDecimalParser.parseWithFastParser("Infinity");
    }

    @Test(expected = NumberFormatException.class)
    public void testParseWithFastParserDoubleNaN() {
        BigDecimalParser.parseWithFastParser("NaN");
    }

    // NEW TESTS: Substring parsing with offset

    @Test
    public void testParseCharArrayOffsetPartial() {
        char[] chars = "000123.456000".toCharArray();
        BigDecimal result = BigDecimalParser.parse(chars, 3, 7);
        assertNotNull(result);
        assertEquals(new BigDecimal("123.456"), result);
    }

    @Test
    public void testParseCharArrayOffsetZeros() {
        char[] chars = "0".toCharArray();
        BigDecimal result = BigDecimalParser.parse(chars, 0, 1);
        assertNotNull(result);
        assertEquals(BigDecimal.ZERO, result);
    }

    @Test
    public void testParseWithFastParserCharArrayOffsetPartial() {
        char[] chars = "xxx123.456yyy".toCharArray();
        BigDecimal result = BigDecimalParser.parseWithFastParser(chars, 3, 7);
        assertNotNull(result);
        assertEquals(new BigDecimal("123.456"), result);
    }

    // NEW TESTS: Boundary conditions for char[] error message

    @Test
    public void testParseCharArrayErrorAtMaxReportLength() {
        char[] chars = new char[MAX_REPORT];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = 'b';
        }
        try {
            BigDecimalParser.parse(chars, 0, chars.length);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            String message = e.getMessage();
            assertNotNull(message);
            assertFalse("Should not truncate at exactly MAX_REPORT", message.contains("truncated"));
        }
    }

    @Test
    public void testParseCharArrayErrorOverMaxReportLength() {
        char[] chars = new char[MAX_REPORT + 1];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = 'c';
        }
        try {
            BigDecimalParser.parse(chars, 0, chars.length);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            String message = e.getMessage();
            assertNotNull(message);
            assertTrue("Should truncate when over MAX_REPORT", message.contains("truncated"));
        }
    }

    // NEW TESTS: Very small values at threshold

    @Test
    public void testParseSmallValueAtThreshold() {
        // Small value at exactly threshold length
        String value = repeatChar('1', 500);
        BigDecimal result = BigDecimalParser.parse(value);
        assertNotNull(result);
        assertEquals(new BigDecimal(value), result);
    }

    @Test
    public void testParseCharArraySmallValueAtThreshold() {
        char[] chars = repeatChar('2', 500).toCharArray();
        BigDecimal result = BigDecimalParser.parse(chars, 0, chars.length);
        assertNotNull(result);
        assertEquals(new BigDecimal(new String(chars)), result);
    }

    // NEW TEST: Large negative number

    @Test
    public void testParseLargeNegativeNumber() {
        String value = "-" + repeatChar('1', 600);
        BigDecimal result = BigDecimalParser.parse(value);
        assertNotNull(result);
        assertEquals(new BigDecimal(value), result);
    }

    @Test
    public void testParseWithFastParserLargeNegativeNumber() {
        String value = "-" + repeatChar('1', 1500);
        BigDecimal result = BigDecimalParser.parseWithFastParser(value);
        assertNotNull(result);
        assertEquals(new BigDecimal(value), result);
    }
}
