package tools.jackson.core.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;

import org.junit.Test;

public class BigIntegerParserTest {

    @Test
    public void parseWithFastParser_ValidPositiveNumber() {
        String input = "12345678901234567890";
        BigInteger result = BigIntegerParser.parseWithFastParser(input);
        assertNotNull(result);
        assertEquals(new BigInteger(input), result);
    }

    @Test
    public void parseWithFastParser_ValidNegativeNumber() {
        String input = "-98765432109876543210";
        BigInteger result = BigIntegerParser.parseWithFastParser(input);
        assertNotNull(result);
        assertEquals(new BigInteger(input), result);
    }

    @Test
    public void parseWithFastParser_ValidZero() {
        String input = "0";
        BigInteger result = BigIntegerParser.parseWithFastParser(input);
        assertNotNull(result);
        assertEquals(BigInteger.ZERO, result);
    }

    @Test
    public void parseWithFastParser_ValidLargeNumber() {
        String input = "123456789012345678901234567890123456789012345678901234567890";
        BigInteger result = BigIntegerParser.parseWithFastParser(input);
        assertNotNull(result);
        assertEquals(new BigInteger(input), result);
    }

    @Test
    public void parseWithFastParser_ValidWithRadix_Decimal() {
        String input = "255";
        BigInteger result = BigIntegerParser.parseWithFastParser(input, 10);
        assertNotNull(result);
        assertEquals(new BigInteger(input), result);
    }

    @Test
    public void parseWithFastParser_ValidWithRadix_Hex() {
        String input = "FF";
        BigInteger result = BigIntegerParser.parseWithFastParser(input, 16);
        assertNotNull(result);
        assertEquals(new BigInteger(input, 16), result);
    }

    @Test
    public void parseWithFastParser_ValidWithRadix_Binary() {
        String input = "1010";
        BigInteger result = BigIntegerParser.parseWithFastParser(input, 2);
        assertNotNull(result);
        assertEquals(new BigInteger(input, 2), result);
    }

    @Test(expected = NumberFormatException.class)
    public void parseWithFastParser_InvalidNumber_ThrowsException() {
        BigIntegerParser.parseWithFastParser("notANumber");
    }

    @Test(expected = StringIndexOutOfBoundsException.class)
    public void parseWithFastParser_EmptyString_ThrowsException() {
        BigIntegerParser.parseWithFastParser("");
    }

    @Test(expected = NumberFormatException.class)
    public void parseWithFastParser_InvalidWithRadix_ThrowsException() {
        BigIntegerParser.parseWithFastParser("GG", 16);
    }

    @Test
    public void parseWithFastParser_InvalidNumber_ExceptionMessageContainsValue() {
        String input = "invalid";
        try {
            BigIntegerParser.parseWithFastParser(input);
        } catch (NumberFormatException nfe) {
            assertTrue(nfe.getMessage().contains("Value \""));
            assertTrue(nfe.getMessage().contains(input));
            assertTrue(nfe.getMessage().contains("BigInteger"));
        }
    }

    @Test
    public void parseWithFastParser_InvalidWithRadix_ExceptionMessageContainsRadix() {
        String input = "invalid";
        try {
            BigIntegerParser.parseWithFastParser(input, 8);
        } catch (NumberFormatException nfe) {
            assertTrue(nfe.getMessage().contains("radix 8"));
            assertTrue(nfe.getMessage().contains("BigInteger"));
        }
    }

    @Test
    public void parseWithFastParser_InvalidNumber_ExceptionMessageHasReason() {
        String input = "12abc";
        try {
            BigIntegerParser.parseWithFastParser(input);
        } catch (NumberFormatException nfe) {
            assertTrue(nfe.getMessage().contains("reason:"));
        }
    }
}
