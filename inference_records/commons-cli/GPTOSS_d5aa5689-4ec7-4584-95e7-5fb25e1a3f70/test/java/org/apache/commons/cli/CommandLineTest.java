package org.apache.commons.cli;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

/**
 * Test suite for {@link CommandLine}.
 */
public class CommandLineTest {

    @Test
    public void testAddAndRetrieveOptionValues() {
        Option opt = new Option("a", true, "desc");
        opt.getValuesList().add("123");

        CommandLine cl = CommandLine.builder()
                .addOption(opt)
                .build();

        assertEquals("123", cl.getOptionValue("a"));
        assertArrayEquals(new String[]{"123"}, cl.getOptionValues("a"));
        assertEquals(1, cl.getOptionCount("a"));
        assertTrue(cl.hasOption("a"));
    }

    @Test
    public void testOptionCountWithMultipleInstances() {
        Option opt1 = new Option("b", true, "desc");
        opt1.getValuesList().add("x");

        Option opt2 = new Option("b", true, "desc");
        opt2.getValuesList().add("y");

        CommandLine cl = CommandLine.builder()
                .addOption(opt1)
                .addOption(opt2)
                .build();

        assertEquals(2, cl.getOptionCount("b"));
    }

    @Test
    public void testGetOptionPropertiesEvenAndOddValues() {
        Option opt = new Option("D", true, "desc");
        opt.getValuesList().addAll(Arrays.asList("param1", "value1", "param2", "value2"));

        CommandLine cl = CommandLine.builder()
                .addOption(opt)
                .build();

        Properties props = cl.getOptionProperties(opt);
        assertEquals("value1", props.getProperty("param1"));
        assertEquals("value2", props.getProperty("param2"));
    }

    @Test
    public void testGetOptionPropertiesWithOddValues() {
        Option opt = new Option("D", true, "desc");
        opt.getValuesList().add("flag");

        CommandLine cl = CommandLine.builder()
                .addOption(opt)
                .build();

        Properties props = cl.getOptionProperties(opt);
        assertEquals("true", props.getProperty("flag"));
    }

    @Test
    public void testParsedOptionValueWithInteger() throws ParseException {
        Option opt = Option.builder("n").type(Integer.class).build();
        opt.getValuesList().add("42");

        CommandLine cl = CommandLine.builder()
                .addOption(opt)
                .build();

        Integer value = cl.getParsedOptionValue(opt);
        assertEquals(Integer.valueOf(42), value);
    }

    @Test
    public void testParsedOptionValueWithDefaultSupplier() throws ParseException {
        Option opt = Option.builder("m").type(Integer.class).build();
        opt.getValuesList().add("100");

        CommandLine cl = CommandLine.builder()
                .addOption(opt)
                .build();

        Integer value = cl.getParsedOptionValue(opt, () -> 0);
        assertEquals(Integer.valueOf(100), value);

        // when option not present, default supplier is used
        CommandLine clEmpty = CommandLine.builder().build();
        Integer defaultVal = clEmpty.getParsedOptionValue(opt, () -> 0);
        assertEquals(Integer.valueOf(0), defaultVal);
    }

    @Test
    public void testParsedOptionValuesArray() throws ParseException {
        Option opt = Option.builder("p").type(Integer.class).build();
        opt.getValuesList().addAll(Arrays.asList("1", "2", "3"));

        CommandLine cl = CommandLine.builder()
                .addOption(opt)
                .build();

        Integer[] values = cl.getParsedOptionValues(opt);
        assertArrayEquals(new Integer[]{1, 2, 3}, values);
    }

    private static class TestConsumer implements Consumer<Option> {
        boolean accepted = false;

        @Override
        public void accept(Option opt) {
            accepted = true;
        }
    }

    @Test
    public void testDeprecatedOptionHandlerInvoked() {
        TestConsumer mockHandler = new TestConsumer();
        Option deprecatedOpt = Option.builder()
                .option("d")
                .deprecated()
                .build();

        CommandLine cl = CommandLine.builder()
                .setDeprecatedHandler(mockHandler)
                .addOption(deprecatedOpt)
                .build();

        assertTrue(cl.hasOption(deprecatedOpt));
        assertTrue(mockHandler.accepted);
    }

    @Test
    public void testGetArgsAndArgList() {
        CommandLine cl = CommandLine.builder()
                .addArg("foo")
                .addArg("bar")
                .build();

        List<String> argList = cl.getArgList();
        assertEquals(Arrays.asList("foo", "bar"), argList);

        String[] argsArray = cl.getArgs();
        assertArrayEquals(new String[]{"foo", "bar"}, argsArray);
    }

    @Test
    public void testOptionValueWithDefaultWhenNotPresent() {
        Option opt = new Option("x", true, "desc");
        opt.getValuesList().add("value");

        CommandLine cl = CommandLine.builder()
                .addOption(opt)
                .build();

        assertEquals("value", cl.getOptionValue("x"));
        assertEquals("default", cl.getOptionValue("y", "default"));
    }

    @Test
    public void testOptionValueForNonExistentOptionReturnsNull() {
        CommandLine cl = CommandLine.builder().build();
        assertNull(cl.getOptionValue("nonexistent"));
        assertNull(cl.getOptionValues("nonexistent"));
    }

    @Test
    public void testGetParsedOptionValueThrowsParseExceptionOnInvalidConversion() {
        Option opt = Option.builder("bad").type(Integer.class).build();
        opt.getValuesList().add("notAnInt");

        CommandLine cl = CommandLine.builder()
                .addOption(opt)
                .build();

        try {
            cl.getParsedOptionValue(opt);
            fail("Expected ParseException");
        } catch (ParseException e) {
            // expected
        }
    }

    @Test
    public void testGetOptionValuesWithNullOption() {
        CommandLine cl = CommandLine.builder().build();
        assertNull(cl.getOptionValues((Option) null));
    }

    @Test
    public void testGetOptionValuesForDeprecatedOptionInvokesHandler() {
        TestConsumer mockHandler = new TestConsumer();
        Option deprecatedOpt = Option.builder()
                .option("e")
                .deprecated()
                .build();
        deprecatedOpt.getValuesList().add("value");

        CommandLine cl = CommandLine.builder()
                .setDeprecatedHandler(mockHandler)
                .addOption(deprecatedOpt)
                .build();

        assertArrayEquals(new String[]{"value"}, cl.getOptionValues(deprecatedOpt));
        assertTrue(mockHandler.accepted);
    }

    @Test
    public void testGetOptionValuesForOptionGroup() throws AlreadySelectedException {
        Option opt1 = new Option("g1", true, "desc");
        opt1.getValuesList().add("1");
        Option opt2 = new Option("g2", true, "desc");
        opt2.getValuesList().add("2");

        OptionGroup group = new OptionGroup();
        group.addOption(opt1).addOption(opt2);

        // group not selected
        CommandLine cl = CommandLine.builder()
                .addOption(opt1)
                .build();
        assertNull(cl.getOptionValues(group));

        // select option g2
        try {
            group.setSelected(opt2);
        } catch (AlreadySelectedException e) {
            fail(e.getMessage());
        }
        // rebuild command line to include opt2
        CommandLine cl2 = CommandLine.builder()
                .addOption(opt1)
                .addOption(opt2)
                .build();
        assertArrayEquals(new String[]{"2"}, cl2.getOptionValues(group));
    }

    @Test
    public void testHasOptionWithNullValues() {
        CommandLine cl = CommandLine.builder().build();
        assertFalse(cl.hasOption((Option) null));
        assertFalse(cl.hasOption((String) null));
        assertFalse(cl.hasOption((OptionGroup) null));
    }

    @Test
    public void testGetOptionCountWithNullOption() {
        CommandLine cl = CommandLine.builder().build();
        assertEquals(0, cl.getOptionCount((Option) null));
        assertEquals(0, cl.getOptionCount((String) null));
    }

    @Test
    public void testGetParsedOptionValueWithNullOption() throws ParseException {
        CommandLine cl = CommandLine.builder().build();
        assertNull(cl.getParsedOptionValue((Option) null));
        assertEquals(Integer.valueOf(5), cl.getParsedOptionValue((Option) null, () -> 5));
        assertEquals(Integer.valueOf(7), cl.getParsedOptionValue((Option) null, 7));
    }

    @Test
    public void testGetParsedOptionValuesWithNullOption() throws ParseException {
        CommandLine cl = CommandLine.builder().build();
        Integer[] defaultArray = new Integer[]{1, 2, 3};
        assertArrayEquals(defaultArray, cl.getParsedOptionValues((Option) null, defaultArray));
        assertArrayEquals(defaultArray, cl.getParsedOptionValues((Option) null, () -> defaultArray));
    }

    @Test
    public void testGetParsedOptionValuesWithMissingOptionReturnsDefault() throws ParseException {
        Option opt = Option.builder("z").type(Integer.class).build();
        CommandLine cl = CommandLine.builder().build(); // no option added
        Integer[] defaultArray = new Integer[]{10, 20};
        assertArrayEquals(defaultArray, cl.getParsedOptionValues(opt, defaultArray));
    }

    @Test
    public void testGetParsedOptionValuesWithConversionException() {
        Option opt = Option.builder("bad").type(Integer.class).build();
        opt.getValuesList().add("notAnInt");
        CommandLine cl = CommandLine.builder()
                .addOption(opt)
                .build();
        try {
            cl.getParsedOptionValues(opt);
            fail("Expected ParseException");
        } catch (ParseException e) {
            // expected
        }
    }

    @Test
    public void testGetParsedOptionValuesForOptionGroup() throws ParseException {
        Option opt1 = Option.builder("g1").type(Integer.class).build();
        opt1.getValuesList().add("1");
        Option opt2 = Option.builder("g2").type(Integer.class).build();
        opt2.getValuesList().add("2");

        OptionGroup group = new OptionGroup();
        group.addOption(opt1).addOption(opt2);

        // group not selected
        CommandLine cl = CommandLine.builder()
                .addOption(opt1)
                .addOption(opt2)
                .build();
        Integer[] defaultArray = new Integer[]{0};
        assertArrayEquals(defaultArray, cl.getParsedOptionValues(group, defaultArray));

        // select option g2
        try {
            group.setSelected(opt2);
        } catch (AlreadySelectedException e) {
            fail(e.getMessage());
        }
        Integer[] expected = new Integer[]{2};
        assertArrayEquals(expected, cl.getParsedOptionValues(group));
    }

    @Test
    public void testGetParsedOptionValuesForOptionGroupWithMissingOption() throws ParseException {
        Option opt1 = Option.builder("g1").type(Integer.class).build();
        opt1.getValuesList().add("1");
        OptionGroup group = new OptionGroup();
        group.addOption(opt1);
        try {
            group.setSelected(opt1);
        } catch (AlreadySelectedException e) {
            fail(e.getMessage());
        }

        CommandLine cl = CommandLine.builder()
                .build(); // no option added

        Integer[] defaultArray = new Integer[]{99};
        assertArrayEquals(defaultArray, cl.getParsedOptionValues(group, defaultArray));
    }

    @Test
    public void testGetOptionValuesForNonExistentOptionReturnsNull() {
        CommandLine cl = CommandLine.builder().build();
        assertNull(cl.getOptionValues("nonexistent"));
    }

    @Test
    public void testGetOptionValuesForCharOption() {
        Option opt = new Option("c", true, "desc");
        opt.getValuesList().add("val");
        CommandLine cl = CommandLine.builder()
                .addOption(opt)
                .build();
        assertArrayEquals(new String[]{"val"}, cl.getOptionValues('c'));
    }

    @Test
    public void testHasOptionForCharOption() {
        Option opt = new Option("h", true, "desc");
        opt.getValuesList().add("val");
        CommandLine cl = CommandLine.builder()
                .addOption(opt)
                .build();
        assertTrue(cl.hasOption('h'));
    }

    @Test
    public void testHasOptionForNonExistentCharOption() {
        CommandLine cl = CommandLine.builder().build();
        assertFalse(cl.hasOption('x'));
    }

    @Test
    public void testGetParsedOptionValueWithDefaultSupplierForMissingOption() throws ParseException {
        Option opt = Option.builder("d").type(Integer.class).build();
        CommandLine cl = CommandLine.builder().build();
        Integer val = cl.getParsedOptionValue(opt, () -> 42);
        assertEquals(Integer.valueOf(42), val);
    }

    @Test
    public void testGetParsedOptionValueWithDefaultObjectForMissingOption() throws ParseException {
        Option opt = Option.builder("d").type(Integer.class).build();
        CommandLine cl = CommandLine.builder().build();
        Integer val = cl.getParsedOptionValue(opt, 99);
        assertEquals(Integer.valueOf(99), val);
    }

    @Test
    public void testGetParsedOptionValueWithDefaultSupplierForNullOption() throws ParseException {
        CommandLine cl = CommandLine.builder().build();
        Integer val = cl.getParsedOptionValue((Option) null, () -> 7);
        assertEquals(Integer.valueOf(7), val);
    }

    @Test
    public void testGetParsedOptionValueWithDefaultObjectForNullOption() throws ParseException {
        CommandLine cl = CommandLine.builder().build();
        Integer val = cl.getParsedOptionValue((Option) null, 8);
        assertEquals(Integer.valueOf(8), val);
    }

    /* --------------------------------------------------------------------- */
    /* New tests targeting uncovered branches                              */
    /* --------------------------------------------------------------------- */

    /** Test that adding a null argument does not alter the args list. */
    @Test
    public void testAddArgWithNull() {
        CommandLine cl = CommandLine.builder()
                .addArg(null) // should be ignored
                .build();
        assertEquals(0, cl.getArgs().length);
    }

    /** Test that adding a null option does not alter the options list. */
    @Test
    public void testAddOptionWithNull() {
        CommandLine cl = CommandLine.builder()
                .addOption(null) // should be ignored
                .build();
        assertEquals(0, cl.getOptions().length);
    }

    /** Test that getOptionValues returns null when passed a null option name. */
    @Test
    public void testGetOptionValuesWithNullOptionName() {
        CommandLine cl = CommandLine.builder().build();
        assertNull(cl.getOptionValues((String) null));
    }

    /** Test that getOptionValues returns null when passed an unknown option name. */
    @Test
    public void testGetOptionValuesWithUnknownOptionName() {
        CommandLine cl = CommandLine.builder().build();
        assertNull(cl.getOptionValues("unknown"));
    }

    /** Test that getOptionValues returns null when the option exists but has no values. */
    @Test
    public void testGetOptionValuesWithNoValues() {
        Option opt = new Option("x", true, "desc");
        CommandLine cl = CommandLine.builder()
                .addOption(opt)
                .build();
        assertNull(cl.getOptionValues(opt));
    }

    /** Test that getOptionValues returns the values for a known option. */
    @Test
    public void testGetOptionValuesWithValues() {
        Option opt = new Option("x", true, "desc");
        opt.getValuesList().addAll(Arrays.asList("a", "b"));
        CommandLine cl = CommandLine.builder()
                .addOption(opt)
                .build();
        assertArrayEquals(new String[]{"a", "b"}, cl.getOptionValues(opt));
    }

    /** Test getOptionValues for an OptionGroup that is not selected. */
    @Test
    public void testGetOptionValuesWithUnselectedGroup() {
        OptionGroup group = new OptionGroup();
        group.addOption(new Option("g1", true, "desc"));
        CommandLine cl = CommandLine.builder()
                .addOption(new Option("g1", true, "desc"))
                .build();
        assertNull(cl.getOptionValues(group));
    }

    /** Test getOptionValues for an OptionGroup that is selected but its option has no values. */
    @Test
    public void testGetOptionValuesWithSelectedGroupNoValues() {
        Option opt = new Option("g1", true, "desc");
        OptionGroup group = new OptionGroup();
        group.addOption(opt);
        try {
            group.setSelected(opt);
        } catch (AlreadySelectedException e) {
            fail(e.getMessage());
        }
        CommandLine cl = CommandLine.builder()
                .addOption(opt)
                .build();
        assertNull(cl.getOptionValues(group));
    }

    /** Test getOptionValues for an OptionGroup that is selected and its option has values. */
    @Test
    public void testGetOptionValuesWithSelectedGroupWithValues() {
        Option opt = new Option("g1", true, "desc");
        opt.getValuesList().add("value");
        OptionGroup group = new OptionGroup();
        group.addOption(opt);
        try {
            group.setSelected(opt);
        } catch (AlreadySelectedException e) {
            fail(e.getMessage());
        }
        CommandLine cl = CommandLine.builder()
                .addOption(opt)
                .build();
        assertArrayEquals(new String[]{"value"}, cl.getOptionValues(group));
    }

    /** Test getOptionCount with a null option name. */
    @Test
    public void testGetOptionCountWithNullOptionName() {
        CommandLine cl = CommandLine.builder().build();
        assertEquals(0, cl.getOptionCount((String) null));
    }

    /** Test getOptionObject with a null option name. */
    @Test
    public void testGetOptionObjectWithNullOptionName() {
        CommandLine cl = CommandLine.builder().build();
        assertNull(cl.getOptionObject((String) null));
    }

    /** Test getOptionObject with an unknown option name. */
    @Test
    public void testGetOptionObjectWithUnknownOptionName() {
        CommandLine cl = CommandLine.builder().build();
        assertNull(cl.getOptionObject("unknown"));
    }

    /** Test getOptionObject with a known option that has a conversion exception. */
    @Test
    public void testGetOptionObjectWithConversionException() {
        Option opt = Option.builder("bad").type(Integer.class).build();
        opt.getValuesList().add("notAnInt");
        CommandLine cl = CommandLine.builder()
                .addOption(opt)
                .build();
        assertNull(cl.getOptionObject(opt.getOpt()));
    }

    /** Test getOptionObject with a known option that converts successfully. */
    @Test
    public void testGetOptionObjectWithSuccessfulConversion() {
        Option opt = Option.builder("num").type(Integer.class).build();
        opt.getValuesList().add("42");
        CommandLine cl = CommandLine.builder()
                .addOption(opt)
                .build();
        assertEquals(Integer.valueOf(42), cl.getOptionObject(opt.getOpt()));
    }

    /** Test that getOptionObject prints an error message when conversion fails. */
    @Test
    public void testGetOptionObjectPrintsErrorOnConversionException() {
        Option opt = Option.builder("bad").type(Integer.class).build();
        opt.getValuesList().add("notAnInt");
        CommandLine cl = CommandLine.builder()
                .addOption(opt)
                .build();

        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        PrintStream originalErr = System.err;
        System.setErr(new PrintStream(errContent));
        try {
            Object result = cl.getOptionObject(opt.getOpt());
            assertNull(result);
            String errOutput = errContent.toString();
            assertTrue(errOutput.contains("Exception found converting"));
            assertTrue(errOutput.contains(opt.getOpt()));
        } finally {
            System.setErr(originalErr);
        }
    }
}
