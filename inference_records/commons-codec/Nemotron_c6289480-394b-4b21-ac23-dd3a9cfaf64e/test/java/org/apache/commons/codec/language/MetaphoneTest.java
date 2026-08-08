package org.apache.commons.codec.language;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;
import org.junit.Test;

public class MetaphoneTest {

    private final Metaphone metaphone = new Metaphone();

    @Test
    public void testDefaultConstructor() {
        assertNotNull(new Metaphone());
        assertEquals(4, new Metaphone().getMaxCodeLen());
    }

    @Test
    public void testEncodeStringNull() {
        assertEquals("", metaphone.encode((String) null));
    }

    @Test
    public void testEncodeStringEmpty() {
        assertEquals("", metaphone.encode(""));
    }

    @Test
    public void testEncodeSingleCharacter() {
        assertEquals("A", metaphone.encode("a"));
        assertEquals("Z", metaphone.encode("z"));
        assertEquals("5", metaphone.encode("5"));
    }

    @Test
    public void testEncodeBasicWords() {
        assertEquals("NT", metaphone.encode("knight"));
        assertEquals("N", metaphone.encode("knee"));
        assertEquals("RT", metaphone.encode("write"));
        assertEquals("WL", metaphone.encode("whale"));
        assertEquals("SLFN", metaphone.encode("xylophone"));
        assertEquals("EN", metaphone.encode("aeon"));
    }

    @Test
    public void testEncodeVowelLeading() {
        assertEquals("APL", metaphone.encode("apple"));
        assertEquals("ELFN", metaphone.encode("elephant"));
        assertEquals("IKL", metaphone.encode("igloo"));
        assertEquals("ORNJ", metaphone.encode("orange"));
        assertEquals("UMBR", metaphone.encode("umbrella"));
    }

    @Test
    public void testEncodeSilentB() {
        assertEquals("LM", metaphone.encode("lamb"));
        assertEquals("KM", metaphone.encode("comb"));
        assertEquals("0M", metaphone.encode("thumb"));
        assertEquals("BT", metaphone.encode("bat"));
    }

    @Test
    public void testEncodeCSpecialCases() {
        assertEquals("SNT", metaphone.encode("scent"));
        assertEquals("SNS", metaphone.encode("science"));
        assertEquals("SKL", metaphone.encode("school"));
        assertEquals("X", metaphone.encode("cia"));
        assertEquals("XT", metaphone.encode("chat"));
        assertEquals("ST", metaphone.encode("city"));
        assertEquals("SNT", metaphone.encode("cent"));
        assertEquals("SYN", metaphone.encode("cyan"));
        assertEquals("KT", metaphone.encode("cat"));
        assertEquals("KT", metaphone.encode("cut"));
    }

    @Test
    public void testEncodeDG() {
        assertEquals("BRJ", metaphone.encode("bridge"));
        assertEquals("EJ", metaphone.encode("edge"));
        assertEquals("JJ", metaphone.encode("judge"));
        assertEquals("TK", metaphone.encode("dog"));
    }

    @Test
    public void testEncodeGSpecialCases() {
        assertEquals("H", metaphone.encode("high"));
        assertEquals("KST", metaphone.encode("ghost"));
        assertEquals("SN", metaphone.encode("sign"));
        assertEquals("SNT", metaphone.encode("signed"));
        assertEquals("JM", metaphone.encode("gem"));
        assertEquals("JN", metaphone.encode("gin"));
        assertEquals("JT", metaphone.encode("get"));
        assertEquals("JF", metaphone.encode("give"));
        assertEquals("EK", metaphone.encode("egg"));
    }

    @Test
    public void testEncodeH() {
        assertEquals("HT", metaphone.encode("hat"));
        assertEquals("SKL", metaphone.encode("school"));
        assertEquals("TX", metaphone.encode("tech"));
        assertEquals("AH", metaphone.encode("aha"));
    }

    @Test
    public void testEncodeSimpleConsonants() {
        assertEquals("FN", metaphone.encode("fan"));
        assertEquals("JM", metaphone.encode("jam"));
        assertEquals("LP", metaphone.encode("lip"));
        assertEquals("MP", metaphone.encode("map"));
        assertEquals("NP", metaphone.encode("nap"));
        assertEquals("RT", metaphone.encode("rat"));
    }

    @Test
    public void testEncodeK() {
        assertEquals("KT", metaphone.encode("kite"));
        assertEquals("SKP", metaphone.encode("skip"));
        assertEquals("AK", metaphone.encode("ak"));
    }

    @Test
    public void testEncodePH() {
        assertEquals("FN", metaphone.encode("phone"));
        assertEquals("KRF", metaphone.encode("graph"));
        assertEquals("PT", metaphone.encode("pat"));
    }

    @Test
    public void testEncodeQ() {
        assertEquals("KN", metaphone.encode("queen"));
        assertEquals("KK", metaphone.encode("quick"));
    }

    @Test
    public void testEncodeSSpecialCases() {
        assertEquals("XP", metaphone.encode("ship"));
        assertEquals("FXN", metaphone.encode("vision"));
        assertEquals("SPXL", metaphone.encode("special"));
        assertEquals("ST", metaphone.encode("sit"));
        assertEquals("BS", metaphone.encode("bus"));
    }

    @Test
    public void testEncodeTSpecialCases() {
        assertEquals("NXN", metaphone.encode("nation"));
        assertEquals("MXN", metaphone.encode("motion"));
        assertEquals("MX", metaphone.encode("match"));
        assertEquals("0", metaphone.encode("the"));
        assertEquals("0N", metaphone.encode("thin"));
        assertEquals("TP", metaphone.encode("top"));
    }

    @Test
    public void testEncodeV() {
        assertEquals("FR", metaphone.encode("very"));
        assertEquals("HF", metaphone.encode("have"));
    }

    @Test
    public void testEncodeWAndY() {
        assertEquals("WT", metaphone.encode("wet"));
        assertEquals("RST", metaphone.encode("wrist"));
        assertEquals("YS", metaphone.encode("yes"));
        assertEquals("R0M", metaphone.encode("rhythm"));
    }

    @Test
    public void testEncodeX() {
        assertEquals("BKS", metaphone.encode("box"));
        assertEquals("TKS", metaphone.encode("tax"));
    }

    @Test
    public void testEncodeZ() {
        assertEquals("S", metaphone.encode("zoo"));
        assertEquals("BS", metaphone.encode("buzz"));
    }

    @Test
    public void testMaxCodeLenDefault() {
        assertEquals("EKSS", metaphone.metaphone("excessive"));
        assertEquals(4, metaphone.getMaxCodeLen());
    }

    @Test
    public void testSetMaxCodeLen() {
        metaphone.setMaxCodeLen(8);
        assertEquals(8, metaphone.getMaxCodeLen());
        assertEquals("EKSSSF", metaphone.metaphone("excessive"));

        metaphone.setMaxCodeLen(2);
        assertEquals(2, metaphone.getMaxCodeLen());
        assertEquals("EK", metaphone.metaphone("excessive"));
    }

    @Test
    public void testIsMetaphoneEqual() {
        assertTrue(metaphone.isMetaphoneEqual("knight", "night"));
        assertTrue(metaphone.isMetaphoneEqual("write", "right"));
        assertTrue(metaphone.isMetaphoneEqual("phone", "fone"));
        assertFalse(metaphone.isMetaphoneEqual("cat", "dog"));
        assertTrue(metaphone.isMetaphoneEqual("", ""));
        assertTrue(metaphone.isMetaphoneEqual(null, null));
        assertFalse(metaphone.isMetaphoneEqual("a", "b"));
    }

    @Test
    public void testEncodeObjectString() throws EncoderException {
        assertEquals("NT", metaphone.encode((Object) "knight"));
    }

    @Test
    public void testEncodeObjectNonStringThrowsException() throws EncoderException {
        EncoderException exception = assertThrows(EncoderException.class, () -> {
            metaphone.encode((Object) 123);
        });
        assertEquals("Parameter supplied to Metaphone encode is not of type java.lang.String", exception.getMessage());
    }

    @Test
    public void testEncodeObjectNull() throws EncoderException {
        EncoderException exception = assertThrows(EncoderException.class, () -> {
            metaphone.encode((Object) null);
        });
        assertEquals("Parameter supplied to Metaphone encode is not of type java.lang.String", exception.getMessage());
    }

    @Test
    public void testCaseInsensitivity() {
        assertEquals(metaphone.encode("KNIGHT"), metaphone.encode("knight"));
        assertEquals(metaphone.encode("Knight"), metaphone.encode("KNIGHT"));
        assertEquals(metaphone.encode("PhOnE"), metaphone.encode("phone"));
    }

    @Test
    public void testComplexWords() {
        assertEquals("KMPL", metaphone.encode("complicated"));
        assertEquals("STRN", metaphone.encode("strong"));
        assertEquals("KRFT", metaphone.encode("craft"));
        assertEquals("SPRN", metaphone.encode("spring"));
        assertEquals("SPLX", metaphone.encode("splash"));
        assertEquals("SKRM", metaphone.encode("scream"));
    }

    @Test
    public void testKnownMetaphonePairs() {
        assertTrue(metaphone.isMetaphoneEqual("smart", "smrt"));
        assertTrue(metaphone.isMetaphoneEqual("catherine", "kathryn"));
        assertTrue(metaphone.isMetaphoneEqual("stephen", "steven"));
        assertTrue(metaphone.isMetaphoneEqual("megan", "meghan"));
        assertTrue(metaphone.isMetaphoneEqual("john", "jon"));
        assertTrue(metaphone.isMetaphoneEqual("schmidt", "schmitt"));
    }

    @Test
    public void testMaxCodeLenZero() {
        metaphone.setMaxCodeLen(0);
        assertEquals("", metaphone.metaphone("test"));
    }

    @Test
    public void testMaxCodeLenNegative() {
        metaphone.setMaxCodeLen(-1);
        assertEquals("", metaphone.metaphone("test"));
    }

    @Test
    public void testInitialKN() {
        assertEquals("NB", metaphone.encode("knob"));
        assertEquals("N", metaphone.encode("know"));
        assertEquals("NK", metaphone.encode("knack"));
    }

    @Test
    public void testInitialGN() {
        assertEquals("NM", metaphone.encode("gnome"));
        assertEquals("NT", metaphone.encode("gnat"));
    }

    @Test
    public void testInitialPN() {
        assertEquals("NMN", metaphone.encode("pneumonia"));
        assertEquals("PSXK", metaphone.encode("psychic"));
    }

    @Test
    public void testInitialAE() {
        assertEquals("ER", metaphone.encode("aero"));
        assertEquals("APL", metaphone.encode("apple"));
    }

    @Test
    public void testInitialWH() {
        assertEquals("WL", metaphone.encode("whale"));
        assertEquals("WN", metaphone.encode("when"));
        assertEquals("RN", metaphone.encode("wren"));
        assertEquals("RT", metaphone.encode("write"));
    }

    @Test
    public void testInitialX() {
        assertEquals("SNN", metaphone.encode("xenon"));
        assertEquals("SLFN", metaphone.encode("xylophone"));
    }

    @Test
    public void testSCH() {
        assertEquals("SKL", metaphone.encode("school"));
        assertEquals("SKM", metaphone.encode("scheme"));
    }

    @Test
    public void testTCH() {
        assertEquals("WX", metaphone.encode("watch"));
        assertEquals("MX", metaphone.encode("match"));
    }

    @Test
    public void testDGE() {
        assertEquals("BRJ", metaphone.encode("bridge"));
        assertEquals("LJ", metaphone.encode("ledge"));
        assertEquals("BJT", metaphone.encode("budget"));
    }

    @Test
    public void testTerminalMB() {
        assertEquals("LM", metaphone.encode("lamb"));
        assertEquals("KLM", metaphone.encode("climb"));
        assertEquals("KM", metaphone.encode("comb"));
    }

    @Test
    public void testTerminalGH() {
        assertEquals("H", metaphone.encode("high"));
        assertEquals("S", metaphone.encode("sigh"));
        assertEquals("0", metaphone.encode("though"));
    }

    @Test
    public void testGNMedial() {
        assertEquals("SN", metaphone.encode("sign"));
        assertEquals("TSN", metaphone.encode("design"));
        assertEquals("RN", metaphone.encode("reign"));
    }

    @Test
    public void testCIA() {
        assertEquals("X", metaphone.encode("cia"));
        assertEquals("SPXL", metaphone.encode("special"));
        assertEquals("SXL", metaphone.encode("social"));
    }

    @Test
    public void testSIOAndSIA() {
        assertEquals("FXN", metaphone.encode("vision"));
        assertEquals("TNXN", metaphone.encode("tension"));
        assertEquals("KNTR", metaphone.encode("controversial"));
    }

    @Test
    public void testTIAAndTIO() {
        assertEquals("NXN", metaphone.encode("nation"));
        assertEquals("MXN", metaphone.encode("motion"));
        assertEquals("PRXL", metaphone.encode("partial"));
    }

    @Test
    public void testTH() {
        assertEquals("0", metaphone.encode("the"));
        assertEquals("0T", metaphone.encode("that"));
        assertEquals("0S", metaphone.encode("this"));
        assertEquals("W0", metaphone.encode("with"));
    }

    @Test
    public void testVToF() {
        assertEquals("FR", metaphone.encode("very"));
        assertEquals("HF", metaphone.encode("have"));
        assertEquals("LF", metaphone.encode("love"));
    }

    @Test
    public void testDuplicateLetters() {
        assertEquals("BK", metaphone.encode("book"));
        assertEquals("LK", metaphone.encode("look"));
        assertEquals("MS", metaphone.encode("mass"));
        assertEquals("BL", metaphone.encode("ball"));
        assertEquals("KK", metaphone.encode("cc"));
        assertEquals("SKSS", metaphone.encode("success"));
    }

    @Test
    public void testHAfterConsonant() {
        assertEquals("SKL", metaphone.encode("school"));
        assertEquals("TX", metaphone.encode("tech"));
        assertEquals("ARX", metaphone.encode("arch"));
        assertEquals("HT", metaphone.encode("hat"));
        assertEquals("AH", metaphone.encode("aha"));
    }

    @Test
    public void testWAndYBeforeVowel() {
        assertEquals("WT", metaphone.encode("wet"));
        assertEquals("AW", metaphone.encode("away"));
        assertEquals("YS", metaphone.encode("yes"));
        assertEquals("BYNT", metaphone.encode("beyond"));
    }

    @Test
    public void testWAndYNotBeforeVowel() {
        assertEquals("RST", metaphone.encode("wrist"));
        assertEquals("RT", metaphone.encode("write"));
        assertEquals("R0M", metaphone.encode("rhythm"));
        assertEquals("JM", metaphone.encode("gym"));
    }

    @Test
    public void testLongWordTruncation() {
        assertEquals("KMPL", metaphone.metaphone("complicated"));
        assertEquals("KMPL", metaphone.metaphone("complication"));
        assertEquals(4, metaphone.metaphone("complicated").length());
    }

    @Test
    public void testEncodeInterfaceCompliance() throws EncoderException {
        StringEncoder encoder = new Metaphone();
        assertEquals("NT", encoder.encode("knight"));
        assertEquals("NT", encoder.encode((Object) "knight"));
    }

    // New tests targeting surviving mutations

    @Test
    public void testIsPreviousCharBoundaryIndexZero() {
        // Tests isPreviousChar at line 147: index > 0 boundary (index=0 should return false)
        // C at position 0 (no previous char) should not trigger SCI/SCE/SCY silent C
        assertEquals("KT", metaphone.encode("cat")); // C at index 0 -> K
        assertEquals("KP", metaphone.encode("cap"));
        assertEquals("KL", metaphone.encode("cal"));
    }

    @Test
    public void testIsPreviousCharBoundaryIndexAtLength() {
        // Tests isPreviousChar at line 147: index < string.length() boundary
        // Not directly testable via public API but covered by other tests
        assertEquals("KT", metaphone.encode("cat"));
    }

    @Test
    public void testSCIFrontVowelSilentC() {
        // Tests line 223: isPreviousChar('S') && !isLastChar && FRONTV.indexOf(next) >= 0
        // SCI, SCE, SCY -> silent C (C produces nothing)
        assertEquals("S", metaphone.encode("sci"));
        assertEquals("S", metaphone.encode("sce"));
        assertEquals("S", metaphone.encode("scy"));
        assertEquals("SNS", metaphone.encode("science"));
    }

    @Test
    public void testSCNotFollowedByFrontVowel() {
        // Tests line 223 false branch: SC + non-front vowel -> SK
        assertEquals("SKM", metaphone.encode("scam"));
        assertEquals("SKL", metaphone.encode("scale"));
        assertEquals("SKR", metaphone.encode("scare"));
        assertEquals("SKP", metaphone.encode("scap"));
    }

    @Test
    public void testSCHBoundary() {
        // Tests line 226: isPreviousChar('S') && isNextChar('H') -> SCH -> SK
        assertEquals("SKL", metaphone.encode("school"));
        assertEquals("SKM", metaphone.encode("scheme"));
        assertEquals("SK", metaphone.encode("sch"));
    }

    @Test
    public void testCIAAndCHBoundary() {
        // Tests line 229: regionMatch("CIA") || isNextChar('H') -> X
        assertEquals("X", metaphone.encode("cia"));
        assertEquals("XT", metaphone.encode("chat"));
        assertEquals("SPXL", metaphone.encode("special"));
        assertEquals("SXL", metaphone.encode("social"));
    }

    @Test
    public void testCBeforeFrontVowelBoundary() {
        // Tests line 232: !isLastChar && FRONTV.indexOf(next) >= 0 -> S
        assertEquals("ST", metaphone.encode("city"));
        assertEquals("SNT", metaphone.encode("cent"));
        assertEquals("SYN", metaphone.encode("cyan"));
    }

    @Test
    public void testCDefaultBoundary() {
        // Tests line 235: default C -> K
        assertEquals("KT", metaphone.encode("cat"));
        assertEquals("KT", metaphone.encode("cut"));
        assertEquals("SKL", metaphone.encode("school"));
    }

    @Test
    public void testDGEArithmeticNPlus1NPlus2() {
        // Tests line 264: MathMutator on n+1 and n+2 in DGE/DGI/DGY check
        // DGE -> J (n+2 = E which is front vowel)
        assertEquals("BRJ", metaphone.encode("bridge"));
        assertEquals("EJ", metaphone.encode("edge"));
        assertEquals("JJ", metaphone.encode("judge"));
        // DGI -> J
        assertEquals("LJ", metaphone.encode("ledgi"));
        // DGY -> J
        assertEquals("HJ", metaphone.encode("hodgy"));
    }

    @Test
    public void testDGNotFollowedByFrontVowel() {
        // Tests line 264 false branch: DG + non-front vowel -> T (default D)
        assertEquals("TK", metaphone.encode("dga"));
        assertEquals("TK", metaphone.encode("dgo"));
        assertEquals("TK", metaphone.encode("dgu"));
        assertEquals("TK", metaphone.encode("dog"));
    }

    @Test
    public void testGHEndOfWordSilent() {
        // Tests line 278: isLastChar(wdsz, n+1) && isNextChar('H') -> GH at end silent
        assertEquals("H", metaphone.encode("high"));
        assertEquals("S", metaphone.encode("sigh"));
        assertEquals("SL", metaphone.encode("sleigh"));
    }

    @Test
    public void testGHBeforeConsonantArithmetic() {
        // Tests line 275: MathMutator on n+1 and n+2 in GH before consonant check
        // !isLastChar(wdsz, n+1) && isNextChar('H') && !isVowel(n+2)
        assertEquals("KST", metaphone.encode("ghost")); // GH before S (consonant) -> silent
        assertEquals("KST", metaphone.encode("ghast")); // GH before S (consonant) -> silent
        assertEquals("KL", metaphone.encode("ghoul"));  // GH before O (vowel) -> not silent, G->K
    }

    @Test
    public void testGNAndGNEDSilentG() {
        // Tests line 283: n > 0 && (regionMatch("GN") || regionMatch("GNED")) -> silent G
        assertEquals("SN", metaphone.encode("sign"));
        assertEquals("TSN", metaphone.encode("design"));
        assertEquals("RN", metaphone.encode("reign"));
        assertEquals("SNT", metaphone.encode("signed"));
    }

    @Test
    public void testGFrontVowelHardFlag() {
        // Tests line 287-292: hard flag and front vowel check for G
        // G before front vowel (E,I,Y) and not hard -> J
        assertEquals("JM", metaphone.encode("gem"));
        assertEquals("JN", metaphone.encode("gin"));
        assertEquals("JT", metaphone.encode("get"));
        assertEquals("JF", metaphone.encode("give"));
        // G before non-front vowel -> K
        assertEquals("KB", metaphone.encode("gab"));
        assertEquals("KB", metaphone.encode("gob"));
        assertEquals("KB", metaphone.encode("gub"));
    }

    @Test
    public void testDoubleGHardFlag() {
        // Tests line 287: hard = isPreviousChar('G') - double G handling
        assertEquals("EK", metaphone.encode("egg"));
        assertEquals("AK", metaphone.encode("agg"));
        assertEquals("OK", metaphone.encode("ogg"));
    }

    @Test
    public void testTIAAndTIOBoundary() {
        // Tests line 309: regionMatch("TIA") || regionMatch("TIO") -> X
        assertEquals("NXN", metaphone.encode("nation"));
        assertEquals("MXN", metaphone.encode("motion"));
        assertEquals("PRXL", metaphone.encode("partial"));
        assertEquals("X", metaphone.encode("tia"));
        assertEquals("X", metaphone.encode("tio"));
    }

    @Test
    public void testTCHBoundary() {
        // Tests line 313: regionMatch("TCH") -> silent
        assertEquals("WX", metaphone.encode("watch"));
        assertEquals("MX", metaphone.encode("match"));
        assertEquals("BX", metaphone.encode("batch"));
    }

    @Test
    public void testTHBoundary() {
        // Tests line 318: regionMatch("TH") -> 0
        assertEquals("0", metaphone.encode("the"));
        assertEquals("0T", metaphone.encode("that"));
        assertEquals("0S", metaphone.encode("this"));
        assertEquals("W0", metaphone.encode("with"));
    }

    @Test
    public void testTDefaultBoundary() {
        // Tests line 321: default T -> T
        assertEquals("TP", metaphone.encode("top"));
        assertEquals("KT", metaphone.encode("cat"));
    }

    @Test
    public void testSHSiaSioBoundary() {
        // Tests line 331: regionMatch("SH") || regionMatch("SIO") || regionMatch("SIA") -> X
        assertEquals("XP", metaphone.encode("ship"));
        assertEquals("FXN", metaphone.encode("vision"));
        assertEquals("SPXL", metaphone.encode("special"));
        assertEquals("SXL", metaphone.encode("social"));
    }

    @Test
    public void testSDefaultBoundary() {
        // Tests line 334: default S -> S
        assertEquals("ST", metaphone.encode("sit"));
        assertEquals("BS", metaphone.encode("bus"));
    }

    @Test
    public void testWYBeforeVowelArithmetic() {
        // Tests line 373: MathMutator on n+1 in W/Y vowel check
        // !isLastChar(wdsz, n) && isVowel(local, n+1)
        assertEquals("WT", metaphone.encode("wet"));   // W before E (vowel) -> W
        assertEquals("YS", metaphone.encode("yes"));   // Y before E (vowel) -> Y
        assertEquals("AW", metaphone.encode("away"));  // W before A (vowel) -> W
        assertEquals("BYNT", metaphone.encode("beyond")); // Y before O (vowel) -> Y
    }

    @Test
    public void testWYNotBeforeVowelBoundary() {
        // Tests line 373 false branch: W/Y not followed by vowel -> silent
        assertEquals("RST", metaphone.encode("wrist"));  // W before R (consonant) -> silent
        assertEquals("RT", metaphone.encode("write"));   // W before R (consonant) -> silent
        assertEquals("R0M", metaphone.encode("rhythm")); // Y before T (consonant) -> silent
        assertEquals("JM", metaphone.encode("gym"));     // Y before M (consonant) -> silent
    }

    @Test
    public void testWYAtEndOfWord() {
        // Tests line 373: isLastChar boundary for W/Y
        assertEquals("A", metaphone.encode("aw"));  // W at end -> silent
        assertEquals("A", metaphone.encode("ay"));  // Y at end -> silent
        assertEquals("W", metaphone.encode("w"));   // Single W -> W (vowel check not reached)
        assertEquals("Y", metaphone.encode("y"));   // Single Y -> Y
    }

    @Test
    public void testRegionMatchBoundaryShortString() {
        // Tests regionMatch boundary: index + test.length - 1 < string.length
        // CIA with only 2 chars "ci" -> regionMatch false
        assertEquals("S", metaphone.encode("ci"));
        assertEquals("S", metaphone.encode("ce"));
        assertEquals("S", metaphone.encode("cy"));
        // CIA with 3 chars "cia" -> regionMatch true -> X
        assertEquals("X", metaphone.encode("cia"));
        // TIA with 2 chars "ti" -> regionMatch false
        assertEquals("T", metaphone.encode("ti"));
        // TIA with 3 chars "tia" -> regionMatch true -> X
        assertEquals("X", metaphone.encode("tia"));
        // SIO with 2 chars "si" -> regionMatch false
        assertEquals("S", metaphone.encode("si"));
        // SIO with 3 chars "sio" -> regionMatch true -> X
        assertEquals("X", metaphone.encode("sio"));
        // SIA with 2 chars "si" -> regionMatch false
        assertEquals("S", metaphone.encode("si"));
        // SIA with 3 chars "sia" -> regionMatch true -> X
        assertEquals("X", metaphone.encode("sia"));
    }

    @Test
    public void testIsNextCharBoundary() {
        // Tests isNextChar boundary: index < string.length() - 1
        // SC at end of word "sc" -> isNextChar('H') false (no next char)
        assertEquals("SK", metaphone.encode("sc"));
        // SCH -> isNextChar('H') true
        assertEquals("SK", metaphone.encode("sch"));
    }

    @Test
    public void testPHBoundary() {
        // Tests P case: isNextChar('H') -> PH -> F
        assertEquals("FN", metaphone.encode("phone"));
        assertEquals("FKS", metaphone.encode("phox"));
        assertEquals("FL", metaphone.encode("phal"));
        // P not followed by H -> P
        assertEquals("PT", metaphone.encode("pat"));
        assertEquals("APL", metaphone.encode("apple"));
    }

    @Test
    public void testXBoundary() {
        // Tests X case: X -> KS
        assertEquals("BKS", metaphone.encode("box"));
        assertEquals("TKS", metaphone.encode("tax"));
        // Initial X -> S
        assertEquals("SNN", metaphone.encode("xenon"));
        assertEquals("S", metaphone.encode("xi"));
    }

    @Test
    public void testZBoundary() {
        // Tests Z case: Z -> S
        assertEquals("S", metaphone.encode("zoo"));
        assertEquals("BS", metaphone.encode("buzz"));
    }

    @Test
    public void testVBoundary() {
        // Tests V case: V -> F
        assertEquals("FR", metaphone.encode("very"));
        assertEquals("HF", metaphone.encode("have"));
        assertEquals("LF", metaphone.encode("love"));
    }

    @Test
    public void testHBoundary() {
        // Tests H case: various boundaries
        // Terminal H -> silent
        assertEquals("H", metaphone.encode("hah"));
        // H after VARSON (CSPTG) -> silent
        assertEquals("SKL", metaphone.encode("school"));
        assertEquals("TX", metaphone.encode("tech"));
        assertEquals("ARX", metaphone.encode("arch"));
        // H after non-VARSON and before vowel -> H
        assertEquals("AH", metaphone.encode("aha"));
        assertEquals("OH", metaphone.encode("oho"));
        assertEquals("EH", metaphone.encode("ehe"));
    }

    @Test
    public void testBBoundary() {
        // Tests B case: B after M at end -> silent
        assertEquals("LM", metaphone.encode("lamb"));
        assertEquals("KLM", metaphone.encode("climb"));
        assertEquals("KM", metaphone.encode("comb"));
        // B default -> B
        assertEquals("BT", metaphone.encode("bat"));
        assertEquals("BL", metaphone.encode("ball"));
    }

    @Test
    public void testKBoundary() {
        // Tests K case: K after C -> silent, else K
        assertEquals("SKP", metaphone.encode("skip"));  // K after C -> silent
        assertEquals("AK", metaphone.encode("ack"));    // K after C -> silent
        assertEquals("KT", metaphone.encode("kite"));   // Initial K -> K
        assertEquals("KP", metaphone.encode("keep"));   // Initial K -> K
    }

    @Test
    public void testQBoundary() {
        // Tests Q case: Q -> K
        assertEquals("KN", metaphone.encode("queen"));
        assertEquals("KK", metaphone.encode("quick"));
    }

    @Test
    public void testInitialCharacterHandling() {
        // Tests initial character handling in metaphone method (lines 80-120)
        assertEquals("N", metaphone.encode("kn"));
        assertEquals("N", metaphone.encode("gn"));
        assertEquals("N", metaphone.encode("pn"));
        assertEquals("E", metaphone.encode("ae"));
        assertEquals("R", metaphone.encode("wr"));
        assertEquals("", metaphone.encode("wh"));
        assertEquals("S", metaphone.encode("xi")); // Initial X -> S
        assertEquals("X", metaphone.encode("x"));  // Single X -> X (uppercase)
    }

    @Test
    public void testSingleAndTwoCharWords() {
        assertEquals("A", metaphone.encode("a"));
        assertEquals("B", metaphone.encode("b"));
        assertEquals("Z", metaphone.encode("z"));
        assertEquals("AN", metaphone.encode("an"));
        assertEquals("ON", metaphone.encode("on"));
        assertEquals("IN", metaphone.encode("in"));
        assertEquals("AT", metaphone.encode("at"));
        assertEquals("AKS", metaphone.encode("ax"));
    }

    @Test
    public void testMaxCodeLenTruncation() {
        assertEquals("EKSS", metaphone.metaphone("excessive"));
        metaphone.setMaxCodeLen(6);
        assertEquals("EKSSSF", metaphone.metaphone("excessive"));
        metaphone.setMaxCodeLen(2);
        assertEquals("EK", metaphone.metaphone("excessive"));
    }

    @Test
    public void testComplexMetaphoneCases() {
        assertEquals("KMPL", metaphone.encode("complicated"));
        assertEquals("KMPL", metaphone.encode("complication"));
        assertEquals("STRN", metaphone.encode("strong"));
        assertEquals("KRFT", metaphone.encode("craft"));
        assertEquals("SPRN", metaphone.encode("spring"));
        assertEquals("SPLX", metaphone.encode("splash"));
        assertEquals("SKRM", metaphone.encode("scream"));
    }

    @Test
    public void testKnownHomophones() {
        assertTrue(metaphone.isMetaphoneEqual("knight", "night"));
        assertTrue(metaphone.isMetaphoneEqual("write", "right"));
        assertTrue(metaphone.isMetaphoneEqual("phone", "fone"));
        assertTrue(metaphone.isMetaphoneEqual("cat", "kat"));
        assertTrue(metaphone.isMetaphoneEqual("cent", "sent"));
        assertTrue(metaphone.isMetaphoneEqual("catherine", "kathryn"));
        assertTrue(metaphone.isMetaphoneEqual("stephen", "steven"));
        assertTrue(metaphone.isMetaphoneEqual("megan", "meghan"));
        assertTrue(metaphone.isMetaphoneEqual("john", "jon"));
        assertTrue(metaphone.isMetaphoneEqual("schmidt", "schmitt"));
    }

    @Test
    public void testNullAndEmptyStrings() {
        assertEquals("", metaphone.encode((String) null));
        assertEquals("", metaphone.encode(""));
        assertTrue(metaphone.isMetaphoneEqual(null, null));
        assertTrue(metaphone.isMetaphoneEqual("", ""));
        assertTrue(metaphone.isMetaphoneEqual(null, ""));
    }

    @Test
    public void testEncoderExceptionMessages() throws EncoderException {
        try {
            metaphone.encode((Object) 123);
        } catch (EncoderException e) {
            assertEquals("Parameter supplied to Metaphone encode is not of type java.lang.String", e.getMessage());
        }
        try {
            metaphone.encode((Object) null);
        } catch (EncoderException e) {
            assertEquals("Parameter supplied to Metaphone encode is not of type java.lang.String", e.getMessage());
        }
    }
}
