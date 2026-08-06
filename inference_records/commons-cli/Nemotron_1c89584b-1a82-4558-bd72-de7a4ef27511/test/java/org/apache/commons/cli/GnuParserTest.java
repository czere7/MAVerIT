package org.apache.commons.cli;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class GnuParserTest {

    private final GnuParser parser = new GnuParser();

    private Options createOptionsWithDAndFoo() {
        final Options options = new Options();
        options.addOption("D", false, "Define a property");
        options.addOption("foo", true, "Foo option");
        return options;
    }

    private Options createOptionsWithLongAndShort() {
        final Options options = new Options();
        options.addOption("v", "verbose", false, "Verbose mode");
        options.addOption("o", "output", true, "Output file");
        return options;
    }

    @Test
    public void testFlattenEmptyArguments() {
        final Options options = new Options();
        final String[] arguments = {};
        final String[] result = parser.flatten(options, arguments, false);
        assertArrayEquals(new String[0], result);
    }

    @Test
    public void testFlattenNullArguments() {
        final Options options = new Options();
        final String[] result = parser.flatten(options, new String[0], false);
        assertArrayEquals(new String[0], result);
    }

    @Test
    public void testFlattenSimpleOptions() {
        final Options options = createOptionsWithLongAndShort();
        final String[] arguments = {"-v", "--output", "file.txt"};
        final String[] result = parser.flatten(options, arguments, false);
        assertArrayEquals(new String[]{"-v", "--output", "file.txt"}, result);
    }

    @Test
    public void testFlattenDoubleDashStopsProcessing() {
        final Options options = createOptionsWithLongAndShort();
        final String[] arguments = {"-v", "--", "--not-an-option", "-v"};
        final String[] result = parser.flatten(options, arguments, false);
        assertArrayEquals(new String[]{"-v", "--", "--not-an-option", "-v"}, result);
    }

    @Test
    public void testFlattenSingleDashAddedAsIs() {
        final Options options = createOptionsWithLongAndShort();
        final String[] arguments = {"-"};
        final String[] result = parser.flatten(options, arguments, false);
        assertArrayEquals(new String[]{"-"}, result);
    }

    @Test
    public void testFlattenKnownLongOptionWithEquals() {
        final Options options = new Options();
        options.addOption("foo", true, "Foo option");
        final String[] arguments = {"--foo=bar"};
        final String[] result = parser.flatten(options, arguments, false);
        assertArrayEquals(new String[]{"--foo", "bar"}, result);
    }

    @Test
    public void testFlattenKnownShortOptionWithEquals() {
        final Options options = new Options();
        options.addOption("o", true, "Output file");
        final String[] arguments = {"-o=file.txt"};
        final String[] result = parser.flatten(options, arguments, false);
        assertArrayEquals(new String[]{"-o", "file.txt"}, result);
    }

    @Test
    public void testFlattenPropertyStyleOption() {
        final Options options = new Options();
        options.addOption("D", false, "Define property");
        final String[] arguments = {"-Dproperty=value"};
        final String[] result = parser.flatten(options, arguments, false);
        assertArrayEquals(new String[]{"-D", "property=value"}, result);
    }

    @Test
    public void testFlattenPropertyStyleOptionWithMultipleEquals() {
        final Options options = new Options();
        options.addOption("D", false, "Define property");
        final String[] arguments = {"-Dprop=value=more"};
        final String[] result = parser.flatten(options, arguments, false);
        assertArrayEquals(new String[]{"-D", "prop=value=more"}, result);
    }

    @Test
    public void testFlattenUnknownOptionNoEquals() {
        final Options options = createOptionsWithLongAndShort();
        final String[] arguments = {"--unknown"};
        final String[] result = parser.flatten(options, arguments, false);
        assertArrayEquals(new String[]{"--unknown"}, result);
    }

    @Test
    public void testFlattenUnknownOptionWithEqualsButPrefixNotOption() {
        final Options options = createOptionsWithLongAndShort();
        final String[] arguments = {"--unknown=value"};
        final String[] result = parser.flatten(options, arguments, false);
        assertArrayEquals(new String[]{"--unknown=value"}, result);
    }

    @Test
    public void testFlattenStopAtNonOptionTrueWithUnknownOption() {
        final Options options = createOptionsWithLongAndShort();
        final String[] arguments = {"-v", "--unknown", "arg1", "arg2"};
        final String[] result = parser.flatten(options, arguments, true);
        assertArrayEquals(new String[]{"-v", "--unknown", "arg1", "arg2"}, result);
    }

    @Test
    public void testFlattenStopAtNonOptionFalseWithUnknownOption() {
        final Options options = createOptionsWithLongAndShort();
        final String[] arguments = {"-v", "--unknown", "arg1", "arg2"};
        final String[] result = parser.flatten(options, arguments, false);
        assertArrayEquals(new String[]{"-v", "--unknown", "arg1", "arg2"}, result);
    }

    @Test
    public void testFlattenNonOptionArguments() {
        final Options options = createOptionsWithLongAndShort();
        final String[] arguments = {"arg1", "arg2", "arg3"};
        final String[] result = parser.flatten(options, arguments, false);
        assertArrayEquals(new String[]{"arg1", "arg2", "arg3"}, result);
    }

    @Test
    public void testFlattenMixedOptionsAndArguments() {
        final Options options = createOptionsWithLongAndShort();
        final String[] arguments = {"-v", "arg1", "--output", "file.txt", "arg2"};
        final String[] result = parser.flatten(options, arguments, false);
        assertArrayEquals(new String[]{"-v", "arg1", "--output", "file.txt", "arg2"}, result);
    }

    @Test
    public void testFlattenOptionWithValueSeparatedBySpace() {
        final Options options = new Options();
        options.addOption("o", true, "Output file");
        final String[] arguments = {"-o", "file.txt"};
        final String[] result = parser.flatten(options, arguments, false);
        assertArrayEquals(new String[]{"-o", "file.txt"}, result);
    }

    @Test
    public void testFlattenLongOptionWithValueSeparatedBySpace() {
        final Options options = new Options();
        options.addOption("output", true, "Output file");
        final String[] arguments = {"--output", "file.txt"};
        final String[] result = parser.flatten(options, arguments, false);
        assertArrayEquals(new String[]{"--output", "file.txt"}, result);
    }

    @Test
    public void testFlattenMultiplePropertyStyleOptions() {
        final Options options = new Options();
        options.addOption("D", false, "Define property");
        final String[] arguments = {"-Dprop1=value1", "-Dprop2=value2"};
        final String[] result = parser.flatten(options, arguments, false);
        assertArrayEquals(new String[]{"-D", "prop1=value1", "-D", "prop2=value2"}, result);
    }

    @Test
    public void testFlattenKnownOptionThenUnknownThenKnown() {
        final Options options = new Options();
        options.addOption("v", false, "Verbose");
        options.addOption("o", true, "Output");
        final String[] arguments = {"-v", "--unknown", "-o", "file.txt"};
        final String[] result = parser.flatten(options, arguments, false);
        assertArrayEquals(new String[]{"-v", "--unknown", "-o", "file.txt"}, result);
    }

    @Test
    public void testFlattenShortOptionClusterNotSplit() {
        final Options options = new Options();
        options.addOption("v", false, "Verbose");
        options.addOption("x", false, "X option");
        final String[] arguments = {"-vx"};
        final String[] result = parser.flatten(options, arguments, false);
        assertArrayEquals(new String[]{"-v", "x"}, result);
    }

    @Test
    public void testFlattenOptionWithEqualsButOptionDoesNotExist() {
        final Options options = new Options();
        options.addOption("foo", true, "Foo");
        final String[] arguments = {"--bar=baz"};
        final String[] result = parser.flatten(options, arguments, false);
        assertArrayEquals(new String[]{"--bar=baz"}, result);
    }

    @Test
    public void testFlattenOptionWithEqualsAndPrefixExists() {
        final Options options = new Options();
        options.addOption("foo", true, "Foo");
        final String[] arguments = {"--foo=bar=baz"};
        final String[] result = parser.flatten(options, arguments, false);
        assertArrayEquals(new String[]{"--foo", "bar=baz"}, result);
    }

    @Test
    public void testFlattenShortOptionWithEqualsAndPrefixExists() {
        final Options options = new Options();
        options.addOption("o", true, "Output");
        final String[] arguments = {"-o=file.txt"};
        final String[] result = parser.flatten(options, arguments, false);
        assertArrayEquals(new String[]{"-o", "file.txt"}, result);
    }

    @Test
    public void testFlattenPropertyStyleOptionButPrefixNotOption() {
        final Options options = new Options();
        options.addOption("X", false, "X option");
        final String[] arguments = {"-Dproperty=value"};
        final String[] result = parser.flatten(options, arguments, false);
        assertArrayEquals(new String[]{"-Dproperty=value"}, result);
    }

    @Test
    public void testFlattenStopAtNonOptionWithNonOptionArgument() {
        final Options options = createOptionsWithLongAndShort();
        final String[] arguments = {"-v", "nonoption", "--output", "file.txt"};
        final String[] result = parser.flatten(options, arguments, true);
        assertArrayEquals(new String[]{"-v", "nonoption", "--output", "file.txt"}, result);
    }

    @Test
    public void testFlattenWithNullArgumentInArray() {
        final Options options = createOptionsWithLongAndShort();
        final String[] arguments = {"-v", null, "arg"};
        final String[] result = parser.flatten(options, arguments, false);
        assertArrayEquals(new String[]{"-v", "arg"}, result);
    }

    @Test
    public void testFlattenComplexScenario() {
        final Options options = new Options();
        options.addOption("D", false, "Property");
        options.addOption("v", "verbose", false, "Verbose");
        options.addOption("o", "output", true, "Output");
        final String[] arguments = {"-Dprop=value", "-v", "--output=file.txt", "--", "--not-parsed", "-v"};
        final String[] result = parser.flatten(options, arguments, false);
        assertArrayEquals(
            new String[]{"-D", "prop=value", "-v", "--output", "file.txt", "--", "--not-parsed", "-v"},
            result
        );
    }

    @Test
    public void testFlattenOnlyDoubleDash() {
        final Options options = new Options();
        final String[] arguments = {"--"};
        final String[] result = parser.flatten(options, arguments, false);
        assertArrayEquals(new String[]{"--"}, result);
    }

    @Test
    public void testFlattenDoubleDashWithRemainingArguments() {
        final Options options = new Options();
        options.addOption("v", false, "Verbose");
        final String[] arguments = {"--", "-v", "arg1", "arg2"};
        final String[] result = parser.flatten(options, arguments, false);
        assertArrayEquals(new String[]{"--", "-v", "arg1", "arg2"}, result);
    }

    @Test
    public void testFlattenOptionWithEqualsButOptionKeyOnlyTwoChars() {
        final Options options = new Options();
        options.addOption("D", false, "Property");
        final String[] arguments = {"-D=value"};
        final String[] result = parser.flatten(options, arguments, false);
        assertArrayEquals(new String[]{"-D", "value"}, result);
    }

    @Test
    public void testFlattenLongOptionWithEqualsButNoOptionDefined() {
        final Options options = new Options();
        options.addOption("foo", true, "Foo");
        final String[] arguments = {"--foo-bar=value"};
        final String[] result = parser.flatten(options, arguments, false);
        assertArrayEquals(new String[]{"--foo-bar=value"}, result);
    }
}
