package org.apache.commons.cli.help;

import org.apache.commons.cli.DeprecatedAttributes;
import org.apache.commons.cli.Option;
import org.junit.Test;

import static org.junit.Assert.*;

public class OptionFormatterTest {

    @Test
    public void testGetArgNameWithArg() {
        Option option = Option.builder("f")
                .hasArg()
                .argName("file")
                .desc("file option")
                .build();
        OptionFormatter formatter = OptionFormatter.from(option);
        assertEquals("<file>", formatter.getArgName());
    }

    @Test
    public void testGetArgNameWithoutArg() {
        Option option = Option.builder("v")
                .desc("verbose")
                .build();
        OptionFormatter formatter = OptionFormatter.from(option);
        assertEquals("", formatter.getArgName());
    }

    @Test
    public void testGetBothOptWithShortAndLong() {
        Option option = Option.builder("o")
                .longOpt("option")
                .desc("option")
                .build();
        OptionFormatter formatter = OptionFormatter.from(option);
        assertEquals("-o, --option", formatter.getBothOpt());
    }

    @Test
    public void testGetBothOptWithShortOnly() {
        Option option = Option.builder("s")
                .desc("short only")
                .build();
        OptionFormatter formatter = OptionFormatter.from(option);
        assertEquals("-s", formatter.getBothOpt());
        assertEquals("-s", formatter.getOpt());
        assertEquals("", formatter.getLongOpt());
    }

    @Test
    public void testGetDescriptionNonDeprecated() {
        Option option = Option.builder("d")
                .desc("description")
                .build();
        OptionFormatter formatter = OptionFormatter.from(option);
        assertEquals("description", formatter.getDescription());
    }

    @Test
    public void testGetDescriptionDeprecatedSimple() {
        Option option = Option.builder("d")
                .desc("description")
                .deprecated()
                .build();
        OptionFormatter formatter = OptionFormatter.builder()
                .setDeprecatedFormatFunction(OptionFormatter.SIMPLE_DEPRECATED_FORMAT)
                .build(option);
        assertEquals("[Deprecated] description", formatter.getDescription());
    }

    @Test
    public void testGetSinceDefault() {
        Option option = Option.builder("n")
                .desc("none")
                .build();
        OptionFormatter formatter = OptionFormatter.from(option);
        assertEquals("--", formatter.getSince());
    }

    @Test
    public void testGetSinceSet() {
        Option option = Option.builder("n")
                .since("1.0")
                .desc("since")
                .build();
        OptionFormatter formatter = OptionFormatter.from(option);
        assertEquals("1.0", formatter.getSince());
    }

    @Test
    public void testToOptionalWithText() {
        Option option = Option.builder("t")
                .desc("text")
                .build();
        OptionFormatter formatter = OptionFormatter.from(option);
        assertEquals("[text]", formatter.toOptional("text"));
    }

    @Test
    public void testToOptionalEmpty() {
        Option option = Option.builder("e")
                .desc("empty")
                .build();
        OptionFormatter formatter = OptionFormatter.from(option);
        assertEquals("", formatter.toOptional(""));
        assertEquals("", formatter.toOptional(null));
    }

    @Test
    public void testToSyntaxOptionRequired() {
        Option option = Option.builder("f")
                .hasArg()
                .argName("file")
                .required()
                .desc("file")
                .build();
        OptionFormatter formatter = OptionFormatter.from(option);
        assertEquals("-f <file>", formatter.toSyntaxOption());
        assertEquals("[-f <file>]", formatter.toSyntaxOption(false));
    }

    @Test
    public void testToSyntaxOptionOptional() {
        Option option = Option.builder("f")
                .hasArg()
                .argName("file")
                .desc("file")
                .build();
        OptionFormatter formatter = OptionFormatter.from(option);
        assertEquals("[-f <file>]", formatter.toSyntaxOption());
    }

    @Test
    public void testCustomSyntaxFormatFunction() {
        Option option = Option.builder("o")
                .longOpt("option")
                .desc("option")
                .build();
        OptionFormatter formatter = OptionFormatter.builder()
                .setSyntaxFormatFunction((fmt, required) -> "custom:" + fmt.getBothOpt())
                .build(option);
        assertEquals("custom:-o, --option", formatter.toSyntaxOption());
        assertEquals("custom:-o, --option", formatter.toSyntaxOption(false));
    }

    @Test
    public void testBuilderArgumentNameDelimiters() {
        Option option = Option.builder("f")
                .hasArg()
                .argName("file")
                .desc("file")
                .build();
        OptionFormatter.Builder builder = OptionFormatter.builder()
                .setArgumentNameDelimiters("(", ")");
        assertEquals("(file)", builder.toArgName("file"));
        OptionFormatter formatter = builder.build(option);
        assertEquals("(file)", formatter.getArgName());
    }

    @Test
    public void testBuilderOptionalDelimiters() {
        Option option = Option.builder("t")
                .desc("text")
                .build();
        OptionFormatter formatter = OptionFormatter.builder()
                .setOptionalDelimiters("<", ">")
                .build(option);
        assertEquals("<text>", formatter.toOptional("text"));
    }

    /* --------------------------------------------------------------------- */
    /* Additional tests to increase branch coverage                         */
    /* --------------------------------------------------------------------- */

    @Test
    public void testGetBothOptWithLongOnly() {
        Option option = Option.builder()
                .longOpt("long")
                .desc("long only")
                .build();
        OptionFormatter formatter = OptionFormatter.from(option);
        assertEquals("--long", formatter.getLongOpt());
        assertEquals("", formatter.getOpt());
        assertEquals("--long", formatter.getBothOpt());
    }

    @Test
    public void testGetOptWithCustomPrefix() {
        Option option = Option.builder("x")
                .desc("custom opt prefix")
                .build();
        OptionFormatter formatter = OptionFormatter.builder()
                .setOptPrefix("+")
                .build(option);
        assertEquals("+x", formatter.getOpt());
    }

    @Test
    public void testGetLongOptWithCustomPrefix() {
        Option option = Option.builder()
                .longOpt("long")
                .desc("custom long prefix")
                .build();
        OptionFormatter formatter = OptionFormatter.builder()
                .setLongOptPrefix("++")
                .build(option);
        assertEquals("++long", formatter.getLongOpt());
    }

    @Test
    public void testGetArgNameDefaultWhenNotSet() {
        Option option = Option.builder("f")
                .hasArg()
                .desc("arg name default")
                .build();
        OptionFormatter formatter = OptionFormatter.from(option);
        assertEquals("<arg>", formatter.getArgName());
    }

    @Test
    public void testGetDescriptionNullAndNotDeprecated() {
        Option option = Option.builder("a")
                .desc(null)
                .build();
        OptionFormatter formatter = OptionFormatter.from(option);
        assertEquals("", formatter.getDescription());
    }

    @Test
    public void testGetDescriptionDeprecatedComplexDefault() {
        Option option = Option.builder("b")
                .desc("old")
                .deprecated(DeprecatedAttributes.builder().get())
                .build();
        OptionFormatter formatter = OptionFormatter.builder()
                .setDeprecatedFormatFunction(OptionFormatter.COMPLEX_DEPRECATED_FORMAT)
                .build(option);
        assertEquals("[Deprecated] old", formatter.getDescription());
    }

    @Test
    public void testGetDescriptionDeprecatedComplexMissingDescriptionDefault() {
        Option option = Option.builder("c")
                .desc("desc")
                .deprecated(DeprecatedAttributes.builder().get())
                .build();
        OptionFormatter formatter = OptionFormatter.builder()
                .setDeprecatedFormatFunction(OptionFormatter.COMPLEX_DEPRECATED_FORMAT)
                .build(option);
        assertEquals("[Deprecated] desc", formatter.getDescription());
    }

    @Test
    public void testGetSinceEmptyString() {
        Option option = Option.builder("n")
                .since("")
                .desc("empty since")
                .build();
        OptionFormatter formatter = OptionFormatter.from(option);
        assertEquals("--", formatter.getSince());
    }

    @Test
    public void testGetBothOptWithCustomSeparator() {
        Option option = Option.builder("s")
                .longOpt("option")
                .desc("sep")
                .build();
        OptionFormatter formatter = OptionFormatter.builder()
                .setOptSeparator("|")
                .build(option);
        assertEquals("-s|--option", formatter.getBothOpt());
    }

    @Test
    public void testGetBothOptWithShortOnlyCustomSeparator() {
        Option option = Option.builder("s")
                .desc("short only sep")
                .build();
        OptionFormatter formatter = OptionFormatter.builder()
                .setOptSeparator("|")
                .build(option);
        assertEquals("-s", formatter.getBothOpt());
    }

    @Test
    public void testToSyntaxOptionRequiredOverride() {
        Option option = Option.builder("f")
                .hasArg()
                .argName("file")
                .required()
                .desc("file")
                .build();
        OptionFormatter formatter = OptionFormatter.from(option);
        assertEquals("-f <file>", formatter.toSyntaxOption()); // required
        assertEquals("[-f <file>]", formatter.toSyntaxOption(false)); // overridden as optional
    }

    @Test
    public void testToSyntaxOptionRequiredOverrideWithNoArg() {
        Option option = Option.builder("f")
                .required()
                .desc("no arg")
                .build();
        OptionFormatter formatter = OptionFormatter.from(option);
        assertEquals("-f", formatter.toSyntaxOption());
        assertEquals("[-f]", formatter.toSyntaxOption(false));
    }

    /* --------------------------------------------------------------------- */
    /* New tests for uncovered branches                                    */
    /* --------------------------------------------------------------------- */

    @Test
    public void testGetDescriptionDeprecatedSimpleNullDescription() {
        Option option = Option.builder("d")
                .desc(null)
                .deprecated()
                .build();
        OptionFormatter formatter = OptionFormatter.builder()
                .setDeprecatedFormatFunction(OptionFormatter.SIMPLE_DEPRECATED_FORMAT)
                .build(option);
        assertEquals("[Deprecated] ", formatter.getDescription());
    }

    @Test
    public void testGetDescriptionDeprecatedSimpleEmptyDescription() {
        Option option = Option.builder("d")
                .desc("")
                .deprecated()
                .build();
        OptionFormatter formatter = OptionFormatter.builder()
                .setDeprecatedFormatFunction(OptionFormatter.SIMPLE_DEPRECATED_FORMAT)
                .build(option);
        assertEquals("[Deprecated] ", formatter.getDescription());
    }

    /* Removed tests that rely on DeprecatedAttributes.Builder methods not present in the current API */

    @Test
    public void testGetDescriptionDeprecatedWithNoDeprecatedFormat() {
        Option option = Option.builder("d")
                .desc("optDesc")
                .deprecated()
                .build();
        OptionFormatter formatter = OptionFormatter.builder()
                .setDeprecatedFormatFunction(OptionFormatter.NO_DEPRECATED_FORMAT)
                .build(option);
        assertEquals("optDesc", formatter.getDescription());
    }

    @Test
    public void testGetDescriptionNonDeprecatedEmptyDescription() {
        Option option = Option.builder("d")
                .desc("")
                .build();
        OptionFormatter formatter = OptionFormatter.from(option);
        assertEquals("", formatter.getDescription());
    }

    @Test
    public void testGetSinceNull() {
        Option option = Option.builder("x")
                .since(null)
                .desc("desc")
                .build();
        OptionFormatter formatter = OptionFormatter.from(option);
        assertEquals("--", formatter.getSince());
    }

    @Test
    public void testGetOptEmpty() {
        Option option = Option.builder()
                .longOpt("long")
                .desc("desc")
                .build();
        OptionFormatter formatter = OptionFormatter.from(option);
        assertEquals("", formatter.getOpt());
        assertEquals("--long", formatter.getLongOpt());
        assertEquals("--long", formatter.getBothOpt());
    }

    @Test
    public void testSyntaxFormatLongOnlyWithArgRequired() {
        Option option = Option.builder()
                .longOpt("long")
                .hasArg()
                .argName("file")
                .desc("desc")
                .build();
        OptionFormatter formatter = OptionFormatter.from(option);
        assertEquals("--long <file>", formatter.toSyntaxOption(true));
        assertEquals("[--long <file>]", formatter.toSyntaxOption(false));
        assertEquals("[--long <file>]", formatter.toSyntaxOption());
    }

    @Test
    public void testSyntaxFormatLongOnlyNoArgRequired() {
        Option option = Option.builder()
                .longOpt("long")
                .desc("desc")
                .build();
        OptionFormatter formatter = OptionFormatter.from(option);
        assertEquals("--long", formatter.toSyntaxOption(true));
        assertEquals("[--long]", formatter.toSyntaxOption(false));
        assertEquals("[--long]", formatter.toSyntaxOption());
    }
}
