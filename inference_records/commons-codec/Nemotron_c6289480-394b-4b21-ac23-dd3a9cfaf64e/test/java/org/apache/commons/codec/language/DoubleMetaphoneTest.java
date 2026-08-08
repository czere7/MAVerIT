package org.apache.commons.codec.language;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.apache.commons.codec.Encoder;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;
import org.junit.Before;
import org.junit.Test;

public class DoubleMetaphoneTest {

    private DoubleMetaphone doubleMetaphone;

    @Before
    public void setUp() {
        doubleMetaphone = new DoubleMetaphone();
    }

    @Test
    public void testDefaultMaxCodeLen() {
        assertEquals(4, doubleMetaphone.getMaxCodeLen());
    }

    @Test
    public void testSetMaxCodeLen() {
        doubleMetaphone.setMaxCodeLen(8);
        assertEquals(8, doubleMetaphone.getMaxCodeLen());
    }

    @Test
    public void testEncodeNullString() throws EncoderException {
        assertNull(doubleMetaphone.encode((String) null));
    }

    @Test
    public void testEncodeEmptyString() {
        assertNull(doubleMetaphone.encode(""));
    }

    @Test
    public void testEncodeWhitespaceOnly() {
        assertNull(doubleMetaphone.encode("   "));
    }

    @Test
    public void testEncodeObjectNull() throws EncoderException {
        try {
            doubleMetaphone.encode((Object) null);
            fail("Expected EncoderException for null object");
        } catch (EncoderException e) {
            assertEquals("DoubleMetaphone encode parameter is not of type String", e.getMessage());
        }
    }

    @Test
    public void testEncodeObjectNonStringThrowsException() throws EncoderException {
        try {
            doubleMetaphone.encode(new Integer(123));
            fail("Expected EncoderException");
        } catch (EncoderException e) {
            assertEquals("DoubleMetaphone encode parameter is not of type String", e.getMessage());
        }
    }

    @Test
    public void testDoubleMetaphoneBasic() {
        assertEquals("APL", doubleMetaphone.doubleMetaphone("apple"));
        assertEquals("APL", doubleMetaphone.encode("apple"));
    }

    @Test
    public void testDoubleMetaphoneAlternate() {
        assertEquals("APL", doubleMetaphone.doubleMetaphone("apple", false));
        assertEquals("APL", doubleMetaphone.doubleMetaphone("apple", true));
    }

    @Test
    public void testDoubleMetaphoneWithAlternateEncoding() {
        String primary = doubleMetaphone.doubleMetaphone("smith", false);
        String alternate = doubleMetaphone.doubleMetaphone("smith", true);
        assertNotNull(primary);
        assertNotNull(alternate);
    }

    @Test
    public void testCaseInsensitivity() {
        String lower = doubleMetaphone.doubleMetaphone("smith");
        String upper = doubleMetaphone.doubleMetaphone("SMITH");
        String mixed = doubleMetaphone.doubleMetaphone("SmItH");
        assertEquals(lower, upper);
        assertEquals(lower, mixed);
    }

    @Test
    public void testTrimInput() {
        assertEquals(doubleMetaphone.doubleMetaphone("smith"), doubleMetaphone.doubleMetaphone("  smith  "));
    }

    @Test
    public void testMaxCodeLenLimitsOutput() {
        doubleMetaphone.setMaxCodeLen(2);
        assertEquals(2, doubleMetaphone.doubleMetaphone("william").length());
        assertEquals(2, doubleMetaphone.doubleMetaphone("william", true).length());
        
        doubleMetaphone.setMaxCodeLen(10);
        assertTrue(doubleMetaphone.doubleMetaphone("william").length() <= 10);
    }

    @Test
    public void testIsDoubleMetaphoneEqual() {
        assertTrue(doubleMetaphone.isDoubleMetaphoneEqual("smith", "smythe"));
        assertTrue(doubleMetaphone.isDoubleMetaphoneEqual("johnson", "jonson"));
        assertFalse(doubleMetaphone.isDoubleMetaphoneEqual("smith", "jones"));
    }

    @Test
    public void testIsDoubleMetaphoneEqualWithAlternate() {
        assertTrue(doubleMetaphone.isDoubleMetaphoneEqual("smith", "smythe", true));
        assertTrue(doubleMetaphone.isDoubleMetaphoneEqual("johnson", "jonson", true));
        assertFalse(doubleMetaphone.isDoubleMetaphoneEqual("smith", "jones", true));
    }

    @Test
    public void testEncodeObjectString() throws EncoderException {
        Object result = doubleMetaphone.encode("smith");
        assertNotNull(result);
        assertTrue(result instanceof String);
        String primary = doubleMetaphone.doubleMetaphone("smith");
        assertEquals(primary, result);
    }

    @Test
    public void testAlternateEncodingDifferences() {
        String primary = doubleMetaphone.doubleMetaphone("kuczewski", false);
        String alternate = doubleMetaphone.doubleMetaphone("kuczewski", true);
        assertNotNull(primary);
        assertNotNull(alternate);
    }

    @Test
    public void testLongNameTruncation() {
        doubleMetaphone.setMaxCodeLen(4);
        String result = doubleMetaphone.doubleMetaphone("supercalifragilisticexpialidocious");
        assertEquals(4, result.length());
    }

    @Test
    public void testDoubleMetaphoneResultIsComplete() {
        doubleMetaphone.setMaxCodeLen(2);
        String result = doubleMetaphone.doubleMetaphone("ab");
        assertEquals(2, result.length());
    }

    @Test
    public void testIsDoubleMetaphoneEqualNullHandling() {
        assertTrue(doubleMetaphone.isDoubleMetaphoneEqual(null, null));
        assertFalse(doubleMetaphone.isDoubleMetaphoneEqual("test", null));
        assertFalse(doubleMetaphone.isDoubleMetaphoneEqual(null, "test"));
    }

    @Test
    public void testIsDoubleMetaphoneEqualEmptyString() {
        assertTrue(doubleMetaphone.isDoubleMetaphoneEqual("", ""));
        assertFalse(doubleMetaphone.isDoubleMetaphoneEqual("test", ""));
    }

    @Test
    public void testMaxCodeLenZero() {
        doubleMetaphone.setMaxCodeLen(0);
        assertEquals("", doubleMetaphone.doubleMetaphone("test"));
    }

    @Test
    public void testMaxCodeLenOne() {
        doubleMetaphone.setMaxCodeLen(1);
        assertEquals(1, doubleMetaphone.doubleMetaphone("test").length());
    }

    @Test
    public void testVowelOnlyWords() {
        assertEquals("A", doubleMetaphone.doubleMetaphone("a"));
        assertEquals("A", doubleMetaphone.doubleMetaphone("aeiou"));
    }

    @Test
    public void testMultipleEncodingsConsistent() {
        for (int i = 0; i < 100; i++) {
            assertEquals(doubleMetaphone.doubleMetaphone("smith"), doubleMetaphone.doubleMetaphone("smith"));
        }
    }

    @Test
    public void testEncodeStringInterface() throws EncoderException {
        StringEncoder encoder = doubleMetaphone;
        String result = encoder.encode("smith");
        assertNotNull(result);
        assertEquals(doubleMetaphone.doubleMetaphone("smith"), result);
    }

    @Test
    public void testEncodeObjectInterface() throws EncoderException {
        Encoder encoder = doubleMetaphone;
        Object result = encoder.encode("smith");
        assertNotNull(result);
        assertTrue(result instanceof String);
        assertEquals(doubleMetaphone.doubleMetaphone("smith"), result);
    }

    @Test
    public void testAlternateEncodingForDifficultNames() {
        String primary = doubleMetaphone.doubleMetaphone("peter", false);
        String alternate = doubleMetaphone.doubleMetaphone("peter", true);
        assertNotNull(primary);
        assertNotNull(alternate);
    }

    @Test
    public void testSpecialCaseCedilla() {
        assertEquals("S", doubleMetaphone.doubleMetaphone("\u00C7a"));
    }

    @Test
    public void testNamesWithHyphens() {
        String result1 = doubleMetaphone.doubleMetaphone("smith-jones");
        String result2 = doubleMetaphone.doubleMetaphone("smithjones");
        assertNotNull(result1);
        assertNotNull(result2);
    }

    @Test
    public void testNamesWithApostrophes() {
        String result1 = doubleMetaphone.doubleMetaphone("o'connor");
        String result2 = doubleMetaphone.doubleMetaphone("oconnor");
        assertNotNull(result1);
        assertNotNull(result2);
    }
}
