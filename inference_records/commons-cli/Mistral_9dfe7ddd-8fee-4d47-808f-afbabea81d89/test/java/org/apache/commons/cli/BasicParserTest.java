package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;

public class BasicParserTest {

    @Test
    public void testConstructor() {
        BasicParser parser = new BasicParser();
        assertNotNull(parser);
    }

    @Test
    public void testFlattenWithEmptyArguments() {
        BasicParser parser = new BasicParser();
        String[] arguments = new String[0];
        String[] result = parser.flatten(new Options(), arguments, false);
        assertArrayEquals(arguments, result);
    }

    @Test
    public void testFlattenWithSingleArgument() {
        BasicParser parser = new BasicParser();
        String[] arguments = {"arg1"};
        String[] result = parser.flatten(new Options(), arguments, false);
        assertArrayEquals(arguments, result);
    }

    @Test
    public void testFlattenWithMultipleArguments() {
        BasicParser parser = new BasicParser();
        String[] arguments = {"arg1", "arg2", "arg3"};
        String[] result = parser.flatten(new Options(), arguments, false);
        assertArrayEquals(arguments, result);
    }

    @Test
    public void testFlattenWithNullArguments() {
        BasicParser parser = new BasicParser();
        String[] result = parser.flatten(new Options(), null, false);
        assertNull(result);
    }

    @Test
    public void testFlattenWithStopAtNonOptionFalse() {
        BasicParser parser = new BasicParser();
        String[] arguments = {"-a", "value", "non-option"};
        String[] result = parser.flatten(new Options(), arguments, false);
        assertArrayEquals(arguments, result);
    }

    @Test
    public void testFlattenWithStopAtNonOptionTrue() {
        BasicParser parser = new BasicParser();
        String[] arguments = {"-a", "value", "non-option"};
        String[] result = parser.flatten(new Options(), arguments, true);
        assertArrayEquals(arguments, result);
    }

    @Test
    public void testFlattenWithOptionsParameter() {
        BasicParser parser = new BasicParser();
        Options options = new Options();
        options.addOption("a", "alpha", false, "description");
        String[] arguments = {"-a", "value"};
        String[] result = parser.flatten(options, arguments, false);
        assertArrayEquals(arguments, result);
    }

    @Test
    public void testFlattenWithEmptyOptions() {
        BasicParser parser = new BasicParser();
        Options options = new Options();
        String[] arguments = {"-a", "value"};
        String[] result = parser.flatten(options, arguments, false);
        assertArrayEquals(arguments, result);
    }

    @Test
    public void testFlattenWithNullOptions() {
        BasicParser parser = new BasicParser();
        String[] arguments = {"-a", "value"};
        String[] result = parser.flatten(null, arguments, false);
        assertArrayEquals(arguments, result);
    }

    @Test
    public void testFlattenWithSpecialCharacters() {
        BasicParser parser = new BasicParser();
        String[] arguments = {"-a", "value", "non-option", "special@char#123"};
        String[] result = parser.flatten(new Options(), arguments, false);
        assertArrayEquals(arguments, result);
    }

    @Test
    public void testFlattenWithQuotedArguments() {
        BasicParser parser = new BasicParser();
        String[] arguments = {"-a", "\"value with spaces\"", "non-option"};
        String[] result = parser.flatten(new Options(), arguments, false);
        assertArrayEquals(arguments, result);
    }

    @Test
    public void testFlattenWithEmptyStringArguments() {
        BasicParser parser = new BasicParser();
        String[] arguments = {"", "", ""};
        String[] result = parser.flatten(new Options(), arguments, false);
        assertArrayEquals(arguments, result);
    }
}
