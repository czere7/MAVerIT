package tools.jackson.core.filter;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Test;
import org.mockito.Mockito;

import tools.jackson.core.Base64Variant;
import tools.jackson.core.JsonGenerator;
import tools.jackson.core.TokenStreamContext;
import tools.jackson.core.filter.TokenFilter.Inclusion;

public class FilteringGeneratorDelegateTest {

    private static class CustomTokenFilter extends TokenFilter {
        @Override public boolean includeString(String value) { return true; }
        @Override public boolean includeNumber(int value) { return true; }
        @Override public boolean includeNumber(long value) { return true; }
        @Override public boolean includeNumber(float value) { return true; }
        @Override public boolean includeNumber(double value) { return true; }
        @Override public boolean includeNumber(BigDecimal value) { return true; }
        @Override public boolean includeNumber(BigInteger value) { return true; }
        @Override public boolean includeBoolean(boolean value) { return true; }
        @Override public boolean includeNull() { return true; }
        @Override public boolean includeBinary() { return true; }
        @Override public boolean includeRawValue() { return true; }
        @Override public TokenFilter filterStartObject() { return this; }
        @Override public TokenFilter filterStartArray() { return this; }
        @Override public TokenFilter includeProperty(String name) { return this; }
    }

    @Test
    public void testConstructorAndGetters() {
        JsonGenerator mockGen = Mockito.mock(JsonGenerator.class);
        FilteringGeneratorDelegate delegate =
                new FilteringGeneratorDelegate(mockGen, null,
                        Inclusion.INCLUDE_ALL_AND_PATH,
                        true);

        assertNull(delegate.getFilter());
        assertNotNull(delegate.getFilterContext());
        assertSame(delegate.getFilterContext(), delegate.streamWriteContext());
        assertEquals(0, delegate.getMatchCount());
    }

    @Test
    public void testWriteStartArrayWithoutFilterNoDelegateCall() throws Exception {
        JsonGenerator mockGen = Mockito.mock(JsonGenerator.class);
        FilteringGeneratorDelegate delegate =
                new FilteringGeneratorDelegate(mockGen, null,
                        Inclusion.INCLUDE_ALL_AND_PATH,
                        true);

        JsonGenerator result = delegate.writeStartArray();

        assertSame(delegate, result);
        verifyNoInteractions(mockGen);
    }

    @Test
    public void testWriteStringWithoutFilterNoDelegateCall() throws Exception {
        JsonGenerator mockGen = Mockito.mock(JsonGenerator.class);
        FilteringGeneratorDelegate delegate =
                new FilteringGeneratorDelegate(mockGen, null,
                        Inclusion.INCLUDE_ALL_AND_PATH,
                        true);

        JsonGenerator result = delegate.writeString("hello");

        assertSame(delegate, result);
        verifyNoInteractions(mockGen);
    }

    @Test
    public void testWriteNumberWithoutFilterNoDelegateCall() throws Exception {
        JsonGenerator mockGen = Mockito.mock(JsonGenerator.class);
        FilteringGeneratorDelegate delegate =
                new FilteringGeneratorDelegate(mockGen, null,
                        Inclusion.INCLUDE_ALL_AND_PATH,
                        true);

        JsonGenerator result = delegate.writeNumber(42);

        assertSame(delegate, result);
        verifyNoInteractions(mockGen);
    }

    @Test
    public void testMatchCountUnchangedWithoutFilter() throws Exception {
        JsonGenerator mockGen = Mockito.mock(JsonGenerator.class);
        FilteringGeneratorDelegate delegate =
                new FilteringGeneratorDelegate(mockGen, null,
                        Inclusion.INCLUDE_ALL_AND_PATH,
                        true);

        delegate.writeStartArray();
        delegate.writeString("foo");
        delegate.writeNumber(123);

        assertEquals(0, delegate.getMatchCount());
    }

    @Test
    public void testWriteStartArrayIncludeAll() throws Exception {
        JsonGenerator mockGen = Mockito.mock(JsonGenerator.class);
        FilteringGeneratorDelegate delegate =
                new FilteringGeneratorDelegate(mockGen, TokenFilter.INCLUDE_ALL,
                        Inclusion.INCLUDE_ALL_AND_PATH,
                        true);

        delegate.writeStartArray();

        verify(mockGen).writeStartArray();
    }

    @Test
    public void testWriteStringFilteredFalse() throws Exception {
        JsonGenerator mockGen = Mockito.mock(JsonGenerator.class);
        CustomTokenFilter filter = new CustomTokenFilter() {
            @Override public boolean includeString(String value) { return false; }
        };
        FilteringGeneratorDelegate delegate =
                new FilteringGeneratorDelegate(mockGen, filter,
                        Inclusion.INCLUDE_ALL_AND_PATH,
                        true);

        delegate.writeString("hello");

        verifyNoInteractions(mockGen);
        assertEquals(0, delegate.getMatchCount());
    }

    @Test
    public void testWriteRawValueIncluded() throws Exception {
        JsonGenerator mockGen = Mockito.mock(JsonGenerator.class);
        CustomTokenFilter filter = new CustomTokenFilter() {
            @Override public boolean includeRawValue() { return true; }
        };
        FilteringGeneratorDelegate delegate =
                new FilteringGeneratorDelegate(mockGen, filter,
                        Inclusion.INCLUDE_ALL_AND_PATH,
                        true);

        delegate.writeRawValue("raw");

        verify(mockGen).writeRawValue("raw");
    }

    @Test
    public void testWriteNameNullFilter() throws Exception {
        JsonGenerator mockGen = Mockito.mock(JsonGenerator.class);
        FilteringGeneratorDelegate delegate =
                new FilteringGeneratorDelegate(mockGen, null,
                        Inclusion.INCLUDE_ALL_AND_PATH,
                        true);

        delegate.writeName("prop");

        verifyNoInteractions(mockGen);
    }

    @Test
    public void testWriteNamePropertyIncludedAll() throws Exception {
        JsonGenerator mockGen = Mockito.mock(JsonGenerator.class);
        CustomTokenFilter filter = new CustomTokenFilter() {
            @Override public TokenFilter includeProperty(String name) { return TokenFilter.INCLUDE_ALL; }
        };
        FilteringGeneratorDelegate delegate =
                new FilteringGeneratorDelegate(mockGen, filter,
                        Inclusion.INCLUDE_ALL_AND_PATH,
                        true);

        delegate.writeName("myProp");

        verify(mockGen).writeName("myProp");
        assertEquals(1, delegate.getMatchCount());
    }

    @Test
    public void testWriteStringStateNullNoDelegateCall() throws Exception {
        JsonGenerator mockGen = Mockito.mock(JsonGenerator.class);

        TokenFilter root = new TokenFilter() {
            @Override public TokenFilter filterStartArray() {
                return new TokenFilter() {
                    @Override public boolean includeString(String value) { return true; }
                    @Override public TokenFilter includeElement(int ix) { return null; }
                };
            }

            @Override public TokenFilter includeRootValue(int ix) { return this; }
        };

        FilteringGeneratorDelegate delegate =
                new FilteringGeneratorDelegate(mockGen, root,
                        Inclusion.INCLUDE_ALL_AND_PATH,
                        true);

        delegate.writeStartArray();

        delegate.writeString("abc");

        verifyNoInteractions(mockGen);
    }

    @Test
    public void testWriteStringIncludeFalse() throws Exception {
        JsonGenerator mockGen = Mockito.mock(JsonGenerator.class);

        TokenFilter root = new TokenFilter() {
            @Override public TokenFilter filterStartArray() {
                return new TokenFilter() {
                    @Override public boolean includeString(String value) { return false; }
                };
            }

            @Override public TokenFilter includeRootValue(int ix) { return this; }
        };

        FilteringGeneratorDelegate delegate =
                new FilteringGeneratorDelegate(mockGen, root,
                        Inclusion.INCLUDE_ALL_AND_PATH,
                        true);

        delegate.writeStartArray();
        delegate.writeString("ignored");

        verifyNoInteractions(mockGen);
    }

    @Test
    public void testWriteRawValueNotIncluded() throws Exception {
        JsonGenerator mockGen = Mockito.mock(JsonGenerator.class);

        TokenFilter filter = new TokenFilter() {
            @Override public boolean includeRawValue() { return false; }
        };

        FilteringGeneratorDelegate delegate =
                new FilteringGeneratorDelegate(mockGen, filter,
                        Inclusion.INCLUDE_ALL_AND_PATH,
                        true);

        delegate.writeRawValue("shouldNotWrite");

        verifyNoInteractions(mockGen);
    }

    @Test
    public void testWriteBinaryIncluded() throws Exception {
        JsonGenerator mockGen = Mockito.mock(JsonGenerator.class);
        Base64Variant b64 = mock(Base64Variant.class);

        TokenFilter filter = new TokenFilter() {
            @Override public boolean includeBinary() { return true; }
        };

        FilteringGeneratorDelegate delegate =
                new FilteringGeneratorDelegate(mockGen, filter,
                        Inclusion.INCLUDE_ALL_AND_PATH,
                        true);

        byte[] data = {1,2,3};
        delegate.writeBinary(b64, data, 0, data.length);

        verify(mockGen).writeBinary(b64, data, 0, data.length);
    }

    @Test
    public void testWriteStringWithNonNullInclusion() throws Exception {
        JsonGenerator mockGen = Mockito.mock(JsonGenerator.class);
        TokenFilter filter = new CustomTokenFilter(); 

        FilteringGeneratorDelegate delegate =
                new FilteringGeneratorDelegate(mockGen, filter,
                        Inclusion.INCLUDE_NON_NULL,
                        true);

        delegate.writeString("test");

        assertEquals(1, delegate.getMatchCount());
        verify(mockGen).writeString("test");
    }

    @Test
    public void testWriteNumberAllowed() throws Exception {
        JsonGenerator mockGen = Mockito.mock(JsonGenerator.class);

        TokenFilter filter = new CustomTokenFilter(); 

        FilteringGeneratorDelegate delegate =
                new FilteringGeneratorDelegate(mockGen, filter,
                        Inclusion.INCLUDE_ALL_AND_PATH,
                        true);

        delegate.writeNumber(999);

        verify(mockGen).writeNumber(999);
    }

    @Test
    public void testWriteNumberRejected() throws Exception {
        JsonGenerator mockGen = Mockito.mock(JsonGenerator.class);

        TokenFilter filter = new CustomTokenFilter() {
            @Override public boolean includeNumber(int value) { return false; }
        };

        FilteringGeneratorDelegate delegate =
                new FilteringGeneratorDelegate(mockGen, filter,
                        Inclusion.INCLUDE_ALL_AND_PATH,
                        true);

        delegate.writeNumber(555);

        verifyNoInteractions(mockGen);
    }

    @Test
    public void testWriteNameWithMatchCount() throws Exception {
        JsonGenerator mockGen = Mockito.mock(JsonGenerator.class);
        CustomTokenFilter filter = new CustomTokenFilter();

        FilteringGeneratorDelegate delegate =
                new FilteringGeneratorDelegate(mockGen, filter,
                        Inclusion.INCLUDE_ALL_AND_PATH,
                        true);

        delegate.writeName("foo");

        assertEquals(0, delegate.getMatchCount());
    }
}
