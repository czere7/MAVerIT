package org.apache.commons.cli;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class OptionTest {

    @Test
    public void builderCreatesOptionWithConfiguredProperties() {
        final Converter<String, RuntimeException> converter = String::toUpperCase;

        final Option option = Option.builder("a")
                .longOpt("argument")
                .hasArg()
                .argName("value")
                .desc("an argument")
                .required()
                .optionalArg(true)
                .since("1.7")
                .type(Integer.class)
                .valueSeparator('=')
                .converter(converter)
                .get();

        assertEquals("a", option.getOpt());
        assertEquals("argument", option.getLongOpt());
        assertEquals("a", option.getKey());
        assertEquals((int) 'a', option.getId());
        assertEquals(1, option.getArgs());
        assertTrue(option.hasArg());
        assertFalse(option.hasArgs());
        assertTrue(option.hasArgName());
        assertEquals("value", option.getArgName());
        assertEquals("an argument", option.getDescription());
        assertTrue(option.isRequired());
        assertTrue(option.hasOptionalArg());
        assertEquals("1.7", option.getSince());
        assertEquals(Integer.class, option.getType());
        assertEquals('=', option.getValueSeparator());
        assertTrue(option.hasValueSeparator());
        assertSame(converter, option.getConverter());
    }

    @Test
    public void builderCanCreateLongOnlyOption() {
        final Option option = Option.builder()
                .longOpt("verbose")
                .desc("verbose output")
                .get();

        assertNull(option.getOpt());
        assertEquals("verbose", option.getLongOpt());
        assertEquals("verbose", option.getKey());
        assertEquals((int) 'v', option.getId());
        assertFalse(option.hasArg());
    }

    @Test(expected = IllegalStateException.class)
    public void builderRejectsOptionWithoutShortOrLongName() {
        Option.builder().get();
    }

    @Test
    public void constructorsInitializeExpectedValues() {
        final Option withoutArgument = new Option("v", "verbose output");
        assertEquals("v", withoutArgument.getOpt());
        assertNull(withoutArgument.getLongOpt());
        assertFalse(withoutArgument.hasArg());
        assertEquals("verbose output", withoutArgument.getDescription());

        final Option withArgument = new Option("f", "file", true, "input file");
        assertEquals("f", withArgument.getOpt());
        assertEquals("file", withArgument.getLongOpt());
        assertTrue(withArgument.hasArg());
        assertEquals(1, withArgument.getArgs());
    }

    @Test
    public void builderArgumentModesSetArgumentCounts() {
        assertEquals(Option.UNINITIALIZED, Option.builder("a").get().getArgs());
        assertEquals(1, Option.builder("a").hasArg().get().getArgs());
        assertEquals(Option.UNINITIALIZED, Option.builder("a").hasArg(false).get().getArgs());
        assertEquals(Option.UNLIMITED_VALUES, Option.builder("a").hasArgs().get().getArgs());
        assertEquals(3, Option.builder("a").numberOfArgs(3).get().getArgs());

        final Option optional = Option.builder("a").optionalArg(true).get();
        assertEquals(1, optional.getArgs());
        assertTrue(optional.hasOptionalArg());
        assertTrue(optional.hasArg());
    }

    @Test
    public void settersUpdateOptionProperties() {
        final Option option = new Option("x", false, null);

        option.setArgName("name");
        option.setArgs(2);
        option.setDescription("description");
        option.setLongOpt("extended");
        option.setOptionalArg(true);
        option.setRequired(true);
        option.setType(null);
        option.setValueSeparator(':');

        assertEquals("name", option.getArgName());
        assertEquals(2, option.getArgs());
        assertEquals("description", option.getDescription());
        assertEquals("extended", option.getLongOpt());
        assertTrue(option.hasOptionalArg());
        assertTrue(option.isRequired());
        assertEquals(String.class, option.getType());
        assertEquals(':', option.getValueSeparator());
        assertTrue(option.hasValueSeparator());
    }

    @Test
    public void processValueStoresSingleValueAndReportsValueState() {
        final Option option = Option.builder("n").hasArg().get();

        assertNull(option.getValue());
        assertNull(option.getValues());
        assertTrue(option.getValuesList().isEmpty());
        assertTrue(option.requiresArg());

        option.processValue("first");

        assertEquals("first", option.getValue());
        assertEquals("first", option.getValue(0));
        assertEquals("first", option.getValue("default"));
        assertArrayEquals(new String[] {"first"}, option.getValues());
        assertEquals(1, option.getValuesList().size());
        assertFalse(option.requiresArg());
        assertFalse(option.acceptsArg());
    }

    @Test
    public void processValueHonorsArgumentLimit() {
        final Option option = Option.builder("n").numberOfArgs(2).get();

        option.processValue("one");
        option.processValue("two");

        assertArrayEquals(new String[] {"one", "two"}, option.getValues());
        assertFalse(option.acceptsArg());
        assertFalse(option.requiresArg());
    }

    @Test(expected = IllegalArgumentException.class)
    public void processValueRejectsValueWhenOptionHasReachedLimit() {
        final Option option = Option.builder("n").hasArg().get();
        option.processValue("one");
        option.processValue("two");
    }

    @Test
    public void processValueSplitsSeparatorValues() {
        final Option option = Option.builder("D")
                .hasArgs()
                .valueSeparator('=')
                .get();

        option.processValue("key=value=remaining");

        assertArrayEquals(new String[] {"key", "value", "remaining"}, option.getValues());
    }

    @Test
    public void processValueStopsSplittingAtFiniteArgumentCount() {
        final Option option = Option.builder("D")
                .numberOfArgs(2)
                .valueSeparator('=')
                .get();

        option.processValue("key=value=remaining");

        assertArrayEquals(new String[] {"key", "value=remaining"}, option.getValues());
    }

    @Test(expected = IllegalStateException.class)
    public void processValueRejectsValuesForOptionWithoutArguments() {
        Option.builder("v").get().processValue("unexpected");
    }

    @Test(expected = NullPointerException.class)
    public void processValueRejectsNull() {
        Option.builder("v").hasArg().get().processValue(null);
    }

    @Test
    public void unlimitedArgumentsAcceptValues() {
        final Option option = Option.builder("x").hasArgs().get();

        assertTrue(option.hasArg());
        assertTrue(option.hasArgs());
        assertTrue(option.acceptsArg());
        assertTrue(option.requiresArg());

        option.processValue("one");
        option.processValue("two");

        assertTrue(option.acceptsArg());
        assertFalse(option.requiresArg());
        assertArrayEquals(new String[] {"one", "two"}, option.getValues());
    }

    @Test
    public void clearValuesRemovesAllProcessedValues() {
        final Option option = Option.builder("x").hasArgs().get();
        option.processValue("one");
        option.processValue("two");

        option.clearValues();

        assertTrue(option.getValuesList().isEmpty());
        assertNull(option.getValue());
        assertNull(option.getValues());
        assertTrue(option.requiresArg());
    }

    @Test
    public void cloneCopiesValuesWithoutSharingValueList() {
        final Option original = Option.builder("x").hasArgs().longOpt("extended").get();
        original.processValue("one");

        final Option clone = (Option) original.clone();
        clone.processValue("two");

        assertArrayEquals(new String[] {"one"}, original.getValues());
        assertArrayEquals(new String[] {"one", "two"}, clone.getValues());
        assertNotSame(original.getValuesList(), clone.getValuesList());
        assertEquals(original, clone);
        assertEquals(original.hashCode(), clone.hashCode());
    }

    @Test
    public void equalityUsesOnlyShortAndLongNames() {
        final Option first = Option.builder("a").longOpt("alpha").desc("first").required().get();
        final Option second = Option.builder("a").longOpt("alpha").hasArg().desc("second").get();
        final Option differentShort = Option.builder("b").longOpt("alpha").get();
        final Option differentLong = Option.builder("a").longOpt("beta").get();

        assertTrue(first.equals(first));
        assertTrue(first.equals(second));
        assertEquals(first.hashCode(), second.hashCode());
        assertFalse(first.equals(differentShort));
        assertFalse(first.equals(differentLong));
        assertFalse(first.equals(null));
        assertFalse(first.equals("a"));
    }

    @Test
    public void converterDefaultsToConverterForConfiguredTypeAndCanBeReplaced() throws Exception {
        final Option option = Option.builder("n").type(Integer.class).get();

        assertNotNull(option.getConverter());
        assertEquals(Integer.valueOf(42), option.getConverter().apply("42"));

        final Converter<Integer, RuntimeException> converter = Integer::valueOf;
        option.setConverter(converter);

        assertSame(converter, option.getConverter());
        assertEquals(Integer.valueOf(7), option.getConverter().apply("7"));
    }

    @Test
    public void deprecatedOptionsExposeAttributesAndFormatting() {
        final Option option = Option.builder("o")
                .longOpt("old")
                .deprecated()
                .get();

        assertTrue(option.isDeprecated());
        assertNotNull(option.getDeprecated());
        assertTrue(option.toDeprecatedString().contains("Option 'o'"));
        assertTrue(option.toDeprecatedString().contains("'old'"));
        assertTrue(option.toDeprecatedString().contains("Deprecated"));
        assertTrue(option.toString().contains("Deprecated"));
    }

    @Test
    public void nonDeprecatedOptionHasEmptyDeprecatedDescription() {
        final Option option = Option.builder("o").get();

        assertFalse(option.isDeprecated());
        assertNull(option.getDeprecated());
        assertEquals("", option.toDeprecatedString());
    }

    @Test
    public void argumentNameAndSeparatorPresenceHandleEmptyAndZeroValues() {
        final Option option = Option.builder("x").argName("").valueSeparator('\0').get();

        assertFalse(option.hasArgName());
        assertFalse(option.hasValueSeparator());

        option.setArgName("value");
        option.setValueSeparator('|');

        assertTrue(option.hasArgName());
        assertTrue(option.hasValueSeparator());
    }

    @Test
    public void valuesListIsLiveAndSupportsDirectInspection() {
        final Option option = Option.builder("x").hasArgs().get();
        final List<String> values = option.getValuesList();

        values.add("direct");

        assertEquals("direct", option.getValue());
        assertArrayEquals(new String[] {"direct"}, option.getValues());
    }

    @Test
    public void toStringDescribesArgumentCardinality() {
        final Option single = Option.builder("a").longOpt("arg").hasArg().desc("single").get();
        final Option many = Option.builder("m").hasArgs().desc("many").get();
        final Option none = Option.builder("n").desc("none").get();

        assertTrue(single.toString().contains(" [ARG]"));
        assertTrue(many.toString().contains("[ARG...]"));
        assertTrue(none.toString().contains(" :: none :: "));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void addValueIsUnsupported() {
        Option.builder("a").hasArg().get().addValue("value");
    }

    @Test
    public void emptyOptionReturnsNullForIndexedValue() {
        final Option option = Option.builder("a").hasArg().get();

        assertNull(option.getValue(0));
        assertEquals("default", option.getValue("default"));
    }

    @Test
    public void acceptsArgHandlesOptionalArgumentWithoutConfiguredCount() {
        final Option option = new Option("a", false, null);
        option.setOptionalArg(true);

        assertTrue(option.acceptsArg());
        assertFalse(option.requiresArg());
    }

    @Test
    public void zeroArgumentCountDoesNotAcceptArguments() {
        final Option option = Option.builder("a").numberOfArgs(0).get();

        assertFalse(option.hasArg());
        assertFalse(option.hasArgs());
        assertFalse(option.acceptsArg());
        assertFalse(option.requiresArg());
    }

    @Test
    public void uninitializedArgumentCountIsNotMultipleArguments() {
        final Option option = new Option("a", false, null);

        assertEquals(Option.UNINITIALIZED, option.getArgs());
        assertFalse(option.hasArgs());
        assertFalse(option.acceptsArg());
        assertFalse(option.requiresArg());
    }

    @Test
    public void builderTypeNullUsesDefaultStringType() {
        final Option option = Option.builder("a").type(null).get();

        assertEquals(String.class, option.getType());
    }

    @Test
    public void hasLongOptDistinguishesPresentAndAbsentLongNames() {
        final Option withoutLongOpt = Option.builder("a").get();
        final Option withLongOpt = Option.builder("a").longOpt("alpha").get();

        assertFalse(withoutLongOpt.hasLongOpt());
        assertTrue(withLongOpt.hasLongOpt());

        withoutLongOpt.setLongOpt("now-present");
        assertTrue(withoutLongOpt.hasLongOpt());
        withoutLongOpt.setLongOpt(null);
        assertFalse(withoutLongOpt.hasLongOpt());
    }

    @Test
    public void hashCodeIsDerivedFromOptionNamesRatherThanConstantZero() {
        final Option option = Option.builder("a").longOpt("alpha").get();

        assertTrue(option.hashCode() != 0);
        assertEquals(option.hashCode(), Option.builder("a").longOpt("alpha").desc("different").get().hashCode());
        assertFalse(option.hashCode() == Option.builder("b").longOpt("alpha").get().hashCode());
    }

    @Test
    public void requiredFlagCanBeBothTrueAndFalse() {
        final Option option = Option.builder("a").required().get();

        assertTrue(option.isRequired());

        option.setRequired(false);
        assertFalse(option.isRequired());

        option.setRequired(true);
        assertTrue(option.isRequired());
    }

    @Test
    public void setTypeChangesTheConfiguredType() {
        final Option option = Option.builder("a").get();

        assertEquals(String.class, option.getType());
        option.setType(Integer.class);
        assertEquals(Integer.class, option.getType());
        option.setType((Class<?>) null);
        assertEquals(String.class, option.getType());
    }

    @Test
    public void acceptsArgChangesExactlyAtFiniteArgumentBoundary() {
        final Option option = Option.builder("a").numberOfArgs(2).get();

        assertTrue(option.acceptsArg());
        option.processValue("first");
        assertTrue(option.acceptsArg());
        option.processValue("second");
        assertFalse(option.acceptsArg());
        assertEquals(2, option.getValuesList().size());
    }

    @Test
    public void toStringIncludesLongNameOnlyWhenOneIsConfigured() {
        final Option withLongName = Option.builder("a")
                .longOpt("alpha")
                .desc("description")
                .get();
        final Option withoutLongName = Option.builder("b")
                .desc("description")
                .get();

        assertTrue(withLongName.toString().contains("Option a alpha"));
        assertFalse(withoutLongName.toString().contains("alpha"));
        assertTrue(withoutLongName.toString().contains("Option b"));
    }

    @Test
    public void optionalArgumentWithZeroArgumentCountStillAcceptsAnArgument() {
        final Option option = Option.builder("a").numberOfArgs(0).get();
        option.setOptionalArg(true);

        assertFalse(option.hasArg());
        assertFalse(option.hasArgs());
        assertTrue(option.hasOptionalArg());
        assertTrue(option.acceptsArg());
        assertFalse(option.requiresArg());
    }

    @Test
    public void deprecatedObjectSetTypeDelegatesToClassBasedSetter() {
        final Option option = Option.builder("a").get();

        option.setType((Object) Integer.class);
        assertEquals(Integer.class, option.getType());

        option.setType((Object) null);
        assertEquals(String.class, option.getType());
    }
}
