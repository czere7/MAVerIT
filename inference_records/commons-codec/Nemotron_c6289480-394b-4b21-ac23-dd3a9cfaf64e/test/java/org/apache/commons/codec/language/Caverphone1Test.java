package org.apache.commons.codec.language;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.apache.commons.codec.EncoderException;
import org.junit.Test;

public class Caverphone1Test {

    private final Caverphone1 encoder = new Caverphone1();

    @Test
    public void testNullInputReturnsSixOnes() {
        assertEquals("111111", encoder.encode(null));
    }

    @Test
    public void testEmptyStringReturnsSixOnes() {
        assertEquals("111111", encoder.encode(""));
    }

    @Test
    public void testResultAlwaysSixCharacters() {
        String result = encoder.encode("test");
        assertNotNull(result);
        assertEquals(6, result.length());
    }

    @Test
    public void testEncodeObjectDelegate() throws Exception {
        Object result = encoder.encode((Object) "test");
        assertTrue(result instanceof String);
        assertEquals(6, ((String) result).length());
    }

    @Test
    public void testEncodeObjectNonStringThrows() {
        try {
            encoder.encode((Object) Integer.valueOf(123));
        } catch (EncoderException e) {
            // expected
            return;
        }
        throw new AssertionError("Expected EncoderException");
    }

    @Test
    public void testIsEncodeEqual() throws Exception {
        assertTrue(encoder.isEncodeEqual("test", "test"));
        assertTrue(encoder.isEncodeEqual("Test", "TEST")); // case insensitive
    }

    @Test
    public void testBasicEncoding() {
        // Simple known transformation
        String result = encoder.encode("Caverphone");
        assertEquals(6, result.length());
        // Result should be deterministic
        assertEquals(result, encoder.encode("Caverphone"));
    }

    @Test
    public void testPrefixCough() {
        // ^cough -> cou2f
        String result = encoder.encode("coughing");
        assertEquals(6, result.length());
    }

    @Test
    public void testPrefixRough() {
        String result = encoder.encode("roughly");
        assertEquals(6, result.length());
    }

    @Test
    public void testPrefixTough() {
        String result = encoder.encode("toughness");
        assertEquals(6, result.length());
    }

    @Test
    public void testPrefixEnough() {
        String result = encoder.encode("enough");
        assertEquals(6, result.length());
    }

    @Test
    public void testPrefixGn() {
        String result = encoder.encode("gnome");
        assertEquals(6, result.length());
    }

    @Test
    public void testSuffixMb() {
        String result = encoder.encode("lamb");
        assertEquals(6, result.length());
    }

    @Test
    public void testReplacementCq() {
        String result = encoder.encode("acquire");
        assertEquals(6, result.length());
    }

    @Test
    public void testReplacementCiCeCy() {
        assertEquals(6, encoder.encode("circle").length());
        assertEquals(6, encoder.encode("center").length());
        assertEquals(6, encoder.encode("cycle").length());
    }

    @Test
    public void testReplacementTch() {
        String result = encoder.encode("catch");
        assertEquals(6, result.length());
    }

    @Test
    public void testReplacementCToK() {
        String result = encoder.encode("cat");
        assertEquals(6, result.length());
    }

    @Test
    public void testReplacementQToK() {
        String result = encoder.encode("queen");
        assertEquals(6, result.length());
    }

    @Test
    public void testReplacementXToK() {
        String result = encoder.encode("xerox");
        assertEquals(6, result.length());
    }

    @Test
    public void testReplacementVToF() {
        String result = encoder.encode("very");
        assertEquals(6, result.length());
    }

    @Test
    public void testReplacementDg() {
        String result = encoder.encode("edge");
        assertEquals(6, result.length());
    }

    @Test
    public void testReplacementTioTia() {
        assertEquals(6, encoder.encode("motion").length());
        assertEquals(6, encoder.encode("partial").length());
    }

    @Test
    public void testReplacementDToT() {
        String result = encoder.encode("dog");
        assertEquals(6, result.length());
    }

    @Test
    public void testReplacementPhToFh() {
        String result = encoder.encode("phone");
        assertEquals(6, result.length());
    }

    @Test
    public void testReplacementBToP() {
        String result = encoder.encode("boy");
        assertEquals(6, result.length());
    }

    @Test
    public void testReplacementSh() {
        String result = encoder.encode("shop");
        assertEquals(6, result.length());
    }

    @Test
    public void testReplacementZToS() {
        String result = encoder.encode("zoo");
        assertEquals(6, result.length());
    }

    @Test
    public void testVowelHandlingAtStart() {
        String result = encoder.encode("apple");
        assertEquals(6, result.length());
    }

    @Test
    public void testGhReplacement() {
        String result = encoder.encode("ghost");
        assertEquals(6, result.length());
    }

    @Test
    public void testGToK() {
        String result = encoder.encode("goat");
        assertEquals(6, result.length());
    }

    @Test
    public void testConsonantCollapsing() {
        // s+, t+, p+, k+, f+, m+, n+
        assertEquals(6, encoder.encode("ssss").length());
        assertEquals(6, encoder.encode("tttt").length());
        assertEquals(6, encoder.encode("pppp").length());
        assertEquals(6, encoder.encode("kkkk").length());
        assertEquals(6, encoder.encode("ffff").length());
        assertEquals(6, encoder.encode("mmmm").length());
        assertEquals(6, encoder.encode("nnnn").length());
    }

    @Test
    public void testWReplacements() {
        assertEquals(6, encoder.encode("wow").length());
        assertEquals(6, encoder.encode("why").length());
        assertEquals(6, encoder.encode("whale").length());
        assertEquals(6, encoder.encode("wyoming").length());
    }

    @Test
    public void testHReplacements() {
        assertEquals(6, encoder.encode("hello").length());
        assertEquals(6, encoder.encode("ahead").length());
    }

    @Test
    public void testRReplacements() {
        assertEquals(6, encoder.encode("red").length());
        assertEquals(6, encoder.encode("cry").length());
    }

    @Test
    public void testLReplacements() {
        assertEquals(6, encoder.encode("love").length());
        assertEquals(6, encoder.encode("fly").length());
    }

    @Test
    public void testJToY() {
        String result = encoder.encode("jump");
        assertEquals(6, result.length());
    }

    @Test
    public void testYReplacements() {
        assertEquals(6, encoder.encode("yes").length());
        assertEquals(6, encoder.encode("yay").length());
    }

    @Test
    public void testNonAlphabeticRemoved() {
        String result = encoder.encode("test-123!");
        assertEquals(6, result.length());
        assertEquals(result, encoder.encode("test"));
    }

    @Test
    public void testCaseInsensitive() {
        String lower = encoder.encode("caverphone");
        String upper = encoder.encode("CAVERPHONE");
        String mixed = encoder.encode("CaVeRpHoNe");
        assertEquals(lower, upper);
        assertEquals(lower, mixed);
    }

    @Test
    public void testDeterministicEncoding() {
        String input = "The quick brown fox jumps over the lazy dog";
        String first = encoder.encode(input);
        String second = encoder.encode(input);
        assertEquals(first, second);
    }

    @Test
    public void testKnownValuesFromSpecification() {
        // Based on Caverphone 1.0 specification examples
        // These test the complete algorithm pipeline
        assertEquals("111111", encoder.encode(""));
        assertEquals("111111", encoder.encode(null));
    }

    @Test
    public void testShortInputPaddedWithOnes() {
        // Input that results in less than 6 chars before padding
        String result = encoder.encode("a");
        assertEquals(6, result.length());
        // Should end with 1s due to padding
        assertTrue(result.endsWith("1"));
    }

    @Test
    public void testLongInputTruncatedToSix() {
        String longInput = "supercalifragilisticexpialidocious";
        String result = encoder.encode(longInput);
        assertEquals(6, result.length());
    }

    // Exact encoding tests with correct expected values per Caverphone1 implementation

    @Test
    public void testExactEncodingTest() {
        // "test" -> TST111
        assertEquals("TST111", encoder.encode("test"));
    }

    @Test
    public void testExactEncodingCaverphone() {
        // "caverphone" -> KFFN11
        assertEquals("KFFN11", encoder.encode("caverphone"));
    }

    @Test
    public void testExactEncodingSingleVowel() {
        // "a" -> A11111
        assertEquals("A11111", encoder.encode("a"));
    }

    @Test
    public void testExactEncodingCoughing() {
        // "coughing" -> KFNK11 (verifies ^cough prefix handling)
        assertEquals("KFNK11", encoder.encode("coughing"));
    }

    @Test
    public void testExactEncodingRoughly() {
        // "roughly" -> RFL111 (verifies ^rough prefix handling)
        assertEquals("RFL111", encoder.encode("roughly"));
    }

    @Test
    public void testExactEncodingGnome() {
        // "gnome" -> NM1111 (verifies ^gn prefix handling)
        assertEquals("NM1111", encoder.encode("gnome"));
    }

    @Test
    public void testExactEncodingLamb() {
        // "lamb" -> LM1111 (verifies mb$ suffix handling)
        assertEquals("LM1111", encoder.encode("lamb"));
    }

    @Test
    public void testExactEncodingAcquire() {
        // "acquire" -> AKR111 (verifies cq replacement)
        assertEquals("AKR111", encoder.encode("acquire"));
    }

    @Test
    public void testExactEncodingCircle() {
        // "circle" -> SKL111 (verifies ci->si, c->k, l->2 removal)
        assertEquals("SKL111", encoder.encode("circle"));
    }

    @Test
    public void testExactEncodingPhone() {
        // "phone" -> FN1111 (verifies ph->fh, h->2 removal)
        assertEquals("FN1111", encoder.encode("phone"));
    }

    @Test
    public void testExactEncodingEdge() {
        // "edge" -> AK1111 (verifies dg->2g, g->k, vowel handling)
        assertEquals("AK1111", encoder.encode("edge"));
    }

    @Test
    public void testExactEncodingMotion() {
        // "motion" -> MSN111 (verifies tio->sio)
        assertEquals("MSN111", encoder.encode("motion"));
    }

    @Test
    public void testExactEncodingPartial() {
        // "partial" -> PS1111 (verifies tia->sia)
        assertEquals("PS1111", encoder.encode("partial"));
    }

    @Test
    public void testExactEncodingGhost() {
        // "ghost" -> ST1111 (verifies gh->22, h->2, vowel handling)
        assertEquals("ST1111", encoder.encode("ghost"));
    }

    @Test
    public void testExactEncodingWhy() {
        // "why" -> W11111 (verifies wy->Wy, why->Why, w->2 not applied to W)
        assertEquals("W11111", encoder.encode("why"));
    }

    @Test
    public void testExactEncodingRed() {
        // "red" -> RT1111 (verifies r3->R3, r->2, d->t)
        assertEquals("RT1111", encoder.encode("red"));
    }

    @Test
    public void testExactEncodingLove() {
        // "love" -> LF1111 (verifies l3->L3, l->2, v->f)
        assertEquals("LF1111", encoder.encode("love"));
    }

    @Test
    public void testExactEncodingJump() {
        // "jump" -> YMP111 (verifies j->y)
        assertEquals("YMP111", encoder.encode("jump"));
    }

    @Test
    public void testExactEncodingYes() {
        // "yes" -> YS1111 (verifies y3->Y3, y->2)
        assertEquals("YS1111", encoder.encode("yes"));
    }

    @Test
    public void testExactEncodingHello() {
        // "hello" -> AL1111 (verifies ^h->A, h->2, l->2)
        assertEquals("AL1111", encoder.encode("hello"));
    }
}
