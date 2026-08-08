package org.apache.commons.cli;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.Test;

public class CommandLineTest {

    // Helper methods
    private void setOptionValues(Option option, List<String> values) throws Exception {
        Field valuesField = Option.class.getDeclaredField("values");
        valuesField.setAccessible(true);
        valuesField.set(option, values);
    }

    private void setOptionValues(Option option, String... values) throws Exception {
        setOptionValues(option, new ArrayList<>(Arrays.asList(values)));
    }

    @Test
    public void testBuilderCreatesCommandLine() {
        final CommandLine cmd = CommandLine.builder().get();
        assertNotNull(cmd);
    }

    @Test
    public void testBuilderWithArgs() {
        final CommandLine cmd = CommandLine.builder()
                .addArg("arg1")
                .addArg("arg2")
                .get();
        
        final List<String> argList = cmd.getArgList();
        assertEquals(2, argList.size());
        assertEquals("arg1", argList.get(0));
        assertEquals("arg2", argList.get(1));
    }

    @Test
    public void testBuilderWithNullArgIgnored() {
        final CommandLine cmd = CommandLine.builder()
                .addArg("arg1")
                .addArg(null)
                .addArg("arg2")
                .get();
        
        assertEquals(2, cmd.getArgList().size());
    }

    @Test
    public void testBuilderWithOption() {
        final Option option = Option.builder("a").hasArg(true).build();
        final CommandLine cmd = CommandLine.builder()
                .addOption(option)
                .get();
        
        final Option[] options = cmd.getOptions();
        assertEquals(1, options.length);
        assertEquals("a", options[0].getOpt());
    }

    @Test
    public void testBuilderWithNullOptionIgnored() {
        final Option option = Option.builder("a").hasArg(true).build();
        final CommandLine cmd = CommandLine.builder()
                .addOption(option)
                .addOption(null)
                .get();
        
        assertEquals(1, cmd.getOptions().length);
    }

    @Test
    public void testGetArgs() {
        final CommandLine cmd = CommandLine.builder()
                .addArg("file1.txt")
                .addArg("file2.txt")
                .get();
        
        final String[] args = cmd.getArgs();
        assertEquals(2, args.length);
        assertEquals("file1.txt", args[0]);
        assertEquals("file2.txt", args[1]);
    }

    @Test
    public void testGetArgList() {
        final List<String> expectedArgs = new LinkedList<>();
        expectedArgs.add("arg1");
        
        final CommandLine cmd = CommandLine.builder()
                .addArg("arg1")
                .get();
        
        assertEquals(expectedArgs, cmd.getArgList());
    }

    @Test
    public void testEmptyArgs() {
        final CommandLine cmd = CommandLine.builder().get();
        
        assertEquals(0, cmd.getArgList().size());
        assertEquals(0, cmd.getArgs().length);
    }

    @Test
    public void testHasOptionWithShortName() {
        final Option option = Option.builder("a").build();
        final CommandLine cmd = CommandLine.builder()
                .addOption(option)
                .get();
        
        assertTrue(cmd.hasOption("a"));
        assertFalse(cmd.hasOption("b"));
    }

    @Test
    public void testHasOptionWithLongName() {
        final Option option = Option.builder("a").longOpt("alpha").build();
        final CommandLine cmd = CommandLine.builder()
                .addOption(option)
                .get();
        
        assertTrue(cmd.hasOption("alpha"));
        assertFalse(cmd.hasOption("beta"));
    }

    @Test
    public void testHasOptionWithChar() {
        final Option option = Option.builder("a").build();
        final CommandLine cmd = CommandLine.builder()
                .addOption(option)
                .get();
        
        assertTrue(cmd.hasOption('a'));
        assertFalse(cmd.hasOption('b'));
    }

    @Test
    public void testHasOptionWithOptionObject() {
        final Option option = Option.builder("a").hasArg(true).build();
        final Option addedOption = Option.builder("a").hasArg(true).build();
        
        final CommandLine cmd = CommandLine.builder()
                .addOption(addedOption)
                .get();
        
        assertTrue(cmd.hasOption(option));
    }

    @Test
    public void testHasOptionWithOptionGroup() throws AlreadySelectedException {
        final Option optionA = Option.builder("a").hasArg(true).build();
        
        final OptionGroup group = new OptionGroup();
        group.addOption(optionA);
        group.setSelected(optionA);
        
        final CommandLine cmd = CommandLine.builder()
                .addOption(optionA)
                .get();
        
        assertTrue(cmd.hasOption(group));
    }

    @Test
    public void testHasOptionWithNullOptionGroup() {
        final CommandLine cmd = CommandLine.builder().get();
        
        assertFalse(cmd.hasOption((OptionGroup) null));
    }

    @Test
    public void testHasOptionWithUnselectedOptionGroup() {
        final Option optionA = Option.builder("a").build();
        final Option optionB = Option.builder("b").build();
        
        final OptionGroup group = new OptionGroup();
        group.addOption(optionA);
        group.addOption(optionB);
        
        final CommandLine cmd = CommandLine.builder().get();
        
        assertFalse(cmd.hasOption(group));
    }

    @Test
    public void testGetOptionValueNotSet() {
        final Option option = Option.builder("a").hasArg(true).build();
        
        final CommandLine cmd = CommandLine.builder()
                .addOption(option)
                .get();
        
        assertNull(cmd.getOptionValue("b"));
    }

    @Test
    public void testGetOptionValueWithDefault() {
        final Option option = Option.builder("a").hasArg(true).build();
        
        final CommandLine cmd = CommandLine.builder()
                .addOption(option)
                .get();
        
        assertEquals("default", cmd.getOptionValue("a", "default"));
    }

    @Test
    public void testGetOptionValueWithSupplierDefault() {
        final Option option = Option.builder("a").hasArg(true).build();
        
        final CommandLine cmd = CommandLine.builder()
                .addOption(option)
                .get();
        
        assertEquals("supplied", cmd.getOptionValue("a", () -> "supplied"));
    }

    @Test
    public void testGetOptionValuesNotSet() {
        final CommandLine cmd = CommandLine.builder().get();
        
        assertNull(cmd.getOptionValues("a"));
    }

    @Test
    public void testGetOptionValuesWithNullOption() {
        final CommandLine cmd = CommandLine.builder().get();
        
        assertNull(cmd.getOptionValues((Option) null));
    }

    @Test
    public void testGetOptionCountWithString() {
        final Option option = Option.builder("a").hasArg(true).build();
        
        final CommandLine cmd = CommandLine.builder()
                .addOption(option)
                .get();
        
        assertEquals(1, cmd.getOptionCount("a"));
        assertEquals(0, cmd.getOptionCount("b"));
    }

    @Test
    public void testGetOptionCountWithChar() {
        final Option option = Option.builder("a").hasArg(true).build();
        
        final CommandLine cmd = CommandLine.builder()
                .addOption(option)
                .get();
        
        assertEquals(1, cmd.getOptionCount('a'));
        assertEquals(0, cmd.getOptionCount('b'));
    }

    @Test
    public void testGetOptionCountWithOption() {
        final Option option = Option.builder("a").hasArg(true).build();
        
        final CommandLine cmd = CommandLine.builder()
                .addOption(option)
                .get();
        
        assertEquals(1, cmd.getOptionCount(option));
    }

    @Test
    public void testGetOptionPropertiesNotSet() {
        final CommandLine cmd = CommandLine.builder().get();
        
        final Properties props = cmd.getOptionProperties("D");
        assertTrue(props.isEmpty());
    }

    @Test
    public void testGetOptions() {
        final Option option1 = Option.builder("a").build();
        final Option option2 = Option.builder("b").build();
        
        final CommandLine cmd = CommandLine.builder()
                .addOption(option1)
                .addOption(option2)
                .get();
        
        final Option[] options = cmd.getOptions();
        assertEquals(2, options.length);
    }

    @Test
    public void testIterator() {
        final Option option1 = Option.builder("a").build();
        final Option option2 = Option.builder("b").build();
        
        final CommandLine cmd = CommandLine.builder()
                .addOption(option1)
                .addOption(option2)
                .get();
        
        final Iterator<Option> iter = cmd.iterator();
        assertTrue(iter.hasNext());
        assertEquals(option1, iter.next());
        assertTrue(iter.hasNext());
        assertEquals(option2, iter.next());
        assertFalse(iter.hasNext());
    }

    @Test
    public void testGetOptionValueWithOptionGroupDefault() {
        final Option optionA = Option.builder("a").hasArg(true).build();
        
        final OptionGroup group = new OptionGroup();
        group.addOption(optionA);
        
        final CommandLine cmd = CommandLine.builder()
                .addOption(optionA)
                .get();
        
        assertEquals("defaultVal", cmd.getOptionValue(group, "defaultVal"));
    }

    @Test
    public void testGetOptionValueWithOptionGroupSupplierDefault() {
        final Option optionA = Option.builder("a").hasArg(true).build();
        
        final OptionGroup group = new OptionGroup();
        group.addOption(optionA);
        
        final CommandLine cmd = CommandLine.builder()
                .addOption(optionA)
                .get();
        
        assertEquals("supplied", cmd.getOptionValue(group, () -> "supplied"));
    }

    @Test
    public void testGetOptionValuesWithNullOptionGroup() {
        final CommandLine cmd = CommandLine.builder().get();
        
        assertNull(cmd.getOptionValues((OptionGroup) null));
    }

    @Test
    public void testGetOptionValuesWithUnselectedOptionGroup() {
        final Option optionA = Option.builder("a").hasArgs().build();
        
        final OptionGroup group = new OptionGroup();
        group.addOption(optionA);
        
        final CommandLine cmd = CommandLine.builder().get();
        
        assertNull(cmd.getOptionValues(group));
    }

    @Test
    public void testGetParsedOptionValueNotSet() throws ParseException {
        final CommandLine cmd = CommandLine.builder().get();
        
        assertNull(cmd.getParsedOptionValue("n"));
    }

    @Test
    public void testGetParsedOptionValueWithDefault() throws ParseException {
        final CommandLine cmd = CommandLine.builder().get();
        
        final Number result = cmd.getParsedOptionValue("n", () -> 100);
        assertEquals(100, result);
    }

    @Test
    public void testGetParsedOptionValueWithDefaultValue() throws ParseException {
        final CommandLine cmd = CommandLine.builder().get();
        
        final Number result = cmd.getParsedOptionValue("n", 100);
        assertEquals(100, result);
    }

    @Test
    public void testGetParsedOptionValueWithNullOption() throws ParseException {
        final CommandLine cmd = CommandLine.builder().get();
        
        final String result = cmd.getParsedOptionValue((Option) null);
        assertNull(result);
    }

    @Test
    public void testGetParsedOptionValueWithNullOptionGroup() throws ParseException {
        final CommandLine cmd = CommandLine.builder().get();
        
        final String result = cmd.getParsedOptionValue((OptionGroup) null);
        assertNull(result);
    }

    @Test
    public void testGetParsedOptionValueWithNullOptionGroupDefault() throws ParseException {
        final CommandLine cmd = CommandLine.builder().get();
        
        final String result = cmd.getParsedOptionValue((OptionGroup) null, () -> "default");
        assertEquals("default", result);
    }

    @Test
    public void testGetParsedOptionValuesWithDefault() throws ParseException {
        final CommandLine cmd = CommandLine.builder().get();
        
        final Number[] defaultArr = new Number[0];
        final Number[] result = cmd.getParsedOptionValues("n", () -> defaultArr);
        assertEquals(0, result.length);
    }

    @Test
    public void testGetParsedOptionValuesWithNullOptionGroup() throws ParseException {
        final CommandLine cmd = CommandLine.builder().get();
        
        final Number[] result = cmd.getParsedOptionValues((OptionGroup) null);
        assertNull(result);
    }

    @Test
    public void testGetParsedOptionValuesWithUnselectedOptionGroup() throws ParseException {
        final Option optionA = Option.builder("a").hasArgs().type(Number.class).build();
        
        final OptionGroup group = new OptionGroup();
        group.addOption(optionA);
        
        final CommandLine cmd = CommandLine.builder().get();
        
        final Number[] result = cmd.getParsedOptionValues(group);
        assertNull(result);
    }

    @Test
    public void testDefaultConstructor() {
        final CommandLine cmd = new CommandLine();
        assertNotNull(cmd);
        assertEquals(0, cmd.getArgList().size());
        assertEquals(0, cmd.getOptions().length);
    }

    @Test
    public void testBuilderSetDeprecatedHandler() {
        final Option option = Option.builder("a").deprecated().build();
        
        final CommandLine cmd = CommandLine.builder()
                .setDeprecatedHandler(o -> { })
                .addOption(option)
                .get();
        
        assertTrue(cmd.hasOption("a"));
    }

    // New tests from failed version (repaired)

    /**
     * Test the protected addArg method via a subclass.
     */
    @Test
    public void testProtectedAddArg() throws Exception {
        final CommandLine cmd = new CommandLine();
        cmd.addArg("testArg");
        assertEquals(1, cmd.getArgList().size());
        assertEquals("testArg", cmd.getArgList().get(0));
    }

    /**
     * Test the protected addArg method with null argument via a subclass.
     */
    @Test
    public void testProtectedAddArgNull() throws Exception {
        final CommandLine cmd = new CommandLine();
        cmd.addArg("valid");
        cmd.addArg(null); // Should be ignored
        assertEquals(1, cmd.getArgList().size());
    }

    /**
     * Test the protected addOption method via a subclass.
     */
    @Test
    public void testProtectedAddOption() throws Exception {
        final CommandLine cmd = new CommandLine();
        Option option = Option.builder("t").build();
        cmd.addOption(option);
        assertEquals(1, cmd.getOptions().length);
        assertTrue(cmd.hasOption("t"));
    }

    /**
     * Test the protected addOption method with null argument via a subclass.
     */
    @Test
    public void testProtectedAddOptionNull() throws Exception {
        final CommandLine cmd = new CommandLine();
        Option option = Option.builder("t").build();
        cmd.addOption(option);
        cmd.addOption(null); // Should be ignored
        assertEquals(1, cmd.getOptions().length);
    }

    /**
     * Test getOptionValues with an option that has multiple values.
     * Targets NO_COVERAGE in getOptionValues (lines 491, 502, 514).
     */
    @Test
    public void testGetOptionValuesWithValues() throws Exception {
        Option option = Option.builder("a").hasArgs().build();
        setOptionValues(option, "value1", "value2");

        CommandLine cmd = CommandLine.builder()
                .addOption(option)
                .get();

        String[] values = cmd.getOptionValues("a");
        assertNotNull(values);
        assertEquals(2, values.length);
        assertEquals("value1", values[0]);
        assertEquals("value2", values[1]);
    }

    /**
     * Test getOptionValues with an option that has values but Option object is passed directly.
     * Targets NO_COVERAGE in getOptionValues(Option).
     */
    @Test
    public void testGetOptionValuesWithOptionObject() throws Exception {
        Option option = Option.builder("b").hasArgs().build();
        setOptionValues(option, "val1", "val2");

        CommandLine cmd = CommandLine.builder()
                .addOption(option)
                .get();

        String[] values = cmd.getOptionValues(option);
        assertNotNull(values);
        assertEquals(2, values.length);
    }

    /**
     * Test getOptionProperties with values.
     * Targets NO_COVERAGE in getOptionProperties (lines 309, 314).
     * Targets SURVIVED in getOptionProperties (line 329).
     */
    @Test
    public void testGetOptionPropertiesWithValues() throws Exception {
        // Option with values like -Dkey=val
        Option option = Option.builder("D").hasArgs().build();
        setOptionValues(option, "key1", "value1");

        CommandLine cmd = CommandLine.builder()
                .addOption(option)
                .get();

        Properties props = cmd.getOptionProperties("D");
        assertEquals("value1", props.getProperty("key1"));
        assertFalse(props.isEmpty());
    }

    /**
     * Test getOptionProperties with Option object.
     * Targets NO_COVERAGE in getOptionProperties(Option).
     */
    @Test
    public void testGetOptionPropertiesWithOptionObject() throws Exception {
        Option option = Option.builder("P").hasArgs().build();
        setOptionValues(option, "propKey", "propVal");

        CommandLine cmd = CommandLine.builder()
                .addOption(option)
                .get();

        Properties props = cmd.getOptionProperties(option);
        assertEquals("propVal", props.getProperty("propKey"));
    }

    /**
     * Test getOptionValue when option is a flag (no arguments).
     * Targets EmptyObjectReturnValsMutator survival in getOptionValue.
     * If option is present but has no value, it should return null, not empty string.
     */
    @Test
    public void testGetOptionValueNoArg() {
        Option option = Option.builder("f").build(); // No argument option

        CommandLine cmd = CommandLine.builder()
                .addOption(option)
                .get();

        assertTrue(cmd.hasOption("f"));
        assertNull(cmd.getOptionValue("f"));
    }

    /**
     * Test getOptionValues for deprecated option.
     * Targets SURVIVED mutation: VoidMethodCallMutator (removed call to handleDeprecated) at line 506.
     */
    @Test
    public void testGetOptionValuesDeprecatedHandler() {
        AtomicBoolean handlerCalled = new AtomicBoolean(false);
        
        Option option = Option.builder("d").deprecated().hasArgs().build();

        CommandLine cmd = CommandLine.builder()
                .setDeprecatedHandler(opt -> handlerCalled.set(true))
                .addOption(option)
                .get();

        // Trigger the iteration in getOptionValues
        cmd.getOptionValues("d");
        
        assertTrue("Deprecated handler should have been called", handlerCalled.get());
    }

    /**
     * Test getOptionObject (deprecated) with invalid conversion.
     * Targets NO_COVERAGE: getOptionObject (lines 276, 289, 291).
     */
    @Test
    public void testGetOptionObjectInvalid() throws Exception { // Fixed: Added Exception to throws clause
        // Option with type Integer, but value is not a number
        Option option = Option.builder("n").hasArg(true).type(Integer.class).build();
        // Need to set the value on the option manually for the deprecated method to work as per its logic
        // However, getOptionObject calls getParsedOptionValue which calls getOptionValue.
        // We can use reflection to set value or use parser, but let's try to trigger the catch block.
        // Since setting values on Option is hard without reflection (and we already used it above),
        // let's use a more direct approach if possible.
        // Actually, let's just verify the method handles the ParseException gracefully.
        
        // Setup: Create command line, add option (manually), try to get object.
        // Note: getOptionObject uses getParsedOptionValue. If value is missing or wrong type, it catches.
        
        // We need a value. Let's use reflection on the CommandLine? No.
        // Let's just assume we can set values via our helper.
        setOptionValues(option, "notANumber");

        CommandLine cmd = CommandLine.builder()
                .addOption(option)
                .get();

        // Call deprecated method
        Object result = cmd.getOptionObject("n");
        
        // Should return null due to catch block
        assertNull(result);
    }
    
    /**
     * Test getParsedOptionValue with valid conversion.
     * Ensures NO_COVERAGE for parsing logic is addressed.
     */
    @Test
    public void testGetParsedOptionValueValid() throws Exception {
        Option option = Option.builder("n").hasArg(true).type(Integer.class).build();
        setOptionValues(option, "42");

        CommandLine cmd = CommandLine.builder()
                .addOption(option)
                .get();

        Integer result = cmd.getParsedOptionValue("n");
        assertEquals(42, result.intValue());
    }

    /**
     * Test getOptionValues with OptionGroup selected.
     * Targets SURVIVED mutations at lines 525 and 538.
     */
    @Test
    public void testGetOptionValuesWithSelectedGroup() throws Exception {
        Option optionA = Option.builder("a").hasArgs().build();
        setOptionValues(optionA, "groupVal");

        OptionGroup group = new OptionGroup();
        group.addOption(optionA);
        group.setSelected(optionA);

        CommandLine cmd = CommandLine.builder()
                .addOption(optionA)
                .get();

        String[] values = cmd.getOptionValues(group);
        assertNotNull(values);
        assertEquals("groupVal", values[0]);
    }
    
    /**
     * Test getOptionCount with char argument.
     */
    @Test
    public void testGetOptionCountChar() {
        Option option = Option.builder("c").hasArg(true).build();
        CommandLine cmd = CommandLine.builder()
                .addOption(option)
                .get();
        
        assertEquals(1, cmd.getOptionCount('c'));
        assertEquals(0, cmd.getOptionCount('d'));
    }
}
