package org.apache.commons.cli;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertSame;

import org.junit.Test;

public class BasicParserTest {

    private static final class ExposedBasicParser extends BasicParser {
        String[] callFlatten(final Options options, final String[] arguments, final boolean stopAtNonOption) {
            return flatten(options, arguments, stopAtNonOption);
        }
    }

    @Test
    public void flattenReturnsTheOriginalArgumentsArray() {
        final String[] arguments = { "-a", "value", "input" };
        final ExposedBasicParser parser = new ExposedBasicParser();

        assertSame(arguments, parser.callFlatten(new Options(), arguments, false));
        assertSame(arguments, parser.callFlatten(new Options(), arguments, true));
    }

    @Test
    public void flattenDoesNotModifyArguments() {
        final String[] arguments = { "--name=value", "-", "--", "file" };
        final String[] original = arguments.clone();
        final ExposedBasicParser parser = new ExposedBasicParser();

        parser.callFlatten(null, arguments, false);

        assertArrayEquals(original, arguments);
    }

    @Test
    public void flattenReturnsNullWhenArgumentsAreNull() {
        final ExposedBasicParser parser = new ExposedBasicParser();

        assertSame(null, parser.callFlatten(null, null, false));
    }

    @Test
    public void flattenIgnoresOptionsAndStopAtNonOption() {
        final String[] arguments = { null, "", "-unknown", "value" };
        final Options options = new Options();
        final ExposedBasicParser parser = new ExposedBasicParser();

        assertSame(arguments, parser.callFlatten(options, arguments, false));
        assertSame(arguments, parser.callFlatten(options, arguments, true));
    }

    @Test
    public void flattenReturnsTheSameEmptyArgumentsArray() {
        final String[] arguments = {};
        final ExposedBasicParser parser = new ExposedBasicParser();

        assertSame(arguments, parser.callFlatten(new Options(), arguments, false));
    }
}
