package tools.jackson.core.io;

import org.junit.Test;
import java.math.BigInteger;
import static org.junit.Assert.*;

public class BigIntegerParserTest {

    private static String repeat(String s, int count) {
        StringBuilder sb = new StringBuilder(s.length() * count);
        for (int i = 0; i < count; i++) {
            sb.append(s);
        }
        return sb.toString();
    }

    @Test
    public void testParseWithFastParser_ValidPositiveInteger() {
        String input = "123456789012345678901234567890";
        BigInteger result = BigIntegerParser.parseWithFastParser(input);
        assertEquals(new BigInteger(input), result);
    }

    @Test
    public void testParseWithFastParser_ValidNegativeInteger() {
        String input = "-987654321098765432109876543210";
        BigInteger result = BigIntegerParser.parseWithFastParser(input);
        assertEquals(new BigInteger(input), result);
    }

    @Test
    public void testParseWithFastParser_Zero() {
        BigInteger result = BigIntegerParser.parseWithFastParser("0");
        assertEquals(BigInteger.ZERO, result);
    }

    @Test
    public void testParseWithFastParser_LargePositiveInteger() {
        String input = repeat("1", 1000);
        BigInteger result = BigIntegerParser.parseWithFastParser(input);
        assertEquals(new BigInteger(input), result);
    }

    @Test
    public void testParseWithFastParser_LargeNegativeInteger() {
        String input = "-" + repeat("9", 500);
        BigInteger result = BigIntegerParser.parseWithFastParser(input);
        assertEquals(new BigInteger(input), result);
    }

    @Test
    public void testParseWithFastParser_WithRadix_Binary() {
        String input = "101010";
        int radix = 2;
        BigInteger result = BigIntegerParser.parseWithFastParser(input, radix);
        assertEquals(new BigInteger(input, radix), result);
    }

    @Test
    public void testParseWithFastParser_WithRadix_Octal() {
        String input = "755";
        int radix = 8;
        BigInteger result = BigIntegerParser.parseWithFastParser(input, radix);
        assertEquals(new BigInteger(input, radix), result);
    }

    @Test
    public void testParseWithFastParser_WithRadix_Hexadecimal() {
        String input = "ABCDEF";
        int radix = 16;
        BigInteger result = BigIntegerParser.parseWithFastParser(input, radix);
        assertEquals(new BigInteger(input, radix), result);
    }

    @Test
    public void testParseWithFastParser_WithRadix_HexadecimalLowercase() {
        String input = "abcdef";
        int radix = 16;
        BigInteger result = BigIntegerParser.parseWithFastParser(input, radix);
        assertEquals(new BigInteger(input, radix), result);
    }

    @Test
    public void testParseWithFastParser_WithRadix_Base36() {
        String input = "ZZZZZZ";
        int radix = 36;
        BigInteger result = BigIntegerParser.parseWithFastParser(input, radix);
        assertEquals(new BigInteger(input, radix), result);
    }

    @Test
    public void testParseWithFastParser_WithRadix_NegativeHex() {
        String input = "-FF";
        int radix = 16;
        BigInteger result = BigIntegerParser.parseWithFastParser(input, radix);
        assertEquals(new BigInteger(input, radix), result);
    }

    @Test
    public void testParseWithFastParser_InvalidInput_ThrowsNumberFormatException() {
        String input = "not_a_number";
        try {
            BigIntegerParser.parseWithFastParser(input);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            assertTrue(e.getMessage().contains("not_a_number"));
            assertTrue(e.getMessage().contains("cannot be represented as `java.math.BigInteger`"));
        }
    }

    @Test
    public void testParseWithFastParser_InvalidInputWithRadix_ThrowsNumberFormatException() {
        String input = "not_a_number";
        int radix = 16;
        try {
            BigIntegerParser.parseWithFastParser(input, radix);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            assertTrue(e.getMessage().contains("not_a_number"));
            assertTrue(e.getMessage().contains("cannot be represented as `java.math.BigInteger` with radix 16"));
        }
    }

    @Test
    public void testParseWithFastParser_EmptyString_ThrowsException() {
        String input = "";
        try {
            BigIntegerParser.parseWithFastParser(input);
            fail("Expected exception for empty string");
        } catch (StringIndexOutOfBoundsException e) {
            // Expected: underlying parser throws StringIndexOutOfBoundsException for empty string
        } catch (NumberFormatException e) {
            // Also acceptable if behavior changes
            assertTrue(e.getMessage().contains("cannot be represented as `java.math.BigInteger`"));
        }
    }

    @Test
    public void testParseWithFastParser_EmptyStringWithRadix_ThrowsException() {
        String input = "";
        int radix = 10;
        try {
            BigIntegerParser.parseWithFastParser(input, radix);
            fail("Expected exception for empty string with radix");
        } catch (StringIndexOutOfBoundsException e) {
            // Expected: underlying parser throws StringIndexOutOfBoundsException for empty string
        } catch (NumberFormatException e) {
            // Also acceptable if behavior changes
            assertTrue(e.getMessage().contains("cannot be represented as `java.math.BigInteger` with radix 10"));
        }
    }

    @Test
    public void testParseWithFastParser_InputWithSpaces_ThrowsNumberFormatException() {
        String input = " 123 ";
        try {
            BigIntegerParser.parseWithFastParser(input);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            assertTrue(e.getMessage().contains(" 123 "));
        }
    }

    @Test
    public void testParseWithFastParser_VeryLongInput_TruncatedInErrorMessage() {
        int maxChars = BigDecimalParser.MAX_CHARS_TO_REPORT;
        String longInput = repeat("1", maxChars + 100) + "invalid";
        try {
            BigIntegerParser.parseWithFastParser(longInput);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            String message = e.getMessage();
            assertTrue("Message should contain truncation marker", message.contains("[truncated]"));
            assertTrue("Message should contain first " + maxChars + " characters", message.contains(repeat("1", maxChars)));
        }
    }

    @Test
    public void testParseWithFastParser_VeryLongInputWithRadix_TruncatedInErrorMessage() {
        int maxChars = BigDecimalParser.MAX_CHARS_TO_REPORT;
        String longInput = repeat("1", maxChars + 100) + "invalid";
        int radix = 16;
        try {
            BigIntegerParser.parseWithFastParser(longInput, radix);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            String message = e.getMessage();
            assertTrue("Message should contain truncation marker", message.contains("[truncated]"));
            assertTrue("Message should contain radix information", message.contains("radix 16"));
        }
    }

    @Test
    public void testParseWithFastParser_InputAtMaxCharsToReportBoundary() {
        int maxChars = BigDecimalParser.MAX_CHARS_TO_REPORT;
        String input = repeat("1", maxChars) + "x";
        try {
            BigIntegerParser.parseWithFastParser(input);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            String message = e.getMessage();
            assertTrue("Message should contain first " + maxChars + " characters", message.contains(repeat("1", maxChars)));
        }
    }

    @Test
    public void testParseWithFastParser_InputJustOverMaxCharsToReport() {
        int maxChars = BigDecimalParser.MAX_CHARS_TO_REPORT;
        String input = repeat("1", maxChars + 1) + "x";
        try {
            BigIntegerParser.parseWithFastParser(input);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            String message = e.getMessage();
            assertTrue("Message should contain truncation marker", message.contains("[truncated]"));
            assertTrue("Message should contain first " + maxChars + " characters", message.contains(repeat("1", maxChars)));
        }
    }

    @Test
    public void testParseWithFastParser_WithRadix_InvalidRadix_ThrowsException() {
        String input = "123";
        int invalidRadix = 37;
        try {
            BigIntegerParser.parseWithFastParser(input, invalidRadix);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            assertTrue(e.getMessage().contains("radix 37"));
        }
    }

    @Test
    public void testParseWithFastParser_WithRadix_Radix2() {
        String input = "11111111111111111111111111111111111111111111111111";
        int radix = 2;
        BigInteger result = BigIntegerParser.parseWithFastParser(input, radix);
        assertEquals(new BigInteger(input, radix), result);
    }

    @Test
    public void testParseWithFastParser_WithRadix_Radix10_LargeNumber() {
        String input = repeat("9", 1000);
        int radix = 10;
        BigInteger result = BigIntegerParser.parseWithFastParser(input, radix);
        assertEquals(new BigInteger(input, radix), result);
    }

    @Test
    public void testParseWithFastParser_NullInput_ThrowsNullPointerException() {
        try {
            BigIntegerParser.parseWithFastParser((String) null);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // Expected
        }
    }

    @Test
    public void testParseWithFastParser_NullInputWithRadix_ThrowsNullPointerException() {
        try {
            BigIntegerParser.parseWithFastParser((String) null, 10);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // Expected
        }
    }

    @Test
    public void testParseWithFastParser_PlusSignPrefix() {
        String input = "+12345678901234567890";
        BigInteger result = BigIntegerParser.parseWithFastParser(input);
        assertEquals(new BigInteger(input), result);
    }

    @Test
    public void testParseWithFastParser_WithRadix_PlusSignPrefix() {
        String input = "+ABC";
        int radix = 16;
        BigInteger result = BigIntegerParser.parseWithFastParser(input, radix);
        assertEquals(new BigInteger(input, radix), result);
    }

    // New tests to kill surviving boundary mutations at line 21 and 32
    @Test
    public void testParseWithFastParser_InvalidInputAtExactMaxCharsBoundary_NoTruncation() {
        int maxChars = BigDecimalParser.MAX_CHARS_TO_REPORT;
        String input = repeat("1", maxChars - 1) + "x"; // length exactly MAX_CHARS_TO_REPORT
        try {
            BigIntegerParser.parseWithFastParser(input);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            String message = e.getMessage();
            assertFalse("Message should NOT contain truncation marker at exact boundary", message.contains("[truncated]"));
            assertTrue("Message should contain full input at exact boundary", message.contains(input));
        }
    }

    @Test
    public void testParseWithFastParser_ValidInputAtExactMaxCharsBoundary_ParsesCorrectly() {
        int maxChars = BigDecimalParser.MAX_CHARS_TO_REPORT;
        String input = repeat("9", maxChars); // valid number at exact boundary
        BigInteger result = BigIntegerParser.parseWithFastParser(input);
        assertEquals(new BigInteger(input), result);
    }

    @Test
    public void testParseWithFastParser_WithRadix_InvalidInputAtExactMaxCharsBoundary_NoTruncation() {
        int maxChars = BigDecimalParser.MAX_CHARS_TO_REPORT;
        String input = repeat("1", maxChars - 1) + "x"; // length exactly MAX_CHARS_TO_REPORT
        int radix = 16;
        try {
            BigIntegerParser.parseWithFastParser(input, radix);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            String message = e.getMessage();
            assertFalse("Message should NOT contain truncation marker at exact boundary", message.contains("[truncated]"));
            assertTrue("Message should contain full input at exact boundary", message.contains(input));
            assertTrue("Message should contain radix information", message.contains("radix 16"));
        }
    }

    @Test
    public void testParseWithFastParser_WithRadix_ValidInputAtExactMaxCharsBoundary_ParsesCorrectly() {
        int maxChars = BigDecimalParser.MAX_CHARS_TO_REPORT;
        String input = repeat("F", maxChars); // valid hex number at exact boundary
        int radix = 16;
        BigInteger result = BigIntegerParser.parseWithFastParser(input, radix);
        assertEquals(new BigInteger(input, radix), result);
    }

    @Test
    public void testParseWithFastParser_InvalidInputOneCharOverMaxCharsBoundary_Truncated() {
        int maxChars = BigDecimalParser.MAX_CHARS_TO_REPORT;
        String input = repeat("1", maxChars) + "x"; // length = MAX_CHARS_TO_REPORT + 1
        try {
            BigIntegerParser.parseWithFastParser(input);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            String message = e.getMessage();
            assertTrue("Message SHOULD contain truncation marker when over boundary", message.contains("[truncated]"));
            assertTrue("Message should contain first " + maxChars + " characters", message.contains(repeat("1", maxChars)));
            assertFalse("Message should NOT contain the full input when over boundary", message.contains(input));
        }
    }

    @Test
    public void testParseWithFastParser_WithRadix_InvalidInputOneCharOverMaxCharsBoundary_Truncated() {
        int maxChars = BigDecimalParser.MAX_CHARS_TO_REPORT;
        String input = repeat("1", maxChars) + "x"; // length = MAX_CHARS_TO_REPORT + 1
        int radix = 16;
        try {
            BigIntegerParser.parseWithFastParser(input, radix);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            String message = e.getMessage();
            assertTrue("Message SHOULD contain truncation marker when over boundary", message.contains("[truncated]"));
            assertTrue("Message should contain first " + maxChars + " characters", message.contains(repeat("1", maxChars)));
            assertTrue("Message should contain radix information", message.contains("radix 16"));
            assertFalse("Message should NOT contain the full input when over boundary", message.contains(input));
        }
    }
}
