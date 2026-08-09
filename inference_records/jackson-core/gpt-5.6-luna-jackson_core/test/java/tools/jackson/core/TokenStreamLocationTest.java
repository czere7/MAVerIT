package tools.jackson.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;

import org.junit.Test;

import tools.jackson.core.io.ContentReference;

public class TokenStreamLocationTest {

    @Test
    public void fourArgumentConstructorUsesUnknownByteOffset() {
        ContentReference contentReference = mock(ContentReference.class);

        TokenStreamLocation location =
                new TokenStreamLocation(contentReference, 42L, 3, 7);

        assertSame(contentReference, location.contentReference());
        assertEquals(-1L, location.getByteOffset());
        assertEquals(42L, location.getCharOffset());
        assertEquals(3, location.getLineNr());
        assertEquals(7, location.getColumnNr());
    }

    @Test
    public void nullContentReferenceIsReplacedWithUnknownReference() {
        TokenStreamLocation location =
                new TokenStreamLocation(null, 10L, 20L, 2, 4);

        assertSame(ContentReference.unknown(), location.contentReference());
        assertEquals(10L, location.getByteOffset());
        assertEquals(20L, location.getCharOffset());
        assertEquals(2, location.getLineNr());
        assertEquals(4, location.getColumnNr());
    }

    @Test
    public void textualOffsetDescriptionReportsKnownAndUnknownLineAndColumn() {
        ContentReference contentReference = mock(ContentReference.class);
        when(contentReference.hasTextualContent()).thenReturn(true);

        TokenStreamLocation known =
                new TokenStreamLocation(contentReference, 12L, 34L, 5, 9);
        TokenStreamLocation unknown =
                new TokenStreamLocation(contentReference, 12L, 34L, -1, -1);

        assertEquals("line: 5, column: 9", known.offsetDescription());
        assertEquals("line: UNKNOWN, column: UNKNOWN", unknown.offsetDescription());
    }

    @Test
    public void binaryOffsetDescriptionReportsByteOffset() {
        ContentReference contentReference = mock(ContentReference.class);
        when(contentReference.hasTextualContent()).thenReturn(false);

        TokenStreamLocation known =
                new TokenStreamLocation(contentReference, 27L, 100L, -1, -1);
        TokenStreamLocation unknown =
                new TokenStreamLocation(contentReference, -1L, 100L, -1, -1);

        assertEquals("byte offset: #27", known.offsetDescription());
        assertEquals("byte offset: #UNKNOWN", unknown.offsetDescription());
    }

    @Test
    public void sourceDescriptionIsConstructedLazilyAndCached() {
        ContentReference contentReference = mock(ContentReference.class);
        when(contentReference.buildSourceDescription()).thenReturn("payload");
        when(contentReference.hasTextualContent()).thenReturn(true);

        TokenStreamLocation location =
                new TokenStreamLocation(contentReference, 1L, 2L, 3, 4);

        assertEquals("payload", location.sourceDescription());
        assertEquals("payload", location.sourceDescription());
        verify(contentReference, times(1)).buildSourceDescription();
    }

    @Test
    public void toStringIncludesSourceAndOffsets() {
        ContentReference contentReference = mock(ContentReference.class);
        when(contentReference.buildSourceDescription()).thenReturn("document.json");
        when(contentReference.hasTextualContent()).thenReturn(true);

        TokenStreamLocation location =
                new TokenStreamLocation(contentReference, 8L, 15L, 2, 6);

        assertEquals("[Source: document.json; line: 2, column: 6]",
                location.toString());
    }

    @Test
    public void toStringAppendsToProvidedBuilder() {
        ContentReference contentReference = mock(ContentReference.class);
        when(contentReference.buildSourceDescription()).thenReturn("input");
        when(contentReference.hasTextualContent()).thenReturn(false);

        TokenStreamLocation location =
                new TokenStreamLocation(contentReference, 11L, 20L, -1, -1);
        StringBuilder builder = new StringBuilder("prefix:");

        StringBuilder result = location.toString(builder);

        assertSame(builder, result);
        assertEquals("prefix:[Source: input; byte offset: #11]",
                result.toString());
    }

    @Test
    public void naLocationHasSpecialDescription() {
        assertEquals("[No location information]", TokenStreamLocation.NA.toString());

        StringBuilder builder = new StringBuilder("prefix:");
        assertSame(builder, TokenStreamLocation.NA.toString(builder));
        assertEquals("prefix:[No location information]", builder.toString());
    }

    @Test
    public void equalLocationsHaveSameHashCode() {
        ContentReference contentReference = mock(ContentReference.class);

        TokenStreamLocation first =
                new TokenStreamLocation(contentReference, 4L, 9L, 1, 2);
        TokenStreamLocation second =
                new TokenStreamLocation(contentReference, 4L, 9L, 1, 2);

        assertEquals(first, first);
        assertEquals(first, second);
        assertEquals(second, first);
        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    public void locationsAreUnequalWhenAnyLocationValueDiffers() {
        ContentReference contentReference = mock(ContentReference.class);

        TokenStreamLocation baseline =
                new TokenStreamLocation(contentReference, 4L, 9L, 1, 2);

        assertNotEquals(baseline,
                new TokenStreamLocation(contentReference, 5L, 9L, 1, 2));
        assertNotEquals(baseline,
                new TokenStreamLocation(contentReference, 4L, 10L, 1, 2));
        assertNotEquals(baseline,
                new TokenStreamLocation(contentReference, 4L, 9L, 2, 2));
        assertNotEquals(baseline,
                new TokenStreamLocation(contentReference, 4L, 9L, 1, 3));
        assertNotEquals(baseline, null);
        assertNotEquals(baseline, "not a location");
    }

    @Test
    public void locationsWithDifferentContentReferencesAreUnequal() {
        ContentReference firstReference = mock(ContentReference.class);
        ContentReference secondReference = mock(ContentReference.class);

        TokenStreamLocation first =
                new TokenStreamLocation(firstReference, 1L, 2L, 3, 4);
        TokenStreamLocation second =
                new TokenStreamLocation(secondReference, 1L, 2L, 3, 4);

        assertFalse(first.equals(second));
        assertNotSame(first.contentReference(), second.contentReference());
        assertTrue(first.equals(first));
    }

    @Test
    public void equalsHandlesNullContentReferences() throws Exception {
        TokenStreamLocation first =
                new TokenStreamLocation(ContentReference.unknown(), 4L, 9L, 1, 2);
        TokenStreamLocation second =
                new TokenStreamLocation(ContentReference.unknown(), 4L, 9L, 1, 2);
        TokenStreamLocation regular =
                new TokenStreamLocation(ContentReference.unknown(), 4L, 9L, 1, 2);

        setContentReference(first, null);
        setContentReference(second, null);

        assertTrue(first.equals(second));
        assertFalse(first.equals(regular));
        assertFalse(regular.equals(first));
    }

    @Test
    public void hashCodeHandlesNullContentReference() throws Exception {
        TokenStreamLocation location =
                new TokenStreamLocation(ContentReference.unknown(), 4L, 9L, 1, 2);

        setContentReference(location, null);

        int expected = 1;
        expected ^= 1;
        expected += 2;
        expected ^= 9;
        expected += 4;

        assertEquals(expected, location.hashCode());
    }

    @Test
    public void zeroLineAndColumnAreReportedAsKnown() {
        ContentReference contentReference = mock(ContentReference.class);
        when(contentReference.hasTextualContent()).thenReturn(true);

        TokenStreamLocation location =
                new TokenStreamLocation(contentReference, 0L, 0L, 0, 0);

        assertEquals("line: 0, column: 0", location.offsetDescription());
    }

    @Test
    public void zeroByteOffsetIsReportedAsKnown() {
        ContentReference contentReference = mock(ContentReference.class);
        when(contentReference.hasTextualContent()).thenReturn(false);

        TokenStreamLocation location =
                new TokenStreamLocation(contentReference, 0L, 0L, -1, -1);

        assertEquals("byte offset: #0", location.offsetDescription());
    }

    @Test
    public void toStringSupportsSourceDescriptionsLongerThanDefaultCapacity() {
        ContentReference contentReference = mock(ContentReference.class);
        when(contentReference.buildSourceDescription())
                .thenReturn("12345678901234567890123456789012345678901");
        when(contentReference.hasTextualContent()).thenReturn(true);

        TokenStreamLocation location =
                new TokenStreamLocation(contentReference, 8L, 15L, 2, 6);

        assertEquals(
                "[Source: 12345678901234567890123456789012345678901; line: 2, column: 6]",
                location.toString());
    }

    private static void setContentReference(TokenStreamLocation location,
            ContentReference contentReference) throws Exception {
        Field field = TokenStreamLocation.class.getDeclaredField("_contentReference");
        field.setAccessible(true);
        field.set(location, contentReference);
    }
}
