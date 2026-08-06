package org.apache.commons.cli.help;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.apache.commons.cli.Option;
import org.junit.Test;

public class OptionFormatterTest {

    @Test
    public void testFromWithDefaultBuilder() {
        Option option = Option.builder("f").longOpt("file").hasArg().desc("Input file").build();
        OptionFormatter formatter = OptionFormatter.from(option);

        assertEquals("-f", formatter.getOpt());
        assertEquals("--file", formatter.getLongOpt());
        assertEquals("-f, --file", formatter.getBothOpt());
        assertEquals("<arg>", formatter.getArgName());
        assertEquals("Input file", formatter.getDescription());
        assertFalse(formatter.isRequired());
    }

    @Test
    public void testBuilderCustomPrefixes() {
        Option option = Option.builder("x").longOpt("extended").build();
        OptionFormatter formatter = OptionFormatter.builder()
                .setOptPrefix("/")
                .setLongOptPrefix("--")
                .build(option);

        assertEquals("/x", formatter.getOpt());
        assertEquals("--extended", formatter.getLongOpt());
    }

    @Test
    public void testBuilderCustomSeparators() {
        Option option = Option.builder("o").longOpt("output").hasArg().argName("FILE").build();
        OptionFormatter formatter = OptionFormatter.builder()
                .setOptSeparator(" | ")
                .setOptArgSeparator("=")
                .build(option);

        assertEquals("-o | --output", formatter.getBothOpt());
        assertEquals("<FILE>", formatter.getArgName());
    }

    @Test
    public void testBuilderCustomDelimiters() {
        Option option = Option.builder("d").longOpt("dir").hasArg().build();
        OptionFormatter formatter = OptionFormatter.builder()
                .setArgumentNameDelimiters("{", "}")
                .setOptionalDelimiters("(", ")")
                .build(option);

        assertEquals("{arg}", formatter.getArgName());
        assertEquals("(-d, --dir)", formatter.toOptional("-d, --dir"));
    }

    @Test
    public void testBuilderCustomDefaultArgName() {
        Option option = Option.builder("i").longOpt("input").hasArg().build();
        OptionFormatter formatter = OptionFormatter.builder()
                .setDefaultArgName("PATH")
                .build(option);

        assertEquals("<PATH>", formatter.getArgName());
    }

    @Test
    public void testOptionWithShortOptOnly() {
        Option option = Option.builder("h").desc("Help").build();
        OptionFormatter formatter = OptionFormatter.from(option);

        assertEquals("-h", formatter.getOpt());
        assertEquals("", formatter.getLongOpt());
        assertEquals("-h", formatter.getBothOpt());
    }

    @Test
    public void testOptionWithLongOptOnly() {
        Option option = Option.builder().longOpt("help").desc("Help").build();
        OptionFormatter formatter = OptionFormatter.from(option);

        assertEquals("", formatter.getOpt());
        assertEquals("--help", formatter.getLongOpt());
        assertEquals("--help", formatter.getBothOpt());
    }

    @Test
    public void testOptionWithBothOpts() {
        Option option = Option.builder("v").longOpt("version").desc("Version").build();
        OptionFormatter formatter = OptionFormatter.from(option);

        assertEquals("-v", formatter.getOpt());
        assertEquals("--version", formatter.getLongOpt());
        assertEquals("-v, --version", formatter.getBothOpt());
    }

    @Test
    public void testOptionWithoutArgs() {
        Option option = Option.builder("q").longOpt("quiet").desc("Quiet mode").build();
        OptionFormatter formatter = OptionFormatter.from(option);

        assertEquals("", formatter.getArgName());
        assertFalse(option.hasArg());
    }

    @Test
    public void testOptionWithArgsAndCustomArgName() {
        Option option = Option.builder("o").longOpt("output").hasArg().argName("FILE").build();
        OptionFormatter formatter = OptionFormatter.from(option);

        assertEquals("<FILE>", formatter.getArgName());
        assertTrue(option.hasArg());
    }

    @Test
    public void testOptionWithArgsButNoArgNameUsesDefault() {
        Option option = Option.builder("i").longOpt("input").hasArg().build();
        OptionFormatter formatter = OptionFormatter.from(option);

        assertEquals("<arg>", formatter.getArgName());
    }

    @Test
    public void testRequiredOption() {
        Option option = Option.builder("r").required().desc("Required").build();
        OptionFormatter formatter = OptionFormatter.from(option);

        assertTrue(formatter.isRequired());
        assertEquals("-r", formatter.toSyntaxOption());
    }

    @Test
    public void testOptionalOption() {
        Option option = Option.builder("o").longOpt("optional").desc("Optional").build();
        OptionFormatter formatter = OptionFormatter.from(option);

        assertFalse(formatter.isRequired());
        assertEquals("[-o]", formatter.toSyntaxOption());
    }

    @Test
    public void testToSyntaxOptionWithExplicitRequired() {
        Option option = Option.builder("x").longOpt("explicit").desc("Test").build();
        OptionFormatter formatter = OptionFormatter.from(option);

        assertEquals("-x", formatter.toSyntaxOption(true));
        assertEquals("[-x]", formatter.toSyntaxOption(false));
    }

    @Test
    public void testDescriptionWithoutDeprecation() {
        Option option = Option.builder("d").desc("Normal description").build();
        OptionFormatter formatter = OptionFormatter.from(option);

        assertEquals("Normal description", formatter.getDescription());
    }

    @Test
    public void testNonDeprecatedOptionWithDeprecatedFormatFunction() {
        Option option = Option.builder("n").desc("New option").build();
        OptionFormatter formatter = OptionFormatter.builder()
                .setDeprecatedFormatFunction(OptionFormatter.SIMPLE_DEPRECATED_FORMAT)
                .build(option);

        assertEquals("New option", formatter.getDescription());
    }

    @Test
    public void testGetSinceWithValue() {
        Option option = Option.builder("s").since("2.0").build();
        OptionFormatter formatter = OptionFormatter.from(option);

        assertEquals("2.0", formatter.getSince());
    }

    @Test
    public void testGetSinceWithoutValueReturnsDefault() {
        Option option = Option.builder("s").build();
        OptionFormatter formatter = OptionFormatter.from(option);

        assertEquals("--", formatter.getSince());
    }

    @Test
    public void testToOptionalWithEmptyString() {
        Option option = Option.builder("e").build();
        OptionFormatter formatter = OptionFormatter.from(option);

        assertEquals("", formatter.toOptional(""));
        assertEquals("", formatter.toOptional(null));
    }

    @Test
    public void testToOptionalWithText() {
        Option option = Option.builder("t").build();
        OptionFormatter formatter = OptionFormatter.from(option);

        assertEquals("[text]", formatter.toOptional("text"));
    }

    @Test
    public void testBuilderCopyFromExistingFormatter() {
        Option option1 = Option.builder("a").longOpt("alpha").hasArg().argName("ARG").required().build();
        OptionFormatter formatter1 = OptionFormatter.builder()
                .setOptPrefix("/")
                .setLongOptPrefix("--")
                .setOptSeparator("; ")
                .setArgumentNameDelimiters("{", "}")
                .setOptionalDelimiters("(", ")")
                .setDefaultArgName("DEFAULT")
                .build(option1);

        Option option2 = Option.builder("b").longOpt("beta").hasArg().build();
        OptionFormatter formatter2 = new OptionFormatter.Builder(formatter1).build(option2);

        assertEquals("/b", formatter2.getOpt());
        assertEquals("--beta", formatter2.getLongOpt());
        assertEquals("/b; --beta", formatter2.getBothOpt());
        assertEquals("{DEFAULT}", formatter2.getArgName());
        assertEquals("(/b; --beta)", formatter2.toOptional("/b; --beta"));
    }

    @Test
    public void testBuilderSettersReturnThis() {
        OptionFormatter.Builder builder = OptionFormatter.builder();
        assertSame(builder, builder.setOptPrefix("-"));
        assertSame(builder, builder.setLongOptPrefix("--"));
        assertSame(builder, builder.setOptSeparator(", "));
        assertSame(builder, builder.setOptArgSeparator(" "));
        assertSame(builder, builder.setArgumentNameDelimiters("<", ">"));
        assertSame(builder, builder.setOptionalDelimiters("[", "]"));
        assertSame(builder, builder.setDefaultArgName("arg"));
        assertSame(builder, builder.setDeprecatedFormatFunction(OptionFormatter.NO_DEPRECATED_FORMAT));
        assertSame(builder, builder.setSyntaxFormatFunction((f, r) -> ""));
    }

    @Test
    public void testCustomSyntaxFormatFunction() {
        Option option = Option.builder("c").longOpt("custom").hasArg().argName("VAL").build();
        OptionFormatter formatter = OptionFormatter.builder()
                .setSyntaxFormatFunction((f, required) -> "CUSTOM:" + f.getBothOpt() + (required ? "!" : "?"))
                .build(option);

        assertEquals("CUSTOM:-c, --custom!", formatter.toSyntaxOption(true));
        assertEquals("CUSTOM:-c, --custom?", formatter.toSyntaxOption(false));
    }

    @Test
    public void testDefaultSyntaxFormatFunctionRequired() {
        Option option = Option.builder("r").longOpt("required").hasArg().argName("ARG").required().build();
        OptionFormatter formatter = OptionFormatter.from(option);

        assertEquals("-r <ARG>", formatter.toSyntaxOption());
    }

    @Test
    public void testDefaultSyntaxFormatFunctionOptional() {
        Option option = Option.builder("o").longOpt("optional").hasArg().argName("ARG").build();
        OptionFormatter formatter = OptionFormatter.from(option);

        assertEquals("[-o <ARG>]", formatter.toSyntaxOption());
    }

    @Test
    public void testDefaultSyntaxFormatFunctionNoArgs() {
        Option option = Option.builder("f").longOpt("flag").build();
        OptionFormatter formatter = OptionFormatter.from(option);

        assertEquals("-f", formatter.toSyntaxOption(true));
        assertEquals("[-f]", formatter.toSyntaxOption(false));
    }

    @Test
    public void testStaticConstants() {
        assertEquals("arg", OptionFormatter.DEFAULT_ARG_NAME);
        assertEquals("usage: ", OptionFormatter.DEFAULT_SYNTAX_PREFIX);
        assertEquals("-", OptionFormatter.DEFAULT_OPT_PREFIX);
        assertEquals("--", OptionFormatter.DEFAULT_LONG_OPT_PREFIX);
        assertEquals(", ", OptionFormatter.DEFAULT_OPT_SEPARATOR);
        assertEquals(" ", OptionFormatter.DEFAULT_OPT_ARG_SEPARATOR);
    }

    @Test
    public void testOptionWithMultipleArgs() {
        Option option = Option.builder("m").longOpt("multi").hasArg(true).argName("FILES").build();
        OptionFormatter formatter = OptionFormatter.from(option);

        assertTrue(option.hasArg());
        assertEquals("<FILES>", formatter.getArgName());
    }

    @Test
    public void testBuilderGetReturnsNull() {
        OptionFormatter.Builder builder = OptionFormatter.builder();
        assertNull(builder.get());
    }

    // --- Valid new tests for branch coverage improvement ---

    @Test
    public void testGetDescriptionWithDeprecatedOptionSimpleFormat() {
        Option option = Option.builder("d")
                .desc("Description")
                .deprecated()
                .build();
        OptionFormatter formatter = OptionFormatter.builder()
                .setDeprecatedFormatFunction(OptionFormatter.SIMPLE_DEPRECATED_FORMAT)
                .build(option);

        assertEquals("[Deprecated] Description", formatter.getDescription());
    }

    @Test
    public void testGetDescriptionWithDeprecatedOptionSimpleFormatNoDescription() {
        Option option = Option.builder("d")
                .deprecated()
                .build();
        OptionFormatter formatter = OptionFormatter.builder()
                .setDeprecatedFormatFunction(OptionFormatter.SIMPLE_DEPRECATED_FORMAT)
                .build(option);

        assertEquals("[Deprecated] ", formatter.getDescription());
    }

    @Test
    public void testDefaultSyntaxFormatFunctionExplicitTrueWithArg() {
        Option option = Option.builder("x").longOpt("explicit").hasArg().argName("VAL").build();
        OptionFormatter formatter = OptionFormatter.from(option);

        assertEquals("-x <VAL>", formatter.toSyntaxOption(true));
    }

    @Test
    public void testDefaultSyntaxFormatFunctionExplicitFalseWithArg() {
        Option option = Option.builder("x").longOpt("explicit").hasArg().argName("VAL").build();
        OptionFormatter formatter = OptionFormatter.from(option);

        assertEquals("[-x <VAL>]", formatter.toSyntaxOption(false));
    }

    @Test
    public void testDefaultSyntaxFormatFunctionExplicitTrueWithoutArg() {
        Option option = Option.builder("x").longOpt("explicit").build();
        OptionFormatter formatter = OptionFormatter.from(option);

        assertEquals("-x", formatter.toSyntaxOption(true));
    }

    @Test
    public void testDefaultSyntaxFormatFunctionExplicitFalseWithoutArg() {
        Option option = Option.builder("x").longOpt("explicit").build();
        OptionFormatter formatter = OptionFormatter.from(option);

        assertEquals("[-x]", formatter.toSyntaxOption(false));
    }

    @Test
    public void testGetDescriptionDefaultFormatWithDeprecatedOption() {
        Option option = Option.builder("d")
                .desc("Description")
                .deprecated()
                .build();
        OptionFormatter formatter = OptionFormatter.from(option);

        assertEquals("Description", formatter.getDescription());
    }

    @Test
    public void testSimpleDeprecatedFormatWithNullDescription() {
        Option option = Option.builder("d")
                .deprecated()
                .build();
        OptionFormatter formatter = OptionFormatter.builder()
                .setDeprecatedFormatFunction(OptionFormatter.SIMPLE_DEPRECATED_FORMAT)
                .build(option);

        assertEquals("[Deprecated] ", formatter.getDescription());
    }

    // --- Additional valid tests for branch coverage ---

    @Test
    public void testDefaultSyntaxFormatFunctionWithDefaultArgName() {
        Option option = Option.builder("x").longOpt("explicit").hasArg().build();
        OptionFormatter formatter = OptionFormatter.from(option);

        assertEquals("-x <arg>", formatter.toSyntaxOption(true));
        assertEquals("[-x <arg>]", formatter.toSyntaxOption(false));
    }

    @Test
    public void testBuilderCopyFromFormatterWithCustomSyntaxFunction() {
        Option option1 = Option.builder("a").longOpt("alpha").hasArg().argName("ARG").required().build();
        OptionFormatter formatter1 = OptionFormatter.builder()
                .setSyntaxFormatFunction((f, required) -> "CUSTOM:" + f.getBothOpt())
                .build(option1);

        Option option2 = Option.builder("b").longOpt("beta").build();
        OptionFormatter formatter2 = new OptionFormatter.Builder(formatter1).build(option2);

        assertEquals("CUSTOM:-b, --beta", formatter2.toSyntaxOption(true));
        assertEquals("CUSTOM:-b, --beta", formatter2.toSyntaxOption(false));
    }

    @Test
    public void testGetArgNameWhenHasArgFalseReturnsEmpty() {
        Option option = Option.builder("f").longOpt("flag").build();
        OptionFormatter formatter = OptionFormatter.from(option);

        assertEquals("", formatter.getArgName());
        assertFalse(option.hasArg());
    }

    @Test
    public void testGetBothOptWithOnlyLongOpt() {
        Option option = Option.builder().longOpt("help").desc("Help").build();
        OptionFormatter formatter = OptionFormatter.from(option);

        assertEquals("--help", formatter.getBothOpt());
    }

    @Test
    public void testGetBothOptWithOnlyShortOpt() {
        Option option = Option.builder("h").desc("Help").build();
        OptionFormatter formatter = OptionFormatter.from(option);

        assertEquals("-h", formatter.getBothOpt());
    }

    @Test
    public void testToSyntaxOptionWithCustomOptionalDelimiters() {
        Option option = Option.builder("o").longOpt("optional").hasArg().argName("FILE").build();
        OptionFormatter formatter = OptionFormatter.builder()
                .setOptionalDelimiters("{", "}")
                .build(option);

        assertEquals("{-o <FILE>}", formatter.toSyntaxOption());
    }

    @Test
    public void testToSyntaxOptionWithCustomOptArgSeparator() {
        Option option = Option.builder("o").longOpt("output").hasArg().argName("FILE").build();
        OptionFormatter formatter = OptionFormatter.builder()
                .setOptArgSeparator("=")
                .build(option);

        assertEquals("[-o=<FILE>]", formatter.toSyntaxOption());
    }
}
