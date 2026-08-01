import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegressionTest3 {

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
    public void test1501() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1501");
        tools.jackson.core.util.DefaultIndenter defaultIndenter0 = new tools.jackson.core.util.DefaultIndenter();
        java.lang.String str1 = defaultIndenter0.getEol();
        boolean boolean2 = defaultIndenter0.isInline();
        java.lang.String str3 = defaultIndenter0.getIndent();
        tools.jackson.core.json.JsonFactory jsonFactory4 = new tools.jackson.core.json.JsonFactory();
        int int5 = jsonFactory4.getFormatReadFeatures();
        tools.jackson.core.json.JsonFactory jsonFactory6 = new tools.jackson.core.json.JsonFactory();
        int int7 = jsonFactory6.getFormatReadFeatures();
        tools.jackson.core.ObjectWriteContext objectWriteContext8 = tools.jackson.core.TokenStreamFactory.EMPTY_WRITE_CONTEXT;
        tools.jackson.core.util.BufferRecycler bufferRecycler9 = new tools.jackson.core.util.BufferRecycler();
        tools.jackson.core.io.SegmentedStringWriter segmentedStringWriter10 = new tools.jackson.core.io.SegmentedStringWriter(bufferRecycler9);
        segmentedStringWriter10.write("MODIFIED-FOR-URL/UNKNOWN/0.0.0");
        java.io.Writer writer14 = segmentedStringWriter10.append('a');
        tools.jackson.core.JsonGenerator jsonGenerator15 = jsonFactory6.createGenerator(objectWriteContext8, (java.io.Writer) segmentedStringWriter10);
        tools.jackson.core.JsonParser jsonParser16 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter17 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion18 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate21 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser16, tokenFilter17, inclusion18, true, true);
        boolean boolean22 = filteringParserDelegate21.isExpectedStartObjectToken();
        int int23 = filteringParserDelegate21.currentTokenId();
        boolean boolean24 = filteringParserDelegate21.isExpectedStartObjectToken();
        tools.jackson.core.util.BufferRecycler bufferRecycler25 = new tools.jackson.core.util.BufferRecycler();
        tools.jackson.core.io.SegmentedStringWriter segmentedStringWriter26 = new tools.jackson.core.io.SegmentedStringWriter(bufferRecycler25);
        segmentedStringWriter26.write("\"_2QK\\n\"");
        int int29 = filteringParserDelegate21.releaseBuffered((java.io.Writer) segmentedStringWriter26);
        tools.jackson.core.JsonGenerator jsonGenerator30 = jsonFactory4.createGenerator(objectWriteContext8, (java.io.Writer) segmentedStringWriter26);
        defaultIndenter0.writeIndentation(jsonGenerator30, 309);
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "\r\n" + "'", str1, "\r\n");
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertEquals("'" + str3 + "' != '" + "  " + "'", str3, "  ");
        org.junit.Assert.assertTrue("'" + int5 + "' != '" + 0 + "'", int5 == 0);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 0 + "'", int7 == 0);
        org.junit.Assert.assertNotNull(objectWriteContext8);
        org.junit.Assert.assertNotNull(writer14);
        org.junit.Assert.assertNotNull(jsonGenerator15);
        org.junit.Assert.assertTrue("'" + boolean22 + "' != '" + false + "'", boolean22 == false);
        org.junit.Assert.assertTrue("'" + int23 + "' != '" + 0 + "'", int23 == 0);
        org.junit.Assert.assertTrue("'" + boolean24 + "' != '" + false + "'", boolean24 == false);
        org.junit.Assert.assertTrue("'" + int29 + "' != '" + (-1) + "'", int29 == (-1));
        org.junit.Assert.assertNotNull(jsonGenerator30);
    }

    @Test
    public void test1502() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1502");
        tools.jackson.core.exc.StreamConstraintsException streamConstraintsException1 = new tools.jackson.core.exc.StreamConstraintsException("0.0.0");
        tools.jackson.core.exc.StreamReadException streamReadException3 = new tools.jackson.core.exc.StreamReadException("");
        java.lang.String str4 = streamReadException3.getPathReference();
        tools.jackson.core.exc.StreamReadException streamReadException6 = new tools.jackson.core.exc.StreamReadException("");
        streamReadException3.addSuppressed((java.lang.Throwable) streamReadException6);
        tools.jackson.core.JacksonException jacksonException8 = streamConstraintsException1.withCause((java.lang.Throwable) streamReadException3);
        tools.jackson.core.util.DefaultPrettyPrinter defaultPrettyPrinter9 = new tools.jackson.core.util.DefaultPrettyPrinter();
        tools.jackson.core.util.DefaultPrettyPrinter.FixedSpaceIndenter fixedSpaceIndenter10 = tools.jackson.core.util.DefaultPrettyPrinter.FixedSpaceIndenter.instance();
        defaultPrettyPrinter9.indentArraysWith((tools.jackson.core.util.DefaultPrettyPrinter.Indenter) fixedSpaceIndenter10);
        tools.jackson.core.util.Separators separators12 = tools.jackson.core.util.Separators.createDefaultInstance();
        tools.jackson.core.util.Separators separators14 = separators12.withObjectEntrySeparator('4');
        tools.jackson.core.util.Separators separators16 = separators14.withArrayElementSeparator('#');
        tools.jackson.core.util.DefaultPrettyPrinter defaultPrettyPrinter17 = defaultPrettyPrinter9.withSeparators(separators16);
        tools.jackson.core.JacksonException jacksonException19 = tools.jackson.core.JacksonException.wrapWithPath((java.lang.Throwable) streamConstraintsException1, (java.lang.Object) defaultPrettyPrinter9, 256);
        tools.jackson.core.util.DefaultPrettyPrinter defaultPrettyPrinter20 = new tools.jackson.core.util.DefaultPrettyPrinter(defaultPrettyPrinter9);
        tools.jackson.core.util.Separators separators21 = tools.jackson.core.util.Separators.createDefaultInstance();
        tools.jackson.core.util.Separators separators23 = separators21.withObjectNameValueSeparator('\000');
        tools.jackson.core.util.Separators separators25 = separators23.withArrayEmptySeparator("\r\n");
        char char26 = separators23.getObjectEntrySeparator();
        tools.jackson.core.util.DefaultPrettyPrinter defaultPrettyPrinter27 = defaultPrettyPrinter20.withSeparators(separators23);
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "" + "'", str4, "");
        org.junit.Assert.assertNotNull(jacksonException8);
        org.junit.Assert.assertNotNull(fixedSpaceIndenter10);
        org.junit.Assert.assertNotNull(separators12);
        org.junit.Assert.assertNotNull(separators14);
        org.junit.Assert.assertNotNull(separators16);
        org.junit.Assert.assertNotNull(defaultPrettyPrinter17);
        org.junit.Assert.assertNotNull(jacksonException19);
        org.junit.Assert.assertNotNull(separators21);
        org.junit.Assert.assertNotNull(separators23);
        org.junit.Assert.assertNotNull(separators25);
        org.junit.Assert.assertTrue("'" + char26 + "' != '" + ',' + "'", char26 == ',');
        org.junit.Assert.assertNotNull(defaultPrettyPrinter27);
    }

    @Test
    public void test1503() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1503");
        tools.jackson.core.util.DefaultPrettyPrinter.NopIndenter nopIndenter0 = tools.jackson.core.util.DefaultPrettyPrinter.NopIndenter.instance();
        boolean boolean1 = nopIndenter0.isInline();
        tools.jackson.core.util.DefaultPrettyPrinter.NopIndenter nopIndenter2 = new tools.jackson.core.util.DefaultPrettyPrinter.NopIndenter();
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter3 = new tools.jackson.core.util.MinimalPrettyPrinter();
        tools.jackson.core.JsonGenerator jsonGenerator4 = null;
        minimalPrettyPrinter3.beforeObjectEntries(jsonGenerator4);
        tools.jackson.core.util.Separators separators9 = new tools.jackson.core.util.Separators('~', ' ', ' ');
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter10 = minimalPrettyPrinter3.setSeparators(separators9);
        tools.jackson.core.JsonGenerator jsonGenerator11 = null;
        tools.jackson.core.util.JsonGeneratorDelegate jsonGeneratorDelegate12 = new tools.jackson.core.util.JsonGeneratorDelegate(jsonGenerator11);
        minimalPrettyPrinter3.beforeObjectEntries((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate12);
        tools.jackson.core.filter.TokenFilter tokenFilter14 = null;
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext15 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter14);
        tools.jackson.core.JsonToken jsonToken16 = tokenFilterContext15.nextTokenToRead();
        tools.jackson.core.filter.TokenFilter tokenFilter17 = tools.jackson.core.filter.TokenFilter.INCLUDE_ALL;
        tools.jackson.core.filter.TokenFilter tokenFilter18 = tokenFilterContext15.checkValue(tokenFilter17);
        java.math.BigDecimal bigDecimal19 = null;
        boolean boolean20 = tokenFilter17.includeNumber(bigDecimal19);
        tools.jackson.core.filter.TokenFilter tokenFilter22 = tokenFilter17.includeRootValue((int) (byte) 1);
        tools.jackson.core.io.IOContext iOContext23 = null;
        java.io.InputStream inputStream24 = null;
        byte[] byteArray28 = new byte[] { (byte) 100, (byte) 1 };
        tools.jackson.core.io.UTF32Reader uTF32Reader32 = new tools.jackson.core.io.UTF32Reader(iOContext23, inputStream24, false, byteArray28, (int) (short) 0, (-1), false);
        boolean boolean34 = tokenFilter22.includeString((java.io.Reader) uTF32Reader32, (-1074));
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion35 = tools.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_NON_NULL;
        tools.jackson.core.filter.FilteringGeneratorDelegate filteringGeneratorDelegate37 = new tools.jackson.core.filter.FilteringGeneratorDelegate((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate12, tokenFilter22, inclusion35, true);
        nopIndenter2.writeIndentation((tools.jackson.core.JsonGenerator) filteringGeneratorDelegate37, (int) '#');
        nopIndenter0.writeIndentation((tools.jackson.core.JsonGenerator) filteringGeneratorDelegate37, (-149));
        tools.jackson.core.exc.StreamWriteException streamWriteException43 = new tools.jackson.core.exc.StreamWriteException((tools.jackson.core.JsonGenerator) filteringGeneratorDelegate37, "0.0.0");
        double[] doubleArray45 = new double[] { 292 };
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.JsonGenerator jsonGenerator48 = filteringGeneratorDelegate37.writeArray(doubleArray45, (int) (byte) 10, 35);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(nopIndenter0);
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + true + "'", boolean1 == true);
        org.junit.Assert.assertNotNull(minimalPrettyPrinter10);
        org.junit.Assert.assertNotNull(tokenFilterContext15);
        org.junit.Assert.assertNull(jsonToken16);
        org.junit.Assert.assertNotNull(tokenFilter17);
        org.junit.Assert.assertNotNull(tokenFilter18);
        org.junit.Assert.assertTrue("'" + boolean20 + "' != '" + true + "'", boolean20 == true);
        org.junit.Assert.assertNotNull(tokenFilter22);
        org.junit.Assert.assertNotNull(byteArray28);
        org.junit.Assert.assertArrayEquals(byteArray28, new byte[] { (byte) 100, (byte) 1 });
        org.junit.Assert.assertTrue("'" + boolean34 + "' != '" + true + "'", boolean34 == true);
        org.junit.Assert.assertTrue("'" + inclusion35 + "' != '" + tools.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_NON_NULL + "'", inclusion35.equals(tools.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_NON_NULL));
        org.junit.Assert.assertNotNull(doubleArray45);
        org.junit.Assert.assertArrayEquals(doubleArray45, new double[] { 292.0d }, 1.0E-15);
    }

    @Test
    public void test1504() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1504");
        tools.jackson.core.JsonPointer jsonPointer0 = tools.jackson.core.JsonPointer.empty();
        tools.jackson.core.filter.JsonPointerBasedFilter jsonPointerBasedFilter1 = new tools.jackson.core.filter.JsonPointerBasedFilter(jsonPointer0);
        tools.jackson.core.filter.TokenFilter tokenFilter3 = jsonPointerBasedFilter1.includeElement(5);
        tools.jackson.core.filter.TokenFilter tokenFilter5 = jsonPointerBasedFilter1.includeElement(15);
        tools.jackson.core.filter.TokenFilter tokenFilter6 = jsonPointerBasedFilter1.filterStartArray();
        org.junit.Assert.assertNotNull(jsonPointer0);
        org.junit.Assert.assertNull(tokenFilter3);
        org.junit.Assert.assertNull(tokenFilter5);
        org.junit.Assert.assertNotNull(tokenFilter6);
    }

    @Test
    public void test1505() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1505");
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
            tools.jackson.core.JsonGenerator jsonGenerator12 = jsonGeneratorDelegate9.writeEmbeddedObject((java.lang.Object) "root");
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(minimalPrettyPrinter7);
    }

    @Test
    public void test1506() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1506");
        byte[] byteArray1 = tools.jackson.core.io.CharTypes.copyHexBytes(true);
        org.junit.Assert.assertNotNull(byteArray1);
        org.junit.Assert.assertArrayEquals(byteArray1, new byte[] { (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 65, (byte) 66, (byte) 67, (byte) 68, (byte) 69, (byte) 70 });
    }

    @Test
    public void test1507() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1507");
        tools.jackson.core.Base64Variant base64Variant0 = tools.jackson.core.Base64Variants.MODIFIED_FOR_URL;
        byte[] byteArray8 = new byte[] { (byte) 10, (byte) -1, (byte) -1, (byte) -1, (byte) -1 };
        int int10 = base64Variant0.encodeBase64Partial(10, (int) 'a', byteArray8, (int) (byte) 1);
        tools.jackson.core.Base64Variant base64Variant13 = new tools.jackson.core.Base64Variant(base64Variant0, "hi!", 0);
        tools.jackson.core.Base64Variant base64Variant14 = base64Variant0.withPaddingAllowed();
        tools.jackson.core.Base64Variant.PaddingReadBehaviour paddingReadBehaviour15 = base64Variant0.paddingReadBehaviour();
        org.junit.Assert.assertNotNull(base64Variant0);
        org.junit.Assert.assertNotNull(byteArray8);
        org.junit.Assert.assertArrayEquals(byteArray8, new byte[] { (byte) 10, (byte) 65, (byte) 65, (byte) -1, (byte) -1 });
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 3 + "'", int10 == 3);
        org.junit.Assert.assertNotNull(base64Variant14);
        org.junit.Assert.assertTrue("'" + paddingReadBehaviour15 + "' != '" + tools.jackson.core.Base64Variant.PaddingReadBehaviour.PADDING_FORBIDDEN + "'", paddingReadBehaviour15.equals(tools.jackson.core.Base64Variant.PaddingReadBehaviour.PADDING_FORBIDDEN));
    }

    @Test
    public void test1508() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1508");
        tools.jackson.core.io.ContentReference contentReference0 = tools.jackson.core.io.ContentReference.redacted();
        tools.jackson.core.TokenStreamLocation tokenStreamLocation4 = new tools.jackson.core.TokenStreamLocation(contentReference0, (long) (short) 10, 33, 33);
        java.lang.Object obj5 = contentReference0.getRawContent();
        java.lang.String str6 = contentReference0.buildSourceDescription();
        tools.jackson.core.TokenStreamLocation tokenStreamLocation10 = new tools.jackson.core.TokenStreamLocation(contentReference0, (long) (-44), 39, 1924645298);
        org.junit.Assert.assertNotNull(contentReference0);
        org.junit.Assert.assertNull(obj5);
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "REDACTED (`StreamReadFeature.INCLUDE_SOURCE_IN_LOCATION` disabled)" + "'", str6, "REDACTED (`StreamReadFeature.INCLUDE_SOURCE_IN_LOCATION` disabled)");
    }

    @Test
    public void test1509() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1509");
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
        tools.jackson.core.filter.TokenFilter tokenFilter35 = null;
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext36 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter35);
        tools.jackson.core.JsonToken jsonToken37 = tokenFilterContext36.nextTokenToRead();
        tools.jackson.core.filter.TokenFilter tokenFilter38 = tools.jackson.core.filter.TokenFilter.INCLUDE_ALL;
        tools.jackson.core.filter.TokenFilter tokenFilter39 = tokenFilterContext36.checkValue(tokenFilter38);
        java.math.BigDecimal bigDecimal40 = null;
        boolean boolean41 = tokenFilter38.includeNumber(bigDecimal40);
        tools.jackson.core.filter.TokenFilter tokenFilter43 = tokenFilter38.includeRootValue((int) (byte) 1);
        tools.jackson.core.filter.TokenFilter tokenFilter45 = tokenFilter38.includeElement(4);
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion46 = tools.jackson.core.filter.TokenFilter.Inclusion.ONLY_INCLUDE_ALL;
        tools.jackson.core.filter.FilteringGeneratorDelegate filteringGeneratorDelegate48 = new tools.jackson.core.filter.FilteringGeneratorDelegate((tools.jackson.core.JsonGenerator) filteringGeneratorDelegate34, tokenFilter45, inclusion46, false);
        tools.jackson.core.TokenStreamContext tokenStreamContext49 = filteringGeneratorDelegate34.streamWriteContext();
        tools.jackson.core.io.SerializedString serializedString51 = new tools.jackson.core.io.SerializedString("0.0");
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.JsonGenerator jsonGenerator52 = filteringGeneratorDelegate34.writeString((tools.jackson.core.SerializableString) serializedString51);
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
        org.junit.Assert.assertNotNull(tokenFilterContext36);
        org.junit.Assert.assertNull(jsonToken37);
        org.junit.Assert.assertNotNull(tokenFilter38);
        org.junit.Assert.assertNotNull(tokenFilter39);
        org.junit.Assert.assertTrue("'" + boolean41 + "' != '" + true + "'", boolean41 == true);
        org.junit.Assert.assertNotNull(tokenFilter43);
        org.junit.Assert.assertNotNull(tokenFilter45);
        org.junit.Assert.assertTrue("'" + inclusion46 + "' != '" + tools.jackson.core.filter.TokenFilter.Inclusion.ONLY_INCLUDE_ALL + "'", inclusion46.equals(tools.jackson.core.filter.TokenFilter.Inclusion.ONLY_INCLUDE_ALL));
        org.junit.Assert.assertNotNull(tokenStreamContext49);
    }

    @Test
    public void test1510() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1510");
        tools.jackson.core.json.JsonFactory jsonFactory0 = new tools.jackson.core.json.JsonFactory();
        int int1 = jsonFactory0.getFormatReadFeatures();
        tools.jackson.core.ObjectWriteContext objectWriteContext2 = tools.jackson.core.TokenStreamFactory.EMPTY_WRITE_CONTEXT;
        tools.jackson.core.util.BufferRecycler bufferRecycler3 = new tools.jackson.core.util.BufferRecycler();
        tools.jackson.core.io.SegmentedStringWriter segmentedStringWriter4 = new tools.jackson.core.io.SegmentedStringWriter(bufferRecycler3);
        segmentedStringWriter4.write("MODIFIED-FOR-URL/UNKNOWN/0.0.0");
        java.io.Writer writer8 = segmentedStringWriter4.append('a');
        tools.jackson.core.JsonGenerator jsonGenerator9 = jsonFactory0.createGenerator(objectWriteContext2, (java.io.Writer) segmentedStringWriter4);
        tools.jackson.core.JsonParser jsonParser11 = jsonFactory0.createParser("tools.jackson.core.exc.StreamWriteException: hi!\n at [No location information] (through reference chain: java.lang.Boolean[\"hi!\"])");
        boolean boolean12 = jsonFactory0.canUseCharArrays();
        boolean boolean13 = jsonFactory0.canUseCharArrays();
        tools.jackson.core.Base64Variant base64Variant14 = tools.jackson.core.Base64Variants.MODIFIED_FOR_URL;
        boolean boolean16 = base64Variant14.usesPaddingChar(33);
        tools.jackson.core.Base64Variant base64Variant17 = tools.jackson.core.Base64Variants.MODIFIED_FOR_URL;
        byte[] byteArray25 = new byte[] { (byte) 10, (byte) -1, (byte) -1, (byte) -1, (byte) -1 };
        int int27 = base64Variant17.encodeBase64Partial(10, (int) 'a', byteArray25, (int) (byte) 1);
        tools.jackson.core.Base64Variant base64Variant30 = new tools.jackson.core.Base64Variant(base64Variant17, "hi!", 0);
        byte[] byteArray34 = new byte[] { (byte) -1, (byte) 100, (byte) 10 };
        java.lang.String str36 = base64Variant30.encode(byteArray34, true);
        tools.jackson.core.Base64Variant.PaddingReadBehaviour paddingReadBehaviour37 = base64Variant30.paddingReadBehaviour();
        tools.jackson.core.Base64Variant base64Variant40 = new tools.jackson.core.Base64Variant(base64Variant30, "2.0", (-44));
        tools.jackson.core.util.BufferRecycler bufferRecycler42 = null;
        tools.jackson.core.util.ByteArrayBuilder byteArrayBuilder43 = new tools.jackson.core.util.ByteArrayBuilder(bufferRecycler42);
        byte[] byteArray45 = byteArrayBuilder43.completeAndCoalesce(10);
        byte[] byteArray46 = byteArrayBuilder43.toByteArray();
        byteArrayBuilder43.close();
        int int48 = byteArrayBuilder43.size();
        tools.jackson.core.util.BufferRecycler bufferRecycler49 = byteArrayBuilder43.bufferRecycler();
        byteArrayBuilder43.appendThreeBytes((-1074));
        byteArrayBuilder43.appendFourBytes(1000);
        byteArrayBuilder43.flush();
        base64Variant30.decode("CkFB__8", byteArrayBuilder43);
        byte[] byteArray56 = byteArrayBuilder43.getCurrentSegment();
        java.lang.String str57 = base64Variant14.encode(byteArray56);
        tools.jackson.core.JsonParser jsonParser58 = jsonFactory0.createParser(byteArray56);
        org.junit.Assert.assertTrue("'" + int1 + "' != '" + 0 + "'", int1 == 0);
        org.junit.Assert.assertNotNull(objectWriteContext2);
        org.junit.Assert.assertNotNull(writer8);
        org.junit.Assert.assertNotNull(jsonGenerator9);
        org.junit.Assert.assertNotNull(jsonParser11);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + true + "'", boolean12 == true);
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + true + "'", boolean13 == true);
        org.junit.Assert.assertNotNull(base64Variant14);
        org.junit.Assert.assertTrue("'" + boolean16 + "' != '" + false + "'", boolean16 == false);
        org.junit.Assert.assertNotNull(base64Variant17);
        org.junit.Assert.assertNotNull(byteArray25);
        org.junit.Assert.assertArrayEquals(byteArray25, new byte[] { (byte) 10, (byte) 65, (byte) 65, (byte) -1, (byte) -1 });
        org.junit.Assert.assertTrue("'" + int27 + "' != '" + 3 + "'", int27 == 3);
        org.junit.Assert.assertNotNull(byteArray34);
        org.junit.Assert.assertArrayEquals(byteArray34, new byte[] { (byte) -1, (byte) 100, (byte) 10 });
        org.junit.Assert.assertEquals("'" + str36 + "' != '" + "\"_2QK\\n\"" + "'", str36, "\"_2QK\\n\"");
        org.junit.Assert.assertTrue("'" + paddingReadBehaviour37 + "' != '" + tools.jackson.core.Base64Variant.PaddingReadBehaviour.PADDING_FORBIDDEN + "'", paddingReadBehaviour37.equals(tools.jackson.core.Base64Variant.PaddingReadBehaviour.PADDING_FORBIDDEN));
        org.junit.Assert.assertNotNull(byteArray45);
        org.junit.Assert.assertArrayEquals(byteArray45, new byte[] { (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0 });
        org.junit.Assert.assertNotNull(byteArray46);
        org.junit.Assert.assertArrayEquals(byteArray46, new byte[] { (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0 });
        org.junit.Assert.assertTrue("'" + int48 + "' != '" + 10 + "'", int48 == 10);
        org.junit.Assert.assertNull(bufferRecycler49);
        org.junit.Assert.assertNotNull(byteArray56);
        org.junit.Assert.assertEquals("'" + str57 + "' != '" + "AAAAAAAAAAAAAP_7zgAAA-gKQUH__wAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA" + "'", str57, "AAAAAAAAAAAAAP_7zgAAA-gKQUH__wAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        org.junit.Assert.assertNotNull(jsonParser58);
    }

    @Test
    public void test1511() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1511");
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder0 = tools.jackson.core.json.JsonFactory.builder();
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder2 = jsonFactoryBuilder0.highestNonEscapedChar((-324));
        tools.jackson.core.util.JsonpCharacterEscapes jsonpCharacterEscapes3 = tools.jackson.core.util.JsonpCharacterEscapes.instance();
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder4 = jsonFactoryBuilder2.characterEscapes((tools.jackson.core.io.CharacterEscapes) jsonpCharacterEscapes3);
        char char5 = jsonFactoryBuilder2.quoteChar();
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder7 = jsonFactoryBuilder2.rootValueSeparator("  ");
        tools.jackson.core.TokenStreamFactory.Feature feature8 = null;
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder9 = jsonFactoryBuilder2.disable(feature8);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(jsonFactoryBuilder0);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder2);
        org.junit.Assert.assertNotNull(jsonpCharacterEscapes3);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder4);
        org.junit.Assert.assertTrue("'" + char5 + "' != '" + '\"' + "'", char5 == '\"');
        org.junit.Assert.assertNotNull(jsonFactoryBuilder7);
    }

    @Test
    public void test1512() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1512");
        tools.jackson.core.json.DupDetector dupDetector0 = null;
        tools.jackson.core.json.JsonWriteContext jsonWriteContext1 = tools.jackson.core.json.JsonWriteContext.createRootContext(dupDetector0);
        boolean boolean2 = jsonWriteContext1.hasCurrentName();
        tools.jackson.core.JsonGenerator jsonGenerator5 = null;
        tools.jackson.core.json.DupDetector dupDetector6 = tools.jackson.core.json.DupDetector.rootDetector(jsonGenerator5);
        tools.jackson.core.json.JsonReadContext jsonReadContext7 = tools.jackson.core.json.JsonReadContext.createRootContext(56, 7, dupDetector6);
        tools.jackson.core.json.JsonWriteContext jsonWriteContext8 = jsonWriteContext1.withDupDetector(dupDetector6);
        int int9 = jsonWriteContext1.getNestingDepth();
        org.junit.Assert.assertNotNull(jsonWriteContext1);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(dupDetector6);
        org.junit.Assert.assertNotNull(jsonReadContext7);
        org.junit.Assert.assertNotNull(jsonWriteContext8);
        org.junit.Assert.assertTrue("'" + int9 + "' != '" + 0 + "'", int9 == 0);
    }

    @Test
    public void test1513() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1513");
        tools.jackson.core.TokenStreamFactory tokenStreamFactory0 = null;
        tools.jackson.core.sym.CharsToNameCanonicalizer charsToNameCanonicalizer1 = tools.jackson.core.sym.CharsToNameCanonicalizer.createRoot(tokenStreamFactory0);
        charsToNameCanonicalizer1.release();
        tools.jackson.core.json.JsonFactory jsonFactory3 = new tools.jackson.core.json.JsonFactory();
        int int4 = jsonFactory3.getFormatReadFeatures();
        tools.jackson.core.ObjectWriteContext objectWriteContext5 = tools.jackson.core.TokenStreamFactory.EMPTY_WRITE_CONTEXT;
        tools.jackson.core.util.BufferRecycler bufferRecycler6 = new tools.jackson.core.util.BufferRecycler();
        tools.jackson.core.io.SegmentedStringWriter segmentedStringWriter7 = new tools.jackson.core.io.SegmentedStringWriter(bufferRecycler6);
        segmentedStringWriter7.write("MODIFIED-FOR-URL/UNKNOWN/0.0.0");
        java.io.Writer writer11 = segmentedStringWriter7.append('a');
        tools.jackson.core.JsonGenerator jsonGenerator12 = jsonFactory3.createGenerator(objectWriteContext5, (java.io.Writer) segmentedStringWriter7);
        tools.jackson.core.util.BufferRecycler bufferRecycler13 = null;
        tools.jackson.core.util.TextBuffer textBuffer14 = new tools.jackson.core.util.TextBuffer(bufferRecycler13);
        tools.jackson.core.util.BufferRecycler bufferRecycler15 = null;
        tools.jackson.core.util.TextBuffer textBuffer16 = new tools.jackson.core.util.TextBuffer(bufferRecycler15);
        textBuffer16.resetWithString("");
        int int19 = textBuffer16.getCurrentSegmentSize();
        char[] charArray20 = textBuffer16.contentsAsArray();
        char[] charArray21 = textBuffer16.contentsAsArray();
        textBuffer14.resetWithShared(charArray21, (int) ',', 32000);
        tools.jackson.core.JsonParser jsonParser25 = jsonFactory3.createParser(charArray21);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str29 = charsToNameCanonicalizer1.findSymbol(charArray21, 100, 4, 8);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(charsToNameCanonicalizer1);
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 0 + "'", int4 == 0);
        org.junit.Assert.assertNotNull(objectWriteContext5);
        org.junit.Assert.assertNotNull(writer11);
        org.junit.Assert.assertNotNull(jsonGenerator12);
        org.junit.Assert.assertTrue("'" + int19 + "' != '" + 0 + "'", int19 == 0);
        org.junit.Assert.assertNotNull(charArray20);
        org.junit.Assert.assertArrayEquals(charArray20, new char[] {});
        org.junit.Assert.assertNotNull(charArray21);
        org.junit.Assert.assertArrayEquals(charArray21, new char[] {});
        org.junit.Assert.assertNotNull(jsonParser25);
    }

    @Test
    public void test1514() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1514");
        tools.jackson.core.sym.ByteQuadsCanonicalizer byteQuadsCanonicalizer0 = tools.jackson.core.sym.ByteQuadsCanonicalizer.createRoot();
        byteQuadsCanonicalizer0.release();
        int int2 = byteQuadsCanonicalizer0.bucketCount();
        int int3 = byteQuadsCanonicalizer0.bucketCount();
        tools.jackson.core.sym.ByteQuadsCanonicalizer byteQuadsCanonicalizer5 = byteQuadsCanonicalizer0.makeChild(353);
        java.lang.String str6 = byteQuadsCanonicalizer5.toString();
        org.junit.Assert.assertNotNull(byteQuadsCanonicalizer0);
        org.junit.Assert.assertTrue("'" + int2 + "' != '" + 0 + "'", int2 == 0);
        org.junit.Assert.assertTrue("'" + int3 + "' != '" + 0 + "'", int3 == 0);
        org.junit.Assert.assertNotNull(byteQuadsCanonicalizer5);
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "[tools.jackson.core.sym.ByteQuadsCanonicalizer: size=0, hashSize=64, 0/0/0/0 pri/sec/ter/spill (=0), total:0]" + "'", str6, "[tools.jackson.core.sym.ByteQuadsCanonicalizer: size=0, hashSize=64, 0/0/0/0 pri/sec/ter/spill (=0), total:0]");
    }

    @Test
    public void test1515() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1515");
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
        tools.jackson.core.filter.TokenFilter tokenFilter13 = tokenFilter3.includeProperty(" \000");
        org.junit.Assert.assertNotNull(tokenFilterContext1);
        org.junit.Assert.assertNull(jsonToken2);
        org.junit.Assert.assertNotNull(tokenFilter3);
        org.junit.Assert.assertNotNull(tokenFilter4);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + true + "'", boolean6 == true);
        org.junit.Assert.assertNotNull(tokenFilter8);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
        org.junit.Assert.assertNotNull(tokenFilterContext11);
        org.junit.Assert.assertNotNull(tokenFilter13);
    }

    @Test
    public void test1516() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1516");
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
        boolean boolean18 = filteringParserDelegate5.hasTokenId(8);
        tools.jackson.core.JsonParser jsonParser19 = filteringParserDelegate5.skipChildren();
        filteringParserDelegate5.clearCurrentToken();
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 0 + "'", int7 == 0);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + true + "'", boolean9 == true);
        org.junit.Assert.assertNotNull(dupDetector14);
        org.junit.Assert.assertNotNull(jsonReadContext15);
        org.junit.Assert.assertTrue("'" + boolean18 + "' != '" + false + "'", boolean18 == false);
        org.junit.Assert.assertNotNull(jsonParser19);
    }

    @Test
    public void test1517() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1517");
        tools.jackson.core.json.JsonFactory jsonFactory0 = new tools.jackson.core.json.JsonFactory();
        int int1 = jsonFactory0.getFormatReadFeatures();
        tools.jackson.core.json.JsonFactory jsonFactory2 = new tools.jackson.core.json.JsonFactory();
        int int3 = jsonFactory2.getFormatReadFeatures();
        tools.jackson.core.ObjectWriteContext objectWriteContext4 = tools.jackson.core.TokenStreamFactory.EMPTY_WRITE_CONTEXT;
        tools.jackson.core.util.BufferRecycler bufferRecycler5 = new tools.jackson.core.util.BufferRecycler();
        tools.jackson.core.io.SegmentedStringWriter segmentedStringWriter6 = new tools.jackson.core.io.SegmentedStringWriter(bufferRecycler5);
        segmentedStringWriter6.write("MODIFIED-FOR-URL/UNKNOWN/0.0.0");
        java.io.Writer writer10 = segmentedStringWriter6.append('a');
        tools.jackson.core.JsonGenerator jsonGenerator11 = jsonFactory2.createGenerator(objectWriteContext4, (java.io.Writer) segmentedStringWriter6);
        tools.jackson.core.util.BufferRecycler bufferRecycler12 = null;
        tools.jackson.core.util.ByteArrayBuilder byteArrayBuilder13 = new tools.jackson.core.util.ByteArrayBuilder(bufferRecycler12);
        byte[] byteArray15 = byteArrayBuilder13.completeAndCoalesce(10);
        byte[] byteArray16 = byteArrayBuilder13.toByteArray();
        tools.jackson.core.util.BufferRecycler bufferRecycler17 = byteArrayBuilder13.bufferRecycler();
        tools.jackson.core.JsonGenerator jsonGenerator18 = jsonFactory0.createGenerator(objectWriteContext4, (java.io.OutputStream) byteArrayBuilder13);
        int int19 = jsonFactory0.getStreamWriteFeatures();
        tools.jackson.core.io.InputDecorator inputDecorator20 = jsonFactory0.getInputDecorator();
        org.junit.Assert.assertTrue("'" + int1 + "' != '" + 0 + "'", int1 == 0);
        org.junit.Assert.assertTrue("'" + int3 + "' != '" + 0 + "'", int3 == 0);
        org.junit.Assert.assertNotNull(objectWriteContext4);
        org.junit.Assert.assertNotNull(writer10);
        org.junit.Assert.assertNotNull(jsonGenerator11);
        org.junit.Assert.assertNotNull(byteArray15);
        org.junit.Assert.assertArrayEquals(byteArray15, new byte[] { (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0 });
        org.junit.Assert.assertNotNull(byteArray16);
        org.junit.Assert.assertArrayEquals(byteArray16, new byte[] { (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0 });
        org.junit.Assert.assertNull(bufferRecycler17);
        org.junit.Assert.assertNotNull(jsonGenerator18);
        org.junit.Assert.assertTrue("'" + int19 + "' != '" + 7 + "'", int19 == 7);
        org.junit.Assert.assertNull(inputDecorator20);
    }

    @Test
    public void test1518() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1518");
        tools.jackson.core.exc.StreamReadException streamReadException1 = new tools.jackson.core.exc.StreamReadException("");
        java.lang.String str2 = streamReadException1.getPathReference();
        tools.jackson.core.exc.StreamReadException streamReadException4 = new tools.jackson.core.exc.StreamReadException("");
        streamReadException1.addSuppressed((java.lang.Throwable) streamReadException4);
        tools.jackson.core.JsonToken jsonToken7 = null;
        tools.jackson.core.type.WritableTypeId writableTypeId9 = new tools.jackson.core.type.WritableTypeId((java.lang.Object) '#', jsonToken7, (java.lang.Object) ' ');
        writableTypeId9.asProperty = "hi!";
        tools.jackson.core.JsonToken jsonToken13 = null;
        tools.jackson.core.type.WritableTypeId writableTypeId15 = new tools.jackson.core.type.WritableTypeId((java.lang.Object) '#', jsonToken13, (java.lang.Object) ' ');
        tools.jackson.core.type.WritableTypeId.Inclusion inclusion16 = tools.jackson.core.type.WritableTypeId.Inclusion.PAYLOAD_PROPERTY;
        writableTypeId15.include = inclusion16;
        writableTypeId9.include = inclusion16;
        tools.jackson.core.JacksonException jacksonException20 = streamReadException4.prependPath((java.lang.Object) inclusion16, "java.lang.String[?]");
        org.junit.Assert.assertEquals("'" + str2 + "' != '" + "" + "'", str2, "");
        org.junit.Assert.assertTrue("'" + inclusion16 + "' != '" + tools.jackson.core.type.WritableTypeId.Inclusion.PAYLOAD_PROPERTY + "'", inclusion16.equals(tools.jackson.core.type.WritableTypeId.Inclusion.PAYLOAD_PROPERTY));
        org.junit.Assert.assertNotNull(jacksonException20);
    }

    @Test
    public void test1519() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1519");
        tools.jackson.core.filter.TokenFilter tokenFilter0 = tools.jackson.core.filter.TokenFilter.INCLUDE_ALL;
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext1 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter0);
        boolean boolean3 = tokenFilter0.includeBoolean(false);
        org.junit.Assert.assertNotNull(tokenFilter0);
        org.junit.Assert.assertNotNull(tokenFilterContext1);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + true + "'", boolean3 == true);
    }

    @Test
    public void test1520() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1520");
        tools.jackson.core.Base64Variant base64Variant0 = tools.jackson.core.Base64Variants.MODIFIED_FOR_URL;
        byte[] byteArray8 = new byte[] { (byte) 10, (byte) -1, (byte) -1, (byte) -1, (byte) -1 };
        int int10 = base64Variant0.encodeBase64Partial(10, (int) 'a', byteArray8, (int) (byte) 1);
        tools.jackson.core.Base64Variant base64Variant13 = new tools.jackson.core.Base64Variant(base64Variant0, "hi!", 0);
        boolean boolean14 = base64Variant0.requiresPaddingOnRead();
        int int15 = base64Variant0.getMaxLineLength();
        org.junit.Assert.assertNotNull(base64Variant0);
        org.junit.Assert.assertNotNull(byteArray8);
        org.junit.Assert.assertArrayEquals(byteArray8, new byte[] { (byte) 10, (byte) 65, (byte) 65, (byte) -1, (byte) -1 });
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 3 + "'", int10 == 3);
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + false + "'", boolean14 == false);
        org.junit.Assert.assertTrue("'" + int15 + "' != '" + 2147483647 + "'", int15 == 2147483647);
    }

    @Test
    public void test1521() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1521");
        char[] charArray5 = new char[] { ',', '4', '4', 'a', '/' };
        tools.jackson.core.util.TextBuffer textBuffer6 = tools.jackson.core.util.TextBuffer.fromInitial(charArray5);
        org.junit.Assert.assertNotNull(charArray5);
        org.junit.Assert.assertArrayEquals(charArray5, new char[] { ',', '4', '4', 'a', '/' });
        org.junit.Assert.assertNotNull(textBuffer6);
    }

    @Test
    public void test1522() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1522");
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
        java.lang.String str20 = filteringParserDelegate15.currentName();
        tools.jackson.core.JsonToken jsonToken21 = filteringParserDelegate15.getLastClearedToken();
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
        org.junit.Assert.assertNull(str20);
        org.junit.Assert.assertNull(jsonToken21);
    }

    @Test
    public void test1523() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1523");
        int int1 = tools.jackson.core.io.schubfach.MathUtils.flog2pow10(166096);
        org.junit.Assert.assertTrue("'" + int1 + "' != '" + 551758 + "'", int1 == 551758);
    }

    @Test
    public void test1524() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1524");
        tools.jackson.core.json.DupDetector dupDetector2 = null;
        tools.jackson.core.json.JsonReadContext jsonReadContext3 = tools.jackson.core.json.JsonReadContext.createRootContext(1, 0, dupDetector2);
        boolean boolean4 = jsonReadContext3.expectComma();
        tools.jackson.core.json.JsonReadContext jsonReadContext7 = jsonReadContext3.createChildObjectContext(3, (int) (short) 10);
        tools.jackson.core.json.JsonReadContext jsonReadContext8 = jsonReadContext7.getParent();
        org.junit.Assert.assertNotNull(jsonReadContext3);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertNotNull(jsonReadContext7);
        org.junit.Assert.assertNotNull(jsonReadContext8);
    }

    @Test
    public void test1525() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1525");
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder0 = tools.jackson.core.json.JsonFactory.builder();
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder2 = jsonFactoryBuilder0.highestNonEscapedChar((-324));
        tools.jackson.core.StreamWriteConstraints streamWriteConstraints3 = tools.jackson.core.StreamWriteConstraints.defaults();
        tools.jackson.core.StreamWriteConstraints.overrideDefaultStreamWriteConstraints(streamWriteConstraints3);
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder5 = jsonFactoryBuilder0.streamWriteConstraints(streamWriteConstraints3);
        tools.jackson.core.io.InputDecorator inputDecorator6 = null;
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder7 = jsonFactoryBuilder0.inputDecorator(inputDecorator6);
        tools.jackson.core.StreamWriteFeature streamWriteFeature8 = null;
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder10 = jsonFactoryBuilder7.configure(streamWriteFeature8, true);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(jsonFactoryBuilder0);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder2);
        org.junit.Assert.assertNotNull(streamWriteConstraints3);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder5);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder7);
    }

    @Test
    public void test1526() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1526");
        tools.jackson.core.JsonPointer jsonPointer0 = tools.jackson.core.JsonPointer.empty();
        boolean boolean1 = jsonPointer0.mayMatchElement();
        tools.jackson.core.JsonPointer jsonPointer2 = tools.jackson.core.JsonPointer.empty();
        boolean boolean3 = jsonPointer2.mayMatchElement();
        tools.jackson.core.JsonPointer jsonPointer4 = jsonPointer0.append(jsonPointer2);
        int int5 = jsonPointer0.getMatchingIndex();
        tools.jackson.core.JsonPointer jsonPointer6 = jsonPointer0.head();
        org.junit.Assert.assertNotNull(jsonPointer0);
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
        org.junit.Assert.assertNotNull(jsonPointer2);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
        org.junit.Assert.assertNotNull(jsonPointer4);
        org.junit.Assert.assertTrue("'" + int5 + "' != '" + (-1) + "'", int5 == (-1));
        org.junit.Assert.assertNull(jsonPointer6);
    }

    @Test
    public void test1527() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1527");
        tools.jackson.core.util.BufferRecycler bufferRecycler0 = null;
        tools.jackson.core.util.TextBuffer textBuffer1 = new tools.jackson.core.util.TextBuffer(bufferRecycler0);
        textBuffer1.resetWithString("");
        int int4 = textBuffer1.getCurrentSegmentSize();
        tools.jackson.core.util.BufferRecycler bufferRecycler5 = textBuffer1.bufferRecycler();
        java.lang.String str6 = textBuffer1.contentsAsString();
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 0 + "'", int4 == 0);
        org.junit.Assert.assertNull(bufferRecycler5);
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "" + "'", str6, "");
    }

    @Test
    public void test1528() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1528");
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
            tools.jackson.core.JsonGenerator jsonGenerator15 = jsonGeneratorDelegate9.writeRawValue("100.0", 1463973900, 4);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(minimalPrettyPrinter7);
    }

    @Test
    public void test1529() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1529");
        tools.jackson.core.StreamReadConstraints streamReadConstraints0 = tools.jackson.core.StreamReadConstraints.defaults();
        streamReadConstraints0.validateStringLength((int) (short) 0);
        streamReadConstraints0.validateNestingDepth((-207530097));
        org.junit.Assert.assertNotNull(streamReadConstraints0);
    }

    @Test
    public void test1530() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1530");
        tools.jackson.core.JsonPointer jsonPointer0 = tools.jackson.core.JsonPointer.empty();
        tools.jackson.core.JsonToken jsonToken2 = null;
        tools.jackson.core.type.WritableTypeId writableTypeId4 = new tools.jackson.core.type.WritableTypeId((java.lang.Object) '#', jsonToken2, (java.lang.Object) ' ');
        tools.jackson.core.JsonToken jsonToken5 = writableTypeId4.valueShape;
        boolean boolean6 = jsonPointer0.equals((java.lang.Object) writableTypeId4);
        java.lang.Object obj7 = writableTypeId4.forValue;
        java.lang.Class<?> wildcardClass8 = writableTypeId4.forValueType;
        java.lang.Object obj9 = writableTypeId4.extra;
        writableTypeId4.wrapperWritten = true;
        org.junit.Assert.assertNotNull(jsonPointer0);
        org.junit.Assert.assertNull(jsonToken5);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertEquals("'" + obj7 + "' != '" + '#' + "'", obj7, '#');
        org.junit.Assert.assertNull(wildcardClass8);
        org.junit.Assert.assertNull(obj9);
    }

    @Test
    public void test1531() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1531");
        java.io.DataOutput dataOutput0 = null;
        tools.jackson.core.io.DataOutputAsStream dataOutputAsStream1 = new tools.jackson.core.io.DataOutputAsStream(dataOutput0);
        // The following exception was thrown during execution in test generation
        try {
            dataOutputAsStream1.write(1924645298);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test1532() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1532");
        tools.jackson.core.util.BufferRecycler bufferRecycler0 = null;
        tools.jackson.core.util.TextBuffer textBuffer1 = new tools.jackson.core.util.TextBuffer(bufferRecycler0);
        textBuffer1.resetWithString("");
        int int4 = textBuffer1.getCurrentSegmentSize();
        char[] charArray5 = textBuffer1.contentsAsArray();
        tools.jackson.core.util.BufferRecycler bufferRecycler6 = new tools.jackson.core.util.BufferRecycler();
        tools.jackson.core.io.SegmentedStringWriter segmentedStringWriter7 = new tools.jackson.core.io.SegmentedStringWriter(bufferRecycler6);
        segmentedStringWriter7.write("MODIFIED-FOR-URL/UNKNOWN/0.0.0");
        java.io.Writer writer11 = segmentedStringWriter7.append('a');
        java.io.Writer writer13 = segmentedStringWriter7.append((java.lang.CharSequence) "Unexpected end of base64-encoded String: base64 variant 'MODIFIED-FOR-URL' expects padding (one or more '\000' characters) at the end. This Base64Variant might have been incorrectly configured");
        int int14 = textBuffer1.contentsToWriter((java.io.Writer) segmentedStringWriter7);
        // The following exception was thrown during execution in test generation
        try {
            char[] charArray15 = textBuffer1.expandCurrentSegment();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 0 + "'", int4 == 0);
        org.junit.Assert.assertNotNull(charArray5);
        org.junit.Assert.assertArrayEquals(charArray5, new char[] {});
        org.junit.Assert.assertNotNull(writer11);
        org.junit.Assert.assertNotNull(writer13);
        org.junit.Assert.assertTrue("'" + int14 + "' != '" + 0 + "'", int14 == 0);
    }

    @Test
    public void test1533() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1533");
        tools.jackson.core.filter.TokenFilter tokenFilter0 = null;
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext1 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter0);
        tools.jackson.core.JsonGenerator jsonGenerator2 = null;
        tokenFilterContext1.writePath(jsonGenerator2);
        tokenFilterContext1.skipParentChecks();
        tools.jackson.core.filter.TokenFilter tokenFilter6 = tokenFilterContext1.setPropertyName("AAAAAAAAAAAAAA");
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter7 = new tools.jackson.core.util.MinimalPrettyPrinter();
        tools.jackson.core.JsonGenerator jsonGenerator8 = null;
        minimalPrettyPrinter7.beforeObjectEntries(jsonGenerator8);
        tools.jackson.core.util.Separators separators13 = new tools.jackson.core.util.Separators('~', ' ', ' ');
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter14 = minimalPrettyPrinter7.setSeparators(separators13);
        tools.jackson.core.JsonGenerator jsonGenerator15 = null;
        tools.jackson.core.util.JsonGeneratorDelegate jsonGeneratorDelegate16 = new tools.jackson.core.util.JsonGeneratorDelegate(jsonGenerator15);
        minimalPrettyPrinter7.beforeObjectEntries((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate16);
        tokenFilterContext1.writePath((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate16);
        double[] doubleArray19 = null;
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.JsonGenerator jsonGenerator22 = jsonGeneratorDelegate16.writeArray(doubleArray19, 65536, (int) (short) 10);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(tokenFilterContext1);
        org.junit.Assert.assertNull(tokenFilter6);
        org.junit.Assert.assertNotNull(minimalPrettyPrinter14);
    }

    @Test
    public void test1534() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1534");
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
        tools.jackson.core.filter.TokenFilter tokenFilter35 = null;
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext36 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter35);
        tools.jackson.core.JsonToken jsonToken37 = tokenFilterContext36.nextTokenToRead();
        tools.jackson.core.filter.TokenFilter tokenFilter38 = tools.jackson.core.filter.TokenFilter.INCLUDE_ALL;
        tools.jackson.core.filter.TokenFilter tokenFilter39 = tokenFilterContext36.checkValue(tokenFilter38);
        java.math.BigDecimal bigDecimal40 = null;
        boolean boolean41 = tokenFilter38.includeNumber(bigDecimal40);
        tools.jackson.core.filter.TokenFilter tokenFilter43 = tokenFilter38.includeRootValue((int) (byte) 1);
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion44 = tools.jackson.core.filter.TokenFilter.Inclusion.ONLY_INCLUDE_ALL;
        tools.jackson.core.filter.FilteringGeneratorDelegate filteringGeneratorDelegate46 = new tools.jackson.core.filter.FilteringGeneratorDelegate((tools.jackson.core.JsonGenerator) filteringGeneratorDelegate34, tokenFilter38, inclusion44, false);
        tools.jackson.core.util.JsonGeneratorDelegate jsonGeneratorDelegate48 = new tools.jackson.core.util.JsonGeneratorDelegate((tools.jackson.core.JsonGenerator) filteringGeneratorDelegate46, false);
        tools.jackson.core.io.SerializedString serializedString50 = new tools.jackson.core.io.SerializedString("0.0");
        char[] charArray51 = serializedString50.asQuotedChars();
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.JsonGenerator jsonGenerator54 = filteringGeneratorDelegate46.writeString(charArray51, 1924645298, 100000000);
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
        org.junit.Assert.assertNotNull(tokenFilterContext36);
        org.junit.Assert.assertNull(jsonToken37);
        org.junit.Assert.assertNotNull(tokenFilter38);
        org.junit.Assert.assertNotNull(tokenFilter39);
        org.junit.Assert.assertTrue("'" + boolean41 + "' != '" + true + "'", boolean41 == true);
        org.junit.Assert.assertNotNull(tokenFilter43);
        org.junit.Assert.assertTrue("'" + inclusion44 + "' != '" + tools.jackson.core.filter.TokenFilter.Inclusion.ONLY_INCLUDE_ALL + "'", inclusion44.equals(tools.jackson.core.filter.TokenFilter.Inclusion.ONLY_INCLUDE_ALL));
        org.junit.Assert.assertNotNull(charArray51);
        org.junit.Assert.assertArrayEquals(charArray51, new char[] { '0', '.', '0' });
    }

    @Test
    public void test1535() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1535");
        tools.jackson.core.Base64Variant base64Variant0 = tools.jackson.core.Base64Variants.MODIFIED_FOR_URL;
        byte[] byteArray8 = new byte[] { (byte) 10, (byte) -1, (byte) -1, (byte) -1, (byte) -1 };
        int int10 = base64Variant0.encodeBase64Partial(10, (int) 'a', byteArray8, (int) (byte) 1);
        tools.jackson.core.Base64Variant base64Variant13 = new tools.jackson.core.Base64Variant(base64Variant0, "hi!", 0);
        byte[] byteArray17 = new byte[] { (byte) -1, (byte) 100, (byte) 10 };
        java.lang.String str19 = base64Variant13.encode(byteArray17, true);
        tools.jackson.core.Base64Variant.PaddingReadBehaviour paddingReadBehaviour20 = base64Variant13.paddingReadBehaviour();
        tools.jackson.core.Base64Variant base64Variant23 = new tools.jackson.core.Base64Variant(base64Variant13, "2.0", (-44));
        tools.jackson.core.util.BufferRecycler bufferRecycler25 = null;
        tools.jackson.core.util.ByteArrayBuilder byteArrayBuilder26 = new tools.jackson.core.util.ByteArrayBuilder(bufferRecycler25);
        byte[] byteArray28 = byteArrayBuilder26.completeAndCoalesce(10);
        byte[] byteArray29 = byteArrayBuilder26.toByteArray();
        byteArrayBuilder26.close();
        int int31 = byteArrayBuilder26.size();
        tools.jackson.core.util.BufferRecycler bufferRecycler32 = byteArrayBuilder26.bufferRecycler();
        byteArrayBuilder26.appendThreeBytes((-1074));
        byteArrayBuilder26.appendFourBytes(1000);
        byteArrayBuilder26.flush();
        base64Variant13.decode("CkFB__8", byteArrayBuilder26);
        byteArrayBuilder26.release();
        org.junit.Assert.assertNotNull(base64Variant0);
        org.junit.Assert.assertNotNull(byteArray8);
        org.junit.Assert.assertArrayEquals(byteArray8, new byte[] { (byte) 10, (byte) 65, (byte) 65, (byte) -1, (byte) -1 });
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 3 + "'", int10 == 3);
        org.junit.Assert.assertNotNull(byteArray17);
        org.junit.Assert.assertArrayEquals(byteArray17, new byte[] { (byte) -1, (byte) 100, (byte) 10 });
        org.junit.Assert.assertEquals("'" + str19 + "' != '" + "\"_2QK\\n\"" + "'", str19, "\"_2QK\\n\"");
        org.junit.Assert.assertTrue("'" + paddingReadBehaviour20 + "' != '" + tools.jackson.core.Base64Variant.PaddingReadBehaviour.PADDING_FORBIDDEN + "'", paddingReadBehaviour20.equals(tools.jackson.core.Base64Variant.PaddingReadBehaviour.PADDING_FORBIDDEN));
        org.junit.Assert.assertNotNull(byteArray28);
        org.junit.Assert.assertArrayEquals(byteArray28, new byte[] { (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0 });
        org.junit.Assert.assertNotNull(byteArray29);
        org.junit.Assert.assertArrayEquals(byteArray29, new byte[] { (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0 });
        org.junit.Assert.assertTrue("'" + int31 + "' != '" + 10 + "'", int31 == 10);
        org.junit.Assert.assertNull(bufferRecycler32);
    }

    @Test
    public void test1536() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1536");
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter0 = new tools.jackson.core.util.MinimalPrettyPrinter();
        tools.jackson.core.util.Separators separators1 = tools.jackson.core.util.Separators.createDefaultInstance();
        tools.jackson.core.util.Separators separators3 = separators1.withObjectNameValueSeparator('\000');
        tools.jackson.core.util.Separators separators5 = separators3.withObjectNameValueSeparator('\000');
        tools.jackson.core.util.Separators.Spacing spacing6 = null;
        tools.jackson.core.util.Separators separators7 = separators3.withObjectNameValueSpacing(spacing6);
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter8 = minimalPrettyPrinter0.setSeparators(separators7);
        org.junit.Assert.assertNotNull(separators1);
        org.junit.Assert.assertNotNull(separators3);
        org.junit.Assert.assertNotNull(separators5);
        org.junit.Assert.assertNotNull(separators7);
        org.junit.Assert.assertNotNull(minimalPrettyPrinter8);
    }

    @Test
    public void test1537() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1537");
        int int1 = tools.jackson.core.util.VersionUtil.parseVersionPart("10.0");
        org.junit.Assert.assertTrue("'" + int1 + "' != '" + 10 + "'", int1 == 10);
    }

    @Test
    public void test1538() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1538");
        tools.jackson.core.util.DefaultPrettyPrinter defaultPrettyPrinter0 = new tools.jackson.core.util.DefaultPrettyPrinter();
        tools.jackson.core.util.Separators separators1 = tools.jackson.core.util.Separators.createDefaultInstance();
        tools.jackson.core.util.Separators separators3 = separators1.withObjectNameValueSeparator('\000');
        tools.jackson.core.util.DefaultPrettyPrinter defaultPrettyPrinter4 = new tools.jackson.core.util.DefaultPrettyPrinter(defaultPrettyPrinter0, separators1);
        tools.jackson.core.util.Separators separators6 = separators1.withObjectEntrySeparator(',');
        tools.jackson.core.util.Separators.Spacing spacing7 = separators1.getObjectEntrySpacing();
        org.junit.Assert.assertNotNull(separators1);
        org.junit.Assert.assertNotNull(separators3);
        org.junit.Assert.assertNotNull(separators6);
        org.junit.Assert.assertTrue("'" + spacing7 + "' != '" + tools.jackson.core.util.Separators.Spacing.NONE + "'", spacing7.equals(tools.jackson.core.util.Separators.Spacing.NONE));
    }

    @Test
    public void test1539() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1539");
        tools.jackson.core.JsonPointer jsonPointer0 = tools.jackson.core.JsonPointer.empty();
        boolean boolean1 = jsonPointer0.mayMatchElement();
        tools.jackson.core.JsonPointer jsonPointer3 = jsonPointer0.appendProperty("tools.jackson.core.exc.StreamWriteException: hi!\n at [No location information]");
        boolean boolean4 = jsonPointer0.matches();
        boolean boolean6 = jsonPointer0.matchesElement((-57));
        boolean boolean7 = jsonPointer0.mayMatchElement();
        tools.jackson.core.JsonPointer jsonPointer8 = jsonPointer0.tail();
        org.junit.Assert.assertNotNull(jsonPointer0);
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
        org.junit.Assert.assertNotNull(jsonPointer3);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + true + "'", boolean4 == true);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertNull(jsonPointer8);
    }

    @Test
    public void test1540() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1540");
        tools.jackson.core.util.BufferRecycler bufferRecycler0 = null;
        tools.jackson.core.util.TextBuffer textBuffer1 = new tools.jackson.core.util.TextBuffer(bufferRecycler0);
        textBuffer1.resetWithString("");
        boolean boolean4 = textBuffer1.hasTextAsCharacters();
        boolean boolean5 = textBuffer1.hasTextAsCharacters();
        textBuffer1.resetWith('\000');
        tools.jackson.core.util.BufferRecycler bufferRecycler8 = textBuffer1.bufferRecycler();
        // The following exception was thrown during execution in test generation
        try {
            textBuffer1.resetWithCopy(" ", (-207395268), (-57));
            org.junit.Assert.fail("Expected exception of type java.lang.StringIndexOutOfBoundsException; message: String index out of range: -207395268");
        } catch (java.lang.StringIndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertNull(bufferRecycler8);
    }

    @Test
    public void test1541() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1541");
        tools.jackson.core.util.BufferRecycler bufferRecycler0 = null;
        tools.jackson.core.util.ByteArrayBuilder byteArrayBuilder1 = new tools.jackson.core.util.ByteArrayBuilder(bufferRecycler0);
        tools.jackson.core.util.BufferRecycler bufferRecycler2 = byteArrayBuilder1.bufferRecycler();
        byte[] byteArray3 = byteArrayBuilder1.getClearAndRelease();
        byteArrayBuilder1.appendTwoBytes(500);
        byte[] byteArray7 = byteArrayBuilder1.completeAndCoalesce((int) '\"');
        org.junit.Assert.assertNull(bufferRecycler2);
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertArrayEquals(byteArray3, new byte[] {});
        org.junit.Assert.assertNotNull(byteArray7);
    }

    @Test
    public void test1542() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1542");
        tools.jackson.core.StreamReadConstraints streamReadConstraints0 = tools.jackson.core.StreamReadConstraints.defaults();
        boolean boolean1 = streamReadConstraints0.hasMaxDocumentLength();
        int int2 = streamReadConstraints0.getMaxNumberLength();
        long long3 = streamReadConstraints0.getMaxDocumentLength();
        tools.jackson.core.StreamWriteConstraints.Builder builder4 = tools.jackson.core.StreamWriteConstraints.builder();
        tools.jackson.core.StreamWriteConstraints streamWriteConstraints5 = builder4.build();
        tools.jackson.core.json.JsonFactory jsonFactory6 = new tools.jackson.core.json.JsonFactory();
        tools.jackson.core.ErrorReportConfiguration errorReportConfiguration7 = jsonFactory6.errorReportConfiguration();
        tools.jackson.core.util.BufferRecycler bufferRecycler8 = new tools.jackson.core.util.BufferRecycler();
        tools.jackson.core.io.SegmentedStringWriter segmentedStringWriter9 = new tools.jackson.core.io.SegmentedStringWriter(bufferRecycler8);
        segmentedStringWriter9.write("MODIFIED-FOR-URL/UNKNOWN/0.0.0");
        tools.jackson.core.util.BufferRecycler bufferRecycler12 = segmentedStringWriter9.bufferRecycler();
        tools.jackson.core.io.ContentReference contentReference13 = tools.jackson.core.io.ContentReference.redacted();
        tools.jackson.core.TokenStreamLocation tokenStreamLocation17 = new tools.jackson.core.TokenStreamLocation(contentReference13, (long) (short) 10, 33, 33);
        java.lang.Object obj18 = contentReference13.getRawContent();
        tools.jackson.core.JsonEncoding jsonEncoding20 = null;
        tools.jackson.core.io.IOContext iOContext21 = new tools.jackson.core.io.IOContext(streamReadConstraints0, streamWriteConstraints5, errorReportConfiguration7, bufferRecycler12, contentReference13, false, jsonEncoding20);
        tools.jackson.core.StreamReadConstraints streamReadConstraints22 = iOContext21.streamReadConstraints();
        tools.jackson.core.StreamWriteConstraints streamWriteConstraints23 = iOContext21.streamWriteConstraints();
        tools.jackson.core.util.BufferRecycler bufferRecycler24 = null;
        tools.jackson.core.util.TextBuffer textBuffer25 = new tools.jackson.core.util.TextBuffer(bufferRecycler24);
        textBuffer25.resetWithString("");
        int int28 = textBuffer25.getCurrentSegmentSize();
        char[] charArray29 = textBuffer25.emptyAndGetCurrentSegment();
        char[] charArray31 = textBuffer25.expandCurrentSegment(0);
        long long34 = tools.jackson.core.io.NumberInput.parseLong19(charArray31, (int) (short) 100, true);
        // The following exception was thrown during execution in test generation
        try {
            iOContext21.releaseConcatBuffer(charArray31);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(streamReadConstraints0);
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
        org.junit.Assert.assertTrue("'" + int2 + "' != '" + 1000 + "'", int2 == 1000);
        org.junit.Assert.assertTrue("'" + long3 + "' != '" + (-1L) + "'", long3 == (-1L));
        org.junit.Assert.assertNotNull(builder4);
        org.junit.Assert.assertNotNull(streamWriteConstraints5);
        org.junit.Assert.assertNotNull(errorReportConfiguration7);
        org.junit.Assert.assertNotNull(bufferRecycler12);
        org.junit.Assert.assertNotNull(contentReference13);
        org.junit.Assert.assertNull(obj18);
        org.junit.Assert.assertNotNull(streamReadConstraints22);
        org.junit.Assert.assertNotNull(streamWriteConstraints23);
        org.junit.Assert.assertTrue("'" + int28 + "' != '" + 0 + "'", int28 == 0);
        org.junit.Assert.assertNotNull(charArray29);
        org.junit.Assert.assertNotNull(charArray31);
        org.junit.Assert.assertTrue("'" + long34 + "' != '" + (-2006898887795321520L) + "'", long34 == (-2006898887795321520L));
    }

    @Test
    public void test1543() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1543");
        tools.jackson.core.json.DupDetector dupDetector2 = null;
        tools.jackson.core.json.JsonReadContext jsonReadContext3 = tools.jackson.core.json.JsonReadContext.createRootContext(1, 0, dupDetector2);
        tools.jackson.core.io.ContentReference contentReference4 = tools.jackson.core.io.ContentReference.rawReference((java.lang.Object) dupDetector2);
        boolean boolean5 = contentReference4.hasTextualContent();
        org.junit.Assert.assertNotNull(jsonReadContext3);
        org.junit.Assert.assertNotNull(contentReference4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
    }

    @Test
    public void test1544() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1544");
        tools.jackson.core.json.JsonFactory jsonFactory0 = new tools.jackson.core.json.JsonFactory();
        tools.jackson.core.ErrorReportConfiguration errorReportConfiguration1 = jsonFactory0.errorReportConfiguration();
        tools.jackson.core.sym.CharsToNameCanonicalizer charsToNameCanonicalizer2 = tools.jackson.core.sym.CharsToNameCanonicalizer.createRoot((tools.jackson.core.TokenStreamFactory) jsonFactory0);
        tools.jackson.core.sym.CharsToNameCanonicalizer charsToNameCanonicalizer3 = charsToNameCanonicalizer2.makeChild();
        tools.jackson.core.util.BufferRecycler bufferRecycler4 = null;
        tools.jackson.core.util.TextBuffer textBuffer5 = new tools.jackson.core.util.TextBuffer(bufferRecycler4);
        textBuffer5.resetWithString("");
        int int8 = textBuffer5.getCurrentSegmentSize();
        char[] charArray9 = textBuffer5.emptyAndGetCurrentSegment();
        char[] charArray11 = textBuffer5.expandCurrentSegment(0);
        char[] charArray12 = textBuffer5.expandCurrentSegment();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str16 = charsToNameCanonicalizer2.findSymbol(charArray12, (-1), 39, 17);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(errorReportConfiguration1);
        org.junit.Assert.assertNotNull(charsToNameCanonicalizer2);
        org.junit.Assert.assertNotNull(charsToNameCanonicalizer3);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 0 + "'", int8 == 0);
        org.junit.Assert.assertNotNull(charArray9);
        org.junit.Assert.assertNotNull(charArray11);
        org.junit.Assert.assertNotNull(charArray12);
    }

    @Test
    public void test1545() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1545");
        tools.jackson.core.util.DefaultPrettyPrinter defaultPrettyPrinter0 = new tools.jackson.core.util.DefaultPrettyPrinter();
        tools.jackson.core.util.DefaultPrettyPrinter.FixedSpaceIndenter fixedSpaceIndenter1 = tools.jackson.core.util.DefaultPrettyPrinter.FixedSpaceIndenter.instance();
        defaultPrettyPrinter0.indentArraysWith((tools.jackson.core.util.DefaultPrettyPrinter.Indenter) fixedSpaceIndenter1);
        tools.jackson.core.util.DefaultPrettyPrinter defaultPrettyPrinter3 = new tools.jackson.core.util.DefaultPrettyPrinter(defaultPrettyPrinter0);
        tools.jackson.core.util.DefaultIndenter defaultIndenter4 = new tools.jackson.core.util.DefaultIndenter();
        java.lang.String str5 = defaultIndenter4.getEol();
        boolean boolean6 = defaultIndenter4.isInline();
        tools.jackson.core.util.DefaultPrettyPrinter defaultPrettyPrinter7 = defaultPrettyPrinter3.withArrayIndenter((tools.jackson.core.util.DefaultPrettyPrinter.Indenter) defaultIndenter4);
        tools.jackson.core.JsonGenerator jsonGenerator8 = null;
        tools.jackson.core.util.JsonGeneratorDelegate jsonGeneratorDelegate9 = new tools.jackson.core.util.JsonGeneratorDelegate(jsonGenerator8);
        // The following exception was thrown during execution in test generation
        try {
            defaultPrettyPrinter7.writeStartArray(jsonGenerator8);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(fixedSpaceIndenter1);
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "\r\n" + "'", str5, "\r\n");
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNotNull(defaultPrettyPrinter7);
    }

    @Test
    public void test1546() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1546");
        tools.jackson.core.json.DupDetector dupDetector2 = null;
        tools.jackson.core.util.SimpleStreamReadContext simpleStreamReadContext3 = tools.jackson.core.util.SimpleStreamReadContext.createRootContext(55296, 0, dupDetector2);
        tools.jackson.core.util.SimpleStreamReadContext simpleStreamReadContext4 = simpleStreamReadContext3.clearAndGetParent();
        org.junit.Assert.assertNotNull(simpleStreamReadContext3);
        org.junit.Assert.assertNull(simpleStreamReadContext4);
    }

    @Test
    public void test1547() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1547");
        tools.jackson.core.json.DupDetector dupDetector2 = null;
        tools.jackson.core.util.SimpleStreamReadContext simpleStreamReadContext3 = tools.jackson.core.util.SimpleStreamReadContext.createRootContext(55296, 0, dupDetector2);
        java.lang.Object obj4 = simpleStreamReadContext3.currentValue();
        simpleStreamReadContext3.setCurrentName("root");
        tools.jackson.core.util.SimpleStreamReadContext simpleStreamReadContext9 = simpleStreamReadContext3.createChildObjectContext(0, 3225);
        org.junit.Assert.assertNotNull(simpleStreamReadContext3);
        org.junit.Assert.assertNull(obj4);
        org.junit.Assert.assertNotNull(simpleStreamReadContext9);
    }

    @Test
    public void test1548() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1548");
        tools.jackson.core.util.Separators separators0 = tools.jackson.core.util.Separators.createDefaultInstance();
        tools.jackson.core.util.Separators separators2 = separators0.withObjectNameValueSeparator('\000');
        tools.jackson.core.util.Separators separators4 = separators2.withObjectNameValueSeparator('\000');
        tools.jackson.core.util.Separators.Spacing spacing5 = null;
        tools.jackson.core.util.Separators separators6 = separators2.withObjectNameValueSpacing(spacing5);
        tools.jackson.core.util.Separators separators8 = separators6.withObjectNameValueSeparator('a');
        org.junit.Assert.assertNotNull(separators0);
        org.junit.Assert.assertNotNull(separators2);
        org.junit.Assert.assertNotNull(separators4);
        org.junit.Assert.assertNotNull(separators6);
        org.junit.Assert.assertNotNull(separators8);
    }

    @Test
    public void test1549() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1549");
        java.util.Locale locale0 = null;
        java.lang.String[] strArray10 = new java.lang.String[] { "0.0", "IA", "tools.jackson.core.exc.StreamWriteException: hi!\n at [No location information] (through reference chain: java.lang.Boolean[\"hi!\"])", "hi!", " /", "root", "REDACTED (`StreamReadFeature.INCLUDE_SOURCE_IN_LOCATION` disabled)", "tools.jackson.core.type.WritableTypeId[256]", "B " };
        java.util.ArrayList<java.lang.String> strList11 = new java.util.ArrayList<java.lang.String>();
        boolean boolean12 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList11, strArray10);
        tools.jackson.core.sym.SimpleNameMatcher simpleNameMatcher13 = tools.jackson.core.sym.SimpleNameMatcher.construct(locale0, (java.util.List<java.lang.String>) strList11);
        tools.jackson.core.sym.BinaryNameMatcher binaryNameMatcher14 = tools.jackson.core.sym.BinaryNameMatcher.construct((java.util.List<java.lang.String>) strList11);
        org.junit.Assert.assertNotNull(strArray10);
        org.junit.Assert.assertArrayEquals(strArray10, new java.lang.String[] { "0.0", "IA", "tools.jackson.core.exc.StreamWriteException: hi!\n at [No location information] (through reference chain: java.lang.Boolean[\"hi!\"])", "hi!", " /", "root", "REDACTED (`StreamReadFeature.INCLUDE_SOURCE_IN_LOCATION` disabled)", "tools.jackson.core.type.WritableTypeId[256]", "B " });
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + true + "'", boolean12 == true);
        org.junit.Assert.assertNotNull(simpleNameMatcher13);
        org.junit.Assert.assertNotNull(binaryNameMatcher14);
    }

    @Test
    public void test1550() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1550");
        tools.jackson.core.Base64Variant base64Variant0 = tools.jackson.core.Base64Variants.MODIFIED_FOR_URL;
        byte[] byteArray8 = new byte[] { (byte) 10, (byte) -1, (byte) -1, (byte) -1, (byte) -1 };
        int int10 = base64Variant0.encodeBase64Partial(10, (int) 'a', byteArray8, (int) (byte) 1);
        tools.jackson.core.Base64Variant base64Variant13 = new tools.jackson.core.Base64Variant(base64Variant0, "hi!", 0);
        tools.jackson.core.Base64Variant base64Variant14 = base64Variant0.withPaddingForbidden();
        // The following exception was thrown during execution in test generation
        try {
            byte[] byteArray16 = base64Variant14.decode("(Float)");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Illegal character '(' (code 0x28) in base64 content");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(base64Variant0);
        org.junit.Assert.assertNotNull(byteArray8);
        org.junit.Assert.assertArrayEquals(byteArray8, new byte[] { (byte) 10, (byte) 65, (byte) 65, (byte) -1, (byte) -1 });
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 3 + "'", int10 == 3);
        org.junit.Assert.assertNotNull(base64Variant14);
    }

    @Test
    public void test1551() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1551");
        byte[] byteArray1 = tools.jackson.core.io.CharTypes.copyHexBytes(false);
        org.junit.Assert.assertNotNull(byteArray1);
        org.junit.Assert.assertArrayEquals(byteArray1, new byte[] { (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102 });
    }

    @Test
    public void test1552() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1552");
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.Base64Variant base64Variant5 = new tools.jackson.core.Base64Variant("UNKNOWN", "Unexpected end of base64-encoded String: base64 variant 'MODIFIED-FOR-URL' expects padding (one or more '\000' characters) at the end. This Base64Variant might have been incorrectly configured", false, '~', 57343);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Base64Alphabet length must be exactly 64 (was 189)");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test1553() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1553");
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
        tools.jackson.core.filter.TokenFilter tokenFilter35 = null;
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext36 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter35);
        tools.jackson.core.JsonToken jsonToken37 = tokenFilterContext36.nextTokenToRead();
        tools.jackson.core.filter.TokenFilter tokenFilter38 = tools.jackson.core.filter.TokenFilter.INCLUDE_ALL;
        tools.jackson.core.filter.TokenFilter tokenFilter39 = tokenFilterContext36.checkValue(tokenFilter38);
        java.math.BigDecimal bigDecimal40 = null;
        boolean boolean41 = tokenFilter38.includeNumber(bigDecimal40);
        tools.jackson.core.filter.TokenFilter tokenFilter43 = tokenFilter38.includeRootValue((int) (byte) 1);
        tools.jackson.core.filter.TokenFilter tokenFilter45 = tokenFilter38.includeElement(4);
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion46 = tools.jackson.core.filter.TokenFilter.Inclusion.ONLY_INCLUDE_ALL;
        tools.jackson.core.filter.FilteringGeneratorDelegate filteringGeneratorDelegate48 = new tools.jackson.core.filter.FilteringGeneratorDelegate((tools.jackson.core.JsonGenerator) filteringGeneratorDelegate34, tokenFilter45, inclusion46, false);
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.JsonGenerator jsonGenerator50 = filteringGeneratorDelegate34.writeObjectPropertyStart("53.0");
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
        org.junit.Assert.assertNotNull(tokenFilterContext36);
        org.junit.Assert.assertNull(jsonToken37);
        org.junit.Assert.assertNotNull(tokenFilter38);
        org.junit.Assert.assertNotNull(tokenFilter39);
        org.junit.Assert.assertTrue("'" + boolean41 + "' != '" + true + "'", boolean41 == true);
        org.junit.Assert.assertNotNull(tokenFilter43);
        org.junit.Assert.assertNotNull(tokenFilter45);
        org.junit.Assert.assertTrue("'" + inclusion46 + "' != '" + tools.jackson.core.filter.TokenFilter.Inclusion.ONLY_INCLUDE_ALL + "'", inclusion46.equals(tools.jackson.core.filter.TokenFilter.Inclusion.ONLY_INCLUDE_ALL));
    }

    @Test
    public void test1554() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1554");
        tools.jackson.core.util.BufferRecycler bufferRecycler1 = null;
        tools.jackson.core.util.ByteArrayBuilder byteArrayBuilder2 = new tools.jackson.core.util.ByteArrayBuilder(bufferRecycler1);
        byte[] byteArray4 = byteArrayBuilder2.completeAndCoalesce(10);
        byte[] byteArray5 = byteArrayBuilder2.toByteArray();
        byteArrayBuilder2.close();
        int int7 = byteArrayBuilder2.size();
        byte[] byteArray9 = byteArrayBuilder2.completeAndCoalesce(353);
        // The following exception was thrown during execution in test generation
        try {
            int int11 = tools.jackson.core.io.NumberOutput.outputLong((long) (-56613888), byteArray9, 1000);
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: 1000");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray4);
        org.junit.Assert.assertArrayEquals(byteArray4, new byte[] { (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0 });
        org.junit.Assert.assertNotNull(byteArray5);
        org.junit.Assert.assertArrayEquals(byteArray5, new byte[] { (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0 });
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 10 + "'", int7 == 10);
        org.junit.Assert.assertNotNull(byteArray9);
    }

    @Test
    public void test1555() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1555");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        tools.jackson.core.filter.TokenFilter tokenFilter6 = filteringParserDelegate5.getFilter();
        boolean boolean7 = filteringParserDelegate5.isExpectedStartArrayToken();
        boolean boolean8 = filteringParserDelegate5.hasCurrentToken();
        filteringParserDelegate5.clearCurrentToken();
        boolean boolean10 = filteringParserDelegate5.isExpectedStartObjectToken();
        boolean boolean11 = filteringParserDelegate5.hasCurrentToken();
        tools.jackson.core.json.DupDetector dupDetector12 = tools.jackson.core.json.DupDetector.rootDetector((tools.jackson.core.JsonParser) filteringParserDelegate5);
        tools.jackson.core.JsonToken jsonToken13 = null;
        boolean boolean14 = filteringParserDelegate5.hasToken(jsonToken13);
        boolean boolean15 = filteringParserDelegate5.isExpectedStartObjectToken();
        org.junit.Assert.assertNull(tokenFilter6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
        org.junit.Assert.assertNotNull(dupDetector12);
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + true + "'", boolean14 == true);
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
    }

    @Test
    public void test1556() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1556");
        tools.jackson.core.util.Separators separators0 = tools.jackson.core.util.Separators.createDefaultInstance();
        tools.jackson.core.util.Separators separators2 = separators0.withObjectNameValueSeparator('\000');
        char char3 = separators0.getArrayElementSeparator();
        tools.jackson.core.util.Separators.Spacing spacing4 = separators0.getArrayElementSpacing();
        tools.jackson.core.util.Separators separators6 = separators0.withRootSeparator("tools.jackson.core.exc.StreamWriteException: hi!\n at [No location information] (through reference chain: java.lang.Boolean[\"hi!\"])");
        org.junit.Assert.assertNotNull(separators0);
        org.junit.Assert.assertNotNull(separators2);
        org.junit.Assert.assertTrue("'" + char3 + "' != '" + ',' + "'", char3 == ',');
        org.junit.Assert.assertTrue("'" + spacing4 + "' != '" + tools.jackson.core.util.Separators.Spacing.NONE + "'", spacing4.equals(tools.jackson.core.util.Separators.Spacing.NONE));
        org.junit.Assert.assertNotNull(separators6);
    }

    @Test
    public void test1557() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1557");
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder0 = tools.jackson.core.json.JsonFactory.builderWithJackson2Defaults();
        tools.jackson.core.SerializableString serializableString1 = null;
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder2 = jsonFactoryBuilder0.rootValueSeparator(serializableString1);
        tools.jackson.core.io.OutputDecorator outputDecorator3 = jsonFactoryBuilder2.outputDecorator();
        tools.jackson.core.io.InputDecorator inputDecorator4 = null;
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder5 = jsonFactoryBuilder2.inputDecorator(inputDecorator4);
        tools.jackson.core.io.SerializedString serializedString6 = tools.jackson.core.PrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
        byte[] byteArray7 = serializedString6.asQuotedUTF8();
        java.lang.String str8 = serializedString6.toString();
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder9 = jsonFactoryBuilder2.rootValueSeparator((tools.jackson.core.SerializableString) serializedString6);
        byte[] byteArray10 = serializedString6.asQuotedUTF8();
        char[] charArray11 = serializedString6.asQuotedChars();
        org.junit.Assert.assertNotNull(jsonFactoryBuilder0);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder2);
        org.junit.Assert.assertNull(outputDecorator3);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder5);
        org.junit.Assert.assertNotNull(serializedString6);
        org.junit.Assert.assertNotNull(byteArray7);
        org.junit.Assert.assertArrayEquals(byteArray7, new byte[] { (byte) 32 });
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + " " + "'", str8, " ");
        org.junit.Assert.assertNotNull(jsonFactoryBuilder9);
        org.junit.Assert.assertNotNull(byteArray10);
        org.junit.Assert.assertArrayEquals(byteArray10, new byte[] { (byte) 32 });
        org.junit.Assert.assertNotNull(charArray11);
        org.junit.Assert.assertArrayEquals(charArray11, new char[] { ' ' });
    }

    @Test
    public void test1558() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1558");
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
            java.lang.String str20 = filteringParserDelegate15.getString();
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
    public void test1559() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1559");
        tools.jackson.core.util.DefaultPrettyPrinter defaultPrettyPrinter0 = new tools.jackson.core.util.DefaultPrettyPrinter();
        tools.jackson.core.util.Separators separators1 = tools.jackson.core.util.Separators.createDefaultInstance();
        tools.jackson.core.util.Separators separators3 = separators1.withObjectNameValueSeparator('\000');
        tools.jackson.core.util.DefaultPrettyPrinter defaultPrettyPrinter4 = new tools.jackson.core.util.DefaultPrettyPrinter(defaultPrettyPrinter0, separators1);
        tools.jackson.core.util.DefaultPrettyPrinter defaultPrettyPrinter5 = new tools.jackson.core.util.DefaultPrettyPrinter(defaultPrettyPrinter0);
        org.junit.Assert.assertNotNull(separators1);
        org.junit.Assert.assertNotNull(separators3);
    }

    @Test
    public void test1560() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1560");
        java.util.Locale locale0 = null;
        java.util.Locale locale1 = null;
        java.lang.String[] strArray9 = new java.lang.String[] { "Unexpected end of base64-encoded String: base64 variant 'MODIFIED-FOR-URL' expects padding (one or more '\000' characters) at the end. This Base64Variant might have been incorrectly configured", "AAAAAAAAAAAAAA", "JSON", "//0.0.0", "", "", "0.0" };
        java.util.ArrayList<java.lang.String> strList10 = new java.util.ArrayList<java.lang.String>();
        boolean boolean11 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList10, strArray9);
        tools.jackson.core.sym.SimpleNameMatcher simpleNameMatcher12 = tools.jackson.core.sym.SimpleNameMatcher.construct(locale1, (java.util.List<java.lang.String>) strList10);
        tools.jackson.core.sym.SimpleNameMatcher simpleNameMatcher13 = tools.jackson.core.sym.SimpleNameMatcher.construct(locale0, (java.util.List<java.lang.String>) strList10);
        int[] intArray14 = tools.jackson.core.io.CharTypes.getInputCodeLatin1();
        int int16 = simpleNameMatcher13.matchByQuad(intArray14, 15);
        org.junit.Assert.assertNotNull(strArray9);
        org.junit.Assert.assertArrayEquals(strArray9, new java.lang.String[] { "Unexpected end of base64-encoded String: base64 variant 'MODIFIED-FOR-URL' expects padding (one or more '\000' characters) at the end. This Base64Variant might have been incorrectly configured", "AAAAAAAAAAAAAA", "JSON", "//0.0.0", "", "", "0.0" });
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + true + "'", boolean11 == true);
        org.junit.Assert.assertNotNull(simpleNameMatcher12);
        org.junit.Assert.assertNotNull(simpleNameMatcher13);
        org.junit.Assert.assertNotNull(intArray14);
        org.junit.Assert.assertTrue("'" + int16 + "' != '" + (-2) + "'", int16 == (-2));
    }

    @Test
    public void test1561() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1561");
        tools.jackson.core.util.BufferRecycler bufferRecycler0 = null;
        tools.jackson.core.util.TextBuffer textBuffer1 = new tools.jackson.core.util.TextBuffer(bufferRecycler0);
        textBuffer1.resetWithString("");
        int int4 = textBuffer1.getCurrentSegmentSize();
        java.lang.String str5 = textBuffer1.toString();
        java.lang.String str8 = textBuffer1.finishAndReturn((int) 'B', false);
        java.lang.String str9 = textBuffer1.toString();
        textBuffer1.resetWith(' ');
        textBuffer1.ensureNotShared();
        char[] charArray16 = new char[] { ' ', ' ', '4' };
        boolean boolean20 = tools.jackson.core.io.NumberInput.inLongRange(charArray16, (-324), (int) '4', false);
        textBuffer1.resetWithShared(charArray16, (-1847717470), 1801838965);
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 0 + "'", int4 == 0);
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "" + "'", str5, "");
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "" + "'", str8, "");
        org.junit.Assert.assertEquals("'" + str9 + "' != '" + "" + "'", str9, "");
        org.junit.Assert.assertNotNull(charArray16);
        org.junit.Assert.assertArrayEquals(charArray16, new char[] { ' ', ' ', '4' });
        org.junit.Assert.assertTrue("'" + boolean20 + "' != '" + false + "'", boolean20 == false);
    }

    @Test
    public void test1562() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1562");
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
            double double11 = filteringParserDelegate5.getValueAsDouble();
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
    public void test1563() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1563");
        tools.jackson.core.util.JsonRecyclerPools jsonRecyclerPools0 = new tools.jackson.core.util.JsonRecyclerPools();
    }

    @Test
    public void test1564() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1564");
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
            tools.jackson.core.JsonGenerator jsonGenerator38 = filteringGeneratorDelegate34.writeRaw("~0", 0, 50000);
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
    public void test1565() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1565");
        tools.jackson.core.util.BufferRecycler bufferRecycler0 = new tools.jackson.core.util.BufferRecycler();
        tools.jackson.core.io.SegmentedStringWriter segmentedStringWriter1 = new tools.jackson.core.io.SegmentedStringWriter(bufferRecycler0);
        segmentedStringWriter1.write("MODIFIED-FOR-URL/UNKNOWN/0.0.0");
        tools.jackson.core.util.BufferRecycler bufferRecycler4 = segmentedStringWriter1.bufferRecycler();
        java.io.Writer writer6 = segmentedStringWriter1.append((java.lang.CharSequence) "[tools.jackson.core.sym.ByteQuadsCanonicalizer: size=0, hashSize=64, 0/0/0/0 pri/sec/ter/spill (=0), total:0]");
        java.io.Writer writer8 = segmentedStringWriter1.append((java.lang.CharSequence) "[tools.jackson.core.sym.ByteQuadsCanonicalizer: size=0, hashSize=0, 0/0/0/0 pri/sec/ter/spill (=0), total:0]");
        org.junit.Assert.assertNotNull(bufferRecycler4);
        org.junit.Assert.assertNotNull(writer6);
        org.junit.Assert.assertNotNull(writer8);
    }

    @Test
    public void test1566() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1566");
        tools.jackson.core.json.JsonFactory jsonFactory0 = new tools.jackson.core.json.JsonFactory();
        int int1 = jsonFactory0.getFormatReadFeatures();
        tools.jackson.core.util.BufferRecycler bufferRecycler2 = new tools.jackson.core.util.BufferRecycler();
        tools.jackson.core.util.ByteArrayBuilder byteArrayBuilder4 = new tools.jackson.core.util.ByteArrayBuilder(bufferRecycler2, 12000);
        tools.jackson.core.io.SegmentedStringWriter segmentedStringWriter5 = new tools.jackson.core.io.SegmentedStringWriter(bufferRecycler2);
        java.lang.String str6 = segmentedStringWriter5.getAndClear();
        tools.jackson.core.JsonGenerator jsonGenerator7 = jsonFactory0.createGenerator((java.io.Writer) segmentedStringWriter5);
        tools.jackson.core.ObjectReadContext objectReadContext8 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter9 = null;
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext10 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter9);
        tools.jackson.core.JsonToken jsonToken11 = tokenFilterContext10.nextTokenToRead();
        tools.jackson.core.filter.TokenFilter tokenFilter12 = tools.jackson.core.filter.TokenFilter.INCLUDE_ALL;
        tools.jackson.core.filter.TokenFilter tokenFilter13 = tokenFilterContext10.checkValue(tokenFilter12);
        java.math.BigDecimal bigDecimal14 = null;
        boolean boolean15 = tokenFilter12.includeNumber(bigDecimal14);
        tools.jackson.core.filter.TokenFilter tokenFilter16 = tokenFilter12.filterStartObject();
        tools.jackson.core.io.IOContext iOContext17 = null;
        java.io.InputStream inputStream18 = null;
        byte[] byteArray22 = new byte[] { (byte) 100, (byte) 1 };
        tools.jackson.core.io.UTF32Reader uTF32Reader26 = new tools.jackson.core.io.UTF32Reader(iOContext17, inputStream18, false, byteArray22, (int) (short) 0, (-1), false);
        boolean boolean27 = uTF32Reader26.ready();
        boolean boolean28 = uTF32Reader26.ready();
        boolean boolean29 = uTF32Reader26.ready();
        boolean boolean31 = tokenFilter16.includeString((java.io.Reader) uTF32Reader26, 0);
        uTF32Reader26.close();
        boolean boolean33 = uTF32Reader26.ready();
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.JsonParser jsonParser34 = jsonFactory0.createParser(objectReadContext8, (java.io.Reader) uTF32Reader26);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + int1 + "' != '" + 0 + "'", int1 == 0);
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "" + "'", str6, "");
        org.junit.Assert.assertNotNull(jsonGenerator7);
        org.junit.Assert.assertNotNull(tokenFilterContext10);
        org.junit.Assert.assertNull(jsonToken11);
        org.junit.Assert.assertNotNull(tokenFilter12);
        org.junit.Assert.assertNotNull(tokenFilter13);
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + true + "'", boolean15 == true);
        org.junit.Assert.assertNotNull(tokenFilter16);
        org.junit.Assert.assertNotNull(byteArray22);
        org.junit.Assert.assertArrayEquals(byteArray22, new byte[] { (byte) 100, (byte) 1 });
        org.junit.Assert.assertTrue("'" + boolean27 + "' != '" + false + "'", boolean27 == false);
        org.junit.Assert.assertTrue("'" + boolean28 + "' != '" + false + "'", boolean28 == false);
        org.junit.Assert.assertTrue("'" + boolean29 + "' != '" + false + "'", boolean29 == false);
        org.junit.Assert.assertTrue("'" + boolean31 + "' != '" + true + "'", boolean31 == true);
        org.junit.Assert.assertTrue("'" + boolean33 + "' != '" + false + "'", boolean33 == false);
    }

    @Test
    public void test1567() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1567");
        tools.jackson.core.sym.ByteQuadsCanonicalizer byteQuadsCanonicalizer0 = tools.jackson.core.sym.ByteQuadsCanonicalizer.createRoot();
        byteQuadsCanonicalizer0.release();
        tools.jackson.core.sym.ByteQuadsCanonicalizer byteQuadsCanonicalizer3 = byteQuadsCanonicalizer0.makeChildOrPlaceholder((-1));
        int int4 = byteQuadsCanonicalizer3.spilloverCount();
        int[] intArray5 = tools.jackson.core.io.CharTypes.getInputCodeUtf8JsNames();
        // The following exception was thrown during execution in test generation
        try {
            int int7 = byteQuadsCanonicalizer3.calcHash(intArray5, (int) (byte) 1);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: qlen is too short, needs to be at least 4");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteQuadsCanonicalizer0);
        org.junit.Assert.assertNotNull(byteQuadsCanonicalizer3);
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 0 + "'", int4 == 0);
        org.junit.Assert.assertNotNull(intArray5);
    }

    @Test
    public void test1568() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1568");
        tools.jackson.core.util.BufferRecycler bufferRecycler0 = null;
        tools.jackson.core.util.TextBuffer textBuffer1 = new tools.jackson.core.util.TextBuffer(bufferRecycler0);
        textBuffer1.resetWithString("");
        int int4 = textBuffer1.getCurrentSegmentSize();
        java.lang.String str5 = textBuffer1.toString();
        java.lang.String str8 = textBuffer1.finishAndReturn((int) 'B', false);
        java.lang.String str9 = textBuffer1.toString();
        char[] charArray10 = textBuffer1.emptyAndGetCurrentSegment();
        tools.jackson.core.util.TextBuffer textBuffer11 = tools.jackson.core.util.TextBuffer.fromInitial(charArray10);
        textBuffer11.releaseBuffers();
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 0 + "'", int4 == 0);
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "" + "'", str5, "");
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "" + "'", str8, "");
        org.junit.Assert.assertEquals("'" + str9 + "' != '" + "" + "'", str9, "");
        org.junit.Assert.assertNotNull(charArray10);
        org.junit.Assert.assertNotNull(textBuffer11);
    }

    @Test
    public void test1569() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1569");
        tools.jackson.core.util.InternCache internCache0 = tools.jackson.core.util.InternCache.instance;
        tools.jackson.core.json.DupDetector dupDetector1 = null;
        tools.jackson.core.util.SimpleStreamWriteContext simpleStreamWriteContext2 = tools.jackson.core.util.SimpleStreamWriteContext.createRootContext(dupDetector1);
        tools.jackson.core.json.DupDetector dupDetector3 = simpleStreamWriteContext2.getDupDetector();
        boolean boolean4 = simpleStreamWriteContext2.inObject();
        boolean boolean5 = internCache0.containsValue((java.lang.Object) boolean4);
        boolean boolean6 = internCache0.isEmpty();
        tools.jackson.core.util.InternCache internCache7 = new tools.jackson.core.util.InternCache();
        internCache0.putAll((java.util.Map<java.lang.String, java.lang.String>) internCache7);
        java.util.Set<java.util.Map.Entry<java.lang.String, java.lang.String>> strEntrySet9 = internCache7.entrySet();
        java.util.concurrent.ConcurrentHashMap.KeySetView<java.lang.String, java.lang.String> strSet11 = internCache7.keySet("tools.jackson.core.exc.StreamReadException: ");
        internCache7.clear();
        org.junit.Assert.assertNotNull(internCache0);
        org.junit.Assert.assertNotNull(simpleStreamWriteContext2);
        org.junit.Assert.assertNull(dupDetector3);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
// flaky "1) test1569(RegressionTest3)":         org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNotNull(strEntrySet9);
        org.junit.Assert.assertNotNull(strSet11);
    }

    @Test
    public void test1570() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1570");
        tools.jackson.core.util.BufferRecycler bufferRecycler0 = null;
        tools.jackson.core.util.TextBuffer textBuffer1 = new tools.jackson.core.util.TextBuffer(bufferRecycler0);
        textBuffer1.resetWithString("");
        int int4 = textBuffer1.getCurrentSegmentSize();
        char[] charArray5 = textBuffer1.emptyAndGetCurrentSegment();
        char[] charArray7 = textBuffer1.expandCurrentSegment(0);
        textBuffer1.resetWithString("UNKNOWN");
        boolean boolean10 = textBuffer1.hasTextAsCharacters();
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 0 + "'", int4 == 0);
        org.junit.Assert.assertNotNull(charArray5);
        org.junit.Assert.assertNotNull(charArray7);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
    }

    @Test
    public void test1571() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1571");
        tools.jackson.core.Base64Variant base64Variant0 = tools.jackson.core.Base64Variants.MODIFIED_FOR_URL;
        byte[] byteArray8 = new byte[] { (byte) 10, (byte) -1, (byte) -1, (byte) -1, (byte) -1 };
        int int10 = base64Variant0.encodeBase64Partial(10, (int) 'a', byteArray8, (int) (byte) 1);
        tools.jackson.core.Base64Variant base64Variant13 = new tools.jackson.core.Base64Variant(base64Variant0, "hi!", 0);
        tools.jackson.core.Base64Variant base64Variant14 = base64Variant0.withPaddingAllowed();
        tools.jackson.core.Base64Variant base64Variant17 = new tools.jackson.core.Base64Variant(base64Variant14, "CkFB__8", 9);
        int int19 = base64Variant14.decodeBase64Char(1924645298);
        tools.jackson.core.Base64Variant base64Variant24 = new tools.jackson.core.Base64Variant(base64Variant14, "10.0", false, '\000', (int) '4');
        org.junit.Assert.assertNotNull(base64Variant0);
        org.junit.Assert.assertNotNull(byteArray8);
        org.junit.Assert.assertArrayEquals(byteArray8, new byte[] { (byte) 10, (byte) 65, (byte) 65, (byte) -1, (byte) -1 });
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 3 + "'", int10 == 3);
        org.junit.Assert.assertNotNull(base64Variant14);
        org.junit.Assert.assertTrue("'" + int19 + "' != '" + (-1) + "'", int19 == (-1));
    }

    @Test
    public void test1572() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1572");
        tools.jackson.core.StreamReadConstraints.Builder builder0 = tools.jackson.core.StreamReadConstraints.builder();
        tools.jackson.core.StreamReadConstraints.Builder builder2 = builder0.maxNumberLength(49);
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(builder2);
    }

    @Test
    public void test1573() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1573");
        java.util.concurrent.ConcurrentHashMap.KeySetView<tools.jackson.core.json.JsonFactory, java.lang.Boolean> jsonFactorySet0 = java.util.concurrent.ConcurrentHashMap.newKeySet();
        org.junit.Assert.assertNotNull(jsonFactorySet0);
    }

    @Test
    public void test1574() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1574");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        tools.jackson.core.JsonParser jsonParser6 = filteringParserDelegate5.skipChildren();
        tools.jackson.core.util.BufferRecycler bufferRecycler7 = null;
        tools.jackson.core.util.TextBuffer textBuffer8 = new tools.jackson.core.util.TextBuffer(bufferRecycler7);
        textBuffer8.resetWithString("");
        int int11 = textBuffer8.getCurrentSegmentSize();
        tools.jackson.core.util.BufferRecycler bufferRecycler12 = new tools.jackson.core.util.BufferRecycler();
        tools.jackson.core.io.SegmentedStringWriter segmentedStringWriter13 = new tools.jackson.core.io.SegmentedStringWriter(bufferRecycler12);
        int int14 = textBuffer8.contentsToWriter((java.io.Writer) segmentedStringWriter13);
        int int15 = filteringParserDelegate5.releaseBuffered((java.io.Writer) segmentedStringWriter13);
        // The following exception was thrown during execution in test generation
        try {
            int int16 = filteringParserDelegate5.getIntValue();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(jsonParser6);
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 0 + "'", int11 == 0);
        org.junit.Assert.assertTrue("'" + int14 + "' != '" + 0 + "'", int14 == 0);
        org.junit.Assert.assertTrue("'" + int15 + "' != '" + (-1) + "'", int15 == (-1));
    }

    @Test
    public void test1575() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1575");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        boolean boolean6 = filteringParserDelegate5.isExpectedStartObjectToken();
        int int7 = filteringParserDelegate5.currentTokenId();
        boolean boolean8 = filteringParserDelegate5.isExpectedStartObjectToken();
        tools.jackson.core.filter.TokenFilter tokenFilter9 = filteringParserDelegate5.getFilter();
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean10 = filteringParserDelegate5.canReadObjectId();
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
    public void test1576() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1576");
        tools.jackson.core.util.BufferRecycler bufferRecycler0 = null;
        tools.jackson.core.util.TextBuffer textBuffer1 = new tools.jackson.core.util.TextBuffer(bufferRecycler0);
        textBuffer1.resetWithString("");
        int int4 = textBuffer1.getCurrentSegmentSize();
        java.lang.String str5 = textBuffer1.toString();
        java.lang.String str8 = textBuffer1.finishAndReturn((int) 'B', false);
        textBuffer1.releaseBuffers();
        char[] charArray10 = textBuffer1.emptyAndGetCurrentSegment();
        java.lang.String str13 = textBuffer1.finishAndReturn((int) (byte) -17, true);
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 0 + "'", int4 == 0);
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "" + "'", str5, "");
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "" + "'", str8, "");
        org.junit.Assert.assertNotNull(charArray10);
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "" + "'", str13, "");
    }

    @Test
    public void test1577() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1577");
        tools.jackson.core.util.BufferRecycler bufferRecycler0 = new tools.jackson.core.util.BufferRecycler();
        tools.jackson.core.io.SegmentedStringWriter segmentedStringWriter1 = new tools.jackson.core.io.SegmentedStringWriter(bufferRecycler0);
        segmentedStringWriter1.write("MODIFIED-FOR-URL/UNKNOWN/0.0.0");
        tools.jackson.core.util.BufferRecycler bufferRecycler4 = segmentedStringWriter1.bufferRecycler();
        segmentedStringWriter1.close();
        java.io.Writer writer7 = segmentedStringWriter1.append((java.lang.CharSequence) "UNKNOWN[34]");
        org.junit.Assert.assertNotNull(bufferRecycler4);
        org.junit.Assert.assertNotNull(writer7);
    }

    @Test
    public void test1578() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1578");
        tools.jackson.core.StreamReadConstraints streamReadConstraints0 = tools.jackson.core.StreamReadConstraints.defaults();
        boolean boolean1 = streamReadConstraints0.hasMaxDocumentLength();
        int int2 = streamReadConstraints0.getMaxNumberLength();
        long long3 = streamReadConstraints0.getMaxDocumentLength();
        tools.jackson.core.StreamWriteConstraints.Builder builder4 = tools.jackson.core.StreamWriteConstraints.builder();
        tools.jackson.core.StreamWriteConstraints streamWriteConstraints5 = builder4.build();
        tools.jackson.core.json.JsonFactory jsonFactory6 = new tools.jackson.core.json.JsonFactory();
        tools.jackson.core.ErrorReportConfiguration errorReportConfiguration7 = jsonFactory6.errorReportConfiguration();
        tools.jackson.core.util.BufferRecycler bufferRecycler8 = new tools.jackson.core.util.BufferRecycler();
        tools.jackson.core.io.SegmentedStringWriter segmentedStringWriter9 = new tools.jackson.core.io.SegmentedStringWriter(bufferRecycler8);
        segmentedStringWriter9.write("MODIFIED-FOR-URL/UNKNOWN/0.0.0");
        tools.jackson.core.util.BufferRecycler bufferRecycler12 = segmentedStringWriter9.bufferRecycler();
        tools.jackson.core.io.ContentReference contentReference13 = tools.jackson.core.io.ContentReference.redacted();
        tools.jackson.core.TokenStreamLocation tokenStreamLocation17 = new tools.jackson.core.TokenStreamLocation(contentReference13, (long) (short) 10, 33, 33);
        java.lang.Object obj18 = contentReference13.getRawContent();
        tools.jackson.core.JsonEncoding jsonEncoding20 = null;
        tools.jackson.core.io.IOContext iOContext21 = new tools.jackson.core.io.IOContext(streamReadConstraints0, streamWriteConstraints5, errorReportConfiguration7, bufferRecycler12, contentReference13, false, jsonEncoding20);
        tools.jackson.core.StreamReadConstraints streamReadConstraints22 = iOContext21.streamReadConstraints();
        tools.jackson.core.StreamWriteConstraints streamWriteConstraints23 = iOContext21.streamWriteConstraints();
        tools.jackson.core.io.SerializedString serializedString24 = tools.jackson.core.PrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
        byte[] byteArray25 = serializedString24.asQuotedUTF8();
        java.lang.String str26 = serializedString24.toString();
        tools.jackson.core.Base64Variant base64Variant27 = tools.jackson.core.Base64Variants.MODIFIED_FOR_URL;
        byte[] byteArray35 = new byte[] { (byte) 10, (byte) -1, (byte) -1, (byte) -1, (byte) -1 };
        int int37 = base64Variant27.encodeBase64Partial(10, (int) 'a', byteArray35, (int) (byte) 1);
        tools.jackson.core.Base64Variant base64Variant40 = new tools.jackson.core.Base64Variant(base64Variant27, "hi!", 0);
        tools.jackson.core.Base64Variant base64Variant41 = base64Variant27.withPaddingAllowed();
        tools.jackson.core.Base64Variant base64Variant42 = tools.jackson.core.Base64Variants.MODIFIED_FOR_URL;
        byte[] byteArray50 = new byte[] { (byte) 10, (byte) -1, (byte) -1, (byte) -1, (byte) -1 };
        int int52 = base64Variant42.encodeBase64Partial(10, (int) 'a', byteArray50, (int) (byte) 1);
        java.lang.String str55 = base64Variant27.encode(byteArray50, false, "//0.0.0");
        int int57 = serializedString24.appendUnquotedUTF8(byteArray50, 56320);
        tools.jackson.core.json.ByteSourceJsonBootstrapper byteSourceJsonBootstrapper60 = new tools.jackson.core.json.ByteSourceJsonBootstrapper(iOContext21, byteArray50, 2147483647, 32767);
        org.junit.Assert.assertNotNull(streamReadConstraints0);
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
        org.junit.Assert.assertTrue("'" + int2 + "' != '" + 1000 + "'", int2 == 1000);
        org.junit.Assert.assertTrue("'" + long3 + "' != '" + (-1L) + "'", long3 == (-1L));
        org.junit.Assert.assertNotNull(builder4);
        org.junit.Assert.assertNotNull(streamWriteConstraints5);
        org.junit.Assert.assertNotNull(errorReportConfiguration7);
        org.junit.Assert.assertNotNull(bufferRecycler12);
        org.junit.Assert.assertNotNull(contentReference13);
        org.junit.Assert.assertNull(obj18);
        org.junit.Assert.assertNotNull(streamReadConstraints22);
        org.junit.Assert.assertNotNull(streamWriteConstraints23);
        org.junit.Assert.assertNotNull(serializedString24);
        org.junit.Assert.assertNotNull(byteArray25);
        org.junit.Assert.assertArrayEquals(byteArray25, new byte[] { (byte) 32 });
        org.junit.Assert.assertEquals("'" + str26 + "' != '" + " " + "'", str26, " ");
        org.junit.Assert.assertNotNull(base64Variant27);
        org.junit.Assert.assertNotNull(byteArray35);
        org.junit.Assert.assertArrayEquals(byteArray35, new byte[] { (byte) 10, (byte) 65, (byte) 65, (byte) -1, (byte) -1 });
        org.junit.Assert.assertTrue("'" + int37 + "' != '" + 3 + "'", int37 == 3);
        org.junit.Assert.assertNotNull(base64Variant41);
        org.junit.Assert.assertNotNull(base64Variant42);
        org.junit.Assert.assertNotNull(byteArray50);
        org.junit.Assert.assertArrayEquals(byteArray50, new byte[] { (byte) 10, (byte) 65, (byte) 65, (byte) -1, (byte) -1 });
        org.junit.Assert.assertTrue("'" + int52 + "' != '" + 3 + "'", int52 == 3);
        org.junit.Assert.assertEquals("'" + str55 + "' != '" + "CkFB__8" + "'", str55, "CkFB__8");
        org.junit.Assert.assertTrue("'" + int57 + "' != '" + (-1) + "'", int57 == (-1));
    }

    @Test
    public void test1579() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1579");
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
        tools.jackson.core.filter.TokenFilter tokenFilter35 = null;
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext36 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter35);
        tools.jackson.core.JsonToken jsonToken37 = tokenFilterContext36.nextTokenToRead();
        tools.jackson.core.filter.TokenFilter tokenFilter38 = tools.jackson.core.filter.TokenFilter.INCLUDE_ALL;
        tools.jackson.core.filter.TokenFilter tokenFilter39 = tokenFilterContext36.checkValue(tokenFilter38);
        java.math.BigDecimal bigDecimal40 = null;
        boolean boolean41 = tokenFilter38.includeNumber(bigDecimal40);
        tools.jackson.core.filter.TokenFilter tokenFilter43 = tokenFilter38.includeRootValue((int) (byte) 1);
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion44 = tools.jackson.core.filter.TokenFilter.Inclusion.ONLY_INCLUDE_ALL;
        tools.jackson.core.filter.FilteringGeneratorDelegate filteringGeneratorDelegate46 = new tools.jackson.core.filter.FilteringGeneratorDelegate((tools.jackson.core.JsonGenerator) filteringGeneratorDelegate34, tokenFilter38, inclusion44, false);
        tools.jackson.core.TokenStreamContext tokenStreamContext47 = filteringGeneratorDelegate46.streamWriteContext();
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder48 = tools.jackson.core.json.JsonFactory.builderWithJackson2Defaults();
        tools.jackson.core.SerializableString serializableString49 = null;
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder50 = jsonFactoryBuilder48.rootValueSeparator(serializableString49);
        tools.jackson.core.io.OutputDecorator outputDecorator51 = jsonFactoryBuilder50.outputDecorator();
        tools.jackson.core.io.InputDecorator inputDecorator52 = null;
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder53 = jsonFactoryBuilder50.inputDecorator(inputDecorator52);
        tools.jackson.core.io.SerializedString serializedString54 = tools.jackson.core.PrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
        byte[] byteArray55 = serializedString54.asQuotedUTF8();
        java.lang.String str56 = serializedString54.toString();
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder57 = jsonFactoryBuilder50.rootValueSeparator((tools.jackson.core.SerializableString) serializedString54);
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.JsonGenerator jsonGenerator58 = filteringGeneratorDelegate46.writeTypeId((java.lang.Object) serializedString54);
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
        org.junit.Assert.assertNotNull(tokenFilterContext36);
        org.junit.Assert.assertNull(jsonToken37);
        org.junit.Assert.assertNotNull(tokenFilter38);
        org.junit.Assert.assertNotNull(tokenFilter39);
        org.junit.Assert.assertTrue("'" + boolean41 + "' != '" + true + "'", boolean41 == true);
        org.junit.Assert.assertNotNull(tokenFilter43);
        org.junit.Assert.assertTrue("'" + inclusion44 + "' != '" + tools.jackson.core.filter.TokenFilter.Inclusion.ONLY_INCLUDE_ALL + "'", inclusion44.equals(tools.jackson.core.filter.TokenFilter.Inclusion.ONLY_INCLUDE_ALL));
        org.junit.Assert.assertNotNull(tokenStreamContext47);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder48);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder50);
        org.junit.Assert.assertNull(outputDecorator51);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder53);
        org.junit.Assert.assertNotNull(serializedString54);
        org.junit.Assert.assertNotNull(byteArray55);
        org.junit.Assert.assertArrayEquals(byteArray55, new byte[] { (byte) 32 });
        org.junit.Assert.assertEquals("'" + str56 + "' != '" + " " + "'", str56, " ");
        org.junit.Assert.assertNotNull(jsonFactoryBuilder57);
    }

    @Test
    public void test1580() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1580");
        tools.jackson.core.json.JsonFactory jsonFactory0 = new tools.jackson.core.json.JsonFactory();
        tools.jackson.core.ErrorReportConfiguration errorReportConfiguration1 = jsonFactory0.errorReportConfiguration();
        tools.jackson.core.json.JsonFactory jsonFactory2 = jsonFactory0.copy();
        tools.jackson.core.json.JsonFactory jsonFactory3 = new tools.jackson.core.json.JsonFactory();
        int int4 = jsonFactory3.getFormatReadFeatures();
        tools.jackson.core.ObjectWriteContext objectWriteContext5 = tools.jackson.core.TokenStreamFactory.EMPTY_WRITE_CONTEXT;
        tools.jackson.core.util.BufferRecycler bufferRecycler6 = new tools.jackson.core.util.BufferRecycler();
        tools.jackson.core.io.SegmentedStringWriter segmentedStringWriter7 = new tools.jackson.core.io.SegmentedStringWriter(bufferRecycler6);
        segmentedStringWriter7.write("MODIFIED-FOR-URL/UNKNOWN/0.0.0");
        java.io.Writer writer11 = segmentedStringWriter7.append('a');
        tools.jackson.core.JsonGenerator jsonGenerator12 = jsonFactory3.createGenerator(objectWriteContext5, (java.io.Writer) segmentedStringWriter7);
        java.nio.file.Path path13 = null;
        tools.jackson.core.JsonEncoding jsonEncoding14 = null;
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.JsonGenerator jsonGenerator15 = jsonFactory0.createGenerator(objectWriteContext5, path13, jsonEncoding14);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(errorReportConfiguration1);
        org.junit.Assert.assertNotNull(jsonFactory2);
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 0 + "'", int4 == 0);
        org.junit.Assert.assertNotNull(objectWriteContext5);
        org.junit.Assert.assertNotNull(writer11);
        org.junit.Assert.assertNotNull(jsonGenerator12);
    }

    @Test
    public void test1581() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1581");
        tools.jackson.core.exc.StreamReadException streamReadException1 = new tools.jackson.core.exc.StreamReadException("");
        java.lang.String str2 = streamReadException1.getPathReference();
        tools.jackson.core.exc.StreamReadException streamReadException4 = new tools.jackson.core.exc.StreamReadException("");
        streamReadException1.addSuppressed((java.lang.Throwable) streamReadException4);
        java.lang.String str6 = streamReadException1.toString();
        tools.jackson.core.filter.TokenFilter tokenFilter7 = null;
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext8 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter7);
        tools.jackson.core.JsonToken jsonToken9 = tokenFilterContext8.nextTokenToRead();
        tools.jackson.core.filter.TokenFilter tokenFilter10 = tools.jackson.core.filter.TokenFilter.INCLUDE_ALL;
        tools.jackson.core.filter.TokenFilter tokenFilter11 = tokenFilterContext8.checkValue(tokenFilter10);
        java.math.BigDecimal bigDecimal12 = null;
        boolean boolean13 = tokenFilter10.includeNumber(bigDecimal12);
        tools.jackson.core.filter.TokenFilter tokenFilter15 = tokenFilter10.includeRootValue((int) (byte) 1);
        tools.jackson.core.filter.TokenFilter tokenFilter17 = tokenFilter10.includeElement(4);
        tools.jackson.core.JacksonException.Reference reference19 = new tools.jackson.core.JacksonException.Reference((java.lang.Object) tokenFilter10, 53);
        tools.jackson.core.JacksonException jacksonException20 = tools.jackson.core.JacksonException.wrapWithPath((java.lang.Throwable) streamReadException1, reference19);
        java.lang.Object obj21 = streamReadException1.processor();
        org.junit.Assert.assertEquals("'" + str2 + "' != '" + "" + "'", str2, "");
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "tools.jackson.core.exc.StreamReadException: " + "'", str6, "tools.jackson.core.exc.StreamReadException: ");
        org.junit.Assert.assertNotNull(tokenFilterContext8);
        org.junit.Assert.assertNull(jsonToken9);
        org.junit.Assert.assertNotNull(tokenFilter10);
        org.junit.Assert.assertNotNull(tokenFilter11);
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + true + "'", boolean13 == true);
        org.junit.Assert.assertNotNull(tokenFilter15);
        org.junit.Assert.assertNotNull(tokenFilter17);
        org.junit.Assert.assertNotNull(jacksonException20);
        org.junit.Assert.assertNull(obj21);
    }

    @Test
    public void test1582() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1582");
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder0 = tools.jackson.core.json.JsonFactory.builder();
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder2 = jsonFactoryBuilder0.highestNonEscapedChar((-324));
        tools.jackson.core.util.JsonpCharacterEscapes jsonpCharacterEscapes3 = tools.jackson.core.util.JsonpCharacterEscapes.instance();
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder4 = jsonFactoryBuilder2.characterEscapes((tools.jackson.core.io.CharacterEscapes) jsonpCharacterEscapes3);
        int int5 = jsonFactoryBuilder4.highestNonEscapedChar();
        tools.jackson.core.io.OutputDecorator outputDecorator6 = null;
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder7 = jsonFactoryBuilder4.outputDecorator(outputDecorator6);
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder9 = jsonFactoryBuilder7.rootValueSeparator("");
        tools.jackson.core.json.JsonReadFeature jsonReadFeature10 = null;
        tools.jackson.core.json.JsonReadFeature[] jsonReadFeatureArray11 = new tools.jackson.core.json.JsonReadFeature[] {};
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder12 = jsonFactoryBuilder7.enable(jsonReadFeature10, jsonReadFeatureArray11);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(jsonFactoryBuilder0);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder2);
        org.junit.Assert.assertNotNull(jsonpCharacterEscapes3);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder4);
        org.junit.Assert.assertTrue("'" + int5 + "' != '" + 0 + "'", int5 == 0);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder7);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder9);
        org.junit.Assert.assertNotNull(jsonReadFeatureArray11);
        org.junit.Assert.assertArrayEquals(jsonReadFeatureArray11, new tools.jackson.core.json.JsonReadFeature[] {});
    }

    @Test
    public void test1583() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1583");
        tools.jackson.core.JsonGenerator jsonGenerator0 = null;
        java.lang.Throwable throwable2 = null;
        tools.jackson.core.exc.StreamWriteException streamWriteException3 = new tools.jackson.core.exc.StreamWriteException(jsonGenerator0, "hi!", throwable2);
        java.lang.String str4 = streamWriteException3.toString();
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter5 = new tools.jackson.core.util.MinimalPrettyPrinter();
        tools.jackson.core.JsonGenerator jsonGenerator6 = null;
        minimalPrettyPrinter5.beforeObjectEntries(jsonGenerator6);
        tools.jackson.core.util.Separators separators11 = new tools.jackson.core.util.Separators('~', ' ', ' ');
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter12 = minimalPrettyPrinter5.setSeparators(separators11);
        tools.jackson.core.JsonGenerator jsonGenerator13 = null;
        tools.jackson.core.util.JsonGeneratorDelegate jsonGeneratorDelegate14 = new tools.jackson.core.util.JsonGeneratorDelegate(jsonGenerator13);
        minimalPrettyPrinter5.beforeObjectEntries((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate14);
        tools.jackson.core.filter.TokenFilter tokenFilter16 = null;
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext17 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter16);
        tools.jackson.core.JsonToken jsonToken18 = tokenFilterContext17.nextTokenToRead();
        tools.jackson.core.filter.TokenFilter tokenFilter19 = tools.jackson.core.filter.TokenFilter.INCLUDE_ALL;
        tools.jackson.core.filter.TokenFilter tokenFilter20 = tokenFilterContext17.checkValue(tokenFilter19);
        java.math.BigDecimal bigDecimal21 = null;
        boolean boolean22 = tokenFilter19.includeNumber(bigDecimal21);
        tools.jackson.core.filter.TokenFilter tokenFilter24 = tokenFilter19.includeRootValue((int) (byte) 1);
        tools.jackson.core.io.IOContext iOContext25 = null;
        java.io.InputStream inputStream26 = null;
        byte[] byteArray30 = new byte[] { (byte) 100, (byte) 1 };
        tools.jackson.core.io.UTF32Reader uTF32Reader34 = new tools.jackson.core.io.UTF32Reader(iOContext25, inputStream26, false, byteArray30, (int) (short) 0, (-1), false);
        boolean boolean36 = tokenFilter24.includeString((java.io.Reader) uTF32Reader34, (-1074));
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion37 = tools.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_NON_NULL;
        tools.jackson.core.filter.FilteringGeneratorDelegate filteringGeneratorDelegate39 = new tools.jackson.core.filter.FilteringGeneratorDelegate((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate14, tokenFilter24, inclusion37, true);
        tools.jackson.core.filter.TokenFilter tokenFilter40 = null;
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext41 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter40);
        tools.jackson.core.JsonToken jsonToken42 = tokenFilterContext41.nextTokenToRead();
        tools.jackson.core.filter.TokenFilter tokenFilter43 = tools.jackson.core.filter.TokenFilter.INCLUDE_ALL;
        tools.jackson.core.filter.TokenFilter tokenFilter44 = tokenFilterContext41.checkValue(tokenFilter43);
        java.math.BigDecimal bigDecimal45 = null;
        boolean boolean46 = tokenFilter43.includeNumber(bigDecimal45);
        tools.jackson.core.filter.TokenFilter tokenFilter48 = tokenFilter43.includeRootValue((int) (byte) 1);
        tools.jackson.core.filter.TokenFilter tokenFilter50 = tokenFilter43.includeElement(4);
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion51 = tools.jackson.core.filter.TokenFilter.Inclusion.ONLY_INCLUDE_ALL;
        tools.jackson.core.filter.FilteringGeneratorDelegate filteringGeneratorDelegate53 = new tools.jackson.core.filter.FilteringGeneratorDelegate((tools.jackson.core.JsonGenerator) filteringGeneratorDelegate39, tokenFilter50, inclusion51, false);
        tools.jackson.core.exc.StreamWriteException streamWriteException54 = streamWriteException3.withGenerator((tools.jackson.core.JsonGenerator) filteringGeneratorDelegate53);
        java.lang.Object obj55 = null;
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.JsonGenerator jsonGenerator57 = filteringGeneratorDelegate53.writeStartArray(obj55, (-323));
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "tools.jackson.core.exc.StreamWriteException: hi!\n at [No location information]" + "'", str4, "tools.jackson.core.exc.StreamWriteException: hi!\n at [No location information]");
        org.junit.Assert.assertNotNull(minimalPrettyPrinter12);
        org.junit.Assert.assertNotNull(tokenFilterContext17);
        org.junit.Assert.assertNull(jsonToken18);
        org.junit.Assert.assertNotNull(tokenFilter19);
        org.junit.Assert.assertNotNull(tokenFilter20);
        org.junit.Assert.assertTrue("'" + boolean22 + "' != '" + true + "'", boolean22 == true);
        org.junit.Assert.assertNotNull(tokenFilter24);
        org.junit.Assert.assertNotNull(byteArray30);
        org.junit.Assert.assertArrayEquals(byteArray30, new byte[] { (byte) 100, (byte) 1 });
        org.junit.Assert.assertTrue("'" + boolean36 + "' != '" + true + "'", boolean36 == true);
        org.junit.Assert.assertTrue("'" + inclusion37 + "' != '" + tools.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_NON_NULL + "'", inclusion37.equals(tools.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_NON_NULL));
        org.junit.Assert.assertNotNull(tokenFilterContext41);
        org.junit.Assert.assertNull(jsonToken42);
        org.junit.Assert.assertNotNull(tokenFilter43);
        org.junit.Assert.assertNotNull(tokenFilter44);
        org.junit.Assert.assertTrue("'" + boolean46 + "' != '" + true + "'", boolean46 == true);
        org.junit.Assert.assertNotNull(tokenFilter48);
        org.junit.Assert.assertNotNull(tokenFilter50);
        org.junit.Assert.assertTrue("'" + inclusion51 + "' != '" + tools.jackson.core.filter.TokenFilter.Inclusion.ONLY_INCLUDE_ALL + "'", inclusion51.equals(tools.jackson.core.filter.TokenFilter.Inclusion.ONLY_INCLUDE_ALL));
        org.junit.Assert.assertNotNull(streamWriteException54);
    }

    @Test
    public void test1584() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1584");
        tools.jackson.core.json.DupDetector dupDetector2 = null;
        tools.jackson.core.util.SimpleStreamReadContext simpleStreamReadContext3 = tools.jackson.core.util.SimpleStreamReadContext.createRootContext(55296, 0, dupDetector2);
        tools.jackson.core.util.SimpleStreamReadContext simpleStreamReadContext6 = simpleStreamReadContext3.createChildObjectContext((int) (byte) -1, 971);
        tools.jackson.core.util.SimpleStreamReadContext simpleStreamReadContext9 = simpleStreamReadContext6.createChildArrayContext(1, (-323));
        tools.jackson.core.json.DupDetector dupDetector10 = simpleStreamReadContext6.getDupDetector();
        tools.jackson.core.util.SimpleStreamReadContext simpleStreamReadContext11 = simpleStreamReadContext6.getParent();
        java.lang.String str12 = simpleStreamReadContext6.typeDesc();
        simpleStreamReadContext6.setCurrentName(" /");
        org.junit.Assert.assertNotNull(simpleStreamReadContext3);
        org.junit.Assert.assertNotNull(simpleStreamReadContext6);
        org.junit.Assert.assertNotNull(simpleStreamReadContext9);
        org.junit.Assert.assertNull(dupDetector10);
        org.junit.Assert.assertNotNull(simpleStreamReadContext11);
        org.junit.Assert.assertEquals("'" + str12 + "' != '" + "Object" + "'", str12, "Object");
    }

    @Test
    public void test1585() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1585");
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
        boolean boolean22 = jsonParserSequence20.isExpectedStartArrayToken();
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean23 = jsonParserSequence20.canParseAsync();
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
        org.junit.Assert.assertTrue("'" + boolean22 + "' != '" + false + "'", boolean22 == false);
    }

    @Test
    public void test1586() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1586");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        tools.jackson.core.filter.TokenFilter tokenFilter6 = filteringParserDelegate5.getFilter();
        boolean boolean7 = filteringParserDelegate5.isExpectedStartArrayToken();
        boolean boolean8 = filteringParserDelegate5.hasCurrentToken();
        tools.jackson.core.util.BufferRecycler bufferRecycler9 = null;
        tools.jackson.core.util.ByteArrayBuilder byteArrayBuilder10 = new tools.jackson.core.util.ByteArrayBuilder(bufferRecycler9);
        byte[] byteArray12 = byteArrayBuilder10.completeAndCoalesce(10);
        byte[] byteArray13 = byteArrayBuilder10.toByteArray();
        byteArrayBuilder10.close();
        byteArrayBuilder10.flush();
        byteArrayBuilder10.reset();
        int int17 = filteringParserDelegate5.releaseBuffered((java.io.OutputStream) byteArrayBuilder10);
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.JsonParser.NumberTypeFP numberTypeFP18 = filteringParserDelegate5.getNumberTypeFP();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(tokenFilter6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertNotNull(byteArray12);
        org.junit.Assert.assertArrayEquals(byteArray12, new byte[] { (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0 });
        org.junit.Assert.assertNotNull(byteArray13);
        org.junit.Assert.assertArrayEquals(byteArray13, new byte[] { (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0 });
        org.junit.Assert.assertTrue("'" + int17 + "' != '" + (-1) + "'", int17 == (-1));
    }

    @Test
    public void test1587() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1587");
        tools.jackson.core.Base64Variant base64Variant0 = tools.jackson.core.Base64Variants.MODIFIED_FOR_URL;
        boolean boolean2 = base64Variant0.equals((java.lang.Object) 3);
        tools.jackson.core.Base64Variant base64Variant3 = tools.jackson.core.Base64Variants.MODIFIED_FOR_URL;
        byte[] byteArray11 = new byte[] { (byte) 10, (byte) -1, (byte) -1, (byte) -1, (byte) -1 };
        int int13 = base64Variant3.encodeBase64Partial(10, (int) 'a', byteArray11, (int) (byte) 1);
        tools.jackson.core.Base64Variant base64Variant16 = new tools.jackson.core.Base64Variant(base64Variant3, "hi!", 0);
        tools.jackson.core.Base64Variant base64Variant17 = base64Variant3.withPaddingAllowed();
        tools.jackson.core.Base64Variant base64Variant18 = tools.jackson.core.Base64Variants.MODIFIED_FOR_URL;
        byte[] byteArray26 = new byte[] { (byte) 10, (byte) -1, (byte) -1, (byte) -1, (byte) -1 };
        int int28 = base64Variant18.encodeBase64Partial(10, (int) 'a', byteArray26, (int) (byte) 1);
        java.lang.String str31 = base64Variant3.encode(byteArray26, false, "//0.0.0");
        tools.jackson.core.Base64Variant base64Variant32 = tools.jackson.core.Base64Variants.MODIFIED_FOR_URL;
        byte[] byteArray40 = new byte[] { (byte) 10, (byte) -1, (byte) -1, (byte) -1, (byte) -1 };
        int int42 = base64Variant32.encodeBase64Partial(10, (int) 'a', byteArray40, (int) (byte) 1);
        tools.jackson.core.Base64Variant base64Variant45 = new tools.jackson.core.Base64Variant(base64Variant32, "hi!", 0);
        tools.jackson.core.Base64Variant base64Variant46 = base64Variant32.withPaddingForbidden();
        boolean boolean47 = base64Variant32.acceptsPaddingOnRead();
        char char49 = base64Variant32.encodeBase64BitsAsChar(1);
        tools.jackson.core.io.IOContext iOContext50 = null;
        java.io.InputStream inputStream51 = null;
        byte[] byteArray55 = new byte[] { (byte) 100, (byte) 1 };
        tools.jackson.core.io.UTF32Reader uTF32Reader59 = new tools.jackson.core.io.UTF32Reader(iOContext50, inputStream51, false, byteArray55, (int) (short) 0, (-1), false);
        java.lang.String str60 = base64Variant32.encode(byteArray55);
        boolean boolean61 = base64Variant32.acceptsPaddingOnRead();
        tools.jackson.core.util.Named[] namedArray62 = new tools.jackson.core.util.Named[] { base64Variant0, base64Variant3, base64Variant32 };
        java.util.ArrayList<tools.jackson.core.util.Named> namedList63 = new java.util.ArrayList<tools.jackson.core.util.Named>();
        boolean boolean64 = java.util.Collections.addAll((java.util.Collection<tools.jackson.core.util.Named>) namedList63, namedArray62);
        java.util.List<java.lang.String> strList66 = tools.jackson.core.sym.PropertyNameMatcher.stringsFromNames((java.util.List<tools.jackson.core.util.Named>) namedList63, true);
        tools.jackson.core.sym.BinaryNameMatcher binaryNameMatcher68 = tools.jackson.core.sym.BinaryNameMatcher.constructFrom((java.util.List<tools.jackson.core.util.Named>) namedList63, false);
        tools.jackson.core.JsonToken jsonToken69 = null;
        tools.jackson.core.json.DupDetector dupDetector70 = null;
        tools.jackson.core.util.SimpleStreamWriteContext simpleStreamWriteContext71 = tools.jackson.core.util.SimpleStreamWriteContext.createRootContext(dupDetector70);
        tools.jackson.core.json.DupDetector dupDetector72 = simpleStreamWriteContext71.getDupDetector();
        tools.jackson.core.json.DupDetector dupDetector73 = null;
        tools.jackson.core.util.SimpleStreamWriteContext simpleStreamWriteContext74 = simpleStreamWriteContext71.withDupDetector(dupDetector73);
        java.lang.String str75 = simpleStreamWriteContext74.typeDesc();
        boolean boolean76 = simpleStreamWriteContext74.hasCurrentName();
        tools.jackson.core.type.WritableTypeId writableTypeId77 = new tools.jackson.core.type.WritableTypeId((java.lang.Object) binaryNameMatcher68, jsonToken69, (java.lang.Object) simpleStreamWriteContext74);
        tools.jackson.core.util.DefaultIndenter defaultIndenter78 = new tools.jackson.core.util.DefaultIndenter();
        java.lang.String str79 = defaultIndenter78.getEol();
        tools.jackson.core.util.DefaultIndenter defaultIndenter81 = defaultIndenter78.withLinefeed("AAAAAAAAAAAAAA");
        tools.jackson.core.util.DefaultIndenter defaultIndenter83 = defaultIndenter81.withIndent("0.0");
        tools.jackson.core.util.DefaultIndenter defaultIndenter85 = defaultIndenter83.withLinefeed("\"AAAAAAAAAAAAAA\"");
        tools.jackson.core.util.SimpleStreamWriteContext simpleStreamWriteContext86 = simpleStreamWriteContext74.createChildObjectContext((java.lang.Object) defaultIndenter85);
        tools.jackson.core.util.DefaultIndenter defaultIndenter88 = defaultIndenter85.withLinefeed("0.0");
        org.junit.Assert.assertNotNull(base64Variant0);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(base64Variant3);
        org.junit.Assert.assertNotNull(byteArray11);
        org.junit.Assert.assertArrayEquals(byteArray11, new byte[] { (byte) 10, (byte) 65, (byte) 65, (byte) -1, (byte) -1 });
        org.junit.Assert.assertTrue("'" + int13 + "' != '" + 3 + "'", int13 == 3);
        org.junit.Assert.assertNotNull(base64Variant17);
        org.junit.Assert.assertNotNull(base64Variant18);
        org.junit.Assert.assertNotNull(byteArray26);
        org.junit.Assert.assertArrayEquals(byteArray26, new byte[] { (byte) 10, (byte) 65, (byte) 65, (byte) -1, (byte) -1 });
        org.junit.Assert.assertTrue("'" + int28 + "' != '" + 3 + "'", int28 == 3);
        org.junit.Assert.assertEquals("'" + str31 + "' != '" + "CkFB__8" + "'", str31, "CkFB__8");
        org.junit.Assert.assertNotNull(base64Variant32);
        org.junit.Assert.assertNotNull(byteArray40);
        org.junit.Assert.assertArrayEquals(byteArray40, new byte[] { (byte) 10, (byte) 65, (byte) 65, (byte) -1, (byte) -1 });
        org.junit.Assert.assertTrue("'" + int42 + "' != '" + 3 + "'", int42 == 3);
        org.junit.Assert.assertNotNull(base64Variant46);
        org.junit.Assert.assertTrue("'" + boolean47 + "' != '" + false + "'", boolean47 == false);
        org.junit.Assert.assertTrue("'" + char49 + "' != '" + 'B' + "'", char49 == 'B');
        org.junit.Assert.assertNotNull(byteArray55);
        org.junit.Assert.assertArrayEquals(byteArray55, new byte[] { (byte) 100, (byte) 1 });
        org.junit.Assert.assertEquals("'" + str60 + "' != '" + "ZAE" + "'", str60, "ZAE");
        org.junit.Assert.assertTrue("'" + boolean61 + "' != '" + false + "'", boolean61 == false);
        org.junit.Assert.assertNotNull(namedArray62);
        org.junit.Assert.assertTrue("'" + boolean64 + "' != '" + true + "'", boolean64 == true);
        org.junit.Assert.assertNotNull(strList66);
        org.junit.Assert.assertNotNull(binaryNameMatcher68);
        org.junit.Assert.assertNotNull(simpleStreamWriteContext71);
        org.junit.Assert.assertNull(dupDetector72);
        org.junit.Assert.assertNotNull(simpleStreamWriteContext74);
        org.junit.Assert.assertEquals("'" + str75 + "' != '" + "root" + "'", str75, "root");
        org.junit.Assert.assertTrue("'" + boolean76 + "' != '" + false + "'", boolean76 == false);
        org.junit.Assert.assertEquals("'" + str79 + "' != '" + "\r\n" + "'", str79, "\r\n");
        org.junit.Assert.assertNotNull(defaultIndenter81);
        org.junit.Assert.assertNotNull(defaultIndenter83);
        org.junit.Assert.assertNotNull(defaultIndenter85);
        org.junit.Assert.assertNotNull(simpleStreamWriteContext86);
        org.junit.Assert.assertNotNull(defaultIndenter88);
    }

    @Test
    public void test1588() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1588");
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
            tools.jackson.core.Version version35 = filteringGeneratorDelegate34.version();
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
    public void test1589() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1589");
        tools.jackson.core.sym.ByteQuadsCanonicalizer byteQuadsCanonicalizer0 = tools.jackson.core.sym.ByteQuadsCanonicalizer.createRoot();
        byteQuadsCanonicalizer0.release();
        int int2 = byteQuadsCanonicalizer0.bucketCount();
        int int3 = byteQuadsCanonicalizer0.bucketCount();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str6 = byteQuadsCanonicalizer0.addName("REDACTED (`StreamReadFeature.INCLUDE_SOURCE_IN_LOCATION` disabled)", 0);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: Internal error: Cannot add names to Root symbol table");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteQuadsCanonicalizer0);
        org.junit.Assert.assertTrue("'" + int2 + "' != '" + 0 + "'", int2 == 0);
        org.junit.Assert.assertTrue("'" + int3 + "' != '" + 0 + "'", int3 == 0);
    }

    @Test
    public void test1590() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1590");
        tools.jackson.core.util.BufferRecycler bufferRecycler0 = null;
        tools.jackson.core.util.TextBuffer textBuffer1 = new tools.jackson.core.util.TextBuffer(bufferRecycler0);
        tools.jackson.core.util.BufferRecycler bufferRecycler2 = null;
        tools.jackson.core.util.TextBuffer textBuffer3 = new tools.jackson.core.util.TextBuffer(bufferRecycler2);
        textBuffer3.resetWithString("");
        int int6 = textBuffer3.getCurrentSegmentSize();
        char[] charArray7 = textBuffer3.contentsAsArray();
        char[] charArray8 = textBuffer3.contentsAsArray();
        textBuffer1.resetWithShared(charArray8, (int) ',', 32000);
        textBuffer1.resetWithCopy("tools.jackson.core.exc.StreamReadException: ", 10, 0);
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
        org.junit.Assert.assertNotNull(charArray7);
        org.junit.Assert.assertArrayEquals(charArray7, new char[] {});
        org.junit.Assert.assertNotNull(charArray8);
        org.junit.Assert.assertArrayEquals(charArray8, new char[] {});
    }

    @Test
    public void test1591() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1591");
        tools.jackson.core.JsonGenerator jsonGenerator0 = null;
        java.lang.Throwable throwable2 = null;
        tools.jackson.core.exc.StreamWriteException streamWriteException3 = new tools.jackson.core.exc.StreamWriteException(jsonGenerator0, "hi!", throwable2);
        java.lang.String str4 = streamWriteException3.toString();
        tools.jackson.core.util.BufferRecycler bufferRecycler5 = null;
        tools.jackson.core.util.TextBuffer textBuffer6 = new tools.jackson.core.util.TextBuffer(bufferRecycler5);
        textBuffer6.resetWithString("");
        int int9 = textBuffer6.getCurrentSegmentSize();
        char[] charArray10 = textBuffer6.emptyAndGetCurrentSegment();
        tools.jackson.core.JacksonException jacksonException12 = streamWriteException3.prependPath((java.lang.Object) textBuffer6, "Object");
        tools.jackson.core.json.JsonFactory jsonFactory13 = new tools.jackson.core.json.JsonFactory();
        java.lang.Class<? extends tools.jackson.core.FormatFeature> wildcardClass14 = jsonFactory13.getFormatWriteFeatureType();
        tools.jackson.core.io.CharacterEscapes characterEscapes15 = jsonFactory13.getCharacterEscapes();
        tools.jackson.core.util.BufferRecycler bufferRecycler16 = new tools.jackson.core.util.BufferRecycler();
        tools.jackson.core.io.SegmentedStringWriter segmentedStringWriter17 = new tools.jackson.core.io.SegmentedStringWriter(bufferRecycler16);
        tools.jackson.core.JsonGenerator jsonGenerator18 = jsonFactory13.createGenerator((java.io.Writer) segmentedStringWriter17);
        tools.jackson.core.exc.StreamWriteException streamWriteException19 = streamWriteException3.withGenerator(jsonGenerator18);
        boolean boolean20 = jsonGenerator18.canWriteComments();
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "tools.jackson.core.exc.StreamWriteException: hi!\n at [No location information]" + "'", str4, "tools.jackson.core.exc.StreamWriteException: hi!\n at [No location information]");
        org.junit.Assert.assertTrue("'" + int9 + "' != '" + 0 + "'", int9 == 0);
        org.junit.Assert.assertNotNull(charArray10);
        org.junit.Assert.assertNotNull(jacksonException12);
        org.junit.Assert.assertNotNull(wildcardClass14);
        org.junit.Assert.assertNull(characterEscapes15);
        org.junit.Assert.assertNotNull(jsonGenerator18);
        org.junit.Assert.assertNotNull(streamWriteException19);
        org.junit.Assert.assertTrue("'" + boolean20 + "' != '" + false + "'", boolean20 == false);
    }

    @Test
    public void test1592() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1592");
        tools.jackson.core.json.JsonFactory jsonFactory0 = new tools.jackson.core.json.JsonFactory();
        java.lang.Class<? extends tools.jackson.core.FormatFeature> wildcardClass1 = jsonFactory0.getFormatWriteFeatureType();
        tools.jackson.core.io.CharacterEscapes characterEscapes2 = jsonFactory0.getCharacterEscapes();
        tools.jackson.core.util.BufferRecycler bufferRecycler3 = new tools.jackson.core.util.BufferRecycler();
        tools.jackson.core.io.SegmentedStringWriter segmentedStringWriter4 = new tools.jackson.core.io.SegmentedStringWriter(bufferRecycler3);
        tools.jackson.core.JsonGenerator jsonGenerator5 = jsonFactory0.createGenerator((java.io.Writer) segmentedStringWriter4);
        tools.jackson.core.TokenStreamFactory tokenStreamFactory6 = jsonFactory0.snapshot();
        tools.jackson.core.json.JsonFactory jsonFactory7 = new tools.jackson.core.json.JsonFactory();
        tools.jackson.core.ErrorReportConfiguration errorReportConfiguration8 = jsonFactory7.errorReportConfiguration();
        int int9 = jsonFactory7.getFactoryFeatures();
        tools.jackson.core.json.JsonFactory jsonFactory10 = new tools.jackson.core.json.JsonFactory();
        int int11 = jsonFactory10.getFormatReadFeatures();
        tools.jackson.core.json.JsonFactory jsonFactory12 = new tools.jackson.core.json.JsonFactory();
        int int13 = jsonFactory12.getFormatReadFeatures();
        tools.jackson.core.ObjectWriteContext objectWriteContext14 = tools.jackson.core.TokenStreamFactory.EMPTY_WRITE_CONTEXT;
        tools.jackson.core.util.BufferRecycler bufferRecycler15 = new tools.jackson.core.util.BufferRecycler();
        tools.jackson.core.io.SegmentedStringWriter segmentedStringWriter16 = new tools.jackson.core.io.SegmentedStringWriter(bufferRecycler15);
        segmentedStringWriter16.write("MODIFIED-FOR-URL/UNKNOWN/0.0.0");
        java.io.Writer writer20 = segmentedStringWriter16.append('a');
        tools.jackson.core.JsonGenerator jsonGenerator21 = jsonFactory12.createGenerator(objectWriteContext14, (java.io.Writer) segmentedStringWriter16);
        tools.jackson.core.JsonParser jsonParser22 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter23 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion24 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate27 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser22, tokenFilter23, inclusion24, true, true);
        boolean boolean28 = filteringParserDelegate27.isExpectedStartObjectToken();
        int int29 = filteringParserDelegate27.currentTokenId();
        boolean boolean30 = filteringParserDelegate27.isExpectedStartObjectToken();
        tools.jackson.core.util.BufferRecycler bufferRecycler31 = new tools.jackson.core.util.BufferRecycler();
        tools.jackson.core.io.SegmentedStringWriter segmentedStringWriter32 = new tools.jackson.core.io.SegmentedStringWriter(bufferRecycler31);
        segmentedStringWriter32.write("\"_2QK\\n\"");
        int int35 = filteringParserDelegate27.releaseBuffered((java.io.Writer) segmentedStringWriter32);
        tools.jackson.core.JsonGenerator jsonGenerator36 = jsonFactory10.createGenerator(objectWriteContext14, (java.io.Writer) segmentedStringWriter32);
        tools.jackson.core.util.BufferRecycler bufferRecycler37 = new tools.jackson.core.util.BufferRecycler();
        tools.jackson.core.util.TextBuffer textBuffer38 = new tools.jackson.core.util.TextBuffer(bufferRecycler37);
        tools.jackson.core.io.SegmentedStringWriter segmentedStringWriter39 = new tools.jackson.core.io.SegmentedStringWriter(bufferRecycler37);
        tools.jackson.core.util.ByteArrayBuilder byteArrayBuilder40 = new tools.jackson.core.util.ByteArrayBuilder(bufferRecycler37);
        tools.jackson.core.JsonGenerator jsonGenerator41 = jsonFactory7.createGenerator(objectWriteContext14, (java.io.OutputStream) byteArrayBuilder40);
        java.io.DataOutput dataOutput42 = null;
        tools.jackson.core.JsonGenerator jsonGenerator43 = jsonFactory0.createGenerator(objectWriteContext14, dataOutput42);
        org.junit.Assert.assertNotNull(wildcardClass1);
        org.junit.Assert.assertNull(characterEscapes2);
        org.junit.Assert.assertNotNull(jsonGenerator5);
        org.junit.Assert.assertNotNull(tokenStreamFactory6);
        org.junit.Assert.assertNotNull(errorReportConfiguration8);
        org.junit.Assert.assertTrue("'" + int9 + "' != '" + 14 + "'", int9 == 14);
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 0 + "'", int11 == 0);
        org.junit.Assert.assertTrue("'" + int13 + "' != '" + 0 + "'", int13 == 0);
        org.junit.Assert.assertNotNull(objectWriteContext14);
        org.junit.Assert.assertNotNull(writer20);
        org.junit.Assert.assertNotNull(jsonGenerator21);
        org.junit.Assert.assertTrue("'" + boolean28 + "' != '" + false + "'", boolean28 == false);
        org.junit.Assert.assertTrue("'" + int29 + "' != '" + 0 + "'", int29 == 0);
        org.junit.Assert.assertTrue("'" + boolean30 + "' != '" + false + "'", boolean30 == false);
        org.junit.Assert.assertTrue("'" + int35 + "' != '" + (-1) + "'", int35 == (-1));
        org.junit.Assert.assertNotNull(jsonGenerator36);
        org.junit.Assert.assertNotNull(jsonGenerator41);
        org.junit.Assert.assertNotNull(jsonGenerator43);
    }

    @Test
    public void test1593() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1593");
        tools.jackson.core.util.Separators.Spacing spacing0 = tools.jackson.core.util.Separators.Spacing.NONE;
        org.junit.Assert.assertTrue("'" + spacing0 + "' != '" + tools.jackson.core.util.Separators.Spacing.NONE + "'", spacing0.equals(tools.jackson.core.util.Separators.Spacing.NONE));
    }

    @Test
    public void test1594() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1594");
        tools.jackson.core.JsonPointer jsonPointer0 = tools.jackson.core.JsonPointer.empty();
        tools.jackson.core.filter.JsonPointerBasedFilter jsonPointerBasedFilter1 = new tools.jackson.core.filter.JsonPointerBasedFilter(jsonPointer0);
        tools.jackson.core.filter.TokenFilter tokenFilter3 = jsonPointerBasedFilter1.includeElement(5);
        tools.jackson.core.filter.TokenFilter tokenFilter5 = jsonPointerBasedFilter1.includeElement(15);
        tools.jackson.core.filter.TokenFilter tokenFilter6 = null;
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext7 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter6);
        tools.jackson.core.JsonToken jsonToken8 = tokenFilterContext7.nextTokenToRead();
        tools.jackson.core.filter.TokenFilter tokenFilter9 = tools.jackson.core.filter.TokenFilter.INCLUDE_ALL;
        tools.jackson.core.filter.TokenFilter tokenFilter10 = tokenFilterContext7.checkValue(tokenFilter9);
        java.math.BigDecimal bigDecimal11 = null;
        boolean boolean12 = tokenFilter9.includeNumber(bigDecimal11);
        tools.jackson.core.filter.TokenFilter tokenFilter14 = tokenFilter9.includeRootValue((int) (byte) 1);
        tools.jackson.core.io.IOContext iOContext15 = null;
        java.io.InputStream inputStream16 = null;
        byte[] byteArray20 = new byte[] { (byte) 100, (byte) 1 };
        tools.jackson.core.io.UTF32Reader uTF32Reader24 = new tools.jackson.core.io.UTF32Reader(iOContext15, inputStream16, false, byteArray20, (int) (short) 0, (-1), false);
        boolean boolean26 = tokenFilter14.includeString((java.io.Reader) uTF32Reader24, (-1074));
        boolean boolean28 = jsonPointerBasedFilter1.includeString((java.io.Reader) uTF32Reader24, (int) '\000');
        tools.jackson.core.filter.TokenFilter tokenFilter29 = jsonPointerBasedFilter1.filterStartArray();
        org.junit.Assert.assertNotNull(jsonPointer0);
        org.junit.Assert.assertNull(tokenFilter3);
        org.junit.Assert.assertNull(tokenFilter5);
        org.junit.Assert.assertNotNull(tokenFilterContext7);
        org.junit.Assert.assertNull(jsonToken8);
        org.junit.Assert.assertNotNull(tokenFilter9);
        org.junit.Assert.assertNotNull(tokenFilter10);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + true + "'", boolean12 == true);
        org.junit.Assert.assertNotNull(tokenFilter14);
        org.junit.Assert.assertNotNull(byteArray20);
        org.junit.Assert.assertArrayEquals(byteArray20, new byte[] { (byte) 100, (byte) 1 });
        org.junit.Assert.assertTrue("'" + boolean26 + "' != '" + true + "'", boolean26 == true);
        org.junit.Assert.assertTrue("'" + boolean28 + "' != '" + true + "'", boolean28 == true);
        org.junit.Assert.assertNotNull(tokenFilter29);
    }

    @Test
    public void test1595() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1595");
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter1 = new tools.jackson.core.util.MinimalPrettyPrinter("  ");
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter2 = new tools.jackson.core.util.MinimalPrettyPrinter();
        tools.jackson.core.JsonGenerator jsonGenerator3 = null;
        minimalPrettyPrinter2.beforeObjectEntries(jsonGenerator3);
        tools.jackson.core.util.Separators separators8 = new tools.jackson.core.util.Separators('~', ' ', ' ');
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter9 = minimalPrettyPrinter2.setSeparators(separators8);
        tools.jackson.core.JsonGenerator jsonGenerator10 = null;
        tools.jackson.core.util.JsonGeneratorDelegate jsonGeneratorDelegate11 = new tools.jackson.core.util.JsonGeneratorDelegate(jsonGenerator10);
        minimalPrettyPrinter2.beforeObjectEntries((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate11);
        tools.jackson.core.filter.TokenFilter tokenFilter13 = null;
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext14 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter13);
        tools.jackson.core.JsonToken jsonToken15 = tokenFilterContext14.nextTokenToRead();
        tools.jackson.core.filter.TokenFilter tokenFilter16 = tools.jackson.core.filter.TokenFilter.INCLUDE_ALL;
        tools.jackson.core.filter.TokenFilter tokenFilter17 = tokenFilterContext14.checkValue(tokenFilter16);
        java.math.BigDecimal bigDecimal18 = null;
        boolean boolean19 = tokenFilter16.includeNumber(bigDecimal18);
        tools.jackson.core.filter.TokenFilter tokenFilter21 = tokenFilter16.includeRootValue((int) (byte) 1);
        tools.jackson.core.io.IOContext iOContext22 = null;
        java.io.InputStream inputStream23 = null;
        byte[] byteArray27 = new byte[] { (byte) 100, (byte) 1 };
        tools.jackson.core.io.UTF32Reader uTF32Reader31 = new tools.jackson.core.io.UTF32Reader(iOContext22, inputStream23, false, byteArray27, (int) (short) 0, (-1), false);
        boolean boolean33 = tokenFilter21.includeString((java.io.Reader) uTF32Reader31, (-1074));
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion34 = tools.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_NON_NULL;
        tools.jackson.core.filter.FilteringGeneratorDelegate filteringGeneratorDelegate36 = new tools.jackson.core.filter.FilteringGeneratorDelegate((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate11, tokenFilter21, inclusion34, true);
        minimalPrettyPrinter1.beforeArrayValues((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate11);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj38 = jsonGeneratorDelegate11.streamWriteOutputTarget();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(minimalPrettyPrinter9);
        org.junit.Assert.assertNotNull(tokenFilterContext14);
        org.junit.Assert.assertNull(jsonToken15);
        org.junit.Assert.assertNotNull(tokenFilter16);
        org.junit.Assert.assertNotNull(tokenFilter17);
        org.junit.Assert.assertTrue("'" + boolean19 + "' != '" + true + "'", boolean19 == true);
        org.junit.Assert.assertNotNull(tokenFilter21);
        org.junit.Assert.assertNotNull(byteArray27);
        org.junit.Assert.assertArrayEquals(byteArray27, new byte[] { (byte) 100, (byte) 1 });
        org.junit.Assert.assertTrue("'" + boolean33 + "' != '" + true + "'", boolean33 == true);
        org.junit.Assert.assertTrue("'" + inclusion34 + "' != '" + tools.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_NON_NULL + "'", inclusion34.equals(tools.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_NON_NULL));
    }

    @Test
    public void test1596() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1596");
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder0 = tools.jackson.core.json.JsonFactory.builderWithJackson2Defaults();
        tools.jackson.core.SerializableString serializableString1 = null;
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder2 = jsonFactoryBuilder0.rootValueSeparator(serializableString1);
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder4 = jsonFactoryBuilder0.quoteChar(' ');
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder6 = jsonFactoryBuilder0.rootValueSeparator("6.5577783776342712E18");
        org.junit.Assert.assertNotNull(jsonFactoryBuilder0);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder2);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder4);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder6);
    }

    @Test
    public void test1597() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1597");
        tools.jackson.core.StreamReadConstraints streamReadConstraints0 = tools.jackson.core.StreamReadConstraints.defaults();
        boolean boolean1 = streamReadConstraints0.hasMaxDocumentLength();
        int int2 = streamReadConstraints0.getMaxNumberLength();
        long long3 = streamReadConstraints0.getMaxDocumentLength();
        tools.jackson.core.StreamWriteConstraints.Builder builder4 = tools.jackson.core.StreamWriteConstraints.builder();
        tools.jackson.core.StreamWriteConstraints streamWriteConstraints5 = builder4.build();
        tools.jackson.core.json.JsonFactory jsonFactory6 = new tools.jackson.core.json.JsonFactory();
        tools.jackson.core.ErrorReportConfiguration errorReportConfiguration7 = jsonFactory6.errorReportConfiguration();
        tools.jackson.core.util.BufferRecycler bufferRecycler8 = new tools.jackson.core.util.BufferRecycler();
        tools.jackson.core.io.SegmentedStringWriter segmentedStringWriter9 = new tools.jackson.core.io.SegmentedStringWriter(bufferRecycler8);
        segmentedStringWriter9.write("MODIFIED-FOR-URL/UNKNOWN/0.0.0");
        tools.jackson.core.util.BufferRecycler bufferRecycler12 = segmentedStringWriter9.bufferRecycler();
        tools.jackson.core.io.ContentReference contentReference13 = tools.jackson.core.io.ContentReference.redacted();
        tools.jackson.core.TokenStreamLocation tokenStreamLocation17 = new tools.jackson.core.TokenStreamLocation(contentReference13, (long) (short) 10, 33, 33);
        java.lang.Object obj18 = contentReference13.getRawContent();
        tools.jackson.core.JsonEncoding jsonEncoding20 = null;
        tools.jackson.core.io.IOContext iOContext21 = new tools.jackson.core.io.IOContext(streamReadConstraints0, streamWriteConstraints5, errorReportConfiguration7, bufferRecycler12, contentReference13, false, jsonEncoding20);
        tools.jackson.core.StreamReadConstraints streamReadConstraints22 = iOContext21.streamReadConstraints();
        tools.jackson.core.StreamWriteConstraints streamWriteConstraints23 = iOContext21.streamWriteConstraints();
        tools.jackson.core.util.TextBuffer textBuffer24 = iOContext21.constructReadConstrainedTextBuffer();
        tools.jackson.core.JsonEncoding jsonEncoding25 = null;
        tools.jackson.core.io.IOContext iOContext26 = iOContext21.setEncoding(jsonEncoding25);
        org.junit.Assert.assertNotNull(streamReadConstraints0);
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
        org.junit.Assert.assertTrue("'" + int2 + "' != '" + 1000 + "'", int2 == 1000);
        org.junit.Assert.assertTrue("'" + long3 + "' != '" + (-1L) + "'", long3 == (-1L));
        org.junit.Assert.assertNotNull(builder4);
        org.junit.Assert.assertNotNull(streamWriteConstraints5);
        org.junit.Assert.assertNotNull(errorReportConfiguration7);
        org.junit.Assert.assertNotNull(bufferRecycler12);
        org.junit.Assert.assertNotNull(contentReference13);
        org.junit.Assert.assertNull(obj18);
        org.junit.Assert.assertNotNull(streamReadConstraints22);
        org.junit.Assert.assertNotNull(streamWriteConstraints23);
        org.junit.Assert.assertNotNull(textBuffer24);
        org.junit.Assert.assertNotNull(iOContext26);
    }

    @Test
    public void test1598() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1598");
        tools.jackson.core.io.ContentReference contentReference0 = tools.jackson.core.io.ContentReference.redacted();
        tools.jackson.core.TokenStreamLocation tokenStreamLocation4 = new tools.jackson.core.TokenStreamLocation(contentReference0, (long) (short) 10, 33, 33);
        java.lang.Object obj5 = contentReference0.getRawContent();
        java.lang.String str6 = contentReference0.buildSourceDescription();
        java.lang.String str7 = contentReference0.buildSourceDescription();
        org.junit.Assert.assertNotNull(contentReference0);
        org.junit.Assert.assertNull(obj5);
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "REDACTED (`StreamReadFeature.INCLUDE_SOURCE_IN_LOCATION` disabled)" + "'", str6, "REDACTED (`StreamReadFeature.INCLUDE_SOURCE_IN_LOCATION` disabled)");
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "REDACTED (`StreamReadFeature.INCLUDE_SOURCE_IN_LOCATION` disabled)" + "'", str7, "REDACTED (`StreamReadFeature.INCLUDE_SOURCE_IN_LOCATION` disabled)");
    }

    @Test
    public void test1599() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1599");
        tools.jackson.core.util.DefaultPrettyPrinter.NopIndenter nopIndenter0 = tools.jackson.core.util.DefaultPrettyPrinter.NopIndenter.instance();
        boolean boolean1 = nopIndenter0.isInline();
        tools.jackson.core.util.DefaultPrettyPrinter.NopIndenter nopIndenter2 = new tools.jackson.core.util.DefaultPrettyPrinter.NopIndenter();
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter3 = new tools.jackson.core.util.MinimalPrettyPrinter();
        tools.jackson.core.JsonGenerator jsonGenerator4 = null;
        minimalPrettyPrinter3.beforeObjectEntries(jsonGenerator4);
        tools.jackson.core.util.Separators separators9 = new tools.jackson.core.util.Separators('~', ' ', ' ');
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter10 = minimalPrettyPrinter3.setSeparators(separators9);
        tools.jackson.core.JsonGenerator jsonGenerator11 = null;
        tools.jackson.core.util.JsonGeneratorDelegate jsonGeneratorDelegate12 = new tools.jackson.core.util.JsonGeneratorDelegate(jsonGenerator11);
        minimalPrettyPrinter3.beforeObjectEntries((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate12);
        tools.jackson.core.filter.TokenFilter tokenFilter14 = null;
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext15 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter14);
        tools.jackson.core.JsonToken jsonToken16 = tokenFilterContext15.nextTokenToRead();
        tools.jackson.core.filter.TokenFilter tokenFilter17 = tools.jackson.core.filter.TokenFilter.INCLUDE_ALL;
        tools.jackson.core.filter.TokenFilter tokenFilter18 = tokenFilterContext15.checkValue(tokenFilter17);
        java.math.BigDecimal bigDecimal19 = null;
        boolean boolean20 = tokenFilter17.includeNumber(bigDecimal19);
        tools.jackson.core.filter.TokenFilter tokenFilter22 = tokenFilter17.includeRootValue((int) (byte) 1);
        tools.jackson.core.io.IOContext iOContext23 = null;
        java.io.InputStream inputStream24 = null;
        byte[] byteArray28 = new byte[] { (byte) 100, (byte) 1 };
        tools.jackson.core.io.UTF32Reader uTF32Reader32 = new tools.jackson.core.io.UTF32Reader(iOContext23, inputStream24, false, byteArray28, (int) (short) 0, (-1), false);
        boolean boolean34 = tokenFilter22.includeString((java.io.Reader) uTF32Reader32, (-1074));
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion35 = tools.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_NON_NULL;
        tools.jackson.core.filter.FilteringGeneratorDelegate filteringGeneratorDelegate37 = new tools.jackson.core.filter.FilteringGeneratorDelegate((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate12, tokenFilter22, inclusion35, true);
        nopIndenter2.writeIndentation((tools.jackson.core.JsonGenerator) filteringGeneratorDelegate37, (int) '#');
        nopIndenter0.writeIndentation((tools.jackson.core.JsonGenerator) filteringGeneratorDelegate37, (-149));
        tools.jackson.core.exc.StreamWriteException streamWriteException43 = new tools.jackson.core.exc.StreamWriteException((tools.jackson.core.JsonGenerator) filteringGeneratorDelegate37, "0.0.0");
        char[] charArray45 = tools.jackson.core.io.CharTypes.copyHexChars(false);
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.JsonGenerator jsonGenerator48 = filteringGeneratorDelegate37.writeString(charArray45, 18730, 33);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(nopIndenter0);
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + true + "'", boolean1 == true);
        org.junit.Assert.assertNotNull(minimalPrettyPrinter10);
        org.junit.Assert.assertNotNull(tokenFilterContext15);
        org.junit.Assert.assertNull(jsonToken16);
        org.junit.Assert.assertNotNull(tokenFilter17);
        org.junit.Assert.assertNotNull(tokenFilter18);
        org.junit.Assert.assertTrue("'" + boolean20 + "' != '" + true + "'", boolean20 == true);
        org.junit.Assert.assertNotNull(tokenFilter22);
        org.junit.Assert.assertNotNull(byteArray28);
        org.junit.Assert.assertArrayEquals(byteArray28, new byte[] { (byte) 100, (byte) 1 });
        org.junit.Assert.assertTrue("'" + boolean34 + "' != '" + true + "'", boolean34 == true);
        org.junit.Assert.assertTrue("'" + inclusion35 + "' != '" + tools.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_NON_NULL + "'", inclusion35.equals(tools.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_NON_NULL));
        org.junit.Assert.assertNotNull(charArray45);
        org.junit.Assert.assertArrayEquals(charArray45, new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' });
    }

    @Test
    public void test1600() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1600");
        tools.jackson.core.util.InternCache internCache0 = tools.jackson.core.util.InternCache.instance;
        tools.jackson.core.json.DupDetector dupDetector1 = null;
        tools.jackson.core.util.SimpleStreamWriteContext simpleStreamWriteContext2 = tools.jackson.core.util.SimpleStreamWriteContext.createRootContext(dupDetector1);
        tools.jackson.core.json.DupDetector dupDetector3 = simpleStreamWriteContext2.getDupDetector();
        boolean boolean4 = simpleStreamWriteContext2.inObject();
        boolean boolean5 = internCache0.containsValue((java.lang.Object) boolean4);
        java.util.Set<java.util.Map.Entry<java.lang.String, java.lang.String>> strEntrySet6 = internCache0.entrySet();
        java.lang.String str9 = internCache0.put("0.0.0", "[JsonPointerFilter at: ]");
        org.junit.Assert.assertNotNull(internCache0);
        org.junit.Assert.assertNotNull(simpleStreamWriteContext2);
        org.junit.Assert.assertNull(dupDetector3);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertNotNull(strEntrySet6);
        org.junit.Assert.assertNull(str9);
    }

    @Test
    public void test1601() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1601");
        tools.jackson.core.util.DefaultIndenter defaultIndenter2 = new tools.jackson.core.util.DefaultIndenter("", "[JsonPointerFilter at: ]");
    }

    @Test
    public void test1602() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1602");
        tools.jackson.core.StreamReadConstraints.Builder builder0 = tools.jackson.core.StreamReadConstraints.builder();
        tools.jackson.core.StreamReadConstraints.Builder builder2 = builder0.maxStringLength(57343);
        tools.jackson.core.StreamReadConstraints.Builder builder4 = builder0.maxTokenCount((long) 56320);
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(builder2);
        org.junit.Assert.assertNotNull(builder4);
    }

    @Test
    public void test1603() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1603");
        tools.jackson.core.json.JsonFactory jsonFactory0 = new tools.jackson.core.json.JsonFactory();
        tools.jackson.core.ErrorReportConfiguration errorReportConfiguration1 = jsonFactory0.errorReportConfiguration();
        tools.jackson.core.sym.CharsToNameCanonicalizer charsToNameCanonicalizer2 = tools.jackson.core.sym.CharsToNameCanonicalizer.createRoot((tools.jackson.core.TokenStreamFactory) jsonFactory0);
        tools.jackson.core.sym.CharsToNameCanonicalizer charsToNameCanonicalizer3 = charsToNameCanonicalizer2.makeChild();
        boolean boolean4 = charsToNameCanonicalizer3.willInternStrings();
        org.junit.Assert.assertNotNull(errorReportConfiguration1);
        org.junit.Assert.assertNotNull(charsToNameCanonicalizer2);
        org.junit.Assert.assertNotNull(charsToNameCanonicalizer3);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
    }

    @Test
    public void test1604() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1604");
        tools.jackson.core.filter.TokenFilter tokenFilter0 = null;
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext1 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter0);
        tools.jackson.core.JsonGenerator jsonGenerator2 = null;
        tokenFilterContext1.writePath(jsonGenerator2);
        int int4 = tokenFilterContext1.getEntryCount();
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter5 = new tools.jackson.core.util.MinimalPrettyPrinter();
        tools.jackson.core.JsonGenerator jsonGenerator6 = null;
        minimalPrettyPrinter5.beforeObjectEntries(jsonGenerator6);
        tools.jackson.core.util.Separators separators11 = new tools.jackson.core.util.Separators('~', ' ', ' ');
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter12 = minimalPrettyPrinter5.setSeparators(separators11);
        tools.jackson.core.JsonGenerator jsonGenerator13 = null;
        tools.jackson.core.util.JsonGeneratorDelegate jsonGeneratorDelegate14 = new tools.jackson.core.util.JsonGeneratorDelegate(jsonGenerator13);
        minimalPrettyPrinter5.beforeObjectEntries((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate14);
        tools.jackson.core.filter.TokenFilter tokenFilter16 = null;
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext17 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter16);
        tools.jackson.core.JsonToken jsonToken18 = tokenFilterContext17.nextTokenToRead();
        tools.jackson.core.filter.TokenFilter tokenFilter19 = tools.jackson.core.filter.TokenFilter.INCLUDE_ALL;
        tools.jackson.core.filter.TokenFilter tokenFilter20 = tokenFilterContext17.checkValue(tokenFilter19);
        java.math.BigDecimal bigDecimal21 = null;
        boolean boolean22 = tokenFilter19.includeNumber(bigDecimal21);
        tools.jackson.core.filter.TokenFilter tokenFilter24 = tokenFilter19.includeRootValue((int) (byte) 1);
        tools.jackson.core.io.IOContext iOContext25 = null;
        java.io.InputStream inputStream26 = null;
        byte[] byteArray30 = new byte[] { (byte) 100, (byte) 1 };
        tools.jackson.core.io.UTF32Reader uTF32Reader34 = new tools.jackson.core.io.UTF32Reader(iOContext25, inputStream26, false, byteArray30, (int) (short) 0, (-1), false);
        boolean boolean36 = tokenFilter24.includeString((java.io.Reader) uTF32Reader34, (-1074));
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion37 = tools.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_NON_NULL;
        tools.jackson.core.filter.FilteringGeneratorDelegate filteringGeneratorDelegate39 = new tools.jackson.core.filter.FilteringGeneratorDelegate((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate14, tokenFilter24, inclusion37, true);
        tokenFilterContext1.ensurePropertyNameWritten((tools.jackson.core.JsonGenerator) filteringGeneratorDelegate39);
        java.lang.Object obj41 = tokenFilterContext1.currentValue();
        tools.jackson.core.filter.TokenFilter tokenFilter42 = tokenFilterContext1.getFilter();
        int int43 = tokenFilterContext1.getEntryCount();
        org.junit.Assert.assertNotNull(tokenFilterContext1);
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 0 + "'", int4 == 0);
        org.junit.Assert.assertNotNull(minimalPrettyPrinter12);
        org.junit.Assert.assertNotNull(tokenFilterContext17);
        org.junit.Assert.assertNull(jsonToken18);
        org.junit.Assert.assertNotNull(tokenFilter19);
        org.junit.Assert.assertNotNull(tokenFilter20);
        org.junit.Assert.assertTrue("'" + boolean22 + "' != '" + true + "'", boolean22 == true);
        org.junit.Assert.assertNotNull(tokenFilter24);
        org.junit.Assert.assertNotNull(byteArray30);
        org.junit.Assert.assertArrayEquals(byteArray30, new byte[] { (byte) 100, (byte) 1 });
        org.junit.Assert.assertTrue("'" + boolean36 + "' != '" + true + "'", boolean36 == true);
        org.junit.Assert.assertTrue("'" + inclusion37 + "' != '" + tools.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_NON_NULL + "'", inclusion37.equals(tools.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_NON_NULL));
        org.junit.Assert.assertNull(obj41);
        org.junit.Assert.assertNull(tokenFilter42);
        org.junit.Assert.assertTrue("'" + int43 + "' != '" + 0 + "'", int43 == 0);
    }

    @Test
    public void test1605() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1605");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        boolean boolean6 = filteringParserDelegate5.isExpectedStartObjectToken();
        int int7 = filteringParserDelegate5.currentTokenId();
        boolean boolean8 = filteringParserDelegate5.isExpectedStartObjectToken();
        tools.jackson.core.JsonParser jsonParser9 = filteringParserDelegate5.skipChildren();
        tools.jackson.core.util.JsonParserDelegate jsonParserDelegate10 = new tools.jackson.core.util.JsonParserDelegate((tools.jackson.core.JsonParser) filteringParserDelegate5);
        tools.jackson.core.json.DupDetector dupDetector11 = tools.jackson.core.json.DupDetector.rootDetector((tools.jackson.core.JsonParser) filteringParserDelegate5);
        // The following exception was thrown during execution in test generation
        try {
            int int12 = filteringParserDelegate5.getTextLength();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 0 + "'", int7 == 0);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertNotNull(jsonParser9);
        org.junit.Assert.assertNotNull(dupDetector11);
    }

    @Test
    public void test1606() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1606");
        tools.jackson.core.Base64Variant base64Variant0 = tools.jackson.core.Base64Variants.MODIFIED_FOR_URL;
        boolean boolean2 = base64Variant0.equals((java.lang.Object) 3);
        tools.jackson.core.Base64Variant base64Variant3 = tools.jackson.core.Base64Variants.MODIFIED_FOR_URL;
        byte[] byteArray11 = new byte[] { (byte) 10, (byte) -1, (byte) -1, (byte) -1, (byte) -1 };
        int int13 = base64Variant3.encodeBase64Partial(10, (int) 'a', byteArray11, (int) (byte) 1);
        tools.jackson.core.Base64Variant base64Variant16 = new tools.jackson.core.Base64Variant(base64Variant3, "hi!", 0);
        tools.jackson.core.Base64Variant base64Variant17 = base64Variant3.withPaddingAllowed();
        tools.jackson.core.Base64Variant base64Variant18 = tools.jackson.core.Base64Variants.MODIFIED_FOR_URL;
        byte[] byteArray26 = new byte[] { (byte) 10, (byte) -1, (byte) -1, (byte) -1, (byte) -1 };
        int int28 = base64Variant18.encodeBase64Partial(10, (int) 'a', byteArray26, (int) (byte) 1);
        java.lang.String str31 = base64Variant3.encode(byteArray26, false, "//0.0.0");
        tools.jackson.core.Base64Variant base64Variant32 = tools.jackson.core.Base64Variants.MODIFIED_FOR_URL;
        byte[] byteArray40 = new byte[] { (byte) 10, (byte) -1, (byte) -1, (byte) -1, (byte) -1 };
        int int42 = base64Variant32.encodeBase64Partial(10, (int) 'a', byteArray40, (int) (byte) 1);
        tools.jackson.core.Base64Variant base64Variant45 = new tools.jackson.core.Base64Variant(base64Variant32, "hi!", 0);
        tools.jackson.core.Base64Variant base64Variant46 = base64Variant32.withPaddingForbidden();
        boolean boolean47 = base64Variant32.acceptsPaddingOnRead();
        char char49 = base64Variant32.encodeBase64BitsAsChar(1);
        tools.jackson.core.io.IOContext iOContext50 = null;
        java.io.InputStream inputStream51 = null;
        byte[] byteArray55 = new byte[] { (byte) 100, (byte) 1 };
        tools.jackson.core.io.UTF32Reader uTF32Reader59 = new tools.jackson.core.io.UTF32Reader(iOContext50, inputStream51, false, byteArray55, (int) (short) 0, (-1), false);
        java.lang.String str60 = base64Variant32.encode(byteArray55);
        boolean boolean61 = base64Variant32.acceptsPaddingOnRead();
        tools.jackson.core.util.Named[] namedArray62 = new tools.jackson.core.util.Named[] { base64Variant0, base64Variant3, base64Variant32 };
        java.util.ArrayList<tools.jackson.core.util.Named> namedList63 = new java.util.ArrayList<tools.jackson.core.util.Named>();
        boolean boolean64 = java.util.Collections.addAll((java.util.Collection<tools.jackson.core.util.Named>) namedList63, namedArray62);
        java.util.List<java.lang.String> strList66 = tools.jackson.core.sym.PropertyNameMatcher.stringsFromNames((java.util.List<tools.jackson.core.util.Named>) namedList63, true);
        tools.jackson.core.sym.BinaryNameMatcher binaryNameMatcher68 = tools.jackson.core.sym.BinaryNameMatcher.constructFrom((java.util.List<tools.jackson.core.util.Named>) namedList63, false);
        int int69 = binaryNameMatcher68.spilloverQuadCount();
        int int71 = binaryNameMatcher68.matchByQuad(32000);
        org.junit.Assert.assertNotNull(base64Variant0);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(base64Variant3);
        org.junit.Assert.assertNotNull(byteArray11);
        org.junit.Assert.assertArrayEquals(byteArray11, new byte[] { (byte) 10, (byte) 65, (byte) 65, (byte) -1, (byte) -1 });
        org.junit.Assert.assertTrue("'" + int13 + "' != '" + 3 + "'", int13 == 3);
        org.junit.Assert.assertNotNull(base64Variant17);
        org.junit.Assert.assertNotNull(base64Variant18);
        org.junit.Assert.assertNotNull(byteArray26);
        org.junit.Assert.assertArrayEquals(byteArray26, new byte[] { (byte) 10, (byte) 65, (byte) 65, (byte) -1, (byte) -1 });
        org.junit.Assert.assertTrue("'" + int28 + "' != '" + 3 + "'", int28 == 3);
        org.junit.Assert.assertEquals("'" + str31 + "' != '" + "CkFB__8" + "'", str31, "CkFB__8");
        org.junit.Assert.assertNotNull(base64Variant32);
        org.junit.Assert.assertNotNull(byteArray40);
        org.junit.Assert.assertArrayEquals(byteArray40, new byte[] { (byte) 10, (byte) 65, (byte) 65, (byte) -1, (byte) -1 });
        org.junit.Assert.assertTrue("'" + int42 + "' != '" + 3 + "'", int42 == 3);
        org.junit.Assert.assertNotNull(base64Variant46);
        org.junit.Assert.assertTrue("'" + boolean47 + "' != '" + false + "'", boolean47 == false);
        org.junit.Assert.assertTrue("'" + char49 + "' != '" + 'B' + "'", char49 == 'B');
        org.junit.Assert.assertNotNull(byteArray55);
        org.junit.Assert.assertArrayEquals(byteArray55, new byte[] { (byte) 100, (byte) 1 });
        org.junit.Assert.assertEquals("'" + str60 + "' != '" + "ZAE" + "'", str60, "ZAE");
        org.junit.Assert.assertTrue("'" + boolean61 + "' != '" + false + "'", boolean61 == false);
        org.junit.Assert.assertNotNull(namedArray62);
        org.junit.Assert.assertTrue("'" + boolean64 + "' != '" + true + "'", boolean64 == true);
        org.junit.Assert.assertNotNull(strList66);
        org.junit.Assert.assertNotNull(binaryNameMatcher68);
        org.junit.Assert.assertTrue("'" + int69 + "' != '" + 0 + "'", int69 == 0);
        org.junit.Assert.assertTrue("'" + int71 + "' != '" + (-1) + "'", int71 == (-1));
    }

    @Test
    public void test1607() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1607");
        tools.jackson.core.sym.ByteQuadsCanonicalizer byteQuadsCanonicalizer0 = tools.jackson.core.sym.ByteQuadsCanonicalizer.createRoot();
        int int3 = byteQuadsCanonicalizer0.calcHash(9, 12000);
        tools.jackson.core.sym.ByteQuadsCanonicalizer byteQuadsCanonicalizer5 = byteQuadsCanonicalizer0.makeChildOrPlaceholder(49);
        org.junit.Assert.assertNotNull(byteQuadsCanonicalizer0);
// flaky "2) test1607(RegressionTest3)":         org.junit.Assert.assertTrue("'" + int3 + "' != '" + (-207851370) + "'", int3 == (-207851370));
        org.junit.Assert.assertNotNull(byteQuadsCanonicalizer5);
    }

    @Test
    public void test1608() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1608");
        tools.jackson.core.JsonPointer jsonPointer0 = tools.jackson.core.JsonPointer.empty();
        boolean boolean1 = jsonPointer0.mayMatchElement();
        tools.jackson.core.JsonPointer jsonPointer3 = jsonPointer0.appendProperty("tools.jackson.core.exc.StreamWriteException: hi!\n at [No location information]");
        boolean boolean4 = jsonPointer0.matches();
        boolean boolean6 = jsonPointer0.matchesElement((-57));
        boolean boolean7 = jsonPointer0.mayMatchProperty();
        tools.jackson.core.JsonPointer jsonPointer9 = jsonPointer0.matchProperty("Unexpected end of base64-encoded String: base64 variant 'MODIFIED-FOR-URL' expects padding (one or more '\000' characters) at the end. This Base64Variant might have been incorrectly configured");
        org.junit.Assert.assertNotNull(jsonPointer0);
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
        org.junit.Assert.assertNotNull(jsonPointer3);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + true + "'", boolean4 == true);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertNull(jsonPointer9);
    }

    @Test
    public void test1609() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1609");
        tools.jackson.core.io.JsonStringEncoder jsonStringEncoder0 = tools.jackson.core.io.JsonStringEncoder.getInstance();
        byte[] byteArray2 = jsonStringEncoder0.encodeAsUTF8((java.lang.CharSequence) "root");
        byte[] byteArray4 = jsonStringEncoder0.quoteAsUTF8((java.lang.CharSequence) "~1");
        org.junit.Assert.assertNotNull(jsonStringEncoder0);
        org.junit.Assert.assertNotNull(byteArray2);
        org.junit.Assert.assertArrayEquals(byteArray2, new byte[] { (byte) 114, (byte) 111, (byte) 111, (byte) 116 });
        org.junit.Assert.assertNotNull(byteArray4);
        org.junit.Assert.assertArrayEquals(byteArray4, new byte[] { (byte) 126, (byte) 49 });
    }

    @Test
    public void test1610() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1610");
        tools.jackson.core.StreamReadConstraints streamReadConstraints0 = tools.jackson.core.StreamReadConstraints.defaults();
        boolean boolean1 = streamReadConstraints0.hasMaxDocumentLength();
        int int2 = streamReadConstraints0.getMaxNumberLength();
        long long3 = streamReadConstraints0.getMaxDocumentLength();
        tools.jackson.core.StreamWriteConstraints.Builder builder4 = tools.jackson.core.StreamWriteConstraints.builder();
        tools.jackson.core.StreamWriteConstraints streamWriteConstraints5 = builder4.build();
        tools.jackson.core.json.JsonFactory jsonFactory6 = new tools.jackson.core.json.JsonFactory();
        tools.jackson.core.ErrorReportConfiguration errorReportConfiguration7 = jsonFactory6.errorReportConfiguration();
        tools.jackson.core.util.BufferRecycler bufferRecycler8 = new tools.jackson.core.util.BufferRecycler();
        tools.jackson.core.io.SegmentedStringWriter segmentedStringWriter9 = new tools.jackson.core.io.SegmentedStringWriter(bufferRecycler8);
        segmentedStringWriter9.write("MODIFIED-FOR-URL/UNKNOWN/0.0.0");
        tools.jackson.core.util.BufferRecycler bufferRecycler12 = segmentedStringWriter9.bufferRecycler();
        tools.jackson.core.io.ContentReference contentReference13 = tools.jackson.core.io.ContentReference.redacted();
        tools.jackson.core.TokenStreamLocation tokenStreamLocation17 = new tools.jackson.core.TokenStreamLocation(contentReference13, (long) (short) 10, 33, 33);
        java.lang.Object obj18 = contentReference13.getRawContent();
        tools.jackson.core.JsonEncoding jsonEncoding20 = null;
        tools.jackson.core.io.IOContext iOContext21 = new tools.jackson.core.io.IOContext(streamReadConstraints0, streamWriteConstraints5, errorReportConfiguration7, bufferRecycler12, contentReference13, false, jsonEncoding20);
        tools.jackson.core.Base64Variant base64Variant22 = tools.jackson.core.Base64Variants.MODIFIED_FOR_URL;
        byte[] byteArray30 = new byte[] { (byte) 10, (byte) -1, (byte) -1, (byte) -1, (byte) -1 };
        int int32 = base64Variant22.encodeBase64Partial(10, (int) 'a', byteArray30, (int) (byte) 1);
        tools.jackson.core.Base64Variant base64Variant35 = new tools.jackson.core.Base64Variant(base64Variant22, "hi!", 0);
        tools.jackson.core.Base64Variant base64Variant36 = base64Variant22.withPaddingForbidden();
        java.lang.String str37 = base64Variant36.missingPaddingMessage();
        tools.jackson.core.io.SerializedString serializedString38 = tools.jackson.core.PrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
        byte[] byteArray39 = serializedString38.asQuotedUTF8();
        java.lang.String str40 = base64Variant36.encode(byteArray39);
        // The following exception was thrown during execution in test generation
        try {
            iOContext21.releaseBase64Buffer(byteArray39);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(streamReadConstraints0);
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
        org.junit.Assert.assertTrue("'" + int2 + "' != '" + 1000 + "'", int2 == 1000);
        org.junit.Assert.assertTrue("'" + long3 + "' != '" + (-1L) + "'", long3 == (-1L));
        org.junit.Assert.assertNotNull(builder4);
        org.junit.Assert.assertNotNull(streamWriteConstraints5);
        org.junit.Assert.assertNotNull(errorReportConfiguration7);
        org.junit.Assert.assertNotNull(bufferRecycler12);
        org.junit.Assert.assertNotNull(contentReference13);
        org.junit.Assert.assertNull(obj18);
        org.junit.Assert.assertNotNull(base64Variant22);
        org.junit.Assert.assertNotNull(byteArray30);
        org.junit.Assert.assertArrayEquals(byteArray30, new byte[] { (byte) 10, (byte) 65, (byte) 65, (byte) -1, (byte) -1 });
        org.junit.Assert.assertTrue("'" + int32 + "' != '" + 3 + "'", int32 == 3);
        org.junit.Assert.assertNotNull(base64Variant36);
        org.junit.Assert.assertEquals("'" + str37 + "' != '" + "Unexpected end of base64-encoded String: base64 variant 'MODIFIED-FOR-URL' expects padding (one or more '\000' characters) at the end. This Base64Variant might have been incorrectly configured" + "'", str37, "Unexpected end of base64-encoded String: base64 variant 'MODIFIED-FOR-URL' expects padding (one or more '\000' characters) at the end. This Base64Variant might have been incorrectly configured");
        org.junit.Assert.assertNotNull(serializedString38);
        org.junit.Assert.assertNotNull(byteArray39);
        org.junit.Assert.assertArrayEquals(byteArray39, new byte[] { (byte) 32 });
        org.junit.Assert.assertEquals("'" + str40 + "' != '" + "IA" + "'", str40, "IA");
    }

    @Test
    public void test1611() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1611");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        tools.jackson.core.filter.TokenFilter tokenFilter6 = filteringParserDelegate5.getFilter();
        boolean boolean7 = filteringParserDelegate5.isExpectedStartArrayToken();
        boolean boolean8 = filteringParserDelegate5.hasCurrentToken();
        tools.jackson.core.TokenStreamContext tokenStreamContext9 = filteringParserDelegate5.streamReadContext();
        int int10 = filteringParserDelegate5.currentTokenId();
        tools.jackson.core.JsonToken jsonToken11 = filteringParserDelegate5.currentToken();
        tools.jackson.core.JsonToken jsonToken12 = filteringParserDelegate5.getLastClearedToken();
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.JsonToken jsonToken13 = filteringParserDelegate5.nextToken();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(tokenFilter6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertNotNull(tokenStreamContext9);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 0 + "'", int10 == 0);
        org.junit.Assert.assertNull(jsonToken11);
        org.junit.Assert.assertNull(jsonToken12);
    }

    @Test
    public void test1612() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1612");
        tools.jackson.core.json.JsonFactory jsonFactory0 = new tools.jackson.core.json.JsonFactory();
        int int1 = jsonFactory0.getFormatReadFeatures();
        tools.jackson.core.ObjectWriteContext objectWriteContext2 = tools.jackson.core.TokenStreamFactory.EMPTY_WRITE_CONTEXT;
        tools.jackson.core.util.BufferRecycler bufferRecycler3 = new tools.jackson.core.util.BufferRecycler();
        tools.jackson.core.io.SegmentedStringWriter segmentedStringWriter4 = new tools.jackson.core.io.SegmentedStringWriter(bufferRecycler3);
        segmentedStringWriter4.write("MODIFIED-FOR-URL/UNKNOWN/0.0.0");
        java.io.Writer writer8 = segmentedStringWriter4.append('a');
        tools.jackson.core.JsonGenerator jsonGenerator9 = jsonFactory0.createGenerator(objectWriteContext2, (java.io.Writer) segmentedStringWriter4);
        tools.jackson.core.JsonParser jsonParser11 = jsonFactory0.createParser("tools.jackson.core.exc.StreamWriteException: hi!\n at [No location information] (through reference chain: java.lang.Boolean[\"hi!\"])");
        boolean boolean12 = jsonFactory0.canParseAsync();
        tools.jackson.core.ObjectReadContext objectReadContext13 = null;
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.JsonParser jsonParser14 = jsonFactory0.createNonBlockingByteBufferParser(objectReadContext13);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + int1 + "' != '" + 0 + "'", int1 == 0);
        org.junit.Assert.assertNotNull(objectWriteContext2);
        org.junit.Assert.assertNotNull(writer8);
        org.junit.Assert.assertNotNull(jsonGenerator9);
        org.junit.Assert.assertNotNull(jsonParser11);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + true + "'", boolean12 == true);
    }

    @Test
    public void test1613() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1613");
        tools.jackson.core.Version version6 = new tools.jackson.core.Version(56319, (int) ' ', (int) (byte) 100, "10.0", "{(Float)=(Float)}", "97.0");
    }

    @Test
    public void test1614() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1614");
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
        java.lang.String str20 = filteringParserDelegate15.currentName();
        tools.jackson.core.filter.TokenFilter tokenFilter21 = filteringParserDelegate15.getFilter();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj22 = filteringParserDelegate15.getTypeId();
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
        org.junit.Assert.assertNull(str20);
        org.junit.Assert.assertNull(tokenFilter21);
    }

    @Test
    public void test1615() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1615");
        java.util.Locale locale0 = null;
        java.lang.String[] strArray2 = new java.lang.String[] { "(Float)" };
        java.util.ArrayList<java.lang.String> strList3 = new java.util.ArrayList<java.lang.String>();
        boolean boolean4 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList3, strArray2);
        tools.jackson.core.sym.SimpleNameMatcher simpleNameMatcher5 = tools.jackson.core.sym.SimpleNameMatcher.construct(locale0, (java.util.List<java.lang.String>) strList3);
        int int9 = simpleNameMatcher5.matchByQuad((int) '#', 50000, 12000);
        org.junit.Assert.assertNotNull(strArray2);
        org.junit.Assert.assertArrayEquals(strArray2, new java.lang.String[] { "(Float)" });
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + true + "'", boolean4 == true);
        org.junit.Assert.assertNotNull(simpleNameMatcher5);
        org.junit.Assert.assertTrue("'" + int9 + "' != '" + (-2) + "'", int9 == (-2));
    }

    @Test
    public void test1616() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1616");
        tools.jackson.core.json.JsonFactory jsonFactory0 = new tools.jackson.core.json.JsonFactory();
        java.lang.Class<? extends tools.jackson.core.FormatFeature> wildcardClass1 = jsonFactory0.getFormatWriteFeatureType();
        tools.jackson.core.io.CharacterEscapes characterEscapes2 = jsonFactory0.getCharacterEscapes();
        tools.jackson.core.ObjectReadContext objectReadContext3 = null;
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.JsonParser jsonParser4 = jsonFactory0.createNonBlockingByteBufferParser(objectReadContext3);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(wildcardClass1);
        org.junit.Assert.assertNull(characterEscapes2);
    }

    @Test
    public void test1617() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1617");
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder0 = tools.jackson.core.json.JsonFactory.builder();
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder2 = jsonFactoryBuilder0.highestNonEscapedChar((-324));
        tools.jackson.core.StreamWriteConstraints streamWriteConstraints3 = tools.jackson.core.StreamWriteConstraints.defaults();
        tools.jackson.core.StreamWriteConstraints.overrideDefaultStreamWriteConstraints(streamWriteConstraints3);
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder5 = jsonFactoryBuilder0.streamWriteConstraints(streamWriteConstraints3);
        tools.jackson.core.io.InputDecorator inputDecorator6 = null;
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder7 = jsonFactoryBuilder0.inputDecorator(inputDecorator6);
        tools.jackson.core.json.JsonReadFeature jsonReadFeature8 = null;
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder10 = jsonFactoryBuilder7.configure(jsonReadFeature8, false);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(jsonFactoryBuilder0);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder2);
        org.junit.Assert.assertNotNull(streamWriteConstraints3);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder5);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder7);
    }

    @Test
    public void test1618() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1618");
        tools.jackson.core.filter.TokenFilter tokenFilter0 = null;
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext1 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter0);
        tools.jackson.core.JsonToken jsonToken2 = tokenFilterContext1.nextTokenToRead();
        tools.jackson.core.filter.TokenFilter tokenFilter3 = tools.jackson.core.filter.TokenFilter.INCLUDE_ALL;
        tools.jackson.core.filter.TokenFilter tokenFilter4 = tokenFilterContext1.checkValue(tokenFilter3);
        java.math.BigDecimal bigDecimal5 = null;
        boolean boolean6 = tokenFilter3.includeNumber(bigDecimal5);
        tools.jackson.core.filter.TokenFilter tokenFilter8 = tokenFilter3.includeRootValue((int) (byte) 1);
        boolean boolean9 = tokenFilter8.includeNull();
        boolean boolean10 = tokenFilter8.includeNull();
        boolean boolean11 = tokenFilter8.includeNull();
        org.junit.Assert.assertNotNull(tokenFilterContext1);
        org.junit.Assert.assertNull(jsonToken2);
        org.junit.Assert.assertNotNull(tokenFilter3);
        org.junit.Assert.assertNotNull(tokenFilter4);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + true + "'", boolean6 == true);
        org.junit.Assert.assertNotNull(tokenFilter8);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + true + "'", boolean9 == true);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + true + "'", boolean11 == true);
    }

    @Test
    public void test1619() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1619");
        tools.jackson.core.JsonGenerator jsonGenerator0 = null;
        java.lang.Throwable throwable2 = null;
        tools.jackson.core.exc.StreamWriteException streamWriteException3 = new tools.jackson.core.exc.StreamWriteException(jsonGenerator0, "hi!", throwable2);
        tools.jackson.core.json.DupDetector dupDetector4 = null;
        tools.jackson.core.util.SimpleStreamWriteContext simpleStreamWriteContext5 = tools.jackson.core.util.SimpleStreamWriteContext.createRootContext(dupDetector4);
        boolean boolean6 = simpleStreamWriteContext5.writeValue();
        tools.jackson.core.JacksonException jacksonException8 = tools.jackson.core.JacksonException.wrapWithPath((java.lang.Throwable) streamWriteException3, (java.lang.Object) boolean6, "hi!");
        tools.jackson.core.JsonGenerator jsonGenerator9 = null;
        tools.jackson.core.exc.StreamWriteException streamWriteException10 = streamWriteException3.withGenerator(jsonGenerator9);
        tools.jackson.core.JacksonException jacksonException11 = streamWriteException3.clearLocation();
        org.junit.Assert.assertNotNull(simpleStreamWriteContext5);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + true + "'", boolean6 == true);
        org.junit.Assert.assertNotNull(jacksonException8);
        org.junit.Assert.assertNotNull(streamWriteException10);
        org.junit.Assert.assertNotNull(jacksonException11);
    }

    @Test
    public void test1620() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1620");
        tools.jackson.core.exc.StreamConstraintsException streamConstraintsException1 = new tools.jackson.core.exc.StreamConstraintsException("2.0");
    }

    @Test
    public void test1621() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1621");
        tools.jackson.core.util.InternCache internCache3 = new tools.jackson.core.util.InternCache(14, (float) 2147483647, 104);
        java.util.Enumeration<java.lang.String> strEnumeration4 = internCache3.elements();
        org.junit.Assert.assertNotNull(strEnumeration4);
    }

    @Test
    public void test1622() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1622");
        tools.jackson.core.util.BufferRecycler bufferRecycler0 = new tools.jackson.core.util.BufferRecycler();
        tools.jackson.core.io.SegmentedStringWriter segmentedStringWriter1 = new tools.jackson.core.io.SegmentedStringWriter(bufferRecycler0);
        segmentedStringWriter1.write("MODIFIED-FOR-URL/UNKNOWN/0.0.0");
        tools.jackson.core.util.BufferRecycler bufferRecycler4 = segmentedStringWriter1.bufferRecycler();
        java.io.Writer writer6 = segmentedStringWriter1.append((java.lang.CharSequence) "2.0");
        org.junit.Assert.assertNotNull(bufferRecycler4);
        org.junit.Assert.assertNotNull(writer6);
    }

    @Test
    public void test1623() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1623");
        tools.jackson.core.Version version0 = tools.jackson.core.Version.unknownVersion();
        int int1 = version0.getPatchLevel();
        tools.jackson.core.Version version2 = tools.jackson.core.Version.unknownVersion();
        boolean boolean3 = version2.isUnknownVersion();
        int int4 = version0.compareTo(version2);
        tools.jackson.core.Version version5 = tools.jackson.core.Version.unknownVersion();
        int int6 = version5.getPatchLevel();
        java.lang.String str7 = version5.toString();
        java.lang.String str8 = version5.getArtifactId();
        int int9 = version2.compareTo(version5);
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder10 = tools.jackson.core.json.JsonFactory.builderWithJackson2Defaults();
        tools.jackson.core.SerializableString serializableString11 = null;
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder12 = jsonFactoryBuilder10.rootValueSeparator(serializableString11);
        boolean boolean13 = version5.equals((java.lang.Object) jsonFactoryBuilder10);
        java.lang.String str14 = version5.toFullString();
        org.junit.Assert.assertNotNull(version0);
        org.junit.Assert.assertTrue("'" + int1 + "' != '" + 0 + "'", int1 == 0);
        org.junit.Assert.assertNotNull(version2);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + true + "'", boolean3 == true);
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 0 + "'", int4 == 0);
        org.junit.Assert.assertNotNull(version5);
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "0.0.0" + "'", str7, "0.0.0");
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "" + "'", str8, "");
        org.junit.Assert.assertTrue("'" + int9 + "' != '" + 0 + "'", int9 == 0);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder10);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder12);
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
        org.junit.Assert.assertEquals("'" + str14 + "' != '" + "//0.0.0" + "'", str14, "//0.0.0");
    }

    @Test
    public void test1624() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1624");
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
            java.lang.String str10 = filteringParserDelegate5.getText();
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
    public void test1625() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1625");
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
        boolean boolean22 = jsonParserSequence20.isExpectedStartArrayToken();
        tools.jackson.core.JsonParser jsonParser23 = jsonParserSequence20.skipChildren();
        tools.jackson.core.JsonToken jsonToken24 = jsonParserSequence20.currentToken();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str25 = jsonParserSequence20.getValueAsString();
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
        org.junit.Assert.assertTrue("'" + boolean22 + "' != '" + false + "'", boolean22 == false);
        org.junit.Assert.assertNotNull(jsonParser23);
        org.junit.Assert.assertNull(jsonToken24);
    }

    @Test
    public void test1626() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1626");
        tools.jackson.core.util.DefaultIndenter defaultIndenter0 = new tools.jackson.core.util.DefaultIndenter();
        java.lang.String str1 = defaultIndenter0.getEol();
        tools.jackson.core.util.DefaultIndenter defaultIndenter3 = defaultIndenter0.withLinefeed("AAAAAAAAAAAAAA");
        tools.jackson.core.util.DefaultIndenter defaultIndenter5 = defaultIndenter3.withIndent("Object");
        java.lang.String str6 = defaultIndenter5.getIndent();
        tools.jackson.core.util.DefaultIndenter defaultIndenter8 = defaultIndenter5.withIndent("tools.jackson.core.type.WritableTypeId[256]");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "\r\n" + "'", str1, "\r\n");
        org.junit.Assert.assertNotNull(defaultIndenter3);
        org.junit.Assert.assertNotNull(defaultIndenter5);
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "Object" + "'", str6, "Object");
        org.junit.Assert.assertNotNull(defaultIndenter8);
    }

    @Test
    public void test1627() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1627");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        boolean boolean6 = filteringParserDelegate5.isExpectedStartObjectToken();
        int int7 = filteringParserDelegate5.currentTokenId();
        boolean boolean8 = filteringParserDelegate5.isExpectedStartObjectToken();
        tools.jackson.core.JsonParser jsonParser9 = filteringParserDelegate5.skipChildren();
        tools.jackson.core.util.JsonParserDelegate jsonParserDelegate10 = new tools.jackson.core.util.JsonParserDelegate((tools.jackson.core.JsonParser) filteringParserDelegate5);
        int int11 = filteringParserDelegate5.currentTokenId();
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean12 = filteringParserDelegate5.getValueAsBoolean();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 0 + "'", int7 == 0);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertNotNull(jsonParser9);
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 0 + "'", int11 == 0);
    }

    @Test
    public void test1628() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1628");
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
        java.lang.String str22 = internCache7.toString();
        java.lang.String str25 = internCache7.putIfAbsent("-1.0", "tools.jackson.core.type.WritableTypeId[256]");
        org.junit.Assert.assertNotNull(internCache0);
        org.junit.Assert.assertNotNull(simpleStreamWriteContext2);
        org.junit.Assert.assertNull(dupDetector3);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
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
        org.junit.Assert.assertEquals("'" + str22 + "' != '" + "{}" + "'", str22, "{}");
        org.junit.Assert.assertNull(str25);
    }

    @Test
    public void test1629() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1629");
        tools.jackson.core.JsonPointer jsonPointer0 = tools.jackson.core.JsonPointer.empty();
        tools.jackson.core.filter.JsonPointerBasedFilter jsonPointerBasedFilter1 = new tools.jackson.core.filter.JsonPointerBasedFilter(jsonPointer0);
        tools.jackson.core.filter.TokenFilter tokenFilter3 = jsonPointerBasedFilter1.includeElement(5);
        boolean boolean4 = jsonPointerBasedFilter1.includeRawValue();
        org.junit.Assert.assertNotNull(jsonPointer0);
        org.junit.Assert.assertNull(tokenFilter3);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + true + "'", boolean4 == true);
    }

    @Test
    public void test1630() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1630");
        tools.jackson.core.Base64Variant base64Variant0 = tools.jackson.core.Base64Variants.MODIFIED_FOR_URL;
        byte[] byteArray8 = new byte[] { (byte) 10, (byte) -1, (byte) -1, (byte) -1, (byte) -1 };
        int int10 = base64Variant0.encodeBase64Partial(10, (int) 'a', byteArray8, (int) (byte) 1);
        tools.jackson.core.Base64Variant base64Variant13 = new tools.jackson.core.Base64Variant(base64Variant0, "hi!", 0);
        byte[] byteArray17 = new byte[] { (byte) -1, (byte) 100, (byte) 10 };
        java.lang.String str19 = base64Variant13.encode(byteArray17, true);
        tools.jackson.core.Base64Variant.PaddingReadBehaviour paddingReadBehaviour20 = base64Variant13.paddingReadBehaviour();
        tools.jackson.core.Base64Variant base64Variant23 = new tools.jackson.core.Base64Variant(base64Variant13, "2.0", (-44));
        tools.jackson.core.util.BufferRecycler bufferRecycler25 = null;
        tools.jackson.core.util.ByteArrayBuilder byteArrayBuilder26 = new tools.jackson.core.util.ByteArrayBuilder(bufferRecycler25);
        byte[] byteArray28 = byteArrayBuilder26.completeAndCoalesce(10);
        byte[] byteArray29 = byteArrayBuilder26.toByteArray();
        byteArrayBuilder26.close();
        int int31 = byteArrayBuilder26.size();
        tools.jackson.core.util.BufferRecycler bufferRecycler32 = byteArrayBuilder26.bufferRecycler();
        byteArrayBuilder26.appendThreeBytes((-1074));
        byteArrayBuilder26.appendFourBytes(1000);
        byteArrayBuilder26.flush();
        base64Variant13.decode("CkFB__8", byteArrayBuilder26);
        byte[] byteArray39 = byteArrayBuilder26.getCurrentSegment();
        byteArrayBuilder26.reset();
        tools.jackson.core.Base64Variant base64Variant41 = tools.jackson.core.Base64Variants.MODIFIED_FOR_URL;
        byte[] byteArray49 = new byte[] { (byte) 10, (byte) -1, (byte) -1, (byte) -1, (byte) -1 };
        int int51 = base64Variant41.encodeBase64Partial(10, (int) 'a', byteArray49, (int) (byte) 1);
        tools.jackson.core.Base64Variant base64Variant54 = new tools.jackson.core.Base64Variant(base64Variant41, "hi!", 0);
        tools.jackson.core.Base64Variant base64Variant55 = base64Variant41.withPaddingForbidden();
        java.lang.String str56 = base64Variant55.missingPaddingMessage();
        tools.jackson.core.io.SerializedString serializedString57 = tools.jackson.core.PrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
        byte[] byteArray58 = serializedString57.asQuotedUTF8();
        java.lang.String str59 = base64Variant55.encode(byteArray58);
        byteArrayBuilder26.write(byteArray58);
        org.junit.Assert.assertNotNull(base64Variant0);
        org.junit.Assert.assertNotNull(byteArray8);
        org.junit.Assert.assertArrayEquals(byteArray8, new byte[] { (byte) 10, (byte) 65, (byte) 65, (byte) -1, (byte) -1 });
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 3 + "'", int10 == 3);
        org.junit.Assert.assertNotNull(byteArray17);
        org.junit.Assert.assertArrayEquals(byteArray17, new byte[] { (byte) -1, (byte) 100, (byte) 10 });
        org.junit.Assert.assertEquals("'" + str19 + "' != '" + "\"_2QK\\n\"" + "'", str19, "\"_2QK\\n\"");
        org.junit.Assert.assertTrue("'" + paddingReadBehaviour20 + "' != '" + tools.jackson.core.Base64Variant.PaddingReadBehaviour.PADDING_FORBIDDEN + "'", paddingReadBehaviour20.equals(tools.jackson.core.Base64Variant.PaddingReadBehaviour.PADDING_FORBIDDEN));
        org.junit.Assert.assertNotNull(byteArray28);
        org.junit.Assert.assertArrayEquals(byteArray28, new byte[] { (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0 });
        org.junit.Assert.assertNotNull(byteArray29);
        org.junit.Assert.assertArrayEquals(byteArray29, new byte[] { (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0 });
        org.junit.Assert.assertTrue("'" + int31 + "' != '" + 10 + "'", int31 == 10);
        org.junit.Assert.assertNull(bufferRecycler32);
        org.junit.Assert.assertNotNull(byteArray39);
        org.junit.Assert.assertNotNull(base64Variant41);
        org.junit.Assert.assertNotNull(byteArray49);
        org.junit.Assert.assertArrayEquals(byteArray49, new byte[] { (byte) 10, (byte) 65, (byte) 65, (byte) -1, (byte) -1 });
        org.junit.Assert.assertTrue("'" + int51 + "' != '" + 3 + "'", int51 == 3);
        org.junit.Assert.assertNotNull(base64Variant55);
        org.junit.Assert.assertEquals("'" + str56 + "' != '" + "Unexpected end of base64-encoded String: base64 variant 'MODIFIED-FOR-URL' expects padding (one or more '\000' characters) at the end. This Base64Variant might have been incorrectly configured" + "'", str56, "Unexpected end of base64-encoded String: base64 variant 'MODIFIED-FOR-URL' expects padding (one or more '\000' characters) at the end. This Base64Variant might have been incorrectly configured");
        org.junit.Assert.assertNotNull(serializedString57);
        org.junit.Assert.assertNotNull(byteArray58);
        org.junit.Assert.assertArrayEquals(byteArray58, new byte[] { (byte) 32 });
        org.junit.Assert.assertEquals("'" + str59 + "' != '" + "IA" + "'", str59, "IA");
    }

    @Test
    public void test1631() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1631");
        tools.jackson.core.Base64Variant base64Variant0 = tools.jackson.core.Base64Variants.MODIFIED_FOR_URL;
        byte[] byteArray8 = new byte[] { (byte) 10, (byte) -1, (byte) -1, (byte) -1, (byte) -1 };
        int int10 = base64Variant0.encodeBase64Partial(10, (int) 'a', byteArray8, (int) (byte) 1);
        tools.jackson.core.Base64Variant base64Variant13 = new tools.jackson.core.Base64Variant(base64Variant0, "hi!", 0);
        tools.jackson.core.Base64Variant base64Variant14 = base64Variant0.withPaddingForbidden();
        java.lang.String str15 = base64Variant14.toString();
        byte byte16 = base64Variant14.getPaddingByte();
        java.lang.String str17 = base64Variant14.getName();
        org.junit.Assert.assertNotNull(base64Variant0);
        org.junit.Assert.assertNotNull(byteArray8);
        org.junit.Assert.assertArrayEquals(byteArray8, new byte[] { (byte) 10, (byte) 65, (byte) 65, (byte) -1, (byte) -1 });
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 3 + "'", int10 == 3);
        org.junit.Assert.assertNotNull(base64Variant14);
        org.junit.Assert.assertEquals("'" + str15 + "' != '" + "MODIFIED-FOR-URL" + "'", str15, "MODIFIED-FOR-URL");
        org.junit.Assert.assertTrue("'" + byte16 + "' != '" + (byte) 0 + "'", byte16 == (byte) 0);
        org.junit.Assert.assertEquals("'" + str17 + "' != '" + "MODIFIED-FOR-URL" + "'", str17, "MODIFIED-FOR-URL");
    }

    @Test
    public void test1632() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1632");
        tools.jackson.core.util.Separators separators0 = tools.jackson.core.util.Separators.createDefaultInstance();
        tools.jackson.core.util.Separators separators2 = separators0.withObjectNameValueSeparator('\000');
        char char3 = separators0.getArrayElementSeparator();
        char char4 = separators0.getObjectEntrySeparator();
        org.junit.Assert.assertNotNull(separators0);
        org.junit.Assert.assertNotNull(separators2);
        org.junit.Assert.assertTrue("'" + char3 + "' != '" + ',' + "'", char3 == ',');
        org.junit.Assert.assertTrue("'" + char4 + "' != '" + ',' + "'", char4 == ',');
    }

    @Test
    public void test1633() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1633");
        java.util.Locale locale0 = null;
        java.util.List<tools.jackson.core.util.Named> namedList1 = null;
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.sym.SimpleNameMatcher simpleNameMatcher3 = tools.jackson.core.sym.SimpleNameMatcher.constructCaseInsensitive(locale0, namedList1, true);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test1634() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1634");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        tools.jackson.core.filter.TokenFilter tokenFilter6 = filteringParserDelegate5.getFilter();
        boolean boolean7 = filteringParserDelegate5.isExpectedStartArrayToken();
        boolean boolean8 = filteringParserDelegate5.hasCurrentToken();
        filteringParserDelegate5.clearCurrentToken();
        tools.jackson.core.JsonToken jsonToken10 = null;
        boolean boolean11 = filteringParserDelegate5.hasToken(jsonToken10);
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean12 = filteringParserDelegate5.canParseAsync();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(tokenFilter6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + true + "'", boolean11 == true);
    }

    @Test
    public void test1635() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1635");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        tools.jackson.core.filter.TokenFilter tokenFilter6 = filteringParserDelegate5.getFilter();
        boolean boolean7 = filteringParserDelegate5.isExpectedStartArrayToken();
        int int8 = filteringParserDelegate5.currentTokenId();
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean9 = filteringParserDelegate5.canParseAsync();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(tokenFilter6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 0 + "'", int8 == 0);
    }

    @Test
    public void test1636() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1636");
        tools.jackson.core.util.BufferRecycler bufferRecycler0 = new tools.jackson.core.util.BufferRecycler();
        tools.jackson.core.util.ByteArrayBuilder byteArrayBuilder2 = new tools.jackson.core.util.ByteArrayBuilder(bufferRecycler0, 12000);
        tools.jackson.core.util.JsonRecyclerPools.ConcurrentDequePool concurrentDequePool3 = tools.jackson.core.util.JsonRecyclerPools.ConcurrentDequePool.construct();
        boolean boolean4 = concurrentDequePool3.clear();
        tools.jackson.core.util.BufferRecycler bufferRecycler5 = bufferRecycler0.withPool((tools.jackson.core.util.RecyclerPool<tools.jackson.core.util.BufferRecycler>) concurrentDequePool3);
        int int6 = concurrentDequePool3.pooledCount();
        boolean boolean7 = concurrentDequePool3.clear();
        org.junit.Assert.assertNotNull(concurrentDequePool3);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + true + "'", boolean4 == true);
        org.junit.Assert.assertNotNull(bufferRecycler5);
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
    }

    @Test
    public void test1637() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1637");
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
            tools.jackson.core.JsonGenerator jsonGenerator39 = filteringGeneratorDelegate35.writeString("MODIFIED-FOR-URL");
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
    public void test1638() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1638");
        tools.jackson.core.util.BufferRecycler bufferRecycler0 = null;
        tools.jackson.core.util.TextBuffer textBuffer1 = new tools.jackson.core.util.TextBuffer(bufferRecycler0);
        textBuffer1.resetWithString("");
        int int4 = textBuffer1.getCurrentSegmentSize();
        char[] charArray5 = textBuffer1.contentsAsArray();
        int int6 = textBuffer1.getTextOffset();
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 0 + "'", int4 == 0);
        org.junit.Assert.assertNotNull(charArray5);
        org.junit.Assert.assertArrayEquals(charArray5, new char[] {});
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
    }

    @Test
    public void test1639() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1639");
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder0 = tools.jackson.core.json.JsonFactory.builderWithJackson2Defaults();
        tools.jackson.core.SerializableString serializableString1 = null;
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder2 = jsonFactoryBuilder0.rootValueSeparator(serializableString1);
        tools.jackson.core.io.CharacterEscapes characterEscapes3 = null;
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder4 = jsonFactoryBuilder0.characterEscapes(characterEscapes3);
        tools.jackson.core.ErrorReportConfiguration errorReportConfiguration5 = null;
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder6 = jsonFactoryBuilder4.errorReportConfiguration(errorReportConfiguration5);
        tools.jackson.core.json.JsonFactory jsonFactory7 = new tools.jackson.core.json.JsonFactory();
        tools.jackson.core.ErrorReportConfiguration errorReportConfiguration8 = jsonFactory7.errorReportConfiguration();
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder9 = jsonFactoryBuilder6.errorReportConfiguration(errorReportConfiguration8);
        int int10 = errorReportConfiguration8.getMaxRawContentLength();
        org.junit.Assert.assertNotNull(jsonFactoryBuilder0);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder2);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder4);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder6);
        org.junit.Assert.assertNotNull(errorReportConfiguration8);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder9);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 500 + "'", int10 == 500);
    }

    @Test
    public void test1640() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1640");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        tools.jackson.core.filter.TokenFilter tokenFilter6 = filteringParserDelegate5.getFilter();
        boolean boolean7 = filteringParserDelegate5.isExpectedStartArrayToken();
        boolean boolean8 = filteringParserDelegate5.hasCurrentToken();
        filteringParserDelegate5.clearCurrentToken();
        boolean boolean10 = filteringParserDelegate5.isExpectedStartObjectToken();
        boolean boolean11 = filteringParserDelegate5.hasCurrentToken();
        tools.jackson.core.JsonToken jsonToken12 = null;
        boolean boolean13 = filteringParserDelegate5.hasToken(jsonToken12);
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.JsonParser.NumberType numberType14 = filteringParserDelegate5.getNumberType();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(tokenFilter6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + true + "'", boolean13 == true);
    }

    @Test
    public void test1641() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1641");
        tools.jackson.core.exc.StreamConstraintsException streamConstraintsException1 = new tools.jackson.core.exc.StreamConstraintsException("0.0.0");
        tools.jackson.core.exc.StreamReadException streamReadException3 = new tools.jackson.core.exc.StreamReadException("");
        java.lang.String str4 = streamReadException3.getPathReference();
        tools.jackson.core.exc.StreamReadException streamReadException6 = new tools.jackson.core.exc.StreamReadException("");
        streamReadException3.addSuppressed((java.lang.Throwable) streamReadException6);
        tools.jackson.core.JacksonException jacksonException8 = streamConstraintsException1.withCause((java.lang.Throwable) streamReadException3);
        tools.jackson.core.util.DefaultPrettyPrinter defaultPrettyPrinter9 = new tools.jackson.core.util.DefaultPrettyPrinter();
        tools.jackson.core.util.DefaultPrettyPrinter.FixedSpaceIndenter fixedSpaceIndenter10 = tools.jackson.core.util.DefaultPrettyPrinter.FixedSpaceIndenter.instance();
        defaultPrettyPrinter9.indentArraysWith((tools.jackson.core.util.DefaultPrettyPrinter.Indenter) fixedSpaceIndenter10);
        tools.jackson.core.util.Separators separators12 = tools.jackson.core.util.Separators.createDefaultInstance();
        tools.jackson.core.util.Separators separators14 = separators12.withObjectEntrySeparator('4');
        tools.jackson.core.util.Separators separators16 = separators14.withArrayElementSeparator('#');
        tools.jackson.core.util.DefaultPrettyPrinter defaultPrettyPrinter17 = defaultPrettyPrinter9.withSeparators(separators16);
        tools.jackson.core.JacksonException jacksonException19 = tools.jackson.core.JacksonException.wrapWithPath((java.lang.Throwable) streamConstraintsException1, (java.lang.Object) defaultPrettyPrinter9, 256);
        tools.jackson.core.util.DefaultPrettyPrinter defaultPrettyPrinter20 = new tools.jackson.core.util.DefaultPrettyPrinter(defaultPrettyPrinter9);
        tools.jackson.core.json.JsonFactory jsonFactory21 = new tools.jackson.core.json.JsonFactory();
        int int22 = jsonFactory21.getFormatReadFeatures();
        tools.jackson.core.json.JsonFactory jsonFactory23 = new tools.jackson.core.json.JsonFactory();
        int int24 = jsonFactory23.getFormatReadFeatures();
        tools.jackson.core.ObjectWriteContext objectWriteContext25 = tools.jackson.core.TokenStreamFactory.EMPTY_WRITE_CONTEXT;
        tools.jackson.core.util.BufferRecycler bufferRecycler26 = new tools.jackson.core.util.BufferRecycler();
        tools.jackson.core.io.SegmentedStringWriter segmentedStringWriter27 = new tools.jackson.core.io.SegmentedStringWriter(bufferRecycler26);
        segmentedStringWriter27.write("MODIFIED-FOR-URL/UNKNOWN/0.0.0");
        java.io.Writer writer31 = segmentedStringWriter27.append('a');
        tools.jackson.core.JsonGenerator jsonGenerator32 = jsonFactory23.createGenerator(objectWriteContext25, (java.io.Writer) segmentedStringWriter27);
        tools.jackson.core.util.BufferRecycler bufferRecycler33 = null;
        tools.jackson.core.util.ByteArrayBuilder byteArrayBuilder34 = new tools.jackson.core.util.ByteArrayBuilder(bufferRecycler33);
        byte[] byteArray36 = byteArrayBuilder34.completeAndCoalesce(10);
        byte[] byteArray37 = byteArrayBuilder34.toByteArray();
        tools.jackson.core.util.BufferRecycler bufferRecycler38 = byteArrayBuilder34.bufferRecycler();
        tools.jackson.core.JsonGenerator jsonGenerator39 = jsonFactory21.createGenerator(objectWriteContext25, (java.io.OutputStream) byteArrayBuilder34);
        defaultPrettyPrinter20.writeObjectNameValueSeparator(jsonGenerator39);
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.JsonGenerator jsonGenerator43 = jsonGenerator39.writeNumberProperty("", 8875587037423695204L);
            org.junit.Assert.fail("Expected exception of type tools.jackson.core.exc.StreamWriteException; message: Cannot write a property name, expecting a value? at [No location information]");
        } catch (tools.jackson.core.exc.StreamWriteException e) {
            // Expected exception.
        }
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "" + "'", str4, "");
        org.junit.Assert.assertNotNull(jacksonException8);
        org.junit.Assert.assertNotNull(fixedSpaceIndenter10);
        org.junit.Assert.assertNotNull(separators12);
        org.junit.Assert.assertNotNull(separators14);
        org.junit.Assert.assertNotNull(separators16);
        org.junit.Assert.assertNotNull(defaultPrettyPrinter17);
        org.junit.Assert.assertNotNull(jacksonException19);
        org.junit.Assert.assertTrue("'" + int22 + "' != '" + 0 + "'", int22 == 0);
        org.junit.Assert.assertTrue("'" + int24 + "' != '" + 0 + "'", int24 == 0);
        org.junit.Assert.assertNotNull(objectWriteContext25);
        org.junit.Assert.assertNotNull(writer31);
        org.junit.Assert.assertNotNull(jsonGenerator32);
        org.junit.Assert.assertNotNull(byteArray36);
        org.junit.Assert.assertArrayEquals(byteArray36, new byte[] { (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0 });
        org.junit.Assert.assertNotNull(byteArray37);
        org.junit.Assert.assertArrayEquals(byteArray37, new byte[] { (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0 });
        org.junit.Assert.assertNull(bufferRecycler38);
        org.junit.Assert.assertNotNull(jsonGenerator39);
    }

    @Test
    public void test1642() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1642");
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
        int int33 = filteringParserDelegate27.currentTokenId();
        // The following exception was thrown during execution in test generation
        try {
            int int34 = filteringParserDelegate27.getStringOffset();
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
        org.junit.Assert.assertTrue("'" + int33 + "' != '" + 0 + "'", int33 == 0);
    }

    @Test
    public void test1643() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1643");
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
        java.lang.String str21 = jsonParserSequence20.currentName();
        tools.jackson.core.io.SerializedString serializedString22 = tools.jackson.core.PrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
        tools.jackson.core.json.DupDetector dupDetector23 = null;
        tools.jackson.core.json.JsonWriteContext jsonWriteContext24 = tools.jackson.core.json.JsonWriteContext.createRootContext(dupDetector23);
        boolean boolean25 = jsonWriteContext24.hasCurrentName();
        tools.jackson.core.JsonGenerator jsonGenerator28 = null;
        tools.jackson.core.json.DupDetector dupDetector29 = tools.jackson.core.json.DupDetector.rootDetector(jsonGenerator28);
        tools.jackson.core.json.JsonReadContext jsonReadContext30 = tools.jackson.core.json.JsonReadContext.createRootContext(56, 7, dupDetector29);
        tools.jackson.core.json.JsonWriteContext jsonWriteContext31 = jsonWriteContext24.withDupDetector(dupDetector29);
        boolean boolean32 = serializedString22.equals((java.lang.Object) jsonWriteContext24);
        int int33 = serializedString22.charLength();
        tools.jackson.core.Base64Variant base64Variant34 = tools.jackson.core.Base64Variants.MODIFIED_FOR_URL;
        byte[] byteArray42 = new byte[] { (byte) 10, (byte) -1, (byte) -1, (byte) -1, (byte) -1 };
        int int44 = base64Variant34.encodeBase64Partial(10, (int) 'a', byteArray42, (int) (byte) 1);
        tools.jackson.core.Base64Variant base64Variant47 = new tools.jackson.core.Base64Variant(base64Variant34, "hi!", 0);
        tools.jackson.core.Base64Variant base64Variant48 = base64Variant34.withPaddingForbidden();
        boolean boolean49 = base64Variant34.acceptsPaddingOnRead();
        char char51 = base64Variant34.encodeBase64BitsAsChar(1);
        tools.jackson.core.io.IOContext iOContext52 = null;
        java.io.InputStream inputStream53 = null;
        byte[] byteArray57 = new byte[] { (byte) 100, (byte) 1 };
        tools.jackson.core.io.UTF32Reader uTF32Reader61 = new tools.jackson.core.io.UTF32Reader(iOContext52, inputStream53, false, byteArray57, (int) (short) 0, (-1), false);
        java.lang.String str62 = base64Variant34.encode(byteArray57);
        int int64 = serializedString22.appendUnquotedUTF8(byteArray57, 971);
        tools.jackson.core.io.JsonStringEncoder jsonStringEncoder65 = tools.jackson.core.io.JsonStringEncoder.getInstance();
        byte[] byteArray67 = jsonStringEncoder65.encodeAsUTF8((java.lang.CharSequence) "root");
        char[] charArray69 = jsonStringEncoder65.quoteAsCharArray((java.lang.CharSequence) "//0.0.0");
        int int71 = serializedString22.appendUnquoted(charArray69, 56319);
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean72 = jsonParserSequence20.nextName((tools.jackson.core.SerializableString) serializedString22);
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
        org.junit.Assert.assertNull(str21);
        org.junit.Assert.assertNotNull(serializedString22);
        org.junit.Assert.assertNotNull(jsonWriteContext24);
        org.junit.Assert.assertTrue("'" + boolean25 + "' != '" + false + "'", boolean25 == false);
        org.junit.Assert.assertNotNull(dupDetector29);
        org.junit.Assert.assertNotNull(jsonReadContext30);
        org.junit.Assert.assertNotNull(jsonWriteContext31);
        org.junit.Assert.assertTrue("'" + boolean32 + "' != '" + false + "'", boolean32 == false);
        org.junit.Assert.assertTrue("'" + int33 + "' != '" + 1 + "'", int33 == 1);
        org.junit.Assert.assertNotNull(base64Variant34);
        org.junit.Assert.assertNotNull(byteArray42);
        org.junit.Assert.assertArrayEquals(byteArray42, new byte[] { (byte) 10, (byte) 65, (byte) 65, (byte) -1, (byte) -1 });
        org.junit.Assert.assertTrue("'" + int44 + "' != '" + 3 + "'", int44 == 3);
        org.junit.Assert.assertNotNull(base64Variant48);
        org.junit.Assert.assertTrue("'" + boolean49 + "' != '" + false + "'", boolean49 == false);
        org.junit.Assert.assertTrue("'" + char51 + "' != '" + 'B' + "'", char51 == 'B');
        org.junit.Assert.assertNotNull(byteArray57);
        org.junit.Assert.assertArrayEquals(byteArray57, new byte[] { (byte) 100, (byte) 1 });
        org.junit.Assert.assertEquals("'" + str62 + "' != '" + "ZAE" + "'", str62, "ZAE");
        org.junit.Assert.assertTrue("'" + int64 + "' != '" + (-1) + "'", int64 == (-1));
        org.junit.Assert.assertNotNull(jsonStringEncoder65);
        org.junit.Assert.assertNotNull(byteArray67);
        org.junit.Assert.assertArrayEquals(byteArray67, new byte[] { (byte) 114, (byte) 111, (byte) 111, (byte) 116 });
        org.junit.Assert.assertNotNull(charArray69);
        org.junit.Assert.assertArrayEquals(charArray69, new char[] { '/', '/', '0', '.', '0', '.', '0' });
        org.junit.Assert.assertTrue("'" + int71 + "' != '" + (-1) + "'", int71 == (-1));
    }

    @Test
    public void test1644() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1644");
        int int2 = tools.jackson.core.io.NumberInput.parseAsInt(" ", (int) ' ');
        org.junit.Assert.assertTrue("'" + int2 + "' != '" + 32 + "'", int2 == 32);
    }

    @Test
    public void test1645() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1645");
        tools.jackson.core.json.DupDetector dupDetector2 = null;
        tools.jackson.core.json.JsonReadContext jsonReadContext3 = tools.jackson.core.json.JsonReadContext.createRootContext(1, 0, dupDetector2);
        java.lang.Object obj4 = jsonReadContext3.currentValue();
        tools.jackson.core.json.DupDetector dupDetector6 = null;
        tools.jackson.core.json.JsonWriteContext jsonWriteContext7 = tools.jackson.core.json.JsonWriteContext.createRootContext(dupDetector6);
        boolean boolean8 = jsonWriteContext7.hasCurrentName();
        tools.jackson.core.JsonGenerator jsonGenerator11 = null;
        tools.jackson.core.json.DupDetector dupDetector12 = tools.jackson.core.json.DupDetector.rootDetector(jsonGenerator11);
        tools.jackson.core.json.JsonReadContext jsonReadContext13 = tools.jackson.core.json.JsonReadContext.createRootContext(56, 7, dupDetector12);
        tools.jackson.core.json.JsonWriteContext jsonWriteContext14 = jsonWriteContext7.withDupDetector(dupDetector12);
        tools.jackson.core.JsonParser jsonParser15 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter16 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion17 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate20 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser15, tokenFilter16, inclusion17, true, true);
        tools.jackson.core.filter.TokenFilter tokenFilter21 = filteringParserDelegate20.getFilter();
        boolean boolean22 = filteringParserDelegate20.isExpectedStartArrayToken();
        boolean boolean23 = filteringParserDelegate20.hasCurrentToken();
        tools.jackson.core.json.DupDetector dupDetector24 = tools.jackson.core.json.DupDetector.rootDetector((tools.jackson.core.JsonParser) filteringParserDelegate20);
        tools.jackson.core.json.JsonWriteContext jsonWriteContext25 = jsonWriteContext7.withDupDetector(dupDetector24);
        dupDetector24.reset();
        tools.jackson.core.json.JsonReadContext jsonReadContext30 = new tools.jackson.core.json.JsonReadContext(jsonReadContext3, (-45), dupDetector24, (-1074), 53, 9);
        tools.jackson.core.json.JsonReadContext jsonReadContext31 = jsonReadContext3.clearAndGetParent();
        org.junit.Assert.assertNotNull(jsonReadContext3);
        org.junit.Assert.assertNull(obj4);
        org.junit.Assert.assertNotNull(jsonWriteContext7);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertNotNull(dupDetector12);
        org.junit.Assert.assertNotNull(jsonReadContext13);
        org.junit.Assert.assertNotNull(jsonWriteContext14);
        org.junit.Assert.assertNull(tokenFilter21);
        org.junit.Assert.assertTrue("'" + boolean22 + "' != '" + false + "'", boolean22 == false);
        org.junit.Assert.assertTrue("'" + boolean23 + "' != '" + false + "'", boolean23 == false);
        org.junit.Assert.assertNotNull(dupDetector24);
        org.junit.Assert.assertNotNull(jsonWriteContext25);
        org.junit.Assert.assertNull(jsonReadContext31);
    }

    @Test
    public void test1646() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1646");
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder0 = tools.jackson.core.json.JsonFactory.builder();
        tools.jackson.core.StreamReadConstraints streamReadConstraints1 = tools.jackson.core.StreamReadConstraints.defaults();
        boolean boolean2 = streamReadConstraints1.hasMaxDocumentLength();
        int int3 = streamReadConstraints1.getMaxNestingDepth();
        streamReadConstraints1.validateNameLength(256);
        int int6 = streamReadConstraints1.getMaxNameLength();
        int int7 = streamReadConstraints1.getMaxNumberLength();
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder8 = jsonFactoryBuilder0.streamReadConstraints(streamReadConstraints1);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder0);
        org.junit.Assert.assertNotNull(streamReadConstraints1);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertTrue("'" + int3 + "' != '" + 500 + "'", int3 == 500);
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 50000 + "'", int6 == 50000);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 1000 + "'", int7 == 1000);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder8);
    }

    @Test
    public void test1647() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1647");
        tools.jackson.core.util.DefaultIndenter defaultIndenter0 = new tools.jackson.core.util.DefaultIndenter();
        java.lang.String str1 = defaultIndenter0.getEol();
        tools.jackson.core.util.DefaultIndenter defaultIndenter3 = defaultIndenter0.withLinefeed("AAAAAAAAAAAAAA");
        tools.jackson.core.util.DefaultIndenter defaultIndenter5 = defaultIndenter3.withIndent("Object");
        java.lang.String str6 = defaultIndenter5.getIndent();
        boolean boolean7 = defaultIndenter5.isInline();
        tools.jackson.core.util.DefaultIndenter defaultIndenter9 = defaultIndenter5.withIndent("6.5577783776342712E18");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "\r\n" + "'", str1, "\r\n");
        org.junit.Assert.assertNotNull(defaultIndenter3);
        org.junit.Assert.assertNotNull(defaultIndenter5);
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "Object" + "'", str6, "Object");
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertNotNull(defaultIndenter9);
    }

    @Test
    public void test1648() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1648");
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter0 = new tools.jackson.core.util.MinimalPrettyPrinter();
        tools.jackson.core.JsonGenerator jsonGenerator1 = null;
        minimalPrettyPrinter0.beforeObjectEntries(jsonGenerator1);
        tools.jackson.core.util.Separators separators6 = new tools.jackson.core.util.Separators('~', ' ', ' ');
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter7 = minimalPrettyPrinter0.setSeparators(separators6);
        tools.jackson.core.JsonGenerator jsonGenerator8 = null;
        tools.jackson.core.util.JsonGeneratorDelegate jsonGeneratorDelegate9 = new tools.jackson.core.util.JsonGeneratorDelegate(jsonGenerator8);
        minimalPrettyPrinter0.beforeObjectEntries((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate9);
        tools.jackson.core.util.JsonGeneratorDelegate jsonGeneratorDelegate11 = new tools.jackson.core.util.JsonGeneratorDelegate((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate9);
        tools.jackson.core.JsonToken jsonToken13 = null;
        tools.jackson.core.type.WritableTypeId writableTypeId15 = new tools.jackson.core.type.WritableTypeId((java.lang.Object) '#', jsonToken13, (java.lang.Object) ' ');
        writableTypeId15.asProperty = "hi!";
        java.lang.Class<?> wildcardClass18 = writableTypeId15.forValueType;
        writableTypeId15.forValue = "";
        java.lang.Object obj21 = writableTypeId15.forValue;
        tools.jackson.core.type.WritableTypeId writableTypeId22 = jsonGeneratorDelegate11.writeTypeSuffix(writableTypeId15);
        tools.jackson.core.Base64Variant base64Variant23 = tools.jackson.core.Base64Variants.MODIFIED_FOR_URL;
        byte[] byteArray31 = new byte[] { (byte) 10, (byte) -1, (byte) -1, (byte) -1, (byte) -1 };
        int int33 = base64Variant23.encodeBase64Partial(10, (int) 'a', byteArray31, (int) (byte) 1);
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.JsonGenerator jsonGenerator34 = jsonGeneratorDelegate11.writeBinary(byteArray31);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(minimalPrettyPrinter7);
        org.junit.Assert.assertNull(wildcardClass18);
        org.junit.Assert.assertEquals("'" + obj21 + "' != '" + "" + "'", obj21, "");
        org.junit.Assert.assertNotNull(writableTypeId22);
        org.junit.Assert.assertNotNull(base64Variant23);
        org.junit.Assert.assertNotNull(byteArray31);
        org.junit.Assert.assertArrayEquals(byteArray31, new byte[] { (byte) 10, (byte) 65, (byte) 65, (byte) -1, (byte) -1 });
        org.junit.Assert.assertTrue("'" + int33 + "' != '" + 3 + "'", int33 == 3);
    }

    @Test
    public void test1649() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1649");
        tools.jackson.core.TokenStreamContext tokenStreamContext0 = null;
        tools.jackson.core.JsonPointer jsonPointer2 = tools.jackson.core.JsonPointer.forPath(tokenStreamContext0, false);
        java.lang.String str3 = jsonPointer2.getMatchingProperty();
        boolean boolean4 = jsonPointer2.mayMatchElement();
        org.junit.Assert.assertNotNull(jsonPointer2);
        org.junit.Assert.assertNull(str3);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
    }

    @Test
    public void test1650() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1650");
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter0 = new tools.jackson.core.util.MinimalPrettyPrinter();
        tools.jackson.core.JsonGenerator jsonGenerator1 = null;
        minimalPrettyPrinter0.beforeObjectEntries(jsonGenerator1);
        tools.jackson.core.util.Separators separators6 = new tools.jackson.core.util.Separators('~', ' ', ' ');
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter7 = minimalPrettyPrinter0.setSeparators(separators6);
        tools.jackson.core.JsonGenerator jsonGenerator8 = null;
        tools.jackson.core.util.JsonGeneratorDelegate jsonGeneratorDelegate9 = new tools.jackson.core.util.JsonGeneratorDelegate(jsonGenerator8);
        minimalPrettyPrinter0.beforeObjectEntries((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate9);
        tools.jackson.core.util.JsonGeneratorDelegate jsonGeneratorDelegate11 = new tools.jackson.core.util.JsonGeneratorDelegate((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate9);
        tools.jackson.core.JsonGenerator jsonGenerator12 = null;
        tools.jackson.core.JsonGenerator jsonGenerator14 = null;
        java.lang.Throwable throwable16 = null;
        tools.jackson.core.exc.StreamWriteException streamWriteException17 = new tools.jackson.core.exc.StreamWriteException(jsonGenerator14, "hi!", throwable16);
        tools.jackson.core.json.DupDetector dupDetector18 = null;
        tools.jackson.core.util.SimpleStreamWriteContext simpleStreamWriteContext19 = tools.jackson.core.util.SimpleStreamWriteContext.createRootContext(dupDetector18);
        boolean boolean20 = simpleStreamWriteContext19.writeValue();
        tools.jackson.core.JacksonException jacksonException22 = tools.jackson.core.JacksonException.wrapWithPath((java.lang.Throwable) streamWriteException17, (java.lang.Object) boolean20, "hi!");
        java.lang.String str23 = streamWriteException17.toString();
        tools.jackson.core.exc.StreamWriteException streamWriteException24 = new tools.jackson.core.exc.StreamWriteException(jsonGenerator12, "0.0.0", (java.lang.Throwable) streamWriteException17);
        tools.jackson.core.JsonGenerator jsonGenerator25 = null;
        java.lang.Throwable throwable27 = null;
        tools.jackson.core.exc.StreamWriteException streamWriteException28 = new tools.jackson.core.exc.StreamWriteException(jsonGenerator25, "hi!", throwable27);
        tools.jackson.core.json.DupDetector dupDetector29 = null;
        tools.jackson.core.util.SimpleStreamWriteContext simpleStreamWriteContext30 = tools.jackson.core.util.SimpleStreamWriteContext.createRootContext(dupDetector29);
        boolean boolean31 = simpleStreamWriteContext30.writeValue();
        tools.jackson.core.JacksonException jacksonException33 = tools.jackson.core.JacksonException.wrapWithPath((java.lang.Throwable) streamWriteException28, (java.lang.Object) boolean31, "hi!");
        tools.jackson.core.JsonToken jsonToken35 = null;
        tools.jackson.core.type.WritableTypeId writableTypeId37 = new tools.jackson.core.type.WritableTypeId((java.lang.Object) '#', jsonToken35, (java.lang.Object) ' ');
        tools.jackson.core.type.WritableTypeId.Inclusion inclusion38 = tools.jackson.core.type.WritableTypeId.Inclusion.PAYLOAD_PROPERTY;
        writableTypeId37.include = inclusion38;
        tools.jackson.core.JacksonException.Reference reference41 = new tools.jackson.core.JacksonException.Reference((java.lang.Object) writableTypeId37, 256);
        tools.jackson.core.JacksonException jacksonException42 = tools.jackson.core.JacksonException.wrapWithPath((java.lang.Throwable) streamWriteException28, reference41);
        java.lang.String str43 = reference41.getDescription();
        int int44 = reference41.getIndex();
        tools.jackson.core.JacksonException jacksonException45 = tools.jackson.core.JacksonException.wrapWithPath((java.lang.Throwable) streamWriteException24, reference41);
        tools.jackson.core.JacksonException.Reference reference46 = new tools.jackson.core.JacksonException.Reference((java.lang.Object) jacksonException45);
        tools.jackson.core.exc.StreamWriteException streamWriteException47 = new tools.jackson.core.exc.StreamWriteException((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate9, (java.lang.Throwable) jacksonException45);
        org.junit.Assert.assertNotNull(minimalPrettyPrinter7);
        org.junit.Assert.assertNotNull(simpleStreamWriteContext19);
        org.junit.Assert.assertTrue("'" + boolean20 + "' != '" + true + "'", boolean20 == true);
        org.junit.Assert.assertNotNull(jacksonException22);
        org.junit.Assert.assertEquals("'" + str23 + "' != '" + "tools.jackson.core.exc.StreamWriteException: hi!\n at [No location information] (through reference chain: java.lang.Boolean[\"hi!\"])" + "'", str23, "tools.jackson.core.exc.StreamWriteException: hi!\n at [No location information] (through reference chain: java.lang.Boolean[\"hi!\"])");
        org.junit.Assert.assertNotNull(simpleStreamWriteContext30);
        org.junit.Assert.assertTrue("'" + boolean31 + "' != '" + true + "'", boolean31 == true);
        org.junit.Assert.assertNotNull(jacksonException33);
        org.junit.Assert.assertTrue("'" + inclusion38 + "' != '" + tools.jackson.core.type.WritableTypeId.Inclusion.PAYLOAD_PROPERTY + "'", inclusion38.equals(tools.jackson.core.type.WritableTypeId.Inclusion.PAYLOAD_PROPERTY));
        org.junit.Assert.assertNotNull(jacksonException42);
        org.junit.Assert.assertEquals("'" + str43 + "' != '" + "tools.jackson.core.type.WritableTypeId[256]" + "'", str43, "tools.jackson.core.type.WritableTypeId[256]");
        org.junit.Assert.assertTrue("'" + int44 + "' != '" + 256 + "'", int44 == 256);
        org.junit.Assert.assertNotNull(jacksonException45);
    }

    @Test
    public void test1651() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1651");
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
        tools.jackson.core.filter.TokenFilter tokenFilter35 = null;
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext36 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter35);
        tools.jackson.core.JsonToken jsonToken37 = tokenFilterContext36.nextTokenToRead();
        tools.jackson.core.filter.TokenFilter tokenFilter38 = tools.jackson.core.filter.TokenFilter.INCLUDE_ALL;
        tools.jackson.core.filter.TokenFilter tokenFilter39 = tokenFilterContext36.checkValue(tokenFilter38);
        java.math.BigDecimal bigDecimal40 = null;
        boolean boolean41 = tokenFilter38.includeNumber(bigDecimal40);
        tools.jackson.core.filter.TokenFilter tokenFilter43 = tokenFilter38.includeRootValue((int) (byte) 1);
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion44 = tools.jackson.core.filter.TokenFilter.Inclusion.ONLY_INCLUDE_ALL;
        tools.jackson.core.filter.FilteringGeneratorDelegate filteringGeneratorDelegate46 = new tools.jackson.core.filter.FilteringGeneratorDelegate((tools.jackson.core.JsonGenerator) filteringGeneratorDelegate34, tokenFilter38, inclusion44, false);
        tools.jackson.core.util.JsonGeneratorDelegate jsonGeneratorDelegate48 = new tools.jackson.core.util.JsonGeneratorDelegate((tools.jackson.core.JsonGenerator) filteringGeneratorDelegate46, false);
        tools.jackson.core.Base64Variant base64Variant49 = tools.jackson.core.Base64Variants.MODIFIED_FOR_URL;
        byte[] byteArray57 = new byte[] { (byte) 10, (byte) -1, (byte) -1, (byte) -1, (byte) -1 };
        int int59 = base64Variant49.encodeBase64Partial(10, (int) 'a', byteArray57, (int) (byte) 1);
        tools.jackson.core.Base64Variant base64Variant62 = new tools.jackson.core.Base64Variant(base64Variant49, "hi!", 0);
        tools.jackson.core.Base64Variant base64Variant63 = base64Variant49.withPaddingForbidden();
        java.lang.String str64 = base64Variant63.missingPaddingMessage();
        tools.jackson.core.util.BufferRecycler bufferRecycler65 = null;
        tools.jackson.core.util.ByteArrayBuilder byteArrayBuilder66 = new tools.jackson.core.util.ByteArrayBuilder(bufferRecycler65);
        byte[] byteArray68 = byteArrayBuilder66.completeAndCoalesce(10);
        byte[] byteArray69 = byteArrayBuilder66.toByteArray();
        java.lang.String str72 = base64Variant63.encode(byteArray69, false, "100.0");
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.JsonGenerator jsonGenerator75 = filteringGeneratorDelegate46.writeUTF8String(byteArray69, (-1552242809), (-324));
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
        org.junit.Assert.assertNotNull(tokenFilterContext36);
        org.junit.Assert.assertNull(jsonToken37);
        org.junit.Assert.assertNotNull(tokenFilter38);
        org.junit.Assert.assertNotNull(tokenFilter39);
        org.junit.Assert.assertTrue("'" + boolean41 + "' != '" + true + "'", boolean41 == true);
        org.junit.Assert.assertNotNull(tokenFilter43);
        org.junit.Assert.assertTrue("'" + inclusion44 + "' != '" + tools.jackson.core.filter.TokenFilter.Inclusion.ONLY_INCLUDE_ALL + "'", inclusion44.equals(tools.jackson.core.filter.TokenFilter.Inclusion.ONLY_INCLUDE_ALL));
        org.junit.Assert.assertNotNull(base64Variant49);
        org.junit.Assert.assertNotNull(byteArray57);
        org.junit.Assert.assertArrayEquals(byteArray57, new byte[] { (byte) 10, (byte) 65, (byte) 65, (byte) -1, (byte) -1 });
        org.junit.Assert.assertTrue("'" + int59 + "' != '" + 3 + "'", int59 == 3);
        org.junit.Assert.assertNotNull(base64Variant63);
        org.junit.Assert.assertEquals("'" + str64 + "' != '" + "Unexpected end of base64-encoded String: base64 variant 'MODIFIED-FOR-URL' expects padding (one or more '\000' characters) at the end. This Base64Variant might have been incorrectly configured" + "'", str64, "Unexpected end of base64-encoded String: base64 variant 'MODIFIED-FOR-URL' expects padding (one or more '\000' characters) at the end. This Base64Variant might have been incorrectly configured");
        org.junit.Assert.assertNotNull(byteArray68);
        org.junit.Assert.assertArrayEquals(byteArray68, new byte[] { (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0 });
        org.junit.Assert.assertNotNull(byteArray69);
        org.junit.Assert.assertArrayEquals(byteArray69, new byte[] { (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str72 + "' != '" + "AAAAAAAAAAAAAA" + "'", str72, "AAAAAAAAAAAAAA");
    }

    @Test
    public void test1652() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1652");
        tools.jackson.core.Base64Variant base64Variant1 = tools.jackson.core.Base64Variants.MODIFIED_FOR_URL;
        byte[] byteArray9 = new byte[] { (byte) 10, (byte) -1, (byte) -1, (byte) -1, (byte) -1 };
        int int11 = base64Variant1.encodeBase64Partial(10, (int) 'a', byteArray9, (int) (byte) 1);
        tools.jackson.core.Base64Variant base64Variant14 = new tools.jackson.core.Base64Variant(base64Variant1, "hi!", 0);
        tools.jackson.core.Base64Variant base64Variant15 = base64Variant1.withPaddingForbidden();
        boolean boolean16 = base64Variant1.acceptsPaddingOnRead();
        char char18 = base64Variant1.encodeBase64BitsAsChar(1);
        tools.jackson.core.io.IOContext iOContext19 = null;
        java.io.InputStream inputStream20 = null;
        byte[] byteArray24 = new byte[] { (byte) 100, (byte) 1 };
        tools.jackson.core.io.UTF32Reader uTF32Reader28 = new tools.jackson.core.io.UTF32Reader(iOContext19, inputStream20, false, byteArray24, (int) (short) 0, (-1), false);
        java.lang.String str29 = base64Variant1.encode(byteArray24);
        boolean boolean30 = base64Variant1.acceptsPaddingOnRead();
        tools.jackson.core.Base64Variant base64Variant31 = base64Variant1.withPaddingAllowed();
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder32 = tools.jackson.core.json.JsonFactory.builder();
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder34 = jsonFactoryBuilder32.highestNonEscapedChar((-324));
        tools.jackson.core.util.JsonpCharacterEscapes jsonpCharacterEscapes35 = tools.jackson.core.util.JsonpCharacterEscapes.instance();
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder36 = jsonFactoryBuilder34.characterEscapes((tools.jackson.core.io.CharacterEscapes) jsonpCharacterEscapes35);
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder37 = tools.jackson.core.json.JsonFactory.builderWithJackson2Defaults();
        tools.jackson.core.SerializableString serializableString38 = null;
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder39 = jsonFactoryBuilder37.rootValueSeparator(serializableString38);
        tools.jackson.core.io.CharacterEscapes characterEscapes40 = null;
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder41 = jsonFactoryBuilder37.characterEscapes(characterEscapes40);
        tools.jackson.core.ErrorReportConfiguration errorReportConfiguration42 = null;
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder43 = jsonFactoryBuilder41.errorReportConfiguration(errorReportConfiguration42);
        tools.jackson.core.json.JsonFactory jsonFactory44 = new tools.jackson.core.json.JsonFactory();
        tools.jackson.core.ErrorReportConfiguration errorReportConfiguration45 = jsonFactory44.errorReportConfiguration();
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder46 = jsonFactoryBuilder43.errorReportConfiguration(errorReportConfiguration45);
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder47 = jsonFactoryBuilder34.errorReportConfiguration(errorReportConfiguration45);
        tools.jackson.core.io.ContentReference contentReference48 = tools.jackson.core.io.ContentReference.construct(false, (java.lang.Object) base64Variant31, errorReportConfiguration45);
        org.junit.Assert.assertNotNull(base64Variant1);
        org.junit.Assert.assertNotNull(byteArray9);
        org.junit.Assert.assertArrayEquals(byteArray9, new byte[] { (byte) 10, (byte) 65, (byte) 65, (byte) -1, (byte) -1 });
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 3 + "'", int11 == 3);
        org.junit.Assert.assertNotNull(base64Variant15);
        org.junit.Assert.assertTrue("'" + boolean16 + "' != '" + false + "'", boolean16 == false);
        org.junit.Assert.assertTrue("'" + char18 + "' != '" + 'B' + "'", char18 == 'B');
        org.junit.Assert.assertNotNull(byteArray24);
        org.junit.Assert.assertArrayEquals(byteArray24, new byte[] { (byte) 100, (byte) 1 });
        org.junit.Assert.assertEquals("'" + str29 + "' != '" + "ZAE" + "'", str29, "ZAE");
        org.junit.Assert.assertTrue("'" + boolean30 + "' != '" + false + "'", boolean30 == false);
        org.junit.Assert.assertNotNull(base64Variant31);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder32);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder34);
        org.junit.Assert.assertNotNull(jsonpCharacterEscapes35);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder36);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder37);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder39);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder41);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder43);
        org.junit.Assert.assertNotNull(errorReportConfiguration45);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder46);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder47);
        org.junit.Assert.assertNotNull(contentReference48);
    }

    @Test
    public void test1653() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1653");
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
            tools.jackson.core.JsonGenerator jsonGenerator35 = filteringGeneratorDelegate34.writeEndArray();
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
    public void test1654() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1654");
        tools.jackson.core.json.JsonFactory jsonFactory0 = new tools.jackson.core.json.JsonFactory();
        java.lang.Class<? extends tools.jackson.core.FormatFeature> wildcardClass1 = jsonFactory0.getFormatWriteFeatureType();
        tools.jackson.core.io.CharacterEscapes characterEscapes2 = jsonFactory0.getCharacterEscapes();
        java.io.InputStream inputStream3 = null;
        tools.jackson.core.JsonParser jsonParser4 = jsonFactory0.createParser(inputStream3);
        tools.jackson.core.filter.TokenFilter tokenFilter5 = null;
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext6 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter5);
        tools.jackson.core.JsonToken jsonToken7 = tokenFilterContext6.nextTokenToRead();
        tools.jackson.core.filter.TokenFilter tokenFilter8 = tools.jackson.core.filter.TokenFilter.INCLUDE_ALL;
        tools.jackson.core.filter.TokenFilter tokenFilter9 = tokenFilterContext6.checkValue(tokenFilter8);
        java.math.BigDecimal bigDecimal10 = null;
        boolean boolean11 = tokenFilter8.includeNumber(bigDecimal10);
        tools.jackson.core.filter.TokenFilter tokenFilter12 = tokenFilter8.filterStartObject();
        tools.jackson.core.io.IOContext iOContext13 = null;
        java.io.InputStream inputStream14 = null;
        byte[] byteArray18 = new byte[] { (byte) 100, (byte) 1 };
        tools.jackson.core.io.UTF32Reader uTF32Reader22 = new tools.jackson.core.io.UTF32Reader(iOContext13, inputStream14, false, byteArray18, (int) (short) 0, (-1), false);
        boolean boolean23 = uTF32Reader22.ready();
        boolean boolean24 = uTF32Reader22.ready();
        boolean boolean25 = uTF32Reader22.ready();
        boolean boolean27 = tokenFilter12.includeString((java.io.Reader) uTF32Reader22, 0);
        uTF32Reader22.close();
        boolean boolean29 = uTF32Reader22.ready();
        tools.jackson.core.JsonParser jsonParser30 = jsonFactory0.createParser((java.io.Reader) uTF32Reader22);
        boolean boolean31 = uTF32Reader22.ready();
        org.junit.Assert.assertNotNull(wildcardClass1);
        org.junit.Assert.assertNull(characterEscapes2);
        org.junit.Assert.assertNotNull(jsonParser4);
        org.junit.Assert.assertNotNull(tokenFilterContext6);
        org.junit.Assert.assertNull(jsonToken7);
        org.junit.Assert.assertNotNull(tokenFilter8);
        org.junit.Assert.assertNotNull(tokenFilter9);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + true + "'", boolean11 == true);
        org.junit.Assert.assertNotNull(tokenFilter12);
        org.junit.Assert.assertNotNull(byteArray18);
        org.junit.Assert.assertArrayEquals(byteArray18, new byte[] { (byte) 100, (byte) 1 });
        org.junit.Assert.assertTrue("'" + boolean23 + "' != '" + false + "'", boolean23 == false);
        org.junit.Assert.assertTrue("'" + boolean24 + "' != '" + false + "'", boolean24 == false);
        org.junit.Assert.assertTrue("'" + boolean25 + "' != '" + false + "'", boolean25 == false);
        org.junit.Assert.assertTrue("'" + boolean27 + "' != '" + true + "'", boolean27 == true);
        org.junit.Assert.assertTrue("'" + boolean29 + "' != '" + false + "'", boolean29 == false);
        org.junit.Assert.assertNotNull(jsonParser30);
        org.junit.Assert.assertTrue("'" + boolean31 + "' != '" + false + "'", boolean31 == false);
    }

    @Test
    public void test1655() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1655");
        tools.jackson.core.JsonToken jsonToken1 = null;
        tools.jackson.core.type.WritableTypeId writableTypeId3 = new tools.jackson.core.type.WritableTypeId((java.lang.Object) '#', jsonToken1, (java.lang.Object) ' ');
        writableTypeId3.asProperty = "hi!";
        java.lang.Class<?> wildcardClass6 = writableTypeId3.forValueType;
        writableTypeId3.forValue = "";
        java.lang.Object obj9 = writableTypeId3.forValue;
        java.lang.Object obj10 = writableTypeId3.forValue;
        tools.jackson.core.Base64Variant base64Variant11 = tools.jackson.core.Base64Variants.MODIFIED_FOR_URL;
        byte[] byteArray19 = new byte[] { (byte) 10, (byte) -1, (byte) -1, (byte) -1, (byte) -1 };
        int int21 = base64Variant11.encodeBase64Partial(10, (int) 'a', byteArray19, (int) (byte) 1);
        tools.jackson.core.Base64Variant base64Variant24 = new tools.jackson.core.Base64Variant(base64Variant11, "hi!", 0);
        tools.jackson.core.Base64Variant base64Variant25 = base64Variant11.withPaddingForbidden();
        writableTypeId3.id = base64Variant25;
        org.junit.Assert.assertNull(wildcardClass6);
        org.junit.Assert.assertEquals("'" + obj9 + "' != '" + "" + "'", obj9, "");
        org.junit.Assert.assertEquals("'" + obj10 + "' != '" + "" + "'", obj10, "");
        org.junit.Assert.assertNotNull(base64Variant11);
        org.junit.Assert.assertNotNull(byteArray19);
        org.junit.Assert.assertArrayEquals(byteArray19, new byte[] { (byte) 10, (byte) 65, (byte) 65, (byte) -1, (byte) -1 });
        org.junit.Assert.assertTrue("'" + int21 + "' != '" + 3 + "'", int21 == 3);
        org.junit.Assert.assertNotNull(base64Variant25);
    }

    @Test
    public void test1656() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1656");
        tools.jackson.core.json.JsonFactory jsonFactory0 = new tools.jackson.core.json.JsonFactory();
        tools.jackson.core.ErrorReportConfiguration errorReportConfiguration1 = jsonFactory0.errorReportConfiguration();
        tools.jackson.core.json.JsonFactory jsonFactory2 = jsonFactory0.copy();
        tools.jackson.core.ObjectReadContext objectReadContext3 = null;
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.JsonParser jsonParser5 = jsonFactory0.createParser(objectReadContext3, "AAAAAAAAAAAAAA");
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(errorReportConfiguration1);
        org.junit.Assert.assertNotNull(jsonFactory2);
    }

    @Test
    public void test1657() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1657");
        tools.jackson.core.Base64Variant base64Variant0 = tools.jackson.core.Base64Variants.MODIFIED_FOR_URL;
        boolean boolean2 = base64Variant0.equals((java.lang.Object) 3);
        tools.jackson.core.Base64Variant base64Variant3 = tools.jackson.core.Base64Variants.MODIFIED_FOR_URL;
        byte[] byteArray11 = new byte[] { (byte) 10, (byte) -1, (byte) -1, (byte) -1, (byte) -1 };
        int int13 = base64Variant3.encodeBase64Partial(10, (int) 'a', byteArray11, (int) (byte) 1);
        tools.jackson.core.Base64Variant base64Variant16 = new tools.jackson.core.Base64Variant(base64Variant3, "hi!", 0);
        tools.jackson.core.Base64Variant base64Variant17 = base64Variant3.withPaddingAllowed();
        tools.jackson.core.Base64Variant base64Variant18 = tools.jackson.core.Base64Variants.MODIFIED_FOR_URL;
        byte[] byteArray26 = new byte[] { (byte) 10, (byte) -1, (byte) -1, (byte) -1, (byte) -1 };
        int int28 = base64Variant18.encodeBase64Partial(10, (int) 'a', byteArray26, (int) (byte) 1);
        java.lang.String str31 = base64Variant3.encode(byteArray26, false, "//0.0.0");
        tools.jackson.core.Base64Variant base64Variant32 = tools.jackson.core.Base64Variants.MODIFIED_FOR_URL;
        byte[] byteArray40 = new byte[] { (byte) 10, (byte) -1, (byte) -1, (byte) -1, (byte) -1 };
        int int42 = base64Variant32.encodeBase64Partial(10, (int) 'a', byteArray40, (int) (byte) 1);
        tools.jackson.core.Base64Variant base64Variant45 = new tools.jackson.core.Base64Variant(base64Variant32, "hi!", 0);
        tools.jackson.core.Base64Variant base64Variant46 = base64Variant32.withPaddingForbidden();
        boolean boolean47 = base64Variant32.acceptsPaddingOnRead();
        char char49 = base64Variant32.encodeBase64BitsAsChar(1);
        tools.jackson.core.io.IOContext iOContext50 = null;
        java.io.InputStream inputStream51 = null;
        byte[] byteArray55 = new byte[] { (byte) 100, (byte) 1 };
        tools.jackson.core.io.UTF32Reader uTF32Reader59 = new tools.jackson.core.io.UTF32Reader(iOContext50, inputStream51, false, byteArray55, (int) (short) 0, (-1), false);
        java.lang.String str60 = base64Variant32.encode(byteArray55);
        boolean boolean61 = base64Variant32.acceptsPaddingOnRead();
        tools.jackson.core.util.Named[] namedArray62 = new tools.jackson.core.util.Named[] { base64Variant0, base64Variant3, base64Variant32 };
        java.util.ArrayList<tools.jackson.core.util.Named> namedList63 = new java.util.ArrayList<tools.jackson.core.util.Named>();
        boolean boolean64 = java.util.Collections.addAll((java.util.Collection<tools.jackson.core.util.Named>) namedList63, namedArray62);
        java.util.List<java.lang.String> strList66 = tools.jackson.core.sym.PropertyNameMatcher.stringsFromNames((java.util.List<tools.jackson.core.util.Named>) namedList63, true);
        tools.jackson.core.sym.BinaryNameMatcher binaryNameMatcher68 = tools.jackson.core.sym.BinaryNameMatcher.constructFrom((java.util.List<tools.jackson.core.util.Named>) namedList63, false);
        java.util.List<java.lang.String> strList70 = tools.jackson.core.sym.PropertyNameMatcher.stringsFromNames((java.util.List<tools.jackson.core.util.Named>) namedList63, false);
        java.util.List<java.lang.String> strList72 = tools.jackson.core.sym.PropertyNameMatcher.stringsFromNames((java.util.List<tools.jackson.core.util.Named>) namedList63, false);
        org.junit.Assert.assertNotNull(base64Variant0);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(base64Variant3);
        org.junit.Assert.assertNotNull(byteArray11);
        org.junit.Assert.assertArrayEquals(byteArray11, new byte[] { (byte) 10, (byte) 65, (byte) 65, (byte) -1, (byte) -1 });
        org.junit.Assert.assertTrue("'" + int13 + "' != '" + 3 + "'", int13 == 3);
        org.junit.Assert.assertNotNull(base64Variant17);
        org.junit.Assert.assertNotNull(base64Variant18);
        org.junit.Assert.assertNotNull(byteArray26);
        org.junit.Assert.assertArrayEquals(byteArray26, new byte[] { (byte) 10, (byte) 65, (byte) 65, (byte) -1, (byte) -1 });
        org.junit.Assert.assertTrue("'" + int28 + "' != '" + 3 + "'", int28 == 3);
        org.junit.Assert.assertEquals("'" + str31 + "' != '" + "CkFB__8" + "'", str31, "CkFB__8");
        org.junit.Assert.assertNotNull(base64Variant32);
        org.junit.Assert.assertNotNull(byteArray40);
        org.junit.Assert.assertArrayEquals(byteArray40, new byte[] { (byte) 10, (byte) 65, (byte) 65, (byte) -1, (byte) -1 });
        org.junit.Assert.assertTrue("'" + int42 + "' != '" + 3 + "'", int42 == 3);
        org.junit.Assert.assertNotNull(base64Variant46);
        org.junit.Assert.assertTrue("'" + boolean47 + "' != '" + false + "'", boolean47 == false);
        org.junit.Assert.assertTrue("'" + char49 + "' != '" + 'B' + "'", char49 == 'B');
        org.junit.Assert.assertNotNull(byteArray55);
        org.junit.Assert.assertArrayEquals(byteArray55, new byte[] { (byte) 100, (byte) 1 });
        org.junit.Assert.assertEquals("'" + str60 + "' != '" + "ZAE" + "'", str60, "ZAE");
        org.junit.Assert.assertTrue("'" + boolean61 + "' != '" + false + "'", boolean61 == false);
        org.junit.Assert.assertNotNull(namedArray62);
        org.junit.Assert.assertTrue("'" + boolean64 + "' != '" + true + "'", boolean64 == true);
        org.junit.Assert.assertNotNull(strList66);
        org.junit.Assert.assertNotNull(binaryNameMatcher68);
        org.junit.Assert.assertNotNull(strList70);
        org.junit.Assert.assertNotNull(strList72);
    }

    @Test
    public void test1658() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1658");
        tools.jackson.core.TokenStreamFactory tokenStreamFactory0 = null;
        tools.jackson.core.sym.CharsToNameCanonicalizer charsToNameCanonicalizer1 = tools.jackson.core.sym.CharsToNameCanonicalizer.createRoot(tokenStreamFactory0);
        charsToNameCanonicalizer1.release();
        tools.jackson.core.sym.CharsToNameCanonicalizer charsToNameCanonicalizer3 = charsToNameCanonicalizer1.makeChild();
        org.junit.Assert.assertNotNull(charsToNameCanonicalizer1);
        org.junit.Assert.assertNotNull(charsToNameCanonicalizer3);
    }

    @Test
    public void test1659() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1659");
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
        tools.jackson.core.filter.TokenFilter tokenFilter35 = null;
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext36 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter35);
        tools.jackson.core.JsonToken jsonToken37 = tokenFilterContext36.nextTokenToRead();
        tools.jackson.core.filter.TokenFilter tokenFilter38 = tools.jackson.core.filter.TokenFilter.INCLUDE_ALL;
        tools.jackson.core.filter.TokenFilter tokenFilter39 = tokenFilterContext36.checkValue(tokenFilter38);
        java.math.BigDecimal bigDecimal40 = null;
        boolean boolean41 = tokenFilter38.includeNumber(bigDecimal40);
        tools.jackson.core.filter.TokenFilter tokenFilter43 = tokenFilter38.includeRootValue((int) (byte) 1);
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion44 = tools.jackson.core.filter.TokenFilter.Inclusion.ONLY_INCLUDE_ALL;
        tools.jackson.core.filter.FilteringGeneratorDelegate filteringGeneratorDelegate46 = new tools.jackson.core.filter.FilteringGeneratorDelegate((tools.jackson.core.JsonGenerator) filteringGeneratorDelegate34, tokenFilter38, inclusion44, false);
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.JsonGenerator jsonGenerator48 = filteringGeneratorDelegate46.writeString("UNKNOWN[34]");
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
        org.junit.Assert.assertNotNull(tokenFilterContext36);
        org.junit.Assert.assertNull(jsonToken37);
        org.junit.Assert.assertNotNull(tokenFilter38);
        org.junit.Assert.assertNotNull(tokenFilter39);
        org.junit.Assert.assertTrue("'" + boolean41 + "' != '" + true + "'", boolean41 == true);
        org.junit.Assert.assertNotNull(tokenFilter43);
        org.junit.Assert.assertTrue("'" + inclusion44 + "' != '" + tools.jackson.core.filter.TokenFilter.Inclusion.ONLY_INCLUDE_ALL + "'", inclusion44.equals(tools.jackson.core.filter.TokenFilter.Inclusion.ONLY_INCLUDE_ALL));
    }

    @Test
    public void test1660() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1660");
        tools.jackson.core.util.BufferRecycler bufferRecycler0 = new tools.jackson.core.util.BufferRecycler();
        tools.jackson.core.util.TextBuffer textBuffer1 = new tools.jackson.core.util.TextBuffer(bufferRecycler0);
        textBuffer1.resetWith('4');
    }

    @Test
    public void test1661() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1661");
        tools.jackson.core.JsonPointer jsonPointer0 = tools.jackson.core.JsonPointer.empty();
        boolean boolean1 = jsonPointer0.mayMatchElement();
        tools.jackson.core.JsonPointer jsonPointer2 = tools.jackson.core.JsonPointer.empty();
        boolean boolean3 = jsonPointer2.mayMatchElement();
        tools.jackson.core.JsonPointer jsonPointer4 = jsonPointer0.append(jsonPointer2);
        tools.jackson.core.JsonPointer jsonPointer5 = tools.jackson.core.JsonPointer.empty();
        boolean boolean6 = jsonPointer5.mayMatchElement();
        tools.jackson.core.JsonPointer jsonPointer8 = jsonPointer5.appendProperty("tools.jackson.core.exc.StreamWriteException: hi!\n at [No location information]");
        boolean boolean9 = jsonPointer5.matches();
        boolean boolean11 = jsonPointer5.matchesElement((-57));
        boolean boolean12 = jsonPointer5.mayMatchElement();
        tools.jackson.core.JsonPointer jsonPointer13 = jsonPointer0.append(jsonPointer5);
        tools.jackson.core.JsonPointer jsonPointer14 = tools.jackson.core.JsonPointer.empty();
        tools.jackson.core.JsonToken jsonToken16 = null;
        tools.jackson.core.type.WritableTypeId writableTypeId18 = new tools.jackson.core.type.WritableTypeId((java.lang.Object) '#', jsonToken16, (java.lang.Object) ' ');
        tools.jackson.core.JsonToken jsonToken19 = writableTypeId18.valueShape;
        boolean boolean20 = jsonPointer14.equals((java.lang.Object) writableTypeId18);
        boolean boolean21 = jsonPointer14.mayMatchElement();
        tools.jackson.core.JsonPointer jsonPointer22 = jsonPointer5.append(jsonPointer14);
        tools.jackson.core.JsonPointer jsonPointer24 = jsonPointer14.matchProperty("-324.0");
        org.junit.Assert.assertNotNull(jsonPointer0);
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
        org.junit.Assert.assertNotNull(jsonPointer2);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
        org.junit.Assert.assertNotNull(jsonPointer4);
        org.junit.Assert.assertNotNull(jsonPointer5);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNotNull(jsonPointer8);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + true + "'", boolean9 == true);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
        org.junit.Assert.assertNotNull(jsonPointer13);
        org.junit.Assert.assertNotNull(jsonPointer14);
        org.junit.Assert.assertNull(jsonToken19);
        org.junit.Assert.assertTrue("'" + boolean20 + "' != '" + false + "'", boolean20 == false);
        org.junit.Assert.assertTrue("'" + boolean21 + "' != '" + false + "'", boolean21 == false);
        org.junit.Assert.assertNotNull(jsonPointer22);
        org.junit.Assert.assertNull(jsonPointer24);
    }

    @Test
    public void test1662() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1662");
        tools.jackson.core.util.BufferRecycler bufferRecycler0 = new tools.jackson.core.util.BufferRecycler();
        tools.jackson.core.util.ByteArrayBuilder byteArrayBuilder2 = new tools.jackson.core.util.ByteArrayBuilder(bufferRecycler0, 12000);
        tools.jackson.core.io.SegmentedStringWriter segmentedStringWriter3 = new tools.jackson.core.io.SegmentedStringWriter(bufferRecycler0);
        tools.jackson.core.util.BufferRecycler bufferRecycler5 = null;
        tools.jackson.core.util.TextBuffer textBuffer6 = new tools.jackson.core.util.TextBuffer(bufferRecycler5);
        textBuffer6.resetWithString("");
        int int9 = textBuffer6.getCurrentSegmentSize();
        char[] charArray10 = textBuffer6.emptyAndGetCurrentSegment();
        char[] charArray12 = textBuffer6.expandCurrentSegment(0);
        bufferRecycler0.releaseCharBuffer(0, charArray12);
        org.junit.Assert.assertTrue("'" + int9 + "' != '" + 0 + "'", int9 == 0);
        org.junit.Assert.assertNotNull(charArray10);
        org.junit.Assert.assertNotNull(charArray12);
    }

    @Test
    public void test1663() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1663");
        tools.jackson.core.util.BufferRecycler bufferRecycler0 = null;
        tools.jackson.core.util.TextBuffer textBuffer1 = new tools.jackson.core.util.TextBuffer(bufferRecycler0);
        textBuffer1.resetWithString("");
        int int4 = textBuffer1.getCurrentSegmentSize();
        char[] charArray5 = textBuffer1.emptyAndGetCurrentSegment();
        char[] charArray7 = textBuffer1.expandCurrentSegment(0);
        char[] charArray8 = textBuffer1.getTextBuffer();
        char[] charArray9 = textBuffer1.emptyAndGetCurrentSegment();
        tools.jackson.core.util.BufferRecycler bufferRecycler10 = new tools.jackson.core.util.BufferRecycler();
        tools.jackson.core.io.SegmentedStringWriter segmentedStringWriter11 = new tools.jackson.core.io.SegmentedStringWriter(bufferRecycler10);
        segmentedStringWriter11.write("MODIFIED-FOR-URL/UNKNOWN/0.0.0");
        tools.jackson.core.util.BufferRecycler bufferRecycler14 = segmentedStringWriter11.bufferRecycler();
        int int15 = textBuffer1.contentsToWriter((java.io.Writer) segmentedStringWriter11);
        java.io.Writer writer17 = segmentedStringWriter11.append((java.lang.CharSequence) "(Float)");
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 0 + "'", int4 == 0);
        org.junit.Assert.assertNotNull(charArray5);
        org.junit.Assert.assertNotNull(charArray7);
        org.junit.Assert.assertNotNull(charArray8);
        org.junit.Assert.assertNotNull(charArray9);
        org.junit.Assert.assertNotNull(bufferRecycler14);
        org.junit.Assert.assertTrue("'" + int15 + "' != '" + 0 + "'", int15 == 0);
        org.junit.Assert.assertNotNull(writer17);
    }

    @Test
    public void test1664() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1664");
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
        tools.jackson.core.filter.TokenFilter tokenFilter35 = null;
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext36 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter35);
        tools.jackson.core.JsonToken jsonToken37 = tokenFilterContext36.nextTokenToRead();
        tools.jackson.core.filter.TokenFilter tokenFilter38 = tools.jackson.core.filter.TokenFilter.INCLUDE_ALL;
        tools.jackson.core.filter.TokenFilter tokenFilter39 = tokenFilterContext36.checkValue(tokenFilter38);
        java.math.BigDecimal bigDecimal40 = null;
        boolean boolean41 = tokenFilter38.includeNumber(bigDecimal40);
        tools.jackson.core.filter.TokenFilter tokenFilter43 = tokenFilter38.includeRootValue((int) (byte) 1);
        tools.jackson.core.filter.TokenFilter tokenFilter45 = tokenFilter38.includeElement(4);
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion46 = tools.jackson.core.filter.TokenFilter.Inclusion.ONLY_INCLUDE_ALL;
        tools.jackson.core.filter.FilteringGeneratorDelegate filteringGeneratorDelegate48 = new tools.jackson.core.filter.FilteringGeneratorDelegate((tools.jackson.core.JsonGenerator) filteringGeneratorDelegate34, tokenFilter45, inclusion46, false);
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.JsonGenerator jsonGenerator50 = filteringGeneratorDelegate34.writeName("-1.0");
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
        org.junit.Assert.assertNotNull(tokenFilterContext36);
        org.junit.Assert.assertNull(jsonToken37);
        org.junit.Assert.assertNotNull(tokenFilter38);
        org.junit.Assert.assertNotNull(tokenFilter39);
        org.junit.Assert.assertTrue("'" + boolean41 + "' != '" + true + "'", boolean41 == true);
        org.junit.Assert.assertNotNull(tokenFilter43);
        org.junit.Assert.assertNotNull(tokenFilter45);
        org.junit.Assert.assertTrue("'" + inclusion46 + "' != '" + tools.jackson.core.filter.TokenFilter.Inclusion.ONLY_INCLUDE_ALL + "'", inclusion46.equals(tools.jackson.core.filter.TokenFilter.Inclusion.ONLY_INCLUDE_ALL));
    }

    @Test
    public void test1665() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1665");
        tools.jackson.core.Base64Variant base64Variant0 = tools.jackson.core.Base64Variants.MODIFIED_FOR_URL;
        byte[] byteArray8 = new byte[] { (byte) 10, (byte) -1, (byte) -1, (byte) -1, (byte) -1 };
        int int10 = base64Variant0.encodeBase64Partial(10, (int) 'a', byteArray8, (int) (byte) 1);
        tools.jackson.core.Base64Variant base64Variant13 = new tools.jackson.core.Base64Variant(base64Variant0, "hi!", 0);
        byte[] byteArray17 = new byte[] { (byte) -1, (byte) 100, (byte) 10 };
        java.lang.String str19 = base64Variant13.encode(byteArray17, true);
        tools.jackson.core.Base64Variant.PaddingReadBehaviour paddingReadBehaviour20 = base64Variant13.paddingReadBehaviour();
        tools.jackson.core.Base64Variant base64Variant23 = new tools.jackson.core.Base64Variant(base64Variant13, "2.0", (-44));
        tools.jackson.core.util.BufferRecycler bufferRecycler25 = null;
        tools.jackson.core.util.ByteArrayBuilder byteArrayBuilder26 = new tools.jackson.core.util.ByteArrayBuilder(bufferRecycler25);
        byte[] byteArray28 = byteArrayBuilder26.completeAndCoalesce(10);
        byte[] byteArray29 = byteArrayBuilder26.toByteArray();
        byteArrayBuilder26.close();
        int int31 = byteArrayBuilder26.size();
        tools.jackson.core.util.BufferRecycler bufferRecycler32 = byteArrayBuilder26.bufferRecycler();
        byteArrayBuilder26.appendThreeBytes((-1074));
        byteArrayBuilder26.appendFourBytes(1000);
        byteArrayBuilder26.flush();
        base64Variant13.decode("CkFB__8", byteArrayBuilder26);
        byte[] byteArray39 = byteArrayBuilder26.getCurrentSegment();
        byteArrayBuilder26.appendFourBytes((int) (short) 100);
        org.junit.Assert.assertNotNull(base64Variant0);
        org.junit.Assert.assertNotNull(byteArray8);
        org.junit.Assert.assertArrayEquals(byteArray8, new byte[] { (byte) 10, (byte) 65, (byte) 65, (byte) -1, (byte) -1 });
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 3 + "'", int10 == 3);
        org.junit.Assert.assertNotNull(byteArray17);
        org.junit.Assert.assertArrayEquals(byteArray17, new byte[] { (byte) -1, (byte) 100, (byte) 10 });
        org.junit.Assert.assertEquals("'" + str19 + "' != '" + "\"_2QK\\n\"" + "'", str19, "\"_2QK\\n\"");
        org.junit.Assert.assertTrue("'" + paddingReadBehaviour20 + "' != '" + tools.jackson.core.Base64Variant.PaddingReadBehaviour.PADDING_FORBIDDEN + "'", paddingReadBehaviour20.equals(tools.jackson.core.Base64Variant.PaddingReadBehaviour.PADDING_FORBIDDEN));
        org.junit.Assert.assertNotNull(byteArray28);
        org.junit.Assert.assertArrayEquals(byteArray28, new byte[] { (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0 });
        org.junit.Assert.assertNotNull(byteArray29);
        org.junit.Assert.assertArrayEquals(byteArray29, new byte[] { (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0 });
        org.junit.Assert.assertTrue("'" + int31 + "' != '" + 10 + "'", int31 == 10);
        org.junit.Assert.assertNull(bufferRecycler32);
        org.junit.Assert.assertNotNull(byteArray39);
    }

    @Test
    public void test1666() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1666");
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
            int int13 = filteringParserDelegate5.getStringLength();
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
    public void test1667() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1667");
        tools.jackson.core.JsonGenerator jsonGenerator0 = null;
        java.lang.Throwable throwable2 = null;
        tools.jackson.core.exc.StreamWriteException streamWriteException3 = new tools.jackson.core.exc.StreamWriteException(jsonGenerator0, "hi!", throwable2);
        tools.jackson.core.json.DupDetector dupDetector4 = null;
        tools.jackson.core.util.SimpleStreamWriteContext simpleStreamWriteContext5 = tools.jackson.core.util.SimpleStreamWriteContext.createRootContext(dupDetector4);
        boolean boolean6 = simpleStreamWriteContext5.writeValue();
        tools.jackson.core.JacksonException jacksonException8 = tools.jackson.core.JacksonException.wrapWithPath((java.lang.Throwable) streamWriteException3, (java.lang.Object) boolean6, "hi!");
        tools.jackson.core.JsonGenerator jsonGenerator9 = null;
        tools.jackson.core.exc.StreamWriteException streamWriteException10 = streamWriteException3.withGenerator(jsonGenerator9);
        java.lang.Object obj11 = null;
        tools.jackson.core.JacksonException.Reference reference12 = new tools.jackson.core.JacksonException.Reference(obj11);
        java.lang.Object obj13 = reference12.from();
        java.util.function.BiFunction<java.lang.String, java.lang.Throwable, tools.jackson.core.JacksonException> strBiFunction14 = null;
        tools.jackson.core.JacksonException jacksonException15 = tools.jackson.core.JacksonException.wrapWithPath((java.lang.Throwable) streamWriteException3, reference12, strBiFunction14);
        java.lang.Object obj16 = reference12.from();
        org.junit.Assert.assertNotNull(simpleStreamWriteContext5);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + true + "'", boolean6 == true);
        org.junit.Assert.assertNotNull(jacksonException8);
        org.junit.Assert.assertNotNull(streamWriteException10);
        org.junit.Assert.assertNull(obj13);
        org.junit.Assert.assertNotNull(jacksonException15);
        org.junit.Assert.assertNull(obj16);
    }

    @Test
    public void test1668() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1668");
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter0 = new tools.jackson.core.util.MinimalPrettyPrinter();
        tools.jackson.core.JsonGenerator jsonGenerator1 = null;
        minimalPrettyPrinter0.beforeObjectEntries(jsonGenerator1);
        tools.jackson.core.util.Separators separators6 = new tools.jackson.core.util.Separators('~', ' ', ' ');
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter7 = minimalPrettyPrinter0.setSeparators(separators6);
        tools.jackson.core.util.DefaultPrettyPrinter.NopIndenter nopIndenter8 = new tools.jackson.core.util.DefaultPrettyPrinter.NopIndenter();
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter9 = new tools.jackson.core.util.MinimalPrettyPrinter();
        tools.jackson.core.JsonGenerator jsonGenerator10 = null;
        minimalPrettyPrinter9.beforeObjectEntries(jsonGenerator10);
        tools.jackson.core.util.Separators separators15 = new tools.jackson.core.util.Separators('~', ' ', ' ');
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter16 = minimalPrettyPrinter9.setSeparators(separators15);
        tools.jackson.core.JsonGenerator jsonGenerator17 = null;
        tools.jackson.core.util.JsonGeneratorDelegate jsonGeneratorDelegate18 = new tools.jackson.core.util.JsonGeneratorDelegate(jsonGenerator17);
        minimalPrettyPrinter9.beforeObjectEntries((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate18);
        tools.jackson.core.filter.TokenFilter tokenFilter20 = null;
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext21 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter20);
        tools.jackson.core.JsonToken jsonToken22 = tokenFilterContext21.nextTokenToRead();
        tools.jackson.core.filter.TokenFilter tokenFilter23 = tools.jackson.core.filter.TokenFilter.INCLUDE_ALL;
        tools.jackson.core.filter.TokenFilter tokenFilter24 = tokenFilterContext21.checkValue(tokenFilter23);
        java.math.BigDecimal bigDecimal25 = null;
        boolean boolean26 = tokenFilter23.includeNumber(bigDecimal25);
        tools.jackson.core.filter.TokenFilter tokenFilter28 = tokenFilter23.includeRootValue((int) (byte) 1);
        tools.jackson.core.io.IOContext iOContext29 = null;
        java.io.InputStream inputStream30 = null;
        byte[] byteArray34 = new byte[] { (byte) 100, (byte) 1 };
        tools.jackson.core.io.UTF32Reader uTF32Reader38 = new tools.jackson.core.io.UTF32Reader(iOContext29, inputStream30, false, byteArray34, (int) (short) 0, (-1), false);
        boolean boolean40 = tokenFilter28.includeString((java.io.Reader) uTF32Reader38, (-1074));
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion41 = tools.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_NON_NULL;
        tools.jackson.core.filter.FilteringGeneratorDelegate filteringGeneratorDelegate43 = new tools.jackson.core.filter.FilteringGeneratorDelegate((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate18, tokenFilter28, inclusion41, true);
        nopIndenter8.writeIndentation((tools.jackson.core.JsonGenerator) filteringGeneratorDelegate43, (int) '#');
        // The following exception was thrown during execution in test generation
        try {
            minimalPrettyPrinter7.writeObjectNameValueSeparator((tools.jackson.core.JsonGenerator) filteringGeneratorDelegate43);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(minimalPrettyPrinter7);
        org.junit.Assert.assertNotNull(minimalPrettyPrinter16);
        org.junit.Assert.assertNotNull(tokenFilterContext21);
        org.junit.Assert.assertNull(jsonToken22);
        org.junit.Assert.assertNotNull(tokenFilter23);
        org.junit.Assert.assertNotNull(tokenFilter24);
        org.junit.Assert.assertTrue("'" + boolean26 + "' != '" + true + "'", boolean26 == true);
        org.junit.Assert.assertNotNull(tokenFilter28);
        org.junit.Assert.assertNotNull(byteArray34);
        org.junit.Assert.assertArrayEquals(byteArray34, new byte[] { (byte) 100, (byte) 1 });
        org.junit.Assert.assertTrue("'" + boolean40 + "' != '" + true + "'", boolean40 == true);
        org.junit.Assert.assertTrue("'" + inclusion41 + "' != '" + tools.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_NON_NULL + "'", inclusion41.equals(tools.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_NON_NULL));
    }

    @Test
    public void test1669() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1669");
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
        boolean boolean15 = internCache7.remove((java.lang.Object) version11, (java.lang.Object) (short) 100);
        java.lang.Object obj16 = null;
        boolean boolean17 = version11.equals(obj16);
        boolean boolean18 = version11.isSnapshot();
        org.junit.Assert.assertNotNull(internCache0);
        org.junit.Assert.assertNotNull(simpleStreamWriteContext2);
        org.junit.Assert.assertNull(dupDetector3);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNotNull(version9);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 0 + "'", int10 == 0);
        org.junit.Assert.assertNotNull(version11);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + true + "'", boolean12 == true);
        org.junit.Assert.assertTrue("'" + int13 + "' != '" + 0 + "'", int13 == 0);
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertTrue("'" + boolean17 + "' != '" + false + "'", boolean17 == false);
        org.junit.Assert.assertTrue("'" + boolean18 + "' != '" + false + "'", boolean18 == false);
    }

    @Test
    public void test1670() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1670");
        tools.jackson.core.json.DupDetector dupDetector0 = null;
        tools.jackson.core.json.JsonWriteContext jsonWriteContext1 = tools.jackson.core.json.JsonWriteContext.createRootContext(dupDetector0);
        boolean boolean2 = jsonWriteContext1.hasCurrentName();
        int int4 = jsonWriteContext1.writeName("\r\n");
        jsonWriteContext1.assignCurrentValue((java.lang.Object) 32000);
        tools.jackson.core.JsonPointer jsonPointer8 = tools.jackson.core.JsonPointer.forPath((tools.jackson.core.TokenStreamContext) jsonWriteContext1, false);
        java.lang.String str9 = jsonPointer8.toString();
        int int10 = jsonPointer8.getMatchingIndex();
        tools.jackson.core.filter.TokenFilter tokenFilter11 = null;
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext12 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter11);
        tools.jackson.core.JsonToken jsonToken13 = tokenFilterContext12.nextTokenToRead();
        tools.jackson.core.filter.TokenFilter tokenFilter14 = tools.jackson.core.filter.TokenFilter.INCLUDE_ALL;
        tools.jackson.core.filter.TokenFilter tokenFilter15 = tokenFilterContext12.checkValue(tokenFilter14);
        boolean boolean16 = tokenFilter15.includeBinary();
        tools.jackson.core.filter.TokenFilter tokenFilter18 = tokenFilter15.includeElement(309);
        boolean boolean19 = jsonPointer8.equals((java.lang.Object) tokenFilter15);
        tools.jackson.core.JsonPointer jsonPointer21 = jsonPointer8.matchElement(292);
        org.junit.Assert.assertNotNull(jsonWriteContext1);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 4 + "'", int4 == 4);
        org.junit.Assert.assertNotNull(jsonPointer8);
        org.junit.Assert.assertEquals("'" + str9 + "' != '" + "" + "'", str9, "");
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + (-1) + "'", int10 == (-1));
        org.junit.Assert.assertNotNull(tokenFilterContext12);
        org.junit.Assert.assertNull(jsonToken13);
        org.junit.Assert.assertNotNull(tokenFilter14);
        org.junit.Assert.assertNotNull(tokenFilter15);
        org.junit.Assert.assertTrue("'" + boolean16 + "' != '" + true + "'", boolean16 == true);
        org.junit.Assert.assertNotNull(tokenFilter18);
        org.junit.Assert.assertTrue("'" + boolean19 + "' != '" + false + "'", boolean19 == false);
        org.junit.Assert.assertNull(jsonPointer21);
    }

    @Test
    public void test1671() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1671");
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter0 = new tools.jackson.core.util.MinimalPrettyPrinter();
        tools.jackson.core.JsonGenerator jsonGenerator1 = null;
        minimalPrettyPrinter0.beforeObjectEntries(jsonGenerator1);
        tools.jackson.core.util.Separators separators6 = new tools.jackson.core.util.Separators('~', ' ', ' ');
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter7 = minimalPrettyPrinter0.setSeparators(separators6);
        tools.jackson.core.JsonGenerator jsonGenerator8 = null;
        tools.jackson.core.util.JsonGeneratorDelegate jsonGeneratorDelegate9 = new tools.jackson.core.util.JsonGeneratorDelegate(jsonGenerator8);
        minimalPrettyPrinter0.beforeObjectEntries((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate9);
        tools.jackson.core.util.JsonGeneratorDelegate jsonGeneratorDelegate11 = new tools.jackson.core.util.JsonGeneratorDelegate((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate9);
        tools.jackson.core.JsonToken jsonToken13 = null;
        tools.jackson.core.type.WritableTypeId writableTypeId15 = new tools.jackson.core.type.WritableTypeId((java.lang.Object) '#', jsonToken13, (java.lang.Object) ' ');
        writableTypeId15.asProperty = "hi!";
        java.lang.Class<?> wildcardClass18 = writableTypeId15.forValueType;
        writableTypeId15.forValue = "";
        java.lang.Object obj21 = writableTypeId15.forValue;
        tools.jackson.core.type.WritableTypeId writableTypeId22 = jsonGeneratorDelegate11.writeTypeSuffix(writableTypeId15);
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean23 = jsonGeneratorDelegate11.isClosed();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(minimalPrettyPrinter7);
        org.junit.Assert.assertNull(wildcardClass18);
        org.junit.Assert.assertEquals("'" + obj21 + "' != '" + "" + "'", obj21, "");
        org.junit.Assert.assertNotNull(writableTypeId22);
    }

    @Test
    public void test1672() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1672");
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder0 = tools.jackson.core.json.JsonFactory.builder();
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder2 = jsonFactoryBuilder0.highestNonEscapedChar((-324));
        tools.jackson.core.util.JsonpCharacterEscapes jsonpCharacterEscapes3 = tools.jackson.core.util.JsonpCharacterEscapes.instance();
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder4 = jsonFactoryBuilder2.characterEscapes((tools.jackson.core.io.CharacterEscapes) jsonpCharacterEscapes3);
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder5 = tools.jackson.core.json.JsonFactory.builderWithJackson2Defaults();
        tools.jackson.core.SerializableString serializableString6 = null;
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder7 = jsonFactoryBuilder5.rootValueSeparator(serializableString6);
        tools.jackson.core.io.CharacterEscapes characterEscapes8 = null;
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder9 = jsonFactoryBuilder5.characterEscapes(characterEscapes8);
        tools.jackson.core.ErrorReportConfiguration errorReportConfiguration10 = null;
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder11 = jsonFactoryBuilder9.errorReportConfiguration(errorReportConfiguration10);
        tools.jackson.core.json.JsonFactory jsonFactory12 = new tools.jackson.core.json.JsonFactory();
        tools.jackson.core.ErrorReportConfiguration errorReportConfiguration13 = jsonFactory12.errorReportConfiguration();
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder14 = jsonFactoryBuilder11.errorReportConfiguration(errorReportConfiguration13);
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder15 = jsonFactoryBuilder2.errorReportConfiguration(errorReportConfiguration13);
        tools.jackson.core.util.RecyclerPool<tools.jackson.core.util.BufferRecycler> bufferRecyclerRecyclerPool16 = jsonFactoryBuilder15.recyclerPool();
        org.junit.Assert.assertNotNull(jsonFactoryBuilder0);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder2);
        org.junit.Assert.assertNotNull(jsonpCharacterEscapes3);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder4);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder5);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder7);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder9);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder11);
        org.junit.Assert.assertNotNull(errorReportConfiguration13);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder14);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder15);
        org.junit.Assert.assertNull(bufferRecyclerRecyclerPool16);
    }

    @Test
    public void test1673() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1673");
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder0 = tools.jackson.core.json.JsonFactory.builder();
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder2 = jsonFactoryBuilder0.highestNonEscapedChar((-324));
        tools.jackson.core.util.JsonpCharacterEscapes jsonpCharacterEscapes3 = tools.jackson.core.util.JsonpCharacterEscapes.instance();
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder4 = jsonFactoryBuilder2.characterEscapes((tools.jackson.core.io.CharacterEscapes) jsonpCharacterEscapes3);
        int[] intArray5 = jsonpCharacterEscapes3.getEscapeCodesForAscii();
        org.junit.Assert.assertNotNull(jsonFactoryBuilder0);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder2);
        org.junit.Assert.assertNotNull(jsonpCharacterEscapes3);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder4);
        org.junit.Assert.assertNotNull(intArray5);
    }

    @Test
    public void test1674() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1674");
        tools.jackson.core.filter.TokenFilter tokenFilter0 = null;
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext1 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter0);
        java.lang.Object obj2 = tokenFilterContext1.currentValue();
        java.lang.Object obj3 = tokenFilterContext1.currentValue();
        int int4 = tokenFilterContext1.getCurrentIndex();
        tools.jackson.core.JsonGenerator jsonGenerator5 = null;
        tools.jackson.core.util.JsonGeneratorDelegate jsonGeneratorDelegate6 = new tools.jackson.core.util.JsonGeneratorDelegate(jsonGenerator5);
        tokenFilterContext1.ensurePropertyNameWritten((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate6);
        tools.jackson.core.JsonParser jsonParser9 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter10 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion11 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate14 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser9, tokenFilter10, inclusion11, true, true);
        tools.jackson.core.JsonParser jsonParser15 = filteringParserDelegate14.skipChildren();
        tools.jackson.core.json.DupDetector dupDetector17 = null;
        tools.jackson.core.util.SimpleStreamWriteContext simpleStreamWriteContext18 = tools.jackson.core.util.SimpleStreamWriteContext.createRootContext(dupDetector17);
        tools.jackson.core.io.ContentReference contentReference19 = null;
        tools.jackson.core.TokenStreamLocation tokenStreamLocation20 = simpleStreamWriteContext18.startLocation(contentReference19);
        java.lang.String str21 = tokenStreamLocation20.sourceDescription();
        tools.jackson.core.JsonGenerator jsonGenerator22 = null;
        java.lang.Throwable throwable24 = null;
        tools.jackson.core.exc.StreamWriteException streamWriteException25 = new tools.jackson.core.exc.StreamWriteException(jsonGenerator22, "hi!", throwable24);
        tools.jackson.core.JsonGenerator jsonGenerator26 = streamWriteException25.processor();
        tools.jackson.core.exc.StreamReadException streamReadException27 = new tools.jackson.core.exc.StreamReadException((tools.jackson.core.JsonParser) filteringParserDelegate14, "root", tokenStreamLocation20, (java.lang.Throwable) streamWriteException25);
        int int28 = tokenStreamLocation20.getLineNr();
        int int29 = tokenStreamLocation20.getColumnNr();
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.JsonGenerator jsonGenerator30 = jsonGeneratorDelegate6.writePOJOProperty("//0.0.0", (java.lang.Object) int29);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(tokenFilterContext1);
        org.junit.Assert.assertNull(obj2);
        org.junit.Assert.assertNull(obj3);
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 0 + "'", int4 == 0);
        org.junit.Assert.assertNotNull(jsonParser15);
        org.junit.Assert.assertNotNull(simpleStreamWriteContext18);
        org.junit.Assert.assertNotNull(tokenStreamLocation20);
        org.junit.Assert.assertEquals("'" + str21 + "' != '" + "UNKNOWN" + "'", str21, "UNKNOWN");
        org.junit.Assert.assertNull(jsonGenerator26);
        org.junit.Assert.assertTrue("'" + int28 + "' != '" + (-1) + "'", int28 == (-1));
        org.junit.Assert.assertTrue("'" + int29 + "' != '" + (-1) + "'", int29 == (-1));
    }

    @Test
    public void test1675() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1675");
        tools.jackson.core.util.BufferRecycler bufferRecycler0 = null;
        tools.jackson.core.util.TextBuffer textBuffer1 = new tools.jackson.core.util.TextBuffer(bufferRecycler0);
        textBuffer1.resetWithString("");
        boolean boolean4 = textBuffer1.hasTextAsCharacters();
        boolean boolean5 = textBuffer1.hasTextAsCharacters();
        textBuffer1.resetWith('\000');
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str9 = textBuffer1.setCurrentAndReturn((-323));
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: TextBuffer overrun: size reached (-322) exceeds maximum of 2147483647");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
    }

    @Test
    public void test1676() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1676");
        double double2 = tools.jackson.core.io.NumberInput.parseAsDouble("6.5577783776342712E18", (double) 14);
        org.junit.Assert.assertTrue("'" + double2 + "' != '" + 6.5577783776342712E18d + "'", double2 == 6.5577783776342712E18d);
    }

    @Test
    public void test1677() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1677");
        tools.jackson.core.util.DefaultPrettyPrinter.NopIndenter nopIndenter0 = tools.jackson.core.util.DefaultPrettyPrinter.NopIndenter.instance();
        boolean boolean1 = nopIndenter0.isInline();
        tools.jackson.core.util.DefaultPrettyPrinter.NopIndenter nopIndenter2 = new tools.jackson.core.util.DefaultPrettyPrinter.NopIndenter();
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter3 = new tools.jackson.core.util.MinimalPrettyPrinter();
        tools.jackson.core.JsonGenerator jsonGenerator4 = null;
        minimalPrettyPrinter3.beforeObjectEntries(jsonGenerator4);
        tools.jackson.core.util.Separators separators9 = new tools.jackson.core.util.Separators('~', ' ', ' ');
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter10 = minimalPrettyPrinter3.setSeparators(separators9);
        tools.jackson.core.JsonGenerator jsonGenerator11 = null;
        tools.jackson.core.util.JsonGeneratorDelegate jsonGeneratorDelegate12 = new tools.jackson.core.util.JsonGeneratorDelegate(jsonGenerator11);
        minimalPrettyPrinter3.beforeObjectEntries((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate12);
        tools.jackson.core.filter.TokenFilter tokenFilter14 = null;
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext15 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter14);
        tools.jackson.core.JsonToken jsonToken16 = tokenFilterContext15.nextTokenToRead();
        tools.jackson.core.filter.TokenFilter tokenFilter17 = tools.jackson.core.filter.TokenFilter.INCLUDE_ALL;
        tools.jackson.core.filter.TokenFilter tokenFilter18 = tokenFilterContext15.checkValue(tokenFilter17);
        java.math.BigDecimal bigDecimal19 = null;
        boolean boolean20 = tokenFilter17.includeNumber(bigDecimal19);
        tools.jackson.core.filter.TokenFilter tokenFilter22 = tokenFilter17.includeRootValue((int) (byte) 1);
        tools.jackson.core.io.IOContext iOContext23 = null;
        java.io.InputStream inputStream24 = null;
        byte[] byteArray28 = new byte[] { (byte) 100, (byte) 1 };
        tools.jackson.core.io.UTF32Reader uTF32Reader32 = new tools.jackson.core.io.UTF32Reader(iOContext23, inputStream24, false, byteArray28, (int) (short) 0, (-1), false);
        boolean boolean34 = tokenFilter22.includeString((java.io.Reader) uTF32Reader32, (-1074));
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion35 = tools.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_NON_NULL;
        tools.jackson.core.filter.FilteringGeneratorDelegate filteringGeneratorDelegate37 = new tools.jackson.core.filter.FilteringGeneratorDelegate((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate12, tokenFilter22, inclusion35, true);
        nopIndenter2.writeIndentation((tools.jackson.core.JsonGenerator) filteringGeneratorDelegate37, (int) '#');
        nopIndenter0.writeIndentation((tools.jackson.core.JsonGenerator) filteringGeneratorDelegate37, (-149));
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj42 = filteringGeneratorDelegate37.streamWriteOutputTarget();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(nopIndenter0);
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + true + "'", boolean1 == true);
        org.junit.Assert.assertNotNull(minimalPrettyPrinter10);
        org.junit.Assert.assertNotNull(tokenFilterContext15);
        org.junit.Assert.assertNull(jsonToken16);
        org.junit.Assert.assertNotNull(tokenFilter17);
        org.junit.Assert.assertNotNull(tokenFilter18);
        org.junit.Assert.assertTrue("'" + boolean20 + "' != '" + true + "'", boolean20 == true);
        org.junit.Assert.assertNotNull(tokenFilter22);
        org.junit.Assert.assertNotNull(byteArray28);
        org.junit.Assert.assertArrayEquals(byteArray28, new byte[] { (byte) 100, (byte) 1 });
        org.junit.Assert.assertTrue("'" + boolean34 + "' != '" + true + "'", boolean34 == true);
        org.junit.Assert.assertTrue("'" + inclusion35 + "' != '" + tools.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_NON_NULL + "'", inclusion35.equals(tools.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_NON_NULL));
    }

    @Test
    public void test1678() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1678");
        tools.jackson.core.util.DefaultPrettyPrinter.NopIndenter nopIndenter0 = tools.jackson.core.util.DefaultPrettyPrinter.NopIndenter.instance();
        boolean boolean1 = nopIndenter0.isInline();
        tools.jackson.core.util.DefaultPrettyPrinter.NopIndenter nopIndenter2 = new tools.jackson.core.util.DefaultPrettyPrinter.NopIndenter();
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter3 = new tools.jackson.core.util.MinimalPrettyPrinter();
        tools.jackson.core.JsonGenerator jsonGenerator4 = null;
        minimalPrettyPrinter3.beforeObjectEntries(jsonGenerator4);
        tools.jackson.core.util.Separators separators9 = new tools.jackson.core.util.Separators('~', ' ', ' ');
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter10 = minimalPrettyPrinter3.setSeparators(separators9);
        tools.jackson.core.JsonGenerator jsonGenerator11 = null;
        tools.jackson.core.util.JsonGeneratorDelegate jsonGeneratorDelegate12 = new tools.jackson.core.util.JsonGeneratorDelegate(jsonGenerator11);
        minimalPrettyPrinter3.beforeObjectEntries((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate12);
        tools.jackson.core.filter.TokenFilter tokenFilter14 = null;
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext15 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter14);
        tools.jackson.core.JsonToken jsonToken16 = tokenFilterContext15.nextTokenToRead();
        tools.jackson.core.filter.TokenFilter tokenFilter17 = tools.jackson.core.filter.TokenFilter.INCLUDE_ALL;
        tools.jackson.core.filter.TokenFilter tokenFilter18 = tokenFilterContext15.checkValue(tokenFilter17);
        java.math.BigDecimal bigDecimal19 = null;
        boolean boolean20 = tokenFilter17.includeNumber(bigDecimal19);
        tools.jackson.core.filter.TokenFilter tokenFilter22 = tokenFilter17.includeRootValue((int) (byte) 1);
        tools.jackson.core.io.IOContext iOContext23 = null;
        java.io.InputStream inputStream24 = null;
        byte[] byteArray28 = new byte[] { (byte) 100, (byte) 1 };
        tools.jackson.core.io.UTF32Reader uTF32Reader32 = new tools.jackson.core.io.UTF32Reader(iOContext23, inputStream24, false, byteArray28, (int) (short) 0, (-1), false);
        boolean boolean34 = tokenFilter22.includeString((java.io.Reader) uTF32Reader32, (-1074));
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion35 = tools.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_NON_NULL;
        tools.jackson.core.filter.FilteringGeneratorDelegate filteringGeneratorDelegate37 = new tools.jackson.core.filter.FilteringGeneratorDelegate((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate12, tokenFilter22, inclusion35, true);
        nopIndenter2.writeIndentation((tools.jackson.core.JsonGenerator) filteringGeneratorDelegate37, (int) '#');
        nopIndenter0.writeIndentation((tools.jackson.core.JsonGenerator) filteringGeneratorDelegate37, (-149));
        tools.jackson.core.exc.StreamWriteException streamWriteException43 = new tools.jackson.core.exc.StreamWriteException((tools.jackson.core.JsonGenerator) filteringGeneratorDelegate37, "0.0.0");
        tools.jackson.core.JsonParser jsonParser44 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter45 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion46 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate49 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser44, tokenFilter45, inclusion46, true, true);
        boolean boolean50 = filteringParserDelegate49.isExpectedStartObjectToken();
        int int51 = filteringParserDelegate49.currentTokenId();
        boolean boolean52 = filteringParserDelegate49.isExpectedStartObjectToken();
        tools.jackson.core.filter.TokenFilter tokenFilter53 = filteringParserDelegate49.getFilter();
        // The following exception was thrown during execution in test generation
        try {
            filteringGeneratorDelegate37.copyCurrentStructure((tools.jackson.core.JsonParser) filteringParserDelegate49);
            org.junit.Assert.fail("Expected exception of type tools.jackson.core.exc.StreamWriteException; message: No current event to copy? at [No location information]");
        } catch (tools.jackson.core.exc.StreamWriteException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(nopIndenter0);
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + true + "'", boolean1 == true);
        org.junit.Assert.assertNotNull(minimalPrettyPrinter10);
        org.junit.Assert.assertNotNull(tokenFilterContext15);
        org.junit.Assert.assertNull(jsonToken16);
        org.junit.Assert.assertNotNull(tokenFilter17);
        org.junit.Assert.assertNotNull(tokenFilter18);
        org.junit.Assert.assertTrue("'" + boolean20 + "' != '" + true + "'", boolean20 == true);
        org.junit.Assert.assertNotNull(tokenFilter22);
        org.junit.Assert.assertNotNull(byteArray28);
        org.junit.Assert.assertArrayEquals(byteArray28, new byte[] { (byte) 100, (byte) 1 });
        org.junit.Assert.assertTrue("'" + boolean34 + "' != '" + true + "'", boolean34 == true);
        org.junit.Assert.assertTrue("'" + inclusion35 + "' != '" + tools.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_NON_NULL + "'", inclusion35.equals(tools.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_NON_NULL));
        org.junit.Assert.assertTrue("'" + boolean50 + "' != '" + false + "'", boolean50 == false);
        org.junit.Assert.assertTrue("'" + int51 + "' != '" + 0 + "'", int51 == 0);
        org.junit.Assert.assertTrue("'" + boolean52 + "' != '" + false + "'", boolean52 == false);
        org.junit.Assert.assertNull(tokenFilter53);
    }

    @Test
    public void test1679() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1679");
        tools.jackson.core.json.JsonFactory jsonFactory0 = new tools.jackson.core.json.JsonFactory();
        tools.jackson.core.ErrorReportConfiguration errorReportConfiguration1 = jsonFactory0.errorReportConfiguration();
        tools.jackson.core.json.JsonFactory jsonFactory2 = jsonFactory0.copy();
        tools.jackson.core.StreamReadFeature streamReadFeature3 = null;
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean4 = jsonFactory2.isEnabled(streamReadFeature3);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(errorReportConfiguration1);
        org.junit.Assert.assertNotNull(jsonFactory2);
    }

    @Test
    public void test1680() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1680");
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
            double double12 = textBuffer1.contentsAsDouble(false);
            org.junit.Assert.fail("Expected exception of type java.lang.NumberFormatException; message: empty String");
        } catch (java.lang.NumberFormatException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 0 + "'", int4 == 0);
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "" + "'", str5, "");
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "" + "'", str8, "");
        org.junit.Assert.assertEquals("'" + str9 + "' != '" + "" + "'", str9, "");
        org.junit.Assert.assertNotNull(charArray10);
    }

    @Test
    public void test1681() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1681");
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
        tools.jackson.core.filter.TokenFilter tokenFilter35 = null;
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext36 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter35);
        tools.jackson.core.JsonToken jsonToken37 = tokenFilterContext36.nextTokenToRead();
        tools.jackson.core.filter.TokenFilter tokenFilter38 = tools.jackson.core.filter.TokenFilter.INCLUDE_ALL;
        tools.jackson.core.filter.TokenFilter tokenFilter39 = tokenFilterContext36.checkValue(tokenFilter38);
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext40 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter38);
        tools.jackson.core.filter.TokenFilter tokenFilter42 = tokenFilter38.includeRootValue(309);
        java.lang.String str43 = tokenFilter42.toString();
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion44 = null;
        tools.jackson.core.filter.FilteringGeneratorDelegate filteringGeneratorDelegate46 = new tools.jackson.core.filter.FilteringGeneratorDelegate((tools.jackson.core.JsonGenerator) filteringGeneratorDelegate34, tokenFilter42, inclusion44, false);
        tools.jackson.core.JsonGenerator jsonGenerator47 = filteringGeneratorDelegate46.delegate();
        tools.jackson.core.TokenStreamContext tokenStreamContext48 = filteringGeneratorDelegate46.streamWriteContext();
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.JsonGenerator jsonGenerator49 = filteringGeneratorDelegate46.writeEndArray();
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
        org.junit.Assert.assertNotNull(tokenFilterContext36);
        org.junit.Assert.assertNull(jsonToken37);
        org.junit.Assert.assertNotNull(tokenFilter38);
        org.junit.Assert.assertNotNull(tokenFilter39);
        org.junit.Assert.assertNotNull(tokenFilterContext40);
        org.junit.Assert.assertNotNull(tokenFilter42);
        org.junit.Assert.assertEquals("'" + str43 + "' != '" + "TokenFilter.INCLUDE_ALL" + "'", str43, "TokenFilter.INCLUDE_ALL");
        org.junit.Assert.assertNotNull(jsonGenerator47);
        org.junit.Assert.assertNotNull(tokenStreamContext48);
    }

    @Test
    public void test1682() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1682");
        tools.jackson.core.json.JsonFactory jsonFactory0 = new tools.jackson.core.json.JsonFactory();
        tools.jackson.core.ErrorReportConfiguration errorReportConfiguration1 = jsonFactory0.errorReportConfiguration();
        tools.jackson.core.sym.CharsToNameCanonicalizer charsToNameCanonicalizer2 = tools.jackson.core.sym.CharsToNameCanonicalizer.createRoot((tools.jackson.core.TokenStreamFactory) jsonFactory0);
        tools.jackson.core.sym.CharsToNameCanonicalizer charsToNameCanonicalizer3 = charsToNameCanonicalizer2.makeChild();
        int int4 = charsToNameCanonicalizer3.size();
        org.junit.Assert.assertNotNull(errorReportConfiguration1);
        org.junit.Assert.assertNotNull(charsToNameCanonicalizer2);
        org.junit.Assert.assertNotNull(charsToNameCanonicalizer3);
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 0 + "'", int4 == 0);
    }

    @Test
    public void test1683() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1683");
        tools.jackson.core.Version version0 = tools.jackson.core.Version.unknownVersion();
        int int1 = version0.getPatchLevel();
        tools.jackson.core.Version version2 = tools.jackson.core.Version.unknownVersion();
        boolean boolean3 = version2.isUnknownVersion();
        int int4 = version0.compareTo(version2);
        tools.jackson.core.Version version5 = tools.jackson.core.Version.unknownVersion();
        int int6 = version5.getPatchLevel();
        java.lang.String str7 = version5.toString();
        java.lang.String str8 = version5.getArtifactId();
        int int9 = version2.compareTo(version5);
        java.lang.String str10 = version5.toString();
        int int11 = version5.getMinorVersion();
        org.junit.Assert.assertNotNull(version0);
        org.junit.Assert.assertTrue("'" + int1 + "' != '" + 0 + "'", int1 == 0);
        org.junit.Assert.assertNotNull(version2);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + true + "'", boolean3 == true);
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 0 + "'", int4 == 0);
        org.junit.Assert.assertNotNull(version5);
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "0.0.0" + "'", str7, "0.0.0");
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "" + "'", str8, "");
        org.junit.Assert.assertTrue("'" + int9 + "' != '" + 0 + "'", int9 == 0);
        org.junit.Assert.assertEquals("'" + str10 + "' != '" + "0.0.0" + "'", str10, "0.0.0");
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 0 + "'", int11 == 0);
    }

    @Test
    public void test1684() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1684");
        tools.jackson.core.json.JsonFactory jsonFactory0 = new tools.jackson.core.json.JsonFactory();
        tools.jackson.core.ErrorReportConfiguration errorReportConfiguration1 = jsonFactory0.errorReportConfiguration();
        tools.jackson.core.sym.CharsToNameCanonicalizer charsToNameCanonicalizer2 = tools.jackson.core.sym.CharsToNameCanonicalizer.createRoot((tools.jackson.core.TokenStreamFactory) jsonFactory0);
        tools.jackson.core.sym.CharsToNameCanonicalizer charsToNameCanonicalizer3 = charsToNameCanonicalizer2.makeChild();
        int int4 = charsToNameCanonicalizer2.hashSeed();
        org.junit.Assert.assertNotNull(errorReportConfiguration1);
        org.junit.Assert.assertNotNull(charsToNameCanonicalizer2);
        org.junit.Assert.assertNotNull(charsToNameCanonicalizer3);
// flaky "3) test1684(RegressionTest3)":         org.junit.Assert.assertTrue("'" + int4 + "' != '" + 1428015511 + "'", int4 == 1428015511);
    }

    @Test
    public void test1685() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1685");
        tools.jackson.core.util.DefaultPrettyPrinter defaultPrettyPrinter0 = new tools.jackson.core.util.DefaultPrettyPrinter();
        tools.jackson.core.util.DefaultPrettyPrinter.FixedSpaceIndenter fixedSpaceIndenter1 = tools.jackson.core.util.DefaultPrettyPrinter.FixedSpaceIndenter.instance();
        defaultPrettyPrinter0.indentArraysWith((tools.jackson.core.util.DefaultPrettyPrinter.Indenter) fixedSpaceIndenter1);
        tools.jackson.core.util.Separators separators3 = tools.jackson.core.util.Separators.createDefaultInstance();
        tools.jackson.core.util.Separators separators5 = separators3.withObjectEntrySeparator('4');
        tools.jackson.core.util.Separators separators7 = separators5.withArrayElementSeparator('#');
        tools.jackson.core.util.DefaultPrettyPrinter defaultPrettyPrinter8 = defaultPrettyPrinter0.withSeparators(separators7);
        tools.jackson.core.JacksonException.Reference reference9 = new tools.jackson.core.JacksonException.Reference((java.lang.Object) defaultPrettyPrinter0);
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter10 = new tools.jackson.core.util.MinimalPrettyPrinter();
        tools.jackson.core.JsonGenerator jsonGenerator11 = null;
        minimalPrettyPrinter10.beforeObjectEntries(jsonGenerator11);
        tools.jackson.core.util.Separators separators16 = new tools.jackson.core.util.Separators('~', ' ', ' ');
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter17 = minimalPrettyPrinter10.setSeparators(separators16);
        tools.jackson.core.JsonGenerator jsonGenerator18 = null;
        tools.jackson.core.util.JsonGeneratorDelegate jsonGeneratorDelegate19 = new tools.jackson.core.util.JsonGeneratorDelegate(jsonGenerator18);
        minimalPrettyPrinter10.beforeObjectEntries((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate19);
        tools.jackson.core.filter.TokenFilter tokenFilter21 = null;
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext22 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter21);
        tools.jackson.core.JsonToken jsonToken23 = tokenFilterContext22.nextTokenToRead();
        tools.jackson.core.filter.TokenFilter tokenFilter24 = tools.jackson.core.filter.TokenFilter.INCLUDE_ALL;
        tools.jackson.core.filter.TokenFilter tokenFilter25 = tokenFilterContext22.checkValue(tokenFilter24);
        java.math.BigDecimal bigDecimal26 = null;
        boolean boolean27 = tokenFilter24.includeNumber(bigDecimal26);
        tools.jackson.core.filter.TokenFilter tokenFilter29 = tokenFilter24.includeRootValue((int) (byte) 1);
        tools.jackson.core.io.IOContext iOContext30 = null;
        java.io.InputStream inputStream31 = null;
        byte[] byteArray35 = new byte[] { (byte) 100, (byte) 1 };
        tools.jackson.core.io.UTF32Reader uTF32Reader39 = new tools.jackson.core.io.UTF32Reader(iOContext30, inputStream31, false, byteArray35, (int) (short) 0, (-1), false);
        boolean boolean41 = tokenFilter29.includeString((java.io.Reader) uTF32Reader39, (-1074));
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion42 = tools.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_NON_NULL;
        tools.jackson.core.filter.FilteringGeneratorDelegate filteringGeneratorDelegate44 = new tools.jackson.core.filter.FilteringGeneratorDelegate((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate19, tokenFilter29, inclusion42, true);
        tools.jackson.core.filter.TokenFilter tokenFilter45 = null;
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext46 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter45);
        tools.jackson.core.JsonToken jsonToken47 = tokenFilterContext46.nextTokenToRead();
        tools.jackson.core.filter.TokenFilter tokenFilter48 = tools.jackson.core.filter.TokenFilter.INCLUDE_ALL;
        tools.jackson.core.filter.TokenFilter tokenFilter49 = tokenFilterContext46.checkValue(tokenFilter48);
        java.math.BigDecimal bigDecimal50 = null;
        boolean boolean51 = tokenFilter48.includeNumber(bigDecimal50);
        tools.jackson.core.filter.TokenFilter tokenFilter53 = tokenFilter48.includeRootValue((int) (byte) 1);
        tools.jackson.core.filter.TokenFilter tokenFilter55 = tokenFilter48.includeElement(4);
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion56 = tools.jackson.core.filter.TokenFilter.Inclusion.ONLY_INCLUDE_ALL;
        tools.jackson.core.filter.FilteringGeneratorDelegate filteringGeneratorDelegate58 = new tools.jackson.core.filter.FilteringGeneratorDelegate((tools.jackson.core.JsonGenerator) filteringGeneratorDelegate44, tokenFilter55, inclusion56, false);
        tools.jackson.core.TokenStreamContext tokenStreamContext59 = filteringGeneratorDelegate44.streamWriteContext();
        // The following exception was thrown during execution in test generation
        try {
            defaultPrettyPrinter0.writeStartArray((tools.jackson.core.JsonGenerator) filteringGeneratorDelegate44);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(fixedSpaceIndenter1);
        org.junit.Assert.assertNotNull(separators3);
        org.junit.Assert.assertNotNull(separators5);
        org.junit.Assert.assertNotNull(separators7);
        org.junit.Assert.assertNotNull(defaultPrettyPrinter8);
        org.junit.Assert.assertNotNull(minimalPrettyPrinter17);
        org.junit.Assert.assertNotNull(tokenFilterContext22);
        org.junit.Assert.assertNull(jsonToken23);
        org.junit.Assert.assertNotNull(tokenFilter24);
        org.junit.Assert.assertNotNull(tokenFilter25);
        org.junit.Assert.assertTrue("'" + boolean27 + "' != '" + true + "'", boolean27 == true);
        org.junit.Assert.assertNotNull(tokenFilter29);
        org.junit.Assert.assertNotNull(byteArray35);
        org.junit.Assert.assertArrayEquals(byteArray35, new byte[] { (byte) 100, (byte) 1 });
        org.junit.Assert.assertTrue("'" + boolean41 + "' != '" + true + "'", boolean41 == true);
        org.junit.Assert.assertTrue("'" + inclusion42 + "' != '" + tools.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_NON_NULL + "'", inclusion42.equals(tools.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_NON_NULL));
        org.junit.Assert.assertNotNull(tokenFilterContext46);
        org.junit.Assert.assertNull(jsonToken47);
        org.junit.Assert.assertNotNull(tokenFilter48);
        org.junit.Assert.assertNotNull(tokenFilter49);
        org.junit.Assert.assertTrue("'" + boolean51 + "' != '" + true + "'", boolean51 == true);
        org.junit.Assert.assertNotNull(tokenFilter53);
        org.junit.Assert.assertNotNull(tokenFilter55);
        org.junit.Assert.assertTrue("'" + inclusion56 + "' != '" + tools.jackson.core.filter.TokenFilter.Inclusion.ONLY_INCLUDE_ALL + "'", inclusion56.equals(tools.jackson.core.filter.TokenFilter.Inclusion.ONLY_INCLUDE_ALL));
        org.junit.Assert.assertNotNull(tokenStreamContext59);
    }

    @Test
    public void test1686() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1686");
        tools.jackson.core.json.JsonFactory jsonFactory0 = new tools.jackson.core.json.JsonFactory();
        tools.jackson.core.ErrorReportConfiguration errorReportConfiguration1 = jsonFactory0.errorReportConfiguration();
        tools.jackson.core.sym.CharsToNameCanonicalizer charsToNameCanonicalizer2 = tools.jackson.core.sym.CharsToNameCanonicalizer.createRoot((tools.jackson.core.TokenStreamFactory) jsonFactory0);
        tools.jackson.core.ObjectWriteContext objectWriteContext3 = tools.jackson.core.TokenStreamFactory.EMPTY_WRITE_CONTEXT;
        tools.jackson.core.util.BufferRecycler bufferRecycler4 = new tools.jackson.core.util.BufferRecycler();
        tools.jackson.core.util.TextBuffer textBuffer5 = new tools.jackson.core.util.TextBuffer(bufferRecycler4);
        tools.jackson.core.io.SegmentedStringWriter segmentedStringWriter6 = new tools.jackson.core.io.SegmentedStringWriter(bufferRecycler4);
        tools.jackson.core.io.SegmentedStringWriter segmentedStringWriter7 = new tools.jackson.core.io.SegmentedStringWriter(bufferRecycler4);
        tools.jackson.core.JsonGenerator jsonGenerator8 = jsonFactory0.createGenerator(objectWriteContext3, (java.io.Writer) segmentedStringWriter7);
        tools.jackson.core.util.ByteArrayBuilder byteArrayBuilder10 = new tools.jackson.core.util.ByteArrayBuilder((int) 'a');
        byteArrayBuilder10.flush();
        tools.jackson.core.JsonEncoding jsonEncoding12 = null;
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.JsonGenerator jsonGenerator13 = jsonFactory0.createGenerator((java.io.OutputStream) byteArrayBuilder10, jsonEncoding12);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(errorReportConfiguration1);
        org.junit.Assert.assertNotNull(charsToNameCanonicalizer2);
        org.junit.Assert.assertNotNull(objectWriteContext3);
        org.junit.Assert.assertNotNull(jsonGenerator8);
    }

    @Test
    public void test1687() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1687");
        tools.jackson.core.io.SerializedString serializedString1 = new tools.jackson.core.io.SerializedString("Unexpected end of base64-encoded String: base64 variant 'MODIFIED-FOR-URL' expects padding (one or more '\000' characters) at the end. This Base64Variant might have been incorrectly configured");
        byte[] byteArray2 = serializedString1.asQuotedUTF8();
        org.junit.Assert.assertNotNull(byteArray2);
    }

    @Test
    public void test1688() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1688");
        tools.jackson.core.json.DupDetector dupDetector0 = null;
        tools.jackson.core.util.SimpleStreamWriteContext simpleStreamWriteContext1 = tools.jackson.core.util.SimpleStreamWriteContext.createRootContext(dupDetector0);
        tools.jackson.core.json.DupDetector dupDetector2 = simpleStreamWriteContext1.getDupDetector();
        boolean boolean3 = simpleStreamWriteContext1.inObject();
        tools.jackson.core.json.JsonFactory jsonFactory4 = new tools.jackson.core.json.JsonFactory();
        java.lang.Class<? extends tools.jackson.core.FormatFeature> wildcardClass5 = jsonFactory4.getFormatWriteFeatureType();
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder6 = jsonFactory4.rebuild();
        tools.jackson.core.util.SimpleStreamWriteContext simpleStreamWriteContext7 = simpleStreamWriteContext1.createChildArrayContext((java.lang.Object) jsonFactoryBuilder6);
        org.junit.Assert.assertNotNull(simpleStreamWriteContext1);
        org.junit.Assert.assertNull(dupDetector2);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
        org.junit.Assert.assertNotNull(wildcardClass5);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder6);
        org.junit.Assert.assertNotNull(simpleStreamWriteContext7);
    }

    @Test
    public void test1689() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1689");
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
            long long11 = filteringParserDelegate5.getValueAsLong((long) 56);
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
    public void test1690() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1690");
        tools.jackson.core.exc.StreamConstraintsException streamConstraintsException1 = new tools.jackson.core.exc.StreamConstraintsException("0.0.0");
        tools.jackson.core.exc.StreamReadException streamReadException3 = new tools.jackson.core.exc.StreamReadException("");
        java.lang.String str4 = streamReadException3.getPathReference();
        tools.jackson.core.exc.StreamReadException streamReadException6 = new tools.jackson.core.exc.StreamReadException("");
        streamReadException3.addSuppressed((java.lang.Throwable) streamReadException6);
        tools.jackson.core.JacksonException jacksonException8 = streamConstraintsException1.withCause((java.lang.Throwable) streamReadException3);
        tools.jackson.core.util.DefaultPrettyPrinter defaultPrettyPrinter9 = new tools.jackson.core.util.DefaultPrettyPrinter();
        tools.jackson.core.util.DefaultPrettyPrinter.FixedSpaceIndenter fixedSpaceIndenter10 = tools.jackson.core.util.DefaultPrettyPrinter.FixedSpaceIndenter.instance();
        defaultPrettyPrinter9.indentArraysWith((tools.jackson.core.util.DefaultPrettyPrinter.Indenter) fixedSpaceIndenter10);
        tools.jackson.core.util.Separators separators12 = tools.jackson.core.util.Separators.createDefaultInstance();
        tools.jackson.core.util.Separators separators14 = separators12.withObjectEntrySeparator('4');
        tools.jackson.core.util.Separators separators16 = separators14.withArrayElementSeparator('#');
        tools.jackson.core.util.DefaultPrettyPrinter defaultPrettyPrinter17 = defaultPrettyPrinter9.withSeparators(separators16);
        tools.jackson.core.JacksonException jacksonException19 = tools.jackson.core.JacksonException.wrapWithPath((java.lang.Throwable) streamConstraintsException1, (java.lang.Object) defaultPrettyPrinter9, 256);
        tools.jackson.core.util.DefaultPrettyPrinter defaultPrettyPrinter20 = new tools.jackson.core.util.DefaultPrettyPrinter(defaultPrettyPrinter9);
        tools.jackson.core.json.JsonFactory jsonFactory21 = new tools.jackson.core.json.JsonFactory();
        int int22 = jsonFactory21.getFormatReadFeatures();
        tools.jackson.core.json.JsonFactory jsonFactory23 = new tools.jackson.core.json.JsonFactory();
        int int24 = jsonFactory23.getFormatReadFeatures();
        tools.jackson.core.ObjectWriteContext objectWriteContext25 = tools.jackson.core.TokenStreamFactory.EMPTY_WRITE_CONTEXT;
        tools.jackson.core.util.BufferRecycler bufferRecycler26 = new tools.jackson.core.util.BufferRecycler();
        tools.jackson.core.io.SegmentedStringWriter segmentedStringWriter27 = new tools.jackson.core.io.SegmentedStringWriter(bufferRecycler26);
        segmentedStringWriter27.write("MODIFIED-FOR-URL/UNKNOWN/0.0.0");
        java.io.Writer writer31 = segmentedStringWriter27.append('a');
        tools.jackson.core.JsonGenerator jsonGenerator32 = jsonFactory23.createGenerator(objectWriteContext25, (java.io.Writer) segmentedStringWriter27);
        tools.jackson.core.util.BufferRecycler bufferRecycler33 = null;
        tools.jackson.core.util.ByteArrayBuilder byteArrayBuilder34 = new tools.jackson.core.util.ByteArrayBuilder(bufferRecycler33);
        byte[] byteArray36 = byteArrayBuilder34.completeAndCoalesce(10);
        byte[] byteArray37 = byteArrayBuilder34.toByteArray();
        tools.jackson.core.util.BufferRecycler bufferRecycler38 = byteArrayBuilder34.bufferRecycler();
        tools.jackson.core.JsonGenerator jsonGenerator39 = jsonFactory21.createGenerator(objectWriteContext25, (java.io.OutputStream) byteArrayBuilder34);
        defaultPrettyPrinter20.writeStartObject(jsonGenerator39);
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "" + "'", str4, "");
        org.junit.Assert.assertNotNull(jacksonException8);
        org.junit.Assert.assertNotNull(fixedSpaceIndenter10);
        org.junit.Assert.assertNotNull(separators12);
        org.junit.Assert.assertNotNull(separators14);
        org.junit.Assert.assertNotNull(separators16);
        org.junit.Assert.assertNotNull(defaultPrettyPrinter17);
        org.junit.Assert.assertNotNull(jacksonException19);
        org.junit.Assert.assertTrue("'" + int22 + "' != '" + 0 + "'", int22 == 0);
        org.junit.Assert.assertTrue("'" + int24 + "' != '" + 0 + "'", int24 == 0);
        org.junit.Assert.assertNotNull(objectWriteContext25);
        org.junit.Assert.assertNotNull(writer31);
        org.junit.Assert.assertNotNull(jsonGenerator32);
        org.junit.Assert.assertNotNull(byteArray36);
        org.junit.Assert.assertArrayEquals(byteArray36, new byte[] { (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0 });
        org.junit.Assert.assertNotNull(byteArray37);
        org.junit.Assert.assertArrayEquals(byteArray37, new byte[] { (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0 });
        org.junit.Assert.assertNull(bufferRecycler38);
        org.junit.Assert.assertNotNull(jsonGenerator39);
    }

    @Test
    public void test1691() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1691");
        tools.jackson.core.exc.StreamReadException streamReadException1 = new tools.jackson.core.exc.StreamReadException("hi!");
        tools.jackson.core.JsonParser jsonParser2 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter3 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion4 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate7 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser2, tokenFilter3, inclusion4, true, true);
        boolean boolean8 = filteringParserDelegate7.isExpectedStartObjectToken();
        tools.jackson.core.exc.StreamReadException streamReadException9 = streamReadException1.withParser((tools.jackson.core.JsonParser) filteringParserDelegate7);
        boolean boolean10 = filteringParserDelegate7.hasCurrentToken();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj11 = filteringParserDelegate7.getObjectId();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertNotNull(streamReadException9);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
    }

    @Test
    public void test1692() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1692");
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
        java.lang.String str20 = filteringParserDelegate15.currentName();
        tools.jackson.core.filter.TokenFilter tokenFilter21 = filteringParserDelegate15.getFilter();
        filteringParserDelegate15.clearCurrentToken();
        // The following exception was thrown during execution in test generation
        try {
            java.math.BigInteger bigInteger23 = filteringParserDelegate15.getBigIntegerValue();
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
        org.junit.Assert.assertNull(str20);
        org.junit.Assert.assertNull(tokenFilter21);
    }

    @Test
    public void test1693() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1693");
        tools.jackson.core.json.JsonFactory jsonFactory0 = new tools.jackson.core.json.JsonFactory();
        java.lang.Class<? extends tools.jackson.core.FormatFeature> wildcardClass1 = jsonFactory0.getFormatWriteFeatureType();
        tools.jackson.core.io.CharacterEscapes characterEscapes2 = jsonFactory0.getCharacterEscapes();
        tools.jackson.core.util.BufferRecycler bufferRecycler3 = new tools.jackson.core.util.BufferRecycler();
        tools.jackson.core.io.SegmentedStringWriter segmentedStringWriter4 = new tools.jackson.core.io.SegmentedStringWriter(bufferRecycler3);
        tools.jackson.core.JsonGenerator jsonGenerator5 = jsonFactory0.createGenerator((java.io.Writer) segmentedStringWriter4);
        tools.jackson.core.JsonGenerator jsonGenerator7 = jsonGenerator5.writeOmittedProperty("//0.0.0");
        org.junit.Assert.assertNotNull(wildcardClass1);
        org.junit.Assert.assertNull(characterEscapes2);
        org.junit.Assert.assertNotNull(jsonGenerator5);
        org.junit.Assert.assertNotNull(jsonGenerator7);
    }

    @Test
    public void test1694() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1694");
        tools.jackson.core.JsonParser jsonParser0 = null;
        tools.jackson.core.filter.TokenFilter tokenFilter1 = null;
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion2 = null;
        tools.jackson.core.filter.FilteringParserDelegate filteringParserDelegate5 = new tools.jackson.core.filter.FilteringParserDelegate(jsonParser0, tokenFilter1, inclusion2, true, true);
        tools.jackson.core.filter.TokenFilter tokenFilter6 = filteringParserDelegate5.getFilter();
        boolean boolean7 = filteringParserDelegate5.isExpectedStartArrayToken();
        tools.jackson.core.filter.TokenFilter tokenFilter8 = filteringParserDelegate5.getFilter();
        boolean boolean9 = filteringParserDelegate5.isExpectedStartObjectToken();
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean10 = filteringParserDelegate5.canParseAsync();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(tokenFilter6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertNull(tokenFilter8);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
    }

    @Test
    public void test1695() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1695");
        tools.jackson.core.util.Separators separators0 = tools.jackson.core.util.Separators.createDefaultInstance();
        java.lang.String str1 = separators0.getArrayEmptySeparator();
        org.junit.Assert.assertNotNull(separators0);
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + " " + "'", str1, " ");
    }

    @Test
    public void test1696() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1696");
        tools.jackson.core.filter.TokenFilter tokenFilter0 = null;
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext1 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter0);
        tools.jackson.core.JsonGenerator jsonGenerator2 = null;
        tokenFilterContext1.writePath(jsonGenerator2);
        int int4 = tokenFilterContext1.getEntryCount();
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter5 = new tools.jackson.core.util.MinimalPrettyPrinter();
        tools.jackson.core.JsonGenerator jsonGenerator6 = null;
        minimalPrettyPrinter5.beforeObjectEntries(jsonGenerator6);
        tools.jackson.core.util.Separators separators11 = new tools.jackson.core.util.Separators('~', ' ', ' ');
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter12 = minimalPrettyPrinter5.setSeparators(separators11);
        tools.jackson.core.JsonGenerator jsonGenerator13 = null;
        tools.jackson.core.util.JsonGeneratorDelegate jsonGeneratorDelegate14 = new tools.jackson.core.util.JsonGeneratorDelegate(jsonGenerator13);
        minimalPrettyPrinter5.beforeObjectEntries((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate14);
        tools.jackson.core.filter.TokenFilter tokenFilter16 = null;
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext17 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter16);
        tools.jackson.core.JsonToken jsonToken18 = tokenFilterContext17.nextTokenToRead();
        tools.jackson.core.filter.TokenFilter tokenFilter19 = tools.jackson.core.filter.TokenFilter.INCLUDE_ALL;
        tools.jackson.core.filter.TokenFilter tokenFilter20 = tokenFilterContext17.checkValue(tokenFilter19);
        java.math.BigDecimal bigDecimal21 = null;
        boolean boolean22 = tokenFilter19.includeNumber(bigDecimal21);
        tools.jackson.core.filter.TokenFilter tokenFilter24 = tokenFilter19.includeRootValue((int) (byte) 1);
        tools.jackson.core.io.IOContext iOContext25 = null;
        java.io.InputStream inputStream26 = null;
        byte[] byteArray30 = new byte[] { (byte) 100, (byte) 1 };
        tools.jackson.core.io.UTF32Reader uTF32Reader34 = new tools.jackson.core.io.UTF32Reader(iOContext25, inputStream26, false, byteArray30, (int) (short) 0, (-1), false);
        boolean boolean36 = tokenFilter24.includeString((java.io.Reader) uTF32Reader34, (-1074));
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion37 = tools.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_NON_NULL;
        tools.jackson.core.filter.FilteringGeneratorDelegate filteringGeneratorDelegate39 = new tools.jackson.core.filter.FilteringGeneratorDelegate((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate14, tokenFilter24, inclusion37, true);
        tokenFilterContext1.ensurePropertyNameWritten((tools.jackson.core.JsonGenerator) filteringGeneratorDelegate39);
        char[] charArray44 = new char[] { ' ', ' ', '4' };
        boolean boolean48 = tools.jackson.core.io.NumberInput.inLongRange(charArray44, (-324), (int) '4', false);
        boolean boolean52 = tools.jackson.core.io.NumberInput.inLongRange(charArray44, 55296, (int) (short) 100, false);
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.JsonGenerator jsonGenerator55 = filteringGeneratorDelegate39.writeNumber(charArray44, 13, 13);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(tokenFilterContext1);
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 0 + "'", int4 == 0);
        org.junit.Assert.assertNotNull(minimalPrettyPrinter12);
        org.junit.Assert.assertNotNull(tokenFilterContext17);
        org.junit.Assert.assertNull(jsonToken18);
        org.junit.Assert.assertNotNull(tokenFilter19);
        org.junit.Assert.assertNotNull(tokenFilter20);
        org.junit.Assert.assertTrue("'" + boolean22 + "' != '" + true + "'", boolean22 == true);
        org.junit.Assert.assertNotNull(tokenFilter24);
        org.junit.Assert.assertNotNull(byteArray30);
        org.junit.Assert.assertArrayEquals(byteArray30, new byte[] { (byte) 100, (byte) 1 });
        org.junit.Assert.assertTrue("'" + boolean36 + "' != '" + true + "'", boolean36 == true);
        org.junit.Assert.assertTrue("'" + inclusion37 + "' != '" + tools.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_NON_NULL + "'", inclusion37.equals(tools.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_NON_NULL));
        org.junit.Assert.assertNotNull(charArray44);
        org.junit.Assert.assertArrayEquals(charArray44, new char[] { ' ', ' ', '4' });
        org.junit.Assert.assertTrue("'" + boolean48 + "' != '" + false + "'", boolean48 == false);
        org.junit.Assert.assertTrue("'" + boolean52 + "' != '" + false + "'", boolean52 == false);
    }

    @Test
    public void test1697() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1697");
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.util.InternCache internCache3 = new tools.jackson.core.util.InternCache(500, (float) (-3), 0);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: null");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test1698() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1698");
        tools.jackson.core.StreamReadConstraints.Builder builder0 = tools.jackson.core.StreamReadConstraints.builder();
        tools.jackson.core.StreamReadConstraints.Builder builder2 = builder0.maxTokenCount((long) 53);
        tools.jackson.core.StreamReadConstraints streamReadConstraints3 = builder2.build();
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(builder2);
        org.junit.Assert.assertNotNull(streamReadConstraints3);
    }

    @Test
    public void test1699() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1699");
        tools.jackson.core.filter.TokenFilter tokenFilter0 = null;
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext1 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter0);
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext2 = tokenFilterContext1.getParent();
        tools.jackson.core.JsonToken jsonToken3 = tokenFilterContext1.nextTokenToRead();
        tools.jackson.core.filter.TokenFilter tokenFilter4 = tools.jackson.core.filter.TokenFilter.INCLUDE_ALL;
        tools.jackson.core.filter.TokenFilter tokenFilter5 = tokenFilter4.filterStartArray();
        tools.jackson.core.util.DefaultIndenter defaultIndenter6 = tools.jackson.core.util.DefaultIndenter.SYSTEM_LINEFEED_INSTANCE;
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext8 = tokenFilterContext1.createChildObjectContext(tokenFilter5, (java.lang.Object) defaultIndenter6, false);
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter9 = new tools.jackson.core.util.MinimalPrettyPrinter();
        tools.jackson.core.JsonGenerator jsonGenerator10 = null;
        minimalPrettyPrinter9.beforeObjectEntries(jsonGenerator10);
        tools.jackson.core.util.Separators separators15 = new tools.jackson.core.util.Separators('~', ' ', ' ');
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter16 = minimalPrettyPrinter9.setSeparators(separators15);
        tools.jackson.core.JsonGenerator jsonGenerator17 = null;
        tools.jackson.core.util.JsonGeneratorDelegate jsonGeneratorDelegate18 = new tools.jackson.core.util.JsonGeneratorDelegate(jsonGenerator17);
        minimalPrettyPrinter9.beforeObjectEntries((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate18);
        tools.jackson.core.filter.TokenFilter tokenFilter20 = null;
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext21 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter20);
        tools.jackson.core.JsonToken jsonToken22 = tokenFilterContext21.nextTokenToRead();
        tools.jackson.core.filter.TokenFilter tokenFilter23 = tools.jackson.core.filter.TokenFilter.INCLUDE_ALL;
        tools.jackson.core.filter.TokenFilter tokenFilter24 = tokenFilterContext21.checkValue(tokenFilter23);
        java.math.BigDecimal bigDecimal25 = null;
        boolean boolean26 = tokenFilter23.includeNumber(bigDecimal25);
        tools.jackson.core.filter.TokenFilter tokenFilter28 = tokenFilter23.includeRootValue((int) (byte) 1);
        tools.jackson.core.io.IOContext iOContext29 = null;
        java.io.InputStream inputStream30 = null;
        byte[] byteArray34 = new byte[] { (byte) 100, (byte) 1 };
        tools.jackson.core.io.UTF32Reader uTF32Reader38 = new tools.jackson.core.io.UTF32Reader(iOContext29, inputStream30, false, byteArray34, (int) (short) 0, (-1), false);
        boolean boolean40 = tokenFilter28.includeString((java.io.Reader) uTF32Reader38, (-1074));
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion41 = tools.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_NON_NULL;
        tools.jackson.core.filter.FilteringGeneratorDelegate filteringGeneratorDelegate43 = new tools.jackson.core.filter.FilteringGeneratorDelegate((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate18, tokenFilter28, inclusion41, true);
        tools.jackson.core.filter.TokenFilter tokenFilter44 = filteringGeneratorDelegate43.getFilter();
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext45 = tokenFilterContext8.closeObject((tools.jackson.core.JsonGenerator) filteringGeneratorDelegate43);
        byte[] byteArray46 = null;
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.JsonGenerator jsonGenerator47 = filteringGeneratorDelegate43.writeBinary(byteArray46);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(tokenFilterContext1);
        org.junit.Assert.assertNull(tokenFilterContext2);
        org.junit.Assert.assertNull(jsonToken3);
        org.junit.Assert.assertNotNull(tokenFilter4);
        org.junit.Assert.assertNotNull(tokenFilter5);
        org.junit.Assert.assertNotNull(defaultIndenter6);
        org.junit.Assert.assertNotNull(tokenFilterContext8);
        org.junit.Assert.assertNotNull(minimalPrettyPrinter16);
        org.junit.Assert.assertNotNull(tokenFilterContext21);
        org.junit.Assert.assertNull(jsonToken22);
        org.junit.Assert.assertNotNull(tokenFilter23);
        org.junit.Assert.assertNotNull(tokenFilter24);
        org.junit.Assert.assertTrue("'" + boolean26 + "' != '" + true + "'", boolean26 == true);
        org.junit.Assert.assertNotNull(tokenFilter28);
        org.junit.Assert.assertNotNull(byteArray34);
        org.junit.Assert.assertArrayEquals(byteArray34, new byte[] { (byte) 100, (byte) 1 });
        org.junit.Assert.assertTrue("'" + boolean40 + "' != '" + true + "'", boolean40 == true);
        org.junit.Assert.assertTrue("'" + inclusion41 + "' != '" + tools.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_NON_NULL + "'", inclusion41.equals(tools.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_NON_NULL));
        org.junit.Assert.assertNotNull(tokenFilter44);
        org.junit.Assert.assertNotNull(tokenFilterContext45);
    }

    @Test
    public void test1700() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1700");
        tools.jackson.core.json.DupDetector dupDetector0 = null;
        tools.jackson.core.util.SimpleStreamWriteContext simpleStreamWriteContext1 = tools.jackson.core.util.SimpleStreamWriteContext.createRootContext(dupDetector0);
        tools.jackson.core.json.DupDetector dupDetector2 = simpleStreamWriteContext1.getDupDetector();
        tools.jackson.core.json.DupDetector dupDetector3 = null;
        tools.jackson.core.util.SimpleStreamWriteContext simpleStreamWriteContext4 = simpleStreamWriteContext1.withDupDetector(dupDetector3);
        java.lang.String str5 = simpleStreamWriteContext4.typeDesc();
        boolean boolean6 = simpleStreamWriteContext4.writeValue();
        boolean boolean7 = simpleStreamWriteContext4.hasPathSegment();
        tools.jackson.core.StreamReadConstraints.Builder builder8 = tools.jackson.core.StreamReadConstraints.builder();
        tools.jackson.core.StreamReadConstraints.Builder builder10 = builder8.maxTokenCount((long) 53);
        tools.jackson.core.util.SimpleStreamWriteContext simpleStreamWriteContext11 = simpleStreamWriteContext4.createChildObjectContext((java.lang.Object) builder8);
        org.junit.Assert.assertNotNull(simpleStreamWriteContext1);
        org.junit.Assert.assertNull(dupDetector2);
        org.junit.Assert.assertNotNull(simpleStreamWriteContext4);
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "root" + "'", str5, "root");
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + true + "'", boolean6 == true);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertNotNull(builder8);
        org.junit.Assert.assertNotNull(builder10);
        org.junit.Assert.assertNotNull(simpleStreamWriteContext11);
    }

    @Test
    public void test1701() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1701");
        tools.jackson.core.JsonGenerator jsonGenerator0 = null;
        java.lang.Throwable throwable2 = null;
        tools.jackson.core.exc.StreamWriteException streamWriteException3 = new tools.jackson.core.exc.StreamWriteException(jsonGenerator0, "hi!", throwable2);
        tools.jackson.core.JsonGenerator jsonGenerator4 = null;
        tools.jackson.core.exc.StreamWriteException streamWriteException5 = streamWriteException3.withGenerator(jsonGenerator4);
        tools.jackson.core.JsonGenerator jsonGenerator6 = streamWriteException5.processor();
        tools.jackson.core.JsonGenerator jsonGenerator7 = null;
        java.lang.Throwable throwable9 = null;
        tools.jackson.core.exc.StreamWriteException streamWriteException10 = new tools.jackson.core.exc.StreamWriteException(jsonGenerator7, "hi!", throwable9);
        tools.jackson.core.json.DupDetector dupDetector11 = null;
        tools.jackson.core.util.SimpleStreamWriteContext simpleStreamWriteContext12 = tools.jackson.core.util.SimpleStreamWriteContext.createRootContext(dupDetector11);
        boolean boolean13 = simpleStreamWriteContext12.writeValue();
        tools.jackson.core.JacksonException jacksonException15 = tools.jackson.core.JacksonException.wrapWithPath((java.lang.Throwable) streamWriteException10, (java.lang.Object) boolean13, "hi!");
        tools.jackson.core.JsonToken jsonToken17 = null;
        tools.jackson.core.type.WritableTypeId writableTypeId19 = new tools.jackson.core.type.WritableTypeId((java.lang.Object) '#', jsonToken17, (java.lang.Object) ' ');
        tools.jackson.core.type.WritableTypeId.Inclusion inclusion20 = tools.jackson.core.type.WritableTypeId.Inclusion.PAYLOAD_PROPERTY;
        writableTypeId19.include = inclusion20;
        tools.jackson.core.JacksonException.Reference reference23 = new tools.jackson.core.JacksonException.Reference((java.lang.Object) writableTypeId19, 256);
        tools.jackson.core.JacksonException jacksonException24 = tools.jackson.core.JacksonException.wrapWithPath((java.lang.Throwable) streamWriteException10, reference23);
        java.lang.String str25 = reference23.getDescription();
        tools.jackson.core.JacksonException jacksonException26 = streamWriteException5.prependPath(reference23);
        java.lang.Object obj27 = null;
        tools.jackson.core.JacksonException jacksonException29 = tools.jackson.core.JacksonException.wrapWithPath((java.lang.Throwable) streamWriteException5, obj27, "\r\n");
        org.junit.Assert.assertNotNull(streamWriteException5);
        org.junit.Assert.assertNull(jsonGenerator6);
        org.junit.Assert.assertNotNull(simpleStreamWriteContext12);
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + true + "'", boolean13 == true);
        org.junit.Assert.assertNotNull(jacksonException15);
        org.junit.Assert.assertTrue("'" + inclusion20 + "' != '" + tools.jackson.core.type.WritableTypeId.Inclusion.PAYLOAD_PROPERTY + "'", inclusion20.equals(tools.jackson.core.type.WritableTypeId.Inclusion.PAYLOAD_PROPERTY));
        org.junit.Assert.assertNotNull(jacksonException24);
        org.junit.Assert.assertEquals("'" + str25 + "' != '" + "tools.jackson.core.type.WritableTypeId[256]" + "'", str25, "tools.jackson.core.type.WritableTypeId[256]");
        org.junit.Assert.assertNotNull(jacksonException26);
        org.junit.Assert.assertNotNull(jacksonException29);
    }

    @Test
    public void test1702() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1702");
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
        tools.jackson.core.filter.TokenFilter tokenFilter35 = null;
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext36 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter35);
        tools.jackson.core.JsonToken jsonToken37 = tokenFilterContext36.nextTokenToRead();
        tools.jackson.core.filter.TokenFilter tokenFilter38 = tools.jackson.core.filter.TokenFilter.INCLUDE_ALL;
        tools.jackson.core.filter.TokenFilter tokenFilter39 = tokenFilterContext36.checkValue(tokenFilter38);
        java.math.BigDecimal bigDecimal40 = null;
        boolean boolean41 = tokenFilter38.includeNumber(bigDecimal40);
        tools.jackson.core.filter.TokenFilter tokenFilter43 = tokenFilter38.includeRootValue((int) (byte) 1);
        tools.jackson.core.filter.TokenFilter tokenFilter45 = tokenFilter38.includeElement(4);
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion46 = tools.jackson.core.filter.TokenFilter.Inclusion.ONLY_INCLUDE_ALL;
        tools.jackson.core.filter.FilteringGeneratorDelegate filteringGeneratorDelegate48 = new tools.jackson.core.filter.FilteringGeneratorDelegate((tools.jackson.core.JsonGenerator) filteringGeneratorDelegate34, tokenFilter45, inclusion46, false);
        tools.jackson.core.TokenStreamContext tokenStreamContext49 = filteringGeneratorDelegate34.streamWriteContext();
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder50 = tools.jackson.core.json.JsonFactory.builderWithJackson2Defaults();
        tools.jackson.core.SerializableString serializableString51 = null;
        tools.jackson.core.json.JsonFactoryBuilder jsonFactoryBuilder52 = jsonFactoryBuilder50.rootValueSeparator(serializableString51);
        int int53 = jsonFactoryBuilder52.streamWriteFeaturesMask();
        // The following exception was thrown during execution in test generation
        try {
            tools.jackson.core.JsonGenerator jsonGenerator54 = filteringGeneratorDelegate34.writeStartObject((java.lang.Object) jsonFactoryBuilder52);
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
        org.junit.Assert.assertNotNull(tokenFilterContext36);
        org.junit.Assert.assertNull(jsonToken37);
        org.junit.Assert.assertNotNull(tokenFilter38);
        org.junit.Assert.assertNotNull(tokenFilter39);
        org.junit.Assert.assertTrue("'" + boolean41 + "' != '" + true + "'", boolean41 == true);
        org.junit.Assert.assertNotNull(tokenFilter43);
        org.junit.Assert.assertNotNull(tokenFilter45);
        org.junit.Assert.assertTrue("'" + inclusion46 + "' != '" + tools.jackson.core.filter.TokenFilter.Inclusion.ONLY_INCLUDE_ALL + "'", inclusion46.equals(tools.jackson.core.filter.TokenFilter.Inclusion.ONLY_INCLUDE_ALL));
        org.junit.Assert.assertNotNull(tokenStreamContext49);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder50);
        org.junit.Assert.assertNotNull(jsonFactoryBuilder52);
        org.junit.Assert.assertTrue("'" + int53 + "' != '" + 7 + "'", int53 == 7);
    }

    @Test
    public void test1703() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1703");
        tools.jackson.core.util.BufferRecycler bufferRecycler0 = new tools.jackson.core.util.BufferRecycler();
        tools.jackson.core.util.ByteArrayBuilder byteArrayBuilder2 = new tools.jackson.core.util.ByteArrayBuilder(bufferRecycler0, 12000);
        tools.jackson.core.util.JsonRecyclerPools.ConcurrentDequePool concurrentDequePool3 = tools.jackson.core.util.JsonRecyclerPools.ConcurrentDequePool.construct();
        boolean boolean4 = concurrentDequePool3.clear();
        tools.jackson.core.util.BufferRecycler bufferRecycler5 = bufferRecycler0.withPool((tools.jackson.core.util.RecyclerPool<tools.jackson.core.util.BufferRecycler>) concurrentDequePool3);
        int int6 = concurrentDequePool3.pooledCount();
        tools.jackson.core.util.BufferRecycler bufferRecycler7 = concurrentDequePool3.createPooled();
        org.junit.Assert.assertNotNull(concurrentDequePool3);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + true + "'", boolean4 == true);
        org.junit.Assert.assertNotNull(bufferRecycler5);
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
        org.junit.Assert.assertNotNull(bufferRecycler7);
    }

    @Test
    public void test1704() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1704");
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
        tools.jackson.core.Base64Variant.PaddingReadBehaviour paddingReadBehaviour30 = base64Variant0.paddingReadBehaviour();
        boolean boolean32 = base64Variant0.usesPaddingChar((-149));
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
        org.junit.Assert.assertTrue("'" + paddingReadBehaviour30 + "' != '" + tools.jackson.core.Base64Variant.PaddingReadBehaviour.PADDING_FORBIDDEN + "'", paddingReadBehaviour30.equals(tools.jackson.core.Base64Variant.PaddingReadBehaviour.PADDING_FORBIDDEN));
        org.junit.Assert.assertTrue("'" + boolean32 + "' != '" + false + "'", boolean32 == false);
    }

    @Test
    public void test1705() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1705");
        tools.jackson.core.util.DefaultPrettyPrinter.NopIndenter nopIndenter0 = tools.jackson.core.util.DefaultPrettyPrinter.NopIndenter.instance();
        boolean boolean1 = nopIndenter0.isInline();
        tools.jackson.core.util.DefaultPrettyPrinter.NopIndenter nopIndenter2 = new tools.jackson.core.util.DefaultPrettyPrinter.NopIndenter();
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter3 = new tools.jackson.core.util.MinimalPrettyPrinter();
        tools.jackson.core.JsonGenerator jsonGenerator4 = null;
        minimalPrettyPrinter3.beforeObjectEntries(jsonGenerator4);
        tools.jackson.core.util.Separators separators9 = new tools.jackson.core.util.Separators('~', ' ', ' ');
        tools.jackson.core.util.MinimalPrettyPrinter minimalPrettyPrinter10 = minimalPrettyPrinter3.setSeparators(separators9);
        tools.jackson.core.JsonGenerator jsonGenerator11 = null;
        tools.jackson.core.util.JsonGeneratorDelegate jsonGeneratorDelegate12 = new tools.jackson.core.util.JsonGeneratorDelegate(jsonGenerator11);
        minimalPrettyPrinter3.beforeObjectEntries((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate12);
        tools.jackson.core.filter.TokenFilter tokenFilter14 = null;
        tools.jackson.core.filter.TokenFilterContext tokenFilterContext15 = tools.jackson.core.filter.TokenFilterContext.createRootContext(tokenFilter14);
        tools.jackson.core.JsonToken jsonToken16 = tokenFilterContext15.nextTokenToRead();
        tools.jackson.core.filter.TokenFilter tokenFilter17 = tools.jackson.core.filter.TokenFilter.INCLUDE_ALL;
        tools.jackson.core.filter.TokenFilter tokenFilter18 = tokenFilterContext15.checkValue(tokenFilter17);
        java.math.BigDecimal bigDecimal19 = null;
        boolean boolean20 = tokenFilter17.includeNumber(bigDecimal19);
        tools.jackson.core.filter.TokenFilter tokenFilter22 = tokenFilter17.includeRootValue((int) (byte) 1);
        tools.jackson.core.io.IOContext iOContext23 = null;
        java.io.InputStream inputStream24 = null;
        byte[] byteArray28 = new byte[] { (byte) 100, (byte) 1 };
        tools.jackson.core.io.UTF32Reader uTF32Reader32 = new tools.jackson.core.io.UTF32Reader(iOContext23, inputStream24, false, byteArray28, (int) (short) 0, (-1), false);
        boolean boolean34 = tokenFilter22.includeString((java.io.Reader) uTF32Reader32, (-1074));
        tools.jackson.core.filter.TokenFilter.Inclusion inclusion35 = tools.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_NON_NULL;
        tools.jackson.core.filter.FilteringGeneratorDelegate filteringGeneratorDelegate37 = new tools.jackson.core.filter.FilteringGeneratorDelegate((tools.jackson.core.JsonGenerator) jsonGeneratorDelegate12, tokenFilter22, inclusion35, true);
        nopIndenter2.writeIndentation((tools.jackson.core.JsonGenerator) filteringGeneratorDelegate37, (int) '#');
        nopIndenter0.writeIndentation((tools.jackson.core.JsonGenerator) filteringGeneratorDelegate37, (-149));
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean42 = filteringGeneratorDelegate37.canWriteComments();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(nopIndenter0);
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + true + "'", boolean1 == true);
        org.junit.Assert.assertNotNull(minimalPrettyPrinter10);
        org.junit.Assert.assertNotNull(tokenFilterContext15);
        org.junit.Assert.assertNull(jsonToken16);
        org.junit.Assert.assertNotNull(tokenFilter17);
        org.junit.Assert.assertNotNull(tokenFilter18);
        org.junit.Assert.assertTrue("'" + boolean20 + "' != '" + true + "'", boolean20 == true);
        org.junit.Assert.assertNotNull(tokenFilter22);
        org.junit.Assert.assertNotNull(byteArray28);
        org.junit.Assert.assertArrayEquals(byteArray28, new byte[] { (byte) 100, (byte) 1 });
        org.junit.Assert.assertTrue("'" + boolean34 + "' != '" + true + "'", boolean34 == true);
        org.junit.Assert.assertTrue("'" + inclusion35 + "' != '" + tools.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_NON_NULL + "'", inclusion35.equals(tools.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_NON_NULL));
    }

    @Test
    public void test1706() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1706");
        tools.jackson.core.util.BufferRecycler bufferRecycler0 = null;
        tools.jackson.core.util.TextBuffer textBuffer1 = new tools.jackson.core.util.TextBuffer(bufferRecycler0);
        textBuffer1.resetWithString("");
        int int4 = textBuffer1.getCurrentSegmentSize();
        char[] charArray5 = textBuffer1.emptyAndGetCurrentSegment();
        char[] charArray7 = textBuffer1.expandCurrentSegment(0);
        textBuffer1.resetWithString("UNKNOWN");
        int int10 = textBuffer1.getCurrentSegmentSize();
        textBuffer1.resetWithEmpty();
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 0 + "'", int4 == 0);
        org.junit.Assert.assertNotNull(charArray5);
        org.junit.Assert.assertNotNull(charArray7);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 0 + "'", int10 == 0);
    }

    @Test
    public void test1707() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1707");
        tools.jackson.core.util.BufferRecycler bufferRecycler0 = new tools.jackson.core.util.BufferRecycler();
        tools.jackson.core.util.TextBuffer textBuffer1 = new tools.jackson.core.util.TextBuffer(bufferRecycler0);
        tools.jackson.core.io.SegmentedStringWriter segmentedStringWriter2 = new tools.jackson.core.io.SegmentedStringWriter(bufferRecycler0);
        tools.jackson.core.io.SegmentedStringWriter segmentedStringWriter3 = new tools.jackson.core.io.SegmentedStringWriter(bufferRecycler0);
        tools.jackson.core.util.BufferRecycler bufferRecycler4 = segmentedStringWriter3.bufferRecycler();
        java.lang.CharSequence charSequence5 = null;
        // The following exception was thrown during execution in test generation
        try {
            java.io.Writer writer8 = segmentedStringWriter3.append(charSequence5, 2031590, 292);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(bufferRecycler4);
    }

    @Test
    public void test1708() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1708");
        tools.jackson.core.StreamReadConstraints streamReadConstraints0 = null;
        tools.jackson.core.util.BufferRecycler bufferRecycler1 = new tools.jackson.core.util.BufferRecycler();
        tools.jackson.core.util.ByteArrayBuilder byteArrayBuilder3 = new tools.jackson.core.util.ByteArrayBuilder(bufferRecycler1, 12000);
        tools.jackson.core.io.SegmentedStringWriter segmentedStringWriter4 = new tools.jackson.core.io.SegmentedStringWriter(bufferRecycler1);
        tools.jackson.core.util.ReadConstrainedTextBuffer readConstrainedTextBuffer5 = new tools.jackson.core.util.ReadConstrainedTextBuffer(streamReadConstraints0, bufferRecycler1);
    }

    @Test
    public void test1709() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1709");
        tools.jackson.core.util.Separators separators3 = new tools.jackson.core.util.Separators('~', ' ', ' ');
        tools.jackson.core.util.Separators separators5 = separators3.withObjectEntrySeparator(',');
        tools.jackson.core.util.DefaultPrettyPrinter defaultPrettyPrinter6 = new tools.jackson.core.util.DefaultPrettyPrinter(separators5);
        tools.jackson.core.exc.StreamConstraintsException streamConstraintsException8 = new tools.jackson.core.exc.StreamConstraintsException("0.0.0");
        tools.jackson.core.exc.StreamReadException streamReadException10 = new tools.jackson.core.exc.StreamReadException("");
        java.lang.String str11 = streamReadException10.getPathReference();
        tools.jackson.core.exc.StreamReadException streamReadException13 = new tools.jackson.core.exc.StreamReadException("");
        streamReadException10.addSuppressed((java.lang.Throwable) streamReadException13);
        tools.jackson.core.JacksonException jacksonException15 = streamConstraintsException8.withCause((java.lang.Throwable) streamReadException10);
        tools.jackson.core.util.DefaultPrettyPrinter defaultPrettyPrinter16 = new tools.jackson.core.util.DefaultPrettyPrinter();
        tools.jackson.core.util.DefaultPrettyPrinter.FixedSpaceIndenter fixedSpaceIndenter17 = tools.jackson.core.util.DefaultPrettyPrinter.FixedSpaceIndenter.instance();
        defaultPrettyPrinter16.indentArraysWith((tools.jackson.core.util.DefaultPrettyPrinter.Indenter) fixedSpaceIndenter17);
        tools.jackson.core.util.Separators separators19 = tools.jackson.core.util.Separators.createDefaultInstance();
        tools.jackson.core.util.Separators separators21 = separators19.withObjectEntrySeparator('4');
        tools.jackson.core.util.Separators separators23 = separators21.withArrayElementSeparator('#');
        tools.jackson.core.util.DefaultPrettyPrinter defaultPrettyPrinter24 = defaultPrettyPrinter16.withSeparators(separators23);
        tools.jackson.core.JacksonException jacksonException26 = tools.jackson.core.JacksonException.wrapWithPath((java.lang.Throwable) streamConstraintsException8, (java.lang.Object) defaultPrettyPrinter16, 256);
        tools.jackson.core.util.DefaultPrettyPrinter defaultPrettyPrinter27 = new tools.jackson.core.util.DefaultPrettyPrinter(defaultPrettyPrinter16);
        tools.jackson.core.json.JsonFactory jsonFactory28 = new tools.jackson.core.json.JsonFactory();
        int int29 = jsonFactory28.getFormatReadFeatures();
        tools.jackson.core.json.JsonFactory jsonFactory30 = new tools.jackson.core.json.JsonFactory();
        int int31 = jsonFactory30.getFormatReadFeatures();
        tools.jackson.core.ObjectWriteContext objectWriteContext32 = tools.jackson.core.TokenStreamFactory.EMPTY_WRITE_CONTEXT;
        tools.jackson.core.util.BufferRecycler bufferRecycler33 = new tools.jackson.core.util.BufferRecycler();
        tools.jackson.core.io.SegmentedStringWriter segmentedStringWriter34 = new tools.jackson.core.io.SegmentedStringWriter(bufferRecycler33);
        segmentedStringWriter34.write("MODIFIED-FOR-URL/UNKNOWN/0.0.0");
        java.io.Writer writer38 = segmentedStringWriter34.append('a');
        tools.jackson.core.JsonGenerator jsonGenerator39 = jsonFactory30.createGenerator(objectWriteContext32, (java.io.Writer) segmentedStringWriter34);
        tools.jackson.core.util.BufferRecycler bufferRecycler40 = null;
        tools.jackson.core.util.ByteArrayBuilder byteArrayBuilder41 = new tools.jackson.core.util.ByteArrayBuilder(bufferRecycler40);
        byte[] byteArray43 = byteArrayBuilder41.completeAndCoalesce(10);
        byte[] byteArray44 = byteArrayBuilder41.toByteArray();
        tools.jackson.core.util.BufferRecycler bufferRecycler45 = byteArrayBuilder41.bufferRecycler();
        tools.jackson.core.JsonGenerator jsonGenerator46 = jsonFactory28.createGenerator(objectWriteContext32, (java.io.OutputStream) byteArrayBuilder41);
        defaultPrettyPrinter27.writeObjectNameValueSeparator(jsonGenerator46);
        tools.jackson.core.json.JsonFactory jsonFactory48 = new tools.jackson.core.json.JsonFactory();
        int int49 = jsonFactory48.getFormatReadFeatures();
        tools.jackson.core.json.JsonFactory jsonFactory50 = new tools.jackson.core.json.JsonFactory();
        int int51 = jsonFactory50.getFormatReadFeatures();
        tools.jackson.core.ObjectWriteContext objectWriteContext52 = tools.jackson.core.TokenStreamFactory.EMPTY_WRITE_CONTEXT;
        tools.jackson.core.util.BufferRecycler bufferRecycler53 = new tools.jackson.core.util.BufferRecycler();
        tools.jackson.core.io.SegmentedStringWriter segmentedStringWriter54 = new tools.jackson.core.io.SegmentedStringWriter(bufferRecycler53);
        segmentedStringWriter54.write("MODIFIED-FOR-URL/UNKNOWN/0.0.0");
        java.io.Writer writer58 = segmentedStringWriter54.append('a');
        tools.jackson.core.JsonGenerator jsonGenerator59 = jsonFactory50.createGenerator(objectWriteContext52, (java.io.Writer) segmentedStringWriter54);
        tools.jackson.core.util.BufferRecycler bufferRecycler60 = null;
        tools.jackson.core.util.ByteArrayBuilder byteArrayBuilder61 = new tools.jackson.core.util.ByteArrayBuilder(bufferRecycler60);
        byte[] byteArray63 = byteArrayBuilder61.completeAndCoalesce(10);
        byte[] byteArray64 = byteArrayBuilder61.toByteArray();
        tools.jackson.core.util.BufferRecycler bufferRecycler65 = byteArrayBuilder61.bufferRecycler();
        tools.jackson.core.JsonGenerator jsonGenerator66 = jsonFactory48.createGenerator(objectWriteContext52, (java.io.OutputStream) byteArrayBuilder61);
        defaultPrettyPrinter27.writeStartObject(jsonGenerator66);
        defaultPrettyPrinter6.writeArrayValueSeparator(jsonGenerator66);
        org.junit.Assert.assertNotNull(separators5);
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "" + "'", str11, "");
        org.junit.Assert.assertNotNull(jacksonException15);
        org.junit.Assert.assertNotNull(fixedSpaceIndenter17);
        org.junit.Assert.assertNotNull(separators19);
        org.junit.Assert.assertNotNull(separators21);
        org.junit.Assert.assertNotNull(separators23);
        org.junit.Assert.assertNotNull(defaultPrettyPrinter24);
        org.junit.Assert.assertNotNull(jacksonException26);
        org.junit.Assert.assertTrue("'" + int29 + "' != '" + 0 + "'", int29 == 0);
        org.junit.Assert.assertTrue("'" + int31 + "' != '" + 0 + "'", int31 == 0);
        org.junit.Assert.assertNotNull(objectWriteContext32);
        org.junit.Assert.assertNotNull(writer38);
        org.junit.Assert.assertNotNull(jsonGenerator39);
        org.junit.Assert.assertNotNull(byteArray43);
        org.junit.Assert.assertArrayEquals(byteArray43, new byte[] { (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0 });
        org.junit.Assert.assertNotNull(byteArray44);
        org.junit.Assert.assertArrayEquals(byteArray44, new byte[] { (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0 });
        org.junit.Assert.assertNull(bufferRecycler45);
        org.junit.Assert.assertNotNull(jsonGenerator46);
        org.junit.Assert.assertTrue("'" + int49 + "' != '" + 0 + "'", int49 == 0);
        org.junit.Assert.assertTrue("'" + int51 + "' != '" + 0 + "'", int51 == 0);
        org.junit.Assert.assertNotNull(objectWriteContext52);
        org.junit.Assert.assertNotNull(writer58);
        org.junit.Assert.assertNotNull(jsonGenerator59);
        org.junit.Assert.assertNotNull(byteArray63);
        org.junit.Assert.assertArrayEquals(byteArray63, new byte[] { (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0 });
        org.junit.Assert.assertNotNull(byteArray64);
        org.junit.Assert.assertArrayEquals(byteArray64, new byte[] { (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0 });
        org.junit.Assert.assertNull(bufferRecycler65);
        org.junit.Assert.assertNotNull(jsonGenerator66);
    }
}
