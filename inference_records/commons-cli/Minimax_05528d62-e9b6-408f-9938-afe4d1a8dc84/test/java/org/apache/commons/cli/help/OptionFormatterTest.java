package org.apache.commons.cli.help;

import org.apache.commons.cli.DeprecatedAttributes;
import org.apache.commons.cli.Option;
import org.junit.Test;

import java.util.function.BiFunction;

import static org.junit.Assert.*;

public class OptionFormatterTest {

    @Test
    public void testGetArgNameWithDefaultDelimiters() {
        Option option = Option.builder("f")
            .hasArg()
            .build();
        OptionFormatter formatter = OptionFormatter.from(option);
        assertEquals("<arg>", formatter.getArgName());
    }

    @Test
    public void testGetArgNameWithCustomArgName() {
        Option option = Option.builder("f")
            .hasArg()
            .argName("file")
            .build();
        OptionFormatter formatter = OptionFormatter.from(option);
        assertEquals("<file>", formatter.getArgName());
    }

    @Test
    public void testGetArgNameNoArg() {
        Option option = Option.builder("v")
            .desc("verbose")
            .build();
        OptionFormatter formatter = OptionFormatter.from(option);
        assertEquals("", formatter.getArgName());
    }

    @Test
    public void testGetOpt() {
        Option option = Option.builder("a")
            .hasArg()
            .build();
        OptionFormatter formatter = OptionFormatter.from(option);
        assertEquals("-a", formatter.getOpt());
    }

    @Test
    public void testGetOptWithDefaultPrefix() {
        Option option = Option.builder("t")
            .hasArg()
            .build();
        OptionFormatter formatter = OptionFormatter.from(option);
        assertEquals("-t", formatter.getOpt());
    }

    @Test
    public void testGetOptEmpty() {
        Option option = Option.builder(null)
            .longOpt("test")
            .hasArg()
            .build();
        OptionFormatter formatter = OptionFormatter.from(option);
        assertEquals("", formatter.getOpt());
    }

    @Test
    public void testGetLongOpt() {
        Option option = Option.builder(null)
            .longOpt("longoption")
            .hasArg()
            .build();
        OptionFormatter formatter = OptionFormatter.from(option);
        assertEquals("--longoption", formatter.getLongOpt());
    }

    @Test
    public void testGetLongOptWithDefaultPrefix() {
        Option option = Option.builder(null)
            .longOpt("verbose")
            .build();
        OptionFormatter formatter = OptionFormatter.from(option);
        assertEquals("--verbose", formatter.getLongOpt());
    }

    @Test
    public void testGetLongOptEmpty() {
        Option option = Option.builder("t")
            .build();
        OptionFormatter formatter = OptionFormatter.from(option);
        assertEquals("", formatter.getLongOpt());
    }

    @Test
    public void testGetBothOptShortOnly() {
        Option option = Option.builder("a")
            .hasArg()
            .build();
        OptionFormatter formatter = OptionFormatter.from(option);
        assertEquals("-a", formatter.getBothOpt());
    }

    @Test
    public void testGetBothOptLongOnly() {
        Option option = Option.builder(null)
            .longOpt("all")
            .hasArg()
            .build();
        OptionFormatter formatter = OptionFormatter.from(option);
        assertEquals("--all", formatter.getBothOpt());
    }

    @Test
    public void testGetBothOptBoth() {
        Option option = Option.builder("a")
            .longOpt("all")
            .hasArg()
            .build();
        OptionFormatter formatter = OptionFormatter.from(option);
        assertEquals("-a, --all", formatter.getBothOpt());
    }

    @Test
    public void testGetDescription() {
        Option option = Option.builder("t")
            .desc("test description")
            .build();
        OptionFormatter formatter = OptionFormatter.from(option);
        assertEquals("test description", formatter.getDescription());
    }

    @Test
    public void testGetDescriptionEmpty() {
        Option option = Option.builder("t")
            .build();
        OptionFormatter formatter = OptionFormatter.from(option);
        assertEquals("", formatter.getDescription());
    }

    @Test
    public void testGetDescriptionWithDeprecated() {
        Option option = Option.builder("d")
            .deprecated()
            .desc("deprecated option")
            .build();
        OptionFormatter formatter = OptionFormatter.from(option);
        assertEquals("deprecated option", formatter.getDescription());
    }

    @Test
    public void testGetDescriptionNoDeprecatedFormat() {
        Option option = Option.builder("d")
            .deprecated()
            .desc("deprecated option")
            .build();
        OptionFormatter formatter = OptionFormatter.builder()
            .setDeprecatedFormatFunction(OptionFormatter.NO_DEPRECATED_FORMAT)
            .build(option);
        assertEquals("deprecated option", formatter.getDescription());
    }

    @Test
    public void testGetDescriptionWithComplexDeprecated() {
        Option option = Option.builder("d")
            .deprecated()
            .desc("deprecated option")
            .build();
        OptionFormatter formatter = OptionFormatter.builder()
            .setDeprecatedFormatFunction(OptionFormatter.COMPLEX_DEPRECATED_FORMAT)
            .build(option);
        assertEquals("[Deprecated] deprecated option", formatter.getDescription());
    }

    @Test
    public void testGetSince() {
        Option option = Option.builder("t")
            .since("1.0")
            .build();
        OptionFormatter formatter = OptionFormatter.from(option);
        assertEquals("1.0", formatter.getSince());
    }

    @Test
    public void testGetSinceEmpty() {
        Option option = Option.builder("t")
            .build();
        OptionFormatter formatter = OptionFormatter.from(option);
        assertEquals("--", formatter.getSince());
    }

    @Test
    public void testIsRequired() {
        Option option = Option.builder("r")
            .required()
            .build();
        OptionFormatter formatter = OptionFormatter.from(option);
        assertTrue(formatter.isRequired());
    }

    @Test
    public void testIsNotRequired() {
        Option option = Option.builder("o")
            .build();
        OptionFormatter formatter = OptionFormatter.from(option);
        assertFalse(formatter.isRequired());
    }

    @Test
    public void testToOptional() {
        Option option = Option.builder("o")
            .hasArg()
            .build();
        OptionFormatter formatter = OptionFormatter.from(option);
        assertEquals("[value]", formatter.toOptional("value"));
    }

    @Test
    public void testToOptionalEmpty() {
        Option option = Option.builder("o")
            .hasArg()
            .build();
        OptionFormatter formatter = OptionFormatter.from(option);
        assertEquals("", formatter.toOptional(""));
    }

    @Test
    public void testToOptionalNull() {
        Option option = Option.builder("o")
            .hasArg()
            .build();
        OptionFormatter formatter = OptionFormatter.from(option);
        assertEquals("", formatter.toOptional(null));
    }

    @Test
    public void testToSyntaxOption() {
        Option option = Option.builder("f")
            .hasArg()
            .build();
        OptionFormatter formatter = OptionFormatter.from(option);
        String syntax = formatter.toSyntaxOption();
        assertNotNull(syntax);
        assertTrue(syntax.contains("-f"));
    }

    @Test
    public void testToSyntaxOptionWithRequired() {
        Option option = Option.builder("f")
            .hasArg()
            .build();
        OptionFormatter formatter = OptionFormatter.from(option);
        String syntax = formatter.toSyntaxOption(true);
        assertNotNull(syntax);
        assertTrue(syntax.contains("-f"));
    }

    @Test
    public void testToSyntaxOptionWithOptional() {
        Option option = Option.builder("f")
            .hasArg()
            .build();
        OptionFormatter formatter = OptionFormatter.from(option);
        String syntax = formatter.toSyntaxOption(false);
        assertNotNull(syntax);
        assertTrue(syntax.startsWith("["));
        assertTrue(syntax.endsWith("]"));
    }

    @Test
    public void testToSyntaxOptionWithRequiredOption() {
        Option option = Option.builder("r")
            .required()
            .hasArg()
            .build();
        OptionFormatter formatter = OptionFormatter.from(option);
        String syntaxRequired = formatter.toSyntaxOption(true);
        String syntaxOptional = formatter.toSyntaxOption(false);
        assertTrue(syntaxRequired.contains("-r"));
        assertTrue(syntaxOptional.startsWith("["));
    }

    @Test
    public void testBuilderWithCustomArgNameDelimiters() {
        Option option = Option.builder("f")
            .hasArg()
            .argName("file")
            .build();
        OptionFormatter formatter = OptionFormatter.builder()
            .setArgumentNameDelimiters("{", "}")
            .build(option);
        assertEquals("{file}", formatter.getArgName());
    }

    @Test
    public void testBuilderWithCustomOptPrefix() {
        Option option = Option.builder("f")
            .hasArg()
            .build();
        OptionFormatter formatter = OptionFormatter.builder()
            .setOptPrefix("/")
            .build(option);
        assertEquals("/f", formatter.getOpt());
    }

    @Test
    public void testBuilderWithCustomLongOptPrefix() {
        Option option = Option.builder(null)
            .longOpt("file")
            .hasArg()
            .build();
        OptionFormatter formatter = OptionFormatter.builder()
            .setLongOptPrefix("+")
            .build(option);
        assertEquals("+file", formatter.getLongOpt());
    }

    @Test
    public void testBuilderWithCustomOptSeparator() {
        Option option = Option.builder("f")
            .longOpt("file")
            .hasArg()
            .build();
        OptionFormatter formatter = OptionFormatter.builder()
            .setOptSeparator(" | ")
            .build(option);
        assertEquals("-f | --file", formatter.getBothOpt());
    }

    @Test
    public void testBuilderWithCustomOptArgSeparator() {
        Option option = Option.builder("f")
            .hasArg()
            .argName("file")
            .build();
        OptionFormatter formatter = OptionFormatter.builder()
            .setOptArgSeparator("=")
            .build(option);
        String syntax = formatter.toSyntaxOption(true);
        assertTrue(syntax.contains("="));
    }

    @Test
    public void testBuilderWithCustomOptionalDelimiters() {
        Option option = Option.builder("o")
            .hasArg()
            .build();
        OptionFormatter formatter = OptionFormatter.builder()
            .setOptionalDelimiters("(", ")")
            .build(option);
        String syntax = formatter.toSyntaxOption(false);
        assertTrue(syntax.startsWith("("));
        assertTrue(syntax.endsWith(")"));
    }

    @Test
    public void testBuilderWithCustomDefaultArgName() {
        Option option = Option.builder("f")
            .hasArg()
            .build();
        OptionFormatter formatter = OptionFormatter.builder()
            .setDefaultArgName("input")
            .build(option);
        assertEquals("<input>", formatter.getArgName());
    }

    @Test
    public void testBuilderToArgName() {
        OptionFormatter.Builder builder = OptionFormatter.builder();
        assertEquals("<test>", builder.toArgName("test"));
    }

    @Test
    public void testBuilderToArgNameNull() {
        OptionFormatter.Builder builder = OptionFormatter.builder();
        assertEquals("<>", builder.toArgName(null));
    }

    @Test
    public void testBuilderCopyConstructor() {
        Option option = Option.builder("f")
            .hasArg()
            .argName("file")
            .build();
        OptionFormatter original = OptionFormatter.from(option);
        
        OptionFormatter copied = new OptionFormatter.Builder(original).build(option);
        
        assertEquals(original.getArgName(), copied.getArgName());
        assertEquals(original.getOpt(), copied.getOpt());
    }

    @Test
    public void testToSyntaxOptionWithCustomFunction() {
        Option option = Option.builder("t")
            .hasArg()
            .build();
        BiFunction<OptionFormatter, Boolean, String> customSyntax = (formatter, required) -> "SYNTAX:" + formatter.getOpt();
        
        OptionFormatter formatter = OptionFormatter.builder()
            .setSyntaxFormatFunction(customSyntax)
            .build(option);
        
        assertEquals("SYNTAX:-t", formatter.toSyntaxOption());
    }

    @Test
    public void testGetBothOptWithOptionalArg() {
        Option option = Option.builder("o")
            .hasArg()
            .optionalArg(true)
            .build();
        OptionFormatter formatter = OptionFormatter.from(option);
        String syntax = formatter.toSyntaxOption(false);
        assertTrue(syntax.startsWith("["));
    }

    @Test
    public void testBuilderSetDeprecatedFormatFunction() {
        Option option = Option.builder("d")
            .deprecated()
            .desc("description")
            .build();
        
        OptionFormatter formatter = OptionFormatter.builder()
            .setDeprecatedFormatFunction(o -> "CUSTOM:" + o.getDescription())
            .build(option);
        
        assertEquals("CUSTOM:description", formatter.getDescription());
    }

    @Test
    public void testBuilderGetReturnsNull() {
        OptionFormatter.Builder builder = OptionFormatter.builder();
        assertNull(builder.get());
    }

    @Test
    public void testGetArgNameNoArgWithEmptyDelimiters() {
        Option option = Option.builder("v")
            .desc("verbose")
            .build();
        OptionFormatter formatter = OptionFormatter.builder()
            .setArgumentNameDelimiters("", "")
            .build(option);
        assertEquals("", formatter.getArgName());
    }

    @Test
    public void testToSyntaxOptionNoArgRequired() {
        Option option = Option.builder("v")
            .desc("verbose")
            .build();
        OptionFormatter formatter = OptionFormatter.from(option);
        String syntax = formatter.toSyntaxOption(true);
        assertNotNull(syntax);
        assertEquals("-v", syntax);
    }

    @Test
    public void testToSyntaxOptionNoArgOptional() {
        Option option = Option.builder("v")
            .desc("verbose")
            .build();
        OptionFormatter formatter = OptionFormatter.from(option);
        String syntax = formatter.toSyntaxOption(false);
        assertNotNull(syntax);
        assertEquals("[-v]", syntax);
    }

    @Test
    public void testToSyntaxOptionWithArgNoArgName() {
        Option option = Option.builder("f")
            .hasArg()
            .build();
        OptionFormatter formatter = OptionFormatter.from(option);
        String syntax = formatter.toSyntaxOption(true);
        assertNotNull(syntax);
        assertTrue(syntax.contains("-f <arg>"));
    }

    @Test
    public void testGetDescriptionWithComplexDeprecatedForRemoval() {
        Option option = Option.builder("d")
            .deprecated(DeprecatedAttributes.builder().setForRemoval(true).get())
            .desc("deprecated option")
            .build();
        OptionFormatter formatter = OptionFormatter.builder()
            .setDeprecatedFormatFunction(OptionFormatter.COMPLEX_DEPRECATED_FORMAT)
            .build(option);
        assertEquals("[Deprecated for removal] deprecated option", formatter.getDescription());
    }

    @Test
    public void testGetDescriptionWithComplexDeprecatedSince() {
        Option option = Option.builder("d")
            .deprecated(DeprecatedAttributes.builder().setSince("2.0").get())
            .desc("deprecated option")
            .build();
        OptionFormatter formatter = OptionFormatter.builder()
            .setDeprecatedFormatFunction(OptionFormatter.COMPLEX_DEPRECATED_FORMAT)
            .build(option);
        assertEquals("[Deprecated since 2.0] deprecated option", formatter.getDescription());
    }

    @Test
    public void testGetDescriptionWithComplexDeprecatedDescription() {
        Option option = Option.builder("d")
            .deprecated(DeprecatedAttributes.builder().setDescription("Use new option instead").get())
            .desc("deprecated option")
            .build();
        OptionFormatter formatter = OptionFormatter.builder()
            .setDeprecatedFormatFunction(OptionFormatter.COMPLEX_DEPRECATED_FORMAT)
            .build(option);
        assertEquals("[Deprecated. Use new option instead] deprecated option", formatter.getDescription());
    }

    @Test
    public void testGetDescriptionWithComplexDeprecatedAllFields() {
        Option option = Option.builder("d")
            .deprecated(DeprecatedAttributes.builder()
                .setForRemoval(true)
                .setSince("3.0")
                .setDescription("Use new option instead")
                .get())
            .desc("deprecated option")
            .build();
        OptionFormatter formatter = OptionFormatter.builder()
            .setDeprecatedFormatFunction(OptionFormatter.COMPLEX_DEPRECATED_FORMAT)
            .build(option);
        assertEquals("[Deprecated for removal since 3.0. Use new option instead] deprecated option", formatter.getDescription());
    }

    @Test
    public void testGetDescriptionWithComplexDeprecatedNoMainDescription() {
        Option option = Option.builder("d")
            .deprecated(DeprecatedAttributes.builder()
                .setForRemoval(true)
                .setSince("3.0")
                .setDescription("Use new option instead")
                .get())
            .build();
        OptionFormatter formatter = OptionFormatter.builder()
            .setDeprecatedFormatFunction(OptionFormatter.COMPLEX_DEPRECATED_FORMAT)
            .build(option);
        assertEquals("[Deprecated for removal since 3.0. Use new option instead]", formatter.getDescription());
    }

    @Test
    public void testToSyntaxOptionNullArgSeparator() {
        Option option = Option.builder("f")
            .hasArg()
            .argName("file")
            .build();
        OptionFormatter formatter = OptionFormatter.builder()
            .setOptArgSeparator(null)
            .build(option);
        String syntax = formatter.toSyntaxOption(true);
        assertNotNull(syntax);
        assertTrue(syntax.contains("-f"));
        assertTrue(syntax.contains("file"));
    }

    @Test
    public void testToSyntaxOptionEmptyArgName() {
        Option option = Option.builder("f")
            .hasArg()
            .argName("")
            .build();
        OptionFormatter formatter = OptionFormatter.from(option);
        String syntax = formatter.toSyntaxOption(true);
        assertNotNull(syntax);
        assertTrue(syntax.contains("-f <arg>"));
    }

    @Test
    public void testGetDescriptionWithSimpleDeprecated() {
        Option option = Option.builder("d")
            .deprecated()
            .desc("test description")
            .build();
        OptionFormatter formatter = OptionFormatter.builder()
            .setDeprecatedFormatFunction(OptionFormatter.SIMPLE_DEPRECATED_FORMAT)
            .build(option);
        assertEquals("[Deprecated] test description", formatter.getDescription());
    }
}
