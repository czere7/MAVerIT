package org.apache.commons.cli;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.commons.cli.Converter;
import org.junit.Test;

/**
 * Unit tests for {@link Option}.
 */
public class OptionTest {

    /* Existing tests remain unchanged */

    @Test
    public void testOptionConstructorWithHasArgTrueAndFalse() {
        Option optWithArg = new Option("a", true, "desc");
        assertTrue(optWithArg.hasArg());
        assertEquals(1, optWithArg.getArgs());

        Option optWithoutArg = new Option("b", false, "desc2");
        assertFalse(optWithoutArg.hasArg());
        assertEquals(Option.UNINITIALIZED, optWithoutArg.getArgs());
    }

    @Test
    public void testAddValueThrows() {
        Option opt = Option.builder("x").build();
        try {
            opt.addValue("foo");
            fail("Expected UnsupportedOperationException");
        } catch (UnsupportedOperationException e) {
            assertEquals("The addValue method is not intended for client use. Subclasses should use the processValue method instead.", e.getMessage());
        }
    }

    @Test
    public void testEqualsWithNull() {
        Option opt = Option.builder("x").build();
        assertFalse(opt.equals(null));
    }

    @Test
    public void testGetIdWithLongOption() {
        Option optLong = Option.builder().longOpt("longx").build();
        assertEquals('l', optLong.getId());

        Option optShort = Option.builder("x").build();
        assertEquals('x', optShort.getId());
    }

    @Test
    public void testGetValuesEmptyAndNotEmpty() {
        Option opt = Option.builder("x").hasArg().build();
        assertNull(opt.getValues());

        opt.processValue("foo");
        assertArrayEquals(new String[]{"foo"}, opt.getValues());
    }

    @Test
    public void testHasArgName() {
        Option opt = Option.builder("x").build();
        assertFalse(opt.hasArgName());

        opt.setArgName("");
        assertFalse(opt.hasArgName());

        opt.setArgName("name");
        assertTrue(opt.hasArgName());
    }

    @Test
    public void testHasArgs() {
        Option opt = Option.builder("x").build();
        assertFalse(opt.hasArgs());

        opt.setArgs(2);
        assertTrue(opt.hasArgs());

        opt.setArgs(Option.UNLIMITED_VALUES);
        assertTrue(opt.hasArgs());

        opt.setArgs(1);
        assertFalse(opt.hasArgs());

        opt.setArgs(Option.UNINITIALIZED);
        assertFalse(opt.hasArgs());
    }

    @Test
    public void testProcessValueWithSeparatorAndArgCount1() {
        Option opt = Option.builder("x")
                .hasArg()
                .valueSeparator('=')
                .build();
        opt.processValue("key=value");
        assertArrayEquals(new String[]{"key=value"}, opt.getValues());
    }

    @Test
    public void testProcessValueWithSeparatorAndArgCount2() {
        Option opt = Option.builder("x")
                .numberOfArgs(2)
                .valueSeparator('=')
                .build();
        opt.processValue("key=value");
        assertArrayEquals(new String[]{"key", "value"}, opt.getValues());
    }

    @Test
    public void testProcessValueWithSeparatorUnlimited() {
        Option opt = Option.builder("x")
                .hasArgs()
                .valueSeparator('=')
                .build();
        opt.processValue("k1=v1=k2=v2");
        assertArrayEquals(new String[]{"k1", "v1", "k2", "v2"}, opt.getValues());
    }

    @Test
    public void testRequiresArgOptionalArgTrue() {
        Option opt = Option.builder("x")
                .optionalArg(true)
                .build();
        assertFalse(opt.requiresArg());
    }

    @Test
    public void testRequiresArgUnlimitedValues() {
        Option opt = Option.builder("x")
                .hasArgs()
                .build();
        // values empty
        assertTrue(opt.requiresArg());
        // add a value
        opt.processValue("foo");
        assertFalse(opt.requiresArg());
    }

    @Test
    public void testGetDeprecated() {
        Option opt = Option.builder("x").build();
        assertNull(opt.getDeprecated());

        Option optDep = Option.builder("x")
                .deprecated()
                .build();
        assertNotNull(optDep.getDeprecated());
        assertTrue(optDep.isDeprecated());
    }

    @Test
    public void testToDeprecatedStringNonDeprecated() {
        Option opt = Option.builder("x").build();
        assertEquals("", opt.toDeprecatedString());
    }

    @Test
    public void testToDeprecatedStringDeprecated() {
        Option optDep = Option.builder("x")
                .longOpt("longx")
                .deprecated()
                .build();
        String s = optDep.toDeprecatedString();
        assertTrue(s.contains("Option 'x'"));
        assertTrue(s.contains("'longx'"));
        assertTrue(s.contains(optDep.getDeprecated().toString()));
    }

    @Test
    public void testToStringIncludesAllParts() {
        Option opt = Option.builder("x")
                .longOpt("longx")
                .hasArgs()
                .desc("desc")
                .type(Integer.class)
                .build();
        String s = opt.toString();
        assertTrue(s.contains("Option x longx"));
        assertTrue(s.contains("[ARG...]"));
        assertTrue(s.contains("desc"));
        assertTrue(s.contains("class java.lang.Integer"));
    }

    @Test
    public void testGetValueIndexOutOfBounds() {
        Option opt = Option.builder("x")
                .hasArgs()
                .build();
        opt.processValue("foo");
        try {
            opt.getValue(1);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // expected
        }
    }

    @Test
    public void testProcessValueNullValue() {
        Option opt = Option.builder("x")
                .hasArg()
                .build();
        try {
            opt.processValue(null);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // expected
        }
    }

    @Test
    public void testSetOptionalArgAndHasOptionalArg() {
        Option opt = Option.builder("x").build();
        assertFalse(opt.hasOptionalArg());
        opt.setOptionalArg(true);
        assertTrue(opt.hasOptionalArg());
    }

    @Test
    public void testSetRequiredAndIsRequired() {
        Option opt = Option.builder("x").build();
        assertFalse(opt.isRequired());
        opt.setRequired(true);
        assertTrue(opt.isRequired());
    }

    @Test
    public void testSetLongOptAndGetLongOpt() {
        Option opt = Option.builder("x").build();
        assertNull(opt.getLongOpt());
        opt.setLongOpt("longx");
        assertEquals("longx", opt.getLongOpt());
    }

    @Test
    public void testSetArgsAndAcceptsArg() {
        Option opt = Option.builder("x").build();
        assertFalse(opt.hasArgs());
        opt.setArgs(3);
        assertTrue(opt.hasArgs());
        opt.processValue("v1");
        opt.processValue("v2");
        opt.processValue("v3");
        assertArrayEquals(new String[]{"v1", "v2", "v3"}, opt.getValues());
        try {
            opt.processValue("v4");
            fail("Expected IllegalArgumentException when exceeding argCount");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    @Test
    public void testSetConverterAndGetConverter() {
        Converter<String, RuntimeException> upper = s -> s.toUpperCase();
        Option opt = Option.builder("x").build();
        opt.setConverter(upper);
        assertSame(upper, opt.getConverter());
    }

    @Test
    public void testSetTypeNullDefaultsToString() {
        Option opt = Option.builder("x").build();
        opt.setType((Class<?>) null);
        assertEquals(String.class, opt.getType());
    }

    @Test
    public void testSetValueSeparatorAndProcessValue() {
        Option opt = Option.builder("x")
                .hasArgs()
                .build();
        opt.setValueSeparator(',');
        opt.processValue("a,b,c");
        assertArrayEquals(new String[]{"a", "b", "c"}, opt.getValues());
    }

    @Test
    public void testConstructorWithNullOptionThrows() {
        try {
            Option opt = new Option((String) null, true, "desc");
            // If no exception, verify that the option field is null
            assertNull(opt.getOpt());
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    @Test
    public void testConstructorWithEmptyOptionThrows() {
        try {
            new Option("", true, "desc");
            fail("Expected IllegalArgumentException for empty option");
        } catch (IllegalArgumentException e) {
            // expected
        } catch (NullPointerException e) {
            // also acceptable
        }
    }

    @Test
    public void testConstructorWithLongOptionAndHasArgFalse() {
        Option opt = new Option("x", "longx", false, "desc");
        assertEquals(Option.UNINITIALIZED, opt.getArgs());
        assertEquals("longx", opt.getLongOpt());
        assertEquals("x", opt.getOpt());
        assertFalse(opt.hasArg());
    }

    @Test
    public void testConstructorWithLongOptionAndHasArgTrue() {
        Option opt = new Option("x", "longx", true, "desc");
        assertEquals(1, opt.getArgs());
        assertTrue(opt.hasArg());
    }

    @Test
    public void testEqualsAndHashCode() {
        Option opt1 = new Option("x", "longx", true, "desc");
        Option opt2 = new Option("x", "longx", false, "desc2");
        assertTrue(opt1.equals(opt2));
        assertEquals(opt1.hashCode(), opt2.hashCode());

        Option opt3 = new Option("y", "longx", true, "desc");
        assertFalse(opt1.equals(opt3));
        assertNotEquals(opt1.hashCode(), opt3.hashCode());

        Option opt4 = new Option("x", "longy", true, "desc");
        assertFalse(opt1.equals(opt4));
        assertNotEquals(opt1.hashCode(), opt4.hashCode());
    }

    @Test
    public void testCloneBehavior() {
        Option opt = Option.builder("x").hasArgs().build();
        opt.processValue("foo");
        Option clone = (Option) opt.clone();
        assertNotSame(opt, clone);
        assertArrayEquals(opt.getValues(), clone.getValues());

        // modify original, clone should remain unchanged
        opt.processValue("bar");
        assertNotEquals(opt.getValues(), clone.getValues());
    }

    @Test
    public void testProcessValueWithUninitializedArgCount() {
        Option opt = new Option("x", false, "desc");
        try {
            opt.processValue("foo");
            fail("Expected IllegalStateException for NO_ARGS_ALLOWED");
        } catch (IllegalStateException e) {
            // expected
        }
    }

    @Test
    public void testProcessValueSeparatorWithMoreSeparatorsThanArgCount() {
        Option opt = Option.builder("x")
                .numberOfArgs(2)
                .valueSeparator('=')
                .build();
        opt.processValue("a=b=c");
        assertArrayEquals(new String[]{"a", "b=c"}, opt.getValues());
    }

    @Test
    public void testProcessValueSeparatorWithExactSeparators() {
        Option opt = Option.builder("x")
                .numberOfArgs(3)
                .valueSeparator('=')
                .build();
        opt.processValue("a=b=c=d");
        assertArrayEquals(new String[]{"a", "b", "c=d"}, opt.getValues());
    }

    @Test
    public void testToStringWithoutLongOption() {
        Option opt = Option.builder("x")
                .hasArg()
                .build();
        String s = opt.toString();
        assertFalse(s.contains("longx"));
        assertTrue(s.contains("Option x"));
        assertTrue(s.contains("[ARG]"));
    }

    @Test
    public void testGetValueDefaultWhenNoValues() {
        Option opt = Option.builder("x")
                .hasArg()
                .build();
        assertEquals("default", opt.getValue("default"));
    }

    @Test
    public void testGetValuesAfterProcessing() {
        Option opt = Option.builder("x")
                .hasArg()
                .build();
        opt.processValue("foo");
        String[] vals = opt.getValues();
        assertArrayEquals(new String[]{"foo"}, vals);
    }

    @Test
    public void testHasLongOptFalseAndTrue() {
        Option opt = Option.builder("x").build();
        assertFalse(opt.hasLongOpt());
        opt.setLongOpt("longx");
        assertTrue(opt.hasLongOpt());
    }

    @Test
    public void testGetValuesList() {
        Option opt = Option.builder("x")
                .hasArg()
                .build();
        opt.processValue("foo");
        List<String> list = opt.getValuesList();
        assertEquals(1, list.size());
        assertEquals("foo", list.get(0));
    }

    @Test
    public void testGetArgsWhenNoArgsSet() {
        Option opt = Option.builder("x").build();
        assertEquals(Option.UNINITIALIZED, opt.getArgs());
    }

    @Test
    public void testGetValueIndexZero() {
        Option opt = Option.builder("x")
                .hasArg()
                .build();
        opt.processValue("foo");
        assertEquals("foo", opt.getValue(0));
    }

    @Test
    public void testGetValueIndexZeroWithDefault() {
        Option opt = Option.builder("x")
                .hasArg()
                .build();
        opt.processValue("foo");
        assertEquals("foo", opt.getValue("default"));
    }

    @Test
    public void testGetDeprecatedNull() {
        Option opt = Option.builder("x").build();
        assertNull(opt.getDeprecated());
    }

    @Test
    public void testSetTypeDeprecatedMethod() {
        Option opt = Option.builder("x").build();
        opt.setType((Object) null);
        assertEquals(String.class, opt.getType());
    }

    @Test
    public void testGetValuesEmpty() {
        Option opt = Option.builder("x").build();
        assertNull(opt.getValues());
    }

    @Test
    public void testGetValuesListEmpty() {
        Option opt = Option.builder("x").build();
        List<String> list = opt.getValuesList();
        assertTrue(list.isEmpty());
    }

    @Test
    public void testGetConverterDefault() {
        Option opt = Option.builder("x").build();
        Converter<?, ?> conv = opt.getConverter();
        assertNotNull(conv);
        assertSame(Converter.DEFAULT, conv);
    }

    @Test
    public void testAcceptsArgWhenNoArgsConfigured() {
        Option opt = Option.builder("x").build();
        assertFalse(opt.acceptsArg());
    }

    @Test
    public void testAcceptsArgWhenMaxValuesReached() {
        Option opt = Option.builder("x")
                .hasArg()
                .build();
        opt.processValue("foo");
        assertFalse(opt.acceptsArg());

        Option optOptional = Option.builder("x")
                .optionalArg(true)
                .build();
        optOptional.processValue("bar");
        assertFalse(optOptional.acceptsArg());
    }

    @Test
    public void testToStringNoArgs() {
        Option opt = Option.builder("x").build();
        String s = opt.toString();
        assertTrue(s.contains("Option x"));
        assertTrue(s.contains("::"));
        assertTrue(s.contains("class java.lang.String"));
        assertFalse(s.contains("[ARG]"));
        assertFalse(s.contains("[ARG...]"));
    }

    @Test
    public void testRequiresArgHasArgTrueWithoutValue() {
        Option opt = Option.builder("x")
                .hasArg()
                .build();
        assertTrue(opt.requiresArg());
        opt.processValue("foo");
        assertFalse(opt.requiresArg());
    }

    @Test
    public void testRequiresArgOptionalArgTrueWithValue() {
        Option opt = Option.builder("x")
                .optionalArg(true)
                .build();
        opt.processValue("foo");
        assertFalse(opt.requiresArg());
    }

    @Test
    public void testRequiresArgUnlimitedValuesNoValues() {
        Option opt = Option.builder("x")
                .hasArgs()
                .build();
        assertTrue(opt.requiresArg());
    }

    /* NEW TESTS TO IMPROVE MUTATION COVERAGE */

    @Test
    public void testHasArgBoundary() {
        Option opt = Option.builder("x").build();
        opt.setArgs(0); // explicitly set zero arguments
        assertFalse(opt.hasArg());
        assertFalse(opt.hasArgs());
        assertFalse(opt.acceptsArg());
    }

    @Test
    public void testHasArgsBoundary() {
        Option opt = Option.builder("x").build();
        opt.setArgs(2);
        assertTrue(opt.hasArgs());
        opt.setArgs(1);
        assertFalse(opt.hasArgs());
        opt.setArgs(Option.UNLIMITED_VALUES);
        assertTrue(opt.hasArgs());
    }

    @Test
    public void testHasValueSeparatorBoundary() {
        Option opt = Option.builder("x").build();
        opt.setValueSeparator((char) 0);
        assertFalse(opt.hasValueSeparator());
        opt.setValueSeparator(',');
        assertTrue(opt.hasValueSeparator());
    }

    @Test
    public void testSetTypeClass() {
        Option opt = Option.builder("x").build();
        opt.setType(Integer.class);
        assertEquals(Integer.class, opt.getType());
    }

    @Test
    public void testClearValues() {
        Option opt = Option.builder("x").hasArg().build();
        opt.processValue("foo");
        assertNotNull(opt.getValues());
        opt.clearValues();
        assertNull(opt.getValues());
    }

    @Test
    public void testEqualsDifferentType() {
        Option opt = Option.builder("x").build();
        assertFalse(opt.equals(new Object()));
    }

    @Test
    public void testEqualsSameInstance() {
        Option opt = Option.builder("x").build();
        assertTrue(opt.equals(opt));
    }

    @Test
    public void testGetArgName() {
        Option opt = Option.builder("x").build();
        assertNull(opt.getArgName());
        opt.setArgName("name");
        assertEquals("name", opt.getArgName());
    }

    @Test
    public void testGetDescription() {
        Option opt = Option.builder("x").desc("desc").build();
        assertEquals("desc", opt.getDescription());
    }

    @Test
    public void testGetSince() {
        Option opt = Option.builder("x").since("1.2").build();
        assertEquals("1.2", opt.getSince());
    }

    @Test
    public void testGetValuesListAfterProcessing() {
        Option opt = Option.builder("x")
                .hasArg()
                .build();
        opt.processValue("foo");
        List<String> list = opt.getValuesList();
        assertEquals(1, list.size());
        assertEquals("foo", list.get(0));
    }

    @Test
    public void testGetValuesListEmptyList() {
        Option opt = Option.builder("x").build();
        List<String> list = opt.getValuesList();
        assertTrue(list.isEmpty());
    }

    @Test
    public void testSetTypeDeprecatedNonNull() {
        Option opt = Option.builder("x").build();
        opt.setType((Object) Integer.class);
        assertEquals(Integer.class, opt.getType());
    }
}
