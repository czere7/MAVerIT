package org.apache.commons.cli;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests for {@link OptionBuilder}.
 */
public class OptionBuilderTest {

    /**
     * Reset the static state of {@link OptionBuilder} before each test.
     */
    @Before
    public void resetBuilder() {
        // Create a dummy option to reset the static fields.
        OptionBuilder.withLongOpt("reset").create('x');
    }

    @Test
    public void testCreateWithAllSettings() {
        Option option = OptionBuilder
                .withLongOpt("long")
                .withDescription("desc")
                .hasArg()
                .isRequired()
                .withType(Integer.class)
                .withValueSeparator('=')
                .withArgName("arg")
                .create('o');

        assertEquals("long", option.getLongOpt());
        assertEquals("desc", option.getDescription());
        assertTrue(option.isRequired());
        assertEquals("arg", option.getArgName());
        assertEquals(Integer.class, option.getType());
        assertEquals('=', option.getValueSeparator());
        assertTrue(option.hasArg());
        assertFalse(option.hasOptionalArg());
    }

    @Test(expected = IllegalStateException.class)
    public void testCreateWithoutLongOptThrows() {
        OptionBuilder.create();
    }

    @Test
    public void testHasArgFalseResetsArgCount() {
        Option option = OptionBuilder
                .hasArg(false)
                .withLongOpt("noarg")
                .create('n');

        assertFalse(option.hasArg());
        assertFalse(option.hasOptionalArg());
    }

    @Test
    public void testHasOptionalArgsWithCount() {
        Option option = OptionBuilder
                .hasOptionalArgs(3)
                .withLongOpt("opt3")
                .create('p');

        assertTrue(option.hasOptionalArg());
        assertTrue(option.hasArgs());
        assertTrue(option.hasArg()); // argCount > 0, so hasArg() should be true
    }

    @Test
    public void testHasOptionalArgsUnlimited() {
        Option option = OptionBuilder
                .hasOptionalArgs()
                .withLongOpt("unlim")
                .create('u');

        assertTrue(option.hasOptionalArg());
        assertTrue(option.hasArgs());
    }

    @Test
    public void testWithValueSeparatorChar() {
        Option option = OptionBuilder
                .withValueSeparator(':')
                .withLongOpt("sep")
                .create('s');

        assertEquals(':', option.getValueSeparator());
    }

    @Test
    public void testWithTypeObjectDeprecated() {
        Option option = OptionBuilder
                .withType((Object) Integer.class)
                .withLongOpt("obj")
                .create('o');

        assertEquals(Integer.class, option.getType());
    }

    @Test
    public void testCreateStringOption() {
        Option option = OptionBuilder
                .withLongOpt("long")
                .create("x");

        assertEquals("x", option.getOpt());
    }

    @Test
    public void testCreateCharOption() {
        Option option = OptionBuilder
                .withLongOpt("long")
                .create('c');

        assertEquals("c", option.getOpt());
    }

    @Test(expected = IllegalStateException.class)
    public void testBuilderResetAfterCreate() {
        // First creation resets the builder.
        OptionBuilder.withLongOpt("tmp").create('t');

        // Without setting longOpt again, this should throw.
        OptionBuilder.create();
    }

    /* --------------------------------------------------------------------- */
    /* Additional tests to increase branch coverage                           */
    /* --------------------------------------------------------------------- */

    @Test
    public void testHasOptionalArgMethod() {
        Option option = OptionBuilder
                .hasOptionalArg()
                .withLongOpt("opt")
                .create('o');

        assertTrue(option.hasOptionalArg());
        assertTrue(option.hasArg());
        assertFalse(option.hasArgs());
        assertNull(option.getArgName());
    }

    @Test
    public void testHasOptionalArgsZero() {
        Option option = OptionBuilder
                .hasOptionalArgs(0)
                .withLongOpt("opt")
                .create('o');

        assertTrue(option.hasOptionalArg());
        assertFalse(option.hasArg());
        assertFalse(option.hasArgs());
        assertNull(option.getArgName());
    }

    @Test
    public void testHasOptionalArgsUnlimitedWithHasArg() {
        Option option = OptionBuilder
                .hasOptionalArgs()
                .withLongOpt("opt")
                .create('o');

        assertTrue(option.hasOptionalArg());
        assertTrue(option.hasArgs());
        assertTrue(option.hasArg());
    }

    @Test
    public void testHasArgsUnlimited() {
        Option option = OptionBuilder
                .hasArgs()
                .withLongOpt("opt")
                .create('o');

        assertTrue(option.hasArgs());
        assertTrue(option.hasArg());
        assertFalse(option.hasOptionalArg());
    }

    @Test
    public void testHasArgsNumber() {
        Option option = OptionBuilder
                .hasArgs(2)
                .withLongOpt("opt")
                .create('o');

        assertTrue(option.hasArgs());
        assertTrue(option.hasArg());
        assertFalse(option.hasOptionalArg());
    }

    @Test
    public void testHasArgMethod() {
        Option option = OptionBuilder
                .hasArg()
                .withLongOpt("opt")
                .create('o');

        assertTrue(option.hasArg());
        assertFalse(option.hasOptionalArg());
    }

    @Test
    public void testIsRequiredFalse() {
        Option option = OptionBuilder
                .isRequired(false)
                .withLongOpt("opt")
                .create('o');

        assertFalse(option.isRequired());
    }

    @Test
    public void testWithValueSeparatorDefault() {
        Option option = OptionBuilder
                .withValueSeparator()
                .withLongOpt("opt")
                .create('o');

        assertEquals('=', option.getValueSeparator());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateWithEmptyOptThrows() {
        OptionBuilder.create("");
    }

    @Test
    public void testCreateWithNullOptReturnsNullOpt() {
        Option option = OptionBuilder.create((String) null);
        assertNotNull(option);
        assertNull(option.getOpt());
    }

    @Test
    public void testHasOptionalArgsZeroWithType() {
        Option option = OptionBuilder
                .hasOptionalArgs(0)
                .withType(Integer.class)
                .withLongOpt("opt")
                .create('o');

        assertTrue(option.hasOptionalArg());
        assertFalse(option.hasArg());
        assertFalse(option.hasArgs());
        assertEquals(Integer.class, option.getType());
    }

    @Test
    public void testHasOptionalArgsZeroWithValueSeparator() {
        Option option = OptionBuilder
                .hasOptionalArgs(0)
                .withValueSeparator(':')
                .withLongOpt("opt")
                .create('o');

        assertTrue(option.hasOptionalArg());
        assertFalse(option.hasArg());
        assertFalse(option.hasArgs());
        assertEquals(':', option.getValueSeparator());
    }

    @Test
    public void testConverterForInteger() throws Exception {
        Option option = OptionBuilder
                .withLongOpt("int")
                .withType(Integer.class)
                .create('i');

        assertNotNull(option.getConverter());
        Object result = option.getConverter().apply("123");
        assertTrue(result instanceof Integer);
        assertEquals(123, ((Integer) result).intValue());
    }

    @Test
    public void testCreateWithInvalidOptionReturnsOption() {
        Option option = OptionBuilder.create("ab");
        assertNotNull(option);
        assertEquals("ab", option.getOpt());
    }
}
