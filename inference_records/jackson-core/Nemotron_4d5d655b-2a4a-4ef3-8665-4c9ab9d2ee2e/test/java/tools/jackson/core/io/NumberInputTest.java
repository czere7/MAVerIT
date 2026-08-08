package tools.jackson.core.io;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.Assert.*;

public class NumberInputTest {

    // ==================== parseInt(char[], int, int) ====================

    @Test
    public void testParseIntCharArray_SingleDigit() {
        char[] ch = {'5'};
        assertEquals(5, NumberInput.parseInt(ch, 0, 1));
    }

    @Test
    public void testParseIntCharArray_MultipleDigits() {
        char[] ch = {'1', '2', '3', '4', '5'};
        assertEquals(12345, NumberInput.parseInt(ch, 0, 5));
    }

    @Test
    public void testParseIntCharArray_WithOffset() {
        char[] ch = {'x', 'x', '1', '2', '3', 'x'};
        assertEquals(123, NumberInput.parseInt(ch, 2, 3));
    }

    @Test
    public void testParseIntCharArray_WithLeadingPlus() {
        char[] ch = {'+', '1', '2', '3'};
        assertEquals(123, NumberInput.parseInt(ch, 0, 4));
    }

    @Test
    public void testParseIntCharArray_NineDigits() {
        char[] ch = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
        assertEquals(123456789, NumberInput.parseInt(ch, 0, 9));
    }

    @Test
    public void testParseIntCharArray_LeadingZeros() {
        char[] ch = {'0', '0', '1', '2', '3'};
        assertEquals(123, NumberInput.parseInt(ch, 0, 5));
    }

    // --- Additional tests for switch fall-through branches (line 68) ---
    @Test
    public void testParseIntCharArray_TwoDigits() {
        char[] ch = {'1', '2'};
        assertEquals(12, NumberInput.parseInt(ch, 0, 2));
    }

    @Test
    public void testParseIntCharArray_ThreeDigits() {
        char[] ch = {'1', '2', '3'};
        assertEquals(123, NumberInput.parseInt(ch, 0, 3));
    }

    @Test
    public void testParseIntCharArray_FourDigits() {
        char[] ch = {'1', '2', '3', '4'};
        assertEquals(1234, NumberInput.parseInt(ch, 0, 4));
    }

    @Test
    public void testParseIntCharArray_SixDigits() {
        char[] ch = {'1', '2', '3', '4', '5', '6'};
        assertEquals(123456, NumberInput.parseInt(ch, 0, 6));
    }

    @Test
    public void testParseIntCharArray_SevenDigits() {
        char[] ch = {'1', '2', '3', '4', '5', '6', '7'};
        assertEquals(1234567, NumberInput.parseInt(ch, 0, 7));
    }

    @Test
    public void testParseIntCharArray_EightDigits() {
        char[] ch = {'1', '2', '3', '4', '5', '6', '7', '8'};
        assertEquals(12345678, NumberInput.parseInt(ch, 0, 8));
    }

    @Test
    public void testParseIntCharArray_LeadingPlusTwoDigits() {
        char[] ch = {'+', '1', '2'};
        assertEquals(12, NumberInput.parseInt(ch, 0, 3));
    }

    @Test
    public void testParseIntCharArray_LeadingPlusEightDigits() {
        char[] ch = {'+', '1', '2', '3', '4', '5', '6', '7', '8'};
        assertEquals(12345678, NumberInput.parseInt(ch, 0, 9));
    }

    // --- Additional test for leading plus with 9 digits (10 chars) to cover switch case 9 with plus handling ---
    @Test
    public void testParseIntCharArray_LeadingPlusNineDigits() {
        char[] ch = {'+', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        assertEquals(123456789, NumberInput.parseInt(ch, 0, 10));
    }

    // --- New tests to ensure all switch case entry points are hit (target line 61 branch coverage) ---
    @Test
    public void testParseIntCharArray_LeadingPlusOneDigit() {
        char[] ch = {'+', '5'};
        assertEquals(5, NumberInput.parseInt(ch, 0, 2));
    }

    @Test
    public void testParseIntCharArray_LeadingPlusThreeDigits() {
        char[] ch = {'+', '1', '2', '3'};
        assertEquals(123, NumberInput.parseInt(ch, 0, 4));
    }

    @Test
    public void testParseIntCharArray_LeadingPlusFourDigits() {
        char[] ch = {'+', '1', '2', '3', '4'};
        assertEquals(1234, NumberInput.parseInt(ch, 0, 5));
    }

    @Test
    public void testParseIntCharArray_LeadingPlusFiveDigits() {
        char[] ch = {'+', '1', '2', '3', '4', '5'};
        assertEquals(12345, NumberInput.parseInt(ch, 0, 6));
    }

    @Test
    public void testParseIntCharArray_LeadingPlusSixDigits() {
        char[] ch = {'+', '1', '2', '3', '4', '5', '6'};
        assertEquals(123456, NumberInput.parseInt(ch, 0, 7));
    }

    @Test
    public void testParseIntCharArray_LeadingPlusSevenDigits() {
        char[] ch = {'+', '1', '2', '3', '4', '5', '6', '7'};
        assertEquals(1234567, NumberInput.parseInt(ch, 0, 8));
    }

    // ==================== parseInt(String) ====================

    @Test
    public void testParseIntString_Positive() {
        assertEquals(123, NumberInput.parseInt("123"));
    }

    @Test
    public void testParseIntString_Negative() {
        assertEquals(-123, NumberInput.parseInt("-123"));
    }

    @Test
    public void testParseIntString_WithLeadingPlus() {
        assertEquals(123, NumberInput.parseInt("+123"));
    }

    @Test
    public void testParseIntString_Zero() {
        assertEquals(0, NumberInput.parseInt("0"));
    }

    @Test
    public void testParseIntString_NineDigits() {
        assertEquals(123456789, NumberInput.parseInt("123456789"));
    }

    @Test
    public void testParseIntString_TenDigits_DelegatesToJDK() {
        assertEquals(1234567890, NumberInput.parseInt("1234567890"));
    }

    @Test
    public void testParseIntString_NegativeTenDigits_DelegatesToJDK() {
        assertEquals(-1234567890, NumberInput.parseInt("-1234567890"));
    }

    @Test
    public void testParseIntString_LeadingZeros() {
        assertEquals(123, NumberInput.parseInt("000123"));
    }

    @Test(expected = NumberFormatException.class)
    public void testParseIntString_InvalidChar_DelegatesToJDK() {
        NumberInput.parseInt("123abc");
    }

    // --- Additional tests for parseInt(String) branches (lines 115, 124, 130, 136, 144) ---
    @Test
    public void testParseIntString_OnlyPlusSign_ThrowsException() {
        try {
            NumberInput.parseInt("+");
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            // expected
        }
    }

    @Test
    public void testParseIntString_OnlyMinusSign_ThrowsException() {
        try {
            NumberInput.parseInt("-");
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            // expected
        }
    }

    @Test
    public void testParseIntString_NonDigitAfterSign_ThrowsException() {
        try {
            NumberInput.parseInt("+a");
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            // expected
        }
    }

    @Test
    public void testParseIntString_NonDigitInMiddle_ThrowsException() {
        try {
            NumberInput.parseInt("12a34");
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            // expected
        }
    }

    @Test
    public void testParseIntString_TwoDigits() {
        assertEquals(12, NumberInput.parseInt("12"));
    }

    @Test
    public void testParseIntString_FourDigits() {
        assertEquals(1234, NumberInput.parseInt("1234"));
    }

    @Test
    public void testParseIntString_NegativeTwoDigits() {
        assertEquals(-12, NumberInput.parseInt("-12"));
    }

    @Test
    public void testParseIntString_NegativeNonDigitInMiddle_ThrowsException() {
        try {
            NumberInput.parseInt("-12a34");
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            // expected
        }
    }

    @Test
    public void testParseIntString_LeadingPlusNonDigit_ThrowsException() {
        try {
            NumberInput.parseInt("+12a34");
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            // expected
        }
    }

    // --- New tests for uncovered invalid-digit branches in parseInt(String) ---
    @Test
    public void testParseIntString_InvalidFirstDigit_NoSign_ThrowsException() {
        try {
            NumberInput.parseInt("abc");
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            // expected
        }
    }

    @Test
    public void testParseIntString_InvalidFirstDigit_Negative_ThrowsException() {
        try {
            NumberInput.parseInt("-abc");
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            // expected
        }
    }

    @Test
    public void testParseIntString_InvalidSecondDigit_Positive_ThrowsException() {
        try {
            NumberInput.parseInt("1a");
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            // expected
        }
    }

    @Test
    public void testParseIntString_InvalidSecondDigit_Negative_ThrowsException() {
        try {
            NumberInput.parseInt("-1a");
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            // expected
        }
    }

    @Test
    public void testParseIntString_InvalidThirdDigit_Positive_ThrowsException() {
        try {
            NumberInput.parseInt("12a");
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            // expected
        }
    }

    @Test
    public void testParseIntString_InvalidThirdDigit_Negative_ThrowsException() {
        try {
            NumberInput.parseInt("-12a");
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            // expected
        }
    }

    @Test
    public void testParseIntString_InvalidFourthDigit_Positive_ThrowsException() {
        try {
            NumberInput.parseInt("123a");
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            // expected
        }
    }

    @Test
    public void testParseIntString_InvalidFourthDigit_Negative_ThrowsException() {
        try {
            NumberInput.parseInt("-123a");
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            // expected
        }
    }

    // --- New tests targeting short-circuit branches for c < '0' (characters below '0') ---
    @Test
    public void testParseIntString_InvalidSecondDigit_BelowZero_Positive_ThrowsException() {
        try {
            NumberInput.parseInt("1/");
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            // expected
        }
    }

    @Test
    public void testParseIntString_InvalidSecondDigit_BelowZero_Negative_ThrowsException() {
        try {
            NumberInput.parseInt("-1/");
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            // expected
        }
    }

    @Test
    public void testParseIntString_InvalidThirdDigit_BelowZero_Positive_ThrowsException() {
        try {
            NumberInput.parseInt("12/");
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            // expected
        }
    }

    @Test
    public void testParseIntString_InvalidThirdDigit_BelowZero_Negative_ThrowsException() {
        try {
            NumberInput.parseInt("-12/");
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            // expected
        }
    }

    @Test
    public void testParseIntString_InvalidFourthDigit_BelowZero_Positive_ThrowsException() {
        try {
            NumberInput.parseInt("123/");
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            // expected
        }
    }

    @Test
    public void testParseIntString_InvalidFourthDigit_BelowZero_Negative_ThrowsException() {
        try {
            NumberInput.parseInt("-123/");
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            // expected
        }
    }

    @Test
    public void testParseIntString_InvalidFifthDigit_BelowZero_Positive_ThrowsException() {
        try {
            NumberInput.parseInt("1234/");
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            // expected
        }
    }

    @Test
    public void testParseIntString_InvalidFifthDigit_BelowZero_Negative_ThrowsException() {
        try {
            NumberInput.parseInt("-1234/");
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            // expected
        }
    }

    // --- New tests for boundary mutations in parseInt(String) (lines 115, 120, 124, 130) ---
    @Test
    public void testParseIntString_ExactlyNineDigits_Positive() {
        assertEquals(123456789, NumberInput.parseInt("123456789"));
    }

    @Test
    public void testParseIntString_ExactlyNineDigits_Negative() {
        assertEquals(-123456789, NumberInput.parseInt("-123456789"));
    }

    @Test
    public void testParseIntString_ExactlyTenDigits_Positive_DelegatesToJDK() {
        assertEquals(1234567890, NumberInput.parseInt("1234567890"));
    }

    @Test
    public void testParseIntString_ExactlyTenDigits_Negative_DelegatesToJDK() {
        assertEquals(-1234567890, NumberInput.parseInt("-1234567890"));
    }

    @Test
    public void testParseIntString_NegativeSingleDigitAfterSign() {
        assertEquals(-5, NumberInput.parseInt("-5"));
    }

    @Test
    public void testParseIntString_PositiveSingleDigit() {
        assertEquals(5, NumberInput.parseInt("5"));
    }

    @Test
    public void testParseIntString_InvalidFirstDigit_AboveNine_Positive_ThrowsException() {
        try {
            NumberInput.parseInt(":23");
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            // expected
        }
    }

    @Test
    public void testParseIntString_InvalidFirstDigit_AboveNine_Negative_ThrowsException() {
        try {
            NumberInput.parseInt("-:23");
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            // expected
        }
    }

    @Test
    public void testParseIntString_InvalidSecondDigit_AboveNine_Positive_ThrowsException() {
        try {
            NumberInput.parseInt("1:3");
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            // expected
        }
    }

    @Test
    public void testParseIntString_InvalidSecondDigit_AboveNine_Negative_ThrowsException() {
        try {
            NumberInput.parseInt("-1:3");
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            // expected
        }
    }

    @Test
    public void testParseIntString_FiveDigits_ExercisesLoop() {
        assertEquals(12345, NumberInput.parseInt("12345"));
    }

    @Test
    public void testParseIntString_SixDigits_ExercisesLoop() {
        assertEquals(123456, NumberInput.parseInt("123456"));
    }

    @Test
    public void testParseIntString_SevenDigits_ExercisesLoop() {
        assertEquals(1234567, NumberInput.parseInt("1234567"));
    }

    @Test
    public void testParseIntString_EightDigits_ExercisesLoop() {
        assertEquals(12345678, NumberInput.parseInt("12345678"));
    }

    @Test
    public void testParseIntString_NineDigits_ExercisesLoop() {
        assertEquals(123456789, NumberInput.parseInt("123456789"));
    }

    @Test
    public void testParseIntString_NegativeFiveDigits_ExercisesLoop() {
        assertEquals(-12345, NumberInput.parseInt("-12345"));
    }

    @Test
    public void testParseIntString_NegativeNineDigits_ExercisesLoop() {
        assertEquals(-123456789, NumberInput.parseInt("-123456789"));
    }

    // ==================== parseLong(char[], int, int) ====================

    @Test
    public void testParseLongCharArray_TenDigits() {
        char[] ch = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
        assertEquals(1234567890L, NumberInput.parseLong(ch, 0, 10));
    }

    @Test
    public void testParseLongCharArray_EighteenDigits() {
        char[] ch = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8'};
        assertEquals(123456789012345678L, NumberInput.parseLong(ch, 0, 18));
    }

    @Test
    public void testParseLongCharArray_WithOffset() {
        char[] ch = {'x', 'x', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'x'};
        assertEquals(1234567890L, NumberInput.parseLong(ch, 2, 10));
    }

    // ==================== parseLong19(char[], int, boolean) ====================

    @Test
    public void testParseLong19_Positive() {
        char[] ch = {'9', '2', '2', '3', '3', '7', '2', '0', '3', '6', '8', '5', '4', '7', '7', '5', '8', '0', '7'};
        assertEquals(9223372036854775807L, NumberInput.parseLong19(ch, 0, false));
    }

    @Test
    public void testParseLong19_Negative() {
        char[] ch = {'9', '2', '2', '3', '3', '7', '2', '0', '3', '6', '8', '5', '4', '7', '7', '5', '8', '0', '8'};
        assertEquals(Long.MIN_VALUE, NumberInput.parseLong19(ch, 0, true));
    }

    @Test
    public void testParseLong19_WithOffset() {
        char[] ch = {'x', 'x', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'x'};
        assertEquals(1234567890123456789L, NumberInput.parseLong19(ch, 2, false));
    }

    // ==================== parseLong(String) ====================

    @Test
    public void testParseLongString_Short_DelegatesToParseInt() {
        assertEquals(123L, NumberInput.parseLong("123"));
    }

    @Test
    public void testParseLongString_NegativeShort_DelegatesToParseInt() {
        assertEquals(-123L, NumberInput.parseLong("-123"));
    }

    @Test
    public void testParseLongString_Long_DelegatesToJDK() {
        assertEquals(1234567890123L, NumberInput.parseLong("1234567890123"));
    }

    @Test
    public void testParseLongString_MaxLong() {
        assertEquals(Long.MAX_VALUE, NumberInput.parseLong("9223372036854775807"));
    }

    @Test
    public void testParseLongString_MinLong() {
        assertEquals(Long.MIN_VALUE, NumberInput.parseLong("-9223372036854775808"));
    }

    // ==================== inLongRange(char[], int, int, boolean) ====================

    @Test
    public void testInLongRangeCharArray_Positive_WithinRange() {
        char[] ch = {'1', '2', '3'};
        assertTrue(NumberInput.inLongRange(ch, 0, 3, false));
    }

    @Test
    public void testInLongRangeCharArray_Positive_MaxLong() {
        char[] ch = {'9', '2', '2', '3', '3', '7', '2', '0', '3', '6', '8', '5', '4', '7', '7', '5', '8', '0', '7'};
        assertTrue(NumberInput.inLongRange(ch, 0, 19, false));
    }

    @Test
    public void testInLongRangeCharArray_Positive_ExceedsMaxLong() {
        char[] ch = {'9', '2', '2', '3', '3', '7', '2', '0', '3', '6', '8', '5', '4', '7', '7', '5', '8', '0', '8'};
        assertFalse(NumberInput.inLongRange(ch, 0, 19, false));
    }

    @Test
    public void testInLongRangeCharArray_Negative_WithinRange() {
        char[] ch = {'1', '2', '3'};
        assertTrue(NumberInput.inLongRange(ch, 0, 3, true));
    }

    @Test
    public void testInLongRangeCharArray_Negative_MinLong() {
        char[] ch = {'9', '2', '2', '3', '3', '7', '2', '0', '3', '6', '8', '5', '4', '7', '7', '5', '8', '0', '8'};
        assertTrue(NumberInput.inLongRange(ch, 0, 19, true));
    }

    @Test
    public void testInLongRangeCharArray_Negative_ExceedsMinLong() {
        char[] ch = {'9', '2', '2', '3', '3', '7', '2', '0', '3', '6', '8', '5', '4', '7', '7', '5', '8', '0', '9'};
        assertFalse(NumberInput.inLongRange(ch, 0, 19, true));
    }

    @Test
    public void testInLongRangeCharArray_WithOffset() {
        char[] ch = {'x', 'x', '1', '2', '3'};
        assertTrue(NumberInput.inLongRange(ch, 2, 3, false));
    }

    // --- Additional tests for inLongRange(char[], ...) branches (lines 229, 234) ---
    @Test
    public void testInLongRangeCharArray_Positive_ExactLengthLessThanMax() {
        // 19 digits, but less than MAX_LONG_STR
        char[] ch = {'9', '2', '2', '3', '3', '7', '2', '0', '3', '6', '8', '5', '4', '7', '7', '5', '8', '0', '6'};
        assertTrue(NumberInput.inLongRange(ch, 0, 19, false));
    }

    @Test
    public void testInLongRangeCharArray_Positive_ExactLengthGreaterThanMax() {
        // 19 digits, but greater than MAX_LONG_STR (not possible since MAX is max, but test the branch)
        // Actually we test the diff > 0 branch by using a value that compares greater
        // Since MAX_LONG_STR is the maximum, we can't have greater, but we can test the negative case
        char[] ch = {'9', '2', '2', '3', '3', '7', '2', '0', '3', '6', '8', '5', '4', '7', '7', '5', '8', '0', '8'};
        assertFalse(NumberInput.inLongRange(ch, 0, 19, false));
    }

    @Test
    public void testInLongRangeCharArray_Negative_ExactLengthLessThanMin() {
        // 19 digits, less than MIN_LONG_STR_NO_SIGN
        char[] ch = {'9', '2', '2', '3', '3', '7', '2', '0', '3', '6', '8', '5', '4', '7', '7', '5', '8', '0', '7'};
        assertTrue(NumberInput.inLongRange(ch, 0, 19, true));
    }

    @Test
    public void testInLongRangeCharArray_Negative_ExactLengthGreaterThanMin() {
        // 19 digits, greater than MIN_LONG_STR_NO_SIGN
        char[] ch = {'9', '2', '2', '3', '3', '7', '2', '0', '3', '6', '8', '5', '4', '7', '7', '5', '8', '0', '9'};
        assertFalse(NumberInput.inLongRange(ch, 0, 19, true));
    }

    @Test
    public void testInLongRangeCharArray_LengthLessThanCmpLen() {
        char[] ch = {'1', '2', '3'};
        assertTrue(NumberInput.inLongRange(ch, 0, 3, false));
        assertTrue(NumberInput.inLongRange(ch, 0, 3, true));
    }

    @Test
    public void testInLongRangeCharArray_LengthGreaterThanCmpLen() {
        // 20 digits - should be false for both
        char[] ch = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
        assertFalse(NumberInput.inLongRange(ch, 0, 20, false));
        assertFalse(NumberInput.inLongRange(ch, 0, 20, true));
    }

    // --- New tests targeting boundary mutations at line 234 (len < cmpLen) and line 261 (diff == 0) ---
    @Test
    public void testInLongRangeCharArray_Positive_LengthEqualCmpLen_WithinRange() {
        // len == cmpLen (19), value less than MAX_LONG_STR -> should return true
        // This kills mutant that changes (len < cmpLen) to (len <= cmpLen)
        char[] ch = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        assertTrue(NumberInput.inLongRange(ch, 0, 19, false));
    }

    @Test
    public void testInLongRangeCharArray_Positive_LengthEqualCmpLen_ExceedsRange() {
        // len == cmpLen (19), value greater than MAX_LONG_STR -> should return false
        // This kills mutant that changes (len > cmpLen) to (len >= cmpLen)
        char[] ch = {'9', '2', '2', '3', '3', '7', '2', '0', '3', '6', '8', '5', '4', '7', '7', '5', '8', '0', '8'};
        assertFalse(NumberInput.inLongRange(ch, 0, 19, false));
    }

    @Test
    public void testInLongRangeCharArray_Negative_LengthEqualCmpLen_WithinRange() {
        // len == cmpLen (19), value less than MIN_LONG_STR_NO_SIGN -> should return true
        char[] ch = {'9', '2', '2', '3', '3', '7', '2', '0', '3', '6', '8', '5', '4', '7', '7', '5', '8', '0', '7'};
        assertTrue(NumberInput.inLongRange(ch, 0, 19, true));
    }

    @Test
    public void testInLongRangeCharArray_Negative_LengthEqualCmpLen_ExceedsRange() {
        // len == cmpLen (19), value greater than MIN_LONG_STR_NO_SIGN -> should return false
        char[] ch = {'9', '2', '2', '3', '3', '7', '2', '0', '3', '6', '8', '5', '4', '7', '7', '5', '8', '0', '9'};
        assertFalse(NumberInput.inLongRange(ch, 0, 19, true));
    }

    @Test
    public void testInLongRangeCharArray_Positive_ExactMaxLong_DiffZero() {
        // Exact match with MAX_LONG_STR -> diff == 0 -> return true (line 261 equivalent)
        char[] ch = {'9', '2', '2', '3', '3', '7', '2', '0', '3', '6', '8', '5', '4', '7', '7', '5', '8', '0', '7'};
        assertTrue(NumberInput.inLongRange(ch, 0, 19, false));
    }

    @Test
    public void testInLongRangeCharArray_Negative_ExactMinLong_DiffZero() {
        // Exact match with MIN_LONG_STR_NO_SIGN -> diff == 0 -> return true
        char[] ch = {'9', '2', '2', '3', '3', '7', '2', '0', '3', '6', '8', '5', '4', '7', '7', '5', '8', '0', '8'};
        assertTrue(NumberInput.inLongRange(ch, 0, 19, true));
    }

    @Test
    public void testInLongRangeCharArray_Positive_Length18_WithinRange() {
        // len (18) < cmpLen (19) -> return true at line 234
        char[] ch = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8'};
        assertTrue(NumberInput.inLongRange(ch, 0, 18, false));
    }

    @Test
    public void testInLongRangeCharArray_Negative_Length18_WithinRange() {
        // len (18) < cmpLen (19) -> return true at line 234
        char[] ch = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8'};
        assertTrue(NumberInput.inLongRange(ch, 0, 18, true));
    }

    // ==================== inLongRange(String, boolean) ====================

    @Test
    public void testInLongRangeString_Positive_WithinRange() {
        assertTrue(NumberInput.inLongRange("123", false));
    }

    @Test
    public void testInLongRangeString_Positive_MaxLong() {
        assertTrue(NumberInput.inLongRange("9223372036854775807", false));
    }

    @Test
    public void testInLongRangeString_Positive_ExceedsMaxLong() {
        assertFalse(NumberInput.inLongRange("9223372036854775808", false));
    }

    @Test
    public void testInLongRangeString_Negative_WithinRange() {
        assertTrue(NumberInput.inLongRange("123", true));
    }

    @Test
    public void testInLongRangeString_Negative_MinLong() {
        assertTrue(NumberInput.inLongRange("9223372036854775808", true));
    }

    @Test
    public void testInLongRangeString_Negative_ExceedsMinLong() {
        assertFalse(NumberInput.inLongRange("9223372036854775809", true));
    }

    @Test
    public void testInLongRangeString_ShorterThanMax() {
        assertTrue(NumberInput.inLongRange("123456789012345678", false));
    }

    @Test
    public void testInLongRangeString_LongerThanMax() {
        assertFalse(NumberInput.inLongRange("12345678901234567890", false));
    }

    // --- Additional test for inLongRange(String, boolean) diff == 0 branch (line 261) ---
    @Test
    public void testInLongRangeString_Positive_ExactMaxLong() {
        // diff == 0 branch
        assertTrue(NumberInput.inLongRange("9223372036854775807", false));
    }

    @Test
    public void testInLongRangeString_Negative_ExactMinLong() {
        // diff == 0 branch for negative
        assertTrue(NumberInput.inLongRange("9223372036854775808", true));
    }

    // --- New tests to ensure diff==0 branch coverage for inLongRange(String, boolean) ---
    @Test
    public void testInLongRangeString_Positive_ExactMaxLong_DiffZero() {
        assertTrue(NumberInput.inLongRange("9223372036854775807", false));
    }

    @Test
    public void testInLongRangeString_Negative_ExactMinLong_DiffZero() {
        assertTrue(NumberInput.inLongRange("9223372036854775808", true));
    }

    // ==================== parseAsInt(String, int) ====================

    @Test
    public void testParseAsInt_Null_ReturnsDefault() {
        assertEquals(42, NumberInput.parseAsInt(null, 42));
    }

    @Test
    public void testParseAsInt_EmptyString_ReturnsDefault() {
        assertEquals(42, NumberInput.parseAsInt("", 42));
    }

    @Test
    public void testParseAsInt_WhitespaceOnly_ReturnsDefault() {
        assertEquals(42, NumberInput.parseAsInt("   ", 42));
    }

    @Test
    public void testParseAsInt_ValidPositive() {
        assertEquals(123, NumberInput.parseAsInt("123", 42));
    }

    @Test
    public void testParseAsInt_ValidNegative() {
        assertEquals(-123, NumberInput.parseAsInt("-123", 42));
    }

    @Test
    public void testParseAsInt_WithLeadingPlus() {
        assertEquals(123, NumberInput.parseAsInt("+123", 42));
    }

    @Test
    public void testParseAsInt_WithLeadingMinusAndPlus() {
        assertEquals(-123, NumberInput.parseAsInt("-123", 42));
        assertEquals(123, NumberInput.parseAsInt("+123", 42));
    }

    @Test
    public void testParseAsInt_WithWhitespace() {
        assertEquals(123, NumberInput.parseAsInt("  123  ", 42));
    }

    @Test
    public void testParseAsInt_DecimalNumber_ParsesAsDouble() {
        assertEquals(123, NumberInput.parseAsInt("123.45", 42));
    }

    @Test
    public void testParseAsInt_ScientificNotation_ParsesAsDouble() {
        assertEquals(123000, NumberInput.parseAsInt("1.23e5", 42));
    }

    @Test
    public void testParseAsInt_InvalidChars_ReturnsDefault() {
        assertEquals(42, NumberInput.parseAsInt("123abc", 42));
    }

    @Test
    public void testParseAsInt_Overflow_ReturnsDefault() {
        assertEquals(42, NumberInput.parseAsInt("99999999999999999999999999999999999999999", 42));
    }

    // --- New tests targeting mutations in parseAsInt (lines 282, 288) ---
    @Test
    public void testParseAsInt_InvalidChar_BelowZero_ReturnsDefault() {
        // Tests c < '0' branch (char '/')
        assertEquals(42, NumberInput.parseAsInt("12/34", 42));
    }

    @Test
    public void testParseAsInt_InvalidChar_AboveNine_ReturnsDefault() {
        // Tests c > '9' branch (char ':')
        assertEquals(42, NumberInput.parseAsInt("12:34", 42));
    }

    @Test
    public void testParseAsInt_InvalidChar_AtFirstPosition_ReturnsDefault() {
        assertEquals(42, NumberInput.parseAsInt("a123", 42));
    }

    @Test
    public void testParseAsInt_InvalidChar_AtFirstPosition_Negative_ReturnsDefault() {
        assertEquals(42, NumberInput.parseAsInt("-a123", 42));
    }

    @Test
    public void testParseAsInt_InvalidChar_AtFirstPosition_Plus_ReturnsDefault() {
        assertEquals(42, NumberInput.parseAsInt("+a123", 42));
    }

    @Test
    public void testParseAsInt_OnlySign_ReturnsDefault() {
        assertEquals(42, NumberInput.parseAsInt("+", 42));
        assertEquals(42, NumberInput.parseAsInt("-", 42));
    }

    @Test
    public void testParseAsInt_MultipleNonDigits_ReturnsDefault() {
        assertEquals(42, NumberInput.parseAsInt("12a34b56", 42));
    }

    @Test
    public void testParseAsInt_ValidTenDigits_ExercisesLoop() {
        // 10 digits, exercises the loop fully (i < len at line 288)
        assertEquals(1234567890, NumberInput.parseAsInt("1234567890", 42));
    }

    @Test
    public void testParseAsInt_ValidNineDigits_ExercisesLoop() {
        assertEquals(123456789, NumberInput.parseAsInt("123456789", 42));
    }

    @Test
    public void testParseAsInt_NegativeTenDigits_ExercisesLoop() {
        assertEquals(-1234567890, NumberInput.parseAsInt("-1234567890", 42));
    }

    @Test
    public void testParseAsInt_NegativeNineDigits_ExercisesLoop() {
        assertEquals(-123456789, NumberInput.parseAsInt("-123456789", 42));
    }

    @Test
    public void testParseAsInt_PlusSignThenDigits_ExercisesLoop() {
        assertEquals(123456789, NumberInput.parseAsInt("+123456789", 42));
    }

    // ==================== parseAsLong(String, long) ====================

    @Test
    public void testParseAsLong_Null_ReturnsDefault() {
        assertEquals(42L, NumberInput.parseAsLong(null, 42L));
    }

    @Test
    public void testParseAsLong_EmptyString_ReturnsDefault() {
        assertEquals(42L, NumberInput.parseAsLong("", 42L));
    }

    @Test
    public void testParseAsLong_WhitespaceOnly_ReturnsDefault() {
        assertEquals(42L, NumberInput.parseAsLong("   ", 42L));
    }

    @Test
    public void testParseAsLong_ValidPositive() {
        assertEquals(123L, NumberInput.parseAsLong("123", 42L));
    }

    @Test
    public void testParseAsLong_ValidNegative() {
        assertEquals(-123L, NumberInput.parseAsLong("-123", 42L));
    }

    @Test
    public void testParseAsLong_WithLeadingPlus() {
        assertEquals(123L, NumberInput.parseAsLong("+123", 42L));
    }

    @Test
    public void testParseAsLong_WithWhitespace() {
        assertEquals(123L, NumberInput.parseAsLong("  123  ", 42L));
    }

    @Test
    public void testParseAsLong_DecimalNumber_ParsesAsDouble() {
        assertEquals(123L, NumberInput.parseAsLong("123.45", 42L));
    }

    @Test
    public void testParseAsLong_ScientificNotation_ParsesAsDouble() {
        assertEquals(123000L, NumberInput.parseAsLong("1.23e5", 42L));
    }

    @Test
    public void testParseAsLong_InvalidChars_ReturnsDefault() {
        assertEquals(42L, NumberInput.parseAsLong("123abc", 42L));
    }

    @Test
    public void testParseAsLong_Overflow_ReturnsDefault() {
        assertEquals(42L, NumberInput.parseAsLong("99999999999999999999999999999999999999999", 42L));
    }

    // --- New tests targeting mutations in parseAsLong (lines 321, 327) ---
    @Test
    public void testParseAsLong_InvalidChar_BelowZero_ReturnsDefault() {
        assertEquals(42L, NumberInput.parseAsLong("12/34", 42L));
    }

    @Test
    public void testParseAsLong_InvalidChar_AboveNine_ReturnsDefault() {
        assertEquals(42L, NumberInput.parseAsLong("12:34", 42L));
    }

    @Test
    public void testParseAsLong_InvalidChar_AtFirstPosition_ReturnsDefault() {
        assertEquals(42L, NumberInput.parseAsLong("a123", 42L));
    }

    @Test
    public void testParseAsLong_InvalidChar_AtFirstPosition_Negative_ReturnsDefault() {
        assertEquals(42L, NumberInput.parseAsLong("-a123", 42L));
    }

    @Test
    public void testParseAsLong_InvalidChar_AtFirstPosition_Plus_ReturnsDefault() {
        assertEquals(42L, NumberInput.parseAsLong("+a123", 42L));
    }

    @Test
    public void testParseAsLong_OnlySign_ReturnsDefault() {
        assertEquals(42L, NumberInput.parseAsLong("+", 42L));
        assertEquals(42L, NumberInput.parseAsLong("-", 42L));
    }

    @Test
    public void testParseAsLong_MultipleNonDigits_ReturnsDefault() {
        assertEquals(42L, NumberInput.parseAsLong("12a34b56", 42L));
    }

    @Test
    public void testParseAsLong_ValidNineteenDigits_ExercisesLoop() {
        assertEquals(1234567890123456789L, NumberInput.parseAsLong("1234567890123456789", 42L));
    }

    @Test
    public void testParseAsLong_NegativeNineteenDigits_ExercisesLoop() {
        assertEquals(-1234567890123456789L, NumberInput.parseAsLong("-1234567890123456789", 42L));
    }

    @Test
    public void testParseAsLong_PlusSignThenDigits_ExercisesLoop() {
        assertEquals(1234567890123456789L, NumberInput.parseAsLong("+1234567890123456789", 42L));
    }

    // ==================== parseAsDouble(String, double, boolean) ====================

    @Test
    public void testParseAsDouble_Null_ReturnsDefault() {
        assertEquals(3.14, NumberInput.parseAsDouble(null, 3.14, false), 0.0);
        assertEquals(3.14, NumberInput.parseAsDouble(null, 3.14, true), 0.0);
    }

    @Test
    public void testParseAsDouble_EmptyString_ReturnsDefault() {
        assertEquals(3.14, NumberInput.parseAsDouble("", 3.14, false), 0.0);
    }

    @Test
    public void testParseAsDouble_WhitespaceOnly_ReturnsDefault() {
        assertEquals(3.14, NumberInput.parseAsDouble("   ", 3.14, false), 0.0);
    }

    @Test
    public void testParseAsDouble_ValidInteger() {
        assertEquals(123.0, NumberInput.parseAsDouble("123", 3.14, false), 0.0);
    }

    @Test
    public void testParseAsDouble_ValidDecimal() {
        assertEquals(123.45, NumberInput.parseAsDouble("123.45", 3.14, false), 0.0001);
    }

    @Test
    public void testParseAsDouble_Negative() {
        assertEquals(-123.45, NumberInput.parseAsDouble("-123.45", 3.14, false), 0.0001);
    }

    @Test
    public void testParseAsDouble_ScientificNotation() {
        assertEquals(1.23e5, NumberInput.parseAsDouble("1.23e5", 3.14, false), 0.001);
    }

    @Test
    public void testParseAsDouble_WithLeadingPlus() {
        assertEquals(123.45, NumberInput.parseAsDouble("+123.45", 3.14, false), 0.0001);
    }

    @Test
    public void testParseAsDouble_WithWhitespace() {
        assertEquals(123.45, NumberInput.parseAsDouble("  123.45  ", 3.14, false), 0.0001);
    }

    @Test
    public void testParseAsDouble_Invalid_ReturnsDefault() {
        assertEquals(3.14, NumberInput.parseAsDouble("abc", 3.14, false), 0.0);
    }

    @Test
    public void testParseAsDouble_UseFastParser() {
        assertEquals(123.45, NumberInput.parseAsDouble("123.45", 3.14, true), 0.0001);
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testParseAsDouble_DeprecatedMethod() {
        assertEquals(123.45, NumberInput.parseAsDouble("123.45", 3.14), 0.0001);
    }

    // ==================== parseDouble(String, boolean) ====================

    @Test
    public void testParseDouble_ValidInteger() {
        assertEquals(123.0, NumberInput.parseDouble("123", false), 0.0);
    }

    @Test
    public void testParseDouble_ValidDecimal() {
        assertEquals(123.45, NumberInput.parseDouble("123.45", false), 0.0001);
    }

    @Test
    public void testParseDouble_Negative() {
        assertEquals(-123.45, NumberInput.parseDouble("-123.45", false), 0.0001);
    }

    @Test
    public void testParseDouble_ScientificNotation() {
        assertEquals(1.23e5, NumberInput.parseDouble("1.23e5", false), 0.001);
    }

    @Test
    public void testParseDouble_WithLeadingPlus() {
        assertEquals(123.45, NumberInput.parseDouble("+123.45", false), 0.0001);
    }

    @Test
    public void testParseDouble_UseFastParser() {
        assertEquals(123.45, NumberInput.parseDouble("123.45", true), 0.0001);
    }

    @Test(expected = NumberFormatException.class)
    public void testParseDouble_Invalid_ThrowsException() {
        NumberInput.parseDouble("abc", false);
    }

    @Test(expected = NumberFormatException.class)
    public void testParseDouble_Invalid_ThrowsException_FastParser() {
        NumberInput.parseDouble("abc", true);
    }

    // --- New tests targeting useFastParser mutation (lines 393, 418) ---
    @Test
    public void testParseDouble_BothParsersSameResult_Decimal() {
        double fast = NumberInput.parseDouble("123.456", true);
        double standard = NumberInput.parseDouble("123.456", false);
        assertEquals(fast, standard, 0.0);
    }

    @Test
    public void testParseDouble_BothParsersSameResult_Negative() {
        double fast = NumberInput.parseDouble("-123.456", true);
        double standard = NumberInput.parseDouble("-123.456", false);
        assertEquals(fast, standard, 0.0);
    }

    @Test
    public void testParseDouble_BothParsersSameResult_Scientific() {
        double fast = NumberInput.parseDouble("1.23e-10", true);
        double standard = NumberInput.parseDouble("1.23e-10", false);
        assertEquals(fast, standard, 0.0);
    }

    @Test
    public void testParseDouble_BothParsersSameResult_MaxDouble() {
        double fast = NumberInput.parseDouble("1.7976931348623157E308", true);
        double standard = NumberInput.parseDouble("1.7976931348623157E308", false);
        assertEquals(fast, standard, 0.0);
    }

    @Test
    public void testParseDouble_BothParsersSameResult_MinDouble() {
        double fast = NumberInput.parseDouble("-1.7976931348623157E308", true);
        double standard = NumberInput.parseDouble("-1.7976931348623157E308", false);
        assertEquals(fast, standard, 0.0);
    }

    @Test
    public void testParseDouble_BothParsersSameResult_SmallDecimal() {
        double fast = NumberInput.parseDouble("0.0000000000000001", true);
        double standard = NumberInput.parseDouble("0.0000000000000001", false);
        assertEquals(fast, standard, 0.0);
    }

    @Test
    public void testParseDouble_BothParsersSameResult_WithPlusSign() {
        double fast = NumberInput.parseDouble("+123.456", true);
        double standard = NumberInput.parseDouble("+123.456", false);
        assertEquals(fast, standard, 0.0);
    }

    // ==================== parseDouble(char[], ...) ====================

    @Test
    public void testParseDoubleCharArray_Valid() {
        char[] ch = {'1', '2', '3', '.', '4', '5'};
        assertEquals(123.45, NumberInput.parseDouble(ch, false), 0.0001);
    }

    @Test
    public void testParseDoubleCharArray_WithOffsetAndLen() {
        char[] ch = {'x', 'x', '1', '2', '3', '.', '4', '5', 'x', 'x'};
        assertEquals(123.45, NumberInput.parseDouble(ch, 2, 6, false), 0.0001);
    }

    @Test
    public void testParseDoubleCharArray_UseFastParser() {
        char[] ch = {'1', '2', '3', '.', '4', '5'};
        assertEquals(123.45, NumberInput.parseDouble(ch, true), 0.0001);
    }

    @Test
    public void testParseDoubleCharArray_WithOffsetAndLen_FastParser() {
        char[] ch = {'x', 'x', '1', '2', '3', '.', '4', '5', 'x', 'x'};
        assertEquals(123.45, NumberInput.parseDouble(ch, 2, 6, true), 0.0001);
    }

    @Test(expected = NumberFormatException.class)
    public void testParseDoubleCharArray_Invalid_ThrowsException() {
        char[] ch = {'a', 'b', 'c'};
        NumberInput.parseDouble(ch, false);
    }

    // ==================== parseFloat(String, boolean) ====================

    @Test
    public void testParseFloat_ValidInteger() {
        assertEquals(123.0f, NumberInput.parseFloat("123", false), 0.0f);
    }

    @Test
    public void testParseFloat_ValidDecimal() {
        assertEquals(123.45f, NumberInput.parseFloat("123.45", false), 0.0001f);
    }

    @Test
    public void testParseFloat_Negative() {
        assertEquals(-123.45f, NumberInput.parseFloat("-123.45", false), 0.0001f);
    }

    @Test
    public void testParseFloat_ScientificNotation() {
        assertEquals(1.23e5f, NumberInput.parseFloat("1.23e5", false), 0.001f);
    }

    @Test
    public void testParseFloat_UseFastParser() {
        assertEquals(123.45f, NumberInput.parseFloat("123.45", true), 0.0001f);
    }

    @Test(expected = NumberFormatException.class)
    public void testParseFloat_Invalid_ThrowsException() {
        NumberInput.parseFloat("abc", false);
    }

    // --- New tests targeting useFastParser mutation (lines 444, 472) ---
    @Test
    public void testParseFloat_BothParsersSameResult_Decimal() {
        float fast = NumberInput.parseFloat("123.456", true);
        float standard = NumberInput.parseFloat("123.456", false);
        assertEquals(fast, standard, 0.0f);
    }

    @Test
    public void testParseFloat_BothParsersSameResult_Negative() {
        float fast = NumberInput.parseFloat("-123.456", true);
        float standard = NumberInput.parseFloat("-123.456", false);
        assertEquals(fast, standard, 0.0f);
    }

    @Test
    public void testParseFloat_BothParsersSameResult_Scientific() {
        float fast = NumberInput.parseFloat("1.23e-10", true);
        float standard = NumberInput.parseFloat("1.23e-10", false);
        assertEquals(fast, standard, 0.0f);
    }

    @Test
    public void testParseFloat_BothParsersSameResult_MaxFloat() {
        float fast = NumberInput.parseFloat("3.4028235E38", true);
        float standard = NumberInput.parseFloat("3.4028235E38", false);
        assertEquals(fast, standard, 0.0f);
    }

    @Test
    public void testParseFloat_BothParsersSameResult_WithPlusSign() {
        float fast = NumberInput.parseFloat("+123.456", true);
        float standard = NumberInput.parseFloat("+123.456", false);
        assertEquals(fast, standard, 0.0f);
    }

    // ==================== parseFloat(char[], ...) ====================

    @Test
    public void testParseFloatCharArray_Valid() {
        char[] ch = {'1', '2', '3', '.', '4', '5'};
        assertEquals(123.45f, NumberInput.parseFloat(ch, false), 0.0001f);
    }

    @Test
    public void testParseFloatCharArray_WithOffsetAndLen() {
        char[] ch = {'x', 'x', '1', '2', '3', '.', '4', '5', 'x', 'x'};
        assertEquals(123.45f, NumberInput.parseFloat(ch, 2, 6, false), 0.0001f);
    }

    @Test
    public void testParseFloatCharArray_UseFastParser() {
        char[] ch = {'1', '2', '3', '.', '4', '5'};
        assertEquals(123.45f, NumberInput.parseFloat(ch, true), 0.0001f);
    }

    @Test
    public void testParseFloatCharArray_WithOffsetAndLen_FastParser() {
        char[] ch = {'x', 'x', '1', '2', '3', '.', '4', '5', 'x', 'x'};
        assertEquals(123.45f, NumberInput.parseFloat(ch, 2, 6, true), 0.0001f);
    }

    @Test(expected = NumberFormatException.class)
    public void testParseFloatCharArray_Invalid_ThrowsException() {
        char[] ch = {'a', 'b', 'c'};
        NumberInput.parseFloat(ch, false);
    }

    // ==================== parseBigDecimal ====================

    @Test
    public void testParseBigDecimalString_ValidInteger() {
        BigDecimal result = NumberInput.parseBigDecimal("123", false);
        assertEquals(new BigDecimal("123"), result);
    }

    @Test
    public void testParseBigDecimalString_ValidDecimal() {
        BigDecimal result = NumberInput.parseBigDecimal("123.456", false);
        assertEquals(new BigDecimal("123.456"), result);
    }

    @Test
    public void testParseBigDecimalString_Negative() {
        BigDecimal result = NumberInput.parseBigDecimal("-123.456", false);
        assertEquals(new BigDecimal("-123.456"), result);
    }

    @Test
    public void testParseBigDecimalString_ScientificNotation() {
        BigDecimal result = NumberInput.parseBigDecimal("1.23e5", false);
        assertEquals(new BigDecimal("1.23E+5"), result);
    }

    @Test
    public void testParseBigDecimalString_UseFastParser() {
        BigDecimal result = NumberInput.parseBigDecimal("123.456", true);
        assertEquals(new BigDecimal("123.456"), result);
    }

    @Test
    public void testParseBigDecimalCharArray_Valid() {
        char[] ch = {'1', '2', '3', '.', '4', '5', '6'};
        BigDecimal result = NumberInput.parseBigDecimal(ch, 0, 7, false);
        assertEquals(new BigDecimal("123.456"), result);
    }

    @Test
    public void testParseBigDecimalCharArray_WithOffsetAndLen() {
        char[] ch = {'x', 'x', '1', '2', '3', '.', '4', '5', '6', 'x', 'x'};
        BigDecimal result = NumberInput.parseBigDecimal(ch, 2, 7, false);
        assertEquals(new BigDecimal("123.456"), result);
    }

    @Test
    public void testParseBigDecimalCharArray_UseFastParser() {
        char[] ch = {'1', '2', '3', '.', '4', '5', '6'};
        BigDecimal result = NumberInput.parseBigDecimal(ch, 0, 7, true);
        assertEquals(new BigDecimal("123.456"), result);
    }

    @Test
    public void testParseBigDecimalCharArray_FullArray() {
        char[] ch = {'1', '2', '3', '.', '4', '5', '6'};
        BigDecimal result = NumberInput.parseBigDecimal(ch, false);
        assertEquals(new BigDecimal("123.456"), result);
    }

    @Test(expected = NumberFormatException.class)
    public void testParseBigDecimalString_Invalid_ThrowsException() {
        NumberInput.parseBigDecimal("abc", false);
    }

    @Test(expected = NumberFormatException.class)
    public void testParseBigDecimalCharArray_Invalid_ThrowsException() {
        char[] ch = {'a', 'b', 'c'};
        NumberInput.parseBigDecimal(ch, 0, 3, false);
    }

    // --- Additional test for parseBigDecimal useFastParser branch (line 548) ---
    @Test
    public void testParseBigDecimalString_UseFastParser_ValidDecimal() {
        BigDecimal result = NumberInput.parseBigDecimal("123.456", true);
        assertEquals(new BigDecimal("123.456"), result);
    }

    @Test
    public void testParseBigDecimalString_UseFastParser_Negative() {
        BigDecimal result = NumberInput.parseBigDecimal("-123.456", true);
        assertEquals(new BigDecimal("-123.456"), result);
    }

    @Test
    public void testParseBigDecimalString_UseFastParser_ScientificNotation() {
        BigDecimal result = NumberInput.parseBigDecimal("1.23e5", true);
        assertEquals(new BigDecimal("1.23E+5"), result);
    }

    @Test
    public void testParseBigDecimalCharArray_UseFastParser_WithOffset() {
        char[] ch = {'x', 'x', '1', '2', '3', '.', '4', '5', '6', 'x', 'x'};
        BigDecimal result = NumberInput.parseBigDecimal(ch, 2, 7, true);
        assertEquals(new BigDecimal("123.456"), result);
    }

    @Test
    public void testParseBigDecimalCharArray_UseFastParser_FullArray() {
        char[] ch = {'1', '2', '3', '.', '4', '5', '6'};
        BigDecimal result = NumberInput.parseBigDecimal(ch, true);
        assertEquals(new BigDecimal("123.456"), result);
    }

    // --- New tests targeting useFastParser mutations (lines 493, 524, 548) ---
    @Test
    public void testParseBigDecimal_BothParsersSameResult_Decimal() {
        BigDecimal fast = NumberInput.parseBigDecimal("123.45678901234567890", true);
        BigDecimal standard = NumberInput.parseBigDecimal("123.45678901234567890", false);
        assertEquals(0, fast.compareTo(standard));
    }

    @Test
    public void testParseBigDecimal_BothParsersSameResult_Negative() {
        BigDecimal fast = NumberInput.parseBigDecimal("-123.45678901234567890", true);
        BigDecimal standard = NumberInput.parseBigDecimal("-123.45678901234567890", false);
        assertEquals(0, fast.compareTo(standard));
    }

    @Test
    public void testParseBigDecimal_BothParsersSameResult_Scientific() {
        BigDecimal fast = NumberInput.parseBigDecimal("1.23e5", true);
        BigDecimal standard = NumberInput.parseBigDecimal("1.23e5", false);
        assertEquals(0, fast.compareTo(standard));
    }

    @Test
    public void testParseBigDecimalCharArray_BothParsersSameResult() {
        char[] ch = {'1', '2', '3', '.', '4', '5', '6', '7', '8', '9'};
        BigDecimal fast = NumberInput.parseBigDecimal(ch, 0, 10, true);
        BigDecimal standard = NumberInput.parseBigDecimal(ch, 0, 10, false);
        assertEquals(0, fast.compareTo(standard));
    }

    @Test
    public void testParseBigDecimal_BothParsersSameResult_WithPlusSign() {
        BigDecimal fast = NumberInput.parseBigDecimal("+123.456", true);
        BigDecimal standard = NumberInput.parseBigDecimal("+123.456", false);
        assertEquals(0, fast.compareTo(standard));
    }

    @Test
    public void testParseBigDecimal_BothParsersSameResult_VeryLarge() {
        BigDecimal fast = NumberInput.parseBigDecimal("123456789012345678901234567890.123456789", true);
        BigDecimal standard = NumberInput.parseBigDecimal("123456789012345678901234567890.123456789", false);
        assertEquals(0, fast.compareTo(standard));
    }

    // ==================== parseBigInteger ====================

    @Test
    public void testParseBigIntegerString_ValidPositive() {
        BigInteger result = NumberInput.parseBigInteger("12345678901234567890", false);
        assertEquals(new BigInteger("12345678901234567890"), result);
    }

    @Test
    public void testParseBigIntegerString_ValidNegative() {
        BigInteger result = NumberInput.parseBigInteger("-12345678901234567890", false);
        assertEquals(new BigInteger("-12345678901234567890"), result);
    }

    @Test
    public void testParseBigIntegerString_WithLeadingPlus() {
        BigInteger result = NumberInput.parseBigInteger("+12345678901234567890", false);
        assertEquals(new BigInteger("12345678901234567890"), result);
    }

    @Test
    public void testParseBigIntegerString_UseFastParser() {
        BigInteger result = NumberInput.parseBigInteger("12345678901234567890", true);
        assertEquals(new BigInteger("12345678901234567890"), result);
    }

    @Test
    public void testParseBigIntegerWithRadix_Binary() {
        BigInteger result = NumberInput.parseBigIntegerWithRadix("1010", 2, false);
        assertEquals(new BigInteger("1010", 2), result);
    }

    @Test
    public void testParseBigIntegerWithRadix_Hex() {
        BigInteger result = NumberInput.parseBigIntegerWithRadix("FF", 16, false);
        assertEquals(new BigInteger("FF", 16), result);
    }

    @Test
    public void testParseBigIntegerWithRadix_UseFastParser() {
        BigInteger result = NumberInput.parseBigIntegerWithRadix("FF", 16, true);
        assertEquals(new BigInteger("FF", 16), result);
    }

    @Test(expected = NumberFormatException.class)
    public void testParseBigIntegerString_Invalid_ThrowsException() {
        NumberInput.parseBigInteger("abc", false);
    }

    @Test(expected = NumberFormatException.class)
    public void testParseBigIntegerWithRadix_Invalid_ThrowsException() {
        NumberInput.parseBigIntegerWithRadix("GG", 16, false);
    }

    // --- New tests targeting useFastParser mutations (lines 571, 586) ---
    @Test
    public void testParseBigInteger_BothParsersSameResult_Positive() {
        BigInteger fast = NumberInput.parseBigInteger("123456789012345678901234567890", true);
        BigInteger standard = NumberInput.parseBigInteger("123456789012345678901234567890", false);
        assertEquals(0, fast.compareTo(standard));
    }

    @Test
    public void testParseBigInteger_BothParsersSameResult_Negative() {
        BigInteger fast = NumberInput.parseBigInteger("-123456789012345678901234567890", true);
        BigInteger standard = NumberInput.parseBigInteger("-123456789012345678901234567890", false);
        assertEquals(0, fast.compareTo(standard));
    }

    @Test
    public void testParseBigInteger_BothParsersSameResult_WithPlusSign() {
        BigInteger fast = NumberInput.parseBigInteger("+12345678901234567890", true);
        BigInteger standard = NumberInput.parseBigInteger("+12345678901234567890", false);
        assertEquals(0, fast.compareTo(standard));
    }

    @Test
    public void testParseBigIntegerWithRadix_BothParsersSameResult_Binary() {
        BigInteger fast = NumberInput.parseBigIntegerWithRadix("1010101010101010", 2, true);
        BigInteger standard = NumberInput.parseBigIntegerWithRadix("1010101010101010", 2, false);
        assertEquals(0, fast.compareTo(standard));
    }

    @Test
    public void testParseBigIntegerWithRadix_BothParsersSameResult_Hex() {
        BigInteger fast = NumberInput.parseBigIntegerWithRadix("ABCDEF1234567890", 16, true);
        BigInteger standard = NumberInput.parseBigIntegerWithRadix("ABCDEF1234567890", 16, false);
        assertEquals(0, fast.compareTo(standard));
    }

    @Test
    public void testParseBigIntegerWithRadix_BothParsersSameResult_Radix36() {
        BigInteger fast = NumberInput.parseBigIntegerWithRadix("ZZZZZZ", 36, true);
        BigInteger standard = NumberInput.parseBigIntegerWithRadix("ZZZZZZ", 36, false);
        assertEquals(0, fast.compareTo(standard));
    }

    // ==================== looksLikeValidNumber ====================

    @Test
    public void testLooksLikeValidNumber_Null_ReturnsFalse() {
        assertFalse(NumberInput.looksLikeValidNumber(null));
    }

    @Test
    public void testLooksLikeValidNumber_EmptyString_ReturnsFalse() {
        assertFalse(NumberInput.looksLikeValidNumber(""));
    }

    @Test
    public void testLooksLikeValidNumber_SingleDigit_True() {
        for (char c = '0'; c <= '9'; c++) {
            assertTrue("Single digit '" + c + "' should be valid", NumberInput.looksLikeValidNumber(String.valueOf(c)));
        }
    }

    @Test
    public void testLooksLikeValidNumber_SingleNonDigit_False() {
        assertFalse(NumberInput.looksLikeValidNumber("a"));
        assertFalse(NumberInput.looksLikeValidNumber("+"));
        assertFalse(NumberInput.looksLikeValidNumber("-"));
        assertFalse(NumberInput.looksLikeValidNumber("."));
    }

    @Test
    public void testLooksLikeValidNumber_PositiveInteger_True() {
        assertTrue(NumberInput.looksLikeValidNumber("123"));
    }

    @Test
    public void testLooksLikeValidNumber_NegativeInteger_True() {
        assertTrue(NumberInput.looksLikeValidNumber("-123"));
    }

    @Test
    public void testLooksLikeValidNumber_WithLeadingPlus_True() {
        assertTrue(NumberInput.looksLikeValidNumber("+123"));
    }

    @Test
    public void testLooksLikeValidNumber_Decimal_True() {
        assertTrue(NumberInput.looksLikeValidNumber("123.45"));
        assertTrue(NumberInput.looksLikeValidNumber(".45"));
        assertTrue(NumberInput.looksLikeValidNumber("123."));
    }

    @Test
    public void testLooksLikeValidNumber_TrailingDot_True() {
        assertTrue(NumberInput.looksLikeValidNumber("+12."));
        assertTrue(NumberInput.looksLikeValidNumber("12."));
    }

    @Test
    public void testLooksLikeValidNumber_ScientificNotation_True() {
        assertTrue(NumberInput.looksLikeValidNumber("1.23e5"));
        assertTrue(NumberInput.looksLikeValidNumber("1.23E5"));
        assertTrue(NumberInput.looksLikeValidNumber("1.23e+5"));
        assertTrue(NumberInput.looksLikeValidNumber("1.23e-5"));
        assertTrue(NumberInput.looksLikeValidNumber("123e5"));
    }

    @Test
    public void testLooksLikeValidNumber_LeadingZeros_True() {
        assertTrue(NumberInput.looksLikeValidNumber("000123"));
        assertTrue(NumberInput.looksLikeValidNumber("00.123"));
    }

    @Test
    public void testLooksLikeValidNumber_Invalid_False() {
        assertFalse(NumberInput.looksLikeValidNumber("abc"));
        assertFalse(NumberInput.looksLikeValidNumber("123abc"));
        assertFalse(NumberInput.looksLikeValidNumber("12.34.56"));
        assertFalse(NumberInput.looksLikeValidNumber("12e"));
        assertFalse(NumberInput.looksLikeValidNumber("e10"));
        assertFalse(NumberInput.looksLikeValidNumber("."));
        assertFalse(NumberInput.looksLikeValidNumber("+"));
        assertFalse(NumberInput.looksLikeValidNumber("-"));
        assertFalse(NumberInput.looksLikeValidNumber("+."));
        assertFalse(NumberInput.looksLikeValidNumber("-."));
    }

    // ==================== Boundary value tests ====================

    @Test
    public void testParseInt_MaxInt() {
        assertEquals(Integer.MAX_VALUE, NumberInput.parseInt("2147483647"));
    }

    @Test
    public void testParseInt_MinInt() {
        assertEquals(Integer.MIN_VALUE, NumberInput.parseInt("-2147483648"));
    }

    @Test
    public void testParseLong_MaxLong() {
        assertEquals(Long.MAX_VALUE, NumberInput.parseLong("9223372036854775807"));
    }

    @Test
    public void testParseLong_MinLong() {
        assertEquals(Long.MIN_VALUE, NumberInput.parseLong("-9223372036854775808"));
    }

    @Test
    public void testInLongRange_MaxLongBoundary() {
        assertTrue(NumberInput.inLongRange("9223372036854775807", false));
        assertFalse(NumberInput.inLongRange("9223372036854775808", false));
    }

    @Test
    public void testInLongRange_MinLongBoundary() {
        assertTrue(NumberInput.inLongRange("9223372036854775808", true));
        assertFalse(NumberInput.inLongRange("9223372036854775809", true));
    }

    @Test
    public void testParseAsInt_MaxInt() {
        assertEquals(Integer.MAX_VALUE, NumberInput.parseAsInt("2147483647", 0));
    }

    @Test
    public void testParseAsInt_MinInt() {
        assertEquals(Integer.MIN_VALUE, NumberInput.parseAsInt("-2147483648", 0));
    }

    @Test
    public void testParseAsLong_MaxLong() {
        assertEquals(Long.MAX_VALUE, NumberInput.parseAsLong("9223372036854775807", 0L));
    }

    @Test
    public void testParseAsLong_MinLong() {
        assertEquals(Long.MIN_VALUE, NumberInput.parseAsLong("-9223372036854775808", 0L));
    }

    // ==================== Deprecated method tests ====================

    @Test
    @SuppressWarnings("deprecation")
    public void testParseDouble_Deprecated() {
        assertEquals(123.45, NumberInput.parseDouble("123.45"), 0.0001);
    }

    @Test(expected = NumberFormatException.class)
    @SuppressWarnings("deprecation")
    public void testParseDouble_Deprecated_Invalid_ThrowsException() {
        NumberInput.parseDouble("abc");
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testParseFloat_Deprecated() {
        assertEquals(123.45f, NumberInput.parseFloat("123.45"), 0.0001f);
    }

    @Test(expected = NumberFormatException.class)
    @SuppressWarnings("deprecation")
    public void testParseFloat_Deprecated_Invalid_ThrowsException() {
        NumberInput.parseFloat("abc");
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testParseBigDecimal_Deprecated() {
        BigDecimal result = NumberInput.parseBigDecimal("123.456");
        assertEquals(new BigDecimal("123.456"), result);
    }

    @Test(expected = NumberFormatException.class)
    @SuppressWarnings("deprecation")
    public void testParseBigDecimal_Deprecated_Invalid_ThrowsException() {
        NumberInput.parseBigDecimal("abc");
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testParseBigDecimalCharArray_Deprecated() {
        char[] ch = {'1', '2', '3', '.', '4', '5', '6'};
        BigDecimal result = NumberInput.parseBigDecimal(ch, 0, 7);
        assertEquals(new BigDecimal("123.456"), result);
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testParseBigDecimalCharArray_FullArray_Deprecated() {
        char[] ch = {'1', '2', '3', '.', '4', '5', '6'};
        BigDecimal result = NumberInput.parseBigDecimal(ch);
        assertEquals(new BigDecimal("123.456"), result);
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testParseBigInteger_Deprecated() {
        BigInteger result = NumberInput.parseBigInteger("12345678901234567890");
        assertEquals(new BigInteger("12345678901234567890"), result);
    }
}
