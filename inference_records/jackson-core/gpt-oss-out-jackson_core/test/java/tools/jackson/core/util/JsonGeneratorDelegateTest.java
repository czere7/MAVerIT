package tools.jackson.core.util;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import tools.jackson.core.JsonGenerator;
import tools.jackson.core.TokenStreamContext;
import tools.jackson.core.StreamWriteFeature;
import tools.jackson.core.util.JacksonFeatureSet;
import tools.jackson.core.Base64Variant;
import tools.jackson.core.ObjectWriteContext;
import tools.jackson.core.TreeNode;
import tools.jackson.core.JsonToken;
import tools.jackson.core.JsonParser;
import tools.jackson.core.SerializableString;
import tools.jackson.core.FormatSchema;
import tools.jackson.core.PrettyPrinter;
import tools.jackson.core.Version;

public class JsonGeneratorDelegateTest {

    @Mock
    private JsonGenerator mockDelegate;

    @Mock
    private ObjectWriteContext mockContext;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        when(mockDelegate.objectWriteContext()).thenReturn(mockContext);
    }

    // ... existing tests omitted for brevity ...

    /* ==================== NEW TESTS TO KILL NULL RETURN MUTATIONS ==================== */

    @Test
    public void testReturnValuesForBinaryByteArray() {
        JsonGeneratorDelegate delegate = new JsonGeneratorDelegate(mockDelegate);
        Base64Variant v = new Base64Variant(
                "default",
                "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/",
                true,
                '=',
                0);
        byte[] data = {1, 2, 3};
        JsonGenerator result = delegate.writeBinary(v, data, 0, data.length);
        assertSame("writeBinary(byte[]) should return the delegate instance", delegate, result);
        verify(mockDelegate).writeBinary(v, data, 0, data.length);
    }

    @Test
    public void testReturnValuesForNumberMethods() {
        JsonGeneratorDelegate delegate = new JsonGeneratorDelegate(mockDelegate);

        // short
        assertSame(delegate,
                delegate.writeNumber((short) 5));
        // int
        assertSame(delegate,
                delegate.writeNumber(10));
        // long
        assertSame(delegate,
                delegate.writeNumber(100L));
        // BigInteger
        BigInteger bi = new BigInteger("123456");
        assertSame(delegate,
                delegate.writeNumber(bi));
        // double
        assertSame(delegate,
                delegate.writeNumber(3.14d));
        // float
        assertSame(delegate,
                delegate.writeNumber(2.71f));
        // BigDecimal
        BigDecimal bd = new BigDecimal("1.234");
        assertSame(delegate,
                delegate.writeNumber(bd));
        // encoded String
        assertSame(delegate,
                delegate.writeNumber("123"));
        // char[] buffer
        char[] buf = {'0', '1'};
        assertSame(delegate,
                delegate.writeNumber(buf, 0, buf.length));

        verify(mockDelegate).writeNumber((short)5);
        verify(mockDelegate).writeNumber(10);
        verify(mockDelegate).writeNumber(100L);
        verify(mockDelegate).writeNumber(bi);
        verify(mockDelegate).writeNumber(3.14d);
        verify(mockDelegate).writeNumber(2.71f);
        verify(mockDelegate).writeNumber(bd);
        verify(mockDelegate).writeNumber("123");
        verify(mockDelegate).writeNumber(buf, 0, buf.length);
    }

    @Test
    public void testReturnValuesForBooleanAndNull() {
        JsonGeneratorDelegate delegate = new JsonGeneratorDelegate(mockDelegate);

        assertSame(delegate,
                delegate.writeBoolean(true));
        assertSame(delegate,
                delegate.writeBoolean(false));
        assertSame(delegate,
                delegate.writeNull());

        verify(mockDelegate).writeBoolean(true);
        verify(mockDelegate).writeBoolean(false);
        verify(mockDelegate).writeNull();
    }

    @Test
    public void testReturnValuesForCommentOmittedObjectIdRefTypeEmbedded() {
        JsonGeneratorDelegate delegate = new JsonGeneratorDelegate(mockDelegate);

        // writeComment
        assertSame(delegate,
                delegate.writeComment("cmt"));
        verify(mockDelegate).writeComment("cmt");

        // writeOmittedProperty
        assertSame(delegate,
                delegate.writeOmittedProperty("prop"));
        verify(mockDelegate).writeOmittedProperty("prop");

        // writeObjectId
        Object id1 = new Object();
        assertSame(delegate,
                delegate.writeObjectId(id1));
        verify(mockDelegate).writeObjectId(id1);

        // writeObjectRef
        Object ref1 = new Object();
        assertSame(delegate,
                delegate.writeObjectRef(ref1));
        verify(mockDelegate).writeObjectRef(ref1);

        // writeTypeId
        Object type1 = new Object();
        assertSame(delegate,
                delegate.writeTypeId(type1));
        verify(mockDelegate).writeTypeId(type1);

        // writeEmbeddedObject
        Object embed = new Object();
        assertSame(delegate,
                delegate.writeEmbeddedObject(embed));
        verify(mockDelegate).writeEmbeddedObject(embed);
    }

}
