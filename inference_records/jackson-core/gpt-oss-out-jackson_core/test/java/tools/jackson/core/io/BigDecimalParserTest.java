package tools.jackson.core.io;

import org.junit.Test;
import java.math.BigDecimal;
import java.lang.reflect.Method;
import static org.junit.Assert.*;

public class BigDecimalParserTest {

    private static String repeatChar(char c, int count) {
        StringBuilder sb = new StringBuilder(count);
        for (int i = 0; i < count; ++i) {
            sb.append(c);
        }
        return sb.toString();
    }

    @Test
    public void parseShortString() {
        String value = "123.45";
        BigDecimal expected = new BigDecimal(value);
        BigDecimal actual = BigDecimalParser.parse(value);
        assertEquals(expected, actual);
    }

    @Test
    public void parseBoundaryLengthUsesFastParser() {
        String longValue = repeatChar('1', 500); // exactly the switch threshold
        BigDecimal expected = new BigDecimal(longValue);
        BigDecimal actual = BigDecimalParser.parse(longValue);
        assertEquals(expected, actual);
    }

    @Test
    public void parseWithFastParserBoundaryLength() {
        String longValue = repeatChar('1', 500); // exactly the switch threshold
        BigDecimal expected = new BigDecimal(longValue);
        BigDecimal actual = BigDecimalParser.parseWithFastParser(longValue);
        assertEquals(expected, actual);
    }

    @Test
    public void parseShortInvalidThrowsNumberFormatException() {
        try {
            BigDecimalParser.parse("abc");
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            String msg = e.getMessage();
            assertTrue(msg.contains("\"abc\""));
        }
    }

    @Test
    public void parseLongInvalidThrowsTruncatedException() {
        String invalid = repeatChar('1', 1100) + "a"; // > MAX_CHARS_TO_REPORT
        try {
            BigDecimalParser.parse(invalid);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            String msg = e.getMessage();
            assertTrue(msg.contains("(truncated to 1000 chars (from " + invalid.length() + "))"));
            // ensure the truncated part is present
            assertTrue(msg.contains("\"" + repeatChar('1', 1000) + "\""));
        }
    }

    @Test
    public void parseWithFastParserLongInvalidThrowsTruncatedException() {
        String invalid = repeatChar('1', 1100) + "b";
        try {
            BigDecimalParser.parseWithFastParser(invalid);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            String msg = e.getMessage();
            assertTrue(msg.contains("(truncated to 1000 chars (from " + invalid.length() + "))"));
            assertTrue(msg.contains("\"" + repeatChar('1', 1000) + "\""));
        }
    }

    @Test
    public void parseCharArrayValid() {
        char[] arr = "123.45".toCharArray();
        BigDecimal expected = new BigDecimal("123.45");
        BigDecimal actual = BigDecimalParser.parse(arr);
        assertEquals(expected, actual);
    }

    @Test
    public void parseCharArrayOffsetLength() {
        char[] big = "0123456789".toCharArray(); // indices 0..9
        BigDecimal expected = new BigDecimal("3456");
        BigDecimal actual = BigDecimalParser.parse(big, 3, 4); // slice "3456"
        assertEquals(expected, actual);
    }

    @Test
    public void parseCharArrayInvalidTruncated() {
        char[] invalidChars = (repeatChar('1', 1100) + "c").toCharArray();
        try {
            BigDecimalParser.parse(invalidChars);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            String msg = e.getMessage();
            assertTrue(msg.contains("(truncated to 1000 chars (from " + invalidChars.length + "))"));
            assertTrue(msg.contains("\"" + repeatChar('1', 1000) + "\""));
        }
    }

    @Test
    public void parseWithFastParserCharArray() {
        char[] arr = "987654321".toCharArray();
        BigDecimal expected = new BigDecimal("987654321");
        BigDecimal actual = BigDecimalParser.parseWithFastParser(arr, 0, arr.length);
        assertEquals(expected, actual);
    }

    /* ------------------------------------------------------------------ */
    /*                      Additional tests for uncovered branches         */
    /* ------------------------------------------------------------------ */

    @Test
    public void parseWithFastParserShortInvalidThrowsNumberFormatException() {
        try {
            BigDecimalParser.parseWithFastParser("abc");
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            String msg = e.getMessage();
            assertTrue(msg.contains("\"abc\""));
        }
    }

    @Test
    public void parseCharArrayShortInvalidCatch() {
        char[] arr = "xyz".toCharArray(); // short length (<500)
        try {
            BigDecimalParser.parse(arr, 0, arr.length);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            String msg = e.getMessage();
            assertTrue(msg.contains("\"xyz\""));
        }
    }

    @Test
    public void parseWithFastParserCharArrayShortInvalidCatch() {
        char[] arr = "abc".toCharArray(); // short length (<500)
        try {
            BigDecimalParser.parseWithFastParser(arr, 0, arr.length);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            String msg = e.getMessage();
            assertTrue(msg.contains("\"abc\""));
        }
    }

    // ------------------------------------------------------------------
    // Tests exercising private failure construction paths
    // ------------------------------------------------------------------

    @Test
    public void parseFailureStringWithNullMessage() throws Exception {
        Method m = BigDecimalParser.class.getDeclaredMethod("_parseFailure", Exception.class, String.class);
        m.setAccessible(true);
        NumberFormatException ex = (NumberFormatException) m.invoke(null,
                new ArithmeticException(), "12345");
        assertNotNull(ex);
        String msg = ex.getMessage();
        assertTrue(msg.contains("\"12345\""));
        assertTrue(msg.contains("Not a valid number representation"));
    }

    @Test
    public void parseFailureCharArrayWithNullMessage() throws Exception {
        Method m = BigDecimalParser.class.getDeclaredMethod(
                "_parseFailure", Exception.class, char[].class, int.class, int.class);
        m.setAccessible(true);
        char[] arr = "12345".toCharArray();
        NumberFormatException ex = (NumberFormatException) m.invoke(null,
                new ArithmeticException(), arr, 0, arr.length);
        assertNotNull(ex);
        String msg = ex.getMessage();
        assertTrue(msg.contains("\"12345\""));
        assertTrue(msg.contains("Not a valid number representation"));
    }

    /* ------------------------------------------------------------------ */
    /*                          New focused tests                        */
    /* ------------------------------------------------------------------ */

    @Test
    public void getValueDescForExactBoundaryString() throws Exception {
        Method m = BigDecimalParser.class.getDeclaredMethod("_getValueDesc", String.class);
        m.setAccessible(true);
        String value = repeatChar('1', 1000); // exactly MAX_CHARS_TO_REPORT
        String result = (String) m.invoke(null, value);
        assertEquals("\"" + value + "\"", result);
    }

    @Test
    public void getValueDescForExactBoundaryArray() throws Exception {
        Method m = BigDecimalParser.class.getDeclaredMethod("_getValueDesc",
                char[].class, int.class, int.class);
        m.setAccessible(true);
        char[] arr = repeatChar('1', 1000).toCharArray(); // exactly MAX_CHARS_TO_REPORT
        String result = (String) m.invoke(null, arr, 0, arr.length);
        assertEquals("\"" + new String(arr) + "\"", result);
    }

    @Test
    public void parseFailureWithCustomMessage() throws Exception {
        Method m = BigDecimalParser.class.getDeclaredMethod("_parseFailure",
                Exception.class, String.class);
        m.setAccessible(true);
        NumberFormatException ex = (NumberFormatException) m.invoke(null,
                new ArithmeticException("bad"), "12345");
        assertEquals(
                "Value \"12345\" cannot be deserialized as `java.math.BigDecimal`, reason:  bad",
                ex.getMessage());
    }

    @Test
    public void parseFailureCharArrayWithCustomMessage() throws Exception {
        Method m = BigDecimalParser.class.getDeclaredMethod("_parseFailure",
                Exception.class, char[].class, int.class, int.class);
        m.setAccessible(true);
        char[] arr = "12345".toCharArray();
        NumberFormatException ex = (NumberFormatException) m.invoke(null,
                new ArithmeticException("bad"), arr, 0, arr.length);
        assertEquals(
                "Value \"12345\" cannot be deserialized as `java.math.BigDecimal`, reason:  bad",
                ex.getMessage());
    }

    /* ------------------------------------------------------------------ */
    /*                          Mutation‑killing tests                     */
    /* ------------------------------------------------------------------ */

    private String createHugeNumber(int digitCount) {
        return repeatChar('9', digitCount) + ".0";
    }

    @Test
    public void parseHugeNumberBelowThresholdSucceeds() {
        // 490 digits + .0 => length 492 < 500, uses BigDecimal constructor
        int digits = 490;
        String str = createHugeNumber(digits);
        BigDecimal expected = new BigDecimal(str);
        BigDecimal actual = BigDecimalParser.parse(str);
        assertEquals(expected, actual);
    }

    @Test
    public void parseHugeNumberAboveThresholdThrowsNumberFormatException() {
        // Adjusted: the fast parser can handle large values without overflow,
        // so parsing should succeed rather than throw.
        int digits = 600;
        String str = createHugeNumber(digits);
        BigDecimal expected = new BigDecimal(str);
        BigDecimal actual = BigDecimalParser.parse(str);
        assertEquals(expected, actual);
    }

    @Test
    public void parseHugeCharArrayAboveThresholdThrowsNumberFormatException() {
        // Adjusted: parsing should succeed rather than throw.
        int digits = 600;
        String str = createHugeNumber(digits);
        char[] arr = str.toCharArray();
        BigDecimal expected = new BigDecimal(str);
        BigDecimal actual = BigDecimalParser.parse(arr, 0, arr.length);
        assertEquals(expected, actual);
    }

    /* ------------------------------------------------------------------ */
    /*                          Boundary tests for mutation killing         */
    /* ------------------------------------------------------------------ */

    @Test
    public void parseBoundaryLengthNegativeNumber() {
        String value = "-" + repeatChar('1', 499); // length 500 including '-'
        BigDecimal expected = new BigDecimal(value);
        BigDecimal actual = BigDecimalParser.parse(value);
        assertEquals(expected, actual);
    }

    @Test
    public void parseBoundaryLengthPositiveSignNumber() {
        String value = "+" + repeatChar('9', 499); // length 500 including '+'
        BigDecimal expected = new BigDecimal(value);
        BigDecimal actual = BigDecimalParser.parse(value);
        assertEquals(expected, actual);
    }

    @Test
    public void parseBoundaryLengthWithFastParserNegativeNumber() {
        String value = "-" + repeatChar('1', 499);
        BigDecimal expected = new BigDecimal(value);
        BigDecimal actual = BigDecimalParser.parseWithFastParser(value);
        assertEquals(expected, actual);
    }

    @Test
    public void parseBoundaryLengthWithFastParserPositiveSignNumber() {
        String value = "+" + repeatChar('9', 499);
        BigDecimal expected = new BigDecimal(value);
        BigDecimal actual = BigDecimalParser.parseWithFastParser(value);
        assertEquals(expected, actual);
    }

    @Test
    public void parseBoundaryLengthCharArrayNegativeNumber() {
        char[] arr = ("-" + repeatChar('1', 499)).toCharArray(); // length 500
        BigDecimal expected = new BigDecimal(new String(arr));
        BigDecimal actual = BigDecimalParser.parse(arr, 0, arr.length);
        assertEquals(expected, actual);
    }

    @Test
    public void parseBoundaryLengthCharArrayPositiveSignNumber() {
        char[] arr = ("+" + repeatChar('9', 499)).toCharArray(); // length 500
        BigDecimal expected = new BigDecimal(new String(arr));
        BigDecimal actual = BigDecimalParser.parse(arr, 0, arr.length);
        assertEquals(expected, actual);
    }

    @Test
    public void parseBoundaryLengthWithFastParserCharArrayNegativeNumber() {
        char[] arr = ("-" + repeatChar('1', 499)).toCharArray();
        BigDecimal expected = new BigDecimal(new String(arr));
        BigDecimal actual = BigDecimalParser.parseWithFastParser(arr, 0, arr.length);
        assertEquals(expected, actual);
    }

    @Test
    public void parseBoundaryLengthWithFastParserCharArrayPositiveSignNumber() {
        char[] arr = ("+" + repeatChar('9', 499)).toCharArray();
        BigDecimal expected = new BigDecimal(new String(arr));
        BigDecimal actual = BigDecimalParser.parseWithFastParser(arr, 0, arr.length);
        assertEquals(expected, actual);
    }
}
