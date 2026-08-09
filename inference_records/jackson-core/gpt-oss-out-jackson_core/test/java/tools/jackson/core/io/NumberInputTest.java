package tools.jackson.core.io;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Test;

/**
 * Unit tests for {@link NumberInput}.
 */
public class NumberInputTest {

    /* -------------------------------------------------------- */
    /*  parseInt(char[], int, int)                              */
    /* -------------------------------------------------------- */

    @Test
    public void testParseIntCharArrayBasic() {
        char[] digits = "123456789".toCharArray();
        assertEquals(123456789, NumberInput.parseInt(digits, 0, digits.length));
    }

    @Test
    public void testParseIntCharArrayWithPlusSign() {
        char[] plusDigits = "+987654321".toCharArray();
        assertEquals(987654321, NumberInput.parseInt(plusDigits, 0, plusDigits.length));
    }

    @Test
    public void testParseIntLeadingZeros() {
        char[] zeroPadded = "000001234".toCharArray(); // length 9
        assertEquals(1234, NumberInput.parseInt(zeroPadded, 0, zeroPadded.length));
    }

    /* New tests for single- and two-digit inputs */
    @Test
    public void testParseIntCharArraySingleDigit() {
        char[] arr = "5".toCharArray();
        assertEquals(5, NumberInput.parseInt(arr, 0, arr.length));
    }

    @Test
    public void testParseIntCharArrayTwoDigits() {
        char[] arr = "12".toCharArray();
        assertEquals(12, NumberInput.parseInt(arr, 0, arr.length));
    }

    /* -------------------------------------------------------- */
    /*  parseLong(char[], int, int)                             */
    /* -------------------------------------------------------- */

    @Test
    public void testParseLongCharArray() {
        char[] number = "1234567890123".toCharArray(); // length 13
        long expected = 1234L * 1_000_000_000L + 567890123L;
        assertEquals(expected, NumberInput.parseLong(number, 0, number.length));
    }

    @Test
    public void testParseLong19Positive() {
        char[] n19 = "1234567890123456789".toCharArray();
        long expected = 1_234_567_890_123_456_789L;
        assertEquals(expected, NumberInput.parseLong19(n19, 0, false));
    }

    @Test
    public void testParseLong19Negative() {
        char[] n19 = "1234567890123456789".toCharArray();
        long expected = -1_234_567_890_123_456_789L;
        assertEquals(expected, NumberInput.parseLong19(n19, 0, true));
    }

    /* -------------------------------------------------------- */
    /*  inLongRange(char[], int, int, boolean)                 */
    /* -------------------------------------------------------- */

    @Test
    public void testInLongRangeCharArray() {
        String max = "9223372036854775807";
        String tooLarge = "9223372036854775808";

        char[] maxArr = max.toCharArray();
        char[] tooLargeArr = tooLarge.toCharArray();

        // Positive range
        assertTrue(NumberInput.inLongRange(maxArr, 0, max.length(), false));
        assertFalse(NumberInput.inLongRange(tooLargeArr, 0, tooLarge.length(), false));

        // Negative range (using absolute value of Long.MIN_VALUE)
        char[] minAbsArr = "9223372036854775808".toCharArray();
        assertTrue(NumberInput.inLongRange(minAbsArr, 0, minAbsArr.length, true));
    }

    @Test
    public void testInLongRangeString() {
        String max = "9223372036854775807";
        String tooLarge = "9223372036854775808";

        assertTrue(NumberInput.inLongRange(max, false));
        assertFalse(NumberInput.inLongRange(tooLarge, false));

        // Negative check
        assertTrue(NumberInput.inLongRange("9223372036854775808", true));
    }

    /* New tests for equal-length comparisons */
    @Test
    public void testInLongRangeCharArrayMaxLessByOne() {
        char[] lessThanMax = "9223372036854775806".toCharArray();
        assertTrue(NumberInput.inLongRange(lessThanMax, 0, lessThanMax.length, false));
    }

    @Test
    public void testInLongRangeCharArrayMinGreaterByOneNegative() {
        char[] greaterThanMin = "9223372036854775809".toCharArray();
        assertFalse(NumberInput.inLongRange(greaterThanMin, 0, greaterThanMin.length, true));
    }

    /* -------------------------------------------------------- */
    /*  looksLikeValidNumber(String)                           */
    /* -------------------------------------------------------- */

    @Test
    public void testLooksLikeValidNumber() {
        assertFalse(NumberInput.looksLikeValidNumber(null));
        assertFalse(NumberInput.looksLikeValidNumber(""));
        assertTrue(NumberInput.looksLikeValidNumber("5"));
        assertTrue(NumberInput.looksLikeValidNumber("-12.34e2"));
        assertTrue(NumberInput.looksLikeValidNumber("+12."));
        assertTrue(NumberInput.looksLikeValidNumber(".5"));
        assertFalse(NumberInput.looksLikeValidNumber("12a"));
        assertFalse(NumberInput.looksLikeValidNumber(" 123")); // leading space
    }

    /* New tests for sign-only strings */
    @Test
    public void testLooksLikeValidNumberSignOnly() {
        assertFalse(NumberInput.looksLikeValidNumber("+"));
        assertFalse(NumberInput.looksLikeValidNumber("-"));
    }

    @Test
    public void testLooksLikeValidNumberNonDigitSingleChar() {
        assertFalse(NumberInput.looksLikeValidNumber("@"));
    }

    @Test
    public void testLooksLikeValidNumberPlusDot() {
        assertFalse(NumberInput.looksLikeValidNumber("+."));
    }

    /* -------------------------------------------------------- */
    /*  parseAsInt(String, int)                                 */
    /* -------------------------------------------------------- */

    @Test
    public void testParseAsInt() {
        assertEquals(42, NumberInput.parseAsInt(" 42 ", -1));
        assertEquals(-12, NumberInput.parseAsInt("-12.34", 0)); // truncated from double
        assertEquals(99, NumberInput.parseAsInt(null, 99));
        assertEquals(100, NumberInput.parseAsInt("   ", 100));
        assertEquals(123, NumberInput.parseAsInt("123.456", -1));
    }

    /* New tests for parseInt(String) edge cases */
    @Test(expected = NumberFormatException.class)
    public void testParseIntStringNegativeOnly() {
        NumberInput.parseInt("-");
    }

    @Test(expected = NumberFormatException.class)
    public void testParseIntStringNonDigitAfterSign() {
        NumberInput.parseInt("+a");
    }

    @Test(expected = NumberFormatException.class)
    public void testParseIntStringNonDigitInMiddle() {
        NumberInput.parseInt("-12a");
    }

    /* Additional tests for large numeric strings */
    @Test(expected = NumberFormatException.class)
    public void testParseIntStringLargePositive() {
        NumberInput.parseInt("+12345678901"); // 11 digits
    }

    @Test(expected = NumberFormatException.class)
    public void testParseIntStringLargeNegative() {
        NumberInput.parseInt("-12345678901");
    }

    /* New tests for sign-only strings in parseAsInt */
    @Test
    public void testParseAsIntSignOnly() {
        assertEquals(99, NumberInput.parseAsInt("+", 99));
        assertEquals(-123, NumberInput.parseAsInt("-", -123));
    }

    /* -------------------------------------------------------- */
    /*  parseAsLong(String, long)                               */
    /* -------------------------------------------------------- */

    @Test
    public void testParseAsLong() {
        assertEquals(Long.MAX_VALUE,
                NumberInput.parseAsLong("9223372036854775807", -1L));
        assertEquals(123L,
                NumberInput.parseAsLong("123.456", -1L));
        // fallback to default when non‑numeric suffix
        assertEquals(0L,
                NumberInput.parseAsLong("-1000abc", 0L));
    }

    /* Test for numeric string exceeding long range */
    @Test
    public void testParseAsLongTooLargeNumericFallback() {
        String tooLarge = "99999999999999999999";
        assertEquals(-1L, NumberInput.parseAsLong(tooLarge, -1L));
    }

    /* New tests for sign-only strings in parseAsLong */
    @Test
    public void testParseAsLongSignOnly() {
        assertEquals(-9876543210L, NumberInput.parseAsLong("+", -9876543210L));
        assertEquals(112233445566778899L, NumberInput.parseAsLong("-", 112233445566778899L));
    }

    /* -------------------------------------------------------- */
    /*  parseAsDouble(String, double, boolean)                 */
    /* -------------------------------------------------------- */

    @Test
    public void testParseAsDouble() {
        assertEquals(1e2, NumberInput.parseAsDouble("1e2", Double.NaN), 0.0);
        assertTrue(Double.isNaN(NumberInput.parseAsDouble(null, Double.NaN)));
        assertTrue(Double.isNaN(NumberInput.parseAsDouble("", Double.NaN)));
        assertEquals(3.14, NumberInput.parseAsDouble("3.14", Double.NaN), 1e-9);
        assertTrue(Double.isNaN(NumberInput.parseAsDouble("abc", Double.NaN)));
    }

    /* -------------------------------------------------------- */
    /*  parseBigDecimal(String)                                 */
    /* -------------------------------------------------------- */

    @Test
    public void testParseBigDecimal() {
        BigDecimal expected = new BigDecimal("12.34");
        assertEquals(expected, NumberInput.parseBigDecimal("12.34"));
    }

    @Test(expected = NumberFormatException.class)
    public void testParseBigDecimalFastParserInvalid() {
        NumberInput.parseBigDecimal("abc", true);
    }

    /* -------------------------------------------------------- */
    /*  parseBigInteger(String)                                 */
    /* -------------------------------------------------------- */

    @Test
    public void testParseBigInteger() {
        BigInteger expected = new BigInteger("123456789012345678901234567890");
        assertEquals(expected, NumberInput.parseBigInteger("123456789012345678901234567890"));
    }

    /* -------------------------------------------------------- */
    /*  parseDouble and parseFloat with fast parser flag       */
    /* -------------------------------------------------------- */

    @Test
    public void testParseDoubleFastParser() {
        String s = "3.141592653589793";
        double expected = Double.parseDouble(s);
        assertEquals(expected, NumberInput.parseDouble(s, true), 1e-15);
        assertEquals(expected, NumberInput.parseDouble(s, false), 1e-15);
    }

    @Test
    public void testParseFloatFastParser() {
        String s = "2.71828f";
        float expected = Float.parseFloat(s);
        assertEquals(expected, NumberInput.parseFloat(s, true), 0.0f);
        assertEquals(expected, NumberInput.parseFloat(s, false), 0.0f);
    }

    /* -------------------------------------------------------- */
    /*  Additional tests for uncovered branch coverage         */
    /* -------------------------------------------------------- */

    @Test
    public void testParseIntStringFallback() {
        String tenDigits = "1234567890";
        assertEquals(Integer.parseInt(tenDigits),
                NumberInput.parseInt(tenDigits));
        String negTenDigits = "-1234567890";
        assertEquals(Integer.parseInt(negTenDigits),
                NumberInput.parseInt(negTenDigits));
    }

    @Test
    public void testParseLongStringShorter() {
        String shortNumber = "1234567";
        long expected = 1234567L;
        // via the String overload which falls back to parseInt for <=9 digits
        assertEquals(expected, NumberInput.parseLong(shortNumber));
    }

    @Test
    public void testInLongRangeCharArrayShorter() {
        char[] shortArr = "123".toCharArray(); // length 3 < 19
        assertTrue(NumberInput.inLongRange(shortArr, 0, shortArr.length, false));
        assertTrue(NumberInput.inLongRange(shortArr, 0, shortArr.length, true));
    }

    @Test
    public void testInLongRangeStringShorter() {
        String shortStr = "123";
        assertTrue(NumberInput.inLongRange(shortStr, false));
        assertTrue(NumberInput.inLongRange(shortStr, true));
    }

    @Test
    public void testInLongRangeStringTooLargePositive() {
        String tooLargePos = "92233720368547758070"; // 20 digits > MAX_LONG_STR length
        assertFalse(NumberInput.inLongRange(tooLargePos, false));
    }

    @Test
    public void testInLongRangeCharArrayTooLargeNegative() {
        char[] tooLargeArr = "92233720368547758070".toCharArray(); // 20 digits > MIN_LONG_STR_NO_SIGN length
        assertFalse(NumberInput.inLongRange(tooLargeArr, 0, tooLargeArr.length, true));
    }

    @Test
    public void testParseAsIntExponent() {
        String expStr = "123e4";
        int expected = (int) NumberInput.parseDouble(expStr, true);
        assertEquals(expected, NumberInput.parseAsInt(expStr, -1));
    }

    @Test
    public void testParseAsLongExponent() {
        String expStr = "5.5E2";
        long expected = (long) NumberInput.parseDouble(expStr, true);
        assertEquals(expected, NumberInput.parseAsLong(expStr, -1L));
    }

    @Test
    public void testParseBigDecimalFastParser() {
        BigDecimal expected = new BigDecimal("12.34");
        assertEquals(expected, NumberInput.parseBigDecimal("12.34", true));
    }

    @Test
    public void testParseBigIntegerWithRadixFastParser() {
        BigInteger expected = new BigInteger("10", 16); // 0x10 -> decimal 16
        assertEquals(expected, NumberInput.parseBigIntegerWithRadix("10", 16, true));
    }

    @Test
    public void testParseBigIntegerWithRadixDefault() {
        BigInteger expected = new BigInteger("10", 16);
        assertEquals(expected, NumberInput.parseBigIntegerWithRadix("10", 16, false));
    }

    @Test
    public void testParseAsIntTooLongNumericFallback() {
        assertEquals(-1, NumberInput.parseAsInt("9999999999", -1));
    }
}
