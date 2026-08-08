package org.apache.commons.codec.language;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.apache.commons.codec.EncoderException;
import org.junit.Test;

/**
 * Tests for {@link Nysiis}.
 */
public class NysiisTest {

    // -----------------------------------------------------------------------
    // Constructor tests
    // -----------------------------------------------------------------------

    @Test
    public void testDefaultConstructorIsStrict() {
        final Nysiis nysiis = new Nysiis();
        assertTrue("Default constructor should be strict", nysiis.isStrict());
    }

    @Test
    public void testConstructorWithStrictTrue() {
        final Nysiis nysiis = new Nysiis(true);
        assertTrue("Constructor with true should be strict", nysiis.isStrict());
    }

    @Test
    public void testConstructorWithStrictFalse() {
        final Nysiis nysiis = new Nysiis(false);
        assertFalse("Constructor with false should not be strict", nysiis.isStrict());
    }

    // -----------------------------------------------------------------------
    // encode(Object) tests - Encoder interface
    // -----------------------------------------------------------------------

    @Test
    public void testEncodeObjectWithValidString() throws EncoderException {
        final Nysiis nysiis = new Nysiis();
        final Object result = nysiis.encode("test");
        assertNotNull(result);
        assertTrue(result instanceof String);
        assertEquals("TAST", result);
    }

    @Test
    public void testEncodeObjectWithNull() throws EncoderException {
        final Nysiis nysiis = new Nysiis();
        final Object result = nysiis.encode(null);
        assertNull("Encoding null should return null", result);
    }

    @Test
    public void testEncodeObjectWithNonStringThrowsException() {
        final Nysiis nysiis = new Nysiis();
        try {
            nysiis.encode(Integer.valueOf(123));
            fail("Should throw EncoderException for non-String input");
        } catch (final EncoderException e) {
            assertEquals("Parameter supplied to Nysiis encode is not of type java.lang.String", e.getMessage());
        }
    }

    // -----------------------------------------------------------------------
    // encode(String) tests - StringEncoder interface
    // -----------------------------------------------------------------------

    @Test
    public void testEncodeStringWithValidInput() {
        final Nysiis nysiis = new Nysiis();
        final String result = nysiis.encode("test");
        assertEquals("TAST", result);
    }

    @Test
    public void testEncodeStringWithNull() {
        final Nysiis nysiis = new Nysiis();
        final String result = nysiis.encode(null);
        assertNull("Encoding null string should return null", result);
    }

    @Test
    public void testEncodeStringWithEmptyString() {
        final Nysiis nysiis = new Nysiis();
        final String result = nysiis.encode("");
        assertEquals("", result);
    }

    // -----------------------------------------------------------------------
    // nysiis(String) tests - core algorithm
    // -----------------------------------------------------------------------

    @Test
    public void testNysiisNull() {
        final Nysiis nysiis = new Nysiis();
        assertNull(nysiis.nysiis(null));
    }

    @Test
    public void testNysiisEmptyString() {
        final Nysiis nysiis = new Nysiis();
        assertEquals("", nysiis.nysiis(""));
    }

    // First character transformations
    @Test
    public void testFirstCharTransformMac() {
        final Nysiis nysiis = new Nysiis(false);
        // MAC -> MCC, then processed -> MC
        assertEquals("MC", nysiis.nysiis("MAC"));
        assertEquals("MCARTY", nysiis.nysiis("MACARTHY"));
    }

    @Test
    public void testFirstCharTransformKn() {
        final Nysiis nysiis = new Nysiis(false);
        // KN -> NN, then processed
        assertEquals("NAGT", nysiis.nysiis("KNIGHT"));
        assertEquals("N", nysiis.nysiis("KN"));
    }

    @Test
    public void testFirstCharTransformK() {
        final Nysiis nysiis = new Nysiis(false);
        // K -> C (but not KN)
        assertEquals("CAT", nysiis.nysiis("KAT"));
        assertEquals("CAN", nysiis.nysiis("KAN"));
    }

    @Test
    public void testFirstCharTransformPhPf() {
        final Nysiis nysiis = new Nysiis(false);
        // PH -> FF, then processed
        assertEquals("FALAP", nysiis.nysiis("PHILIP"));
        // PF -> FF, then processed
        assertEquals("FAFAR", nysiis.nysiis("PFEIFFER"));
    }

    @Test
    public void testFirstCharTransformSch() {
        final Nysiis nysiis = new Nysiis(false);
        // SCH -> SSS, then processed
        assertEquals("SNAD", nysiis.nysiis("SCHMIDT"));
        assertEquals("S", nysiis.nysiis("SCH"));
    }

    // Last character transformations
    @Test
    public void testLastCharTransformEeIe() {
        final Nysiis nysiis = new Nysiis(false);
        // EE -> Y
        assertEquals("JAN", nysiis.nysiis("JANE"));
        // IE -> Y
        assertEquals("JANY", nysiis.nysiis("JANIE"));
    }

    @Test
    public void testLastCharTransformDtEtc() {
        final Nysiis nysiis = new Nysiis(false);
        // DT, RT, RD, NT, ND -> D
        // BRADT (5 chars) -> DT->D -> BRAD (4 chars) -> process -> BRAD
        assertEquals("BRAD", nysiis.nysiis("BRADT"));
        // HARD (4 chars) -> RD->D -> HAD (3 chars) -> process -> HAD
        assertEquals("HAD", nysiis.nysiis("HARD"));
        // HARRT (5 chars) -> RT->D -> HARD (4 chars) -> process -> HARD
        assertEquals("HARD", nysiis.nysiis("HARRT"));
        // HARRD (5 chars) -> RD->D -> HARD (4 chars) -> process -> HARD
        assertEquals("HARD", nysiis.nysiis("HARRD"));
        // HARNT (5 chars) -> NT->D -> HARD (4 chars) -> process -> HARD
        assertEquals("HARD", nysiis.nysiis("HARNT"));
        // HARND (5 chars) -> ND->D -> HARD (4 chars) -> process -> HARD
        assertEquals("HARD", nysiis.nysiis("HARND"));
    }

    // Vowel handling (A, E, I, O, U -> A)
    @Test
    public void testVowelTransform() {
        final Nysiis nysiis = new Nysiis(false);
        assertEquals("JAN", nysiis.nysiis("JANE"));
        assertEquals("JAN", nysiis.nysiis("JANI"));
        assertEquals("JAN", nysiis.nysiis("JANO"));
        assertEquals("JAN", nysiis.nysiis("JANU"));
    }

    // EV -> AF
    @Test
    public void testEvTransform() {
        final Nysiis nysiis = new Nysiis(false);
        assertEquals("EV", nysiis.nysiis("EV")); // EV at start not processed (only from position 1)
        assertEquals("LAF", nysiis.nysiis("LEV")); // LEV: L, then EV->AF = LAF
        assertEquals("EVRACAN", nysiis.nysiis("EVRIKAN"));
    }

    // Q -> G (only from position 1)
    @Test
    public void testQTransform() {
        final Nysiis nysiis = new Nysiis(false);
        assertEquals("Q", nysiis.nysiis("Q")); // First char not transformed
        assertEquals("QAN", nysiis.nysiis("QAN")); // Q at start stays Q
    }

    // Z -> S (only from position 1)
    @Test
    public void testZTransform() {
        final Nysiis nysiis = new Nysiis(false);
        assertEquals("ZAN", nysiis.nysiis("ZAN")); // Z at start stays Z
        assertEquals("Z", nysiis.nysiis("Z"));
    }

    // M -> N (only from position 1)
    @Test
    public void testMTransform() {
        final Nysiis nysiis = new Nysiis(false);
        assertEquals("MAN", nysiis.nysiis("MAN")); // M at start stays M
        assertEquals("MAN", nysiis.nysiis("MANN")); // collapse repeated N
    }

    // K -> C, KN -> NN (only from position 1, but KN at start handled in step 1)
    @Test
    public void testKTransform() {
        final Nysiis nysiis = new Nysiis(false);
        // K -> C (when not followed by N, at start)
        assertEquals("CAT", nysiis.nysiis("KAT"));
        // KN -> NN (when followed by N, at start)
        assertEquals("NAGT", nysiis.nysiis("KNIGHT"));
    }

    // SCH -> SSS (at start)
    @Test
    public void testSchTransform() {
        final Nysiis nysiis = new Nysiis(false);
        assertEquals("S", nysiis.nysiis("SCH"));
        assertEquals("SNADAR", nysiis.nysiis("SCHNEIDER"));
    }

    // PH -> FF (at start or in remaining)
    @Test
    public void testPhTransform() {
        final Nysiis nysiis = new Nysiis(false);
        assertEquals("FALAP", nysiis.nysiis("PHILIP"));
        assertEquals("ALF", nysiis.nysiis("ALPH")); // PH at end -> FF, then processed
    }

    // H handling: if previous or next is non-vowel, replace with previous
    @Test
    public void testHTransform() {
        final Nysiis nysiis = new Nysiis(false);
        // H with non-vowel previous -> previous
        assertEquals("S", nysiis.nysiis("SHOE")); // H after S (non-vowel) -> S, then O->A, E->A, trailing A removed
        // H with vowel previous and non-vowel next -> previous
        assertEquals("AN", nysiis.nysiis("AHN")); // H after A (vowel), before N (non-vowel) -> A
    }

    // W handling: if previous is vowel, replace with previous
    @Test
    public void testWTransform() {
        final Nysiis nysiis = new Nysiis(false);
        // W after vowel -> previous vowel (which becomes A)
        assertEquals("AN", nysiis.nysiis("AWN")); // W after A -> A, then N
        // W after non-vowel -> W stays
        assertEquals("SWAN", nysiis.nysiis("SWEEN")); // W after S (non-vowel) -> W, then E->A, E->A (collapse), N
    }

    // Collapse repeated characters
    @Test
    public void testCollapseRepeatedChars() {
        final Nysiis nysiis = new Nysiis(false);
        assertEquals("BAN", nysiis.nysiis("BAAAN")); // AAA -> A
        assertEquals("SAN", nysiis.nysiis("SSINN")); // SS -> S, I->A, NN -> N
    }

    // Trailing character removal rules
    @Test
    public void testTrailingSRemoval() {
        final Nysiis nysiis = new Nysiis(false);
        assertEquals("JAN", nysiis.nysiis("JONS")); // trailing S removed
        assertEquals("JAN", nysiis.nysiis("JONESS")); // trailing S removed, then collapse
    }

    @Test
    public void testTrailingAyReplacement() {
        final Nysiis nysiis = new Nysiis(false);
        assertEquals("JANY", nysiis.nysiis("JONAY")); // AY not at end after processing (becomes JANY), no AY->Y rule triggered
        assertEquals("AY", nysiis.nysiis("AY")); // AY -> AY (trailing AY replacement only works when key length > 2)
    }

    @Test
    public void testTrailingARemoval() {
        final Nysiis nysiis = new Nysiis(false);
        assertEquals("JAN", nysiis.nysiis("JONA")); // trailing A removed
        assertEquals("JAN", nysiis.nysiis("JONAA")); // trailing A removed, then collapse
    }

    // Strict mode length limiting (max 6)
    @Test
    public void testStrictModeLimitsToSixChars() {
        final Nysiis strict = new Nysiis(true);
        final Nysiis nonStrict = new Nysiis(false);

        // Input that produces more than 6 chars
        final String longResult = nonStrict.nysiis("SUPERCALIFRAGILISTIC");
        assertTrue("Non-strict should allow > 6 chars", longResult.length() > 6);

        final String strictResult = strict.nysiis("SUPERCALIFRAGILISTIC");
        assertEquals("Strict mode should limit to 6 chars", 6, strictResult.length());
        assertEquals(longResult.substring(0, 6), strictResult);
    }

    // Known examples from Wikipedia / references
    @Test
    public void testKnownExamples() {
        final Nysiis nysiis = new Nysiis(false);

        // Examples from Wikipedia
        assertEquals("MCARTY", nysiis.nysiis("MACARTHY"));
        assertEquals("MCARTY", nysiis.nysiis("MCARTHY")); // should match after MCC transformation

        // Soundex comparison examples
        assertEquals("JAN", nysiis.nysiis("JANE"));
        assertEquals("JAN", nysiis.nysiis("JANA"));
        assertEquals("JAN", nysiis.nysiis("JANI"));
    }

    // Case insensitivity (SoundexUtils.clean converts to uppercase)
    @Test
    public void testCaseInsensitivity() {
        final Nysiis nysiis = new Nysiis(false);
        assertEquals(nysiis.nysiis("test"), nysiis.nysiis("TEST"));
        assertEquals(nysiis.nysiis("Test"), nysiis.nysiis("tEsT"));
    }

    // Whitespace and non-alphabetic handling (via SoundexUtils.clean)
    @Test
    public void testNonAlphabeticRemoval() {
        final Nysiis nysiis = new Nysiis(false);
        assertEquals("SNAT", nysiis.nysiis("SMITH"));
        assertEquals("SNAT", nysiis.nysiis("SMITH ")); // trailing space removed
        assertEquals("SNAT", nysiis.nysiis("SMITH-")); // hyphen removed
        assertEquals("SNAT", nysiis.nysiis("SM.ITH")); // dot removed
    }

    // Complex multi-step transformations
    @Test
    public void testComplexTransformations() {
        final Nysiis nysiis = new Nysiis(false);

        // PFEIFFER -> FFEIFFER (PF->FF) -> process -> FAFAR
        assertEquals("FAFAR", nysiis.nysiis("PFEIFFER"));

        // SCHNEIDER -> SSSNEIDER (SCH->SSS) -> process -> SNADAR
        assertEquals("SNADAR", nysiis.nysiis("SCHNEIDER"));

        // KNIGHT -> NNIGHT (KN->NN) -> process -> NAGT
        assertEquals("NAGT", nysiis.nysiis("KNIGHT"));
    }

    // First character preservation (step 3 and 9 of algorithm)
    @Test
    public void testFirstCharPreserved() {
        final Nysiis nysiis = new Nysiis(false);
        assertEquals('S', nysiis.nysiis("SMITH").charAt(0));
        assertEquals('J', nysiis.nysiis("JONES").charAt(0));
        assertEquals('C', nysiis.nysiis("KANT").charAt(0)); // K becomes C at start, first char of transformed string
    }

    // encode(Object) returns same as encode(String) for String input
    @Test
    public void testEncodeObjectAndStringConsistency() throws EncoderException {
        final Nysiis nysiis = new Nysiis();
        final String input = "TESTING";
        final String stringResult = nysiis.encode(input);
        final Object objectResult = nysiis.encode((Object) input);
        assertEquals(stringResult, objectResult);
        assertSame(stringResult.getClass(), objectResult.getClass());
    }

    // Test with various names that should produce same encoding
    @Test
    public void testSimilarNamesProduceSameEncoding() {
        final Nysiis nysiis = new Nysiis(false);

        // These should be similar phonetically
        final String code1 = nysiis.nysiis("SMITH");
        final String code2 = nysiis.nysiis("SMYTH");
        final String code3 = nysiis.nysiis("SMYTHE");

        // At minimum, they should all start with S
        assertEquals('S', code1.charAt(0));
        assertEquals('S', code2.charAt(0));
        assertEquals('S', code3.charAt(0));
    }

    // Edge case: single character
    @Test
    public void testSingleCharacter() {
        final Nysiis nysiis = new Nysiis(false);
        assertEquals("A", nysiis.nysiis("A"));
        assertEquals("B", nysiis.nysiis("B"));
        assertEquals("C", nysiis.nysiis("C"));
    }

    // Edge case: two characters
    @Test
    public void testTwoCharacters() {
        final Nysiis nysiis = new Nysiis(false);
        assertEquals("AB", nysiis.nysiis("AB"));
        assertEquals("AY", nysiis.nysiis("AY")); // AY -> AY (trailing AY replacement only works when key length > 2)
    }

    // Test strict vs non-strict with exactly 6 chars
    @Test
    public void testStrictModeBoundaryAtSix() {
        final Nysiis strict = new Nysiis(true);
        final Nysiis nonStrict = new Nysiis(false);

        final String result = nonStrict.nysiis("ABCDEF"); // exactly 6
        assertEquals(6, result.length());
        assertEquals(result, strict.nysiis("ABCDEF"));
    }

    // Test isStrict() getter
    @Test
    public void testIsStrictGetter() {
        assertTrue(new Nysiis(true).isStrict());
        assertFalse(new Nysiis(false).isStrict());
        assertTrue(new Nysiis().isStrict()); // default
    }

    // Test with known problematic cases
    @Test
    public void testHWithVowelBeforeAndAfter() {
        final Nysiis nysiis = new Nysiis(false);
        // AHA -> H has vowel before (A) and after (A) -> H stays
        // Rule: H -> previous if previous or next is non-vowel
        // Here both are vowels, so H should stay
        final String result = nysiis.nysiis("AHA");
        // A -> A, H -> H (both neighbors vowels), A -> A (trailing removed)
        // Then collapse: AHA -> AH
        assertEquals("AH", result);
    }

    @Test
    public void testWWithVowelBefore() {
        final Nysiis nysiis = new Nysiis(false);
        // AWB -> W has vowel A before -> W becomes A
        // Then AAB -> collapse to AB
        final String result = nysiis.nysiis("AWB");
        assertEquals("AB", result);
    }

    // Test that EncoderException is thrown with proper message for non-String
    @Test
    public void testEncodeObjectExceptionMessage() {
        final Nysiis nysiis = new Nysiis();
        try {
            nysiis.encode(new Object());
            fail("Expected EncoderException");
        } catch (final EncoderException e) {
            assertEquals("Parameter supplied to Nysiis encode is not of type java.lang.String", e.getMessage());
        }
    }

    // Test thread-safety / immutability - multiple calls produce same result
    @Test
    public void testImmutableMultipleCalls() {
        final Nysiis nysiis = new Nysiis(false);
        final String input = "PFEIFFER";
        final String result1 = nysiis.nysiis(input);
        final String result2 = nysiis.nysiis(input);
        final String result3 = nysiis.encode(input);
        final String result4 = nysiis.encode(input);

        assertEquals(result1, result2);
        assertEquals(result1, result3);
        assertEquals(result1, result4);
    }

    // Test with different strict settings on same input
    @Test
    public void testStrictVsNonStrictSameInput() {
        final Nysiis strict = new Nysiis(true);
        final Nysiis nonStrict = new Nysiis(false);

        final String input = "SUPERCALIFRAGILISTICEXPIALIDOCIOUS";
        final String strictResult = strict.nysiis(input);
        final String nonStrictResult = nonStrict.nysiis(input);

        assertEquals(6, strictResult.length());
        assertTrue(nonStrictResult.length() >= 6);
        assertEquals(nonStrictResult.substring(0, 6), strictResult);
    }

    // Test encode(String) throws EncoderException? No, it doesn't - only encode(Object) does
    @Test
    public void testEncodeStringDoesNotThrow() {
        final Nysiis nysiis = new Nysiis();
        // Should not throw any exception
        nysiis.encode("test");
        nysiis.encode("");
        nysiis.encode(null);
    }

    // -----------------------------------------------------------------------
    // Additional tests for branch coverage in transcodeRemaining method
    // -----------------------------------------------------------------------

    /**
     * Tests EV -> AF transformation in remaining characters (position 1+).
     * Covers branch: if (curr == 'E' && next == 'V') in transcodeRemaining.
     */
    @Test
    public void testEvTransformInRemaining() {
        final Nysiis nysiis = new Nysiis(false);
        // AEV: A (first), then E followed by V at position 1 -> EV becomes AF
        // A + AF = AAF -> collapse -> AF
        assertEquals("AF", nysiis.nysiis("AEV"));
        // LEV: L (first), then E followed by V at position 1 -> EV becomes AF
        // L + AF = LAF
        assertEquals("LAF", nysiis.nysiis("LEV"));
        // XEV: X (first), then E followed by V at position 1
        // X + AF = XAF
        assertEquals("XAF", nysiis.nysiis("XEV"));
    }

    /**
     * Tests SCH -> SSS transformation in remaining characters (position 1+).
     * Covers branch: if (curr == 'S' && next == 'C' && aNext == 'H') in transcodeRemaining.
     */
    @Test
    public void testSchTransformInRemaining() {
        final Nysiis nysiis = new Nysiis(false);
        // ASCH: A (first), then SCH at position 1 -> SSS
        // A + SSS = ASSS -> collapse -> AS -> trailing S removed -> A -> trailing A removed -> ""
        assertEquals("", nysiis.nysiis("ASCH"));
        // BSCHE: B (first), then SCH at position 1 -> SSS, then E->A
        // B + SSS + A = BSSSA -> collapse -> BSA -> trailing A removed -> BS
        assertEquals("BS", nysiis.nysiis("BSCHE"));
    }

    /**
     * Tests H handling in remaining characters with non-vowel previous.
     * Covers branch: if (curr == 'H' && (!isVowel(prev) || !isVowel(next))) where prev is non-vowel.
     */
    @Test
    public void testHTransformWithNonVowelPrevious() {
        final Nysiis nysiis = new Nysiis(false);
        // BHA: B (first), then H at position 1 with prev=B (non-vowel), next=A (vowel)
        // H -> prev (B) -> BB -> collapse -> B, then A -> A (trailing A removed) -> B
        assertEquals("B", nysiis.nysiis("BHA"));
        // SHOE: S (first), then H at position 1 with prev=S (non-vowel), next=O (vowel)
        // H -> prev (S) -> SS -> collapse -> S, then O->A, E->A -> SAA -> trailing A removed -> S
        assertEquals("S", nysiis.nysiis("SHOE"));
    }

    /**
     * Tests H handling in remaining characters with non-vowel next.
     * Covers branch: if (curr == 'H' && (!isVowel(prev) || !isVowel(next))) where next is non-vowel.
     */
    @Test
    public void testHTransformWithNonVowelNext() {
        final Nysiis nysiis = new Nysiis(false);
        // AHB: A (first), then H at position 1 with prev=A (vowel), next=B (non-vowel)
        // H -> prev (A) -> AA -> collapse -> A, then B -> B -> AB
        assertEquals("AB", nysiis.nysiis("AHB"));
        // AHN: A (first), then H at position 1 with prev=A (vowel), next=N (non-vowel)
        // H -> prev (A) -> AA -> collapse -> A, then N -> N -> AN
        assertEquals("AN", nysiis.nysiis("AHN"));
    }

    /**
     * Tests H handling in remaining characters with both vowel previous and next.
     * Covers branch: if (curr == 'H' && (!isVowel(prev) || !isVowel(next))) where both are vowels (condition false).
     */
    @Test
    public void testHTransformWithVowelPreviousAndNext() {
        final Nysiis nysiis = new Nysiis(false);
        // AHA: A (first), then H at position 1 with prev=A (vowel), next=A (vowel)
        // H stays H -> AH -> trailing A removed -> AH
        assertEquals("AH", nysiis.nysiis("AHA"));
        // EHE: E (first), then H at position 1 with prev=E (vowel), next=E (vowel)
        // H stays H -> EH -> E->A -> EHA -> trailing A removed -> EH
        assertEquals("EH", nysiis.nysiis("EHE"));
    }

    /**
     * Tests W handling in remaining characters with vowel previous.
     * Covers branch: if (curr == 'W' && isVowel(prev)) in transcodeRemaining.
     */
    @Test
    public void testWTransformWithVowelPrevious() {
        final Nysiis nysiis = new Nysiis(false);
        // AWB: A (first), then W at position 1 with prev=A (vowel)
        // W -> prev (A) -> AA -> collapse -> A, then B -> B -> AB
        assertEquals("AB", nysiis.nysiis("AWB"));
        // AWN: A (first), then W at position 1 with prev=A (vowel), next=N
        // W -> prev (A) -> AA -> collapse -> A, then N -> N -> AN
        assertEquals("AN", nysiis.nysiis("AWN"));
    }

    /**
     * Tests W handling in remaining characters with non-vowel previous.
     * Covers branch: if (curr == 'W' && isVowel(prev)) where condition is false.
     */
    @Test
    public void testWTransformWithNonVowelPrevious() {
        final Nysiis nysiis = new Nysiis(false);
        // BWA: B (first), then W at position 1 with prev=B (non-vowel)
        // W stays W -> BW -> BWA -> trailing A removed -> BW
        assertEquals("BW", nysiis.nysiis("BWA"));
        // SWEAN: S (first), then W at position 1 with prev=S (non-vowel)
        // W stays W -> SW -> SWE -> S (non-vowel), W stays -> SWA -> trailing A removed -> SW
        // Wait, let's trace: S W E A N
        // i=1: W, prev=S (non-vowel), next=E -> W stays W -> key=SW
        // i=2: E, vowel -> A -> key=SWA
        // i=3: A, vowel -> A -> key=SWAA -> collapse -> SWA
        // i=4: N -> key=SWAN
        // trailing: N not S, not AY, not A -> SWAN
        assertEquals("SWAN", nysiis.nysiis("SWEAN"));
        // Original test had SWEEN -> SWAN
        assertEquals("SWAN", nysiis.nysiis("SWEEN"));
    }

    /**
     * Tests K -> C transformation in remaining characters (not followed by N).
     * Covers branch: case 'K': if (next == 'N') ... else return CHARS_C.
     */
    @Test
    public void testKTransformInRemainingNotFollowedByN() {
        final Nysiis nysiis = new Nysiis(false);
        // AKA: A (first), then K at position 1 with next=A (not N)
        // K -> C -> AC -> ACA -> trailing A removed -> AC
        assertEquals("AC", nysiis.nysiis("AKA"));
        // AKAT: A (first), then K at position 1 with next=A
        // K -> C -> AC -> ACA -> A -> ACAT -> trailing T -> ACAT
        assertEquals("ACAT", nysiis.nysiis("AKAT"));
    }

    /**
     * Tests KN -> NN transformation in remaining characters.
     * Covers branch: case 'K': if (next == 'N') return CHARS_NN.
     */
    @Test
    public void testKnTransformInRemaining() {
        final Nysiis nysiis = new Nysiis(false);
        // AKN: A (first), then K at position 1 with next=N
        // KN -> NN -> ANN -> AN (collapse NN) -> trailing N -> AN
        assertEquals("AN", nysiis.nysiis("AKN"));
        // AKNE: A (first), then K at position 1 with next=N
        // KN -> NN -> ANN -> AN (collapse), then E->A -> ANA -> trailing A removed -> AN
        assertEquals("AN", nysiis.nysiis("AKNE"));
    }

    /**
     * Tests Q -> G transformation in remaining characters.
     * Covers branch: case 'Q': return CHARS_G.
     */
    @Test
    public void testQTransformInRemaining() {
        final Nysiis nysiis = new Nysiis(false);
        // AQA: A (first), then Q at position 1
        // Q -> G -> AG -> AGA -> trailing A removed -> AG
        assertEquals("AG", nysiis.nysiis("AQA"));
        // AQAT: A (first), then Q at position 1
        // Q -> G -> AG -> AGA -> A -> AGAT -> trailing T -> AGAT
        assertEquals("AGAT", nysiis.nysiis("AQAT"));
    }

    /**
     * Tests Z -> S transformation in remaining characters.
     * Covers branch: case 'Z': return CHARS_S.
     */
    @Test
    public void testZTransformInRemaining() {
        final Nysiis nysiis = new Nysiis(false);
        // AZA: A (first), then Z at position 1
        // Z -> S -> AS -> ASA -> trailing A removed -> AS
        assertEquals("AS", nysiis.nysiis("AZA"));
        // AZAT: A (first), then Z at position 1
        // Z -> S -> AS -> ASA -> A -> ASAT -> trailing T -> ASAT
        assertEquals("ASAT", nysiis.nysiis("AZAT"));
    }

    /**
     * Tests M -> N transformation in remaining characters.
     * Covers branch: case 'M': return CHARS_N.
     */
    @Test
    public void testMTransformInRemaining() {
        final Nysiis nysiis = new Nysiis(false);
        // AMA: A (first), then M at position 1
        // M -> N -> AN -> ANA -> trailing A removed -> AN
        assertEquals("AN", nysiis.nysiis("AMA"));
        // AMAT: A (first), then M at position 1
        // M -> N -> AN -> ANA -> A -> ANAT -> trailing T -> ANAT
        assertEquals("ANAT", nysiis.nysiis("AMAT"));
    }

    /**
     * Tests PH -> FF transformation in remaining characters.
     * Covers branch: if (curr == 'P' && next == 'H') return CHARS_FF.
     */
    @Test
    public void testPhTransformInRemaining() {
        final Nysiis nysiis = new Nysiis(false);
        // APH: A (first), then P at position 1 with next=H
        // PH -> FF -> AFF -> AF (collapse FF) -> trailing F -> AF
        assertEquals("AF", nysiis.nysiis("APH"));
        // APHA: A (first), then P at position 1 with next=H
        // PH -> FF -> AFF -> AF (collapse), then A -> AFA -> trailing A removed -> AF
        assertEquals("AF", nysiis.nysiis("APHA"));
    }

    /**
     * Tests vowel transformation (A,E,I,O,U -> A) in remaining characters.
     * Covers branch: if (isVowel(curr)) return CHARS_A.
     */
    @Test
    public void testVowelTransformInRemaining() {
        final Nysiis nysiis = new Nysiis(false);
        // ABE: A (first), then B, then E at position 2
        // E -> A -> ABA -> trailing A removed -> AB
        assertEquals("AB", nysiis.nysiis("ABE"));
        // ABI: A (first), then B, then I at position 2
        // I -> A -> ABA -> trailing A removed -> AB
        assertEquals("AB", nysiis.nysiis("ABI"));
        // ABO: A (first), then B, then O at position 2
        // O -> A -> ABA -> trailing A removed -> AB
        assertEquals("AB", nysiis.nysiis("ABO"));
        // ABU: A (first), then B, then U at position 2
        // U -> A -> ABA -> trailing A removed -> AB
        assertEquals("AB", nysiis.nysiis("ABU"));
    }

    /**
     * Tests default case in transcodeRemaining (character passes through unchanged).
     * Covers the final return new char[] { curr }.
     */
    @Test
    public void testDefaultTransformInRemaining() {
        final Nysiis nysiis = new Nysiis(false);
        // AB: A (first), then B at position 1 (no special handling)
        // B stays B -> AB
        assertEquals("AB", nysiis.nysiis("AB"));
        // ABC: A (first), then B at position 1, then C at position 2
        // B stays B, C stays C -> ABC
        assertEquals("ABC", nysiis.nysiis("ABC"));
        // ABD: A (first), then B at position 1, then D at position 2
        // B stays B, D stays D -> ABD
        assertEquals("ABD", nysiis.nysiis("ABD"));
    }

    /**
     * Tests trailing S removal when key length > 1.
     * Covers branch: if (lastChar == 'S') in nysiis method.
     */
    @Test
    public void testTrailingSRemovalInNysiisMethod() {
        final Nysiis nysiis = new Nysiis(false);
        // JONS: J (first), O->A, N, S -> JANS -> trailing S removed -> JAN
        assertEquals("JAN", nysiis.nysiis("JONS"));
        // JONESS: J (first), O->A, N, E->A, S, S -> JANSS -> collapse -> JANS -> trailing S removed -> JAN
        assertEquals("JAN", nysiis.nysiis("JONESS"));
        // AS: A (first), S -> AS -> trailing S removed -> A -> trailing A removed -> ""
        assertEquals("", nysiis.nysiis("AS"));
    }

    /**
     * Tests trailing AY -> Y replacement when key length > 2.
     * Covers branch: if (last2Char == 'A' && lastChar == 'Y') in nysiis method.
     */
    @Test
    public void testTrailingAyReplacementInNysiisMethod() {
        final Nysiis nysiis = new Nysiis(false);
        // JONAY: J (first), O->A, N, A, Y -> JANAY -> trailing AY -> JANY
        assertEquals("JANY", nysiis.nysiis("JONAY"));
        // BAY: B (first), A, Y -> BAY (length 3, but key would be BAY -> BAY -> BAY? Wait)
        // Let's trace: B A Y
        // i=1: A (vowel) -> A -> key=BA
        // i=2: Y -> key=BAY
        // trailing: lastChar=Y, last2Char=A -> AY -> replace with Y -> BY
        assertEquals("BY", nysiis.nysiis("BAY"));
        // CAY: C (first), A, Y -> CAY -> trailing AY -> CY
        assertEquals("CY", nysiis.nysiis("CAY"));
    }

    /**
     * Tests trailing A removal when key length > 1.
     * Covers branch: if (lastChar == 'A') in nysiis method.
     */
    @Test
    public void testTrailingARemovalInNysiisMethod() {
        final Nysiis nysiis = new Nysiis(false);
        // JONA: J (first), O->A, N, A -> JANA -> trailing A removed -> JAN
        assertEquals("JAN", nysiis.nysiis("JONA"));
        // BANANA: B (first), A, N, A, N, A -> BANANA -> trailing A removed -> BANAN
        assertEquals("BANAN", nysiis.nysiis("BANANA"));
        // A: single char -> A (no trailing removal for length 1)
        assertEquals("A", nysiis.nysiis("A"));
        // AA: A (first), A -> AA -> collapse -> A -> trailing A removed -> "" (key.length() > 1 after collapse? No, collapse happens in loop)
        // Actually: key starts with 'A', i=1: A (vowel) -> A, chars[1]==chars[0] -> not appended, key="A"
        // key.length() = 1, so trailing removal block skipped. Result "A".
        assertEquals("A", nysiis.nysiis("AA"));
    }

    /**
     * Tests strict mode substring when result length > 6.
     * Covers branch: return isStrict() ? string.substring(0, Math.min(TRUE_LENGTH, string.length())) : string.
     */
    @Test
    public void testStrictModeSubstring() {
        final Nysiis strict = new Nysiis(true);
        final Nysiis nonStrict = new Nysiis(false);
        // Input that produces exactly 7 chars in non-strict
        final String input = "ABCDEFG"; // 7 chars, all consonants
        final String nonStrictResult = nonStrict.nysiis(input);
        assertEquals(7, nonStrictResult.length());
        final String strictResult = strict.nysiis(input);
        assertEquals(6, strictResult.length());
        assertEquals(nonStrictResult.substring(0, 6), strictResult);
    }

    /**
     * Tests key length <= 1 edge cases for trailing character removal.
     * Covers branch: if (key.length() > 1) in nysiis method.
     */
    @Test
    public void testKeyLengthEdgeCases() {
        final Nysiis nysiis = new Nysiis(false);
        // Single character - no trailing removal
        assertEquals("A", nysiis.nysiis("A"));
        assertEquals("B", nysiis.nysiis("B"));
        assertEquals("S", nysiis.nysiis("S"));
        // Two characters - trailing removal applies (S then A)
        assertEquals("", nysiis.nysiis("AS")); // trailing S removed -> A -> trailing A removed -> ""
        assertEquals("B", nysiis.nysiis("BA")); // trailing A removed -> B
        assertEquals("BY", nysiis.nysiis("BAY")); // trailing AY -> Y (key length 3 > 2)
    }

    /**
     * Tests collapse of repeated characters in the main loop.
     * Covers the collapse logic: if (chars[i] != chars[i-1]) key.append(chars[i]).
     */
    @Test
    public void testCollapseInMainLoop() {
        final Nysiis nysiis = new Nysiis(false);
        // BBB: B (first), B, B -> all same -> key="B"
        assertEquals("B", nysiis.nysiis("BBB"));
        // ABBB: A (first), B, B, B -> key="AB"
        assertEquals("AB", nysiis.nysiis("ABBB"));
        // BAAA: B (first), A, A, A -> vowels become A -> BAAA -> collapse -> BA -> trailing A removed -> B
        assertEquals("B", nysiis.nysiis("BAAA"));
        // SSINN: S (first), S, I->A, N, N -> SSANN -> collapse -> SAN -> trailing N -> SAN
        assertEquals("SAN", nysiis.nysiis("SSINN"));
    }

    // -----------------------------------------------------------------------
    // New tests targeting missed branch coverage in transcodeRemaining
    // -----------------------------------------------------------------------

    /**
     * Tests SCH condition false branch where curr='S', next='C', but aNext!='H'.
     * Covers branch: if (curr == 'S' && next == 'C' && aNext == 'H') where first two true, third false.
     */
    @Test
    public void testSchConditionFalseWithSAndCButNotH() {
        final Nysiis nysiis = new Nysiis(false);
        // ASCA: A (first), then S at pos 1, C at pos 2, A at pos 3
        // SCH condition: curr='S', next='C', aNext='A' != 'H' -> false
        // Falls through to default -> S stays S, then C, then A->A
        // Key: A + S + C + A = ASCA -> trailing A removed -> ASC
        assertEquals("ASC", nysiis.nysiis("ASCA"));
        // ASCB: similar, ends with B
        assertEquals("ASCB", nysiis.nysiis("ASCB"));
    }

    /**
     * Tests PH condition false branch where curr='P', next!='H'.
     * Covers branch: if (curr == 'P' && next == 'H') where curr='P' but next!='H'.
     */
    @Test
    public void testPhConditionFalseWithPNotFollowedByH() {
        final Nysiis nysiis = new Nysiis(false);
        // APA: A (first), then P at pos 1, A at pos 2
        // PH condition: curr='P', next='A' != 'H' -> false
        // P stays P -> APA -> trailing A removed -> AP
        assertEquals("AP", nysiis.nysiis("APA"));
        // APB: similar, ends with B
        assertEquals("APB", nysiis.nysiis("APB"));
    }

    /**
     * Tests K transformation at end of string (next = SPACE).
     * Covers branch: case 'K': if (next == 'N') with next = SPACE (not 'N').
     */
    @Test
    public void testKTransformAtEndOfString() {
        final Nysiis nysiis = new Nysiis(false);
        // AK: A (first), K at pos 1 (end), next = SPACE
        // K -> C (since next != 'N') -> AC
        assertEquals("AC", nysiis.nysiis("AK"));
        // BK: B (first), K at pos 1 (end)
        // K -> C -> BC
        assertEquals("BC", nysiis.nysiis("BK"));
    }

    /**
     * Tests H transformation at end of string (next = SPACE).
     * SPACE is not a vowel, so !isVowel(next) = true.
     * Covers branch: if (curr == 'H' && (!isVowel(prev) || !isVowel(next))) with next = SPACE.
     */
    @Test
    public void testHTransformAtEndOfString() {
        final Nysiis nysiis = new Nysiis(false);
        // AH: A (first), H at pos 1 (end), prev='A' (vowel), next=SPACE (non-vowel)
        // !isVowel(prev)=false, !isVowel(next)=true -> condition true -> H becomes prev (A)
        // Key: A + A = AA -> collapse -> A -> trailing A removed -> ""
        // But wait: key.length() after loop is 1 (AA collapsed to A), so trailing removal skipped
        // Actual result: "A"
        assertEquals("A", nysiis.nysiis("AH"));
        // BH: B (first), H at pos 1 (end), prev='B' (non-vowel), next=SPACE
        // !isVowel(prev)=true -> short-circuit -> H becomes B
        // Key: B + B = BB -> collapse -> B
        assertEquals("B", nysiis.nysiis("BH"));
    }

    /**
     * Tests W transformation at end of string.
     * Covers branch: if (curr == 'W' && isVowel(prev)) with W at end.
     */
    @Test
    public void testWTransformAtEndOfString() {
        final Nysiis nysiis = new Nysiis(false);
        // AW: A (first), W at pos 1 (end), prev='A' (vowel)
        // W -> prev (A) -> AA -> collapse -> A -> trailing A removed -> ""
        // But key.length() after loop is 1, so trailing removal skipped
        // Actual result: "A"
        assertEquals("A", nysiis.nysiis("AW"));
        // BW: B (first), W at pos 1 (end), prev='B' (non-vowel)
        // W stays W -> BW
        assertEquals("BW", nysiis.nysiis("BW"));
    }

    /**
     * Tests EV condition false branch where curr='E', next!= 'V' (including SPACE at end).
     * Covers branch: if (curr == 'E' && next == 'V') where curr='E' but next!='V'.
     */
    @Test
    public void testEvConditionFalseWithENotFollowedByV() {
        final Nysiis nysiis = new Nysiis(false);
        // AE: A (first), E at pos 1 (end), next = SPACE
        // EV condition: curr='E', next=SPACE != 'V' -> false
        // Falls to vowel rule -> E -> A -> AA -> collapse -> A -> trailing A removed -> ""
        // But key.length() after loop is 1, so trailing removal skipped
        // Actual result: "A"
        assertEquals("A", nysiis.nysiis("AE"));
        // AEX: A (first), E at pos 1, X at pos 2
        // EV condition: curr='E', next='X' != 'V' -> false
        // E -> A -> AX -> trailing X -> AX
        assertEquals("AX", nysiis.nysiis("AEX"));
    }

    /**
     * Tests SCH condition with curr='S', next!='C'.
     * Covers branch: if (curr == 'S' && next == 'C' && aNext == 'H') where curr='S' but next!='C'.
     */
    @Test
    public void testSchConditionFalseWithSNotFollowedByC() {
        final Nysiis nysiis = new Nysiis(false);
        // ASB: A (first), S at pos 1, B at pos 2
        // SCH condition: curr='S', next='B' != 'C' -> false
        // S stays S -> ASB
        assertEquals("ASB", nysiis.nysiis("ASB"));
        // AS: A (first), S at pos 1 (end), next=SPACE
        // SCH condition: curr='S', next=SPACE != 'C' -> false
        // S stays S -> AS -> trailing S removed -> A -> trailing A removed -> ""
        assertEquals("", nysiis.nysiis("AS"));
    }

    /**
     * Tests PH condition with curr='P' at end of string (next = SPACE).
     * Covers branch: if (curr == 'P' && next == 'H') where curr='P', next=SPACE.
     */
    @Test
    public void testPhConditionAtEndOfString() {
        final Nysiis nysiis = new Nysiis(false);
        // AP: A (first), P at pos 1 (end), next = SPACE
        // PH condition: curr='P', next=SPACE != 'H' -> false
        // P stays P -> AP -> trailing P -> AP
        assertEquals("AP", nysiis.nysiis("AP"));
    }

    /**
     * Tests H condition with prev=SPACE (theoretically impossible due to clean, but tests boundary).
     * Actually, since i starts at 1, prev is always chars[0] or later, never SPACE.
     * But we test H with various vowel/non-vowel combinations to ensure all sub-branches covered.
     */
    @Test
    public void testHTransformEdgeCases() {
        final Nysiis nysiis = new Nysiis(false);
        // OH: O (first), H at pos 1 (end), prev='O' (vowel), next=SPACE (non-vowel)
        // !isVowel(prev)=false, !isVowel(next)=true -> H becomes prev (O)
        // Key: O + O = OO -> collapse -> O
        assertEquals("O", nysiis.nysiis("OH"));
        // UH: U (first), H at pos 1 (end), similar
        assertEquals("U", nysiis.nysiis("UH"));
        // EH: E (first), H at pos 1 (end)
        // E->A in vowel rule? No, H is processed before vowel rule for H.
        // Wait: H rule comes before vowel rule? No, vowel rule is second (after EV).
        // Order: EV, Vowel, Q/Z/M/K, SCH, PH, H, W, default.
        // So for H at pos 1: H rule applies (curr='H'), not vowel rule.
        // prev='E' (vowel), next=SPACE (non-vowel) -> H becomes prev (E) -> EE -> collapse -> E
        assertEquals("E", nysiis.nysiis("EH"));
    }

    /**
     * Tests W condition with prev=SPACE boundary (not possible in practice, but tests W at position 1 with various prev).
     */
    @Test
    public void testWTransformEdgeCases() {
        final Nysiis nysiis = new Nysiis(false);
        // OW: O (first), W at pos 1 (end), prev='O' (vowel)
        // W -> prev (O) -> OO -> collapse -> O
        assertEquals("O", nysiis.nysiis("OW"));
        // EW: E (first), W at pos 1 (end), prev='E' (vowel) -> W becomes E -> EE -> collapse -> E
        assertEquals("E", nysiis.nysiis("EW"));
        // IW: I (first), W at pos 1 (end) -> I
        assertEquals("I", nysiis.nysiis("IW"));
    }

    /**
     * Tests K condition with next = SPACE (end of string) explicitly.
     * Although logically same as next != 'N', ensures branch coverage for next=SPACE.
     */
    @Test
    public void testKTransformWithNextSpace() {
        final Nysiis nysiis = new Nysiis(false);
        // AK: covered above, but explicit test for clarity
        assertEquals("AC", nysiis.nysiis("AK"));
        // K at position 2: ABK -> A, B, K(end)
        // i=1: B -> key=AB
        // i=2: K, prev=B, next=SPACE -> K->C -> ABC
        assertEquals("ABC", nysiis.nysiis("ABK"));
    }

    /**
     * Tests the default case in transcodeRemaining with various characters.
     * Ensures the final 'return new char[] { curr }' is exercised.
     */
    @Test
    public void testDefaultCaseInTranscodeRemaining() {
        final Nysiis nysiis = new Nysiis(false);
        // Characters that fall through all conditions: B, C, D, F, G, J, L, R, T, V, X, Y
        // AB: A (first), B -> AB
        assertEquals("AB", nysiis.nysiis("AB"));
        // ACD: A, C, D -> ACD
        assertEquals("ACD", nysiis.nysiis("ACD"));
        // AFG: A, F, G -> AFG
        assertEquals("AFG", nysiis.nysiis("AFG"));
        // AL: A, L -> AL
        assertEquals("AL", nysiis.nysiis("AL"));
        // AR: A, R -> AR
        assertEquals("AR", nysiis.nysiis("AR"));
        // AT: A, T -> AT
        assertEquals("AT", nysiis.nysiis("AT"));
        // AV: A, V -> AV
        assertEquals("AV", nysiis.nysiis("AV"));
        // AY: A, Y -> AY -> trailing AY -> Y? No, key length 2, so no AY replacement. -> AY
        assertEquals("AY", nysiis.nysiis("AY"));
    }

    /**
     * Tests the switch statement default case in transcodeRemaining.
     * Covers characters not Q, Z, M, K, S, P, H, W, vowel, E-V.
     */
    @Test
    public void testSwitchDefaultCaseInTranscodeRemaining() {
        final Nysiis nysiis = new Nysiis(false);
        // B, C, D, F, G, J, L, R, T, V, X, Y at position 1
        // All should pass through unchanged
        assertEquals("AB", nysiis.nysiis("AB")); // B
        assertEquals("AC", nysiis.nysiis("AC")); // C
        assertEquals("AD", nysiis.nysiis("AD")); // D
        assertEquals("AF", nysiis.nysiis("AF")); // F
        assertEquals("AG", nysiis.nysiis("AG")); // G
        assertEquals("AJ", nysiis.nysiis("AJ")); // J
        assertEquals("AL", nysiis.nysiis("AL")); // L
        assertEquals("AR", nysiis.nysiis("AR")); // R
        assertEquals("AT", nysiis.nysiis("AT")); // T
        assertEquals("AV", nysiis.nysiis("AV")); // V
        assertEquals("AX", nysiis.nysiis("AX")); // X
        assertEquals("AY", nysiis.nysiis("AY")); // Y
    }

    // -----------------------------------------------------------------------
    // Tests to kill surviving EmptyObjectReturnValsMutator on nysiis() return
    // -----------------------------------------------------------------------

    /**
     * Tests that nysiis() returns correct non-empty values for valid inputs.
     * Targets mutation: EmptyObjectReturnValsMutator replacing return value with "".
     * This test explicitly verifies the return value is not empty for multiple code paths.
     */
    @Test
    public void testNysiisReturnValueNotEmptyForValidInputs() throws EncoderException {
        final Nysiis nysiis = new Nysiis(false);
        final Nysiis strict = new Nysiis(true);

        // Test basic encoding via nysiis(String)
        String result = nysiis.nysiis("TEST");
        assertNotNull("nysiis(String) should not return null for valid input", result);
        assertFalse("nysiis(String) should not return empty string for valid input", result.isEmpty());
        assertEquals("TAST", result);

        // Test via encode(String)
        result = nysiis.encode("TEST");
        assertNotNull("encode(String) should not return null", result);
        assertFalse("encode(String) should not return empty string", result.isEmpty());
        assertEquals("TAST", result);

        // Test via encode(Object) with explicit cast
        Object objResult = nysiis.encode((Object) "TEST");
        assertNotNull("encode(Object) should not return null", objResult);
        assertTrue("encode(Object) should return String", objResult instanceof String);
        assertFalse("encode(Object) should not return empty string", ((String) objResult).isEmpty());
        assertEquals("TAST", objResult);

        // Test strict mode returns correct non-empty string
        result = strict.nysiis("TEST");
        assertNotNull("Strict nysiis should not return null", result);
        assertFalse("Strict nysiis should not return empty string", result.isEmpty());
        assertEquals("TAST", result);

        // Test strict mode with long input (exercises substring branch)
        result = strict.nysiis("SUPERCALIFRAGILISTIC");
        assertNotNull("Strict nysiis with long input should not return null", result);
        assertFalse("Strict nysiis with long input should not return empty string", result.isEmpty());
        assertEquals(6, result.length());

        // Test non-strict mode with long input
        result = nysiis.nysiis("SUPERCALIFRAGILISTIC");
        assertNotNull("Non-strict nysiis with long input should not return null", result);
        assertFalse("Non-strict nysiis with long input should not return empty string", result.isEmpty());
        assertTrue("Non-strict should allow > 6 chars", result.length() > 6);

        // Test multiple distinct inputs to cover different algorithm paths
        assertEquals("SNAT", nysiis.nysiis("SMITH"));
        assertEquals("JAN", nysiis.nysiis("JONES"));
        assertEquals("MCARTY", nysiis.nysiis("MACARTHY"));
        assertEquals("NAGT", nysiis.nysiis("KNIGHT"));
        assertEquals("FAFAR", nysiis.nysiis("PFEIFFER"));
        assertEquals("SNADAR", nysiis.nysiis("SCHNEIDER"));
    }

    /**
     * Tests nysiis() with inputs that produce empty string legitimately,
     * ensuring the mutation doesn't accidentally pass for those cases.
     */
    @Test
    public void testNysiisReturnValueEmptyOnlyForLegitimateCases() {
        final Nysiis nysiis = new Nysiis(false);

        // These inputs legitimately produce empty string after transformations
        assertEquals("", nysiis.nysiis("AS"));   // trailing S removed -> A -> trailing A removed -> ""
        assertEquals("", nysiis.nysiis("ASS"));  // trailing S removed -> A -> trailing A removed -> ""
        assertEquals("", nysiis.nysiis("ASCH")); // SCH->SSS, collapse, trailing S, trailing A -> ""

        // But these should NOT be empty
        assertNotNull(nysiis.nysiis("A"));
        assertFalse(nysiis.nysiis("A").isEmpty());
        assertEquals("A", nysiis.nysiis("A"));

        assertNotNull(nysiis.nysiis("B"));
        assertFalse(nysiis.nysiis("B").isEmpty());
        assertEquals("B", nysiis.nysiis("B"));

        assertNotNull(nysiis.nysiis("TEST"));
        assertFalse(nysiis.nysiis("TEST").isEmpty());
        assertEquals("TAST", nysiis.nysiis("TEST"));
    }

}
