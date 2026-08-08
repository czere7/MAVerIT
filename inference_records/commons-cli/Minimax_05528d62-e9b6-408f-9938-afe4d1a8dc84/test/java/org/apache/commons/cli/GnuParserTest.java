package org.apache.commons.cli;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;

public class GnuParserTest {

    private GnuParser parser;

    @Before
    public void setUp() {
        parser = new GnuParser();
    }

    private String[] flatten(final Options options, final String[] arguments, final boolean stopAtNonOption) {
        return parser.flatten(options, arguments, stopAtNonOption);
    }

    @Test
    public void testFlattenEmptyArguments() {
        Options options = new Options();
        String[] result = flatten(options, new String[]{}, false);
        assertArrayEquals(new String[]{}, result);
    }

    @Test
    public void testFlattenDoubleDash() {
        Options options = new Options();
        String[] args = new String[]{"--"};
        String[] result = flatten(options, args, false);
        assertArrayEquals(new String[]{"--"}, result);
    }

    @Test
    public void testFlattenDoubleDashWithFollowingArgs() {
        Options options = new Options();
        String[] args = new String[]{"--", "-f", "bar"};
        String[] result = flatten(options, args, false);
        assertArrayEquals(new String[]{"--", "-f", "bar"}, result);
    }

    @Test
    public void testFlattenSingleDash() {
        Options options = new Options();
        String[] args = new String[]{"-"};
        String[] result = flatten(options, args, false);
        assertArrayEquals(new String[]{"-"}, result);
    }

    @Test
    public void testFlattenKnownLongOption() {
        Options options = new Options();
        options.addOption(Option.builder().longOpt("foo").build());
        
        String[] args = new String[]{"--foo"};
        String[] result = flatten(options, args, false);
        assertArrayEquals(new String[]{"--foo"}, result);
    }

    @Test
    public void testFlattenKnownShortOption() {
        Options options = new Options();
        options.addOption(Option.builder("f").build());
        
        String[] args = new String[]{"-f"};
        String[] result = flatten(options, args, false);
        assertArrayEquals(new String[]{"-f"}, result);
    }

    @Test
    public void testFlattenLongOptionWithValueEquals() {
        Options options = new Options();
        options.addOption(Option.builder().longOpt("foo").build());
        
        String[] args = new String[]{"--foo=bar"};
        String[] result = flatten(options, args, false);
        assertArrayEquals(new String[]{"--foo", "bar"}, result);
    }

    @Test
    public void testFlattenShortOptionWithValueEquals() {
        Options options = new Options();
        options.addOption(Option.builder("f").build());
        
        String[] args = new String[]{"-f=bar"};
        String[] result = flatten(options, args, false);
        assertArrayEquals(new String[]{"-f", "bar"}, result);
    }
    
    @Test
    public void testFlattenShortOptionWithValueNoEquals() {
        Options options = new Options();
        options.addOption(Option.builder("f").build());
        
        String[] args = new String[]{"-fbar"};
        String[] result = flatten(options, args, false);
        assertArrayEquals(new String[]{"-f", "bar"}, result);
    }

    @Test
    public void testFlattenSpecialPropertyFormat() {
        Options options = new Options();
        options.addOption(Option.builder("D").build());
        
        String[] args = new String[]{"-Dproperty=value"};
        String[] result = flatten(options, args, false);
        assertArrayEquals(new String[]{"-D", "property=value"}, result);
    }

    @Test
    public void testFlattenCombinedShortOptions() {
        Options options = new Options();
        options.addOption(Option.builder("a").build());
        
        String[] args = new String[]{"-abc"};
        String[] result = flatten(options, args, false);
        assertArrayEquals(new String[]{"-a", "bc"}, result);
    }

    @Test
    public void testFlattenUnknownOptionWithStop() {
        Options options = new Options();
        
        String[] args = new String[]{"-x", "foo", "bar"};
        String[] result = flatten(options, args, true);
        assertArrayEquals(new String[]{"-x", "foo", "bar"}, result);
    }

    @Test
    public void testFlattenUnknownOptionWithoutStop() {
        Options options = new Options();
        
        String[] args = new String[]{"-x", "foo", "bar"};
        String[] result = flatten(options, args, false);
        assertArrayEquals(new String[]{"-x", "foo", "bar"}, result);
    }

    @Test
    public void testFlattenNonOptionWithStop() {
        Options options = new Options();
        
        String[] args = new String[]{"foo", "-x", "bar"};
        String[] result = flatten(options, args, true);
        assertArrayEquals(new String[]{"foo", "-x", "bar"}, result);
    }

    @Test
    public void testFlattenNonOptionWithoutStop() {
        Options options = new Options();
        
        String[] args = new String[]{"foo", "bar"};
        String[] result = flatten(options, args, false);
        assertArrayEquals(new String[]{"foo", "bar"}, result);
    }

    @Test
    public void testFlattenWithNullElement() {
        Options options = new Options();
        options.addOption(Option.builder("f").build());
        
        String[] args = new String[]{"-f", null, "bar"};
        String[] result = flatten(options, args, false);
        assertArrayEquals(new String[]{"-f", "bar"}, result);
    }
}
