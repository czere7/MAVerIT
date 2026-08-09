package tools.jackson.core.json;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.HashSet;
import java.lang.reflect.Field;

import org.junit.Test;
import org.mockito.Mockito;

import tools.jackson.core.JsonGenerator;
import tools.jackson.core.JsonParser;
import tools.jackson.core.TokenStreamLocation;

/**
 * Unit tests for {@link DupDetector}.
 */
public class DupDetectorTest {

    /** Helper to create a dummy parser that returns a stub location. */
    private JsonParser createMockParser() {
        JsonParser parser = mock(JsonParser.class);
        TokenStreamLocation loc = mock(TokenStreamLocation.class);
        when(parser.currentLocation()).thenReturn(loc);
        return parser;
    }

    /** Helper to create a dummy generator. */
    private JsonGenerator createMockGenerator() {
        return mock(JsonGenerator.class);
    }

    @Test
    public void testIsDupFirstAndSecondNames() {
        DupDetector det = DupDetector.rootDetector(createMockParser());

        // first name -> should not be duplicate
        assertFalse(det.isDup("foo"));
        // second occurrence of same name -> duplicate
        assertTrue(det.isDup("foo"));

        // second distinct name -> not duplicate
        assertFalse(det.isDup("bar"));
        // duplicate of second name -> true
        assertTrue(det.isDup("bar"));
    }

    @Test
    public void testIsDupThirdNameUsesSeenSet() {
        DupDetector det = DupDetector.rootDetector(createMockParser());

        // first two unique names
        assertFalse(det.isDup("foo"));  // _firstName
        assertFalse(det.isDup("bar"));  // _secondName

        // third distinct name, triggers creation of _seen set
        assertFalse(det.isDup("baz"));

        // Now duplicates should be detected via the set
        assertTrue(det.isDup("foo"));
        assertTrue(det.isDup("bar"));
        assertTrue(det.isDup("baz"));
    }

    @Test
    public void testResetClearsState() {
        DupDetector det = DupDetector.rootDetector(createMockParser());

        // populate some state
        assertFalse(det.isDup("one"));
        assertFalse(det.isDup("two"));
        assertFalse(det.isDup("three"));  // triggers _seen

        // Reset and verify fresh start
        det.reset();
        assertFalse(det.isDup("one"));
        assertTrue(det.isDup("one"));  // duplicate after reset
    }

    @Test
    public void testChildDetectorHasIndependentState() {
        JsonParser parser = createMockParser();

        DupDetector parent = DupDetector.rootDetector(parser);
        DupDetector child = parent.child();

        // Both share the same source reference
        assertSame(parent.getSource(), child.getSource());

        // Parent detects duplicate after first occurrence
        assertFalse(parent.isDup("alpha"));
        assertTrue(parent.isDup("alpha"));

        // Child is independent; its first call to "alpha" should not be a duplicate
        assertFalse(child.isDup("alpha"));
    }

    @Test
    public void testFindLocationReturnsParserLocation() {
        JsonParser parser = createMockParser();
        TokenStreamLocation expectedLoc = mock(TokenStreamLocation.class);
        when(parser.currentLocation()).thenReturn(expectedLoc);

        DupDetector det = DupDetector.rootDetector(parser);
        assertSame(expectedLoc, det.findLocation());
    }

    @Test
    public void testFindLocationForGeneratorReturnsNull() {
        JsonGenerator generator = createMockGenerator();
        DupDetector det = DupDetector.rootDetector(generator);
        assertNull(det.findLocation());
    }

    /* NEW TESTS ----------------------------------------------------------- */

    /**
     * Verify that {@link DupDetector#getSource()} returns the actual source
     * object and is never null for valid detectors.
     */
    @Test
    public void testGetSourceNonNull() {
        JsonParser parser = createMockParser();
        DupDetector detFromParser = DupDetector.rootDetector(parser);
        assertNotNull(detFromParser.getSource());
        assertSame(parser, detFromParser.getSource());

        JsonGenerator generator = createMockGenerator();
        DupDetector detFromGen = DupDetector.rootDetector(generator);
        assertNotNull(detFromGen.getSource());
        assertSame(generator, detFromGen.getSource());
    }

    /**
     * Use reflection to ensure that the first and second names are stored
     * correctly after distinct calls.  Mutations of the null‑check conditions
     * would break this invariant.
     */
    @Test
    public void testInternalStateAfterDistinctNames() throws Exception {
        DupDetector det = DupDetector.rootDetector(createMockParser());

        det.isDup("foo");   // first name
        det.isDup("bar");   // second distinct name

        Field fFirstName = DupDetector.class.getDeclaredField("_firstName");
        fFirstName.setAccessible(true);
        Object firstVal = fFirstName.get(det);
        assertEquals("foo", firstVal);

        Field fSecondName = DupDetector.class.getDeclaredField("_secondName");
        fSecondName.setAccessible(true);
        Object secondVal = fSecondName.get(det);
        assertEquals("bar", secondVal);
    }

    /**
     * After three distinct names, all duplicates should be detected correctly.
     * This also confirms that the internal state is consistent with expected behaviour.
     */
    @Test
    public void testDuplicateAfterThreeDistinctNames() {
        DupDetector det = DupDetector.rootDetector(createMockParser());

        assertFalse(det.isDup("foo"));   // first distinct
        assertFalse(det.isDup("bar"));   // second distinct
        assertFalse(det.isDup("baz"));   // third distinct, creates _seen

        assertTrue(det.isDup("foo"));
        assertTrue(det.isDup("bar"));
        assertTrue(det.isDup("baz"));
    }
}
