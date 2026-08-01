import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegressionTest0 {

    public static boolean debug = false;

    public void assertBooleanArrayEquals(boolean[] expectedArray, boolean[] actualArray) {
        if (expectedArray.length != actualArray.length) {
            throw new AssertionError("Array lengths differ: " + expectedArray.length + " != " + actualArray.length);
        }
        for (int i = 0; i < expectedArray.length; i++) {
            if (expectedArray[i] != actualArray[i]) {
                throw new AssertionError("Arrays differ at index " + i + ": " + expectedArray[i] + " != " + actualArray[i]);
            }
        }
    }

    @Test
    public void test0001() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0001");
        int int0 = tools.jackson.core.util.BufferRecycler.CHAR_TOKEN_BUFFER;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 0 + "'", int0 == 0);
    }

    @Test
    public void test0002() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0002");
        tools.jackson.core.io.IOContext iOContext0 = null;
        java.io.OutputStream outputStream1 = null;
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.io.UTF8Writer uTF8Writer2 = new tools.jackson.core.io.UTF8Writer(iOContext0, outputStream1);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0003() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0003");
        char[] charArray4 = new char[] { 'a', 'a', '#', 'a' };
        // The following exception was thrown during execution in test generation
        try {
            double double8 = tools.jackson.core.io.NumberInput.parseDouble(charArray4, (int) (byte) 10, (int) '4', false);
            org.junit.Assert.fail("Expected exception of type java.lang.StringIndexOutOfBoundsException; message: String index out of range: 62");
        } catch (java.lang.StringIndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(charArray4);
        org.junit.Assert.assertArrayEquals(charArray4, new char[] { 'a', 'a', '#', 'a' });
    }

    @Test
    public void test0004() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0004");
        int int0 = tools.jackson.core.sym.PropertyNameMatcher.MATCH_UNKNOWN_NAME;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + (-2) + "'", int0 == (-2));
    }

    @Test
    public void test0005() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0005");
        char[] charArray3 = new char[] { '#', '4', '#' };
        // The following exception was thrown during execution in test generation
        try {
            long long6 = tools.jackson.core.io.NumberInput.parseLong19(charArray3, 0, false);
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: 3");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(charArray3);
        org.junit.Assert.assertArrayEquals(charArray3, new char[] { '#', '4', '#' });
    }

    @Test
    public void test0006() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0006");
        int int0 = tools.jackson.core.io.CharacterEscapes.ESCAPE_NONE;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 0 + "'", int0 == 0);
    }

    @Test
    public void test0007() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0007");
        int int0 = tools.jackson.core.io.schubfach.MathUtils.K_MIN;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + (-324) + "'", int0 == (-324));
    }

    @Test
    public void test0008() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0008");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        // The following exception was thrown during execution in test generation
        try {
            char[] charArray6 = filteringParserDelegate5.getTextCharacters();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0009() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0009");
        // The following exception was thrown during execution in test generation
        try {
            java.math.BigDecimal bigDecimal1 = tools.jackson.core.io.BigDecimalParser.parse("hi!");
            org.junit.Assert.fail("Expected exception of type java.lang.NumberFormatException; message: Value \"hi!\" cannot be deserialized as `java.math.BigDecimal`, reason:  Not a valid number representation");
        } catch (java.lang.NumberFormatException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0010() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0010");
        // The following exception was thrown during execution in test generation
        try {
            int[] intArray1 = tools.jackson.core.sym.BinaryNameMatcher._quads("");
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: 0");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0011() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0011");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.StreamReadConstraints streamReadConstraints6 = filteringParserDelegate5.streamReadConstraints();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0012() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0012");
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.util.ByteArrayBuilder byteArrayBuilder1 = new tools.jackson.core.util.ByteArrayBuilder((-324));
            org.junit.Assert.fail("Expected exception of type java.lang.NegativeArraySizeException; message: null");
        } catch (java.lang.NegativeArraySizeException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0013() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0013");
        double double2 = tools.jackson.core.io.NumberInput.parseAsDouble("", (double) 1.0f);
        org.junit.Assert.assertTrue("'" + double2 + "' != '" + 1.0d + "'", double2 == 1.0d);
    }

    @Test
    public void test0014() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0014");
        int[] intArray0 = tools.jackson.core.io.CharTypes.get7BitOutputEscapes();
        org.junit.Assert.assertNotNull(intArray0);
    }

    @Test
    public void test0015() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0015");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        // The following exception was thrown during execution in test generation
        try {
            int int6 = filteringParserDelegate5.getTextLength();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0016() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0016");
        int int0 = tools.jackson.core.io.schubfach.FloatToDecimal.H;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 9 + "'", int0 == 9);
    }

    @Test
    public void test0017() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0017");
        int int0 = tools.jackson.core.TokenStreamContext.TYPE_ROOT;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 0 + "'", int0 == 0);
    }

    @Test
    public void test0018() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0018");
        // The following exception was thrown during execution in test generation
        try {
            java.math.BigInteger bigInteger1 = tools.jackson.core.io.NumberInput.parseBigInteger("");
            org.junit.Assert.fail("Expected exception of type java.lang.NumberFormatException; message: Zero length BigInteger");
        } catch (java.lang.NumberFormatException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0019() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0019");
        tools.jackson.core.Base64Variant.PaddingReadBehaviour paddingReadBehaviour0 = tools.jackson.core.Base64Variant.PaddingReadBehaviour.PADDING_ALLOWED;
        org.junit.Assert.assertTrue("'" + paddingReadBehaviour0 + "' != '" + tools.jackson.core.Base64Variant.PaddingReadBehaviour.PADDING_ALLOWED + "'", paddingReadBehaviour0.equals(tools.jackson.core.Base64Variant.PaddingReadBehaviour.PADDING_ALLOWED));
    }

    @Test
    public void test0020() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0020");
        double double2 = tools.jackson.core.io.NumberInput.parseAsDouble("hi!", (double) (short) 100);
        org.junit.Assert.assertTrue("'" + double2 + "' != '" + 100.0d + "'", double2 == 100.0d);
    }

    @Test
    public void test0021() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0021");
        long long0 = tools.jackson.core.StreamReadConstraints.DEFAULT_MAX_DOC_LEN;
        org.junit.Assert.assertTrue("'" + long0 + "' != '" + (-1L) + "'", long0 == (-1L));
    }

    @Test
    public void test0022() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0022");
        int int0 = tools.jackson.core.json.JsonWriteContext.STATUS_EXPECT_NAME;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 5 + "'", int0 == 5);
    }

    @Test
    public void test0023() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0023");
        tools.jackson.core.ObjectWriteContext objectWriteContext0 = null;
        tools.jackson.core.io.IOContext iOContext1 = null;
        tools.jackson.core.util.BufferRecycler bufferRecycler4 = null;
        tools.jackson.core.util.ByteArrayBuilder byteArrayBuilder5 = new tools.jackson.core.util.ByteArrayBuilder(bufferRecycler4);
        tools.jackson.core.util.BufferRecycler bufferRecycler6 = byteArrayBuilder5.bufferRecycler();
        tools.jackson.core.SerializableString serializableString7 = null;
        tools.jackson.core.io.CharacterEscapes characterEscapes8 = null;
        tools.jackson.core.PrettyPrinter prettyPrinter9 = null;
        tools.jackson.core.util.BufferRecycler bufferRecycler12 = null;
        tools.jackson.core.util.ByteArrayBuilder byteArrayBuilder13 = new tools.jackson.core.util.ByteArrayBuilder(bufferRecycler12);
        byte[] byteArray15 = byteArrayBuilder13.completeAndCoalesce(10);
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.json.UTF8JsonGenerator uTF8JsonGenerator18 = new tools.jackson.core.json.UTF8JsonGenerator(objectWriteContext0, iOContext1, (int) (short) 100, (-1), (java.io.OutputStream) byteArrayBuilder5, serializableString7, characterEscapes8, prettyPrinter9, (int) (byte) 100, ' ', byteArray15, (int) (short) 1, false);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(bufferRecycler6);
        org.junit.Assert.assertNotNull(byteArray15);
        org.junit.Assert.assertArrayEquals(byteArray15, new byte[] { (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0 });
    }

    @Test
    public void test0024() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0024");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.async.NonBlockingInputFeeder nonBlockingInputFeeder6 = filteringParserDelegate5.nonBlockingInputFeeder();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0025() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0025");
        int int0 = tools.jackson.core.Base64Variant.BASE64_VALUE_PADDING;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + (-2) + "'", int0 == (-2));
    }

    @Test
    public void test0026() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0026");
        java.io.IOException iOException0 = null;
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.exc.JacksonIOException jacksonIOException1 = tools.jackson.core.exc.JacksonIOException.construct(iOException0);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0027() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0027");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        tools.jackson.core.sym.PropertyNameMatcher propertyNameMatcher6 = null;
        // The following exception was thrown during execution in test generation
        try {
            int int7 = filteringParserDelegate5.nextNameMatch(propertyNameMatcher6);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0028() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0028");
        long long1 = tools.jackson.core.io.schubfach.MathUtils.g1((int) (short) -1);
        org.junit.Assert.assertTrue("'" + long1 + "' != '" + 5764607523034234880L + "'", long1 == 5764607523034234880L);
    }

    @Test
    public void test0029() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0029");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        boolean boolean6 = filteringParserDelegate5.isExpectedStartObjectToken();
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean7 = filteringParserDelegate5.canReadObjectId();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
    }

    @Test
    public void test0030() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0030");
        long long2 = tools.jackson.core.io.schubfach.DoubleToDecimal.multiplyHigh((long) 0, (long) ' ');
        org.junit.Assert.assertTrue("'" + long2 + "' != '" + 0L + "'", long2 == 0L);
    }

    @Test
    public void test0031() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0031");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        boolean boolean6 = filteringParserDelegate5.isExpectedStartObjectToken();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str7 = filteringParserDelegate5.nextName();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
    }

    @Test
    public void test0032() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0032");
        tools.jackson.core.StreamReadConstraints streamReadConstraints0 = null;
        tools.jackson.core.StreamWriteConstraints streamWriteConstraints1 = tools.jackson.core.StreamWriteConstraints.defaults();
        tools.jackson.core.ErrorReportConfiguration errorReportConfiguration2 = null;
        tools.jackson.core.util.BufferRecycler bufferRecycler3 = null;
        tools.jackson.core.io.ContentReference contentReference4 = null;
        tools.jackson.core.JsonEncoding jsonEncoding6 = null;
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.io.IOContext iOContext7 = new tools.jackson.core.io.IOContext(streamReadConstraints0, streamWriteConstraints1, errorReportConfiguration2, bufferRecycler3, contentReference4, false, jsonEncoding6);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(streamWriteConstraints1);
    }

    @Test
    public void test0033() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0033");
        int int0 = tools.jackson.core.ErrorReportConfiguration.DEFAULT_MAX_ERROR_TOKEN_LENGTH;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 256 + "'", int0 == 256);
    }

    @Test
    public void test0034() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0034");
        boolean boolean1 = tools.jackson.core.io.NumberOutput.notFinite((float) 3);
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
    }

    @Test
    public void test0035() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0035");
        char char0 = tools.jackson.core.JsonPointer.ESC;
        org.junit.Assert.assertTrue("'" + char0 + "' != '" + '~' + "'", char0 == '~');
    }

    @Test
    public void test0036() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0036");
        tools.jackson.core.ObjectReadContext objectReadContext0 = null;
        tools.jackson.core.io.IOContext iOContext1 = null;
        tools.jackson.core.io.IOContext iOContext4 = null;
        java.io.InputStream inputStream5 = null;
        byte[] byteArray9 = new byte[] { (byte) 100, (byte) 1 };
        tools.jackson.core.io.UTF32Reader uTF32Reader13 = new tools.jackson.core.io.UTF32Reader(iOContext4, inputStream5, false, byteArray9, (int) (short) 0, (-1), false);
        tools.jackson.core.sym.CharsToNameCanonicalizer charsToNameCanonicalizer14 = null;
        char[] charArray18 = new char[] { ' ', ' ', '4' };
        boolean boolean22 = tools.jackson.core.io.NumberInput.inLongRange(charArray18, (-324), (int) '4', false);
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.json.ReaderBasedJsonParser readerBasedJsonParser26 = new tools.jackson.core.json.ReaderBasedJsonParser(objectReadContext0, iOContext1, (-1), (int) ' ', (java.io.Reader) uTF32Reader13, charsToNameCanonicalizer14, charArray18, (int) (byte) 10, (-2), false);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray9);
        org.junit.Assert.assertArrayEquals(byteArray9, new byte[] { (byte) 100, (byte) 1 });
        org.junit.Assert.assertNotNull(charArray18);
        org.junit.Assert.assertArrayEquals(charArray18, new char[] { ' ', ' ', '4' });
        org.junit.Assert.assertTrue("'" + boolean22 + "' != '" + false + "'", boolean22 == false);
    }

    @Test
    public void test0037() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0037");
        int int0 = tools.jackson.core.io.schubfach.FloatToDecimal.K_MIN;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + (-45) + "'", int0 == (-45));
    }

    @Test
    public void test0038() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0038");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.JsonToken jsonToken6 = filteringParserDelegate5.nextValue();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0039() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0039");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean6 = filteringParserDelegate5.canParseAsync();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0040() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0040");
        // The following exception was thrown during execution in test generation
        try {
            java.util.concurrent.ConcurrentHashMap.KeySetView<java.lang.RuntimeException, java.lang.Boolean> runtimeExceptionSet1 = java.util.concurrent.ConcurrentHashMap.newKeySet((-324));
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: null");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0041() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0041");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        boolean boolean6 = filteringParserDelegate5.isExpectedStartObjectToken();
        int int7 = filteringParserDelegate5.currentTokenId();
        // The following exception was thrown during execution in test generation
        try {
            int int8 = filteringParserDelegate5.getValueAsInt();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 0 + "'", int7 == 0);
    }

    @Test
    public void test0042() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0042");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        boolean boolean6 = filteringParserDelegate5.isExpectedStartObjectToken();
        int int7 = filteringParserDelegate5.currentTokenId();
        // The following exception was thrown during execution in test generation
        try {
            short short8 = filteringParserDelegate5.getShortValue();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 0 + "'", int7 == 0);
    }

    @Test
    public void test0043() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0043");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        boolean boolean6 = filteringParserDelegate5.isExpectedStartObjectToken();
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean7 = filteringParserDelegate5.getValueAsBoolean();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
    }

    @Test
    public void test0044() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0044");
        int[] intArray1 = tools.jackson.core.sym.BinaryNameMatcher._quads("hi!");
        org.junit.Assert.assertNotNull(intArray1);
        org.junit.Assert.assertArrayEquals(intArray1, new int[] { 6842657 });
    }

    @Test
    public void test0045() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0045");
        java.lang.String str2 = tools.jackson.core.io.NumberOutput.toString((float) 100L, false);
        org.junit.Assert.assertEquals("'" + str2 + "' != '" + "100.0" + "'", str2, "100.0");
    }

    @Test
    public void test0046() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0046");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        boolean boolean6 = filteringParserDelegate5.isExpectedStartObjectToken();
        int int7 = filteringParserDelegate5.currentTokenId();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str8 = filteringParserDelegate5.nextStringValue();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 0 + "'", int7 == 0);
    }

    @Test
    public void test0047() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0047");
        tools.jackson.core.util.DefaultPrettyPrinter.FixedSpaceIndenter fixedSpaceIndenter0 = tools.jackson.core.util.DefaultPrettyPrinter.FixedSpaceIndenter.instance();
        tools.jackson.core.JsonGenerator jsonGenerator1 = null;
        // The following exception was thrown during execution in test generation
        try {
            fixedSpaceIndenter0.writeIndentation(jsonGenerator1, 0);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(fixedSpaceIndenter0);
    }

    @Test
    public void test0048() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0048");
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder0 = tools.jackson.core.json.JsonFactory.builderWithJackson2Defaults();
        tools.jackson.core.TokenStreamFactory.Feature feature1 = null;
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder2 = jsonFactoryBuilder0.disable(feature1);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(jsonFactoryBuilder0);
    }

    @Test
    public void test0049() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0049");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        boolean boolean6 = filteringParserDelegate5.isExpectedStartObjectToken();
        int int7 = filteringParserDelegate5.currentTokenId();
        // The following exception was thrown during execution in test generation
        try {
            long long8 = filteringParserDelegate5.getLongValue();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 0 + "'", int7 == 0);
    }

    @Test
    public void test0050() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0050");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        boolean boolean6 = filteringParserDelegate5.isExpectedStartObjectToken();
        // The following exception was thrown during execution in test generation
        try {
            double double7 = filteringParserDelegate5.getDoubleValue();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
    }

    @Test
    public void test0051() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0051");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        boolean boolean6 = filteringParserDelegate5.isExpectedStartObjectToken();
        // The following exception was thrown during execution in test generation
        try {
            long long7 = filteringParserDelegate5.getValueAsLong();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
    }

    @Test
    public void test0052() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0052");
        tools.jackson.core.exc.StreamReadException streamReadException1 = new tools.jackson.core.exc.StreamReadException("");
        java.lang.String str2 = streamReadException1.getPathReference();
        java.lang.Object obj3 = streamReadException1.processor();
        org.junit.Assert.assertEquals("'" + str2 + "' != '" + "" + "'", str2, "");
        org.junit.Assert.assertNull(obj3);
    }

    @Test
    public void test0053() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0053");
        tools.jackson.core.Base64Variant base64Variant0 = tools.jackson.core.Base64Variants.MODIFIED_FOR_URL;
        byte[] byteArray8 = new byte[] { (byte) 10, (byte) -1, (byte) -1, (byte) -1, (byte) -1 };
        int int10 = base64Variant0.encodeBase64Partial(10, (int) 'a', byteArray8, (int) (byte) 1);
        tools.jackson.core.Base64Variant base64Variant13 = new tools.jackson.core.Base64Variant(base64Variant0, "hi!", 0);
        tools.jackson.core.Base64Variant base64Variant14 = base64Variant0.withPaddingForbidden();
        byte byte15 = base64Variant0.getPaddingByte();
        org.junit.Assert.assertNotNull(base64Variant0);
        org.junit.Assert.assertNotNull(byteArray8);
        org.junit.Assert.assertArrayEquals(byteArray8, new byte[] { (byte) 10, (byte) 65, (byte) 65, (byte) -1, (byte) -1 });
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 3 + "'", int10 == 3);
        org.junit.Assert.assertNotNull(base64Variant14);
        org.junit.Assert.assertTrue("'" + byte15 + "' != '" + (byte) 0 + "'", byte15 == (byte) 0);
    }

    @Test
    public void test0054() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0054");
        int int0 = tools.jackson.core.util.RecyclerPool.StatefulImplBase.SERIALIZATION_SHARED;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + (-1) + "'", int0 == (-1));
    }

    @Test
    public void test0055() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0055");
        char[] charArray3 = new char[] { ' ', ' ', '4' };
        boolean boolean7 = tools.jackson.core.io.NumberInput.inLongRange(charArray3, (-324), (int) '4', false);
        // The following exception was thrown during execution in test generation
        try {
            java.math.BigDecimal bigDecimal9 = tools.jackson.core.io.NumberInput.parseBigDecimal(charArray3, false);
            org.junit.Assert.fail("Expected exception of type java.lang.NumberFormatException; message: Value \"  4\" cannot be deserialized as `java.math.BigDecimal`, reason:  Not a valid number representation");
        } catch (java.lang.NumberFormatException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(charArray3);
        org.junit.Assert.assertArrayEquals(charArray3, new char[] { ' ', ' ', '4' });
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
    }

    @Test
    public void test0056() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0056");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        tools.jackson.core.filter.TokenFilter tokenFilter6 = filteringParserDelegate5.getFilter();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj7 = filteringParserDelegate5.getEmbeddedObject();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(tokenFilter6);
    }

    @Test
    public void test0057() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0057");
        tools.jackson.core.ErrorReportConfiguration errorReportConfiguration0 = null;
        tools.jackson.core.ErrorReportConfiguration.overrideDefaultErrorReportConfiguration(errorReportConfiguration0);
    }

    @Test
    public void test0058() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0058");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        boolean boolean6 = filteringParserDelegate5.isExpectedStartObjectToken();
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.JsonToken jsonToken7 = filteringParserDelegate5.nextValue();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
    }

    @Test
    public void test0059() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0059");
        int int0 = tools.jackson.core.sym.PropertyNameMatcher.MATCH_ODD_TOKEN;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + (-3) + "'", int0 == (-3));
    }

    @Test
    public void test0060() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0060");
        int int0 = tools.jackson.core.base.GeneratorBase.SURR1_FIRST;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 55296 + "'", int0 == 55296);
    }

    @Test
    public void test0061() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0061");
        tools.jackson.core.Base64Variant base64Variant0 = tools.jackson.core.Base64Variants.MODIFIED_FOR_URL;
        byte[] byteArray8 = new byte[] { (byte) 10, (byte) -1, (byte) -1, (byte) -1, (byte) -1 };
        int int10 = base64Variant0.encodeBase64Partial(10, (int) 'a', byteArray8, (int) (byte) 1);
        tools.jackson.core.Base64Variant base64Variant13 = new tools.jackson.core.Base64Variant(base64Variant0, "hi!", 0);
        boolean boolean14 = base64Variant0.requiresPaddingOnRead();
        java.lang.StringBuilder stringBuilder15 = null;
        // The following exception was thrown during execution in test generation
        try {
            base64Variant0.encodeBase64Chunk(stringBuilder15, 9);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(base64Variant0);
        org.junit.Assert.assertNotNull(byteArray8);
        org.junit.Assert.assertArrayEquals(byteArray8, new byte[] { (byte) 10, (byte) 65, (byte) 65, (byte) -1, (byte) -1 });
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 3 + "'", int10 == 3);
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + false + "'", boolean14 == false);
    }

    @Test
    public void test0062() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0062");
        int int2 = tools.jackson.core.io.NumberInput.parseAsInt("", 55296);
        org.junit.Assert.assertTrue("'" + int2 + "' != '" + 55296 + "'", int2 == 55296);
    }

    @Test
    public void test0063() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0063");
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter1 = new tools.jackson.core.util.MinimalPrettyPrinter("hi!");
        tools.jackson.core.JsonGenerator jsonGenerator2 = null;
        // The following exception was thrown during execution in test generation
        try {
            minimalPrettyPrinter1.writeEndObject(jsonGenerator2, (int) '4');
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0064() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0064");
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder0 = tools.jackson.core.json.JsonFactory.builderWithJackson2Defaults();
        tools.jackson.core.StreamWriteFeature streamWriteFeature1 = null;
        tools.jackson.core.StreamWriteFeature[] streamWriteFeatureArray2 = new tools.jackson.core.StreamWriteFeature[] {};
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder3 = jsonFactoryBuilder0.disable(streamWriteFeature1, streamWriteFeatureArray2);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(jsonFactoryBuilder0);
        org.junit.Assert.assertNotNull(streamWriteFeatureArray2);
        org.junit.Assert.assertArrayEquals(streamWriteFeatureArray2, new tools.jackson.core.StreamWriteFeature[] {});
    }

    @Test
    public void test0065() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0065");
        java.lang.String[] strArray2 = new java.lang.String[] { "Unexpected end of base64-encoded String: base64 variant 'MODIFIED-FOR-URL' expects padding (one or more '\000' characters) at the end. This Base64Variant might have been incorrectly configured", "" };
        java.util.ArrayList<java.lang.String> strList3 = new java.util.ArrayList<java.lang.String>();
        boolean boolean4 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList3, strArray2);
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.sym.BinaryNameMatcher binaryNameMatcher5 = tools.jackson.core.sym.BinaryNameMatcher.construct((java.util.List<java.lang.String>) strList3);
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: 0");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray2);
        org.junit.Assert.assertArrayEquals(strArray2, new java.lang.String[] { "Unexpected end of base64-encoded String: base64 variant 'MODIFIED-FOR-URL' expects padding (one or more '\000' characters) at the end. This Base64Variant might have been incorrectly configured", "" });
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + true + "'", boolean4 == true);
    }

    @Test
    public void test0066() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0066");
        tools.jackson.core.json.DupDetector dupDetector2 = null;
        tools.jackson.core.json.JsonReadContext jsonReadContext3 = tools.jackson.core.json.JsonReadContext.createRootContext(1, 0, dupDetector2);
        tools.jackson.core.json.JsonReadContext jsonReadContext4 = jsonReadContext3.getParent();
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean5 = jsonReadContext4.expectComma();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(jsonReadContext3);
        org.junit.Assert.assertNull(jsonReadContext4);
    }

    @Test
    public void test0067() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0067");
        tools.jackson.core.io.IOContext iOContext0 = null;
        java.io.InputStream inputStream1 = null;
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.json.ByteSourceJsonBootstrapper byteSourceJsonBootstrapper2 = new tools.jackson.core.json.ByteSourceJsonBootstrapper(iOContext0, inputStream1);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0068() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0068");
        int int0 = tools.jackson.core.util.BufferRecycler.CHAR_TEXT_BUFFER;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 2 + "'", int0 == 2);
    }

    @Test
    public void test0069() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0069");
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.JsonPointer jsonPointer1 = tools.jackson.core.JsonPointer.valueOf("hi!");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Invalid input: JSON Pointer expression must start with '/': \"hi!\"");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0070() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0070");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        tools.jackson.core.filter.TokenFilter tokenFilter6 = filteringParserDelegate5.getFilter();
        tools.jackson.core.type.ResolvedType resolvedType7 = null;
        // The following exception was thrown during execution in test generation
        try {
            java.lang.CharSequence charSequence8 = filteringParserDelegate5.readValueAs(resolvedType7);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(tokenFilter6);
    }

    @Test
    public void test0071() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0071");
        int int0 = tools.jackson.core.Base64Variant.BASE64_VALUE_INVALID;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + (-1) + "'", int0 == (-1));
    }

    @Test
    public void test0072() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0072");
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder0 = tools.jackson.core.json.JsonFactory.builderWithJackson2Defaults();
        tools.jackson.core.StreamWriteFeature streamWriteFeature1 = null;
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder2 = jsonFactoryBuilder0.enable(streamWriteFeature1);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(jsonFactoryBuilder0);
    }

    @Test
    public void test0073() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0073");
        int int0 = tools.jackson.core.io.schubfach.DoubleToDecimal.H;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 17 + "'", int0 == 17);
    }

    @Test
    public void test0074() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0074");
        java.lang.String str0 = tools.jackson.core.json.JsonFactory.FORMAT_NAME_JSON;
        org.junit.Assert.assertEquals("'" + str0 + "' != '" + "JSON" + "'", str0, "JSON");
    }

    @Test
    public void test0075() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0075");
        tools.jackson.core.type.WritableTypeId.Inclusion inclusion0 = tools.jackson.core.type.WritableTypeId.Inclusion.METADATA_PROPERTY;
        org.junit.Assert.assertTrue("'" + inclusion0 + "' != '" + tools.jackson.core.type.WritableTypeId.Inclusion.METADATA_PROPERTY + "'", inclusion0.equals(tools.jackson.core.type.WritableTypeId.Inclusion.METADATA_PROPERTY));
    }

    @Test
    public void test0076() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0076");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        boolean boolean6 = filteringParserDelegate5.isExpectedStartObjectToken();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj7 = filteringParserDelegate5.getTypeId();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
    }

    @Test
    public void test0077() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0077");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        boolean boolean6 = filteringParserDelegate5.isExpectedStartObjectToken();
        int int7 = filteringParserDelegate5.currentTokenId();
        boolean boolean8 = filteringParserDelegate5.isExpectedStartObjectToken();
        // The following exception was thrown during execution in test generation
        try {
            long long9 = filteringParserDelegate5.getLongValue();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 0 + "'", int7 == 0);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
    }

    @Test
    public void test0078() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0078");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        boolean boolean6 = filteringParserDelegate5.isExpectedStartObjectToken();
        int int7 = filteringParserDelegate5.currentTokenId();
        boolean boolean8 = filteringParserDelegate5.isExpectedStartObjectToken();
        // The following exception was thrown during execution in test generation
        try {
            int int10 = filteringParserDelegate5.getValueAsInt((int) ' ');
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 0 + "'", int7 == 0);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
    }

    @Test
    public void test0079() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0079");
        char[] charArray6 = new char[] { '~', '#', '\000', '\000', 'a', '\000' };
        // The following exception was thrown during execution in test generation
        try {
            java.math.BigDecimal bigDecimal9 = tools.jackson.core.io.NumberInput.parseBigDecimal(charArray6, (-45), (-45));
            org.junit.Assert.fail("Expected exception of type java.lang.StringIndexOutOfBoundsException; message: String index out of range: -45");
        } catch (java.lang.StringIndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(charArray6);
        org.junit.Assert.assertArrayEquals(charArray6, new char[] { '~', '#', '\000', '\000', 'a', '\000' });
    }

    @Test
    public void test0080() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0080");
        int int0 = tools.jackson.core.sym.CharsToNameCanonicalizer.HASH_MULT;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 33 + "'", int0 == 33);
    }

    @Test
    public void test0081() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0081");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj6 = filteringParserDelegate5.getNumberValueDeferred();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0082() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0082");
        tools.jackson.core.Base64Variant base64Variant0 = tools.jackson.core.Base64Variants.MODIFIED_FOR_URL;
        java.lang.StringBuilder stringBuilder1 = null;
        // The following exception was thrown during execution in test generation
        try {
            base64Variant0.encodeBase64Chunk(stringBuilder1, 10);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(base64Variant0);
    }

    @Test
    public void test0083() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0083");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        boolean boolean6 = filteringParserDelegate5.isExpectedStartObjectToken();
        // The following exception was thrown during execution in test generation
        try {
            long long8 = filteringParserDelegate5.nextLongValue((long) 10);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
    }

    @Test
    public void test0084() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0084");
        int int0 = tools.jackson.core.io.JsonStringEncoder.MAX_BYTE_BUFFER_SIZE;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 32000 + "'", int0 == 32000);
    }

    @Test
    public void test0085() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0085");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.JsonToken jsonToken6 = filteringParserDelegate5.nextToken();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0086() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0086");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        tools.jackson.core.filter.TokenFilter tokenFilter6 = filteringParserDelegate5.getFilter();
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean7 = filteringParserDelegate5.isNaN();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(tokenFilter6);
    }

    @Test
    public void test0087() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0087");
        java.lang.String str0 = tools.jackson.core.util.Separators.DEFAULT_ARRAY_EMPTY_SEPARATOR;
        org.junit.Assert.assertEquals("'" + str0 + "' != '" + " " + "'", str0, " ");
    }

    @Test
    public void test0088() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0088");
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder0 = tools.jackson.core.json.JsonFactory.builderWithJackson2Defaults();
        tools.jackson.core.SerializableString serializableString1 = null;
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder2 = jsonFactoryBuilder0.rootValueSeparator(serializableString1);
        tools.jackson.core.io.OutputDecorator outputDecorator3 = jsonFactoryBuilder2.outputDecorator();
        tools.jackson.core.StreamReadFeature streamReadFeature4 = null;
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder5 = jsonFactoryBuilder2.disable(streamReadFeature4);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(jsonFactoryBuilder0);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder2);
        org.junit.Assert.assertNull(outputDecorator3);
    }

    @Test
    public void test0089() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0089");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        tools.jackson.core.JsonParser jsonParser6 = filteringParserDelegate5.skipChildren();
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.JsonToken jsonToken7 = filteringParserDelegate5.nextValue();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(jsonParser6);
    }

    @Test
    public void test0090() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0090");
        tools.jackson.core.Base64Variant base64Variant0 = tools.jackson.core.Base64Variants.MODIFIED_FOR_URL;
        byte[] byteArray8 = new byte[] { (byte) 10, (byte) -1, (byte) -1, (byte) -1, (byte) -1 };
        int int10 = base64Variant0.encodeBase64Partial(10, (int) 'a', byteArray8, (int) (byte) 1);
        tools.jackson.core.Base64Variant base64Variant13 = new tools.jackson.core.Base64Variant(base64Variant0, "hi!", 0);
        char char14 = base64Variant0.getPaddingChar();
        tools.jackson.core.Base64Variant base64Variant15 = base64Variant0.withPaddingForbidden();
        org.junit.Assert.assertNotNull(base64Variant0);
        org.junit.Assert.assertNotNull(byteArray8);
        org.junit.Assert.assertArrayEquals(byteArray8, new byte[] { (byte) 10, (byte) 65, (byte) 65, (byte) -1, (byte) -1 });
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 3 + "'", int10 == 3);
        org.junit.Assert.assertTrue("'" + char14 + "' != '" + '\000' + "'", char14 == '\000');
        org.junit.Assert.assertNotNull(base64Variant15);
    }

    @Test
    public void test0091() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0091");
        int[] intArray0 = tools.jackson.core.io.CharTypes.getInputCodeLatin1JsNames();
        org.junit.Assert.assertNotNull(intArray0);
    }

    @Test
    public void test0092() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0092");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        tools.jackson.core.filter.TokenFilter tokenFilter6 = filteringParserDelegate5.getFilter();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Number number7 = filteringParserDelegate5.getNumberValue();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(tokenFilter6);
    }

    @Test
    public void test0093() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0093");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        tools.jackson.core.JsonParser jsonParser6 = filteringParserDelegate5.skipChildren();
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean7 = filteringParserDelegate5.hasStringCharacters();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(jsonParser6);
    }

    @Test
    public void test0094() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0094");
        int int1 = tools.jackson.core.io.schubfach.MathUtils.flog2pow10(17);
        org.junit.Assert.assertTrue("'" + int1 + "' != '" + 56 + "'", int1 == 56);
    }

    @Test
    public void test0095() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0095");
        int int0 = tools.jackson.core.util.RecyclerPool.BoundedPoolBase.DEFAULT_CAPACITY;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 100 + "'", int0 == 100);
    }

    @Test
    public void test0096() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0096");
        tools.jackson.core.ObjectReadContext objectReadContext0 = null;
        tools.jackson.core.io.IOContext iOContext1 = null;
        java.io.DataInput dataInput4 = null;
        tools.jackson.core.sym.ByteQuadsCanonicalizer byteQuadsCanonicalizer5 = null;
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.json.UTF8DataInputWithDocLengthJsonParser uTF8DataInputWithDocLengthJsonParser7 = new tools.jackson.core.json.UTF8DataInputWithDocLengthJsonParser(objectReadContext0, iOContext1, (int) (short) 1, (int) '#', dataInput4, byteQuadsCanonicalizer5, (-324));
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0097() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0097");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        boolean boolean6 = filteringParserDelegate5.isExpectedStartObjectToken();
        int int7 = filteringParserDelegate5.currentTokenId();
        boolean boolean8 = filteringParserDelegate5.isExpectedStartObjectToken();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str9 = filteringParserDelegate5.getString();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 0 + "'", int7 == 0);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
    }

    @Test
    public void test0098() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0098");
        long long1 = tools.jackson.core.io.schubfach.MathUtils.g0((-324));
        org.junit.Assert.assertTrue("'" + long1 + "' != '" + 6557778377634271669L + "'", long1 == 6557778377634271669L);
    }

    @Test
    public void test0099() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0099");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        boolean boolean6 = filteringParserDelegate5.isExpectedStartObjectToken();
        int int7 = filteringParserDelegate5.currentTokenId();
        boolean boolean8 = filteringParserDelegate5.isExpectedStartObjectToken();
        tools.jackson.core.JsonParser jsonParser9 = filteringParserDelegate5.skipChildren();
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean10 = filteringParserDelegate5.isClosed();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 0 + "'", int7 == 0);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertNotNull(jsonParser9);
    }

    @Test
    public void test0100() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0100");
        int int0 = tools.jackson.core.util.BufferRecycler.CHAR_NAME_COPY_BUFFER;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 3 + "'", int0 == 3);
    }

    @Test
    public void test0101() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0101");
        int int0 = tools.jackson.core.io.schubfach.DoubleToDecimal.E_MAX;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 309 + "'", int0 == 309);
    }

    @Test
    public void test0102() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0102");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        tools.jackson.core.filter.TokenFilter tokenFilter6 = filteringParserDelegate5.getFilter();
        boolean boolean7 = filteringParserDelegate5.isExpectedStartArrayToken();
        boolean boolean8 = filteringParserDelegate5.hasCurrentToken();
        tools.jackson.core.filter.TokenFilter tokenFilter9 = null;
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext10 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter9);
        tools.jackson.core.JsonToken jsonToken11 = tokenFilterContext10.nextTokenToRead();
        tools.jackson.core.filter.TokenFilter tokenFilter12 = tools.jackson.core.filter.TokenFilter.INCLUDE_ALL;
        tools.jackson.core.filter.TokenFilter tokenFilter13 = tokenFilterContext10.checkValue(tokenFilter12);
        java.math.BigDecimal bigDecimal14 = null;
        boolean boolean15 = tokenFilter12.includeNumber(bigDecimal14);
        java.math.BigInteger bigInteger16 = null;
        boolean boolean17 = tokenFilter12.includeNumber(bigInteger16);
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion18 = tools.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_NON_NULL;
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate20 = new tools.jackson.core.filter.FilteringParserDelegate((tools.jackson.core.JsonParser) filteringParserDelegate5, tokenFilter12, inclusion18, false);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(tokenFilter6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertNotNull(tokenFilterContext10);
        org.junit.Assert.assertNull(jsonToken11);
        org.junit.Assert.assertNotNull(tokenFilter12);
        org.junit.Assert.assertNotNull(tokenFilter13);
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + true + "'", boolean15 == true);
        org.junit.Assert.assertTrue("'" + boolean17 + "' != '" + true + "'", boolean17 == true);
        org.junit.Assert.assertTrue("'" + inclusion18 + "' != '" + tools.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_NON_NULL + "'", inclusion18.equals(tools.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_NON_NULL));
    }

    @Test
    public void test0103() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0103");
        int int0 = tools.jackson.core.io.schubfach.FloatToDecimal.C_TINY;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 8 + "'", int0 == 8);
    }

    @Test
    public void test0104() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0104");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        tools.jackson.core.filter.TokenFilter tokenFilter6 = filteringParserDelegate5.getFilter();
        boolean boolean7 = filteringParserDelegate5.isExpectedStartArrayToken();
        boolean boolean8 = filteringParserDelegate5.hasCurrentToken();
        // The following exception was thrown during execution in test generation
        try {
            filteringParserDelegate5.assignCurrentValue((java.lang.Object) 3);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(tokenFilter6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
    }

    @Test
    public void test0105() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0105");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        boolean boolean6 = filteringParserDelegate5.isExpectedStartObjectToken();
        int int7 = filteringParserDelegate5.currentTokenId();
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.Version version8 = filteringParserDelegate5.version();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 0 + "'", int7 == 0);
    }

    @Test
    public void test0106() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0106");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        boolean boolean6 = filteringParserDelegate5.isExpectedStartObjectToken();
        int int7 = filteringParserDelegate5.currentTokenId();
        boolean boolean8 = filteringParserDelegate5.isExpectedStartObjectToken();
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean10 = filteringParserDelegate5.getValueAsBoolean(true);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 0 + "'", int7 == 0);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
    }

    @Test
    public void test0107() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0107");
        tools.jackson.core.JsonGenerator jsonGenerator0 = null;
        java.lang.Throwable throwable2 = null;
        tools.jackson.core.exc.StreamWriteException streamWriteException3 = new tools.jackson.core.exc.StreamWriteException(jsonGenerator0, "hi!", throwable2);
        tools.jackson.core.json.DupDetector dupDetector4 = null;
        tools.jackson.core.util.SimpleStreamWriteContext simpleStreamWriteContext5 = tools.jackson.core.util.SimpleStreamWriteContext.createRootContext(dupDetector4);
        boolean boolean6 = simpleStreamWriteContext5.writeValue();
        tools.jackson.core.JacksonException jacksonException8 = tools.jackson.core.JacksonException.wrapWithPath((java.lang.Throwable) streamWriteException3, (java.lang.Object) boolean6, "hi!");
        java.lang.String str9 = streamWriteException3.toString();
        java.lang.String str10 = streamWriteException3.toString();
        org.junit.Assert.assertNotNull(simpleStreamWriteContext5);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + true + "'", boolean6 == true);
        org.junit.Assert.assertNotNull(jacksonException8);
        org.junit.Assert.assertEquals("'" + str9 + "' != '" + "tools.jackson.core.exc.StreamWriteException: hi!\n at [No location information] (through reference chain: java.lang.Boolean[\"hi!\"])" + "'", str9, "tools.jackson.core.exc.StreamWriteException: hi!\n at [No location information] (through reference chain: java.lang.Boolean[\"hi!\"])");
        org.junit.Assert.assertEquals("'" + str10 + "' != '" + "tools.jackson.core.exc.StreamWriteException: hi!\n at [No location information] (through reference chain: java.lang.Boolean[\"hi!\"])" + "'", str10, "tools.jackson.core.exc.StreamWriteException: hi!\n at [No location information] (through reference chain: java.lang.Boolean[\"hi!\"])");
    }

    @Test
    public void test0108() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0108");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        // The following exception was thrown during execution in test generation
        try {
            long long7 = filteringParserDelegate5.getValueAsLong((long) (-324));
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0109() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0109");
        int[] intArray0 = tools.jackson.core.io.CharTypes.getInputCodeComment();
        org.junit.Assert.assertNotNull(intArray0);
    }

    @Test
    public void test0110() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0110");
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter1 = new tools.jackson.core.util.MinimalPrettyPrinter("hi!");
        tools.jackson.core.JsonGenerator jsonGenerator2 = null;
        // The following exception was thrown during execution in test generation
        try {
            minimalPrettyPrinter1.writeStartObject(jsonGenerator2);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0111() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0111");
        java.lang.String str0 = tools.jackson.core.util.Separators.DEFAULT_ROOT_VALUE_SEPARATOR;
        org.junit.Assert.assertEquals("'" + str0 + "' != '" + " " + "'", str0, " ");
    }

    @Test
    public void test0112() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0112");
        tools.jackson.core.Base64Variant.PaddingReadBehaviour paddingReadBehaviour0 = tools.jackson.core.Base64Variant.PaddingReadBehaviour.PADDING_FORBIDDEN;
        org.junit.Assert.assertTrue("'" + paddingReadBehaviour0 + "' != '" + tools.jackson.core.Base64Variant.PaddingReadBehaviour.PADDING_FORBIDDEN + "'", paddingReadBehaviour0.equals(tools.jackson.core.Base64Variant.PaddingReadBehaviour.PADDING_FORBIDDEN));
    }

    @Test
    public void test0113() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0113");
        java.lang.String str1 = tools.jackson.core.io.NumberOutput.toString((double) 'a');
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "97.0" + "'", str1, "97.0");
    }

    @Test
    public void test0114() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0114");
        // The following exception was thrown during execution in test generation
        try {
            char char1 = tools.jackson.core.io.CharTypes.hexToChar((-3));
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: -3");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0115() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0115");
        int int0 = tools.jackson.core.io.schubfach.FloatToDecimal.P;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 24 + "'", int0 == 24);
    }

    @Test
    public void test0116() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0116");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        tools.jackson.core.filter.TokenFilter tokenFilter6 = filteringParserDelegate5.getFilter();
        boolean boolean7 = filteringParserDelegate5.isExpectedStartArrayToken();
        // The following exception was thrown during execution in test generation
        try {
            filteringParserDelegate5.close();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(tokenFilter6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
    }

    @Test
    public void test0117() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0117");
        tools.jackson.core.util.BufferRecycler bufferRecycler0 = null;
        tools.jackson.core.util.TextBuffer textBuffer1 = new tools.jackson.core.util.TextBuffer(bufferRecycler0);
        textBuffer1.resetWithString("");
        // The following exception was thrown during execution in test generation
        try {
            textBuffer1.resetWithCopy(" ", 17, (int) (byte) 10);
            org.junit.Assert.fail("Expected exception of type java.lang.StringIndexOutOfBoundsException; message: String index out of range: 27");
        } catch (java.lang.StringIndexOutOfBoundsException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0118() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0118");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        boolean boolean6 = filteringParserDelegate5.isExpectedStartObjectToken();
        int int7 = filteringParserDelegate5.currentTokenId();
        boolean boolean8 = filteringParserDelegate5.isExpectedStartObjectToken();
        tools.jackson.core.JsonParser jsonParser9 = filteringParserDelegate5.skipChildren();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj10 = jsonParser9.getObjectId();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 0 + "'", int7 == 0);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertNotNull(jsonParser9);
    }

    @Test
    public void test0119() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0119");
        int int1 = tools.jackson.core.sym.ByteQuadsCanonicalizer.multiplyByFourFifths((int) (byte) 1);
        org.junit.Assert.assertTrue("'" + int1 + "' != '" + 0 + "'", int1 == 0);
    }

    @Test
    public void test0120() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0120");
        tools.jackson.core.StreamReadConstraints streamReadConstraints0 = null;
        tools.jackson.core.StreamReadConstraints.overrideDefaultStreamReadConstraints(streamReadConstraints0);
    }

    @Test
    public void test0121() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0121");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        boolean boolean6 = filteringParserDelegate5.isExpectedStartObjectToken();
        tools.jackson.core.JsonGenerator jsonGenerator8 = null;
        java.lang.Throwable throwable10 = null;
        tools.jackson.core.exc.StreamWriteException streamWriteException11 = new tools.jackson.core.exc.StreamWriteException(jsonGenerator8, "hi!", throwable10);
        tools.jackson.core.json.DupDetector dupDetector12 = null;
        tools.jackson.core.util.SimpleStreamWriteContext simpleStreamWriteContext13 = tools.jackson.core.util.SimpleStreamWriteContext.createRootContext(dupDetector12);
        boolean boolean14 = simpleStreamWriteContext13.writeValue();
        tools.jackson.core.JacksonException jacksonException16 = tools.jackson.core.JacksonException.wrapWithPath((java.lang.Throwable) streamWriteException11, (java.lang.Object) boolean14, "hi!");
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.exc.StreamReadException streamReadException17 = new tools.jackson.core.exc.StreamReadException((tools.jackson.core.JsonParser) filteringParserDelegate5, " ", (java.lang.Throwable) streamWriteException11);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNotNull(simpleStreamWriteContext13);
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + true + "'", boolean14 == true);
        org.junit.Assert.assertNotNull(jacksonException16);
    }

    @Test
    public void test0122() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0122");
        tools.jackson.core.filter.TokenFilter tokenFilter0 = null;
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext1 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter0);
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext2 = tokenFilterContext1.getParent();
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean3 = tokenFilterContext2.hasCurrentName();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(tokenFilterContext1);
        org.junit.Assert.assertNull(tokenFilterContext2);
    }

    @Test
    public void test0123() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0123");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        boolean boolean6 = filteringParserDelegate5.isExpectedStartObjectToken();
        int int7 = filteringParserDelegate5.currentTokenId();
        // The following exception was thrown during execution in test generation
        try {
            int int8 = filteringParserDelegate5.getTextLength();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 0 + "'", int7 == 0);
    }

    @Test
    public void test0124() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0124");
        tools.jackson.core.json.DupDetector dupDetector2 = null;
        tools.jackson.core.json.JsonReadContext jsonReadContext3 = tools.jackson.core.json.JsonReadContext.createRootContext(1, 0, dupDetector2);
        boolean boolean4 = jsonReadContext3.expectComma();
        tools.jackson.core.json.JsonReadContext jsonReadContext7 = jsonReadContext3.createChildObjectContext(3, (int) (short) 10);
        boolean boolean8 = jsonReadContext3.hasCurrentName();
        org.junit.Assert.assertNotNull(jsonReadContext3);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertNotNull(jsonReadContext7);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
    }

    @Test
    public void test0125() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0125");
        tools.jackson.core.util.BufferRecycler bufferRecycler0 = null;
        tools.jackson.core.util.TextBuffer textBuffer1 = new tools.jackson.core.util.TextBuffer(bufferRecycler0);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str3 = textBuffer1.setCurrentAndReturn(1);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0126() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0126");
        tools.jackson.core.Base64Variant base64Variant0 = tools.jackson.core.Base64Variants.MODIFIED_FOR_URL;
        byte[] byteArray8 = new byte[] { (byte) 10, (byte) -1, (byte) -1, (byte) -1, (byte) -1 };
        int int10 = base64Variant0.encodeBase64Partial(10, (int) 'a', byteArray8, (int) (byte) 1);
        tools.jackson.core.Base64Variant base64Variant13 = new tools.jackson.core.Base64Variant(base64Variant0, "hi!", 0);
        char char14 = base64Variant0.getPaddingChar();
        // The following exception was thrown during execution in test generation
        try {
            byte[] byteArray16 = base64Variant0.decode("tools.jackson.core.exc.StreamWriteException: hi!\n at [No location information] (through reference chain: java.lang.Boolean[\"hi!\"])");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Illegal character '.' (code 0x2e) in base64 content");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(base64Variant0);
        org.junit.Assert.assertNotNull(byteArray8);
        org.junit.Assert.assertArrayEquals(byteArray8, new byte[] { (byte) 10, (byte) 65, (byte) 65, (byte) -1, (byte) -1 });
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 3 + "'", int10 == 3);
        org.junit.Assert.assertTrue("'" + char14 + "' != '" + '\000' + "'", char14 == '\000');
    }

    @Test
    public void test0127() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0127");
        int int0 = tools.jackson.core.json.JsonWriteContext.STATUS_EXPECT_VALUE;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 4 + "'", int0 == 4);
    }

    @Test
    public void test0128() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0128");
        tools.jackson.core.Base64Variant.PaddingReadBehaviour paddingReadBehaviour0 = tools.jackson.core.Base64Variant.PaddingReadBehaviour.PADDING_REQUIRED;
        org.junit.Assert.assertTrue("'" + paddingReadBehaviour0 + "' != '" + tools.jackson.core.Base64Variant.PaddingReadBehaviour.PADDING_REQUIRED + "'", paddingReadBehaviour0.equals(tools.jackson.core.Base64Variant.PaddingReadBehaviour.PADDING_REQUIRED));
    }

    @Test
    public void test0129() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0129");
        tools.jackson.core.JsonToken jsonToken1 = null;
        tools.jackson.core.type.WritableTypeId writableTypeId3 = new tools.jackson.core.type.WritableTypeId((java.lang.Object) '#', jsonToken1, (java.lang.Object) ' ');
        tools.jackson.core.type.WritableTypeId.Inclusion inclusion4 = tools.jackson.core.type.WritableTypeId.Inclusion.PAYLOAD_PROPERTY;
        writableTypeId3.include = inclusion4;
        java.lang.Object obj6 = writableTypeId3.extra;
        org.junit.Assert.assertTrue("'" + inclusion4 + "' != '" + tools.jackson.core.type.WritableTypeId.Inclusion.PAYLOAD_PROPERTY + "'", inclusion4.equals(tools.jackson.core.type.WritableTypeId.Inclusion.PAYLOAD_PROPERTY));
        org.junit.Assert.assertNull(obj6);
    }

    @Test
    public void test0130() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0130");
        double double2 = tools.jackson.core.io.NumberInput.parseAsDouble("100.0", (double) 56);
        org.junit.Assert.assertTrue("'" + double2 + "' != '" + 100.0d + "'", double2 == 100.0d);
    }

    @Test
    public void test0131() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0131");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        tools.jackson.core.filter.TokenFilter tokenFilter6 = filteringParserDelegate5.getFilter();
        boolean boolean7 = filteringParserDelegate5.isExpectedStartArrayToken();
        boolean boolean8 = filteringParserDelegate5.hasCurrentToken();
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean9 = filteringParserDelegate5.hasStringCharacters();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(tokenFilter6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
    }

    @Test
    public void test0132() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0132");
        int int1 = tools.jackson.core.util.VersionUtil.parseVersionPart("\r\n");
        org.junit.Assert.assertTrue("'" + int1 + "' != '" + 0 + "'", int1 == 0);
    }

    @Test
    public void test0133() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0133");
        boolean boolean1 = tools.jackson.core.io.NumberOutput.notFinite((float) (-3));
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
    }

    @Test
    public void test0134() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0134");
        tools.jackson.core.json.DupDetector dupDetector0 = null;
        tools.jackson.core.json.JsonWriteContext jsonWriteContext1 = tools.jackson.core.json.JsonWriteContext.createRootContext(dupDetector0);
        tools.jackson.core.json.JsonWriteContext jsonWriteContext2 = jsonWriteContext1.getParent();
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.json.DupDetector dupDetector3 = jsonWriteContext2.getDupDetector();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(jsonWriteContext1);
        org.junit.Assert.assertNull(jsonWriteContext2);
    }

    @Test
    public void test0135() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0135");
        tools.jackson.core.util.DefaultPrettyPrinter defaultPrettyPrinter0 = null;
        tools.jackson.core.util.Separators separators1 = null;
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.util.DefaultPrettyPrinter defaultPrettyPrinter2 = new tools.jackson.core.util.DefaultPrettyPrinter(defaultPrettyPrinter0, separators1);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0136() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0136");
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter1 = new tools.jackson.core.util.MinimalPrettyPrinter("hi!");
        tools.jackson.core.JsonGenerator jsonGenerator2 = null;
        // The following exception was thrown during execution in test generation
        try {
            minimalPrettyPrinter1.writeEndObject(jsonGenerator2, 1);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0137() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0137");
        char[] charArray5 = new char[] { '#', 'a', 'a', ' ', '4' };
        // The following exception was thrown during execution in test generation
        try {
            long long8 = tools.jackson.core.io.NumberInput.parseLong(charArray5, (int) ' ', (int) (byte) 0);
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: 22");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(charArray5);
        org.junit.Assert.assertArrayEquals(charArray5, new char[] { '#', 'a', 'a', ' ', '4' });
    }

    @Test
    public void test0138() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0138");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        boolean boolean6 = filteringParserDelegate5.isExpectedStartObjectToken();
        int int7 = filteringParserDelegate5.currentTokenId();
        boolean boolean8 = filteringParserDelegate5.isExpectedStartObjectToken();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Boolean boolean9 = filteringParserDelegate5.nextBooleanValue();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 0 + "'", int7 == 0);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
    }

    @Test
    public void test0139() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0139");
        java.io.IOException iOException0 = null;
        tools.jackson.core.io.IOContext iOContext1 = null;
        java.io.InputStream inputStream2 = null;
        byte[] byteArray6 = new byte[] { (byte) 100, (byte) 1 };
        tools.jackson.core.io.UTF32Reader uTF32Reader10 = new tools.jackson.core.io.UTF32Reader(iOContext1, inputStream2, false, byteArray6, (int) (short) 0, (-1), false);
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.exc.JacksonIOException jacksonIOException11 = tools.jackson.core.exc.JacksonIOException.construct(iOException0, (java.io.Closeable) inputStream2);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray6);
        org.junit.Assert.assertArrayEquals(byteArray6, new byte[] { (byte) 100, (byte) 1 });
    }

    @Test
    public void test0140() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0140");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        tools.jackson.core.filter.TokenFilter tokenFilter6 = filteringParserDelegate5.getFilter();
        boolean boolean7 = filteringParserDelegate5.isExpectedStartArrayToken();
        boolean boolean8 = filteringParserDelegate5.hasCurrentToken();
        tools.jackson.core.TokenStreamContext tokenStreamContext9 = filteringParserDelegate5.streamReadContext();
        tools.jackson.core.sym.PropertyNameMatcher propertyNameMatcher10 = null;
        // The following exception was thrown during execution in test generation
        try {
            int int11 = filteringParserDelegate5.nextNameMatch(propertyNameMatcher10);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(tokenFilter6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertNotNull(tokenStreamContext9);
    }

    @Test
    public void test0141() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0141");
        int int0 = tools.jackson.core.io.schubfach.DoubleToDecimal.Q_MAX;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 971 + "'", int0 == 971);
    }

    @Test
    public void test0142() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0142");
        int int1 = tools.jackson.core.util.VersionUtil.parseVersionPart("UNKNOWN");
        org.junit.Assert.assertTrue("'" + int1 + "' != '" + 0 + "'", int1 == 0);
    }

    @Test
    public void test0143() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0143");
        tools.jackson.core.json.DupDetector dupDetector2 = null;
        tools.jackson.core.json.JsonReadContext jsonReadContext3 = tools.jackson.core.json.JsonReadContext.createRootContext(1, 0, dupDetector2);
        tools.jackson.core.json.JsonReadContext jsonReadContext4 = jsonReadContext3.getParent();
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.json.JsonReadContext jsonReadContext5 = jsonReadContext4.clearAndGetParent();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(jsonReadContext3);
        org.junit.Assert.assertNull(jsonReadContext4);
    }

    @Test
    public void test0144() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0144");
        tools.jackson.core.io.IOContext iOContext0 = null;
        java.io.InputStream inputStream1 = null;
        byte[] byteArray5 = new byte[] { (byte) 100, (byte) 1 };
        tools.jackson.core.io.UTF32Reader uTF32Reader9 = new tools.jackson.core.io.UTF32Reader(iOContext0, inputStream1, false, byteArray5, (int) (short) 0, (-1), false);
        char[] charArray13 = new char[] { ' ', ' ', '4' };
        boolean boolean17 = tools.jackson.core.io.NumberInput.inLongRange(charArray13, (-324), (int) '4', false);
        // The following exception was thrown during execution in test generation
        try {
            int int20 = uTF32Reader9.read(charArray13, 32000, (int) (short) 0);
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: read(buf,32000,0), cbuf[3]");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray5);
        org.junit.Assert.assertArrayEquals(byteArray5, new byte[] { (byte) 100, (byte) 1 });
        org.junit.Assert.assertNotNull(charArray13);
        org.junit.Assert.assertArrayEquals(charArray13, new char[] { ' ', ' ', '4' });
        org.junit.Assert.assertTrue("'" + boolean17 + "' != '" + false + "'", boolean17 == false);
    }

    @Test
    public void test0145() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0145");
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder0 = tools.jackson.core.json.JsonFactory.builder();
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder2 = jsonFactoryBuilder0.highestNonEscapedChar((-324));
        tools.jackson.core.json.JsonWriteFeature jsonWriteFeature3 = null;
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder5 = jsonFactoryBuilder2.configure(jsonWriteFeature3, true);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(jsonFactoryBuilder0);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder2);
    }

    @Test
    public void test0146() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0146");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        boolean boolean6 = filteringParserDelegate5.isExpectedStartObjectToken();
        int int7 = filteringParserDelegate5.currentTokenId();
        tools.jackson.core.sym.PropertyNameMatcher propertyNameMatcher8 = null;
        // The following exception was thrown during execution in test generation
        try {
            int int9 = filteringParserDelegate5.nextNameMatch(propertyNameMatcher8);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 0 + "'", int7 == 0);
    }

    @Test
    public void test0147() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0147");
        char[] charArray0 = null;
        // The following exception was thrown during execution in test generation
        try {
            int int3 = tools.jackson.core.io.NumberInput.parseInt(charArray0, 32000, (int) (byte) 10);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0148() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0148");
        tools.jackson.core.util.BufferRecyclers bufferRecyclers0 = new tools.jackson.core.util.BufferRecyclers();
    }

    @Test
    public void test0149() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0149");
        tools.jackson.core.json.DupDetector dupDetector0 = null;
        tools.jackson.core.json.JsonWriteContext jsonWriteContext1 = tools.jackson.core.json.JsonWriteContext.createRootContext(dupDetector0);
        tools.jackson.core.json.JsonWriteContext jsonWriteContext2 = jsonWriteContext1.getParent();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj3 = jsonWriteContext2.currentValue();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(jsonWriteContext1);
        org.junit.Assert.assertNull(jsonWriteContext2);
    }

    @Test
    public void test0150() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0150");
        int int0 = tools.jackson.core.io.schubfach.FloatToDecimal.Q_MIN;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + (-149) + "'", int0 == (-149));
    }

    @Test
    public void test0151() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0151");
        tools.jackson.core.Base64Variant base64Variant0 = tools.jackson.core.Base64Variants.MODIFIED_FOR_URL;
        byte[] byteArray8 = new byte[] { (byte) 10, (byte) -1, (byte) -1, (byte) -1, (byte) -1 };
        int int10 = base64Variant0.encodeBase64Partial(10, (int) 'a', byteArray8, (int) (byte) 1);
        tools.jackson.core.Base64Variant base64Variant13 = new tools.jackson.core.Base64Variant(base64Variant0, "hi!", 0);
        tools.jackson.core.Base64Variant base64Variant14 = base64Variant0.withPaddingForbidden();
        boolean boolean15 = base64Variant0.acceptsPaddingOnRead();
        java.lang.StringBuilder stringBuilder16 = null;
        // The following exception was thrown during execution in test generation
        try {
            base64Variant0.encodeBase64Chunk(stringBuilder16, 309);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(base64Variant0);
        org.junit.Assert.assertNotNull(byteArray8);
        org.junit.Assert.assertArrayEquals(byteArray8, new byte[] { (byte) 10, (byte) 65, (byte) 65, (byte) -1, (byte) -1 });
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 3 + "'", int10 == 3);
        org.junit.Assert.assertNotNull(base64Variant14);
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
    }

    @Test
    public void test0152() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0152");
        java.io.DataOutput dataOutput0 = null;
        tools.jackson.core.io.DataOutputAsStream dataOutputAsStream1 = new tools.jackson.core.io.DataOutputAsStream(dataOutput0);
        // The following exception was thrown during execution in test generation
        try {
            dataOutputAsStream1.write((int) (short) 10);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0153() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0153");
        tools.jackson.core.Version version0 = tools.jackson.core.Version.unknownVersion();
        int int1 = version0.getPatchLevel();
        java.lang.String str2 = version0.toString();
        java.lang.String str3 = version0.getArtifactId();
        java.lang.String str4 = version0.toFullString();
        org.junit.Assert.assertNotNull(version0);
        org.junit.Assert.assertTrue("'" + int1 + "' != '" + 0 + "'", int1 == 0);
        org.junit.Assert.assertEquals("'" + str2 + "' != '" + "0.0.0" + "'", str2, "0.0.0");
        org.junit.Assert.assertEquals("'" + str3 + "' != '" + "" + "'", str3, "");
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "//0.0.0" + "'", str4, "//0.0.0");
    }

    @Test
    public void test0154() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0154");
        byte byte0 = tools.jackson.core.json.ByteSourceJsonBootstrapper.UTF8_BOM_1;
        org.junit.Assert.assertTrue("'" + byte0 + "' != '" + (byte) -17 + "'", byte0 == (byte) -17);
    }

    @Test
    public void test0155() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0155");
        char[] charArray4 = new char[] { ' ', ' ', '4' };
        boolean boolean8 = tools.jackson.core.io.NumberInput.inLongRange(charArray4, (-324), (int) '4', false);
        boolean boolean12 = tools.jackson.core.io.NumberInput.inLongRange(charArray4, 55296, (int) (short) 100, false);
        // The following exception was thrown during execution in test generation
        try {
            int int14 = tools.jackson.core.io.NumberOutput.outputInt((-45), charArray4, (-149));
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: -149");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(charArray4);
        org.junit.Assert.assertArrayEquals(charArray4, new char[] { ' ', ' ', '4' });
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
    }

    @Test
    public void test0156() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0156");
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder0 = tools.jackson.core.json.JsonFactory.builder();
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder2 = jsonFactoryBuilder0.highestNonEscapedChar((-324));
        tools.jackson.core.StreamWriteFeature streamWriteFeature3 = null;
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder4 = jsonFactoryBuilder2.disable(streamWriteFeature3);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(jsonFactoryBuilder0);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder2);
    }

    @Test
    public void test0157() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0157");
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter1 = new tools.jackson.core.util.MinimalPrettyPrinter("hi!");
        tools.jackson.core.JsonGenerator jsonGenerator2 = null;
        // The following exception was thrown during execution in test generation
        try {
            minimalPrettyPrinter1.writeEndArray(jsonGenerator2, 8);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0158() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0158");
        tools.jackson.core.filter.TokenFilter tokenFilter0 = null;
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext1 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter0);
        tools.jackson.core.JsonToken jsonToken2 = tokenFilterContext1.nextTokenToRead();
        tools.jackson.core.filter.TokenFilter tokenFilter3 = tools.jackson.core.filter.TokenFilter.INCLUDE_ALL;
        tools.jackson.core.filter.TokenFilter tokenFilter4 = tokenFilterContext1.checkValue(tokenFilter3);
        java.math.BigDecimal bigDecimal5 = null;
        boolean boolean6 = tokenFilter3.includeNumber(bigDecimal5);
        tools.jackson.core.filter.TokenFilter tokenFilter7 = tokenFilter3.filterStartObject();
        java.math.BigInteger bigInteger8 = null;
        boolean boolean9 = tokenFilter7.includeNumber(bigInteger8);
        tools.jackson.core.JsonParser jsonParser10 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter11 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion12 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate15 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser10, tokenFilter11, inclusion12, true, true);
        tools.jackson.core.filter.TokenFilter tokenFilter16 = filteringParserDelegate15.getFilter();
        boolean boolean17 = filteringParserDelegate15.isExpectedStartArrayToken();
        tools.jackson.core.filter.TokenFilter tokenFilter18 = filteringParserDelegate15.getFilter();
        boolean boolean19 = tokenFilter7.includeValue((tools.jackson.core.JsonParser) filteringParserDelegate15);
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.ObjectReadContext objectReadContext20 = filteringParserDelegate15.objectReadContext();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(tokenFilterContext1);
        org.junit.Assert.assertNull(jsonToken2);
        org.junit.Assert.assertNotNull(tokenFilter3);
        org.junit.Assert.assertNotNull(tokenFilter4);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + true + "'", boolean6 == true);
        org.junit.Assert.assertNotNull(tokenFilter7);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + true + "'", boolean9 == true);
        org.junit.Assert.assertNull(tokenFilter16);
        org.junit.Assert.assertTrue("'" + boolean17 + "' != '" + false + "'", boolean17 == false);
        org.junit.Assert.assertNull(tokenFilter18);
        org.junit.Assert.assertTrue("'" + boolean19 + "' != '" + true + "'", boolean19 == true);
    }

    @Test
    public void test0159() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0159");
        int int0 = tools.jackson.core.TokenStreamContext.TYPE_ARRAY;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 1 + "'", int0 == 1);
    }

    @Test
    public void test0160() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0160");
        tools.jackson.core.util.BufferRecycler bufferRecycler0 = null;
        tools.jackson.core.util.TextBuffer textBuffer1 = new tools.jackson.core.util.TextBuffer(bufferRecycler0);
        textBuffer1.resetWithString("");
        int int4 = textBuffer1.getCurrentSegmentSize();
        // The following exception was thrown during execution in test generation
        try {
            double double6 = textBuffer1.contentsAsDouble(false);
            org.junit.Assert.fail("Expected exception of type java.lang.NumberFormatException; message: empty String");
        } catch (java.lang.NumberFormatException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 0 + "'", int4 == 0);
    }

    @Test
    public void test0161() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0161");
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder0 = tools.jackson.core.json.JsonFactory.builder();
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder2 = jsonFactoryBuilder0.highestNonEscapedChar((-324));
        tools.jackson.core.TokenStreamFactory.Feature feature3 = null;
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder4 = jsonFactoryBuilder0.disable(feature3);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(jsonFactoryBuilder0);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder2);
    }

    @Test
    public void test0162() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0162");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        boolean boolean6 = filteringParserDelegate5.isExpectedStartObjectToken();
        int int7 = filteringParserDelegate5.currentTokenId();
        tools.jackson.core.JsonToken jsonToken8 = null;
        boolean boolean9 = filteringParserDelegate5.hasToken(jsonToken8);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Number number10 = filteringParserDelegate5.getNumberValueExact();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 0 + "'", int7 == 0);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + true + "'", boolean9 == true);
    }

    @Test
    public void test0163() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0163");
        int int0 = tools.jackson.core.util.BufferRecycler.BYTE_WRITE_CONCAT_BUFFER;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 2 + "'", int0 == 2);
    }

    @Test
    public void test0164() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0164");
        int int0 = tools.jackson.core.sym.CharsToNameCanonicalizer.MAX_ENTRIES_FOR_REUSE;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 12000 + "'", int0 == 12000);
    }

    @Test
    public void test0165() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0165");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        tools.jackson.core.filter.TokenFilter tokenFilter6 = filteringParserDelegate5.getFilter();
        boolean boolean7 = filteringParserDelegate5.isExpectedStartArrayToken();
        boolean boolean8 = filteringParserDelegate5.hasCurrentToken();
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.async.NonBlockingInputFeeder nonBlockingInputFeeder9 = filteringParserDelegate5.nonBlockingInputFeeder();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(tokenFilter6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
    }

    @Test
    public void test0166() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0166");
        tools.jackson.core.ObjectWriteContext objectWriteContext0 = null;
        tools.jackson.core.io.IOContext iOContext1 = null;
        tools.jackson.core.util.BufferRecycler bufferRecycler4 = null;
        tools.jackson.core.util.ByteArrayBuilder byteArrayBuilder5 = new tools.jackson.core.util.ByteArrayBuilder(bufferRecycler4);
        byte[] byteArray7 = byteArrayBuilder5.completeAndCoalesce(10);
        byteArrayBuilder5.close();
        tools.jackson.core.SerializableString serializableString9 = null;
        tools.jackson.core.io.CharacterEscapes characterEscapes10 = null;
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter12 = new tools.jackson.core.util.MinimalPrettyPrinter("hi!");
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.json.UTF8JsonGenerator uTF8JsonGenerator15 = new tools.jackson.core.json.UTF8JsonGenerator(objectWriteContext0, iOContext1, 4, (int) (byte) -1, (java.io.OutputStream) byteArrayBuilder5, serializableString9, characterEscapes10, (tools.jackson.core.PrettyPrinter) minimalPrettyPrinter12, (int) 'B', 'B');
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray7);
        org.junit.Assert.assertArrayEquals(byteArray7, new byte[] { (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0 });
    }

    @Test
    public void test0167() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0167");
        int int0 = tools.jackson.core.sym.PropertyNameMatcher.MATCH_END_OBJECT;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + (-1) + "'", int0 == (-1));
    }

    @Test
    public void test0168() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0168");
        tools.jackson.core.JsonGenerator jsonGenerator0 = null;
        tools.jackson.core.JsonGenerator jsonGenerator2 = null;
        java.lang.Throwable throwable4 = null;
        tools.jackson.core.exc.StreamWriteException streamWriteException5 = new tools.jackson.core.exc.StreamWriteException(jsonGenerator2, "hi!", throwable4);
        tools.jackson.core.JsonGenerator jsonGenerator6 = null;
        tools.jackson.core.exc.StreamWriteException streamWriteException7 = streamWriteException5.withGenerator(jsonGenerator6);
        tools.jackson.core.exc.StreamWriteException streamWriteException8 = new tools.jackson.core.exc.StreamWriteException(jsonGenerator0, "0.0.0", (java.lang.Throwable) streamWriteException7);
        java.lang.String str9 = streamWriteException8.getOriginalMessage();
        org.junit.Assert.assertNotNull(streamWriteException7);
        org.junit.Assert.assertEquals("'" + str9 + "' != '" + "0.0.0" + "'", str9, "0.0.0");
    }

    @Test
    public void test0169() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0169");
        tools.jackson.core.json.DupDetector dupDetector0 = null;
        tools.jackson.core.json.JsonWriteContext jsonWriteContext1 = tools.jackson.core.json.JsonWriteContext.createRootContext(dupDetector0);
        tools.jackson.core.json.JsonWriteContext jsonWriteContext2 = jsonWriteContext1.getParent();
        // The following exception was thrown during execution in test generation
        try {
            int int3 = jsonWriteContext2.writeValue();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(jsonWriteContext1);
        org.junit.Assert.assertNull(jsonWriteContext2);
    }

    @Test
    public void test0170() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0170");
        tools.jackson.core.ObjectReadContext objectReadContext0 = null;
        tools.jackson.core.io.IOContext iOContext1 = null;
        tools.jackson.core.io.IOContext iOContext4 = null;
        java.io.InputStream inputStream5 = null;
        byte[] byteArray9 = new byte[] { (byte) 100, (byte) 1 };
        tools.jackson.core.io.UTF32Reader uTF32Reader13 = new tools.jackson.core.io.UTF32Reader(iOContext4, inputStream5, false, byteArray9, (int) (short) 0, (-1), false);
        tools.jackson.core.sym.CharsToNameCanonicalizer charsToNameCanonicalizer14 = null;
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.json.ReaderBasedJsonParser readerBasedJsonParser15 = new tools.jackson.core.json.ReaderBasedJsonParser(objectReadContext0, iOContext1, 0, 5, (java.io.Reader) uTF32Reader13, charsToNameCanonicalizer14);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray9);
        org.junit.Assert.assertArrayEquals(byteArray9, new byte[] { (byte) 100, (byte) 1 });
    }

    @Test
    public void test0171() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0171");
        tools.jackson.core.filter.TokenFilter tokenFilter0 = null;
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext1 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter0);
        java.lang.String str2 = tokenFilterContext1.currentName();
        org.junit.Assert.assertNotNull(tokenFilterContext1);
        org.junit.Assert.assertNull(str2);
    }

    @Test
    public void test0172() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0172");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        tools.jackson.core.JsonParser jsonParser6 = filteringParserDelegate5.skipChildren();
        tools.jackson.core.json.DupDetector dupDetector8 = null;
        tools.jackson.core.util.SimpleStreamWriteContext simpleStreamWriteContext9 = tools.jackson.core.util.SimpleStreamWriteContext.createRootContext(dupDetector8);
        tools.jackson.core.io.ContentReference contentReference10 = null;
        tools.jackson.core.TokenStreamLocation tokenStreamLocation11 = simpleStreamWriteContext9.startLocation(contentReference10);
        java.lang.String str12 = tokenStreamLocation11.sourceDescription();
        tools.jackson.core.JsonGenerator jsonGenerator13 = null;
        java.lang.Throwable throwable15 = null;
        tools.jackson.core.exc.StreamWriteException streamWriteException16 = new tools.jackson.core.exc.StreamWriteException(jsonGenerator13, "hi!", throwable15);
        tools.jackson.core.JsonGenerator jsonGenerator17 = streamWriteException16.processor();
        tools.jackson.core.exc.StreamReadException streamReadException18 = new tools.jackson.core.exc.StreamReadException((tools.jackson.core.JsonParser) filteringParserDelegate5, "root", tokenStreamLocation11, (java.lang.Throwable) streamWriteException16);
        // The following exception was thrown during execution in test generation
        try {
            java.math.BigDecimal bigDecimal19 = filteringParserDelegate5.getDecimalValue();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(jsonParser6);
        org.junit.Assert.assertNotNull(simpleStreamWriteContext9);
        org.junit.Assert.assertNotNull(tokenStreamLocation11);
        org.junit.Assert.assertEquals("'" + str12 + "' != '" + "UNKNOWN" + "'", str12, "UNKNOWN");
        org.junit.Assert.assertNull(jsonGenerator17);
    }

    @Test
    public void test0173() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0173");
        int int0 = tools.jackson.core.io.JsonStringEncoder.MAX_CHAR_BUFFER_SIZE;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 32000 + "'", int0 == 32000);
    }

    @Test
    public void test0174() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0174");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        tools.jackson.core.filter.TokenFilter tokenFilter6 = filteringParserDelegate5.getFilter();
        boolean boolean7 = filteringParserDelegate5.isExpectedStartArrayToken();
        boolean boolean8 = filteringParserDelegate5.hasCurrentToken();
        tools.jackson.core.json.DupDetector dupDetector9 = tools.jackson.core.json.DupDetector.rootDetector((tools.jackson.core.JsonParser) filteringParserDelegate5);
        boolean boolean10 = filteringParserDelegate5.isExpectedStartObjectToken();
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.async.NonBlockingInputFeeder nonBlockingInputFeeder11 = filteringParserDelegate5.nonBlockingInputFeeder();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(tokenFilter6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertNotNull(dupDetector9);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
    }

    @Test
    public void test0175() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0175");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        tools.jackson.core.filter.TokenFilter tokenFilter6 = filteringParserDelegate5.getFilter();
        boolean boolean7 = filteringParserDelegate5.isExpectedStartArrayToken();
        boolean boolean8 = filteringParserDelegate5.hasCurrentToken();
        tools.jackson.core.json.DupDetector dupDetector9 = tools.jackson.core.json.DupDetector.rootDetector((tools.jackson.core.JsonParser) filteringParserDelegate5);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str10 = filteringParserDelegate5.nextName();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(tokenFilter6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertNotNull(dupDetector9);
    }

    @Test
    public void test0176() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0176");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        tools.jackson.core.filter.TokenFilter tokenFilter6 = filteringParserDelegate5.getFilter();
        boolean boolean7 = filteringParserDelegate5.isExpectedStartArrayToken();
        boolean boolean8 = filteringParserDelegate5.hasCurrentToken();
        tools.jackson.core.TokenStreamContext tokenStreamContext9 = filteringParserDelegate5.streamReadContext();
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.JsonParser.NumberTypeFP numberTypeFP10 = filteringParserDelegate5.getNumberTypeFP();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(tokenFilter6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertNotNull(tokenStreamContext9);
    }

    @Test
    public void test0177() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0177");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        tools.jackson.core.filter.TokenFilter tokenFilter6 = filteringParserDelegate5.getFilter();
        boolean boolean7 = filteringParserDelegate5.isExpectedStartArrayToken();
        boolean boolean8 = filteringParserDelegate5.hasCurrentToken();
        // The following exception was thrown during execution in test generation
        try {
            long long10 = filteringParserDelegate5.getValueAsLong(0L);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(tokenFilter6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
    }

    @Test
    public void test0178() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0178");
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder0 = tools.jackson.core.json.JsonFactory.builder();
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder2 = jsonFactoryBuilder0.highestNonEscapedChar((-324));
        tools.jackson.core.StreamReadFeature streamReadFeature3 = null;
        tools.jackson.core.StreamReadFeature streamReadFeature4 = null;
        tools.jackson.core.StreamReadFeature[] streamReadFeatureArray5 = new tools.jackson.core.StreamReadFeature[] { streamReadFeature4 };
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder6 = jsonFactoryBuilder0.disable(streamReadFeature3, streamReadFeatureArray5);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(jsonFactoryBuilder0);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder2);
        org.junit.Assert.assertNotNull(streamReadFeatureArray5);
        org.junit.Assert.assertArrayEquals(streamReadFeatureArray5, new tools.jackson.core.StreamReadFeature[] { null });
    }

    @Test
    public void test0179() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0179");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        boolean boolean6 = filteringParserDelegate5.isExpectedStartObjectToken();
        int int7 = filteringParserDelegate5.currentTokenId();
        boolean boolean8 = filteringParserDelegate5.isExpectedStartObjectToken();
        tools.jackson.core.JsonParser jsonParser9 = filteringParserDelegate5.skipChildren();
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.JsonToken jsonToken10 = filteringParserDelegate5.nextValue();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 0 + "'", int7 == 0);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertNotNull(jsonParser9);
    }

    @Test
    public void test0180() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0180");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        tools.jackson.core.filter.TokenFilter tokenFilter6 = filteringParserDelegate5.getFilter();
        boolean boolean7 = filteringParserDelegate5.isExpectedStartArrayToken();
        boolean boolean8 = filteringParserDelegate5.hasCurrentToken();
        tools.jackson.core.json.DupDetector dupDetector9 = tools.jackson.core.json.DupDetector.rootDetector((tools.jackson.core.JsonParser) filteringParserDelegate5);
        // The following exception was thrown during execution in test generation
        try {
            byte[] byteArray10 = filteringParserDelegate5.getBinaryValue();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(tokenFilter6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertNotNull(dupDetector9);
    }

    @Test
    public void test0181() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0181");
        tools.jackson.core.filter.TokenFilter tokenFilter0 = null;
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext1 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter0);
        tools.jackson.core.JsonToken jsonToken2 = tokenFilterContext1.nextTokenToRead();
        tools.jackson.core.filter.TokenFilter tokenFilter3 = tools.jackson.core.filter.TokenFilter.INCLUDE_ALL;
        tools.jackson.core.filter.TokenFilter tokenFilter4 = tokenFilterContext1.checkValue(tokenFilter3);
        java.math.BigDecimal bigDecimal5 = null;
        boolean boolean6 = tokenFilter3.includeNumber(bigDecimal5);
        tools.jackson.core.filter.TokenFilter tokenFilter7 = tokenFilter3.filterStartObject();
        java.math.BigInteger bigInteger8 = null;
        boolean boolean9 = tokenFilter7.includeNumber(bigInteger8);
        boolean boolean11 = tokenFilter7.includeEmptyArray(false);
        org.junit.Assert.assertNotNull(tokenFilterContext1);
        org.junit.Assert.assertNull(jsonToken2);
        org.junit.Assert.assertNotNull(tokenFilter3);
        org.junit.Assert.assertNotNull(tokenFilter4);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + true + "'", boolean6 == true);
        org.junit.Assert.assertNotNull(tokenFilter7);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + true + "'", boolean9 == true);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
    }

    @Test
    public void test0182() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0182");
        tools.jackson.core.io.IOContext iOContext0 = null;
        java.io.InputStream inputStream1 = null;
        byte[] byteArray5 = new byte[] { (byte) 100, (byte) 1 };
        tools.jackson.core.io.UTF32Reader uTF32Reader9 = new tools.jackson.core.io.UTF32Reader(iOContext0, inputStream1, false, byteArray5, (int) (short) 0, (-1), false);
        // The following exception was thrown during execution in test generation
        try {
            uTF32Reader9.mark((int) (byte) 0);
            org.junit.Assert.fail("Expected exception of type java.io.IOException; message: mark() not supported");
        } catch (java.io.IOException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray5);
        org.junit.Assert.assertArrayEquals(byteArray5, new byte[] { (byte) 100, (byte) 1 });
    }

    @Test
    public void test0183() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0183");
        int int0 = tools.jackson.core.ErrorReportConfiguration.DEFAULT_MAX_RAW_CONTENT_LENGTH;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 500 + "'", int0 == 500);
    }

    @Test
    public void test0184() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0184");
        int int0 = tools.jackson.core.io.schubfach.DoubleToDecimal.Q_MIN;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + (-1074) + "'", int0 == (-1074));
    }

    @Test
    public void test0185() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0185");
        tools.jackson.core.JsonToken jsonToken2 = null;
        tools.jackson.core.type.WritableTypeId writableTypeId4 = new tools.jackson.core.type.WritableTypeId((java.lang.Object) '#', jsonToken2, (java.lang.Object) ' ');
        tools.jackson.core.type.WritableTypeId.Inclusion inclusion5 = tools.jackson.core.type.WritableTypeId.Inclusion.PAYLOAD_PROPERTY;
        writableTypeId4.include = inclusion5;
        tools.jackson.core.JacksonException.Reference reference8 = new tools.jackson.core.JacksonException.Reference((java.lang.Object) writableTypeId4, 256);
        tools.jackson.core.ErrorReportConfiguration errorReportConfiguration11 = null;
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.io.ContentReference contentReference12 = tools.jackson.core.io.ContentReference.construct(false, (java.lang.Object) writableTypeId4, (int) 'a', 8, errorReportConfiguration11);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + inclusion5 + "' != '" + tools.jackson.core.type.WritableTypeId.Inclusion.PAYLOAD_PROPERTY + "'", inclusion5.equals(tools.jackson.core.type.WritableTypeId.Inclusion.PAYLOAD_PROPERTY));
    }

    @Test
    public void test0186() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0186");
        long long0 = tools.jackson.core.io.schubfach.DoubleToDecimal.C_TINY;
        org.junit.Assert.assertTrue("'" + long0 + "' != '" + 3L + "'", long0 == 3L);
    }

    @Test
    public void test0187() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0187");
        int int0 = tools.jackson.core.StreamReadConstraints.DEFAULT_MAX_STRING_LEN;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 100000000 + "'", int0 == 100000000);
    }

    @Test
    public void test0188() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0188");
        tools.jackson.core.util.BufferRecycler bufferRecycler0 = null;
        tools.jackson.core.util.ByteArrayBuilder byteArrayBuilder1 = new tools.jackson.core.util.ByteArrayBuilder(bufferRecycler0);
        byte[] byteArray3 = byteArrayBuilder1.completeAndCoalesce(10);
        byte[] byteArray4 = byteArrayBuilder1.toByteArray();
        byteArrayBuilder1.release();
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertArrayEquals(byteArray3, new byte[] { (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0 });
        org.junit.Assert.assertNotNull(byteArray4);
        org.junit.Assert.assertArrayEquals(byteArray4, new byte[] { (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0 });
    }

    @Test
    public void test0189() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0189");
        java.lang.StringBuilder stringBuilder0 = null;
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.io.CharTypes.appendQuoted(stringBuilder0, "\r\n");
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0190() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0190");
        tools.jackson.core.io.IOContext iOContext0 = null;
        java.io.InputStream inputStream1 = null;
        byte[] byteArray5 = new byte[] { (byte) 100, (byte) 1 };
        tools.jackson.core.io.UTF32Reader uTF32Reader9 = new tools.jackson.core.io.UTF32Reader(iOContext0, inputStream1, false, byteArray5, (int) (short) 0, (-1), false);
        // The following exception was thrown during execution in test generation
        try {
            long long11 = uTF32Reader9.skip(100L);
            org.junit.Assert.fail("Expected exception of type java.io.CharConversionException; message: Unexpected EOF in the middle of a 4-byte UTF-32 char: got -1, needed 4, at char #0, byte #-1)");
        } catch (java.io.CharConversionException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray5);
        org.junit.Assert.assertArrayEquals(byteArray5, new byte[] { (byte) 100, (byte) 1 });
    }

    @Test
    public void test0191() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0191");
        java.lang.String str1 = tools.jackson.core.io.NumberOutput.toString((float) 2);
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "2.0" + "'", str1, "2.0");
    }

    @Test
    public void test0192() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0192");
        tools.jackson.core.json.DupDetector dupDetector0 = null;
        tools.jackson.core.util.SimpleStreamWriteContext simpleStreamWriteContext1 = tools.jackson.core.util.SimpleStreamWriteContext.createRootContext(dupDetector0);
        tools.jackson.core.json.DupDetector dupDetector2 = simpleStreamWriteContext1.getDupDetector();
        boolean boolean3 = simpleStreamWriteContext1.inObject();
        int int4 = simpleStreamWriteContext1.getEntryCount();
        org.junit.Assert.assertNotNull(simpleStreamWriteContext1);
        org.junit.Assert.assertNull(dupDetector2);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 0 + "'", int4 == 0);
    }

    @Test
    public void test0193() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0193");
        tools.jackson.core.util.DefaultIndenter defaultIndenter0 = new tools.jackson.core.util.DefaultIndenter();
        java.lang.String str1 = defaultIndenter0.getEol();
        tools.jackson.core.util.DefaultIndenter defaultIndenter3 = defaultIndenter0.withLinefeed("AAAAAAAAAAAAAA");
        tools.jackson.core.JsonGenerator jsonGenerator4 = null;
        // The following exception was thrown during execution in test generation
        try {
            defaultIndenter3.writeIndentation(jsonGenerator4, 0);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "\r\n" + "'", str1, "\r\n");
        org.junit.Assert.assertNotNull(defaultIndenter3);
    }

    @Test
    public void test0194() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0194");
        int int1 = tools.jackson.core.io.JsonStringEncoder._initialCharBufSize(4);
        org.junit.Assert.assertTrue("'" + int1 + "' != '" + 16 + "'", int1 == 16);
    }

    @Test
    public void test0195() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0195");
        java.lang.String str1 = tools.jackson.core.io.NumberOutput.toString(0.0f);
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "0.0" + "'", str1, "0.0");
    }

    @Test
    public void test0196() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0196");
        long long2 = tools.jackson.core.io.schubfach.DoubleToDecimal.multiplyHigh(100L, (-1L));
        org.junit.Assert.assertTrue("'" + long2 + "' != '" + (-1L) + "'", long2 == (-1L));
    }

    @Test
    public void test0197() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0197");
        tools.jackson.core.util.DefaultPrettyPrinter.FixedSpaceIndenter fixedSpaceIndenter0 = new tools.jackson.core.util.DefaultPrettyPrinter.FixedSpaceIndenter();
    }

    @Test
    public void test0198() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0198");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        tools.jackson.core.filter.TokenFilter tokenFilter6 = filteringParserDelegate5.getFilter();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str7 = filteringParserDelegate5.getString();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(tokenFilter6);
    }

    @Test
    public void test0199() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0199");
        char[] charArray3 = new char[] { ' ', ' ', '4' };
        boolean boolean7 = tools.jackson.core.io.NumberInput.inLongRange(charArray3, (-324), (int) '4', false);
        boolean boolean11 = tools.jackson.core.io.NumberInput.inLongRange(charArray3, 55296, (int) (short) 100, false);
        // The following exception was thrown during execution in test generation
        try {
            long long14 = tools.jackson.core.io.NumberInput.parseLong19(charArray3, (int) ' ', false);
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: 32");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(charArray3);
        org.junit.Assert.assertArrayEquals(charArray3, new char[] { ' ', ' ', '4' });
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
    }

    @Test
    public void test0200() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0200");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        tools.jackson.core.JsonParser jsonParser6 = filteringParserDelegate5.skipChildren();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj7 = jsonParser6.getObjectId();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(jsonParser6);
    }

    @Test
    public void test0201() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0201");
        tools.jackson.core.json.DupDetector dupDetector0 = null;
        tools.jackson.core.util.SimpleStreamWriteContext simpleStreamWriteContext1 = tools.jackson.core.util.SimpleStreamWriteContext.createRootContext(dupDetector0);
        tools.jackson.core.json.DupDetector dupDetector2 = simpleStreamWriteContext1.getDupDetector();
        tools.jackson.core.json.DupDetector dupDetector3 = null;
        tools.jackson.core.util.SimpleStreamWriteContext simpleStreamWriteContext4 = simpleStreamWriteContext1.withDupDetector(dupDetector3);
        java.lang.String str5 = simpleStreamWriteContext4.typeDesc();
        boolean boolean6 = simpleStreamWriteContext4.hasCurrentName();
        boolean boolean7 = simpleStreamWriteContext4.inArray();
        org.junit.Assert.assertNotNull(simpleStreamWriteContext1);
        org.junit.Assert.assertNull(dupDetector2);
        org.junit.Assert.assertNotNull(simpleStreamWriteContext4);
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "root" + "'", str5, "root");
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
    }

    @Test
    public void test0202() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0202");
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.util.RecyclerPool<tools.jackson.core.util.BufferRecycler> bufferRecyclerRecyclerPool1 = tools.jackson.core.util.JsonRecyclerPools.newBoundedPool((-45));
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: capacity must be > 0, was: -45");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0203() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0203");
        boolean boolean1 = tools.jackson.core.io.NumberOutput.notFinite((double) (-1.0f));
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
    }

    @Test
    public void test0204() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0204");
        int int1 = tools.jackson.core.io.schubfach.MathUtils.flog10threeQuartersPow2(4);
        org.junit.Assert.assertTrue("'" + int1 + "' != '" + 1 + "'", int1 == 1);
    }

    @Test
    public void test0205() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0205");
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder0 = new tools.jackson.core.json.JsonFactoryBuilder();
    }

    @Test
    public void test0206() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0206");
        tools.jackson.core.util.DefaultPrettyPrinter defaultPrettyPrinter0 = new tools.jackson.core.util.DefaultPrettyPrinter();
        tools.jackson.core.JsonGenerator jsonGenerator1 = null;
        // The following exception was thrown during execution in test generation
        try {
            defaultPrettyPrinter0.writeRootValueSeparator(jsonGenerator1);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0207() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0207");
        tools.jackson.core.Base64Variant base64Variant0 = tools.jackson.core.Base64Variants.MODIFIED_FOR_URL;
        int int2 = base64Variant0.decodeBase64Char(',');
        org.junit.Assert.assertNotNull(base64Variant0);
        org.junit.Assert.assertTrue("'" + int2 + "' != '" + (-1) + "'", int2 == (-1));
    }

    @Test
    public void test0208() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0208");
        tools.jackson.core.io.IOContext iOContext0 = null;
        java.io.InputStream inputStream1 = null;
        byte[] byteArray5 = new byte[] { (byte) 100, (byte) 1 };
        tools.jackson.core.io.UTF32Reader uTF32Reader9 = new tools.jackson.core.io.UTF32Reader(iOContext0, inputStream1, false, byteArray5, (int) (short) 0, (-1), false);
        // The following exception was thrown during execution in test generation
        try {
            long long11 = uTF32Reader9.skip((long) 12000);
            org.junit.Assert.fail("Expected exception of type java.io.CharConversionException; message: Unexpected EOF in the middle of a 4-byte UTF-32 char: got -1, needed 4, at char #0, byte #-1)");
        } catch (java.io.CharConversionException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray5);
        org.junit.Assert.assertArrayEquals(byteArray5, new byte[] { (byte) 100, (byte) 1 });
    }

    @Test
    public void test0209() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0209");
        tools.jackson.core.ObjectReadContext objectReadContext0 = null;
        tools.jackson.core.io.IOContext iOContext1 = null;
        java.io.Reader reader4 = null;
        tools.jackson.core.sym.CharsToNameCanonicalizer charsToNameCanonicalizer5 = null;
        char[] charArray9 = new char[] { ' ', ' ', '4' };
        boolean boolean13 = tools.jackson.core.io.NumberInput.inLongRange(charArray9, (-324), (int) '4', false);
        boolean boolean17 = tools.jackson.core.io.NumberInput.inLongRange(charArray9, 55296, (int) (short) 100, false);
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.json.ReaderBasedJsonParser readerBasedJsonParser21 = new tools.jackson.core.json.ReaderBasedJsonParser(objectReadContext0, iOContext1, (int) (byte) -1, 32000, reader4, charsToNameCanonicalizer5, charArray9, (-324), 2, false);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(charArray9);
        org.junit.Assert.assertArrayEquals(charArray9, new char[] { ' ', ' ', '4' });
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
        org.junit.Assert.assertTrue("'" + boolean17 + "' != '" + false + "'", boolean17 == false);
    }

    @Test
    public void test0210() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0210");
        tools.jackson.core.JsonGenerator jsonGenerator2 = null;
        tools.jackson.core.json.DupDetector dupDetector3 = tools.jackson.core.json.DupDetector.rootDetector(jsonGenerator2);
        tools.jackson.core.json.JsonReadContext jsonReadContext4 = tools.jackson.core.json.JsonReadContext.createRootContext(56, 7, dupDetector3);
        boolean boolean5 = jsonReadContext4.hasCurrentName();
        org.junit.Assert.assertNotNull(dupDetector3);
        org.junit.Assert.assertNotNull(jsonReadContext4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
    }

    @Test
    public void test0211() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0211");
        tools.jackson.core.util.BufferRecycler bufferRecycler0 = null;
        tools.jackson.core.util.TextBuffer textBuffer1 = new tools.jackson.core.util.TextBuffer(bufferRecycler0);
        textBuffer1.resetWithString("");
        boolean boolean4 = textBuffer1.hasTextAsCharacters();
        tools.jackson.core.util.BufferRecycler bufferRecycler5 = null;
        tools.jackson.core.util.TextBuffer textBuffer6 = new tools.jackson.core.util.TextBuffer(bufferRecycler5);
        textBuffer6.resetWithString("");
        int int9 = textBuffer6.getCurrentSegmentSize();
        java.lang.String str10 = textBuffer6.toString();
        java.lang.String str13 = textBuffer6.finishAndReturn((int) 'B', false);
        java.lang.String str14 = textBuffer6.toString();
        char[] charArray15 = textBuffer6.emptyAndGetCurrentSegment();
        // The following exception was thrown during execution in test generation
        try {
            textBuffer1.append(charArray15, 12000, (-2));
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertTrue("'" + int9 + "' != '" + 0 + "'", int9 == 0);
        org.junit.Assert.assertEquals("'" + str10 + "' != '" + "" + "'", str10, "");
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "" + "'", str13, "");
        org.junit.Assert.assertEquals("'" + str14 + "' != '" + "" + "'", str14, "");
        org.junit.Assert.assertNotNull(charArray15);
    }

    @Test
    public void test0212() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0212");
        tools.jackson.core.JsonToken jsonToken1 = null;
        tools.jackson.core.type.WritableTypeId writableTypeId3 = new tools.jackson.core.type.WritableTypeId((java.lang.Object) '#', jsonToken1, (java.lang.Object) ' ');
        tools.jackson.core.JsonToken jsonToken4 = writableTypeId3.valueShape;
        java.lang.Object obj5 = writableTypeId3.forValue;
        org.junit.Assert.assertNull(jsonToken4);
        org.junit.Assert.assertEquals("'" + obj5 + "' != '" + '#' + "'", obj5, '#');
    }

    @Test
    public void test0213() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0213");
        tools.jackson.core.json.DupDetector dupDetector2 = null;
        tools.jackson.core.json.JsonReadContext jsonReadContext3 = tools.jackson.core.json.JsonReadContext.createRootContext(1, 0, dupDetector2);
        boolean boolean4 = jsonReadContext3.expectComma();
        tools.jackson.core.json.JsonReadContext jsonReadContext7 = jsonReadContext3.createChildObjectContext(3, (int) (short) 10);
        java.lang.String str8 = jsonReadContext3.currentName();
        org.junit.Assert.assertNotNull(jsonReadContext3);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertNotNull(jsonReadContext7);
        org.junit.Assert.assertNull(str8);
    }

    @Test
    public void test0214() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0214");
        tools.jackson.core.util.DefaultPrettyPrinter defaultPrettyPrinter0 = new tools.jackson.core.util.DefaultPrettyPrinter();
        tools.jackson.core.util.Separators separators1 = tools.jackson.core.util.Separators.createDefaultInstance();
        tools.jackson.core.util.Separators separators3 = separators1.withObjectNameValueSeparator('\000');
        tools.jackson.core.util.Separators separators5 = separators3.withObjectNameValueSeparator('\000');
        tools.jackson.core.util.Separators.Spacing spacing6 = null;
        tools.jackson.core.util.Separators separators7 = separators3.withObjectNameValueSpacing(spacing6);
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.util.DefaultPrettyPrinter defaultPrettyPrinter8 = defaultPrettyPrinter0.withSeparators(separators7);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(separators1);
        org.junit.Assert.assertNotNull(separators3);
        org.junit.Assert.assertNotNull(separators5);
        org.junit.Assert.assertNotNull(separators7);
    }

    @Test
    public void test0215() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0215");
        char char0 = tools.jackson.core.JsonPointer.SEPARATOR;
        org.junit.Assert.assertTrue("'" + char0 + "' != '" + '/' + "'", char0 == '/');
    }

    @Test
    public void test0216() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0216");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        tools.jackson.core.JsonParser jsonParser6 = filteringParserDelegate5.skipChildren();
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.TokenStreamLocation tokenStreamLocation7 = filteringParserDelegate5.currentTokenLocation();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(jsonParser6);
    }

    @Test
    public void test0217() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0217");
        // The following exception was thrown during execution in test generation
        try {
            double double1 = tools.jackson.core.io.NumberInput.parseDouble("tools.jackson.core.type.WritableTypeId[256]");
            org.junit.Assert.fail("Expected exception of type java.lang.NumberFormatException; message: For input string: \"tools.jackson.core.type.WritableTypeId[256]\"");
        } catch (java.lang.NumberFormatException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0218() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0218");
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder0 = tools.jackson.core.json.JsonFactory.builderWithJackson2Defaults();
        tools.jackson.core.SerializableString serializableString1 = null;
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder2 = jsonFactoryBuilder0.rootValueSeparator(serializableString1);
        tools.jackson.core.StreamWriteFeature streamWriteFeature3 = null;
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder4 = jsonFactoryBuilder0.enable(streamWriteFeature3);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(jsonFactoryBuilder0);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder2);
    }

    @Test
    public void test0219() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0219");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        tools.jackson.core.filter.TokenFilter tokenFilter6 = filteringParserDelegate5.getFilter();
        boolean boolean7 = filteringParserDelegate5.isExpectedStartArrayToken();
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.Version version8 = filteringParserDelegate5.version();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(tokenFilter6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
    }

    @Test
    public void test0220() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0220");
        tools.jackson.core.io.IOContext iOContext1 = null;
        java.io.InputStream inputStream2 = null;
        byte[] byteArray6 = new byte[] { (byte) 100, (byte) 1 };
        tools.jackson.core.io.UTF32Reader uTF32Reader10 = new tools.jackson.core.io.UTF32Reader(iOContext1, inputStream2, false, byteArray6, (int) (short) 0, (-1), false);
        // The following exception was thrown during execution in test generation
        try {
            int int12 = tools.jackson.core.io.NumberOutput.outputLong((long) (-1074), byteArray6, (-324));
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: -324");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray6);
        org.junit.Assert.assertArrayEquals(byteArray6, new byte[] { (byte) 100, (byte) 1 });
    }

    @Test
    public void test0221() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0221");
        int int0 = tools.jackson.core.json.JsonWriteContext.STATUS_OK_AS_IS;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 0 + "'", int0 == 0);
    }

    @Test
    public void test0222() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0222");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        tools.jackson.core.filter.TokenFilter tokenFilter6 = filteringParserDelegate5.getFilter();
        boolean boolean7 = filteringParserDelegate5.isExpectedStartArrayToken();
        boolean boolean8 = filteringParserDelegate5.hasCurrentToken();
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.JsonToken jsonToken9 = filteringParserDelegate5.nextValue();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(tokenFilter6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
    }

    @Test
    public void test0223() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0223");
        java.io.DataOutput dataOutput0 = null;
        tools.jackson.core.io.DataOutputAsStream dataOutputAsStream1 = new tools.jackson.core.io.DataOutputAsStream(dataOutput0);
        byte[] byteArray4 = new byte[] { (byte) 100, (byte) 10 };
        // The following exception was thrown during execution in test generation
        try {
            dataOutputAsStream1.write(byteArray4, 9, 10);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray4);
        org.junit.Assert.assertArrayEquals(byteArray4, new byte[] { (byte) 100, (byte) 10 });
    }

    @Test
    public void test0224() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0224");
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder0 = tools.jackson.core.json.JsonFactory.builder();
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder2 = jsonFactoryBuilder0.highestNonEscapedChar((-324));
        tools.jackson.core.StreamWriteFeature streamWriteFeature3 = null;
        tools.jackson.core.StreamWriteFeature streamWriteFeature4 = null;
        tools.jackson.core.StreamWriteFeature[] streamWriteFeatureArray5 = new tools.jackson.core.StreamWriteFeature[] { streamWriteFeature4 };
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder6 = jsonFactoryBuilder0.disable(streamWriteFeature3, streamWriteFeatureArray5);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(jsonFactoryBuilder0);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder2);
        org.junit.Assert.assertNotNull(streamWriteFeatureArray5);
        org.junit.Assert.assertArrayEquals(streamWriteFeatureArray5, new tools.jackson.core.StreamWriteFeature[] { null });
    }

    @Test
    public void test0225() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0225");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        tools.jackson.core.filter.TokenFilter tokenFilter6 = filteringParserDelegate5.getFilter();
        boolean boolean7 = filteringParserDelegate5.isExpectedStartArrayToken();
        tools.jackson.core.filter.TokenFilter tokenFilter8 = filteringParserDelegate5.getFilter();
        tools.jackson.core.StreamReadFeature streamReadFeature9 = null;
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean10 = filteringParserDelegate5.isEnabled(streamReadFeature9);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(tokenFilter6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertNull(tokenFilter8);
    }

    @Test
    public void test0226() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0226");
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder0 = tools.jackson.core.json.JsonFactory.builderWithJackson2Defaults();
        tools.jackson.core.SerializableString serializableString1 = null;
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder2 = jsonFactoryBuilder0.rootValueSeparator(serializableString1);
        tools.jackson.core.io.OutputDecorator outputDecorator3 = jsonFactoryBuilder2.outputDecorator();
        tools.jackson.core.util.RecyclerPool<tools.jackson.core.util.BufferRecycler> bufferRecyclerRecyclerPool4 = jsonFactoryBuilder2.recyclerPool();
        tools.jackson.core.json.JsonReadFeature jsonReadFeature5 = null;
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder7 = jsonFactoryBuilder2.configure(jsonReadFeature5, false);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(jsonFactoryBuilder0);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder2);
        org.junit.Assert.assertNull(outputDecorator3);
        org.junit.Assert.assertNotNull(bufferRecyclerRecyclerPool4);
    }

    @Test
    public void test0227() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0227");
        tools.jackson.core.Base64Variant base64Variant0 = tools.jackson.core.Base64Variants.MODIFIED_FOR_URL;
        byte[] byteArray8 = new byte[] { (byte) 10, (byte) -1, (byte) -1, (byte) -1, (byte) -1 };
        int int10 = base64Variant0.encodeBase64Partial(10, (int) 'a', byteArray8, (int) (byte) 1);
        tools.jackson.core.Base64Variant base64Variant13 = new tools.jackson.core.Base64Variant(base64Variant0, "hi!", 0);
        char char14 = base64Variant0.getPaddingChar();
        boolean boolean15 = base64Variant0.usesPadding();
        org.junit.Assert.assertNotNull(base64Variant0);
        org.junit.Assert.assertNotNull(byteArray8);
        org.junit.Assert.assertArrayEquals(byteArray8, new byte[] { (byte) 10, (byte) 65, (byte) 65, (byte) -1, (byte) -1 });
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 3 + "'", int10 == 3);
        org.junit.Assert.assertTrue("'" + char14 + "' != '" + '\000' + "'", char14 == '\000');
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
    }

    @Test
    public void test0228() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0228");
        int int0 = tools.jackson.core.io.schubfach.MathUtils.H;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 17 + "'", int0 == 17);
    }

    @Test
    public void test0229() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0229");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        tools.jackson.core.filter.TokenFilter tokenFilter6 = filteringParserDelegate5.getFilter();
        tools.jackson.core.JsonToken jsonToken7 = filteringParserDelegate5.currentToken();
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean8 = filteringParserDelegate5.canParseAsync();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(tokenFilter6);
        org.junit.Assert.assertNull(jsonToken7);
    }

    @Test
    public void test0230() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0230");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        boolean boolean6 = filteringParserDelegate5.isExpectedStartObjectToken();
        int int7 = filteringParserDelegate5.currentTokenId();
        boolean boolean8 = filteringParserDelegate5.isExpectedStartObjectToken();
        tools.jackson.core.JsonParser jsonParser9 = filteringParserDelegate5.skipChildren();
        // The following exception was thrown during execution in test generation
        try {
            int int10 = filteringParserDelegate5.getIntValue();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 0 + "'", int7 == 0);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertNotNull(jsonParser9);
    }

    @Test
    public void test0231() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0231");
        tools.jackson.core.filter.TokenFilter tokenFilter0 = null;
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext1 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter0);
        tools.jackson.core.JsonToken jsonToken2 = tokenFilterContext1.nextTokenToRead();
        tools.jackson.core.filter.TokenFilter tokenFilter3 = tools.jackson.core.filter.TokenFilter.INCLUDE_ALL;
        tools.jackson.core.filter.TokenFilter tokenFilter4 = tokenFilterContext1.checkValue(tokenFilter3);
        java.math.BigDecimal bigDecimal5 = null;
        boolean boolean6 = tokenFilter3.includeNumber(bigDecimal5);
        tools.jackson.core.filter.TokenFilter tokenFilter8 = tokenFilter3.includeRootValue((int) (byte) 1);
        tools.jackson.core.filter.TokenFilter tokenFilter9 = tokenFilter8.filterStartArray();
        boolean boolean11 = tokenFilter9.includeNumber((long) 7);
        org.junit.Assert.assertNotNull(tokenFilterContext1);
        org.junit.Assert.assertNull(jsonToken2);
        org.junit.Assert.assertNotNull(tokenFilter3);
        org.junit.Assert.assertNotNull(tokenFilter4);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + true + "'", boolean6 == true);
        org.junit.Assert.assertNotNull(tokenFilter8);
        org.junit.Assert.assertNotNull(tokenFilter9);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + true + "'", boolean11 == true);
    }

    @Test
    public void test0232() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0232");
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.Base64Variant base64Variant0 = tools.jackson.core.util.VersionUtil.throwInternalReturnAny();
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: Internal error: this code path should never get executed");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0233() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0233");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        tools.jackson.core.filter.TokenFilter tokenFilter6 = filteringParserDelegate5.getFilter();
        boolean boolean7 = filteringParserDelegate5.isExpectedStartArrayToken();
        tools.jackson.core.filter.TokenFilter tokenFilter8 = filteringParserDelegate5.getFilter();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj9 = filteringParserDelegate5.getNumberValueDeferred();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(tokenFilter6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertNull(tokenFilter8);
    }

    @Test
    public void test0234() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0234");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        tools.jackson.core.JsonParser jsonParser6 = filteringParserDelegate5.skipChildren();
        tools.jackson.core.json.DupDetector dupDetector8 = null;
        tools.jackson.core.util.SimpleStreamWriteContext simpleStreamWriteContext9 = tools.jackson.core.util.SimpleStreamWriteContext.createRootContext(dupDetector8);
        tools.jackson.core.io.ContentReference contentReference10 = null;
        tools.jackson.core.TokenStreamLocation tokenStreamLocation11 = simpleStreamWriteContext9.startLocation(contentReference10);
        java.lang.String str12 = tokenStreamLocation11.sourceDescription();
        tools.jackson.core.JsonGenerator jsonGenerator13 = null;
        java.lang.Throwable throwable15 = null;
        tools.jackson.core.exc.StreamWriteException streamWriteException16 = new tools.jackson.core.exc.StreamWriteException(jsonGenerator13, "hi!", throwable15);
        tools.jackson.core.JsonGenerator jsonGenerator17 = streamWriteException16.processor();
        tools.jackson.core.exc.StreamReadException streamReadException18 = new tools.jackson.core.exc.StreamReadException((tools.jackson.core.JsonParser) filteringParserDelegate5, "root", tokenStreamLocation11, (java.lang.Throwable) streamWriteException16);
        tools.jackson.core.exc.StreamReadException streamReadException20 = new tools.jackson.core.exc.StreamReadException("hi!");
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.JacksonException jacksonException21 = streamWriteException16.withCause((java.lang.Throwable) streamReadException20);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: Can't overwrite cause with tools.jackson.core.exc.StreamReadException: hi!");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(jsonParser6);
        org.junit.Assert.assertNotNull(simpleStreamWriteContext9);
        org.junit.Assert.assertNotNull(tokenStreamLocation11);
        org.junit.Assert.assertEquals("'" + str12 + "' != '" + "UNKNOWN" + "'", str12, "UNKNOWN");
        org.junit.Assert.assertNull(jsonGenerator17);
    }

    @Test
    public void test0235() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0235");
        tools.jackson.core.Base64Variant base64Variant0 = tools.jackson.core.Base64Variants.MODIFIED_FOR_URL;
        byte[] byteArray8 = new byte[] { (byte) 10, (byte) -1, (byte) -1, (byte) -1, (byte) -1 };
        int int10 = base64Variant0.encodeBase64Partial(10, (int) 'a', byteArray8, (int) (byte) 1);
        tools.jackson.core.Base64Variant base64Variant13 = new tools.jackson.core.Base64Variant(base64Variant0, "hi!", 0);
        boolean boolean14 = base64Variant0.usesPadding();
        char[] charArray19 = new char[] { ' ', ' ', '4' };
        boolean boolean23 = tools.jackson.core.io.NumberInput.inLongRange(charArray19, (-324), (int) '4', false);
        // The following exception was thrown during execution in test generation
        try {
            int int25 = base64Variant0.encodeBase64Chunk(10, charArray19, (int) '/');
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: 47");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(base64Variant0);
        org.junit.Assert.assertNotNull(byteArray8);
        org.junit.Assert.assertArrayEquals(byteArray8, new byte[] { (byte) 10, (byte) 65, (byte) 65, (byte) -1, (byte) -1 });
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 3 + "'", int10 == 3);
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + false + "'", boolean14 == false);
        org.junit.Assert.assertNotNull(charArray19);
        org.junit.Assert.assertArrayEquals(charArray19, new char[] { ' ', ' ', '4' });
        org.junit.Assert.assertTrue("'" + boolean23 + "' != '" + false + "'", boolean23 == false);
    }

    @Test
    public void test0236() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0236");
        tools.jackson.core.json.DupDetector dupDetector2 = null;
        tools.jackson.core.json.JsonReadContext jsonReadContext3 = tools.jackson.core.json.JsonReadContext.createRootContext(1, 0, dupDetector2);
        boolean boolean4 = jsonReadContext3.expectComma();
        tools.jackson.core.json.JsonReadContext jsonReadContext7 = jsonReadContext3.createChildObjectContext(3, (int) (short) 10);
        boolean boolean8 = jsonReadContext7.inObject();
        tools.jackson.core.json.JsonReadContext jsonReadContext11 = jsonReadContext7.createChildObjectContext(256, 10);
        tools.jackson.core.json.JsonReadContext jsonReadContext12 = jsonReadContext11.clearAndGetParent();
        org.junit.Assert.assertNotNull(jsonReadContext3);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertNotNull(jsonReadContext7);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + true + "'", boolean8 == true);
        org.junit.Assert.assertNotNull(jsonReadContext11);
        org.junit.Assert.assertNotNull(jsonReadContext12);
    }

    @Test
    public void test0237() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0237");
        int int0 = tools.jackson.core.io.schubfach.DoubleToDecimal.P;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 53 + "'", int0 == 53);
    }

    @Test
    public void test0238() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0238");
        int int0 = tools.jackson.core.io.UTF8Writer.SURR1_FIRST;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 55296 + "'", int0 == 55296);
    }

    @Test
    public void test0239() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0239");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        tools.jackson.core.filter.TokenFilter tokenFilter6 = filteringParserDelegate5.getFilter();
        boolean boolean7 = filteringParserDelegate5.isExpectedStartArrayToken();
        tools.jackson.core.filter.TokenFilter tokenFilter8 = filteringParserDelegate5.getFilter();
        // The following exception was thrown during execution in test generation
        try {
            int int9 = filteringParserDelegate5.getValueAsInt();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(tokenFilter6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertNull(tokenFilter8);
    }

    @Test
    public void test0240() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0240");
        long long0 = tools.jackson.core.StreamReadConstraints.DEFAULT_MAX_TOKEN_COUNT;
        org.junit.Assert.assertTrue("'" + long0 + "' != '" + (-1L) + "'", long0 == (-1L));
    }

    @Test
    public void test0241() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0241");
        tools.jackson.core.util.DefaultIndenter defaultIndenter0 = tools.jackson.core.util.DefaultIndenter.SYSTEM_LINEFEED_INSTANCE;
        tools.jackson.core.JsonGenerator jsonGenerator1 = null;
        // The following exception was thrown during execution in test generation
        try {
            defaultIndenter0.writeIndentation(jsonGenerator1, (-324));
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(defaultIndenter0);
    }

    @Test
    public void test0242() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0242");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        tools.jackson.core.JsonParser jsonParser6 = filteringParserDelegate5.skipChildren();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str7 = filteringParserDelegate5.nextName();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(jsonParser6);
    }

    @Test
    public void test0243() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0243");
        tools.jackson.core.Version version0 = tools.jackson.core.Version.unknownVersion();
        java.lang.String str1 = version0.toString();
        int int2 = version0.getMinorVersion();
        org.junit.Assert.assertNotNull(version0);
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "0.0.0" + "'", str1, "0.0.0");
        org.junit.Assert.assertTrue("'" + int2 + "' != '" + 0 + "'", int2 == 0);
    }

    @Test
    public void test0244() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0244");
        tools.jackson.core.filter.TokenFilter tokenFilter0 = null;
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext1 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter0);
        tools.jackson.core.JsonToken jsonToken2 = tokenFilterContext1.nextTokenToRead();
        tools.jackson.core.filter.TokenFilter tokenFilter3 = tools.jackson.core.filter.TokenFilter.INCLUDE_ALL;
        tools.jackson.core.filter.TokenFilter tokenFilter4 = tokenFilterContext1.checkValue(tokenFilter3);
        java.math.BigDecimal bigDecimal5 = null;
        boolean boolean6 = tokenFilter3.includeNumber(bigDecimal5);
        tools.jackson.core.filter.TokenFilter tokenFilter7 = tokenFilter3.filterStartObject();
        java.math.BigInteger bigInteger8 = null;
        boolean boolean9 = tokenFilter7.includeNumber(bigInteger8);
        tools.jackson.core.JsonParser jsonParser10 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter11 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion12 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate15 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser10, tokenFilter11, inclusion12, true, true);
        tools.jackson.core.filter.TokenFilter tokenFilter16 = filteringParserDelegate15.getFilter();
        boolean boolean17 = filteringParserDelegate15.isExpectedStartArrayToken();
        tools.jackson.core.filter.TokenFilter tokenFilter18 = filteringParserDelegate15.getFilter();
        boolean boolean19 = tokenFilter7.includeValue((tools.jackson.core.JsonParser) filteringParserDelegate15);
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.TokenStreamLocation tokenStreamLocation20 = filteringParserDelegate15.currentLocation();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(tokenFilterContext1);
        org.junit.Assert.assertNull(jsonToken2);
        org.junit.Assert.assertNotNull(tokenFilter3);
        org.junit.Assert.assertNotNull(tokenFilter4);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + true + "'", boolean6 == true);
        org.junit.Assert.assertNotNull(tokenFilter7);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + true + "'", boolean9 == true);
        org.junit.Assert.assertNull(tokenFilter16);
        org.junit.Assert.assertTrue("'" + boolean17 + "' != '" + false + "'", boolean17 == false);
        org.junit.Assert.assertNull(tokenFilter18);
        org.junit.Assert.assertTrue("'" + boolean19 + "' != '" + true + "'", boolean19 == true);
    }

    @Test
    public void test0245() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0245");
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder0 = tools.jackson.core.json.JsonFactory.builderWithJackson2Defaults();
        tools.jackson.core.SerializableString serializableString1 = null;
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder2 = jsonFactoryBuilder0.rootValueSeparator(serializableString1);
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder4 = jsonFactoryBuilder0.quoteChar(' ');
        tools.jackson.core.json.JsonWriteFeature jsonWriteFeature5 = null;
        tools.jackson.core.json.JsonWriteFeature jsonWriteFeature6 = null;
        tools.jackson.core.json.JsonWriteFeature[] jsonWriteFeatureArray7 = new tools.jackson.core.json.JsonWriteFeature[] { jsonWriteFeature6 };
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder8 = jsonFactoryBuilder0.disable(jsonWriteFeature5, jsonWriteFeatureArray7);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(jsonFactoryBuilder0);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder2);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder4);
        org.junit.Assert.assertNotNull(jsonWriteFeatureArray7);
        org.junit.Assert.assertArrayEquals(jsonWriteFeatureArray7, new tools.jackson.core.json.JsonWriteFeature[] { null });
    }

    @Test
    public void test0246() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0246");
        tools.jackson.core.filter.TokenFilter tokenFilter0 = null;
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext1 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter0);
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext2 = tokenFilterContext1.getParent();
        tools.jackson.core.JsonGenerator jsonGenerator3 = null;
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.filter.TokenFilterContext tokenFilterContext4 = tokenFilterContext1.closeObject(jsonGenerator3);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(tokenFilterContext1);
        org.junit.Assert.assertNull(tokenFilterContext2);
    }

    @Test
    public void test0247() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0247");
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter0 = new tools.jackson.core.util.MinimalPrettyPrinter();
        tools.jackson.core.JsonGenerator jsonGenerator1 = null;
        // The following exception was thrown during execution in test generation
        try {
            minimalPrettyPrinter0.writeObjectEntrySeparator(jsonGenerator1);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0248() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0248");
        tools.jackson.core.sym.ByteQuadsCanonicalizer byteQuadsCanonicalizer0 = tools.jackson.core.sym.ByteQuadsCanonicalizer.createRoot();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str3 = byteQuadsCanonicalizer0.addName("ZAE", (-324));
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: Internal error: Cannot add names to Root symbol table");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteQuadsCanonicalizer0);
    }

    @Test
    public void test0249() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0249");
        tools.jackson.core.util.Separators.Spacing spacing2 = null;
        tools.jackson.core.util.Separators.Spacing spacing4 = null;
        tools.jackson.core.util.Separators.Spacing spacing7 = null;
        tools.jackson.core.util.Separators separators9 = new tools.jackson.core.util.Separators("hi!", '#', spacing2, '~', spacing4, "\"_2QK\\n\"", 'B', spacing7, "0.0.0");
    }

    @Test
    public void test0250() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0250");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        tools.jackson.core.filter.TokenFilter tokenFilter6 = filteringParserDelegate5.getFilter();
        boolean boolean7 = filteringParserDelegate5.isExpectedStartArrayToken();
        // The following exception was thrown during execution in test generation
        try {
            char[] charArray8 = filteringParserDelegate5.getStringCharacters();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(tokenFilter6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
    }

    @Test
    public void test0251() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0251");
        int int1 = tools.jackson.core.io.JsonStringEncoder._initialByteBufSize((int) (short) -1);
        org.junit.Assert.assertTrue("'" + int1 + "' != '" + 24 + "'", int1 == 24);
    }

    @Test
    public void test0252() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0252");
        tools.jackson.core.json.DupDetector dupDetector2 = null;
        tools.jackson.core.json.JsonReadContext jsonReadContext3 = tools.jackson.core.json.JsonReadContext.createRootContext(1, 0, dupDetector2);
        tools.jackson.core.json.JsonReadContext jsonReadContext4 = jsonReadContext3.getParent();
        tools.jackson.core.json.JsonReadContext jsonReadContext5 = jsonReadContext3.getParent();
        boolean boolean6 = jsonReadContext3.hasCurrentName();
        java.lang.Object obj7 = jsonReadContext3.currentValue();
        org.junit.Assert.assertNotNull(jsonReadContext3);
        org.junit.Assert.assertNull(jsonReadContext4);
        org.junit.Assert.assertNull(jsonReadContext5);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNull(obj7);
    }

    @Test
    public void test0253() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0253");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        boolean boolean6 = filteringParserDelegate5.isExpectedStartObjectToken();
        int int7 = filteringParserDelegate5.currentTokenId();
        tools.jackson.core.JsonToken jsonToken8 = null;
        boolean boolean9 = filteringParserDelegate5.hasToken(jsonToken8);
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.JsonParser.NumberTypeFP numberTypeFP10 = filteringParserDelegate5.getNumberTypeFP();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 0 + "'", int7 == 0);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + true + "'", boolean9 == true);
    }

    @Test
    public void test0254() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0254");
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder0 = tools.jackson.core.json.JsonFactory.builderWithJackson2Defaults();
        tools.jackson.core.SerializableString serializableString1 = null;
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder2 = jsonFactoryBuilder0.rootValueSeparator(serializableString1);
        tools.jackson.core.io.CharacterEscapes characterEscapes3 = null;
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder4 = jsonFactoryBuilder0.characterEscapes(characterEscapes3);
        int int5 = jsonFactoryBuilder4.streamReadFeaturesMask();
        tools.jackson.core.json.JsonReadFeature jsonReadFeature6 = null;
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder8 = jsonFactoryBuilder4.configure(jsonReadFeature6, false);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(jsonFactoryBuilder0);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder2);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder4);
        org.junit.Assert.assertTrue("'" + int5 + "' != '" + 9 + "'", int5 == 9);
    }

    @Test
    public void test0255() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0255");
        tools.jackson.core.util.JsonRecyclerPools.ConcurrentDequePool concurrentDequePool0 = tools.jackson.core.util.JsonRecyclerPools.ConcurrentDequePool.construct();
        tools.jackson.core.util.BufferRecycler bufferRecycler1 = null;
        // The following exception was thrown during execution in test generation
        try {
            concurrentDequePool0.releasePooled(bufferRecycler1);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(concurrentDequePool0);
    }

    @Test
    public void test0256() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0256");
        tools.jackson.core.util.BufferRecycler bufferRecycler0 = null;
        tools.jackson.core.util.ByteArrayBuilder byteArrayBuilder1 = new tools.jackson.core.util.ByteArrayBuilder(bufferRecycler0);
        byte[] byteArray3 = byteArrayBuilder1.completeAndCoalesce(10);
        byteArrayBuilder1.write(5);
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertArrayEquals(byteArray3, new byte[] { (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0 });
    }

    @Test
    public void test0257() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0257");
        java.lang.String str1 = tools.jackson.core.io.schubfach.FloatToDecimal.toString((float) 8);
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "8.0" + "'", str1, "8.0");
    }

    @Test
    public void test0258() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0258");
        tools.jackson.core.Base64Variant base64Variant0 = tools.jackson.core.Base64Variants.MODIFIED_FOR_URL;
        byte[] byteArray8 = new byte[] { (byte) 10, (byte) -1, (byte) -1, (byte) -1, (byte) -1 };
        int int10 = base64Variant0.encodeBase64Partial(10, (int) 'a', byteArray8, (int) (byte) 1);
        tools.jackson.core.Base64Variant base64Variant13 = new tools.jackson.core.Base64Variant(base64Variant0, "hi!", 0);
        tools.jackson.core.Base64Variant base64Variant14 = base64Variant0.withPaddingForbidden();
        boolean boolean15 = base64Variant0.acceptsPaddingOnRead();
        char char17 = base64Variant0.encodeBase64BitsAsChar(1);
        tools.jackson.core.io.IOContext iOContext18 = null;
        java.io.InputStream inputStream19 = null;
        byte[] byteArray23 = new byte[] { (byte) 100, (byte) 1 };
        tools.jackson.core.io.UTF32Reader uTF32Reader27 = new tools.jackson.core.io.UTF32Reader(iOContext18, inputStream19, false, byteArray23, (int) (short) 0, (-1), false);
        java.lang.String str28 = base64Variant0.encode(byteArray23);
        boolean boolean29 = base64Variant0.acceptsPaddingOnRead();
        char char30 = base64Variant0.getPaddingChar();
        org.junit.Assert.assertNotNull(base64Variant0);
        org.junit.Assert.assertNotNull(byteArray8);
        org.junit.Assert.assertArrayEquals(byteArray8, new byte[] { (byte) 10, (byte) 65, (byte) 65, (byte) -1, (byte) -1 });
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 3 + "'", int10 == 3);
        org.junit.Assert.assertNotNull(base64Variant14);
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertTrue("'" + char17 + "' != '" + 'B' + "'", char17 == 'B');
        org.junit.Assert.assertNotNull(byteArray23);
        org.junit.Assert.assertArrayEquals(byteArray23, new byte[] { (byte) 100, (byte) 1 });
        org.junit.Assert.assertEquals("'" + str28 + "' != '" + "ZAE" + "'", str28, "ZAE");
        org.junit.Assert.assertTrue("'" + boolean29 + "' != '" + false + "'", boolean29 == false);
        org.junit.Assert.assertTrue("'" + char30 + "' != '" + '\000' + "'", char30 == '\000');
    }

    @Test
    public void test0259() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0259");
        tools.jackson.core.ObjectReadContext objectReadContext0 = null;
        tools.jackson.core.io.IOContext iOContext1 = null;
        java.io.DataInput dataInput4 = null;
        tools.jackson.core.sym.ByteQuadsCanonicalizer byteQuadsCanonicalizer5 = tools.jackson.core.sym.ByteQuadsCanonicalizer.createRoot();
        byteQuadsCanonicalizer5.release();
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.json.UTF8DataInputJsonParser uTF8DataInputJsonParser8 = new tools.jackson.core.json.UTF8DataInputJsonParser(objectReadContext0, iOContext1, 12000, 971, dataInput4, byteQuadsCanonicalizer5, 4);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteQuadsCanonicalizer5);
    }

    @Test
    public void test0260() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0260");
        tools.jackson.core.filter.TokenFilter tokenFilter0 = null;
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext1 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter0);
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext2 = tokenFilterContext1.getParent();
        boolean boolean3 = tokenFilterContext1.inArray();
        org.junit.Assert.assertNotNull(tokenFilterContext1);
        org.junit.Assert.assertNull(tokenFilterContext2);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
    }

    @Test
    public void test0261() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0261");
        byte[] byteArray2 = new byte[] { (byte) -17 };
        // The following exception was thrown during execution in test generation
        try {
            int int4 = tools.jackson.core.io.NumberOutput.outputLong(0L, byteArray2, 1);
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: 1");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray2);
        org.junit.Assert.assertArrayEquals(byteArray2, new byte[] { (byte) -17 });
    }

    @Test
    public void test0262() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0262");
        java.lang.String str0 = tools.jackson.core.util.Separators.DEFAULT_OBJECT_EMPTY_SEPARATOR;
        org.junit.Assert.assertEquals("'" + str0 + "' != '" + " " + "'", str0, " ");
    }

    @Test
    public void test0263() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0263");
        tools.jackson.core.JacksonException.Reference reference2 = new tools.jackson.core.JacksonException.Reference((java.lang.Object) "hi!", (-149));
        java.lang.String str3 = reference2.getPropertyName();
        org.junit.Assert.assertNull(str3);
    }

    @Test
    public void test0264() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0264");
        tools.jackson.core.ObjectReadContext objectReadContext0 = null;
        tools.jackson.core.io.IOContext iOContext1 = null;
        java.io.InputStream inputStream4 = null;
        tools.jackson.core.sym.ByteQuadsCanonicalizer byteQuadsCanonicalizer5 = tools.jackson.core.sym.ByteQuadsCanonicalizer.createRoot();
        tools.jackson.core.Base64Variant base64Variant6 = tools.jackson.core.Base64Variants.MODIFIED_FOR_URL;
        byte[] byteArray14 = new byte[] { (byte) 10, (byte) -1, (byte) -1, (byte) -1, (byte) -1 };
        int int16 = base64Variant6.encodeBase64Partial(10, (int) 'a', byteArray14, (int) (byte) 1);
        tools.jackson.core.Base64Variant base64Variant19 = new tools.jackson.core.Base64Variant(base64Variant6, "hi!", 0);
        tools.jackson.core.Base64Variant base64Variant20 = base64Variant6.withPaddingForbidden();
        boolean boolean21 = base64Variant6.acceptsPaddingOnRead();
        char char23 = base64Variant6.encodeBase64BitsAsChar(1);
        tools.jackson.core.io.IOContext iOContext24 = null;
        java.io.InputStream inputStream25 = null;
        byte[] byteArray29 = new byte[] { (byte) 100, (byte) 1 };
        tools.jackson.core.io.UTF32Reader uTF32Reader33 = new tools.jackson.core.io.UTF32Reader(iOContext24, inputStream25, false, byteArray29, (int) (short) 0, (-1), false);
        java.lang.String str34 = base64Variant6.encode(byteArray29);
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.json.UTF8StreamJsonParser uTF8StreamJsonParser39 = new tools.jackson.core.json.UTF8StreamJsonParser(objectReadContext0, iOContext1, (-149), (int) '/', inputStream4, byteQuadsCanonicalizer5, byteArray29, 500, 309, (-1), true);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteQuadsCanonicalizer5);
        org.junit.Assert.assertNotNull(base64Variant6);
        org.junit.Assert.assertNotNull(byteArray14);
        org.junit.Assert.assertArrayEquals(byteArray14, new byte[] { (byte) 10, (byte) 65, (byte) 65, (byte) -1, (byte) -1 });
        org.junit.Assert.assertTrue("'" + int16 + "' != '" + 3 + "'", int16 == 3);
        org.junit.Assert.assertNotNull(base64Variant20);
        org.junit.Assert.assertTrue("'" + boolean21 + "' != '" + false + "'", boolean21 == false);
        org.junit.Assert.assertTrue("'" + char23 + "' != '" + 'B' + "'", char23 == 'B');
        org.junit.Assert.assertNotNull(byteArray29);
        org.junit.Assert.assertArrayEquals(byteArray29, new byte[] { (byte) 100, (byte) 1 });
        org.junit.Assert.assertEquals("'" + str34 + "' != '" + "ZAE" + "'", str34, "ZAE");
    }

    @Test
    public void test0265() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0265");
        tools.jackson.core.io.JsonStringEncoder jsonStringEncoder0 = new tools.jackson.core.io.JsonStringEncoder();
    }

    @Test
    public void test0266() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0266");
        int int0 = tools.jackson.core.io.UTF8Writer.SURR1_LAST;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 56319 + "'", int0 == 56319);
    }

    @Test
    public void test0267() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0267");
        tools.jackson.core.filter.TokenFilter tokenFilter0 = null;
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext1 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter0);
        tools.jackson.core.JsonToken jsonToken2 = tokenFilterContext1.nextTokenToRead();
        tools.jackson.core.filter.TokenFilter tokenFilter3 = tools.jackson.core.filter.TokenFilter.INCLUDE_ALL;
        tools.jackson.core.filter.TokenFilter tokenFilter4 = tokenFilterContext1.checkValue(tokenFilter3);
        java.math.BigDecimal bigDecimal5 = null;
        boolean boolean6 = tokenFilter3.includeNumber(bigDecimal5);
        tools.jackson.core.filter.TokenFilter tokenFilter8 = tokenFilter3.includeRootValue((int) (byte) 1);
        boolean boolean10 = tokenFilter3.includeString("");
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext11 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter3);
        tools.jackson.core.JsonGenerator jsonGenerator12 = null;
        tokenFilterContext11.writePath(jsonGenerator12);
        org.junit.Assert.assertNotNull(tokenFilterContext1);
        org.junit.Assert.assertNull(jsonToken2);
        org.junit.Assert.assertNotNull(tokenFilter3);
        org.junit.Assert.assertNotNull(tokenFilter4);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + true + "'", boolean6 == true);
        org.junit.Assert.assertNotNull(tokenFilter8);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
        org.junit.Assert.assertNotNull(tokenFilterContext11);
    }

    @Test
    public void test0268() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0268");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        tools.jackson.core.filter.TokenFilter tokenFilter6 = filteringParserDelegate5.getFilter();
        tools.jackson.core.StreamReadFeature streamReadFeature7 = null;
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean8 = filteringParserDelegate5.isEnabled(streamReadFeature7);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(tokenFilter6);
    }

    @Test
    public void test0269() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0269");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        tools.jackson.core.filter.TokenFilter tokenFilter6 = filteringParserDelegate5.getFilter();
        boolean boolean7 = filteringParserDelegate5.isExpectedStartArrayToken();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Number number8 = filteringParserDelegate5.getNumberValue();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(tokenFilter6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
    }

    @Test
    public void test0270() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0270");
        tools.jackson.core.Version version0 = tools.jackson.core.Version.unknownVersion();
        int int1 = version0.getPatchLevel();
        java.lang.String str2 = version0.getArtifactId();
        org.junit.Assert.assertNotNull(version0);
        org.junit.Assert.assertTrue("'" + int1 + "' != '" + 0 + "'", int1 == 0);
        org.junit.Assert.assertEquals("'" + str2 + "' != '" + "" + "'", str2, "");
    }

    @Test
    public void test0271() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0271");
        tools.jackson.core.json.DupDetector dupDetector0 = null;
        tools.jackson.core.json.JsonWriteContext jsonWriteContext1 = tools.jackson.core.json.JsonWriteContext.createRootContext(dupDetector0);
        boolean boolean2 = jsonWriteContext1.hasCurrentName();
        tools.jackson.core.JsonGenerator jsonGenerator5 = null;
        tools.jackson.core.json.DupDetector dupDetector6 = tools.jackson.core.json.DupDetector.rootDetector(jsonGenerator5);
        tools.jackson.core.json.JsonReadContext jsonReadContext7 = tools.jackson.core.json.JsonReadContext.createRootContext(56, 7, dupDetector6);
        tools.jackson.core.json.JsonWriteContext jsonWriteContext8 = jsonWriteContext1.withDupDetector(dupDetector6);
        tools.jackson.core.JsonParser jsonParser9 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter10 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion11 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate14 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser9, tokenFilter10, inclusion11, true, true);
        tools.jackson.core.filter.TokenFilter tokenFilter15 = filteringParserDelegate14.getFilter();
        boolean boolean16 = filteringParserDelegate14.isExpectedStartArrayToken();
        boolean boolean17 = filteringParserDelegate14.hasCurrentToken();
        tools.jackson.core.json.DupDetector dupDetector18 = tools.jackson.core.json.DupDetector.rootDetector((tools.jackson.core.JsonParser) filteringParserDelegate14);
        tools.jackson.core.json.JsonWriteContext jsonWriteContext19 = jsonWriteContext1.withDupDetector(dupDetector18);
        boolean boolean20 = jsonWriteContext1.inRoot();
        org.junit.Assert.assertNotNull(jsonWriteContext1);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(dupDetector6);
        org.junit.Assert.assertNotNull(jsonReadContext7);
        org.junit.Assert.assertNotNull(jsonWriteContext8);
        org.junit.Assert.assertNull(tokenFilter15);
        org.junit.Assert.assertTrue("'" + boolean16 + "' != '" + false + "'", boolean16 == false);
        org.junit.Assert.assertTrue("'" + boolean17 + "' != '" + false + "'", boolean17 == false);
        org.junit.Assert.assertNotNull(dupDetector18);
        org.junit.Assert.assertNotNull(jsonWriteContext19);
        org.junit.Assert.assertTrue("'" + boolean20 + "' != '" + true + "'", boolean20 == true);
    }

    @Test
    public void test0272() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0272");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        tools.jackson.core.filter.TokenFilter tokenFilter6 = filteringParserDelegate5.getFilter();
        boolean boolean7 = filteringParserDelegate5.isExpectedStartArrayToken();
        boolean boolean8 = filteringParserDelegate5.hasCurrentToken();
        filteringParserDelegate5.clearCurrentToken();
        tools.jackson.core.io.ContentReference contentReference11 = tools.jackson.core.io.ContentReference.redacted();
        tools.jackson.core.TokenStreamLocation tokenStreamLocation15 = new tools.jackson.core.TokenStreamLocation(contentReference11, (long) (short) 10, 33, 33);
        tools.jackson.core.exc.StreamReadException streamReadException16 = new tools.jackson.core.exc.StreamReadException((tools.jackson.core.JsonParser) filteringParserDelegate5, "CkFB__8", tokenStreamLocation15);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str18 = filteringParserDelegate5.getValueAsString("0.0.0");
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(tokenFilter6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertNotNull(contentReference11);
    }

    @Test
    public void test0273() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0273");
        tools.jackson.core.JsonGenerator jsonGenerator0 = null;
        java.lang.Throwable throwable2 = null;
        tools.jackson.core.exc.StreamWriteException streamWriteException3 = new tools.jackson.core.exc.StreamWriteException(jsonGenerator0, "hi!", throwable2);
        tools.jackson.core.json.DupDetector dupDetector4 = null;
        tools.jackson.core.util.SimpleStreamWriteContext simpleStreamWriteContext5 = tools.jackson.core.util.SimpleStreamWriteContext.createRootContext(dupDetector4);
        boolean boolean6 = simpleStreamWriteContext5.writeValue();
        tools.jackson.core.JacksonException jacksonException8 = tools.jackson.core.JacksonException.wrapWithPath((java.lang.Throwable) streamWriteException3, (java.lang.Object) boolean6, "hi!");
        tools.jackson.core.JsonGenerator jsonGenerator9 = null;
        tools.jackson.core.exc.StreamWriteException streamWriteException10 = streamWriteException3.withGenerator(jsonGenerator9);
        tools.jackson.core.Base64Variant base64Variant11 = tools.jackson.core.Base64Variants.MODIFIED_FOR_URL;
        byte[] byteArray19 = new byte[] { (byte) 10, (byte) -1, (byte) -1, (byte) -1, (byte) -1 };
        int int21 = base64Variant11.encodeBase64Partial(10, (int) 'a', byteArray19, (int) (byte) 1);
        tools.jackson.core.JacksonException jacksonException23 = streamWriteException3.prependPath((java.lang.Object) 10, "100.0");
        org.junit.Assert.assertNotNull(simpleStreamWriteContext5);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + true + "'", boolean6 == true);
        org.junit.Assert.assertNotNull(jacksonException8);
        org.junit.Assert.assertNotNull(streamWriteException10);
        org.junit.Assert.assertNotNull(base64Variant11);
        org.junit.Assert.assertNotNull(byteArray19);
        org.junit.Assert.assertArrayEquals(byteArray19, new byte[] { (byte) 10, (byte) 65, (byte) 65, (byte) -1, (byte) -1 });
        org.junit.Assert.assertTrue("'" + int21 + "' != '" + 3 + "'", int21 == 3);
        org.junit.Assert.assertNotNull(jacksonException23);
    }

    @Test
    public void test0274() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0274");
        tools.jackson.core.util.Separators.Spacing spacing0 = tools.jackson.core.util.Separators.Spacing.BOTH;
        org.junit.Assert.assertTrue("'" + spacing0 + "' != '" + tools.jackson.core.util.Separators.Spacing.BOTH + "'", spacing0.equals(tools.jackson.core.util.Separators.Spacing.BOTH));
    }

    @Test
    public void test0275() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0275");
        tools.jackson.core.json.DupDetector dupDetector0 = null;
        tools.jackson.core.util.SimpleStreamWriteContext simpleStreamWriteContext1 = tools.jackson.core.util.SimpleStreamWriteContext.createRootContext(dupDetector0);
        tools.jackson.core.json.DupDetector dupDetector2 = simpleStreamWriteContext1.getDupDetector();
        tools.jackson.core.json.DupDetector dupDetector3 = null;
        tools.jackson.core.util.SimpleStreamWriteContext simpleStreamWriteContext4 = simpleStreamWriteContext1.withDupDetector(dupDetector3);
        java.lang.String str5 = simpleStreamWriteContext4.typeDesc();
        boolean boolean6 = simpleStreamWriteContext4.writeValue();
        java.lang.String str7 = simpleStreamWriteContext4.currentName();
        org.junit.Assert.assertNotNull(simpleStreamWriteContext1);
        org.junit.Assert.assertNull(dupDetector2);
        org.junit.Assert.assertNotNull(simpleStreamWriteContext4);
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "root" + "'", str5, "root");
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + true + "'", boolean6 == true);
        org.junit.Assert.assertNull(str7);
    }

    @Test
    public void test0276() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0276");
        java.util.concurrent.ConcurrentHashMap.KeySetView<tools.jackson.core.sym.ByteQuadsCanonicalizer, java.lang.Boolean> byteQuadsCanonicalizerSet1 = java.util.concurrent.ConcurrentHashMap.newKeySet((int) '4');
        org.junit.Assert.assertNotNull(byteQuadsCanonicalizerSet1);
    }

    @Test
    public void test0277() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0277");
        tools.jackson.core.io.ContentReference contentReference0 = null;
        tools.jackson.core.TokenStreamLocation tokenStreamLocation4 = new tools.jackson.core.TokenStreamLocation(contentReference0, (long) (short) 10, 33, (int) (byte) 100);
        java.lang.StringBuilder stringBuilder5 = null;
        // The following exception was thrown during execution in test generation
        try {
            java.lang.StringBuilder stringBuilder6 = tokenStreamLocation4.toString(stringBuilder5);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0278() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0278");
        tools.jackson.core.filter.TokenFilter tokenFilter0 = null;
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext1 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter0);
        tools.jackson.core.JsonToken jsonToken2 = tokenFilterContext1.nextTokenToRead();
        tools.jackson.core.filter.TokenFilter tokenFilter3 = tools.jackson.core.filter.TokenFilter.INCLUDE_ALL;
        tools.jackson.core.filter.TokenFilter tokenFilter4 = tokenFilterContext1.checkValue(tokenFilter3);
        java.math.BigDecimal bigDecimal5 = null;
        boolean boolean6 = tokenFilter3.includeNumber(bigDecimal5);
        tools.jackson.core.filter.TokenFilter tokenFilter8 = tokenFilter3.includeRootValue((int) (byte) 1);
        boolean boolean10 = tokenFilter3.includeString("");
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext11 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter3);
        boolean boolean13 = tokenFilter3.includeEmptyArray(true);
        org.junit.Assert.assertNotNull(tokenFilterContext1);
        org.junit.Assert.assertNull(jsonToken2);
        org.junit.Assert.assertNotNull(tokenFilter3);
        org.junit.Assert.assertNotNull(tokenFilter4);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + true + "'", boolean6 == true);
        org.junit.Assert.assertNotNull(tokenFilter8);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
        org.junit.Assert.assertNotNull(tokenFilterContext11);
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
    }

    @Test
    public void test0279() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0279");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        boolean boolean6 = filteringParserDelegate5.isExpectedStartObjectToken();
        int int7 = filteringParserDelegate5.currentTokenId();
        tools.jackson.core.JsonToken jsonToken8 = null;
        boolean boolean9 = filteringParserDelegate5.hasToken(jsonToken8);
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.StreamReadConstraints streamReadConstraints10 = filteringParserDelegate5.streamReadConstraints();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 0 + "'", int7 == 0);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + true + "'", boolean9 == true);
    }

    @Test
    public void test0280() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0280");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        tools.jackson.core.filter.TokenFilter tokenFilter6 = filteringParserDelegate5.getFilter();
        boolean boolean7 = filteringParserDelegate5.isExpectedStartArrayToken();
        boolean boolean8 = filteringParserDelegate5.hasCurrentToken();
        filteringParserDelegate5.clearCurrentToken();
        boolean boolean10 = filteringParserDelegate5.isExpectedStartObjectToken();
        tools.jackson.core.util.BufferRecycler bufferRecycler11 = null;
        tools.jackson.core.util.ByteArrayBuilder byteArrayBuilder12 = new tools.jackson.core.util.ByteArrayBuilder(bufferRecycler11);
        tools.jackson.core.util.BufferRecycler bufferRecycler13 = byteArrayBuilder12.bufferRecycler();
        byte[] byteArray14 = byteArrayBuilder12.getClearAndRelease();
        // The following exception was thrown during execution in test generation
        try {
            int int15 = filteringParserDelegate5.readBinaryValue((java.io.OutputStream) byteArrayBuilder12);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(tokenFilter6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        org.junit.Assert.assertNull(bufferRecycler13);
        org.junit.Assert.assertNotNull(byteArray14);
        org.junit.Assert.assertArrayEquals(byteArray14, new byte[] {});
    }

    @Test
    public void test0281() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0281");
        tools.jackson.core.json.DupDetector dupDetector0 = null;
        tools.jackson.core.json.JsonWriteContext jsonWriteContext1 = tools.jackson.core.json.JsonWriteContext.createRootContext(dupDetector0);
        boolean boolean2 = jsonWriteContext1.hasCurrentName();
        tools.jackson.core.JsonGenerator jsonGenerator5 = null;
        tools.jackson.core.json.DupDetector dupDetector6 = tools.jackson.core.json.DupDetector.rootDetector(jsonGenerator5);
        tools.jackson.core.json.JsonReadContext jsonReadContext7 = tools.jackson.core.json.JsonReadContext.createRootContext(56, 7, dupDetector6);
        tools.jackson.core.json.JsonWriteContext jsonWriteContext8 = jsonWriteContext1.withDupDetector(dupDetector6);
        tools.jackson.core.JsonParser jsonParser9 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter10 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion11 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate14 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser9, tokenFilter10, inclusion11, true, true);
        tools.jackson.core.filter.TokenFilter tokenFilter15 = filteringParserDelegate14.getFilter();
        boolean boolean16 = filteringParserDelegate14.isExpectedStartArrayToken();
        boolean boolean17 = filteringParserDelegate14.hasCurrentToken();
        tools.jackson.core.json.DupDetector dupDetector18 = tools.jackson.core.json.DupDetector.rootDetector((tools.jackson.core.JsonParser) filteringParserDelegate14);
        tools.jackson.core.json.JsonWriteContext jsonWriteContext19 = jsonWriteContext1.withDupDetector(dupDetector18);
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.TokenStreamLocation tokenStreamLocation20 = dupDetector18.findLocation();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(jsonWriteContext1);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(dupDetector6);
        org.junit.Assert.assertNotNull(jsonReadContext7);
        org.junit.Assert.assertNotNull(jsonWriteContext8);
        org.junit.Assert.assertNull(tokenFilter15);
        org.junit.Assert.assertTrue("'" + boolean16 + "' != '" + false + "'", boolean16 == false);
        org.junit.Assert.assertTrue("'" + boolean17 + "' != '" + false + "'", boolean17 == false);
        org.junit.Assert.assertNotNull(dupDetector18);
        org.junit.Assert.assertNotNull(jsonWriteContext19);
    }

    @Test
    public void test0282() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0282");
        tools.jackson.core.io.ContentReference contentReference2 = tools.jackson.core.io.ContentReference.rawReference(true, (java.lang.Object) 10.0f);
        java.lang.String str3 = contentReference2.buildSourceDescription();
        org.junit.Assert.assertNotNull(contentReference2);
        org.junit.Assert.assertEquals("'" + str3 + "' != '" + "(Float)" + "'", str3, "(Float)");
    }

    @Test
    public void test0283() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0283");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        java.io.Writer writer6 = null;
        // The following exception was thrown during execution in test generation
        try {
            long long7 = filteringParserDelegate5.readString(writer6);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0284() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0284");
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder0 = tools.jackson.core.json.JsonFactory.builderWithJackson2Defaults();
        tools.jackson.core.SerializableString serializableString1 = null;
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder2 = jsonFactoryBuilder0.rootValueSeparator(serializableString1);
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder3 = jsonFactoryBuilder0.configureForJackson2();
        tools.jackson.core.util.JsonpCharacterEscapes jsonpCharacterEscapes4 = tools.jackson.core.util.JsonpCharacterEscapes.instance();
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder5 = jsonFactoryBuilder3.characterEscapes((tools.jackson.core.io.CharacterEscapes) jsonpCharacterEscapes4);
        tools.jackson.core.io.InputDecorator inputDecorator6 = jsonFactoryBuilder5.inputDecorator();
        org.junit.Assert.assertNotNull(jsonFactoryBuilder0);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder2);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder3);
        org.junit.Assert.assertNotNull(jsonpCharacterEscapes4);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder5);
        org.junit.Assert.assertNull(inputDecorator6);
    }

    @Test
    public void test0285() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0285");
        tools.jackson.core.util.BufferRecycler bufferRecycler0 = null;
        tools.jackson.core.util.ByteArrayBuilder byteArrayBuilder1 = new tools.jackson.core.util.ByteArrayBuilder(bufferRecycler0);
        byte[] byteArray3 = byteArrayBuilder1.completeAndCoalesce(10);
        byteArrayBuilder1.close();
        byteArrayBuilder1.appendTwoBytes(1);
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertArrayEquals(byteArray3, new byte[] { (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0 });
    }

    @Test
    public void test0286() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0286");
        tools.jackson.core.Version version0 = tools.jackson.core.Version.unknownVersion();
        boolean boolean1 = version0.isUnknownVersion();
        java.lang.String str2 = version0.getArtifactId();
        org.junit.Assert.assertNotNull(version0);
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + true + "'", boolean1 == true);
        org.junit.Assert.assertEquals("'" + str2 + "' != '" + "" + "'", str2, "");
    }

    @Test
    public void test0287() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0287");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        boolean boolean6 = filteringParserDelegate5.isExpectedStartObjectToken();
        int int7 = filteringParserDelegate5.currentTokenId();
        boolean boolean8 = filteringParserDelegate5.isExpectedStartObjectToken();
        tools.jackson.core.JsonParser jsonParser9 = filteringParserDelegate5.skipChildren();
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean10 = filteringParserDelegate5.isExpectedNumberIntToken();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 0 + "'", int7 == 0);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertNotNull(jsonParser9);
    }

    @Test
    public void test0288() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0288");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean6 = filteringParserDelegate5.getValueAsBoolean();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0289() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0289");
        java.io.DataOutput dataOutput0 = null;
        tools.jackson.core.io.DataOutputAsStream dataOutputAsStream1 = new tools.jackson.core.io.DataOutputAsStream(dataOutput0);
        tools.jackson.core.Base64Variant base64Variant2 = tools.jackson.core.Base64Variants.MODIFIED_FOR_URL;
        byte[] byteArray10 = new byte[] { (byte) 10, (byte) -1, (byte) -1, (byte) -1, (byte) -1 };
        int int12 = base64Variant2.encodeBase64Partial(10, (int) 'a', byteArray10, (int) (byte) 1);
        tools.jackson.core.Base64Variant base64Variant15 = new tools.jackson.core.Base64Variant(base64Variant2, "hi!", 0);
        tools.jackson.core.Base64Variant base64Variant16 = base64Variant2.withPaddingForbidden();
        java.lang.String str17 = base64Variant16.missingPaddingMessage();
        tools.jackson.core.util.BufferRecycler bufferRecycler18 = null;
        tools.jackson.core.util.ByteArrayBuilder byteArrayBuilder19 = new tools.jackson.core.util.ByteArrayBuilder(bufferRecycler18);
        byte[] byteArray21 = byteArrayBuilder19.completeAndCoalesce(10);
        byte[] byteArray22 = byteArrayBuilder19.toByteArray();
        java.lang.String str25 = base64Variant16.encode(byteArray22, false, "100.0");
        // The following exception was thrown during execution in test generation
        try {
            dataOutputAsStream1.write(byteArray22, 53, 10);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(base64Variant2);
        org.junit.Assert.assertNotNull(byteArray10);
        org.junit.Assert.assertArrayEquals(byteArray10, new byte[] { (byte) 10, (byte) 65, (byte) 65, (byte) -1, (byte) -1 });
        org.junit.Assert.assertTrue("'" + int12 + "' != '" + 3 + "'", int12 == 3);
        org.junit.Assert.assertNotNull(base64Variant16);
        org.junit.Assert.assertEquals("'" + str17 + "' != '" + "Unexpected end of base64-encoded String: base64 variant 'MODIFIED-FOR-URL' expects padding (one or more '\000' characters) at the end. This Base64Variant might have been incorrectly configured" + "'", str17, "Unexpected end of base64-encoded String: base64 variant 'MODIFIED-FOR-URL' expects padding (one or more '\000' characters) at the end. This Base64Variant might have been incorrectly configured");
        org.junit.Assert.assertNotNull(byteArray21);
        org.junit.Assert.assertArrayEquals(byteArray21, new byte[] { (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0 });
        org.junit.Assert.assertNotNull(byteArray22);
        org.junit.Assert.assertArrayEquals(byteArray22, new byte[] { (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str25 + "' != '" + "AAAAAAAAAAAAAA" + "'", str25, "AAAAAAAAAAAAAA");
    }

    @Test
    public void test0290() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0290");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        boolean boolean6 = filteringParserDelegate5.isExpectedStartObjectToken();
        int int7 = filteringParserDelegate5.currentTokenId();
        boolean boolean8 = filteringParserDelegate5.isExpectedStartObjectToken();
        tools.jackson.core.JsonParser jsonParser9 = filteringParserDelegate5.skipChildren();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj10 = filteringParserDelegate5.currentValue();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 0 + "'", int7 == 0);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertNotNull(jsonParser9);
    }

    @Test
    public void test0291() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0291");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str6 = filteringParserDelegate5.nextStringValue();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0292() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0292");
        tools.jackson.core.util.InternCache internCache0 = tools.jackson.core.util.InternCache.instance;
        boolean boolean1 = internCache0.isEmpty();
        org.junit.Assert.assertNotNull(internCache0);
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + true + "'", boolean1 == true);
    }

    @Test
    public void test0293() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0293");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        boolean boolean6 = filteringParserDelegate5.isExpectedStartObjectToken();
        int int7 = filteringParserDelegate5.currentTokenId();
        boolean boolean8 = filteringParserDelegate5.isExpectedStartObjectToken();
        tools.jackson.core.JsonParser jsonParser9 = filteringParserDelegate5.skipChildren();
        tools.jackson.core.util.JsonParserDelegate jsonParserDelegate10 = new tools.jackson.core.util.JsonParserDelegate((tools.jackson.core.JsonParser) filteringParserDelegate5);
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.Version version11 = jsonParserDelegate10.version();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 0 + "'", int7 == 0);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertNotNull(jsonParser9);
    }

    @Test
    public void test0294() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0294");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        boolean boolean6 = filteringParserDelegate5.isExpectedStartObjectToken();
        int int7 = filteringParserDelegate5.currentTokenId();
        boolean boolean8 = filteringParserDelegate5.isExpectedStartObjectToken();
        tools.jackson.core.JsonParser jsonParser9 = filteringParserDelegate5.skipChildren();
        tools.jackson.core.util.JsonParserDelegate jsonParserDelegate10 = new tools.jackson.core.util.JsonParserDelegate((tools.jackson.core.JsonParser) filteringParserDelegate5);
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.JsonToken jsonToken11 = filteringParserDelegate5.nextValue();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 0 + "'", int7 == 0);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertNotNull(jsonParser9);
    }

    @Test
    public void test0295() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0295");
        tools.jackson.core.util.RecyclerPool<tools.jackson.core.util.BufferRecycler> bufferRecyclerRecyclerPool0 = tools.jackson.core.util.JsonRecyclerPools.nonRecyclingPool();
        org.junit.Assert.assertNotNull(bufferRecyclerRecyclerPool0);
    }

    @Test
    public void test0296() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0296");
        tools.jackson.core.JsonGenerator jsonGenerator0 = null;
        tools.jackson.core.json.DupDetector dupDetector1 = tools.jackson.core.json.DupDetector.rootDetector(jsonGenerator0);
        dupDetector1.reset();
        tools.jackson.core.TokenStreamLocation tokenStreamLocation3 = dupDetector1.findLocation();
        org.junit.Assert.assertNotNull(dupDetector1);
        org.junit.Assert.assertNull(tokenStreamLocation3);
    }

    @Test
    public void test0297() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0297");
        tools.jackson.core.json.DupDetector dupDetector0 = null;
        tools.jackson.core.util.SimpleStreamWriteContext simpleStreamWriteContext1 = tools.jackson.core.util.SimpleStreamWriteContext.createRootContext(dupDetector0);
        tools.jackson.core.json.DupDetector dupDetector2 = simpleStreamWriteContext1.getDupDetector();
        tools.jackson.core.json.DupDetector dupDetector3 = null;
        tools.jackson.core.util.SimpleStreamWriteContext simpleStreamWriteContext4 = simpleStreamWriteContext1.withDupDetector(dupDetector3);
        java.lang.String str5 = simpleStreamWriteContext4.typeDesc();
        boolean boolean6 = simpleStreamWriteContext4.writeValue();
        boolean boolean7 = simpleStreamWriteContext4.hasPathSegment();
        tools.jackson.core.json.DupDetector dupDetector8 = simpleStreamWriteContext4.getDupDetector();
        org.junit.Assert.assertNotNull(simpleStreamWriteContext1);
        org.junit.Assert.assertNull(dupDetector2);
        org.junit.Assert.assertNotNull(simpleStreamWriteContext4);
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "root" + "'", str5, "root");
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + true + "'", boolean6 == true);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertNull(dupDetector8);
    }

    @Test
    public void test0298() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0298");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        tools.jackson.core.filter.TokenFilter tokenFilter6 = filteringParserDelegate5.getFilter();
        boolean boolean7 = filteringParserDelegate5.isExpectedStartArrayToken();
        boolean boolean8 = filteringParserDelegate5.hasCurrentToken();
        tools.jackson.core.TokenStreamContext tokenStreamContext9 = filteringParserDelegate5.streamReadContext();
        tools.jackson.core.JsonParser jsonParser10 = filteringParserDelegate5.delegate();
        // The following exception was thrown during execution in test generation
        try {
            int int11 = filteringParserDelegate5.getIntValue();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(tokenFilter6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertNotNull(tokenStreamContext9);
        org.junit.Assert.assertNull(jsonParser10);
    }

    @Test
    public void test0299() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0299");
        tools.jackson.core.JsonToken jsonToken1 = null;
        tools.jackson.core.type.WritableTypeId writableTypeId3 = new tools.jackson.core.type.WritableTypeId((java.lang.Object) '#', jsonToken1, (java.lang.Object) ' ');
        tools.jackson.core.type.WritableTypeId.Inclusion inclusion4 = tools.jackson.core.type.WritableTypeId.Inclusion.PAYLOAD_PROPERTY;
        writableTypeId3.include = inclusion4;
        tools.jackson.core.type.WritableTypeId.Inclusion inclusion6 = writableTypeId3.include;
        org.junit.Assert.assertTrue("'" + inclusion4 + "' != '" + tools.jackson.core.type.WritableTypeId.Inclusion.PAYLOAD_PROPERTY + "'", inclusion4.equals(tools.jackson.core.type.WritableTypeId.Inclusion.PAYLOAD_PROPERTY));
        org.junit.Assert.assertTrue("'" + inclusion6 + "' != '" + tools.jackson.core.type.WritableTypeId.Inclusion.PAYLOAD_PROPERTY + "'", inclusion6.equals(tools.jackson.core.type.WritableTypeId.Inclusion.PAYLOAD_PROPERTY));
    }

    @Test
    public void test0300() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0300");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        // The following exception was thrown during execution in test generation
        try {
            long long6 = filteringParserDelegate5.getValueAsLong();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0301() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0301");
        tools.jackson.core.type.WritableTypeId writableTypeId0 = new tools.jackson.core.type.WritableTypeId();
    }

    @Test
    public void test0302() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0302");
        tools.jackson.core.util.BufferRecycler bufferRecycler0 = null;
        tools.jackson.core.util.TextBuffer textBuffer1 = new tools.jackson.core.util.TextBuffer(bufferRecycler0);
        textBuffer1.resetWithString("");
        boolean boolean4 = textBuffer1.hasTextAsCharacters();
        java.lang.String str5 = textBuffer1.toString();
        textBuffer1.resetWithString("//0.0.0");
        textBuffer1.releaseBuffers();
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "" + "'", str5, "");
    }

    @Test
    public void test0303() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0303");
        tools.jackson.core.JsonGenerator jsonGenerator0 = null;
        java.lang.Throwable throwable2 = null;
        tools.jackson.core.exc.StreamWriteException streamWriteException3 = new tools.jackson.core.exc.StreamWriteException(jsonGenerator0, "hi!", throwable2);
        java.lang.StringBuilder stringBuilder4 = null;
        java.lang.StringBuilder stringBuilder5 = streamWriteException3.getPathReference(stringBuilder4);
        java.util.List<tools.jackson.core.JacksonException.Reference> referenceList6 = streamWriteException3.getPath();
        org.junit.Assert.assertNull(stringBuilder5);
        org.junit.Assert.assertNotNull(referenceList6);
    }

    @Test
    public void test0304() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0304");
        int int0 = tools.jackson.core.io.schubfach.FloatToDecimal.E_MIN;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + (-44) + "'", int0 == (-44));
    }

    @Test
    public void test0305() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0305");
        java.io.DataOutput dataOutput0 = null;
        tools.jackson.core.io.DataOutputAsStream dataOutputAsStream1 = new tools.jackson.core.io.DataOutputAsStream(dataOutput0);
        tools.jackson.core.io.SerializedString serializedString2 = tools.jackson.core.PrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
        byte[] byteArray3 = serializedString2.asQuotedUTF8();
        // The following exception was thrown during execution in test generation
        try {
            dataOutputAsStream1.write(byteArray3);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(serializedString2);
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertArrayEquals(byteArray3, new byte[] { (byte) 32 });
    }

    @Test
    public void test0306() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0306");
        tools.jackson.core.Base64Variant base64Variant0 = tools.jackson.core.Base64Variants.MODIFIED_FOR_URL;
        byte[] byteArray8 = new byte[] { (byte) 10, (byte) -1, (byte) -1, (byte) -1, (byte) -1 };
        int int10 = base64Variant0.encodeBase64Partial(10, (int) 'a', byteArray8, (int) (byte) 1);
        tools.jackson.core.Base64Variant base64Variant13 = new tools.jackson.core.Base64Variant(base64Variant0, "hi!", 0);
        boolean boolean14 = base64Variant0.requiresPaddingOnRead();
        tools.jackson.core.Base64Variant base64Variant16 = tools.jackson.core.Base64Variants.MODIFIED_FOR_URL;
        byte[] byteArray24 = new byte[] { (byte) 10, (byte) -1, (byte) -1, (byte) -1, (byte) -1 };
        int int26 = base64Variant16.encodeBase64Partial(10, (int) 'a', byteArray24, (int) (byte) 1);
        tools.jackson.core.Base64Variant base64Variant29 = new tools.jackson.core.Base64Variant(base64Variant16, "hi!", 0);
        tools.jackson.core.Base64Variant base64Variant30 = base64Variant16.withPaddingForbidden();
        boolean boolean31 = base64Variant16.acceptsPaddingOnRead();
        char char33 = base64Variant16.encodeBase64BitsAsChar(1);
        tools.jackson.core.io.IOContext iOContext34 = null;
        java.io.InputStream inputStream35 = null;
        byte[] byteArray39 = new byte[] { (byte) 100, (byte) 1 };
        tools.jackson.core.io.UTF32Reader uTF32Reader43 = new tools.jackson.core.io.UTF32Reader(iOContext34, inputStream35, false, byteArray39, (int) (short) 0, (-1), false);
        java.lang.String str44 = base64Variant16.encode(byteArray39);
        // The following exception was thrown during execution in test generation
        try {
            int int46 = base64Variant0.encodeBase64Chunk((int) (byte) -17, byteArray39, 309);
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: 309");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(base64Variant0);
        org.junit.Assert.assertNotNull(byteArray8);
        org.junit.Assert.assertArrayEquals(byteArray8, new byte[] { (byte) 10, (byte) 65, (byte) 65, (byte) -1, (byte) -1 });
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 3 + "'", int10 == 3);
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + false + "'", boolean14 == false);
        org.junit.Assert.assertNotNull(base64Variant16);
        org.junit.Assert.assertNotNull(byteArray24);
        org.junit.Assert.assertArrayEquals(byteArray24, new byte[] { (byte) 10, (byte) 65, (byte) 65, (byte) -1, (byte) -1 });
        org.junit.Assert.assertTrue("'" + int26 + "' != '" + 3 + "'", int26 == 3);
        org.junit.Assert.assertNotNull(base64Variant30);
        org.junit.Assert.assertTrue("'" + boolean31 + "' != '" + false + "'", boolean31 == false);
        org.junit.Assert.assertTrue("'" + char33 + "' != '" + 'B' + "'", char33 == 'B');
        org.junit.Assert.assertNotNull(byteArray39);
        org.junit.Assert.assertArrayEquals(byteArray39, new byte[] { (byte) 100, (byte) 1 });
        org.junit.Assert.assertEquals("'" + str44 + "' != '" + "ZAE" + "'", str44, "ZAE");
    }

    @Test
    public void test0307() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0307");
        int int0 = tools.jackson.core.json.JsonWriteContext.STATUS_OK_AFTER_COMMA;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 1 + "'", int0 == 1);
    }

    @Test
    public void test0308() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0308");
        tools.jackson.core.json.DupDetector dupDetector0 = null;
        tools.jackson.core.util.SimpleStreamWriteContext simpleStreamWriteContext1 = tools.jackson.core.util.SimpleStreamWriteContext.createRootContext(dupDetector0);
        tools.jackson.core.json.DupDetector dupDetector2 = simpleStreamWriteContext1.getDupDetector();
        java.lang.String str3 = simpleStreamWriteContext1.currentName();
        org.junit.Assert.assertNotNull(simpleStreamWriteContext1);
        org.junit.Assert.assertNull(dupDetector2);
        org.junit.Assert.assertNull(str3);
    }

    @Test
    public void test0309() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0309");
        int int0 = tools.jackson.core.base.GeneratorBase.SURR2_FIRST;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 56320 + "'", int0 == 56320);
    }

    @Test
    public void test0310() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0310");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        boolean boolean6 = filteringParserDelegate5.isExpectedStartObjectToken();
        int int7 = filteringParserDelegate5.currentTokenId();
        boolean boolean8 = filteringParserDelegate5.isExpectedStartObjectToken();
        tools.jackson.core.JsonParser jsonParser9 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter10 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion11 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate14 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser9, tokenFilter10, inclusion11, true, true);
        tools.jackson.core.filter.TokenFilter tokenFilter15 = filteringParserDelegate14.getFilter();
        boolean boolean16 = filteringParserDelegate14.isExpectedStartArrayToken();
        boolean boolean17 = filteringParserDelegate14.hasCurrentToken();
        tools.jackson.core.TokenStreamContext tokenStreamContext18 = filteringParserDelegate14.streamReadContext();
        tools.jackson.core.JsonParser jsonParser19 = filteringParserDelegate14.delegate();
        // The following exception was thrown during execution in test generation
        try {
            filteringParserDelegate5.assignCurrentValue((java.lang.Object) jsonParser19);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 0 + "'", int7 == 0);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertNull(tokenFilter15);
        org.junit.Assert.assertTrue("'" + boolean16 + "' != '" + false + "'", boolean16 == false);
        org.junit.Assert.assertTrue("'" + boolean17 + "' != '" + false + "'", boolean17 == false);
        org.junit.Assert.assertNotNull(tokenStreamContext18);
        org.junit.Assert.assertNull(jsonParser19);
    }

    @Test
    public void test0311() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0311");
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder0 = tools.jackson.core.json.JsonFactory.builderWithJackson2Defaults();
        tools.jackson.core.SerializableString serializableString1 = null;
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder2 = jsonFactoryBuilder0.rootValueSeparator(serializableString1);
        int int3 = jsonFactoryBuilder2.streamWriteFeaturesMask();
        tools.jackson.core.json.JsonReadFeature jsonReadFeature4 = null;
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder6 = jsonFactoryBuilder2.configure(jsonReadFeature4, false);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(jsonFactoryBuilder0);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder2);
        org.junit.Assert.assertTrue("'" + int3 + "' != '" + 7 + "'", int3 == 7);
    }

    @Test
    public void test0312() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0312");
        tools.jackson.core.util.DefaultPrettyPrinter defaultPrettyPrinter0 = new tools.jackson.core.util.DefaultPrettyPrinter();
        tools.jackson.core.util.DefaultPrettyPrinter.FixedSpaceIndenter fixedSpaceIndenter1 = tools.jackson.core.util.DefaultPrettyPrinter.FixedSpaceIndenter.instance();
        defaultPrettyPrinter0.indentArraysWith((tools.jackson.core.util.DefaultPrettyPrinter.Indenter) fixedSpaceIndenter1);
        tools.jackson.core.util.Separators separators3 = tools.jackson.core.util.Separators.createDefaultInstance();
        tools.jackson.core.util.Separators separators5 = separators3.withObjectEntrySeparator('4');
        tools.jackson.core.util.Separators separators7 = separators5.withArrayElementSeparator('#');
        tools.jackson.core.util.DefaultPrettyPrinter defaultPrettyPrinter8 = defaultPrettyPrinter0.withSeparators(separators7);
        tools.jackson.core.JsonGenerator jsonGenerator9 = null;
        // The following exception was thrown during execution in test generation
        try {
            defaultPrettyPrinter8.writeEndObject(jsonGenerator9, 1);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(fixedSpaceIndenter1);
        org.junit.Assert.assertNotNull(separators3);
        org.junit.Assert.assertNotNull(separators5);
        org.junit.Assert.assertNotNull(separators7);
        org.junit.Assert.assertNotNull(defaultPrettyPrinter8);
    }

    @Test
    public void test0313() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0313");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        tools.jackson.core.filter.TokenFilter tokenFilter6 = filteringParserDelegate5.getFilter();
        boolean boolean7 = filteringParserDelegate5.isExpectedStartArrayToken();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj8 = filteringParserDelegate5.currentValue();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(tokenFilter6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
    }

    @Test
    public void test0314() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0314");
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder0 = tools.jackson.core.json.JsonFactory.builderWithJackson2Defaults();
        tools.jackson.core.SerializableString serializableString1 = null;
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder2 = jsonFactoryBuilder0.rootValueSeparator(serializableString1);
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder3 = jsonFactoryBuilder0.configureForJackson2();
        tools.jackson.core.io.InputDecorator inputDecorator4 = null;
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder5 = jsonFactoryBuilder0.inputDecorator(inputDecorator4);
        tools.jackson.core.TokenStreamFactory.Feature feature6 = null;
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder7 = jsonFactoryBuilder5.disable(feature6);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(jsonFactoryBuilder0);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder2);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder3);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder5);
    }

    @Test
    public void test0315() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0315");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        boolean boolean6 = filteringParserDelegate5.isExpectedStartObjectToken();
        int int7 = filteringParserDelegate5.currentTokenId();
        boolean boolean8 = filteringParserDelegate5.isExpectedStartObjectToken();
        tools.jackson.core.JsonParser jsonParser9 = filteringParserDelegate5.skipChildren();
        // The following exception was thrown during execution in test generation
        try {
            double double11 = filteringParserDelegate5.getValueAsDouble((double) '4');
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 0 + "'", int7 == 0);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertNotNull(jsonParser9);
    }

    @Test
    public void test0316() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0316");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj6 = filteringParserDelegate5.getTypeId();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0317() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0317");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        boolean boolean6 = filteringParserDelegate5.isExpectedStartObjectToken();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str7 = filteringParserDelegate5.nextStringValue();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
    }

    @Test
    public void test0318() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0318");
        java.util.concurrent.ConcurrentHashMap.KeySetView<tools.jackson.core.util.Named, java.lang.Boolean> namedSet1 = java.util.concurrent.ConcurrentHashMap.newKeySet(2);
        org.junit.Assert.assertNotNull(namedSet1);
    }

    @Test
    public void test0319() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0319");
        int int0 = tools.jackson.core.StreamReadConstraints.DEFAULT_MAX_NUM_LEN;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 1000 + "'", int0 == 1000);
    }

    @Test
    public void test0320() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0320");
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder0 = tools.jackson.core.json.JsonFactory.builderWithJackson2Defaults();
        tools.jackson.core.SerializableString serializableString1 = null;
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder2 = jsonFactoryBuilder0.rootValueSeparator(serializableString1);
        tools.jackson.core.io.OutputDecorator outputDecorator3 = null;
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder4 = jsonFactoryBuilder2.outputDecorator(outputDecorator3);
        int int5 = jsonFactoryBuilder2.streamWriteFeaturesMask();
        org.junit.Assert.assertNotNull(jsonFactoryBuilder0);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder2);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder4);
        org.junit.Assert.assertTrue("'" + int5 + "' != '" + 7 + "'", int5 == 7);
    }

    @Test
    public void test0321() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0321");
        long long1 = tools.jackson.core.io.schubfach.MathUtils.pow10((int) '\000');
        org.junit.Assert.assertTrue("'" + long1 + "' != '" + 1L + "'", long1 == 1L);
    }

    @Test
    public void test0322() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0322");
        int int0 = tools.jackson.core.StreamReadConstraints.DEFAULT_MAX_NAME_LEN;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 50000 + "'", int0 == 50000);
    }

    @Test
    public void test0323() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0323");
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder0 = tools.jackson.core.json.JsonFactory.builderWithJackson2Defaults();
        tools.jackson.core.SerializableString serializableString1 = null;
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder2 = jsonFactoryBuilder0.rootValueSeparator(serializableString1);
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder4 = jsonFactoryBuilder0.quoteChar(' ');
        tools.jackson.core.StreamWriteFeature streamWriteFeature5 = null;
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder6 = jsonFactoryBuilder0.enable(streamWriteFeature5);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(jsonFactoryBuilder0);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder2);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder4);
    }

    @Test
    public void test0324() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0324");
        tools.jackson.core.Base64Variant base64Variant0 = tools.jackson.core.Base64Variants.MODIFIED_FOR_URL;
        byte[] byteArray8 = new byte[] { (byte) 10, (byte) -1, (byte) -1, (byte) -1, (byte) -1 };
        int int10 = base64Variant0.encodeBase64Partial(10, (int) 'a', byteArray8, (int) (byte) 1);
        tools.jackson.core.Base64Variant base64Variant13 = new tools.jackson.core.Base64Variant(base64Variant0, "hi!", 0);
        tools.jackson.core.Base64Variant base64Variant14 = base64Variant0.withPaddingForbidden();
        boolean boolean15 = base64Variant0.acceptsPaddingOnRead();
        char char17 = base64Variant0.encodeBase64BitsAsChar(1);
        tools.jackson.core.io.IOContext iOContext18 = null;
        java.io.InputStream inputStream19 = null;
        byte[] byteArray23 = new byte[] { (byte) 100, (byte) 1 };
        tools.jackson.core.io.UTF32Reader uTF32Reader27 = new tools.jackson.core.io.UTF32Reader(iOContext18, inputStream19, false, byteArray23, (int) (short) 0, (-1), false);
        java.lang.String str28 = base64Variant0.encode(byteArray23);
        boolean boolean29 = base64Variant0.acceptsPaddingOnRead();
        tools.jackson.core.io.IOContext iOContext31 = null;
        java.io.InputStream inputStream32 = null;
        byte[] byteArray36 = new byte[] { (byte) 100, (byte) 1 };
        tools.jackson.core.io.UTF32Reader uTF32Reader40 = new tools.jackson.core.io.UTF32Reader(iOContext31, inputStream32, false, byteArray36, (int) (short) 0, (-1), false);
        // The following exception was thrown during execution in test generation
        try {
            int int42 = base64Variant0.encodeBase64Chunk(256, byteArray36, 9);
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: 9");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(base64Variant0);
        org.junit.Assert.assertNotNull(byteArray8);
        org.junit.Assert.assertArrayEquals(byteArray8, new byte[] { (byte) 10, (byte) 65, (byte) 65, (byte) -1, (byte) -1 });
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 3 + "'", int10 == 3);
        org.junit.Assert.assertNotNull(base64Variant14);
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertTrue("'" + char17 + "' != '" + 'B' + "'", char17 == 'B');
        org.junit.Assert.assertNotNull(byteArray23);
        org.junit.Assert.assertArrayEquals(byteArray23, new byte[] { (byte) 100, (byte) 1 });
        org.junit.Assert.assertEquals("'" + str28 + "' != '" + "ZAE" + "'", str28, "ZAE");
        org.junit.Assert.assertTrue("'" + boolean29 + "' != '" + false + "'", boolean29 == false);
        org.junit.Assert.assertNotNull(byteArray36);
        org.junit.Assert.assertArrayEquals(byteArray36, new byte[] { (byte) 100, (byte) 1 });
    }

    @Test
    public void test0325() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0325");
        tools.jackson.core.util.BufferRecycler bufferRecycler0 = null;
        tools.jackson.core.util.TextBuffer textBuffer1 = new tools.jackson.core.util.TextBuffer(bufferRecycler0);
        textBuffer1.resetWithString("");
        int int4 = textBuffer1.getCurrentSegmentSize();
        java.lang.String str5 = textBuffer1.toString();
        java.lang.String str8 = textBuffer1.finishAndReturn((int) 'B', false);
        java.lang.String str11 = textBuffer1.finishAndReturn((-2), false);
        textBuffer1.setCurrentLength((int) '/');
        byte[] byteArray14 = null;
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str17 = textBuffer1.resetWithASCII(byteArray14, (int) (short) -1, (-1074));
            org.junit.Assert.fail("Expected exception of type java.lang.StringIndexOutOfBoundsException; message: String index out of range: -1074");
        } catch (java.lang.StringIndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 0 + "'", int4 == 0);
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "" + "'", str5, "");
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "" + "'", str8, "");
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "" + "'", str11, "");
    }

    @Test
    public void test0326() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0326");
        tools.jackson.core.util.InternCache internCache0 = tools.jackson.core.util.InternCache.instance;
        tools.jackson.core.json.DupDetector dupDetector1 = null;
        tools.jackson.core.util.SimpleStreamWriteContext simpleStreamWriteContext2 = tools.jackson.core.util.SimpleStreamWriteContext.createRootContext(dupDetector1);
        tools.jackson.core.json.DupDetector dupDetector3 = simpleStreamWriteContext2.getDupDetector();
        boolean boolean4 = simpleStreamWriteContext2.inObject();
        boolean boolean5 = internCache0.containsValue((java.lang.Object) boolean4);
        boolean boolean6 = internCache0.isEmpty();
        tools.jackson.core.util.InternCache internCache7 = new tools.jackson.core.util.InternCache();
        internCache0.putAll((java.util.Map<java.lang.String, java.lang.String>) internCache7);
        java.util.function.ToDoubleFunction<java.util.Map.Entry<java.lang.String, java.lang.String>> strEntryToDoubleFunction10 = null;
        java.util.function.DoubleBinaryOperator doubleBinaryOperator12 = null;
        // The following exception was thrown during execution in test generation
        try {
            double double13 = internCache0.reduceEntriesToDouble(0L, strEntryToDoubleFunction10, (double) (-3), doubleBinaryOperator12);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(internCache0);
        org.junit.Assert.assertNotNull(simpleStreamWriteContext2);
        org.junit.Assert.assertNull(dupDetector3);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + true + "'", boolean6 == true);
    }

    @Test
    public void test0327() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0327");
        tools.jackson.core.util.BufferRecycler bufferRecycler1 = null;
        tools.jackson.core.util.TextBuffer textBuffer2 = new tools.jackson.core.util.TextBuffer(bufferRecycler1);
        textBuffer2.resetWithString("");
        int int5 = textBuffer2.getCurrentSegmentSize();
        java.lang.String str6 = textBuffer2.toString();
        java.lang.String str9 = textBuffer2.finishAndReturn((int) 'B', false);
        java.lang.String str10 = textBuffer2.toString();
        char[] charArray11 = textBuffer2.emptyAndGetCurrentSegment();
        // The following exception was thrown during execution in test generation
        try {
            int int13 = tools.jackson.core.io.NumberOutput.outputLong((long) 309, charArray11, 1000);
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: 1000");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + int5 + "' != '" + 0 + "'", int5 == 0);
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "" + "'", str6, "");
        org.junit.Assert.assertEquals("'" + str9 + "' != '" + "" + "'", str9, "");
        org.junit.Assert.assertEquals("'" + str10 + "' != '" + "" + "'", str10, "");
        org.junit.Assert.assertNotNull(charArray11);
    }

    @Test
    public void test0328() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0328");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        tools.jackson.core.filter.TokenFilter tokenFilter6 = filteringParserDelegate5.getFilter();
        boolean boolean7 = filteringParserDelegate5.isExpectedStartArrayToken();
        boolean boolean8 = filteringParserDelegate5.hasCurrentToken();
        tools.jackson.core.TokenStreamContext tokenStreamContext9 = filteringParserDelegate5.streamReadContext();
        int int10 = filteringParserDelegate5.getMatchCount();
        // The following exception was thrown during execution in test generation
        try {
            java.math.BigInteger bigInteger11 = filteringParserDelegate5.getBigIntegerValue();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(tokenFilter6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertNotNull(tokenStreamContext9);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 0 + "'", int10 == 0);
    }

    @Test
    public void test0329() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0329");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        tools.jackson.core.filter.TokenFilter tokenFilter6 = filteringParserDelegate5.getFilter();
        boolean boolean7 = filteringParserDelegate5.isExpectedStartArrayToken();
        boolean boolean8 = filteringParserDelegate5.hasCurrentToken();
        tools.jackson.core.TokenStreamContext tokenStreamContext9 = filteringParserDelegate5.streamReadContext();
        // The following exception was thrown during execution in test generation
        try {
            byte[] byteArray10 = filteringParserDelegate5.getBinaryValue();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(tokenFilter6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertNotNull(tokenStreamContext9);
    }

    @Test
    public void test0330() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0330");
        tools.jackson.core.ObjectReadContext objectReadContext0 = null;
        tools.jackson.core.io.IOContext iOContext1 = null;
        java.io.DataInput dataInput4 = null;
        tools.jackson.core.sym.ByteQuadsCanonicalizer byteQuadsCanonicalizer5 = tools.jackson.core.sym.ByteQuadsCanonicalizer.createRoot();
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.json.UTF8DataInputJsonParser uTF8DataInputJsonParser7 = new tools.jackson.core.json.UTF8DataInputJsonParser(objectReadContext0, iOContext1, (int) (short) -1, (int) 'B', dataInput4, byteQuadsCanonicalizer5, 2);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteQuadsCanonicalizer5);
    }

    @Test
    public void test0331() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0331");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        tools.jackson.core.filter.TokenFilter tokenFilter6 = filteringParserDelegate5.getFilter();
        boolean boolean7 = filteringParserDelegate5.isExpectedStartArrayToken();
        boolean boolean8 = filteringParserDelegate5.hasCurrentToken();
        tools.jackson.core.TokenStreamContext tokenStreamContext9 = filteringParserDelegate5.streamReadContext();
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean10 = filteringParserDelegate5.isExpectedNumberIntToken();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(tokenFilter6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertNotNull(tokenStreamContext9);
    }

    @Test
    public void test0332() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0332");
        tools.jackson.core.JsonGenerator jsonGenerator0 = null;
        tools.jackson.core.util.JsonGeneratorDelegate jsonGeneratorDelegate1 = new tools.jackson.core.util.JsonGeneratorDelegate(jsonGenerator0);
        tools.jackson.core.filter.TokenFilter tokenFilter2 = null;
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext3 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter2);
        tools.jackson.core.JsonToken jsonToken4 = tokenFilterContext3.nextTokenToRead();
        tools.jackson.core.filter.TokenFilter tokenFilter5 = tools.jackson.core.filter.TokenFilter.INCLUDE_ALL;
        tools.jackson.core.filter.TokenFilter tokenFilter6 = tokenFilterContext3.checkValue(tokenFilter5);
        java.math.BigDecimal bigDecimal7 = null;
        boolean boolean8 = tokenFilter5.includeNumber(bigDecimal7);
        tools.jackson.core.filter.TokenFilter tokenFilter10 = tokenFilter5.includeRootValue((int) (byte) 1);
        tools.jackson.core.filter.TokenFilter tokenFilter11 = tokenFilter10.filterStartArray();
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext12 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter10);
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.JsonGenerator jsonGenerator13 = jsonGeneratorDelegate1.writeEmbeddedObject((java.lang.Object) tokenFilter10);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(tokenFilterContext3);
        org.junit.Assert.assertNull(jsonToken4);
        org.junit.Assert.assertNotNull(tokenFilter5);
        org.junit.Assert.assertNotNull(tokenFilter6);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + true + "'", boolean8 == true);
        org.junit.Assert.assertNotNull(tokenFilter10);
        org.junit.Assert.assertNotNull(tokenFilter11);
        org.junit.Assert.assertNotNull(tokenFilterContext12);
    }

    @Test
    public void test0333() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0333");
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder0 = tools.jackson.core.json.JsonFactory.builderWithJackson2Defaults();
        tools.jackson.core.SerializableString serializableString1 = null;
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder2 = jsonFactoryBuilder0.rootValueSeparator(serializableString1);
        tools.jackson.core.io.CharacterEscapes characterEscapes3 = null;
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder4 = jsonFactoryBuilder0.characterEscapes(characterEscapes3);
        int int5 = jsonFactoryBuilder4.streamReadFeaturesMask();
        tools.jackson.core.StreamWriteFeature streamWriteFeature6 = null;
        tools.jackson.core.StreamWriteFeature streamWriteFeature7 = null;
        tools.jackson.core.StreamWriteFeature[] streamWriteFeatureArray8 = new tools.jackson.core.StreamWriteFeature[] { streamWriteFeature7 };
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder9 = jsonFactoryBuilder4.enable(streamWriteFeature6, streamWriteFeatureArray8);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(jsonFactoryBuilder0);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder2);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder4);
        org.junit.Assert.assertTrue("'" + int5 + "' != '" + 9 + "'", int5 == 9);
        org.junit.Assert.assertNotNull(streamWriteFeatureArray8);
        org.junit.Assert.assertArrayEquals(streamWriteFeatureArray8, new tools.jackson.core.StreamWriteFeature[] { null });
    }

    @Test
    public void test0334() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0334");
        tools.jackson.core.exc.StreamReadException streamReadException1 = new tools.jackson.core.exc.StreamReadException("hi!");
        tools.jackson.core.JsonParser jsonParser2 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter3 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion4 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate7 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser2, tokenFilter3, inclusion4, true, true);
        boolean boolean8 = filteringParserDelegate7.isExpectedStartObjectToken();
        tools.jackson.core.exc.StreamReadException streamReadException9 = streamReadException1.withParser((tools.jackson.core.JsonParser) filteringParserDelegate7);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj10 = filteringParserDelegate7.getObjectId();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertNotNull(streamReadException9);
    }

    @Test
    public void test0335() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0335");
        tools.jackson.core.JsonGenerator jsonGenerator0 = null;
        java.lang.Throwable throwable2 = null;
        tools.jackson.core.exc.StreamWriteException streamWriteException3 = new tools.jackson.core.exc.StreamWriteException(jsonGenerator0, "hi!", throwable2);
        java.lang.String str4 = streamWriteException3.toString();
        tools.jackson.core.exc.StreamConstraintsException streamConstraintsException6 = new tools.jackson.core.exc.StreamConstraintsException("0.0.0");
        tools.jackson.core.exc.StreamReadException streamReadException8 = new tools.jackson.core.exc.StreamReadException("");
        java.lang.String str9 = streamReadException8.getPathReference();
        tools.jackson.core.exc.StreamReadException streamReadException11 = new tools.jackson.core.exc.StreamReadException("");
        streamReadException8.addSuppressed((java.lang.Throwable) streamReadException11);
        tools.jackson.core.JacksonException jacksonException13 = streamConstraintsException6.withCause((java.lang.Throwable) streamReadException8);
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.JacksonException jacksonException14 = streamWriteException3.withCause((java.lang.Throwable) streamReadException8);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: Can't overwrite cause with tools.jackson.core.exc.StreamReadException: ");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "tools.jackson.core.exc.StreamWriteException: hi!\n at [No location information]" + "'", str4, "tools.jackson.core.exc.StreamWriteException: hi!\n at [No location information]");
        org.junit.Assert.assertEquals("'" + str9 + "' != '" + "" + "'", str9, "");
        org.junit.Assert.assertNotNull(jacksonException13);
    }

    @Test
    public void test0336() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0336");
        tools.jackson.core.json.DupDetector dupDetector0 = null;
        tools.jackson.core.json.JsonWriteContext jsonWriteContext1 = tools.jackson.core.json.JsonWriteContext.createRootContext(dupDetector0);
        boolean boolean2 = jsonWriteContext1.hasCurrentName();
        tools.jackson.core.JsonGenerator jsonGenerator5 = null;
        tools.jackson.core.json.DupDetector dupDetector6 = tools.jackson.core.json.DupDetector.rootDetector(jsonGenerator5);
        tools.jackson.core.json.JsonReadContext jsonReadContext7 = tools.jackson.core.json.JsonReadContext.createRootContext(56, 7, dupDetector6);
        tools.jackson.core.json.JsonWriteContext jsonWriteContext8 = jsonWriteContext1.withDupDetector(dupDetector6);
        tools.jackson.core.json.JsonWriteContext jsonWriteContext9 = jsonWriteContext8.getParent();
        java.lang.Object obj10 = jsonWriteContext8.currentValue();
        org.junit.Assert.assertNotNull(jsonWriteContext1);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(dupDetector6);
        org.junit.Assert.assertNotNull(jsonReadContext7);
        org.junit.Assert.assertNotNull(jsonWriteContext8);
        org.junit.Assert.assertNull(jsonWriteContext9);
        org.junit.Assert.assertNull(obj10);
    }

    @Test
    public void test0337() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0337");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        tools.jackson.core.filter.TokenFilter tokenFilter6 = filteringParserDelegate5.getFilter();
        boolean boolean7 = filteringParserDelegate5.isExpectedStartArrayToken();
        boolean boolean8 = filteringParserDelegate5.hasCurrentToken();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Number number9 = filteringParserDelegate5.getNumberValue();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(tokenFilter6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
    }

    @Test
    public void test0338() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0338");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        boolean boolean6 = filteringParserDelegate5.isExpectedStartObjectToken();
        int int7 = filteringParserDelegate5.currentTokenId();
        boolean boolean8 = filteringParserDelegate5.isExpectedStartObjectToken();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj9 = filteringParserDelegate5.currentValue();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 0 + "'", int7 == 0);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
    }

    @Test
    public void test0339() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0339");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean6 = filteringParserDelegate5.hasStringCharacters();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0340() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0340");
        tools.jackson.core.filter.TokenFilter tokenFilter0 = null;
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext1 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter0);
        tools.jackson.core.JsonToken jsonToken2 = tokenFilterContext1.nextTokenToRead();
        tools.jackson.core.filter.TokenFilter tokenFilter3 = tools.jackson.core.filter.TokenFilter.INCLUDE_ALL;
        tools.jackson.core.filter.TokenFilter tokenFilter4 = tokenFilterContext1.checkValue(tokenFilter3);
        java.math.BigDecimal bigDecimal5 = null;
        boolean boolean6 = tokenFilter3.includeNumber(bigDecimal5);
        tokenFilter3.filterFinishArray();
        java.lang.String str8 = tokenFilter3.toString();
        org.junit.Assert.assertNotNull(tokenFilterContext1);
        org.junit.Assert.assertNull(jsonToken2);
        org.junit.Assert.assertNotNull(tokenFilter3);
        org.junit.Assert.assertNotNull(tokenFilter4);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + true + "'", boolean6 == true);
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "TokenFilter.INCLUDE_ALL" + "'", str8, "TokenFilter.INCLUDE_ALL");
    }

    @Test
    public void test0341() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0341");
        tools.jackson.core.JsonGenerator jsonGenerator0 = null;
        java.lang.Throwable throwable2 = null;
        tools.jackson.core.exc.StreamWriteException streamWriteException3 = new tools.jackson.core.exc.StreamWriteException(jsonGenerator0, "hi!", throwable2);
        tools.jackson.core.JsonGenerator jsonGenerator4 = streamWriteException3.processor();
        java.lang.StringBuilder stringBuilder5 = null;
        java.lang.StringBuilder stringBuilder6 = streamWriteException3.getPathReference(stringBuilder5);
        org.junit.Assert.assertNull(jsonGenerator4);
        org.junit.Assert.assertNull(stringBuilder6);
    }

    @Test
    public void test0342() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0342");
        tools.jackson.core.util.BufferRecycler bufferRecycler0 = null;
        tools.jackson.core.util.TextBuffer textBuffer1 = new tools.jackson.core.util.TextBuffer(bufferRecycler0);
        textBuffer1.resetWithString("");
        int int4 = textBuffer1.getCurrentSegmentSize();
        java.lang.String str5 = textBuffer1.toString();
        java.lang.String str8 = textBuffer1.finishAndReturn((int) 'B', false);
        java.lang.String str11 = textBuffer1.finishAndReturn((-2), false);
        // The following exception was thrown during execution in test generation
        try {
            long long13 = textBuffer1.contentsAsLong(true);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 0 + "'", int4 == 0);
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "" + "'", str5, "");
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "" + "'", str8, "");
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "" + "'", str11, "");
    }

    @Test
    public void test0343() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0343");
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder0 = tools.jackson.core.json.JsonFactory.builder();
        tools.jackson.core.io.OutputDecorator outputDecorator1 = jsonFactoryBuilder0.outputDecorator();
        org.junit.Assert.assertNotNull(jsonFactoryBuilder0);
        org.junit.Assert.assertNull(outputDecorator1);
    }

    @Test
    public void test0344() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0344");
        int int0 = tools.jackson.core.io.schubfach.FloatToDecimal.E_MAX;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 39 + "'", int0 == 39);
    }

    @Test
    public void test0345() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0345");
        tools.jackson.core.filter.TokenFilter tokenFilter0 = null;
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext1 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter0);
        java.lang.Object obj2 = tokenFilterContext1.currentValue();
        tools.jackson.core.filter.TokenFilter tokenFilter3 = null;
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext4 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter3);
        tools.jackson.core.JsonToken jsonToken5 = tokenFilterContext4.nextTokenToRead();
        tools.jackson.core.filter.TokenFilter tokenFilter6 = tools.jackson.core.filter.TokenFilter.INCLUDE_ALL;
        tools.jackson.core.filter.TokenFilter tokenFilter7 = tokenFilterContext4.checkValue(tokenFilter6);
        java.math.BigDecimal bigDecimal8 = null;
        boolean boolean9 = tokenFilter6.includeNumber(bigDecimal8);
        tools.jackson.core.filter.TokenFilter tokenFilter11 = tokenFilter6.includeRootValue((int) (byte) 1);
        tools.jackson.core.filter.TokenFilter tokenFilter12 = tokenFilterContext1.checkValue(tokenFilter6);
        tools.jackson.core.filter.TokenFilter tokenFilter13 = null;
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext14 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter13);
        tools.jackson.core.JsonToken jsonToken15 = tokenFilterContext14.nextTokenToRead();
        tools.jackson.core.filter.TokenFilter tokenFilter16 = tools.jackson.core.filter.TokenFilter.INCLUDE_ALL;
        tools.jackson.core.filter.TokenFilter tokenFilter17 = tokenFilterContext14.checkValue(tokenFilter16);
        java.math.BigDecimal bigDecimal18 = null;
        boolean boolean19 = tokenFilter16.includeNumber(bigDecimal18);
        java.math.BigInteger bigInteger20 = null;
        boolean boolean21 = tokenFilter16.includeNumber(bigInteger20);
        tools.jackson.core.JsonParser jsonParser22 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter23 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion24 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate27 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser22, tokenFilter23, inclusion24, true, true);
        tools.jackson.core.filter.TokenFilter tokenFilter28 = filteringParserDelegate27.getFilter();
        boolean boolean29 = filteringParserDelegate27.isExpectedStartArrayToken();
        boolean boolean30 = filteringParserDelegate27.hasCurrentToken();
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext32 = tokenFilterContext1.createChildObjectContext(tokenFilter16, (java.lang.Object) filteringParserDelegate27, true);
        // The following exception was thrown during execution in test generation
        try {
            long long33 = filteringParserDelegate27.getLongValue();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(tokenFilterContext1);
        org.junit.Assert.assertNull(obj2);
        org.junit.Assert.assertNotNull(tokenFilterContext4);
        org.junit.Assert.assertNull(jsonToken5);
        org.junit.Assert.assertNotNull(tokenFilter6);
        org.junit.Assert.assertNotNull(tokenFilter7);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + true + "'", boolean9 == true);
        org.junit.Assert.assertNotNull(tokenFilter11);
        org.junit.Assert.assertNotNull(tokenFilter12);
        org.junit.Assert.assertNotNull(tokenFilterContext14);
        org.junit.Assert.assertNull(jsonToken15);
        org.junit.Assert.assertNotNull(tokenFilter16);
        org.junit.Assert.assertNotNull(tokenFilter17);
        org.junit.Assert.assertTrue("'" + boolean19 + "' != '" + true + "'", boolean19 == true);
        org.junit.Assert.assertTrue("'" + boolean21 + "' != '" + true + "'", boolean21 == true);
        org.junit.Assert.assertNull(tokenFilter28);
        org.junit.Assert.assertTrue("'" + boolean29 + "' != '" + false + "'", boolean29 == false);
        org.junit.Assert.assertTrue("'" + boolean30 + "' != '" + false + "'", boolean30 == false);
        org.junit.Assert.assertNotNull(tokenFilterContext32);
    }

    @Test
    public void test0346() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0346");
        tools.jackson.core.JsonGenerator jsonGenerator0 = null;
        tools.jackson.core.util.JsonGeneratorDelegate jsonGeneratorDelegate1 = new tools.jackson.core.util.JsonGeneratorDelegate(jsonGenerator0);
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.JsonGenerator jsonGenerator2 = jsonGeneratorDelegate1.writeEndObject();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0347() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0347");
        tools.jackson.core.exc.StreamReadException streamReadException1 = new tools.jackson.core.exc.StreamReadException("hi!");
        tools.jackson.core.JsonParser jsonParser2 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter3 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion4 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate7 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser2, tokenFilter3, inclusion4, true, true);
        boolean boolean8 = filteringParserDelegate7.isExpectedStartObjectToken();
        tools.jackson.core.exc.StreamReadException streamReadException9 = streamReadException1.withParser((tools.jackson.core.JsonParser) filteringParserDelegate7);
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean11 = filteringParserDelegate7.getValueAsBoolean(false);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertNotNull(streamReadException9);
    }

    @Test
    public void test0348() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0348");
        tools.jackson.core.JsonGenerator jsonGenerator0 = null;
        tools.jackson.core.util.JsonGeneratorDelegate jsonGeneratorDelegate1 = new tools.jackson.core.util.JsonGeneratorDelegate(jsonGenerator0);
        tools.jackson.core.JsonParser jsonParser2 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter3 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion4 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate7 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser2, tokenFilter3, inclusion4, true, true);
        boolean boolean8 = filteringParserDelegate7.isExpectedStartObjectToken();
        int int9 = filteringParserDelegate7.currentTokenId();
        boolean boolean10 = filteringParserDelegate7.isExpectedStartObjectToken();
        tools.jackson.core.JsonParser jsonParser11 = filteringParserDelegate7.skipChildren();
        // The following exception was thrown during execution in test generation
        try {
            jsonGeneratorDelegate1.copyCurrentStructure(jsonParser11);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertTrue("'" + int9 + "' != '" + 0 + "'", int9 == 0);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        org.junit.Assert.assertNotNull(jsonParser11);
    }

    @Test
    public void test0349() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0349");
        tools.jackson.core.ObjectReadContext objectReadContext0 = null;
        tools.jackson.core.io.IOContext iOContext1 = null;
        java.io.DataInput dataInput4 = null;
        tools.jackson.core.sym.ByteQuadsCanonicalizer byteQuadsCanonicalizer5 = tools.jackson.core.sym.ByteQuadsCanonicalizer.createRoot();
        byteQuadsCanonicalizer5.release();
        tools.jackson.core.sym.ByteQuadsCanonicalizer byteQuadsCanonicalizer8 = byteQuadsCanonicalizer5.makeChildOrPlaceholder(309);
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.json.UTF8DataInputWithDocLengthJsonParser uTF8DataInputWithDocLengthJsonParser10 = new tools.jackson.core.json.UTF8DataInputWithDocLengthJsonParser(objectReadContext0, iOContext1, 32000, (int) '~', dataInput4, byteQuadsCanonicalizer8, (int) (byte) 10);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteQuadsCanonicalizer5);
        org.junit.Assert.assertNotNull(byteQuadsCanonicalizer8);
    }

    @Test
    public void test0350() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0350");
        tools.jackson.core.util.BufferRecycler bufferRecycler0 = null;
        tools.jackson.core.util.TextBuffer textBuffer1 = new tools.jackson.core.util.TextBuffer(bufferRecycler0);
        textBuffer1.resetWithString("");
        int int4 = textBuffer1.getCurrentSegmentSize();
        java.lang.String str5 = textBuffer1.toString();
        java.lang.String str8 = textBuffer1.finishAndReturn((int) 'B', false);
        java.lang.String str9 = textBuffer1.toString();
        char[] charArray10 = textBuffer1.emptyAndGetCurrentSegment();
        // The following exception was thrown during execution in test generation
        try {
            java.math.BigDecimal bigDecimal14 = tools.jackson.core.io.NumberInput.parseBigDecimal(charArray10, 1000, (-45), false);
            org.junit.Assert.fail("Expected exception of type java.lang.StringIndexOutOfBoundsException; message: String index out of range: -45");
        } catch (java.lang.StringIndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 0 + "'", int4 == 0);
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "" + "'", str5, "");
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "" + "'", str8, "");
        org.junit.Assert.assertEquals("'" + str9 + "' != '" + "" + "'", str9, "");
        org.junit.Assert.assertNotNull(charArray10);
    }

    @Test
    public void test0351() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0351");
        tools.jackson.core.util.InternCache internCache0 = tools.jackson.core.util.InternCache.instance;
        tools.jackson.core.json.DupDetector dupDetector1 = null;
        tools.jackson.core.util.SimpleStreamWriteContext simpleStreamWriteContext2 = tools.jackson.core.util.SimpleStreamWriteContext.createRootContext(dupDetector1);
        tools.jackson.core.json.DupDetector dupDetector3 = simpleStreamWriteContext2.getDupDetector();
        boolean boolean4 = simpleStreamWriteContext2.inObject();
        boolean boolean5 = internCache0.containsValue((java.lang.Object) boolean4);
        boolean boolean6 = internCache0.isEmpty();
        tools.jackson.core.util.InternCache internCache7 = new tools.jackson.core.util.InternCache();
        internCache0.putAll((java.util.Map<java.lang.String, java.lang.String>) internCache7);
        java.lang.String str10 = internCache7.intern("(Float)");
        java.util.concurrent.ConcurrentHashMap.KeySetView<java.lang.String, java.lang.String> strSet11 = internCache7.keySet();
        org.junit.Assert.assertNotNull(internCache0);
        org.junit.Assert.assertNotNull(simpleStreamWriteContext2);
        org.junit.Assert.assertNull(dupDetector3);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
// flaky "1) test0351(RegressionTest0)":         org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertEquals("'" + str10 + "' != '" + "(Float)" + "'", str10, "(Float)");
        org.junit.Assert.assertNotNull(strSet11);
    }

    @Test
    public void test0352() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0352");
        tools.jackson.core.util.BufferRecycler bufferRecycler0 = null;
        tools.jackson.core.util.ByteArrayBuilder byteArrayBuilder1 = new tools.jackson.core.util.ByteArrayBuilder(bufferRecycler0);
        tools.jackson.core.util.BufferRecycler bufferRecycler2 = byteArrayBuilder1.bufferRecycler();
        byte[] byteArray3 = byteArrayBuilder1.getClearAndRelease();
        byteArrayBuilder1.appendTwoBytes(500);
        byteArrayBuilder1.setCurrentSegmentLength((-1074));
        org.junit.Assert.assertNull(bufferRecycler2);
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertArrayEquals(byteArray3, new byte[] {});
    }

    @Test
    public void test0353() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0353");
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.JsonPointer jsonPointer1 = tools.jackson.core.JsonPointer.compile("100.0");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Invalid input: JSON Pointer expression must start with '/': \"100.0\"");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0354() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0354");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        tools.jackson.core.filter.TokenFilter tokenFilter6 = filteringParserDelegate5.getFilter();
        boolean boolean7 = filteringParserDelegate5.isExpectedStartArrayToken();
        boolean boolean8 = filteringParserDelegate5.hasCurrentToken();
        filteringParserDelegate5.clearCurrentToken();
        java.io.Writer writer10 = null;
        // The following exception was thrown during execution in test generation
        try {
            long long11 = filteringParserDelegate5.readString(writer10);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(tokenFilter6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
    }

    @Test
    public void test0355() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0355");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        boolean boolean6 = filteringParserDelegate5.isExpectedStartObjectToken();
        int int7 = filteringParserDelegate5.currentTokenId();
        boolean boolean8 = filteringParserDelegate5.isExpectedStartObjectToken();
        java.io.Writer writer9 = null;
        // The following exception was thrown during execution in test generation
        try {
            long long10 = filteringParserDelegate5.readString(writer9);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 0 + "'", int7 == 0);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
    }

    @Test
    public void test0356() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0356");
        tools.jackson.core.json.DupDetector dupDetector0 = null;
        tools.jackson.core.util.SimpleStreamWriteContext simpleStreamWriteContext1 = tools.jackson.core.util.SimpleStreamWriteContext.createRootContext(dupDetector0);
        tools.jackson.core.io.ContentReference contentReference2 = null;
        tools.jackson.core.TokenStreamLocation tokenStreamLocation3 = simpleStreamWriteContext1.startLocation(contentReference2);
        int int4 = tokenStreamLocation3.getLineNr();
        org.junit.Assert.assertNotNull(simpleStreamWriteContext1);
        org.junit.Assert.assertNotNull(tokenStreamLocation3);
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + (-1) + "'", int4 == (-1));
    }

    @Test
    public void test0357() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0357");
        tools.jackson.core.json.DupDetector dupDetector2 = null;
        tools.jackson.core.json.JsonReadContext jsonReadContext3 = tools.jackson.core.json.JsonReadContext.createRootContext(1, 0, dupDetector2);
        boolean boolean4 = jsonReadContext3.inRoot();
        org.junit.Assert.assertNotNull(jsonReadContext3);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + true + "'", boolean4 == true);
    }

    @Test
    public void test0358() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0358");
        tools.jackson.core.json.DupDetector dupDetector0 = null;
        tools.jackson.core.util.SimpleStreamWriteContext simpleStreamWriteContext1 = tools.jackson.core.util.SimpleStreamWriteContext.createRootContext(dupDetector0);
        tools.jackson.core.json.DupDetector dupDetector2 = simpleStreamWriteContext1.getDupDetector();
        tools.jackson.core.json.DupDetector dupDetector3 = null;
        tools.jackson.core.util.SimpleStreamWriteContext simpleStreamWriteContext4 = simpleStreamWriteContext1.withDupDetector(dupDetector3);
        java.lang.String str5 = simpleStreamWriteContext4.typeDesc();
        tools.jackson.core.util.SimpleStreamWriteContext simpleStreamWriteContext6 = simpleStreamWriteContext4.getParent();
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.json.DupDetector dupDetector7 = simpleStreamWriteContext6.getDupDetector();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(simpleStreamWriteContext1);
        org.junit.Assert.assertNull(dupDetector2);
        org.junit.Assert.assertNotNull(simpleStreamWriteContext4);
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "root" + "'", str5, "root");
        org.junit.Assert.assertNull(simpleStreamWriteContext6);
    }

    @Test
    public void test0359() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0359");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        tools.jackson.core.filter.TokenFilter tokenFilter6 = filteringParserDelegate5.getFilter();
        boolean boolean7 = filteringParserDelegate5.isExpectedStartArrayToken();
        tools.jackson.core.Base64Variant base64Variant8 = tools.jackson.core.Base64Variants.MODIFIED_FOR_URL;
        byte[] byteArray16 = new byte[] { (byte) 10, (byte) -1, (byte) -1, (byte) -1, (byte) -1 };
        int int18 = base64Variant8.encodeBase64Partial(10, (int) 'a', byteArray16, (int) (byte) 1);
        tools.jackson.core.Base64Variant base64Variant21 = new tools.jackson.core.Base64Variant(base64Variant8, "hi!", 0);
        char char22 = base64Variant8.getPaddingChar();
        // The following exception was thrown during execution in test generation
        try {
            byte[] byteArray23 = filteringParserDelegate5.getBinaryValue(base64Variant8);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(tokenFilter6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertNotNull(base64Variant8);
        org.junit.Assert.assertNotNull(byteArray16);
        org.junit.Assert.assertArrayEquals(byteArray16, new byte[] { (byte) 10, (byte) 65, (byte) 65, (byte) -1, (byte) -1 });
        org.junit.Assert.assertTrue("'" + int18 + "' != '" + 3 + "'", int18 == 3);
        org.junit.Assert.assertTrue("'" + char22 + "' != '" + '\000' + "'", char22 == '\000');
    }

    @Test
    public void test0360() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0360");
        java.io.IOException iOException0 = null;
        tools.jackson.core.JsonParser jsonParser1 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter2 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion3 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate6 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser1, tokenFilter2, inclusion3, true, true);
        tools.jackson.core.filter.TokenFilter tokenFilter7 = filteringParserDelegate6.getFilter();
        boolean boolean8 = filteringParserDelegate6.isExpectedStartArrayToken();
        boolean boolean9 = filteringParserDelegate6.hasCurrentToken();
        tools.jackson.core.json.DupDetector dupDetector10 = tools.jackson.core.json.DupDetector.rootDetector((tools.jackson.core.JsonParser) filteringParserDelegate6);
        boolean boolean11 = filteringParserDelegate6.isExpectedStartObjectToken();
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.exc.JacksonIOException jacksonIOException12 = tools.jackson.core.exc.JacksonIOException.construct(iOException0, (java.io.Closeable) filteringParserDelegate6);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(tokenFilter7);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertNotNull(dupDetector10);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
    }

    @Test
    public void test0361() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0361");
        tools.jackson.core.StreamWriteConstraints streamWriteConstraints0 = null;
        tools.jackson.core.StreamWriteConstraints.overrideDefaultStreamWriteConstraints(streamWriteConstraints0);
    }

    @Test
    public void test0362() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0362");
        tools.jackson.core.filter.TokenFilter tokenFilter0 = tools.jackson.core.filter.TokenFilter.INCLUDE_ALL;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = tokenFilter0.filterStartArray();
        boolean boolean3 = tokenFilter0.includeString("97.0");
        org.junit.Assert.assertNotNull(tokenFilter0);
        org.junit.Assert.assertNotNull(tokenFilter1);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + true + "'", boolean3 == true);
    }

    @Test
    public void test0363() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0363");
        tools.jackson.core.JsonParser jsonParser1 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter2 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion3 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate6 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser1, tokenFilter2, inclusion3, true, true);
        boolean boolean7 = filteringParserDelegate6.isExpectedStartObjectToken();
        int int8 = filteringParserDelegate6.currentTokenId();
        tools.jackson.core.JsonToken jsonToken9 = null;
        boolean boolean10 = filteringParserDelegate6.hasToken(jsonToken9);
        int int11 = filteringParserDelegate6.currentTokenId();
        boolean boolean12 = filteringParserDelegate6.isExpectedStartObjectToken();
        tools.jackson.core.JsonParser jsonParser13 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter14 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion15 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate18 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser13, tokenFilter14, inclusion15, true, true);
        tools.jackson.core.filter.TokenFilter tokenFilter19 = filteringParserDelegate18.getFilter();
        tools.jackson.core.util.JsonParserSequence jsonParserSequence20 = tools.jackson.core.util.JsonParserSequence.createFlattened(true, (tools.jackson.core.JsonParser) filteringParserDelegate6, (tools.jackson.core.JsonParser) filteringParserDelegate18);
        tools.jackson.core.JsonParser jsonParser21 = jsonParserSequence20.skipChildren();
        // The following exception was thrown during execution in test generation
        try {
            java.math.BigDecimal bigDecimal22 = jsonParserSequence20.getDecimalValue();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 0 + "'", int8 == 0);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 0 + "'", int11 == 0);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
        org.junit.Assert.assertNull(tokenFilter19);
        org.junit.Assert.assertNotNull(jsonParserSequence20);
        org.junit.Assert.assertNotNull(jsonParser21);
    }

    @Test
    public void test0364() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0364");
        tools.jackson.core.JsonParser jsonParser1 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter2 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion3 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate6 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser1, tokenFilter2, inclusion3, true, true);
        boolean boolean7 = filteringParserDelegate6.isExpectedStartObjectToken();
        int int8 = filteringParserDelegate6.currentTokenId();
        tools.jackson.core.JsonToken jsonToken9 = null;
        boolean boolean10 = filteringParserDelegate6.hasToken(jsonToken9);
        int int11 = filteringParserDelegate6.currentTokenId();
        boolean boolean12 = filteringParserDelegate6.isExpectedStartObjectToken();
        tools.jackson.core.JsonParser jsonParser13 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter14 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion15 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate18 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser13, tokenFilter14, inclusion15, true, true);
        tools.jackson.core.filter.TokenFilter tokenFilter19 = filteringParserDelegate18.getFilter();
        tools.jackson.core.util.JsonParserSequence jsonParserSequence20 = tools.jackson.core.util.JsonParserSequence.createFlattened(true, (tools.jackson.core.JsonParser) filteringParserDelegate6, (tools.jackson.core.JsonParser) filteringParserDelegate18);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj21 = filteringParserDelegate18.currentValue();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 0 + "'", int8 == 0);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 0 + "'", int11 == 0);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
        org.junit.Assert.assertNull(tokenFilter19);
        org.junit.Assert.assertNotNull(jsonParserSequence20);
    }

    @Test
    public void test0365() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0365");
        tools.jackson.core.JsonParser jsonParser1 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter2 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion3 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate6 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser1, tokenFilter2, inclusion3, true, true);
        boolean boolean7 = filteringParserDelegate6.isExpectedStartObjectToken();
        int int8 = filteringParserDelegate6.currentTokenId();
        tools.jackson.core.JsonToken jsonToken9 = null;
        boolean boolean10 = filteringParserDelegate6.hasToken(jsonToken9);
        int int11 = filteringParserDelegate6.currentTokenId();
        boolean boolean12 = filteringParserDelegate6.isExpectedStartObjectToken();
        tools.jackson.core.JsonParser jsonParser13 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter14 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion15 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate18 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser13, tokenFilter14, inclusion15, true, true);
        tools.jackson.core.filter.TokenFilter tokenFilter19 = filteringParserDelegate18.getFilter();
        tools.jackson.core.util.JsonParserSequence jsonParserSequence20 = tools.jackson.core.util.JsonParserSequence.createFlattened(true, (tools.jackson.core.JsonParser) filteringParserDelegate6, (tools.jackson.core.JsonParser) filteringParserDelegate18);
        // The following exception was thrown during execution in test generation
        try {
            int int21 = filteringParserDelegate18.streamReadFeatures();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 0 + "'", int8 == 0);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 0 + "'", int11 == 0);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
        org.junit.Assert.assertNull(tokenFilter19);
        org.junit.Assert.assertNotNull(jsonParserSequence20);
    }

    @Test
    public void test0366() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0366");
        tools.jackson.core.util.InternCache internCache0 = tools.jackson.core.util.InternCache.instance;
        tools.jackson.core.json.DupDetector dupDetector1 = null;
        tools.jackson.core.util.SimpleStreamWriteContext simpleStreamWriteContext2 = tools.jackson.core.util.SimpleStreamWriteContext.createRootContext(dupDetector1);
        tools.jackson.core.json.DupDetector dupDetector3 = simpleStreamWriteContext2.getDupDetector();
        boolean boolean4 = simpleStreamWriteContext2.inObject();
        boolean boolean5 = internCache0.containsValue((java.lang.Object) boolean4);
        java.util.function.ToDoubleFunction<java.util.Map.Entry<java.lang.String, java.lang.String>> strEntryToDoubleFunction7 = null;
        java.util.function.DoubleBinaryOperator doubleBinaryOperator9 = null;
        // The following exception was thrown during execution in test generation
        try {
            double double10 = internCache0.reduceEntriesToDouble((long) 9, strEntryToDoubleFunction7, (double) 1L, doubleBinaryOperator9);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(internCache0);
        org.junit.Assert.assertNotNull(simpleStreamWriteContext2);
        org.junit.Assert.assertNull(dupDetector3);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
    }

    @Test
    public void test0367() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0367");
        tools.jackson.core.JsonGenerator jsonGenerator0 = null;
        tools.jackson.core.util.JsonGeneratorDelegate jsonGeneratorDelegate1 = new tools.jackson.core.util.JsonGeneratorDelegate(jsonGenerator0);
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.JsonGenerator jsonGenerator3 = jsonGeneratorDelegate1.writeString("100.0");
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0368() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0368");
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter0 = new tools.jackson.core.util.MinimalPrettyPrinter();
        tools.jackson.core.JsonGenerator jsonGenerator1 = null;
        minimalPrettyPrinter0.beforeObjectEntries(jsonGenerator1);
        tools.jackson.core.util.Separators separators6 = new tools.jackson.core.util.Separators('~', ' ', ' ');
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter7 = minimalPrettyPrinter0.setSeparators(separators6);
        tools.jackson.core.JsonGenerator jsonGenerator8 = null;
        tools.jackson.core.util.JsonGeneratorDelegate jsonGeneratorDelegate9 = new tools.jackson.core.util.JsonGeneratorDelegate(jsonGenerator8);
        minimalPrettyPrinter0.beforeObjectEntries((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate9);
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.JsonGenerator jsonGenerator12 = jsonGeneratorDelegate9.writeRaw('a');
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(minimalPrettyPrinter7);
    }

    @Test
    public void test0369() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0369");
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter1 = new tools.jackson.core.util.MinimalPrettyPrinter("hi!");
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter2 = new tools.jackson.core.util.MinimalPrettyPrinter();
        tools.jackson.core.JsonGenerator jsonGenerator3 = null;
        minimalPrettyPrinter2.beforeObjectEntries(jsonGenerator3);
        tools.jackson.core.util.Separators separators8 = new tools.jackson.core.util.Separators('~', ' ', ' ');
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter9 = minimalPrettyPrinter2.setSeparators(separators8);
        tools.jackson.core.JsonGenerator jsonGenerator10 = null;
        tools.jackson.core.util.JsonGeneratorDelegate jsonGeneratorDelegate11 = new tools.jackson.core.util.JsonGeneratorDelegate(jsonGenerator10);
        minimalPrettyPrinter2.beforeObjectEntries((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate11);
        // The following exception was thrown during execution in test generation
        try {
            minimalPrettyPrinter1.writeEndArray((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate11, 12000);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(minimalPrettyPrinter9);
    }

    @Test
    public void test0370() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0370");
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder0 = tools.jackson.core.json.JsonFactory.builderWithJackson2Defaults();
        tools.jackson.core.StreamReadFeature streamReadFeature1 = null;
        tools.jackson.core.StreamReadFeature[] streamReadFeatureArray2 = new tools.jackson.core.StreamReadFeature[] {};
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder3 = jsonFactoryBuilder0.enable(streamReadFeature1, streamReadFeatureArray2);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(jsonFactoryBuilder0);
        org.junit.Assert.assertNotNull(streamReadFeatureArray2);
        org.junit.Assert.assertArrayEquals(streamReadFeatureArray2, new tools.jackson.core.StreamReadFeature[] {});
    }

    @Test
    public void test0371() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0371");
        // The following exception was thrown during execution in test generation
        try {
            java.math.BigDecimal bigDecimal1 = tools.jackson.core.io.BigDecimalParser.parse(" ");
            org.junit.Assert.fail("Expected exception of type java.lang.NumberFormatException; message: Value \" \" cannot be deserialized as `java.math.BigDecimal`, reason:  Not a valid number representation");
        } catch (java.lang.NumberFormatException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0372() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0372");
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter0 = new tools.jackson.core.util.MinimalPrettyPrinter();
        tools.jackson.core.JsonGenerator jsonGenerator1 = null;
        minimalPrettyPrinter0.beforeObjectEntries(jsonGenerator1);
        tools.jackson.core.util.Separators separators6 = new tools.jackson.core.util.Separators('~', ' ', ' ');
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter7 = minimalPrettyPrinter0.setSeparators(separators6);
        tools.jackson.core.JsonGenerator jsonGenerator8 = null;
        tools.jackson.core.util.JsonGeneratorDelegate jsonGeneratorDelegate9 = new tools.jackson.core.util.JsonGeneratorDelegate(jsonGenerator8);
        minimalPrettyPrinter0.beforeObjectEntries((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate9);
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.io.CharacterEscapes characterEscapes11 = jsonGeneratorDelegate9.getCharacterEscapes();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(minimalPrettyPrinter7);
    }

    @Test
    public void test0373() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0373");
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext2 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter1);
        java.lang.Object obj3 = tokenFilterContext2.currentValue();
        tools.jackson.core.filter.TokenFilter tokenFilter4 = null;
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext5 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter4);
        tools.jackson.core.JsonToken jsonToken6 = tokenFilterContext5.nextTokenToRead();
        tools.jackson.core.filter.TokenFilter tokenFilter7 = tools.jackson.core.filter.TokenFilter.INCLUDE_ALL;
        tools.jackson.core.filter.TokenFilter tokenFilter8 = tokenFilterContext5.checkValue(tokenFilter7);
        java.math.BigDecimal bigDecimal9 = null;
        boolean boolean10 = tokenFilter7.includeNumber(bigDecimal9);
        tools.jackson.core.filter.TokenFilter tokenFilter12 = tokenFilter7.includeRootValue((int) (byte) 1);
        tools.jackson.core.filter.TokenFilter tokenFilter13 = tokenFilterContext2.checkValue(tokenFilter7);
        tools.jackson.core.filter.TokenFilter tokenFilter14 = null;
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext15 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter14);
        tools.jackson.core.JsonToken jsonToken16 = tokenFilterContext15.nextTokenToRead();
        tools.jackson.core.filter.TokenFilter tokenFilter17 = tools.jackson.core.filter.TokenFilter.INCLUDE_ALL;
        tools.jackson.core.filter.TokenFilter tokenFilter18 = tokenFilterContext15.checkValue(tokenFilter17);
        java.math.BigDecimal bigDecimal19 = null;
        boolean boolean20 = tokenFilter17.includeNumber(bigDecimal19);
        java.math.BigInteger bigInteger21 = null;
        boolean boolean22 = tokenFilter17.includeNumber(bigInteger21);
        tools.jackson.core.JsonParser jsonParser23 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter24 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion25 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate28 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser23, tokenFilter24, inclusion25, true, true);
        tools.jackson.core.filter.TokenFilter tokenFilter29 = filteringParserDelegate28.getFilter();
        boolean boolean30 = filteringParserDelegate28.isExpectedStartArrayToken();
        boolean boolean31 = filteringParserDelegate28.hasCurrentToken();
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext33 = tokenFilterContext2.createChildObjectContext(tokenFilter17, (java.lang.Object) filteringParserDelegate28, true);
        tools.jackson.core.ErrorReportConfiguration errorReportConfiguration36 = null;
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.io.ContentReference contentReference37 = tools.jackson.core.io.ContentReference.construct(false, (java.lang.Object) tokenFilterContext2, 16, 50000, errorReportConfiguration36);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(tokenFilterContext2);
        org.junit.Assert.assertNull(obj3);
        org.junit.Assert.assertNotNull(tokenFilterContext5);
        org.junit.Assert.assertNull(jsonToken6);
        org.junit.Assert.assertNotNull(tokenFilter7);
        org.junit.Assert.assertNotNull(tokenFilter8);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
        org.junit.Assert.assertNotNull(tokenFilter12);
        org.junit.Assert.assertNotNull(tokenFilter13);
        org.junit.Assert.assertNotNull(tokenFilterContext15);
        org.junit.Assert.assertNull(jsonToken16);
        org.junit.Assert.assertNotNull(tokenFilter17);
        org.junit.Assert.assertNotNull(tokenFilter18);
        org.junit.Assert.assertTrue("'" + boolean20 + "' != '" + true + "'", boolean20 == true);
        org.junit.Assert.assertTrue("'" + boolean22 + "' != '" + true + "'", boolean22 == true);
        org.junit.Assert.assertNull(tokenFilter29);
        org.junit.Assert.assertTrue("'" + boolean30 + "' != '" + false + "'", boolean30 == false);
        org.junit.Assert.assertTrue("'" + boolean31 + "' != '" + false + "'", boolean31 == false);
        org.junit.Assert.assertNotNull(tokenFilterContext33);
    }

    @Test
    public void test0374() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0374");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        tools.jackson.core.filter.TokenFilter tokenFilter6 = filteringParserDelegate5.getFilter();
        boolean boolean7 = filteringParserDelegate5.isExpectedStartArrayToken();
        tools.jackson.core.filter.TokenFilter tokenFilter8 = filteringParserDelegate5.getFilter();
        // The following exception was thrown during execution in test generation
        try {
            float float9 = filteringParserDelegate5.getFloatValue();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(tokenFilter6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertNull(tokenFilter8);
    }

    @Test
    public void test0375() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0375");
        int int0 = tools.jackson.core.json.JsonWriteContext.STATUS_OK_AFTER_COLON;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 2 + "'", int0 == 2);
    }

    @Test
    public void test0376() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0376");
        tools.jackson.core.filter.TokenFilter tokenFilter0 = null;
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext1 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter0);
        java.lang.Object obj2 = tokenFilterContext1.currentValue();
        tools.jackson.core.filter.TokenFilter tokenFilter3 = null;
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext4 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter3);
        tools.jackson.core.JsonToken jsonToken5 = tokenFilterContext4.nextTokenToRead();
        tools.jackson.core.filter.TokenFilter tokenFilter6 = tools.jackson.core.filter.TokenFilter.INCLUDE_ALL;
        tools.jackson.core.filter.TokenFilter tokenFilter7 = tokenFilterContext4.checkValue(tokenFilter6);
        java.math.BigDecimal bigDecimal8 = null;
        boolean boolean9 = tokenFilter6.includeNumber(bigDecimal8);
        tools.jackson.core.filter.TokenFilter tokenFilter11 = tokenFilter6.includeRootValue((int) (byte) 1);
        tools.jackson.core.filter.TokenFilter tokenFilter12 = tokenFilterContext1.checkValue(tokenFilter6);
        tools.jackson.core.filter.TokenFilter tokenFilter13 = null;
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext14 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter13);
        tools.jackson.core.JsonToken jsonToken15 = tokenFilterContext14.nextTokenToRead();
        tools.jackson.core.filter.TokenFilter tokenFilter16 = tools.jackson.core.filter.TokenFilter.INCLUDE_ALL;
        tools.jackson.core.filter.TokenFilter tokenFilter17 = tokenFilterContext14.checkValue(tokenFilter16);
        java.math.BigDecimal bigDecimal18 = null;
        boolean boolean19 = tokenFilter16.includeNumber(bigDecimal18);
        java.math.BigInteger bigInteger20 = null;
        boolean boolean21 = tokenFilter16.includeNumber(bigInteger20);
        tools.jackson.core.JsonParser jsonParser22 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter23 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion24 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate27 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser22, tokenFilter23, inclusion24, true, true);
        tools.jackson.core.filter.TokenFilter tokenFilter28 = filteringParserDelegate27.getFilter();
        boolean boolean29 = filteringParserDelegate27.isExpectedStartArrayToken();
        boolean boolean30 = filteringParserDelegate27.hasCurrentToken();
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext32 = tokenFilterContext1.createChildObjectContext(tokenFilter16, (java.lang.Object) filteringParserDelegate27, true);
        // The following exception was thrown during execution in test generation
        try {
            double double33 = filteringParserDelegate27.getDoubleValue();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(tokenFilterContext1);
        org.junit.Assert.assertNull(obj2);
        org.junit.Assert.assertNotNull(tokenFilterContext4);
        org.junit.Assert.assertNull(jsonToken5);
        org.junit.Assert.assertNotNull(tokenFilter6);
        org.junit.Assert.assertNotNull(tokenFilter7);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + true + "'", boolean9 == true);
        org.junit.Assert.assertNotNull(tokenFilter11);
        org.junit.Assert.assertNotNull(tokenFilter12);
        org.junit.Assert.assertNotNull(tokenFilterContext14);
        org.junit.Assert.assertNull(jsonToken15);
        org.junit.Assert.assertNotNull(tokenFilter16);
        org.junit.Assert.assertNotNull(tokenFilter17);
        org.junit.Assert.assertTrue("'" + boolean19 + "' != '" + true + "'", boolean19 == true);
        org.junit.Assert.assertTrue("'" + boolean21 + "' != '" + true + "'", boolean21 == true);
        org.junit.Assert.assertNull(tokenFilter28);
        org.junit.Assert.assertTrue("'" + boolean29 + "' != '" + false + "'", boolean29 == false);
        org.junit.Assert.assertTrue("'" + boolean30 + "' != '" + false + "'", boolean30 == false);
        org.junit.Assert.assertNotNull(tokenFilterContext32);
    }

    @Test
    public void test0377() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0377");
        tools.jackson.core.Base64Variant base64Variant0 = tools.jackson.core.Base64Variants.MIME;
        org.junit.Assert.assertNotNull(base64Variant0);
    }

    @Test
    public void test0378() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0378");
        int int0 = tools.jackson.core.io.schubfach.DoubleToDecimal.K_MIN;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + (-324) + "'", int0 == (-324));
    }

    @Test
    public void test0379() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0379");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        tools.jackson.core.filter.TokenFilter tokenFilter6 = filteringParserDelegate5.getFilter();
        boolean boolean7 = filteringParserDelegate5.isExpectedStartArrayToken();
        boolean boolean8 = filteringParserDelegate5.hasCurrentToken();
        tools.jackson.core.json.DupDetector dupDetector9 = tools.jackson.core.json.DupDetector.rootDetector((tools.jackson.core.JsonParser) filteringParserDelegate5);
        tools.jackson.core.JsonParser jsonParser10 = filteringParserDelegate5.delegate();
        // The following exception was thrown during execution in test generation
        try {
            char[] charArray11 = filteringParserDelegate5.getStringCharacters();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(tokenFilter6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertNotNull(dupDetector9);
        org.junit.Assert.assertNull(jsonParser10);
    }

    @Test
    public void test0380() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0380");
        tools.jackson.core.json.DupDetector dupDetector0 = null;
        tools.jackson.core.util.SimpleStreamWriteContext simpleStreamWriteContext1 = tools.jackson.core.util.SimpleStreamWriteContext.createRootContext(dupDetector0);
        tools.jackson.core.json.DupDetector dupDetector2 = simpleStreamWriteContext1.getDupDetector();
        tools.jackson.core.json.DupDetector dupDetector3 = null;
        tools.jackson.core.util.SimpleStreamWriteContext simpleStreamWriteContext4 = simpleStreamWriteContext1.withDupDetector(dupDetector3);
        java.lang.String str5 = simpleStreamWriteContext4.typeDesc();
        tools.jackson.core.util.SimpleStreamWriteContext simpleStreamWriteContext6 = simpleStreamWriteContext4.getParent();
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean7 = simpleStreamWriteContext6.inArray();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(simpleStreamWriteContext1);
        org.junit.Assert.assertNull(dupDetector2);
        org.junit.Assert.assertNotNull(simpleStreamWriteContext4);
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "root" + "'", str5, "root");
        org.junit.Assert.assertNull(simpleStreamWriteContext6);
    }

    @Test
    public void test0381() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0381");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        tools.jackson.core.filter.TokenFilter tokenFilter6 = filteringParserDelegate5.getFilter();
        boolean boolean7 = filteringParserDelegate5.isExpectedStartArrayToken();
        boolean boolean8 = filteringParserDelegate5.hasCurrentToken();
        tools.jackson.core.json.DupDetector dupDetector9 = tools.jackson.core.json.DupDetector.rootDetector((tools.jackson.core.JsonParser) filteringParserDelegate5);
        java.io.Writer writer10 = null;
        // The following exception was thrown during execution in test generation
        try {
            int int11 = filteringParserDelegate5.getString(writer10);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(tokenFilter6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertNotNull(dupDetector9);
    }

    @Test
    public void test0382() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0382");
        tools.jackson.core.util.BufferRecycler bufferRecycler0 = null;
        tools.jackson.core.util.ByteArrayBuilder byteArrayBuilder1 = new tools.jackson.core.util.ByteArrayBuilder(bufferRecycler0);
        byteArrayBuilder1.setCurrentSegmentLength(2);
    }

    @Test
    public void test0383() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0383");
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter0 = new tools.jackson.core.util.MinimalPrettyPrinter();
        tools.jackson.core.JsonGenerator jsonGenerator1 = null;
        minimalPrettyPrinter0.beforeObjectEntries(jsonGenerator1);
        tools.jackson.core.util.Separators separators6 = new tools.jackson.core.util.Separators('~', ' ', ' ');
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter7 = minimalPrettyPrinter0.setSeparators(separators6);
        tools.jackson.core.JsonGenerator jsonGenerator8 = null;
        tools.jackson.core.util.JsonGeneratorDelegate jsonGeneratorDelegate9 = new tools.jackson.core.util.JsonGeneratorDelegate(jsonGenerator8);
        minimalPrettyPrinter0.beforeObjectEntries((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate9);
        tools.jackson.core.util.JsonGeneratorDelegate jsonGeneratorDelegate11 = new tools.jackson.core.util.JsonGeneratorDelegate((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate9);
        tools.jackson.core.Base64Variant base64Variant12 = tools.jackson.core.Base64Variants.MODIFIED_FOR_URL;
        byte[] byteArray20 = new byte[] { (byte) 10, (byte) -1, (byte) -1, (byte) -1, (byte) -1 };
        int int22 = base64Variant12.encodeBase64Partial(10, (int) 'a', byteArray20, (int) (byte) 1);
        tools.jackson.core.Base64Variant base64Variant25 = new tools.jackson.core.Base64Variant(base64Variant12, "hi!", 0);
        boolean boolean26 = base64Variant12.requiresPaddingOnRead();
        byte[] byteArray31 = new byte[] { (byte) 100, (byte) 10, (byte) 1, (byte) -1 };
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.JsonGenerator jsonGenerator34 = jsonGeneratorDelegate9.writeBinary(base64Variant12, byteArray31, 29, 17);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(minimalPrettyPrinter7);
        org.junit.Assert.assertNotNull(base64Variant12);
        org.junit.Assert.assertNotNull(byteArray20);
        org.junit.Assert.assertArrayEquals(byteArray20, new byte[] { (byte) 10, (byte) 65, (byte) 65, (byte) -1, (byte) -1 });
        org.junit.Assert.assertTrue("'" + int22 + "' != '" + 3 + "'", int22 == 3);
        org.junit.Assert.assertTrue("'" + boolean26 + "' != '" + false + "'", boolean26 == false);
        org.junit.Assert.assertNotNull(byteArray31);
        org.junit.Assert.assertArrayEquals(byteArray31, new byte[] { (byte) 100, (byte) 10, (byte) 1, (byte) -1 });
    }

    @Test
    public void test0384() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0384");
        java.lang.String str0 = tools.jackson.core.JsonPointer.ESC_TILDE;
        org.junit.Assert.assertEquals("'" + str0 + "' != '" + "~0" + "'", str0, "~0");
    }

    @Test
    public void test0385() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0385");
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter0 = new tools.jackson.core.util.MinimalPrettyPrinter();
        tools.jackson.core.JsonGenerator jsonGenerator1 = null;
        minimalPrettyPrinter0.beforeObjectEntries(jsonGenerator1);
        tools.jackson.core.util.Separators separators6 = new tools.jackson.core.util.Separators('~', ' ', ' ');
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter7 = minimalPrettyPrinter0.setSeparators(separators6);
        tools.jackson.core.JsonGenerator jsonGenerator8 = null;
        tools.jackson.core.util.JsonGeneratorDelegate jsonGeneratorDelegate9 = new tools.jackson.core.util.JsonGeneratorDelegate(jsonGenerator8);
        minimalPrettyPrinter0.beforeObjectEntries((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate9);
        tools.jackson.core.util.JsonGeneratorDelegate jsonGeneratorDelegate11 = new tools.jackson.core.util.JsonGeneratorDelegate((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate9);
        tools.jackson.core.JsonParser jsonParser12 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter13 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion14 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate17 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser12, tokenFilter13, inclusion14, true, true);
        tools.jackson.core.JsonParser jsonParser18 = filteringParserDelegate17.skipChildren();
        // The following exception was thrown during execution in test generation
        try {
            jsonGeneratorDelegate11.copyCurrentEvent(jsonParser18);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(minimalPrettyPrinter7);
        org.junit.Assert.assertNotNull(jsonParser18);
    }

    @Test
    public void test0386() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0386");
        tools.jackson.core.JacksonException.Reference reference2 = new tools.jackson.core.JacksonException.Reference((java.lang.Object) "hi!", (-149));
        int int3 = reference2.getIndex();
        org.junit.Assert.assertTrue("'" + int3 + "' != '" + (-149) + "'", int3 == (-149));
    }

    @Test
    public void test0387() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0387");
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter0 = new tools.jackson.core.util.MinimalPrettyPrinter();
        tools.jackson.core.JsonGenerator jsonGenerator1 = null;
        minimalPrettyPrinter0.beforeObjectEntries(jsonGenerator1);
        tools.jackson.core.util.Separators separators6 = new tools.jackson.core.util.Separators('~', ' ', ' ');
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter7 = minimalPrettyPrinter0.setSeparators(separators6);
        tools.jackson.core.JsonGenerator jsonGenerator8 = null;
        tools.jackson.core.util.JsonGeneratorDelegate jsonGeneratorDelegate9 = new tools.jackson.core.util.JsonGeneratorDelegate(jsonGenerator8);
        minimalPrettyPrinter0.beforeObjectEntries((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate9);
        tools.jackson.core.util.JsonGeneratorDelegate jsonGeneratorDelegate11 = new tools.jackson.core.util.JsonGeneratorDelegate((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate9);
        // The following exception was thrown during execution in test generation
        try {
            int int12 = jsonGeneratorDelegate9.streamWriteOutputBuffered();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(minimalPrettyPrinter7);
    }

    @Test
    public void test0388() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0388");
        tools.jackson.core.util.InternCache internCache0 = tools.jackson.core.util.InternCache.instance;
        tools.jackson.core.json.DupDetector dupDetector1 = null;
        tools.jackson.core.util.SimpleStreamWriteContext simpleStreamWriteContext2 = tools.jackson.core.util.SimpleStreamWriteContext.createRootContext(dupDetector1);
        tools.jackson.core.json.DupDetector dupDetector3 = simpleStreamWriteContext2.getDupDetector();
        boolean boolean4 = simpleStreamWriteContext2.inObject();
        boolean boolean5 = internCache0.containsValue((java.lang.Object) boolean4);
        boolean boolean6 = internCache0.isEmpty();
        tools.jackson.core.util.InternCache internCache7 = new tools.jackson.core.util.InternCache();
        internCache0.putAll((java.util.Map<java.lang.String, java.lang.String>) internCache7);
        tools.jackson.core.Version version9 = tools.jackson.core.Version.unknownVersion();
        int int10 = version9.getPatchLevel();
        tools.jackson.core.Version version11 = tools.jackson.core.Version.unknownVersion();
        boolean boolean12 = version11.isUnknownVersion();
        int int13 = version9.compareTo(version11);
        tools.jackson.core.util.BufferRecycler bufferRecycler14 = null;
        tools.jackson.core.util.TextBuffer textBuffer15 = new tools.jackson.core.util.TextBuffer(bufferRecycler14);
        textBuffer15.resetWithString("");
        int int18 = textBuffer15.getCurrentSegmentSize();
        char[] charArray19 = textBuffer15.contentsAsArray();
        char[] charArray20 = textBuffer15.contentsAsArray();
        boolean boolean21 = internCache7.remove((java.lang.Object) version11, (java.lang.Object) charArray20);
        // The following exception was thrown during execution in test generation
        try {
            int int24 = tools.jackson.core.io.NumberInput.parseInt(charArray20, 2, (int) '\000');
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: 1");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(internCache0);
        org.junit.Assert.assertNotNull(simpleStreamWriteContext2);
        org.junit.Assert.assertNull(dupDetector3);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
// flaky "2) test0388(RegressionTest0)":         org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNotNull(version9);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 0 + "'", int10 == 0);
        org.junit.Assert.assertNotNull(version11);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + true + "'", boolean12 == true);
        org.junit.Assert.assertTrue("'" + int13 + "' != '" + 0 + "'", int13 == 0);
        org.junit.Assert.assertTrue("'" + int18 + "' != '" + 0 + "'", int18 == 0);
        org.junit.Assert.assertNotNull(charArray19);
        org.junit.Assert.assertArrayEquals(charArray19, new char[] {});
        org.junit.Assert.assertNotNull(charArray20);
        org.junit.Assert.assertArrayEquals(charArray20, new char[] {});
        org.junit.Assert.assertTrue("'" + boolean21 + "' != '" + false + "'", boolean21 == false);
    }

    @Test
    public void test0389() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0389");
        tools.jackson.core.exc.StreamReadException streamReadException1 = new tools.jackson.core.exc.StreamReadException("");
        java.lang.String str2 = streamReadException1.getPathReference();
        tools.jackson.core.exc.StreamReadException streamReadException4 = new tools.jackson.core.exc.StreamReadException("");
        streamReadException1.addSuppressed((java.lang.Throwable) streamReadException4);
        tools.jackson.core.JsonParser jsonParser6 = streamReadException4.processor();
        tools.jackson.core.filter.TokenFilter tokenFilter7 = null;
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext8 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter7);
        tools.jackson.core.JsonToken jsonToken9 = tokenFilterContext8.nextTokenToRead();
        tools.jackson.core.filter.TokenFilter tokenFilter10 = tools.jackson.core.filter.TokenFilter.INCLUDE_ALL;
        tools.jackson.core.filter.TokenFilter tokenFilter11 = tokenFilterContext8.checkValue(tokenFilter10);
        java.math.BigDecimal bigDecimal12 = null;
        boolean boolean13 = tokenFilter10.includeNumber(bigDecimal12);
        tools.jackson.core.filter.TokenFilter tokenFilter14 = tokenFilter10.filterStartObject();
        java.math.BigInteger bigInteger15 = null;
        boolean boolean16 = tokenFilter14.includeNumber(bigInteger15);
        tools.jackson.core.JsonParser jsonParser17 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter18 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion19 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate22 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser17, tokenFilter18, inclusion19, true, true);
        tools.jackson.core.filter.TokenFilter tokenFilter23 = filteringParserDelegate22.getFilter();
        boolean boolean24 = filteringParserDelegate22.isExpectedStartArrayToken();
        tools.jackson.core.filter.TokenFilter tokenFilter25 = filteringParserDelegate22.getFilter();
        boolean boolean26 = tokenFilter14.includeValue((tools.jackson.core.JsonParser) filteringParserDelegate22);
        tools.jackson.core.exc.StreamReadException streamReadException27 = streamReadException4.withParser((tools.jackson.core.JsonParser) filteringParserDelegate22);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj28 = filteringParserDelegate22.getEmbeddedObject();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertEquals("'" + str2 + "' != '" + "" + "'", str2, "");
        org.junit.Assert.assertNull(jsonParser6);
        org.junit.Assert.assertNotNull(tokenFilterContext8);
        org.junit.Assert.assertNull(jsonToken9);
        org.junit.Assert.assertNotNull(tokenFilter10);
        org.junit.Assert.assertNotNull(tokenFilter11);
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + true + "'", boolean13 == true);
        org.junit.Assert.assertNotNull(tokenFilter14);
        org.junit.Assert.assertTrue("'" + boolean16 + "' != '" + true + "'", boolean16 == true);
        org.junit.Assert.assertNull(tokenFilter23);
        org.junit.Assert.assertTrue("'" + boolean24 + "' != '" + false + "'", boolean24 == false);
        org.junit.Assert.assertNull(tokenFilter25);
        org.junit.Assert.assertTrue("'" + boolean26 + "' != '" + true + "'", boolean26 == true);
        org.junit.Assert.assertNotNull(streamReadException27);
    }

    @Test
    public void test0390() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0390");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        tools.jackson.core.filter.TokenFilter tokenFilter6 = filteringParserDelegate5.getFilter();
        boolean boolean7 = filteringParserDelegate5.isExpectedStartArrayToken();
        boolean boolean8 = filteringParserDelegate5.hasCurrentToken();
        tools.jackson.core.TokenStreamContext tokenStreamContext9 = filteringParserDelegate5.streamReadContext();
        int int10 = filteringParserDelegate5.getMatchCount();
        tools.jackson.core.Base64Variant base64Variant11 = tools.jackson.core.Base64Variants.MODIFIED_FOR_URL;
        byte[] byteArray19 = new byte[] { (byte) 10, (byte) -1, (byte) -1, (byte) -1, (byte) -1 };
        int int21 = base64Variant11.encodeBase64Partial(10, (int) 'a', byteArray19, (int) (byte) 1);
        tools.jackson.core.Base64Variant base64Variant24 = new tools.jackson.core.Base64Variant(base64Variant11, "hi!", 0);
        tools.jackson.core.Base64Variant base64Variant25 = base64Variant11.withPaddingForbidden();
        java.lang.String str26 = base64Variant25.missingPaddingMessage();
        tools.jackson.core.util.BufferRecycler bufferRecycler27 = null;
        tools.jackson.core.util.ByteArrayBuilder byteArrayBuilder28 = new tools.jackson.core.util.ByteArrayBuilder(bufferRecycler27);
        byte[] byteArray30 = byteArrayBuilder28.completeAndCoalesce(10);
        byte[] byteArray31 = byteArrayBuilder28.toByteArray();
        java.lang.String str34 = base64Variant25.encode(byteArray31, false, "100.0");
        java.lang.String str35 = base64Variant25.toString();
        // The following exception was thrown during execution in test generation
        try {
            byte[] byteArray36 = filteringParserDelegate5.getBinaryValue(base64Variant25);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(tokenFilter6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertNotNull(tokenStreamContext9);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 0 + "'", int10 == 0);
        org.junit.Assert.assertNotNull(base64Variant11);
        org.junit.Assert.assertNotNull(byteArray19);
        org.junit.Assert.assertArrayEquals(byteArray19, new byte[] { (byte) 10, (byte) 65, (byte) 65, (byte) -1, (byte) -1 });
        org.junit.Assert.assertTrue("'" + int21 + "' != '" + 3 + "'", int21 == 3);
        org.junit.Assert.assertNotNull(base64Variant25);
        org.junit.Assert.assertEquals("'" + str26 + "' != '" + "Unexpected end of base64-encoded String: base64 variant 'MODIFIED-FOR-URL' expects padding (one or more '\000' characters) at the end. This Base64Variant might have been incorrectly configured" + "'", str26, "Unexpected end of base64-encoded String: base64 variant 'MODIFIED-FOR-URL' expects padding (one or more '\000' characters) at the end. This Base64Variant might have been incorrectly configured");
        org.junit.Assert.assertNotNull(byteArray30);
        org.junit.Assert.assertArrayEquals(byteArray30, new byte[] { (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0 });
        org.junit.Assert.assertNotNull(byteArray31);
        org.junit.Assert.assertArrayEquals(byteArray31, new byte[] { (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str34 + "' != '" + "AAAAAAAAAAAAAA" + "'", str34, "AAAAAAAAAAAAAA");
        org.junit.Assert.assertEquals("'" + str35 + "' != '" + "MODIFIED-FOR-URL" + "'", str35, "MODIFIED-FOR-URL");
    }

    @Test
    public void test0391() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0391");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        tools.jackson.core.filter.TokenFilter tokenFilter6 = filteringParserDelegate5.getFilter();
        boolean boolean7 = filteringParserDelegate5.isExpectedStartArrayToken();
        boolean boolean8 = filteringParserDelegate5.hasCurrentToken();
        filteringParserDelegate5.clearCurrentToken();
        boolean boolean10 = filteringParserDelegate5.isExpectedStartObjectToken();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj11 = filteringParserDelegate5.streamReadInputSource();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(tokenFilter6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
    }

    @Test
    public void test0392() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0392");
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter0 = new tools.jackson.core.util.MinimalPrettyPrinter();
        tools.jackson.core.JsonGenerator jsonGenerator1 = null;
        minimalPrettyPrinter0.beforeObjectEntries(jsonGenerator1);
        tools.jackson.core.util.Separators separators6 = new tools.jackson.core.util.Separators('~', ' ', ' ');
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter7 = minimalPrettyPrinter0.setSeparators(separators6);
        tools.jackson.core.JsonGenerator jsonGenerator8 = null;
        tools.jackson.core.util.JsonGeneratorDelegate jsonGeneratorDelegate9 = new tools.jackson.core.util.JsonGeneratorDelegate(jsonGenerator8);
        minimalPrettyPrinter0.beforeObjectEntries((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate9);
        tools.jackson.core.util.JsonGeneratorDelegate jsonGeneratorDelegate11 = new tools.jackson.core.util.JsonGeneratorDelegate((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate9);
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.JsonGenerator jsonGenerator13 = jsonGeneratorDelegate9.writeBoolean(false);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(minimalPrettyPrinter7);
    }

    @Test
    public void test0393() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0393");
        tools.jackson.core.util.BufferRecycler bufferRecycler0 = null;
        tools.jackson.core.util.TextBuffer textBuffer1 = new tools.jackson.core.util.TextBuffer(bufferRecycler0);
        textBuffer1.resetWithString("");
        int int4 = textBuffer1.getCurrentSegmentSize();
        java.lang.String str5 = textBuffer1.toString();
        // The following exception was thrown during execution in test generation
        try {
            int int7 = textBuffer1.contentsAsInt(false);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 0 + "'", int4 == 0);
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "" + "'", str5, "");
    }

    @Test
    public void test0394() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0394");
        int int1 = tools.jackson.core.io.JsonStringEncoder._initialCharBufSize(309);
        org.junit.Assert.assertTrue("'" + int1 + "' != '" + 353 + "'", int1 == 353);
    }

    @Test
    public void test0395() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0395");
        int int0 = tools.jackson.core.util.TextBuffer.MIN_SEGMENT_LEN;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 500 + "'", int0 == 500);
    }

    @Test
    public void test0396() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0396");
        tools.jackson.core.JsonGenerator jsonGenerator0 = null;
        tools.jackson.core.util.JsonGeneratorDelegate jsonGeneratorDelegate1 = new tools.jackson.core.util.JsonGeneratorDelegate(jsonGenerator0);
        tools.jackson.core.io.SerializedString serializedString2 = tools.jackson.core.PrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
        byte[] byteArray3 = serializedString2.asQuotedUTF8();
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.JsonGenerator jsonGenerator4 = jsonGeneratorDelegate1.writeRawValue((tools.jackson.core.SerializableString) serializedString2);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(serializedString2);
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertArrayEquals(byteArray3, new byte[] { (byte) 32 });
    }

    @Test
    public void test0397() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0397");
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter0 = new tools.jackson.core.util.MinimalPrettyPrinter();
        tools.jackson.core.JsonGenerator jsonGenerator1 = null;
        minimalPrettyPrinter0.beforeObjectEntries(jsonGenerator1);
        tools.jackson.core.util.Separators separators6 = new tools.jackson.core.util.Separators('~', ' ', ' ');
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter7 = minimalPrettyPrinter0.setSeparators(separators6);
        tools.jackson.core.JsonGenerator jsonGenerator8 = null;
        tools.jackson.core.util.JsonGeneratorDelegate jsonGeneratorDelegate9 = new tools.jackson.core.util.JsonGeneratorDelegate(jsonGenerator8);
        minimalPrettyPrinter0.beforeObjectEntries((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate9);
        tools.jackson.core.JsonGenerator jsonGenerator11 = null;
        tools.jackson.core.util.JsonGeneratorDelegate jsonGeneratorDelegate12 = new tools.jackson.core.util.JsonGeneratorDelegate(jsonGenerator11);
        // The following exception was thrown during execution in test generation
        try {
            minimalPrettyPrinter0.writeEndArray(jsonGenerator11, 10);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(minimalPrettyPrinter7);
    }

    @Test
    public void test0398() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0398");
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder0 = tools.jackson.core.json.JsonFactory.builderWithJackson2Defaults();
        tools.jackson.core.SerializableString serializableString1 = null;
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder2 = jsonFactoryBuilder0.rootValueSeparator(serializableString1);
        tools.jackson.core.io.OutputDecorator outputDecorator3 = null;
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder4 = jsonFactoryBuilder2.outputDecorator(outputDecorator3);
        tools.jackson.core.StreamWriteFeature streamWriteFeature5 = null;
        tools.jackson.core.StreamWriteFeature[] streamWriteFeatureArray6 = new tools.jackson.core.StreamWriteFeature[] {};
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder7 = jsonFactoryBuilder2.enable(streamWriteFeature5, streamWriteFeatureArray6);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(jsonFactoryBuilder0);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder2);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder4);
        org.junit.Assert.assertNotNull(streamWriteFeatureArray6);
        org.junit.Assert.assertArrayEquals(streamWriteFeatureArray6, new tools.jackson.core.StreamWriteFeature[] {});
    }

    @Test
    public void test0399() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0399");
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder0 = tools.jackson.core.json.JsonFactory.builderWithJackson2Defaults();
        tools.jackson.core.SerializableString serializableString1 = null;
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder2 = jsonFactoryBuilder0.rootValueSeparator(serializableString1);
        tools.jackson.core.io.CharacterEscapes characterEscapes3 = null;
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder4 = jsonFactoryBuilder0.characterEscapes(characterEscapes3);
        tools.jackson.core.io.OutputDecorator outputDecorator5 = jsonFactoryBuilder4.outputDecorator();
        tools.jackson.core.json.JsonReadFeature jsonReadFeature6 = null;
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder8 = jsonFactoryBuilder4.configure(jsonReadFeature6, false);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(jsonFactoryBuilder0);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder2);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder4);
        org.junit.Assert.assertNull(outputDecorator5);
    }

    @Test
    public void test0400() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0400");
        tools.jackson.core.util.DefaultPrettyPrinter defaultPrettyPrinter0 = new tools.jackson.core.util.DefaultPrettyPrinter();
        tools.jackson.core.util.DefaultPrettyPrinter.FixedSpaceIndenter fixedSpaceIndenter1 = tools.jackson.core.util.DefaultPrettyPrinter.FixedSpaceIndenter.instance();
        defaultPrettyPrinter0.indentArraysWith((tools.jackson.core.util.DefaultPrettyPrinter.Indenter) fixedSpaceIndenter1);
        tools.jackson.core.util.Separators separators3 = tools.jackson.core.util.Separators.createDefaultInstance();
        tools.jackson.core.util.Separators separators5 = separators3.withObjectEntrySeparator('4');
        tools.jackson.core.util.Separators separators7 = separators5.withArrayElementSeparator('#');
        tools.jackson.core.util.DefaultPrettyPrinter defaultPrettyPrinter8 = defaultPrettyPrinter0.withSeparators(separators7);
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter9 = new tools.jackson.core.util.MinimalPrettyPrinter();
        tools.jackson.core.JsonGenerator jsonGenerator10 = null;
        minimalPrettyPrinter9.beforeObjectEntries(jsonGenerator10);
        tools.jackson.core.util.Separators separators15 = new tools.jackson.core.util.Separators('~', ' ', ' ');
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter16 = minimalPrettyPrinter9.setSeparators(separators15);
        tools.jackson.core.JsonGenerator jsonGenerator17 = null;
        tools.jackson.core.util.JsonGeneratorDelegate jsonGeneratorDelegate18 = new tools.jackson.core.util.JsonGeneratorDelegate(jsonGenerator17);
        minimalPrettyPrinter9.beforeObjectEntries((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate18);
        // The following exception was thrown during execution in test generation
        try {
            defaultPrettyPrinter8.writeEndArray((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate18, 10);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(fixedSpaceIndenter1);
        org.junit.Assert.assertNotNull(separators3);
        org.junit.Assert.assertNotNull(separators5);
        org.junit.Assert.assertNotNull(separators7);
        org.junit.Assert.assertNotNull(defaultPrettyPrinter8);
        org.junit.Assert.assertNotNull(minimalPrettyPrinter16);
    }

    @Test
    public void test0401() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0401");
        int int1 = tools.jackson.core.io.schubfach.MathUtils.flog2pow10((int) (byte) -17);
        org.junit.Assert.assertTrue("'" + int1 + "' != '" + (-57) + "'", int1 == (-57));
    }

    @Test
    public void test0402() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0402");
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter0 = new tools.jackson.core.util.MinimalPrettyPrinter();
        tools.jackson.core.JsonGenerator jsonGenerator1 = null;
        minimalPrettyPrinter0.beforeObjectEntries(jsonGenerator1);
        tools.jackson.core.util.Separators separators6 = new tools.jackson.core.util.Separators('~', ' ', ' ');
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter7 = minimalPrettyPrinter0.setSeparators(separators6);
        tools.jackson.core.JsonGenerator jsonGenerator8 = null;
        tools.jackson.core.util.JsonGeneratorDelegate jsonGeneratorDelegate9 = new tools.jackson.core.util.JsonGeneratorDelegate(jsonGenerator8);
        minimalPrettyPrinter0.beforeObjectEntries((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate9);
        tools.jackson.core.util.JsonGeneratorDelegate jsonGeneratorDelegate11 = new tools.jackson.core.util.JsonGeneratorDelegate((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate9);
        tools.jackson.core.StreamWriteFeature streamWriteFeature12 = null;
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean13 = jsonGeneratorDelegate9.isEnabled(streamWriteFeature12);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(minimalPrettyPrinter7);
    }

    @Test
    public void test0403() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0403");
        tools.jackson.core.util.BufferRecycler bufferRecycler0 = null;
        tools.jackson.core.util.TextBuffer textBuffer1 = new tools.jackson.core.util.TextBuffer(bufferRecycler0);
        textBuffer1.resetWithString("");
        int int4 = textBuffer1.getCurrentSegmentSize();
        java.lang.String str5 = textBuffer1.toString();
        java.lang.String str8 = textBuffer1.finishAndReturn((int) 'B', false);
        java.lang.String str9 = textBuffer1.toString();
        char[] charArray10 = textBuffer1.emptyAndGetCurrentSegment();
        tools.jackson.core.util.TextBuffer textBuffer11 = tools.jackson.core.util.TextBuffer.fromInitial(charArray10);
        // The following exception was thrown during execution in test generation
        try {
            double double15 = tools.jackson.core.io.NumberInput.parseDouble(charArray10, (int) (short) 100, (int) '\000', false);
            org.junit.Assert.fail("Expected exception of type java.lang.NumberFormatException; message: empty String");
        } catch (java.lang.NumberFormatException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 0 + "'", int4 == 0);
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "" + "'", str5, "");
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "" + "'", str8, "");
        org.junit.Assert.assertEquals("'" + str9 + "' != '" + "" + "'", str9, "");
        org.junit.Assert.assertNotNull(charArray10);
        org.junit.Assert.assertNotNull(textBuffer11);
    }

    @Test
    public void test0404() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0404");
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder0 = tools.jackson.core.json.JsonFactory.builderWithJackson2Defaults();
        tools.jackson.core.SerializableString serializableString1 = null;
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder2 = jsonFactoryBuilder0.rootValueSeparator(serializableString1);
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder4 = jsonFactoryBuilder0.quoteChar(' ');
        tools.jackson.core.json.JsonReadFeature jsonReadFeature5 = null;
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder6 = jsonFactoryBuilder0.disable(jsonReadFeature5);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(jsonFactoryBuilder0);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder2);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder4);
    }

    @Test
    public void test0405() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0405");
        tools.jackson.core.util.DefaultPrettyPrinter defaultPrettyPrinter0 = new tools.jackson.core.util.DefaultPrettyPrinter();
        tools.jackson.core.util.DefaultPrettyPrinter.FixedSpaceIndenter fixedSpaceIndenter1 = tools.jackson.core.util.DefaultPrettyPrinter.FixedSpaceIndenter.instance();
        defaultPrettyPrinter0.indentArraysWith((tools.jackson.core.util.DefaultPrettyPrinter.Indenter) fixedSpaceIndenter1);
        tools.jackson.core.JsonGenerator jsonGenerator3 = null;
        tools.jackson.core.util.JsonGeneratorDelegate jsonGeneratorDelegate4 = new tools.jackson.core.util.JsonGeneratorDelegate(jsonGenerator3);
        // The following exception was thrown during execution in test generation
        try {
            fixedSpaceIndenter1.writeIndentation((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate4, 50000);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(fixedSpaceIndenter1);
    }

    @Test
    public void test0406() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0406");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        tools.jackson.core.filter.TokenFilter tokenFilter6 = filteringParserDelegate5.getFilter();
        boolean boolean7 = filteringParserDelegate5.isExpectedStartArrayToken();
        boolean boolean8 = filteringParserDelegate5.hasCurrentToken();
        tools.jackson.core.TokenStreamContext tokenStreamContext9 = filteringParserDelegate5.streamReadContext();
        tools.jackson.core.JsonParser jsonParser10 = filteringParserDelegate5.delegate();
        // The following exception was thrown during execution in test generation
        try {
            filteringParserDelegate5.finishToken();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(tokenFilter6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertNotNull(tokenStreamContext9);
        org.junit.Assert.assertNull(jsonParser10);
    }

    @Test
    public void test0407() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0407");
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder0 = tools.jackson.core.json.JsonFactory.builderWithJackson2Defaults();
        tools.jackson.core.SerializableString serializableString1 = null;
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder2 = jsonFactoryBuilder0.rootValueSeparator(serializableString1);
        tools.jackson.core.ErrorReportConfiguration errorReportConfiguration3 = null;
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder4 = jsonFactoryBuilder0.errorReportConfiguration(errorReportConfiguration3);
        tools.jackson.core.StreamWriteFeature streamWriteFeature5 = null;
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder6 = jsonFactoryBuilder0.enable(streamWriteFeature5);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(jsonFactoryBuilder0);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder2);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder4);
    }

    @Test
    public void test0408() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0408");
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter0 = new tools.jackson.core.util.MinimalPrettyPrinter();
        tools.jackson.core.JsonGenerator jsonGenerator1 = null;
        minimalPrettyPrinter0.beforeObjectEntries(jsonGenerator1);
        tools.jackson.core.util.Separators separators6 = new tools.jackson.core.util.Separators('~', ' ', ' ');
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter7 = minimalPrettyPrinter0.setSeparators(separators6);
        tools.jackson.core.JsonGenerator jsonGenerator8 = null;
        tools.jackson.core.util.JsonGeneratorDelegate jsonGeneratorDelegate9 = new tools.jackson.core.util.JsonGeneratorDelegate(jsonGenerator8);
        minimalPrettyPrinter0.beforeObjectEntries((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate9);
        tools.jackson.core.util.JsonGeneratorDelegate jsonGeneratorDelegate11 = new tools.jackson.core.util.JsonGeneratorDelegate((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate9);
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.JsonGenerator jsonGenerator13 = jsonGeneratorDelegate11.writeRawValue("tools.jackson.core.type.WritableTypeId[256]");
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(minimalPrettyPrinter7);
    }

    @Test
    public void test0409() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0409");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        boolean boolean6 = filteringParserDelegate5.isExpectedStartObjectToken();
        int int7 = filteringParserDelegate5.currentTokenId();
        tools.jackson.core.JsonToken jsonToken8 = null;
        boolean boolean9 = filteringParserDelegate5.hasToken(jsonToken8);
        tools.jackson.core.JsonToken jsonToken10 = null;
        tools.jackson.core.JsonGenerator jsonGenerator13 = null;
        tools.jackson.core.json.DupDetector dupDetector14 = tools.jackson.core.json.DupDetector.rootDetector(jsonGenerator13);
        tools.jackson.core.json.JsonReadContext jsonReadContext15 = tools.jackson.core.json.JsonReadContext.createRootContext(56, 7, dupDetector14);
        tools.jackson.core.type.WritableTypeId writableTypeId16 = new tools.jackson.core.type.WritableTypeId((java.lang.Object) filteringParserDelegate5, jsonToken10, (java.lang.Object) jsonReadContext15);
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.TokenStreamLocation tokenStreamLocation17 = filteringParserDelegate5.currentTokenLocation();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 0 + "'", int7 == 0);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + true + "'", boolean9 == true);
        org.junit.Assert.assertNotNull(dupDetector14);
        org.junit.Assert.assertNotNull(jsonReadContext15);
    }

    @Test
    public void test0410() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0410");
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter0 = new tools.jackson.core.util.MinimalPrettyPrinter();
        tools.jackson.core.JsonGenerator jsonGenerator1 = null;
        minimalPrettyPrinter0.beforeObjectEntries(jsonGenerator1);
        tools.jackson.core.util.Separators separators6 = new tools.jackson.core.util.Separators('~', ' ', ' ');
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter7 = minimalPrettyPrinter0.setSeparators(separators6);
        tools.jackson.core.JsonGenerator jsonGenerator8 = null;
        tools.jackson.core.util.JsonGeneratorDelegate jsonGeneratorDelegate9 = new tools.jackson.core.util.JsonGeneratorDelegate(jsonGenerator8);
        minimalPrettyPrinter0.beforeObjectEntries((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate9);
        tools.jackson.core.util.JsonGeneratorDelegate jsonGeneratorDelegate11 = new tools.jackson.core.util.JsonGeneratorDelegate((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate9);
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean12 = jsonGeneratorDelegate11.canWriteTypeId();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(minimalPrettyPrinter7);
    }

    @Test
    public void test0411() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0411");
        tools.jackson.core.Base64Variant base64Variant0 = tools.jackson.core.Base64Variants.MODIFIED_FOR_URL;
        byte[] byteArray8 = new byte[] { (byte) 10, (byte) -1, (byte) -1, (byte) -1, (byte) -1 };
        int int10 = base64Variant0.encodeBase64Partial(10, (int) 'a', byteArray8, (int) (byte) 1);
        tools.jackson.core.Base64Variant base64Variant13 = new tools.jackson.core.Base64Variant(base64Variant0, "hi!", 0);
        boolean boolean14 = base64Variant0.usesPadding();
        // The following exception was thrown during execution in test generation
        try {
            char char16 = base64Variant0.encodeBase64BitsAsChar((int) (byte) -1);
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: -1");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(base64Variant0);
        org.junit.Assert.assertNotNull(byteArray8);
        org.junit.Assert.assertArrayEquals(byteArray8, new byte[] { (byte) 10, (byte) 65, (byte) 65, (byte) -1, (byte) -1 });
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 3 + "'", int10 == 3);
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + false + "'", boolean14 == false);
    }

    @Test
    public void test0412() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0412");
        tools.jackson.core.StreamWriteConstraints streamWriteConstraints0 = tools.jackson.core.StreamWriteConstraints.defaults();
        tools.jackson.core.StreamWriteConstraints.overrideDefaultStreamWriteConstraints(streamWriteConstraints0);
        tools.jackson.core.StreamWriteConstraints.overrideDefaultStreamWriteConstraints(streamWriteConstraints0);
        // The following exception was thrown during execution in test generation
        try {
            streamWriteConstraints0.validateNestingDepth(56319);
            org.junit.Assert.fail("Expected exception of type tools.jackson.core.exc.StreamConstraintsException; message: Document nesting depth (56319) exceeds the maximum allowed (500, from `StreamWriteConstraints.getMaxNestingDepth()`)");
        } catch (tools.jackson.core.exc.StreamConstraintsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(streamWriteConstraints0);
    }

    @Test
    public void test0413() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0413");
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder0 = tools.jackson.core.json.JsonFactory.builderWithJackson2Defaults();
        tools.jackson.core.SerializableString serializableString1 = null;
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder2 = jsonFactoryBuilder0.rootValueSeparator(serializableString1);
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder3 = jsonFactoryBuilder0.configureForJackson2();
        tools.jackson.core.io.InputDecorator inputDecorator4 = null;
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder5 = jsonFactoryBuilder0.inputDecorator(inputDecorator4);
        tools.jackson.core.json.JsonWriteFeature jsonWriteFeature6 = null;
        tools.jackson.core.json.JsonWriteFeature jsonWriteFeature7 = null;
        tools.jackson.core.json.JsonWriteFeature[] jsonWriteFeatureArray8 = new tools.jackson.core.json.JsonWriteFeature[] { jsonWriteFeature7 };
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder9 = jsonFactoryBuilder5.disable(jsonWriteFeature6, jsonWriteFeatureArray8);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(jsonFactoryBuilder0);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder2);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder3);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder5);
        org.junit.Assert.assertNotNull(jsonWriteFeatureArray8);
        org.junit.Assert.assertArrayEquals(jsonWriteFeatureArray8, new tools.jackson.core.json.JsonWriteFeature[] { null });
    }

    @Test
    public void test0414() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0414");
        tools.jackson.core.util.BufferRecycler bufferRecycler0 = null;
        tools.jackson.core.util.TextBuffer textBuffer1 = new tools.jackson.core.util.TextBuffer(bufferRecycler0);
        textBuffer1.resetWithString("");
        int int4 = textBuffer1.getCurrentSegmentSize();
        java.lang.String str5 = textBuffer1.toString();
        java.lang.String str8 = textBuffer1.finishAndReturn((int) 'B', false);
        java.lang.String str9 = textBuffer1.toString();
        textBuffer1.resetWith(' ');
        textBuffer1.ensureNotShared();
        byte[] byteArray13 = tools.jackson.core.util.ByteArrayBuilder.NO_BYTES;
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str16 = textBuffer1.resetWithUTF8(byteArray13, 0, 309);
            org.junit.Assert.fail("Expected exception of type java.lang.StringIndexOutOfBoundsException; message: String index out of range: 309");
        } catch (java.lang.StringIndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 0 + "'", int4 == 0);
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "" + "'", str5, "");
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "" + "'", str8, "");
        org.junit.Assert.assertEquals("'" + str9 + "' != '" + "" + "'", str9, "");
        org.junit.Assert.assertNotNull(byteArray13);
        org.junit.Assert.assertArrayEquals(byteArray13, new byte[] {});
    }

    @Test
    public void test0415() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0415");
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter0 = new tools.jackson.core.util.MinimalPrettyPrinter();
        tools.jackson.core.JsonGenerator jsonGenerator1 = null;
        minimalPrettyPrinter0.beforeObjectEntries(jsonGenerator1);
        tools.jackson.core.util.Separators separators6 = new tools.jackson.core.util.Separators('~', ' ', ' ');
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter7 = minimalPrettyPrinter0.setSeparators(separators6);
        tools.jackson.core.JsonGenerator jsonGenerator8 = null;
        tools.jackson.core.util.JsonGeneratorDelegate jsonGeneratorDelegate9 = new tools.jackson.core.util.JsonGeneratorDelegate(jsonGenerator8);
        minimalPrettyPrinter0.beforeObjectEntries((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate9);
        tools.jackson.core.util.JsonGeneratorDelegate jsonGeneratorDelegate11 = new tools.jackson.core.util.JsonGeneratorDelegate((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate9);
        tools.jackson.core.util.BufferRecycler bufferRecycler12 = null;
        tools.jackson.core.util.ByteArrayBuilder byteArrayBuilder13 = new tools.jackson.core.util.ByteArrayBuilder(bufferRecycler12);
        byte[] byteArray15 = byteArrayBuilder13.completeAndCoalesce(10);
        byte[] byteArray16 = byteArrayBuilder13.toByteArray();
        tools.jackson.core.util.BufferRecycler bufferRecycler17 = byteArrayBuilder13.bufferRecycler();
        byteArrayBuilder13.appendFourBytes((-3));
        tools.jackson.core.util.BufferRecycler bufferRecycler20 = byteArrayBuilder13.bufferRecycler();
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.JsonGenerator jsonGenerator21 = jsonGeneratorDelegate9.writeObjectId((java.lang.Object) bufferRecycler20);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(minimalPrettyPrinter7);
        org.junit.Assert.assertNotNull(byteArray15);
        org.junit.Assert.assertArrayEquals(byteArray15, new byte[] { (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0 });
        org.junit.Assert.assertNotNull(byteArray16);
        org.junit.Assert.assertArrayEquals(byteArray16, new byte[] { (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0 });
        org.junit.Assert.assertNull(bufferRecycler17);
        org.junit.Assert.assertNull(bufferRecycler20);
    }

    @Test
    public void test0416() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0416");
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder0 = tools.jackson.core.json.JsonFactory.builderWithJackson2Defaults();
        tools.jackson.core.SerializableString serializableString1 = null;
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder2 = jsonFactoryBuilder0.rootValueSeparator(serializableString1);
        tools.jackson.core.io.OutputDecorator outputDecorator3 = jsonFactoryBuilder2.outputDecorator();
        tools.jackson.core.util.RecyclerPool<tools.jackson.core.util.BufferRecycler> bufferRecyclerRecyclerPool4 = jsonFactoryBuilder2.recyclerPool();
        tools.jackson.core.StreamReadFeature streamReadFeature5 = null;
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder6 = jsonFactoryBuilder2.disable(streamReadFeature5);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(jsonFactoryBuilder0);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder2);
        org.junit.Assert.assertNull(outputDecorator3);
        org.junit.Assert.assertNotNull(bufferRecyclerRecyclerPool4);
    }

    @Test
    public void test0417() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0417");
        int int0 = tools.jackson.core.io.CharacterEscapes.ESCAPE_STANDARD;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + (-1) + "'", int0 == (-1));
    }

    @Test
    public void test0418() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0418");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        boolean boolean6 = filteringParserDelegate5.isExpectedStartObjectToken();
        int int7 = filteringParserDelegate5.currentTokenId();
        tools.jackson.core.JsonToken jsonToken8 = null;
        boolean boolean9 = filteringParserDelegate5.hasToken(jsonToken8);
        int int10 = filteringParserDelegate5.currentTokenId();
        boolean boolean11 = filteringParserDelegate5.isExpectedStartObjectToken();
        boolean boolean12 = filteringParserDelegate5.hasCurrentToken();
        tools.jackson.core.type.ResolvedType resolvedType13 = null;
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.JacksonException.Reference reference14 = filteringParserDelegate5.readValueAs(resolvedType13);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 0 + "'", int7 == 0);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + true + "'", boolean9 == true);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 0 + "'", int10 == 0);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
    }

    @Test
    public void test0419() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0419");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        boolean boolean6 = filteringParserDelegate5.isExpectedStartObjectToken();
        // The following exception was thrown during execution in test generation
        try {
            java.math.BigDecimal bigDecimal7 = filteringParserDelegate5.getDecimalValue();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
    }

    @Test
    public void test0420() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0420");
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter0 = new tools.jackson.core.util.MinimalPrettyPrinter();
        tools.jackson.core.JsonGenerator jsonGenerator1 = null;
        minimalPrettyPrinter0.beforeObjectEntries(jsonGenerator1);
        tools.jackson.core.util.Separators separators6 = new tools.jackson.core.util.Separators('~', ' ', ' ');
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter7 = minimalPrettyPrinter0.setSeparators(separators6);
        tools.jackson.core.JsonGenerator jsonGenerator8 = null;
        tools.jackson.core.util.JsonGeneratorDelegate jsonGeneratorDelegate9 = new tools.jackson.core.util.JsonGeneratorDelegate(jsonGenerator8);
        minimalPrettyPrinter0.beforeObjectEntries((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate9);
        tools.jackson.core.util.JsonGeneratorDelegate jsonGeneratorDelegate11 = new tools.jackson.core.util.JsonGeneratorDelegate((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate9);
        tools.jackson.core.StreamWriteFeature streamWriteFeature12 = null;
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean13 = jsonGeneratorDelegate11.isEnabled(streamWriteFeature12);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(minimalPrettyPrinter7);
    }

    @Test
    public void test0421() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0421");
        tools.jackson.core.ObjectWriteContext objectWriteContext0 = null;
        tools.jackson.core.io.IOContext iOContext1 = null;
        java.io.Writer writer4 = null;
        tools.jackson.core.io.SerializedString serializedString5 = tools.jackson.core.PrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
        byte[] byteArray6 = serializedString5.asQuotedUTF8();
        java.lang.String str7 = serializedString5.toString();
        tools.jackson.core.Base64Variant base64Variant8 = tools.jackson.core.Base64Variants.MODIFIED_FOR_URL;
        byte[] byteArray16 = new byte[] { (byte) 10, (byte) -1, (byte) -1, (byte) -1, (byte) -1 };
        int int18 = base64Variant8.encodeBase64Partial(10, (int) 'a', byteArray16, (int) (byte) 1);
        tools.jackson.core.Base64Variant base64Variant21 = new tools.jackson.core.Base64Variant(base64Variant8, "hi!", 0);
        tools.jackson.core.Base64Variant base64Variant22 = base64Variant8.withPaddingAllowed();
        tools.jackson.core.Base64Variant base64Variant23 = tools.jackson.core.Base64Variants.MODIFIED_FOR_URL;
        byte[] byteArray31 = new byte[] { (byte) 10, (byte) -1, (byte) -1, (byte) -1, (byte) -1 };
        int int33 = base64Variant23.encodeBase64Partial(10, (int) 'a', byteArray31, (int) (byte) 1);
        java.lang.String str36 = base64Variant8.encode(byteArray31, false, "//0.0.0");
        int int38 = serializedString5.appendUnquotedUTF8(byteArray31, 56320);
        tools.jackson.core.util.DefaultPrettyPrinter defaultPrettyPrinter39 = new tools.jackson.core.util.DefaultPrettyPrinter();
        tools.jackson.core.util.Separators separators40 = tools.jackson.core.util.Separators.createDefaultInstance();
        tools.jackson.core.util.Separators separators42 = separators40.withObjectNameValueSeparator('\000');
        tools.jackson.core.util.DefaultPrettyPrinter defaultPrettyPrinter43 = new tools.jackson.core.util.DefaultPrettyPrinter(defaultPrettyPrinter39, separators40);
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder44 = tools.jackson.core.json.JsonFactory.builder();
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder46 = jsonFactoryBuilder44.highestNonEscapedChar((-324));
        tools.jackson.core.util.JsonpCharacterEscapes jsonpCharacterEscapes47 = tools.jackson.core.util.JsonpCharacterEscapes.instance();
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder48 = jsonFactoryBuilder46.characterEscapes((tools.jackson.core.io.CharacterEscapes) jsonpCharacterEscapes47);
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.json.WriterBasedJsonGenerator writerBasedJsonGenerator51 = new tools.jackson.core.json.WriterBasedJsonGenerator(objectWriteContext0, iOContext1, 1000, (int) (short) 1, writer4, (tools.jackson.core.SerializableString) serializedString5, (tools.jackson.core.PrettyPrinter) defaultPrettyPrinter39, (tools.jackson.core.io.CharacterEscapes) jsonpCharacterEscapes47, (int) (byte) 0, ',');
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(serializedString5);
        org.junit.Assert.assertNotNull(byteArray6);
        org.junit.Assert.assertArrayEquals(byteArray6, new byte[] { (byte) 32 });
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + " " + "'", str7, " ");
        org.junit.Assert.assertNotNull(base64Variant8);
        org.junit.Assert.assertNotNull(byteArray16);
        org.junit.Assert.assertArrayEquals(byteArray16, new byte[] { (byte) 10, (byte) 65, (byte) 65, (byte) -1, (byte) -1 });
        org.junit.Assert.assertTrue("'" + int18 + "' != '" + 3 + "'", int18 == 3);
        org.junit.Assert.assertNotNull(base64Variant22);
        org.junit.Assert.assertNotNull(base64Variant23);
        org.junit.Assert.assertNotNull(byteArray31);
        org.junit.Assert.assertArrayEquals(byteArray31, new byte[] { (byte) 10, (byte) 65, (byte) 65, (byte) -1, (byte) -1 });
        org.junit.Assert.assertTrue("'" + int33 + "' != '" + 3 + "'", int33 == 3);
        org.junit.Assert.assertEquals("'" + str36 + "' != '" + "CkFB__8" + "'", str36, "CkFB__8");
        org.junit.Assert.assertTrue("'" + int38 + "' != '" + (-1) + "'", int38 == (-1));
        org.junit.Assert.assertNotNull(separators40);
        org.junit.Assert.assertNotNull(separators42);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder44);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder46);
        org.junit.Assert.assertNotNull(jsonpCharacterEscapes47);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder48);
    }

    @Test
    public void test0422() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0422");
        tools.jackson.core.util.Separators separators0 = new tools.jackson.core.util.Separators();
        char char1 = separators0.getObjectNameValueSeparator();
        org.junit.Assert.assertTrue("'" + char1 + "' != '" + ':' + "'", char1 == ':');
    }

    @Test
    public void test0423() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0423");
        tools.jackson.core.ObjectWriteContext objectWriteContext0 = null;
        tools.jackson.core.io.IOContext iOContext1 = null;
        tools.jackson.core.util.BufferRecycler bufferRecycler4 = null;
        tools.jackson.core.util.ByteArrayBuilder byteArrayBuilder5 = new tools.jackson.core.util.ByteArrayBuilder(bufferRecycler4);
        tools.jackson.core.util.BufferRecycler bufferRecycler6 = byteArrayBuilder5.bufferRecycler();
        byte[] byteArray7 = byteArrayBuilder5.getClearAndRelease();
        byteArrayBuilder5.appendTwoBytes(500);
        tools.jackson.core.io.SerializedString serializedString10 = tools.jackson.core.PrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
        byte[] byteArray11 = serializedString10.asQuotedUTF8();
        java.lang.String str12 = serializedString10.toString();
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder13 = tools.jackson.core.json.JsonFactory.builder();
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder15 = jsonFactoryBuilder13.highestNonEscapedChar((-324));
        tools.jackson.core.util.JsonpCharacterEscapes jsonpCharacterEscapes16 = tools.jackson.core.util.JsonpCharacterEscapes.instance();
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder17 = jsonFactoryBuilder15.characterEscapes((tools.jackson.core.io.CharacterEscapes) jsonpCharacterEscapes16);
        tools.jackson.core.util.DefaultPrettyPrinter defaultPrettyPrinter18 = new tools.jackson.core.util.DefaultPrettyPrinter();
        tools.jackson.core.util.DefaultIndenter defaultIndenter19 = new tools.jackson.core.util.DefaultIndenter();
        java.lang.String str20 = defaultIndenter19.getEol();
        tools.jackson.core.util.DefaultIndenter defaultIndenter22 = defaultIndenter19.withLinefeed("AAAAAAAAAAAAAA");
        defaultPrettyPrinter18.indentObjectsWith((tools.jackson.core.util.DefaultPrettyPrinter.Indenter) defaultIndenter22);
        byte[] byteArray26 = null;
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.json.UTF8JsonGenerator uTF8JsonGenerator29 = new tools.jackson.core.json.UTF8JsonGenerator(objectWriteContext0, iOContext1, (int) (byte) 100, (int) (short) 0, (java.io.OutputStream) byteArrayBuilder5, (tools.jackson.core.SerializableString) serializedString10, (tools.jackson.core.io.CharacterEscapes) jsonpCharacterEscapes16, (tools.jackson.core.PrettyPrinter) defaultPrettyPrinter18, (int) '#', '\000', byteArray26, (int) '\000', false);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(bufferRecycler6);
        org.junit.Assert.assertNotNull(byteArray7);
        org.junit.Assert.assertArrayEquals(byteArray7, new byte[] {});
        org.junit.Assert.assertNotNull(serializedString10);
        org.junit.Assert.assertNotNull(byteArray11);
        org.junit.Assert.assertArrayEquals(byteArray11, new byte[] { (byte) 32 });
        org.junit.Assert.assertEquals("'" + str12 + "' != '" + " " + "'", str12, " ");
        org.junit.Assert.assertNotNull(jsonFactoryBuilder13);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder15);
        org.junit.Assert.assertNotNull(jsonpCharacterEscapes16);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder17);
        org.junit.Assert.assertEquals("'" + str20 + "' != '" + "\r\n" + "'", str20, "\r\n");
        org.junit.Assert.assertNotNull(defaultIndenter22);
    }

    @Test
    public void test0424() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0424");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        boolean boolean6 = filteringParserDelegate5.isExpectedStartObjectToken();
        int int7 = filteringParserDelegate5.currentTokenId();
        boolean boolean8 = filteringParserDelegate5.isExpectedStartObjectToken();
        tools.jackson.core.filter.TokenFilter tokenFilter9 = filteringParserDelegate5.getFilter();
        java.io.Writer writer10 = null;
        // The following exception was thrown during execution in test generation
        try {
            long long11 = filteringParserDelegate5.readString(writer10);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 0 + "'", int7 == 0);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertNull(tokenFilter9);
    }

    @Test
    public void test0425() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0425");
        tools.jackson.core.ObjectWriteContext objectWriteContext0 = null;
        tools.jackson.core.io.IOContext iOContext1 = null;
        tools.jackson.core.util.BufferRecycler bufferRecycler4 = null;
        tools.jackson.core.util.ByteArrayBuilder byteArrayBuilder5 = new tools.jackson.core.util.ByteArrayBuilder(bufferRecycler4);
        byte[] byteArray7 = byteArrayBuilder5.completeAndCoalesce(10);
        byte[] byteArray8 = byteArrayBuilder5.toByteArray();
        byteArrayBuilder5.close();
        tools.jackson.core.io.SerializedString serializedString10 = tools.jackson.core.PrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
        tools.jackson.core.util.JsonpCharacterEscapes jsonpCharacterEscapes11 = new tools.jackson.core.util.JsonpCharacterEscapes();
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter12 = new tools.jackson.core.util.MinimalPrettyPrinter();
        tools.jackson.core.JsonGenerator jsonGenerator13 = null;
        minimalPrettyPrinter12.beforeObjectEntries(jsonGenerator13);
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.json.UTF8JsonGenerator uTF8JsonGenerator17 = new tools.jackson.core.json.UTF8JsonGenerator(objectWriteContext0, iOContext1, (int) '\000', 0, (java.io.OutputStream) byteArrayBuilder5, (tools.jackson.core.SerializableString) serializedString10, (tools.jackson.core.io.CharacterEscapes) jsonpCharacterEscapes11, (tools.jackson.core.PrettyPrinter) minimalPrettyPrinter12, 500, '4');
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray7);
        org.junit.Assert.assertArrayEquals(byteArray7, new byte[] { (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0 });
        org.junit.Assert.assertNotNull(byteArray8);
        org.junit.Assert.assertArrayEquals(byteArray8, new byte[] { (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0 });
        org.junit.Assert.assertNotNull(serializedString10);
    }

    @Test
    public void test0426() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0426");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        tools.jackson.core.filter.TokenFilter tokenFilter6 = filteringParserDelegate5.getFilter();
        boolean boolean7 = filteringParserDelegate5.isExpectedStartArrayToken();
        boolean boolean8 = filteringParserDelegate5.hasCurrentToken();
        tools.jackson.core.json.DupDetector dupDetector9 = tools.jackson.core.json.DupDetector.rootDetector((tools.jackson.core.JsonParser) filteringParserDelegate5);
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.exc.StreamReadException streamReadException11 = new tools.jackson.core.exc.StreamReadException((tools.jackson.core.JsonParser) filteringParserDelegate5, "Unexpected end of base64-encoded String: base64 variant 'MODIFIED-FOR-URL' expects padding (one or more '\000' characters) at the end. This Base64Variant might have been incorrectly configured");
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(tokenFilter6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertNotNull(dupDetector9);
    }

    @Test
    public void test0427() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0427");
        tools.jackson.core.StreamWriteConstraints.Builder builder0 = tools.jackson.core.StreamWriteConstraints.builder();
        tools.jackson.core.StreamWriteConstraints streamWriteConstraints1 = builder0.build();
        tools.jackson.core.StreamWriteConstraints.Builder builder3 = builder0.maxNestingDepth((int) 'a');
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(streamWriteConstraints1);
        org.junit.Assert.assertNotNull(builder3);
    }

    @Test
    public void test0428() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0428");
        tools.jackson.core.JsonPointer jsonPointer0 = tools.jackson.core.JsonPointer.empty();
        boolean boolean1 = jsonPointer0.mayMatchElement();
        tools.jackson.core.JsonPointer jsonPointer3 = jsonPointer0.appendProperty("tools.jackson.core.exc.StreamWriteException: hi!\n at [No location information]");
        boolean boolean4 = jsonPointer0.matches();
        java.lang.String str5 = jsonPointer0.getMatchingProperty();
        org.junit.Assert.assertNotNull(jsonPointer0);
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
        org.junit.Assert.assertNotNull(jsonPointer3);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + true + "'", boolean4 == true);
        org.junit.Assert.assertNull(str5);
    }

    @Test
    public void test0429() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0429");
        tools.jackson.core.JsonToken jsonToken1 = null;
        tools.jackson.core.type.WritableTypeId writableTypeId3 = new tools.jackson.core.type.WritableTypeId((java.lang.Object) '#', jsonToken1, (java.lang.Object) ' ');
        writableTypeId3.asProperty = "hi!";
        tools.jackson.core.JsonToken jsonToken7 = null;
        tools.jackson.core.type.WritableTypeId writableTypeId9 = new tools.jackson.core.type.WritableTypeId((java.lang.Object) '#', jsonToken7, (java.lang.Object) ' ');
        tools.jackson.core.type.WritableTypeId.Inclusion inclusion10 = tools.jackson.core.type.WritableTypeId.Inclusion.PAYLOAD_PROPERTY;
        writableTypeId9.include = inclusion10;
        writableTypeId3.include = inclusion10;
        tools.jackson.core.type.WritableTypeId.Inclusion inclusion13 = writableTypeId3.include;
        boolean boolean14 = inclusion13.requiresObjectContext();
        org.junit.Assert.assertTrue("'" + inclusion10 + "' != '" + tools.jackson.core.type.WritableTypeId.Inclusion.PAYLOAD_PROPERTY + "'", inclusion10.equals(tools.jackson.core.type.WritableTypeId.Inclusion.PAYLOAD_PROPERTY));
        org.junit.Assert.assertTrue("'" + inclusion13 + "' != '" + tools.jackson.core.type.WritableTypeId.Inclusion.PAYLOAD_PROPERTY + "'", inclusion13.equals(tools.jackson.core.type.WritableTypeId.Inclusion.PAYLOAD_PROPERTY));
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + true + "'", boolean14 == true);
    }

    @Test
    public void test0430() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0430");
        tools.jackson.core.exc.StreamReadException streamReadException1 = new tools.jackson.core.exc.StreamReadException("hi!");
        tools.jackson.core.JsonParser jsonParser2 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter3 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion4 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate7 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser2, tokenFilter3, inclusion4, true, true);
        boolean boolean8 = filteringParserDelegate7.isExpectedStartObjectToken();
        tools.jackson.core.exc.StreamReadException streamReadException9 = streamReadException1.withParser((tools.jackson.core.JsonParser) filteringParserDelegate7);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str10 = filteringParserDelegate7.getText();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertNotNull(streamReadException9);
    }

    @Test
    public void test0431() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0431");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        tools.jackson.core.filter.TokenFilter tokenFilter6 = filteringParserDelegate5.getFilter();
        tools.jackson.core.JsonToken jsonToken7 = filteringParserDelegate5.currentToken();
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.TokenStreamLocation tokenStreamLocation8 = filteringParserDelegate5.currentLocation();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(tokenFilter6);
        org.junit.Assert.assertNull(jsonToken7);
    }

    @Test
    public void test0432() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0432");
        tools.jackson.core.sym.ByteQuadsCanonicalizer byteQuadsCanonicalizer0 = tools.jackson.core.sym.ByteQuadsCanonicalizer.createRoot();
        byteQuadsCanonicalizer0.release();
        tools.jackson.core.sym.ByteQuadsCanonicalizer byteQuadsCanonicalizer3 = byteQuadsCanonicalizer0.makeChildOrPlaceholder(309);
        int[] intArray5 = tools.jackson.core.io.CharTypes.getInputCodeUtf8();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str7 = byteQuadsCanonicalizer3.addName("\"_2QK\\n\"", intArray5, 100000000);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: Internal error: Cannot add names to Placeholder symbol table");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteQuadsCanonicalizer0);
        org.junit.Assert.assertNotNull(byteQuadsCanonicalizer3);
        org.junit.Assert.assertNotNull(intArray5);
    }

    @Test
    public void test0433() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0433");
        tools.jackson.core.util.DefaultPrettyPrinter.NopIndenter nopIndenter0 = new tools.jackson.core.util.DefaultPrettyPrinter.NopIndenter();
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter1 = new tools.jackson.core.util.MinimalPrettyPrinter();
        tools.jackson.core.JsonGenerator jsonGenerator2 = null;
        minimalPrettyPrinter1.beforeObjectEntries(jsonGenerator2);
        tools.jackson.core.util.Separators separators7 = new tools.jackson.core.util.Separators('~', ' ', ' ');
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter8 = minimalPrettyPrinter1.setSeparators(separators7);
        tools.jackson.core.JsonGenerator jsonGenerator9 = null;
        tools.jackson.core.util.JsonGeneratorDelegate jsonGeneratorDelegate10 = new tools.jackson.core.util.JsonGeneratorDelegate(jsonGenerator9);
        minimalPrettyPrinter1.beforeObjectEntries((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate10);
        tools.jackson.core.filter.TokenFilter tokenFilter12 = null;
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext13 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter12);
        tools.jackson.core.JsonToken jsonToken14 = tokenFilterContext13.nextTokenToRead();
        tools.jackson.core.filter.TokenFilter tokenFilter15 = tools.jackson.core.filter.TokenFilter.INCLUDE_ALL;
        tools.jackson.core.filter.TokenFilter tokenFilter16 = tokenFilterContext13.checkValue(tokenFilter15);
        java.math.BigDecimal bigDecimal17 = null;
        boolean boolean18 = tokenFilter15.includeNumber(bigDecimal17);
        tools.jackson.core.filter.TokenFilter tokenFilter20 = tokenFilter15.includeRootValue((int) (byte) 1);
        tools.jackson.core.io.IOContext iOContext21 = null;
        java.io.InputStream inputStream22 = null;
        byte[] byteArray26 = new byte[] { (byte) 100, (byte) 1 };
        tools.jackson.core.io.UTF32Reader uTF32Reader30 = new tools.jackson.core.io.UTF32Reader(iOContext21, inputStream22, false, byteArray26, (int) (short) 0, (-1), false);
        boolean boolean32 = tokenFilter20.includeString((java.io.Reader) uTF32Reader30, (-1074));
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion33 = tools.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_NON_NULL;
        tools.jackson.core.filter.FilteringGeneratorDelegate filteringGeneratorDelegate35 = new tools.jackson.core.filter.FilteringGeneratorDelegate((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate10, tokenFilter20, inclusion33, true);
        nopIndenter0.writeIndentation((tools.jackson.core.JsonGenerator) filteringGeneratorDelegate35, (int) '#');
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.JsonGenerator jsonGenerator38 = filteringGeneratorDelegate35.writeEndObject();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(minimalPrettyPrinter8);
        org.junit.Assert.assertNotNull(tokenFilterContext13);
        org.junit.Assert.assertNull(jsonToken14);
        org.junit.Assert.assertNotNull(tokenFilter15);
        org.junit.Assert.assertNotNull(tokenFilter16);
        org.junit.Assert.assertTrue("'" + boolean18 + "' != '" + true + "'", boolean18 == true);
        org.junit.Assert.assertNotNull(tokenFilter20);
        org.junit.Assert.assertNotNull(byteArray26);
        org.junit.Assert.assertArrayEquals(byteArray26, new byte[] { (byte) 100, (byte) 1 });
        org.junit.Assert.assertTrue("'" + boolean32 + "' != '" + true + "'", boolean32 == true);
        org.junit.Assert.assertTrue("'" + inclusion33 + "' != '" + tools.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_NON_NULL + "'", inclusion33.equals(tools.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_NON_NULL));
    }

    @Test
    public void test0434() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0434");
        long long1 = tools.jackson.core.io.schubfach.MathUtils.g0(33);
        org.junit.Assert.assertTrue("'" + long1 + "' != '" + 8875587037423695204L + "'", long1 == 8875587037423695204L);
    }

    @Test
    public void test0435() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0435");
        tools.jackson.core.util.BufferRecycler bufferRecycler0 = null;
        tools.jackson.core.util.TextBuffer textBuffer1 = new tools.jackson.core.util.TextBuffer(bufferRecycler0);
        textBuffer1.resetWithString("");
        boolean boolean4 = textBuffer1.hasTextAsCharacters();
        boolean boolean5 = textBuffer1.hasTextAsCharacters();
        java.lang.String str7 = textBuffer1.setCurrentAndReturn(0);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "" + "'", str7, "");
    }

    @Test
    public void test0436() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0436");
        tools.jackson.core.exc.StreamReadException streamReadException1 = new tools.jackson.core.exc.StreamReadException("hi!");
        tools.jackson.core.JsonParser jsonParser2 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter3 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion4 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate7 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser2, tokenFilter3, inclusion4, true, true);
        boolean boolean8 = filteringParserDelegate7.isExpectedStartObjectToken();
        tools.jackson.core.exc.StreamReadException streamReadException9 = streamReadException1.withParser((tools.jackson.core.JsonParser) filteringParserDelegate7);
        // The following exception was thrown during execution in test generation
        try {
            int int10 = filteringParserDelegate7.getIntValue();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertNotNull(streamReadException9);
    }

    @Test
    public void test0437() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0437");
        tools.jackson.core.JsonToken jsonToken1 = null;
        tools.jackson.core.type.WritableTypeId writableTypeId3 = new tools.jackson.core.type.WritableTypeId((java.lang.Object) '#', jsonToken1, (java.lang.Object) ' ');
        tools.jackson.core.JsonToken jsonToken4 = writableTypeId3.valueShape;
        java.lang.Object obj5 = writableTypeId3.extra;
        java.lang.Object obj6 = writableTypeId3.forValue;
        java.lang.String str7 = writableTypeId3.asProperty;
        org.junit.Assert.assertNull(jsonToken4);
        org.junit.Assert.assertNull(obj5);
        org.junit.Assert.assertEquals("'" + obj6 + "' != '" + '#' + "'", obj6, '#');
        org.junit.Assert.assertNull(str7);
    }

    @Test
    public void test0438() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0438");
        int int0 = tools.jackson.core.json.JsonWriteContext.STATUS_OK_AFTER_SPACE;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 3 + "'", int0 == 3);
    }

    @Test
    public void test0439() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0439");
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter0 = new tools.jackson.core.util.MinimalPrettyPrinter();
        tools.jackson.core.JsonGenerator jsonGenerator1 = null;
        minimalPrettyPrinter0.beforeObjectEntries(jsonGenerator1);
        tools.jackson.core.util.Separators separators6 = new tools.jackson.core.util.Separators('~', ' ', ' ');
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter7 = minimalPrettyPrinter0.setSeparators(separators6);
        tools.jackson.core.JsonGenerator jsonGenerator8 = null;
        tools.jackson.core.util.JsonGeneratorDelegate jsonGeneratorDelegate9 = new tools.jackson.core.util.JsonGeneratorDelegate(jsonGenerator8);
        minimalPrettyPrinter0.beforeObjectEntries((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate9);
        tools.jackson.core.util.JsonGeneratorDelegate jsonGeneratorDelegate11 = new tools.jackson.core.util.JsonGeneratorDelegate((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate9);
        double[] doubleArray12 = new double[] {};
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.JsonGenerator jsonGenerator15 = jsonGeneratorDelegate9.writeArray(doubleArray12, 12000, (int) (short) 1);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(minimalPrettyPrinter7);
        org.junit.Assert.assertNotNull(doubleArray12);
        org.junit.Assert.assertArrayEquals(doubleArray12, new double[] {}, 1.0E-15);
    }

    @Test
    public void test0440() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0440");
        tools.jackson.core.io.CharTypes charTypes0 = new tools.jackson.core.io.CharTypes();
    }

    @Test
    public void test0441() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0441");
        int int0 = tools.jackson.core.util.BufferRecycler.BYTE_BASE64_CODEC_BUFFER;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 3 + "'", int0 == 3);
    }

    @Test
    public void test0442() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0442");
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder0 = tools.jackson.core.json.JsonFactory.builderWithJackson2Defaults();
        tools.jackson.core.SerializableString serializableString1 = null;
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder2 = jsonFactoryBuilder0.rootValueSeparator(serializableString1);
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder3 = jsonFactoryBuilder0.configureForJackson2();
        int int4 = jsonFactoryBuilder3.highestNonEscapedChar();
        org.junit.Assert.assertNotNull(jsonFactoryBuilder0);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder2);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder3);
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 0 + "'", int4 == 0);
    }

    @Test
    public void test0443() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0443");
        tools.jackson.core.json.DupDetector dupDetector2 = null;
        tools.jackson.core.json.JsonReadContext jsonReadContext3 = tools.jackson.core.json.JsonReadContext.createRootContext(1, 0, dupDetector2);
        boolean boolean4 = jsonReadContext3.expectComma();
        tools.jackson.core.json.JsonReadContext jsonReadContext7 = jsonReadContext3.createChildObjectContext(3, (int) (short) 10);
        boolean boolean8 = jsonReadContext7.inObject();
        tools.jackson.core.json.JsonReadContext jsonReadContext11 = jsonReadContext7.createChildObjectContext(256, 10);
        java.lang.String str12 = jsonReadContext7.currentName();
        org.junit.Assert.assertNotNull(jsonReadContext3);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertNotNull(jsonReadContext7);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + true + "'", boolean8 == true);
        org.junit.Assert.assertNotNull(jsonReadContext11);
        org.junit.Assert.assertNull(str12);
    }

    @Test
    public void test0444() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0444");
        tools.jackson.core.Base64Variant base64Variant0 = tools.jackson.core.Base64Variants.MODIFIED_FOR_URL;
        byte[] byteArray8 = new byte[] { (byte) 10, (byte) -1, (byte) -1, (byte) -1, (byte) -1 };
        int int10 = base64Variant0.encodeBase64Partial(10, (int) 'a', byteArray8, (int) (byte) 1);
        tools.jackson.core.Base64Variant base64Variant13 = new tools.jackson.core.Base64Variant(base64Variant0, "hi!", 0);
        tools.jackson.core.Base64Variant base64Variant14 = base64Variant0.withPaddingForbidden();
        boolean boolean15 = base64Variant0.acceptsPaddingOnRead();
        char char17 = base64Variant0.encodeBase64BitsAsChar(1);
        tools.jackson.core.Base64Variant base64Variant20 = tools.jackson.core.Base64Variants.MODIFIED_FOR_URL;
        byte[] byteArray28 = new byte[] { (byte) 10, (byte) -1, (byte) -1, (byte) -1, (byte) -1 };
        int int30 = base64Variant20.encodeBase64Partial(10, (int) 'a', byteArray28, (int) (byte) 1);
        tools.jackson.core.Base64Variant base64Variant33 = new tools.jackson.core.Base64Variant(base64Variant20, "hi!", 0);
        tools.jackson.core.Base64Variant base64Variant34 = base64Variant20.withPaddingForbidden();
        boolean boolean35 = base64Variant20.acceptsPaddingOnRead();
        char char37 = base64Variant20.encodeBase64BitsAsChar(1);
        tools.jackson.core.io.IOContext iOContext38 = null;
        java.io.InputStream inputStream39 = null;
        byte[] byteArray43 = new byte[] { (byte) 100, (byte) 1 };
        tools.jackson.core.io.UTF32Reader uTF32Reader47 = new tools.jackson.core.io.UTF32Reader(iOContext38, inputStream39, false, byteArray43, (int) (short) 0, (-1), false);
        java.lang.String str48 = base64Variant20.encode(byteArray43);
        // The following exception was thrown during execution in test generation
        try {
            int int50 = base64Variant0.encodeBase64Partial(8, (int) (byte) 100, byteArray43, (int) ' ');
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: 32");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(base64Variant0);
        org.junit.Assert.assertNotNull(byteArray8);
        org.junit.Assert.assertArrayEquals(byteArray8, new byte[] { (byte) 10, (byte) 65, (byte) 65, (byte) -1, (byte) -1 });
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 3 + "'", int10 == 3);
        org.junit.Assert.assertNotNull(base64Variant14);
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertTrue("'" + char17 + "' != '" + 'B' + "'", char17 == 'B');
        org.junit.Assert.assertNotNull(base64Variant20);
        org.junit.Assert.assertNotNull(byteArray28);
        org.junit.Assert.assertArrayEquals(byteArray28, new byte[] { (byte) 10, (byte) 65, (byte) 65, (byte) -1, (byte) -1 });
        org.junit.Assert.assertTrue("'" + int30 + "' != '" + 3 + "'", int30 == 3);
        org.junit.Assert.assertNotNull(base64Variant34);
        org.junit.Assert.assertTrue("'" + boolean35 + "' != '" + false + "'", boolean35 == false);
        org.junit.Assert.assertTrue("'" + char37 + "' != '" + 'B' + "'", char37 == 'B');
        org.junit.Assert.assertNotNull(byteArray43);
        org.junit.Assert.assertArrayEquals(byteArray43, new byte[] { (byte) 100, (byte) 1 });
        org.junit.Assert.assertEquals("'" + str48 + "' != '" + "ZAE" + "'", str48, "ZAE");
    }

    @Test
    public void test0445() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0445");
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter0 = new tools.jackson.core.util.MinimalPrettyPrinter();
        tools.jackson.core.JsonGenerator jsonGenerator1 = null;
        minimalPrettyPrinter0.beforeObjectEntries(jsonGenerator1);
        tools.jackson.core.util.Separators separators6 = new tools.jackson.core.util.Separators('~', ' ', ' ');
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter7 = minimalPrettyPrinter0.setSeparators(separators6);
        tools.jackson.core.JsonGenerator jsonGenerator8 = null;
        tools.jackson.core.util.JsonGeneratorDelegate jsonGeneratorDelegate9 = new tools.jackson.core.util.JsonGeneratorDelegate(jsonGenerator8);
        minimalPrettyPrinter0.beforeObjectEntries((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate9);
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean11 = jsonGeneratorDelegate9.canWriteComments();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(minimalPrettyPrinter7);
    }

    @Test
    public void test0446() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0446");
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder0 = tools.jackson.core.json.JsonFactory.builderWithJackson2Defaults();
        tools.jackson.core.SerializableString serializableString1 = null;
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder2 = jsonFactoryBuilder0.rootValueSeparator(serializableString1);
        tools.jackson.core.io.OutputDecorator outputDecorator3 = jsonFactoryBuilder2.outputDecorator();
        tools.jackson.core.json.JsonReadFeature jsonReadFeature4 = null;
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder5 = jsonFactoryBuilder2.enable(jsonReadFeature4);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(jsonFactoryBuilder0);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder2);
        org.junit.Assert.assertNull(outputDecorator3);
    }

    @Test
    public void test0447() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0447");
        // The following exception was thrown during execution in test generation
        try {
            java.math.BigDecimal bigDecimal1 = tools.jackson.core.io.NumberInput.parseBigDecimal("");
            org.junit.Assert.fail("Expected exception of type java.lang.NumberFormatException; message: Value \"\" cannot be deserialized as `java.math.BigDecimal`, reason:  Not a valid number representation");
        } catch (java.lang.NumberFormatException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0448() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0448");
        tools.jackson.core.ErrorReportConfiguration errorReportConfiguration4 = null;
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.io.ContentReference contentReference5 = tools.jackson.core.io.ContentReference.construct(false, (java.lang.Object) '4', 971, 256, errorReportConfiguration4);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0449() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0449");
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter0 = new tools.jackson.core.util.MinimalPrettyPrinter();
        tools.jackson.core.JsonGenerator jsonGenerator1 = null;
        minimalPrettyPrinter0.beforeObjectEntries(jsonGenerator1);
        tools.jackson.core.util.Separators separators6 = new tools.jackson.core.util.Separators('~', ' ', ' ');
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter7 = minimalPrettyPrinter0.setSeparators(separators6);
        tools.jackson.core.JsonGenerator jsonGenerator8 = null;
        tools.jackson.core.util.JsonGeneratorDelegate jsonGeneratorDelegate9 = new tools.jackson.core.util.JsonGeneratorDelegate(jsonGenerator8);
        minimalPrettyPrinter0.beforeObjectEntries((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate9);
        tools.jackson.core.filter.TokenFilter tokenFilter11 = null;
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext12 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter11);
        tools.jackson.core.JsonToken jsonToken13 = tokenFilterContext12.nextTokenToRead();
        tools.jackson.core.filter.TokenFilter tokenFilter14 = tools.jackson.core.filter.TokenFilter.INCLUDE_ALL;
        tools.jackson.core.filter.TokenFilter tokenFilter15 = tokenFilterContext12.checkValue(tokenFilter14);
        java.math.BigDecimal bigDecimal16 = null;
        boolean boolean17 = tokenFilter14.includeNumber(bigDecimal16);
        tools.jackson.core.filter.TokenFilter tokenFilter19 = tokenFilter14.includeRootValue((int) (byte) 1);
        tools.jackson.core.io.IOContext iOContext20 = null;
        java.io.InputStream inputStream21 = null;
        byte[] byteArray25 = new byte[] { (byte) 100, (byte) 1 };
        tools.jackson.core.io.UTF32Reader uTF32Reader29 = new tools.jackson.core.io.UTF32Reader(iOContext20, inputStream21, false, byteArray25, (int) (short) 0, (-1), false);
        boolean boolean31 = tokenFilter19.includeString((java.io.Reader) uTF32Reader29, (-1074));
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion32 = tools.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_NON_NULL;
        tools.jackson.core.filter.FilteringGeneratorDelegate filteringGeneratorDelegate34 = new tools.jackson.core.filter.FilteringGeneratorDelegate((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate9, tokenFilter19, inclusion32, true);
        tools.jackson.core.io.SerializedString serializedString35 = tools.jackson.core.PrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
        byte[] byteArray36 = serializedString35.asQuotedUTF8();
        java.lang.String str37 = serializedString35.toString();
        tools.jackson.core.Base64Variant base64Variant38 = tools.jackson.core.Base64Variants.MODIFIED_FOR_URL;
        byte[] byteArray46 = new byte[] { (byte) 10, (byte) -1, (byte) -1, (byte) -1, (byte) -1 };
        int int48 = base64Variant38.encodeBase64Partial(10, (int) 'a', byteArray46, (int) (byte) 1);
        tools.jackson.core.Base64Variant base64Variant51 = new tools.jackson.core.Base64Variant(base64Variant38, "hi!", 0);
        tools.jackson.core.Base64Variant base64Variant52 = base64Variant38.withPaddingAllowed();
        tools.jackson.core.Base64Variant base64Variant53 = tools.jackson.core.Base64Variants.MODIFIED_FOR_URL;
        byte[] byteArray61 = new byte[] { (byte) 10, (byte) -1, (byte) -1, (byte) -1, (byte) -1 };
        int int63 = base64Variant53.encodeBase64Partial(10, (int) 'a', byteArray61, (int) (byte) 1);
        java.lang.String str66 = base64Variant38.encode(byteArray61, false, "//0.0.0");
        int int68 = serializedString35.appendUnquotedUTF8(byteArray61, 56320);
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.JsonGenerator jsonGenerator69 = filteringGeneratorDelegate34.writeName((tools.jackson.core.SerializableString) serializedString35);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(minimalPrettyPrinter7);
        org.junit.Assert.assertNotNull(tokenFilterContext12);
        org.junit.Assert.assertNull(jsonToken13);
        org.junit.Assert.assertNotNull(tokenFilter14);
        org.junit.Assert.assertNotNull(tokenFilter15);
        org.junit.Assert.assertTrue("'" + boolean17 + "' != '" + true + "'", boolean17 == true);
        org.junit.Assert.assertNotNull(tokenFilter19);
        org.junit.Assert.assertNotNull(byteArray25);
        org.junit.Assert.assertArrayEquals(byteArray25, new byte[] { (byte) 100, (byte) 1 });
        org.junit.Assert.assertTrue("'" + boolean31 + "' != '" + true + "'", boolean31 == true);
        org.junit.Assert.assertTrue("'" + inclusion32 + "' != '" + tools.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_NON_NULL + "'", inclusion32.equals(tools.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_NON_NULL));
        org.junit.Assert.assertNotNull(serializedString35);
        org.junit.Assert.assertNotNull(byteArray36);
        org.junit.Assert.assertArrayEquals(byteArray36, new byte[] { (byte) 32 });
        org.junit.Assert.assertEquals("'" + str37 + "' != '" + " " + "'", str37, " ");
        org.junit.Assert.assertNotNull(base64Variant38);
        org.junit.Assert.assertNotNull(byteArray46);
        org.junit.Assert.assertArrayEquals(byteArray46, new byte[] { (byte) 10, (byte) 65, (byte) 65, (byte) -1, (byte) -1 });
        org.junit.Assert.assertTrue("'" + int48 + "' != '" + 3 + "'", int48 == 3);
        org.junit.Assert.assertNotNull(base64Variant52);
        org.junit.Assert.assertNotNull(base64Variant53);
        org.junit.Assert.assertNotNull(byteArray61);
        org.junit.Assert.assertArrayEquals(byteArray61, new byte[] { (byte) 10, (byte) 65, (byte) 65, (byte) -1, (byte) -1 });
        org.junit.Assert.assertTrue("'" + int63 + "' != '" + 3 + "'", int63 == 3);
        org.junit.Assert.assertEquals("'" + str66 + "' != '" + "CkFB__8" + "'", str66, "CkFB__8");
        org.junit.Assert.assertTrue("'" + int68 + "' != '" + (-1) + "'", int68 == (-1));
    }

    @Test
    public void test0450() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0450");
        tools.jackson.core.JsonParser jsonParser1 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter2 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion3 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate6 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser1, tokenFilter2, inclusion3, true, true);
        boolean boolean7 = filteringParserDelegate6.isExpectedStartObjectToken();
        int int8 = filteringParserDelegate6.currentTokenId();
        tools.jackson.core.JsonToken jsonToken9 = null;
        boolean boolean10 = filteringParserDelegate6.hasToken(jsonToken9);
        int int11 = filteringParserDelegate6.currentTokenId();
        boolean boolean12 = filteringParserDelegate6.isExpectedStartObjectToken();
        tools.jackson.core.JsonParser jsonParser13 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter14 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion15 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate18 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser13, tokenFilter14, inclusion15, true, true);
        tools.jackson.core.filter.TokenFilter tokenFilter19 = filteringParserDelegate18.getFilter();
        tools.jackson.core.util.JsonParserSequence jsonParserSequence20 = tools.jackson.core.util.JsonParserSequence.createFlattened(true, (tools.jackson.core.JsonParser) filteringParserDelegate6, (tools.jackson.core.JsonParser) filteringParserDelegate18);
        // The following exception was thrown during execution in test generation
        try {
            short short21 = filteringParserDelegate18.getShortValue();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 0 + "'", int8 == 0);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 0 + "'", int11 == 0);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
        org.junit.Assert.assertNull(tokenFilter19);
        org.junit.Assert.assertNotNull(jsonParserSequence20);
    }

    @Test
    public void test0451() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0451");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        boolean boolean6 = filteringParserDelegate5.isExpectedStartObjectToken();
        int int7 = filteringParserDelegate5.currentTokenId();
        tools.jackson.core.JsonToken jsonToken8 = null;
        boolean boolean9 = filteringParserDelegate5.hasToken(jsonToken8);
        // The following exception was thrown during execution in test generation
        try {
            int int10 = filteringParserDelegate5.getValueAsInt();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 0 + "'", int7 == 0);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + true + "'", boolean9 == true);
    }

    @Test
    public void test0452() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0452");
        tools.jackson.core.util.DefaultPrettyPrinter.NopIndenter nopIndenter0 = new tools.jackson.core.util.DefaultPrettyPrinter.NopIndenter();
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter1 = new tools.jackson.core.util.MinimalPrettyPrinter();
        tools.jackson.core.JsonGenerator jsonGenerator2 = null;
        minimalPrettyPrinter1.beforeObjectEntries(jsonGenerator2);
        tools.jackson.core.util.Separators separators7 = new tools.jackson.core.util.Separators('~', ' ', ' ');
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter8 = minimalPrettyPrinter1.setSeparators(separators7);
        tools.jackson.core.JsonGenerator jsonGenerator9 = null;
        tools.jackson.core.util.JsonGeneratorDelegate jsonGeneratorDelegate10 = new tools.jackson.core.util.JsonGeneratorDelegate(jsonGenerator9);
        minimalPrettyPrinter1.beforeObjectEntries((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate10);
        tools.jackson.core.filter.TokenFilter tokenFilter12 = null;
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext13 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter12);
        tools.jackson.core.JsonToken jsonToken14 = tokenFilterContext13.nextTokenToRead();
        tools.jackson.core.filter.TokenFilter tokenFilter15 = tools.jackson.core.filter.TokenFilter.INCLUDE_ALL;
        tools.jackson.core.filter.TokenFilter tokenFilter16 = tokenFilterContext13.checkValue(tokenFilter15);
        java.math.BigDecimal bigDecimal17 = null;
        boolean boolean18 = tokenFilter15.includeNumber(bigDecimal17);
        tools.jackson.core.filter.TokenFilter tokenFilter20 = tokenFilter15.includeRootValue((int) (byte) 1);
        tools.jackson.core.io.IOContext iOContext21 = null;
        java.io.InputStream inputStream22 = null;
        byte[] byteArray26 = new byte[] { (byte) 100, (byte) 1 };
        tools.jackson.core.io.UTF32Reader uTF32Reader30 = new tools.jackson.core.io.UTF32Reader(iOContext21, inputStream22, false, byteArray26, (int) (short) 0, (-1), false);
        boolean boolean32 = tokenFilter20.includeString((java.io.Reader) uTF32Reader30, (-1074));
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion33 = tools.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_NON_NULL;
        tools.jackson.core.filter.FilteringGeneratorDelegate filteringGeneratorDelegate35 = new tools.jackson.core.filter.FilteringGeneratorDelegate((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate10, tokenFilter20, inclusion33, true);
        nopIndenter0.writeIndentation((tools.jackson.core.JsonGenerator) filteringGeneratorDelegate35, (int) '#');
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.JsonGenerator jsonGenerator39 = filteringGeneratorDelegate35.writeStartArray((java.lang.Object) "[No location information]");
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(minimalPrettyPrinter8);
        org.junit.Assert.assertNotNull(tokenFilterContext13);
        org.junit.Assert.assertNull(jsonToken14);
        org.junit.Assert.assertNotNull(tokenFilter15);
        org.junit.Assert.assertNotNull(tokenFilter16);
        org.junit.Assert.assertTrue("'" + boolean18 + "' != '" + true + "'", boolean18 == true);
        org.junit.Assert.assertNotNull(tokenFilter20);
        org.junit.Assert.assertNotNull(byteArray26);
        org.junit.Assert.assertArrayEquals(byteArray26, new byte[] { (byte) 100, (byte) 1 });
        org.junit.Assert.assertTrue("'" + boolean32 + "' != '" + true + "'", boolean32 == true);
        org.junit.Assert.assertTrue("'" + inclusion33 + "' != '" + tools.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_NON_NULL + "'", inclusion33.equals(tools.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_NON_NULL));
    }

    @Test
    public void test0453() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0453");
        tools.jackson.core.util.DefaultPrettyPrinter.NopIndenter nopIndenter0 = new tools.jackson.core.util.DefaultPrettyPrinter.NopIndenter();
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter1 = new tools.jackson.core.util.MinimalPrettyPrinter();
        tools.jackson.core.JsonGenerator jsonGenerator2 = null;
        minimalPrettyPrinter1.beforeObjectEntries(jsonGenerator2);
        tools.jackson.core.util.Separators separators7 = new tools.jackson.core.util.Separators('~', ' ', ' ');
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter8 = minimalPrettyPrinter1.setSeparators(separators7);
        tools.jackson.core.JsonGenerator jsonGenerator9 = null;
        tools.jackson.core.util.JsonGeneratorDelegate jsonGeneratorDelegate10 = new tools.jackson.core.util.JsonGeneratorDelegate(jsonGenerator9);
        minimalPrettyPrinter1.beforeObjectEntries((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate10);
        tools.jackson.core.filter.TokenFilter tokenFilter12 = null;
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext13 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter12);
        tools.jackson.core.JsonToken jsonToken14 = tokenFilterContext13.nextTokenToRead();
        tools.jackson.core.filter.TokenFilter tokenFilter15 = tools.jackson.core.filter.TokenFilter.INCLUDE_ALL;
        tools.jackson.core.filter.TokenFilter tokenFilter16 = tokenFilterContext13.checkValue(tokenFilter15);
        java.math.BigDecimal bigDecimal17 = null;
        boolean boolean18 = tokenFilter15.includeNumber(bigDecimal17);
        tools.jackson.core.filter.TokenFilter tokenFilter20 = tokenFilter15.includeRootValue((int) (byte) 1);
        tools.jackson.core.io.IOContext iOContext21 = null;
        java.io.InputStream inputStream22 = null;
        byte[] byteArray26 = new byte[] { (byte) 100, (byte) 1 };
        tools.jackson.core.io.UTF32Reader uTF32Reader30 = new tools.jackson.core.io.UTF32Reader(iOContext21, inputStream22, false, byteArray26, (int) (short) 0, (-1), false);
        boolean boolean32 = tokenFilter20.includeString((java.io.Reader) uTF32Reader30, (-1074));
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion33 = tools.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_NON_NULL;
        tools.jackson.core.filter.FilteringGeneratorDelegate filteringGeneratorDelegate35 = new tools.jackson.core.filter.FilteringGeneratorDelegate((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate10, tokenFilter20, inclusion33, true);
        nopIndenter0.writeIndentation((tools.jackson.core.JsonGenerator) filteringGeneratorDelegate35, (int) '#');
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.JsonGenerator jsonGenerator39 = filteringGeneratorDelegate35.writeRawValue("root");
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(minimalPrettyPrinter8);
        org.junit.Assert.assertNotNull(tokenFilterContext13);
        org.junit.Assert.assertNull(jsonToken14);
        org.junit.Assert.assertNotNull(tokenFilter15);
        org.junit.Assert.assertNotNull(tokenFilter16);
        org.junit.Assert.assertTrue("'" + boolean18 + "' != '" + true + "'", boolean18 == true);
        org.junit.Assert.assertNotNull(tokenFilter20);
        org.junit.Assert.assertNotNull(byteArray26);
        org.junit.Assert.assertArrayEquals(byteArray26, new byte[] { (byte) 100, (byte) 1 });
        org.junit.Assert.assertTrue("'" + boolean32 + "' != '" + true + "'", boolean32 == true);
        org.junit.Assert.assertTrue("'" + inclusion33 + "' != '" + tools.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_NON_NULL + "'", inclusion33.equals(tools.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_NON_NULL));
    }

    @Test
    public void test0454() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0454");
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder0 = tools.jackson.core.json.JsonFactory.builderWithJackson2Defaults();
        tools.jackson.core.SerializableString serializableString1 = null;
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder2 = jsonFactoryBuilder0.rootValueSeparator(serializableString1);
        int int3 = jsonFactoryBuilder2.streamWriteFeaturesMask();
        tools.jackson.core.StreamWriteFeature streamWriteFeature4 = null;
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder5 = jsonFactoryBuilder2.disable(streamWriteFeature4);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(jsonFactoryBuilder0);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder2);
        org.junit.Assert.assertTrue("'" + int3 + "' != '" + 7 + "'", int3 == 7);
    }

    @Test
    public void test0455() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0455");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        tools.jackson.core.filter.TokenFilter tokenFilter6 = filteringParserDelegate5.getFilter();
        boolean boolean7 = filteringParserDelegate5.isExpectedStartArrayToken();
        boolean boolean8 = filteringParserDelegate5.hasCurrentToken();
        tools.jackson.core.json.DupDetector dupDetector9 = tools.jackson.core.json.DupDetector.rootDetector((tools.jackson.core.JsonParser) filteringParserDelegate5);
        tools.jackson.core.JsonParser jsonParser10 = filteringParserDelegate5.delegate();
        boolean boolean12 = filteringParserDelegate5.hasTokenId((-44));
        // The following exception was thrown during execution in test generation
        try {
            double double14 = filteringParserDelegate5.getValueAsDouble(1.0d);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(tokenFilter6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertNotNull(dupDetector9);
        org.junit.Assert.assertNull(jsonParser10);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
    }

    @Test
    public void test0456() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0456");
        tools.jackson.core.JsonGenerator jsonGenerator0 = null;
        tools.jackson.core.util.JsonGeneratorDelegate jsonGeneratorDelegate1 = new tools.jackson.core.util.JsonGeneratorDelegate(jsonGenerator0);
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.JsonGenerator jsonGenerator3 = jsonGeneratorDelegate1.writeNumber((long) (-2));
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0457() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0457");
        tools.jackson.core.util.InternCache internCache3 = new tools.jackson.core.util.InternCache(100000000, (float) ' ', (int) (short) 100);
    }

    @Test
    public void test0458() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0458");
        tools.jackson.core.util.BufferRecycler bufferRecycler0 = null;
        tools.jackson.core.util.TextBuffer textBuffer1 = new tools.jackson.core.util.TextBuffer(bufferRecycler0);
        textBuffer1.resetWithString("");
        int int4 = textBuffer1.getCurrentSegmentSize();
        char[] charArray5 = textBuffer1.emptyAndGetCurrentSegment();
        char[] charArray7 = textBuffer1.expandCurrentSegment(0);
        char[] charArray8 = textBuffer1.expandCurrentSegment();
        boolean boolean9 = textBuffer1.hasTextAsCharacters();
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 0 + "'", int4 == 0);
        org.junit.Assert.assertNotNull(charArray5);
        org.junit.Assert.assertNotNull(charArray7);
        org.junit.Assert.assertNotNull(charArray8);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + true + "'", boolean9 == true);
    }

    @Test
    public void test0459() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0459");
        tools.jackson.core.io.SerializedString serializedString1 = tools.jackson.core.PrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
        byte[] byteArray2 = serializedString1.asQuotedUTF8();
        tools.jackson.core.util.ByteArrayBuilder byteArrayBuilder4 = tools.jackson.core.util.ByteArrayBuilder.fromInitial(byteArray2, 53);
        // The following exception was thrown during execution in test generation
        try {
            int int6 = tools.jackson.core.io.NumberOutput.outputInt(12000, byteArray2, 56);
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: 56");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(serializedString1);
        org.junit.Assert.assertNotNull(byteArray2);
        org.junit.Assert.assertArrayEquals(byteArray2, new byte[] { (byte) 32 });
        org.junit.Assert.assertNotNull(byteArrayBuilder4);
    }

    @Test
    public void test0460() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0460");
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion0 = tools.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_ALL_AND_PATH;
        org.junit.Assert.assertTrue("'" + inclusion0 + "' != '" + tools.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_ALL_AND_PATH + "'", inclusion0.equals(tools.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_ALL_AND_PATH));
    }

    @Test
    public void test0461() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0461");
        int int0 = tools.jackson.core.io.UTF8Writer.SURR2_FIRST;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 56320 + "'", int0 == 56320);
    }

    @Test
    public void test0462() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0462");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        tools.jackson.core.filter.TokenFilter tokenFilter6 = filteringParserDelegate5.getFilter();
        boolean boolean7 = filteringParserDelegate5.isExpectedStartArrayToken();
        boolean boolean8 = filteringParserDelegate5.hasCurrentToken();
        // The following exception was thrown during execution in test generation
        try {
            filteringParserDelegate5.close();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(tokenFilter6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
    }

    @Test
    public void test0463() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0463");
        tools.jackson.core.JsonPointer jsonPointer0 = tools.jackson.core.JsonPointer.empty();
        boolean boolean1 = jsonPointer0.mayMatchElement();
        tools.jackson.core.JsonPointer jsonPointer3 = jsonPointer0.appendProperty("tools.jackson.core.exc.StreamWriteException: hi!\n at [No location information]");
        boolean boolean4 = jsonPointer0.matches();
        boolean boolean6 = jsonPointer0.matchesElement((-57));
        boolean boolean7 = jsonPointer0.mayMatchElement();
        java.lang.String str8 = jsonPointer0.getMatchingProperty();
        org.junit.Assert.assertNotNull(jsonPointer0);
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
        org.junit.Assert.assertNotNull(jsonPointer3);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + true + "'", boolean4 == true);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertNull(str8);
    }

    @Test
    public void test0464() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0464");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        tools.jackson.core.JsonParser jsonParser6 = filteringParserDelegate5.skipChildren();
        tools.jackson.core.json.DupDetector dupDetector8 = null;
        tools.jackson.core.util.SimpleStreamWriteContext simpleStreamWriteContext9 = tools.jackson.core.util.SimpleStreamWriteContext.createRootContext(dupDetector8);
        tools.jackson.core.io.ContentReference contentReference10 = null;
        tools.jackson.core.TokenStreamLocation tokenStreamLocation11 = simpleStreamWriteContext9.startLocation(contentReference10);
        java.lang.String str12 = tokenStreamLocation11.sourceDescription();
        tools.jackson.core.JsonGenerator jsonGenerator13 = null;
        java.lang.Throwable throwable15 = null;
        tools.jackson.core.exc.StreamWriteException streamWriteException16 = new tools.jackson.core.exc.StreamWriteException(jsonGenerator13, "hi!", throwable15);
        tools.jackson.core.JsonGenerator jsonGenerator17 = streamWriteException16.processor();
        tools.jackson.core.exc.StreamReadException streamReadException18 = new tools.jackson.core.exc.StreamReadException((tools.jackson.core.JsonParser) filteringParserDelegate5, "root", tokenStreamLocation11, (java.lang.Throwable) streamWriteException16);
        tools.jackson.core.io.SerializedString serializedString19 = tools.jackson.core.PrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
        byte[] byteArray20 = serializedString19.asQuotedUTF8();
        java.lang.String str21 = serializedString19.toString();
        tools.jackson.core.Base64Variant base64Variant22 = tools.jackson.core.Base64Variants.MODIFIED_FOR_URL;
        byte[] byteArray30 = new byte[] { (byte) 10, (byte) -1, (byte) -1, (byte) -1, (byte) -1 };
        int int32 = base64Variant22.encodeBase64Partial(10, (int) 'a', byteArray30, (int) (byte) 1);
        tools.jackson.core.Base64Variant base64Variant35 = new tools.jackson.core.Base64Variant(base64Variant22, "hi!", 0);
        tools.jackson.core.Base64Variant base64Variant36 = base64Variant22.withPaddingAllowed();
        tools.jackson.core.Base64Variant base64Variant37 = tools.jackson.core.Base64Variants.MODIFIED_FOR_URL;
        byte[] byteArray45 = new byte[] { (byte) 10, (byte) -1, (byte) -1, (byte) -1, (byte) -1 };
        int int47 = base64Variant37.encodeBase64Partial(10, (int) 'a', byteArray45, (int) (byte) 1);
        java.lang.String str50 = base64Variant22.encode(byteArray45, false, "//0.0.0");
        int int52 = serializedString19.appendUnquotedUTF8(byteArray45, 56320);
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean53 = filteringParserDelegate5.nextName((tools.jackson.core.SerializableString) serializedString19);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(jsonParser6);
        org.junit.Assert.assertNotNull(simpleStreamWriteContext9);
        org.junit.Assert.assertNotNull(tokenStreamLocation11);
        org.junit.Assert.assertEquals("'" + str12 + "' != '" + "UNKNOWN" + "'", str12, "UNKNOWN");
        org.junit.Assert.assertNull(jsonGenerator17);
        org.junit.Assert.assertNotNull(serializedString19);
        org.junit.Assert.assertNotNull(byteArray20);
        org.junit.Assert.assertArrayEquals(byteArray20, new byte[] { (byte) 32 });
        org.junit.Assert.assertEquals("'" + str21 + "' != '" + " " + "'", str21, " ");
        org.junit.Assert.assertNotNull(base64Variant22);
        org.junit.Assert.assertNotNull(byteArray30);
        org.junit.Assert.assertArrayEquals(byteArray30, new byte[] { (byte) 10, (byte) 65, (byte) 65, (byte) -1, (byte) -1 });
        org.junit.Assert.assertTrue("'" + int32 + "' != '" + 3 + "'", int32 == 3);
        org.junit.Assert.assertNotNull(base64Variant36);
        org.junit.Assert.assertNotNull(base64Variant37);
        org.junit.Assert.assertNotNull(byteArray45);
        org.junit.Assert.assertArrayEquals(byteArray45, new byte[] { (byte) 10, (byte) 65, (byte) 65, (byte) -1, (byte) -1 });
        org.junit.Assert.assertTrue("'" + int47 + "' != '" + 3 + "'", int47 == 3);
        org.junit.Assert.assertEquals("'" + str50 + "' != '" + "CkFB__8" + "'", str50, "CkFB__8");
        org.junit.Assert.assertTrue("'" + int52 + "' != '" + (-1) + "'", int52 == (-1));
    }

    @Test
    public void test0465() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0465");
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter0 = new tools.jackson.core.util.MinimalPrettyPrinter();
        tools.jackson.core.JsonGenerator jsonGenerator1 = null;
        minimalPrettyPrinter0.beforeObjectEntries(jsonGenerator1);
        tools.jackson.core.util.Separators separators6 = new tools.jackson.core.util.Separators('~', ' ', ' ');
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter7 = minimalPrettyPrinter0.setSeparators(separators6);
        tools.jackson.core.JsonGenerator jsonGenerator8 = null;
        tools.jackson.core.util.JsonGeneratorDelegate jsonGeneratorDelegate9 = new tools.jackson.core.util.JsonGeneratorDelegate(jsonGenerator8);
        minimalPrettyPrinter0.beforeObjectEntries((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate9);
        tools.jackson.core.filter.TokenFilter tokenFilter11 = null;
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext12 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter11);
        tools.jackson.core.JsonToken jsonToken13 = tokenFilterContext12.nextTokenToRead();
        tools.jackson.core.filter.TokenFilter tokenFilter14 = tools.jackson.core.filter.TokenFilter.INCLUDE_ALL;
        tools.jackson.core.filter.TokenFilter tokenFilter15 = tokenFilterContext12.checkValue(tokenFilter14);
        java.math.BigDecimal bigDecimal16 = null;
        boolean boolean17 = tokenFilter14.includeNumber(bigDecimal16);
        tools.jackson.core.filter.TokenFilter tokenFilter19 = tokenFilter14.includeRootValue((int) (byte) 1);
        tools.jackson.core.io.IOContext iOContext20 = null;
        java.io.InputStream inputStream21 = null;
        byte[] byteArray25 = new byte[] { (byte) 100, (byte) 1 };
        tools.jackson.core.io.UTF32Reader uTF32Reader29 = new tools.jackson.core.io.UTF32Reader(iOContext20, inputStream21, false, byteArray25, (int) (short) 0, (-1), false);
        boolean boolean31 = tokenFilter19.includeString((java.io.Reader) uTF32Reader29, (-1074));
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion32 = tools.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_NON_NULL;
        tools.jackson.core.filter.FilteringGeneratorDelegate filteringGeneratorDelegate34 = new tools.jackson.core.filter.FilteringGeneratorDelegate((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate9, tokenFilter19, inclusion32, true);
        tools.jackson.core.util.BufferRecycler bufferRecycler35 = null;
        tools.jackson.core.util.ByteArrayBuilder byteArrayBuilder36 = new tools.jackson.core.util.ByteArrayBuilder(bufferRecycler35);
        tools.jackson.core.util.BufferRecycler bufferRecycler37 = byteArrayBuilder36.bufferRecycler();
        byte[] byteArray38 = byteArrayBuilder36.getClearAndRelease();
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.JsonGenerator jsonGenerator39 = filteringGeneratorDelegate34.writeBinary(byteArray38);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(minimalPrettyPrinter7);
        org.junit.Assert.assertNotNull(tokenFilterContext12);
        org.junit.Assert.assertNull(jsonToken13);
        org.junit.Assert.assertNotNull(tokenFilter14);
        org.junit.Assert.assertNotNull(tokenFilter15);
        org.junit.Assert.assertTrue("'" + boolean17 + "' != '" + true + "'", boolean17 == true);
        org.junit.Assert.assertNotNull(tokenFilter19);
        org.junit.Assert.assertNotNull(byteArray25);
        org.junit.Assert.assertArrayEquals(byteArray25, new byte[] { (byte) 100, (byte) 1 });
        org.junit.Assert.assertTrue("'" + boolean31 + "' != '" + true + "'", boolean31 == true);
        org.junit.Assert.assertTrue("'" + inclusion32 + "' != '" + tools.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_NON_NULL + "'", inclusion32.equals(tools.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_NON_NULL));
        org.junit.Assert.assertNull(bufferRecycler37);
        org.junit.Assert.assertNotNull(byteArray38);
        org.junit.Assert.assertArrayEquals(byteArray38, new byte[] {});
    }

    @Test
    public void test0466() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0466");
        tools.jackson.core.JsonGenerator jsonGenerator0 = null;
        tools.jackson.core.util.JsonGeneratorDelegate jsonGeneratorDelegate1 = new tools.jackson.core.util.JsonGeneratorDelegate(jsonGenerator0);
        char[] charArray5 = new char[] { ' ', ' ', '4' };
        boolean boolean9 = tools.jackson.core.io.NumberInput.inLongRange(charArray5, (-324), (int) '4', false);
        boolean boolean13 = tools.jackson.core.io.NumberInput.inLongRange(charArray5, 971, (int) (byte) 100, false);
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.JsonGenerator jsonGenerator16 = jsonGeneratorDelegate1.writeRaw(charArray5, 53, (-57));
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(charArray5);
        org.junit.Assert.assertArrayEquals(charArray5, new char[] { ' ', ' ', '4' });
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
    }

    @Test
    public void test0467() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0467");
        tools.jackson.core.Base64Variant base64Variant0 = tools.jackson.core.Base64Variants.MODIFIED_FOR_URL;
        byte[] byteArray8 = new byte[] { (byte) 10, (byte) -1, (byte) -1, (byte) -1, (byte) -1 };
        int int10 = base64Variant0.encodeBase64Partial(10, (int) 'a', byteArray8, (int) (byte) 1);
        tools.jackson.core.Base64Variant base64Variant13 = new tools.jackson.core.Base64Variant(base64Variant0, "hi!", 0);
        tools.jackson.core.Base64Variant base64Variant14 = base64Variant0.withPaddingAllowed();
        tools.jackson.core.Base64Variant base64Variant15 = tools.jackson.core.Base64Variants.MODIFIED_FOR_URL;
        byte[] byteArray23 = new byte[] { (byte) 10, (byte) -1, (byte) -1, (byte) -1, (byte) -1 };
        int int25 = base64Variant15.encodeBase64Partial(10, (int) 'a', byteArray23, (int) (byte) 1);
        java.lang.String str28 = base64Variant0.encode(byteArray23, false, "//0.0.0");
        boolean boolean30 = base64Variant0.usesPaddingChar('#');
        org.junit.Assert.assertNotNull(base64Variant0);
        org.junit.Assert.assertNotNull(byteArray8);
        org.junit.Assert.assertArrayEquals(byteArray8, new byte[] { (byte) 10, (byte) 65, (byte) 65, (byte) -1, (byte) -1 });
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 3 + "'", int10 == 3);
        org.junit.Assert.assertNotNull(base64Variant14);
        org.junit.Assert.assertNotNull(base64Variant15);
        org.junit.Assert.assertNotNull(byteArray23);
        org.junit.Assert.assertArrayEquals(byteArray23, new byte[] { (byte) 10, (byte) 65, (byte) 65, (byte) -1, (byte) -1 });
        org.junit.Assert.assertTrue("'" + int25 + "' != '" + 3 + "'", int25 == 3);
        org.junit.Assert.assertEquals("'" + str28 + "' != '" + "CkFB__8" + "'", str28, "CkFB__8");
        org.junit.Assert.assertTrue("'" + boolean30 + "' != '" + false + "'", boolean30 == false);
    }

    @Test
    public void test0468() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0468");
        tools.jackson.core.filter.TokenFilter tokenFilter0 = null;
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext1 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter0);
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext2 = tokenFilterContext1.getParent();
        tools.jackson.core.JsonToken jsonToken3 = tokenFilterContext1.nextTokenToRead();
        tools.jackson.core.filter.TokenFilter tokenFilter4 = tools.jackson.core.filter.TokenFilter.INCLUDE_ALL;
        tools.jackson.core.filter.TokenFilter tokenFilter5 = tokenFilter4.filterStartArray();
        tools.jackson.core.util.DefaultIndenter defaultIndenter6 = tools.jackson.core.util.DefaultIndenter.SYSTEM_LINEFEED_INSTANCE;
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext8 = tokenFilterContext1.createChildObjectContext(tokenFilter5, (java.lang.Object) defaultIndenter6, false);
        java.lang.String str9 = defaultIndenter6.getIndent();
        org.junit.Assert.assertNotNull(tokenFilterContext1);
        org.junit.Assert.assertNull(tokenFilterContext2);
        org.junit.Assert.assertNull(jsonToken3);
        org.junit.Assert.assertNotNull(tokenFilter4);
        org.junit.Assert.assertNotNull(tokenFilter5);
        org.junit.Assert.assertNotNull(defaultIndenter6);
        org.junit.Assert.assertNotNull(tokenFilterContext8);
        org.junit.Assert.assertEquals("'" + str9 + "' != '" + "  " + "'", str9, "  ");
    }

    @Test
    public void test0469() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0469");
        char[] charArray3 = new char[] { ' ', ' ', '4' };
        boolean boolean7 = tools.jackson.core.io.NumberInput.inLongRange(charArray3, (-324), (int) '4', false);
        boolean boolean11 = tools.jackson.core.io.NumberInput.inLongRange(charArray3, 971, (int) (byte) 100, false);
        // The following exception was thrown during execution in test generation
        try {
            java.math.BigDecimal bigDecimal15 = tools.jackson.core.io.NumberInput.parseBigDecimal(charArray3, 3, (int) (byte) 0, false);
            org.junit.Assert.fail("Expected exception of type java.lang.NumberFormatException; message: Value \"\" cannot be deserialized as `java.math.BigDecimal`, reason:  Not a valid number representation");
        } catch (java.lang.NumberFormatException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(charArray3);
        org.junit.Assert.assertArrayEquals(charArray3, new char[] { ' ', ' ', '4' });
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
    }

    @Test
    public void test0470() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0470");
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.util.InternCache internCache3 = new tools.jackson.core.util.InternCache(8, 0.0f, 50000);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: null");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0471() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0471");
        int int0 = tools.jackson.core.util.RecyclerPool.StatefulImplBase.SERIALIZATION_NON_SHARED;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 1 + "'", int0 == 1);
    }

    @Test
    public void test0472() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0472");
        tools.jackson.core.json.DupDetector dupDetector0 = null;
        tools.jackson.core.util.SimpleStreamWriteContext simpleStreamWriteContext1 = tools.jackson.core.util.SimpleStreamWriteContext.createRootContext(dupDetector0);
        tools.jackson.core.json.DupDetector dupDetector2 = simpleStreamWriteContext1.getDupDetector();
        java.lang.Object obj3 = simpleStreamWriteContext1.currentValue();
        org.junit.Assert.assertNotNull(simpleStreamWriteContext1);
        org.junit.Assert.assertNull(dupDetector2);
        org.junit.Assert.assertNull(obj3);
    }

    @Test
    public void test0473() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0473");
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder0 = tools.jackson.core.json.JsonFactory.builderWithJackson2Defaults();
        tools.jackson.core.SerializableString serializableString1 = null;
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder2 = jsonFactoryBuilder0.rootValueSeparator(serializableString1);
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder4 = jsonFactoryBuilder0.quoteChar(' ');
        int int5 = jsonFactoryBuilder4.factoryFeaturesMask();
        org.junit.Assert.assertNotNull(jsonFactoryBuilder0);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder2);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder4);
        org.junit.Assert.assertTrue("'" + int5 + "' != '" + 15 + "'", int5 == 15);
    }

    @Test
    public void test0474() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0474");
        tools.jackson.core.util.DefaultPrettyPrinter.NopIndenter nopIndenter0 = new tools.jackson.core.util.DefaultPrettyPrinter.NopIndenter();
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter1 = new tools.jackson.core.util.MinimalPrettyPrinter();
        tools.jackson.core.JsonGenerator jsonGenerator2 = null;
        minimalPrettyPrinter1.beforeObjectEntries(jsonGenerator2);
        tools.jackson.core.util.Separators separators7 = new tools.jackson.core.util.Separators('~', ' ', ' ');
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter8 = minimalPrettyPrinter1.setSeparators(separators7);
        tools.jackson.core.JsonGenerator jsonGenerator9 = null;
        tools.jackson.core.util.JsonGeneratorDelegate jsonGeneratorDelegate10 = new tools.jackson.core.util.JsonGeneratorDelegate(jsonGenerator9);
        minimalPrettyPrinter1.beforeObjectEntries((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate10);
        tools.jackson.core.filter.TokenFilter tokenFilter12 = null;
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext13 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter12);
        tools.jackson.core.JsonToken jsonToken14 = tokenFilterContext13.nextTokenToRead();
        tools.jackson.core.filter.TokenFilter tokenFilter15 = tools.jackson.core.filter.TokenFilter.INCLUDE_ALL;
        tools.jackson.core.filter.TokenFilter tokenFilter16 = tokenFilterContext13.checkValue(tokenFilter15);
        java.math.BigDecimal bigDecimal17 = null;
        boolean boolean18 = tokenFilter15.includeNumber(bigDecimal17);
        tools.jackson.core.filter.TokenFilter tokenFilter20 = tokenFilter15.includeRootValue((int) (byte) 1);
        tools.jackson.core.io.IOContext iOContext21 = null;
        java.io.InputStream inputStream22 = null;
        byte[] byteArray26 = new byte[] { (byte) 100, (byte) 1 };
        tools.jackson.core.io.UTF32Reader uTF32Reader30 = new tools.jackson.core.io.UTF32Reader(iOContext21, inputStream22, false, byteArray26, (int) (short) 0, (-1), false);
        boolean boolean32 = tokenFilter20.includeString((java.io.Reader) uTF32Reader30, (-1074));
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion33 = tools.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_NON_NULL;
        tools.jackson.core.filter.FilteringGeneratorDelegate filteringGeneratorDelegate35 = new tools.jackson.core.filter.FilteringGeneratorDelegate((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate10, tokenFilter20, inclusion33, true);
        nopIndenter0.writeIndentation((tools.jackson.core.JsonGenerator) filteringGeneratorDelegate35, (int) '#');
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.JsonGenerator jsonGenerator38 = filteringGeneratorDelegate35.writeStartObject();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(minimalPrettyPrinter8);
        org.junit.Assert.assertNotNull(tokenFilterContext13);
        org.junit.Assert.assertNull(jsonToken14);
        org.junit.Assert.assertNotNull(tokenFilter15);
        org.junit.Assert.assertNotNull(tokenFilter16);
        org.junit.Assert.assertTrue("'" + boolean18 + "' != '" + true + "'", boolean18 == true);
        org.junit.Assert.assertNotNull(tokenFilter20);
        org.junit.Assert.assertNotNull(byteArray26);
        org.junit.Assert.assertArrayEquals(byteArray26, new byte[] { (byte) 100, (byte) 1 });
        org.junit.Assert.assertTrue("'" + boolean32 + "' != '" + true + "'", boolean32 == true);
        org.junit.Assert.assertTrue("'" + inclusion33 + "' != '" + tools.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_NON_NULL + "'", inclusion33.equals(tools.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_NON_NULL));
    }

    @Test
    public void test0475() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0475");
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder0 = tools.jackson.core.json.JsonFactory.builderWithJackson2Defaults();
        tools.jackson.core.SerializableString serializableString1 = null;
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder2 = jsonFactoryBuilder0.rootValueSeparator(serializableString1);
        tools.jackson.core.io.OutputDecorator outputDecorator3 = jsonFactoryBuilder2.outputDecorator();
        tools.jackson.core.io.InputDecorator inputDecorator4 = null;
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder5 = jsonFactoryBuilder2.inputDecorator(inputDecorator4);
        tools.jackson.core.StreamWriteFeature streamWriteFeature6 = null;
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder7 = jsonFactoryBuilder5.enable(streamWriteFeature6);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(jsonFactoryBuilder0);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder2);
        org.junit.Assert.assertNull(outputDecorator3);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder5);
    }

    @Test
    public void test0476() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0476");
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter0 = new tools.jackson.core.util.MinimalPrettyPrinter();
        tools.jackson.core.JsonGenerator jsonGenerator1 = null;
        minimalPrettyPrinter0.beforeObjectEntries(jsonGenerator1);
        tools.jackson.core.util.Separators separators6 = new tools.jackson.core.util.Separators('~', ' ', ' ');
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter7 = minimalPrettyPrinter0.setSeparators(separators6);
        tools.jackson.core.JsonGenerator jsonGenerator8 = null;
        tools.jackson.core.util.JsonGeneratorDelegate jsonGeneratorDelegate9 = new tools.jackson.core.util.JsonGeneratorDelegate(jsonGenerator8);
        minimalPrettyPrinter0.beforeObjectEntries((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate9);
        tools.jackson.core.filter.TokenFilter tokenFilter11 = null;
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext12 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter11);
        tools.jackson.core.JsonToken jsonToken13 = tokenFilterContext12.nextTokenToRead();
        tools.jackson.core.filter.TokenFilter tokenFilter14 = tools.jackson.core.filter.TokenFilter.INCLUDE_ALL;
        tools.jackson.core.filter.TokenFilter tokenFilter15 = tokenFilterContext12.checkValue(tokenFilter14);
        java.math.BigDecimal bigDecimal16 = null;
        boolean boolean17 = tokenFilter14.includeNumber(bigDecimal16);
        tools.jackson.core.filter.TokenFilter tokenFilter19 = tokenFilter14.includeRootValue((int) (byte) 1);
        tools.jackson.core.io.IOContext iOContext20 = null;
        java.io.InputStream inputStream21 = null;
        byte[] byteArray25 = new byte[] { (byte) 100, (byte) 1 };
        tools.jackson.core.io.UTF32Reader uTF32Reader29 = new tools.jackson.core.io.UTF32Reader(iOContext20, inputStream21, false, byteArray25, (int) (short) 0, (-1), false);
        boolean boolean31 = tokenFilter19.includeString((java.io.Reader) uTF32Reader29, (-1074));
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion32 = tools.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_NON_NULL;
        tools.jackson.core.filter.FilteringGeneratorDelegate filteringGeneratorDelegate34 = new tools.jackson.core.filter.FilteringGeneratorDelegate((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate9, tokenFilter19, inclusion32, true);
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.JsonGenerator jsonGenerator36 = filteringGeneratorDelegate34.writeNumber("UNKNOWN");
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(minimalPrettyPrinter7);
        org.junit.Assert.assertNotNull(tokenFilterContext12);
        org.junit.Assert.assertNull(jsonToken13);
        org.junit.Assert.assertNotNull(tokenFilter14);
        org.junit.Assert.assertNotNull(tokenFilter15);
        org.junit.Assert.assertTrue("'" + boolean17 + "' != '" + true + "'", boolean17 == true);
        org.junit.Assert.assertNotNull(tokenFilter19);
        org.junit.Assert.assertNotNull(byteArray25);
        org.junit.Assert.assertArrayEquals(byteArray25, new byte[] { (byte) 100, (byte) 1 });
        org.junit.Assert.assertTrue("'" + boolean31 + "' != '" + true + "'", boolean31 == true);
        org.junit.Assert.assertTrue("'" + inclusion32 + "' != '" + tools.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_NON_NULL + "'", inclusion32.equals(tools.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_NON_NULL));
    }

    @Test
    public void test0477() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0477");
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder0 = tools.jackson.core.json.JsonFactory.builderWithJackson2Defaults();
        tools.jackson.core.SerializableString serializableString1 = null;
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder2 = jsonFactoryBuilder0.rootValueSeparator(serializableString1);
        tools.jackson.core.io.OutputDecorator outputDecorator3 = null;
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder4 = jsonFactoryBuilder2.outputDecorator(outputDecorator3);
        java.util.List<tools.jackson.core.util.JsonGeneratorDecorator> jsonGeneratorDecoratorList5 = jsonFactoryBuilder4.generatorDecorators();
        org.junit.Assert.assertNotNull(jsonFactoryBuilder0);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder2);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder4);
        org.junit.Assert.assertNull(jsonGeneratorDecoratorList5);
    }

    @Test
    public void test0478() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0478");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        tools.jackson.core.filter.TokenFilter tokenFilter6 = filteringParserDelegate5.getFilter();
        tools.jackson.core.JsonToken jsonToken7 = filteringParserDelegate5.currentToken();
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean8 = filteringParserDelegate5.canReadObjectId();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(tokenFilter6);
        org.junit.Assert.assertNull(jsonToken7);
    }

    @Test
    public void test0479() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0479");
        tools.jackson.core.util.DefaultPrettyPrinter.NopIndenter nopIndenter0 = new tools.jackson.core.util.DefaultPrettyPrinter.NopIndenter();
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter1 = new tools.jackson.core.util.MinimalPrettyPrinter();
        tools.jackson.core.JsonGenerator jsonGenerator2 = null;
        minimalPrettyPrinter1.beforeObjectEntries(jsonGenerator2);
        tools.jackson.core.util.Separators separators7 = new tools.jackson.core.util.Separators('~', ' ', ' ');
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter8 = minimalPrettyPrinter1.setSeparators(separators7);
        tools.jackson.core.JsonGenerator jsonGenerator9 = null;
        tools.jackson.core.util.JsonGeneratorDelegate jsonGeneratorDelegate10 = new tools.jackson.core.util.JsonGeneratorDelegate(jsonGenerator9);
        minimalPrettyPrinter1.beforeObjectEntries((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate10);
        tools.jackson.core.filter.TokenFilter tokenFilter12 = null;
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext13 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter12);
        tools.jackson.core.JsonToken jsonToken14 = tokenFilterContext13.nextTokenToRead();
        tools.jackson.core.filter.TokenFilter tokenFilter15 = tools.jackson.core.filter.TokenFilter.INCLUDE_ALL;
        tools.jackson.core.filter.TokenFilter tokenFilter16 = tokenFilterContext13.checkValue(tokenFilter15);
        java.math.BigDecimal bigDecimal17 = null;
        boolean boolean18 = tokenFilter15.includeNumber(bigDecimal17);
        tools.jackson.core.filter.TokenFilter tokenFilter20 = tokenFilter15.includeRootValue((int) (byte) 1);
        tools.jackson.core.io.IOContext iOContext21 = null;
        java.io.InputStream inputStream22 = null;
        byte[] byteArray26 = new byte[] { (byte) 100, (byte) 1 };
        tools.jackson.core.io.UTF32Reader uTF32Reader30 = new tools.jackson.core.io.UTF32Reader(iOContext21, inputStream22, false, byteArray26, (int) (short) 0, (-1), false);
        boolean boolean32 = tokenFilter20.includeString((java.io.Reader) uTF32Reader30, (-1074));
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion33 = tools.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_NON_NULL;
        tools.jackson.core.filter.FilteringGeneratorDelegate filteringGeneratorDelegate35 = new tools.jackson.core.filter.FilteringGeneratorDelegate((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate10, tokenFilter20, inclusion33, true);
        nopIndenter0.writeIndentation((tools.jackson.core.JsonGenerator) filteringGeneratorDelegate35, (int) '#');
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.JsonGenerator jsonGenerator39 = filteringGeneratorDelegate35.writeComment("100.0");
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(minimalPrettyPrinter8);
        org.junit.Assert.assertNotNull(tokenFilterContext13);
        org.junit.Assert.assertNull(jsonToken14);
        org.junit.Assert.assertNotNull(tokenFilter15);
        org.junit.Assert.assertNotNull(tokenFilter16);
        org.junit.Assert.assertTrue("'" + boolean18 + "' != '" + true + "'", boolean18 == true);
        org.junit.Assert.assertNotNull(tokenFilter20);
        org.junit.Assert.assertNotNull(byteArray26);
        org.junit.Assert.assertArrayEquals(byteArray26, new byte[] { (byte) 100, (byte) 1 });
        org.junit.Assert.assertTrue("'" + boolean32 + "' != '" + true + "'", boolean32 == true);
        org.junit.Assert.assertTrue("'" + inclusion33 + "' != '" + tools.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_NON_NULL + "'", inclusion33.equals(tools.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_NON_NULL));
    }

    @Test
    public void test0480() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0480");
        tools.jackson.core.io.ContentReference contentReference0 = tools.jackson.core.io.ContentReference.redacted();
        java.lang.Object obj1 = null;
        tools.jackson.core.JacksonException.Reference reference2 = new tools.jackson.core.JacksonException.Reference(obj1);
        java.lang.Object obj3 = reference2.from();
        boolean boolean4 = contentReference0.equals((java.lang.Object) reference2);
        tools.jackson.core.TokenStreamLocation tokenStreamLocation8 = new tools.jackson.core.TokenStreamLocation(contentReference0, (long) '#', 0, (int) (byte) 10);
        java.lang.StringBuilder stringBuilder9 = null;
        // The following exception was thrown during execution in test generation
        try {
            java.lang.StringBuilder stringBuilder10 = tokenStreamLocation8.toString(stringBuilder9);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(contentReference0);
        org.junit.Assert.assertNull(obj3);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
    }

    @Test
    public void test0481() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0481");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        boolean boolean6 = filteringParserDelegate5.isExpectedStartObjectToken();
        int int7 = filteringParserDelegate5.currentTokenId();
        boolean boolean8 = filteringParserDelegate5.isExpectedStartObjectToken();
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.JsonToken jsonToken9 = filteringParserDelegate5.nextValue();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 0 + "'", int7 == 0);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
    }

    @Test
    public void test0482() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0482");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        int int6 = filteringParserDelegate5.currentTokenId();
        java.lang.String str7 = filteringParserDelegate5.currentName();
        // The following exception was thrown during execution in test generation
        try {
            int int8 = filteringParserDelegate5.getIntValue();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
        org.junit.Assert.assertNull(str7);
    }

    @Test
    public void test0483() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0483");
        char[] charArray3 = new char[] { ' ', ' ', '4' };
        boolean boolean7 = tools.jackson.core.io.NumberInput.inLongRange(charArray3, (-324), (int) '4', false);
        boolean boolean11 = tools.jackson.core.io.NumberInput.inLongRange(charArray3, 55296, (int) (short) 100, false);
        // The following exception was thrown during execution in test generation
        try {
            int int14 = tools.jackson.core.io.NumberInput.parseInt(charArray3, (-324), (int) (byte) 10);
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: -324");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(charArray3);
        org.junit.Assert.assertArrayEquals(charArray3, new char[] { ' ', ' ', '4' });
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
    }

    @Test
    public void test0484() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0484");
        tools.jackson.core.Base64Variant base64Variant0 = tools.jackson.core.Base64Variants.MODIFIED_FOR_URL;
        boolean boolean2 = base64Variant0.equals((java.lang.Object) 3);
        java.lang.String str3 = base64Variant0.missingPaddingMessage();
        org.junit.Assert.assertNotNull(base64Variant0);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertEquals("'" + str3 + "' != '" + "Unexpected end of base64-encoded String: base64 variant 'MODIFIED-FOR-URL' expects padding (one or more '\000' characters) at the end. This Base64Variant might have been incorrectly configured" + "'", str3, "Unexpected end of base64-encoded String: base64 variant 'MODIFIED-FOR-URL' expects padding (one or more '\000' characters) at the end. This Base64Variant might have been incorrectly configured");
    }

    @Test
    public void test0485() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0485");
        tools.jackson.core.json.DupDetector dupDetector0 = null;
        tools.jackson.core.util.SimpleStreamWriteContext simpleStreamWriteContext1 = tools.jackson.core.util.SimpleStreamWriteContext.createRootContext(dupDetector0);
        tools.jackson.core.io.ContentReference contentReference2 = null;
        tools.jackson.core.TokenStreamLocation tokenStreamLocation3 = simpleStreamWriteContext1.startLocation(contentReference2);
        java.lang.String str4 = tokenStreamLocation3.sourceDescription();
        int int5 = tokenStreamLocation3.getColumnNr();
        org.junit.Assert.assertNotNull(simpleStreamWriteContext1);
        org.junit.Assert.assertNotNull(tokenStreamLocation3);
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "UNKNOWN" + "'", str4, "UNKNOWN");
        org.junit.Assert.assertTrue("'" + int5 + "' != '" + (-1) + "'", int5 == (-1));
    }

    @Test
    public void test0486() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0486");
        tools.jackson.core.JsonGenerator jsonGenerator0 = null;
        java.lang.Throwable throwable2 = null;
        tools.jackson.core.exc.StreamWriteException streamWriteException3 = new tools.jackson.core.exc.StreamWriteException(jsonGenerator0, "hi!", throwable2);
        tools.jackson.core.JsonGenerator jsonGenerator4 = null;
        tools.jackson.core.exc.StreamWriteException streamWriteException5 = streamWriteException3.withGenerator(jsonGenerator4);
        tools.jackson.core.JsonGenerator jsonGenerator6 = null;
        java.lang.Throwable throwable8 = null;
        tools.jackson.core.exc.StreamWriteException streamWriteException9 = new tools.jackson.core.exc.StreamWriteException(jsonGenerator6, "hi!", throwable8);
        tools.jackson.core.json.DupDetector dupDetector10 = null;
        tools.jackson.core.util.SimpleStreamWriteContext simpleStreamWriteContext11 = tools.jackson.core.util.SimpleStreamWriteContext.createRootContext(dupDetector10);
        boolean boolean12 = simpleStreamWriteContext11.writeValue();
        tools.jackson.core.JacksonException jacksonException14 = tools.jackson.core.JacksonException.wrapWithPath((java.lang.Throwable) streamWriteException9, (java.lang.Object) boolean12, "hi!");
        tools.jackson.core.JsonToken jsonToken16 = null;
        tools.jackson.core.type.WritableTypeId writableTypeId18 = new tools.jackson.core.type.WritableTypeId((java.lang.Object) '#', jsonToken16, (java.lang.Object) ' ');
        tools.jackson.core.type.WritableTypeId.Inclusion inclusion19 = tools.jackson.core.type.WritableTypeId.Inclusion.PAYLOAD_PROPERTY;
        writableTypeId18.include = inclusion19;
        tools.jackson.core.JacksonException.Reference reference22 = new tools.jackson.core.JacksonException.Reference((java.lang.Object) writableTypeId18, 256);
        tools.jackson.core.JacksonException jacksonException23 = tools.jackson.core.JacksonException.wrapWithPath((java.lang.Throwable) streamWriteException9, reference22);
        java.lang.String str24 = reference22.getDescription();
        java.lang.String str25 = reference22.getPropertyName();
        tools.jackson.core.JacksonException jacksonException26 = streamWriteException5.prependPath(reference22);
        org.junit.Assert.assertNotNull(streamWriteException5);
        org.junit.Assert.assertNotNull(simpleStreamWriteContext11);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + true + "'", boolean12 == true);
        org.junit.Assert.assertNotNull(jacksonException14);
        org.junit.Assert.assertTrue("'" + inclusion19 + "' != '" + tools.jackson.core.type.WritableTypeId.Inclusion.PAYLOAD_PROPERTY + "'", inclusion19.equals(tools.jackson.core.type.WritableTypeId.Inclusion.PAYLOAD_PROPERTY));
        org.junit.Assert.assertNotNull(jacksonException23);
        org.junit.Assert.assertEquals("'" + str24 + "' != '" + "tools.jackson.core.type.WritableTypeId[256]" + "'", str24, "tools.jackson.core.type.WritableTypeId[256]");
        org.junit.Assert.assertNull(str25);
        org.junit.Assert.assertNotNull(jacksonException26);
    }

    @Test
    public void test0487() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0487");
        tools.jackson.core.json.DupDetector dupDetector0 = null;
        tools.jackson.core.util.SimpleStreamWriteContext simpleStreamWriteContext1 = tools.jackson.core.util.SimpleStreamWriteContext.createRootContext(dupDetector0);
        tools.jackson.core.json.DupDetector dupDetector2 = simpleStreamWriteContext1.getDupDetector();
        tools.jackson.core.json.DupDetector dupDetector3 = null;
        tools.jackson.core.util.SimpleStreamWriteContext simpleStreamWriteContext4 = simpleStreamWriteContext1.withDupDetector(dupDetector3);
        java.lang.String str5 = simpleStreamWriteContext4.typeDesc();
        boolean boolean6 = simpleStreamWriteContext4.writeValue();
        boolean boolean7 = simpleStreamWriteContext4.hasPathSegment();
        int int8 = simpleStreamWriteContext4.getEntryCount();
        org.junit.Assert.assertNotNull(simpleStreamWriteContext1);
        org.junit.Assert.assertNull(dupDetector2);
        org.junit.Assert.assertNotNull(simpleStreamWriteContext4);
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "root" + "'", str5, "root");
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + true + "'", boolean6 == true);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 1 + "'", int8 == 1);
    }

    @Test
    public void test0488() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0488");
        int int0 = tools.jackson.core.io.UTF8Writer.SURR2_LAST;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 57343 + "'", int0 == 57343);
    }

    @Test
    public void test0489() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0489");
        tools.jackson.core.JsonGenerator jsonGenerator0 = null;
        tools.jackson.core.util.JsonGeneratorDelegate jsonGeneratorDelegate1 = new tools.jackson.core.util.JsonGeneratorDelegate(jsonGenerator0);
        tools.jackson.core.JsonParser jsonParser2 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter3 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion4 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate7 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser2, tokenFilter3, inclusion4, true, true);
        boolean boolean8 = filteringParserDelegate7.isExpectedStartObjectToken();
        int int9 = filteringParserDelegate7.currentTokenId();
        boolean boolean10 = filteringParserDelegate7.isExpectedStartObjectToken();
        tools.jackson.core.JsonParser jsonParser11 = filteringParserDelegate7.skipChildren();
        tools.jackson.core.util.JsonParserDelegate jsonParserDelegate12 = new tools.jackson.core.util.JsonParserDelegate((tools.jackson.core.JsonParser) filteringParserDelegate7);
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.JsonGenerator jsonGenerator13 = jsonGeneratorDelegate1.writeTypeId((java.lang.Object) jsonParserDelegate12);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertTrue("'" + int9 + "' != '" + 0 + "'", int9 == 0);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        org.junit.Assert.assertNotNull(jsonParser11);
    }

    @Test
    public void test0490() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0490");
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder0 = tools.jackson.core.json.JsonFactory.builderWithJackson2Defaults();
        tools.jackson.core.SerializableString serializableString1 = null;
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder2 = jsonFactoryBuilder0.rootValueSeparator(serializableString1);
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder4 = jsonFactoryBuilder2.rootValueSeparator("\r\n");
        int int5 = jsonFactoryBuilder2.highestNonEscapedChar();
        org.junit.Assert.assertNotNull(jsonFactoryBuilder0);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder2);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder4);
        org.junit.Assert.assertTrue("'" + int5 + "' != '" + 0 + "'", int5 == 0);
    }

    @Test
    public void test0491() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0491");
        java.lang.String str2 = tools.jackson.core.io.NumberOutput.toString((double) 6557778377634271669L, false);
        org.junit.Assert.assertEquals("'" + str2 + "' != '" + "6.5577783776342712E18" + "'", str2, "6.5577783776342712E18");
    }

    @Test
    public void test0492() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0492");
        tools.jackson.core.filter.TokenFilter tokenFilter0 = null;
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext1 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter0);
        tools.jackson.core.JsonToken jsonToken2 = tokenFilterContext1.nextTokenToRead();
        tools.jackson.core.filter.TokenFilter tokenFilter3 = tools.jackson.core.filter.TokenFilter.INCLUDE_ALL;
        tools.jackson.core.filter.TokenFilter tokenFilter4 = tokenFilterContext1.checkValue(tokenFilter3);
        java.math.BigDecimal bigDecimal5 = null;
        boolean boolean6 = tokenFilter3.includeNumber(bigDecimal5);
        tools.jackson.core.filter.TokenFilter tokenFilter8 = tokenFilter3.includeRootValue((int) (byte) 1);
        tokenFilter3.filterFinishObject();
        java.lang.String str10 = tokenFilter3.toString();
        org.junit.Assert.assertNotNull(tokenFilterContext1);
        org.junit.Assert.assertNull(jsonToken2);
        org.junit.Assert.assertNotNull(tokenFilter3);
        org.junit.Assert.assertNotNull(tokenFilter4);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + true + "'", boolean6 == true);
        org.junit.Assert.assertNotNull(tokenFilter8);
        org.junit.Assert.assertEquals("'" + str10 + "' != '" + "TokenFilter.INCLUDE_ALL" + "'", str10, "TokenFilter.INCLUDE_ALL");
    }

    @Test
    public void test0493() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0493");
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder0 = tools.jackson.core.json.JsonFactory.builderWithJackson2Defaults();
        tools.jackson.core.SerializableString serializableString1 = null;
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder2 = jsonFactoryBuilder0.rootValueSeparator(serializableString1);
        tools.jackson.core.io.OutputDecorator outputDecorator3 = jsonFactoryBuilder2.outputDecorator();
        tools.jackson.core.io.InputDecorator inputDecorator4 = null;
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder5 = jsonFactoryBuilder2.inputDecorator(inputDecorator4);
        tools.jackson.core.util.JsonGeneratorDecorator jsonGeneratorDecorator6 = null;
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder7 = jsonFactoryBuilder5.addDecorator(jsonGeneratorDecorator6);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder0);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder2);
        org.junit.Assert.assertNull(outputDecorator3);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder5);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder7);
    }

    @Test
    public void test0494() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0494");
        tools.jackson.core.util.DefaultPrettyPrinter.NopIndenter nopIndenter0 = new tools.jackson.core.util.DefaultPrettyPrinter.NopIndenter();
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter1 = new tools.jackson.core.util.MinimalPrettyPrinter();
        tools.jackson.core.JsonGenerator jsonGenerator2 = null;
        minimalPrettyPrinter1.beforeObjectEntries(jsonGenerator2);
        tools.jackson.core.util.Separators separators7 = new tools.jackson.core.util.Separators('~', ' ', ' ');
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter8 = minimalPrettyPrinter1.setSeparators(separators7);
        tools.jackson.core.JsonGenerator jsonGenerator9 = null;
        tools.jackson.core.util.JsonGeneratorDelegate jsonGeneratorDelegate10 = new tools.jackson.core.util.JsonGeneratorDelegate(jsonGenerator9);
        minimalPrettyPrinter1.beforeObjectEntries((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate10);
        tools.jackson.core.filter.TokenFilter tokenFilter12 = null;
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext13 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter12);
        tools.jackson.core.JsonToken jsonToken14 = tokenFilterContext13.nextTokenToRead();
        tools.jackson.core.filter.TokenFilter tokenFilter15 = tools.jackson.core.filter.TokenFilter.INCLUDE_ALL;
        tools.jackson.core.filter.TokenFilter tokenFilter16 = tokenFilterContext13.checkValue(tokenFilter15);
        java.math.BigDecimal bigDecimal17 = null;
        boolean boolean18 = tokenFilter15.includeNumber(bigDecimal17);
        tools.jackson.core.filter.TokenFilter tokenFilter20 = tokenFilter15.includeRootValue((int) (byte) 1);
        tools.jackson.core.io.IOContext iOContext21 = null;
        java.io.InputStream inputStream22 = null;
        byte[] byteArray26 = new byte[] { (byte) 100, (byte) 1 };
        tools.jackson.core.io.UTF32Reader uTF32Reader30 = new tools.jackson.core.io.UTF32Reader(iOContext21, inputStream22, false, byteArray26, (int) (short) 0, (-1), false);
        boolean boolean32 = tokenFilter20.includeString((java.io.Reader) uTF32Reader30, (-1074));
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion33 = tools.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_NON_NULL;
        tools.jackson.core.filter.FilteringGeneratorDelegate filteringGeneratorDelegate35 = new tools.jackson.core.filter.FilteringGeneratorDelegate((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate10, tokenFilter20, inclusion33, true);
        nopIndenter0.writeIndentation((tools.jackson.core.JsonGenerator) filteringGeneratorDelegate35, (int) '#');
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder38 = tools.jackson.core.json.JsonFactory.builderWithJackson2Defaults();
        tools.jackson.core.SerializableString serializableString39 = null;
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder40 = jsonFactoryBuilder38.rootValueSeparator(serializableString39);
        tools.jackson.core.io.OutputDecorator outputDecorator41 = jsonFactoryBuilder40.outputDecorator();
        tools.jackson.core.io.InputDecorator inputDecorator42 = null;
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder43 = jsonFactoryBuilder40.inputDecorator(inputDecorator42);
        tools.jackson.core.io.SerializedString serializedString44 = tools.jackson.core.PrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
        byte[] byteArray45 = serializedString44.asQuotedUTF8();
        java.lang.String str46 = serializedString44.toString();
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder47 = jsonFactoryBuilder40.rootValueSeparator((tools.jackson.core.SerializableString) serializedString44);
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.JsonGenerator jsonGenerator48 = filteringGeneratorDelegate35.writeString((tools.jackson.core.SerializableString) serializedString44);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(minimalPrettyPrinter8);
        org.junit.Assert.assertNotNull(tokenFilterContext13);
        org.junit.Assert.assertNull(jsonToken14);
        org.junit.Assert.assertNotNull(tokenFilter15);
        org.junit.Assert.assertNotNull(tokenFilter16);
        org.junit.Assert.assertTrue("'" + boolean18 + "' != '" + true + "'", boolean18 == true);
        org.junit.Assert.assertNotNull(tokenFilter20);
        org.junit.Assert.assertNotNull(byteArray26);
        org.junit.Assert.assertArrayEquals(byteArray26, new byte[] { (byte) 100, (byte) 1 });
        org.junit.Assert.assertTrue("'" + boolean32 + "' != '" + true + "'", boolean32 == true);
        org.junit.Assert.assertTrue("'" + inclusion33 + "' != '" + tools.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_NON_NULL + "'", inclusion33.equals(tools.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_NON_NULL));
        org.junit.Assert.assertNotNull(jsonFactoryBuilder38);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder40);
        org.junit.Assert.assertNull(outputDecorator41);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder43);
        org.junit.Assert.assertNotNull(serializedString44);
        org.junit.Assert.assertNotNull(byteArray45);
        org.junit.Assert.assertArrayEquals(byteArray45, new byte[] { (byte) 32 });
        org.junit.Assert.assertEquals("'" + str46 + "' != '" + " " + "'", str46, " ");
        org.junit.Assert.assertNotNull(jsonFactoryBuilder47);
    }

    @Test
    public void test0495() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0495");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        boolean boolean6 = filteringParserDelegate5.isExpectedStartObjectToken();
        int int7 = filteringParserDelegate5.currentTokenId();
        tools.jackson.core.JsonToken jsonToken8 = null;
        boolean boolean9 = filteringParserDelegate5.hasToken(jsonToken8);
        int int10 = filteringParserDelegate5.currentTokenId();
        boolean boolean11 = filteringParserDelegate5.isExpectedStartObjectToken();
        boolean boolean12 = filteringParserDelegate5.hasCurrentToken();
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.JsonToken jsonToken13 = filteringParserDelegate5.nextToken();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 0 + "'", int7 == 0);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + true + "'", boolean9 == true);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 0 + "'", int10 == 0);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
    }

    @Test
    public void test0496() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0496");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        boolean boolean6 = filteringParserDelegate5.isExpectedStartObjectToken();
        int int7 = filteringParserDelegate5.currentTokenId();
        tools.jackson.core.JsonToken jsonToken8 = null;
        boolean boolean9 = filteringParserDelegate5.hasToken(jsonToken8);
        int int10 = filteringParserDelegate5.currentTokenId();
        boolean boolean11 = filteringParserDelegate5.isExpectedStartObjectToken();
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.FormatSchema formatSchema12 = filteringParserDelegate5.getSchema();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 0 + "'", int7 == 0);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + true + "'", boolean9 == true);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 0 + "'", int10 == 0);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
    }

    @Test
    public void test0497() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0497");
        java.io.DataOutput dataOutput0 = null;
        tools.jackson.core.io.DataOutputAsStream dataOutputAsStream1 = new tools.jackson.core.io.DataOutputAsStream(dataOutput0);
        tools.jackson.core.Base64Variant base64Variant2 = tools.jackson.core.Base64Variants.MODIFIED_FOR_URL;
        byte[] byteArray10 = new byte[] { (byte) 10, (byte) -1, (byte) -1, (byte) -1, (byte) -1 };
        int int12 = base64Variant2.encodeBase64Partial(10, (int) 'a', byteArray10, (int) (byte) 1);
        tools.jackson.core.Base64Variant base64Variant15 = new tools.jackson.core.Base64Variant(base64Variant2, "hi!", 0);
        tools.jackson.core.Base64Variant base64Variant16 = base64Variant2.withPaddingAllowed();
        tools.jackson.core.Base64Variant base64Variant17 = tools.jackson.core.Base64Variants.MODIFIED_FOR_URL;
        byte[] byteArray25 = new byte[] { (byte) 10, (byte) -1, (byte) -1, (byte) -1, (byte) -1 };
        int int27 = base64Variant17.encodeBase64Partial(10, (int) 'a', byteArray25, (int) (byte) 1);
        java.lang.String str30 = base64Variant2.encode(byteArray25, false, "//0.0.0");
        // The following exception was thrown during execution in test generation
        try {
            dataOutputAsStream1.write(byteArray25);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(base64Variant2);
        org.junit.Assert.assertNotNull(byteArray10);
        org.junit.Assert.assertArrayEquals(byteArray10, new byte[] { (byte) 10, (byte) 65, (byte) 65, (byte) -1, (byte) -1 });
        org.junit.Assert.assertTrue("'" + int12 + "' != '" + 3 + "'", int12 == 3);
        org.junit.Assert.assertNotNull(base64Variant16);
        org.junit.Assert.assertNotNull(base64Variant17);
        org.junit.Assert.assertNotNull(byteArray25);
        org.junit.Assert.assertArrayEquals(byteArray25, new byte[] { (byte) 10, (byte) 65, (byte) 65, (byte) -1, (byte) -1 });
        org.junit.Assert.assertTrue("'" + int27 + "' != '" + 3 + "'", int27 == 3);
        org.junit.Assert.assertEquals("'" + str30 + "' != '" + "CkFB__8" + "'", str30, "CkFB__8");
    }

    @Test
    public void test0498() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0498");
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder0 = tools.jackson.core.json.JsonFactory.builder();
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder2 = jsonFactoryBuilder0.highestNonEscapedChar((-324));
        tools.jackson.core.util.JsonpCharacterEscapes jsonpCharacterEscapes3 = tools.jackson.core.util.JsonpCharacterEscapes.instance();
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder4 = jsonFactoryBuilder2.characterEscapes((tools.jackson.core.io.CharacterEscapes) jsonpCharacterEscapes3);
        tools.jackson.core.json.JsonReadFeature jsonReadFeature5 = null;
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder6 = jsonFactoryBuilder4.disable(jsonReadFeature5);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(jsonFactoryBuilder0);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder2);
        org.junit.Assert.assertNotNull(jsonpCharacterEscapes3);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder4);
    }

    @Test
    public void test0499() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0499");
        tools.jackson.core.util.InternCache internCache1 = tools.jackson.core.util.InternCache.instance;
        tools.jackson.core.json.DupDetector dupDetector2 = null;
        tools.jackson.core.util.SimpleStreamWriteContext simpleStreamWriteContext3 = tools.jackson.core.util.SimpleStreamWriteContext.createRootContext(dupDetector2);
        tools.jackson.core.json.DupDetector dupDetector4 = simpleStreamWriteContext3.getDupDetector();
        boolean boolean5 = simpleStreamWriteContext3.inObject();
        boolean boolean6 = internCache1.containsValue((java.lang.Object) boolean5);
        boolean boolean7 = internCache1.isEmpty();
        tools.jackson.core.util.InternCache internCache8 = new tools.jackson.core.util.InternCache();
        internCache1.putAll((java.util.Map<java.lang.String, java.lang.String>) internCache8);
        tools.jackson.core.Version version10 = tools.jackson.core.Version.unknownVersion();
        int int11 = version10.getPatchLevel();
        tools.jackson.core.Version version12 = tools.jackson.core.Version.unknownVersion();
        boolean boolean13 = version12.isUnknownVersion();
        int int14 = version10.compareTo(version12);
        tools.jackson.core.util.BufferRecycler bufferRecycler15 = null;
        tools.jackson.core.util.TextBuffer textBuffer16 = new tools.jackson.core.util.TextBuffer(bufferRecycler15);
        textBuffer16.resetWithString("");
        int int19 = textBuffer16.getCurrentSegmentSize();
        char[] charArray20 = textBuffer16.contentsAsArray();
        char[] charArray21 = textBuffer16.contentsAsArray();
        boolean boolean22 = internCache8.remove((java.lang.Object) version12, (java.lang.Object) charArray21);
        // The following exception was thrown during execution in test generation
        try {
            int int24 = tools.jackson.core.io.NumberOutput.outputInt((int) (byte) -17, charArray21, 50000);
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: 50000");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(internCache1);
        org.junit.Assert.assertNotNull(simpleStreamWriteContext3);
        org.junit.Assert.assertNull(dupDetector4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
// flaky "3) test0499(RegressionTest0)":         org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertNotNull(version10);
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 0 + "'", int11 == 0);
        org.junit.Assert.assertNotNull(version12);
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + true + "'", boolean13 == true);
        org.junit.Assert.assertTrue("'" + int14 + "' != '" + 0 + "'", int14 == 0);
        org.junit.Assert.assertTrue("'" + int19 + "' != '" + 0 + "'", int19 == 0);
        org.junit.Assert.assertNotNull(charArray20);
        org.junit.Assert.assertArrayEquals(charArray20, new char[] {});
        org.junit.Assert.assertNotNull(charArray21);
        org.junit.Assert.assertArrayEquals(charArray21, new char[] {});
        org.junit.Assert.assertTrue("'" + boolean22 + "' != '" + false + "'", boolean22 == false);
    }

    @Test
    public void test0500() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0500");
        tools.jackson.core.util.BufferRecycler bufferRecycler0 = null;
        tools.jackson.core.util.TextBuffer textBuffer1 = new tools.jackson.core.util.TextBuffer(bufferRecycler0);
        textBuffer1.resetWithString("");
        int int4 = textBuffer1.getCurrentSegmentSize();
        char[] charArray5 = textBuffer1.contentsAsArray();
        char[] charArray6 = textBuffer1.contentsAsArray();
        // The following exception was thrown during execution in test generation
        try {
            java.math.BigDecimal bigDecimal7 = tools.jackson.core.io.NumberInput.parseBigDecimal(charArray6);
            org.junit.Assert.fail("Expected exception of type java.lang.NumberFormatException; message: Value \"\" cannot be deserialized as `java.math.BigDecimal`, reason:  Not a valid number representation");
        } catch (java.lang.NumberFormatException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 0 + "'", int4 == 0);
        org.junit.Assert.assertNotNull(charArray5);
        org.junit.Assert.assertArrayEquals(charArray5, new char[] {});
        org.junit.Assert.assertNotNull(charArray6);
        org.junit.Assert.assertArrayEquals(charArray6, new char[] {});
    }
}
