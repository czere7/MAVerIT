package tools.jackson.core.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Test;

public class NumberInputTest {

    @Test
    public void parseIntCharArrayParsesDigitsAndOffsets() {
        assertEquals(123456789,
                NumberInput.parseInt("123456789".toCharArray(), 0, 9));
        assertEquals(42,
                NumberInput.parseInt("xx+42yy".toCharArray(), 2, 3));
        assertEquals(7,
                NumberInput.parseInt("7".toCharArray(), 0, 1));
        assertEquals(1234,
                NumberInput.parseInt("xx1234yy".toCharArray(), 2, 4));
        assertEquals(12345678,
                NumberInput.parseInt("12345678".toCharArray(), 0, 8));
        assertEquals(123456789,
                NumberInput.parseInt("+123456789".toCharArray(), 0, 10));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void parseIntCharArrayRejectsZeroLengthInput() {
        NumberInput.parseInt("x".toCharArray(), 0, 0);
    }

    @Test
    public void parseIntStringHandlesSignsAndBoundaries() {
        assertEquals(0, NumberInput.parseInt("0"));
        assertEquals(0, NumberInput.parseInt("-0"));
        assertEquals(123, NumberInput.parseInt("123"));
        assertEquals(-123, NumberInput.parseInt("-123"));
        assertEquals(-12, NumberInput.parseInt("-12"));
        assertEquals(-1234, NumberInput.parseInt("-1234"));
        assertEquals(Integer.MAX_VALUE,
                NumberInput.parseInt("2147483647"));
        assertEquals(Integer.MIN_VALUE,
                NumberInput.parseInt("-2147483648"));
        assertEquals(1234567890,
                NumberInput.parseInt("1234567890"));
        assertEquals(-1234567890,
                NumberInput.parseInt("-1234567890"));
    }

    @Test
    public void parseIntStringExercisesLengthAndFallbackBoundaries() {
        assertEquals(214748364,
                NumberInput.parseInt("214748364"));
        assertEquals(-214748364,
                NumberInput.parseInt("-214748364"));
        assertEquals(2147483647,
                NumberInput.parseInt("2147483647"));
        assertEquals(-2147483648,
                NumberInput.parseInt("-2147483648"));
        assertEquals(123456789,
                NumberInput.parseInt("+123456789"));
    }

    @Test(expected = NumberFormatException.class)
    public void parseIntStringRejectsInvalidInput() {
        NumberInput.parseInt("12x");
    }

    @Test(expected = NumberFormatException.class)
    public void parseIntStringRejectsInvalidFirstDigit() {
        NumberInput.parseInt("x12");
    }

    @Test(expected = NumberFormatException.class)
    public void parseIntStringRejectsInvalidSecondDigit() {
        NumberInput.parseInt("1x2");
    }

    @Test(expected = NumberFormatException.class)
    public void parseIntStringRejectsInvalidDigitAfterThreeCharacters() {
        NumberInput.parseInt("123x");
    }

    @Test(expected = NumberFormatException.class)
    public void parseIntStringRejectsSignOnlyValue() {
        NumberInput.parseInt("-");
    }

    @Test(expected = StringIndexOutOfBoundsException.class)
    public void parseIntStringRejectsEmptyValue() {
        NumberInput.parseInt("");
    }

    @Test(expected = NumberFormatException.class)
    public void parseIntStringRejectsPlusSignOnlyValue() {
        NumberInput.parseInt("+");
    }

    @Test
    public void parseLongCharArrayParsesValuesAndOffsets() {
        char[] value = "001234567890".toCharArray();
        assertEquals(1234567890L,
                NumberInput.parseLong(value, 0, value.length));

        char[] offsetValue = "xx123456789012345678yy".toCharArray();
        assertEquals(123456789012345678L,
                NumberInput.parseLong(offsetValue, 2, 18));
        assertEquals(1000000000L,
                NumberInput.parseLong("1000000000".toCharArray(), 0, 10));
        assertEquals(999999999999999999L,
                NumberInput.parseLong(
                        "999999999999999999".toCharArray(), 0, 18));
    }

    @Test
    public void parseLong19HandlesLongBoundaries() {
        assertEquals(Long.MAX_VALUE,
                NumberInput.parseLong19(
                        "9223372036854775807".toCharArray(), 0, false));
        assertEquals(Long.MIN_VALUE,
                NumberInput.parseLong19(
                        "9223372036854775808".toCharArray(), 0, true));
        assertEquals(1234567890123456789L,
                NumberInput.parseLong19(
                        "xx1234567890123456789yy".toCharArray(), 2, false));
    }

    @Test
    public void parseLongStringHandlesBoundaries() {
        assertEquals(123456789L,
                NumberInput.parseLong("123456789"));
        assertEquals(-123456789L,
                NumberInput.parseLong("-123456789"));
        assertEquals(Long.MAX_VALUE,
                NumberInput.parseLong("9223372036854775807"));
        assertEquals(Long.MIN_VALUE,
                NumberInput.parseLong("-9223372036854775808"));
    }

    @Test
    public void inLongRangeChecksSignedMagnitudeBoundaries() {
        assertTrue(NumberInput.inLongRange(
                "9223372036854775807", false));
        assertFalse(NumberInput.inLongRange(
                "9223372036854775808", false));
        assertTrue(NumberInput.inLongRange(
                "9223372036854775808", true));
        assertFalse(NumberInput.inLongRange(
                "9223372036854775809", true));
        assertTrue(NumberInput.inLongRange("1", false));
        assertFalse(NumberInput.inLongRange(
                "12345678901234567890", false));
        assertTrue(NumberInput.inLongRange(
                "9223372036854775806", false));
        assertFalse(NumberInput.inLongRange(
                "9993372036854775807", false));
    }

    @Test
    public void inLongRangeCharArrayHonorsOffsetAndLength() {
        char[] value =
                "xx9223372036854775807yy".toCharArray();
        assertTrue(NumberInput.inLongRange(value, 2, 19, false));

        char[] tooLarge =
                "xx9223372036854775808yy".toCharArray();
        assertFalse(NumberInput.inLongRange(tooLarge, 2, 19, false));

        assertTrue(NumberInput.inLongRange(
                "9223372036854775808".toCharArray(), 0, 19, true));
        assertFalse(NumberInput.inLongRange(
                "9223372036854775809".toCharArray(), 0, 19, true));
    }

    @Test
    public void inLongRangeDistinguishesLengthAndFirstDifferentDigit() {
        assertTrue(NumberInput.inLongRange(
                "0000000000000000001", false));
        assertFalse(NumberInput.inLongRange(
                "10000000000000000000", false));
        assertTrue(NumberInput.inLongRange(
                "8223372036854775807", false));
        assertFalse(NumberInput.inLongRange(
                "9223372036854775808", false));
        assertTrue(NumberInput.inLongRange(
                "9223372036854775807", true));
        assertTrue(NumberInput.inLongRange(
                "9223372036854775808", true));
        assertTrue(NumberInput.inLongRange(
                "8223372036854775808", true));
        assertFalse(NumberInput.inLongRange(
                "9223372036854775809", true));
    }

    @Test
    public void parseAsIntHandlesDefaultsAndCoercion() {
        assertEquals(42, NumberInput.parseAsInt(" 42 ", -1));
        assertEquals(42, NumberInput.parseAsInt("+42", -1));
        assertEquals(-42, NumberInput.parseAsInt("-42", -1));
        assertEquals(12, NumberInput.parseAsInt("12.9", -1));
        assertEquals(1000, NumberInput.parseAsInt("1e3", -1));
        assertEquals(1, NumberInput.parseAsInt("++1", 7));
        assertEquals(7, NumberInput.parseAsInt("+", 7));
        assertEquals(7, NumberInput.parseAsInt("-", 7));
        assertEquals(7, NumberInput.parseAsInt("1e", 7));
        assertEquals(7, NumberInput.parseAsInt(null, 7));
        assertEquals(7, NumberInput.parseAsInt("   ", 7));
        assertEquals(7, NumberInput.parseAsInt("not-a-number", 7));
        assertEquals(7, NumberInput.parseAsInt("2147483648", 7));
        assertEquals(5, NumberInput.parseAsInt("-2147483649", 5));
        assertEquals(5, NumberInput.parseAsInt("12.3.4", 5));
    }

    @Test
    public void parseAsIntExercisesExactIntegerLimitsAndEmptySign() {
        assertEquals(Integer.MAX_VALUE,
                NumberInput.parseAsInt("2147483647", -1));
        assertEquals(Integer.MIN_VALUE,
                NumberInput.parseAsInt("-2147483648", -1));
        assertEquals(13, NumberInput.parseAsInt("+13", -1));
        assertEquals(19, NumberInput.parseAsInt("+", 19));
        assertEquals(19, NumberInput.parseAsInt("+  ", 19));
    }

    @Test
    public void parseAsLongHandlesValuesAndDefaults() {
        assertEquals(1234567890123L,
                NumberInput.parseAsLong("1234567890123", -1L));
        assertEquals(-12L,
                NumberInput.parseAsLong("-12.8", -1L));
        assertEquals(10000000000L,
                NumberInput.parseAsLong("1e10", -1L));
        assertEquals(12L,
                NumberInput.parseAsLong("+12", -1L));
        assertEquals(9L,
                NumberInput.parseAsLong("+", 9L));
        assertEquals(9L,
                NumberInput.parseAsLong("-", 9L));
        assertEquals(9L,
                NumberInput.parseAsLong("1e", 9L));
        assertEquals(9L,
                NumberInput.parseAsLong(null, 9L));
        assertEquals(9L,
                NumberInput.parseAsLong("", 9L));
        assertEquals(9L,
                NumberInput.parseAsLong("invalid", 9L));
        assertEquals(9L,
                NumberInput.parseAsLong(
                        "9223372036854775808", 9L));
    }

    @Test
    public void parseAsLongExercisesExactLimitsAndOverflowFallback() {
        assertEquals(Long.MAX_VALUE,
                NumberInput.parseAsLong("9223372036854775807", -1L));
        assertEquals(Long.MIN_VALUE,
                NumberInput.parseAsLong("-9223372036854775808", -1L));
        assertEquals(11L, NumberInput.parseAsLong("+", 11L));
        assertEquals(11L, NumberInput.parseAsLong("1.2.3", 11L));
        assertEquals(11L, NumberInput.parseAsLong(
                "9223372036854775808", 11L));
    }

    @Test
    public void parseAsDoubleUsesDefaultsAndTrimsInput() {
        assertEquals(4.25,
                NumberInput.parseAsDouble(" 4.25 ", -1.0), 0.0);
        assertEquals(-3.0,
                NumberInput.parseAsDouble("bad", -3.0), 0.0);
        assertEquals(3.5,
                NumberInput.parseAsDouble(" 3.5 ", -1.0, false), 0.0);
        assertEquals(2.0,
                NumberInput.parseAsDouble("2", -1.0, true), 0.0);
        assertEquals(-1.0,
                NumberInput.parseAsDouble(null, -1.0, false), 0.0);
        assertEquals(-1.0,
                NumberInput.parseAsDouble(" ", -1.0, false), 0.0);
    }

    @Test
    public void parseDoubleSupportsStringAndCharArrayForms() {
        assertEquals(12.5,
                NumberInput.parseDouble("12.5", false), 0.0);
        assertEquals(-1250.0,
                NumberInput.parseDouble("-1.25e3", true), 0.0);
        assertEquals(6.5,
                NumberInput.parseDouble("6.5"), 0.0);

        char[] value = "xx123.75yy".toCharArray();
        assertEquals(123.75,
                NumberInput.parseDouble(value, 2, 6, false), 0.0);
        assertEquals(123.75,
                NumberInput.parseDouble(
                        "123.75".toCharArray(), true), 0.0);
    }

    @Test
    public void parseDoubleCharArrayUsesExactOffsetForBothParsers() {
        char[] value = "xx-0.125e2yy".toCharArray();
        assertEquals(-12.5,
                NumberInput.parseDouble(value, 2, 8, false), 0.0);
        assertEquals(-12.5,
                NumberInput.parseDouble(value, 2, 8, true), 0.0);
    }

    @Test(expected = NumberFormatException.class)
    public void parseDoubleRejectsInvalidString() {
        NumberInput.parseDouble("12x", false);
    }

    @Test
    public void parseFloatSupportsStringAndCharArrayForms() {
        assertEquals(1.25f,
                NumberInput.parseFloat("1.25", false), 0.0f);
        assertEquals(-250.0f,
                NumberInput.parseFloat("-2.5e2", true), 0.0f);
        assertEquals(2.5f,
                NumberInput.parseFloat("2.5"), 0.0f);

        char[] value = "xx6.75yy".toCharArray();
        assertEquals(6.75f,
                NumberInput.parseFloat(value, 2, 4, false), 0.0f);
        assertEquals(6.75f,
                NumberInput.parseFloat(
                        "6.75".toCharArray(), true), 0.0f);
    }

    @Test
    public void parseFloatCharArrayUsesExactOffsetForBothParsers() {
        char[] value = "xx-0.125e2yy".toCharArray();
        assertEquals(-12.5f,
                NumberInput.parseFloat(value, 2, 8, false), 0.0f);
        assertEquals(-12.5f,
                NumberInput.parseFloat(value, 2, 8, true), 0.0f);
    }

    @Test(expected = NumberFormatException.class)
    public void parseFloatRejectsInvalidString() {
        NumberInput.parseFloat("not-float", false);
    }

    @Test
    public void parseBigDecimalSupportsStringAndArrayForms() {
        assertEquals(0,
                new BigDecimal("12345.6700").compareTo(
                        NumberInput.parseBigDecimal(
                                "12345.6700", false)));
        assertEquals(0,
                new BigDecimal("1.25e3").compareTo(
                        NumberInput.parseBigDecimal("1.25e3", true)));

        char[] value = "xx987.6500yy".toCharArray();
        assertEquals(0,
                new BigDecimal("987.6500").compareTo(
                        NumberInput.parseBigDecimal(
                                value, 2, 8, false)));
        assertEquals(0,
                new BigDecimal("987.6500").compareTo(
                        NumberInput.parseBigDecimal(
                                "987.6500".toCharArray(), true)));
        assertEquals(new BigDecimal("12.50"),
                NumberInput.parseBigDecimal("12.50"));
        assertNotNull(NumberInput.parseBigDecimal(
                "42.125".toCharArray()));
    }

    @Test
    public void parseBigDecimalPreservesLargeExactValuesInBothModes() {
        String value = "123456789012345678901234567890.123456789";
        BigDecimal expected = new BigDecimal(value);
        assertEquals(expected,
                NumberInput.parseBigDecimal(value, false));
        assertEquals(expected,
                NumberInput.parseBigDecimal(value, true));
        char[] wrapped = ("xx" + value + "yy").toCharArray();
        assertEquals(expected,
                NumberInput.parseBigDecimal(
                        wrapped, 2, value.length(), false));
        assertEquals(expected,
                NumberInput.parseBigDecimal(
                        wrapped, 2, value.length(), true));
    }

    @Test(expected = NumberFormatException.class)
    public void parseBigDecimalRejectsInvalidString() {
        NumberInput.parseBigDecimal("1.2.3", false);
    }

    @Test(expected = NumberFormatException.class)
    public void parseBigDecimalFastParserRejectsInvalidString() {
        NumberInput.parseBigDecimal("1.2.3", true);
    }

    @Test
    public void parseBigIntegerSupportsDecimalAndRadixForms() {
        BigInteger large =
                new BigInteger("123456789012345678901234567890");
        assertEquals(large,
                NumberInput.parseBigInteger(large.toString(), false));
        assertEquals(large,
                NumberInput.parseBigInteger(large.toString(), true));
        assertEquals(new BigInteger("123456789"),
                NumberInput.parseBigInteger("123456789"));
        assertEquals(new BigInteger("ff", 16),
                NumberInput.parseBigIntegerWithRadix("ff", 16, false));
        assertEquals(new BigInteger("101010", 2),
                NumberInput.parseBigIntegerWithRadix(
                        "101010", 2, true));
    }

    @Test
    public void parseBigIntegerWithRadixPreservesSignsAndValues() {
        assertEquals(new BigInteger("-ff", 16),
                NumberInput.parseBigIntegerWithRadix("-ff", 16, false));
        assertEquals(new BigInteger("-ff", 16),
                NumberInput.parseBigIntegerWithRadix("-ff", 16, true));
        assertEquals(new BigInteger("zz", 36),
                NumberInput.parseBigIntegerWithRadix("zz", 36, true));
    }

    @Test(expected = NumberFormatException.class)
    public void parseBigIntegerRejectsInvalidString() {
        NumberInput.parseBigInteger("12x", true);
    }

    @Test
    public void looksLikeValidNumberRecognizesSupportedForms() {
        assertTrue(NumberInput.looksLikeValidNumber("0"));
        assertTrue(NumberInput.looksLikeValidNumber("7"));
        assertTrue(NumberInput.looksLikeValidNumber("0012"));
        assertTrue(NumberInput.looksLikeValidNumber("+12.5"));
        assertTrue(NumberInput.looksLikeValidNumber("-1.2e+3"));
        assertTrue(NumberInput.looksLikeValidNumber("12."));
        assertTrue(NumberInput.looksLikeValidNumber("-.5"));
        assertTrue(NumberInput.looksLikeValidNumber("1e10"));
        assertTrue(NumberInput.looksLikeValidNumber("1E-10"));
        assertTrue(NumberInput.looksLikeValidNumber("+12."));
    }

    @Test
    public void looksLikeValidNumberRejectsInvalidForms() {
        assertFalse(NumberInput.looksLikeValidNumber(null));
        assertFalse(NumberInput.looksLikeValidNumber(""));
        assertFalse(NumberInput.looksLikeValidNumber("."));
        assertFalse(NumberInput.looksLikeValidNumber("x"));
        assertFalse(NumberInput.looksLikeValidNumber("1e"));
        assertFalse(NumberInput.looksLikeValidNumber("12.e"));
        assertFalse(NumberInput.looksLikeValidNumber("e10"));
        assertFalse(NumberInput.looksLikeValidNumber(" 12"));
        assertFalse(NumberInput.looksLikeValidNumber("12 "));
        assertFalse(NumberInput.looksLikeValidNumber("NaN"));
        assertFalse(NumberInput.looksLikeValidNumber("+"));
        assertFalse(NumberInput.looksLikeValidNumber("-"));
        assertTrue(NumberInput.looksLikeValidNumber("1."));
        assertTrue(NumberInput.looksLikeValidNumber("-1."));
        assertTrue(NumberInput.looksLikeValidNumber("+1."));
    }

    @Test
    public void looksLikeValidNumberExercisesSingleCharacterAndTrailingDotBoundaries() {
        assertTrue(NumberInput.looksLikeValidNumber("0"));
        assertTrue(NumberInput.looksLikeValidNumber("9"));
        assertFalse(NumberInput.looksLikeValidNumber("a"));
        assertFalse(NumberInput.looksLikeValidNumber("+"));
        assertFalse(NumberInput.looksLikeValidNumber("-"));
        assertTrue(NumberInput.looksLikeValidNumber("9."));
        assertTrue(NumberInput.looksLikeValidNumber("-9."));
        assertTrue(NumberInput.looksLikeValidNumber("+9."));
        assertFalse(NumberInput.looksLikeValidNumber("9.."));
        assertFalse(NumberInput.looksLikeValidNumber("9.e1"));
        assertFalse(NumberInput.looksLikeValidNumber("1e+"));
        assertFalse(NumberInput.looksLikeValidNumber("1e-"));
    }
}
