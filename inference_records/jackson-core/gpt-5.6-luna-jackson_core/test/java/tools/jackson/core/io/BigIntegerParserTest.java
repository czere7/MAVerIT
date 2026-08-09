package tools.jackson.core.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;

import org.junit.Test;

public class BigIntegerParserTest {

    @Test
    public void parsesDecimalValue() {
        String value = "123456789012345678901234567890";

        assertEquals(new BigInteger(value), BigIntegerParser.parseWithFastParser(value));
    }

    @Test
    public void parsesNegativeAndPositiveValues() {
        assertEquals(new BigInteger("-987654321"), BigIntegerParser.parseWithFastParser("-987654321"));
        assertEquals(new BigInteger("+987654321"), BigIntegerParser.parseWithFastParser("+987654321"));
    }

    @Test
    public void parsesVeryLargeDecimalValue() {
        String value = repeated('9', 1000);

        assertEquals(new BigInteger(value), BigIntegerParser.parseWithFastParser(value));
    }

    @Test
    public void parsesValueUsingSpecifiedRadix() {
        assertEquals(new BigInteger("deadbeef", 16),
                BigIntegerParser.parseWithFastParser("deadbeef", 16));
        assertEquals(new BigInteger("-101010", 2),
                BigIntegerParser.parseWithFastParser("-101010", 2));
    }

    @Test
    public void rejectsInvalidDecimalValueWithDescriptiveMessage() {
        String value = "12x34";

        try {
            BigIntegerParser.parseWithFastParser(value);
        } catch (NumberFormatException e) {
            assertTrue(e.getMessage().startsWith(
                    "Value \"" + value
                            + "\" cannot be represented as `java.math.BigInteger`, reason: "));
            return;
        }

        throw new AssertionError("Expected NumberFormatException");
    }

    @Test
    public void rejectsInvalidRadixValueWithDescriptiveMessage() {
        String value = "123";

        try {
            BigIntegerParser.parseWithFastParser(value, 1);
        } catch (NumberFormatException e) {
            assertTrue(e.getMessage().startsWith(
                    "Value \"" + value
                            + "\" cannot be represented as `java.math.BigInteger` with radix 1, reason: "));
            return;
        }

        throw new AssertionError("Expected NumberFormatException");
    }

    @Test
    public void truncatesLongInvalidValueInErrorMessage() {
        String prefix = repeated('7', BigDecimalParser.MAX_CHARS_TO_REPORT);
        String value = prefix + "x";

        try {
            BigIntegerParser.parseWithFastParser(value);
        } catch (NumberFormatException e) {
            assertTrue(e.getMessage().contains(
                    "Value \"" + prefix + " [truncated]\" cannot be represented as `java.math.BigInteger`"));
            return;
        }

        throw new AssertionError("Expected NumberFormatException");
    }

    @Test
    public void truncatesLongInvalidValueInRadixErrorMessage() {
        String prefix = repeated('7', BigDecimalParser.MAX_CHARS_TO_REPORT);
        String value = prefix + "x";
        int radix = 1;

        try {
            BigIntegerParser.parseWithFastParser(value, radix);
        } catch (NumberFormatException e) {
            assertTrue(e.getMessage().contains(
                    "Value \"" + prefix + " [truncated]\" cannot be represented as `java.math.BigInteger`"
                            + " with radix " + radix + ", reason: "));
            return;
        }

        throw new AssertionError("Expected NumberFormatException");
    }

    @Test
    public void doesNotTruncateInvalidDecimalValueAtReportingLimit() {
        String value = repeated('7', BigDecimalParser.MAX_CHARS_TO_REPORT - 1) + "x";

        try {
            BigIntegerParser.parseWithFastParser(value);
        } catch (NumberFormatException e) {
            assertTrue(e.getMessage().contains(
                    "Value \"" + value
                            + "\" cannot be represented as `java.math.BigInteger`, reason: "));
            assertTrue(!e.getMessage().contains("[truncated]"));
            return;
        }

        throw new AssertionError("Expected NumberFormatException");
    }

    @Test
    public void doesNotTruncateInvalidRadixValueAtReportingLimit() {
        String value = repeated('7', BigDecimalParser.MAX_CHARS_TO_REPORT - 1) + "x";
        int radix = 1;

        try {
            BigIntegerParser.parseWithFastParser(value, radix);
        } catch (NumberFormatException e) {
            assertTrue(e.getMessage().contains(
                    "Value \"" + value
                            + "\" cannot be represented as `java.math.BigInteger` with radix "
                            + radix + ", reason: "));
            assertTrue(!e.getMessage().contains("[truncated]"));
            return;
        }

        throw new AssertionError("Expected NumberFormatException");
    }

    private static String repeated(char character, int count) {
        StringBuilder result = new StringBuilder(count);
        for (int i = 0; i < count; i++) {
            result.append(character);
        }
        return result.toString();
    }
}
