package tools.jackson.core.json;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import tools.jackson.core.JsonGenerator;
import tools.jackson.core.JsonParser;
import tools.jackson.core.TokenStreamLocation;

public class DupDetectorTest {

    @Test
    public void detectsDuplicatesAmongFirstTwoNames() {
        DupDetector detector = DupDetector.rootDetector((JsonGenerator) null);

        assertFalse(detector.isDup("first"));
        assertFalse(detector.isDup("second"));
        assertTrue(detector.isDup("first"));
        assertTrue(detector.isDup("second"));
    }

    @Test
    public void detectsDuplicatesAfterSeenSetIsCreated() {
        DupDetector detector = DupDetector.rootDetector((JsonGenerator) null);

        assertFalse(detector.isDup("one"));
        assertFalse(detector.isDup("two"));
        assertFalse(detector.isDup("three"));
        assertFalse(detector.isDup("four"));

        assertTrue(detector.isDup(new String("one")));
        assertTrue(detector.isDup(new String("three")));
        assertTrue(detector.isDup(new String("four")));
        assertFalse(detector.isDup("five"));
    }

    @Test
    public void namesAreTrackedWithinOneDetectorOnly() {
        DupDetector detector = DupDetector.rootDetector((JsonGenerator) null);
        DupDetector child = detector.child();

        assertFalse(detector.isDup("name"));
        assertFalse(child.isDup("name"));
        assertTrue(detector.isDup("name"));
        assertTrue(child.isDup("name"));
    }

    @Test
    public void childRetainsSourceButHasIndependentState() {
        DupDetector parent = DupDetector.rootDetector((JsonGenerator) null);
        DupDetector child = parent.child();

        assertNull(parent.getSource());
        assertNull(child.getSource());

        assertFalse(parent.isDup("parent"));
        assertFalse(child.isDup("child"));
        assertTrue(parent.isDup("parent"));
        assertTrue(child.isDup("child"));
    }

    @Test
    public void resetClearsAllPreviouslySeenNames() {
        DupDetector detector = DupDetector.rootDetector((JsonParser) null);

        assertFalse(detector.isDup("first"));
        assertFalse(detector.isDup("second"));
        assertFalse(detector.isDup("third"));
        assertTrue(detector.isDup("first"));

        detector.reset();

        assertFalse(detector.isDup("first"));
        assertFalse(detector.isDup("second"));
        assertFalse(detector.isDup("third"));
        assertTrue(detector.isDup("first"));
    }

    @Test
    public void generatorSourceHasNoLocation() {
        DupDetector detector = DupDetector.rootDetector((JsonGenerator) null);

        assertNull(detector.findLocation());
    }

    @Test
    public void parserSourceReturnsCurrentLocation() {
        JsonParser parser = mock(JsonParser.class);
        TokenStreamLocation location = mock(TokenStreamLocation.class);
        when(parser.currentLocation()).thenReturn(location);

        DupDetector detector = DupDetector.rootDetector(parser);

        assertSame(location, detector.findLocation());
    }

    @Test
    public void getSourceReturnsTheOriginalParser() {
        JsonParser parser = mock(JsonParser.class);

        DupDetector detector = DupDetector.rootDetector(parser);

        assertSame(parser, detector.getSource());
        assertSame(parser, detector.child().getSource());
    }

    @Test
    public void firstNameComparisonOnlyTreatsEqualTextAsDuplicate() {
        DupDetector detector = DupDetector.rootDetector((JsonGenerator) null);

        assertFalse(detector.isDup(new String("first")));
        assertFalse(detector.isDup(new String("different")));
        assertTrue(detector.isDup(new String("first")));
    }

    @Test
    public void secondNameComparisonOnlyTreatsEqualTextAsDuplicate() {
        DupDetector detector = DupDetector.rootDetector((JsonGenerator) null);

        assertFalse(detector.isDup("first"));
        assertFalse(detector.isDup(new String("second")));
        assertTrue(detector.isDup(new String("second")));
        assertFalse(detector.isDup("third"));
    }

    @Test
    public void differentFirstNameDoesNotBecomeDuplicateOrPreventSecondNameFromBeingRecorded() {
        DupDetector detector = DupDetector.rootDetector((JsonGenerator) null);

        assertFalse(detector.isDup("alpha"));
        assertFalse(detector.isDup("beta"));
        assertFalse(detector.isDup("gamma"));
        assertTrue(detector.isDup(new String("alpha")));
        assertTrue(detector.isDup(new String("beta")));
        assertTrue(detector.isDup(new String("gamma")));
    }

    @Test
    public void differentSecondNameDoesNotBecomeDuplicateBeforeSeenSetLookup() {
        DupDetector detector = DupDetector.rootDetector((JsonGenerator) null);

        assertFalse(detector.isDup("alpha"));
        assertFalse(detector.isDup("beta"));
        assertFalse(detector.isDup(new String("gamma")));
        assertTrue(detector.isDup(new String("alpha")));
        assertTrue(detector.isDup(new String("beta")));
        assertTrue(detector.isDup(new String("gamma")));
    }

    @Test
    public void distinctNamesRemainDistinctWhenTheyAreEqualOnlyToNeitherInitialName() {
        DupDetector detector = DupDetector.rootDetector((JsonGenerator) null);

        assertFalse(detector.isDup("first"));
        assertFalse(detector.isDup("second"));
        assertFalse(detector.isDup("third"));
        assertFalse(detector.isDup("fourth"));
        assertFalse(detector.isDup("fifth"));

        assertTrue(detector.isDup("first"));
        assertTrue(detector.isDup("second"));
        assertTrue(detector.isDup("third"));
        assertTrue(detector.isDup("fourth"));
        assertTrue(detector.isDup("fifth"));
    }

    @Test(expected = NullPointerException.class)
    public void nullNamesAreNotSupportedAfterASecondNameIsRecorded() {
        DupDetector detector = DupDetector.rootDetector((JsonGenerator) null);

        assertFalse(detector.isDup("first"));
        assertFalse(detector.isDup("second"));

        detector.isDup(null);
    }
}
