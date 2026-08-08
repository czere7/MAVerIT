package org.apache.commons.cli.help;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.util.function.BiFunction;

import org.apache.commons.cli.DeprecatedAttributes;
import org.apache.commons.cli.Option;
import org.junit.Test;

public class OptionFormatterTest {

    @Test
    public void defaultFormattingIncludesOptionNamesArgumentNameAndDescription() {
        final Option option = Option.builder("v")
                .longOpt("verbose")
                .hasArg()
                .argName("file")
                .desc("enable verbose output")
                .get();

        final OptionFormatter formatter = OptionFormatter.from(option);

        assertEquals("-v", formatter.getOpt());
        assertEquals("--verbose", formatter.getLongOpt());
        assertEquals("-v, --verbose", formatter.getBothOpt());
        assertEquals("<file>", formatter.getArgName());
        assertEquals("enable verbose output", formatter.getDescription());
        assertFalse(formatter.isRequired());
        assertEquals("[-v <file>]", formatter.toSyntaxOption());
        assertEquals("-v <file>", formatter.toSyntaxOption(true));
    }

    @Test
    public void formattingHandlesAnOptionWithOnlyALongName() {
        final Option option = Option.builder()
                .longOpt("help")
                .desc("display help")
                .get();

        final OptionFormatter formatter = OptionFormatter.from(option);

        assertEquals("", formatter.getOpt());
        assertEquals("--help", formatter.getLongOpt());
        assertEquals("--help", formatter.getBothOpt());
        assertEquals("", formatter.getArgName());
        assertEquals("[--help]", formatter.toSyntaxOption());
    }

    @Test
    public void formattingUsesDefaultArgumentNameWhenOptionHasAnArgumentWithoutName() {
        final Option option = Option.builder("f")
                .hasArg()
                .get();

        final OptionFormatter formatter = OptionFormatter.from(option);

        assertEquals("<arg>", formatter.getArgName());
        assertEquals("[-f <arg>]", formatter.toSyntaxOption());
    }

    @Test
    public void requiredOptionIsNotWrappedInOptionalDelimiters() {
        final Option option = Option.builder("f")
                .hasArg()
                .required()
                .get();

        final OptionFormatter formatter = OptionFormatter.from(option);

        assertTrue(formatter.isRequired());
        assertEquals("-f <arg>", formatter.toSyntaxOption());
        assertEquals("[-f <arg>]", formatter.toSyntaxOption(false));
    }

    @Test
    public void customBuilderValuesControlOptionAndArgumentFormatting() {
        final Option option = Option.builder("o")
                .longOpt("output")
                .hasArg()
                .argName("path")
                .get();

        final OptionFormatter formatter = OptionFormatter.builder()
                .setOptPrefix("/")
                .setLongOptPrefix("--long-")
                .setOptSeparator(" | ")
                .setOptArgSeparator("=")
                .setArgumentNameDelimiters("{", "}")
                .setOptionalDelimiters("(", ")")
                .build(option);

        assertEquals("/o", formatter.getOpt());
        assertEquals("--long-output", formatter.getLongOpt());
        assertEquals("/o | --long-output", formatter.getBothOpt());
        assertEquals("{path}", formatter.getArgName());
        assertEquals("(/o={path})", formatter.toSyntaxOption());
    }

    @Test
    public void nullCustomValuesUseBuilderDefaults() {
        final Option option = Option.builder("x")
                .hasArg()
                .get();

        final OptionFormatter formatter = OptionFormatter.builder()
                .setOptPrefix(null)
                .setLongOptPrefix(null)
                .setOptSeparator(null)
                .setOptArgSeparator(null)
                .setDefaultArgName(null)
                .setArgumentNameDelimiters(null, null)
                .setOptionalDelimiters(null, null)
                .build(option);

        assertEquals("x", formatter.getOpt());
        assertEquals("", formatter.getLongOpt());
        assertEquals("arg", formatter.getArgName());
        assertEquals("xarg", formatter.toSyntaxOption());
    }

    @Test
    public void toArgNameWrapsTextAndTreatsNullAsEmpty() {
        final OptionFormatter.Builder builder = OptionFormatter.builder()
                .setArgumentNameDelimiters("[", "]");

        assertEquals("[value]", builder.toArgName("value"));
        assertEquals("[]", builder.toArgName(null));
    }

    @Test
    public void optionalTextIsWrappedOnlyWhenNonEmpty() {
        final OptionFormatter formatter = OptionFormatter.builder()
                .setOptionalDelimiters("<", ">")
                .build(Option.builder("x").get());

        assertEquals("<value>", formatter.toOptional("value"));
        assertEquals("", formatter.toOptional(""));
        assertEquals("", formatter.toOptional(null));
    }

    @Test
    public void descriptionDefaultsToEmptyStringForNonDeprecatedOption() {
        final Option option = Option.builder("q").get();

        assertEquals("", OptionFormatter.from(option).getDescription());
    }

    @Test
    public void deprecatedDescriptionUsesConfiguredFunction() {
        final Option option = Option.builder("d")
                .desc("legacy option")
                .deprecated()
                .get();

        final OptionFormatter simple = OptionFormatter.builder()
                .setDeprecatedFormatFunction(OptionFormatter.SIMPLE_DEPRECATED_FORMAT)
                .build(option);

        final OptionFormatter none = OptionFormatter.builder()
                .setDeprecatedFormatFunction(OptionFormatter.NO_DEPRECATED_FORMAT)
                .build(option);

        assertEquals("[Deprecated] legacy option", simple.getDescription());
        assertEquals("legacy option", none.getDescription());
    }

    @Test
    public void sinceDefaultsToLongOptionPrefixWhenUnset() {
        final Option withoutSince = Option.builder("a").get();
        final Option withSince = Option.builder("b")
                .since("2.0")
                .get();

        assertEquals("--", OptionFormatter.from(withoutSince).getSince());
        assertEquals("2.0", OptionFormatter.from(withSince).getSince());
    }

    @Test
    public void customSyntaxFunctionReceivesFormatterAndRequiredFlag() {
        final Option option = Option.builder("v").get();
        final OptionFormatter formatter = OptionFormatter.builder()
                .setSyntaxFormatFunction((f, required) -> f.getOpt() + ":" + required)
                .build(option);

        assertEquals("-v:false", formatter.toSyntaxOption());
        assertEquals("-v:true", formatter.toSyntaxOption(true));
    }

    @Test
    public void formattingAnOptionWithOnlyAShortNameDoesNotAddSeparator() {
        final Option option = Option.builder("v").get();

        assertEquals("-v", OptionFormatter.from(option).getBothOpt());
    }

    @Test
    public void complexDeprecatedDescriptionIncludesAllAvailableAttributes() {
        final DeprecatedAttributes deprecated = DeprecatedAttributes.builder()
                .setDescription("use another option")
                .setSince("2.0")
                .setForRemoval(true)
                .get();

        final Option option = Option.builder("d")
                .desc("legacy option")
                .deprecated(deprecated)
                .get();

        final OptionFormatter formatter = OptionFormatter.builder()
                .setDeprecatedFormatFunction(OptionFormatter.COMPLEX_DEPRECATED_FORMAT)
                .build(option);

        assertEquals("[Deprecated for removal since 2.0. use another option] legacy option",
                formatter.getDescription());
    }

    @Test
    public void complexDeprecatedDescriptionOmitsEmptyAttributesAndDescription() {
        final Option option = Option.builder("d")
                .deprecated()
                .get();

        final OptionFormatter formatter = OptionFormatter.builder()
                .setDeprecatedFormatFunction(OptionFormatter.COMPLEX_DEPRECATED_FORMAT)
                .build(option);

        assertEquals("[Deprecated]", formatter.getDescription());
    }

    @Test
    public void builderSupplierCurrentlyReturnsNull() {
        assertNull(OptionFormatter.builder().get());
    }

    @Test
    public void builderCopyPreservesFormattingConfigurationAndSyntaxFunction() {
        final Option originalOption = Option.builder("o")
                .longOpt("output")
                .hasArg()
                .argName("path")
                .get();

        final OptionFormatter original = OptionFormatter.builder()
                .setOptPrefix("/")
                .setLongOptPrefix("--long-")
                .setOptSeparator(" | ")
                .setOptArgSeparator("=")
                .setArgumentNameDelimiters("{", "}")
                .setOptionalDelimiters("(", ")")
                .setSyntaxFormatFunction((formatter, required) ->
                        formatter.getBothOpt() + ":" + formatter.getArgName() + ":" + required)
                .build(originalOption);

        final OptionFormatter copied = new OptionFormatter.Builder(original).build(originalOption);

        assertEquals("/o", copied.getOpt());
        assertEquals("--long-output", copied.getLongOpt());
        assertEquals("/o | --long-output", copied.getBothOpt());
        assertEquals("{path}", copied.getArgName());
        assertEquals("/o | --long-output:{path}:false", copied.toSyntaxOption());
    }

    @Test
    public void defaultSyntaxFunctionUsesOptionRequiredStateWhenRequiredArgumentIsNull() throws Exception {
        final Option option = Option.builder("x").get();
        final OptionFormatter formatter = OptionFormatter.from(option);

        final Field syntaxFunctionField = OptionFormatter.class.getDeclaredField("syntaxFormatFunction");
        syntaxFunctionField.setAccessible(true);

        @SuppressWarnings("unchecked")
        final BiFunction<OptionFormatter, Boolean, String> syntaxFunction =
                (BiFunction<OptionFormatter, Boolean, String>) syntaxFunctionField.get(formatter);

        assertEquals("[-x]", syntaxFunction.apply(formatter, null));
    }
}
