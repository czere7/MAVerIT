package org.apache.commons.cli;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.UnrecognizedOptionException;
import org.junit.Test;

import static org.junit.Assert.*;

public class BasicParserTest {

    @Test
    public void testFlattenEchoArguments() {
        BasicParser parser = new BasicParser();
        Options options = new Options();
        String[] args = new String[]{"arg1", "arg2"};
        String[] result = parser.flatten(options, args, false);
        assertSame("flatten should return the same array reference", args, result);
    }

    @Test
    public void testParseWithoutOptions() throws ParseException {
        BasicParser parser = new BasicParser();
        Options options = new Options();
        String[] args = new String[]{"foo", "bar"};
        CommandLine cmd = parser.parse(options, args);
        assertArrayEquals("Arguments should be preserved", args, cmd.getArgs());
    }

    @Test
    public void testParseWithOption() throws ParseException {
        BasicParser parser = new BasicParser();
        Options options = new Options();
        Option optA = new Option("a", true, "desc");
        options.addOption(optA);
        String[] args = new String[]{"-a", "value", "pos1"};
        CommandLine cmd = parser.parse(options, args);
        assertTrue("Option 'a' should be present", cmd.hasOption("a"));
        assertEquals("Option 'a' should have correct value", "value", cmd.getOptionValue("a"));
        assertArrayEquals("Positional arguments should be preserved", new String[]{"pos1"}, cmd.getArgs());
    }

    @Test(expected = UnrecognizedOptionException.class)
    public void testParseUnrecognizedOptionThrows() throws ParseException {
        BasicParser parser = new BasicParser();
        Options options = new Options();
        String[] args = new String[]{"-x"};
        parser.parse(options, args);
    }

    @Test
    public void testParseStopAtNonOptionTrue() throws ParseException {
        BasicParser parser = new BasicParser();
        Options options = new Options();
        String[] args = new String[]{"pos1", "-x", "pos2"};
        CommandLine cmd = parser.parse(options, args, true);
        assertArrayEquals("All tokens should be treated as arguments",
                new String[]{"pos1", "-x", "pos2"}, cmd.getArgs());
    }
}
