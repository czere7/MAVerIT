package org.apache.commons.codec.language;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.apache.commons.codec.EncoderException;
import org.junit.Test;

public class ColognePhoneticTest {

    private final ColognePhonetic encoder = new ColognePhonetic();

    @Test
    public void testNullInput() {
        assertNull(encoder.colognePhonetic(null));
        assertNull(encoder.encode((String) null));
    }

    @Test
    public void testEmptyString() {
        assertEquals("", encoder.colognePhonetic(""));
        assertEquals("", encoder.encode(""));
    }

    @Test
    public void testExampleFromDocumentation() {
        assertEquals("65752682", encoder.colognePhonetic("Müller-Lüdenscheidt"));
        assertEquals("65752682", encoder.colognePhonetic("Mueller-Luedenscheidt"));
    }

    @Test
    public void testUmlautReplacement() {
        assertEquals(encoder.colognePhonetic("Ä"), encoder.colognePhonetic("A"));
        assertEquals(encoder.colognePhonetic("Ö"), encoder.colognePhonetic("O"));
        assertEquals(encoder.colognePhonetic("Ü"), encoder.colognePhonetic("U"));
        assertEquals(encoder.colognePhonetic("ä"), encoder.colognePhonetic("A"));
        assertEquals(encoder.colognePhonetic("ö"), encoder.colognePhonetic("O"));
        assertEquals(encoder.colognePhonetic("ü"), encoder.colognePhonetic("U"));
    }

    @Test
    public void testSharpSReplacement() {
        assertEquals(encoder.colognePhonetic("ß"), encoder.colognePhonetic("SS"));
        assertEquals(encoder.colognePhonetic("Straße"), encoder.colognePhonetic("Strasse"));
    }

    @Test
    public void testVowelsAndYEncodeToZero() {
        assertEquals("0", encoder.colognePhonetic("A"));
        assertEquals("0", encoder.colognePhonetic("E"));
        assertEquals("0", encoder.colognePhonetic("I"));
        assertEquals("0", encoder.colognePhonetic("J"));
        assertEquals("0", encoder.colognePhonetic("O"));
        assertEquals("0", encoder.colognePhonetic("U"));
        assertEquals("0", encoder.colognePhonetic("Y"));
    }

    @Test
    public void testHIsIgnored() {
        assertEquals("", encoder.colognePhonetic("H"));
        assertEquals("1", encoder.colognePhonetic("BH"));
        assertEquals("1", encoder.colognePhonetic("HB"));
    }

    @Test
    public void testBAndPNotBeforeH() {
        assertEquals("1", encoder.colognePhonetic("B"));
        assertEquals("1", encoder.colognePhonetic("P"));
        assertEquals("1", encoder.colognePhonetic("PA"));
        assertEquals("3", encoder.colognePhonetic("PH"));
    }

    @Test
    public void testDAndTNotBeforeCSZ() {
        assertEquals("2", encoder.colognePhonetic("D"));
        assertEquals("2", encoder.colognePhonetic("T"));
        assertEquals("2", encoder.colognePhonetic("DA"));
        assertEquals("2", encoder.colognePhonetic("TA"));
        assertEquals("8", encoder.colognePhonetic("DC"));
        assertEquals("8", encoder.colognePhonetic("DS"));
        assertEquals("8", encoder.colognePhonetic("DZ"));
        assertEquals("8", encoder.colognePhonetic("TC"));
        assertEquals("8", encoder.colognePhonetic("TS"));
        assertEquals("8", encoder.colognePhonetic("TZ"));
    }

    @Test
    public void testFPVW() {
        assertEquals("3", encoder.colognePhonetic("F"));
        assertEquals("3", encoder.colognePhonetic("V"));
        assertEquals("3", encoder.colognePhonetic("W"));
        assertEquals("3", encoder.colognePhonetic("PH"));
    }

    @Test
    public void testGKQ() {
        assertEquals("4", encoder.colognePhonetic("G"));
        assertEquals("4", encoder.colognePhonetic("K"));
        assertEquals("4", encoder.colognePhonetic("Q"));
    }

    @Test
    public void testCContextRules() {
        // Onset C before A,H,K,L,O,Q,R,U,X -> 4
        assertEquals("4", encoder.colognePhonetic("CA"));
        assertEquals("4", encoder.colognePhonetic("CH"));
        assertEquals("4", encoder.colognePhonetic("CK"));
        assertEquals("45", encoder.colognePhonetic("CL"));
        assertEquals("4", encoder.colognePhonetic("CO"));
        assertEquals("4", encoder.colognePhonetic("CQ"));
        assertEquals("47", encoder.colognePhonetic("CR"));
        assertEquals("4", encoder.colognePhonetic("CU"));
        assertEquals("48", encoder.colognePhonetic("CX"));
        
        // Onset C before other letters -> 8
        assertEquals("81", encoder.colognePhonetic("CB"));
        assertEquals("8", encoder.colognePhonetic("CC"));
        assertEquals("82", encoder.colognePhonetic("CD"));
        assertEquals("8", encoder.colognePhonetic("CE"));
        assertEquals("83", encoder.colognePhonetic("CF"));
        assertEquals("84", encoder.colognePhonetic("CG"));
        assertEquals("8", encoder.colognePhonetic("CI"));
        assertEquals("8", encoder.colognePhonetic("CJ"));
        assertEquals("86", encoder.colognePhonetic("CM"));
        assertEquals("86", encoder.colognePhonetic("CN"));
        assertEquals("81", encoder.colognePhonetic("CP"));
        assertEquals("8", encoder.colognePhonetic("CS"));
        assertEquals("82", encoder.colognePhonetic("CT"));
        assertEquals("83", encoder.colognePhonetic("CV"));
        assertEquals("83", encoder.colognePhonetic("CW"));
        assertEquals("8", encoder.colognePhonetic("CY"));
        assertEquals("8", encoder.colognePhonetic("CZ"));
        
        // C after S,Z -> 8
        assertEquals("8", encoder.colognePhonetic("SC"));
        assertEquals("8", encoder.colognePhonetic("ZC"));
        
        // C after vowel, before A,H,K,O,Q,U,X -> 4
        assertEquals("04", encoder.colognePhonetic("ACA"));
        assertEquals("04", encoder.colognePhonetic("ACH"));
    }

    @Test
    public void testXContextRules() {
        assertEquals("48", encoder.colognePhonetic("X"));
        assertEquals("048", encoder.colognePhonetic("AX"));
        assertEquals("148", encoder.colognePhonetic("BX"));
        assertEquals("48", encoder.colognePhonetic("CX"));
        assertEquals("48", encoder.colognePhonetic("KX"));
        assertEquals("48", encoder.colognePhonetic("QX"));
    }

    @Test
    public void testSAndZ() {
        assertEquals("8", encoder.colognePhonetic("S"));
        assertEquals("8", encoder.colognePhonetic("Z"));
    }

    @Test
    public void testL() {
        assertEquals("5", encoder.colognePhonetic("L"));
    }

    @Test
    public void testMAndN() {
        assertEquals("6", encoder.colognePhonetic("M"));
        assertEquals("6", encoder.colognePhonetic("N"));
    }

    @Test
    public void testR() {
        assertEquals("7", encoder.colognePhonetic("R"));
    }

    @Test
    public void testDTXDefault() {
        assertEquals("248", encoder.colognePhonetic("DX"));
        assertEquals("248", encoder.colognePhonetic("TX"));
    }

    @Test
    public void testCollapseConsecutiveDigits() {
        assertEquals("0", encoder.colognePhonetic("AA"));
        assertEquals("1", encoder.colognePhonetic("BB"));
        assertEquals("8", encoder.colognePhonetic("SS"));
    }

    @Test
    public void testRemoveZerosExceptAtBeginning() {
        assertEquals("01", encoder.colognePhonetic("ABA"));
        assertEquals("1", encoder.colognePhonetic("BAB"));
        assertEquals("01", encoder.colognePhonetic("AAB"));
    }

    @Test
    public void testNonAlphabeticCharactersIgnored() {
        assertEquals("1", encoder.colognePhonetic("B1"));
        assertEquals("1", encoder.colognePhonetic("B-"));
        assertEquals("1", encoder.colognePhonetic("B B"));
        assertEquals("18", encoder.colognePhonetic("B_C"));
        assertEquals("65752682", encoder.colognePhonetic("Müller-Lüdenscheidt"));
    }

    @Test
    public void testLowerCaseInput() {
        assertEquals(encoder.colognePhonetic("müller"), encoder.colognePhonetic("MÜLLER"));
        assertEquals(encoder.colognePhonetic("straße"), encoder.colognePhonetic("STRASSE"));
    }

    @Test
    public void testEncodeStringMethod() {
        assertEquals("65752682", encoder.encode("Müller-Lüdenscheidt"));
    }

    @Test
    public void testEncodeObjectWithString() throws EncoderException {
        assertEquals("65752682", encoder.encode((Object) "Müller-Lüdenscheidt"));
    }

    @Test
    public void testEncodeObjectWithNonStringThrowsException() {
        try {
            encoder.encode(new Object());
            fail("Expected EncoderException");
        } catch (EncoderException e) {
            assertTrue(e.getMessage().contains("String"));
            assertTrue(e.getMessage().contains("java.lang.Object"));
        }
    }

    @Test
    public void testIsEncodeEqual() {
        assertTrue(encoder.isEncodeEqual("Müller", "Mueller"));
        assertTrue(encoder.isEncodeEqual("Straße", "Strasse"));
        assertFalse(encoder.isEncodeEqual("Müller", "Meier"));
    }

    @Test
    public void testComplexExamples() {
        assertEquals("862", encoder.colognePhonetic("Schmidt"));
        assertEquals("862", encoder.colognePhonetic("Schmitt"));
        assertEquals("67", encoder.colognePhonetic("Meyer"));
        assertEquals("67", encoder.colognePhonetic("Maier"));
        assertEquals("67", encoder.colognePhonetic("Mayer"));
        assertEquals("8627", encoder.colognePhonetic("Schneider"));
    }

    @Test
    public void testThreadSafety() throws InterruptedException {
        final String testInput = "Müller-Lüdenscheidt";
        final String expected = "65752682";
        
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                assertEquals(expected, encoder.colognePhonetic(testInput));
            }
        });
        
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                assertEquals(expected, encoder.encode(testInput));
            }
        });
        
        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                assertTrue(encoder.isEncodeEqual(testInput, "Mueller-Ludenscheidt"));
            }
        });
        
        t1.start();
        t2.start();
        t3.start();
        
        t1.join();
        t2.join();
        t3.join();
    }

    @Test
    public void testSingleCharacters() {
        assertEquals("0", encoder.colognePhonetic("A"));
        assertEquals("1", encoder.colognePhonetic("B"));
        assertEquals("2", encoder.colognePhonetic("D"));
        assertEquals("3", encoder.colognePhonetic("F"));
        assertEquals("4", encoder.colognePhonetic("G"));
        assertEquals("", encoder.colognePhonetic("H"));
        assertEquals("0", encoder.colognePhonetic("I"));
        assertEquals("0", encoder.colognePhonetic("J"));
        assertEquals("4", encoder.colognePhonetic("K"));
        assertEquals("5", encoder.colognePhonetic("L"));
        assertEquals("6", encoder.colognePhonetic("M"));
        assertEquals("6", encoder.colognePhonetic("N"));
        assertEquals("0", encoder.colognePhonetic("O"));
        assertEquals("1", encoder.colognePhonetic("P"));
        assertEquals("4", encoder.colognePhonetic("Q"));
        assertEquals("7", encoder.colognePhonetic("R"));
        assertEquals("8", encoder.colognePhonetic("S"));
        assertEquals("2", encoder.colognePhonetic("T"));
        assertEquals("0", encoder.colognePhonetic("U"));
        assertEquals("3", encoder.colognePhonetic("V"));
        assertEquals("3", encoder.colognePhonetic("W"));
        assertEquals("48", encoder.colognePhonetic("X"));
        assertEquals("0", encoder.colognePhonetic("Y"));
        assertEquals("8", encoder.colognePhonetic("Z"));
    }

    @Test
    public void testKnownVariants() {
        assertTrue(encoder.isEncodeEqual("Weiß", "Weiss"));
        assertTrue(encoder.isEncodeEqual("Groß", "Gross"));
        assertTrue(encoder.isEncodeEqual("Häuser", "Haeuser"));
        assertTrue(encoder.isEncodeEqual("Töpfer", "Toepfer"));
    }

    @Test
    public void testEdgeCaseMultipleIdenticalCodesAfterZeroRemoval() {
        assertEquals("1", encoder.colognePhonetic("BAAB"));
        assertEquals("1", encoder.colognePhonetic("BABB"));
    }

    @Test
    public void testCAtOnset() {
        assertEquals("4", encoder.colognePhonetic("CA"));
        assertEquals("81", encoder.colognePhonetic("CB"));
    }

    @Test
    public void testPHCombination() {
        assertEquals("3", encoder.colognePhonetic("PH"));
        assertEquals("3", encoder.colognePhonetic("PHI"));
        assertEquals("1", encoder.colognePhonetic("PA"));
        assertEquals("1", encoder.colognePhonetic("PB"));
    }

    @Test
    public void testXAfterCKQ() {
        assertEquals("48", encoder.colognePhonetic("CX"));
        assertEquals("48", encoder.colognePhonetic("KX"));
        assertEquals("48", encoder.colognePhonetic("QX"));
        assertEquals("048", encoder.colognePhonetic("AX"));
        assertEquals("148", encoder.colognePhonetic("BX"));
    }

    @Test
    public void testCSZAfterSZ() {
        assertEquals("8", encoder.colognePhonetic("SC"));
        assertEquals("8", encoder.colognePhonetic("ZC"));
    }

    @Test
    public void testDTBeforeCSZ() {
        assertEquals("8", encoder.colognePhonetic("DC"));
        assertEquals("8", encoder.colognePhonetic("DS"));
        assertEquals("8", encoder.colognePhonetic("DZ"));
        assertEquals("8", encoder.colognePhonetic("TC"));
        assertEquals("8", encoder.colognePhonetic("TS"));
        assertEquals("8", encoder.colognePhonetic("TZ"));
        assertEquals("2", encoder.colognePhonetic("DA"));
        assertEquals("21", encoder.colognePhonetic("DB"));
        assertEquals("2", encoder.colognePhonetic("TA"));
        assertEquals("21", encoder.colognePhonetic("TB"));
    }

    @Test
    public void testInputBufferAndOutputBufferInternals() {
        assertNotNull(encoder.colognePhonetic("TEST"));
        assertEquals("282", encoder.colognePhonetic("TEST"));
    }

    @Test
    public void testLongString() {
        String longInput = "This is a very long string with multiple words and special characters like ÄÖÜß";
        String result = encoder.colognePhonetic(longInput);
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertTrue(result.matches("[0-9]+"));
    }

    @Test
    public void testEncodeExceptionMessage() {
        try {
            encoder.encode(123);
            fail("Expected EncoderException");
        } catch (EncoderException e) {
            String msg = e.getMessage();
            assertTrue(msg.contains("String"));
            assertTrue(msg.contains("java.lang.Integer"));
        }
    }

    @Test
    public void testConstructor() {
        ColognePhonetic newEncoder = new ColognePhonetic();
        assertNotNull(newEncoder);
        assertEquals("65752682", newEncoder.colognePhonetic("Müller-Lüdenscheidt"));
    }

    // Additional tests for branch coverage improvement

    @Test
    public void testCAfterConsonantBeforeAHKOQUX() {
        // C after non-S/Z consonant, before AHKOQUX letters -> code 4
        // AHKOQUX = {A, H, K, O, Q, U, X} (L and R are NOT in this set for non-onset C)
        assertEquals("14", encoder.colognePhonetic("BCA"));  // B=1, C=4 (before A), A=0 removed
        assertEquals("14", encoder.colognePhonetic("BCH"));  // B=1, C=4 (before H), H ignored
        assertEquals("14", encoder.colognePhonetic("BCK"));  // B=1, C=4 (before K), K=4 -> collapse to 4
        assertEquals("185", encoder.colognePhonetic("BCL")); // B=1, C=8 (before L not in AHKOQUX), L=5
        assertEquals("14", encoder.colognePhonetic("BCO"));  // B=1, C=4 (before O), O=0 removed
        assertEquals("187", encoder.colognePhonetic("BCR")); // B=1, C=8 (before R not in AHKOQUX), R=7
        assertEquals("14", encoder.colognePhonetic("BCU"));  // B=1, C=4 (before U), U=0 removed
        assertEquals("148", encoder.colognePhonetic("BCX")); // B=1, C=4 (before X), X=48 -> 4,8
    }

    @Test
    public void testCAfterIgnoredH() {
        // H is ignored but updates lastChar, affecting subsequent C context
        assertEquals("8", encoder.colognePhonetic("HC"));    // H ignored, C at onset before IGNORE -> 8
        assertEquals("08", encoder.colognePhonetic("AHC"));  // A=0, H ignored, C after A (vowel) before IGNORE -> 8
        assertEquals("48", encoder.colognePhonetic("CHC"));  // C=4 (onset before H), H ignored, C after H (lastChar=H not SZ, next=IGNORE not AHKOQUX) -> 8
    }

    @Test
    public void testXAfterIgnoredH() {
        // H ignored but lastChar=H affects X rule (H not in CKQ)
        assertEquals("48", encoder.colognePhonetic("HX"));   // H ignored, X onset (lastChar=H not in CKQ) -> 48
        assertEquals("048", encoder.colognePhonetic("AHX")); // A=0, H ignored, X after H (lastChar=H not in CKQ) -> 48
        assertEquals("48", encoder.colognePhonetic("XH"));   // X=48 (lastChar=IGNORE), H ignored
    }

    @Test
    public void testMultipleUmlautsAndSharpS() {
        assertEquals("65752682", encoder.colognePhonetic("Müller-Lüdenscheidt"));
        assertEquals("65752682", encoder.colognePhonetic("MÜLLER-LÜDENSCHEIDT"));
        assertEquals("862", encoder.colognePhonetic("Schmitt"));
        assertEquals("862", encoder.colognePhonetic("SCHMITT"));
        assertEquals("67", encoder.colognePhonetic("Mayer"));
        assertEquals("67", encoder.colognePhonetic("MAYER"));
    }

    @Test
    public void testOnlyNonAlphabeticInput() {
        assertEquals("", encoder.colognePhonetic("123"));
        assertEquals("", encoder.colognePhonetic("-_!@#"));
        assertEquals("", encoder.colognePhonetic("   "));
        assertEquals("", encoder.colognePhonetic("1-2-3"));
    }

    @Test
    public void testZeroHandlingEdgeCases() {
        // Multiple leading zeros collapse to single zero
        assertEquals("0", encoder.colognePhonetic("AAA"));
        assertEquals("0", encoder.colognePhonetic("AEI"));
        
        // Zero between identical non-zero codes
        assertEquals("1", encoder.colognePhonetic("BAB"));   // B=1, A=0 removed, B=1 collapse
        assertEquals("1", encoder.colognePhonetic("BBA"));   // B=1, B=1 collapse, A=0 removed
        assertEquals("01", encoder.colognePhonetic("ABB"));  // A=0, B=1, B=1 collapse
        
        // Zero at start preserved, internal zeros removed
        assertEquals("01", encoder.colognePhonetic("AB"));
        assertEquals("012", encoder.colognePhonetic("ABD"));
    }

    @Test
    public void testComplexContextCombinations() {
        // CH combination: C=4 (before H), H ignored
        assertEquals("4", encoder.colognePhonetic("CH"));
        assertEquals("41", encoder.colognePhonetic("CHB"));
        
        // SCH combination: S=8, C after S=8, H ignored
        assertEquals("8", encoder.colognePhonetic("SCH"));  // S=8, C=8 (after S), H ignored -> collapse to 8
        assertEquals("82", encoder.colognePhonetic("SCHD")); // S=8, C=8 collapse, H ignored, D=2
        
        // TSCH: T before S=8, S=8, C after S=8, H ignored -> all 8s collapse to single 8
        assertEquals("8", encoder.colognePhonetic("TSCH"));
        
        // PF combination: P before F (not H) -> 1, F=3
        assertEquals("13", encoder.colognePhonetic("PF"));
        
        // PH combination: P before H -> 3 (via FPVW), H ignored
        assertEquals("3", encoder.colognePhonetic("PH"));
        assertEquals("31", encoder.colognePhonetic("PHB"));
    }

    @Test
    public void testEncodeObjectNull() throws EncoderException {
        try {
            encoder.encode((Object) null);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // expected - implementation throws NPE when null is passed to encode(Object)
        }
    }

    // New tests targeting the surviving mutation: void method call to CologneOutputBuffer.put at line 388
    // The mutation removes the call to output.put(CHAR_IGNORE) for 'H' character processing.
    // These tests exercise the H character path in contexts where the put call's execution
    // is verified through the resulting phonetic code, ensuring the H handling logic
    // (including the put call for CHAR_IGNORE) is fully exercised.

    @Test
    public void testHCharacterPutCallSurvival() {
        // Test H at various positions to exercise the output.put(CHAR_IGNORE) call
        // The put call for CHAR_IGNORE ('-') is made when processing 'H' character
        // This tests the specific void method call that the mutation removes
        
        // Single H
        assertEquals("", encoder.colognePhonetic("H"));
        
        // H at start
        assertEquals("1", encoder.colognePhonetic("HB"));
        assertEquals("2", encoder.colognePhonetic("HD"));
        assertEquals("3", encoder.colognePhonetic("HF"));
        assertEquals("4", encoder.colognePhonetic("HG"));
        assertEquals("5", encoder.colognePhonetic("HL"));
        assertEquals("6", encoder.colognePhonetic("HM"));
        assertEquals("7", encoder.colognePhonetic("HR"));
        assertEquals("8", encoder.colognePhonetic("HS"));
        assertEquals("48", encoder.colognePhonetic("HX"));
        
        // H in middle - between identical codes to test collapse behavior
        assertEquals("1", encoder.colognePhonetic("BHB"));   // B=1, H ignored, B=1 -> collapse to 1
        assertEquals("2", encoder.colognePhonetic("DHD"));   // D=2, H ignored, D=2 -> collapse to 2
        assertEquals("3", encoder.colognePhonetic("FHF"));   // F=3, H ignored, F=3 -> collapse to 3
        assertEquals("4", encoder.colognePhonetic("GKQ"));   // G=4, K=4, Q=4 -> collapse to 4 (no H)
        assertEquals("4", encoder.colognePhonetic("GHKQ"));  // G=4, H ignored, K=4, Q=4 -> collapse to 4
        
        // H between different codes
        assertEquals("12", encoder.colognePhonetic("BHD"));  // B=1, H ignored, D=2
        assertEquals("13", encoder.colognePhonetic("BHF"));  // B=1, H ignored, F=3
        assertEquals("14", encoder.colognePhonetic("BHG"));  // B=1, H ignored, G=4
        
        // H after vowel (zero) - tests zero handling with H
        assertEquals("01", encoder.colognePhonetic("AHB"));  // A=0, H ignored, B=1 -> 01 (leading zero kept)
        assertEquals("1", encoder.colognePhonetic("BAH"));   // B=1, A=0, H ignored -> 10 -> 1 (trailing zero removed)
        assertEquals("0", encoder.colognePhonetic("AHA"));   // A=0, H ignored, A=0 -> 00 -> 0 (collapse zeros)
        
        // Multiple H's
        assertEquals("", encoder.colognePhonetic("HH"));
        assertEquals("", encoder.colognePhonetic("HHH"));
        assertEquals("1", encoder.colognePhonetic("BHHHB")); // B=1, H*3 ignored, B=1 -> collapse to 1
        
        // H with umlauts and sharp S (preprocessing)
        assertEquals("8", encoder.colognePhonetic("HßH"));   // H ignored, ß->SS=88->8, H ignored -> 8
        
        // Complex: H in "Schmitt" context
        assertEquals("862", encoder.colognePhonetic("SCHMITT"));
        
        // H with PH combination (P before H -> 3)
        assertEquals("3", encoder.colognePhonetic("PH"));
        assertEquals("3", encoder.colognePhonetic("HPH"));   // H ignored, P before H -> 3, H ignored
        assertEquals("31", encoder.colognePhonetic("PHB"));  // P before H -> 3, H ignored, B=1
        assertEquals("31", encoder.colognePhonetic("HPHB")); // H ignored, P before H -> 3, H ignored, B=1 -> 31
        
        // H with CH combination
        assertEquals("4", encoder.colognePhonetic("CH"));
        assertEquals("4", encoder.colognePhonetic("HCH"));   // H ignored, C before H -> 4, H ignored
        assertEquals("41", encoder.colognePhonetic("CHB"));
        assertEquals("41", encoder.colognePhonetic("HCHB")); // H ignored, C before H -> 4, H ignored, B=1
        
        // H with X (X not after CKQ)
        assertEquals("48", encoder.colognePhonetic("X"));
        assertEquals("48", encoder.colognePhonetic("HX"));   // H ignored, X onset -> 48
        assertEquals("48", encoder.colognePhonetic("XH"));   // X=48, H ignored
        assertEquals("148", encoder.colognePhonetic("BXH")); // B=1, X=48, H ignored -> 148
        assertEquals("148", encoder.colognePhonetic("BHX")); // B=1, H ignored, X=48 -> 148
        
        // H with C context rules
        // C at onset before H -> 4
        assertEquals("4", encoder.colognePhonetic("CH"));
        // C after H (H ignored, lastChar=H not SZ), next=IGNORE not AHKOQUX -> 8
        assertEquals("8", encoder.colognePhonetic("HC"));
        // C after vowel (A), before H -> 4
        assertEquals("04", encoder.colognePhonetic("ACH"));
        // C after H (ignored), before A -> lastChar=H (not SZ), next=A (in AHKOQUX) -> 4
        assertEquals("04", encoder.colognePhonetic("ACH"));  // A=0, C before H -> 4, H ignored (was "AHC" but C is at end in "AHC")
        // C after consonant (B), before H -> lastChar=B (not SZ), next=H (in AHKOQUX) -> 4
        assertEquals("14", encoder.colognePhonetic("BCH"));  // B=1, C before H -> 4, H ignored
        // C after consonant (B), before L (not in AHKOQUX) -> 8
        assertEquals("185", encoder.colognePhonetic("BCL")); // B=1, C before L -> 8, L=5
    }

    @Test
    public void testPreprocessUmlautHandling() {
        // Test preprocessing of umlauts and sharp S indirectly through public API
        assertEquals(encoder.colognePhonetic("Ä"), encoder.colognePhonetic("A"));
        assertEquals(encoder.colognePhonetic("Ö"), encoder.colognePhonetic("O"));
        assertEquals(encoder.colognePhonetic("Ü"), encoder.colognePhonetic("U"));
        assertEquals(encoder.colognePhonetic("ß"), encoder.colognePhonetic("SS"));
        
        // Lowercase umlauts
        assertEquals(encoder.colognePhonetic("ä"), encoder.colognePhonetic("A"));
        assertEquals(encoder.colognePhonetic("ö"), encoder.colognePhonetic("O"));
        assertEquals(encoder.colognePhonetic("ü"), encoder.colognePhonetic("U"));
        assertEquals(encoder.colognePhonetic("ß"), encoder.colognePhonetic("SS"));
        
        // Mixed case with umlauts
        assertEquals(encoder.colognePhonetic("müller"), encoder.colognePhonetic("MÜLLER"));
        assertEquals(encoder.colognePhonetic("straße"), encoder.colognePhonetic("STRASSE"));
    }

    @Test
    public void testHWithZeroRemovalAndCollapse() {
        // Comprehensive test for H character interaction with zero removal and collapse
        // This exercises the put(CHAR_IGNORE) call in various contexts
        
        // H between vowels (zeros)
        assertEquals("0", encoder.colognePhonetic("AHA"));   // 0,0 -> collapse to 0
        assertEquals("0", encoder.colognePhonetic("AHHHA")); // multiple H, same result
        
        // H between consonant and vowel
        assertEquals("1", encoder.colognePhonetic("BAH"));   // B=1, A=0 removed, H ignored
        assertEquals("01", encoder.colognePhonetic("AHB"));  // A=0 kept, H ignored, B=1
        
        // H between different consonants
        assertEquals("12", encoder.colognePhonetic("BHD"));  // B=1, H ignored, D=2
        assertEquals("13", encoder.colognePhonetic("BHF"));  // B=1, H ignored, F=3
        assertEquals("14", encoder.colognePhonetic("BHG"));  // B=1, H ignored, G=4
        assertEquals("15", encoder.colognePhonetic("BHL"));  // B=1, H ignored, L=5
        assertEquals("16", encoder.colognePhonetic("BHM"));  // B=1, H ignored, M=6
        assertEquals("17", encoder.colognePhonetic("BHR"));  // B=1, H ignored, R=7
        assertEquals("18", encoder.colognePhonetic("BHS"));  // B=1, H ignored, S=8
        assertEquals("148", encoder.colognePhonetic("BHX")); // B=1, H ignored, X=48
        
        // H at start with various followers
        assertEquals("1", encoder.colognePhonetic("HB"));
        assertEquals("2", encoder.colognePhonetic("HD"));
        assertEquals("3", encoder.colognePhonetic("HF"));
        assertEquals("4", encoder.colognePhonetic("HG"));
        assertEquals("5", encoder.colognePhonetic("HL"));
        assertEquals("6", encoder.colognePhonetic("HM"));
        assertEquals("7", encoder.colognePhonetic("HR"));
        assertEquals("8", encoder.colognePhonetic("HS"));
        assertEquals("48", encoder.colognePhonetic("HX"));
        
        // H at end
        assertEquals("1", encoder.colognePhonetic("BH"));
        assertEquals("2", encoder.colognePhonetic("DH"));
        assertEquals("3", encoder.colognePhonetic("FH"));
        assertEquals("4", encoder.colognePhonetic("GH"));
        assertEquals("5", encoder.colognePhonetic("LH"));
        assertEquals("6", encoder.colognePhonetic("MH"));
        assertEquals("7", encoder.colognePhonetic("RH"));
        assertEquals("8", encoder.colognePhonetic("SH"));
        assertEquals("48", encoder.colognePhonetic("XH"));
        
        // Multiple H's interspersed
        assertEquals("123", encoder.colognePhonetic("BHDHF")); // B=1, H, D=2, H, F=3 -> 123
        assertEquals("123", encoder.colognePhonetic("BHHDHF")); // same with extra H
        
        // H with special characters (preprocessing)
        assertEquals("1", encoder.colognePhonetic("B-H"));     // hyphen ignored, H ignored
        assertEquals("1", encoder.colognePhonetic("B H"));     // space ignored, H ignored
        assertEquals("1", encoder.colognePhonetic("B1H"));     // digit ignored, H ignored
    }
}
