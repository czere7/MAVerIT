package org.apache.commons.cli;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class CommandLineTest {

    private CommandLine commandLine;
    private Option optionA;
    private Option optionB;
    private Option optionLong;
    private Option optionWithArgs;
    private Option deprecatedOption;
    private OptionGroup optionGroup;

    @Before
    public void setUp() {
        optionA = Option.builder("a").desc("Option A").build();
        optionB = Option.builder("b").desc("Option B").hasArg().build();
        optionLong = Option.builder("long").longOpt("long-option").desc("Long option").build();
        optionWithArgs = Option.builder("m").longOpt("multi").hasArgs().desc("Multi arg option").build();
        deprecatedOption = Option.builder("d").longOpt("deprecated").desc("Deprecated").deprecated().build();

        optionGroup = new OptionGroup()
            .addOption(Option.builder("x").desc("Option X").build())
            .addOption(Option.builder("y").longOpt("y-long").desc("Option Y").build());

        commandLine = CommandLine.builder()
            .addArg("arg1")
            .addArg("arg2")
            .addOption(optionA)
            .addOption(optionB)
            .addOption(optionLong)
            .addOption(optionWithArgs)
            .addOption(deprecatedOption)
            .get();
    }

    @Test
    public void testBuilderCreatesEmptyCommandLine() {
        CommandLine cl = CommandLine.builder().get();
        assertNotNull(cl);
        assertTrue(cl.getArgList().isEmpty());
        assertArrayEquals(new String[0], cl.getArgs());
        assertArrayEquals(new Option[0], cl.getOptions());
    }

    @Test
    public void testBuilderAddsArgs() {
        CommandLine cl = CommandLine.builder()
            .addArg("first")
            .addArg("second")
            .addArg(null)
            .get();

        List<String> args = cl.getArgList();
        assertEquals(2, args.size());
        assertEquals("first", args.get(0));
        assertEquals("second", args.get(1));
        assertArrayEquals(new String[]{"first", "second"}, cl.getArgs());
    }

    @Test
    public void testBuilderAddsOptions() {
        CommandLine cl = CommandLine.builder()
            .addOption(optionA)
            .addOption(optionB)
            .get();

        Option[] options = cl.getOptions();
        assertEquals(2, options.length);
        assertTrue(Arrays.asList(options).contains(optionA));
        assertTrue(Arrays.asList(options).contains(optionB));
    }

    @Test
    public void testBuilderSetDeprecatedHandler() {
        final StringBuilder captured = new StringBuilder();
        Consumer<Option> handler = opt -> captured.append(opt.toDeprecatedString());

        CommandLine cl = CommandLine.builder()
            .addOption(deprecatedOption)
            .setDeprecatedHandler(handler)
            .get();

        assertTrue(cl.hasOption("d"));
        assertTrue(captured.toString().contains("deprecated"));
    }

    @Test
    public void testDefaultDeprecatedHandlerPrintsToSystemOut() {
        // Just verify it doesn't throw
        CommandLine cl = CommandLine.builder()
            .addOption(deprecatedOption)
            .get();
        assertTrue(cl.hasOption("d"));
    }

    @Test
    public void testHasOptionWithChar() {
        assertTrue(commandLine.hasOption('a'));
        assertTrue(commandLine.hasOption('b'));
        assertFalse(commandLine.hasOption('z'));
    }

    @Test
    public void testHasOptionWithString() {
        assertTrue(commandLine.hasOption("a"));
        assertTrue(commandLine.hasOption("long-option"));
        assertTrue(commandLine.hasOption("--long-option"));
        assertTrue(commandLine.hasOption("-a"));
        assertFalse(commandLine.hasOption("nonexistent"));
        assertFalse(commandLine.hasOption((String) null));
    }

    @Test
    public void testHasOptionWithOptionObject() {
        assertTrue(commandLine.hasOption(optionA));
        assertTrue(commandLine.hasOption(optionB));
        Option other = Option.builder("z").build();
        assertFalse(commandLine.hasOption(other));
    }

    @Test
    public void testHasOptionWithOptionGroupNotSelected() {
        // OptionGroup is not selected when using Builder directly (selection happens during parsing)
        CommandLine cl = CommandLine.builder()
            .addOption(optionGroup.getOptions().iterator().next())
            .get();

        assertFalse(cl.hasOption(optionGroup));
        assertFalse(cl.hasOption((OptionGroup) null));
    }

    @Test
    public void testGetOptionValueWithChar() {
        Option optWithValue = Option.builder("b").hasArg().build();
        optWithValue.getValuesList().add("testValue");

        CommandLine cl = CommandLine.builder()
            .addOption(optWithValue)
            .get();

        assertEquals("testValue", cl.getOptionValue('b'));
        assertNull(cl.getOptionValue('z'));
    }

    @Test
    public void testGetOptionValueWithString() {
        Option optWithValue = Option.builder("b").hasArg().build();
        optWithValue.getValuesList().add("testValue");

        CommandLine cl = CommandLine.builder()
            .addOption(optWithValue)
            .get();

        assertEquals("testValue", cl.getOptionValue("b"));
        assertEquals("testValue", cl.getOptionValue("--b"));
        assertEquals("testValue", cl.getOptionValue("-b"));
        assertNull(cl.getOptionValue("missing"));
    }

    @Test
    public void testGetOptionValueWithDefaultValue() {
        CommandLine cl = CommandLine.builder().get();

        assertEquals("default", cl.getOptionValue('a', "default"));
        assertEquals("supplied", cl.getOptionValue("b", () -> "supplied"));
    }

    @Test
    public void testGetOptionValueWithOptionObject() {
        Option optWithValue = Option.builder("multi").hasArgs().build();
        optWithValue.getValuesList().add("val1");
        optWithValue.getValuesList().add("val2");

        CommandLine cl = CommandLine.builder()
            .addOption(optWithValue)
            .get();

        assertEquals("val1", cl.getOptionValue(optWithValue));
        assertEquals("default", cl.getOptionValue(Option.builder("x").build(), "default"));
        assertEquals("supplied", cl.getOptionValue(Option.builder("y").build(), () -> "supplied"));
    }

    @Test
    public void testGetOptionValueWithOptionGroupNotSelected() {
        // OptionGroup not selected - returns null/default
        OptionGroup group = new OptionGroup()
            .addOption(Option.builder("x").hasArg().build())
            .addOption(Option.builder("y").build());

        CommandLine cl = CommandLine.builder()
            .addOption(group.getOptions().iterator().next())
            .get();

        assertNull(cl.getOptionValue(group));
        assertEquals("default", cl.getOptionValue(group, "default"));
        assertEquals("supplied", cl.getOptionValue(group, () -> "supplied"));

        CommandLine cl2 = CommandLine.builder().get();
        assertNull(cl2.getOptionValue(group));
        assertEquals("default", cl2.getOptionValue(group, "default"));
    }

    @Test
    public void testGetOptionValues() {
        Option multi = Option.builder("m").hasArgs().build();
        multi.getValuesList().add("val1");
        multi.getValuesList().add("val2");
        multi.getValuesList().add("val3");

        CommandLine cl = CommandLine.builder()
            .addOption(multi)
            .addOption(optionA) // no args
            .get();

        String[] values = cl.getOptionValues('m');
        assertArrayEquals(new String[]{"val1", "val2", "val3"}, values);

        assertNull(cl.getOptionValues('a')); // no args
        assertNull(cl.getOptionValues('z')); // missing

        assertArrayEquals(new String[]{"val1", "val2", "val3"}, cl.getOptionValues(multi));
        assertArrayEquals(new String[]{"val1", "val2", "val3"}, cl.getOptionValues("m"));
    }

    @Test
    public void testGetOptionValuesWithOptionGroupNotSelected() {
        OptionGroup group = new OptionGroup()
            .addOption(Option.builder("x").hasArgs().build())
            .addOption(Option.builder("y").build());

        CommandLine cl = CommandLine.builder()
            .addOption(group.getOptions().iterator().next())
            .get();

        assertNull(cl.getOptionValues(group));

        CommandLine cl2 = CommandLine.builder().get();
        assertNull(cl2.getOptionValues(group));
    }

    @Test
    public void testGetOptions() {
        Option[] options = commandLine.getOptions();
        assertEquals(5, options.length);
        assertTrue(Arrays.asList(options).contains(optionA));
        assertTrue(Arrays.asList(options).contains(optionB));
        assertTrue(Arrays.asList(options).contains(optionLong));
        assertTrue(Arrays.asList(options).contains(optionWithArgs));
        assertTrue(Arrays.asList(options).contains(deprecatedOption));
    }

    @Test
    public void testGetArgs() {
        String[] args = commandLine.getArgs();
        assertArrayEquals(new String[]{"arg1", "arg2"}, args);

        CommandLine empty = CommandLine.builder().get();
        assertArrayEquals(new String[0], empty.getArgs());
    }

    @Test
    public void testGetArgList() {
        List<String> args = commandLine.getArgList();
        assertEquals(2, args.size());
        assertEquals("arg1", args.get(0));
        assertEquals("arg2", args.get(1));

        CommandLine empty = CommandLine.builder().get();
        assertTrue(empty.getArgList().isEmpty());
    }

    @Test
    public void testGetOptionCount() {
        Option multi = Option.builder("m").hasArgs().build();
        multi.getValuesList().add("v1");
        multi.getValuesList().add("v2");

        CommandLine cl = CommandLine.builder()
            .addOption(optionA)
            .addOption(optionA) // same option twice
            .addOption(multi)
            .addOption(multi) // same option twice
            .get();

        assertEquals(2, cl.getOptionCount('a'));
        assertEquals(2, cl.getOptionCount("a"));
        assertEquals(2, cl.getOptionCount(optionA));
        assertEquals(2, cl.getOptionCount(multi));
        assertEquals(0, cl.getOptionCount('z'));
    }

    @Test
    public void testGetOptionProperties() {
        Option propOpt = Option.builder("D").hasArgs().build();
        propOpt.getValuesList().add("key1");
        propOpt.getValuesList().add("value1");
        propOpt.getValuesList().add("key2");
        propOpt.getValuesList().add("value2");
        propOpt.getValuesList().add("flagOnly");

        CommandLine cl = CommandLine.builder()
            .addOption(propOpt)
            .get();

        Properties props = cl.getOptionProperties("D");
        assertEquals("value1", props.getProperty("key1"));
        assertEquals("value2", props.getProperty("key2"));
        assertEquals("true", props.getProperty("flagOnly"));

        Properties props2 = cl.getOptionProperties(propOpt);
        assertEquals(props, props2);

        // Non-existent option returns empty properties
        Properties empty = cl.getOptionProperties("missing");
        assertTrue(empty.isEmpty());
    }

    @Test
    public void testGetParsedOptionValueWithDefaultConverter() throws ParseException {
        Option numberOpt = Option.builder("n").hasArg().type(Number.class).build();
        numberOpt.getValuesList().add("42");

        CommandLine cl = CommandLine.builder()
            .addOption(numberOpt)
            .get();

        Number parsed = cl.getParsedOptionValue('n');
        assertNotNull(parsed);
        assertEquals(Long.valueOf(42), parsed);

        // Test Double parsing with decimal point using default converter
        Option doubleOpt = Option.builder("n2").hasArg().type(Number.class).build();
        doubleOpt.getValuesList().add("42.0");
        CommandLine cl2 = CommandLine.builder().addOption(doubleOpt).get();
        Number parsedDouble = cl2.getParsedOptionValue("n2");
        assertEquals(Double.valueOf(42.0), parsedDouble);
    }

    @Test
    public void testGetParsedOptionValueWithCustomConverter() throws ParseException {
        Option customOpt = Option.builder("c").hasArg().build();
        customOpt.setConverter(s -> "converted:" + s);
        customOpt.getValuesList().add("input");

        CommandLine cl = CommandLine.builder()
            .addOption(customOpt)
            .get();

        String result = cl.getParsedOptionValue('c');
        assertEquals("converted:input", result);
    }

    @Test
    public void testGetParsedOptionValueWithDefaultValue() throws ParseException {
        CommandLine cl = CommandLine.builder().get();

        String defaultVal = cl.getParsedOptionValue('x', "default");
        assertEquals("default", defaultVal);

        String supplied = cl.getParsedOptionValue("y", () -> "supplied");
        assertEquals("supplied", supplied);

        Integer defaultInt = cl.getParsedOptionValue('z', Integer.valueOf(99));
        assertEquals(Integer.valueOf(99), defaultInt);
    }

    @Test
    public void testGetParsedOptionValueWithOptionObject() throws ParseException {
        Option opt = Option.builder("o").hasArg().type(Integer.class).build();
        opt.getValuesList().add("123");

        CommandLine cl = CommandLine.builder()
            .addOption(opt)
            .get();

        Integer parsed = cl.getParsedOptionValue(opt);
        assertEquals(Integer.valueOf(123), parsed);

        Integer defaultVal = cl.getParsedOptionValue(Option.builder("missing").build(), 456);
        assertEquals(Integer.valueOf(456), defaultVal);
    }

    @Test
    public void testGetParsedOptionValueWithOptionGroupNotSelected() throws ParseException {
        OptionGroup group = new OptionGroup()
            .addOption(Option.builder("x").hasArg().type(Long.class).build())
            .addOption(Option.builder("y").build());

        CommandLine cl = CommandLine.builder()
            .addOption(group.getOptions().iterator().next())
            .get();

        assertNull(cl.getParsedOptionValue(group));

        CommandLine cl2 = CommandLine.builder().get();
        assertNull(cl2.getParsedOptionValue(group));
        assertEquals("default", cl2.getParsedOptionValue(group, "default"));
    }

    @Test
    public void testGetParsedOptionValues() throws ParseException {
        Option multi = Option.builder("m").hasArgs().type(Integer.class).build();
        multi.getValuesList().add("1");
        multi.getValuesList().add("2");
        multi.getValuesList().add("3");

        CommandLine cl = CommandLine.builder()
            .addOption(multi)
            .get();

        Integer[] parsed = cl.getParsedOptionValues('m');
        assertArrayEquals(new Integer[]{1, 2, 3}, parsed);

        Integer[] parsed2 = cl.getParsedOptionValues(multi);
        assertArrayEquals(new Integer[]{1, 2, 3}, parsed2);

        Integer[] parsed3 = cl.getParsedOptionValues("m");
        assertArrayEquals(new Integer[]{1, 2, 3}, parsed3);
    }

    @Test
    public void testGetParsedOptionValuesWithDefault() throws ParseException {
        CommandLine cl = CommandLine.builder().get();

        Integer[] defaultArr = cl.getParsedOptionValues('x', new Integer[]{7, 8, 9});
        assertArrayEquals(new Integer[]{7, 8, 9}, defaultArr);

        Integer[] supplied = cl.getParsedOptionValues("y", () -> new Integer[]{10, 11});
        assertArrayEquals(new Integer[]{10, 11}, supplied);
    }

    @Test
    public void testGetParsedOptionValuesWithOptionGroupNotSelected() throws ParseException {
        OptionGroup group = new OptionGroup()
            .addOption(Option.builder("x").hasArgs().type(Double.class).build())
            .addOption(Option.builder("y").build());

        CommandLine cl = CommandLine.builder()
            .addOption(group.getOptions().iterator().next())
            .get();

        assertNull(cl.getParsedOptionValues(group));

        CommandLine cl2 = CommandLine.builder().get();
        assertNull(cl2.getParsedOptionValues(group));
        Double[] defaultArr = cl2.getParsedOptionValues(group, new Double[]{3.3});
        assertArrayEquals(new Double[]{3.3}, defaultArr);
    }

    @Test
    public void testIterator() {
        Iterator<Option> iter = commandLine.iterator();
        int count = 0;
        while (iter.hasNext()) {
            iter.next();
            count++;
        }
        assertEquals(5, count);
    }

    @Test
    public void testAddArgProtected() {
        // Test via builder which uses protected addArg
        CommandLine cl = CommandLine.builder()
            .addArg("test")
            .get();
        assertEquals(1, cl.getArgList().size());
        assertEquals("test", cl.getArgList().get(0));
    }

    @Test
    public void testAddOptionProtected() {
        // Test via builder which uses protected addOption
        CommandLine cl = CommandLine.builder()
            .addOption(optionA)
            .get();
        assertEquals(1, cl.getOptions().length);
    }

    @Test
    public void testDeprecatedOptionHandling() {
        final List<Option> deprecatedSeen = new ArrayList<>();
        Consumer<Option> handler = deprecatedSeen::add;

        CommandLine cl = CommandLine.builder()
            .addOption(deprecatedOption)
            .setDeprecatedHandler(handler)
            .get();

        assertTrue(cl.hasOption('d'));
        assertEquals(1, deprecatedSeen.size());
        assertSame(deprecatedOption, deprecatedSeen.get(0));

        // getOptionValue also triggers deprecated handler
        deprecatedSeen.clear();
        cl.getOptionValue(deprecatedOption);
        assertEquals(1, deprecatedSeen.size());

        // getOptionValues also triggers
        deprecatedSeen.clear();
        cl.getOptionValues(deprecatedOption);
        assertEquals(1, deprecatedSeen.size());
    }

    @Test
    public void testResolveOptionStripsHyphens() {
        Option opt = Option.builder("test").longOpt("long-test").build();
        opt.getValuesList().add("value");

        CommandLine cl = CommandLine.builder()
            .addOption(opt)
            .get();

        assertEquals("value", cl.getOptionValue("test"));
        assertEquals("value", cl.getOptionValue("-test"));
        assertEquals("value", cl.getOptionValue("--test"));
        assertEquals("value", cl.getOptionValue("long-test"));
        assertEquals("value", cl.getOptionValue("--long-test"));
        assertNull(cl.getOptionValue("unknown"));
    }

    @Test
    public void testGetParsedOptionValueThrowsParseExceptionOnConversionError() {
        Option badOpt = Option.builder("bad").hasArg().type(Integer.class).build();
        badOpt.getValuesList().add("not-a-number");

        CommandLine cl = CommandLine.builder()
            .addOption(badOpt)
            .get();

        try {
            cl.getParsedOptionValue("bad");
            fail("Expected ParseException");
        } catch (ParseException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NumberFormatException);
        }
    }

    @Test
    public void testGetParsedOptionValuesThrowsParseExceptionOnConversionError() {
        Option badOpt = Option.builder("bad").hasArgs().type(Integer.class).build();
        badOpt.getValuesList().add("1");
        badOpt.getValuesList().add("not-a-number");

        CommandLine cl = CommandLine.builder()
            .addOption(badOpt)
            .get();

        try {
            cl.getParsedOptionValues("bad");
            fail("Expected ParseException");
        } catch (ParseException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NumberFormatException);
        }
    }

    @Test
    public void testBuilderDeprecatedBuildMethod() {
        CommandLine.Builder builder = CommandLine.builder()
            .addArg("test")
            .addOption(optionA);

        @SuppressWarnings("deprecation")
        CommandLine cl = builder.build();

        assertNotNull(cl);
        assertEquals(1, cl.getArgList().size());
        assertEquals(1, cl.getOptions().length);
    }

    @Test
    public void testBuilderDeprecatedConstructor() {
        @SuppressWarnings("deprecation")
        CommandLine.Builder builder = new CommandLine.Builder();
        CommandLine cl = builder.addArg("test").get();

        assertNotNull(cl);
        assertEquals(1, cl.getArgList().size());
    }

    @Test
    public void testEmptyCommandLineBehavior() {
        CommandLine cl = CommandLine.builder().get();

        assertFalse(cl.hasOption('a'));
        assertFalse(cl.hasOption("a"));
        assertFalse(cl.hasOption(optionA));
        assertFalse(cl.hasOption(optionGroup));

        assertNull(cl.getOptionValue('a'));
        assertNull(cl.getOptionValue("a"));
        assertNull(cl.getOptionValue(optionA));
        assertNull(cl.getOptionValue(optionGroup));

        assertEquals("default", cl.getOptionValue('a', "default"));
        assertEquals("default", cl.getOptionValue("a", "default"));
        assertEquals("default", cl.getOptionValue(optionA, "default"));
        assertEquals("default", cl.getOptionValue(optionGroup, "default"));

        assertNull(cl.getOptionValues('a'));
        assertNull(cl.getOptionValues("a"));
        assertNull(cl.getOptionValues(optionA));
        assertNull(cl.getOptionValues(optionGroup));

        assertEquals(0, cl.getOptionCount('a'));
        assertEquals(0, cl.getOptionCount("a"));
        assertEquals(0, cl.getOptionCount(optionA));

        assertArrayEquals(new String[0], cl.getArgs());
        assertTrue(cl.getArgList().isEmpty());
        assertArrayEquals(new Option[0], cl.getOptions());
        assertFalse(cl.iterator().hasNext());
    }

    @Test
    public void testMultipleOptionsWithSameName() {
        Option opt1 = Option.builder("x").hasArg().build();
        opt1.getValuesList().add("first");
        Option opt2 = Option.builder("x").hasArg().build();
        opt2.getValuesList().add("second");

        CommandLine cl = CommandLine.builder()
            .addOption(opt1)
            .addOption(opt2)
            .get();

        // getOptionValue returns first match
        assertEquals("first", cl.getOptionValue('x'));

        // getOptionValues returns all values from all matching options
        String[] values = cl.getOptionValues('x');
        assertArrayEquals(new String[]{"first", "second"}, values);

        assertEquals(2, cl.getOptionCount('x'));
    }

    @Test
    public void testOptionWithMultipleValues() {
        Option multi = Option.builder("m").hasArgs().build();
        multi.getValuesList().add("v1");
        multi.getValuesList().add("v2");
        multi.getValuesList().add("v3");

        CommandLine cl = CommandLine.builder()
            .addOption(multi)
            .get();

        assertEquals("v1", cl.getOptionValue('m'));
        assertArrayEquals(new String[]{"v1", "v2", "v3"}, cl.getOptionValues('m'));
    }

    @Test
    public void testGetOptionPropertiesWithMultipleOptions() {
        Option prop1 = Option.builder("D").hasArgs().build();
        prop1.getValuesList().add("k1");
        prop1.getValuesList().add("v1");

        Option prop2 = Option.builder("D").hasArgs().build();
        prop2.getValuesList().add("k2");
        prop2.getValuesList().add("v2");

        CommandLine cl = CommandLine.builder()
            .addOption(prop1)
            .addOption(prop2)
            .get();

        Properties props = cl.getOptionProperties("D");
        assertEquals("v1", props.getProperty("k1"));
        assertEquals("v2", props.getProperty("k2"));
    }

    @Test
    public void testSerializable() throws Exception {
        CommandLine cl = CommandLine.builder()
            .addArg("arg1")
            .addOption(optionA)
            .get();

        // Serialize and deserialize
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(cl);
        oos.close();

        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        CommandLine deserialized = (CommandLine) ois.readObject();
        ois.close();

        assertArrayEquals(cl.getArgs(), deserialized.getArgs());
        assertEquals(cl.getOptions().length, deserialized.getOptions().length);
        assertTrue(cl.hasOption('a') == deserialized.hasOption('a'));
    }

    @Test
    public void testProtectedConstructor() {
        // Protected no-arg constructor creates empty command line
        CommandLine cl = new CommandLine() {};
        assertNotNull(cl);
        assertTrue(cl.getArgList().isEmpty());
        assertArrayEquals(new Option[0], cl.getOptions());
    }

    @Test
    public void testPrivateConstructorViaBuilder() {
        // Verify builder uses private constructor with all params
        final Consumer<Option> customHandler = opt -> {};
        CommandLine cl = CommandLine.builder()
            .addArg("test")
            .addOption(optionA)
            .setDeprecatedHandler(customHandler)
            .get();

        assertEquals(1, cl.getArgList().size());
        assertEquals(1, cl.getOptions().length);
    }

    @Test
    public void testGetParsedOptionValueWithNullOption() throws ParseException {
        CommandLine cl = CommandLine.builder().get();

        assertNull(cl.getParsedOptionValue((Option) null));
        assertNull(cl.getParsedOptionValue((Option) null, () -> null));
        assertEquals("default", cl.getParsedOptionValue((Option) null, "default"));
    }

    @Test
    public void testGetParsedOptionValuesWithNullOption() throws ParseException {
        CommandLine cl = CommandLine.builder().get();

        assertNull(cl.getParsedOptionValues((Option) null));
        assertNull(cl.getParsedOptionValues((Option) null, () -> null));
        Integer[] defaultArr = new Integer[]{1, 2};
        assertArrayEquals(defaultArr, cl.getParsedOptionValues((Option) null, defaultArr));
    }

    @Test
    public void testGetParsedOptionValueWithNullOptionGroup() throws ParseException {
        CommandLine cl = CommandLine.builder().get();

        assertNull(cl.getParsedOptionValue((OptionGroup) null));
        assertNull(cl.getParsedOptionValue((OptionGroup) null, () -> null));
        assertEquals("default", cl.getParsedOptionValue((OptionGroup) null, "default"));
    }

    @Test
    public void testGetParsedOptionValuesWithNullOptionGroup() throws ParseException {
        CommandLine cl = CommandLine.builder().get();

        assertNull(cl.getParsedOptionValues((OptionGroup) null));
        assertNull(cl.getParsedOptionValues((OptionGroup) null, () -> null));
        Integer[] defaultArr = new Integer[]{1, 2};
        assertArrayEquals(defaultArr, cl.getParsedOptionValues((OptionGroup) null, defaultArr));
    }

    @Test
    public void testHasOptionWithNullOptionGroup() {
        CommandLine cl = CommandLine.builder().get();
        assertFalse(cl.hasOption((OptionGroup) null));
    }

    @Test
    public void testGetOptionValueWithNullOptionGroup() {
        CommandLine cl = CommandLine.builder().get();
        assertNull(cl.getOptionValue((OptionGroup) null));
        assertEquals("default", cl.getOptionValue((OptionGroup) null, "default"));
        assertEquals("supplied", cl.getOptionValue((OptionGroup) null, () -> "supplied"));
    }

    @Test
    public void testGetOptionValuesWithNullOptionGroup() {
        CommandLine cl = CommandLine.builder().get();
        assertNull(cl.getOptionValues((OptionGroup) null));
    }

    @Test
    public void testGetOptionCountWithNullOption() {
        CommandLine cl = CommandLine.builder().get();
        assertEquals(0, cl.getOptionCount((Option) null));
    }

    @Test
    public void testOptionGroupNotSelected() {
        OptionGroup group = new OptionGroup()
            .addOption(Option.builder("x").build())
            .addOption(Option.builder("y").build());

        CommandLine cl = CommandLine.builder().get(); // no options added

        assertFalse(group.isSelected());
        assertFalse(cl.hasOption(group));
        assertNull(cl.getOptionValue(group));
        assertNull(cl.getOptionValues(group));
    }

    @Test
    public void testBuilderWithNullArg() {
        CommandLine cl = CommandLine.builder()
            .addArg(null)
            .addArg("valid")
            .get();

        assertEquals(1, cl.getArgList().size());
        assertEquals("valid", cl.getArgList().get(0));
    }

    @Test
    public void testBuilderWithNullOption() {
        CommandLine cl = CommandLine.builder()
            .addOption(null)
            .addOption(optionA)
            .get();

        assertEquals(1, cl.getOptions().length);
    }

    @Test
    public void testGetOptionPropertiesWithSingleValue() {
        Option prop = Option.builder("D").hasArg().build();
        prop.getValuesList().add("keyOnly");

        CommandLine cl = CommandLine.builder()
            .addOption(prop)
            .get();

        Properties props = cl.getOptionProperties("D");
        assertEquals("true", props.getProperty("keyOnly"));
    }

    @Test
    public void testGetOptionPropertiesWithEmptyOption() {
        Option prop = Option.builder("D").hasArgs().build();
        // no values added

        CommandLine cl = CommandLine.builder()
            .addOption(prop)
            .get();

        Properties props = cl.getOptionProperties("D");
        assertTrue(props.isEmpty());
    }

    // ===== Tests targeting missed branch coverage =====

    @Test
    public void testPrivateGetMethodWithNullSupplier() throws Exception {
        // Test private get(Supplier) method with null supplier - covers missed branch at line 213
        CommandLine cl = CommandLine.builder().get();
        
        java.lang.reflect.Method method = CommandLine.class.getDeclaredMethod("get", Supplier.class);
        method.setAccessible(true);
        
        // Call with null supplier - should return null (supplier == null branch)
        Object result = method.invoke(cl, (Supplier<Object>) null);
        assertNull(result);
        
        // Call with non-null supplier - should return supplier.get() (supplier != null branch)
        result = method.invoke(cl, (Supplier<String>) () -> "test");
        assertEquals("test", result);
    }

    @Test
    public void testGetOptionValueWithOptionAndSupplierExistingOption() {
        // Test getOptionValue(Option, Supplier) when option exists (answer != null branch)
        // This covers the missed branch at line 448 where answer != null
        Option opt = Option.builder("x").hasArg().build();
        opt.getValuesList().add("existingValue");
        
        CommandLine cl = CommandLine.builder()
            .addOption(opt)
            .get();
        
        // Option exists, so answer != null, should return answer without calling get(defaultValue)
        String result = cl.getOptionValue(opt, () -> "default");
        assertEquals("existingValue", result);
        
        // Also test with String default
        result = cl.getOptionValue(opt, "default");
        assertEquals("existingValue", result);
    }

    @Test
    public void testHasOptionWithSelectedOptionGroup() {
        // Test hasOption(OptionGroup) when group IS selected - covers missed branch at line 990
        OptionGroup group = new OptionGroup()
            .addOption(Option.builder("x").build())
            .addOption(Option.builder("y").build());
        
        CommandLine cl = CommandLine.builder()
            .addOption(group.getOptions().iterator().next()) // add "x"
            .get();
        
        // Use reflection to mark group as selected (select "x")
        try {
            java.lang.reflect.Field selectedField = OptionGroup.class.getDeclaredField("selected");
            selectedField.setAccessible(true);
            selectedField.set(group, "x"); // "x" is the key of the first option
        } catch (Exception e) {
            fail("Reflection failed: " + e.getMessage());
        }
        
        assertTrue(group.isSelected());
        assertTrue(cl.hasOption(group));
    }

    @Test
    public void testGetOptionValueWithSelectedOptionGroup() {
        // Test getOptionValue(OptionGroup) when group IS selected
        OptionGroup group = new OptionGroup()
            .addOption(Option.builder("x").hasArg().build())
            .addOption(Option.builder("y").build());
        
        Option optX = group.getOptions().iterator().next();
        optX.getValuesList().add("selectedValue");
        
        CommandLine cl = CommandLine.builder()
            .addOption(optX)
            .get();
        
        // Use reflection to mark group as selected
        try {
            java.lang.reflect.Field selectedField = OptionGroup.class.getDeclaredField("selected");
            selectedField.setAccessible(true);
            selectedField.set(group, "x");
        } catch (Exception e) {
            fail("Reflection failed: " + e.getMessage());
        }
        
        assertTrue(group.isSelected());
        assertEquals("selectedValue", cl.getOptionValue(group));
        assertEquals("selectedValue", cl.getOptionValue(group, "default"));
        assertEquals("selectedValue", cl.getOptionValue(group, () -> "supplied"));
    }

    @Test
    public void testGetOptionValuesWithSelectedOptionGroup() {
        // Test getOptionValues(OptionGroup) when group IS selected
        OptionGroup group = new OptionGroup()
            .addOption(Option.builder("x").hasArgs().build())
            .addOption(Option.builder("y").build());
        
        Option optX = group.getOptions().iterator().next();
        optX.getValuesList().add("v1");
        optX.getValuesList().add("v2");
        
        CommandLine cl = CommandLine.builder()
            .addOption(optX)
            .get();
        
        // Use reflection to mark group as selected
        try {
            java.lang.reflect.Field selectedField = OptionGroup.class.getDeclaredField("selected");
            selectedField.setAccessible(true);
            selectedField.set(group, "x");
        } catch (Exception e) {
            fail("Reflection failed: " + e.getMessage());
        }
        
        assertTrue(group.isSelected());
        String[] values = cl.getOptionValues(group);
        assertNotNull(values);
        assertArrayEquals(new String[]{"v1", "v2"}, values);
    }

    @Test
    public void testGetParsedOptionValueWithSelectedOptionGroup() throws ParseException {
        // Test getParsedOptionValue(OptionGroup, Supplier) when group IS selected - covers missed branch at line 667
        OptionGroup group = new OptionGroup()
            .addOption(Option.builder("x").hasArg().type(Integer.class).build())
            .addOption(Option.builder("y").build());
        
        Option optX = group.getOptions().iterator().next();
        optX.getValuesList().add("42");
        
        CommandLine cl = CommandLine.builder()
            .addOption(optX)
            .get();
        
        // Use reflection to mark group as selected
        try {
            java.lang.reflect.Field selectedField = OptionGroup.class.getDeclaredField("selected");
            selectedField.setAccessible(true);
            selectedField.set(group, "x");
        } catch (Exception e) {
            fail("Reflection failed: " + e.getMessage());
        }
        
        assertTrue(group.isSelected());
        Integer parsed = cl.getParsedOptionValue(group);
        assertEquals(Integer.valueOf(42), parsed);
        
        // Test with default value supplier (should not be used since group is selected)
        parsed = cl.getParsedOptionValue(group, () -> 99);
        assertEquals(Integer.valueOf(42), parsed);
        
        // Test with default value
        parsed = cl.getParsedOptionValue(group, 99);
        assertEquals(Integer.valueOf(42), parsed);
    }

    @Test
    public void testGetParsedOptionValuesWithSelectedOptionGroup() throws ParseException {
        // Test getParsedOptionValues(OptionGroup, Supplier) when group IS selected - covers missed branch at line 863
        OptionGroup group = new OptionGroup()
            .addOption(Option.builder("x").hasArgs().type(Integer.class).build())
            .addOption(Option.builder("y").build());
        
        Option optX = group.getOptions().iterator().next();
        optX.getValuesList().add("1");
        optX.getValuesList().add("2");
        
        CommandLine cl = CommandLine.builder()
            .addOption(optX)
            .get();
        
        // Use reflection to mark group as selected
        try {
            java.lang.reflect.Field selectedField = OptionGroup.class.getDeclaredField("selected");
            selectedField.setAccessible(true);
            selectedField.set(group, "x");
        } catch (Exception e) {
            fail("Reflection failed: " + e.getMessage());
        }
        
        assertTrue(group.isSelected());
        Integer[] parsed = cl.getParsedOptionValues(group);
        assertArrayEquals(new Integer[]{1, 2}, parsed);
        
        // Test with default value supplier (should not be used since group is selected)
        parsed = cl.getParsedOptionValues(group, () -> new Integer[]{99});
        assertArrayEquals(new Integer[]{1, 2}, parsed);
        
        // Test with default value
        parsed = cl.getParsedOptionValues(group, new Integer[]{99});
        assertArrayEquals(new Integer[]{1, 2}, parsed);
    }

    @Test
    public void testGetOptionValueWithOptionAndNullSupplier() {
        // Test getOptionValue(Option, Supplier) with null supplier - covers get(defaultValue) with null
        Option opt = Option.builder("x").hasArg().build();
        opt.getValuesList().add("value");
        
        CommandLine cl = CommandLine.builder()
            .addOption(opt)
            .get();
        
        // Option exists, so answer != null, supplier not called
        String result = cl.getOptionValue(opt, (Supplier<String>) null);
        assertEquals("value", result);
        
        // Option missing, answer == null, supplier is null -> get(null) returns null
        Option missingOpt = Option.builder("missing").build();
        result = cl.getOptionValue(missingOpt, (Supplier<String>) null);
        assertNull(result);
    }

    @Test
    public void testGetOptionValuesWithDeprecatedOptionAndNullHandler() {
        // Test getOptionValues(Option) with deprecated option and null handler
        // Covers handleDeprecated null handler branch
        Option deprecatedOpt = Option.builder("d").hasArg().deprecated().build();
        deprecatedOpt.getValuesList().add("value1");
        deprecatedOpt.getValuesList().add("value2");
        
        CommandLine cl = CommandLine.builder()
            .addOption(deprecatedOpt)
            .setDeprecatedHandler(null)
            .get();
        
        // Should not throw NPE
        String[] values = cl.getOptionValues(deprecatedOpt);
        assertNotNull(values);
        assertArrayEquals(new String[]{"value1", "value2"}, values);
    }

    @Test
    public void testGetOptionValueWithOptionGroupNotSelectedAndNullSupplier() {
        // Test getOptionValue(OptionGroup, Supplier) with not selected group and null supplier
        OptionGroup group = new OptionGroup()
            .addOption(Option.builder("x").hasArg().build())
            .addOption(Option.builder("y").build());
        
        CommandLine cl = CommandLine.builder().get();
        
        // Group not selected, supplier is null -> get(null) returns null
        String result = cl.getOptionValue(group, (Supplier<String>) null);
        assertNull(result);
        
        // Group not selected, supplier returns value
        result = cl.getOptionValue(group, () -> "supplied");
        assertEquals("supplied", result);
    }

    @Test
    public void testGetParsedOptionValueWithOptionAndNullSupplier() throws ParseException {
        // Test getParsedOptionValue(Option, Supplier) with null supplier
        Option opt = Option.builder("x").hasArg().type(Integer.class).build();
        opt.getValuesList().add("42");
        
        CommandLine cl = CommandLine.builder()
            .addOption(opt)
            .get();
        
        // Option exists, supplier not called
        Integer result = cl.getParsedOptionValue(opt, (Supplier<Integer>) null);
        assertEquals(Integer.valueOf(42), result);
        
        // Option missing, supplier is null -> get(null) returns null
        Option missingOpt = Option.builder("missing").type(Integer.class).build();
        result = cl.getParsedOptionValue(missingOpt, (Supplier<Integer>) null);
        assertNull(result);
    }

    @Test
    public void testGetParsedOptionValuesWithOptionAndNullSupplier() throws ParseException {
        // Test getParsedOptionValues(Option, Supplier) with null supplier
        Option opt = Option.builder("x").hasArgs().type(Integer.class).build();
        opt.getValuesList().add("1");
        opt.getValuesList().add("2");
        
        CommandLine cl = CommandLine.builder()
            .addOption(opt)
            .get();
        
        // Option exists, supplier not called
        Integer[] result = cl.getParsedOptionValues(opt, (Supplier<Integer[]>) null);
        assertArrayEquals(new Integer[]{1, 2}, result);
        
        // Option missing, supplier is null -> get(null) returns null
        Option missingOpt = Option.builder("missing").hasArgs().type(Integer.class).build();
        result = cl.getParsedOptionValues(missingOpt, (Supplier<Integer[]>) null);
        assertNull(result);
    }

    // ===== New tests for mutation killing =====

    @Test
    public void testGetOptionObjectPrintsToSystemErrOnConversionError() {
        // Tests the SURVIVED mutation at line 291: VoidMethodCallMutator removes System.err.println
        // getOptionObject catches ParseException and prints to System.err, then returns null
        Option badOpt = Option.builder("bad").hasArg().type(Integer.class).build();
        badOpt.getValuesList().add("not-a-number");

        CommandLine cl = CommandLine.builder()
            .addOption(badOpt)
            .get();

        // Capture System.err
        PrintStream originalErr = System.err;
        ByteArrayOutputStream errCapture = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errCapture));

        try {
            Object result = cl.getOptionObject("bad");
            assertNull(result); // Should return null on conversion error
            
            String output = errCapture.toString();
            assertTrue("Should print exception message to System.err", 
                output.contains("Exception found converting bad to desired type"));
            assertTrue("Should include exception message", 
                output.contains("not-a-number") || output.contains("NumberFormatException"));
        } finally {
            System.setErr(originalErr);
        }
    }

    @Test
    public void testGetOptionObjectReturnsNullOnMissingOption() {
        // Tests the deprecated getOptionObject with missing option
        CommandLine cl = CommandLine.builder().get();
        
        Object result = cl.getOptionObject('z');
        assertNull(result);
        
        result = cl.getOptionObject("missing");
        assertNull(result);
    }

    @Test
    public void testGetOptionObjectReturnsParsedValueOnSuccess() {
        // Tests getOptionObject returns parsed value when conversion succeeds
        Option opt = Option.builder("n").hasArg().type(Integer.class).build();
        opt.getValuesList().add("42");

        CommandLine cl = CommandLine.builder()
            .addOption(opt)
            .get();

        Object result = cl.getOptionObject('n');
        assertNotNull(result);
        assertEquals(Integer.valueOf(42), result);

        result = cl.getOptionObject("n");
        assertEquals(Integer.valueOf(42), result);
    }

    @Test
    public void testGetOptionValueMissingOptionReturnsNullNotEmptyString() {
        // Tests the NO_COVERAGE mutation at line 376: EmptyObjectReturnValsMutator replaces return with ""
        // Verifies that missing option returns null, not empty string
        CommandLine cl = CommandLine.builder().get();
        
        String result = cl.getOptionValue('z');
        assertNull("Missing option should return null, not empty string", result);
        
        result = cl.getOptionValue("missing");
        assertNull("Missing option should return null, not empty string", result);
        
        result = cl.getOptionValue(Option.builder("missing").build());
        assertNull("Missing option should return null, not empty string", result);
    }

    @Test
    public void testGetParsedOptionValueMissingOptionReturnsDefaultNotNull() throws ParseException {
        // Tests the NO_COVERAGE mutation at line 567: NullReturnValsMutator replaces return with null
        // Verifies that missing option with default supplier returns the default, not null
        CommandLine cl = CommandLine.builder().get();
        
        String result = cl.getParsedOptionValue("missing", () -> "supplied-default");
        assertEquals("Should return supplied default, not null", "supplied-default", result);
        
        String result2 = cl.getParsedOptionValue("missing", "explicit-default");
        assertEquals("Should return explicit default, not null", "explicit-default", result2);
        
        Integer result3 = cl.getParsedOptionValue("missing", () -> Integer.valueOf(99));
        assertEquals(Integer.valueOf(99), result3);
    }

    @Test
    public void testGetParsedOptionValueExistingOptionReturnsParsedValue() throws ParseException {
        // Verifies that existing option returns parsed value (not null)
        Option opt = Option.builder("n").hasArg().type(Integer.class).build();
        opt.getValuesList().add("42");

        CommandLine cl = CommandLine.builder()
            .addOption(opt)
            .get();

        Integer parsed = cl.getParsedOptionValue('n');
        assertNotNull("Should return parsed value, not null", parsed);
        assertEquals(Integer.valueOf(42), parsed);
    }
}
