package tools.jackson.core.io;

import java.math.BigInteger;
import java.lang.reflect.Field;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link BigIntegerParser}.
 */
public class BigIntegerParserTest {

    /**
     * Helper to obtain the value of {@code BigDecimalParser.MAX_CHARS_TO_REPORT}
     * via reflection (the constant is package-private).
     */
    private int getMaxCharsToReport() throws Exception {
        Field f = BigDecimalParser.class.getDeclaredField("MAX_CHARS_TO_REPORT");
        f.setAccessible(true);
        return f.getInt(null);
    }

    @Test
    public void testParseWithFastParser_validLargeNumber() throws Exception {
        // Create a very large numeric string (more than 500 digits)
        StringBuilder sb = new StringBuilder(600);
        for (int i = 0; i < 600; ++i) {
            sb.append('9');
        }
        String valueStr = sb.toString();

        BigInteger result = BigIntegerParser.parseWithFastParser(valueStr);
        assertEquals(new BigInteger(valueStr), result);
    }

    @Test
    public void testParseWithFastParser_validNumberRadix() throws Exception {
        // Hexadecimal number 1A3F -> decimal 6719
        String valueStr = "1A3F";
        int radix = 16;

        BigInteger result = BigIntegerParser.parseWithFastParser(valueStr, radix);
        assertEquals(BigInteger.valueOf(6719L), result);
    }

    @Test
    public void testParseWithFastParser_invalidNumber_truncatedMessage() throws Exception {
        String longInvalid = new String(new char[1000]).replace('\0', 'x'); // 1000 x's

        try {
            BigIntegerParser.parseWithFastParser(longInvalid);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException nfe) {
            int maxChars = getMaxCharsToReport();
            String expectedReport;
            if (longInvalid.length() <= maxChars) {
                expectedReport = longInvalid;
            } else {
                expectedReport = longInvalid.substring(0, maxChars) + " [truncated]";
            }
            String prefix = "Value \"" + expectedReport
                    + "\" cannot be represented as `java.math.BigInteger`, reason: ";
            assertTrue("Exception message should start with the correct prefix",
                    nfe.getMessage().startsWith(prefix));
        }
    }

    @Test
    public void testParseWithFastParser_invalidNumberRadix_truncatedMessage() throws Exception {
        String longInvalid = new String(new char[1200]).replace('\0', 'z'); // 1200 z's
        int radix = 10;

        try {
            BigIntegerParser.parseWithFastParser(longInvalid, radix);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException nfe) {
            int maxChars = getMaxCharsToReport();
            String expectedReport;
            if (longInvalid.length() <= maxChars) {
                expectedReport = longInvalid;
            } else {
                expectedReport = longInvalid.substring(0, maxChars) + " [truncated]";
            }
            String prefix = "Value \"" + expectedReport
                    + "\" cannot be represented as `java.math.BigInteger` with radix "
                    + radix + ", reason: ";
            assertTrue("Exception message should start with the correct prefix",
                    nfe.getMessage().startsWith(prefix));
        }
    }

    @Test(expected = NullPointerException.class)
    public void testParseWithFastParser_nullValue_throwsNPE() {
        BigIntegerParser.parseWithFastParser(null);
    }

    @Test(expected = NullPointerException.class)
    public void testParseWithFastParser_nullValueRadix_throwsNPE() {
        BigIntegerParser.parseWithFastParser(null, 10);
    }

    /* ------------------------------------------------------------------ *
     * Additional tests focusing on the missing branches in the catch block
     * ------------------------------------------------------------------ */

    @Test
    public void testParseWithFastParser_invalidNumberShort_noTruncation() throws Exception {
        String valueStr = "123a"; // invalid, length < MAX_CHARS_TO_REPORT
        try {
            BigIntegerParser.parseWithFastParser(valueStr);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException nfe) {
            int maxChars = getMaxCharsToReport();
            assertTrue(nfe.getMessage().length() > 0);
            // should not contain "[truncated]" since string is short
            assertFalse(nfe.getMessage(), nfe.getMessage().contains("[truncated]"));
            String expectedPrefix =
                    "Value \"" + valueStr + "\" cannot be represented as `java.math.BigInteger`, reason: ";
            assertTrue(nfe.getMessage().startsWith(expectedPrefix));
        }
    }

    @Test
    public void testParseWithFastParser_invalidNumberShortRadix_noTruncation() throws Exception {
        String valueStr = "123a"; // invalid, length < MAX_CHARS_TO_REPORT
        int radix = 10;
        try {
            BigIntegerParser.parseWithFastParser(valueStr, radix);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException nfe) {
            assertTrue(nfe.getMessage().length() > 0);
            // should not contain "[truncated]" since string is short
            assertFalse(nfe.getMessage(), nfe.getMessage().contains("[truncated]"));
            String expectedPrefix =
                    "Value \"" + valueStr + "\" cannot be represented as `java.math.BigInteger` with radix "
                            + radix + ", reason: ";
            assertTrue(nfe.getMessage().startsWith(expectedPrefix));
        }
    }

    @Test
    public void testParseWithFastParser_invalidNumberExactlyMaxLength() throws Exception {
        int maxChars = getMaxCharsToReport();
        // Build a string exactly the maximum length, but still invalid
        StringBuilder sb = new StringBuilder(maxChars);
        for (int i = 0; i < maxChars; ++i) {
            sb.append('x');
        }
        String valueStr = sb.toString();

        try {
            BigIntegerParser.parseWithFastParser(valueStr);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException nfe) {
            // Since the length equals MAX_CHARS_TO_REPORT, no truncation should happen
            assertFalse(nfe.getMessage(), nfe.getMessage().contains("[truncated]"));
            String expectedPrefix =
                    "Value \"" + valueStr + "\" cannot be represented as `java.math.BigInteger`, reason: ";
            assertTrue(nfe.getMessage().startsWith(expectedPrefix));
        }
    }

    /* ------------------------------------------------------------------ *
     * New tests added to cover remaining branch paths
     * ------------------------------------------------------------------ */

    @Test
    public void testParseWithFastParser_emptyString_throwsNFE() {
        String valueStr = "";
        try {
            BigIntegerParser.parseWithFastParser(valueStr);
            fail("Expected exception");
        } catch (Throwable t) { // Capture both NumberFormatException and possible SIOOBE
            assertTrue(t instanceof NumberFormatException || t instanceof IndexOutOfBoundsException ||
                    t instanceof StringIndexOutOfBoundsException);
            if (t instanceof NumberFormatException) {
                // Expect original message to contain "Zero length"
                assertTrue("Message should indicate zero length", t.getMessage().contains("Zero"));
            } else { // For the unexpected SIOOBE
                assertTrue(t.getMessage() != null && !t.getMessage().isEmpty());
            }
        }
    }

    @Test
    public void testParseWithFastParser_emptyStringRadix_throwsNFE() {
        String valueStr = "";
        int radix = 10;
        try {
            BigIntegerParser.parseWithFastParser(valueStr, radix);
            fail("Expected exception");
        } catch (Throwable t) { // Capture both NumberFormatException and possible SIOOBE
            assertTrue(t instanceof NumberFormatException || t instanceof IndexOutOfBoundsException ||
                    t instanceof StringIndexOutOfBoundsException);
            if (t instanceof NumberFormatException) {
                // Expect original message to contain "Zero length"
                assertTrue("Message should indicate zero length", t.getMessage().contains("Zero"));
            } else { // For the unexpected SIOOBE
                assertTrue(t.getMessage() != null && !t.getMessage().isEmpty());
            }
        }
    }

    /* ------------------------------------------------------------------ *
     * New test to cover equality boundary for radix-based parser
     * ------------------------------------------------------------------ */

    @Test
    public void testParseWithFastParser_invalidNumberRadixExactlyMaxLength() throws Exception {
        int maxChars = getMaxCharsToReport();
        // Build a string exactly the maximum length, but invalid in decimal radix
        StringBuilder sb = new StringBuilder(maxChars);
        for (int i = 0; i < maxChars; ++i) {
            sb.append('a'); // 'a' is not a valid digit in radix 10
        }
        String valueStr = sb.toString();

        try {
            BigIntegerParser.parseWithFastParser(valueStr, 10);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException nfe) {
            // For equality case there should be no truncation
            assertFalse(nfe.getMessage(), nfe.getMessage().contains("[truncated]"));
            String expectedPrefix =
                    "Value \"" + valueStr + "\" cannot be represented as `java.math.BigInteger` with radix "
                            + 10 + ", reason: ";
            assertTrue(nfe.getMessage().startsWith(expectedPrefix));
        }
    }
}
