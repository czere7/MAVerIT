package tools.jackson.core.filter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.StringReader;
import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Test;

import tools.jackson.core.Base64Variant;
import tools.jackson.core.JsonGenerator;
import tools.jackson.core.SerializableString;
import tools.jackson.core.filter.TokenFilter.Inclusion;

public class FilteringGeneratorDelegateTest {

    private JsonGenerator delegate() {
        return mock(JsonGenerator.class);
    }

    @Test
    public void constructorExposesFilterAndRootContext() {
        JsonGenerator generator = delegate();
        TokenFilter filter = new PropertyFilter("value", true);

        FilteringGeneratorDelegate filtering =
                new FilteringGeneratorDelegate(generator, filter,
                        Inclusion.INCLUDE_NON_NULL, false);

        assertSame(filter, filtering.getFilter());
        assertSame(filtering.getFilterContext(), filtering.streamWriteContext());
        assertTrue(filtering.getFilterContext().inRoot());
        assertEquals(0, filtering.getMatchCount());
    }

    @Test
    public void includeAllDelegatesContainerAndScalarWrites() throws Exception {
        JsonGenerator generator = delegate();
        FilteringGeneratorDelegate filtering =
                new FilteringGeneratorDelegate(generator, TokenFilter.INCLUDE_ALL,
                        Inclusion.INCLUDE_NON_NULL, true);

        assertSame(filtering, filtering.writeStartObject());
        assertSame(filtering, filtering.writeName("value"));
        assertSame(filtering, filtering.writeString("accepted"));
        assertSame(filtering, filtering.writeEndObject());

        verify(generator).writeStartObject();
        verify(generator).writeName("value");
        verify(generator).writeString("accepted");
        verify(generator).writeEndObject();
        assertEquals(0, filtering.getMatchCount());
    }

    @Test
    public void matchingPropertyIsWrittenWithItsValue() throws Exception {
        JsonGenerator generator = delegate();
        FilteringGeneratorDelegate filtering =
                new FilteringGeneratorDelegate(generator,
                        new PropertyFilter("value", true),
                        Inclusion.INCLUDE_NON_NULL, true);

        filtering.writeStartObject();
        filtering.writeName("value");
        filtering.writeString("accepted");
        filtering.writeEndObject();

        verify(generator).writeStartObject();
        verify(generator).writeName("value");
        verify(generator).writeString("accepted");
        verify(generator).writeEndObject();
        assertEquals(1, filtering.getMatchCount());
    }

    @Test
    public void nonMatchingPropertySuppressesItsValue() throws Exception {
        JsonGenerator generator = delegate();
        FilteringGeneratorDelegate filtering =
                new FilteringGeneratorDelegate(generator,
                        new PropertyFilter("value", true),
                        Inclusion.INCLUDE_NON_NULL, true);

        filtering.writeStartObject();
        filtering.writeName("other");
        filtering.writeString("rejected");
        filtering.writeEndObject();

        verify(generator).writeStartObject();
        verify(generator).writeEndObject();
        assertEquals(0, filtering.getMatchCount());
    }

    @Test
    public void rejectedScalarIsNotDelegated() throws Exception {
        JsonGenerator generator = delegate();
        FilteringGeneratorDelegate filtering =
                new FilteringGeneratorDelegate(generator,
                        new PropertyFilter("value", false),
                        Inclusion.INCLUDE_NON_NULL, true);

        filtering.writeStartObject();
        filtering.writeName("value");
        filtering.writeString("rejected");
        filtering.writeEndObject();

        verify(generator).writeStartObject();
        verify(generator).writeEndObject();
        assertEquals(1, filtering.getMatchCount());
    }

    @Test
    public void serializablePropertyNameUsesSameFilteringLogic() throws Exception {
        JsonGenerator generator = delegate();
        SerializableString name = mock(SerializableString.class);
        when(name.getValue()).thenReturn("value");

        FilteringGeneratorDelegate filtering =
                new FilteringGeneratorDelegate(generator,
                        new PropertyFilter("value", true),
                        Inclusion.INCLUDE_NON_NULL, true);

        filtering.writeStartObject();
        filtering.writeName(name);
        filtering.writeNumber(13);
        filtering.writeEndObject();

        verify(generator).writeStartObject();
        verify(generator).writeName("value");
        verify(generator).writeNumber(13);
        verify(generator).writeEndObject();
        assertEquals(1, filtering.getMatchCount());
    }

    @Test
    public void nullFilterSuppressesValuesAndContainers() throws Exception {
        JsonGenerator generator = delegate();
        FilteringGeneratorDelegate filtering =
                new FilteringGeneratorDelegate(generator, null,
                        Inclusion.INCLUDE_NON_NULL, true);

        filtering.writeStartArray();
        filtering.writeString("ignored");
        filtering.writeEndArray();

        verifyNoInteractions(generator);
        assertTrue(filtering.getFilterContext().inRoot());
        assertEquals(0, filtering.getMatchCount());
    }

    @Test
    public void arrayElementCanBeIncluded() throws Exception {
        JsonGenerator generator = delegate();
        FilteringGeneratorDelegate filtering =
                new FilteringGeneratorDelegate(generator,
                        new ArrayFilter(), Inclusion.INCLUDE_NON_NULL, true);

        filtering.writeStartArray();
        filtering.writeString("element");
        filtering.writeEndArray();

        verify(generator).writeStartArray();
        verify(generator).writeString("element");
        verify(generator).writeEndArray();
        assertEquals(1, filtering.getMatchCount());
        assertTrue(filtering.getFilterContext().inRoot());
    }

    @Test
    public void rawValueIsSuppressedWhenFilterRejectsRawValues() throws Exception {
        JsonGenerator generator = delegate();
        FilteringGeneratorDelegate filtering =
                new FilteringGeneratorDelegate(generator,
                        new RawRejectingFilter(),
                        Inclusion.INCLUDE_NON_NULL, true);

        filtering.writeStartObject();
        filtering.writeName("value");
        filtering.writeRaw("raw");
        filtering.writeEndObject();

        verify(generator).writeStartObject();
        verify(generator).writeEndObject();
        assertEquals(0, filtering.getMatchCount());
    }

    @Test
    public void binaryValuesAreSuppressedWhenFilterRejectsThem() throws Exception {
        JsonGenerator generator = delegate();
        Base64Variant variant = mock(Base64Variant.class);
        byte[] bytes = new byte[] { 1, 2 };

        FilteringGeneratorDelegate filtering =
                new FilteringGeneratorDelegate(generator,
                        new RejectingBinaryFilter(),
                        Inclusion.INCLUDE_NON_NULL, true);

        assertEquals(-1, filtering.writeBinary(variant,
                new ByteArrayInputStream(bytes), bytes.length));
        assertSame(filtering, filtering.writeBinary(variant, bytes, 0, bytes.length));

        verifyNoInteractions(generator);
        assertEquals(0, filtering.getMatchCount());
    }

    @Test
    public void allContainerOverloadsDelegateWhenFilterIncludesEverything() throws Exception {
        JsonGenerator generator = delegate();
        FilteringGeneratorDelegate filtering =
                new FilteringGeneratorDelegate(generator, TokenFilter.INCLUDE_ALL,
                        Inclusion.INCLUDE_NON_NULL, true);

        Object value = new Object();

        filtering.writeStartArray();
        filtering.writeEndArray();
        filtering.writeStartArray(value);
        filtering.writeEndArray();
        filtering.writeStartArray(value, 2);
        filtering.writeEndArray();

        filtering.writeStartObject();
        filtering.writeEndObject();
        filtering.writeStartObject(value);
        filtering.writeEndObject();
        filtering.writeStartObject(value, 2);
        filtering.writeEndObject();

        verify(generator).writeStartArray();
        verify(generator).writeStartArray(value);
        verify(generator).writeStartArray(value, 2);
        verify(generator, times(3)).writeEndArray();
        verify(generator).writeStartObject();
        verify(generator).writeStartObject(value);
        verify(generator).writeStartObject(value, 2);
        verify(generator, times(3)).writeEndObject();
    }

    @Test
    public void scalarOverloadsDelegateWhenFilterIncludesEverything() throws Exception {
        JsonGenerator generator = delegate();
        Base64Variant variant = mock(Base64Variant.class);
        SerializableString serializable = mock(SerializableString.class);
        FilteringGeneratorDelegate filtering =
                new FilteringGeneratorDelegate(generator, TokenFilter.INCLUDE_ALL,
                        Inclusion.INCLUDE_NON_NULL, true);

        byte[] bytes = new byte[] { 1, 2 };
        char[] chars = new char[] { 'a', 'b', 'c' };

        filtering.writeString(chars, 0, 2);
        filtering.writeString(serializable);
        filtering.writeString(new StringReader("reader"), 6);
        filtering.writeRawUTF8String(bytes, 0, 2);
        filtering.writeUTF8String(bytes, 0, 2);
        filtering.writeRaw("raw");
        filtering.writeRaw("raw", 0, 2);
        filtering.writeRaw(serializable);
        filtering.writeRaw(chars, 0, 2);
        filtering.writeRaw('x');
        filtering.writeRawValue("value");
        filtering.writeRawValue("value", 0, 2);
        filtering.writeRawValue(chars, 0, 2);
        filtering.writeNumber((short) 1);
        filtering.writeNumber(2);
        filtering.writeNumber(3L);
        filtering.writeNumber(BigInteger.TEN);
        filtering.writeNumber(1.25d);
        filtering.writeNumber(2.5f);
        filtering.writeNumber(BigDecimal.ONE);
        filtering.writeNumber("4");
        filtering.writeNumber(chars, 0, 1);
        filtering.writeBoolean(true);
        filtering.writeNull();
        filtering.writeBinary(variant, bytes, 0, 2);
        filtering.writeBinary(variant, new ByteArrayInputStream(bytes), 2);

        verify(generator).writeString(chars, 0, 2);
        verify(generator).writeString(serializable);
        verify(generator).writeString(org.mockito.ArgumentMatchers.any(StringReader.class), eq(6));
        verify(generator).writeRawUTF8String(bytes, 0, 2);
        verify(generator).writeUTF8String(bytes, 0, 2);
        verify(generator).writeRaw("raw");
        verify(generator).writeRaw("raw", 0, 2);
        verify(generator).writeRaw(serializable);
        verify(generator).writeRaw(chars, 0, 2);
        verify(generator).writeRaw('x');
        verify(generator).writeRawValue("value");
        verify(generator).writeRawValue("value", 0, 2);
        verify(generator).writeRawValue(chars, 0, 2);
        verify(generator).writeNumber((short) 1);
        verify(generator).writeNumber(2);
        verify(generator).writeNumber(3L);
        verify(generator).writeNumber(BigInteger.TEN);
        verify(generator).writeNumber(1.25d);
        verify(generator).writeNumber(2.5f);
        verify(generator).writeNumber(BigDecimal.ONE);
        verify(generator).writeNumber("4");
        verify(generator).writeNumber(chars, 0, 1);
        verify(generator).writeBoolean(true);
        verify(generator).writeNull();
        verify(generator).writeBinary(variant, bytes, 0, 2);
        verify(generator).writeBinary(eq(variant),
                org.mockito.ArgumentMatchers.any(ByteArrayInputStream.class), eq(2));
    }

    @Test
    public void propertyIdIsConvertedToAPropertyName() throws Exception {
        JsonGenerator generator = delegate();
        FilteringGeneratorDelegate filtering =
                new FilteringGeneratorDelegate(generator, TokenFilter.INCLUDE_ALL,
                        Inclusion.INCLUDE_NON_NULL, true);

        assertSame(filtering, filtering.writeStartObject());
        assertSame(filtering, filtering.writePropertyId(42L));
        assertSame(filtering, filtering.writeEndObject());

        verify(generator).writeName("42");
    }

    @Test
    public void emptyContainersAreIncludedWithPathInclusion() throws Exception {
        JsonGenerator generator = delegate();
        FilteringGeneratorDelegate filtering =
                new FilteringGeneratorDelegate(generator,
                        new EmptyContainerFilter(),
                        Inclusion.INCLUDE_ALL_AND_PATH, true);

        filtering.writeStartArray();
        filtering.writeEndArray();
        filtering.writeStartObject();
        filtering.writeEndObject();

        verify(generator).writeStartArray();
        verify(generator).writeEndArray();
        verify(generator).writeStartObject();
        verify(generator).writeEndObject();
    }

    @Test
    public void acceptedBinaryValuesAreDelegatedAndInputStreamLengthIsReturned() throws Exception {
        JsonGenerator generator = delegate();
        Base64Variant variant = mock(Base64Variant.class);
        byte[] bytes = new byte[] { 3, 4, 5 };
        when(generator.writeBinary(eq(variant),
                org.mockito.ArgumentMatchers.any(ByteArrayInputStream.class), eq(3)))
                .thenReturn(7);

        FilteringGeneratorDelegate filtering =
                new FilteringGeneratorDelegate(generator,
                        new AcceptingBinaryFilter(),
                        Inclusion.INCLUDE_NON_NULL, true);

        assertEquals(7, filtering.writeBinary(variant,
                new ByteArrayInputStream(bytes), bytes.length));
        assertSame(filtering, filtering.writeBinary(variant, bytes, 0, bytes.length));

        verify(generator).writeBinary(eq(variant),
                org.mockito.ArgumentMatchers.any(ByteArrayInputStream.class), eq(3));
        verify(generator).writeBinary(variant, bytes, 0, bytes.length);
        assertEquals(2, filtering.getMatchCount());
    }

    @Test
    public void acceptedRawValuesAreDelegatedAndPropertyNameIsWritten() throws Exception {
        JsonGenerator generator = delegate();
        FilteringGeneratorDelegate filtering =
                new FilteringGeneratorDelegate(generator,
                        new AcceptingRawFilter(),
                        Inclusion.INCLUDE_NON_NULL, true);

        filtering.writeStartObject();
        assertSame(filtering, filtering.writeName("rawValue"));
        assertSame(filtering, filtering.writeRaw("raw-content"));
        assertSame(filtering, filtering.writeEndObject());

        verify(generator).writeStartObject();
        verify(generator).writeName("rawValue");
        verify(generator).writeRaw("raw-content");
        verify(generator).writeEndObject();
        assertEquals(1, filtering.getMatchCount());
    }

    @Test
    public void pathInclusionWritesNestedContainerAndPropertyPathBeforeValue() throws Exception {
        JsonGenerator generator = delegate();
        FilteringGeneratorDelegate filtering =
                new FilteringGeneratorDelegate(generator,
                        new NestedPropertyFilter(),
                        Inclusion.INCLUDE_ALL_AND_PATH, true);

        filtering.writeStartObject();
        filtering.writeName("container");
        filtering.writeStartObject();
        filtering.writeName("value");
        filtering.writeString("included");
        filtering.writeEndObject();
        filtering.writeEndObject();

        verify(generator, times(2)).writeStartObject();
        verify(generator).writeName("container");
        verify(generator).writeName("value");
        verify(generator).writeString("included");
        verify(generator, times(2)).writeEndObject();
        assertEquals(1, filtering.getMatchCount());
    }

    @Test
    public void firstMatchSuppressesLaterMatchesWhenMultipleMatchesAreDisallowed() throws Exception {
        JsonGenerator generator = delegate();
        FilteringGeneratorDelegate filtering =
                new FilteringGeneratorDelegate(generator,
                        new AcceptEveryPropertyFilter(),
                        Inclusion.INCLUDE_NON_NULL, false);

        filtering.writeStartObject();
        filtering.writeName("first");
        filtering.writeString("one");
        filtering.writeName("second");
        filtering.writeString("two");
        filtering.writeEndObject();

        verify(generator).writeStartObject();
        verify(generator).writeName("first");
        verify(generator).writeString("one");
        verify(generator).writeEndObject();
        verify(generator, times(1)).writeString("one");
        assertEquals(1, filtering.getMatchCount());
    }

    @Test
    public void booleanValueIsIncludedOnlyWhenBooleanFilterAcceptsIt() throws Exception {
        JsonGenerator generator = delegate();
        FilteringGeneratorDelegate filtering =
                new FilteringGeneratorDelegate(generator,
                        new BooleanPropertyFilter(),
                        Inclusion.INCLUDE_NON_NULL, true);

        filtering.writeStartObject();
        filtering.writeName("accepted");
        assertSame(filtering, filtering.writeBoolean(true));
        filtering.writeName("rejected");
        assertSame(filtering, filtering.writeBoolean(false));
        filtering.writeEndObject();

        verify(generator).writeStartObject();
        verify(generator).writeName("accepted");
        verify(generator).writeBoolean(true);
        verify(generator).writeEndObject();
        assertEquals(1, filtering.getMatchCount());
    }

    @Test
    public void acceptedBinaryByteArrayWriteReturnsThisAndCountsMatch() throws Exception {
        JsonGenerator generator = delegate();
        Base64Variant variant = mock(Base64Variant.class);
        byte[] bytes = new byte[] { 8, 9 };

        FilteringGeneratorDelegate filtering =
                new FilteringGeneratorDelegate(generator,
                        new AcceptingBinaryFilter(),
                        Inclusion.INCLUDE_NON_NULL, true);

        assertSame(filtering, filtering.writeBinary(variant, bytes, 0, bytes.length));
        verify(generator).writeBinary(variant, bytes, 0, bytes.length);
        assertEquals(1, filtering.getMatchCount());
    }

    @Test
    public void acceptedRawWriteReturnsThisAndCountsMatch() throws Exception {
        JsonGenerator generator = delegate();
        FilteringGeneratorDelegate filtering =
                new FilteringGeneratorDelegate(generator,
                        new AcceptingRawFilter(),
                        Inclusion.INCLUDE_NON_NULL, true);

        assertSame(filtering, filtering.writeRaw("raw"));
        verify(generator).writeRaw("raw");
        assertEquals(1, filtering.getMatchCount());
    }

    @Test
    public void multipleMatchesRemainIncludedWhenAllowed() throws Exception {
        JsonGenerator generator = delegate();
        FilteringGeneratorDelegate filtering =
                new FilteringGeneratorDelegate(generator,
                        new AcceptEveryPropertyFilter(),
                        Inclusion.INCLUDE_NON_NULL, true);

        filtering.writeStartObject();
        filtering.writeName("first");
        filtering.writeString("one");
        filtering.writeName("second");
        filtering.writeString("two");
        filtering.writeEndObject();

        verify(generator).writeName("first");
        verify(generator).writeString("one");
        verify(generator).writeName("second");
        verify(generator).writeString("two");
        verify(generator).writeEndObject();
        assertEquals(2, filtering.getMatchCount());
    }

    @Test
    public void commentsAndMetadataAreSuppressedWhenCurrentPropertyIsRejected()
            throws Exception {
        JsonGenerator generator = delegate();
        FilteringGeneratorDelegate filtering =
                new FilteringGeneratorDelegate(generator,
                        new PropertyFilter("accepted", true),
                        Inclusion.INCLUDE_NON_NULL, true);

        filtering.writeStartObject();
        filtering.writeName("rejected");
        assertSame(filtering, filtering.writeComment("ignored"));
        assertSame(filtering, filtering.writeObjectId("ignored"));
        assertSame(filtering, filtering.writeObjectRef("ignored"));
        assertSame(filtering, filtering.writeTypeId("ignored"));
        filtering.writeEndObject();

        verify(generator).writeStartObject();
        verify(generator).writeEndObject();
        verifyNoInteractionsExceptStructure(generator);
    }

    @Test
    public void commentsAndMetadataDelegateWhenCurrentPropertyIsIncluded()
            throws Exception {
        JsonGenerator generator = delegate();
        FilteringGeneratorDelegate filtering =
                new FilteringGeneratorDelegate(generator,
                        new AcceptEveryPropertyFilter(),
                        Inclusion.INCLUDE_NON_NULL, true);

        filtering.writeStartObject();
        filtering.writeName("value");
        filtering.writeComment("comment");
        filtering.writeObjectId("id");
        filtering.writeObjectRef("ref");
        filtering.writeTypeId("type");
        filtering.writeEndObject();

        verify(generator).writeStartObject();
        verify(generator).writeName("value");
        verify(generator).writeComment("comment");
        verify(generator).writeObjectId("id");
        verify(generator).writeObjectRef("ref");
        verify(generator).writeTypeId("type");
        verify(generator).writeEndObject();
    }

    @Test
    public void acceptedNullIsDelegatedAndRejectedNullIsSuppressed() throws Exception {
        JsonGenerator generator = delegate();
        FilteringGeneratorDelegate filtering =
                new FilteringGeneratorDelegate(generator,
                        new NullPropertyFilter(),
                        Inclusion.INCLUDE_NON_NULL, true);

        filtering.writeStartObject();
        assertSame(filtering, filtering.writeName("accepted"));
        assertSame(filtering, filtering.writeNull());
        assertSame(filtering, filtering.writeName("rejected"));
        assertSame(filtering, filtering.writeNull());
        assertSame(filtering, filtering.writeEndObject());

        verify(generator).writeStartObject();
        verify(generator).writeName("accepted");
        verify(generator).writeNull();
        verify(generator).writeEndObject();
        assertEquals(1, filtering.getMatchCount());
    }

    @Test
    public void rejectedRawWriteReturnsThisWithoutIncrementingMatchCount() throws Exception {
        JsonGenerator generator = delegate();
        FilteringGeneratorDelegate filtering =
                new FilteringGeneratorDelegate(generator,
                        new RawRejectingFilter(),
                        Inclusion.INCLUDE_NON_NULL, true);

        filtering.writeStartObject();
        assertSame(filtering, filtering.writeName("value"));
        assertSame(filtering, filtering.writeRaw("ignored"));
        assertSame(filtering, filtering.writeRawValue("ignored"));
        assertSame(filtering, filtering.writeEndObject());

        verify(generator).writeStartObject();
        verify(generator).writeEndObject();
        assertEquals(0, filtering.getMatchCount());
    }

    @Test
    public void pathInclusionWritesParentPathOnlyForAcceptedNestedValue() throws Exception {
        JsonGenerator generator = delegate();
        FilteringGeneratorDelegate filtering =
                new FilteringGeneratorDelegate(generator,
                        new NestedPropertyFilter(),
                        Inclusion.INCLUDE_ALL_AND_PATH, false);

        filtering.writeStartObject();
        filtering.writeName("container");
        filtering.writeStartObject();
        filtering.writeName("value");
        filtering.writeString("value");
        filtering.writeName("other");
        filtering.writeString("ignored");
        filtering.writeEndObject();
        filtering.writeEndObject();

        verify(generator, times(2)).writeStartObject();
        verify(generator).writeName("container");
        verify(generator).writeName("value");
        verify(generator).writeString("value");
        verify(generator, times(2)).writeEndObject();
        assertEquals(1, filtering.getMatchCount());
    }

    private void verifyNoInteractionsExceptStructure(JsonGenerator generator) {
        verify(generator).writeStartObject();
        verify(generator).writeEndObject();
    }

    private static class PropertyFilter extends TokenFilter {
        private final String property;
        private final boolean acceptString;

        PropertyFilter(String property, boolean acceptString) {
            this.property = property;
            this.acceptString = acceptString;
        }

        @Override
        public TokenFilter includeProperty(String name) {
            return property.equals(name) ? TokenFilter.INCLUDE_ALL : null;
        }

        @Override
        public boolean includeString(String value) {
            return acceptString;
        }

        @Override
        public boolean includeNumber(int value) {
            return true;
        }
    }

    private static class ArrayFilter extends TokenFilter {
        @Override
        public TokenFilter includeRootValue(int index) {
            return this;
        }

        @Override
        public TokenFilter includeElement(int index) {
            return this;
        }

        @Override
        public boolean includeString(String value) {
            return true;
        }
    }

    private static class RawRejectingFilter extends TokenFilter {
        @Override
        public TokenFilter includeProperty(String name) {
            return this;
        }

        @Override
        public boolean includeRawValue() {
            return false;
        }
    }

    private static class RejectingBinaryFilter extends TokenFilter {
        @Override
        public boolean includeBinary() {
            return false;
        }
    }

    private static class EmptyContainerFilter extends TokenFilter {
        @Override
        public boolean includeEmptyArray(boolean contents) {
            return true;
        }

        @Override
        public boolean includeEmptyObject(boolean contents) {
            return true;
        }
    }

    private static class AcceptingBinaryFilter extends TokenFilter {
        @Override
        public boolean includeBinary() {
            return true;
        }
    }

    private static class AcceptingRawFilter extends TokenFilter {
        @Override
        public TokenFilter includeProperty(String name) {
            return this;
        }

        @Override
        public boolean includeRawValue() {
            return true;
        }
    }

    private static class NestedPropertyFilter extends TokenFilter {
        @Override
        public TokenFilter includeProperty(String name) {
            if ("container".equals(name)) {
                return this;
            }
            if ("value".equals(name)) {
                return TokenFilter.INCLUDE_ALL;
            }
            return null;
        }

        @Override
        public boolean includeString(String value) {
            return true;
        }
    }

    private static class AcceptEveryPropertyFilter extends TokenFilter {
        @Override
        public TokenFilter includeProperty(String name) {
            return TokenFilter.INCLUDE_ALL;
        }

        @Override
        public boolean includeString(String value) {
            return true;
        }
    }

    private static class BooleanPropertyFilter extends TokenFilter {
        @Override
        public TokenFilter includeProperty(String name) {
            return this;
        }

        @Override
        public boolean includeBoolean(boolean value) {
            return value;
        }
    }

    private static class NullPropertyFilter extends TokenFilter {
        @Override
        public TokenFilter includeProperty(String name) {
            return "accepted".equals(name) ? this : null;
        }

        @Override
        public boolean includeNull() {
            return true;
        }
    }
}
