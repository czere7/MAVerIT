package tools.jackson.core.util;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.lang.reflect.Method;

import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.ArgumentCaptor;

import tools.jackson.core.JsonGenerator;
import tools.jackson.core.JacksonException;
import tools.jackson.core.SerializableString;
import tools.jackson.core.util.DefaultPrettyPrinter.Indenter;
import tools.jackson.core.util.Separators;
import tools.jackson.core.util.Separators.Spacing;

/**
 * Unit tests for {@link DefaultPrettyPrinter}.
 */
public class DefaultPrettyPrinterTest {

    /**
     * Simple subclass of {@link DefaultPrettyPrinter} used to test
     * {@code createInstance()} behaviour.
     */
    private static class SubPrettyPrinter extends DefaultPrettyPrinter {
        // no override of createInstance()
    }

    /* --------------------------------------------------------------------- */

    @Test
    public void testWriteObjectNameValueSeparator() throws Exception {
        DefaultPrettyPrinter pp = new DefaultPrettyPrinter();
        JsonGenerator g = mock(JsonGenerator.class);

        pp.writeObjectNameValueSeparator(g);
        verify(g).writeRaw(" : "); // updated to reflect default spacing
        verifyNoMoreInteractions(g);
    }

    /* --------------------------------------------------------------------- */

    @Test
    public void testStartObjectAndIndentation() throws Exception {
        DefaultPrettyPrinter pp = new DefaultPrettyPrinter();
        JsonGenerator g = mock(JsonGenerator.class);

        InOrder in = inOrder(g);
        pp.writeStartObject(g);          // should write '{'
        pp.beforeObjectEntries(g);       // indentation for level 1

        in.verify(g).writeRaw('{');
        in.verify(g).writeRaw(eq(System.lineSeparator()));

        ArgumentCaptor<char[]> captor = ArgumentCaptor.forClass(char[].class);
        in.verify(g).writeRaw(captor.capture(), anyInt(), eq(2));
        char[] actualIndent = captor.getValue();
        // The captured array may contain many spaces; we only care that
        // the first two characters are spaces.
        assertTrue(actualIndent.length >= 2);
        assertEquals(' ', actualIndent[0]);
        assertEquals(' ', actualIndent[1]);

        verifyNoMoreInteractions(g);
    }

    /* --------------------------------------------------------------------- */

    @Test
    public void testObjectEntrySeparatorWithIndentation() throws Exception {
        DefaultPrettyPrinter pp = new DefaultPrettyPrinter();
        JsonGenerator g = mock(JsonGenerator.class);

        InOrder in = inOrder(g);
        // Simulate writing a single entry: after name/value separator
        pp.writeStartObject(g);          // write '{'
        pp.beforeObjectEntries(g);       // indent
        pp.writeObjectNameValueSeparator(g);   // ':'
        pp.writeObjectEntrySeparator(g);        // ',' + indent

        in.verify(g).writeRaw('{');
        in.verify(g).writeRaw(eq(System.lineSeparator()));
        ArgumentCaptor<char[]> captor1 = ArgumentCaptor.forClass(char[].class);
        in.verify(g).writeRaw(captor1.capture(), anyInt(), eq(2));
        char[] firstIndent = captor1.getValue();
        assertTrue(firstIndent.length >= 2);
        assertEquals(' ', firstIndent[0]);
        assertEquals(' ', firstIndent[1]);

        in.verify(g).writeRaw(" : ");
        in.verify(g).writeRaw(","); // comma as String
        in.verify(g).writeRaw(eq(System.lineSeparator()));
        ArgumentCaptor<char[]> captor2 = ArgumentCaptor.forClass(char[].class);
        in.verify(g).writeRaw(captor2.capture(), anyInt(), eq(2));
        char[] secondIndent = captor2.getValue();
        assertTrue(secondIndent.length >= 2);
        assertEquals(' ', secondIndent[0]);
        assertEquals(' ', secondIndent[1]);

        verifyNoMoreInteractions(g);
    }

    /* --------------------------------------------------------------------- */

    @Test
    public void testWriteEndObjectEmpty() throws Exception {
        DefaultPrettyPrinter pp = new DefaultPrettyPrinter();
        JsonGenerator g = mock(JsonGenerator.class);

        InOrder in = inOrder(g);
        pp.writeStartObject(g);          // write '{'
        // No entries written
        pp.writeEndObject(g, 0);         // empty object

        in.verify(g).writeRaw('{');
        in.verify(g).writeRaw(" ");      // default _objectEmptySeparator is a single space
        in.verify(g).writeRaw('}');
        verifyNoMoreInteractions(g);
    }

    /* --------------------------------------------------------------------- */

    @Test
    public void testCreateInstanceBehaviour() {
        DefaultPrettyPrinter dp = new DefaultPrettyPrinter();
        DefaultPrettyPrinter copy = dp.createInstance();

        assertNotSame(dp, copy);

        SubPrettyPrinter sub = new SubPrettyPrinter();
        try {
            sub.createInstance();
            fail("Expected IllegalStateException for subclass");
        } catch (IllegalStateException e) {
            // expected
            assertTrue(e.getMessage().contains("Failed `createInstance()`"));
        }
    }

    /* --------------------------------------------------------------------- */

    @Test
    public void testWithSeparatorsChangesEntrySeparator() throws Exception {
        DefaultPrettyPrinter base = new DefaultPrettyPrinter();
        Separators custom = new Separators(':', ';', ','); // change entry separator to ';'
        DefaultPrettyPrinter pp = base.withSeparators(custom);

        JsonGenerator g = mock(JsonGenerator.class);
        InOrder in = inOrder(g);

        pp.writeObjectEntrySeparator(g);   // should write ';' + indent

        in.verify(g).writeRaw(";");
        in.verify(g).writeRaw(eq(System.lineSeparator()));
        // No indentation is expected when nesting level is 0
        verifyNoMoreInteractions(g);
    }

    /* --------------------------------------------------------------------- */

    @Test
    public void testIndentArraysWithCustomIndenter() throws Exception {
        DefaultPrettyPrinter pp = new DefaultPrettyPrinter();
        JsonGenerator g = mock(JsonGenerator.class);
        Indenter custom = mock(Indenter.class);
        when(custom.isInline()).thenReturn(false);

        pp.indentArraysWith(custom);

        // start array
        pp.writeStartArray(g);
        verify(g, times(1)).writeRaw('[');

        ArgumentCaptor<Integer> levelCap = ArgumentCaptor.forClass(Integer.class);
        pp.beforeArrayValues(g);
        verify(custom).writeIndentation(eq(g), levelCap.capture());
        assertEquals(1, levelCap.getValue().intValue());

        // end array with values
        InOrder in = inOrder(custom);
        pp.writeEndArray(g, 3); // >0 so should indent again at level 0
        in.verify(custom).writeIndentation(eq(g), eq(1)); // beforeArrayValues
        in.verify(custom).writeIndentation(eq(g), eq(0)); // after end
    }

    /* --------------------------------------------------------------------- */

    @Test
    public void testIndentObjectsWithCustomIndenter() throws Exception {
        DefaultPrettyPrinter pp = new DefaultPrettyPrinter();
        JsonGenerator g = mock(JsonGenerator.class);
        Indenter custom = mock(Indenter.class);
        when(custom.isInline()).thenReturn(false);

        pp.indentObjectsWith(custom);

        // start object
        pp.writeStartObject(g);
        verify(g, times(1)).writeRaw('{');

        ArgumentCaptor<Integer> levelCap = ArgumentCaptor.forClass(Integer.class);
        pp.beforeObjectEntries(g);
        verify(custom).writeIndentation(eq(g), levelCap.capture());
        assertEquals(1, levelCap.getValue().intValue());

        // end object with entries
        InOrder in = inOrder(custom);
        pp.writeEndObject(g, 2); // >0 so indentation after decrement
        in.verify(custom).writeIndentation(eq(g), eq(1)); // beforeObjectEntries
        in.verify(custom).writeIndentation(eq(g), eq(0)); // after end
    }

    /* --------------------------------------------------------------------- */

    @Test
    public void testWithArrayAndObjectIndenterCreateNewInstance() throws Exception {
        DefaultPrettyPrinter pp = new DefaultPrettyPrinter();
        JsonGenerator g = mock(JsonGenerator.class);
        Indenter arrayCustom = mock(Indenter.class);
        when(arrayCustom.isInline()).thenReturn(false);

        DefaultPrettyPrinter arrayPp = pp.withArrayIndenter(arrayCustom);
        assertNotSame(pp, arrayPp);

        // test array indentation
        InOrder inArr = inOrder(arrayCustom);
        arrayPp.writeStartArray(g);   // '['
        arrayPp.beforeArrayValues(g); // should indent
        inArr.verify(arrayCustom).writeIndentation(eq(g), eq(1));

        Indenter objectCustom = mock(Indenter.class);
        when(objectCustom.isInline()).thenReturn(false);

        DefaultPrettyPrinter objPp = pp.withObjectIndenter(objectCustom);
        assertNotSame(pp, objPp);

        InOrder inObj = inOrder(objectCustom);
        objPp.writeStartObject(g);   // '{'
        objPp.beforeObjectEntries(g); // should indent
        inObj.verify(objectCustom).writeIndentation(eq(g), eq(1));
    }

    /* --------------------------------------------------------------------- */

    @Test
    public void testWriteRootValueSeparatorWithCustomSeparators() throws Exception {
        DefaultPrettyPrinter pp = new DefaultPrettyPrinter();
        JsonGenerator gDefault = mock(JsonGenerator.class);

        // No separator by default (space)
        ArgumentCaptor<SerializableString> captorDefault = ArgumentCaptor.forClass(SerializableString.class);
        pp.writeRootValueSeparator(gDefault);
        verify(gDefault).writeRaw(captorDefault.capture());
        assertEquals(" ", captorDefault.getValue().getValue());
        verifyNoMoreInteractions(gDefault);

        // Create separators with a root separator '|'
        Separators sep = new Separators("|", ':', Spacing.BOTH, ',', Spacing.NONE,
                "oo", ',', Spacing.NONE, "aa");
        DefaultPrettyPrinter pp2 = pp.withSeparators(sep);

        JsonGenerator gCustom = mock(JsonGenerator.class);
        ArgumentCaptor<SerializableString> captorCustom = ArgumentCaptor.forClass(SerializableString.class);
        pp2.writeRootValueSeparator(gCustom);
        verify(gCustom).writeRaw(captorCustom.capture());
        assertEquals("|", captorCustom.getValue().getValue());
    }

    /* --------------------------------------------------------------------- */

    @Test
    public void testWithSpacesChangesObjectNameValueSeparator() throws Exception {
        DefaultPrettyPrinter base = new DefaultPrettyPrinter();
        JsonGenerator gTrue = mock(JsonGenerator.class);
        JsonGenerator gFalse = mock(JsonGenerator.class);

        Method m = DefaultPrettyPrinter.class.getDeclaredMethod("_withSpaces", boolean.class);
        m.setAccessible(true);
        DefaultPrettyPrinter pTrue = (DefaultPrettyPrinter) m.invoke(base, true);
        DefaultPrettyPrinter pFalse = (DefaultPrettyPrinter) m.invoke(base, false);

        // With spaces: should be " : "
        pTrue.writeObjectNameValueSeparator(gTrue);
        verify(gTrue).writeRaw(" : ");

        // Without spaces: should be ":"
        pFalse.writeObjectNameValueSeparator(gFalse);
        verify(gFalse).writeRaw(":");
    }
}
