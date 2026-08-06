import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegressionTest0 {

    public static boolean debug = false;

    public void assertBooleanArrayEquals(boolean[] expectedArray, boolean[] actualArray) {
        if (expectedArray.length != actualArray.length) {
            throw new AssertionError("Array lengths differ: " + expectedArray.length + " != " + actualArray.length);
        }
        for (int i = 0; i < expectedArray.length; i++) {
            if (expectedArray[i] != actualArray[i]) {
                throw new AssertionError("Arrays differ at index " + i + ": " + expectedArray[i] + " != " + actualArray[i]);
            }
        }
    }

    @Test
    public void test001() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test001");
        org.apache.commons.cli.OptionBuilder optionBuilder1 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs((int) (byte) 1);
        org.junit.Assert.assertNotNull(optionBuilder1);
    }

    @Test
    public void test002() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test002");
        java.lang.Appendable appendable0 = null;
        org.apache.commons.cli.help.TextHelpAppendable textHelpAppendable1 = new org.apache.commons.cli.help.TextHelpAppendable(appendable0);
        org.apache.commons.cli.help.TableDefinition tableDefinition2 = null;
        // The following exception was thrown during execution in test generation
        try {
            textHelpAppendable1.appendTable(tableDefinition2);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test003() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test003");
        org.apache.commons.cli.Option option0 = null;
        org.apache.commons.cli.help.OptionFormatter optionFormatter1 = org.apache.commons.cli.help.OptionFormatter.from(option0);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str3 = optionFormatter1.toSyntaxOption(true);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(optionFormatter1);
    }

    @Test
    public void test004() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test004");
        // The following exception was thrown during execution in test generation
        try {
            org.apache.commons.cli.Option option3 = new org.apache.commons.cli.Option("hi!", false, "");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: The option 'hi!' contains an illegal character : '!'.");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test005() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test005");
        java.lang.Appendable appendable0 = null;
        org.apache.commons.cli.help.TextHelpAppendable textHelpAppendable1 = new org.apache.commons.cli.help.TextHelpAppendable(appendable0);
        java.lang.CharSequence charSequence2 = null;
        textHelpAppendable1.appendParagraph(charSequence2);
        java.lang.Object[] objArray11 = new java.lang.Object[] { 1, 1.0d, 10, 0.0d, (-1), 100.0f };
        // The following exception was thrown during execution in test generation
        try {
            textHelpAppendable1.appendFormat("hi!", objArray11);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(objArray11);
        org.junit.Assert.assertEquals(java.util.Arrays.deepToString(objArray11), "[1, 1.0, 10, 0.0, -1, 100.0]");
        org.junit.Assert.assertEquals(java.util.Arrays.toString(objArray11), "[1, 1.0, 10, 0.0, -1, 100.0]");
    }

    @Test
    public void test006() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test006");
        // The following exception was thrown during execution in test generation
        try {
            java.io.File[] fileArray1 = org.apache.commons.cli.TypeHandler.createFiles("hi!");
            org.junit.Assert.fail("Expected exception of type java.lang.UnsupportedOperationException; message: Not yet implemented");
        } catch (java.lang.UnsupportedOperationException e) {
            // Expected exception.
        }
    }

    @Test
    public void test007() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test007");
        java.lang.Class<?> wildcardClass1 = org.apache.commons.cli.PatternOptionBuilder.getValueType('4');
        org.junit.Assert.assertNull(wildcardClass1);
    }

    @Test
    public void test008() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test008");
        org.apache.commons.cli.OptionBuilder optionBuilder0 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs();
        org.junit.Assert.assertNotNull(optionBuilder0);
    }

    @Test
    public void test009() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test009");
        org.apache.commons.cli.DefaultParser.NonOptionAction nonOptionAction0 = org.apache.commons.cli.DefaultParser.NonOptionAction.STOP;
        org.junit.Assert.assertTrue("'" + nonOptionAction0 + "' != '" + org.apache.commons.cli.DefaultParser.NonOptionAction.STOP + "'", nonOptionAction0.equals(org.apache.commons.cli.DefaultParser.NonOptionAction.STOP));
    }

    @Test
    public void test010() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test010");
        int int0 = org.apache.commons.cli.help.TextHelpAppendable.DEFAULT_INDENT;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 3 + "'", int0 == 3);
    }

    @Test
    public void test011() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test011");
        org.apache.commons.cli.Option option0 = null;
        org.apache.commons.cli.help.OptionFormatter optionFormatter1 = org.apache.commons.cli.help.OptionFormatter.from(option0);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str2 = optionFormatter1.toSyntaxOption();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(optionFormatter1);
    }

    @Test
    public void test012() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test012");
        java.lang.Appendable appendable0 = null;
        org.apache.commons.cli.help.TextHelpAppendable textHelpAppendable1 = new org.apache.commons.cli.help.TextHelpAppendable(appendable0);
        java.lang.CharSequence charSequence2 = null;
        textHelpAppendable1.appendParagraph(charSequence2);
        java.lang.CharSequence charSequence4 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.apache.commons.cli.help.FilterHelpAppendable filterHelpAppendable7 = textHelpAppendable1.append(charSequence4, (-1), 100);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test013() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test013");
        int int0 = org.apache.commons.cli.help.TextStyle.UNSET_MAX_WIDTH;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 2147483647 + "'", int0 == 2147483647);
    }

    @Test
    public void test014() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test014");
        org.apache.commons.cli.Option option0 = null;
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str1 = org.apache.commons.cli.HelpFormatter.getDescription(option0);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test015() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test015");
        // The following exception was thrown during execution in test generation
        try {
            org.apache.commons.cli.Option option1 = org.apache.commons.cli.OptionBuilder.create("");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Empty option name.");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test016() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test016");
        int int0 = org.apache.commons.cli.help.TextHelpAppendable.DEFAULT_WIDTH;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 74 + "'", int0 == 74);
    }

    @Test
    public void test017() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test017");
        java.lang.String str0 = org.apache.commons.cli.HelpFormatter.DEFAULT_LONG_OPT_PREFIX;
        org.junit.Assert.assertEquals("'" + str0 + "' != '" + "--" + "'", str0, "--");
    }

    @Test
    public void test018() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test018");
        org.apache.commons.cli.Option option0 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.apache.commons.cli.MissingArgumentException missingArgumentException1 = new org.apache.commons.cli.MissingArgumentException(option0);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test019() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test019");
        // The following exception was thrown during execution in test generation
        try {
            java.net.URL uRL1 = org.apache.commons.cli.TypeHandler.createURL("--");
            org.junit.Assert.fail("Expected exception of type org.apache.commons.cli.ParseException; message: java.net.MalformedURLException: no protocol: --");
        } catch (org.apache.commons.cli.ParseException e) {
            // Expected exception.
        }
    }

    @Test
    public void test020() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test020");
        org.apache.commons.cli.DefaultParser defaultParser1 = new org.apache.commons.cli.DefaultParser(false);
        org.apache.commons.cli.Options options2 = null;
        java.lang.String[] strArray5 = new java.lang.String[] { "--", "" };
        // The following exception was thrown during execution in test generation
        try {
            org.apache.commons.cli.CommandLine commandLine6 = defaultParser1.parse(options2, strArray5);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: options");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
        org.junit.Assert.assertArrayEquals(strArray5, new java.lang.String[] { "--", "" });
    }

    @Test
    public void test021() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test021");
        java.lang.Appendable appendable0 = null;
        org.apache.commons.cli.help.TextHelpAppendable textHelpAppendable1 = new org.apache.commons.cli.help.TextHelpAppendable(appendable0);
        java.lang.CharSequence charSequence2 = null;
        textHelpAppendable1.appendParagraph(charSequence2);
        textHelpAppendable1.setIndent(0);
        // The following exception was thrown during execution in test generation
        try {
            textHelpAppendable1.printWrapped("--");
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test022() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test022");
        org.apache.commons.cli.Option option0 = null;
        org.apache.commons.cli.help.OptionFormatter optionFormatter1 = org.apache.commons.cli.help.OptionFormatter.from(option0);
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean2 = optionFormatter1.isRequired();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(optionFormatter1);
    }

    @Test
    public void test023() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test023");
        java.lang.String str0 = org.apache.commons.cli.help.OptionFormatter.DEFAULT_ARG_NAME;
        org.junit.Assert.assertEquals("'" + str0 + "' != '" + "arg" + "'", str0, "arg");
    }

    @Test
    public void test024() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test024");
        org.apache.commons.cli.ParseException parseException1 = new org.apache.commons.cli.ParseException("hi!");
    }

    @Test
    public void test025() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test025");
        java.lang.Appendable appendable0 = null;
        org.apache.commons.cli.help.TextHelpAppendable textHelpAppendable1 = new org.apache.commons.cli.help.TextHelpAppendable(appendable0);
        textHelpAppendable1.setIndent((int) (short) -1);
        // The following exception was thrown during execution in test generation
        try {
            textHelpAppendable1.appendTitle((java.lang.CharSequence) "hi!");
            org.junit.Assert.fail("Expected exception of type java.lang.NegativeArraySizeException; message: null");
        } catch (java.lang.NegativeArraySizeException e) {
            // Expected exception.
        }
    }

    @Test
    public void test026() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test026");
        java.lang.Object obj1 = org.apache.commons.cli.PatternOptionBuilder.getValueClass('4');
        org.junit.Assert.assertNull(obj1);
    }

    @Test
    public void test027() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test027");
        java.lang.Appendable appendable0 = null;
        org.apache.commons.cli.help.TextHelpAppendable textHelpAppendable1 = new org.apache.commons.cli.help.TextHelpAppendable(appendable0);
        textHelpAppendable1.setIndent((int) (short) -1);
        org.apache.commons.cli.OptionBuilder optionBuilder6 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs((int) (byte) 1);
        java.util.Comparator<org.apache.commons.cli.Option> optionComparator10 = org.apache.commons.cli.help.AbstractHelpFormatter.DEFAULT_COMPARATOR;
        java.lang.Throwable throwable11 = null;
        org.apache.commons.cli.ParseException parseException12 = org.apache.commons.cli.ParseException.wrap(throwable11);
        java.lang.Object[] objArray13 = new java.lang.Object[] { optionBuilder6, 0.0d, "", 'a', optionComparator10, parseException12 };
        // The following exception was thrown during execution in test generation
        try {
            textHelpAppendable1.appendFormat("", objArray13);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(optionBuilder6);
        org.junit.Assert.assertNotNull(optionComparator10);
        org.junit.Assert.assertNotNull(parseException12);
        org.junit.Assert.assertNotNull(objArray13);
    }

    @Test
    public void test028() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test028");
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Class<?> wildcardClass1 = org.apache.commons.cli.TypeHandler.createClass("--");
            org.junit.Assert.fail("Expected exception of type org.apache.commons.cli.ParseException; message: java.lang.ClassNotFoundException: --");
        } catch (org.apache.commons.cli.ParseException e) {
            // Expected exception.
        }
    }

    @Test
    public void test029() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test029");
        // The following exception was thrown during execution in test generation
        try {
            org.apache.commons.cli.Option option1 = org.apache.commons.cli.OptionBuilder.create("--");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Illegal option name '-'.");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test030() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test030");
        org.apache.commons.cli.DefaultParser.NonOptionAction nonOptionAction0 = org.apache.commons.cli.DefaultParser.NonOptionAction.THROW;
        org.junit.Assert.assertTrue("'" + nonOptionAction0 + "' != '" + org.apache.commons.cli.DefaultParser.NonOptionAction.THROW + "'", nonOptionAction0.equals(org.apache.commons.cli.DefaultParser.NonOptionAction.THROW));
    }

    @Test
    public void test031() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test031");
        org.apache.commons.cli.help.TextStyle.Alignment alignment0 = org.apache.commons.cli.help.TextStyle.Alignment.CENTER;
        org.junit.Assert.assertTrue("'" + alignment0 + "' != '" + org.apache.commons.cli.help.TextStyle.Alignment.CENTER + "'", alignment0.equals(org.apache.commons.cli.help.TextStyle.Alignment.CENTER));
    }

    @Test
    public void test032() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test032");
        org.apache.commons.cli.OptionBuilder optionBuilder1 = org.apache.commons.cli.OptionBuilder.withLongOpt("");
        org.junit.Assert.assertNotNull(optionBuilder1);
    }

    @Test
    public void test033() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test033");
        java.lang.String str0 = org.apache.commons.cli.HelpFormatter.DEFAULT_ARG_NAME;
        org.junit.Assert.assertEquals("'" + str0 + "' != '" + "arg" + "'", str0, "arg");
    }

    @Test
    public void test034() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test034");
        org.apache.commons.cli.help.OptionFormatter.Builder builder0 = org.apache.commons.cli.help.OptionFormatter.builder();
        org.apache.commons.cli.help.OptionFormatter.Builder builder2 = builder0.setDefaultArgName("");
        org.apache.commons.cli.help.OptionFormatter.Builder builder5 = builder0.setOptionalDelimiters("hi!", "hi!");
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(builder2);
        org.junit.Assert.assertNotNull(builder5);
    }

    @Test
    public void test035() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test035");
        // The following exception was thrown during execution in test generation
        try {
            java.util.Date date1 = org.apache.commons.cli.TypeHandler.createDate("hi!");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: org.apache.commons.cli.ParseException: java.text.ParseException: Unparseable date: \"hi!\"");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test036() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test036");
        java.lang.Appendable appendable0 = null;
        org.apache.commons.cli.help.TextHelpAppendable textHelpAppendable1 = new org.apache.commons.cli.help.TextHelpAppendable(appendable0);
        java.lang.CharSequence charSequence2 = null;
        textHelpAppendable1.appendParagraph(charSequence2);
        textHelpAppendable1.setIndent(0);
        // The following exception was thrown during execution in test generation
        try {
            org.apache.commons.cli.help.FilterHelpAppendable filterHelpAppendable7 = textHelpAppendable1.append('4');
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test037() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test037");
        int int3 = org.apache.commons.cli.help.TextHelpAppendable.indexOfWrap((java.lang.CharSequence) "", 3, (int) (short) 100);
        org.junit.Assert.assertTrue("'" + int3 + "' != '" + 0 + "'", int3 == 0);
    }

    @Test
    public void test038() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test038");
        java.lang.Appendable appendable0 = null;
        org.apache.commons.cli.help.TextHelpAppendable textHelpAppendable1 = new org.apache.commons.cli.help.TextHelpAppendable(appendable0);
        java.lang.CharSequence charSequence2 = null;
        textHelpAppendable1.appendParagraph(charSequence2);
        textHelpAppendable1.setIndent(0);
        java.lang.Object[] objArray11 = new java.lang.Object[] { 10L, (short) 1, "", (short) 100 };
        textHelpAppendable1.appendParagraphFormat("", objArray11);
        // The following exception was thrown during execution in test generation
        try {
            textHelpAppendable1.appendHeader(10, (java.lang.CharSequence) "--");
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(objArray11);
        org.junit.Assert.assertEquals(java.util.Arrays.deepToString(objArray11), "[10, 1, , 100]");
        org.junit.Assert.assertEquals(java.util.Arrays.toString(objArray11), "[10, 1, , 100]");
    }

    @Test
    public void test039() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test039");
        // The following exception was thrown during execution in test generation
        try {
            org.apache.commons.cli.Option option2 = new org.apache.commons.cli.Option("hi!", "");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: The option 'hi!' contains an illegal character : '!'.");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test040() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test040");
        java.lang.String str0 = org.apache.commons.cli.help.OptionFormatter.DEFAULT_LONG_OPT_PREFIX;
        org.junit.Assert.assertEquals("'" + str0 + "' != '" + "--" + "'", str0, "--");
    }

    @Test
    public void test041() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test041");
        boolean boolean1 = org.apache.commons.cli.PatternOptionBuilder.isValueCode(' ');
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
    }

    @Test
    public void test042() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test042");
        // The following exception was thrown during execution in test generation
        try {
            int int3 = org.apache.commons.cli.help.TextHelpAppendable.indexOfWrap((java.lang.CharSequence) "hi!", (int) (short) 0, 3);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Width must be greater than 0");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test043() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test043");
        org.apache.commons.cli.Option option0 = null;
        org.apache.commons.cli.help.OptionFormatter optionFormatter1 = org.apache.commons.cli.help.OptionFormatter.from(option0);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str2 = optionFormatter1.getSince();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(optionFormatter1);
    }

    @Test
    public void test044() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test044");
        int int0 = org.apache.commons.cli.Option.UNINITIALIZED;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + (-1) + "'", int0 == (-1));
    }

    @Test
    public void test045() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test045");
        org.apache.commons.cli.Option option0 = null;
        org.apache.commons.cli.help.OptionFormatter optionFormatter1 = org.apache.commons.cli.help.OptionFormatter.from(option0);
        java.lang.String str3 = optionFormatter1.toOptional("--");
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str4 = optionFormatter1.getSince();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(optionFormatter1);
        org.junit.Assert.assertEquals("'" + str3 + "' != '" + "[--]" + "'", str3, "[--]");
    }

    @Test
    public void test046() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test046");
        org.apache.commons.cli.DefaultParser defaultParser1 = new org.apache.commons.cli.DefaultParser(false);
        org.apache.commons.cli.Options options2 = null;
        java.lang.String[] strArray6 = new java.lang.String[] { "[--]", "--", "--" };
        // The following exception was thrown during execution in test generation
        try {
            org.apache.commons.cli.CommandLine commandLine8 = defaultParser1.parse(options2, strArray6, true);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: options");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
        org.junit.Assert.assertArrayEquals(strArray6, new java.lang.String[] { "[--]", "--", "--" });
    }

    @Test
    public void test047() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test047");
        // The following exception was thrown during execution in test generation
        try {
            java.util.Date date1 = org.apache.commons.cli.TypeHandler.createDate("--");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: org.apache.commons.cli.ParseException: java.text.ParseException: Unparseable date: \"--\"");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test048() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test048");
        org.apache.commons.cli.DefaultParser.Builder builder0 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser1 = builder0.get();
        org.apache.commons.cli.Options options2 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties7 = null;
        org.apache.commons.cli.CommandLine commandLine9 = defaultParser1.parse(options2, strArray6, properties7, false);
        // The following exception was thrown during execution in test generation
        try {
            org.apache.commons.cli.Options options13 = options2.addOption("", false, "hi!");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Empty option name.");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(defaultParser1);
        org.junit.Assert.assertNotNull(strArray6);
        org.junit.Assert.assertArrayEquals(strArray6, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine9);
    }

    @Test
    public void test049() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test049");
        org.apache.commons.cli.OptionGroup optionGroup0 = null;
        org.apache.commons.cli.Option option1 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.apache.commons.cli.AlreadySelectedException alreadySelectedException2 = new org.apache.commons.cli.AlreadySelectedException(optionGroup0, option1);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test050() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test050");
        org.apache.commons.cli.Option.Builder builder0 = org.apache.commons.cli.Option.builder();
        org.apache.commons.cli.Option.Builder builder1 = builder0.deprecated();
        org.apache.commons.cli.DeprecatedAttributes deprecatedAttributes2 = null;
        org.apache.commons.cli.Option.Builder builder3 = builder1.deprecated(deprecatedAttributes2);
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(builder1);
        org.junit.Assert.assertNotNull(builder3);
    }

    @Test
    public void test051() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test051");
        java.lang.Appendable appendable0 = null;
        org.apache.commons.cli.help.TextHelpAppendable textHelpAppendable1 = new org.apache.commons.cli.help.TextHelpAppendable(appendable0);
        java.lang.CharSequence charSequence2 = null;
        textHelpAppendable1.appendParagraph(charSequence2);
        org.apache.commons.cli.help.TextHelpAppendable textHelpAppendable4 = new org.apache.commons.cli.help.TextHelpAppendable((java.lang.Appendable) textHelpAppendable1);
        // The following exception was thrown during execution in test generation
        try {
            org.apache.commons.cli.help.FilterHelpAppendable filterHelpAppendable8 = textHelpAppendable4.append((java.lang.CharSequence) "hi!", (int) (short) 10, (-1));
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test052() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test052");
        org.apache.commons.cli.Option option0 = null;
        org.apache.commons.cli.help.OptionFormatter optionFormatter1 = org.apache.commons.cli.help.OptionFormatter.from(option0);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str2 = optionFormatter1.getOpt();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(optionFormatter1);
    }

    @Test
    public void test053() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test053");
        int int0 = org.apache.commons.cli.Option.UNLIMITED_VALUES;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + (-2) + "'", int0 == (-2));
    }

    @Test
    public void test054() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test054");
        java.lang.String str0 = org.apache.commons.cli.HelpFormatter.DEFAULT_OPT_PREFIX;
        org.junit.Assert.assertEquals("'" + str0 + "' != '" + "-" + "'", str0, "-");
    }

    @Test
    public void test055() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test055");
        java.lang.Appendable appendable0 = null;
        org.apache.commons.cli.help.TextHelpAppendable textHelpAppendable1 = new org.apache.commons.cli.help.TextHelpAppendable(appendable0);
        java.lang.CharSequence charSequence2 = null;
        textHelpAppendable1.appendParagraph(charSequence2);
        textHelpAppendable1.setIndent(1);
        // The following exception was thrown during execution in test generation
        try {
            org.apache.commons.cli.help.FilterHelpAppendable filterHelpAppendable9 = textHelpAppendable1.append((java.lang.CharSequence) "-", (int) (byte) 0, 10);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test056() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test056");
        java.lang.Class<java.io.File> fileClass0 = org.apache.commons.cli.PatternOptionBuilder.FILE_VALUE;
        org.junit.Assert.assertNotNull(fileClass0);
    }

    @Test
    public void test057() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test057");
        org.apache.commons.cli.DefaultParser.Builder builder0 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser1 = builder0.get();
        org.apache.commons.cli.Options options2 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties7 = null;
        org.apache.commons.cli.CommandLine commandLine9 = defaultParser1.parse(options2, strArray6, properties7, false);
        java.lang.Object obj11 = commandLine9.getOptionObject('4');
        org.apache.commons.cli.OptionGroup optionGroup12 = null;
        java.lang.String str14 = commandLine9.getOptionValue(optionGroup12, "[--]");
        java.util.function.Supplier<java.lang.String> strSupplier16 = null;
        java.lang.String str17 = commandLine9.getOptionValue("hi!", strSupplier16);
        org.apache.commons.cli.DefaultParser.Builder builder19 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser20 = builder19.get();
        org.apache.commons.cli.Options options21 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray25 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties26 = null;
        org.apache.commons.cli.CommandLine commandLine28 = defaultParser20.parse(options21, strArray25, properties26, false);
        java.lang.Object obj30 = commandLine28.getOptionObject('4');
        org.apache.commons.cli.Option option31 = null;
        java.util.Properties properties32 = commandLine28.getOptionProperties(option31);
        java.lang.Object[] objArray33 = new java.lang.Object[] { properties32 };
        org.apache.commons.cli.DefaultParser.Builder builder34 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser35 = builder34.get();
        org.apache.commons.cli.Options options36 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray40 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties41 = null;
        org.apache.commons.cli.CommandLine commandLine43 = defaultParser35.parse(options36, strArray40, properties41, false);
        java.lang.Object obj45 = commandLine43.getOptionObject('4');
        org.apache.commons.cli.Option option46 = null;
        java.util.Properties properties47 = commandLine43.getOptionProperties(option46);
        java.lang.Object[] objArray48 = new java.lang.Object[] { properties47 };
        org.apache.commons.cli.DefaultParser.Builder builder49 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser50 = builder49.get();
        org.apache.commons.cli.Options options51 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray55 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties56 = null;
        org.apache.commons.cli.CommandLine commandLine58 = defaultParser50.parse(options51, strArray55, properties56, false);
        java.lang.Object obj60 = commandLine58.getOptionObject('4');
        org.apache.commons.cli.Option option61 = null;
        java.util.Properties properties62 = commandLine58.getOptionProperties(option61);
        java.lang.Object[] objArray63 = new java.lang.Object[] { properties62 };
        org.apache.commons.cli.DefaultParser.Builder builder64 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser65 = builder64.get();
        org.apache.commons.cli.Options options66 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray70 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties71 = null;
        org.apache.commons.cli.CommandLine commandLine73 = defaultParser65.parse(options66, strArray70, properties71, false);
        java.lang.Object obj75 = commandLine73.getOptionObject('4');
        org.apache.commons.cli.Option option76 = null;
        java.util.Properties properties77 = commandLine73.getOptionProperties(option76);
        java.lang.Object[] objArray78 = new java.lang.Object[] { properties77 };
        org.apache.commons.cli.DefaultParser.Builder builder79 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser80 = builder79.get();
        org.apache.commons.cli.Options options81 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray85 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties86 = null;
        org.apache.commons.cli.CommandLine commandLine88 = defaultParser80.parse(options81, strArray85, properties86, false);
        java.lang.Object obj90 = commandLine88.getOptionObject('4');
        org.apache.commons.cli.Option option91 = null;
        java.util.Properties properties92 = commandLine88.getOptionProperties(option91);
        java.lang.Object[] objArray93 = new java.lang.Object[] { properties92 };
        java.lang.Object[][] objArray94 = new java.lang.Object[][] { objArray33, objArray48, objArray63, objArray78, objArray93 };
        java.lang.Object[][] objArray95 = commandLine9.getParsedOptionValues("--", objArray94);
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(defaultParser1);
        org.junit.Assert.assertNotNull(strArray6);
        org.junit.Assert.assertArrayEquals(strArray6, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine9);
        org.junit.Assert.assertNull(obj11);
        org.junit.Assert.assertEquals("'" + str14 + "' != '" + "[--]" + "'", str14, "[--]");
        org.junit.Assert.assertNull(str17);
        org.junit.Assert.assertNotNull(builder19);
        org.junit.Assert.assertNotNull(defaultParser20);
        org.junit.Assert.assertNotNull(strArray25);
        org.junit.Assert.assertArrayEquals(strArray25, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine28);
        org.junit.Assert.assertNull(obj30);
        org.junit.Assert.assertNotNull(properties32);
        org.junit.Assert.assertNotNull(objArray33);
        org.junit.Assert.assertEquals(java.util.Arrays.deepToString(objArray33), "[{}]");
        org.junit.Assert.assertEquals(java.util.Arrays.toString(objArray33), "[{}]");
        org.junit.Assert.assertNotNull(builder34);
        org.junit.Assert.assertNotNull(defaultParser35);
        org.junit.Assert.assertNotNull(strArray40);
        org.junit.Assert.assertArrayEquals(strArray40, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine43);
        org.junit.Assert.assertNull(obj45);
        org.junit.Assert.assertNotNull(properties47);
        org.junit.Assert.assertNotNull(objArray48);
        org.junit.Assert.assertEquals(java.util.Arrays.deepToString(objArray48), "[{}]");
        org.junit.Assert.assertEquals(java.util.Arrays.toString(objArray48), "[{}]");
        org.junit.Assert.assertNotNull(builder49);
        org.junit.Assert.assertNotNull(defaultParser50);
        org.junit.Assert.assertNotNull(strArray55);
        org.junit.Assert.assertArrayEquals(strArray55, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine58);
        org.junit.Assert.assertNull(obj60);
        org.junit.Assert.assertNotNull(properties62);
        org.junit.Assert.assertNotNull(objArray63);
        org.junit.Assert.assertEquals(java.util.Arrays.deepToString(objArray63), "[{}]");
        org.junit.Assert.assertEquals(java.util.Arrays.toString(objArray63), "[{}]");
        org.junit.Assert.assertNotNull(builder64);
        org.junit.Assert.assertNotNull(defaultParser65);
        org.junit.Assert.assertNotNull(strArray70);
        org.junit.Assert.assertArrayEquals(strArray70, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine73);
        org.junit.Assert.assertNull(obj75);
        org.junit.Assert.assertNotNull(properties77);
        org.junit.Assert.assertNotNull(objArray78);
        org.junit.Assert.assertEquals(java.util.Arrays.deepToString(objArray78), "[{}]");
        org.junit.Assert.assertEquals(java.util.Arrays.toString(objArray78), "[{}]");
        org.junit.Assert.assertNotNull(builder79);
        org.junit.Assert.assertNotNull(defaultParser80);
        org.junit.Assert.assertNotNull(strArray85);
        org.junit.Assert.assertArrayEquals(strArray85, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine88);
        org.junit.Assert.assertNull(obj90);
        org.junit.Assert.assertNotNull(properties92);
        org.junit.Assert.assertNotNull(objArray93);
        org.junit.Assert.assertEquals(java.util.Arrays.deepToString(objArray93), "[{}]");
        org.junit.Assert.assertEquals(java.util.Arrays.toString(objArray93), "[{}]");
        org.junit.Assert.assertNotNull(objArray94);
        org.junit.Assert.assertNotNull(objArray95);
    }

    @Test
    public void test058() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test058");
        java.io.File file1 = org.apache.commons.cli.TypeHandler.createFile("-");
        org.junit.Assert.assertNotNull(file1);
        org.junit.Assert.assertNull("file1.getParent() == null", file1.getParent());
        org.junit.Assert.assertEquals(file1.toString(), "-");
    }

    @Test
    public void test059() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test059");
        org.apache.commons.cli.Option.Builder builder0 = org.apache.commons.cli.Option.builder();
        org.apache.commons.cli.Option.Builder builder2 = builder0.hasArg(true);
        org.apache.commons.cli.Option.Builder builder3 = builder0.hasArgs();
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(builder2);
        org.junit.Assert.assertNotNull(builder3);
    }

    @Test
    public void test060() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test060");
        org.apache.commons.cli.OptionBuilder optionBuilder1 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs((int) (byte) -1);
        org.junit.Assert.assertNotNull(optionBuilder1);
    }

    @Test
    public void test061() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test061");
        org.apache.commons.cli.DefaultParser.NonOptionAction nonOptionAction0 = org.apache.commons.cli.DefaultParser.NonOptionAction.IGNORE;
        org.junit.Assert.assertTrue("'" + nonOptionAction0 + "' != '" + org.apache.commons.cli.DefaultParser.NonOptionAction.IGNORE + "'", nonOptionAction0.equals(org.apache.commons.cli.DefaultParser.NonOptionAction.IGNORE));
    }

    @Test
    public void test062() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test062");
        java.lang.String str0 = org.apache.commons.cli.help.AbstractHelpFormatter.DEFAULT_SYNTAX_PREFIX;
        org.junit.Assert.assertEquals("'" + str0 + "' != '" + "usage: " + "'", str0, "usage: ");
    }

    @Test
    public void test063() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test063");
        java.lang.Class<java.io.File[]> fileArrayClass0 = org.apache.commons.cli.PatternOptionBuilder.FILES_VALUE;
        org.junit.Assert.assertNotNull(fileArrayClass0);
    }

    @Test
    public void test064() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test064");
        org.apache.commons.cli.DefaultParser.Builder builder0 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser1 = builder0.get();
        org.apache.commons.cli.Options options2 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties7 = null;
        org.apache.commons.cli.CommandLine commandLine9 = defaultParser1.parse(options2, strArray6, properties7, false);
        java.lang.Object obj11 = commandLine9.getOptionObject('4');
        org.apache.commons.cli.OptionBuilder optionBuilder13 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs();
        org.apache.commons.cli.OptionBuilder optionBuilder15 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs((int) (byte) 1);
        org.apache.commons.cli.OptionBuilder optionBuilder17 = org.apache.commons.cli.OptionBuilder.withLongOpt("");
        org.apache.commons.cli.OptionBuilder optionBuilder19 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs((int) (byte) 1);
        org.apache.commons.cli.OptionBuilder optionBuilder20 = org.apache.commons.cli.OptionBuilder.hasArgs();
        org.apache.commons.cli.OptionBuilder optionBuilder21 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs();
        org.apache.commons.cli.OptionBuilder[] optionBuilderArray22 = new org.apache.commons.cli.OptionBuilder[] { optionBuilder13, optionBuilder15, optionBuilder17, optionBuilder19, optionBuilder20, optionBuilder21 };
        org.apache.commons.cli.OptionBuilder[] optionBuilderArray23 = commandLine9.getParsedOptionValues('#', optionBuilderArray22);
        org.apache.commons.cli.DefaultParser.Builder builder25 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser26 = builder25.get();
        org.apache.commons.cli.DefaultParser.Builder builder27 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser28 = builder27.get();
        org.apache.commons.cli.CommandLineParser[] commandLineParserArray29 = new org.apache.commons.cli.CommandLineParser[] { defaultParser26, defaultParser28 };
        org.apache.commons.cli.CommandLineParser[] commandLineParserArray30 = commandLine9.getParsedOptionValues('#', commandLineParserArray29);
        java.lang.Object obj32 = commandLine9.getOptionObject(' ');
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(defaultParser1);
        org.junit.Assert.assertNotNull(strArray6);
        org.junit.Assert.assertArrayEquals(strArray6, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine9);
        org.junit.Assert.assertNull(obj11);
        org.junit.Assert.assertNotNull(optionBuilder13);
        org.junit.Assert.assertNotNull(optionBuilder15);
        org.junit.Assert.assertNotNull(optionBuilder17);
        org.junit.Assert.assertNotNull(optionBuilder19);
        org.junit.Assert.assertNotNull(optionBuilder20);
        org.junit.Assert.assertNotNull(optionBuilder21);
        org.junit.Assert.assertNotNull(optionBuilderArray22);
        org.junit.Assert.assertNotNull(optionBuilderArray23);
        org.junit.Assert.assertNotNull(builder25);
        org.junit.Assert.assertNotNull(defaultParser26);
        org.junit.Assert.assertNotNull(builder27);
        org.junit.Assert.assertNotNull(defaultParser28);
        org.junit.Assert.assertNotNull(commandLineParserArray29);
        org.junit.Assert.assertNotNull(commandLineParserArray30);
        org.junit.Assert.assertNull(obj32);
    }

    @Test
    public void test065() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test065");
        // The following exception was thrown during execution in test generation
        try {
            org.apache.commons.cli.Option.Builder builder1 = org.apache.commons.cli.Option.builder("");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Empty option name.");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test066() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test066");
        org.apache.commons.cli.PosixParser posixParser0 = new org.apache.commons.cli.PosixParser();
        org.apache.commons.cli.Options options1 = new org.apache.commons.cli.Options();
        org.apache.commons.cli.DefaultParser.Builder builder2 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser3 = builder2.get();
        org.apache.commons.cli.Options options4 = new org.apache.commons.cli.Options();
        org.apache.commons.cli.DefaultParser.Builder builder5 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser6 = builder5.get();
        org.apache.commons.cli.Options options7 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray11 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties12 = null;
        org.apache.commons.cli.CommandLine commandLine14 = defaultParser6.parse(options7, strArray11, properties12, false);
        org.apache.commons.cli.DefaultParser.Builder builder15 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser16 = builder15.get();
        org.apache.commons.cli.Options options17 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray21 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties22 = null;
        org.apache.commons.cli.CommandLine commandLine24 = defaultParser16.parse(options17, strArray21, properties22, false);
        java.lang.Object obj26 = commandLine24.getOptionObject('4');
        org.apache.commons.cli.Option option27 = null;
        java.util.Properties properties28 = commandLine24.getOptionProperties(option27);
        org.apache.commons.cli.CommandLine commandLine30 = defaultParser3.parse(options4, strArray11, properties28, false);
        org.apache.commons.cli.DefaultParser.Builder builder31 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser32 = builder31.get();
        org.apache.commons.cli.Options options33 = new org.apache.commons.cli.Options();
        org.apache.commons.cli.DefaultParser.Builder builder34 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser35 = builder34.get();
        org.apache.commons.cli.Options options36 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray40 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties41 = null;
        org.apache.commons.cli.CommandLine commandLine43 = defaultParser35.parse(options36, strArray40, properties41, false);
        org.apache.commons.cli.DefaultParser.Builder builder44 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser45 = builder44.get();
        org.apache.commons.cli.Options options46 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray50 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties51 = null;
        org.apache.commons.cli.CommandLine commandLine53 = defaultParser45.parse(options46, strArray50, properties51, false);
        java.lang.Object obj55 = commandLine53.getOptionObject('4');
        org.apache.commons.cli.Option option56 = null;
        java.util.Properties properties57 = commandLine53.getOptionProperties(option56);
        org.apache.commons.cli.CommandLine commandLine59 = defaultParser32.parse(options33, strArray40, properties57, false);
        org.apache.commons.cli.CommandLine commandLine61 = posixParser0.parse(options1, strArray11, properties57, true);
        java.util.Iterator<org.apache.commons.cli.Option> optionItor62 = commandLine61.iterator();
        org.junit.Assert.assertNotNull(builder2);
        org.junit.Assert.assertNotNull(defaultParser3);
        org.junit.Assert.assertNotNull(builder5);
        org.junit.Assert.assertNotNull(defaultParser6);
        org.junit.Assert.assertNotNull(strArray11);
        org.junit.Assert.assertArrayEquals(strArray11, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine14);
        org.junit.Assert.assertNotNull(builder15);
        org.junit.Assert.assertNotNull(defaultParser16);
        org.junit.Assert.assertNotNull(strArray21);
        org.junit.Assert.assertArrayEquals(strArray21, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine24);
        org.junit.Assert.assertNull(obj26);
        org.junit.Assert.assertNotNull(properties28);
        org.junit.Assert.assertNotNull(commandLine30);
        org.junit.Assert.assertNotNull(builder31);
        org.junit.Assert.assertNotNull(defaultParser32);
        org.junit.Assert.assertNotNull(builder34);
        org.junit.Assert.assertNotNull(defaultParser35);
        org.junit.Assert.assertNotNull(strArray40);
        org.junit.Assert.assertArrayEquals(strArray40, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine43);
        org.junit.Assert.assertNotNull(builder44);
        org.junit.Assert.assertNotNull(defaultParser45);
        org.junit.Assert.assertNotNull(strArray50);
        org.junit.Assert.assertArrayEquals(strArray50, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine53);
        org.junit.Assert.assertNull(obj55);
        org.junit.Assert.assertNotNull(properties57);
        org.junit.Assert.assertNotNull(commandLine59);
        org.junit.Assert.assertNotNull(commandLine61);
        org.junit.Assert.assertNotNull(optionItor62);
    }

    @Test
    public void test067() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test067");
        org.apache.commons.cli.OptionBuilder optionBuilder1 = org.apache.commons.cli.OptionBuilder.withLongOpt("usage: ");
        org.junit.Assert.assertNotNull(optionBuilder1);
    }

    @Test
    public void test068() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test068");
        org.apache.commons.cli.CommandLine.Builder builder0 = new org.apache.commons.cli.CommandLine.Builder();
        org.apache.commons.cli.CommandLine commandLine1 = builder0.build();
        org.apache.commons.cli.CommandLine commandLine2 = builder0.build();
        java.lang.Object obj4 = commandLine2.getOptionObject("[--]");
        org.junit.Assert.assertNotNull(commandLine1);
        org.junit.Assert.assertNotNull(commandLine2);
        org.junit.Assert.assertNull(obj4);
    }

    @Test
    public void test069() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test069");
        java.lang.Appendable appendable0 = null;
        org.apache.commons.cli.help.TextHelpAppendable textHelpAppendable1 = new org.apache.commons.cli.help.TextHelpAppendable(appendable0);
        java.lang.CharSequence charSequence2 = null;
        textHelpAppendable1.appendParagraph(charSequence2);
        textHelpAppendable1.setIndent(0);
        // The following exception was thrown during execution in test generation
        try {
            org.apache.commons.cli.help.FilterHelpAppendable filterHelpAppendable7 = textHelpAppendable1.append('#');
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test070() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test070");
        org.apache.commons.cli.DefaultParser.Builder builder0 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser1 = builder0.get();
        org.apache.commons.cli.Options options2 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties7 = null;
        org.apache.commons.cli.CommandLine commandLine9 = defaultParser1.parse(options2, strArray6, properties7, false);
        java.lang.Object obj11 = commandLine9.getOptionObject('4');
        java.lang.Object obj13 = commandLine9.getOptionObject('4');
        org.apache.commons.cli.OptionGroup optionGroup14 = new org.apache.commons.cli.OptionGroup();
        boolean boolean15 = optionGroup14.isRequired();
        boolean boolean16 = commandLine9.hasOption(optionGroup14);
        org.apache.commons.cli.Option option17 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.apache.commons.cli.AlreadySelectedException alreadySelectedException18 = new org.apache.commons.cli.AlreadySelectedException(optionGroup14, option17);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(defaultParser1);
        org.junit.Assert.assertNotNull(strArray6);
        org.junit.Assert.assertArrayEquals(strArray6, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine9);
        org.junit.Assert.assertNull(obj11);
        org.junit.Assert.assertNull(obj13);
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertTrue("'" + boolean16 + "' != '" + false + "'", boolean16 == false);
    }

    @Test
    public void test071() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test071");
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Number number1 = org.apache.commons.cli.TypeHandler.createNumber("usage: ");
            org.junit.Assert.fail("Expected exception of type org.apache.commons.cli.ParseException; message: java.lang.NumberFormatException: For input string: \"usage: \"");
        } catch (org.apache.commons.cli.ParseException e) {
            // Expected exception.
        }
    }

    @Test
    public void test072() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test072");
        org.apache.commons.cli.DefaultParser.Builder builder0 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser1 = builder0.get();
        org.apache.commons.cli.Options options2 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties7 = null;
        org.apache.commons.cli.CommandLine commandLine9 = defaultParser1.parse(options2, strArray6, properties7, false);
        org.apache.commons.cli.DefaultParser.Builder builder10 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser11 = builder10.get();
        org.apache.commons.cli.Options options12 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray16 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties17 = null;
        org.apache.commons.cli.CommandLine commandLine19 = defaultParser11.parse(options12, strArray16, properties17, false);
        org.apache.commons.cli.DefaultParser.Builder builder20 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser21 = builder20.get();
        org.apache.commons.cli.Options options22 = new org.apache.commons.cli.Options();
        org.apache.commons.cli.DefaultParser.Builder builder23 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser24 = builder23.get();
        org.apache.commons.cli.Options options25 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray29 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties30 = null;
        org.apache.commons.cli.CommandLine commandLine32 = defaultParser24.parse(options25, strArray29, properties30, false);
        org.apache.commons.cli.DefaultParser.Builder builder33 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser34 = builder33.get();
        org.apache.commons.cli.Options options35 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray39 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties40 = null;
        org.apache.commons.cli.CommandLine commandLine42 = defaultParser34.parse(options35, strArray39, properties40, false);
        java.lang.Object obj44 = commandLine42.getOptionObject('4');
        org.apache.commons.cli.Option option45 = null;
        java.util.Properties properties46 = commandLine42.getOptionProperties(option45);
        org.apache.commons.cli.CommandLine commandLine48 = defaultParser21.parse(options22, strArray29, properties46, false);
        org.apache.commons.cli.DefaultParser.Builder builder49 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser50 = builder49.get();
        org.apache.commons.cli.Options options51 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray55 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties56 = null;
        org.apache.commons.cli.CommandLine commandLine58 = defaultParser50.parse(options51, strArray55, properties56, false);
        java.lang.Object obj60 = commandLine58.getOptionObject('4');
        java.util.Properties properties62 = commandLine58.getOptionProperties("--");
        org.apache.commons.cli.CommandLine commandLine63 = defaultParser1.parse(options12, strArray29, properties62);
        org.apache.commons.cli.Option option64 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.apache.commons.cli.Options options65 = options12.addOption(option64);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(defaultParser1);
        org.junit.Assert.assertNotNull(strArray6);
        org.junit.Assert.assertArrayEquals(strArray6, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine9);
        org.junit.Assert.assertNotNull(builder10);
        org.junit.Assert.assertNotNull(defaultParser11);
        org.junit.Assert.assertNotNull(strArray16);
        org.junit.Assert.assertArrayEquals(strArray16, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine19);
        org.junit.Assert.assertNotNull(builder20);
        org.junit.Assert.assertNotNull(defaultParser21);
        org.junit.Assert.assertNotNull(builder23);
        org.junit.Assert.assertNotNull(defaultParser24);
        org.junit.Assert.assertNotNull(strArray29);
        org.junit.Assert.assertArrayEquals(strArray29, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine32);
        org.junit.Assert.assertNotNull(builder33);
        org.junit.Assert.assertNotNull(defaultParser34);
        org.junit.Assert.assertNotNull(strArray39);
        org.junit.Assert.assertArrayEquals(strArray39, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine42);
        org.junit.Assert.assertNull(obj44);
        org.junit.Assert.assertNotNull(properties46);
        org.junit.Assert.assertNotNull(commandLine48);
        org.junit.Assert.assertNotNull(builder49);
        org.junit.Assert.assertNotNull(defaultParser50);
        org.junit.Assert.assertNotNull(strArray55);
        org.junit.Assert.assertArrayEquals(strArray55, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine58);
        org.junit.Assert.assertNull(obj60);
        org.junit.Assert.assertNotNull(properties62);
        org.junit.Assert.assertNotNull(commandLine63);
    }

    @Test
    public void test073() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test073");
        org.apache.commons.cli.OptionBuilder optionBuilder0 = org.apache.commons.cli.OptionBuilder.hasArg();
        org.junit.Assert.assertNotNull(optionBuilder0);
    }

    @Test
    public void test074() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test074");
        org.apache.commons.cli.DeprecatedAttributes.Builder builder0 = new org.apache.commons.cli.DeprecatedAttributes.Builder();
        org.apache.commons.cli.DeprecatedAttributes.Builder builder2 = builder0.setDescription("hi!");
        org.junit.Assert.assertNotNull(builder2);
    }

    @Test
    public void test075() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test075");
        org.apache.commons.cli.OptionBuilder optionBuilder1 = org.apache.commons.cli.OptionBuilder.withArgName("--");
        org.junit.Assert.assertNotNull(optionBuilder1);
    }

    @Test
    public void test076() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test076");
        org.apache.commons.cli.OptionBuilder optionBuilder1 = org.apache.commons.cli.OptionBuilder.withLongOpt("[--]");
        org.junit.Assert.assertNotNull(optionBuilder1);
    }

    @Test
    public void test077() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test077");
        org.apache.commons.cli.DefaultParser.Builder builder0 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser1 = builder0.get();
        org.apache.commons.cli.Options options2 = new org.apache.commons.cli.Options();
        org.apache.commons.cli.DefaultParser.Builder builder3 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser4 = builder3.get();
        org.apache.commons.cli.Options options5 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray9 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties10 = null;
        org.apache.commons.cli.CommandLine commandLine12 = defaultParser4.parse(options5, strArray9, properties10, false);
        org.apache.commons.cli.DefaultParser.Builder builder13 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser14 = builder13.get();
        org.apache.commons.cli.Options options15 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray19 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties20 = null;
        org.apache.commons.cli.CommandLine commandLine22 = defaultParser14.parse(options15, strArray19, properties20, false);
        java.lang.Object obj24 = commandLine22.getOptionObject('4');
        org.apache.commons.cli.Option option25 = null;
        java.util.Properties properties26 = commandLine22.getOptionProperties(option25);
        org.apache.commons.cli.CommandLine commandLine28 = defaultParser1.parse(options2, strArray9, properties26, false);
        org.apache.commons.cli.Option option29 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.apache.commons.cli.OptionGroup optionGroup30 = options2.getOptionGroup(option29);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(defaultParser1);
        org.junit.Assert.assertNotNull(builder3);
        org.junit.Assert.assertNotNull(defaultParser4);
        org.junit.Assert.assertNotNull(strArray9);
        org.junit.Assert.assertArrayEquals(strArray9, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine12);
        org.junit.Assert.assertNotNull(builder13);
        org.junit.Assert.assertNotNull(defaultParser14);
        org.junit.Assert.assertNotNull(strArray19);
        org.junit.Assert.assertArrayEquals(strArray19, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine22);
        org.junit.Assert.assertNull(obj24);
        org.junit.Assert.assertNotNull(properties26);
        org.junit.Assert.assertNotNull(commandLine28);
    }

    @Test
    public void test078() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test078");
        org.apache.commons.cli.DefaultParser.Builder builder0 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser1 = builder0.get();
        org.apache.commons.cli.Options options2 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties7 = null;
        org.apache.commons.cli.CommandLine commandLine9 = defaultParser1.parse(options2, strArray6, properties7, false);
        java.lang.Object obj11 = commandLine9.getOptionObject('4');
        org.apache.commons.cli.OptionBuilder optionBuilder13 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs();
        org.apache.commons.cli.OptionBuilder optionBuilder15 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs((int) (byte) 1);
        org.apache.commons.cli.OptionBuilder optionBuilder17 = org.apache.commons.cli.OptionBuilder.withLongOpt("");
        org.apache.commons.cli.OptionBuilder optionBuilder19 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs((int) (byte) 1);
        org.apache.commons.cli.OptionBuilder optionBuilder20 = org.apache.commons.cli.OptionBuilder.hasArgs();
        org.apache.commons.cli.OptionBuilder optionBuilder21 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs();
        org.apache.commons.cli.OptionBuilder[] optionBuilderArray22 = new org.apache.commons.cli.OptionBuilder[] { optionBuilder13, optionBuilder15, optionBuilder17, optionBuilder19, optionBuilder20, optionBuilder21 };
        org.apache.commons.cli.OptionBuilder[] optionBuilderArray23 = commandLine9.getParsedOptionValues('#', optionBuilderArray22);
        java.util.function.Supplier<java.lang.String> strSupplier25 = null;
        java.lang.String str26 = commandLine9.getOptionValue('a', strSupplier25);
        // The following exception was thrown during execution in test generation
        try {
            org.apache.commons.cli.OptionBuilder optionBuilder27 = org.apache.commons.cli.OptionBuilder.withType((java.lang.Object) 'a');
            org.junit.Assert.fail("Expected exception of type java.lang.ClassCastException; message: java.lang.Character cannot be cast to java.lang.Class");
        } catch (java.lang.ClassCastException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(defaultParser1);
        org.junit.Assert.assertNotNull(strArray6);
        org.junit.Assert.assertArrayEquals(strArray6, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine9);
        org.junit.Assert.assertNull(obj11);
        org.junit.Assert.assertNotNull(optionBuilder13);
        org.junit.Assert.assertNotNull(optionBuilder15);
        org.junit.Assert.assertNotNull(optionBuilder17);
        org.junit.Assert.assertNotNull(optionBuilder19);
        org.junit.Assert.assertNotNull(optionBuilder20);
        org.junit.Assert.assertNotNull(optionBuilder21);
        org.junit.Assert.assertNotNull(optionBuilderArray22);
        org.junit.Assert.assertNotNull(optionBuilderArray23);
        org.junit.Assert.assertNull(str26);
    }

    @Test
    public void test079() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test079");
        // The following exception was thrown during execution in test generation
        try {
            org.apache.commons.cli.Option option4 = new org.apache.commons.cli.Option("[--]", "-", true, "[--]");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Illegal option name '['.");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test080() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test080");
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj2 = org.apache.commons.cli.TypeHandler.createValue("[--]", (java.lang.Object) 74);
            org.junit.Assert.fail("Expected exception of type java.lang.ClassCastException; message: java.lang.Integer cannot be cast to java.lang.Class");
        } catch (java.lang.ClassCastException e) {
            // Expected exception.
        }
    }

    @Test
    public void test081() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test081");
        org.apache.commons.cli.GnuParser gnuParser0 = new org.apache.commons.cli.GnuParser();
        org.apache.commons.cli.Option option1 = null;
        java.util.ListIterator<java.lang.String> strItor2 = null;
        // The following exception was thrown during execution in test generation
        try {
            gnuParser0.processArgs(option1, strItor2);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test082() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test082");
        org.apache.commons.cli.help.TextStyle textStyle0 = org.apache.commons.cli.help.TextStyle.DEFAULT;
        boolean boolean1 = textStyle0.isScalable();
        org.junit.Assert.assertNotNull(textStyle0);
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + true + "'", boolean1 == true);
    }

    @Test
    public void test083() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test083");
        java.lang.String str0 = org.apache.commons.cli.help.OptionFormatter.DEFAULT_OPT_PREFIX;
        org.junit.Assert.assertEquals("'" + str0 + "' != '" + "-" + "'", str0, "-");
    }

    @Test
    public void test084() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test084");
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj2 = org.apache.commons.cli.TypeHandler.createValue("arg", (java.lang.Object) (-2));
            org.junit.Assert.fail("Expected exception of type java.lang.ClassCastException; message: java.lang.Integer cannot be cast to java.lang.Class");
        } catch (java.lang.ClassCastException e) {
            // Expected exception.
        }
    }

    @Test
    public void test085() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test085");
        org.apache.commons.cli.OptionBuilder optionBuilder0 = org.apache.commons.cli.OptionBuilder.hasOptionalArg();
        org.junit.Assert.assertNotNull(optionBuilder0);
    }

    @Test
    public void test086() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test086");
        org.apache.commons.cli.CommandLine.Builder builder0 = new org.apache.commons.cli.CommandLine.Builder();
        org.apache.commons.cli.CommandLine commandLine1 = builder0.build();
        org.apache.commons.cli.CommandLine commandLine2 = builder0.build();
        java.util.function.Supplier<java.lang.String> strSupplier4 = null;
        java.lang.String str5 = commandLine2.getOptionValue("arg", strSupplier4);
        org.junit.Assert.assertNotNull(commandLine1);
        org.junit.Assert.assertNotNull(commandLine2);
        org.junit.Assert.assertNull(str5);
    }

    @Test
    public void test087() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test087");
        org.apache.commons.cli.TypeHandler typeHandler0 = new org.apache.commons.cli.TypeHandler();
        java.lang.Class<java.net.URL> uRLClass1 = org.apache.commons.cli.PatternOptionBuilder.URL_VALUE;
        org.apache.commons.cli.Converter<java.net.URL, ?> uRLConverter2 = typeHandler0.getConverter(uRLClass1);
        org.junit.Assert.assertNotNull(uRLClass1);
        org.junit.Assert.assertNotNull(uRLConverter2);
    }

    @Test
    public void test088() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test088");
        org.apache.commons.cli.DefaultParser.Builder builder0 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser1 = builder0.get();
        org.apache.commons.cli.Options options2 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties7 = null;
        org.apache.commons.cli.CommandLine commandLine9 = defaultParser1.parse(options2, strArray6, properties7, false);
        org.apache.commons.cli.DefaultParser.Builder builder10 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser11 = builder10.get();
        org.apache.commons.cli.Options options12 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray16 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties17 = null;
        org.apache.commons.cli.CommandLine commandLine19 = defaultParser11.parse(options12, strArray16, properties17, false);
        org.apache.commons.cli.DefaultParser.Builder builder20 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser21 = builder20.get();
        org.apache.commons.cli.Options options22 = new org.apache.commons.cli.Options();
        org.apache.commons.cli.DefaultParser.Builder builder23 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser24 = builder23.get();
        org.apache.commons.cli.Options options25 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray29 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties30 = null;
        org.apache.commons.cli.CommandLine commandLine32 = defaultParser24.parse(options25, strArray29, properties30, false);
        org.apache.commons.cli.DefaultParser.Builder builder33 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser34 = builder33.get();
        org.apache.commons.cli.Options options35 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray39 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties40 = null;
        org.apache.commons.cli.CommandLine commandLine42 = defaultParser34.parse(options35, strArray39, properties40, false);
        java.lang.Object obj44 = commandLine42.getOptionObject('4');
        org.apache.commons.cli.Option option45 = null;
        java.util.Properties properties46 = commandLine42.getOptionProperties(option45);
        org.apache.commons.cli.CommandLine commandLine48 = defaultParser21.parse(options22, strArray29, properties46, false);
        org.apache.commons.cli.DefaultParser.Builder builder49 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser50 = builder49.get();
        org.apache.commons.cli.Options options51 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray55 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties56 = null;
        org.apache.commons.cli.CommandLine commandLine58 = defaultParser50.parse(options51, strArray55, properties56, false);
        java.lang.Object obj60 = commandLine58.getOptionObject('4');
        java.util.Properties properties62 = commandLine58.getOptionProperties("--");
        org.apache.commons.cli.CommandLine commandLine63 = defaultParser1.parse(options12, strArray29, properties62);
        org.apache.commons.cli.Option option64 = null;
        java.lang.Object obj65 = commandLine63.getParsedOptionValue(option64);
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(defaultParser1);
        org.junit.Assert.assertNotNull(strArray6);
        org.junit.Assert.assertArrayEquals(strArray6, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine9);
        org.junit.Assert.assertNotNull(builder10);
        org.junit.Assert.assertNotNull(defaultParser11);
        org.junit.Assert.assertNotNull(strArray16);
        org.junit.Assert.assertArrayEquals(strArray16, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine19);
        org.junit.Assert.assertNotNull(builder20);
        org.junit.Assert.assertNotNull(defaultParser21);
        org.junit.Assert.assertNotNull(builder23);
        org.junit.Assert.assertNotNull(defaultParser24);
        org.junit.Assert.assertNotNull(strArray29);
        org.junit.Assert.assertArrayEquals(strArray29, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine32);
        org.junit.Assert.assertNotNull(builder33);
        org.junit.Assert.assertNotNull(defaultParser34);
        org.junit.Assert.assertNotNull(strArray39);
        org.junit.Assert.assertArrayEquals(strArray39, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine42);
        org.junit.Assert.assertNull(obj44);
        org.junit.Assert.assertNotNull(properties46);
        org.junit.Assert.assertNotNull(commandLine48);
        org.junit.Assert.assertNotNull(builder49);
        org.junit.Assert.assertNotNull(defaultParser50);
        org.junit.Assert.assertNotNull(strArray55);
        org.junit.Assert.assertArrayEquals(strArray55, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine58);
        org.junit.Assert.assertNull(obj60);
        org.junit.Assert.assertNotNull(properties62);
        org.junit.Assert.assertNotNull(commandLine63);
        org.junit.Assert.assertNull(obj65);
    }

    @Test
    public void test089() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test089");
        org.apache.commons.cli.OptionBuilder optionBuilder1 = org.apache.commons.cli.OptionBuilder.isRequired(true);
        org.junit.Assert.assertNotNull(optionBuilder1);
    }

    @Test
    public void test090() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test090");
        java.lang.String[] strArray3 = new java.lang.String[] { "hi!", "arg" };
        java.util.ArrayList<java.lang.String> strList4 = new java.util.ArrayList<java.lang.String>();
        boolean boolean5 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList4, strArray3);
        org.apache.commons.cli.AmbiguousOptionException ambiguousOptionException6 = new org.apache.commons.cli.AmbiguousOptionException("hi!", (java.util.Collection<java.lang.String>) strList4);
        org.apache.commons.cli.ParseException parseException7 = new org.apache.commons.cli.ParseException((java.lang.Throwable) ambiguousOptionException6);
        org.junit.Assert.assertNotNull(strArray3);
        org.junit.Assert.assertArrayEquals(strArray3, new java.lang.String[] { "hi!", "arg" });
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
    }

    @Test
    public void test091() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test091");
        org.apache.commons.cli.DefaultParser.Builder builder0 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser1 = builder0.get();
        org.apache.commons.cli.Options options2 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties7 = null;
        org.apache.commons.cli.CommandLine commandLine9 = defaultParser1.parse(options2, strArray6, properties7, false);
        java.lang.Object obj11 = commandLine9.getOptionObject('4');
        org.apache.commons.cli.OptionBuilder optionBuilder13 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs();
        org.apache.commons.cli.OptionBuilder optionBuilder15 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs((int) (byte) 1);
        org.apache.commons.cli.OptionBuilder optionBuilder17 = org.apache.commons.cli.OptionBuilder.withLongOpt("");
        org.apache.commons.cli.OptionBuilder optionBuilder19 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs((int) (byte) 1);
        org.apache.commons.cli.OptionBuilder optionBuilder20 = org.apache.commons.cli.OptionBuilder.hasArgs();
        org.apache.commons.cli.OptionBuilder optionBuilder21 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs();
        org.apache.commons.cli.OptionBuilder[] optionBuilderArray22 = new org.apache.commons.cli.OptionBuilder[] { optionBuilder13, optionBuilder15, optionBuilder17, optionBuilder19, optionBuilder20, optionBuilder21 };
        org.apache.commons.cli.OptionBuilder[] optionBuilderArray23 = commandLine9.getParsedOptionValues('#', optionBuilderArray22);
        org.apache.commons.cli.OptionGroup optionGroup24 = new org.apache.commons.cli.OptionGroup();
        boolean boolean25 = optionGroup24.isRequired();
        java.lang.String str27 = commandLine9.getOptionValue(optionGroup24, "");
        org.apache.commons.cli.Option option28 = null;
        java.util.function.Supplier<java.lang.String> strSupplier29 = null;
        java.lang.String str30 = commandLine9.getOptionValue(option28, strSupplier29);
        org.apache.commons.cli.Option option31 = null;
        org.apache.commons.cli.help.OptionFormatter[] optionFormatterArray32 = commandLine9.getParsedOptionValues(option31);
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(defaultParser1);
        org.junit.Assert.assertNotNull(strArray6);
        org.junit.Assert.assertArrayEquals(strArray6, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine9);
        org.junit.Assert.assertNull(obj11);
        org.junit.Assert.assertNotNull(optionBuilder13);
        org.junit.Assert.assertNotNull(optionBuilder15);
        org.junit.Assert.assertNotNull(optionBuilder17);
        org.junit.Assert.assertNotNull(optionBuilder19);
        org.junit.Assert.assertNotNull(optionBuilder20);
        org.junit.Assert.assertNotNull(optionBuilder21);
        org.junit.Assert.assertNotNull(optionBuilderArray22);
        org.junit.Assert.assertNotNull(optionBuilderArray23);
        org.junit.Assert.assertTrue("'" + boolean25 + "' != '" + false + "'", boolean25 == false);
        org.junit.Assert.assertEquals("'" + str27 + "' != '" + "" + "'", str27, "");
        org.junit.Assert.assertNull(str30);
        org.junit.Assert.assertNull(optionFormatterArray32);
    }

    @Test
    public void test092() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test092");
        org.apache.commons.cli.DefaultParser.Builder builder0 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser1 = builder0.get();
        org.apache.commons.cli.Options options2 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties7 = null;
        org.apache.commons.cli.CommandLine commandLine9 = defaultParser1.parse(options2, strArray6, properties7, false);
        java.lang.Object obj11 = commandLine9.getOptionObject('4');
        org.apache.commons.cli.OptionBuilder optionBuilder13 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs();
        org.apache.commons.cli.OptionBuilder optionBuilder15 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs((int) (byte) 1);
        org.apache.commons.cli.OptionBuilder optionBuilder17 = org.apache.commons.cli.OptionBuilder.withLongOpt("");
        org.apache.commons.cli.OptionBuilder optionBuilder19 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs((int) (byte) 1);
        org.apache.commons.cli.OptionBuilder optionBuilder20 = org.apache.commons.cli.OptionBuilder.hasArgs();
        org.apache.commons.cli.OptionBuilder optionBuilder21 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs();
        org.apache.commons.cli.OptionBuilder[] optionBuilderArray22 = new org.apache.commons.cli.OptionBuilder[] { optionBuilder13, optionBuilder15, optionBuilder17, optionBuilder19, optionBuilder20, optionBuilder21 };
        org.apache.commons.cli.OptionBuilder[] optionBuilderArray23 = commandLine9.getParsedOptionValues('#', optionBuilderArray22);
        java.util.function.Supplier<java.lang.String> strSupplier25 = null;
        java.lang.String str26 = commandLine9.getOptionValue('a', strSupplier25);
        org.apache.commons.cli.Option option27 = null;
        java.lang.Throwable throwable28 = null;
        org.apache.commons.cli.ParseException parseException29 = org.apache.commons.cli.ParseException.wrap(throwable28);
        java.lang.Throwable throwable30 = commandLine9.getParsedOptionValue(option27, (java.lang.Throwable) parseException29);
        org.apache.commons.cli.ParseException parseException31 = new org.apache.commons.cli.ParseException((java.lang.Throwable) parseException29);
        org.apache.commons.cli.ParseException parseException32 = new org.apache.commons.cli.ParseException((java.lang.Throwable) parseException31);
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(defaultParser1);
        org.junit.Assert.assertNotNull(strArray6);
        org.junit.Assert.assertArrayEquals(strArray6, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine9);
        org.junit.Assert.assertNull(obj11);
        org.junit.Assert.assertNotNull(optionBuilder13);
        org.junit.Assert.assertNotNull(optionBuilder15);
        org.junit.Assert.assertNotNull(optionBuilder17);
        org.junit.Assert.assertNotNull(optionBuilder19);
        org.junit.Assert.assertNotNull(optionBuilder20);
        org.junit.Assert.assertNotNull(optionBuilder21);
        org.junit.Assert.assertNotNull(optionBuilderArray22);
        org.junit.Assert.assertNotNull(optionBuilderArray23);
        org.junit.Assert.assertNull(str26);
        org.junit.Assert.assertNotNull(parseException29);
        org.junit.Assert.assertNotNull(throwable30);
        org.junit.Assert.assertNull("throwable30.getLocalizedMessage() == null", throwable30.getLocalizedMessage());
        org.junit.Assert.assertNull("throwable30.getMessage() == null", throwable30.getMessage());
        org.junit.Assert.assertEquals(throwable30.toString(), "org.apache.commons.cli.ParseException");
    }

    @Test
    public void test093() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test093");
        org.apache.commons.cli.DefaultParser.Builder builder0 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser1 = builder0.get();
        org.apache.commons.cli.Options options2 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties7 = null;
        org.apache.commons.cli.CommandLine commandLine9 = defaultParser1.parse(options2, strArray6, properties7, false);
        java.lang.Object obj11 = commandLine9.getOptionObject('4');
        org.apache.commons.cli.OptionBuilder optionBuilder13 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs();
        org.apache.commons.cli.OptionBuilder optionBuilder15 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs((int) (byte) 1);
        org.apache.commons.cli.OptionBuilder optionBuilder17 = org.apache.commons.cli.OptionBuilder.withLongOpt("");
        org.apache.commons.cli.OptionBuilder optionBuilder19 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs((int) (byte) 1);
        org.apache.commons.cli.OptionBuilder optionBuilder20 = org.apache.commons.cli.OptionBuilder.hasArgs();
        org.apache.commons.cli.OptionBuilder optionBuilder21 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs();
        org.apache.commons.cli.OptionBuilder[] optionBuilderArray22 = new org.apache.commons.cli.OptionBuilder[] { optionBuilder13, optionBuilder15, optionBuilder17, optionBuilder19, optionBuilder20, optionBuilder21 };
        org.apache.commons.cli.OptionBuilder[] optionBuilderArray23 = commandLine9.getParsedOptionValues('#', optionBuilderArray22);
        org.apache.commons.cli.DefaultParser.Builder builder25 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser26 = builder25.get();
        org.apache.commons.cli.DefaultParser.Builder builder27 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser28 = builder27.get();
        org.apache.commons.cli.CommandLineParser[] commandLineParserArray29 = new org.apache.commons.cli.CommandLineParser[] { defaultParser26, defaultParser28 };
        org.apache.commons.cli.CommandLineParser[] commandLineParserArray30 = commandLine9.getParsedOptionValues('#', commandLineParserArray29);
        org.apache.commons.cli.Option option31 = null;
        org.apache.commons.cli.DefaultParser.Builder builder32 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser33 = builder32.get();
        org.apache.commons.cli.Options options34 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray38 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties39 = null;
        org.apache.commons.cli.CommandLine commandLine41 = defaultParser33.parse(options34, strArray38, properties39, false);
        java.lang.Object obj43 = commandLine41.getOptionObject('4');
        java.util.Properties properties45 = commandLine41.getOptionProperties("--");
        java.util.Hashtable<java.lang.Object, java.lang.Object> objMap46 = commandLine9.getParsedOptionValue(option31, (java.util.Hashtable<java.lang.Object, java.lang.Object>) properties45);
        org.apache.commons.cli.Option option47 = null;
        org.apache.commons.cli.Option.Builder builder48 = commandLine9.getParsedOptionValue(option47);
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(defaultParser1);
        org.junit.Assert.assertNotNull(strArray6);
        org.junit.Assert.assertArrayEquals(strArray6, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine9);
        org.junit.Assert.assertNull(obj11);
        org.junit.Assert.assertNotNull(optionBuilder13);
        org.junit.Assert.assertNotNull(optionBuilder15);
        org.junit.Assert.assertNotNull(optionBuilder17);
        org.junit.Assert.assertNotNull(optionBuilder19);
        org.junit.Assert.assertNotNull(optionBuilder20);
        org.junit.Assert.assertNotNull(optionBuilder21);
        org.junit.Assert.assertNotNull(optionBuilderArray22);
        org.junit.Assert.assertNotNull(optionBuilderArray23);
        org.junit.Assert.assertNotNull(builder25);
        org.junit.Assert.assertNotNull(defaultParser26);
        org.junit.Assert.assertNotNull(builder27);
        org.junit.Assert.assertNotNull(defaultParser28);
        org.junit.Assert.assertNotNull(commandLineParserArray29);
        org.junit.Assert.assertNotNull(commandLineParserArray30);
        org.junit.Assert.assertNotNull(builder32);
        org.junit.Assert.assertNotNull(defaultParser33);
        org.junit.Assert.assertNotNull(strArray38);
        org.junit.Assert.assertArrayEquals(strArray38, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine41);
        org.junit.Assert.assertNull(obj43);
        org.junit.Assert.assertNotNull(properties45);
        org.junit.Assert.assertNotNull(objMap46);
        org.junit.Assert.assertNull(builder48);
    }

    @Test
    public void test094() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test094");
        org.apache.commons.cli.Options options0 = new org.apache.commons.cli.Options();
        java.lang.String str1 = options0.toString();
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "[ Options: [ short {} ] [ long {} ]" + "'", str1, "[ Options: [ short {} ] [ long {} ]");
    }

    @Test
    public void test095() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test095");
        org.apache.commons.cli.Option.Builder builder0 = org.apache.commons.cli.Option.builder();
        org.apache.commons.cli.Option.Builder builder1 = builder0.deprecated();
        org.apache.commons.cli.Option.Builder builder3 = builder0.hasArg(false);
        // The following exception was thrown during execution in test generation
        try {
            org.apache.commons.cli.Option option4 = builder0.get();
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: Either opt or longOpt must be specified");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(builder1);
        org.junit.Assert.assertNotNull(builder3);
    }

    @Test
    public void test096() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test096");
        org.apache.commons.cli.OptionBuilder optionBuilder1 = org.apache.commons.cli.OptionBuilder.withArgName("arg");
        org.junit.Assert.assertNotNull(optionBuilder1);
    }

    @Test
    public void test097() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test097");
        org.apache.commons.cli.CommandLine.Builder builder0 = new org.apache.commons.cli.CommandLine.Builder();
        org.apache.commons.cli.CommandLine commandLine1 = builder0.build();
        org.apache.commons.cli.CommandLine commandLine2 = builder0.build();
        org.apache.commons.cli.OptionGroup optionGroup3 = new org.apache.commons.cli.OptionGroup();
        java.util.function.Supplier<org.apache.commons.cli.CommandLine> commandLineSupplier4 = null;
        org.apache.commons.cli.CommandLine commandLine5 = commandLine2.getParsedOptionValue(optionGroup3, commandLineSupplier4);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str7 = commandLine5.getOptionValue('#');
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(commandLine1);
        org.junit.Assert.assertNotNull(commandLine2);
        org.junit.Assert.assertNull(commandLine5);
    }

    @Test
    public void test098() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test098");
        java.lang.Class<java.lang.String> strClass0 = org.apache.commons.cli.PatternOptionBuilder.STRING_VALUE;
        org.junit.Assert.assertNotNull(strClass0);
    }

    @Test
    public void test099() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test099");
        org.apache.commons.cli.OptionBuilder optionBuilder0 = org.apache.commons.cli.OptionBuilder.withValueSeparator();
        org.junit.Assert.assertNotNull(optionBuilder0);
    }

    @Test
    public void test100() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test100");
        org.apache.commons.cli.CommandLine.Builder builder0 = org.apache.commons.cli.CommandLine.builder();
        org.junit.Assert.assertNotNull(builder0);
    }

    @Test
    public void test101() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test101");
        org.apache.commons.cli.DefaultParser.Builder builder0 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser1 = builder0.get();
        org.apache.commons.cli.Options options2 = new org.apache.commons.cli.Options();
        org.apache.commons.cli.DefaultParser.Builder builder3 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser4 = builder3.get();
        org.apache.commons.cli.Options options5 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray9 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties10 = null;
        org.apache.commons.cli.CommandLine commandLine12 = defaultParser4.parse(options5, strArray9, properties10, false);
        org.apache.commons.cli.DefaultParser.Builder builder13 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser14 = builder13.get();
        org.apache.commons.cli.Options options15 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray19 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties20 = null;
        org.apache.commons.cli.CommandLine commandLine22 = defaultParser14.parse(options15, strArray19, properties20, false);
        java.lang.Object obj24 = commandLine22.getOptionObject('4');
        org.apache.commons.cli.Option option25 = null;
        java.util.Properties properties26 = commandLine22.getOptionProperties(option25);
        org.apache.commons.cli.CommandLine commandLine28 = defaultParser1.parse(options2, strArray9, properties26, false);
        org.apache.commons.cli.CommandLine.Builder builder30 = new org.apache.commons.cli.CommandLine.Builder();
        org.apache.commons.cli.CommandLine commandLine31 = commandLine28.getParsedOptionValue(' ', (java.util.function.Supplier<org.apache.commons.cli.CommandLine>) builder30);
        java.util.function.Consumer<org.apache.commons.cli.Option> optionConsumer32 = null;
        org.apache.commons.cli.CommandLine.Builder builder33 = builder30.setDeprecatedHandler(optionConsumer32);
        org.apache.commons.cli.CommandLine commandLine34 = builder30.get();
        java.util.Iterator<org.apache.commons.cli.Option> optionItor35 = commandLine34.iterator();
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(defaultParser1);
        org.junit.Assert.assertNotNull(builder3);
        org.junit.Assert.assertNotNull(defaultParser4);
        org.junit.Assert.assertNotNull(strArray9);
        org.junit.Assert.assertArrayEquals(strArray9, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine12);
        org.junit.Assert.assertNotNull(builder13);
        org.junit.Assert.assertNotNull(defaultParser14);
        org.junit.Assert.assertNotNull(strArray19);
        org.junit.Assert.assertArrayEquals(strArray19, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine22);
        org.junit.Assert.assertNull(obj24);
        org.junit.Assert.assertNotNull(properties26);
        org.junit.Assert.assertNotNull(commandLine28);
        org.junit.Assert.assertNotNull(commandLine31);
        org.junit.Assert.assertNotNull(builder33);
        org.junit.Assert.assertNotNull(commandLine34);
        org.junit.Assert.assertNotNull(optionItor35);
    }

    @Test
    public void test102() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test102");
        org.apache.commons.cli.help.TextStyle textStyle0 = org.apache.commons.cli.help.TextStyle.DEFAULT;
        java.lang.CharSequence charSequence3 = textStyle0.pad(false, (java.lang.CharSequence) "");
        boolean boolean4 = textStyle0.isScalable();
        int int5 = textStyle0.getIndent();
        org.junit.Assert.assertNotNull(textStyle0);
        org.junit.Assert.assertEquals("'" + charSequence3 + "' != '" + "" + "'", charSequence3, "");
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + true + "'", boolean4 == true);
        org.junit.Assert.assertTrue("'" + int5 + "' != '" + 0 + "'", int5 == 0);
    }

    @Test
    public void test103() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test103");
        org.apache.commons.cli.CommandLine.Builder builder0 = new org.apache.commons.cli.CommandLine.Builder();
        org.apache.commons.cli.CommandLine commandLine1 = builder0.build();
        org.apache.commons.cli.CommandLine commandLine2 = builder0.build();
        org.apache.commons.cli.Option option3 = null;
        int int4 = commandLine2.getOptionCount(option3);
        java.lang.String str6 = commandLine2.getOptionValue(' ');
        org.junit.Assert.assertNotNull(commandLine1);
        org.junit.Assert.assertNotNull(commandLine2);
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 0 + "'", int4 == 0);
        org.junit.Assert.assertNull(str6);
    }

    @Test
    public void test104() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test104");
        org.apache.commons.cli.Option.Builder builder0 = org.apache.commons.cli.Option.builder();
        org.apache.commons.cli.Option.Builder builder1 = builder0.deprecated();
        org.apache.commons.cli.Option.Builder builder3 = builder0.numberOfArgs((int) (short) -1);
        // The following exception was thrown during execution in test generation
        try {
            org.apache.commons.cli.Option option4 = builder0.get();
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: Either opt or longOpt must be specified");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(builder1);
        org.junit.Assert.assertNotNull(builder3);
    }

    @Test
    public void test105() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test105");
        int int0 = org.apache.commons.cli.help.TextHelpAppendable.DEFAULT_LEFT_PAD;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 1 + "'", int0 == 1);
    }

    @Test
    public void test106() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test106");
        java.lang.Appendable appendable0 = null;
        org.apache.commons.cli.help.TextHelpAppendable textHelpAppendable1 = new org.apache.commons.cli.help.TextHelpAppendable(appendable0);
        java.lang.CharSequence charSequence2 = null;
        textHelpAppendable1.appendParagraph(charSequence2);
        textHelpAppendable1.setIndent(0);
        // The following exception was thrown during execution in test generation
        try {
            textHelpAppendable1.appendHeader(74, (java.lang.CharSequence) "[--]");
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test107() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test107");
        java.lang.Class<java.io.FileInputStream> fileInputStreamClass0 = org.apache.commons.cli.PatternOptionBuilder.EXISTING_FILE_VALUE;
        org.junit.Assert.assertNotNull(fileInputStreamClass0);
    }

    @Test
    public void test108() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test108");
        java.lang.Class<java.lang.Object> objClass0 = org.apache.commons.cli.PatternOptionBuilder.OBJECT_VALUE;
        org.junit.Assert.assertNotNull(objClass0);
    }

    @Test
    public void test109() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test109");
        org.apache.commons.cli.Option.Builder builder0 = org.apache.commons.cli.Option.builder();
        org.apache.commons.cli.Option.Builder builder2 = builder0.hasArg(true);
        org.apache.commons.cli.Option.Builder builder3 = org.apache.commons.cli.Option.builder();
        org.apache.commons.cli.Option.Builder builder4 = builder3.deprecated();
        org.apache.commons.cli.Option.Builder builder6 = builder3.numberOfArgs((int) (short) -1);
        java.lang.Class<java.net.URL> uRLClass7 = org.apache.commons.cli.PatternOptionBuilder.URL_VALUE;
        org.apache.commons.cli.Option.Builder builder8 = builder3.type(uRLClass7);
        org.apache.commons.cli.Option.Builder builder9 = builder0.type(uRLClass7);
        // The following exception was thrown during execution in test generation
        try {
            org.apache.commons.cli.Option option10 = builder0.get();
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: Either opt or longOpt must be specified");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(builder2);
        org.junit.Assert.assertNotNull(builder3);
        org.junit.Assert.assertNotNull(builder4);
        org.junit.Assert.assertNotNull(builder6);
        org.junit.Assert.assertNotNull(uRLClass7);
        org.junit.Assert.assertNotNull(builder8);
        org.junit.Assert.assertNotNull(builder9);
    }

    @Test
    public void test110() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test110");
        org.apache.commons.cli.OptionGroup optionGroup0 = new org.apache.commons.cli.OptionGroup();
        java.util.Collection<org.apache.commons.cli.Option> optionCollection1 = optionGroup0.getOptions();
        java.lang.String str2 = optionGroup0.getSelected();
        org.junit.Assert.assertNotNull(optionCollection1);
        org.junit.Assert.assertNull(str2);
    }

    @Test
    public void test111() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test111");
        org.apache.commons.cli.DefaultParser.Builder builder0 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser1 = builder0.get();
        org.apache.commons.cli.Options options2 = new org.apache.commons.cli.Options();
        org.apache.commons.cli.DefaultParser.Builder builder3 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser4 = builder3.get();
        org.apache.commons.cli.Options options5 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray9 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties10 = null;
        org.apache.commons.cli.CommandLine commandLine12 = defaultParser4.parse(options5, strArray9, properties10, false);
        org.apache.commons.cli.DefaultParser.Builder builder13 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser14 = builder13.get();
        org.apache.commons.cli.Options options15 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray19 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties20 = null;
        org.apache.commons.cli.CommandLine commandLine22 = defaultParser14.parse(options15, strArray19, properties20, false);
        java.lang.Object obj24 = commandLine22.getOptionObject('4');
        org.apache.commons.cli.Option option25 = null;
        java.util.Properties properties26 = commandLine22.getOptionProperties(option25);
        org.apache.commons.cli.CommandLine commandLine28 = defaultParser1.parse(options2, strArray9, properties26, false);
        org.apache.commons.cli.CommandLine.Builder builder30 = new org.apache.commons.cli.CommandLine.Builder();
        org.apache.commons.cli.CommandLine commandLine31 = commandLine28.getParsedOptionValue(' ', (java.util.function.Supplier<org.apache.commons.cli.CommandLine>) builder30);
        org.apache.commons.cli.Option option32 = null;
        org.apache.commons.cli.CommandLine.Builder builder33 = builder30.addOption(option32);
        org.apache.commons.cli.CommandLine.Builder builder35 = builder33.addArg("");
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(defaultParser1);
        org.junit.Assert.assertNotNull(builder3);
        org.junit.Assert.assertNotNull(defaultParser4);
        org.junit.Assert.assertNotNull(strArray9);
        org.junit.Assert.assertArrayEquals(strArray9, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine12);
        org.junit.Assert.assertNotNull(builder13);
        org.junit.Assert.assertNotNull(defaultParser14);
        org.junit.Assert.assertNotNull(strArray19);
        org.junit.Assert.assertArrayEquals(strArray19, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine22);
        org.junit.Assert.assertNull(obj24);
        org.junit.Assert.assertNotNull(properties26);
        org.junit.Assert.assertNotNull(commandLine28);
        org.junit.Assert.assertNotNull(commandLine31);
        org.junit.Assert.assertNotNull(builder33);
        org.junit.Assert.assertNotNull(builder35);
    }

    @Test
    public void test112() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test112");
        org.apache.commons.cli.help.TextStyle textStyle0 = org.apache.commons.cli.help.TextStyle.DEFAULT;
        java.lang.CharSequence charSequence3 = textStyle0.pad(false, (java.lang.CharSequence) "");
        int int4 = textStyle0.getIndent();
        org.apache.commons.cli.help.TextStyle.Alignment alignment5 = textStyle0.getAlignment();
        org.junit.Assert.assertNotNull(textStyle0);
        org.junit.Assert.assertEquals("'" + charSequence3 + "' != '" + "" + "'", charSequence3, "");
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 0 + "'", int4 == 0);
        org.junit.Assert.assertTrue("'" + alignment5 + "' != '" + org.apache.commons.cli.help.TextStyle.Alignment.LEFT + "'", alignment5.equals(org.apache.commons.cli.help.TextStyle.Alignment.LEFT));
    }

    @Test
    public void test113() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test113");
        org.apache.commons.cli.OptionGroup optionGroup0 = new org.apache.commons.cli.OptionGroup();
        boolean boolean1 = optionGroup0.isRequired();
        boolean boolean2 = optionGroup0.isRequired();
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
    }

    @Test
    public void test114() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test114");
        int int0 = org.apache.commons.cli.HelpFormatter.DEFAULT_WIDTH;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 74 + "'", int0 == 74);
    }

    @Test
    public void test115() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test115");
        java.lang.Class<?> wildcardClass0 = org.apache.commons.cli.PatternOptionBuilder.CLASS_VALUE;
        org.junit.Assert.assertNotNull(wildcardClass0);
    }

    @Test
    public void test116() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test116");
        java.lang.Appendable appendable0 = null;
        org.apache.commons.cli.help.TextHelpAppendable textHelpAppendable1 = new org.apache.commons.cli.help.TextHelpAppendable(appendable0);
        java.lang.CharSequence charSequence2 = null;
        textHelpAppendable1.appendParagraph(charSequence2);
        org.apache.commons.cli.help.TextHelpAppendable textHelpAppendable4 = new org.apache.commons.cli.help.TextHelpAppendable((java.lang.Appendable) textHelpAppendable1);
        // The following exception was thrown during execution in test generation
        try {
            textHelpAppendable4.printWrapped("[--]");
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test117() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test117");
        java.lang.Throwable throwable0 = null;
        org.apache.commons.cli.ParseException parseException1 = org.apache.commons.cli.ParseException.wrap(throwable0);
        org.apache.commons.cli.ParseException parseException2 = new org.apache.commons.cli.ParseException(throwable0);
        org.junit.Assert.assertNotNull(parseException1);
    }

    @Test
    public void test118() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test118");
        org.apache.commons.cli.DeprecatedAttributes.Builder builder0 = new org.apache.commons.cli.DeprecatedAttributes.Builder();
        org.apache.commons.cli.DeprecatedAttributes deprecatedAttributes1 = builder0.get();
        java.lang.String str2 = deprecatedAttributes1.getSince();
        boolean boolean3 = deprecatedAttributes1.isForRemoval();
        org.junit.Assert.assertNotNull(deprecatedAttributes1);
        org.junit.Assert.assertEquals("'" + str2 + "' != '" + "" + "'", str2, "");
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
    }

    @Test
    public void test119() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test119");
        org.apache.commons.cli.Option option0 = null;
        org.apache.commons.cli.help.OptionFormatter optionFormatter1 = org.apache.commons.cli.help.OptionFormatter.from(option0);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str2 = optionFormatter1.getLongOpt();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(optionFormatter1);
    }

    @Test
    public void test120() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test120");
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Number number1 = org.apache.commons.cli.TypeHandler.createNumber("[--]");
            org.junit.Assert.fail("Expected exception of type org.apache.commons.cli.ParseException; message: java.lang.NumberFormatException: For input string: \"[--]\"");
        } catch (org.apache.commons.cli.ParseException e) {
            // Expected exception.
        }
    }

    @Test
    public void test121() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test121");
        java.lang.Appendable appendable0 = null;
        org.apache.commons.cli.help.TextHelpAppendable textHelpAppendable1 = new org.apache.commons.cli.help.TextHelpAppendable(appendable0);
        java.lang.CharSequence charSequence2 = null;
        textHelpAppendable1.appendParagraph(charSequence2);
        textHelpAppendable1.setIndent(0);
        java.lang.Object[] objArray11 = new java.lang.Object[] { 10L, (short) 1, "", (short) 100 };
        textHelpAppendable1.appendParagraphFormat("", objArray11);
        org.apache.commons.cli.help.TextHelpAppendable textHelpAppendable13 = new org.apache.commons.cli.help.TextHelpAppendable((java.lang.Appendable) textHelpAppendable1);
        java.lang.CharSequence charSequence14 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.apache.commons.cli.help.FilterHelpAppendable filterHelpAppendable17 = textHelpAppendable1.append(charSequence14, (-1), (int) (byte) 0);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(objArray11);
        org.junit.Assert.assertEquals(java.util.Arrays.deepToString(objArray11), "[10, 1, , 100]");
        org.junit.Assert.assertEquals(java.util.Arrays.toString(objArray11), "[10, 1, , 100]");
    }

    @Test
    public void test122() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test122");
        java.lang.Appendable appendable0 = null;
        org.apache.commons.cli.help.TextHelpAppendable textHelpAppendable1 = new org.apache.commons.cli.help.TextHelpAppendable(appendable0);
        java.lang.CharSequence charSequence2 = null;
        textHelpAppendable1.appendParagraph(charSequence2);
        textHelpAppendable1.setIndent(0);
        java.lang.Object[] objArray11 = new java.lang.Object[] { 10L, (short) 1, "", (short) 100 };
        textHelpAppendable1.appendParagraphFormat("", objArray11);
        org.apache.commons.cli.help.TextHelpAppendable textHelpAppendable13 = new org.apache.commons.cli.help.TextHelpAppendable((java.lang.Appendable) textHelpAppendable1);
        org.apache.commons.cli.help.TextHelpAppendable textHelpAppendable14 = new org.apache.commons.cli.help.TextHelpAppendable((java.lang.Appendable) textHelpAppendable13);
        org.apache.commons.cli.PosixParser posixParser16 = new org.apache.commons.cli.PosixParser();
        org.apache.commons.cli.Options options17 = new org.apache.commons.cli.Options();
        org.apache.commons.cli.DefaultParser.Builder builder18 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser19 = builder18.get();
        org.apache.commons.cli.Options options20 = new org.apache.commons.cli.Options();
        org.apache.commons.cli.DefaultParser.Builder builder21 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser22 = builder21.get();
        org.apache.commons.cli.Options options23 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray27 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties28 = null;
        org.apache.commons.cli.CommandLine commandLine30 = defaultParser22.parse(options23, strArray27, properties28, false);
        org.apache.commons.cli.DefaultParser.Builder builder31 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser32 = builder31.get();
        org.apache.commons.cli.Options options33 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray37 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties38 = null;
        org.apache.commons.cli.CommandLine commandLine40 = defaultParser32.parse(options33, strArray37, properties38, false);
        java.lang.Object obj42 = commandLine40.getOptionObject('4');
        org.apache.commons.cli.Option option43 = null;
        java.util.Properties properties44 = commandLine40.getOptionProperties(option43);
        org.apache.commons.cli.CommandLine commandLine46 = defaultParser19.parse(options20, strArray27, properties44, false);
        org.apache.commons.cli.DefaultParser.Builder builder47 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser48 = builder47.get();
        org.apache.commons.cli.Options options49 = new org.apache.commons.cli.Options();
        org.apache.commons.cli.DefaultParser.Builder builder50 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser51 = builder50.get();
        org.apache.commons.cli.Options options52 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray56 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties57 = null;
        org.apache.commons.cli.CommandLine commandLine59 = defaultParser51.parse(options52, strArray56, properties57, false);
        org.apache.commons.cli.DefaultParser.Builder builder60 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser61 = builder60.get();
        org.apache.commons.cli.Options options62 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray66 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties67 = null;
        org.apache.commons.cli.CommandLine commandLine69 = defaultParser61.parse(options62, strArray66, properties67, false);
        java.lang.Object obj71 = commandLine69.getOptionObject('4');
        org.apache.commons.cli.Option option72 = null;
        java.util.Properties properties73 = commandLine69.getOptionProperties(option72);
        org.apache.commons.cli.CommandLine commandLine75 = defaultParser48.parse(options49, strArray56, properties73, false);
        org.apache.commons.cli.CommandLine commandLine77 = posixParser16.parse(options17, strArray27, properties73, true);
        // The following exception was thrown during execution in test generation
        try {
            textHelpAppendable14.appendFormat("hi!", (java.lang.Object[]) strArray27);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(objArray11);
        org.junit.Assert.assertEquals(java.util.Arrays.deepToString(objArray11), "[10, 1, , 100]");
        org.junit.Assert.assertEquals(java.util.Arrays.toString(objArray11), "[10, 1, , 100]");
        org.junit.Assert.assertNotNull(builder18);
        org.junit.Assert.assertNotNull(defaultParser19);
        org.junit.Assert.assertNotNull(builder21);
        org.junit.Assert.assertNotNull(defaultParser22);
        org.junit.Assert.assertNotNull(strArray27);
        org.junit.Assert.assertArrayEquals(strArray27, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine30);
        org.junit.Assert.assertNotNull(builder31);
        org.junit.Assert.assertNotNull(defaultParser32);
        org.junit.Assert.assertNotNull(strArray37);
        org.junit.Assert.assertArrayEquals(strArray37, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine40);
        org.junit.Assert.assertNull(obj42);
        org.junit.Assert.assertNotNull(properties44);
        org.junit.Assert.assertNotNull(commandLine46);
        org.junit.Assert.assertNotNull(builder47);
        org.junit.Assert.assertNotNull(defaultParser48);
        org.junit.Assert.assertNotNull(builder50);
        org.junit.Assert.assertNotNull(defaultParser51);
        org.junit.Assert.assertNotNull(strArray56);
        org.junit.Assert.assertArrayEquals(strArray56, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine59);
        org.junit.Assert.assertNotNull(builder60);
        org.junit.Assert.assertNotNull(defaultParser61);
        org.junit.Assert.assertNotNull(strArray66);
        org.junit.Assert.assertArrayEquals(strArray66, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine69);
        org.junit.Assert.assertNull(obj71);
        org.junit.Assert.assertNotNull(properties73);
        org.junit.Assert.assertNotNull(commandLine75);
        org.junit.Assert.assertNotNull(commandLine77);
    }

    @Test
    public void test123() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test123");
        org.apache.commons.cli.Option option0 = null;
        org.apache.commons.cli.help.OptionFormatter optionFormatter1 = org.apache.commons.cli.help.OptionFormatter.from(option0);
        java.lang.String str3 = optionFormatter1.toOptional("--");
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean4 = optionFormatter1.isRequired();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(optionFormatter1);
        org.junit.Assert.assertEquals("'" + str3 + "' != '" + "[--]" + "'", str3, "[--]");
    }

    @Test
    public void test124() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test124");
        // The following exception was thrown during execution in test generation
        try {
            org.apache.commons.cli.Option.Builder builder1 = org.apache.commons.cli.Option.builder("--");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Illegal option name '-'.");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test125() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test125");
        org.apache.commons.cli.BasicParser basicParser0 = new org.apache.commons.cli.BasicParser();
    }

    @Test
    public void test126() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test126");
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Class<?> wildcardClass1 = org.apache.commons.cli.TypeHandler.createClass("[--]");
            org.junit.Assert.fail("Expected exception of type org.apache.commons.cli.ParseException; message: java.lang.ClassNotFoundException: [--]");
        } catch (org.apache.commons.cli.ParseException e) {
            // Expected exception.
        }
    }

    @Test
    public void test127() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test127");
        org.apache.commons.cli.CommandLine.Builder builder0 = new org.apache.commons.cli.CommandLine.Builder();
        org.apache.commons.cli.CommandLine commandLine1 = builder0.build();
        org.apache.commons.cli.CommandLine commandLine2 = builder0.build();
        org.apache.commons.cli.Option option3 = null;
        java.lang.String str5 = commandLine2.getOptionValue(option3, "");
        org.apache.commons.cli.DefaultParser.Builder builder6 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser7 = builder6.get();
        org.apache.commons.cli.Options options8 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray12 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties13 = null;
        org.apache.commons.cli.CommandLine commandLine15 = defaultParser7.parse(options8, strArray12, properties13, false);
        java.lang.Object obj17 = commandLine15.getOptionObject('4');
        java.lang.Object obj19 = commandLine15.getOptionObject('4');
        org.apache.commons.cli.OptionGroup optionGroup20 = new org.apache.commons.cli.OptionGroup();
        boolean boolean21 = optionGroup20.isRequired();
        boolean boolean22 = commandLine15.hasOption(optionGroup20);
        java.lang.String[] strArray26 = new java.lang.String[] { "hi!", "arg" };
        java.util.ArrayList<java.lang.String> strList27 = new java.util.ArrayList<java.lang.String>();
        boolean boolean28 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList27, strArray26);
        org.apache.commons.cli.AmbiguousOptionException ambiguousOptionException29 = new org.apache.commons.cli.AmbiguousOptionException("hi!", (java.util.Collection<java.lang.String>) strList27);
        java.lang.String[] strArray33 = new java.lang.String[] { "hi!", "arg" };
        java.util.ArrayList<java.lang.String> strList34 = new java.util.ArrayList<java.lang.String>();
        boolean boolean35 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList34, strArray33);
        org.apache.commons.cli.AmbiguousOptionException ambiguousOptionException36 = new org.apache.commons.cli.AmbiguousOptionException("hi!", (java.util.Collection<java.lang.String>) strList34);
        org.apache.commons.cli.ParseException parseException37 = new org.apache.commons.cli.ParseException((java.lang.Throwable) ambiguousOptionException36);
        org.apache.commons.cli.AmbiguousOptionException[] ambiguousOptionExceptionArray38 = new org.apache.commons.cli.AmbiguousOptionException[] { ambiguousOptionException29, ambiguousOptionException36 };
        org.apache.commons.cli.AmbiguousOptionException[] ambiguousOptionExceptionArray39 = commandLine2.getParsedOptionValues(optionGroup20, ambiguousOptionExceptionArray38);
        org.junit.Assert.assertNotNull(commandLine1);
        org.junit.Assert.assertNotNull(commandLine2);
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "" + "'", str5, "");
        org.junit.Assert.assertNotNull(builder6);
        org.junit.Assert.assertNotNull(defaultParser7);
        org.junit.Assert.assertNotNull(strArray12);
        org.junit.Assert.assertArrayEquals(strArray12, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine15);
        org.junit.Assert.assertNull(obj17);
        org.junit.Assert.assertNull(obj19);
        org.junit.Assert.assertTrue("'" + boolean21 + "' != '" + false + "'", boolean21 == false);
        org.junit.Assert.assertTrue("'" + boolean22 + "' != '" + false + "'", boolean22 == false);
        org.junit.Assert.assertNotNull(strArray26);
        org.junit.Assert.assertArrayEquals(strArray26, new java.lang.String[] { "hi!", "arg" });
        org.junit.Assert.assertTrue("'" + boolean28 + "' != '" + true + "'", boolean28 == true);
        org.junit.Assert.assertNotNull(strArray33);
        org.junit.Assert.assertArrayEquals(strArray33, new java.lang.String[] { "hi!", "arg" });
        org.junit.Assert.assertTrue("'" + boolean35 + "' != '" + true + "'", boolean35 == true);
        org.junit.Assert.assertNotNull(ambiguousOptionExceptionArray38);
        org.junit.Assert.assertNotNull(ambiguousOptionExceptionArray39);
    }

    @Test
    public void test128() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test128");
        org.apache.commons.cli.Option option1 = org.apache.commons.cli.OptionBuilder.create("arg");
        java.util.List<java.lang.String> strList2 = option1.getValuesList();
        org.apache.commons.cli.MissingOptionException missingOptionException3 = new org.apache.commons.cli.MissingOptionException((java.util.List) strList2);
        org.junit.Assert.assertNotNull(option1);
        org.junit.Assert.assertNotNull(strList2);
    }

    @Test
    public void test129() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test129");
        java.lang.String[] strArray3 = new java.lang.String[] { "hi!", "arg" };
        java.util.ArrayList<java.lang.String> strList4 = new java.util.ArrayList<java.lang.String>();
        boolean boolean5 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList4, strArray3);
        org.apache.commons.cli.AmbiguousOptionException ambiguousOptionException6 = new org.apache.commons.cli.AmbiguousOptionException("hi!", (java.util.Collection<java.lang.String>) strList4);
        java.util.Collection<java.lang.String> strCollection7 = ambiguousOptionException6.getMatchingOptions();
        org.apache.commons.cli.ParseException parseException8 = new org.apache.commons.cli.ParseException((java.lang.Throwable) ambiguousOptionException6);
        org.junit.Assert.assertNotNull(strArray3);
        org.junit.Assert.assertArrayEquals(strArray3, new java.lang.String[] { "hi!", "arg" });
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertNotNull(strCollection7);
    }

    @Test
    public void test130() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test130");
        org.apache.commons.cli.UnrecognizedOptionException unrecognizedOptionException1 = new org.apache.commons.cli.UnrecognizedOptionException("");
        org.apache.commons.cli.ParseException parseException2 = new org.apache.commons.cli.ParseException((java.lang.Throwable) unrecognizedOptionException1);
    }

    @Test
    public void test131() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test131");
        int int0 = org.apache.commons.cli.HelpFormatter.DEFAULT_DESC_PAD;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 3 + "'", int0 == 3);
    }

    @Test
    public void test132() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test132");
        org.apache.commons.cli.OptionBuilder optionBuilder1 = org.apache.commons.cli.OptionBuilder.withValueSeparator('#');
        org.junit.Assert.assertNotNull(optionBuilder1);
    }

    @Test
    public void test133() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test133");
        java.lang.String str0 = org.apache.commons.cli.help.AbstractHelpFormatter.DEFAULT_OPTION_GROUP_SEPARATOR;
        org.junit.Assert.assertEquals("'" + str0 + "' != '" + " | " + "'", str0, " | ");
    }

    @Test
    public void test134() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test134");
        org.apache.commons.cli.DefaultParser.Builder builder0 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser1 = builder0.get();
        org.apache.commons.cli.PosixParser posixParser2 = new org.apache.commons.cli.PosixParser();
        org.apache.commons.cli.Options options3 = new org.apache.commons.cli.Options();
        org.apache.commons.cli.DefaultParser.Builder builder4 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser5 = builder4.get();
        org.apache.commons.cli.Options options6 = new org.apache.commons.cli.Options();
        org.apache.commons.cli.DefaultParser.Builder builder7 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser8 = builder7.get();
        org.apache.commons.cli.Options options9 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray13 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties14 = null;
        org.apache.commons.cli.CommandLine commandLine16 = defaultParser8.parse(options9, strArray13, properties14, false);
        org.apache.commons.cli.DefaultParser.Builder builder17 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser18 = builder17.get();
        org.apache.commons.cli.Options options19 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray23 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties24 = null;
        org.apache.commons.cli.CommandLine commandLine26 = defaultParser18.parse(options19, strArray23, properties24, false);
        java.lang.Object obj28 = commandLine26.getOptionObject('4');
        org.apache.commons.cli.Option option29 = null;
        java.util.Properties properties30 = commandLine26.getOptionProperties(option29);
        org.apache.commons.cli.CommandLine commandLine32 = defaultParser5.parse(options6, strArray13, properties30, false);
        org.apache.commons.cli.DefaultParser.Builder builder33 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser34 = builder33.get();
        org.apache.commons.cli.Options options35 = new org.apache.commons.cli.Options();
        org.apache.commons.cli.DefaultParser.Builder builder36 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser37 = builder36.get();
        org.apache.commons.cli.Options options38 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray42 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties43 = null;
        org.apache.commons.cli.CommandLine commandLine45 = defaultParser37.parse(options38, strArray42, properties43, false);
        org.apache.commons.cli.DefaultParser.Builder builder46 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser47 = builder46.get();
        org.apache.commons.cli.Options options48 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray52 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties53 = null;
        org.apache.commons.cli.CommandLine commandLine55 = defaultParser47.parse(options48, strArray52, properties53, false);
        java.lang.Object obj57 = commandLine55.getOptionObject('4');
        org.apache.commons.cli.Option option58 = null;
        java.util.Properties properties59 = commandLine55.getOptionProperties(option58);
        org.apache.commons.cli.CommandLine commandLine61 = defaultParser34.parse(options35, strArray42, properties59, false);
        org.apache.commons.cli.CommandLine commandLine63 = posixParser2.parse(options3, strArray13, properties59, true);
        org.apache.commons.cli.DefaultParser.Builder builder64 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser65 = builder64.get();
        org.apache.commons.cli.Options options66 = new org.apache.commons.cli.Options();
        org.apache.commons.cli.DefaultParser.Builder builder67 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser68 = builder67.get();
        org.apache.commons.cli.Options options69 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray73 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties74 = null;
        org.apache.commons.cli.CommandLine commandLine76 = defaultParser68.parse(options69, strArray73, properties74, false);
        org.apache.commons.cli.DefaultParser.Builder builder77 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser78 = builder77.get();
        org.apache.commons.cli.Options options79 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray83 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties84 = null;
        org.apache.commons.cli.CommandLine commandLine86 = defaultParser78.parse(options79, strArray83, properties84, false);
        java.lang.Object obj88 = commandLine86.getOptionObject('4');
        org.apache.commons.cli.Option option89 = null;
        java.util.Properties properties90 = commandLine86.getOptionProperties(option89);
        org.apache.commons.cli.CommandLine commandLine92 = defaultParser65.parse(options66, strArray73, properties90, false);
        org.apache.commons.cli.CommandLine commandLine94 = defaultParser1.parse(options3, strArray73, false);
        boolean boolean96 = options3.hasOption("-");
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(defaultParser1);
        org.junit.Assert.assertNotNull(builder4);
        org.junit.Assert.assertNotNull(defaultParser5);
        org.junit.Assert.assertNotNull(builder7);
        org.junit.Assert.assertNotNull(defaultParser8);
        org.junit.Assert.assertNotNull(strArray13);
        org.junit.Assert.assertArrayEquals(strArray13, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine16);
        org.junit.Assert.assertNotNull(builder17);
        org.junit.Assert.assertNotNull(defaultParser18);
        org.junit.Assert.assertNotNull(strArray23);
        org.junit.Assert.assertArrayEquals(strArray23, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine26);
        org.junit.Assert.assertNull(obj28);
        org.junit.Assert.assertNotNull(properties30);
        org.junit.Assert.assertNotNull(commandLine32);
        org.junit.Assert.assertNotNull(builder33);
        org.junit.Assert.assertNotNull(defaultParser34);
        org.junit.Assert.assertNotNull(builder36);
        org.junit.Assert.assertNotNull(defaultParser37);
        org.junit.Assert.assertNotNull(strArray42);
        org.junit.Assert.assertArrayEquals(strArray42, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine45);
        org.junit.Assert.assertNotNull(builder46);
        org.junit.Assert.assertNotNull(defaultParser47);
        org.junit.Assert.assertNotNull(strArray52);
        org.junit.Assert.assertArrayEquals(strArray52, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine55);
        org.junit.Assert.assertNull(obj57);
        org.junit.Assert.assertNotNull(properties59);
        org.junit.Assert.assertNotNull(commandLine61);
        org.junit.Assert.assertNotNull(commandLine63);
        org.junit.Assert.assertNotNull(builder64);
        org.junit.Assert.assertNotNull(defaultParser65);
        org.junit.Assert.assertNotNull(builder67);
        org.junit.Assert.assertNotNull(defaultParser68);
        org.junit.Assert.assertNotNull(strArray73);
        org.junit.Assert.assertArrayEquals(strArray73, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine76);
        org.junit.Assert.assertNotNull(builder77);
        org.junit.Assert.assertNotNull(defaultParser78);
        org.junit.Assert.assertNotNull(strArray83);
        org.junit.Assert.assertArrayEquals(strArray83, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine86);
        org.junit.Assert.assertNull(obj88);
        org.junit.Assert.assertNotNull(properties90);
        org.junit.Assert.assertNotNull(commandLine92);
        org.junit.Assert.assertNotNull(commandLine94);
        org.junit.Assert.assertTrue("'" + boolean96 + "' != '" + false + "'", boolean96 == false);
    }

    @Test
    public void test135() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test135");
        java.lang.Appendable appendable0 = null;
        org.apache.commons.cli.help.TextHelpAppendable textHelpAppendable1 = new org.apache.commons.cli.help.TextHelpAppendable(appendable0);
        java.lang.CharSequence charSequence2 = null;
        textHelpAppendable1.appendParagraph(charSequence2);
        textHelpAppendable1.setIndent(0);
        java.lang.Object[] objArray11 = new java.lang.Object[] { 10L, (short) 1, "", (short) 100 };
        textHelpAppendable1.appendParagraphFormat("", objArray11);
        org.apache.commons.cli.help.TextHelpAppendable textHelpAppendable13 = new org.apache.commons.cli.help.TextHelpAppendable((java.lang.Appendable) textHelpAppendable1);
        // The following exception was thrown during execution in test generation
        try {
            org.apache.commons.cli.help.FilterHelpAppendable filterHelpAppendable17 = textHelpAppendable1.append((java.lang.CharSequence) "[ Options: [ short {} ] [ long {} ]", (int) '#', 74);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(objArray11);
        org.junit.Assert.assertEquals(java.util.Arrays.deepToString(objArray11), "[10, 1, , 100]");
        org.junit.Assert.assertEquals(java.util.Arrays.toString(objArray11), "[10, 1, , 100]");
    }

    @Test
    public void test136() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test136");
        java.lang.String str0 = org.apache.commons.cli.HelpFormatter.DEFAULT_LONG_OPT_SEPARATOR;
        org.junit.Assert.assertEquals("'" + str0 + "' != '" + " " + "'", str0, " ");
    }

    @Test
    public void test137() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test137");
        org.apache.commons.cli.Option option1 = org.apache.commons.cli.OptionBuilder.create("arg");
        option1.setRequired(true);
        boolean boolean4 = option1.hasArgName();
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean6 = option1.addValue("--");
            org.junit.Assert.fail("Expected exception of type java.lang.UnsupportedOperationException; message: The addValue method is not intended for client use. Subclasses should use the processValue method instead.");
        } catch (java.lang.UnsupportedOperationException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(option1);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
    }

    @Test
    public void test138() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test138");
        org.apache.commons.cli.DefaultParser.Builder builder0 = org.apache.commons.cli.DefaultParser.builder();
        java.util.function.Consumer<org.apache.commons.cli.Option> optionConsumer1 = null;
        org.apache.commons.cli.DefaultParser.Builder builder2 = builder0.setDeprecatedHandler(optionConsumer1);
        org.apache.commons.cli.DefaultParser.Builder builder4 = builder2.setStripLeadingAndTrailingQuotes((java.lang.Boolean) false);
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(builder2);
        org.junit.Assert.assertNotNull(builder4);
    }

    @Test
    public void test139() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test139");
        org.apache.commons.cli.DefaultParser.Builder builder0 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser1 = builder0.get();
        org.apache.commons.cli.Options options2 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties7 = null;
        org.apache.commons.cli.CommandLine commandLine9 = defaultParser1.parse(options2, strArray6, properties7, false);
        org.apache.commons.cli.DefaultParser.Builder builder11 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser12 = builder11.get();
        org.apache.commons.cli.Options options13 = new org.apache.commons.cli.Options();
        org.apache.commons.cli.DefaultParser.Builder builder14 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser15 = builder14.get();
        org.apache.commons.cli.Options options16 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray20 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties21 = null;
        org.apache.commons.cli.CommandLine commandLine23 = defaultParser15.parse(options16, strArray20, properties21, false);
        org.apache.commons.cli.DefaultParser.Builder builder24 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser25 = builder24.get();
        org.apache.commons.cli.Options options26 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray30 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties31 = null;
        org.apache.commons.cli.CommandLine commandLine33 = defaultParser25.parse(options26, strArray30, properties31, false);
        java.lang.Object obj35 = commandLine33.getOptionObject('4');
        org.apache.commons.cli.Option option36 = null;
        java.util.Properties properties37 = commandLine33.getOptionProperties(option36);
        org.apache.commons.cli.CommandLine commandLine39 = defaultParser12.parse(options13, strArray20, properties37, false);
        org.apache.commons.cli.CommandLine.Builder builder41 = new org.apache.commons.cli.CommandLine.Builder();
        org.apache.commons.cli.CommandLine commandLine42 = commandLine39.getParsedOptionValue(' ', (java.util.function.Supplier<org.apache.commons.cli.CommandLine>) builder41);
        org.apache.commons.cli.Option option43 = null;
        org.apache.commons.cli.CommandLine.Builder builder44 = builder41.addOption(option43);
        org.apache.commons.cli.CommandLine commandLine45 = commandLine9.getParsedOptionValue('#', (java.util.function.Supplier<org.apache.commons.cli.CommandLine>) builder41);
        java.util.function.Supplier<java.lang.String> strSupplier47 = null;
        java.lang.String str48 = commandLine45.getOptionValue('4', strSupplier47);
        java.lang.String str51 = commandLine45.getOptionValue('#', "[ Options: [ short {} ] [ long {} ]");
        java.lang.Object obj53 = commandLine45.getOptionObject(" | ");
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(defaultParser1);
        org.junit.Assert.assertNotNull(strArray6);
        org.junit.Assert.assertArrayEquals(strArray6, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine9);
        org.junit.Assert.assertNotNull(builder11);
        org.junit.Assert.assertNotNull(defaultParser12);
        org.junit.Assert.assertNotNull(builder14);
        org.junit.Assert.assertNotNull(defaultParser15);
        org.junit.Assert.assertNotNull(strArray20);
        org.junit.Assert.assertArrayEquals(strArray20, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine23);
        org.junit.Assert.assertNotNull(builder24);
        org.junit.Assert.assertNotNull(defaultParser25);
        org.junit.Assert.assertNotNull(strArray30);
        org.junit.Assert.assertArrayEquals(strArray30, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine33);
        org.junit.Assert.assertNull(obj35);
        org.junit.Assert.assertNotNull(properties37);
        org.junit.Assert.assertNotNull(commandLine39);
        org.junit.Assert.assertNotNull(commandLine42);
        org.junit.Assert.assertNotNull(builder44);
        org.junit.Assert.assertNotNull(commandLine45);
        org.junit.Assert.assertNull(str48);
        org.junit.Assert.assertEquals("'" + str51 + "' != '" + "[ Options: [ short {} ] [ long {} ]" + "'", str51, "[ Options: [ short {} ] [ long {} ]");
        org.junit.Assert.assertNull(obj53);
    }

    @Test
    public void test140() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test140");
        org.apache.commons.cli.Option.Builder builder0 = org.apache.commons.cli.Option.builder();
        org.apache.commons.cli.Option.Builder builder2 = builder0.hasArg(true);
        org.apache.commons.cli.Option.Builder builder4 = builder0.valueSeparator('4');
        // The following exception was thrown during execution in test generation
        try {
            org.apache.commons.cli.Option.Builder builder6 = builder0.option("");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Empty option name.");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(builder2);
        org.junit.Assert.assertNotNull(builder4);
    }

    @Test
    public void test141() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test141");
        java.util.Map<java.lang.Class<?>, org.apache.commons.cli.Converter<?, ? extends java.lang.Throwable>> wildcardClassMap0 = org.apache.commons.cli.TypeHandler.createDefaultMap();
        org.junit.Assert.assertNotNull(wildcardClassMap0);
    }

    @Test
    public void test142() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test142");
        org.apache.commons.cli.help.OptionFormatter.Builder builder0 = org.apache.commons.cli.help.OptionFormatter.builder();
        org.apache.commons.cli.help.OptionFormatter.Builder builder2 = builder0.setDefaultArgName("");
        org.apache.commons.cli.help.OptionFormatter.Builder builder4 = builder2.setOptSeparator("[--]");
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(builder2);
        org.junit.Assert.assertNotNull(builder4);
    }

    @Test
    public void test143() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test143");
        java.lang.Appendable appendable0 = null;
        org.apache.commons.cli.help.TextHelpAppendable textHelpAppendable1 = new org.apache.commons.cli.help.TextHelpAppendable(appendable0);
        java.lang.CharSequence charSequence2 = null;
        textHelpAppendable1.appendParagraph(charSequence2);
        org.apache.commons.cli.help.TextHelpAppendable textHelpAppendable4 = new org.apache.commons.cli.help.TextHelpAppendable((java.lang.Appendable) textHelpAppendable1);
        textHelpAppendable4.setIndent(10);
    }

    @Test
    public void test144() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test144");
        org.apache.commons.cli.DefaultParser.Builder builder0 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser1 = builder0.get();
        org.apache.commons.cli.Options options2 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties7 = null;
        org.apache.commons.cli.CommandLine commandLine9 = defaultParser1.parse(options2, strArray6, properties7, false);
        java.lang.Object obj11 = commandLine9.getOptionObject('4');
        org.apache.commons.cli.OptionBuilder optionBuilder13 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs();
        org.apache.commons.cli.OptionBuilder optionBuilder15 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs((int) (byte) 1);
        org.apache.commons.cli.OptionBuilder optionBuilder17 = org.apache.commons.cli.OptionBuilder.withLongOpt("");
        org.apache.commons.cli.OptionBuilder optionBuilder19 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs((int) (byte) 1);
        org.apache.commons.cli.OptionBuilder optionBuilder20 = org.apache.commons.cli.OptionBuilder.hasArgs();
        org.apache.commons.cli.OptionBuilder optionBuilder21 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs();
        org.apache.commons.cli.OptionBuilder[] optionBuilderArray22 = new org.apache.commons.cli.OptionBuilder[] { optionBuilder13, optionBuilder15, optionBuilder17, optionBuilder19, optionBuilder20, optionBuilder21 };
        org.apache.commons.cli.OptionBuilder[] optionBuilderArray23 = commandLine9.getParsedOptionValues('#', optionBuilderArray22);
        java.util.function.Supplier<java.lang.String> strSupplier25 = null;
        java.lang.String str26 = commandLine9.getOptionValue('a', strSupplier25);
        org.apache.commons.cli.Option option27 = null;
        java.lang.Throwable throwable28 = null;
        org.apache.commons.cli.ParseException parseException29 = org.apache.commons.cli.ParseException.wrap(throwable28);
        java.lang.Throwable throwable30 = commandLine9.getParsedOptionValue(option27, (java.lang.Throwable) parseException29);
        java.lang.String[] strArray35 = new java.lang.String[] { "hi!", "arg" };
        java.util.ArrayList<java.lang.String> strList36 = new java.util.ArrayList<java.lang.String>();
        boolean boolean37 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList36, strArray35);
        org.apache.commons.cli.AmbiguousOptionException ambiguousOptionException38 = new org.apache.commons.cli.AmbiguousOptionException("hi!", (java.util.Collection<java.lang.String>) strList36);
        java.lang.String[] strArray42 = new java.lang.String[] { "hi!", "arg" };
        java.util.ArrayList<java.lang.String> strList43 = new java.util.ArrayList<java.lang.String>();
        boolean boolean44 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList43, strArray42);
        org.apache.commons.cli.AmbiguousOptionException ambiguousOptionException45 = new org.apache.commons.cli.AmbiguousOptionException("hi!", (java.util.Collection<java.lang.String>) strList43);
        java.lang.Exception[] exceptionArray46 = new java.lang.Exception[] { ambiguousOptionException38, ambiguousOptionException45 };
        java.lang.Exception[] exceptionArray47 = commandLine9.getParsedOptionValues("arg", exceptionArray46);
        boolean boolean49 = commandLine9.hasOption('a');
        org.apache.commons.cli.OptionBuilder optionBuilder51 = commandLine9.getParsedOptionValue('#');
        boolean boolean53 = commandLine9.hasOption("");
        org.apache.commons.cli.CommandLine[] commandLineArray55 = new org.apache.commons.cli.CommandLine[] {};
        org.apache.commons.cli.CommandLine[] commandLineArray56 = commandLine9.getParsedOptionValues('#', commandLineArray55);
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(defaultParser1);
        org.junit.Assert.assertNotNull(strArray6);
        org.junit.Assert.assertArrayEquals(strArray6, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine9);
        org.junit.Assert.assertNull(obj11);
        org.junit.Assert.assertNotNull(optionBuilder13);
        org.junit.Assert.assertNotNull(optionBuilder15);
        org.junit.Assert.assertNotNull(optionBuilder17);
        org.junit.Assert.assertNotNull(optionBuilder19);
        org.junit.Assert.assertNotNull(optionBuilder20);
        org.junit.Assert.assertNotNull(optionBuilder21);
        org.junit.Assert.assertNotNull(optionBuilderArray22);
        org.junit.Assert.assertNotNull(optionBuilderArray23);
        org.junit.Assert.assertNull(str26);
        org.junit.Assert.assertNotNull(parseException29);
        org.junit.Assert.assertNotNull(throwable30);
        org.junit.Assert.assertNull("throwable30.getLocalizedMessage() == null", throwable30.getLocalizedMessage());
        org.junit.Assert.assertNull("throwable30.getMessage() == null", throwable30.getMessage());
        org.junit.Assert.assertEquals(throwable30.toString(), "org.apache.commons.cli.ParseException");
        org.junit.Assert.assertNotNull(strArray35);
        org.junit.Assert.assertArrayEquals(strArray35, new java.lang.String[] { "hi!", "arg" });
        org.junit.Assert.assertTrue("'" + boolean37 + "' != '" + true + "'", boolean37 == true);
        org.junit.Assert.assertNotNull(strArray42);
        org.junit.Assert.assertArrayEquals(strArray42, new java.lang.String[] { "hi!", "arg" });
        org.junit.Assert.assertTrue("'" + boolean44 + "' != '" + true + "'", boolean44 == true);
        org.junit.Assert.assertNotNull(exceptionArray46);
        org.junit.Assert.assertNotNull(exceptionArray47);
        org.junit.Assert.assertTrue("'" + boolean49 + "' != '" + false + "'", boolean49 == false);
        org.junit.Assert.assertNull(optionBuilder51);
        org.junit.Assert.assertTrue("'" + boolean53 + "' != '" + false + "'", boolean53 == false);
        org.junit.Assert.assertNotNull(commandLineArray55);
        org.junit.Assert.assertArrayEquals(commandLineArray55, new org.apache.commons.cli.CommandLine[] {});
        org.junit.Assert.assertNotNull(commandLineArray56);
        org.junit.Assert.assertArrayEquals(commandLineArray56, new org.apache.commons.cli.CommandLine[] {});
    }

    @Test
    public void test145() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test145");
        java.lang.Appendable appendable0 = null;
        org.apache.commons.cli.help.TextHelpAppendable textHelpAppendable1 = new org.apache.commons.cli.help.TextHelpAppendable(appendable0);
        java.lang.CharSequence charSequence2 = null;
        textHelpAppendable1.appendParagraph(charSequence2);
        textHelpAppendable1.setIndent(0);
        java.lang.Object[] objArray11 = new java.lang.Object[] { 10L, (short) 1, "", (short) 100 };
        textHelpAppendable1.appendParagraphFormat("", objArray11);
        org.apache.commons.cli.help.TextHelpAppendable textHelpAppendable13 = new org.apache.commons.cli.help.TextHelpAppendable((java.lang.Appendable) textHelpAppendable1);
        org.apache.commons.cli.help.TextHelpAppendable textHelpAppendable14 = new org.apache.commons.cli.help.TextHelpAppendable((java.lang.Appendable) textHelpAppendable13);
        // The following exception was thrown during execution in test generation
        try {
            org.apache.commons.cli.help.FilterHelpAppendable filterHelpAppendable18 = textHelpAppendable14.append((java.lang.CharSequence) "arg", (-2), (int) (byte) 0);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(objArray11);
        org.junit.Assert.assertEquals(java.util.Arrays.deepToString(objArray11), "[10, 1, , 100]");
        org.junit.Assert.assertEquals(java.util.Arrays.toString(objArray11), "[10, 1, , 100]");
    }

    @Test
    public void test146() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test146");
        org.apache.commons.cli.Option option2 = org.apache.commons.cli.OptionBuilder.create("arg");
        option2.setRequired(true);
        boolean boolean5 = option2.hasArgName();
        java.lang.String str7 = option2.getValue("");
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj8 = org.apache.commons.cli.TypeHandler.createValue("", (java.lang.Object) option2);
            org.junit.Assert.fail("Expected exception of type java.lang.ClassCastException; message: org.apache.commons.cli.Option cannot be cast to java.lang.Class");
        } catch (java.lang.ClassCastException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(option2);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "" + "'", str7, "");
    }

    @Test
    public void test147() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test147");
        java.lang.Appendable appendable0 = null;
        org.apache.commons.cli.help.TextHelpAppendable textHelpAppendable1 = new org.apache.commons.cli.help.TextHelpAppendable(appendable0);
        java.lang.CharSequence charSequence2 = null;
        textHelpAppendable1.appendParagraph(charSequence2);
        textHelpAppendable1.setIndent(0);
        textHelpAppendable1.setLeftPad(100);
        // The following exception was thrown during execution in test generation
        try {
            textHelpAppendable1.appendHeader((int) (byte) 10, (java.lang.CharSequence) "[ Options: [ short {} ] [ long {} ]");
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test148() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test148");
        org.apache.commons.cli.help.OptionFormatter.Builder builder0 = org.apache.commons.cli.help.OptionFormatter.builder();
        org.apache.commons.cli.help.OptionFormatter.Builder builder2 = builder0.setOptSeparator("--");
        org.apache.commons.cli.help.OptionFormatter.Builder builder5 = builder0.setArgumentNameDelimiters(" | ", "-");
        java.util.function.BiFunction<org.apache.commons.cli.help.OptionFormatter, java.lang.Boolean, java.lang.String> optionFormatterBiFunction6 = null;
        org.apache.commons.cli.help.OptionFormatter.Builder builder7 = builder5.setSyntaxFormatFunction(optionFormatterBiFunction6);
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(builder2);
        org.junit.Assert.assertNotNull(builder5);
        org.junit.Assert.assertNotNull(builder7);
    }

    @Test
    public void test149() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test149");
        org.apache.commons.cli.OptionBuilder optionBuilder1 = org.apache.commons.cli.OptionBuilder.isRequired(false);
        org.junit.Assert.assertNotNull(optionBuilder1);
    }

    @Test
    public void test150() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test150");
        int int0 = org.apache.commons.cli.help.HelpFormatter.DEFAULT_LEFT_PAD;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 1 + "'", int0 == 1);
    }

    @Test
    public void test151() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test151");
        org.apache.commons.cli.DeprecatedAttributes.Builder builder0 = new org.apache.commons.cli.DeprecatedAttributes.Builder();
        org.apache.commons.cli.DeprecatedAttributes deprecatedAttributes1 = builder0.get();
        org.apache.commons.cli.DeprecatedAttributes.Builder builder3 = builder0.setSince("");
        org.junit.Assert.assertNotNull(deprecatedAttributes1);
        org.junit.Assert.assertNotNull(builder3);
    }

    @Test
    public void test152() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test152");
        // The following exception was thrown during execution in test generation
        try {
            java.util.Date date1 = org.apache.commons.cli.TypeHandler.createDate("-");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: org.apache.commons.cli.ParseException: java.text.ParseException: Unparseable date: \"-\"");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test153() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test153");
        org.apache.commons.cli.DefaultParser.Builder builder0 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser1 = builder0.get();
        org.apache.commons.cli.Options options2 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties7 = null;
        org.apache.commons.cli.CommandLine commandLine9 = defaultParser1.parse(options2, strArray6, properties7, false);
        org.apache.commons.cli.DefaultParser.Builder builder11 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser12 = builder11.get();
        org.apache.commons.cli.Options options13 = new org.apache.commons.cli.Options();
        org.apache.commons.cli.DefaultParser.Builder builder14 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser15 = builder14.get();
        org.apache.commons.cli.Options options16 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray20 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties21 = null;
        org.apache.commons.cli.CommandLine commandLine23 = defaultParser15.parse(options16, strArray20, properties21, false);
        org.apache.commons.cli.DefaultParser.Builder builder24 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser25 = builder24.get();
        org.apache.commons.cli.Options options26 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray30 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties31 = null;
        org.apache.commons.cli.CommandLine commandLine33 = defaultParser25.parse(options26, strArray30, properties31, false);
        java.lang.Object obj35 = commandLine33.getOptionObject('4');
        org.apache.commons.cli.Option option36 = null;
        java.util.Properties properties37 = commandLine33.getOptionProperties(option36);
        org.apache.commons.cli.CommandLine commandLine39 = defaultParser12.parse(options13, strArray20, properties37, false);
        org.apache.commons.cli.CommandLine.Builder builder41 = new org.apache.commons.cli.CommandLine.Builder();
        org.apache.commons.cli.CommandLine commandLine42 = commandLine39.getParsedOptionValue(' ', (java.util.function.Supplier<org.apache.commons.cli.CommandLine>) builder41);
        org.apache.commons.cli.Option option43 = null;
        org.apache.commons.cli.CommandLine.Builder builder44 = builder41.addOption(option43);
        org.apache.commons.cli.CommandLine commandLine45 = commandLine9.getParsedOptionValue('#', (java.util.function.Supplier<org.apache.commons.cli.CommandLine>) builder41);
        java.util.function.Supplier<java.lang.String> strSupplier47 = null;
        java.lang.String str48 = commandLine45.getOptionValue('4', strSupplier47);
        java.lang.String str51 = commandLine45.getOptionValue('#', "[ Options: [ short {} ] [ long {} ]");
        java.lang.Class<?> wildcardClass52 = commandLine45.getClass();
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(defaultParser1);
        org.junit.Assert.assertNotNull(strArray6);
        org.junit.Assert.assertArrayEquals(strArray6, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine9);
        org.junit.Assert.assertNotNull(builder11);
        org.junit.Assert.assertNotNull(defaultParser12);
        org.junit.Assert.assertNotNull(builder14);
        org.junit.Assert.assertNotNull(defaultParser15);
        org.junit.Assert.assertNotNull(strArray20);
        org.junit.Assert.assertArrayEquals(strArray20, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine23);
        org.junit.Assert.assertNotNull(builder24);
        org.junit.Assert.assertNotNull(defaultParser25);
        org.junit.Assert.assertNotNull(strArray30);
        org.junit.Assert.assertArrayEquals(strArray30, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine33);
        org.junit.Assert.assertNull(obj35);
        org.junit.Assert.assertNotNull(properties37);
        org.junit.Assert.assertNotNull(commandLine39);
        org.junit.Assert.assertNotNull(commandLine42);
        org.junit.Assert.assertNotNull(builder44);
        org.junit.Assert.assertNotNull(commandLine45);
        org.junit.Assert.assertNull(str48);
        org.junit.Assert.assertEquals("'" + str51 + "' != '" + "[ Options: [ short {} ] [ long {} ]" + "'", str51, "[ Options: [ short {} ] [ long {} ]");
        org.junit.Assert.assertNotNull(wildcardClass52);
    }

    @Test
    public void test154() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test154");
        org.apache.commons.cli.Option.Builder builder0 = org.apache.commons.cli.Option.builder();
        org.apache.commons.cli.Option.Builder builder1 = builder0.deprecated();
        org.apache.commons.cli.Option.Builder builder3 = builder0.numberOfArgs((int) (short) -1);
        org.apache.commons.cli.Option.Builder builder4 = builder0.valueSeparator();
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(builder1);
        org.junit.Assert.assertNotNull(builder3);
        org.junit.Assert.assertNotNull(builder4);
    }

    @Test
    public void test155() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test155");
        org.apache.commons.cli.Option option1 = org.apache.commons.cli.OptionBuilder.create("arg");
        option1.setRequired(true);
        java.lang.String str5 = option1.getValue("");
        org.junit.Assert.assertNotNull(option1);
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "" + "'", str5, "");
    }

    @Test
    public void test156() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test156");
        org.apache.commons.cli.DefaultParser.Builder builder0 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser1 = builder0.get();
        org.apache.commons.cli.Options options2 = new org.apache.commons.cli.Options();
        org.apache.commons.cli.DefaultParser.Builder builder3 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser4 = builder3.get();
        org.apache.commons.cli.Options options5 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray9 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties10 = null;
        org.apache.commons.cli.CommandLine commandLine12 = defaultParser4.parse(options5, strArray9, properties10, false);
        org.apache.commons.cli.DefaultParser.Builder builder13 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser14 = builder13.get();
        org.apache.commons.cli.Options options15 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray19 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties20 = null;
        org.apache.commons.cli.CommandLine commandLine22 = defaultParser14.parse(options15, strArray19, properties20, false);
        java.lang.Object obj24 = commandLine22.getOptionObject('4');
        org.apache.commons.cli.Option option25 = null;
        java.util.Properties properties26 = commandLine22.getOptionProperties(option25);
        org.apache.commons.cli.CommandLine commandLine28 = defaultParser1.parse(options2, strArray9, properties26, false);
        java.lang.String[] strArray30 = commandLine28.getOptionValues(" ");
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(defaultParser1);
        org.junit.Assert.assertNotNull(builder3);
        org.junit.Assert.assertNotNull(defaultParser4);
        org.junit.Assert.assertNotNull(strArray9);
        org.junit.Assert.assertArrayEquals(strArray9, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine12);
        org.junit.Assert.assertNotNull(builder13);
        org.junit.Assert.assertNotNull(defaultParser14);
        org.junit.Assert.assertNotNull(strArray19);
        org.junit.Assert.assertArrayEquals(strArray19, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine22);
        org.junit.Assert.assertNull(obj24);
        org.junit.Assert.assertNotNull(properties26);
        org.junit.Assert.assertNotNull(commandLine28);
        org.junit.Assert.assertNull(strArray30);
    }

    @Test
    public void test157() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test157");
        org.apache.commons.cli.help.OptionFormatter.Builder builder0 = org.apache.commons.cli.help.OptionFormatter.builder();
        org.apache.commons.cli.help.OptionFormatter.Builder builder2 = builder0.setDefaultArgName("");
        org.apache.commons.cli.help.OptionFormatter.Builder builder4 = builder0.setOptArgSeparator("arg");
        org.apache.commons.cli.help.OptionFormatter.Builder builder7 = builder0.setOptionalDelimiters("arg", "[ Options: [ short {} ] [ long {} ]");
        org.apache.commons.cli.help.OptionFormatter.Builder builder9 = builder7.setOptPrefix("[ Options: [ short {} ] [ long {} ]");
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(builder2);
        org.junit.Assert.assertNotNull(builder4);
        org.junit.Assert.assertNotNull(builder7);
        org.junit.Assert.assertNotNull(builder9);
    }

    @Test
    public void test158() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test158");
        org.apache.commons.cli.help.OptionFormatter.Builder builder0 = org.apache.commons.cli.help.OptionFormatter.builder();
        org.apache.commons.cli.help.OptionFormatter.Builder builder2 = builder0.setDefaultArgName("");
        org.apache.commons.cli.help.OptionFormatter.Builder builder4 = builder0.setOptArgSeparator("arg");
        org.apache.commons.cli.help.OptionFormatter.Builder builder6 = builder0.setDefaultArgName("");
        org.apache.commons.cli.help.OptionFormatter.Builder builder8 = builder0.setDefaultArgName(" ");
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(builder2);
        org.junit.Assert.assertNotNull(builder4);
        org.junit.Assert.assertNotNull(builder6);
        org.junit.Assert.assertNotNull(builder8);
    }

    @Test
    public void test159() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test159");
        java.io.File file1 = org.apache.commons.cli.TypeHandler.createFile("");
        org.junit.Assert.assertNotNull(file1);
        org.junit.Assert.assertNull("file1.getParent() == null", file1.getParent());
        org.junit.Assert.assertEquals(file1.toString(), "");
    }

    @Test
    public void test160() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test160");
        org.apache.commons.cli.DefaultParser.Builder builder0 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser1 = builder0.get();
        org.apache.commons.cli.Options options2 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties7 = null;
        org.apache.commons.cli.CommandLine commandLine9 = defaultParser1.parse(options2, strArray6, properties7, false);
        java.lang.Object obj11 = commandLine9.getOptionObject('4');
        java.lang.Object obj13 = commandLine9.getOptionObject('4');
        org.apache.commons.cli.OptionGroup optionGroup14 = new org.apache.commons.cli.OptionGroup();
        boolean boolean15 = optionGroup14.isRequired();
        boolean boolean16 = commandLine9.hasOption(optionGroup14);
        org.apache.commons.cli.Option[] optionArray17 = commandLine9.getOptions();
        boolean boolean19 = commandLine9.hasOption("arg");
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(defaultParser1);
        org.junit.Assert.assertNotNull(strArray6);
        org.junit.Assert.assertArrayEquals(strArray6, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine9);
        org.junit.Assert.assertNull(obj11);
        org.junit.Assert.assertNull(obj13);
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertTrue("'" + boolean16 + "' != '" + false + "'", boolean16 == false);
        org.junit.Assert.assertNotNull(optionArray17);
        org.junit.Assert.assertArrayEquals(optionArray17, new org.apache.commons.cli.Option[] {});
        org.junit.Assert.assertTrue("'" + boolean19 + "' != '" + false + "'", boolean19 == false);
    }

    @Test
    public void test161() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test161");
        org.apache.commons.cli.GnuParser gnuParser0 = new org.apache.commons.cli.GnuParser();
        org.apache.commons.cli.DefaultParser.Builder builder1 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser2 = builder1.get();
        org.apache.commons.cli.Options options3 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray7 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties8 = null;
        org.apache.commons.cli.CommandLine commandLine10 = defaultParser2.parse(options3, strArray7, properties8, false);
        java.util.List<java.lang.String> strList12 = options3.getMatchingOptions("arg");
        java.lang.String[] strArray19 = new java.lang.String[] { "usage: ", "usage: ", "usage: ", "--", "", "usage: " };
        org.apache.commons.cli.CommandLine commandLine21 = gnuParser0.parse(options3, strArray19, true);
        org.apache.commons.cli.DefaultParser.Builder builder22 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser23 = builder22.get();
        org.apache.commons.cli.Options options24 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray28 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties29 = null;
        org.apache.commons.cli.CommandLine commandLine31 = defaultParser23.parse(options24, strArray28, properties29, false);
        java.util.List<java.lang.String> strList33 = options24.getMatchingOptions("arg");
        java.lang.String[] strArray38 = new java.lang.String[] { "[--]", " | ", "arg", "-" };
        org.apache.commons.cli.DefaultParser.Builder builder39 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser40 = builder39.get();
        org.apache.commons.cli.Options options41 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray45 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties46 = null;
        org.apache.commons.cli.CommandLine commandLine48 = defaultParser40.parse(options41, strArray45, properties46, false);
        java.lang.Object obj50 = commandLine48.getOptionObject('4');
        java.util.Properties properties52 = commandLine48.getOptionProperties("--");
        org.apache.commons.cli.CommandLine commandLine54 = gnuParser0.parse(options24, strArray38, properties52, false);
        org.junit.Assert.assertNotNull(builder1);
        org.junit.Assert.assertNotNull(defaultParser2);
        org.junit.Assert.assertNotNull(strArray7);
        org.junit.Assert.assertArrayEquals(strArray7, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine10);
        org.junit.Assert.assertNotNull(strList12);
        org.junit.Assert.assertNotNull(strArray19);
        org.junit.Assert.assertArrayEquals(strArray19, new java.lang.String[] { "usage: ", "usage: ", "usage: ", "--", "", "usage: " });
        org.junit.Assert.assertNotNull(commandLine21);
        org.junit.Assert.assertNotNull(builder22);
        org.junit.Assert.assertNotNull(defaultParser23);
        org.junit.Assert.assertNotNull(strArray28);
        org.junit.Assert.assertArrayEquals(strArray28, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine31);
        org.junit.Assert.assertNotNull(strList33);
        org.junit.Assert.assertNotNull(strArray38);
        org.junit.Assert.assertArrayEquals(strArray38, new java.lang.String[] { "[--]", " | ", "arg", "-" });
        org.junit.Assert.assertNotNull(builder39);
        org.junit.Assert.assertNotNull(defaultParser40);
        org.junit.Assert.assertNotNull(strArray45);
        org.junit.Assert.assertArrayEquals(strArray45, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine48);
        org.junit.Assert.assertNull(obj50);
        org.junit.Assert.assertNotNull(properties52);
        org.junit.Assert.assertNotNull(commandLine54);
    }

    @Test
    public void test162() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test162");
        java.io.File file1 = org.apache.commons.cli.TypeHandler.createFile("--");
        org.junit.Assert.assertNotNull(file1);
        org.junit.Assert.assertNull("file1.getParent() == null", file1.getParent());
        org.junit.Assert.assertEquals(file1.toString(), "--");
    }

    @Test
    public void test163() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test163");
        org.apache.commons.cli.DefaultParser.Builder builder0 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser1 = builder0.get();
        org.apache.commons.cli.Options options2 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties7 = null;
        org.apache.commons.cli.CommandLine commandLine9 = defaultParser1.parse(options2, strArray6, properties7, false);
        java.lang.Object obj11 = commandLine9.getOptionObject('4');
        org.apache.commons.cli.OptionBuilder optionBuilder13 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs();
        org.apache.commons.cli.OptionBuilder optionBuilder15 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs((int) (byte) 1);
        org.apache.commons.cli.OptionBuilder optionBuilder17 = org.apache.commons.cli.OptionBuilder.withLongOpt("");
        org.apache.commons.cli.OptionBuilder optionBuilder19 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs((int) (byte) 1);
        org.apache.commons.cli.OptionBuilder optionBuilder20 = org.apache.commons.cli.OptionBuilder.hasArgs();
        org.apache.commons.cli.OptionBuilder optionBuilder21 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs();
        org.apache.commons.cli.OptionBuilder[] optionBuilderArray22 = new org.apache.commons.cli.OptionBuilder[] { optionBuilder13, optionBuilder15, optionBuilder17, optionBuilder19, optionBuilder20, optionBuilder21 };
        org.apache.commons.cli.OptionBuilder[] optionBuilderArray23 = commandLine9.getParsedOptionValues('#', optionBuilderArray22);
        java.util.function.Supplier<java.lang.String> strSupplier25 = null;
        java.lang.String str26 = commandLine9.getOptionValue('a', strSupplier25);
        org.apache.commons.cli.OptionGroup optionGroup27 = new org.apache.commons.cli.OptionGroup();
        java.util.Collection<org.apache.commons.cli.Option> optionCollection28 = optionGroup27.getOptions();
        boolean boolean29 = commandLine9.hasOption(optionGroup27);
        java.lang.String str30 = optionGroup27.toString();
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(defaultParser1);
        org.junit.Assert.assertNotNull(strArray6);
        org.junit.Assert.assertArrayEquals(strArray6, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine9);
        org.junit.Assert.assertNull(obj11);
        org.junit.Assert.assertNotNull(optionBuilder13);
        org.junit.Assert.assertNotNull(optionBuilder15);
        org.junit.Assert.assertNotNull(optionBuilder17);
        org.junit.Assert.assertNotNull(optionBuilder19);
        org.junit.Assert.assertNotNull(optionBuilder20);
        org.junit.Assert.assertNotNull(optionBuilder21);
        org.junit.Assert.assertNotNull(optionBuilderArray22);
        org.junit.Assert.assertNotNull(optionBuilderArray23);
        org.junit.Assert.assertNull(str26);
        org.junit.Assert.assertNotNull(optionCollection28);
        org.junit.Assert.assertTrue("'" + boolean29 + "' != '" + false + "'", boolean29 == false);
        org.junit.Assert.assertEquals("'" + str30 + "' != '" + "[]" + "'", str30, "[]");
    }

    @Test
    public void test164() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test164");
        java.lang.Appendable appendable0 = null;
        org.apache.commons.cli.help.TextHelpAppendable textHelpAppendable1 = new org.apache.commons.cli.help.TextHelpAppendable(appendable0);
        java.lang.CharSequence charSequence2 = null;
        textHelpAppendable1.appendParagraph(charSequence2);
        org.apache.commons.cli.help.TextHelpAppendable textHelpAppendable4 = new org.apache.commons.cli.help.TextHelpAppendable((java.lang.Appendable) textHelpAppendable1);
        textHelpAppendable1.appendHeader((int) (byte) 10, (java.lang.CharSequence) "");
        // The following exception was thrown during execution in test generation
        try {
            org.apache.commons.cli.help.FilterHelpAppendable filterHelpAppendable9 = textHelpAppendable1.append('a');
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test165() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test165");
        int int0 = org.apache.commons.cli.help.HelpFormatter.DEFAULT_WIDTH;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 74 + "'", int0 == 74);
    }

    @Test
    public void test166() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test166");
        org.apache.commons.cli.DefaultParser.Builder builder0 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser1 = builder0.get();
        org.apache.commons.cli.Options options2 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties7 = null;
        org.apache.commons.cli.CommandLine commandLine9 = defaultParser1.parse(options2, strArray6, properties7, false);
        org.apache.commons.cli.DefaultParser.Builder builder11 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser12 = builder11.get();
        org.apache.commons.cli.Options options13 = new org.apache.commons.cli.Options();
        org.apache.commons.cli.DefaultParser.Builder builder14 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser15 = builder14.get();
        org.apache.commons.cli.Options options16 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray20 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties21 = null;
        org.apache.commons.cli.CommandLine commandLine23 = defaultParser15.parse(options16, strArray20, properties21, false);
        org.apache.commons.cli.DefaultParser.Builder builder24 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser25 = builder24.get();
        org.apache.commons.cli.Options options26 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray30 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties31 = null;
        org.apache.commons.cli.CommandLine commandLine33 = defaultParser25.parse(options26, strArray30, properties31, false);
        java.lang.Object obj35 = commandLine33.getOptionObject('4');
        org.apache.commons.cli.Option option36 = null;
        java.util.Properties properties37 = commandLine33.getOptionProperties(option36);
        org.apache.commons.cli.CommandLine commandLine39 = defaultParser12.parse(options13, strArray20, properties37, false);
        org.apache.commons.cli.CommandLine.Builder builder41 = new org.apache.commons.cli.CommandLine.Builder();
        org.apache.commons.cli.CommandLine commandLine42 = commandLine39.getParsedOptionValue(' ', (java.util.function.Supplier<org.apache.commons.cli.CommandLine>) builder41);
        org.apache.commons.cli.Option option43 = null;
        org.apache.commons.cli.CommandLine.Builder builder44 = builder41.addOption(option43);
        org.apache.commons.cli.CommandLine commandLine45 = commandLine9.getParsedOptionValue('#', (java.util.function.Supplier<org.apache.commons.cli.CommandLine>) builder41);
        java.util.function.Supplier<java.lang.String> strSupplier47 = null;
        java.lang.String str48 = commandLine45.getOptionValue('4', strSupplier47);
        java.lang.String str51 = commandLine45.getOptionValue('#', "[ Options: [ short {} ] [ long {} ]");
        java.lang.String[] strArray53 = commandLine45.getOptionValues('4');
        org.apache.commons.cli.CommandLine.Builder builder55 = new org.apache.commons.cli.CommandLine.Builder();
        org.apache.commons.cli.CommandLine commandLine56 = builder55.build();
        org.apache.commons.cli.CommandLine commandLine57 = builder55.build();
        org.apache.commons.cli.CommandLine.Builder builder59 = builder55.addArg("[--]");
        org.apache.commons.cli.CommandLine commandLine60 = commandLine45.getParsedOptionValue("hi!", (java.util.function.Supplier<org.apache.commons.cli.CommandLine>) builder55);
        java.util.List<java.lang.String> strList61 = commandLine45.getArgList();
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(defaultParser1);
        org.junit.Assert.assertNotNull(strArray6);
        org.junit.Assert.assertArrayEquals(strArray6, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine9);
        org.junit.Assert.assertNotNull(builder11);
        org.junit.Assert.assertNotNull(defaultParser12);
        org.junit.Assert.assertNotNull(builder14);
        org.junit.Assert.assertNotNull(defaultParser15);
        org.junit.Assert.assertNotNull(strArray20);
        org.junit.Assert.assertArrayEquals(strArray20, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine23);
        org.junit.Assert.assertNotNull(builder24);
        org.junit.Assert.assertNotNull(defaultParser25);
        org.junit.Assert.assertNotNull(strArray30);
        org.junit.Assert.assertArrayEquals(strArray30, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine33);
        org.junit.Assert.assertNull(obj35);
        org.junit.Assert.assertNotNull(properties37);
        org.junit.Assert.assertNotNull(commandLine39);
        org.junit.Assert.assertNotNull(commandLine42);
        org.junit.Assert.assertNotNull(builder44);
        org.junit.Assert.assertNotNull(commandLine45);
        org.junit.Assert.assertNull(str48);
        org.junit.Assert.assertEquals("'" + str51 + "' != '" + "[ Options: [ short {} ] [ long {} ]" + "'", str51, "[ Options: [ short {} ] [ long {} ]");
        org.junit.Assert.assertNull(strArray53);
        org.junit.Assert.assertNotNull(commandLine56);
        org.junit.Assert.assertNotNull(commandLine57);
        org.junit.Assert.assertNotNull(builder59);
        org.junit.Assert.assertNotNull(commandLine60);
        org.junit.Assert.assertNotNull(strList61);
    }

    @Test
    public void test167() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test167");
        org.apache.commons.cli.DefaultParser.Builder builder0 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser1 = builder0.get();
        org.apache.commons.cli.Options options2 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties7 = null;
        org.apache.commons.cli.CommandLine commandLine9 = defaultParser1.parse(options2, strArray6, properties7, false);
        java.lang.Object obj11 = commandLine9.getOptionObject('4');
        org.apache.commons.cli.OptionBuilder optionBuilder13 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs();
        org.apache.commons.cli.OptionBuilder optionBuilder15 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs((int) (byte) 1);
        org.apache.commons.cli.OptionBuilder optionBuilder17 = org.apache.commons.cli.OptionBuilder.withLongOpt("");
        org.apache.commons.cli.OptionBuilder optionBuilder19 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs((int) (byte) 1);
        org.apache.commons.cli.OptionBuilder optionBuilder20 = org.apache.commons.cli.OptionBuilder.hasArgs();
        org.apache.commons.cli.OptionBuilder optionBuilder21 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs();
        org.apache.commons.cli.OptionBuilder[] optionBuilderArray22 = new org.apache.commons.cli.OptionBuilder[] { optionBuilder13, optionBuilder15, optionBuilder17, optionBuilder19, optionBuilder20, optionBuilder21 };
        org.apache.commons.cli.OptionBuilder[] optionBuilderArray23 = commandLine9.getParsedOptionValues('#', optionBuilderArray22);
        org.apache.commons.cli.OptionGroup optionGroup24 = new org.apache.commons.cli.OptionGroup();
        boolean boolean25 = optionGroup24.isRequired();
        java.lang.String str27 = commandLine9.getOptionValue(optionGroup24, "");
        org.apache.commons.cli.Option option28 = null;
        java.util.function.Supplier<java.lang.String> strSupplier29 = null;
        java.lang.String str30 = commandLine9.getOptionValue(option28, strSupplier29);
        org.apache.commons.cli.OptionGroup optionGroup31 = null;
        java.util.function.Supplier<java.lang.String> strSupplier32 = null;
        java.lang.String str33 = commandLine9.getOptionValue(optionGroup31, strSupplier32);
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(defaultParser1);
        org.junit.Assert.assertNotNull(strArray6);
        org.junit.Assert.assertArrayEquals(strArray6, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine9);
        org.junit.Assert.assertNull(obj11);
        org.junit.Assert.assertNotNull(optionBuilder13);
        org.junit.Assert.assertNotNull(optionBuilder15);
        org.junit.Assert.assertNotNull(optionBuilder17);
        org.junit.Assert.assertNotNull(optionBuilder19);
        org.junit.Assert.assertNotNull(optionBuilder20);
        org.junit.Assert.assertNotNull(optionBuilder21);
        org.junit.Assert.assertNotNull(optionBuilderArray22);
        org.junit.Assert.assertNotNull(optionBuilderArray23);
        org.junit.Assert.assertTrue("'" + boolean25 + "' != '" + false + "'", boolean25 == false);
        org.junit.Assert.assertEquals("'" + str27 + "' != '" + "" + "'", str27, "");
        org.junit.Assert.assertNull(str30);
        org.junit.Assert.assertNull(str33);
    }

    @Test
    public void test168() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test168");
        org.apache.commons.cli.OptionBuilder optionBuilder0 = org.apache.commons.cli.OptionBuilder.isRequired();
        org.junit.Assert.assertNotNull(optionBuilder0);
    }

    @Test
    public void test169() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test169");
        java.util.function.Function<org.apache.commons.cli.Option, java.lang.String> optionFunction0 = org.apache.commons.cli.help.OptionFormatter.COMPLEX_DEPRECATED_FORMAT;
        org.junit.Assert.assertNotNull(optionFunction0);
    }

    @Test
    public void test170() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test170");
        java.lang.Appendable appendable0 = null;
        org.apache.commons.cli.help.TextHelpAppendable textHelpAppendable1 = new org.apache.commons.cli.help.TextHelpAppendable(appendable0);
        java.lang.CharSequence charSequence2 = null;
        textHelpAppendable1.appendParagraph(charSequence2);
        textHelpAppendable1.setIndent(0);
        java.lang.Object[] objArray11 = new java.lang.Object[] { 10L, (short) 1, "", (short) 100 };
        textHelpAppendable1.appendParagraphFormat("", objArray11);
        org.apache.commons.cli.help.TextHelpAppendable textHelpAppendable13 = new org.apache.commons.cli.help.TextHelpAppendable((java.lang.Appendable) textHelpAppendable1);
        // The following exception was thrown during execution in test generation
        try {
            textHelpAppendable1.appendParagraph((java.lang.CharSequence) " ");
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(objArray11);
        org.junit.Assert.assertEquals(java.util.Arrays.deepToString(objArray11), "[10, 1, , 100]");
        org.junit.Assert.assertEquals(java.util.Arrays.toString(objArray11), "[10, 1, , 100]");
    }

    @Test
    public void test171() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test171");
        org.apache.commons.cli.DefaultParser.Builder builder0 = org.apache.commons.cli.DefaultParser.builder();
        java.util.function.Consumer<org.apache.commons.cli.Option> optionConsumer1 = null;
        org.apache.commons.cli.DefaultParser.Builder builder2 = builder0.setDeprecatedHandler(optionConsumer1);
        org.apache.commons.cli.DefaultParser defaultParser3 = builder0.get();
        java.util.function.Consumer<org.apache.commons.cli.Option> optionConsumer4 = null;
        org.apache.commons.cli.DefaultParser.Builder builder5 = builder0.setDeprecatedHandler(optionConsumer4);
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(builder2);
        org.junit.Assert.assertNotNull(defaultParser3);
        org.junit.Assert.assertNotNull(builder5);
    }

    @Test
    public void test172() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test172");
        org.apache.commons.cli.OptionBuilder optionBuilder0 = org.apache.commons.cli.OptionBuilder.hasArgs();
        java.lang.Class<?> wildcardClass1 = optionBuilder0.getClass();
        org.junit.Assert.assertNotNull(optionBuilder0);
        org.junit.Assert.assertNotNull(wildcardClass1);
    }

    @Test
    public void test173() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test173");
        org.apache.commons.cli.DefaultParser.Builder builder0 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser1 = builder0.get();
        org.apache.commons.cli.Options options2 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties7 = null;
        org.apache.commons.cli.CommandLine commandLine9 = defaultParser1.parse(options2, strArray6, properties7, false);
        org.apache.commons.cli.DefaultParser.Builder builder11 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser12 = builder11.get();
        org.apache.commons.cli.Options options13 = new org.apache.commons.cli.Options();
        org.apache.commons.cli.DefaultParser.Builder builder14 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser15 = builder14.get();
        org.apache.commons.cli.Options options16 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray20 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties21 = null;
        org.apache.commons.cli.CommandLine commandLine23 = defaultParser15.parse(options16, strArray20, properties21, false);
        org.apache.commons.cli.DefaultParser.Builder builder24 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser25 = builder24.get();
        org.apache.commons.cli.Options options26 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray30 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties31 = null;
        org.apache.commons.cli.CommandLine commandLine33 = defaultParser25.parse(options26, strArray30, properties31, false);
        java.lang.Object obj35 = commandLine33.getOptionObject('4');
        org.apache.commons.cli.Option option36 = null;
        java.util.Properties properties37 = commandLine33.getOptionProperties(option36);
        org.apache.commons.cli.CommandLine commandLine39 = defaultParser12.parse(options13, strArray20, properties37, false);
        org.apache.commons.cli.CommandLine.Builder builder41 = new org.apache.commons.cli.CommandLine.Builder();
        org.apache.commons.cli.CommandLine commandLine42 = commandLine39.getParsedOptionValue(' ', (java.util.function.Supplier<org.apache.commons.cli.CommandLine>) builder41);
        org.apache.commons.cli.Option option43 = null;
        org.apache.commons.cli.CommandLine.Builder builder44 = builder41.addOption(option43);
        org.apache.commons.cli.CommandLine commandLine45 = commandLine9.getParsedOptionValue('#', (java.util.function.Supplier<org.apache.commons.cli.CommandLine>) builder41);
        int int47 = commandLine9.getOptionCount("-");
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(defaultParser1);
        org.junit.Assert.assertNotNull(strArray6);
        org.junit.Assert.assertArrayEquals(strArray6, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine9);
        org.junit.Assert.assertNotNull(builder11);
        org.junit.Assert.assertNotNull(defaultParser12);
        org.junit.Assert.assertNotNull(builder14);
        org.junit.Assert.assertNotNull(defaultParser15);
        org.junit.Assert.assertNotNull(strArray20);
        org.junit.Assert.assertArrayEquals(strArray20, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine23);
        org.junit.Assert.assertNotNull(builder24);
        org.junit.Assert.assertNotNull(defaultParser25);
        org.junit.Assert.assertNotNull(strArray30);
        org.junit.Assert.assertArrayEquals(strArray30, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine33);
        org.junit.Assert.assertNull(obj35);
        org.junit.Assert.assertNotNull(properties37);
        org.junit.Assert.assertNotNull(commandLine39);
        org.junit.Assert.assertNotNull(commandLine42);
        org.junit.Assert.assertNotNull(builder44);
        org.junit.Assert.assertNotNull(commandLine45);
        org.junit.Assert.assertTrue("'" + int47 + "' != '" + 0 + "'", int47 == 0);
    }

    @Test
    public void test174() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test174");
        org.apache.commons.cli.DefaultParser defaultParser0 = new org.apache.commons.cli.DefaultParser();
    }

    @Test
    public void test175() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test175");
        org.apache.commons.cli.DefaultParser.Builder builder0 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser1 = builder0.get();
        org.apache.commons.cli.Options options2 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties7 = null;
        org.apache.commons.cli.CommandLine commandLine9 = defaultParser1.parse(options2, strArray6, properties7, false);
        java.lang.Object obj11 = commandLine9.getOptionObject('4');
        org.apache.commons.cli.OptionBuilder optionBuilder13 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs();
        org.apache.commons.cli.OptionBuilder optionBuilder15 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs((int) (byte) 1);
        org.apache.commons.cli.OptionBuilder optionBuilder17 = org.apache.commons.cli.OptionBuilder.withLongOpt("");
        org.apache.commons.cli.OptionBuilder optionBuilder19 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs((int) (byte) 1);
        org.apache.commons.cli.OptionBuilder optionBuilder20 = org.apache.commons.cli.OptionBuilder.hasArgs();
        org.apache.commons.cli.OptionBuilder optionBuilder21 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs();
        org.apache.commons.cli.OptionBuilder[] optionBuilderArray22 = new org.apache.commons.cli.OptionBuilder[] { optionBuilder13, optionBuilder15, optionBuilder17, optionBuilder19, optionBuilder20, optionBuilder21 };
        org.apache.commons.cli.OptionBuilder[] optionBuilderArray23 = commandLine9.getParsedOptionValues('#', optionBuilderArray22);
        java.util.function.Supplier<java.lang.String> strSupplier25 = null;
        java.lang.String str26 = commandLine9.getOptionValue('a', strSupplier25);
        org.apache.commons.cli.Option option27 = null;
        java.lang.Throwable throwable28 = null;
        org.apache.commons.cli.ParseException parseException29 = org.apache.commons.cli.ParseException.wrap(throwable28);
        java.lang.Throwable throwable30 = commandLine9.getParsedOptionValue(option27, (java.lang.Throwable) parseException29);
        java.lang.String[] strArray35 = new java.lang.String[] { "hi!", "arg" };
        java.util.ArrayList<java.lang.String> strList36 = new java.util.ArrayList<java.lang.String>();
        boolean boolean37 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList36, strArray35);
        org.apache.commons.cli.AmbiguousOptionException ambiguousOptionException38 = new org.apache.commons.cli.AmbiguousOptionException("hi!", (java.util.Collection<java.lang.String>) strList36);
        java.lang.String[] strArray42 = new java.lang.String[] { "hi!", "arg" };
        java.util.ArrayList<java.lang.String> strList43 = new java.util.ArrayList<java.lang.String>();
        boolean boolean44 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList43, strArray42);
        org.apache.commons.cli.AmbiguousOptionException ambiguousOptionException45 = new org.apache.commons.cli.AmbiguousOptionException("hi!", (java.util.Collection<java.lang.String>) strList43);
        java.lang.Exception[] exceptionArray46 = new java.lang.Exception[] { ambiguousOptionException38, ambiguousOptionException45 };
        java.lang.Exception[] exceptionArray47 = commandLine9.getParsedOptionValues("arg", exceptionArray46);
        boolean boolean49 = commandLine9.hasOption('a');
        org.apache.commons.cli.DefaultParser.Builder builder51 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser52 = builder51.get();
        org.apache.commons.cli.Options options53 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray57 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties58 = null;
        org.apache.commons.cli.CommandLine commandLine60 = defaultParser52.parse(options53, strArray57, properties58, false);
        java.lang.Object obj62 = commandLine60.getOptionObject('4');
        org.apache.commons.cli.OptionBuilder optionBuilder64 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs();
        org.apache.commons.cli.OptionBuilder optionBuilder66 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs((int) (byte) 1);
        org.apache.commons.cli.OptionBuilder optionBuilder68 = org.apache.commons.cli.OptionBuilder.withLongOpt("");
        org.apache.commons.cli.OptionBuilder optionBuilder70 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs((int) (byte) 1);
        org.apache.commons.cli.OptionBuilder optionBuilder71 = org.apache.commons.cli.OptionBuilder.hasArgs();
        org.apache.commons.cli.OptionBuilder optionBuilder72 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs();
        org.apache.commons.cli.OptionBuilder[] optionBuilderArray73 = new org.apache.commons.cli.OptionBuilder[] { optionBuilder64, optionBuilder66, optionBuilder68, optionBuilder70, optionBuilder71, optionBuilder72 };
        org.apache.commons.cli.OptionBuilder[] optionBuilderArray74 = commandLine60.getParsedOptionValues('#', optionBuilderArray73);
        org.apache.commons.cli.DefaultParser.Builder builder76 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser77 = builder76.get();
        org.apache.commons.cli.DefaultParser.Builder builder78 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser79 = builder78.get();
        org.apache.commons.cli.CommandLineParser[] commandLineParserArray80 = new org.apache.commons.cli.CommandLineParser[] { defaultParser77, defaultParser79 };
        org.apache.commons.cli.CommandLineParser[] commandLineParserArray81 = commandLine60.getParsedOptionValues('#', commandLineParserArray80);
        org.apache.commons.cli.Option option82 = null;
        org.apache.commons.cli.DefaultParser.Builder builder83 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser84 = builder83.get();
        org.apache.commons.cli.Options options85 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray89 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties90 = null;
        org.apache.commons.cli.CommandLine commandLine92 = defaultParser84.parse(options85, strArray89, properties90, false);
        java.lang.Object obj94 = commandLine92.getOptionObject('4');
        java.util.Properties properties96 = commandLine92.getOptionProperties("--");
        java.util.Hashtable<java.lang.Object, java.lang.Object> objMap97 = commandLine60.getParsedOptionValue(option82, (java.util.Hashtable<java.lang.Object, java.lang.Object>) properties96);
        java.util.Map<java.lang.Object, java.lang.Object> objMap98 = commandLine9.getParsedOptionValue(' ', (java.util.Map<java.lang.Object, java.lang.Object>) objMap97);
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(defaultParser1);
        org.junit.Assert.assertNotNull(strArray6);
        org.junit.Assert.assertArrayEquals(strArray6, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine9);
        org.junit.Assert.assertNull(obj11);
        org.junit.Assert.assertNotNull(optionBuilder13);
        org.junit.Assert.assertNotNull(optionBuilder15);
        org.junit.Assert.assertNotNull(optionBuilder17);
        org.junit.Assert.assertNotNull(optionBuilder19);
        org.junit.Assert.assertNotNull(optionBuilder20);
        org.junit.Assert.assertNotNull(optionBuilder21);
        org.junit.Assert.assertNotNull(optionBuilderArray22);
        org.junit.Assert.assertNotNull(optionBuilderArray23);
        org.junit.Assert.assertNull(str26);
        org.junit.Assert.assertNotNull(parseException29);
        org.junit.Assert.assertNotNull(throwable30);
        org.junit.Assert.assertNull("throwable30.getLocalizedMessage() == null", throwable30.getLocalizedMessage());
        org.junit.Assert.assertNull("throwable30.getMessage() == null", throwable30.getMessage());
        org.junit.Assert.assertEquals(throwable30.toString(), "org.apache.commons.cli.ParseException");
        org.junit.Assert.assertNotNull(strArray35);
        org.junit.Assert.assertArrayEquals(strArray35, new java.lang.String[] { "hi!", "arg" });
        org.junit.Assert.assertTrue("'" + boolean37 + "' != '" + true + "'", boolean37 == true);
        org.junit.Assert.assertNotNull(strArray42);
        org.junit.Assert.assertArrayEquals(strArray42, new java.lang.String[] { "hi!", "arg" });
        org.junit.Assert.assertTrue("'" + boolean44 + "' != '" + true + "'", boolean44 == true);
        org.junit.Assert.assertNotNull(exceptionArray46);
        org.junit.Assert.assertNotNull(exceptionArray47);
        org.junit.Assert.assertTrue("'" + boolean49 + "' != '" + false + "'", boolean49 == false);
        org.junit.Assert.assertNotNull(builder51);
        org.junit.Assert.assertNotNull(defaultParser52);
        org.junit.Assert.assertNotNull(strArray57);
        org.junit.Assert.assertArrayEquals(strArray57, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine60);
        org.junit.Assert.assertNull(obj62);
        org.junit.Assert.assertNotNull(optionBuilder64);
        org.junit.Assert.assertNotNull(optionBuilder66);
        org.junit.Assert.assertNotNull(optionBuilder68);
        org.junit.Assert.assertNotNull(optionBuilder70);
        org.junit.Assert.assertNotNull(optionBuilder71);
        org.junit.Assert.assertNotNull(optionBuilder72);
        org.junit.Assert.assertNotNull(optionBuilderArray73);
        org.junit.Assert.assertNotNull(optionBuilderArray74);
        org.junit.Assert.assertNotNull(builder76);
        org.junit.Assert.assertNotNull(defaultParser77);
        org.junit.Assert.assertNotNull(builder78);
        org.junit.Assert.assertNotNull(defaultParser79);
        org.junit.Assert.assertNotNull(commandLineParserArray80);
        org.junit.Assert.assertNotNull(commandLineParserArray81);
        org.junit.Assert.assertNotNull(builder83);
        org.junit.Assert.assertNotNull(defaultParser84);
        org.junit.Assert.assertNotNull(strArray89);
        org.junit.Assert.assertArrayEquals(strArray89, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine92);
        org.junit.Assert.assertNull(obj94);
        org.junit.Assert.assertNotNull(properties96);
        org.junit.Assert.assertNotNull(objMap97);
        org.junit.Assert.assertNotNull(objMap98);
    }

    @Test
    public void test176() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test176");
        org.apache.commons.cli.Option.Builder builder0 = org.apache.commons.cli.Option.builder();
        org.apache.commons.cli.Option.Builder builder1 = builder0.deprecated();
        org.apache.commons.cli.Option.Builder builder3 = builder0.optionalArg(false);
        org.apache.commons.cli.Option.Builder builder5 = builder3.required(true);
        // The following exception was thrown during execution in test generation
        try {
            org.apache.commons.cli.Option.Builder builder7 = builder3.option("");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Empty option name.");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(builder1);
        org.junit.Assert.assertNotNull(builder3);
        org.junit.Assert.assertNotNull(builder5);
    }

    @Test
    public void test177() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test177");
        org.apache.commons.cli.DefaultParser.Builder builder0 = org.apache.commons.cli.DefaultParser.builder();
        java.util.function.Consumer<org.apache.commons.cli.Option> optionConsumer1 = null;
        org.apache.commons.cli.DefaultParser.Builder builder2 = builder0.setDeprecatedHandler(optionConsumer1);
        org.apache.commons.cli.DefaultParser defaultParser3 = builder0.get();
        org.apache.commons.cli.DefaultParser.Builder builder5 = builder0.setAllowPartialMatching(false);
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(builder2);
        org.junit.Assert.assertNotNull(defaultParser3);
        org.junit.Assert.assertNotNull(builder5);
    }

    @Test
    public void test178() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test178");
        org.apache.commons.cli.HelpFormatter.Builder builder0 = new org.apache.commons.cli.HelpFormatter.Builder();
        org.apache.commons.cli.HelpFormatter.Builder builder2 = builder0.setShowSince(false);
        java.io.PrintWriter printWriter3 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.apache.commons.cli.HelpFormatter.Builder builder4 = builder0.setPrintWriter(printWriter3);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: printWriter");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(builder2);
    }

    @Test
    public void test179() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test179");
        java.lang.Object obj1 = org.apache.commons.cli.PatternOptionBuilder.getValueClass(' ');
        org.junit.Assert.assertNull(obj1);
    }

    @Test
    public void test180() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test180");
        // The following exception was thrown during execution in test generation
        try {
            org.apache.commons.cli.Option option2 = new org.apache.commons.cli.Option("-", " | ");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Illegal option name '-'.");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test181() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test181");
        java.lang.Appendable appendable0 = null;
        org.apache.commons.cli.help.TextHelpAppendable textHelpAppendable1 = new org.apache.commons.cli.help.TextHelpAppendable(appendable0);
        java.lang.CharSequence charSequence2 = null;
        textHelpAppendable1.appendParagraph(charSequence2);
        textHelpAppendable1.setIndent(0);
        java.lang.Object[] objArray11 = new java.lang.Object[] { 10L, (short) 1, "", (short) 100 };
        textHelpAppendable1.appendParagraphFormat("", objArray11);
        org.apache.commons.cli.help.TextHelpAppendable textHelpAppendable13 = new org.apache.commons.cli.help.TextHelpAppendable((java.lang.Appendable) textHelpAppendable1);
        org.apache.commons.cli.help.TextHelpAppendable textHelpAppendable14 = new org.apache.commons.cli.help.TextHelpAppendable((java.lang.Appendable) textHelpAppendable13);
        // The following exception was thrown during execution in test generation
        try {
            textHelpAppendable13.appendParagraph((java.lang.CharSequence) "[ Options: [ short {} ] [ long {} ]");
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(objArray11);
        org.junit.Assert.assertEquals(java.util.Arrays.deepToString(objArray11), "[10, 1, , 100]");
        org.junit.Assert.assertEquals(java.util.Arrays.toString(objArray11), "[10, 1, , 100]");
    }

    @Test
    public void test182() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test182");
        org.apache.commons.cli.help.OptionFormatter.Builder builder0 = org.apache.commons.cli.help.OptionFormatter.builder();
        org.apache.commons.cli.help.OptionFormatter.Builder builder2 = builder0.setDefaultArgName("");
        org.apache.commons.cli.help.OptionFormatter.Builder builder4 = builder0.setOptArgSeparator(" | ");
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(builder2);
        org.junit.Assert.assertNotNull(builder4);
    }

    @Test
    public void test183() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test183");
        org.apache.commons.cli.DefaultParser.Builder builder0 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser1 = builder0.get();
        org.apache.commons.cli.Options options2 = new org.apache.commons.cli.Options();
        org.apache.commons.cli.DefaultParser.Builder builder3 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser4 = builder3.get();
        org.apache.commons.cli.Options options5 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray9 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties10 = null;
        org.apache.commons.cli.CommandLine commandLine12 = defaultParser4.parse(options5, strArray9, properties10, false);
        org.apache.commons.cli.DefaultParser.Builder builder13 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser14 = builder13.get();
        org.apache.commons.cli.Options options15 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray19 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties20 = null;
        org.apache.commons.cli.CommandLine commandLine22 = defaultParser14.parse(options15, strArray19, properties20, false);
        java.lang.Object obj24 = commandLine22.getOptionObject('4');
        org.apache.commons.cli.Option option25 = null;
        java.util.Properties properties26 = commandLine22.getOptionProperties(option25);
        org.apache.commons.cli.CommandLine commandLine28 = defaultParser1.parse(options2, strArray9, properties26, false);
        org.apache.commons.cli.CommandLine.Builder builder30 = new org.apache.commons.cli.CommandLine.Builder();
        org.apache.commons.cli.CommandLine commandLine31 = commandLine28.getParsedOptionValue(' ', (java.util.function.Supplier<org.apache.commons.cli.CommandLine>) builder30);
        java.util.function.Consumer<org.apache.commons.cli.Option> optionConsumer32 = null;
        org.apache.commons.cli.CommandLine.Builder builder33 = builder30.setDeprecatedHandler(optionConsumer32);
        org.apache.commons.cli.CommandLine commandLine34 = builder30.get();
        java.lang.Object obj36 = commandLine34.getOptionObject(" | ");
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(defaultParser1);
        org.junit.Assert.assertNotNull(builder3);
        org.junit.Assert.assertNotNull(defaultParser4);
        org.junit.Assert.assertNotNull(strArray9);
        org.junit.Assert.assertArrayEquals(strArray9, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine12);
        org.junit.Assert.assertNotNull(builder13);
        org.junit.Assert.assertNotNull(defaultParser14);
        org.junit.Assert.assertNotNull(strArray19);
        org.junit.Assert.assertArrayEquals(strArray19, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine22);
        org.junit.Assert.assertNull(obj24);
        org.junit.Assert.assertNotNull(properties26);
        org.junit.Assert.assertNotNull(commandLine28);
        org.junit.Assert.assertNotNull(commandLine31);
        org.junit.Assert.assertNotNull(builder33);
        org.junit.Assert.assertNotNull(commandLine34);
        org.junit.Assert.assertNull(obj36);
    }

    @Test
    public void test184() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test184");
        org.apache.commons.cli.Options options0 = new org.apache.commons.cli.Options();
        org.apache.commons.cli.OptionGroup optionGroup1 = new org.apache.commons.cli.OptionGroup();
        java.util.Collection<org.apache.commons.cli.Option> optionCollection2 = optionGroup1.getOptions();
        boolean boolean3 = optionGroup1.isSelected();
        org.apache.commons.cli.Options options4 = options0.addOptionGroup(optionGroup1);
        // The following exception was thrown during execution in test generation
        try {
            org.apache.commons.cli.Options options9 = options0.addRequiredOption("--", " ", true, "hi!");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Illegal option name '-'.");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(optionCollection2);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
        org.junit.Assert.assertNotNull(options4);
    }

    @Test
    public void test185() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test185");
        org.apache.commons.cli.DefaultParser.Builder builder0 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser1 = builder0.get();
        org.apache.commons.cli.Options options2 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties7 = null;
        org.apache.commons.cli.CommandLine commandLine9 = defaultParser1.parse(options2, strArray6, properties7, false);
        java.lang.Object obj11 = commandLine9.getOptionObject('4');
        org.apache.commons.cli.OptionBuilder optionBuilder13 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs();
        org.apache.commons.cli.OptionBuilder optionBuilder15 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs((int) (byte) 1);
        org.apache.commons.cli.OptionBuilder optionBuilder17 = org.apache.commons.cli.OptionBuilder.withLongOpt("");
        org.apache.commons.cli.OptionBuilder optionBuilder19 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs((int) (byte) 1);
        org.apache.commons.cli.OptionBuilder optionBuilder20 = org.apache.commons.cli.OptionBuilder.hasArgs();
        org.apache.commons.cli.OptionBuilder optionBuilder21 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs();
        org.apache.commons.cli.OptionBuilder[] optionBuilderArray22 = new org.apache.commons.cli.OptionBuilder[] { optionBuilder13, optionBuilder15, optionBuilder17, optionBuilder19, optionBuilder20, optionBuilder21 };
        org.apache.commons.cli.OptionBuilder[] optionBuilderArray23 = commandLine9.getParsedOptionValues('#', optionBuilderArray22);
        java.util.function.Supplier<java.lang.String> strSupplier25 = null;
        java.lang.String str26 = commandLine9.getOptionValue('a', strSupplier25);
        org.apache.commons.cli.Option option27 = null;
        java.lang.Throwable throwable28 = null;
        org.apache.commons.cli.ParseException parseException29 = org.apache.commons.cli.ParseException.wrap(throwable28);
        java.lang.Throwable throwable30 = commandLine9.getParsedOptionValue(option27, (java.lang.Throwable) parseException29);
        java.lang.String[] strArray35 = new java.lang.String[] { "hi!", "arg" };
        java.util.ArrayList<java.lang.String> strList36 = new java.util.ArrayList<java.lang.String>();
        boolean boolean37 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList36, strArray35);
        org.apache.commons.cli.AmbiguousOptionException ambiguousOptionException38 = new org.apache.commons.cli.AmbiguousOptionException("hi!", (java.util.Collection<java.lang.String>) strList36);
        java.lang.String[] strArray42 = new java.lang.String[] { "hi!", "arg" };
        java.util.ArrayList<java.lang.String> strList43 = new java.util.ArrayList<java.lang.String>();
        boolean boolean44 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList43, strArray42);
        org.apache.commons.cli.AmbiguousOptionException ambiguousOptionException45 = new org.apache.commons.cli.AmbiguousOptionException("hi!", (java.util.Collection<java.lang.String>) strList43);
        java.lang.Exception[] exceptionArray46 = new java.lang.Exception[] { ambiguousOptionException38, ambiguousOptionException45 };
        java.lang.Exception[] exceptionArray47 = commandLine9.getParsedOptionValues("arg", exceptionArray46);
        boolean boolean49 = commandLine9.hasOption('a');
        org.apache.commons.cli.OptionBuilder optionBuilder51 = commandLine9.getParsedOptionValue('#');
        java.util.List<java.lang.String> strList52 = commandLine9.getArgList();
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(defaultParser1);
        org.junit.Assert.assertNotNull(strArray6);
        org.junit.Assert.assertArrayEquals(strArray6, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine9);
        org.junit.Assert.assertNull(obj11);
        org.junit.Assert.assertNotNull(optionBuilder13);
        org.junit.Assert.assertNotNull(optionBuilder15);
        org.junit.Assert.assertNotNull(optionBuilder17);
        org.junit.Assert.assertNotNull(optionBuilder19);
        org.junit.Assert.assertNotNull(optionBuilder20);
        org.junit.Assert.assertNotNull(optionBuilder21);
        org.junit.Assert.assertNotNull(optionBuilderArray22);
        org.junit.Assert.assertNotNull(optionBuilderArray23);
        org.junit.Assert.assertNull(str26);
        org.junit.Assert.assertNotNull(parseException29);
        org.junit.Assert.assertNotNull(throwable30);
        org.junit.Assert.assertNull("throwable30.getLocalizedMessage() == null", throwable30.getLocalizedMessage());
        org.junit.Assert.assertNull("throwable30.getMessage() == null", throwable30.getMessage());
        org.junit.Assert.assertEquals(throwable30.toString(), "org.apache.commons.cli.ParseException");
        org.junit.Assert.assertNotNull(strArray35);
        org.junit.Assert.assertArrayEquals(strArray35, new java.lang.String[] { "hi!", "arg" });
        org.junit.Assert.assertTrue("'" + boolean37 + "' != '" + true + "'", boolean37 == true);
        org.junit.Assert.assertNotNull(strArray42);
        org.junit.Assert.assertArrayEquals(strArray42, new java.lang.String[] { "hi!", "arg" });
        org.junit.Assert.assertTrue("'" + boolean44 + "' != '" + true + "'", boolean44 == true);
        org.junit.Assert.assertNotNull(exceptionArray46);
        org.junit.Assert.assertNotNull(exceptionArray47);
        org.junit.Assert.assertTrue("'" + boolean49 + "' != '" + false + "'", boolean49 == false);
        org.junit.Assert.assertNull(optionBuilder51);
        org.junit.Assert.assertNotNull(strList52);
    }

    @Test
    public void test186() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test186");
        org.apache.commons.cli.DefaultParser.Builder builder0 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser1 = builder0.get();
        org.apache.commons.cli.Options options2 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties7 = null;
        org.apache.commons.cli.CommandLine commandLine9 = defaultParser1.parse(options2, strArray6, properties7, false);
        java.lang.Object obj11 = commandLine9.getOptionObject('4');
        org.apache.commons.cli.OptionBuilder optionBuilder13 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs();
        org.apache.commons.cli.OptionBuilder optionBuilder15 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs((int) (byte) 1);
        org.apache.commons.cli.OptionBuilder optionBuilder17 = org.apache.commons.cli.OptionBuilder.withLongOpt("");
        org.apache.commons.cli.OptionBuilder optionBuilder19 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs((int) (byte) 1);
        org.apache.commons.cli.OptionBuilder optionBuilder20 = org.apache.commons.cli.OptionBuilder.hasArgs();
        org.apache.commons.cli.OptionBuilder optionBuilder21 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs();
        org.apache.commons.cli.OptionBuilder[] optionBuilderArray22 = new org.apache.commons.cli.OptionBuilder[] { optionBuilder13, optionBuilder15, optionBuilder17, optionBuilder19, optionBuilder20, optionBuilder21 };
        org.apache.commons.cli.OptionBuilder[] optionBuilderArray23 = commandLine9.getParsedOptionValues('#', optionBuilderArray22);
        java.util.ArrayList<java.lang.String>[] strListArray25 = commandLine9.getParsedOptionValues("[--]");
        org.apache.commons.cli.Option[] optionArray26 = commandLine9.getOptions();
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(defaultParser1);
        org.junit.Assert.assertNotNull(strArray6);
        org.junit.Assert.assertArrayEquals(strArray6, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine9);
        org.junit.Assert.assertNull(obj11);
        org.junit.Assert.assertNotNull(optionBuilder13);
        org.junit.Assert.assertNotNull(optionBuilder15);
        org.junit.Assert.assertNotNull(optionBuilder17);
        org.junit.Assert.assertNotNull(optionBuilder19);
        org.junit.Assert.assertNotNull(optionBuilder20);
        org.junit.Assert.assertNotNull(optionBuilder21);
        org.junit.Assert.assertNotNull(optionBuilderArray22);
        org.junit.Assert.assertNotNull(optionBuilderArray23);
        org.junit.Assert.assertNull(strListArray25);
        org.junit.Assert.assertNotNull(optionArray26);
        org.junit.Assert.assertArrayEquals(optionArray26, new org.apache.commons.cli.Option[] {});
    }

    @Test
    public void test187() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test187");
        org.apache.commons.cli.Option option0 = null;
        org.apache.commons.cli.help.OptionFormatter optionFormatter1 = org.apache.commons.cli.help.OptionFormatter.from(option0);
        java.lang.String str3 = optionFormatter1.toOptional("--");
        org.apache.commons.cli.help.OptionFormatter.Builder builder4 = new org.apache.commons.cli.help.OptionFormatter.Builder(optionFormatter1);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str5 = optionFormatter1.getLongOpt();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(optionFormatter1);
        org.junit.Assert.assertEquals("'" + str3 + "' != '" + "[--]" + "'", str3, "[--]");
    }

    @Test
    public void test188() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test188");
        org.apache.commons.cli.OptionBuilder optionBuilder1 = org.apache.commons.cli.OptionBuilder.withValueSeparator('a');
        org.junit.Assert.assertNotNull(optionBuilder1);
    }

    @Test
    public void test189() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test189");
        org.apache.commons.cli.Option option0 = null;
        org.apache.commons.cli.help.OptionFormatter optionFormatter1 = org.apache.commons.cli.help.OptionFormatter.from(option0);
        java.lang.String str3 = optionFormatter1.toOptional("--");
        org.apache.commons.cli.help.OptionFormatter.Builder builder4 = new org.apache.commons.cli.help.OptionFormatter.Builder(optionFormatter1);
        java.lang.String str6 = optionFormatter1.toOptional("--");
        org.apache.commons.cli.help.OptionFormatter.Builder builder7 = new org.apache.commons.cli.help.OptionFormatter.Builder(optionFormatter1);
        org.apache.commons.cli.help.OptionFormatter.Builder builder8 = new org.apache.commons.cli.help.OptionFormatter.Builder(optionFormatter1);
        org.junit.Assert.assertNotNull(optionFormatter1);
        org.junit.Assert.assertEquals("'" + str3 + "' != '" + "[--]" + "'", str3, "[--]");
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "[--]" + "'", str6, "[--]");
    }

    @Test
    public void test190() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test190");
        org.apache.commons.cli.DefaultParser.Builder builder0 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser1 = builder0.get();
        org.apache.commons.cli.Options options2 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties7 = null;
        org.apache.commons.cli.CommandLine commandLine9 = defaultParser1.parse(options2, strArray6, properties7, false);
        java.lang.Object obj11 = commandLine9.getOptionObject('4');
        java.lang.Object obj13 = commandLine9.getOptionObject('4');
        org.apache.commons.cli.OptionGroup optionGroup14 = new org.apache.commons.cli.OptionGroup();
        boolean boolean15 = optionGroup14.isRequired();
        boolean boolean16 = commandLine9.hasOption(optionGroup14);
        boolean boolean17 = optionGroup14.isRequired();
        boolean boolean18 = optionGroup14.isSelected();
        boolean boolean19 = optionGroup14.isRequired();
        boolean boolean20 = optionGroup14.isRequired();
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(defaultParser1);
        org.junit.Assert.assertNotNull(strArray6);
        org.junit.Assert.assertArrayEquals(strArray6, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine9);
        org.junit.Assert.assertNull(obj11);
        org.junit.Assert.assertNull(obj13);
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertTrue("'" + boolean16 + "' != '" + false + "'", boolean16 == false);
        org.junit.Assert.assertTrue("'" + boolean17 + "' != '" + false + "'", boolean17 == false);
        org.junit.Assert.assertTrue("'" + boolean18 + "' != '" + false + "'", boolean18 == false);
        org.junit.Assert.assertTrue("'" + boolean19 + "' != '" + false + "'", boolean19 == false);
        org.junit.Assert.assertTrue("'" + boolean20 + "' != '" + false + "'", boolean20 == false);
    }

    @Test
    public void test191() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test191");
        org.apache.commons.cli.OptionBuilder optionBuilder1 = org.apache.commons.cli.OptionBuilder.withLongOpt("-");
        org.junit.Assert.assertNotNull(optionBuilder1);
    }

    @Test
    public void test192() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test192");
        org.apache.commons.cli.Option.Builder builder0 = org.apache.commons.cli.Option.builder();
        org.apache.commons.cli.Option.Builder builder2 = builder0.hasArg(true);
        org.apache.commons.cli.Option.Builder builder4 = builder0.valueSeparator('4');
        org.apache.commons.cli.Option.Builder builder6 = builder0.optionalArg(true);
        org.apache.commons.cli.Option.Builder builder8 = builder6.argName("usage: ");
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(builder2);
        org.junit.Assert.assertNotNull(builder4);
        org.junit.Assert.assertNotNull(builder6);
        org.junit.Assert.assertNotNull(builder8);
    }

    @Test
    public void test193() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test193");
        org.apache.commons.cli.Option.Builder builder0 = org.apache.commons.cli.Option.builder();
        org.apache.commons.cli.Option.Builder builder1 = builder0.deprecated();
        org.apache.commons.cli.Option.Builder builder3 = builder0.numberOfArgs((int) (short) -1);
        java.lang.Class<java.net.URL> uRLClass4 = org.apache.commons.cli.PatternOptionBuilder.URL_VALUE;
        org.apache.commons.cli.Option.Builder builder5 = builder0.type(uRLClass4);
        org.apache.commons.cli.Option.Builder builder7 = builder5.argName("[--]");
        org.apache.commons.cli.Option.Builder builder9 = builder7.required(false);
        java.lang.Class<java.net.URL> uRLClass10 = org.apache.commons.cli.PatternOptionBuilder.URL_VALUE;
        org.apache.commons.cli.OptionBuilder optionBuilder11 = org.apache.commons.cli.OptionBuilder.withType(uRLClass10);
        org.apache.commons.cli.Option.Builder builder12 = builder9.type(uRLClass10);
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(builder1);
        org.junit.Assert.assertNotNull(builder3);
        org.junit.Assert.assertNotNull(uRLClass4);
        org.junit.Assert.assertNotNull(builder5);
        org.junit.Assert.assertNotNull(builder7);
        org.junit.Assert.assertNotNull(builder9);
        org.junit.Assert.assertNotNull(uRLClass10);
        org.junit.Assert.assertNotNull(optionBuilder11);
        org.junit.Assert.assertNotNull(builder12);
    }

    @Test
    public void test194() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test194");
        // The following exception was thrown during execution in test generation
        try {
            java.util.Date date1 = org.apache.commons.cli.TypeHandler.createDate("[ Options: [ short {} ] [ long {} ]");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: org.apache.commons.cli.ParseException: java.text.ParseException: Unparseable date: \"[ Options: [ short {} ] [ long {} ]\"");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test195() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test195");
        org.apache.commons.cli.HelpFormatter.Builder builder0 = new org.apache.commons.cli.HelpFormatter.Builder();
        org.apache.commons.cli.HelpFormatter.Builder builder2 = builder0.setShowSince(false);
        org.apache.commons.cli.HelpFormatter helpFormatter3 = builder0.get();
        java.lang.String str4 = helpFormatter3.getSyntaxPrefix();
        java.io.PrintWriter printWriter5 = null;
        org.apache.commons.cli.Options options9 = new org.apache.commons.cli.Options();
        java.util.List<?> wildcardList10 = options9.getRequiredOptions();
        // The following exception was thrown during execution in test generation
        try {
            helpFormatter3.printHelp(printWriter5, 0, "--", "--", options9, (int) 'a', (int) 'a', "hi!");
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(builder2);
        org.junit.Assert.assertNotNull(helpFormatter3);
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "usage: " + "'", str4, "usage: ");
        org.junit.Assert.assertNotNull(wildcardList10);
    }

    @Test
    public void test196() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test196");
        org.apache.commons.cli.DefaultParser.Builder builder0 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser1 = builder0.get();
        org.apache.commons.cli.Options options2 = new org.apache.commons.cli.Options();
        org.apache.commons.cli.DefaultParser.Builder builder3 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser4 = builder3.get();
        org.apache.commons.cli.Options options5 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray9 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties10 = null;
        org.apache.commons.cli.CommandLine commandLine12 = defaultParser4.parse(options5, strArray9, properties10, false);
        org.apache.commons.cli.DefaultParser.Builder builder13 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser14 = builder13.get();
        org.apache.commons.cli.Options options15 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray19 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties20 = null;
        org.apache.commons.cli.CommandLine commandLine22 = defaultParser14.parse(options15, strArray19, properties20, false);
        java.lang.Object obj24 = commandLine22.getOptionObject('4');
        org.apache.commons.cli.Option option25 = null;
        java.util.Properties properties26 = commandLine22.getOptionProperties(option25);
        org.apache.commons.cli.CommandLine commandLine28 = defaultParser1.parse(options2, strArray9, properties26, false);
        org.apache.commons.cli.CommandLine.Builder builder30 = new org.apache.commons.cli.CommandLine.Builder();
        org.apache.commons.cli.CommandLine commandLine31 = commandLine28.getParsedOptionValue(' ', (java.util.function.Supplier<org.apache.commons.cli.CommandLine>) builder30);
        java.util.function.Consumer<org.apache.commons.cli.Option> optionConsumer32 = null;
        org.apache.commons.cli.CommandLine.Builder builder33 = builder30.setDeprecatedHandler(optionConsumer32);
        org.apache.commons.cli.CommandLine commandLine34 = builder30.get();
        org.apache.commons.cli.DefaultParser.Builder builder35 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser36 = builder35.get();
        org.apache.commons.cli.Options options37 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray41 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties42 = null;
        org.apache.commons.cli.CommandLine commandLine44 = defaultParser36.parse(options37, strArray41, properties42, false);
        java.lang.Object obj46 = commandLine44.getOptionObject('4');
        org.apache.commons.cli.OptionBuilder optionBuilder48 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs();
        org.apache.commons.cli.OptionBuilder optionBuilder50 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs((int) (byte) 1);
        org.apache.commons.cli.OptionBuilder optionBuilder52 = org.apache.commons.cli.OptionBuilder.withLongOpt("");
        org.apache.commons.cli.OptionBuilder optionBuilder54 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs((int) (byte) 1);
        org.apache.commons.cli.OptionBuilder optionBuilder55 = org.apache.commons.cli.OptionBuilder.hasArgs();
        org.apache.commons.cli.OptionBuilder optionBuilder56 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs();
        org.apache.commons.cli.OptionBuilder[] optionBuilderArray57 = new org.apache.commons.cli.OptionBuilder[] { optionBuilder48, optionBuilder50, optionBuilder52, optionBuilder54, optionBuilder55, optionBuilder56 };
        org.apache.commons.cli.OptionBuilder[] optionBuilderArray58 = commandLine44.getParsedOptionValues('#', optionBuilderArray57);
        org.apache.commons.cli.OptionGroup optionGroup59 = new org.apache.commons.cli.OptionGroup();
        boolean boolean60 = optionGroup59.isRequired();
        java.lang.String str62 = commandLine44.getOptionValue(optionGroup59, "");
        boolean boolean63 = optionGroup59.isRequired();
        java.lang.String[] strArray64 = commandLine34.getOptionValues(optionGroup59);
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(defaultParser1);
        org.junit.Assert.assertNotNull(builder3);
        org.junit.Assert.assertNotNull(defaultParser4);
        org.junit.Assert.assertNotNull(strArray9);
        org.junit.Assert.assertArrayEquals(strArray9, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine12);
        org.junit.Assert.assertNotNull(builder13);
        org.junit.Assert.assertNotNull(defaultParser14);
        org.junit.Assert.assertNotNull(strArray19);
        org.junit.Assert.assertArrayEquals(strArray19, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine22);
        org.junit.Assert.assertNull(obj24);
        org.junit.Assert.assertNotNull(properties26);
        org.junit.Assert.assertNotNull(commandLine28);
        org.junit.Assert.assertNotNull(commandLine31);
        org.junit.Assert.assertNotNull(builder33);
        org.junit.Assert.assertNotNull(commandLine34);
        org.junit.Assert.assertNotNull(builder35);
        org.junit.Assert.assertNotNull(defaultParser36);
        org.junit.Assert.assertNotNull(strArray41);
        org.junit.Assert.assertArrayEquals(strArray41, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine44);
        org.junit.Assert.assertNull(obj46);
        org.junit.Assert.assertNotNull(optionBuilder48);
        org.junit.Assert.assertNotNull(optionBuilder50);
        org.junit.Assert.assertNotNull(optionBuilder52);
        org.junit.Assert.assertNotNull(optionBuilder54);
        org.junit.Assert.assertNotNull(optionBuilder55);
        org.junit.Assert.assertNotNull(optionBuilder56);
        org.junit.Assert.assertNotNull(optionBuilderArray57);
        org.junit.Assert.assertNotNull(optionBuilderArray58);
        org.junit.Assert.assertTrue("'" + boolean60 + "' != '" + false + "'", boolean60 == false);
        org.junit.Assert.assertEquals("'" + str62 + "' != '" + "" + "'", str62, "");
        org.junit.Assert.assertTrue("'" + boolean63 + "' != '" + false + "'", boolean63 == false);
        org.junit.Assert.assertNull(strArray64);
    }

    @Test
    public void test197() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test197");
        org.apache.commons.cli.UnrecognizedOptionException unrecognizedOptionException2 = new org.apache.commons.cli.UnrecognizedOptionException("arg", "--");
        java.lang.String str3 = unrecognizedOptionException2.getOption();
        java.lang.Throwable[] throwableArray4 = unrecognizedOptionException2.getSuppressed();
        org.junit.Assert.assertEquals("'" + str3 + "' != '" + "--" + "'", str3, "--");
        org.junit.Assert.assertNotNull(throwableArray4);
        org.junit.Assert.assertArrayEquals(throwableArray4, new java.lang.Throwable[] {});
    }

    @Test
    public void test198() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test198");
        org.apache.commons.cli.Option option1 = org.apache.commons.cli.OptionBuilder.create("arg");
        java.lang.String str3 = option1.getValue("[--]");
        java.lang.String str5 = option1.getValue("Deprecated");
        org.junit.Assert.assertNotNull(option1);
        org.junit.Assert.assertEquals("'" + str3 + "' != '" + "[--]" + "'", str3, "[--]");
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "Deprecated" + "'", str5, "Deprecated");
    }

    @Test
    public void test199() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test199");
        // The following exception was thrown during execution in test generation
        try {
            java.io.File[] fileArray1 = org.apache.commons.cli.TypeHandler.createFiles("[ Option arg [ARG...] :: null :: class java.lang.String ]");
            org.junit.Assert.fail("Expected exception of type java.lang.UnsupportedOperationException; message: Not yet implemented");
        } catch (java.lang.UnsupportedOperationException e) {
            // Expected exception.
        }
    }

    @Test
    public void test200() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test200");
        org.apache.commons.cli.help.OptionFormatter.Builder builder0 = org.apache.commons.cli.help.OptionFormatter.builder();
        org.apache.commons.cli.help.OptionFormatter.Builder builder2 = builder0.setDefaultArgName("");
        org.apache.commons.cli.help.OptionFormatter.Builder builder4 = builder0.setOptArgSeparator("arg");
        org.apache.commons.cli.help.OptionFormatter.Builder builder7 = builder0.setOptionalDelimiters("arg", "[ Options: [ short {} ] [ long {} ]");
        java.util.function.Function<org.apache.commons.cli.Option, java.lang.String> optionFunction8 = org.apache.commons.cli.help.OptionFormatter.SIMPLE_DEPRECATED_FORMAT;
        org.apache.commons.cli.help.OptionFormatter.Builder builder9 = builder0.setDeprecatedFormatFunction(optionFunction8);
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(builder2);
        org.junit.Assert.assertNotNull(builder4);
        org.junit.Assert.assertNotNull(builder7);
        org.junit.Assert.assertNotNull(optionFunction8);
        org.junit.Assert.assertNotNull(builder9);
    }

    @Test
    public void test201() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test201");
        org.apache.commons.cli.Option.Builder builder1 = org.apache.commons.cli.Option.builder();
        org.apache.commons.cli.Option.Builder builder3 = builder1.hasArg(true);
        org.apache.commons.cli.Option.Builder builder4 = org.apache.commons.cli.Option.builder();
        org.apache.commons.cli.Option.Builder builder5 = builder4.deprecated();
        org.apache.commons.cli.Option.Builder builder7 = builder4.numberOfArgs((int) (short) -1);
        java.lang.Class<java.net.URL> uRLClass8 = org.apache.commons.cli.PatternOptionBuilder.URL_VALUE;
        org.apache.commons.cli.Option.Builder builder9 = builder4.type(uRLClass8);
        org.apache.commons.cli.Option.Builder builder10 = builder1.type(uRLClass8);
        // The following exception was thrown during execution in test generation
        try {
            java.net.URL uRL11 = org.apache.commons.cli.TypeHandler.createValue("usage: ", uRLClass8);
            org.junit.Assert.fail("Expected exception of type org.apache.commons.cli.ParseException; message: java.net.MalformedURLException: unknown protocol: usage");
        } catch (org.apache.commons.cli.ParseException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(builder1);
        org.junit.Assert.assertNotNull(builder3);
        org.junit.Assert.assertNotNull(builder4);
        org.junit.Assert.assertNotNull(builder5);
        org.junit.Assert.assertNotNull(builder7);
        org.junit.Assert.assertNotNull(uRLClass8);
        org.junit.Assert.assertNotNull(builder9);
        org.junit.Assert.assertNotNull(builder10);
    }

    @Test
    public void test202() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test202");
        org.apache.commons.cli.Option option0 = null;
        org.apache.commons.cli.help.OptionFormatter optionFormatter1 = org.apache.commons.cli.help.OptionFormatter.from(option0);
        java.lang.String str3 = optionFormatter1.toOptional("--");
        org.apache.commons.cli.help.OptionFormatter.Builder builder4 = new org.apache.commons.cli.help.OptionFormatter.Builder(optionFormatter1);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str6 = optionFormatter1.toSyntaxOption(false);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(optionFormatter1);
        org.junit.Assert.assertEquals("'" + str3 + "' != '" + "[--]" + "'", str3, "[--]");
    }

    @Test
    public void test203() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test203");
        java.lang.Appendable appendable0 = null;
        org.apache.commons.cli.help.TextHelpAppendable textHelpAppendable1 = new org.apache.commons.cli.help.TextHelpAppendable(appendable0);
        java.lang.CharSequence charSequence2 = null;
        textHelpAppendable1.appendParagraph(charSequence2);
        textHelpAppendable1.setIndent(0);
        textHelpAppendable1.setLeftPad(100);
        textHelpAppendable1.printWrapped("");
    }

    @Test
    public void test204() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test204");
        int int0 = org.apache.commons.cli.HelpFormatter.DEFAULT_LEFT_PAD;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 1 + "'", int0 == 1);
    }

    @Test
    public void test205() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test205");
        java.lang.Appendable appendable0 = null;
        org.apache.commons.cli.help.TextHelpAppendable textHelpAppendable1 = new org.apache.commons.cli.help.TextHelpAppendable(appendable0);
        java.lang.CharSequence charSequence2 = null;
        textHelpAppendable1.appendParagraph(charSequence2);
        textHelpAppendable1.setIndent(0);
        textHelpAppendable1.setLeftPad(100);
        int int8 = textHelpAppendable1.getIndent();
        int int9 = textHelpAppendable1.getIndent();
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 0 + "'", int8 == 0);
        org.junit.Assert.assertTrue("'" + int9 + "' != '" + 0 + "'", int9 == 0);
    }

    @Test
    public void test206() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test206");
        org.apache.commons.cli.Option option1 = org.apache.commons.cli.OptionBuilder.create("arg");
        option1.setRequired(true);
        boolean boolean4 = option1.hasArgName();
        java.lang.String str5 = option1.toString();
        char char6 = option1.getValueSeparator();
        org.junit.Assert.assertNotNull(option1);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "[ Option arg :: null :: class java.lang.String ]" + "'", str5, "[ Option arg :: null :: class java.lang.String ]");
        org.junit.Assert.assertTrue("'" + char6 + "' != '" + '\000' + "'", char6 == '\000');
    }

    @Test
    public void test207() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test207");
        org.apache.commons.cli.HelpFormatter helpFormatter0 = new org.apache.commons.cli.HelpFormatter();
        org.apache.commons.cli.Options options2 = new org.apache.commons.cli.Options();
        org.apache.commons.cli.OptionGroup optionGroup3 = new org.apache.commons.cli.OptionGroup();
        java.util.Collection<org.apache.commons.cli.Option> optionCollection4 = optionGroup3.getOptions();
        boolean boolean5 = optionGroup3.isSelected();
        org.apache.commons.cli.Options options6 = options2.addOptionGroup(optionGroup3);
        helpFormatter0.printHelp("usage: ", options2);
        boolean boolean9 = options2.hasLongOption("usage: ");
        org.junit.Assert.assertNotNull(optionCollection4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertNotNull(options6);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
    }

    @Test
    public void test208() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test208");
        org.apache.commons.cli.Option option0 = null;
        org.apache.commons.cli.help.OptionFormatter optionFormatter1 = org.apache.commons.cli.help.OptionFormatter.from(option0);
        java.lang.String str3 = optionFormatter1.toOptional("--");
        org.apache.commons.cli.help.OptionFormatter.Builder builder4 = new org.apache.commons.cli.help.OptionFormatter.Builder(optionFormatter1);
        java.lang.String str6 = optionFormatter1.toOptional("--");
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str7 = optionFormatter1.getOpt();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(optionFormatter1);
        org.junit.Assert.assertEquals("'" + str3 + "' != '" + "[--]" + "'", str3, "[--]");
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "[--]" + "'", str6, "[--]");
    }

    @Test
    public void test209() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test209");
        java.lang.String str0 = org.apache.commons.cli.help.OptionFormatter.DEFAULT_SYNTAX_PREFIX;
        org.junit.Assert.assertEquals("'" + str0 + "' != '" + "usage: " + "'", str0, "usage: ");
    }

    @Test
    public void test210() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test210");
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj1 = org.apache.commons.cli.TypeHandler.createObject("usage: ");
            org.junit.Assert.fail("Expected exception of type org.apache.commons.cli.ParseException; message: java.lang.ClassNotFoundException: usage: ");
        } catch (org.apache.commons.cli.ParseException e) {
            // Expected exception.
        }
    }

    @Test
    public void test211() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test211");
        java.lang.String str0 = org.apache.commons.cli.help.OptionFormatter.DEFAULT_OPT_SEPARATOR;
        org.junit.Assert.assertEquals("'" + str0 + "' != '" + ", " + "'", str0, ", ");
    }

    @Test
    public void test212() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test212");
        // The following exception was thrown during execution in test generation
        try {
            org.apache.commons.cli.Option option0 = org.apache.commons.cli.OptionBuilder.create();
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: longopt missing");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
    }

    @Test
    public void test213() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test213");
        org.apache.commons.cli.DefaultParser defaultParser1 = new org.apache.commons.cli.DefaultParser(true);
    }

    @Test
    public void test214() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test214");
        java.lang.Appendable appendable0 = null;
        org.apache.commons.cli.help.TextHelpAppendable textHelpAppendable1 = new org.apache.commons.cli.help.TextHelpAppendable(appendable0);
        java.lang.CharSequence charSequence2 = null;
        textHelpAppendable1.appendParagraph(charSequence2);
        org.apache.commons.cli.help.TextHelpAppendable textHelpAppendable4 = new org.apache.commons.cli.help.TextHelpAppendable((java.lang.Appendable) textHelpAppendable1);
        int int5 = textHelpAppendable4.getIndent();
        // The following exception was thrown during execution in test generation
        try {
            org.apache.commons.cli.help.FilterHelpAppendable filterHelpAppendable7 = textHelpAppendable4.append('4');
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + int5 + "' != '" + 3 + "'", int5 == 3);
    }

    @Test
    public void test215() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test215");
        org.apache.commons.cli.Option option1 = org.apache.commons.cli.OptionBuilder.create("arg");
        option1.setRequired(true);
        boolean boolean4 = option1.hasArgName();
        boolean boolean5 = option1.hasValueSeparator();
        option1.setLongOpt(", ");
        org.junit.Assert.assertNotNull(option1);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
    }

    @Test
    public void test216() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test216");
        java.lang.Appendable appendable0 = null;
        org.apache.commons.cli.help.TextHelpAppendable textHelpAppendable1 = new org.apache.commons.cli.help.TextHelpAppendable(appendable0);
        java.lang.CharSequence charSequence2 = null;
        textHelpAppendable1.appendParagraph(charSequence2);
        org.apache.commons.cli.help.TextHelpAppendable textHelpAppendable4 = new org.apache.commons.cli.help.TextHelpAppendable((java.lang.Appendable) textHelpAppendable1);
        java.lang.CharSequence[] charSequenceArray20 = new java.lang.CharSequence[] { "Deprecated", "[]", "usage: ", "--", "-", "hi!", "usage: ", "usage: ", "[ Option arg :: null :: class java.lang.String ]", " ", "Deprecated", "[]", "-", "-" };
        java.util.ArrayList<java.lang.CharSequence> charSequenceList21 = new java.util.ArrayList<java.lang.CharSequence>();
        boolean boolean22 = java.util.Collections.addAll((java.util.Collection<java.lang.CharSequence>) charSequenceList21, charSequenceArray20);
        // The following exception was thrown during execution in test generation
        try {
            textHelpAppendable4.appendList(false, (java.util.Collection<java.lang.CharSequence>) charSequenceList21);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(charSequenceArray20);
        org.junit.Assert.assertTrue("'" + boolean22 + "' != '" + true + "'", boolean22 == true);
    }

    @Test
    public void test217() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test217");
        org.apache.commons.cli.Option option0 = null;
        org.apache.commons.cli.help.OptionFormatter optionFormatter1 = org.apache.commons.cli.help.OptionFormatter.from(option0);
        java.lang.String str3 = optionFormatter1.toOptional("--");
        org.apache.commons.cli.help.OptionFormatter.Builder builder4 = new org.apache.commons.cli.help.OptionFormatter.Builder(optionFormatter1);
        java.lang.String str6 = optionFormatter1.toOptional("--");
        org.apache.commons.cli.help.OptionFormatter.Builder builder7 = new org.apache.commons.cli.help.OptionFormatter.Builder(optionFormatter1);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str8 = optionFormatter1.getOpt();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(optionFormatter1);
        org.junit.Assert.assertEquals("'" + str3 + "' != '" + "[--]" + "'", str3, "[--]");
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "[--]" + "'", str6, "[--]");
    }

    @Test
    public void test218() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test218");
        org.apache.commons.cli.MissingOptionException missingOptionException1 = new org.apache.commons.cli.MissingOptionException("");
    }

    @Test
    public void test219() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test219");
        java.lang.Appendable appendable0 = null;
        org.apache.commons.cli.help.TextHelpAppendable textHelpAppendable1 = new org.apache.commons.cli.help.TextHelpAppendable(appendable0);
        java.lang.CharSequence charSequence2 = null;
        textHelpAppendable1.appendParagraph(charSequence2);
        textHelpAppendable1.setIndent(1);
        textHelpAppendable1.setLeftPad(1);
        org.apache.commons.cli.help.TextStyle.Builder builder8 = textHelpAppendable1.getTextStyleBuilder();
        boolean boolean9 = builder8.isScalable();
        org.junit.Assert.assertNotNull(builder8);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + true + "'", boolean9 == true);
    }

    @Test
    public void test220() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test220");
        org.apache.commons.cli.DeprecatedAttributes.Builder builder0 = org.apache.commons.cli.DeprecatedAttributes.builder();
        org.junit.Assert.assertNotNull(builder0);
    }

    @Test
    public void test221() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test221");
        org.apache.commons.cli.Option option0 = null;
        org.apache.commons.cli.help.OptionFormatter optionFormatter1 = org.apache.commons.cli.help.OptionFormatter.from(option0);
        java.lang.String str3 = optionFormatter1.toOptional("--");
        org.apache.commons.cli.help.OptionFormatter.Builder builder4 = new org.apache.commons.cli.help.OptionFormatter.Builder(optionFormatter1);
        java.lang.String str6 = optionFormatter1.toOptional("--");
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str7 = optionFormatter1.getBothOpt();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(optionFormatter1);
        org.junit.Assert.assertEquals("'" + str3 + "' != '" + "[--]" + "'", str3, "[--]");
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "[--]" + "'", str6, "[--]");
    }

    @Test
    public void test222() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test222");
        org.apache.commons.cli.Option.Builder builder0 = org.apache.commons.cli.Option.builder();
        org.apache.commons.cli.Option.Builder builder1 = builder0.deprecated();
        org.apache.commons.cli.DeprecatedAttributes.Builder builder2 = new org.apache.commons.cli.DeprecatedAttributes.Builder();
        org.apache.commons.cli.DeprecatedAttributes deprecatedAttributes3 = builder2.get();
        java.lang.String str4 = deprecatedAttributes3.getSince();
        org.apache.commons.cli.Option.Builder builder5 = builder0.deprecated(deprecatedAttributes3);
        // The following exception was thrown during execution in test generation
        try {
            org.apache.commons.cli.Option option6 = builder0.get();
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: Either opt or longOpt must be specified");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(builder1);
        org.junit.Assert.assertNotNull(deprecatedAttributes3);
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "" + "'", str4, "");
        org.junit.Assert.assertNotNull(builder5);
    }

    @Test
    public void test223() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test223");
        org.apache.commons.cli.help.TextStyle.Alignment alignment0 = org.apache.commons.cli.help.TextStyle.Alignment.RIGHT;
        org.junit.Assert.assertTrue("'" + alignment0 + "' != '" + org.apache.commons.cli.help.TextStyle.Alignment.RIGHT + "'", alignment0.equals(org.apache.commons.cli.help.TextStyle.Alignment.RIGHT));
    }

    @Test
    public void test224() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test224");
        org.apache.commons.cli.DefaultParser.Builder builder0 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser1 = builder0.get();
        org.apache.commons.cli.Options options2 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties7 = null;
        org.apache.commons.cli.CommandLine commandLine9 = defaultParser1.parse(options2, strArray6, properties7, false);
        java.lang.Object obj11 = commandLine9.getOptionObject('4');
        org.apache.commons.cli.OptionGroup optionGroup12 = null;
        java.lang.String str14 = commandLine9.getOptionValue(optionGroup12, "[--]");
        org.apache.commons.cli.Options options15 = new org.apache.commons.cli.Options();
        org.apache.commons.cli.OptionGroup optionGroup16 = new org.apache.commons.cli.OptionGroup();
        java.util.Collection<org.apache.commons.cli.Option> optionCollection17 = optionGroup16.getOptions();
        boolean boolean18 = optionGroup16.isSelected();
        org.apache.commons.cli.Options options19 = options15.addOptionGroup(optionGroup16);
        java.lang.String str21 = commandLine9.getOptionValue(optionGroup16, " ");
        java.lang.String str23 = commandLine9.getOptionValue('#');
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(defaultParser1);
        org.junit.Assert.assertNotNull(strArray6);
        org.junit.Assert.assertArrayEquals(strArray6, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine9);
        org.junit.Assert.assertNull(obj11);
        org.junit.Assert.assertEquals("'" + str14 + "' != '" + "[--]" + "'", str14, "[--]");
        org.junit.Assert.assertNotNull(optionCollection17);
        org.junit.Assert.assertTrue("'" + boolean18 + "' != '" + false + "'", boolean18 == false);
        org.junit.Assert.assertNotNull(options19);
        org.junit.Assert.assertEquals("'" + str21 + "' != '" + " " + "'", str21, " ");
        org.junit.Assert.assertNull(str23);
    }

    @Test
    public void test225() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test225");
        org.apache.commons.cli.DefaultParser.Builder builder0 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser1 = builder0.get();
        org.apache.commons.cli.Options options2 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties7 = null;
        org.apache.commons.cli.CommandLine commandLine9 = defaultParser1.parse(options2, strArray6, properties7, false);
        java.lang.Object obj11 = commandLine9.getOptionObject('4');
        org.apache.commons.cli.OptionBuilder optionBuilder13 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs();
        org.apache.commons.cli.OptionBuilder optionBuilder15 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs((int) (byte) 1);
        org.apache.commons.cli.OptionBuilder optionBuilder17 = org.apache.commons.cli.OptionBuilder.withLongOpt("");
        org.apache.commons.cli.OptionBuilder optionBuilder19 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs((int) (byte) 1);
        org.apache.commons.cli.OptionBuilder optionBuilder20 = org.apache.commons.cli.OptionBuilder.hasArgs();
        org.apache.commons.cli.OptionBuilder optionBuilder21 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs();
        org.apache.commons.cli.OptionBuilder[] optionBuilderArray22 = new org.apache.commons.cli.OptionBuilder[] { optionBuilder13, optionBuilder15, optionBuilder17, optionBuilder19, optionBuilder20, optionBuilder21 };
        org.apache.commons.cli.OptionBuilder[] optionBuilderArray23 = commandLine9.getParsedOptionValues('#', optionBuilderArray22);
        java.util.function.Supplier<java.lang.String> strSupplier25 = null;
        java.lang.String str26 = commandLine9.getOptionValue('a', strSupplier25);
        org.apache.commons.cli.OptionGroup optionGroup27 = new org.apache.commons.cli.OptionGroup();
        java.util.Collection<org.apache.commons.cli.Option> optionCollection28 = optionGroup27.getOptions();
        boolean boolean29 = commandLine9.hasOption(optionGroup27);
        java.lang.String[] strArray31 = commandLine9.getOptionValues('#');
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(defaultParser1);
        org.junit.Assert.assertNotNull(strArray6);
        org.junit.Assert.assertArrayEquals(strArray6, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine9);
        org.junit.Assert.assertNull(obj11);
        org.junit.Assert.assertNotNull(optionBuilder13);
        org.junit.Assert.assertNotNull(optionBuilder15);
        org.junit.Assert.assertNotNull(optionBuilder17);
        org.junit.Assert.assertNotNull(optionBuilder19);
        org.junit.Assert.assertNotNull(optionBuilder20);
        org.junit.Assert.assertNotNull(optionBuilder21);
        org.junit.Assert.assertNotNull(optionBuilderArray22);
        org.junit.Assert.assertNotNull(optionBuilderArray23);
        org.junit.Assert.assertNull(str26);
        org.junit.Assert.assertNotNull(optionCollection28);
        org.junit.Assert.assertTrue("'" + boolean29 + "' != '" + false + "'", boolean29 == false);
        org.junit.Assert.assertNull(strArray31);
    }

    @Test
    public void test226() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test226");
        org.apache.commons.cli.Option.Builder builder0 = org.apache.commons.cli.Option.builder();
        org.apache.commons.cli.Option.Builder builder1 = builder0.deprecated();
        org.apache.commons.cli.Option.Builder builder3 = builder0.numberOfArgs((int) (short) -1);
        java.lang.Class<java.net.URL> uRLClass4 = org.apache.commons.cli.PatternOptionBuilder.URL_VALUE;
        org.apache.commons.cli.Option.Builder builder5 = builder0.type(uRLClass4);
        org.apache.commons.cli.Option.Builder builder7 = builder5.valueSeparator(' ');
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(builder1);
        org.junit.Assert.assertNotNull(builder3);
        org.junit.Assert.assertNotNull(uRLClass4);
        org.junit.Assert.assertNotNull(builder5);
        org.junit.Assert.assertNotNull(builder7);
    }

    @Test
    public void test227() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test227");
        int int0 = org.apache.commons.cli.help.HelpFormatter.DEFAULT_COLUMN_SPACING;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 5 + "'", int0 == 5);
    }

    @Test
    public void test228() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test228");
        java.lang.Class<?> wildcardClass1 = org.apache.commons.cli.PatternOptionBuilder.getValueType('\000');
        org.junit.Assert.assertNull(wildcardClass1);
    }

    @Test
    public void test229() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test229");
        org.apache.commons.cli.HelpFormatter helpFormatter0 = new org.apache.commons.cli.HelpFormatter();
        int int1 = helpFormatter0.defaultLeftPad;
        org.junit.Assert.assertTrue("'" + int1 + "' != '" + 1 + "'", int1 == 1);
    }

    @Test
    public void test230() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test230");
        java.lang.Appendable appendable0 = null;
        org.apache.commons.cli.help.TextHelpAppendable textHelpAppendable1 = new org.apache.commons.cli.help.TextHelpAppendable(appendable0);
        java.lang.CharSequence charSequence2 = null;
        textHelpAppendable1.appendParagraph(charSequence2);
        org.apache.commons.cli.DefaultParser.Builder builder5 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser6 = builder5.get();
        org.apache.commons.cli.Options options7 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray11 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties12 = null;
        org.apache.commons.cli.CommandLine commandLine14 = defaultParser6.parse(options7, strArray11, properties12, false);
        java.lang.Object obj16 = commandLine14.getOptionObject('4');
        org.apache.commons.cli.OptionBuilder optionBuilder18 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs();
        org.apache.commons.cli.OptionBuilder optionBuilder20 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs((int) (byte) 1);
        org.apache.commons.cli.OptionBuilder optionBuilder22 = org.apache.commons.cli.OptionBuilder.withLongOpt("");
        org.apache.commons.cli.OptionBuilder optionBuilder24 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs((int) (byte) 1);
        org.apache.commons.cli.OptionBuilder optionBuilder25 = org.apache.commons.cli.OptionBuilder.hasArgs();
        org.apache.commons.cli.OptionBuilder optionBuilder26 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs();
        org.apache.commons.cli.OptionBuilder[] optionBuilderArray27 = new org.apache.commons.cli.OptionBuilder[] { optionBuilder18, optionBuilder20, optionBuilder22, optionBuilder24, optionBuilder25, optionBuilder26 };
        org.apache.commons.cli.OptionBuilder[] optionBuilderArray28 = commandLine14.getParsedOptionValues('#', optionBuilderArray27);
        org.apache.commons.cli.DefaultParser.Builder builder30 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser31 = builder30.get();
        org.apache.commons.cli.DefaultParser.Builder builder32 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser33 = builder32.get();
        org.apache.commons.cli.CommandLineParser[] commandLineParserArray34 = new org.apache.commons.cli.CommandLineParser[] { defaultParser31, defaultParser33 };
        org.apache.commons.cli.CommandLineParser[] commandLineParserArray35 = commandLine14.getParsedOptionValues('#', commandLineParserArray34);
        // The following exception was thrown during execution in test generation
        try {
            textHelpAppendable1.appendParagraphFormat("[ Option arg [ARG...] :: null :: class java.lang.String ]", (java.lang.Object[]) commandLineParserArray35);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(builder5);
        org.junit.Assert.assertNotNull(defaultParser6);
        org.junit.Assert.assertNotNull(strArray11);
        org.junit.Assert.assertArrayEquals(strArray11, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine14);
        org.junit.Assert.assertNull(obj16);
        org.junit.Assert.assertNotNull(optionBuilder18);
        org.junit.Assert.assertNotNull(optionBuilder20);
        org.junit.Assert.assertNotNull(optionBuilder22);
        org.junit.Assert.assertNotNull(optionBuilder24);
        org.junit.Assert.assertNotNull(optionBuilder25);
        org.junit.Assert.assertNotNull(optionBuilder26);
        org.junit.Assert.assertNotNull(optionBuilderArray27);
        org.junit.Assert.assertNotNull(optionBuilderArray28);
        org.junit.Assert.assertNotNull(builder30);
        org.junit.Assert.assertNotNull(defaultParser31);
        org.junit.Assert.assertNotNull(builder32);
        org.junit.Assert.assertNotNull(defaultParser33);
        org.junit.Assert.assertNotNull(commandLineParserArray34);
        org.junit.Assert.assertNotNull(commandLineParserArray35);
    }

    @Test
    public void test231() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test231");
        java.lang.Appendable appendable0 = null;
        org.apache.commons.cli.help.TextHelpAppendable textHelpAppendable1 = new org.apache.commons.cli.help.TextHelpAppendable(appendable0);
        java.lang.CharSequence charSequence2 = null;
        textHelpAppendable1.appendParagraph(charSequence2);
        textHelpAppendable1.setIndent(0);
        java.lang.Object[] objArray11 = new java.lang.Object[] { 10L, (short) 1, "", (short) 100 };
        textHelpAppendable1.appendParagraphFormat("", objArray11);
        org.apache.commons.cli.help.TextHelpAppendable textHelpAppendable13 = new org.apache.commons.cli.help.TextHelpAppendable((java.lang.Appendable) textHelpAppendable1);
        // The following exception was thrown during execution in test generation
        try {
            textHelpAppendable1.appendTitle((java.lang.CharSequence) " | ");
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(objArray11);
        org.junit.Assert.assertEquals(java.util.Arrays.deepToString(objArray11), "[10, 1, , 100]");
        org.junit.Assert.assertEquals(java.util.Arrays.toString(objArray11), "[10, 1, , 100]");
    }

    @Test
    public void test232() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test232");
        org.apache.commons.cli.DefaultParser.Builder builder0 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser1 = builder0.get();
        org.apache.commons.cli.Options options2 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties7 = null;
        org.apache.commons.cli.CommandLine commandLine9 = defaultParser1.parse(options2, strArray6, properties7, false);
        java.lang.Object obj11 = commandLine9.getOptionObject('4');
        org.apache.commons.cli.OptionBuilder optionBuilder13 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs();
        org.apache.commons.cli.OptionBuilder optionBuilder15 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs((int) (byte) 1);
        org.apache.commons.cli.OptionBuilder optionBuilder17 = org.apache.commons.cli.OptionBuilder.withLongOpt("");
        org.apache.commons.cli.OptionBuilder optionBuilder19 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs((int) (byte) 1);
        org.apache.commons.cli.OptionBuilder optionBuilder20 = org.apache.commons.cli.OptionBuilder.hasArgs();
        org.apache.commons.cli.OptionBuilder optionBuilder21 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs();
        org.apache.commons.cli.OptionBuilder[] optionBuilderArray22 = new org.apache.commons.cli.OptionBuilder[] { optionBuilder13, optionBuilder15, optionBuilder17, optionBuilder19, optionBuilder20, optionBuilder21 };
        org.apache.commons.cli.OptionBuilder[] optionBuilderArray23 = commandLine9.getParsedOptionValues('#', optionBuilderArray22);
        java.util.ArrayList<java.lang.String>[] strListArray25 = commandLine9.getParsedOptionValues("[--]");
        java.lang.reflect.AnnotatedElement annotatedElement27 = commandLine9.getParsedOptionValue("--");
        java.lang.String str30 = commandLine9.getOptionValue("[ Option arg [ARG...] :: null :: class java.lang.String ]", "[ Options: [ short {} ] [ long {} ]");
        java.util.function.Supplier<org.apache.commons.cli.CommandLine> commandLineSupplier32 = null;
        org.apache.commons.cli.CommandLine commandLine33 = commandLine9.getParsedOptionValue(" | ", commandLineSupplier32);
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(defaultParser1);
        org.junit.Assert.assertNotNull(strArray6);
        org.junit.Assert.assertArrayEquals(strArray6, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine9);
        org.junit.Assert.assertNull(obj11);
        org.junit.Assert.assertNotNull(optionBuilder13);
        org.junit.Assert.assertNotNull(optionBuilder15);
        org.junit.Assert.assertNotNull(optionBuilder17);
        org.junit.Assert.assertNotNull(optionBuilder19);
        org.junit.Assert.assertNotNull(optionBuilder20);
        org.junit.Assert.assertNotNull(optionBuilder21);
        org.junit.Assert.assertNotNull(optionBuilderArray22);
        org.junit.Assert.assertNotNull(optionBuilderArray23);
        org.junit.Assert.assertNull(strListArray25);
        org.junit.Assert.assertNull(annotatedElement27);
        org.junit.Assert.assertEquals("'" + str30 + "' != '" + "[ Options: [ short {} ] [ long {} ]" + "'", str30, "[ Options: [ short {} ] [ long {} ]");
        org.junit.Assert.assertNull(commandLine33);
    }

    @Test
    public void test233() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test233");
        org.apache.commons.cli.DefaultParser.Builder builder0 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser1 = builder0.get();
        org.apache.commons.cli.Options options2 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties7 = null;
        org.apache.commons.cli.CommandLine commandLine9 = defaultParser1.parse(options2, strArray6, properties7, false);
        java.lang.Object obj11 = commandLine9.getOptionObject('4');
        org.apache.commons.cli.OptionBuilder optionBuilder13 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs();
        org.apache.commons.cli.OptionBuilder optionBuilder15 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs((int) (byte) 1);
        org.apache.commons.cli.OptionBuilder optionBuilder17 = org.apache.commons.cli.OptionBuilder.withLongOpt("");
        org.apache.commons.cli.OptionBuilder optionBuilder19 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs((int) (byte) 1);
        org.apache.commons.cli.OptionBuilder optionBuilder20 = org.apache.commons.cli.OptionBuilder.hasArgs();
        org.apache.commons.cli.OptionBuilder optionBuilder21 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs();
        org.apache.commons.cli.OptionBuilder[] optionBuilderArray22 = new org.apache.commons.cli.OptionBuilder[] { optionBuilder13, optionBuilder15, optionBuilder17, optionBuilder19, optionBuilder20, optionBuilder21 };
        org.apache.commons.cli.OptionBuilder[] optionBuilderArray23 = commandLine9.getParsedOptionValues('#', optionBuilderArray22);
        org.apache.commons.cli.DefaultParser.Builder builder25 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser26 = builder25.get();
        org.apache.commons.cli.DefaultParser.Builder builder27 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser28 = builder27.get();
        org.apache.commons.cli.CommandLineParser[] commandLineParserArray29 = new org.apache.commons.cli.CommandLineParser[] { defaultParser26, defaultParser28 };
        org.apache.commons.cli.CommandLineParser[] commandLineParserArray30 = commandLine9.getParsedOptionValues('#', commandLineParserArray29);
        org.apache.commons.cli.Option option31 = null;
        org.apache.commons.cli.DefaultParser.Builder builder32 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser33 = builder32.get();
        org.apache.commons.cli.Options options34 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray38 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties39 = null;
        org.apache.commons.cli.CommandLine commandLine41 = defaultParser33.parse(options34, strArray38, properties39, false);
        java.lang.Object obj43 = commandLine41.getOptionObject('4');
        java.util.Properties properties45 = commandLine41.getOptionProperties("--");
        java.util.Hashtable<java.lang.Object, java.lang.Object> objMap46 = commandLine9.getParsedOptionValue(option31, (java.util.Hashtable<java.lang.Object, java.lang.Object>) properties45);
        java.util.function.Supplier<java.lang.String> strSupplier48 = null;
        java.lang.String str49 = commandLine9.getOptionValue("[]", strSupplier48);
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(defaultParser1);
        org.junit.Assert.assertNotNull(strArray6);
        org.junit.Assert.assertArrayEquals(strArray6, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine9);
        org.junit.Assert.assertNull(obj11);
        org.junit.Assert.assertNotNull(optionBuilder13);
        org.junit.Assert.assertNotNull(optionBuilder15);
        org.junit.Assert.assertNotNull(optionBuilder17);
        org.junit.Assert.assertNotNull(optionBuilder19);
        org.junit.Assert.assertNotNull(optionBuilder20);
        org.junit.Assert.assertNotNull(optionBuilder21);
        org.junit.Assert.assertNotNull(optionBuilderArray22);
        org.junit.Assert.assertNotNull(optionBuilderArray23);
        org.junit.Assert.assertNotNull(builder25);
        org.junit.Assert.assertNotNull(defaultParser26);
        org.junit.Assert.assertNotNull(builder27);
        org.junit.Assert.assertNotNull(defaultParser28);
        org.junit.Assert.assertNotNull(commandLineParserArray29);
        org.junit.Assert.assertNotNull(commandLineParserArray30);
        org.junit.Assert.assertNotNull(builder32);
        org.junit.Assert.assertNotNull(defaultParser33);
        org.junit.Assert.assertNotNull(strArray38);
        org.junit.Assert.assertArrayEquals(strArray38, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine41);
        org.junit.Assert.assertNull(obj43);
        org.junit.Assert.assertNotNull(properties45);
        org.junit.Assert.assertNotNull(objMap46);
        org.junit.Assert.assertNull(str49);
    }

    @Test
    public void test234() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test234");
        org.apache.commons.cli.DefaultParser.Builder builder0 = org.apache.commons.cli.DefaultParser.builder();
        java.util.function.Consumer<org.apache.commons.cli.Option> optionConsumer1 = null;
        org.apache.commons.cli.DefaultParser.Builder builder2 = builder0.setDeprecatedHandler(optionConsumer1);
        org.apache.commons.cli.DefaultParser defaultParser3 = builder0.get();
        org.apache.commons.cli.DefaultParser.Builder builder5 = builder0.setStripLeadingAndTrailingQuotes((java.lang.Boolean) false);
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(builder2);
        org.junit.Assert.assertNotNull(defaultParser3);
        org.junit.Assert.assertNotNull(builder5);
    }

    @Test
    public void test235() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test235");
        org.apache.commons.cli.help.TextStyle textStyle0 = org.apache.commons.cli.help.TextStyle.DEFAULT;
        java.lang.CharSequence charSequence3 = textStyle0.pad(false, (java.lang.CharSequence) "");
        int int4 = textStyle0.getIndent();
        java.lang.CharSequence charSequence7 = textStyle0.pad(false, (java.lang.CharSequence) "[ Options: [ short {} ] [ long {} ]");
        org.junit.Assert.assertNotNull(textStyle0);
        org.junit.Assert.assertEquals("'" + charSequence3 + "' != '" + "" + "'", charSequence3, "");
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 0 + "'", int4 == 0);
        org.junit.Assert.assertEquals("'" + charSequence7 + "' != '" + "[ Options: [ short {} ] [ long {} ]" + "'", charSequence7, "[ Options: [ short {} ] [ long {} ]");
    }

    @Test
    public void test236() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test236");
        org.apache.commons.cli.Option option1 = org.apache.commons.cli.OptionBuilder.create("arg");
        option1.setRequired(true);
        option1.setArgName("Deprecated");
        org.junit.Assert.assertNotNull(option1);
    }

    @Test
    public void test237() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test237");
        org.apache.commons.cli.Option.Builder builder0 = org.apache.commons.cli.Option.builder();
        org.apache.commons.cli.Option.Builder builder1 = builder0.deprecated();
        org.apache.commons.cli.DeprecatedAttributes.Builder builder2 = new org.apache.commons.cli.DeprecatedAttributes.Builder();
        org.apache.commons.cli.DeprecatedAttributes deprecatedAttributes3 = builder2.get();
        java.lang.String str4 = deprecatedAttributes3.getSince();
        java.lang.String str5 = deprecatedAttributes3.toString();
        org.apache.commons.cli.Option.Builder builder6 = builder0.deprecated(deprecatedAttributes3);
        org.apache.commons.cli.Option.Builder builder8 = builder6.valueSeparator('4');
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(builder1);
        org.junit.Assert.assertNotNull(deprecatedAttributes3);
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "" + "'", str4, "");
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "Deprecated" + "'", str5, "Deprecated");
        org.junit.Assert.assertNotNull(builder6);
        org.junit.Assert.assertNotNull(builder8);
    }

    @Test
    public void test238() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test238");
        org.apache.commons.cli.HelpFormatter helpFormatter0 = new org.apache.commons.cli.HelpFormatter();
        org.apache.commons.cli.Options options2 = new org.apache.commons.cli.Options();
        org.apache.commons.cli.OptionGroup optionGroup3 = new org.apache.commons.cli.OptionGroup();
        java.util.Collection<org.apache.commons.cli.Option> optionCollection4 = optionGroup3.getOptions();
        boolean boolean5 = optionGroup3.isSelected();
        org.apache.commons.cli.Options options6 = options2.addOptionGroup(optionGroup3);
        helpFormatter0.printHelp("usage: ", options2);
        helpFormatter0.setWidth((int) 'a');
        org.apache.commons.cli.Options options11 = new org.apache.commons.cli.Options();
        org.apache.commons.cli.OptionGroup optionGroup12 = new org.apache.commons.cli.OptionGroup();
        java.util.Collection<org.apache.commons.cli.Option> optionCollection13 = optionGroup12.getOptions();
        boolean boolean14 = optionGroup12.isSelected();
        org.apache.commons.cli.Options options15 = options11.addOptionGroup(optionGroup12);
        helpFormatter0.printHelp("[ Options: [ short {} ] [ long {} ]", options11, false);
        helpFormatter0.setSyntaxPrefix("");
        org.junit.Assert.assertNotNull(optionCollection4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertNotNull(options6);
        org.junit.Assert.assertNotNull(optionCollection13);
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + false + "'", boolean14 == false);
        org.junit.Assert.assertNotNull(options15);
    }

    @Test
    public void test239() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test239");
        org.apache.commons.cli.DefaultParser.Builder builder0 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser1 = builder0.get();
        org.apache.commons.cli.Options options2 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties7 = null;
        org.apache.commons.cli.CommandLine commandLine9 = defaultParser1.parse(options2, strArray6, properties7, false);
        java.lang.Object obj11 = commandLine9.getOptionObject('4');
        java.lang.Object obj13 = commandLine9.getOptionObject('4');
        org.apache.commons.cli.OptionGroup optionGroup14 = new org.apache.commons.cli.OptionGroup();
        boolean boolean15 = optionGroup14.isRequired();
        boolean boolean16 = commandLine9.hasOption(optionGroup14);
        boolean boolean17 = optionGroup14.isRequired();
        boolean boolean18 = optionGroup14.isSelected();
        java.util.Collection<java.lang.String> strCollection19 = optionGroup14.getNames();
        boolean boolean20 = optionGroup14.isRequired();
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(defaultParser1);
        org.junit.Assert.assertNotNull(strArray6);
        org.junit.Assert.assertArrayEquals(strArray6, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine9);
        org.junit.Assert.assertNull(obj11);
        org.junit.Assert.assertNull(obj13);
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertTrue("'" + boolean16 + "' != '" + false + "'", boolean16 == false);
        org.junit.Assert.assertTrue("'" + boolean17 + "' != '" + false + "'", boolean17 == false);
        org.junit.Assert.assertTrue("'" + boolean18 + "' != '" + false + "'", boolean18 == false);
        org.junit.Assert.assertNotNull(strCollection19);
        org.junit.Assert.assertTrue("'" + boolean20 + "' != '" + false + "'", boolean20 == false);
    }

    @Test
    public void test240() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test240");
        org.apache.commons.cli.CommandLine.Builder builder0 = new org.apache.commons.cli.CommandLine.Builder();
        org.apache.commons.cli.CommandLine commandLine1 = builder0.build();
        org.apache.commons.cli.CommandLine commandLine2 = builder0.build();
        org.apache.commons.cli.CommandLine commandLine3 = builder0.get();
        org.apache.commons.cli.Option option5 = org.apache.commons.cli.OptionBuilder.create("arg");
        java.lang.String str7 = option5.getValue("[--]");
        org.apache.commons.cli.help.OptionFormatter.Builder builder8 = org.apache.commons.cli.help.OptionFormatter.builder();
        org.apache.commons.cli.help.OptionFormatter.Builder builder10 = builder8.setOptArgSeparator("");
        org.apache.commons.cli.help.OptionFormatter optionFormatter11 = builder8.get();
        option5.setType((java.lang.Object) optionFormatter11);
        java.lang.String str13 = option5.getSince();
        org.apache.commons.cli.CommandLine.Builder builder14 = builder0.addOption(option5);
        org.apache.commons.cli.CommandLine commandLine15 = builder14.build();
        org.apache.commons.cli.DefaultParser.Builder builder16 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser17 = builder16.get();
        org.apache.commons.cli.Options options18 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray22 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties23 = null;
        org.apache.commons.cli.CommandLine commandLine25 = defaultParser17.parse(options18, strArray22, properties23, false);
        java.lang.Object obj27 = commandLine25.getOptionObject('4');
        java.lang.Object obj29 = commandLine25.getOptionObject('4');
        org.apache.commons.cli.OptionGroup optionGroup30 = new org.apache.commons.cli.OptionGroup();
        boolean boolean31 = optionGroup30.isRequired();
        boolean boolean32 = commandLine25.hasOption(optionGroup30);
        org.apache.commons.cli.CommandLine[] commandLineArray33 = commandLine15.getParsedOptionValue(optionGroup30);
        org.junit.Assert.assertNotNull(commandLine1);
        org.junit.Assert.assertNotNull(commandLine2);
        org.junit.Assert.assertNotNull(commandLine3);
        org.junit.Assert.assertNotNull(option5);
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "[--]" + "'", str7, "[--]");
        org.junit.Assert.assertNotNull(builder8);
        org.junit.Assert.assertNotNull(builder10);
        org.junit.Assert.assertNull(optionFormatter11);
        org.junit.Assert.assertNull(str13);
        org.junit.Assert.assertNotNull(builder14);
        org.junit.Assert.assertNotNull(commandLine15);
        org.junit.Assert.assertNotNull(builder16);
        org.junit.Assert.assertNotNull(defaultParser17);
        org.junit.Assert.assertNotNull(strArray22);
        org.junit.Assert.assertArrayEquals(strArray22, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine25);
        org.junit.Assert.assertNull(obj27);
        org.junit.Assert.assertNull(obj29);
        org.junit.Assert.assertTrue("'" + boolean31 + "' != '" + false + "'", boolean31 == false);
        org.junit.Assert.assertTrue("'" + boolean32 + "' != '" + false + "'", boolean32 == false);
        org.junit.Assert.assertNull(commandLineArray33);
    }

    @Test
    public void test241() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test241");
        org.apache.commons.cli.HelpFormatter helpFormatter0 = new org.apache.commons.cli.HelpFormatter();
        java.lang.String str1 = helpFormatter0.getLongOptSeparator();
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + " " + "'", str1, " ");
    }

    @Test
    public void test242() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test242");
        org.apache.commons.cli.help.OptionFormatter.Builder builder0 = org.apache.commons.cli.help.OptionFormatter.builder();
        org.apache.commons.cli.help.OptionFormatter.Builder builder2 = builder0.setOptArgSeparator("");
        org.apache.commons.cli.help.OptionFormatter.Builder builder5 = builder2.setOptionalDelimiters("[]", "--");
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(builder2);
        org.junit.Assert.assertNotNull(builder5);
    }

    @Test
    public void test243() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test243");
        org.apache.commons.cli.Option option0 = null;
        org.apache.commons.cli.help.OptionFormatter optionFormatter1 = org.apache.commons.cli.help.OptionFormatter.from(option0);
        java.lang.String str3 = optionFormatter1.toOptional("--");
        org.apache.commons.cli.help.OptionFormatter.Builder builder4 = new org.apache.commons.cli.help.OptionFormatter.Builder(optionFormatter1);
        java.lang.String str6 = optionFormatter1.toOptional("--");
        org.apache.commons.cli.help.OptionFormatter.Builder builder7 = new org.apache.commons.cli.help.OptionFormatter.Builder(optionFormatter1);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str8 = optionFormatter1.getSince();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(optionFormatter1);
        org.junit.Assert.assertEquals("'" + str3 + "' != '" + "[--]" + "'", str3, "[--]");
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "[--]" + "'", str6, "[--]");
    }

    @Test
    public void test244() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test244");
        org.apache.commons.cli.DefaultParser.Builder builder0 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser1 = builder0.get();
        org.apache.commons.cli.Options options2 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties7 = null;
        org.apache.commons.cli.CommandLine commandLine9 = defaultParser1.parse(options2, strArray6, properties7, false);
        java.lang.Object obj11 = commandLine9.getOptionObject('4');
        org.apache.commons.cli.OptionGroup optionGroup12 = null;
        java.lang.String str14 = commandLine9.getOptionValue(optionGroup12, "[--]");
        java.util.function.Supplier<java.lang.String> strSupplier16 = null;
        java.lang.String str17 = commandLine9.getOptionValue("hi!", strSupplier16);
        org.apache.commons.cli.DefaultParser.Builder builder18 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser19 = builder18.get();
        org.apache.commons.cli.Options options20 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray24 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties25 = null;
        org.apache.commons.cli.CommandLine commandLine27 = defaultParser19.parse(options20, strArray24, properties25, false);
        java.lang.Object obj29 = commandLine27.getOptionObject('4');
        org.apache.commons.cli.OptionBuilder optionBuilder31 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs();
        org.apache.commons.cli.OptionBuilder optionBuilder33 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs((int) (byte) 1);
        org.apache.commons.cli.OptionBuilder optionBuilder35 = org.apache.commons.cli.OptionBuilder.withLongOpt("");
        org.apache.commons.cli.OptionBuilder optionBuilder37 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs((int) (byte) 1);
        org.apache.commons.cli.OptionBuilder optionBuilder38 = org.apache.commons.cli.OptionBuilder.hasArgs();
        org.apache.commons.cli.OptionBuilder optionBuilder39 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs();
        org.apache.commons.cli.OptionBuilder[] optionBuilderArray40 = new org.apache.commons.cli.OptionBuilder[] { optionBuilder31, optionBuilder33, optionBuilder35, optionBuilder37, optionBuilder38, optionBuilder39 };
        org.apache.commons.cli.OptionBuilder[] optionBuilderArray41 = commandLine27.getParsedOptionValues('#', optionBuilderArray40);
        org.apache.commons.cli.OptionGroup optionGroup42 = new org.apache.commons.cli.OptionGroup();
        boolean boolean43 = optionGroup42.isRequired();
        java.lang.String str45 = commandLine27.getOptionValue(optionGroup42, "");
        org.apache.commons.cli.Option option46 = null;
        optionGroup42.setSelected(option46);
        java.lang.String str48 = optionGroup42.getSelected();
        boolean boolean49 = optionGroup42.isRequired();
        org.apache.commons.cli.DefaultParser.Builder builder50 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser51 = builder50.get();
        org.apache.commons.cli.Options options52 = new org.apache.commons.cli.Options();
        org.apache.commons.cli.DefaultParser.Builder builder53 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser54 = builder53.get();
        org.apache.commons.cli.Options options55 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray59 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties60 = null;
        org.apache.commons.cli.CommandLine commandLine62 = defaultParser54.parse(options55, strArray59, properties60, false);
        org.apache.commons.cli.DefaultParser.Builder builder63 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser64 = builder63.get();
        org.apache.commons.cli.Options options65 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray69 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties70 = null;
        org.apache.commons.cli.CommandLine commandLine72 = defaultParser64.parse(options65, strArray69, properties70, false);
        java.lang.Object obj74 = commandLine72.getOptionObject('4');
        org.apache.commons.cli.Option option75 = null;
        java.util.Properties properties76 = commandLine72.getOptionProperties(option75);
        org.apache.commons.cli.CommandLine commandLine78 = defaultParser51.parse(options52, strArray59, properties76, false);
        org.apache.commons.cli.CommandLine.Builder builder80 = new org.apache.commons.cli.CommandLine.Builder();
        org.apache.commons.cli.CommandLine commandLine81 = commandLine78.getParsedOptionValue(' ', (java.util.function.Supplier<org.apache.commons.cli.CommandLine>) builder80);
        java.util.List<java.lang.String> strList82 = commandLine78.getArgList();
        java.lang.Iterable<java.lang.String> strIterable83 = commandLine9.getParsedOptionValue(optionGroup42, (java.lang.Iterable<java.lang.String>) strList82);
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(defaultParser1);
        org.junit.Assert.assertNotNull(strArray6);
        org.junit.Assert.assertArrayEquals(strArray6, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine9);
        org.junit.Assert.assertNull(obj11);
        org.junit.Assert.assertEquals("'" + str14 + "' != '" + "[--]" + "'", str14, "[--]");
        org.junit.Assert.assertNull(str17);
        org.junit.Assert.assertNotNull(builder18);
        org.junit.Assert.assertNotNull(defaultParser19);
        org.junit.Assert.assertNotNull(strArray24);
        org.junit.Assert.assertArrayEquals(strArray24, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine27);
        org.junit.Assert.assertNull(obj29);
        org.junit.Assert.assertNotNull(optionBuilder31);
        org.junit.Assert.assertNotNull(optionBuilder33);
        org.junit.Assert.assertNotNull(optionBuilder35);
        org.junit.Assert.assertNotNull(optionBuilder37);
        org.junit.Assert.assertNotNull(optionBuilder38);
        org.junit.Assert.assertNotNull(optionBuilder39);
        org.junit.Assert.assertNotNull(optionBuilderArray40);
        org.junit.Assert.assertNotNull(optionBuilderArray41);
        org.junit.Assert.assertTrue("'" + boolean43 + "' != '" + false + "'", boolean43 == false);
        org.junit.Assert.assertEquals("'" + str45 + "' != '" + "" + "'", str45, "");
        org.junit.Assert.assertNull(str48);
        org.junit.Assert.assertTrue("'" + boolean49 + "' != '" + false + "'", boolean49 == false);
        org.junit.Assert.assertNotNull(builder50);
        org.junit.Assert.assertNotNull(defaultParser51);
        org.junit.Assert.assertNotNull(builder53);
        org.junit.Assert.assertNotNull(defaultParser54);
        org.junit.Assert.assertNotNull(strArray59);
        org.junit.Assert.assertArrayEquals(strArray59, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine62);
        org.junit.Assert.assertNotNull(builder63);
        org.junit.Assert.assertNotNull(defaultParser64);
        org.junit.Assert.assertNotNull(strArray69);
        org.junit.Assert.assertArrayEquals(strArray69, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine72);
        org.junit.Assert.assertNull(obj74);
        org.junit.Assert.assertNotNull(properties76);
        org.junit.Assert.assertNotNull(commandLine78);
        org.junit.Assert.assertNotNull(commandLine81);
        org.junit.Assert.assertNotNull(strList82);
        org.junit.Assert.assertNotNull(strIterable83);
    }

    @Test
    public void test245() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test245");
        org.apache.commons.cli.UnrecognizedOptionException unrecognizedOptionException2 = new org.apache.commons.cli.UnrecognizedOptionException("--", "usage: ");
        org.apache.commons.cli.UnrecognizedOptionException unrecognizedOptionException4 = new org.apache.commons.cli.UnrecognizedOptionException("");
        unrecognizedOptionException2.addSuppressed((java.lang.Throwable) unrecognizedOptionException4);
        org.apache.commons.cli.UnrecognizedOptionException unrecognizedOptionException8 = new org.apache.commons.cli.UnrecognizedOptionException("--", "usage: ");
        org.apache.commons.cli.UnrecognizedOptionException unrecognizedOptionException10 = new org.apache.commons.cli.UnrecognizedOptionException("");
        unrecognizedOptionException8.addSuppressed((java.lang.Throwable) unrecognizedOptionException10);
        unrecognizedOptionException2.addSuppressed((java.lang.Throwable) unrecognizedOptionException8);
        org.apache.commons.cli.ParseException parseException13 = new org.apache.commons.cli.ParseException((java.lang.Throwable) unrecognizedOptionException2);
    }

    @Test
    public void test246() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test246");
        org.apache.commons.cli.DefaultParser.Builder builder0 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser1 = builder0.get();
        org.apache.commons.cli.Options options2 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties7 = null;
        org.apache.commons.cli.CommandLine commandLine9 = defaultParser1.parse(options2, strArray6, properties7, false);
        java.lang.Object obj11 = commandLine9.getOptionObject('4');
        org.apache.commons.cli.OptionGroup optionGroup12 = null;
        java.lang.String str14 = commandLine9.getOptionValue(optionGroup12, "[--]");
        java.util.function.Supplier<java.lang.String> strSupplier16 = null;
        java.lang.String str17 = commandLine9.getOptionValue("hi!", strSupplier16);
        org.apache.commons.cli.Option option19 = org.apache.commons.cli.OptionBuilder.create("arg");
        java.util.List<java.lang.String> strList20 = option19.getValuesList();
        boolean boolean21 = commandLine9.hasOption(option19);
        boolean boolean22 = option19.isDeprecated();
        option19.setValueSeparator(' ');
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(defaultParser1);
        org.junit.Assert.assertNotNull(strArray6);
        org.junit.Assert.assertArrayEquals(strArray6, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine9);
        org.junit.Assert.assertNull(obj11);
        org.junit.Assert.assertEquals("'" + str14 + "' != '" + "[--]" + "'", str14, "[--]");
        org.junit.Assert.assertNull(str17);
        org.junit.Assert.assertNotNull(option19);
        org.junit.Assert.assertNotNull(strList20);
        org.junit.Assert.assertTrue("'" + boolean21 + "' != '" + false + "'", boolean21 == false);
        org.junit.Assert.assertTrue("'" + boolean22 + "' != '" + false + "'", boolean22 == false);
    }

    @Test
    public void test247() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test247");
        java.lang.Appendable appendable0 = null;
        org.apache.commons.cli.help.TextHelpAppendable textHelpAppendable1 = new org.apache.commons.cli.help.TextHelpAppendable(appendable0);
        java.lang.CharSequence charSequence2 = null;
        textHelpAppendable1.appendParagraph(charSequence2);
        textHelpAppendable1.setIndent(0);
        java.lang.Object[] objArray11 = new java.lang.Object[] { 10L, (short) 1, "", (short) 100 };
        textHelpAppendable1.appendParagraphFormat("", objArray11);
        org.apache.commons.cli.help.TextHelpAppendable textHelpAppendable13 = new org.apache.commons.cli.help.TextHelpAppendable((java.lang.Appendable) textHelpAppendable1);
        org.apache.commons.cli.help.TextHelpAppendable textHelpAppendable14 = new org.apache.commons.cli.help.TextHelpAppendable((java.lang.Appendable) textHelpAppendable13);
        org.apache.commons.cli.help.TextStyle.Builder builder15 = textHelpAppendable14.getTextStyleBuilder();
        org.apache.commons.cli.help.TextStyle.Builder builder17 = builder15.setIndent((int) (byte) 0);
        int int18 = builder17.getLeftPad();
        org.apache.commons.cli.help.TextStyle.Builder builder20 = builder17.setMinWidth(10);
        org.junit.Assert.assertNotNull(objArray11);
        org.junit.Assert.assertEquals(java.util.Arrays.deepToString(objArray11), "[10, 1, , 100]");
        org.junit.Assert.assertEquals(java.util.Arrays.toString(objArray11), "[10, 1, , 100]");
        org.junit.Assert.assertNotNull(builder15);
        org.junit.Assert.assertNotNull(builder17);
        org.junit.Assert.assertTrue("'" + int18 + "' != '" + 1 + "'", int18 == 1);
        org.junit.Assert.assertNotNull(builder20);
    }

    @Test
    public void test248() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test248");
        java.lang.String[] strArray3 = new java.lang.String[] { "hi!", "arg" };
        java.util.ArrayList<java.lang.String> strList4 = new java.util.ArrayList<java.lang.String>();
        boolean boolean5 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList4, strArray3);
        org.apache.commons.cli.AmbiguousOptionException ambiguousOptionException6 = new org.apache.commons.cli.AmbiguousOptionException("hi!", (java.util.Collection<java.lang.String>) strList4);
        org.apache.commons.cli.ParseException parseException7 = org.apache.commons.cli.ParseException.wrap((java.lang.Throwable) ambiguousOptionException6);
        org.junit.Assert.assertNotNull(strArray3);
        org.junit.Assert.assertArrayEquals(strArray3, new java.lang.String[] { "hi!", "arg" });
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertNotNull(parseException7);
    }

    @Test
    public void test249() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test249");
        java.lang.Appendable appendable0 = null;
        org.apache.commons.cli.help.TextHelpAppendable textHelpAppendable1 = new org.apache.commons.cli.help.TextHelpAppendable(appendable0);
        java.lang.CharSequence charSequence2 = null;
        textHelpAppendable1.appendParagraph(charSequence2);
        textHelpAppendable1.setIndent(0);
        java.lang.Object[] objArray11 = new java.lang.Object[] { 10L, (short) 1, "", (short) 100 };
        textHelpAppendable1.appendParagraphFormat("", objArray11);
        org.apache.commons.cli.help.TextHelpAppendable textHelpAppendable13 = new org.apache.commons.cli.help.TextHelpAppendable((java.lang.Appendable) textHelpAppendable1);
        org.apache.commons.cli.help.TextHelpAppendable textHelpAppendable14 = new org.apache.commons.cli.help.TextHelpAppendable((java.lang.Appendable) textHelpAppendable13);
        org.apache.commons.cli.help.TextStyle.Builder builder15 = textHelpAppendable14.getTextStyleBuilder();
        org.apache.commons.cli.help.TextStyle.Builder builder17 = builder15.setIndent((int) (byte) 0);
        int int18 = builder17.getLeftPad();
        org.apache.commons.cli.help.TextStyle.Builder builder20 = builder17.setLeftPad((int) '\000');
        org.junit.Assert.assertNotNull(objArray11);
        org.junit.Assert.assertEquals(java.util.Arrays.deepToString(objArray11), "[10, 1, , 100]");
        org.junit.Assert.assertEquals(java.util.Arrays.toString(objArray11), "[10, 1, , 100]");
        org.junit.Assert.assertNotNull(builder15);
        org.junit.Assert.assertNotNull(builder17);
        org.junit.Assert.assertTrue("'" + int18 + "' != '" + 1 + "'", int18 == 1);
        org.junit.Assert.assertNotNull(builder20);
    }

    @Test
    public void test250() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test250");
        org.apache.commons.cli.Option.Builder builder0 = org.apache.commons.cli.Option.builder();
        org.apache.commons.cli.Option.Builder builder1 = builder0.deprecated();
        org.apache.commons.cli.DeprecatedAttributes.Builder builder2 = new org.apache.commons.cli.DeprecatedAttributes.Builder();
        org.apache.commons.cli.DeprecatedAttributes deprecatedAttributes3 = builder2.get();
        java.lang.String str4 = deprecatedAttributes3.getSince();
        org.apache.commons.cli.Option.Builder builder5 = builder0.deprecated(deprecatedAttributes3);
        org.apache.commons.cli.Option.Builder builder7 = builder5.valueSeparator(' ');
        org.apache.commons.cli.Option.Builder builder8 = builder7.deprecated();
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(builder1);
        org.junit.Assert.assertNotNull(deprecatedAttributes3);
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "" + "'", str4, "");
        org.junit.Assert.assertNotNull(builder5);
        org.junit.Assert.assertNotNull(builder7);
        org.junit.Assert.assertNotNull(builder8);
    }

    @Test
    public void test251() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test251");
        org.apache.commons.cli.OptionBuilder optionBuilder1 = org.apache.commons.cli.OptionBuilder.withValueSeparator('4');
        org.junit.Assert.assertNotNull(optionBuilder1);
    }

    @Test
    public void test252() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test252");
        java.lang.String[] strArray5 = new java.lang.String[] { "hi!", "arg" };
        java.util.ArrayList<java.lang.String> strList6 = new java.util.ArrayList<java.lang.String>();
        boolean boolean7 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList6, strArray5);
        org.apache.commons.cli.AmbiguousOptionException ambiguousOptionException8 = new org.apache.commons.cli.AmbiguousOptionException("hi!", (java.util.Collection<java.lang.String>) strList6);
        org.apache.commons.cli.AmbiguousOptionException ambiguousOptionException9 = new org.apache.commons.cli.AmbiguousOptionException("[--]", (java.util.Collection<java.lang.String>) strList6);
        org.apache.commons.cli.AmbiguousOptionException ambiguousOptionException10 = new org.apache.commons.cli.AmbiguousOptionException("arg", (java.util.Collection<java.lang.String>) strList6);
        org.junit.Assert.assertNotNull(strArray5);
        org.junit.Assert.assertArrayEquals(strArray5, new java.lang.String[] { "hi!", "arg" });
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
    }

    @Test
    public void test253() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test253");
        org.apache.commons.cli.Option.Builder builder0 = org.apache.commons.cli.Option.builder();
        org.apache.commons.cli.Option.Builder builder1 = builder0.deprecated();
        org.apache.commons.cli.DeprecatedAttributes.Builder builder2 = new org.apache.commons.cli.DeprecatedAttributes.Builder();
        org.apache.commons.cli.DeprecatedAttributes deprecatedAttributes3 = builder2.get();
        java.lang.String str4 = deprecatedAttributes3.getSince();
        org.apache.commons.cli.Option.Builder builder5 = builder0.deprecated(deprecatedAttributes3);
        java.lang.String str6 = deprecatedAttributes3.toString();
        java.lang.String str7 = deprecatedAttributes3.getSince();
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(builder1);
        org.junit.Assert.assertNotNull(deprecatedAttributes3);
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "" + "'", str4, "");
        org.junit.Assert.assertNotNull(builder5);
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "Deprecated" + "'", str6, "Deprecated");
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "" + "'", str7, "");
    }

    @Test
    public void test254() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test254");
        org.apache.commons.cli.CommandLine.Builder builder0 = new org.apache.commons.cli.CommandLine.Builder();
        org.apache.commons.cli.CommandLine commandLine1 = builder0.build();
        org.apache.commons.cli.CommandLine commandLine2 = builder0.build();
        java.lang.Object obj4 = commandLine2.getParsedOptionValue("usage: ");
        org.junit.Assert.assertNotNull(commandLine1);
        org.junit.Assert.assertNotNull(commandLine2);
        org.junit.Assert.assertNull(obj4);
    }

    @Test
    public void test255() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test255");
        org.apache.commons.cli.Option.Builder builder0 = org.apache.commons.cli.Option.builder();
        org.apache.commons.cli.Option.Builder builder1 = builder0.deprecated();
        org.apache.commons.cli.Option.Builder builder3 = builder0.optionalArg(false);
        org.apache.commons.cli.Option.Builder builder5 = builder3.required(true);
        org.apache.commons.cli.Option.Builder builder7 = builder3.desc("Deprecated");
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(builder1);
        org.junit.Assert.assertNotNull(builder3);
        org.junit.Assert.assertNotNull(builder5);
        org.junit.Assert.assertNotNull(builder7);
    }

    @Test
    public void test256() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test256");
        org.apache.commons.cli.Options options0 = new org.apache.commons.cli.Options();
        org.apache.commons.cli.OptionGroup optionGroup1 = new org.apache.commons.cli.OptionGroup();
        java.util.Collection<org.apache.commons.cli.Option> optionCollection2 = optionGroup1.getOptions();
        boolean boolean3 = optionGroup1.isSelected();
        org.apache.commons.cli.Options options4 = options0.addOptionGroup(optionGroup1);
        org.apache.commons.cli.CommandLine.Builder builder5 = new org.apache.commons.cli.CommandLine.Builder();
        org.apache.commons.cli.CommandLine commandLine6 = builder5.build();
        org.apache.commons.cli.CommandLine commandLine7 = builder5.build();
        org.apache.commons.cli.OptionGroup optionGroup8 = new org.apache.commons.cli.OptionGroup();
        java.util.function.Supplier<org.apache.commons.cli.CommandLine> commandLineSupplier9 = null;
        org.apache.commons.cli.CommandLine commandLine10 = commandLine7.getParsedOptionValue(optionGroup8, commandLineSupplier9);
        org.apache.commons.cli.Options options11 = options0.addOptionGroup(optionGroup8);
        // The following exception was thrown during execution in test generation
        try {
            org.apache.commons.cli.Options options14 = options0.addOption("hi!", "[]");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: The option 'hi!' contains an illegal character : '!'.");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(optionCollection2);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
        org.junit.Assert.assertNotNull(options4);
        org.junit.Assert.assertNotNull(commandLine6);
        org.junit.Assert.assertNotNull(commandLine7);
        org.junit.Assert.assertNull(commandLine10);
        org.junit.Assert.assertNotNull(options11);
    }

    @Test
    public void test257() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test257");
        org.apache.commons.cli.GnuParser gnuParser0 = new org.apache.commons.cli.GnuParser();
        org.apache.commons.cli.DefaultParser.Builder builder1 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser2 = builder1.get();
        org.apache.commons.cli.Options options3 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray7 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties8 = null;
        org.apache.commons.cli.CommandLine commandLine10 = defaultParser2.parse(options3, strArray7, properties8, false);
        org.apache.commons.cli.DefaultParser.Builder builder11 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser12 = builder11.get();
        org.apache.commons.cli.Options options13 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray17 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties18 = null;
        org.apache.commons.cli.CommandLine commandLine20 = defaultParser12.parse(options13, strArray17, properties18, false);
        org.apache.commons.cli.DefaultParser.Builder builder21 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser22 = builder21.get();
        org.apache.commons.cli.Options options23 = new org.apache.commons.cli.Options();
        org.apache.commons.cli.DefaultParser.Builder builder24 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser25 = builder24.get();
        org.apache.commons.cli.Options options26 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray30 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties31 = null;
        org.apache.commons.cli.CommandLine commandLine33 = defaultParser25.parse(options26, strArray30, properties31, false);
        org.apache.commons.cli.DefaultParser.Builder builder34 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser35 = builder34.get();
        org.apache.commons.cli.Options options36 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray40 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties41 = null;
        org.apache.commons.cli.CommandLine commandLine43 = defaultParser35.parse(options36, strArray40, properties41, false);
        java.lang.Object obj45 = commandLine43.getOptionObject('4');
        org.apache.commons.cli.Option option46 = null;
        java.util.Properties properties47 = commandLine43.getOptionProperties(option46);
        org.apache.commons.cli.CommandLine commandLine49 = defaultParser22.parse(options23, strArray30, properties47, false);
        org.apache.commons.cli.DefaultParser.Builder builder50 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser51 = builder50.get();
        org.apache.commons.cli.Options options52 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray56 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties57 = null;
        org.apache.commons.cli.CommandLine commandLine59 = defaultParser51.parse(options52, strArray56, properties57, false);
        java.lang.Object obj61 = commandLine59.getOptionObject('4');
        java.util.Properties properties63 = commandLine59.getOptionProperties("--");
        org.apache.commons.cli.CommandLine commandLine64 = defaultParser2.parse(options13, strArray30, properties63);
        java.lang.String[] strArray70 = new java.lang.String[] { "[--]", "--", "[]", "[--]", "-" };
        org.apache.commons.cli.CommandLine commandLine72 = gnuParser0.parse(options13, strArray70, false);
        boolean boolean74 = options13.hasShortOption("arg");
        boolean boolean76 = options13.hasOption("[--]");
        org.junit.Assert.assertNotNull(builder1);
        org.junit.Assert.assertNotNull(defaultParser2);
        org.junit.Assert.assertNotNull(strArray7);
        org.junit.Assert.assertArrayEquals(strArray7, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine10);
        org.junit.Assert.assertNotNull(builder11);
        org.junit.Assert.assertNotNull(defaultParser12);
        org.junit.Assert.assertNotNull(strArray17);
        org.junit.Assert.assertArrayEquals(strArray17, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine20);
        org.junit.Assert.assertNotNull(builder21);
        org.junit.Assert.assertNotNull(defaultParser22);
        org.junit.Assert.assertNotNull(builder24);
        org.junit.Assert.assertNotNull(defaultParser25);
        org.junit.Assert.assertNotNull(strArray30);
        org.junit.Assert.assertArrayEquals(strArray30, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine33);
        org.junit.Assert.assertNotNull(builder34);
        org.junit.Assert.assertNotNull(defaultParser35);
        org.junit.Assert.assertNotNull(strArray40);
        org.junit.Assert.assertArrayEquals(strArray40, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine43);
        org.junit.Assert.assertNull(obj45);
        org.junit.Assert.assertNotNull(properties47);
        org.junit.Assert.assertNotNull(commandLine49);
        org.junit.Assert.assertNotNull(builder50);
        org.junit.Assert.assertNotNull(defaultParser51);
        org.junit.Assert.assertNotNull(strArray56);
        org.junit.Assert.assertArrayEquals(strArray56, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine59);
        org.junit.Assert.assertNull(obj61);
        org.junit.Assert.assertNotNull(properties63);
        org.junit.Assert.assertNotNull(commandLine64);
        org.junit.Assert.assertNotNull(strArray70);
        org.junit.Assert.assertArrayEquals(strArray70, new java.lang.String[] { "[--]", "--", "[]", "[--]", "-" });
        org.junit.Assert.assertNotNull(commandLine72);
        org.junit.Assert.assertTrue("'" + boolean74 + "' != '" + false + "'", boolean74 == false);
        org.junit.Assert.assertTrue("'" + boolean76 + "' != '" + false + "'", boolean76 == false);
    }

    @Test
    public void test258() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test258");
        java.lang.String[] strArray3 = new java.lang.String[] { "hi!", "arg" };
        java.util.ArrayList<java.lang.String> strList4 = new java.util.ArrayList<java.lang.String>();
        boolean boolean5 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList4, strArray3);
        org.apache.commons.cli.AmbiguousOptionException ambiguousOptionException6 = new org.apache.commons.cli.AmbiguousOptionException("hi!", (java.util.Collection<java.lang.String>) strList4);
        org.apache.commons.cli.MissingOptionException missingOptionException7 = new org.apache.commons.cli.MissingOptionException((java.util.List) strList4);
        org.junit.Assert.assertNotNull(strArray3);
        org.junit.Assert.assertArrayEquals(strArray3, new java.lang.String[] { "hi!", "arg" });
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
    }

    @Test
    public void test259() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test259");
        org.apache.commons.cli.Option option1 = org.apache.commons.cli.OptionBuilder.create("arg");
        option1.setValueSeparator('a');
        java.lang.String str5 = option1.getValue("[ Option arg [ARG...] :: null :: class java.lang.String ]");
        org.junit.Assert.assertNotNull(option1);
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "[ Option arg [ARG...] :: null :: class java.lang.String ]" + "'", str5, "[ Option arg [ARG...] :: null :: class java.lang.String ]");
    }

    @Test
    public void test260() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test260");
        org.apache.commons.cli.CommandLine.Builder builder0 = new org.apache.commons.cli.CommandLine.Builder();
        org.apache.commons.cli.CommandLine commandLine1 = builder0.build();
        org.apache.commons.cli.Option option2 = null;
        org.apache.commons.cli.CommandLine.Builder builder3 = builder0.addOption(option2);
        org.apache.commons.cli.DefaultParser.Builder builder4 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser5 = builder4.get();
        org.apache.commons.cli.Options options6 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray10 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties11 = null;
        org.apache.commons.cli.CommandLine commandLine13 = defaultParser5.parse(options6, strArray10, properties11, false);
        org.apache.commons.cli.DefaultParser.Builder builder15 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser16 = builder15.get();
        org.apache.commons.cli.Options options17 = new org.apache.commons.cli.Options();
        org.apache.commons.cli.DefaultParser.Builder builder18 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser19 = builder18.get();
        org.apache.commons.cli.Options options20 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray24 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties25 = null;
        org.apache.commons.cli.CommandLine commandLine27 = defaultParser19.parse(options20, strArray24, properties25, false);
        org.apache.commons.cli.DefaultParser.Builder builder28 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser29 = builder28.get();
        org.apache.commons.cli.Options options30 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray34 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties35 = null;
        org.apache.commons.cli.CommandLine commandLine37 = defaultParser29.parse(options30, strArray34, properties35, false);
        java.lang.Object obj39 = commandLine37.getOptionObject('4');
        org.apache.commons.cli.Option option40 = null;
        java.util.Properties properties41 = commandLine37.getOptionProperties(option40);
        org.apache.commons.cli.CommandLine commandLine43 = defaultParser16.parse(options17, strArray24, properties41, false);
        org.apache.commons.cli.CommandLine.Builder builder45 = new org.apache.commons.cli.CommandLine.Builder();
        org.apache.commons.cli.CommandLine commandLine46 = commandLine43.getParsedOptionValue(' ', (java.util.function.Supplier<org.apache.commons.cli.CommandLine>) builder45);
        org.apache.commons.cli.Option option47 = null;
        org.apache.commons.cli.CommandLine.Builder builder48 = builder45.addOption(option47);
        org.apache.commons.cli.CommandLine commandLine49 = commandLine13.getParsedOptionValue('#', (java.util.function.Supplier<org.apache.commons.cli.CommandLine>) builder45);
        org.apache.commons.cli.Option option51 = org.apache.commons.cli.OptionBuilder.create("arg");
        option51.setRequired(true);
        java.lang.Object obj54 = option51.getType();
        org.apache.commons.cli.CommandLine.Builder builder55 = builder45.addOption(option51);
        org.apache.commons.cli.CommandLine.Builder builder56 = builder0.addOption(option51);
        org.junit.Assert.assertNotNull(commandLine1);
        org.junit.Assert.assertNotNull(builder3);
        org.junit.Assert.assertNotNull(builder4);
        org.junit.Assert.assertNotNull(defaultParser5);
        org.junit.Assert.assertNotNull(strArray10);
        org.junit.Assert.assertArrayEquals(strArray10, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine13);
        org.junit.Assert.assertNotNull(builder15);
        org.junit.Assert.assertNotNull(defaultParser16);
        org.junit.Assert.assertNotNull(builder18);
        org.junit.Assert.assertNotNull(defaultParser19);
        org.junit.Assert.assertNotNull(strArray24);
        org.junit.Assert.assertArrayEquals(strArray24, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine27);
        org.junit.Assert.assertNotNull(builder28);
        org.junit.Assert.assertNotNull(defaultParser29);
        org.junit.Assert.assertNotNull(strArray34);
        org.junit.Assert.assertArrayEquals(strArray34, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine37);
        org.junit.Assert.assertNull(obj39);
        org.junit.Assert.assertNotNull(properties41);
        org.junit.Assert.assertNotNull(commandLine43);
        org.junit.Assert.assertNotNull(commandLine46);
        org.junit.Assert.assertNotNull(builder48);
        org.junit.Assert.assertNotNull(commandLine49);
        org.junit.Assert.assertNotNull(option51);
        org.junit.Assert.assertNotNull(obj54);
        org.junit.Assert.assertEquals(obj54.toString(), "class java.lang.String");
        org.junit.Assert.assertEquals(java.lang.String.valueOf(obj54), "class java.lang.String");
        org.junit.Assert.assertEquals(java.util.Objects.toString(obj54), "class java.lang.String");
        org.junit.Assert.assertNotNull(builder55);
        org.junit.Assert.assertNotNull(builder56);
    }

    @Test
    public void test261() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test261");
        java.lang.Appendable appendable0 = null;
        org.apache.commons.cli.help.TextHelpAppendable textHelpAppendable1 = new org.apache.commons.cli.help.TextHelpAppendable(appendable0);
        java.lang.CharSequence charSequence2 = null;
        textHelpAppendable1.appendParagraph(charSequence2);
        org.apache.commons.cli.help.TextHelpAppendable textHelpAppendable4 = new org.apache.commons.cli.help.TextHelpAppendable((java.lang.Appendable) textHelpAppendable1);
        java.lang.CharSequence[] charSequenceArray20 = new java.lang.CharSequence[] { "", "arg", "arg", " | ", "", "[ Option arg :: null :: class java.lang.String ]", "hi!", " | ", "[ Options: [ short {} ] [ long {} ]", "[--]", "Deprecated", "", ", ", "usage: " };
        java.util.ArrayList<java.lang.CharSequence> charSequenceList21 = new java.util.ArrayList<java.lang.CharSequence>();
        boolean boolean22 = java.util.Collections.addAll((java.util.Collection<java.lang.CharSequence>) charSequenceList21, charSequenceArray20);
        // The following exception was thrown during execution in test generation
        try {
            textHelpAppendable1.appendList(false, (java.util.Collection<java.lang.CharSequence>) charSequenceList21);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(charSequenceArray20);
        org.junit.Assert.assertTrue("'" + boolean22 + "' != '" + true + "'", boolean22 == true);
    }

    @Test
    public void test262() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test262");
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj1 = org.apache.commons.cli.TypeHandler.createObject("[ Option arg [ARG...] :: null :: class java.lang.String ]");
            org.junit.Assert.fail("Expected exception of type org.apache.commons.cli.ParseException; message: java.lang.ClassNotFoundException: [ Option arg [ARG///] :: null :: class java/lang/String ]");
        } catch (org.apache.commons.cli.ParseException e) {
            // Expected exception.
        }
    }

    @Test
    public void test263() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test263");
        org.apache.commons.cli.DefaultParser.Builder builder0 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser1 = builder0.get();
        org.apache.commons.cli.Options options2 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties7 = null;
        org.apache.commons.cli.CommandLine commandLine9 = defaultParser1.parse(options2, strArray6, properties7, false);
        java.lang.Object obj11 = commandLine9.getOptionObject('4');
        org.apache.commons.cli.OptionBuilder optionBuilder13 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs();
        org.apache.commons.cli.OptionBuilder optionBuilder15 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs((int) (byte) 1);
        org.apache.commons.cli.OptionBuilder optionBuilder17 = org.apache.commons.cli.OptionBuilder.withLongOpt("");
        org.apache.commons.cli.OptionBuilder optionBuilder19 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs((int) (byte) 1);
        org.apache.commons.cli.OptionBuilder optionBuilder20 = org.apache.commons.cli.OptionBuilder.hasArgs();
        org.apache.commons.cli.OptionBuilder optionBuilder21 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs();
        org.apache.commons.cli.OptionBuilder[] optionBuilderArray22 = new org.apache.commons.cli.OptionBuilder[] { optionBuilder13, optionBuilder15, optionBuilder17, optionBuilder19, optionBuilder20, optionBuilder21 };
        org.apache.commons.cli.OptionBuilder[] optionBuilderArray23 = commandLine9.getParsedOptionValues('#', optionBuilderArray22);
        java.util.function.Supplier<java.lang.String> strSupplier25 = null;
        java.lang.String str26 = commandLine9.getOptionValue('a', strSupplier25);
        java.lang.String str28 = commandLine9.getOptionValue('#');
        org.apache.commons.cli.DefaultParser.Builder builder29 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser30 = builder29.get();
        org.apache.commons.cli.Options options31 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray35 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties36 = null;
        org.apache.commons.cli.CommandLine commandLine38 = defaultParser30.parse(options31, strArray35, properties36, false);
        java.lang.Object obj40 = commandLine38.getOptionObject('4');
        org.apache.commons.cli.OptionGroup optionGroup41 = null;
        java.lang.String str43 = commandLine38.getOptionValue(optionGroup41, "[--]");
        java.util.function.Supplier<java.lang.String> strSupplier45 = null;
        java.lang.String str46 = commandLine38.getOptionValue("hi!", strSupplier45);
        org.apache.commons.cli.Option option48 = org.apache.commons.cli.OptionBuilder.create("arg");
        java.util.List<java.lang.String> strList49 = option48.getValuesList();
        boolean boolean50 = commandLine38.hasOption(option48);
        java.lang.String str52 = commandLine9.getOptionValue(option48, "hi!");
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(defaultParser1);
        org.junit.Assert.assertNotNull(strArray6);
        org.junit.Assert.assertArrayEquals(strArray6, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine9);
        org.junit.Assert.assertNull(obj11);
        org.junit.Assert.assertNotNull(optionBuilder13);
        org.junit.Assert.assertNotNull(optionBuilder15);
        org.junit.Assert.assertNotNull(optionBuilder17);
        org.junit.Assert.assertNotNull(optionBuilder19);
        org.junit.Assert.assertNotNull(optionBuilder20);
        org.junit.Assert.assertNotNull(optionBuilder21);
        org.junit.Assert.assertNotNull(optionBuilderArray22);
        org.junit.Assert.assertNotNull(optionBuilderArray23);
        org.junit.Assert.assertNull(str26);
        org.junit.Assert.assertNull(str28);
        org.junit.Assert.assertNotNull(builder29);
        org.junit.Assert.assertNotNull(defaultParser30);
        org.junit.Assert.assertNotNull(strArray35);
        org.junit.Assert.assertArrayEquals(strArray35, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine38);
        org.junit.Assert.assertNull(obj40);
        org.junit.Assert.assertEquals("'" + str43 + "' != '" + "[--]" + "'", str43, "[--]");
        org.junit.Assert.assertNull(str46);
        org.junit.Assert.assertNotNull(option48);
        org.junit.Assert.assertNotNull(strList49);
        org.junit.Assert.assertTrue("'" + boolean50 + "' != '" + false + "'", boolean50 == false);
        org.junit.Assert.assertEquals("'" + str52 + "' != '" + "hi!" + "'", str52, "hi!");
    }

    @Test
    public void test264() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test264");
        org.apache.commons.cli.Options options1 = org.apache.commons.cli.PatternOptionBuilder.parsePattern("Deprecated");
        org.junit.Assert.assertNotNull(options1);
    }

    @Test
    public void test265() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test265");
        org.apache.commons.cli.Option option1 = org.apache.commons.cli.OptionBuilder.create("arg");
        java.lang.String str3 = option1.getValue("[--]");
        org.apache.commons.cli.help.OptionFormatter.Builder builder4 = org.apache.commons.cli.help.OptionFormatter.builder();
        org.apache.commons.cli.help.OptionFormatter.Builder builder6 = builder4.setOptArgSeparator("");
        org.apache.commons.cli.help.OptionFormatter optionFormatter7 = builder4.get();
        option1.setType((java.lang.Object) optionFormatter7);
        boolean boolean9 = option1.hasValueSeparator();
        org.junit.Assert.assertNotNull(option1);
        org.junit.Assert.assertEquals("'" + str3 + "' != '" + "[--]" + "'", str3, "[--]");
        org.junit.Assert.assertNotNull(builder4);
        org.junit.Assert.assertNotNull(builder6);
        org.junit.Assert.assertNull(optionFormatter7);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
    }

    @Test
    public void test266() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test266");
        org.apache.commons.cli.Option.Builder builder0 = org.apache.commons.cli.Option.builder();
        org.apache.commons.cli.Option.Builder builder1 = builder0.deprecated();
        org.apache.commons.cli.Option.Builder builder3 = builder0.numberOfArgs((int) (short) -1);
        java.lang.Class<java.net.URL> uRLClass4 = org.apache.commons.cli.PatternOptionBuilder.URL_VALUE;
        org.apache.commons.cli.Option.Builder builder5 = builder0.type(uRLClass4);
        org.apache.commons.cli.Option.Builder builder7 = builder5.argName("[--]");
        org.apache.commons.cli.Option.Builder builder9 = builder7.required(false);
        org.apache.commons.cli.Option.Builder builder10 = builder7.deprecated();
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(builder1);
        org.junit.Assert.assertNotNull(builder3);
        org.junit.Assert.assertNotNull(uRLClass4);
        org.junit.Assert.assertNotNull(builder5);
        org.junit.Assert.assertNotNull(builder7);
        org.junit.Assert.assertNotNull(builder9);
        org.junit.Assert.assertNotNull(builder10);
    }

    @Test
    public void test267() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test267");
        org.apache.commons.cli.Option option1 = org.apache.commons.cli.OptionBuilder.create("arg");
        boolean boolean2 = option1.hasValueSeparator();
        java.lang.String str4 = option1.getValue((int) (short) 1);
        org.junit.Assert.assertNotNull(option1);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNull(str4);
    }

    @Test
    public void test268() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test268");
        org.apache.commons.cli.HelpFormatter.Builder builder0 = new org.apache.commons.cli.HelpFormatter.Builder();
        org.apache.commons.cli.HelpFormatter.Builder builder2 = builder0.setShowSince(false);
        org.apache.commons.cli.HelpFormatter helpFormatter3 = builder0.get();
        java.util.function.Function<org.apache.commons.cli.Option, java.lang.String> optionFunction4 = org.apache.commons.cli.help.OptionFormatter.NO_DEPRECATED_FORMAT;
        org.apache.commons.cli.HelpFormatter.Builder builder5 = builder0.setShowDeprecated(optionFunction4);
        java.io.PrintWriter printWriter6 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.apache.commons.cli.HelpFormatter.Builder builder7 = builder5.setPrintWriter(printWriter6);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: printWriter");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(builder2);
        org.junit.Assert.assertNotNull(helpFormatter3);
        org.junit.Assert.assertNotNull(optionFunction4);
        org.junit.Assert.assertNotNull(builder5);
    }

    @Test
    public void test269() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test269");
        org.apache.commons.cli.MissingOptionException missingOptionException1 = new org.apache.commons.cli.MissingOptionException(" | ");
        java.util.List list2 = missingOptionException1.getMissingOptions();
        org.junit.Assert.assertNull(list2);
    }

    @Test
    public void test270() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test270");
        org.apache.commons.cli.DefaultParser.Builder builder0 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser1 = builder0.get();
        org.apache.commons.cli.Options options2 = new org.apache.commons.cli.Options();
        org.apache.commons.cli.DefaultParser.Builder builder3 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser4 = builder3.get();
        org.apache.commons.cli.Options options5 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray9 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties10 = null;
        org.apache.commons.cli.CommandLine commandLine12 = defaultParser4.parse(options5, strArray9, properties10, false);
        org.apache.commons.cli.DefaultParser.Builder builder13 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser14 = builder13.get();
        org.apache.commons.cli.Options options15 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray19 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties20 = null;
        org.apache.commons.cli.CommandLine commandLine22 = defaultParser14.parse(options15, strArray19, properties20, false);
        java.lang.Object obj24 = commandLine22.getOptionObject('4');
        org.apache.commons.cli.Option option25 = null;
        java.util.Properties properties26 = commandLine22.getOptionProperties(option25);
        org.apache.commons.cli.CommandLine commandLine28 = defaultParser1.parse(options2, strArray9, properties26, false);
        org.apache.commons.cli.CommandLine.Builder builder30 = new org.apache.commons.cli.CommandLine.Builder();
        org.apache.commons.cli.CommandLine commandLine31 = commandLine28.getParsedOptionValue(' ', (java.util.function.Supplier<org.apache.commons.cli.CommandLine>) builder30);
        org.apache.commons.cli.Option option32 = null;
        java.util.Properties properties33 = commandLine28.getOptionProperties(option32);
        org.apache.commons.cli.DefaultParser.Builder builder34 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser35 = builder34.get();
        org.apache.commons.cli.Options options36 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray40 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties41 = null;
        org.apache.commons.cli.CommandLine commandLine43 = defaultParser35.parse(options36, strArray40, properties41, false);
        java.lang.Object obj45 = commandLine43.getOptionObject('4');
        org.apache.commons.cli.OptionBuilder optionBuilder47 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs();
        org.apache.commons.cli.OptionBuilder optionBuilder49 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs((int) (byte) 1);
        org.apache.commons.cli.OptionBuilder optionBuilder51 = org.apache.commons.cli.OptionBuilder.withLongOpt("");
        org.apache.commons.cli.OptionBuilder optionBuilder53 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs((int) (byte) 1);
        org.apache.commons.cli.OptionBuilder optionBuilder54 = org.apache.commons.cli.OptionBuilder.hasArgs();
        org.apache.commons.cli.OptionBuilder optionBuilder55 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs();
        org.apache.commons.cli.OptionBuilder[] optionBuilderArray56 = new org.apache.commons.cli.OptionBuilder[] { optionBuilder47, optionBuilder49, optionBuilder51, optionBuilder53, optionBuilder54, optionBuilder55 };
        org.apache.commons.cli.OptionBuilder[] optionBuilderArray57 = commandLine43.getParsedOptionValues('#', optionBuilderArray56);
        org.apache.commons.cli.OptionGroup optionGroup58 = new org.apache.commons.cli.OptionGroup();
        boolean boolean59 = optionGroup58.isRequired();
        java.lang.String str61 = commandLine43.getOptionValue(optionGroup58, "");
        org.apache.commons.cli.DefaultParser.NonOptionAction nonOptionAction62 = org.apache.commons.cli.DefaultParser.NonOptionAction.STOP;
        org.apache.commons.cli.DefaultParser.NonOptionAction nonOptionAction63 = org.apache.commons.cli.DefaultParser.NonOptionAction.THROW;
        org.apache.commons.cli.DefaultParser.NonOptionAction nonOptionAction64 = org.apache.commons.cli.DefaultParser.NonOptionAction.STOP;
        org.apache.commons.cli.DefaultParser.NonOptionAction nonOptionAction65 = org.apache.commons.cli.DefaultParser.NonOptionAction.IGNORE;
        org.apache.commons.cli.DefaultParser.NonOptionAction nonOptionAction66 = org.apache.commons.cli.DefaultParser.NonOptionAction.IGNORE;
        org.apache.commons.cli.DefaultParser.NonOptionAction[] nonOptionActionArray67 = new org.apache.commons.cli.DefaultParser.NonOptionAction[] { nonOptionAction62, nonOptionAction63, nonOptionAction64, nonOptionAction65, nonOptionAction66 };
        org.apache.commons.cli.DefaultParser.NonOptionAction[] nonOptionActionArray68 = commandLine28.getParsedOptionValues(optionGroup58, nonOptionActionArray67);
        org.apache.commons.cli.Option option70 = org.apache.commons.cli.OptionBuilder.create("arg");
        option70.setRequired(true);
        boolean boolean73 = option70.hasArgName();
        boolean boolean74 = commandLine28.hasOption(option70);
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(defaultParser1);
        org.junit.Assert.assertNotNull(builder3);
        org.junit.Assert.assertNotNull(defaultParser4);
        org.junit.Assert.assertNotNull(strArray9);
        org.junit.Assert.assertArrayEquals(strArray9, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine12);
        org.junit.Assert.assertNotNull(builder13);
        org.junit.Assert.assertNotNull(defaultParser14);
        org.junit.Assert.assertNotNull(strArray19);
        org.junit.Assert.assertArrayEquals(strArray19, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine22);
        org.junit.Assert.assertNull(obj24);
        org.junit.Assert.assertNotNull(properties26);
        org.junit.Assert.assertNotNull(commandLine28);
        org.junit.Assert.assertNotNull(commandLine31);
        org.junit.Assert.assertNotNull(properties33);
        org.junit.Assert.assertNotNull(builder34);
        org.junit.Assert.assertNotNull(defaultParser35);
        org.junit.Assert.assertNotNull(strArray40);
        org.junit.Assert.assertArrayEquals(strArray40, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine43);
        org.junit.Assert.assertNull(obj45);
        org.junit.Assert.assertNotNull(optionBuilder47);
        org.junit.Assert.assertNotNull(optionBuilder49);
        org.junit.Assert.assertNotNull(optionBuilder51);
        org.junit.Assert.assertNotNull(optionBuilder53);
        org.junit.Assert.assertNotNull(optionBuilder54);
        org.junit.Assert.assertNotNull(optionBuilder55);
        org.junit.Assert.assertNotNull(optionBuilderArray56);
        org.junit.Assert.assertNotNull(optionBuilderArray57);
        org.junit.Assert.assertTrue("'" + boolean59 + "' != '" + false + "'", boolean59 == false);
        org.junit.Assert.assertEquals("'" + str61 + "' != '" + "" + "'", str61, "");
        org.junit.Assert.assertTrue("'" + nonOptionAction62 + "' != '" + org.apache.commons.cli.DefaultParser.NonOptionAction.STOP + "'", nonOptionAction62.equals(org.apache.commons.cli.DefaultParser.NonOptionAction.STOP));
        org.junit.Assert.assertTrue("'" + nonOptionAction63 + "' != '" + org.apache.commons.cli.DefaultParser.NonOptionAction.THROW + "'", nonOptionAction63.equals(org.apache.commons.cli.DefaultParser.NonOptionAction.THROW));
        org.junit.Assert.assertTrue("'" + nonOptionAction64 + "' != '" + org.apache.commons.cli.DefaultParser.NonOptionAction.STOP + "'", nonOptionAction64.equals(org.apache.commons.cli.DefaultParser.NonOptionAction.STOP));
        org.junit.Assert.assertTrue("'" + nonOptionAction65 + "' != '" + org.apache.commons.cli.DefaultParser.NonOptionAction.IGNORE + "'", nonOptionAction65.equals(org.apache.commons.cli.DefaultParser.NonOptionAction.IGNORE));
        org.junit.Assert.assertTrue("'" + nonOptionAction66 + "' != '" + org.apache.commons.cli.DefaultParser.NonOptionAction.IGNORE + "'", nonOptionAction66.equals(org.apache.commons.cli.DefaultParser.NonOptionAction.IGNORE));
        org.junit.Assert.assertNotNull(nonOptionActionArray67);
        org.junit.Assert.assertArrayEquals(nonOptionActionArray67, new org.apache.commons.cli.DefaultParser.NonOptionAction[] { org.apache.commons.cli.DefaultParser.NonOptionAction.STOP, org.apache.commons.cli.DefaultParser.NonOptionAction.THROW, org.apache.commons.cli.DefaultParser.NonOptionAction.STOP, org.apache.commons.cli.DefaultParser.NonOptionAction.IGNORE, org.apache.commons.cli.DefaultParser.NonOptionAction.IGNORE });
        org.junit.Assert.assertNotNull(nonOptionActionArray68);
        org.junit.Assert.assertArrayEquals(nonOptionActionArray68, new org.apache.commons.cli.DefaultParser.NonOptionAction[] { org.apache.commons.cli.DefaultParser.NonOptionAction.STOP, org.apache.commons.cli.DefaultParser.NonOptionAction.THROW, org.apache.commons.cli.DefaultParser.NonOptionAction.STOP, org.apache.commons.cli.DefaultParser.NonOptionAction.IGNORE, org.apache.commons.cli.DefaultParser.NonOptionAction.IGNORE });
        org.junit.Assert.assertNotNull(option70);
        org.junit.Assert.assertTrue("'" + boolean73 + "' != '" + false + "'", boolean73 == false);
        org.junit.Assert.assertTrue("'" + boolean74 + "' != '" + false + "'", boolean74 == false);
    }

    @Test
    public void test271() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test271");
        org.apache.commons.cli.Option option1 = org.apache.commons.cli.OptionBuilder.create("arg");
        option1.setRequired(true);
        org.apache.commons.cli.DeprecatedAttributes deprecatedAttributes4 = option1.getDeprecated();
        org.junit.Assert.assertNotNull(option1);
        org.junit.Assert.assertNull(deprecatedAttributes4);
    }

    @Test
    public void test272() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test272");
        org.apache.commons.cli.DefaultParser.NonOptionAction nonOptionAction0 = org.apache.commons.cli.DefaultParser.NonOptionAction.SKIP;
        org.junit.Assert.assertTrue("'" + nonOptionAction0 + "' != '" + org.apache.commons.cli.DefaultParser.NonOptionAction.SKIP + "'", nonOptionAction0.equals(org.apache.commons.cli.DefaultParser.NonOptionAction.SKIP));
    }

    @Test
    public void test273() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test273");
        java.lang.Class<java.lang.Number> numberClass0 = org.apache.commons.cli.PatternOptionBuilder.NUMBER_VALUE;
        org.junit.Assert.assertNotNull(numberClass0);
    }

    @Test
    public void test274() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test274");
        org.apache.commons.cli.DefaultParser.Builder builder0 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser1 = builder0.get();
        org.apache.commons.cli.Options options2 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties7 = null;
        org.apache.commons.cli.CommandLine commandLine9 = defaultParser1.parse(options2, strArray6, properties7, false);
        java.lang.Object obj11 = commandLine9.getOptionObject('4');
        org.apache.commons.cli.OptionBuilder optionBuilder13 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs();
        org.apache.commons.cli.OptionBuilder optionBuilder15 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs((int) (byte) 1);
        org.apache.commons.cli.OptionBuilder optionBuilder17 = org.apache.commons.cli.OptionBuilder.withLongOpt("");
        org.apache.commons.cli.OptionBuilder optionBuilder19 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs((int) (byte) 1);
        org.apache.commons.cli.OptionBuilder optionBuilder20 = org.apache.commons.cli.OptionBuilder.hasArgs();
        org.apache.commons.cli.OptionBuilder optionBuilder21 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs();
        org.apache.commons.cli.OptionBuilder[] optionBuilderArray22 = new org.apache.commons.cli.OptionBuilder[] { optionBuilder13, optionBuilder15, optionBuilder17, optionBuilder19, optionBuilder20, optionBuilder21 };
        org.apache.commons.cli.OptionBuilder[] optionBuilderArray23 = commandLine9.getParsedOptionValues('#', optionBuilderArray22);
        java.util.function.Supplier<java.lang.String> strSupplier25 = null;
        java.lang.String str26 = commandLine9.getOptionValue('a', strSupplier25);
        java.lang.String str28 = commandLine9.getOptionValue('#');
        org.apache.commons.cli.CommandLine.Builder builder29 = new org.apache.commons.cli.CommandLine.Builder();
        org.apache.commons.cli.CommandLine commandLine30 = builder29.build();
        org.apache.commons.cli.CommandLine commandLine31 = builder29.build();
        org.apache.commons.cli.CommandLine commandLine32 = builder29.get();
        org.apache.commons.cli.Option option34 = org.apache.commons.cli.OptionBuilder.create("arg");
        java.lang.String str36 = option34.getValue("[--]");
        org.apache.commons.cli.help.OptionFormatter.Builder builder37 = org.apache.commons.cli.help.OptionFormatter.builder();
        org.apache.commons.cli.help.OptionFormatter.Builder builder39 = builder37.setOptArgSeparator("");
        org.apache.commons.cli.help.OptionFormatter optionFormatter40 = builder37.get();
        option34.setType((java.lang.Object) optionFormatter40);
        java.lang.String str42 = option34.getSince();
        org.apache.commons.cli.CommandLine.Builder builder43 = builder29.addOption(option34);
        org.apache.commons.cli.DefaultParser.Builder builder44 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser45 = builder44.get();
        org.apache.commons.cli.Options options46 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray50 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties51 = null;
        org.apache.commons.cli.CommandLine commandLine53 = defaultParser45.parse(options46, strArray50, properties51, false);
        org.apache.commons.cli.DefaultParser.Builder builder55 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser56 = builder55.get();
        org.apache.commons.cli.Options options57 = new org.apache.commons.cli.Options();
        org.apache.commons.cli.DefaultParser.Builder builder58 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser59 = builder58.get();
        org.apache.commons.cli.Options options60 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray64 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties65 = null;
        org.apache.commons.cli.CommandLine commandLine67 = defaultParser59.parse(options60, strArray64, properties65, false);
        org.apache.commons.cli.DefaultParser.Builder builder68 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser69 = builder68.get();
        org.apache.commons.cli.Options options70 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray74 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties75 = null;
        org.apache.commons.cli.CommandLine commandLine77 = defaultParser69.parse(options70, strArray74, properties75, false);
        java.lang.Object obj79 = commandLine77.getOptionObject('4');
        org.apache.commons.cli.Option option80 = null;
        java.util.Properties properties81 = commandLine77.getOptionProperties(option80);
        org.apache.commons.cli.CommandLine commandLine83 = defaultParser56.parse(options57, strArray64, properties81, false);
        org.apache.commons.cli.CommandLine.Builder builder85 = new org.apache.commons.cli.CommandLine.Builder();
        org.apache.commons.cli.CommandLine commandLine86 = commandLine83.getParsedOptionValue(' ', (java.util.function.Supplier<org.apache.commons.cli.CommandLine>) builder85);
        org.apache.commons.cli.Option option87 = null;
        org.apache.commons.cli.CommandLine.Builder builder88 = builder85.addOption(option87);
        org.apache.commons.cli.CommandLine commandLine89 = commandLine53.getParsedOptionValue('#', (java.util.function.Supplier<org.apache.commons.cli.CommandLine>) builder85);
        org.apache.commons.cli.Option option91 = org.apache.commons.cli.OptionBuilder.create("arg");
        option91.setRequired(true);
        java.lang.Object obj94 = option91.getType();
        org.apache.commons.cli.CommandLine.Builder builder95 = builder85.addOption(option91);
        org.apache.commons.cli.CommandLine commandLine96 = commandLine9.getParsedOptionValue(option34, (java.util.function.Supplier<org.apache.commons.cli.CommandLine>) builder95);
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(defaultParser1);
        org.junit.Assert.assertNotNull(strArray6);
        org.junit.Assert.assertArrayEquals(strArray6, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine9);
        org.junit.Assert.assertNull(obj11);
        org.junit.Assert.assertNotNull(optionBuilder13);
        org.junit.Assert.assertNotNull(optionBuilder15);
        org.junit.Assert.assertNotNull(optionBuilder17);
        org.junit.Assert.assertNotNull(optionBuilder19);
        org.junit.Assert.assertNotNull(optionBuilder20);
        org.junit.Assert.assertNotNull(optionBuilder21);
        org.junit.Assert.assertNotNull(optionBuilderArray22);
        org.junit.Assert.assertNotNull(optionBuilderArray23);
        org.junit.Assert.assertNull(str26);
        org.junit.Assert.assertNull(str28);
        org.junit.Assert.assertNotNull(commandLine30);
        org.junit.Assert.assertNotNull(commandLine31);
        org.junit.Assert.assertNotNull(commandLine32);
        org.junit.Assert.assertNotNull(option34);
        org.junit.Assert.assertEquals("'" + str36 + "' != '" + "[--]" + "'", str36, "[--]");
        org.junit.Assert.assertNotNull(builder37);
        org.junit.Assert.assertNotNull(builder39);
        org.junit.Assert.assertNull(optionFormatter40);
        org.junit.Assert.assertNull(str42);
        org.junit.Assert.assertNotNull(builder43);
        org.junit.Assert.assertNotNull(builder44);
        org.junit.Assert.assertNotNull(defaultParser45);
        org.junit.Assert.assertNotNull(strArray50);
        org.junit.Assert.assertArrayEquals(strArray50, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine53);
        org.junit.Assert.assertNotNull(builder55);
        org.junit.Assert.assertNotNull(defaultParser56);
        org.junit.Assert.assertNotNull(builder58);
        org.junit.Assert.assertNotNull(defaultParser59);
        org.junit.Assert.assertNotNull(strArray64);
        org.junit.Assert.assertArrayEquals(strArray64, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine67);
        org.junit.Assert.assertNotNull(builder68);
        org.junit.Assert.assertNotNull(defaultParser69);
        org.junit.Assert.assertNotNull(strArray74);
        org.junit.Assert.assertArrayEquals(strArray74, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine77);
        org.junit.Assert.assertNull(obj79);
        org.junit.Assert.assertNotNull(properties81);
        org.junit.Assert.assertNotNull(commandLine83);
        org.junit.Assert.assertNotNull(commandLine86);
        org.junit.Assert.assertNotNull(builder88);
        org.junit.Assert.assertNotNull(commandLine89);
        org.junit.Assert.assertNotNull(option91);
        org.junit.Assert.assertNotNull(obj94);
        org.junit.Assert.assertEquals(obj94.toString(), "class java.lang.String");
        org.junit.Assert.assertEquals(java.lang.String.valueOf(obj94), "class java.lang.String");
        org.junit.Assert.assertEquals(java.util.Objects.toString(obj94), "class java.lang.String");
        org.junit.Assert.assertNotNull(builder95);
        org.junit.Assert.assertNotNull(commandLine96);
    }

    @Test
    public void test275() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test275");
        org.apache.commons.cli.HelpFormatter helpFormatter0 = new org.apache.commons.cli.HelpFormatter();
        org.apache.commons.cli.Options options2 = new org.apache.commons.cli.Options();
        org.apache.commons.cli.OptionGroup optionGroup3 = new org.apache.commons.cli.OptionGroup();
        java.util.Collection<org.apache.commons.cli.Option> optionCollection4 = optionGroup3.getOptions();
        boolean boolean5 = optionGroup3.isSelected();
        org.apache.commons.cli.Options options6 = options2.addOptionGroup(optionGroup3);
        helpFormatter0.printHelp("usage: ", options2);
        helpFormatter0.setWidth((int) 'a');
        org.apache.commons.cli.HelpFormatter.Builder builder13 = new org.apache.commons.cli.HelpFormatter.Builder();
        org.apache.commons.cli.HelpFormatter.Builder builder15 = builder13.setShowSince(false);
        org.apache.commons.cli.HelpFormatter helpFormatter16 = builder13.get();
        helpFormatter16.defaultLeftPad = '#';
        org.apache.commons.cli.DefaultParser.Builder builder21 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser22 = builder21.get();
        org.apache.commons.cli.Options options23 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray27 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties28 = null;
        org.apache.commons.cli.CommandLine commandLine30 = defaultParser22.parse(options23, strArray27, properties28, false);
        helpFormatter16.printHelp(" ", ", ", options23, "Deprecated", true);
        helpFormatter0.printHelp((int) (byte) 10, " ", "usage: ", options23, " | ", true);
        org.junit.Assert.assertNotNull(optionCollection4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertNotNull(options6);
        org.junit.Assert.assertNotNull(builder15);
        org.junit.Assert.assertNotNull(helpFormatter16);
        org.junit.Assert.assertNotNull(builder21);
        org.junit.Assert.assertNotNull(defaultParser22);
        org.junit.Assert.assertNotNull(strArray27);
        org.junit.Assert.assertArrayEquals(strArray27, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine30);
    }

    @Test
    public void test276() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test276");
        org.apache.commons.cli.GnuParser gnuParser0 = new org.apache.commons.cli.GnuParser();
        org.apache.commons.cli.DefaultParser.Builder builder1 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser2 = builder1.get();
        org.apache.commons.cli.Options options3 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray7 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties8 = null;
        org.apache.commons.cli.CommandLine commandLine10 = defaultParser2.parse(options3, strArray7, properties8, false);
        org.apache.commons.cli.DefaultParser.Builder builder11 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser12 = builder11.get();
        org.apache.commons.cli.Options options13 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray17 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties18 = null;
        org.apache.commons.cli.CommandLine commandLine20 = defaultParser12.parse(options13, strArray17, properties18, false);
        org.apache.commons.cli.DefaultParser.Builder builder21 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser22 = builder21.get();
        org.apache.commons.cli.Options options23 = new org.apache.commons.cli.Options();
        org.apache.commons.cli.DefaultParser.Builder builder24 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser25 = builder24.get();
        org.apache.commons.cli.Options options26 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray30 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties31 = null;
        org.apache.commons.cli.CommandLine commandLine33 = defaultParser25.parse(options26, strArray30, properties31, false);
        org.apache.commons.cli.DefaultParser.Builder builder34 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser35 = builder34.get();
        org.apache.commons.cli.Options options36 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray40 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties41 = null;
        org.apache.commons.cli.CommandLine commandLine43 = defaultParser35.parse(options36, strArray40, properties41, false);
        java.lang.Object obj45 = commandLine43.getOptionObject('4');
        org.apache.commons.cli.Option option46 = null;
        java.util.Properties properties47 = commandLine43.getOptionProperties(option46);
        org.apache.commons.cli.CommandLine commandLine49 = defaultParser22.parse(options23, strArray30, properties47, false);
        org.apache.commons.cli.DefaultParser.Builder builder50 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser51 = builder50.get();
        org.apache.commons.cli.Options options52 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray56 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties57 = null;
        org.apache.commons.cli.CommandLine commandLine59 = defaultParser51.parse(options52, strArray56, properties57, false);
        java.lang.Object obj61 = commandLine59.getOptionObject('4');
        java.util.Properties properties63 = commandLine59.getOptionProperties("--");
        org.apache.commons.cli.CommandLine commandLine64 = defaultParser2.parse(options13, strArray30, properties63);
        java.lang.String[] strArray70 = new java.lang.String[] { "[--]", "--", "[]", "[--]", "-" };
        org.apache.commons.cli.CommandLine commandLine72 = gnuParser0.parse(options13, strArray70, false);
        java.util.List<?> wildcardList73 = options13.getRequiredOptions();
        // The following exception was thrown during execution in test generation
        try {
            org.apache.commons.cli.Options options78 = options13.addRequiredOption("", "", false, ", ");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Empty option name.");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(builder1);
        org.junit.Assert.assertNotNull(defaultParser2);
        org.junit.Assert.assertNotNull(strArray7);
        org.junit.Assert.assertArrayEquals(strArray7, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine10);
        org.junit.Assert.assertNotNull(builder11);
        org.junit.Assert.assertNotNull(defaultParser12);
        org.junit.Assert.assertNotNull(strArray17);
        org.junit.Assert.assertArrayEquals(strArray17, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine20);
        org.junit.Assert.assertNotNull(builder21);
        org.junit.Assert.assertNotNull(defaultParser22);
        org.junit.Assert.assertNotNull(builder24);
        org.junit.Assert.assertNotNull(defaultParser25);
        org.junit.Assert.assertNotNull(strArray30);
        org.junit.Assert.assertArrayEquals(strArray30, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine33);
        org.junit.Assert.assertNotNull(builder34);
        org.junit.Assert.assertNotNull(defaultParser35);
        org.junit.Assert.assertNotNull(strArray40);
        org.junit.Assert.assertArrayEquals(strArray40, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine43);
        org.junit.Assert.assertNull(obj45);
        org.junit.Assert.assertNotNull(properties47);
        org.junit.Assert.assertNotNull(commandLine49);
        org.junit.Assert.assertNotNull(builder50);
        org.junit.Assert.assertNotNull(defaultParser51);
        org.junit.Assert.assertNotNull(strArray56);
        org.junit.Assert.assertArrayEquals(strArray56, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine59);
        org.junit.Assert.assertNull(obj61);
        org.junit.Assert.assertNotNull(properties63);
        org.junit.Assert.assertNotNull(commandLine64);
        org.junit.Assert.assertNotNull(strArray70);
        org.junit.Assert.assertArrayEquals(strArray70, new java.lang.String[] { "[--]", "--", "[]", "[--]", "-" });
        org.junit.Assert.assertNotNull(commandLine72);
        org.junit.Assert.assertNotNull(wildcardList73);
    }

    @Test
    public void test277() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test277");
        org.apache.commons.cli.DefaultParser.Builder builder0 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser1 = builder0.get();
        org.apache.commons.cli.Options options2 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties7 = null;
        org.apache.commons.cli.CommandLine commandLine9 = defaultParser1.parse(options2, strArray6, properties7, false);
        java.lang.Object obj11 = commandLine9.getOptionObject('4');
        org.apache.commons.cli.OptionBuilder optionBuilder13 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs();
        org.apache.commons.cli.OptionBuilder optionBuilder15 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs((int) (byte) 1);
        org.apache.commons.cli.OptionBuilder optionBuilder17 = org.apache.commons.cli.OptionBuilder.withLongOpt("");
        org.apache.commons.cli.OptionBuilder optionBuilder19 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs((int) (byte) 1);
        org.apache.commons.cli.OptionBuilder optionBuilder20 = org.apache.commons.cli.OptionBuilder.hasArgs();
        org.apache.commons.cli.OptionBuilder optionBuilder21 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs();
        org.apache.commons.cli.OptionBuilder[] optionBuilderArray22 = new org.apache.commons.cli.OptionBuilder[] { optionBuilder13, optionBuilder15, optionBuilder17, optionBuilder19, optionBuilder20, optionBuilder21 };
        org.apache.commons.cli.OptionBuilder[] optionBuilderArray23 = commandLine9.getParsedOptionValues('#', optionBuilderArray22);
        java.util.ArrayList<java.lang.String>[] strListArray25 = commandLine9.getParsedOptionValues("[--]");
        java.lang.reflect.AnnotatedElement annotatedElement27 = commandLine9.getParsedOptionValue("--");
        org.apache.commons.cli.DefaultParser.Builder builder28 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser29 = builder28.get();
        org.apache.commons.cli.Options options30 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray34 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties35 = null;
        org.apache.commons.cli.CommandLine commandLine37 = defaultParser29.parse(options30, strArray34, properties35, false);
        java.lang.Object obj39 = commandLine37.getOptionObject('4');
        org.apache.commons.cli.OptionBuilder optionBuilder41 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs();
        org.apache.commons.cli.OptionBuilder optionBuilder43 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs((int) (byte) 1);
        org.apache.commons.cli.OptionBuilder optionBuilder45 = org.apache.commons.cli.OptionBuilder.withLongOpt("");
        org.apache.commons.cli.OptionBuilder optionBuilder47 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs((int) (byte) 1);
        org.apache.commons.cli.OptionBuilder optionBuilder48 = org.apache.commons.cli.OptionBuilder.hasArgs();
        org.apache.commons.cli.OptionBuilder optionBuilder49 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs();
        org.apache.commons.cli.OptionBuilder[] optionBuilderArray50 = new org.apache.commons.cli.OptionBuilder[] { optionBuilder41, optionBuilder43, optionBuilder45, optionBuilder47, optionBuilder48, optionBuilder49 };
        org.apache.commons.cli.OptionBuilder[] optionBuilderArray51 = commandLine37.getParsedOptionValues('#', optionBuilderArray50);
        org.apache.commons.cli.OptionGroup optionGroup52 = new org.apache.commons.cli.OptionGroup();
        boolean boolean53 = optionGroup52.isRequired();
        java.lang.String str55 = commandLine37.getOptionValue(optionGroup52, "");
        boolean boolean56 = optionGroup52.isRequired();
        org.apache.commons.cli.Option option58 = org.apache.commons.cli.OptionBuilder.create("arg");
        option58.setRequired(true);
        boolean boolean61 = option58.hasArgName();
        org.apache.commons.cli.OptionGroup optionGroup62 = optionGroup52.addOption(option58);
        java.lang.String str63 = option58.getKey();
        int int64 = commandLine9.getOptionCount(option58);
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(defaultParser1);
        org.junit.Assert.assertNotNull(strArray6);
        org.junit.Assert.assertArrayEquals(strArray6, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine9);
        org.junit.Assert.assertNull(obj11);
        org.junit.Assert.assertNotNull(optionBuilder13);
        org.junit.Assert.assertNotNull(optionBuilder15);
        org.junit.Assert.assertNotNull(optionBuilder17);
        org.junit.Assert.assertNotNull(optionBuilder19);
        org.junit.Assert.assertNotNull(optionBuilder20);
        org.junit.Assert.assertNotNull(optionBuilder21);
        org.junit.Assert.assertNotNull(optionBuilderArray22);
        org.junit.Assert.assertNotNull(optionBuilderArray23);
        org.junit.Assert.assertNull(strListArray25);
        org.junit.Assert.assertNull(annotatedElement27);
        org.junit.Assert.assertNotNull(builder28);
        org.junit.Assert.assertNotNull(defaultParser29);
        org.junit.Assert.assertNotNull(strArray34);
        org.junit.Assert.assertArrayEquals(strArray34, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine37);
        org.junit.Assert.assertNull(obj39);
        org.junit.Assert.assertNotNull(optionBuilder41);
        org.junit.Assert.assertNotNull(optionBuilder43);
        org.junit.Assert.assertNotNull(optionBuilder45);
        org.junit.Assert.assertNotNull(optionBuilder47);
        org.junit.Assert.assertNotNull(optionBuilder48);
        org.junit.Assert.assertNotNull(optionBuilder49);
        org.junit.Assert.assertNotNull(optionBuilderArray50);
        org.junit.Assert.assertNotNull(optionBuilderArray51);
        org.junit.Assert.assertTrue("'" + boolean53 + "' != '" + false + "'", boolean53 == false);
        org.junit.Assert.assertEquals("'" + str55 + "' != '" + "" + "'", str55, "");
        org.junit.Assert.assertTrue("'" + boolean56 + "' != '" + false + "'", boolean56 == false);
        org.junit.Assert.assertNotNull(option58);
        org.junit.Assert.assertTrue("'" + boolean61 + "' != '" + false + "'", boolean61 == false);
        org.junit.Assert.assertNotNull(optionGroup62);
        org.junit.Assert.assertEquals("'" + str63 + "' != '" + "arg" + "'", str63, "arg");
        org.junit.Assert.assertTrue("'" + int64 + "' != '" + 0 + "'", int64 == 0);
    }

    @Test
    public void test278() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test278");
        org.apache.commons.cli.DefaultParser.Builder builder0 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser1 = builder0.get();
        org.apache.commons.cli.Options options2 = new org.apache.commons.cli.Options();
        org.apache.commons.cli.DefaultParser.Builder builder3 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser4 = builder3.get();
        org.apache.commons.cli.Options options5 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray9 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties10 = null;
        org.apache.commons.cli.CommandLine commandLine12 = defaultParser4.parse(options5, strArray9, properties10, false);
        org.apache.commons.cli.DefaultParser.Builder builder13 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser14 = builder13.get();
        org.apache.commons.cli.Options options15 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray19 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties20 = null;
        org.apache.commons.cli.CommandLine commandLine22 = defaultParser14.parse(options15, strArray19, properties20, false);
        java.lang.Object obj24 = commandLine22.getOptionObject('4');
        org.apache.commons.cli.Option option25 = null;
        java.util.Properties properties26 = commandLine22.getOptionProperties(option25);
        org.apache.commons.cli.CommandLine commandLine28 = defaultParser1.parse(options2, strArray9, properties26, false);
        org.apache.commons.cli.CommandLine.Builder builder30 = new org.apache.commons.cli.CommandLine.Builder();
        org.apache.commons.cli.CommandLine commandLine31 = commandLine28.getParsedOptionValue(' ', (java.util.function.Supplier<org.apache.commons.cli.CommandLine>) builder30);
        org.apache.commons.cli.DefaultParser.Builder builder32 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser33 = builder32.get();
        org.apache.commons.cli.Options options34 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray38 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties39 = null;
        org.apache.commons.cli.CommandLine commandLine41 = defaultParser33.parse(options34, strArray38, properties39, false);
        java.lang.Object obj43 = commandLine41.getOptionObject('4');
        org.apache.commons.cli.OptionBuilder optionBuilder45 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs();
        org.apache.commons.cli.OptionBuilder optionBuilder47 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs((int) (byte) 1);
        org.apache.commons.cli.OptionBuilder optionBuilder49 = org.apache.commons.cli.OptionBuilder.withLongOpt("");
        org.apache.commons.cli.OptionBuilder optionBuilder51 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs((int) (byte) 1);
        org.apache.commons.cli.OptionBuilder optionBuilder52 = org.apache.commons.cli.OptionBuilder.hasArgs();
        org.apache.commons.cli.OptionBuilder optionBuilder53 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs();
        org.apache.commons.cli.OptionBuilder[] optionBuilderArray54 = new org.apache.commons.cli.OptionBuilder[] { optionBuilder45, optionBuilder47, optionBuilder49, optionBuilder51, optionBuilder52, optionBuilder53 };
        org.apache.commons.cli.OptionBuilder[] optionBuilderArray55 = commandLine41.getParsedOptionValues('#', optionBuilderArray54);
        org.apache.commons.cli.OptionGroup optionGroup56 = new org.apache.commons.cli.OptionGroup();
        boolean boolean57 = optionGroup56.isRequired();
        java.lang.String str59 = commandLine41.getOptionValue(optionGroup56, "");
        boolean boolean60 = optionGroup56.isRequired();
        org.apache.commons.cli.Option option62 = org.apache.commons.cli.OptionBuilder.create("arg");
        option62.setRequired(true);
        boolean boolean65 = option62.hasArgName();
        org.apache.commons.cli.OptionGroup optionGroup66 = optionGroup56.addOption(option62);
        org.apache.commons.cli.DefaultParser.Builder builder67 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser68 = builder67.get();
        org.apache.commons.cli.Options options69 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray73 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties74 = null;
        org.apache.commons.cli.CommandLine commandLine76 = defaultParser68.parse(options69, strArray73, properties74, false);
        java.lang.Object obj78 = commandLine76.getOptionObject('4');
        org.apache.commons.cli.OptionBuilder optionBuilder80 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs();
        org.apache.commons.cli.OptionBuilder optionBuilder82 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs((int) (byte) 1);
        org.apache.commons.cli.OptionBuilder optionBuilder84 = org.apache.commons.cli.OptionBuilder.withLongOpt("");
        org.apache.commons.cli.OptionBuilder optionBuilder86 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs((int) (byte) 1);
        org.apache.commons.cli.OptionBuilder optionBuilder87 = org.apache.commons.cli.OptionBuilder.hasArgs();
        org.apache.commons.cli.OptionBuilder optionBuilder88 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs();
        org.apache.commons.cli.OptionBuilder[] optionBuilderArray89 = new org.apache.commons.cli.OptionBuilder[] { optionBuilder80, optionBuilder82, optionBuilder84, optionBuilder86, optionBuilder87, optionBuilder88 };
        org.apache.commons.cli.OptionBuilder[] optionBuilderArray90 = commandLine76.getParsedOptionValues('#', optionBuilderArray89);
        org.apache.commons.cli.DefaultParser.Builder builder92 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser93 = builder92.get();
        org.apache.commons.cli.DefaultParser.Builder builder94 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser95 = builder94.get();
        org.apache.commons.cli.CommandLineParser[] commandLineParserArray96 = new org.apache.commons.cli.CommandLineParser[] { defaultParser93, defaultParser95 };
        org.apache.commons.cli.CommandLineParser[] commandLineParserArray97 = commandLine76.getParsedOptionValues('#', commandLineParserArray96);
        org.apache.commons.cli.CommandLineParser[] commandLineParserArray98 = commandLine28.getParsedOptionValue(optionGroup66, commandLineParserArray97);
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(defaultParser1);
        org.junit.Assert.assertNotNull(builder3);
        org.junit.Assert.assertNotNull(defaultParser4);
        org.junit.Assert.assertNotNull(strArray9);
        org.junit.Assert.assertArrayEquals(strArray9, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine12);
        org.junit.Assert.assertNotNull(builder13);
        org.junit.Assert.assertNotNull(defaultParser14);
        org.junit.Assert.assertNotNull(strArray19);
        org.junit.Assert.assertArrayEquals(strArray19, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine22);
        org.junit.Assert.assertNull(obj24);
        org.junit.Assert.assertNotNull(properties26);
        org.junit.Assert.assertNotNull(commandLine28);
        org.junit.Assert.assertNotNull(commandLine31);
        org.junit.Assert.assertNotNull(builder32);
        org.junit.Assert.assertNotNull(defaultParser33);
        org.junit.Assert.assertNotNull(strArray38);
        org.junit.Assert.assertArrayEquals(strArray38, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine41);
        org.junit.Assert.assertNull(obj43);
        org.junit.Assert.assertNotNull(optionBuilder45);
        org.junit.Assert.assertNotNull(optionBuilder47);
        org.junit.Assert.assertNotNull(optionBuilder49);
        org.junit.Assert.assertNotNull(optionBuilder51);
        org.junit.Assert.assertNotNull(optionBuilder52);
        org.junit.Assert.assertNotNull(optionBuilder53);
        org.junit.Assert.assertNotNull(optionBuilderArray54);
        org.junit.Assert.assertNotNull(optionBuilderArray55);
        org.junit.Assert.assertTrue("'" + boolean57 + "' != '" + false + "'", boolean57 == false);
        org.junit.Assert.assertEquals("'" + str59 + "' != '" + "" + "'", str59, "");
        org.junit.Assert.assertTrue("'" + boolean60 + "' != '" + false + "'", boolean60 == false);
        org.junit.Assert.assertNotNull(option62);
        org.junit.Assert.assertTrue("'" + boolean65 + "' != '" + false + "'", boolean65 == false);
        org.junit.Assert.assertNotNull(optionGroup66);
        org.junit.Assert.assertNotNull(builder67);
        org.junit.Assert.assertNotNull(defaultParser68);
        org.junit.Assert.assertNotNull(strArray73);
        org.junit.Assert.assertArrayEquals(strArray73, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine76);
        org.junit.Assert.assertNull(obj78);
        org.junit.Assert.assertNotNull(optionBuilder80);
        org.junit.Assert.assertNotNull(optionBuilder82);
        org.junit.Assert.assertNotNull(optionBuilder84);
        org.junit.Assert.assertNotNull(optionBuilder86);
        org.junit.Assert.assertNotNull(optionBuilder87);
        org.junit.Assert.assertNotNull(optionBuilder88);
        org.junit.Assert.assertNotNull(optionBuilderArray89);
        org.junit.Assert.assertNotNull(optionBuilderArray90);
        org.junit.Assert.assertNotNull(builder92);
        org.junit.Assert.assertNotNull(defaultParser93);
        org.junit.Assert.assertNotNull(builder94);
        org.junit.Assert.assertNotNull(defaultParser95);
        org.junit.Assert.assertNotNull(commandLineParserArray96);
        org.junit.Assert.assertNotNull(commandLineParserArray97);
        org.junit.Assert.assertNotNull(commandLineParserArray98);
    }

    @Test
    public void test279() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test279");
        java.lang.Appendable appendable0 = null;
        org.apache.commons.cli.help.TextHelpAppendable textHelpAppendable1 = new org.apache.commons.cli.help.TextHelpAppendable(appendable0);
        java.lang.CharSequence charSequence2 = null;
        textHelpAppendable1.appendParagraph(charSequence2);
        textHelpAppendable1.setIndent(0);
        java.lang.Object[] objArray11 = new java.lang.Object[] { 10L, (short) 1, "", (short) 100 };
        textHelpAppendable1.appendParagraphFormat("", objArray11);
        // The following exception was thrown during execution in test generation
        try {
            textHelpAppendable1.printWrapped("--");
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(objArray11);
        org.junit.Assert.assertEquals(java.util.Arrays.deepToString(objArray11), "[10, 1, , 100]");
        org.junit.Assert.assertEquals(java.util.Arrays.toString(objArray11), "[10, 1, , 100]");
    }

    @Test
    public void test280() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test280");
        java.lang.Appendable appendable0 = null;
        org.apache.commons.cli.help.TextHelpAppendable textHelpAppendable1 = new org.apache.commons.cli.help.TextHelpAppendable(appendable0);
        textHelpAppendable1.setIndent((int) (short) -1);
        textHelpAppendable1.setLeftPad((-1));
    }

    @Test
    public void test281() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test281");
        org.apache.commons.cli.help.TextStyle textStyle0 = org.apache.commons.cli.help.TextStyle.DEFAULT;
        java.lang.CharSequence charSequence3 = textStyle0.pad(false, (java.lang.CharSequence) "");
        int int4 = textStyle0.getIndent();
        int int5 = textStyle0.getLeftPad();
        boolean boolean6 = textStyle0.isScalable();
        org.apache.commons.cli.help.TextStyle.Alignment alignment7 = textStyle0.getAlignment();
        org.junit.Assert.assertNotNull(textStyle0);
        org.junit.Assert.assertEquals("'" + charSequence3 + "' != '" + "" + "'", charSequence3, "");
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 0 + "'", int4 == 0);
        org.junit.Assert.assertTrue("'" + int5 + "' != '" + 0 + "'", int5 == 0);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + true + "'", boolean6 == true);
        org.junit.Assert.assertTrue("'" + alignment7 + "' != '" + org.apache.commons.cli.help.TextStyle.Alignment.LEFT + "'", alignment7.equals(org.apache.commons.cli.help.TextStyle.Alignment.LEFT));
    }

    @Test
    public void test282() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test282");
        org.apache.commons.cli.DefaultParser.Builder builder0 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser1 = builder0.get();
        org.apache.commons.cli.Options options2 = new org.apache.commons.cli.Options();
        org.apache.commons.cli.DefaultParser.Builder builder3 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser4 = builder3.get();
        org.apache.commons.cli.Options options5 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray9 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties10 = null;
        org.apache.commons.cli.CommandLine commandLine12 = defaultParser4.parse(options5, strArray9, properties10, false);
        org.apache.commons.cli.DefaultParser.Builder builder13 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser14 = builder13.get();
        org.apache.commons.cli.Options options15 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray19 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties20 = null;
        org.apache.commons.cli.CommandLine commandLine22 = defaultParser14.parse(options15, strArray19, properties20, false);
        java.lang.Object obj24 = commandLine22.getOptionObject('4');
        org.apache.commons.cli.Option option25 = null;
        java.util.Properties properties26 = commandLine22.getOptionProperties(option25);
        org.apache.commons.cli.CommandLine commandLine28 = defaultParser1.parse(options2, strArray9, properties26, false);
        boolean boolean30 = options2.hasOption("[ Options: [ short {} ] [ long {} ]");
        org.apache.commons.cli.DefaultParser.Builder builder31 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser32 = builder31.get();
        org.apache.commons.cli.Options options33 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray37 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties38 = null;
        org.apache.commons.cli.CommandLine commandLine40 = defaultParser32.parse(options33, strArray37, properties38, false);
        boolean boolean42 = options33.hasShortOption("arg");
        org.apache.commons.cli.Options options43 = options2.addOptions(options33);
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(defaultParser1);
        org.junit.Assert.assertNotNull(builder3);
        org.junit.Assert.assertNotNull(defaultParser4);
        org.junit.Assert.assertNotNull(strArray9);
        org.junit.Assert.assertArrayEquals(strArray9, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine12);
        org.junit.Assert.assertNotNull(builder13);
        org.junit.Assert.assertNotNull(defaultParser14);
        org.junit.Assert.assertNotNull(strArray19);
        org.junit.Assert.assertArrayEquals(strArray19, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine22);
        org.junit.Assert.assertNull(obj24);
        org.junit.Assert.assertNotNull(properties26);
        org.junit.Assert.assertNotNull(commandLine28);
        org.junit.Assert.assertTrue("'" + boolean30 + "' != '" + false + "'", boolean30 == false);
        org.junit.Assert.assertNotNull(builder31);
        org.junit.Assert.assertNotNull(defaultParser32);
        org.junit.Assert.assertNotNull(strArray37);
        org.junit.Assert.assertArrayEquals(strArray37, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine40);
        org.junit.Assert.assertTrue("'" + boolean42 + "' != '" + false + "'", boolean42 == false);
        org.junit.Assert.assertNotNull(options43);
    }

    @Test
    public void test283() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test283");
        org.apache.commons.cli.HelpFormatter.Builder builder0 = new org.apache.commons.cli.HelpFormatter.Builder();
        org.apache.commons.cli.HelpFormatter.Builder builder2 = builder0.setShowSince(false);
        org.apache.commons.cli.HelpFormatter helpFormatter3 = builder0.get();
        helpFormatter3.setLongOptSeparator(" | ");
        java.lang.String str6 = helpFormatter3.defaultNewLine;
        helpFormatter3.defaultLeftPad = 'a';
        int int9 = helpFormatter3.getDescPadding();
        org.junit.Assert.assertNotNull(builder2);
        org.junit.Assert.assertNotNull(helpFormatter3);
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "\r\n" + "'", str6, "\r\n");
        org.junit.Assert.assertTrue("'" + int9 + "' != '" + 3 + "'", int9 == 3);
    }

    @Test
    public void test284() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test284");
        org.apache.commons.cli.Option option1 = org.apache.commons.cli.OptionBuilder.create("arg");
        java.lang.String str3 = option1.getValue("[--]");
        org.apache.commons.cli.help.OptionFormatter.Builder builder4 = org.apache.commons.cli.help.OptionFormatter.builder();
        org.apache.commons.cli.help.OptionFormatter.Builder builder6 = builder4.setOptArgSeparator("");
        org.apache.commons.cli.help.OptionFormatter optionFormatter7 = builder4.get();
        option1.setType((java.lang.Object) optionFormatter7);
        java.lang.String str9 = option1.getSince();
        java.lang.String str11 = option1.getValue("hi!");
        org.junit.Assert.assertNotNull(option1);
        org.junit.Assert.assertEquals("'" + str3 + "' != '" + "[--]" + "'", str3, "[--]");
        org.junit.Assert.assertNotNull(builder4);
        org.junit.Assert.assertNotNull(builder6);
        org.junit.Assert.assertNull(optionFormatter7);
        org.junit.Assert.assertNull(str9);
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "hi!" + "'", str11, "hi!");
    }

    @Test
    public void test285() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test285");
        java.lang.Appendable appendable0 = null;
        org.apache.commons.cli.help.TextHelpAppendable textHelpAppendable1 = new org.apache.commons.cli.help.TextHelpAppendable(appendable0);
        java.lang.CharSequence charSequence2 = null;
        textHelpAppendable1.appendParagraph(charSequence2);
        textHelpAppendable1.setIndent(1);
        textHelpAppendable1.setLeftPad(1);
        org.apache.commons.cli.help.TextStyle.Builder builder8 = textHelpAppendable1.getTextStyleBuilder();
        // The following exception was thrown during execution in test generation
        try {
            org.apache.commons.cli.help.FilterHelpAppendable filterHelpAppendable12 = textHelpAppendable1.append((java.lang.CharSequence) ", ", (-1), (int) '4');
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(builder8);
    }

    @Test
    public void test286() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test286");
        org.apache.commons.cli.DefaultParser.Builder builder0 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser1 = builder0.get();
        org.apache.commons.cli.Options options2 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties7 = null;
        org.apache.commons.cli.CommandLine commandLine9 = defaultParser1.parse(options2, strArray6, properties7, false);
        org.apache.commons.cli.DefaultParser.Builder builder11 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser12 = builder11.get();
        org.apache.commons.cli.Options options13 = new org.apache.commons.cli.Options();
        org.apache.commons.cli.DefaultParser.Builder builder14 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser15 = builder14.get();
        org.apache.commons.cli.Options options16 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray20 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties21 = null;
        org.apache.commons.cli.CommandLine commandLine23 = defaultParser15.parse(options16, strArray20, properties21, false);
        org.apache.commons.cli.DefaultParser.Builder builder24 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser25 = builder24.get();
        org.apache.commons.cli.Options options26 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray30 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties31 = null;
        org.apache.commons.cli.CommandLine commandLine33 = defaultParser25.parse(options26, strArray30, properties31, false);
        java.lang.Object obj35 = commandLine33.getOptionObject('4');
        org.apache.commons.cli.Option option36 = null;
        java.util.Properties properties37 = commandLine33.getOptionProperties(option36);
        org.apache.commons.cli.CommandLine commandLine39 = defaultParser12.parse(options13, strArray20, properties37, false);
        org.apache.commons.cli.CommandLine.Builder builder41 = new org.apache.commons.cli.CommandLine.Builder();
        org.apache.commons.cli.CommandLine commandLine42 = commandLine39.getParsedOptionValue(' ', (java.util.function.Supplier<org.apache.commons.cli.CommandLine>) builder41);
        org.apache.commons.cli.Option option43 = null;
        org.apache.commons.cli.CommandLine.Builder builder44 = builder41.addOption(option43);
        org.apache.commons.cli.CommandLine commandLine45 = commandLine9.getParsedOptionValue('#', (java.util.function.Supplier<org.apache.commons.cli.CommandLine>) builder41);
        java.util.function.Supplier<java.lang.String> strSupplier47 = null;
        java.lang.String str48 = commandLine45.getOptionValue('4', strSupplier47);
        java.lang.String str51 = commandLine45.getOptionValue('#', "[ Options: [ short {} ] [ long {} ]");
        org.apache.commons.cli.DefaultParser.Builder builder52 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser53 = builder52.get();
        org.apache.commons.cli.Options options54 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray58 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties59 = null;
        org.apache.commons.cli.CommandLine commandLine61 = defaultParser53.parse(options54, strArray58, properties59, false);
        java.lang.Object obj63 = commandLine61.getOptionObject('4');
        org.apache.commons.cli.OptionGroup optionGroup64 = null;
        java.lang.String str66 = commandLine61.getOptionValue(optionGroup64, "[--]");
        java.util.function.Supplier<java.lang.String> strSupplier68 = null;
        java.lang.String str69 = commandLine61.getOptionValue("hi!", strSupplier68);
        org.apache.commons.cli.Option option71 = org.apache.commons.cli.OptionBuilder.create("arg");
        java.util.List<java.lang.String> strList72 = option71.getValuesList();
        boolean boolean73 = commandLine61.hasOption(option71);
        java.lang.String[] strArray74 = commandLine45.getOptionValues(option71);
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(defaultParser1);
        org.junit.Assert.assertNotNull(strArray6);
        org.junit.Assert.assertArrayEquals(strArray6, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine9);
        org.junit.Assert.assertNotNull(builder11);
        org.junit.Assert.assertNotNull(defaultParser12);
        org.junit.Assert.assertNotNull(builder14);
        org.junit.Assert.assertNotNull(defaultParser15);
        org.junit.Assert.assertNotNull(strArray20);
        org.junit.Assert.assertArrayEquals(strArray20, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine23);
        org.junit.Assert.assertNotNull(builder24);
        org.junit.Assert.assertNotNull(defaultParser25);
        org.junit.Assert.assertNotNull(strArray30);
        org.junit.Assert.assertArrayEquals(strArray30, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine33);
        org.junit.Assert.assertNull(obj35);
        org.junit.Assert.assertNotNull(properties37);
        org.junit.Assert.assertNotNull(commandLine39);
        org.junit.Assert.assertNotNull(commandLine42);
        org.junit.Assert.assertNotNull(builder44);
        org.junit.Assert.assertNotNull(commandLine45);
        org.junit.Assert.assertNull(str48);
        org.junit.Assert.assertEquals("'" + str51 + "' != '" + "[ Options: [ short {} ] [ long {} ]" + "'", str51, "[ Options: [ short {} ] [ long {} ]");
        org.junit.Assert.assertNotNull(builder52);
        org.junit.Assert.assertNotNull(defaultParser53);
        org.junit.Assert.assertNotNull(strArray58);
        org.junit.Assert.assertArrayEquals(strArray58, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine61);
        org.junit.Assert.assertNull(obj63);
        org.junit.Assert.assertEquals("'" + str66 + "' != '" + "[--]" + "'", str66, "[--]");
        org.junit.Assert.assertNull(str69);
        org.junit.Assert.assertNotNull(option71);
        org.junit.Assert.assertNotNull(strList72);
        org.junit.Assert.assertTrue("'" + boolean73 + "' != '" + false + "'", boolean73 == false);
        org.junit.Assert.assertNull(strArray74);
    }

    @Test
    public void test287() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test287");
        org.apache.commons.cli.OptionBuilder optionBuilder1 = org.apache.commons.cli.OptionBuilder.hasArg(true);
        org.junit.Assert.assertNotNull(optionBuilder1);
    }

    @Test
    public void test288() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test288");
        org.apache.commons.cli.DefaultParser.Builder builder0 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser1 = builder0.get();
        org.apache.commons.cli.Options options2 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties7 = null;
        org.apache.commons.cli.CommandLine commandLine9 = defaultParser1.parse(options2, strArray6, properties7, false);
        org.apache.commons.cli.DefaultParser.Builder builder11 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser12 = builder11.get();
        org.apache.commons.cli.Options options13 = new org.apache.commons.cli.Options();
        org.apache.commons.cli.DefaultParser.Builder builder14 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser15 = builder14.get();
        org.apache.commons.cli.Options options16 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray20 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties21 = null;
        org.apache.commons.cli.CommandLine commandLine23 = defaultParser15.parse(options16, strArray20, properties21, false);
        org.apache.commons.cli.DefaultParser.Builder builder24 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser25 = builder24.get();
        org.apache.commons.cli.Options options26 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray30 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties31 = null;
        org.apache.commons.cli.CommandLine commandLine33 = defaultParser25.parse(options26, strArray30, properties31, false);
        java.lang.Object obj35 = commandLine33.getOptionObject('4');
        org.apache.commons.cli.Option option36 = null;
        java.util.Properties properties37 = commandLine33.getOptionProperties(option36);
        org.apache.commons.cli.CommandLine commandLine39 = defaultParser12.parse(options13, strArray20, properties37, false);
        org.apache.commons.cli.CommandLine.Builder builder41 = new org.apache.commons.cli.CommandLine.Builder();
        org.apache.commons.cli.CommandLine commandLine42 = commandLine39.getParsedOptionValue(' ', (java.util.function.Supplier<org.apache.commons.cli.CommandLine>) builder41);
        org.apache.commons.cli.Option option43 = null;
        org.apache.commons.cli.CommandLine.Builder builder44 = builder41.addOption(option43);
        org.apache.commons.cli.CommandLine commandLine45 = commandLine9.getParsedOptionValue('#', (java.util.function.Supplier<org.apache.commons.cli.CommandLine>) builder41);
        org.apache.commons.cli.Option option47 = org.apache.commons.cli.OptionBuilder.create("arg");
        option47.setRequired(true);
        java.lang.Object obj50 = option47.getType();
        org.apache.commons.cli.CommandLine.Builder builder51 = builder41.addOption(option47);
        java.lang.String str53 = option47.getValue("arg");
        java.lang.Object obj54 = option47.getType();
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(defaultParser1);
        org.junit.Assert.assertNotNull(strArray6);
        org.junit.Assert.assertArrayEquals(strArray6, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine9);
        org.junit.Assert.assertNotNull(builder11);
        org.junit.Assert.assertNotNull(defaultParser12);
        org.junit.Assert.assertNotNull(builder14);
        org.junit.Assert.assertNotNull(defaultParser15);
        org.junit.Assert.assertNotNull(strArray20);
        org.junit.Assert.assertArrayEquals(strArray20, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine23);
        org.junit.Assert.assertNotNull(builder24);
        org.junit.Assert.assertNotNull(defaultParser25);
        org.junit.Assert.assertNotNull(strArray30);
        org.junit.Assert.assertArrayEquals(strArray30, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine33);
        org.junit.Assert.assertNull(obj35);
        org.junit.Assert.assertNotNull(properties37);
        org.junit.Assert.assertNotNull(commandLine39);
        org.junit.Assert.assertNotNull(commandLine42);
        org.junit.Assert.assertNotNull(builder44);
        org.junit.Assert.assertNotNull(commandLine45);
        org.junit.Assert.assertNotNull(option47);
        org.junit.Assert.assertNotNull(obj50);
        org.junit.Assert.assertEquals(obj50.toString(), "class java.lang.String");
        org.junit.Assert.assertEquals(java.lang.String.valueOf(obj50), "class java.lang.String");
        org.junit.Assert.assertEquals(java.util.Objects.toString(obj50), "class java.lang.String");
        org.junit.Assert.assertNotNull(builder51);
        org.junit.Assert.assertEquals("'" + str53 + "' != '" + "arg" + "'", str53, "arg");
        org.junit.Assert.assertNotNull(obj54);
        org.junit.Assert.assertEquals(obj54.toString(), "class java.lang.String");
        org.junit.Assert.assertEquals(java.lang.String.valueOf(obj54), "class java.lang.String");
        org.junit.Assert.assertEquals(java.util.Objects.toString(obj54), "class java.lang.String");
    }

    @Test
    public void test289() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test289");
        org.apache.commons.cli.Option option0 = null;
        org.apache.commons.cli.help.OptionFormatter optionFormatter1 = org.apache.commons.cli.help.OptionFormatter.from(option0);
        java.lang.String str3 = optionFormatter1.toOptional("--");
        org.apache.commons.cli.help.OptionFormatter.Builder builder4 = new org.apache.commons.cli.help.OptionFormatter.Builder(optionFormatter1);
        java.lang.String str6 = optionFormatter1.toOptional("--");
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str7 = optionFormatter1.getArgName();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(optionFormatter1);
        org.junit.Assert.assertEquals("'" + str3 + "' != '" + "[--]" + "'", str3, "[--]");
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "[--]" + "'", str6, "[--]");
    }

    @Test
    public void test290() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test290");
        org.apache.commons.cli.DefaultParser.Builder builder0 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser1 = builder0.get();
        org.apache.commons.cli.Options options2 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties7 = null;
        org.apache.commons.cli.CommandLine commandLine9 = defaultParser1.parse(options2, strArray6, properties7, false);
        java.lang.Object obj11 = commandLine9.getOptionObject('4');
        org.apache.commons.cli.OptionBuilder optionBuilder13 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs();
        org.apache.commons.cli.OptionBuilder optionBuilder15 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs((int) (byte) 1);
        org.apache.commons.cli.OptionBuilder optionBuilder17 = org.apache.commons.cli.OptionBuilder.withLongOpt("");
        org.apache.commons.cli.OptionBuilder optionBuilder19 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs((int) (byte) 1);
        org.apache.commons.cli.OptionBuilder optionBuilder20 = org.apache.commons.cli.OptionBuilder.hasArgs();
        org.apache.commons.cli.OptionBuilder optionBuilder21 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs();
        org.apache.commons.cli.OptionBuilder[] optionBuilderArray22 = new org.apache.commons.cli.OptionBuilder[] { optionBuilder13, optionBuilder15, optionBuilder17, optionBuilder19, optionBuilder20, optionBuilder21 };
        org.apache.commons.cli.OptionBuilder[] optionBuilderArray23 = commandLine9.getParsedOptionValues('#', optionBuilderArray22);
        org.apache.commons.cli.DefaultParser.Builder builder25 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser26 = builder25.get();
        org.apache.commons.cli.DefaultParser.Builder builder27 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser28 = builder27.get();
        org.apache.commons.cli.CommandLineParser[] commandLineParserArray29 = new org.apache.commons.cli.CommandLineParser[] { defaultParser26, defaultParser28 };
        org.apache.commons.cli.CommandLineParser[] commandLineParserArray30 = commandLine9.getParsedOptionValues('#', commandLineParserArray29);
        org.apache.commons.cli.DefaultParser.Builder builder31 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser32 = builder31.get();
        org.apache.commons.cli.Options options33 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray37 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties38 = null;
        org.apache.commons.cli.CommandLine commandLine40 = defaultParser32.parse(options33, strArray37, properties38, false);
        java.lang.Object obj42 = commandLine40.getOptionObject('4');
        java.lang.Object obj44 = commandLine40.getOptionObject('4');
        org.apache.commons.cli.OptionGroup optionGroup45 = new org.apache.commons.cli.OptionGroup();
        boolean boolean46 = optionGroup45.isRequired();
        boolean boolean47 = commandLine40.hasOption(optionGroup45);
        boolean boolean48 = optionGroup45.isRequired();
        boolean boolean49 = optionGroup45.isSelected();
        boolean boolean50 = optionGroup45.isRequired();
        org.apache.commons.cli.help.TextStyle.Alignment alignment51 = commandLine9.getParsedOptionValue(optionGroup45);
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(defaultParser1);
        org.junit.Assert.assertNotNull(strArray6);
        org.junit.Assert.assertArrayEquals(strArray6, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine9);
        org.junit.Assert.assertNull(obj11);
        org.junit.Assert.assertNotNull(optionBuilder13);
        org.junit.Assert.assertNotNull(optionBuilder15);
        org.junit.Assert.assertNotNull(optionBuilder17);
        org.junit.Assert.assertNotNull(optionBuilder19);
        org.junit.Assert.assertNotNull(optionBuilder20);
        org.junit.Assert.assertNotNull(optionBuilder21);
        org.junit.Assert.assertNotNull(optionBuilderArray22);
        org.junit.Assert.assertNotNull(optionBuilderArray23);
        org.junit.Assert.assertNotNull(builder25);
        org.junit.Assert.assertNotNull(defaultParser26);
        org.junit.Assert.assertNotNull(builder27);
        org.junit.Assert.assertNotNull(defaultParser28);
        org.junit.Assert.assertNotNull(commandLineParserArray29);
        org.junit.Assert.assertNotNull(commandLineParserArray30);
        org.junit.Assert.assertNotNull(builder31);
        org.junit.Assert.assertNotNull(defaultParser32);
        org.junit.Assert.assertNotNull(strArray37);
        org.junit.Assert.assertArrayEquals(strArray37, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine40);
        org.junit.Assert.assertNull(obj42);
        org.junit.Assert.assertNull(obj44);
        org.junit.Assert.assertTrue("'" + boolean46 + "' != '" + false + "'", boolean46 == false);
        org.junit.Assert.assertTrue("'" + boolean47 + "' != '" + false + "'", boolean47 == false);
        org.junit.Assert.assertTrue("'" + boolean48 + "' != '" + false + "'", boolean48 == false);
        org.junit.Assert.assertTrue("'" + boolean49 + "' != '" + false + "'", boolean49 == false);
        org.junit.Assert.assertTrue("'" + boolean50 + "' != '" + false + "'", boolean50 == false);
        org.junit.Assert.assertNull(alignment51);
    }

    @Test
    public void test291() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test291");
        org.apache.commons.cli.HelpFormatter helpFormatter0 = new org.apache.commons.cli.HelpFormatter();
        helpFormatter0.setDescPadding((int) 'a');
        java.lang.String str3 = helpFormatter0.getLongOptPrefix();
        org.junit.Assert.assertEquals("'" + str3 + "' != '" + "--" + "'", str3, "--");
    }

    @Test
    public void test292() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test292");
        org.apache.commons.cli.HelpFormatter.Builder builder0 = new org.apache.commons.cli.HelpFormatter.Builder();
        java.util.function.Function<org.apache.commons.cli.Option, java.lang.String> optionFunction1 = org.apache.commons.cli.help.OptionFormatter.NO_DEPRECATED_FORMAT;
        org.apache.commons.cli.HelpFormatter.Builder builder2 = builder0.setShowDeprecated(optionFunction1);
        org.junit.Assert.assertNotNull(optionFunction1);
        org.junit.Assert.assertNotNull(builder2);
    }

    @Test
    public void test293() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test293");
        org.apache.commons.cli.HelpFormatter.Builder builder0 = new org.apache.commons.cli.HelpFormatter.Builder();
        org.apache.commons.cli.HelpFormatter.Builder builder2 = builder0.setShowSince(false);
        org.apache.commons.cli.HelpFormatter helpFormatter3 = builder0.get();
        helpFormatter3.defaultLeftPad = '#';
        java.lang.String str6 = helpFormatter3.defaultLongOptPrefix;
        org.junit.Assert.assertNotNull(builder2);
        org.junit.Assert.assertNotNull(helpFormatter3);
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "--" + "'", str6, "--");
    }

    @Test
    public void test294() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test294");
        org.apache.commons.cli.DefaultParser.Builder builder0 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser1 = builder0.get();
        org.apache.commons.cli.Options options2 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties7 = null;
        org.apache.commons.cli.CommandLine commandLine9 = defaultParser1.parse(options2, strArray6, properties7, false);
        java.lang.Object obj11 = commandLine9.getOptionObject('4');
        java.lang.Object obj13 = commandLine9.getOptionObject('4');
        org.apache.commons.cli.OptionGroup optionGroup14 = new org.apache.commons.cli.OptionGroup();
        boolean boolean15 = optionGroup14.isRequired();
        boolean boolean16 = commandLine9.hasOption(optionGroup14);
        boolean boolean17 = optionGroup14.isRequired();
        java.util.Collection<java.lang.String> strCollection18 = optionGroup14.getNames();
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(defaultParser1);
        org.junit.Assert.assertNotNull(strArray6);
        org.junit.Assert.assertArrayEquals(strArray6, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine9);
        org.junit.Assert.assertNull(obj11);
        org.junit.Assert.assertNull(obj13);
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertTrue("'" + boolean16 + "' != '" + false + "'", boolean16 == false);
        org.junit.Assert.assertTrue("'" + boolean17 + "' != '" + false + "'", boolean17 == false);
        org.junit.Assert.assertNotNull(strCollection18);
    }

    @Test
    public void test295() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test295");
        org.apache.commons.cli.HelpFormatter.Builder builder0 = new org.apache.commons.cli.HelpFormatter.Builder();
        org.apache.commons.cli.HelpFormatter.Builder builder2 = builder0.setShowSince(false);
        org.apache.commons.cli.HelpFormatter helpFormatter3 = builder0.get();
        helpFormatter3.setLongOptSeparator(" | ");
        java.lang.String str6 = helpFormatter3.defaultNewLine;
        helpFormatter3.setWidth((int) '4');
        org.junit.Assert.assertNotNull(builder2);
        org.junit.Assert.assertNotNull(helpFormatter3);
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "\r\n" + "'", str6, "\r\n");
    }

    @Test
    public void test296() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test296");
        java.lang.Appendable appendable0 = null;
        org.apache.commons.cli.help.TextHelpAppendable textHelpAppendable1 = new org.apache.commons.cli.help.TextHelpAppendable(appendable0);
        java.lang.CharSequence charSequence2 = null;
        textHelpAppendable1.appendParagraph(charSequence2);
        textHelpAppendable1.setIndent(0);
        java.lang.Object[] objArray11 = new java.lang.Object[] { 10L, (short) 1, "", (short) 100 };
        textHelpAppendable1.appendParagraphFormat("", objArray11);
        org.apache.commons.cli.help.TextHelpAppendable textHelpAppendable13 = new org.apache.commons.cli.help.TextHelpAppendable((java.lang.Appendable) textHelpAppendable1);
        org.apache.commons.cli.help.TextHelpAppendable textHelpAppendable14 = new org.apache.commons.cli.help.TextHelpAppendable((java.lang.Appendable) textHelpAppendable13);
        org.apache.commons.cli.help.TextStyle.Builder builder15 = textHelpAppendable14.getTextStyleBuilder();
        textHelpAppendable14.appendHeader(0, (java.lang.CharSequence) "");
        java.lang.CharSequence[] charSequenceArray29 = new java.lang.CharSequence[] { "hi!", "hi!", "\r\n", "[ Options: [ short {} ] [ long {} ]", "[--]", "-", "arg", "[ Option arg :: null :: class java.lang.String ]", "--" };
        java.util.ArrayList<java.lang.CharSequence> charSequenceList30 = new java.util.ArrayList<java.lang.CharSequence>();
        boolean boolean31 = java.util.Collections.addAll((java.util.Collection<java.lang.CharSequence>) charSequenceList30, charSequenceArray29);
        // The following exception was thrown during execution in test generation
        try {
            textHelpAppendable14.appendList(false, (java.util.Collection<java.lang.CharSequence>) charSequenceList30);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(objArray11);
        org.junit.Assert.assertEquals(java.util.Arrays.deepToString(objArray11), "[10, 1, , 100]");
        org.junit.Assert.assertEquals(java.util.Arrays.toString(objArray11), "[10, 1, , 100]");
        org.junit.Assert.assertNotNull(builder15);
        org.junit.Assert.assertNotNull(charSequenceArray29);
        org.junit.Assert.assertTrue("'" + boolean31 + "' != '" + true + "'", boolean31 == true);
    }

    @Test
    public void test297() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test297");
        java.lang.Appendable appendable0 = null;
        org.apache.commons.cli.help.TextHelpAppendable textHelpAppendable1 = new org.apache.commons.cli.help.TextHelpAppendable(appendable0);
        java.lang.CharSequence charSequence2 = null;
        textHelpAppendable1.appendParagraph(charSequence2);
        textHelpAppendable1.setIndent(0);
        java.lang.Object[] objArray11 = new java.lang.Object[] { 10L, (short) 1, "", (short) 100 };
        textHelpAppendable1.appendParagraphFormat("", objArray11);
        org.apache.commons.cli.help.TextHelpAppendable textHelpAppendable13 = new org.apache.commons.cli.help.TextHelpAppendable((java.lang.Appendable) textHelpAppendable1);
        org.apache.commons.cli.help.TextHelpAppendable textHelpAppendable14 = new org.apache.commons.cli.help.TextHelpAppendable((java.lang.Appendable) textHelpAppendable13);
        org.apache.commons.cli.help.TextStyle.Builder builder15 = textHelpAppendable14.getTextStyleBuilder();
        org.apache.commons.cli.help.TextStyle.Builder builder17 = builder15.setIndent((int) (byte) 0);
        org.apache.commons.cli.help.TextStyle textStyle18 = builder15.get();
        org.apache.commons.cli.help.TextStyle.Builder builder20 = builder15.setLeftPad((int) (byte) -1);
        org.junit.Assert.assertNotNull(objArray11);
        org.junit.Assert.assertEquals(java.util.Arrays.deepToString(objArray11), "[10, 1, , 100]");
        org.junit.Assert.assertEquals(java.util.Arrays.toString(objArray11), "[10, 1, , 100]");
        org.junit.Assert.assertNotNull(builder15);
        org.junit.Assert.assertNotNull(builder17);
        org.junit.Assert.assertNotNull(textStyle18);
        org.junit.Assert.assertNotNull(builder20);
    }

    @Test
    public void test298() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test298");
        org.apache.commons.cli.HelpFormatter helpFormatter0 = new org.apache.commons.cli.HelpFormatter();
        org.apache.commons.cli.Options options2 = new org.apache.commons.cli.Options();
        org.apache.commons.cli.OptionGroup optionGroup3 = new org.apache.commons.cli.OptionGroup();
        java.util.Collection<org.apache.commons.cli.Option> optionCollection4 = optionGroup3.getOptions();
        boolean boolean5 = optionGroup3.isSelected();
        org.apache.commons.cli.Options options6 = options2.addOptionGroup(optionGroup3);
        helpFormatter0.printHelp("usage: ", options2);
        helpFormatter0.setWidth((int) 'a');
        org.apache.commons.cli.Options options11 = new org.apache.commons.cli.Options();
        org.apache.commons.cli.OptionGroup optionGroup12 = new org.apache.commons.cli.OptionGroup();
        java.util.Collection<org.apache.commons.cli.Option> optionCollection13 = optionGroup12.getOptions();
        boolean boolean14 = optionGroup12.isSelected();
        org.apache.commons.cli.Options options15 = options11.addOptionGroup(optionGroup12);
        helpFormatter0.printHelp("[ Options: [ short {} ] [ long {} ]", options11, false);
        helpFormatter0.setSyntaxPrefix(" | ");
        java.lang.String str20 = helpFormatter0.defaultSyntaxPrefix;
        org.junit.Assert.assertNotNull(optionCollection4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertNotNull(options6);
        org.junit.Assert.assertNotNull(optionCollection13);
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + false + "'", boolean14 == false);
        org.junit.Assert.assertNotNull(options15);
        org.junit.Assert.assertEquals("'" + str20 + "' != '" + " | " + "'", str20, " | ");
    }

    @Test
    public void test299() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test299");
        // The following exception was thrown during execution in test generation
        try {
            org.apache.commons.cli.Option option2 = new org.apache.commons.cli.Option("-", "usage: ");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Illegal option name '-'.");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test300() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test300");
        org.apache.commons.cli.DefaultParser.Builder builder0 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser1 = builder0.get();
        org.apache.commons.cli.Options options2 = new org.apache.commons.cli.Options();
        org.apache.commons.cli.DefaultParser.Builder builder3 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser4 = builder3.get();
        org.apache.commons.cli.Options options5 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray9 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties10 = null;
        org.apache.commons.cli.CommandLine commandLine12 = defaultParser4.parse(options5, strArray9, properties10, false);
        org.apache.commons.cli.DefaultParser.Builder builder13 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser14 = builder13.get();
        org.apache.commons.cli.Options options15 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray19 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties20 = null;
        org.apache.commons.cli.CommandLine commandLine22 = defaultParser14.parse(options15, strArray19, properties20, false);
        java.lang.Object obj24 = commandLine22.getOptionObject('4');
        org.apache.commons.cli.Option option25 = null;
        java.util.Properties properties26 = commandLine22.getOptionProperties(option25);
        org.apache.commons.cli.CommandLine commandLine28 = defaultParser1.parse(options2, strArray9, properties26, false);
        org.apache.commons.cli.CommandLine.Builder builder30 = new org.apache.commons.cli.CommandLine.Builder();
        org.apache.commons.cli.CommandLine commandLine31 = commandLine28.getParsedOptionValue(' ', (java.util.function.Supplier<org.apache.commons.cli.CommandLine>) builder30);
        java.util.function.Consumer<org.apache.commons.cli.Option> optionConsumer32 = null;
        org.apache.commons.cli.CommandLine.Builder builder33 = builder30.setDeprecatedHandler(optionConsumer32);
        org.apache.commons.cli.CommandLine commandLine34 = builder33.build();
        java.lang.Object obj36 = commandLine34.getOptionObject('#');
        java.lang.String str39 = commandLine34.getOptionValue(" | ", " | ");
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(defaultParser1);
        org.junit.Assert.assertNotNull(builder3);
        org.junit.Assert.assertNotNull(defaultParser4);
        org.junit.Assert.assertNotNull(strArray9);
        org.junit.Assert.assertArrayEquals(strArray9, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine12);
        org.junit.Assert.assertNotNull(builder13);
        org.junit.Assert.assertNotNull(defaultParser14);
        org.junit.Assert.assertNotNull(strArray19);
        org.junit.Assert.assertArrayEquals(strArray19, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine22);
        org.junit.Assert.assertNull(obj24);
        org.junit.Assert.assertNotNull(properties26);
        org.junit.Assert.assertNotNull(commandLine28);
        org.junit.Assert.assertNotNull(commandLine31);
        org.junit.Assert.assertNotNull(builder33);
        org.junit.Assert.assertNotNull(commandLine34);
        org.junit.Assert.assertNull(obj36);
        org.junit.Assert.assertEquals("'" + str39 + "' != '" + " | " + "'", str39, " | ");
    }

    @Test
    public void test301() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test301");
        org.apache.commons.cli.HelpFormatter helpFormatter0 = new org.apache.commons.cli.HelpFormatter();
        org.apache.commons.cli.Options options2 = new org.apache.commons.cli.Options();
        org.apache.commons.cli.OptionGroup optionGroup3 = new org.apache.commons.cli.OptionGroup();
        java.util.Collection<org.apache.commons.cli.Option> optionCollection4 = optionGroup3.getOptions();
        boolean boolean5 = optionGroup3.isSelected();
        org.apache.commons.cli.Options options6 = options2.addOptionGroup(optionGroup3);
        helpFormatter0.printHelp("usage: ", options2);
        helpFormatter0.setWidth((int) 'a');
        org.apache.commons.cli.DefaultParser.Builder builder12 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser13 = builder12.get();
        org.apache.commons.cli.Options options14 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray18 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties19 = null;
        org.apache.commons.cli.CommandLine commandLine21 = defaultParser13.parse(options14, strArray18, properties19, false);
        boolean boolean23 = options14.hasShortOption("arg");
        org.apache.commons.cli.Option option25 = org.apache.commons.cli.OptionBuilder.create("arg");
        option25.setRequired(true);
        boolean boolean28 = option25.hasArgName();
        org.apache.commons.cli.Options options29 = options14.addOption(option25);
        helpFormatter0.printHelp(" ", "[ Option arg :: null :: class java.lang.String ]", options29, "hi!");
        helpFormatter0.setDescPadding(2147483647);
        int int34 = helpFormatter0.defaultLeftPad;
        org.junit.Assert.assertNotNull(optionCollection4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertNotNull(options6);
        org.junit.Assert.assertNotNull(builder12);
        org.junit.Assert.assertNotNull(defaultParser13);
        org.junit.Assert.assertNotNull(strArray18);
        org.junit.Assert.assertArrayEquals(strArray18, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine21);
        org.junit.Assert.assertTrue("'" + boolean23 + "' != '" + false + "'", boolean23 == false);
        org.junit.Assert.assertNotNull(option25);
        org.junit.Assert.assertTrue("'" + boolean28 + "' != '" + false + "'", boolean28 == false);
        org.junit.Assert.assertNotNull(options29);
        org.junit.Assert.assertTrue("'" + int34 + "' != '" + 1 + "'", int34 == 1);
    }

    @Test
    public void test302() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test302");
        org.apache.commons.cli.help.TextStyle textStyle0 = org.apache.commons.cli.help.TextStyle.DEFAULT;
        int int1 = textStyle0.getMaxWidth();
        int int2 = textStyle0.getMaxWidth();
        boolean boolean3 = textStyle0.isScalable();
        int int4 = textStyle0.getLeftPad();
        org.junit.Assert.assertNotNull(textStyle0);
        org.junit.Assert.assertTrue("'" + int1 + "' != '" + 2147483647 + "'", int1 == 2147483647);
        org.junit.Assert.assertTrue("'" + int2 + "' != '" + 2147483647 + "'", int2 == 2147483647);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + true + "'", boolean3 == true);
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 0 + "'", int4 == 0);
    }

    @Test
    public void test303() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test303");
        org.apache.commons.cli.HelpFormatter.Builder builder0 = new org.apache.commons.cli.HelpFormatter.Builder();
        org.apache.commons.cli.HelpFormatter.Builder builder2 = builder0.setShowSince(false);
        org.apache.commons.cli.HelpFormatter helpFormatter3 = builder0.get();
        helpFormatter3.defaultLeftPad = '#';
        org.apache.commons.cli.DefaultParser.Builder builder8 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser9 = builder8.get();
        org.apache.commons.cli.Options options10 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray14 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties15 = null;
        org.apache.commons.cli.CommandLine commandLine17 = defaultParser9.parse(options10, strArray14, properties15, false);
        helpFormatter3.printHelp(" ", ", ", options10, "Deprecated", true);
        java.lang.String str21 = helpFormatter3.getOptPrefix();
        int int22 = helpFormatter3.getLeftPadding();
        helpFormatter3.setNewLine(" ");
        org.junit.Assert.assertNotNull(builder2);
        org.junit.Assert.assertNotNull(helpFormatter3);
        org.junit.Assert.assertNotNull(builder8);
        org.junit.Assert.assertNotNull(defaultParser9);
        org.junit.Assert.assertNotNull(strArray14);
        org.junit.Assert.assertArrayEquals(strArray14, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine17);
        org.junit.Assert.assertEquals("'" + str21 + "' != '" + "-" + "'", str21, "-");
        org.junit.Assert.assertTrue("'" + int22 + "' != '" + 35 + "'", int22 == 35);
    }

    @Test
    public void test304() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test304");
        java.lang.Appendable appendable0 = null;
        org.apache.commons.cli.help.TextHelpAppendable textHelpAppendable1 = new org.apache.commons.cli.help.TextHelpAppendable(appendable0);
        java.lang.CharSequence charSequence2 = null;
        textHelpAppendable1.appendParagraph(charSequence2);
        textHelpAppendable1.setIndent(0);
        textHelpAppendable1.setLeftPad(100);
        textHelpAppendable1.setLeftPad((int) '4');
        org.apache.commons.cli.DefaultParser.Builder builder11 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser12 = builder11.get();
        org.apache.commons.cli.Options options13 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray17 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties18 = null;
        org.apache.commons.cli.CommandLine commandLine20 = defaultParser12.parse(options13, strArray17, properties18, false);
        java.lang.Object obj22 = commandLine20.getOptionObject('4');
        java.lang.Object obj24 = commandLine20.getOptionObject('4');
        java.lang.Object obj26 = commandLine20.getOptionObject('\000');
        java.lang.String[] strArray27 = commandLine20.getArgs();
        // The following exception was thrown during execution in test generation
        try {
            textHelpAppendable1.appendParagraphFormat("[ Option arg :: null :: class java.lang.String ]", (java.lang.Object[]) strArray27);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(builder11);
        org.junit.Assert.assertNotNull(defaultParser12);
        org.junit.Assert.assertNotNull(strArray17);
        org.junit.Assert.assertArrayEquals(strArray17, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine20);
        org.junit.Assert.assertNull(obj22);
        org.junit.Assert.assertNull(obj24);
        org.junit.Assert.assertNull(obj26);
        org.junit.Assert.assertNotNull(strArray27);
        org.junit.Assert.assertArrayEquals(strArray27, new java.lang.String[] { "", "[--]", "hi!" });
    }

    @Test
    public void test305() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test305");
        org.apache.commons.cli.ParseException parseException1 = new org.apache.commons.cli.ParseException(" ");
    }

    @Test
    public void test306() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test306");
        org.apache.commons.cli.Option option1 = org.apache.commons.cli.OptionBuilder.create("arg");
        option1.setRequired(true);
        boolean boolean4 = option1.hasArgName();
        java.lang.String str5 = option1.getArgName();
        org.junit.Assert.assertNotNull(option1);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertNull(str5);
    }

    @Test
    public void test307() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test307");
        org.apache.commons.cli.Option.Builder builder0 = org.apache.commons.cli.Option.builder();
        org.apache.commons.cli.Option.Builder builder1 = builder0.deprecated();
        org.apache.commons.cli.Option.Builder builder3 = builder0.optionalArg(false);
        org.apache.commons.cli.Option.Builder builder5 = builder3.longOpt("");
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(builder1);
        org.junit.Assert.assertNotNull(builder3);
        org.junit.Assert.assertNotNull(builder5);
    }

    @Test
    public void test308() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test308");
        org.apache.commons.cli.Option option1 = org.apache.commons.cli.OptionBuilder.create('\000');
        org.junit.Assert.assertNotNull(option1);
    }

    @Test
    public void test309() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test309");
        org.apache.commons.cli.DefaultParser.Builder builder0 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser1 = builder0.get();
        org.apache.commons.cli.Options options2 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties7 = null;
        org.apache.commons.cli.CommandLine commandLine9 = defaultParser1.parse(options2, strArray6, properties7, false);
        java.lang.Object obj11 = commandLine9.getOptionObject('4');
        org.apache.commons.cli.OptionBuilder optionBuilder13 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs();
        org.apache.commons.cli.OptionBuilder optionBuilder15 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs((int) (byte) 1);
        org.apache.commons.cli.OptionBuilder optionBuilder17 = org.apache.commons.cli.OptionBuilder.withLongOpt("");
        org.apache.commons.cli.OptionBuilder optionBuilder19 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs((int) (byte) 1);
        org.apache.commons.cli.OptionBuilder optionBuilder20 = org.apache.commons.cli.OptionBuilder.hasArgs();
        org.apache.commons.cli.OptionBuilder optionBuilder21 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs();
        org.apache.commons.cli.OptionBuilder[] optionBuilderArray22 = new org.apache.commons.cli.OptionBuilder[] { optionBuilder13, optionBuilder15, optionBuilder17, optionBuilder19, optionBuilder20, optionBuilder21 };
        org.apache.commons.cli.OptionBuilder[] optionBuilderArray23 = commandLine9.getParsedOptionValues('#', optionBuilderArray22);
        org.apache.commons.cli.Option option24 = null;
        java.util.Comparator<org.apache.commons.cli.Option> optionComparator25 = org.apache.commons.cli.help.AbstractHelpFormatter.DEFAULT_COMPARATOR;
        java.util.Comparator<org.apache.commons.cli.Option> optionComparator26 = commandLine9.getParsedOptionValue(option24, optionComparator25);
        java.lang.String[] strArray28 = commandLine9.getOptionValues("[ Option arg :: null :: class java.lang.String ]");
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(defaultParser1);
        org.junit.Assert.assertNotNull(strArray6);
        org.junit.Assert.assertArrayEquals(strArray6, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine9);
        org.junit.Assert.assertNull(obj11);
        org.junit.Assert.assertNotNull(optionBuilder13);
        org.junit.Assert.assertNotNull(optionBuilder15);
        org.junit.Assert.assertNotNull(optionBuilder17);
        org.junit.Assert.assertNotNull(optionBuilder19);
        org.junit.Assert.assertNotNull(optionBuilder20);
        org.junit.Assert.assertNotNull(optionBuilder21);
        org.junit.Assert.assertNotNull(optionBuilderArray22);
        org.junit.Assert.assertNotNull(optionBuilderArray23);
        org.junit.Assert.assertNotNull(optionComparator25);
        org.junit.Assert.assertNotNull(optionComparator26);
        org.junit.Assert.assertNull(strArray28);
    }

    @Test
    public void test310() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test310");
        org.apache.commons.cli.OptionBuilder optionBuilder1 = org.apache.commons.cli.OptionBuilder.withDescription(", ");
        org.junit.Assert.assertNotNull(optionBuilder1);
    }

    @Test
    public void test311() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test311");
        org.apache.commons.cli.DefaultParser.Builder builder0 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser1 = builder0.get();
        org.apache.commons.cli.Options options2 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties7 = null;
        org.apache.commons.cli.CommandLine commandLine9 = defaultParser1.parse(options2, strArray6, properties7, false);
        org.apache.commons.cli.DefaultParser.Builder builder11 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser12 = builder11.get();
        org.apache.commons.cli.Options options13 = new org.apache.commons.cli.Options();
        org.apache.commons.cli.DefaultParser.Builder builder14 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser15 = builder14.get();
        org.apache.commons.cli.Options options16 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray20 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties21 = null;
        org.apache.commons.cli.CommandLine commandLine23 = defaultParser15.parse(options16, strArray20, properties21, false);
        org.apache.commons.cli.DefaultParser.Builder builder24 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser25 = builder24.get();
        org.apache.commons.cli.Options options26 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray30 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties31 = null;
        org.apache.commons.cli.CommandLine commandLine33 = defaultParser25.parse(options26, strArray30, properties31, false);
        java.lang.Object obj35 = commandLine33.getOptionObject('4');
        org.apache.commons.cli.Option option36 = null;
        java.util.Properties properties37 = commandLine33.getOptionProperties(option36);
        org.apache.commons.cli.CommandLine commandLine39 = defaultParser12.parse(options13, strArray20, properties37, false);
        org.apache.commons.cli.CommandLine.Builder builder41 = new org.apache.commons.cli.CommandLine.Builder();
        org.apache.commons.cli.CommandLine commandLine42 = commandLine39.getParsedOptionValue(' ', (java.util.function.Supplier<org.apache.commons.cli.CommandLine>) builder41);
        org.apache.commons.cli.Option option43 = null;
        org.apache.commons.cli.CommandLine.Builder builder44 = builder41.addOption(option43);
        org.apache.commons.cli.CommandLine commandLine45 = commandLine9.getParsedOptionValue('#', (java.util.function.Supplier<org.apache.commons.cli.CommandLine>) builder41);
        java.util.function.Supplier<java.lang.String> strSupplier47 = null;
        java.lang.String str48 = commandLine45.getOptionValue('4', strSupplier47);
        java.lang.String str51 = commandLine45.getOptionValue('#', "[ Options: [ short {} ] [ long {} ]");
        java.lang.String[] strArray53 = commandLine45.getOptionValues('4');
        org.apache.commons.cli.CommandLine.Builder builder55 = new org.apache.commons.cli.CommandLine.Builder();
        org.apache.commons.cli.CommandLine commandLine56 = builder55.build();
        org.apache.commons.cli.CommandLine commandLine57 = builder55.build();
        org.apache.commons.cli.CommandLine.Builder builder59 = builder55.addArg("[--]");
        org.apache.commons.cli.CommandLine commandLine60 = commandLine45.getParsedOptionValue("hi!", (java.util.function.Supplier<org.apache.commons.cli.CommandLine>) builder55);
        org.apache.commons.cli.Option option62 = null;
        org.apache.commons.cli.Option option63 = commandLine45.getParsedOptionValue(' ', option62);
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(defaultParser1);
        org.junit.Assert.assertNotNull(strArray6);
        org.junit.Assert.assertArrayEquals(strArray6, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine9);
        org.junit.Assert.assertNotNull(builder11);
        org.junit.Assert.assertNotNull(defaultParser12);
        org.junit.Assert.assertNotNull(builder14);
        org.junit.Assert.assertNotNull(defaultParser15);
        org.junit.Assert.assertNotNull(strArray20);
        org.junit.Assert.assertArrayEquals(strArray20, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine23);
        org.junit.Assert.assertNotNull(builder24);
        org.junit.Assert.assertNotNull(defaultParser25);
        org.junit.Assert.assertNotNull(strArray30);
        org.junit.Assert.assertArrayEquals(strArray30, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine33);
        org.junit.Assert.assertNull(obj35);
        org.junit.Assert.assertNotNull(properties37);
        org.junit.Assert.assertNotNull(commandLine39);
        org.junit.Assert.assertNotNull(commandLine42);
        org.junit.Assert.assertNotNull(builder44);
        org.junit.Assert.assertNotNull(commandLine45);
        org.junit.Assert.assertNull(str48);
        org.junit.Assert.assertEquals("'" + str51 + "' != '" + "[ Options: [ short {} ] [ long {} ]" + "'", str51, "[ Options: [ short {} ] [ long {} ]");
        org.junit.Assert.assertNull(strArray53);
        org.junit.Assert.assertNotNull(commandLine56);
        org.junit.Assert.assertNotNull(commandLine57);
        org.junit.Assert.assertNotNull(builder59);
        org.junit.Assert.assertNotNull(commandLine60);
        org.junit.Assert.assertNull(option63);
    }

    @Test
    public void test312() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test312");
        org.apache.commons.cli.DefaultParser.Builder builder0 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser1 = builder0.get();
        org.apache.commons.cli.Options options2 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties7 = null;
        org.apache.commons.cli.CommandLine commandLine9 = defaultParser1.parse(options2, strArray6, properties7, false);
        java.lang.Object obj11 = commandLine9.getOptionObject('4');
        org.apache.commons.cli.OptionGroup optionGroup12 = null;
        java.lang.String str14 = commandLine9.getOptionValue(optionGroup12, "[--]");
        java.util.function.Supplier<java.lang.String> strSupplier16 = null;
        java.lang.String str17 = commandLine9.getOptionValue("hi!", strSupplier16);
        org.apache.commons.cli.Option option19 = org.apache.commons.cli.OptionBuilder.create("arg");
        java.util.List<java.lang.String> strList20 = option19.getValuesList();
        boolean boolean21 = commandLine9.hasOption(option19);
        org.apache.commons.cli.DefaultParser.Builder builder23 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser24 = builder23.get();
        org.apache.commons.cli.Options options25 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray29 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties30 = null;
        org.apache.commons.cli.CommandLine commandLine32 = defaultParser24.parse(options25, strArray29, properties30, false);
        java.lang.Object obj34 = commandLine32.getOptionObject('4');
        java.lang.Object obj36 = commandLine32.getOptionObject('4');
        org.apache.commons.cli.OptionGroup optionGroup37 = new org.apache.commons.cli.OptionGroup();
        boolean boolean38 = optionGroup37.isRequired();
        boolean boolean39 = commandLine32.hasOption(optionGroup37);
        org.apache.commons.cli.Option[] optionArray40 = commandLine32.getOptions();
        org.apache.commons.cli.Option[] optionArray41 = commandLine9.getParsedOptionValue('#', optionArray40);
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(defaultParser1);
        org.junit.Assert.assertNotNull(strArray6);
        org.junit.Assert.assertArrayEquals(strArray6, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine9);
        org.junit.Assert.assertNull(obj11);
        org.junit.Assert.assertEquals("'" + str14 + "' != '" + "[--]" + "'", str14, "[--]");
        org.junit.Assert.assertNull(str17);
        org.junit.Assert.assertNotNull(option19);
        org.junit.Assert.assertNotNull(strList20);
        org.junit.Assert.assertTrue("'" + boolean21 + "' != '" + false + "'", boolean21 == false);
        org.junit.Assert.assertNotNull(builder23);
        org.junit.Assert.assertNotNull(defaultParser24);
        org.junit.Assert.assertNotNull(strArray29);
        org.junit.Assert.assertArrayEquals(strArray29, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine32);
        org.junit.Assert.assertNull(obj34);
        org.junit.Assert.assertNull(obj36);
        org.junit.Assert.assertTrue("'" + boolean38 + "' != '" + false + "'", boolean38 == false);
        org.junit.Assert.assertTrue("'" + boolean39 + "' != '" + false + "'", boolean39 == false);
        org.junit.Assert.assertNotNull(optionArray40);
        org.junit.Assert.assertArrayEquals(optionArray40, new org.apache.commons.cli.Option[] {});
        org.junit.Assert.assertNotNull(optionArray41);
        org.junit.Assert.assertArrayEquals(optionArray41, new org.apache.commons.cli.Option[] {});
    }

    @Test
    public void test313() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test313");
        java.lang.String str0 = org.apache.commons.cli.HelpFormatter.DEFAULT_SYNTAX_PREFIX;
        org.junit.Assert.assertEquals("'" + str0 + "' != '" + "usage: " + "'", str0, "usage: ");
    }

    @Test
    public void test314() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test314");
        org.apache.commons.cli.HelpFormatter helpFormatter0 = new org.apache.commons.cli.HelpFormatter();
        org.apache.commons.cli.Options options2 = new org.apache.commons.cli.Options();
        org.apache.commons.cli.OptionGroup optionGroup3 = new org.apache.commons.cli.OptionGroup();
        java.util.Collection<org.apache.commons.cli.Option> optionCollection4 = optionGroup3.getOptions();
        boolean boolean5 = optionGroup3.isSelected();
        org.apache.commons.cli.Options options6 = options2.addOptionGroup(optionGroup3);
        helpFormatter0.printHelp("usage: ", options2);
        helpFormatter0.setWidth((int) 'a');
        org.apache.commons.cli.DefaultParser.Builder builder12 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser13 = builder12.get();
        org.apache.commons.cli.Options options14 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray18 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties19 = null;
        org.apache.commons.cli.CommandLine commandLine21 = defaultParser13.parse(options14, strArray18, properties19, false);
        boolean boolean23 = options14.hasShortOption("arg");
        org.apache.commons.cli.Option option25 = org.apache.commons.cli.OptionBuilder.create("arg");
        option25.setRequired(true);
        boolean boolean28 = option25.hasArgName();
        org.apache.commons.cli.Options options29 = options14.addOption(option25);
        helpFormatter0.printHelp(" ", "[ Option arg :: null :: class java.lang.String ]", options29, "hi!");
        helpFormatter0.setDescPadding(2147483647);
        helpFormatter0.setWidth(35);
        org.junit.Assert.assertNotNull(optionCollection4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertNotNull(options6);
        org.junit.Assert.assertNotNull(builder12);
        org.junit.Assert.assertNotNull(defaultParser13);
        org.junit.Assert.assertNotNull(strArray18);
        org.junit.Assert.assertArrayEquals(strArray18, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine21);
        org.junit.Assert.assertTrue("'" + boolean23 + "' != '" + false + "'", boolean23 == false);
        org.junit.Assert.assertNotNull(option25);
        org.junit.Assert.assertTrue("'" + boolean28 + "' != '" + false + "'", boolean28 == false);
        org.junit.Assert.assertNotNull(options29);
    }

    @Test
    public void test315() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test315");
        org.apache.commons.cli.help.OptionFormatter.Builder builder0 = org.apache.commons.cli.help.OptionFormatter.builder();
        org.apache.commons.cli.help.OptionFormatter.Builder builder2 = builder0.setOptSeparator("--");
        org.apache.commons.cli.help.OptionFormatter.Builder builder4 = builder2.setLongOptPrefix("");
        org.apache.commons.cli.help.OptionFormatter.Builder builder6 = builder4.setOptSeparator("[]");
        org.apache.commons.cli.help.OptionFormatter.Builder builder8 = builder4.setOptArgSeparator(" ");
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(builder2);
        org.junit.Assert.assertNotNull(builder4);
        org.junit.Assert.assertNotNull(builder6);
        org.junit.Assert.assertNotNull(builder8);
    }

    @Test
    public void test316() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test316");
        org.apache.commons.cli.DefaultParser.Builder builder0 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser1 = builder0.get();
        org.apache.commons.cli.Options options2 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties7 = null;
        org.apache.commons.cli.CommandLine commandLine9 = defaultParser1.parse(options2, strArray6, properties7, false);
        org.apache.commons.cli.DefaultParser.Builder builder11 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser12 = builder11.get();
        org.apache.commons.cli.Options options13 = new org.apache.commons.cli.Options();
        org.apache.commons.cli.DefaultParser.Builder builder14 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser15 = builder14.get();
        org.apache.commons.cli.Options options16 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray20 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties21 = null;
        org.apache.commons.cli.CommandLine commandLine23 = defaultParser15.parse(options16, strArray20, properties21, false);
        org.apache.commons.cli.DefaultParser.Builder builder24 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser25 = builder24.get();
        org.apache.commons.cli.Options options26 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray30 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties31 = null;
        org.apache.commons.cli.CommandLine commandLine33 = defaultParser25.parse(options26, strArray30, properties31, false);
        java.lang.Object obj35 = commandLine33.getOptionObject('4');
        org.apache.commons.cli.Option option36 = null;
        java.util.Properties properties37 = commandLine33.getOptionProperties(option36);
        org.apache.commons.cli.CommandLine commandLine39 = defaultParser12.parse(options13, strArray20, properties37, false);
        org.apache.commons.cli.CommandLine.Builder builder41 = new org.apache.commons.cli.CommandLine.Builder();
        org.apache.commons.cli.CommandLine commandLine42 = commandLine39.getParsedOptionValue(' ', (java.util.function.Supplier<org.apache.commons.cli.CommandLine>) builder41);
        org.apache.commons.cli.Option option43 = null;
        org.apache.commons.cli.CommandLine.Builder builder44 = builder41.addOption(option43);
        org.apache.commons.cli.CommandLine commandLine45 = commandLine9.getParsedOptionValue('#', (java.util.function.Supplier<org.apache.commons.cli.CommandLine>) builder41);
        java.util.function.Supplier<java.lang.String> strSupplier47 = null;
        java.lang.String str48 = commandLine45.getOptionValue('4', strSupplier47);
        java.lang.String str51 = commandLine45.getOptionValue('#', "[ Options: [ short {} ] [ long {} ]");
        java.lang.String[] strArray53 = commandLine45.getOptionValues('4');
        java.util.function.Supplier<java.lang.String> strSupplier55 = null;
        java.lang.String str56 = commandLine45.getOptionValue("[ Option arg :: null :: class java.lang.String ]", strSupplier55);
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(defaultParser1);
        org.junit.Assert.assertNotNull(strArray6);
        org.junit.Assert.assertArrayEquals(strArray6, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine9);
        org.junit.Assert.assertNotNull(builder11);
        org.junit.Assert.assertNotNull(defaultParser12);
        org.junit.Assert.assertNotNull(builder14);
        org.junit.Assert.assertNotNull(defaultParser15);
        org.junit.Assert.assertNotNull(strArray20);
        org.junit.Assert.assertArrayEquals(strArray20, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine23);
        org.junit.Assert.assertNotNull(builder24);
        org.junit.Assert.assertNotNull(defaultParser25);
        org.junit.Assert.assertNotNull(strArray30);
        org.junit.Assert.assertArrayEquals(strArray30, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine33);
        org.junit.Assert.assertNull(obj35);
        org.junit.Assert.assertNotNull(properties37);
        org.junit.Assert.assertNotNull(commandLine39);
        org.junit.Assert.assertNotNull(commandLine42);
        org.junit.Assert.assertNotNull(builder44);
        org.junit.Assert.assertNotNull(commandLine45);
        org.junit.Assert.assertNull(str48);
        org.junit.Assert.assertEquals("'" + str51 + "' != '" + "[ Options: [ short {} ] [ long {} ]" + "'", str51, "[ Options: [ short {} ] [ long {} ]");
        org.junit.Assert.assertNull(strArray53);
        org.junit.Assert.assertNull(str56);
    }

    @Test
    public void test317() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test317");
        org.apache.commons.cli.DeprecatedAttributes.Builder builder0 = new org.apache.commons.cli.DeprecatedAttributes.Builder();
        org.apache.commons.cli.DeprecatedAttributes deprecatedAttributes1 = builder0.get();
        java.lang.String str2 = deprecatedAttributes1.getSince();
        java.lang.String str3 = deprecatedAttributes1.toString();
        boolean boolean4 = deprecatedAttributes1.isForRemoval();
        org.junit.Assert.assertNotNull(deprecatedAttributes1);
        org.junit.Assert.assertEquals("'" + str2 + "' != '" + "" + "'", str2, "");
        org.junit.Assert.assertEquals("'" + str3 + "' != '" + "Deprecated" + "'", str3, "Deprecated");
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
    }

    @Test
    public void test318() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test318");
        org.apache.commons.cli.HelpFormatter helpFormatter0 = new org.apache.commons.cli.HelpFormatter();
        org.apache.commons.cli.Options options2 = new org.apache.commons.cli.Options();
        org.apache.commons.cli.OptionGroup optionGroup3 = new org.apache.commons.cli.OptionGroup();
        java.util.Collection<org.apache.commons.cli.Option> optionCollection4 = optionGroup3.getOptions();
        boolean boolean5 = optionGroup3.isSelected();
        org.apache.commons.cli.Options options6 = options2.addOptionGroup(optionGroup3);
        helpFormatter0.printHelp("usage: ", options2);
        helpFormatter0.setWidth((int) 'a');
        org.apache.commons.cli.DefaultParser.Builder builder12 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser13 = builder12.get();
        org.apache.commons.cli.Options options14 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray18 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties19 = null;
        org.apache.commons.cli.CommandLine commandLine21 = defaultParser13.parse(options14, strArray18, properties19, false);
        boolean boolean23 = options14.hasShortOption("arg");
        org.apache.commons.cli.Option option25 = org.apache.commons.cli.OptionBuilder.create("arg");
        option25.setRequired(true);
        boolean boolean28 = option25.hasArgName();
        org.apache.commons.cli.Options options29 = options14.addOption(option25);
        helpFormatter0.printHelp(" ", "[ Option arg :: null :: class java.lang.String ]", options29, "hi!");
        java.lang.String str32 = helpFormatter0.getSyntaxPrefix();
        helpFormatter0.setOptPrefix("");
        int int35 = helpFormatter0.defaultLeftPad;
        org.junit.Assert.assertNotNull(optionCollection4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertNotNull(options6);
        org.junit.Assert.assertNotNull(builder12);
        org.junit.Assert.assertNotNull(defaultParser13);
        org.junit.Assert.assertNotNull(strArray18);
        org.junit.Assert.assertArrayEquals(strArray18, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine21);
        org.junit.Assert.assertTrue("'" + boolean23 + "' != '" + false + "'", boolean23 == false);
        org.junit.Assert.assertNotNull(option25);
        org.junit.Assert.assertTrue("'" + boolean28 + "' != '" + false + "'", boolean28 == false);
        org.junit.Assert.assertNotNull(options29);
        org.junit.Assert.assertEquals("'" + str32 + "' != '" + "usage: " + "'", str32, "usage: ");
        org.junit.Assert.assertTrue("'" + int35 + "' != '" + 1 + "'", int35 == 1);
    }

    @Test
    public void test319() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test319");
        org.apache.commons.cli.Option.Builder builder0 = org.apache.commons.cli.Option.builder();
        org.apache.commons.cli.Option.Builder builder1 = builder0.deprecated();
        org.apache.commons.cli.Option.Builder builder3 = builder0.numberOfArgs((int) (short) -1);
        java.lang.Class<java.net.URL> uRLClass4 = org.apache.commons.cli.PatternOptionBuilder.URL_VALUE;
        org.apache.commons.cli.Option.Builder builder5 = builder0.type(uRLClass4);
        org.apache.commons.cli.OptionBuilder optionBuilder6 = org.apache.commons.cli.OptionBuilder.withType(uRLClass4);
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(builder1);
        org.junit.Assert.assertNotNull(builder3);
        org.junit.Assert.assertNotNull(uRLClass4);
        org.junit.Assert.assertNotNull(builder5);
        org.junit.Assert.assertNotNull(optionBuilder6);
    }

    @Test
    public void test320() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test320");
        org.apache.commons.cli.HelpFormatter helpFormatter0 = new org.apache.commons.cli.HelpFormatter();
        org.apache.commons.cli.Options options2 = new org.apache.commons.cli.Options();
        org.apache.commons.cli.OptionGroup optionGroup3 = new org.apache.commons.cli.OptionGroup();
        java.util.Collection<org.apache.commons.cli.Option> optionCollection4 = optionGroup3.getOptions();
        boolean boolean5 = optionGroup3.isSelected();
        org.apache.commons.cli.Options options6 = options2.addOptionGroup(optionGroup3);
        helpFormatter0.printHelp("usage: ", options2);
        helpFormatter0.setWidth((int) 'a');
        java.lang.String str10 = helpFormatter0.getSyntaxPrefix();
        helpFormatter0.setWidth(0);
        java.lang.String str13 = helpFormatter0.defaultLongOptPrefix;
        helpFormatter0.defaultOptPrefix = "--";
        org.junit.Assert.assertNotNull(optionCollection4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertNotNull(options6);
        org.junit.Assert.assertEquals("'" + str10 + "' != '" + "usage: " + "'", str10, "usage: ");
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "--" + "'", str13, "--");
    }

    @Test
    public void test321() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test321");
        org.apache.commons.cli.OptionGroup optionGroup0 = new org.apache.commons.cli.OptionGroup();
        java.util.Collection<org.apache.commons.cli.Option> optionCollection1 = optionGroup0.getOptions();
        boolean boolean2 = optionGroup0.isSelected();
        org.apache.commons.cli.DefaultParser.Builder builder3 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser4 = builder3.get();
        org.apache.commons.cli.Options options5 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray9 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties10 = null;
        org.apache.commons.cli.CommandLine commandLine12 = defaultParser4.parse(options5, strArray9, properties10, false);
        java.lang.Object obj14 = commandLine12.getOptionObject('4');
        org.apache.commons.cli.OptionGroup optionGroup15 = null;
        java.lang.String str17 = commandLine12.getOptionValue(optionGroup15, "[--]");
        java.util.function.Supplier<java.lang.String> strSupplier19 = null;
        java.lang.String str20 = commandLine12.getOptionValue("hi!", strSupplier19);
        org.apache.commons.cli.Option option22 = org.apache.commons.cli.OptionBuilder.create("arg");
        java.util.List<java.lang.String> strList23 = option22.getValuesList();
        boolean boolean24 = commandLine12.hasOption(option22);
        optionGroup0.setSelected(option22);
        org.apache.commons.cli.DefaultParser.Builder builder26 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser27 = builder26.get();
        org.apache.commons.cli.Options options28 = new org.apache.commons.cli.Options();
        org.apache.commons.cli.DefaultParser.Builder builder29 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser30 = builder29.get();
        org.apache.commons.cli.Options options31 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray35 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties36 = null;
        org.apache.commons.cli.CommandLine commandLine38 = defaultParser30.parse(options31, strArray35, properties36, false);
        org.apache.commons.cli.DefaultParser.Builder builder39 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser40 = builder39.get();
        org.apache.commons.cli.Options options41 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray45 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties46 = null;
        org.apache.commons.cli.CommandLine commandLine48 = defaultParser40.parse(options41, strArray45, properties46, false);
        java.lang.Object obj50 = commandLine48.getOptionObject('4');
        org.apache.commons.cli.Option option51 = null;
        java.util.Properties properties52 = commandLine48.getOptionProperties(option51);
        org.apache.commons.cli.CommandLine commandLine54 = defaultParser27.parse(options28, strArray35, properties52, false);
        org.apache.commons.cli.CommandLine.Builder builder56 = new org.apache.commons.cli.CommandLine.Builder();
        org.apache.commons.cli.CommandLine commandLine57 = commandLine54.getParsedOptionValue(' ', (java.util.function.Supplier<org.apache.commons.cli.CommandLine>) builder56);
        java.util.function.Consumer<org.apache.commons.cli.Option> optionConsumer58 = null;
        org.apache.commons.cli.CommandLine.Builder builder59 = builder56.setDeprecatedHandler(optionConsumer58);
        org.apache.commons.cli.CommandLine commandLine60 = builder56.get();
        org.apache.commons.cli.DefaultParser.Builder builder61 = org.apache.commons.cli.DefaultParser.builder();
        org.apache.commons.cli.DefaultParser defaultParser62 = builder61.get();
        org.apache.commons.cli.Options options63 = new org.apache.commons.cli.Options();
        java.lang.String[] strArray67 = new java.lang.String[] { "", "[--]", "hi!" };
        java.util.Properties properties68 = null;
        org.apache.commons.cli.CommandLine commandLine70 = defaultParser62.parse(options63, strArray67, properties68, false);
        java.lang.Object obj72 = commandLine70.getOptionObject('4');
        org.apache.commons.cli.OptionBuilder optionBuilder74 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs();
        org.apache.commons.cli.OptionBuilder optionBuilder76 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs((int) (byte) 1);
        org.apache.commons.cli.OptionBuilder optionBuilder78 = org.apache.commons.cli.OptionBuilder.withLongOpt("");
        org.apache.commons.cli.OptionBuilder optionBuilder80 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs((int) (byte) 1);
        org.apache.commons.cli.OptionBuilder optionBuilder81 = org.apache.commons.cli.OptionBuilder.hasArgs();
        org.apache.commons.cli.OptionBuilder optionBuilder82 = org.apache.commons.cli.OptionBuilder.hasOptionalArgs();
        org.apache.commons.cli.OptionBuilder[] optionBuilderArray83 = new org.apache.commons.cli.OptionBuilder[] { optionBuilder74, optionBuilder76, optionBuilder78, optionBuilder80, optionBuilder81, optionBuilder82 };
        org.apache.commons.cli.OptionBuilder[] optionBuilderArray84 = commandLine70.getParsedOptionValues('#', optionBuilderArray83);
        org.apache.commons.cli.OptionGroup optionGroup85 = new org.apache.commons.cli.OptionGroup();
        boolean boolean86 = optionGroup85.isRequired();
        java.lang.String str88 = commandLine70.getOptionValue(optionGroup85, "");
        boolean boolean89 = optionGroup85.isRequired();
        org.apache.commons.cli.Option option91 = org.apache.commons.cli.OptionBuilder.create("arg");
        option91.setRequired(true);
        boolean boolean94 = option91.hasArgName();
        org.apache.commons.cli.OptionGroup optionGroup95 = optionGroup85.addOption(option91);
        java.lang.String str97 = commandLine60.getOptionValue(option91, "arg");
        org.apache.commons.cli.AlreadySelectedException alreadySelectedException98 = new org.apache.commons.cli.AlreadySelectedException(optionGroup0, option91);
        org.apache.commons.cli.OptionGroup optionGroup99 = alreadySelectedException98.getOptionGroup();
        org.junit.Assert.assertNotNull(optionCollection1);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(builder3);
        org.junit.Assert.assertNotNull(defaultParser4);
        org.junit.Assert.assertNotNull(strArray9);
        org.junit.Assert.assertArrayEquals(strArray9, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine12);
        org.junit.Assert.assertNull(obj14);
        org.junit.Assert.assertEquals("'" + str17 + "' != '" + "[--]" + "'", str17, "[--]");
        org.junit.Assert.assertNull(str20);
        org.junit.Assert.assertNotNull(option22);
        org.junit.Assert.assertNotNull(strList23);
        org.junit.Assert.assertTrue("'" + boolean24 + "' != '" + false + "'", boolean24 == false);
        org.junit.Assert.assertNotNull(builder26);
        org.junit.Assert.assertNotNull(defaultParser27);
        org.junit.Assert.assertNotNull(builder29);
        org.junit.Assert.assertNotNull(defaultParser30);
        org.junit.Assert.assertNotNull(strArray35);
        org.junit.Assert.assertArrayEquals(strArray35, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine38);
        org.junit.Assert.assertNotNull(builder39);
        org.junit.Assert.assertNotNull(defaultParser40);
        org.junit.Assert.assertNotNull(strArray45);
        org.junit.Assert.assertArrayEquals(strArray45, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine48);
        org.junit.Assert.assertNull(obj50);
        org.junit.Assert.assertNotNull(properties52);
        org.junit.Assert.assertNotNull(commandLine54);
        org.junit.Assert.assertNotNull(commandLine57);
        org.junit.Assert.assertNotNull(builder59);
        org.junit.Assert.assertNotNull(commandLine60);
        org.junit.Assert.assertNotNull(builder61);
        org.junit.Assert.assertNotNull(defaultParser62);
        org.junit.Assert.assertNotNull(strArray67);
        org.junit.Assert.assertArrayEquals(strArray67, new java.lang.String[] { "", "[--]", "hi!" });
        org.junit.Assert.assertNotNull(commandLine70);
        org.junit.Assert.assertNull(obj72);
        org.junit.Assert.assertNotNull(optionBuilder74);
        org.junit.Assert.assertNotNull(optionBuilder76);
        org.junit.Assert.assertNotNull(optionBuilder78);
        org.junit.Assert.assertNotNull(optionBuilder80);
        org.junit.Assert.assertNotNull(optionBuilder81);
        org.junit.Assert.assertNotNull(optionBuilder82);
        org.junit.Assert.assertNotNull(optionBuilderArray83);
        org.junit.Assert.assertNotNull(optionBuilderArray84);
        org.junit.Assert.assertTrue("'" + boolean86 + "' != '" + false + "'", boolean86 == false);
        org.junit.Assert.assertEquals("'" + str88 + "' != '" + "" + "'", str88, "");
        org.junit.Assert.assertTrue("'" + boolean89 + "' != '" + false + "'", boolean89 == false);
        org.junit.Assert.assertNotNull(option91);
        org.junit.Assert.assertTrue("'" + boolean94 + "' != '" + false + "'", boolean94 == false);
        org.junit.Assert.assertNotNull(optionGroup95);
        org.junit.Assert.assertEquals("'" + str97 + "' != '" + "arg" + "'", str97, "arg");
        org.junit.Assert.assertNotNull(optionGroup99);
    }

    @Test
    public void test322() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test322");
        org.apache.commons.cli.Option option1 = org.apache.commons.cli.OptionBuilder.create("arg");
        option1.setRequired(true);
        boolean boolean4 = option1.hasArgName();
        boolean boolean5 = option1.hasValueSeparator();
        java.lang.String str6 = option1.getKey();
        org.junit.Assert.assertNotNull(option1);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "arg" + "'", str6, "arg");
    }
}

