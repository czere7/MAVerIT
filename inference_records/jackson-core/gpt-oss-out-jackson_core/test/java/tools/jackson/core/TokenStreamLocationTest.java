package tools.jackson.core;

import org.junit.Test;
import java.lang.reflect.Field;
import tools.jackson.core.io.ContentReference;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TokenStreamLocationTest {

    /** Helper to create a mocked ContentReference. */
    private ContentReference mockContentRef(boolean textual) {
        ContentReference cr = mock(ContentReference.class);
        when(cr.hasTextualContent()).thenReturn(textual);
        when(cr.buildSourceDescription())
                .thenReturn(textual ? "textual" : "binary");
        return cr;
    }

    @Test
    public void testNAtoString() {
        assertEquals("[No location information]", TokenStreamLocation.NA.toString());
        // NA should be a singleton with special handling in equals
        assertTrue(TokenStreamLocation.NA.equals(TokenStreamLocation.NA));
        // Different instance cannot equal it
        ContentReference cr = ContentReference.unknown();
        TokenStreamLocation other = new TokenStreamLocation(cr, 0L, -1, -1);
        assertFalse(other.equals(TokenStreamLocation.NA));
    }

    @Test
    public void testConstructorNullContentRefUsesUnknown() {
        TokenStreamLocation loc = new TokenStreamLocation(null, 123L, 5, 10);
        assertNotNull(loc.contentReference());
        assertTrue(loc.contentReference().equals(ContentReference.unknown()));
    }

    @Test
    public void testGetters() {
        ContentReference cr = mockContentRef(true);
        TokenStreamLocation loc = new TokenStreamLocation(cr, 42L, 3, 7);
        assertEquals(3, loc.getLineNr());
        assertEquals(7, loc.getColumnNr());
        assertEquals(42L, loc.getCharOffset());
        // Byte offset is -1 for this constructor
        assertEquals(-1L, loc.getByteOffset());
    }

    @Test
    public void testSourceDescriptionLaziness() throws Exception {
        ContentReference cr = mockContentRef(true);
        TokenStreamLocation loc = new TokenStreamLocation(cr, 10L, 2, 4);

        // Before first call, internal field should be null
        Field descField = TokenStreamLocation.class.getDeclaredField("_sourceDescription");
        descField.setAccessible(true);
        assertNull(descField.get(loc));

        String first = loc.sourceDescription();
        assertNotNull(first);
        // After first call, internal field should be set
        assertNotNull(descField.get(loc));
        // Subsequent calls return the same string
        assertEquals(first, loc.sourceDescription());
    }

    @Test
    public void testOffsetDescriptionTextual() {
        ContentReference cr = mockContentRef(true);

        TokenStreamLocation loc = new TokenStreamLocation(cr, 100L, 5, 12);
        String desc = loc.appendOffsetDescription(new StringBuilder()).toString();
        assertTrue(desc.contains("line: 5"));
        assertTrue(desc.contains("column: 12"));

        // When line and column are negative
        TokenStreamLocation unknownLoc = new TokenStreamLocation(cr, 100L, -1, -1);
        String descUnknown = unknownLoc.appendOffsetDescription(new StringBuilder()).toString();
        assertTrue(descUnknown.contains("line: UNKNOWN"));
        assertTrue(descUnknown.contains("column: UNKNOWN"));
    }

    @Test
    public void testOffsetDescriptionBinary() {
        ContentReference cr = mockContentRef(false);

        // Binary path uses byte offset
        TokenStreamLocation loc = new TokenStreamLocation(cr, 200L, 50L, -1, -1);
        String desc = loc.appendOffsetDescription(new StringBuilder()).toString();
        assertTrue(desc.contains("byte offset: #200"));

        // When byte offset is negative
        TokenStreamLocation unknownLoc = new TokenStreamLocation(cr, -1L, -1L, -1, -1);
        String descUnknown = unknownLoc.appendOffsetDescription(new StringBuilder()).toString();
        assertTrue(descUnknown.contains("byte offset: #UNKNOWN"));
    }

    @Test
    public void testEqualsAndHashCode() {
        ContentReference cr1 = mockContentRef(true);
        TokenStreamLocation loc1 = new TokenStreamLocation(cr1, 10L, 2, 3);

        // Same reference and values
        TokenStreamLocation loc2 = new TokenStreamLocation(cr1, 10L, 2, 3);
        assertTrue(loc1.equals(loc2));
        assertEquals(loc1.hashCode(), loc2.hashCode());

        // Different line
        TokenStreamLocation locDiffLine = new TokenStreamLocation(cr1, 10L, 5, 3);
        assertFalse(loc1.equals(locDiffLine));

        // Different column
        TokenStreamLocation locDiffCol = new TokenStreamLocation(cr1, 10L, 2, 8);
        assertFalse(loc1.equals(locDiffCol));
    }

    @Test
    public void testToStringFormat() {
        ContentReference cr = mockContentRef(true);
        TokenStreamLocation loc = new TokenStreamLocation(cr, 999L, 4, 6);

        String toStr = loc.toString();
        assertTrue(toStr.startsWith("[Source: "));
        // Should contain description of source and offsets
        assertTrue(toStr.contains("line: 4"));
        assertTrue(toStr.contains("column: 6"));
        assertTrue(toStr.endsWith("]"));

        // NA special case already tested in testNAtoString
    }

    /* ------------------------------------------------------------------ */
    /* Additional focused tests to cover remaining branches                */
    /* ------------------------------------------------------------------ */

    @Test
    public void testOffsetDescriptionMixedTextual() {
        ContentReference cr = mockContentRef(true);

        // line non-negative, column negative
        TokenStreamLocation loc1 = new TokenStreamLocation(cr, 200L, 10, -1);
        String desc1 = loc1.appendOffsetDescription(new StringBuilder()).toString();
        assertTrue(desc1.contains("line: 10"));
        assertTrue(desc1.contains("column: UNKNOWN"));

        // line negative, column non-negative
        TokenStreamLocation loc2 = new TokenStreamLocation(cr, 200L, -1, 15);
        String desc2 = loc2.appendOffsetDescription(new StringBuilder()).toString();
        assertTrue(desc2.contains("line: UNKNOWN"));
        assertTrue(desc2.contains("column: 15"));
    }

    @Test
    public void testHashCodeWithNullContentRef() throws Exception {
        ContentReference cr = mockContentRef(true);
        TokenStreamLocation loc = new TokenStreamLocation(cr, 123L, 5, 6);

        int normalHash = loc.hashCode();

        // Force _contentReference to null via reflection
        Field contentField = TokenStreamLocation.class.getDeclaredField("_contentReference");
        contentField.setAccessible(true);
        contentField.set(loc, null);

        int nullHash = loc.hashCode();

        assertNotEquals(normalHash, nullHash);

        // Manually compute expected hash for null content reference (base=1)
        int expectedNullHash = computeExpectedHash(1, loc);
        assertEquals(expectedNullHash, nullHash);
    }

    @Test
    public void testEqualsSelfAndNull() {
        ContentReference cr = mockContentRef(true);
        TokenStreamLocation loc = new TokenStreamLocation(cr, 10L, 2, 3);

        // Self comparison
        assertTrue(loc.equals(loc));

        // Null comparison
        assertFalse(loc.equals(null));
    }

    @Test
    public void testEqualsDifferentContentRefs() {
        ContentReference cr1 = mock(ContentReference.class);
        ContentReference cr2 = mock(ContentReference.class);
        TokenStreamLocation loc1 = new TokenStreamLocation(cr1, 10L, 5, 6);
        TokenStreamLocation loc2 = new TokenStreamLocation(cr2, 10L, 5, 6);

        assertFalse(loc1.equals(loc2));
    }

    @Test
    public void testEqualsDifferentClass() {
        ContentReference cr = mockContentRef(true);
        TokenStreamLocation loc = new TokenStreamLocation(cr, 10L, 5, 6);
        Object other = new Object();
        assertFalse(loc.equals(other));
    }

    /* New tests for equals branches involving null content references */

    @Test
    public void testEqualsBothNullContentReferences() throws Exception {
        ContentReference cr = mockContentRef(true);
        TokenStreamLocation loc1 = new TokenStreamLocation(cr, 100L, 5, 7);
        TokenStreamLocation loc2 = new TokenStreamLocation(cr, 100L, 5, 7);

        Field refField = TokenStreamLocation.class.getDeclaredField("_contentReference");
        refField.setAccessible(true);
        // Set both to null
        refField.set(loc1, null);
        refField.set(loc2, null);

        assertTrue(loc1.equals(loc2));
    }

    @Test
    public void testEqualsOneNullContentReference() throws Exception {
        TokenStreamLocation locWithRef = new TokenStreamLocation(mockContentRef(true), 50L, 3,4);
        TokenStreamLocation locWithoutRef = new TokenStreamLocation(mockContentRef(true), 50L, 3,4);

        Field refField = TokenStreamLocation.class.getDeclaredField("_contentReference");
        refField.setAccessible(true);
        // Make the second location have null reference
        refField.set(locWithoutRef, null);

        assertFalse(locWithRef.equals(locWithoutRef));
    }

    /* Helper to compute expected hash based on the algorithm in TokenStreamLocation.hashCode() */
    private int computeExpectedHash(int base, TokenStreamLocation loc) {
        int hash = base;
        hash ^= loc.getLineNr();
        hash += loc.getColumnNr();
        hash ^= (int) loc.getCharOffset();
        hash += (int) loc.getByteOffset();
        return hash;
    }

    /* ---------- New focused tests to cover remaining branches ---------- */

    @Test
    public void testEqualsWithSameNonNullContentReference() {
        ContentReference cr = ContentReference.unknown(); // singleton, equals itself
        TokenStreamLocation loc1 = new TokenStreamLocation(cr, 50L, 3, 4);
        TokenStreamLocation loc2 = new TokenStreamLocation(cr, 50L, 3, 4);
        assertTrue(loc1.equals(loc2));
        assertEquals(loc1.hashCode(), loc2.hashCode());
    }

    @Test
    public void testHashCodeBaseTwoMatchesExpected() {
        ContentReference cr = ContentReference.unknown();
        TokenStreamLocation loc = new TokenStreamLocation(cr, 123L, 4, 5);
        int expected = computeExpectedHash(2, loc); // base should be 2 for non‑null reference
        assertEquals(expected, loc.hashCode());
    }

    @Test
    public void testBinaryToStringFormat() {
        ContentReference cr = mockContentRef(false); // binary content
        TokenStreamLocation loc = new TokenStreamLocation(cr, 200L, 50L, -1, -1);
        String toStr = loc.toString();
        assertTrue(toStr.startsWith("[Source: "));
        assertTrue(toStr.contains("byte offset: #200"));
        assertTrue(toStr.endsWith("]"));
    }

    /* NEW TESTS FOR SURVIVING MUTATIONS                               */

    @Test
    public void testAppendOffsetDescriptionExactTextual() {
        ContentReference cr = mockContentRef(true);
        TokenStreamLocation loc = new TokenStreamLocation(cr, 400L, 10, 20);
        String result = loc.appendOffsetDescription(new StringBuilder()).toString();
        assertEquals("line: 10, column: 20", result);
    }

    @Test
    public void testAppendOffsetDescriptionExactBinary() {
        ContentReference cr = mockContentRef(false);
        TokenStreamLocation loc = new TokenStreamLocation(cr, 30L, -1L, -1, -1);
        String result = loc.appendOffsetDescription(new StringBuilder()).toString();
        assertEquals("byte offset: #30", result);
    }

    @Test
    public void testSourceDescriptionExactValue() {
        ContentReference crText = mockContentRef(true);
        TokenStreamLocation locText = new TokenStreamLocation(crText, 100L, 2, 3);
        assertEquals("textual", locText.sourceDescription());

        ContentReference crBinary = mockContentRef(false);
        TokenStreamLocation locBinary = new TokenStreamLocation(crBinary, 200L, -1L, -1, -1);
        assertEquals("binary", locBinary.sourceDescription());
    }

    @Test
    public void testToStringExactFormatTextual() {
        ContentReference cr = mockContentRef(true);
        TokenStreamLocation loc = new TokenStreamLocation(cr, 999L, 4, 6);
        String expected = "[Source: textual; line: 4, column: 6]";
        assertEquals(expected, loc.toString());
    }

    @Test
    public void testToStringExactFormatBinary() {
        ContentReference cr = mockContentRef(false);
        TokenStreamLocation loc = new TokenStreamLocation(cr, 50L, -1L, -1, -1);
        String expected = "[Source: binary; byte offset: #50]";
        assertEquals(expected, loc.toString());
    }

    /* Additional boundary tests to kill conditional mutation survivors */

    @Test
    public void testAppendOffsetDescriptionBoundaryZeroTextual() {
        ContentReference cr = mockContentRef(true);
        TokenStreamLocation loc = new TokenStreamLocation(cr, 200L, 0, 0);
        String result = loc.appendOffsetDescription(new StringBuilder()).toString();
        assertEquals("line: 0, column: 0", result);
    }

    @Test
    public void testAppendOffsetDescriptionBoundaryZeroBinary() {
        ContentReference cr = mockContentRef(false);
        TokenStreamLocation loc = new TokenStreamLocation(cr, 0L, -1L, -1, -1);
        String result = loc.appendOffsetDescription(new StringBuilder()).toString();
        assertEquals("byte offset: #0", result);
    }

    @Test
    public void testToStringBoundaryZeroValuesTextual() {
        ContentReference cr = mockContentRef(true);
        TokenStreamLocation loc = new TokenStreamLocation(cr, 10L, 0, 0);
        String expected = "[Source: textual; line: 0, column: 0]";
        assertEquals(expected, loc.toString());
    }

    @Test
    public void testToStringBoundaryZeroValuesBinary() {
        ContentReference cr = mockContentRef(false);
        TokenStreamLocation loc = new TokenStreamLocation(cr, 0L, -1L, -1, -1);
        String expected = "[Source: binary; byte offset: #0]";
        assertEquals(expected, loc.toString());
    }

    @Test
    public void testOffsetDescriptionTextualUnknown() {
        ContentReference cr = mockContentRef(true);
        TokenStreamLocation loc = new TokenStreamLocation(cr, 5L, -1, -1);
        assertEquals("line: UNKNOWN, column: UNKNOWN", loc.offsetDescription());
    }

    @Test
    public void testOffsetDescriptionBinaryUnknown() {
        ContentReference cr = mockContentRef(false);
        // Byte offset is positive, so description should include that value
        TokenStreamLocation loc = new TokenStreamLocation(cr, 5L, -1L, -1, -1);
        assertEquals("byte offset: #5", loc.offsetDescription());
    }

    /* NEW TEST TO KILL ADDITIONALLY SURVIVING MUTATION IN toString */
    @Test
    public void testToStringWithLongSourceDescription() {
        ContentReference cr = mock(ContentReference.class);
        when(cr.hasTextualContent()).thenReturn(true);
        String longDesc = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFG";
        when(cr.buildSourceDescription()).thenReturn(longDesc);

        TokenStreamLocation loc = new TokenStreamLocation(cr, 10L, 1, 2);

        String toStr = loc.toString();
        assertEquals("[Source: " + longDesc + "; line: 1, column: 2]", toStr);
    }
}
