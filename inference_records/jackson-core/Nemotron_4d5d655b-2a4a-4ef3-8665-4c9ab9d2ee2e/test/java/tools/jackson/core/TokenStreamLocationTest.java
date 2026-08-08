package tools.jackson.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotEquals;

import java.io.File;
import java.io.Serializable;
import java.net.URL;

import org.junit.Test;

import tools.jackson.core.io.ContentReference;

public class TokenStreamLocationTest {

    @Test
    public void testNAConstant() {
        assertNotNull(TokenStreamLocation.NA);
        assertEquals(-1L, TokenStreamLocation.NA.getByteOffset());
        assertEquals(-1L, TokenStreamLocation.NA.getCharOffset());
        assertEquals(-1, TokenStreamLocation.NA.getLineNr());
        assertEquals(-1, TokenStreamLocation.NA.getColumnNr());
        assertNotNull(TokenStreamLocation.NA.contentReference());
        assertEquals("[No location information]", TokenStreamLocation.NA.toString());
    }

    @Test
    public void testConstructorWithFourArgs() {
        ContentReference contentRef = ContentReference.unknown();
        long totalChars = 100L;
        int lineNr = 10;
        int colNr = 5;

        TokenStreamLocation loc = new TokenStreamLocation(contentRef, totalChars, lineNr, colNr);

        assertSame(contentRef, loc.contentReference());
        assertEquals(-1L, loc.getByteOffset());
        assertEquals(totalChars, loc.getCharOffset());
        assertEquals(lineNr, loc.getLineNr());
        assertEquals(colNr, loc.getColumnNr());
    }

    @Test
    public void testConstructorWithFiveArgs() {
        ContentReference contentRef = ContentReference.unknown();
        long totalBytes = 200L;
        long totalChars = 100L;
        int lineNr = 10;
        int colNr = 5;

        TokenStreamLocation loc = new TokenStreamLocation(contentRef, totalBytes, totalChars, lineNr, colNr);

        assertSame(contentRef, loc.contentReference());
        assertEquals(totalBytes, loc.getByteOffset());
        assertEquals(totalChars, loc.getCharOffset());
        assertEquals(lineNr, loc.getLineNr());
        assertEquals(colNr, loc.getColumnNr());
    }

    @Test
    public void testConstructorHandlesNullContentReference() {
        long totalChars = 100L;
        int lineNr = 10;
        int colNr = 5;

        TokenStreamLocation loc = new TokenStreamLocation(null, totalChars, lineNr, colNr);

        assertNotNull(loc.contentReference());
        assertEquals(ContentReference.unknown(), loc.contentReference());
    }

    @Test
    public void testConstructorFiveArgsHandlesNullContentReference() {
        long totalBytes = 200L;
        long totalChars = 100L;
        int lineNr = 10;
        int colNr = 5;

        TokenStreamLocation loc = new TokenStreamLocation(null, totalBytes, totalChars, lineNr, colNr);

        assertNotNull(loc.contentReference());
        assertEquals(ContentReference.unknown(), loc.contentReference());
    }

    @Test
    public void testContentReferenceAccessor() {
        ContentReference contentRef = ContentReference.unknown();
        TokenStreamLocation loc = new TokenStreamLocation(contentRef, 100L, 10, 5);

        assertSame(contentRef, loc.contentReference());
    }

    @Test
    public void testGetLineNr() {
        TokenStreamLocation loc = new TokenStreamLocation(ContentReference.unknown(), 100L, 42, 10);
        assertEquals(42, loc.getLineNr());
    }

    @Test
    public void testGetColumnNr() {
        TokenStreamLocation loc = new TokenStreamLocation(ContentReference.unknown(), 100L, 10, 42);
        assertEquals(42, loc.getColumnNr());
    }

    @Test
    public void testGetCharOffset() {
        TokenStreamLocation loc = new TokenStreamLocation(ContentReference.unknown(), 12345L, 10, 5);
        assertEquals(12345L, loc.getCharOffset());
    }

    @Test
    public void testGetByteOffset() {
        TokenStreamLocation loc = new TokenStreamLocation(ContentReference.unknown(), 54321L, 100L, 10, 5);
        assertEquals(54321L, loc.getByteOffset());
    }

    @Test
    public void testGetByteOffsetDefaultConstructor() {
        TokenStreamLocation loc = new TokenStreamLocation(ContentReference.unknown(), 100L, 10, 5);
        assertEquals(-1L, loc.getByteOffset());
    }

    @Test
    public void testSourceDescriptionForUnknownContentReference() {
        TokenStreamLocation loc = new TokenStreamLocation(ContentReference.unknown(), 100L, 10, 5);
        String desc = loc.sourceDescription();
        assertNotNull(desc);
    }

    @Test
    public void testSourceDescriptionCaching() {
        ContentReference contentRef = ContentReference.unknown();
        TokenStreamLocation loc = new TokenStreamLocation(contentRef, 100L, 10, 5);

        String desc1 = loc.sourceDescription();
        String desc2 = loc.sourceDescription();

        assertSame(desc1, desc2);
        assertNotNull(desc1);
    }

    @Test
    public void testOffsetDescriptionForUnknownContentReference() {
        TokenStreamLocation loc = new TokenStreamLocation(ContentReference.unknown(), 100L, 10, 5);
        String desc = loc.offsetDescription();
        assertNotNull(desc);
        assertTrue(desc.contains("byte offset: #UNKNOWN"));
        assertFalse(desc.contains("line:"));
        assertFalse(desc.contains("column:"));
    }

    @Test
    public void testOffsetDescriptionBinaryContentWithUnknownLine() {
        TokenStreamLocation loc = new TokenStreamLocation(ContentReference.unknown(), 100L, -1, 5);

        String desc = loc.offsetDescription();
        assertTrue(desc.contains("byte offset: #UNKNOWN"));
        assertFalse(desc.contains("line:"));
        assertFalse(desc.contains("column:"));
    }

    @Test
    public void testOffsetDescriptionBinaryContentWithUnknownColumn() {
        TokenStreamLocation loc = new TokenStreamLocation(ContentReference.unknown(), 100L, 10, -1);

        String desc = loc.offsetDescription();
        assertTrue(desc.contains("byte offset: #UNKNOWN"));
        assertFalse(desc.contains("line:"));
        assertFalse(desc.contains("column:"));
    }

    @Test
    public void testOffsetDescriptionBinaryContentBothUnknown() {
        TokenStreamLocation loc = new TokenStreamLocation(ContentReference.unknown(), 100L, -1, -1);

        String desc = loc.offsetDescription();
        assertTrue(desc.contains("byte offset: #UNKNOWN"));
        assertFalse(desc.contains("line:"));
        assertFalse(desc.contains("column:"));
    }

    @Test
    public void testOffsetDescriptionBinaryContent() {
        ContentReference contentRef = ContentReference.unknown();
        TokenStreamLocation loc = new TokenStreamLocation(contentRef, 500L, 100L, 10, 5);

        String desc = loc.offsetDescription();
        assertTrue(desc.contains("byte offset: #500"));
        assertFalse(desc.contains("line:"));
        assertFalse(desc.contains("column:"));
    }

    @Test
    public void testOffsetDescriptionBinaryContentUnknownBytes() {
        ContentReference contentRef = ContentReference.unknown();
        TokenStreamLocation loc = new TokenStreamLocation(contentRef, -1L, 100L, 10, 5);

        String desc = loc.offsetDescription();
        assertTrue(desc.contains("byte offset: #UNKNOWN"));
    }

    @Test
    public void testAppendOffsetDescriptionBinaryFromTextualConstructor() {
        TokenStreamLocation loc = new TokenStreamLocation(ContentReference.unknown(), 100L, 10, 5);

        StringBuilder sb = new StringBuilder("prefix: ");
        loc.appendOffsetDescription(sb);

        String result = sb.toString();
        assertTrue(result.startsWith("prefix: "));
        assertTrue(result.contains("byte offset: #UNKNOWN"));
        assertFalse(result.contains("line:"));
        assertFalse(result.contains("column:"));
    }

    @Test
    public void testAppendOffsetDescriptionBinary() {
        ContentReference contentRef = ContentReference.unknown();
        TokenStreamLocation loc = new TokenStreamLocation(contentRef, 500L, 100L, 10, 5);

        StringBuilder sb = new StringBuilder("prefix: ");
        loc.appendOffsetDescription(sb);

        String result = sb.toString();
        assertTrue(result.startsWith("prefix: "));
        assertTrue(result.contains("byte offset: #500"));
    }

    @Test
    public void testAppendOffsetDescriptionReturnsSameBuilder() {
        TokenStreamLocation loc = new TokenStreamLocation(ContentReference.unknown(), 100L, 10, 5);

        StringBuilder sb = new StringBuilder();
        StringBuilder result = loc.appendOffsetDescription(sb);

        assertSame(sb, result);
    }

    @Test
    public void testEqualsSameObject() {
        TokenStreamLocation loc = new TokenStreamLocation(ContentReference.unknown(), 100L, 10, 5);
        assertTrue(loc.equals(loc));
    }

    @Test
    public void testEqualsNull() {
        TokenStreamLocation loc = new TokenStreamLocation(ContentReference.unknown(), 100L, 10, 5);
        assertFalse(loc.equals(null));
    }

    @Test
    public void testEqualsDifferentClass() {
        TokenStreamLocation loc = new TokenStreamLocation(ContentReference.unknown(), 100L, 10, 5);
        assertFalse(loc.equals("not a location"));
    }

    @Test
    public void testEqualsEqualLocations() {
        ContentReference contentRef = ContentReference.unknown();
        TokenStreamLocation loc1 = new TokenStreamLocation(contentRef, 200L, 100L, 10, 5);
        TokenStreamLocation loc2 = new TokenStreamLocation(contentRef, 200L, 100L, 10, 5);

        assertTrue(loc1.equals(loc2));
        assertTrue(loc2.equals(loc1));
    }

    @Test
    public void testEqualsDifferentLineNr() {
        ContentReference contentRef = ContentReference.unknown();
        TokenStreamLocation loc1 = new TokenStreamLocation(contentRef, 200L, 100L, 10, 5);
        TokenStreamLocation loc2 = new TokenStreamLocation(contentRef, 200L, 100L, 11, 5);

        assertFalse(loc1.equals(loc2));
    }

    @Test
    public void testEqualsDifferentColumnNr() {
        ContentReference contentRef = ContentReference.unknown();
        TokenStreamLocation loc1 = new TokenStreamLocation(contentRef, 200L, 100L, 10, 5);
        TokenStreamLocation loc2 = new TokenStreamLocation(contentRef, 200L, 100L, 10, 6);

        assertFalse(loc1.equals(loc2));
    }

    @Test
    public void testEqualsDifferentCharOffset() {
        ContentReference contentRef = ContentReference.unknown();
        TokenStreamLocation loc1 = new TokenStreamLocation(contentRef, 200L, 100L, 10, 5);
        TokenStreamLocation loc2 = new TokenStreamLocation(contentRef, 200L, 101L, 10, 5);

        assertFalse(loc1.equals(loc2));
    }

    @Test
    public void testEqualsDifferentByteOffset() {
        ContentReference contentRef = ContentReference.unknown();
        TokenStreamLocation loc1 = new TokenStreamLocation(contentRef, 200L, 100L, 10, 5);
        TokenStreamLocation loc2 = new TokenStreamLocation(contentRef, 201L, 100L, 10, 5);

        assertFalse(loc1.equals(loc2));
    }

    @Test
    public void testEqualsWithNullContentReference() {
        TokenStreamLocation loc1 = new TokenStreamLocation(null, 100L, 10, 5);
        TokenStreamLocation loc2 = new TokenStreamLocation(null, 100L, 10, 5);

        assertTrue(loc1.equals(loc2));
    }

    @Test
    public void testEqualsWithOneNullContentReference() {
        ContentReference contentRef = ContentReference.unknown();
        TokenStreamLocation loc1 = new TokenStreamLocation(null, 100L, 10, 5);
        TokenStreamLocation loc2 = new TokenStreamLocation(contentRef, 100L, 10, 5);

        assertTrue(loc1.equals(loc2));
    }

    @Test
    public void testHashCodeConsistency() {
        TokenStreamLocation loc = new TokenStreamLocation(ContentReference.unknown(), 200L, 100L, 10, 5);

        int hash1 = loc.hashCode();
        int hash2 = loc.hashCode();

        assertEquals(hash1, hash2);
    }

    @Test
    public void testHashCodeEqualObjectsHaveEqualHashCodes() {
        ContentReference contentRef = ContentReference.unknown();
        TokenStreamLocation loc1 = new TokenStreamLocation(contentRef, 200L, 100L, 10, 5);
        TokenStreamLocation loc2 = new TokenStreamLocation(contentRef, 200L, 100L, 10, 5);

        assertTrue(loc1.equals(loc2));
        assertEquals(loc1.hashCode(), loc2.hashCode());
    }

    @Test
    public void testToStringNA() {
        assertEquals("[No location information]", TokenStreamLocation.NA.toString());
    }

    @Test
    public void testToStringWithUnknownContentReference() {
        TokenStreamLocation loc = new TokenStreamLocation(ContentReference.unknown(), 100L, 10, 5);

        String str = loc.toString();
        assertTrue(str.startsWith("[Source: "));
        assertTrue(str.contains("byte offset: #UNKNOWN"));
        assertFalse(str.contains("line:"));
        assertFalse(str.contains("column:"));
        assertTrue(str.endsWith("]"));
    }

    @Test
    public void testToStringBinaryContent() {
        ContentReference contentRef = ContentReference.unknown();
        TokenStreamLocation loc = new TokenStreamLocation(contentRef, 500L, 100L, 10, 5);

        String str = loc.toString();
        assertTrue(str.startsWith("[Source: "));
        assertTrue(str.contains("byte offset: #500"));
        assertTrue(str.endsWith("]"));
    }

    @Test
    public void testToStringWithStringBuilder() {
        TokenStreamLocation loc = new TokenStreamLocation(ContentReference.unknown(), 100L, 10, 5);

        StringBuilder sb = new StringBuilder("prefix: ");
        StringBuilder result = loc.toString(sb);

        assertSame(sb, result);
        String str = sb.toString();
        assertTrue(str.startsWith("prefix: [Source: "));
        assertTrue(str.contains("byte offset: #UNKNOWN"));
        assertFalse(str.contains("line:"));
        assertFalse(str.contains("column:"));
        assertTrue(str.endsWith("]"));
    }

    @Test
    public void testToStringWithStringBuilderNA() {
        StringBuilder sb = new StringBuilder("prefix: ");
        StringBuilder result = TokenStreamLocation.NA.toString(sb);

        assertSame(sb, result);
        assertEquals("prefix: [No location information]", sb.toString());
    }

    @Test
    public void testImplementsSerializable() {
        TokenStreamLocation loc = new TokenStreamLocation(ContentReference.unknown(), 100L, 10, 5);
        assertTrue(loc instanceof Serializable);
    }

    @Test
    public void testContentReferenceHasTextualContent() {
        ContentReference unknownRef = ContentReference.unknown();

        TokenStreamLocation textualLoc = new TokenStreamLocation(unknownRef, 100L, 10, 5);
        TokenStreamLocation binaryLoc = new TokenStreamLocation(unknownRef, 500L, 100L, 10, 5);

        assertFalse(textualLoc.contentReference().hasTextualContent());
        assertFalse(binaryLoc.contentReference().hasTextualContent());
    }

    @Test
    public void testOffsetDescriptionWithZeroValues() {
        TokenStreamLocation loc = new TokenStreamLocation(ContentReference.unknown(), 0L, 0, 0);

        String desc = loc.offsetDescription();
        assertTrue(desc.contains("byte offset: #UNKNOWN"));
        assertFalse(desc.contains("line:"));
        assertFalse(desc.contains("column:"));
    }

    @Test
    public void testNAHasUnknownContentReference() {
        assertSame(ContentReference.unknown(), TokenStreamLocation.NA.contentReference());
    }
}
