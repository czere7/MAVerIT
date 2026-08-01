import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ErrorTest0 {

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
    public void test1() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ErrorTest0.test1");
        tools.jackson.core.util.BufferRecycler bufferRecycler0 = new tools.jackson.core.util.BufferRecycler();
        tools.jackson.core.util.ByteArrayBuilder byteArrayBuilder2 = new tools.jackson.core.util.ByteArrayBuilder(bufferRecycler0, 12000);
        tools.jackson.core.io.SegmentedStringWriter segmentedStringWriter3 = new tools.jackson.core.io.SegmentedStringWriter(bufferRecycler0);
        java.lang.String str4 = segmentedStringWriter3.getAndClear();
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        segmentedStringWriter3.write("8.0", (int) (short) 10, 56);
    }

    @Test
    public void test2() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ErrorTest0.test2");
        tools.jackson.core.util.BufferRecycler bufferRecycler0 = new tools.jackson.core.util.BufferRecycler();
        tools.jackson.core.util.TextBuffer textBuffer1 = new tools.jackson.core.util.TextBuffer(bufferRecycler0);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        int int3 = textBuffer1.contentsAsInt(false);
    }

    @Test
    public void test3() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ErrorTest0.test3");
        tools.jackson.core.sym.ByteQuadsCanonicalizer byteQuadsCanonicalizer0 = tools.jackson.core.sym.ByteQuadsCanonicalizer.createRoot();
        byteQuadsCanonicalizer0.release();
        tools.jackson.core.sym.ByteQuadsCanonicalizer byteQuadsCanonicalizer3 = byteQuadsCanonicalizer0.makeChildOrPlaceholder(309);
        int int7 = byteQuadsCanonicalizer0.calcHash((int) (byte) -17, 53, 9);
        int int8 = byteQuadsCanonicalizer0.spilloverCount();
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        java.lang.String str11 = byteQuadsCanonicalizer0.findName(1645, (-1074));
    }

    @Test
    public void test4() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ErrorTest0.test4");
        tools.jackson.core.sym.ByteQuadsCanonicalizer byteQuadsCanonicalizer0 = tools.jackson.core.sym.ByteQuadsCanonicalizer.createRoot();
        int int1 = byteQuadsCanonicalizer0.size();
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        java.lang.String str5 = byteQuadsCanonicalizer0.findName((-207530097), 1000, 29);
    }

    @Test
    public void test5() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ErrorTest0.test5");
        tools.jackson.core.json.JsonFactory jsonFactory0 = new tools.jackson.core.json.JsonFactory();
        int int1 = jsonFactory0.getFormatReadFeatures();
        tools.jackson.core.util.BufferRecycler bufferRecycler2 = new tools.jackson.core.util.BufferRecycler();
        tools.jackson.core.util.ByteArrayBuilder byteArrayBuilder4 = new tools.jackson.core.util.ByteArrayBuilder(bufferRecycler2, 12000);
        tools.jackson.core.io.SegmentedStringWriter segmentedStringWriter5 = new tools.jackson.core.io.SegmentedStringWriter(bufferRecycler2);
        java.lang.String str6 = segmentedStringWriter5.getAndClear();
        tools.jackson.core.JsonGenerator jsonGenerator7 = jsonFactory0.createGenerator((java.io.Writer) segmentedStringWriter5);
        tools.jackson.core.sym.CharsToNameCanonicalizer charsToNameCanonicalizer9 = tools.jackson.core.sym.CharsToNameCanonicalizer.createRoot((tools.jackson.core.TokenStreamFactory) jsonFactory0, (int) (short) 0);
        int int10 = charsToNameCanonicalizer9.size();
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        int int11 = charsToNameCanonicalizer9.collisionCount();
    }

    @Test
    public void test6() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ErrorTest0.test6");
        tools.jackson.core.sym.ByteQuadsCanonicalizer byteQuadsCanonicalizer0 = tools.jackson.core.sym.ByteQuadsCanonicalizer.createRoot();
        byteQuadsCanonicalizer0.release();
        int int2 = byteQuadsCanonicalizer0.bucketCount();
        int int3 = byteQuadsCanonicalizer0.bucketCount();
        java.lang.String str4 = byteQuadsCanonicalizer0.toString();
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        java.lang.String str8 = byteQuadsCanonicalizer0.findName(100000000, (int) (short) 100, (int) '~');
    }

    @Test
    public void test7() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ErrorTest0.test7");
        tools.jackson.core.util.BufferRecycler bufferRecycler0 = new tools.jackson.core.util.BufferRecycler();
        tools.jackson.core.util.ByteArrayBuilder byteArrayBuilder2 = new tools.jackson.core.util.ByteArrayBuilder(bufferRecycler0, 12000);
        tools.jackson.core.util.TextBuffer textBuffer3 = new tools.jackson.core.util.TextBuffer(bufferRecycler0);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        java.math.BigDecimal bigDecimal5 = textBuffer3.contentsAsDecimal(false);
    }
}

