package org.apache.commons.codec.language;

import org.junit.Test;
import static org.junit.Assert.*;

public class Caverphone2Test {

    private final Caverphone2 caverphone2 = new Caverphone2();

    @Test
    public void testEmptyString() {
        assertEquals("1111111111", caverphone2.encode(""));
    }

    @Test
    public void testNullString() {
        assertEquals("1111111111", caverphone2.encode(null));
    }

    @Test
    public void testBasicName() {
        assertEquals("YN11111111", caverphone2.encode("John"));
    }

    @Test
    public void testNameWithSpecialCharacters() {
        assertEquals("YN11111111", caverphone2.encode("John!@#$%^&*()"));
    }

    @Test
    public void testNameWithSpaces() {
        assertEquals("YNTA111111", caverphone2.encode("John Doe"));
    }

    @Test
    public void testNameWithNumbers() {
        assertEquals("YN11111111", caverphone2.encode("John123"));
    }

    @Test
    public void testNameWithFinalE() {
        assertEquals("YN11111111", caverphone2.encode("JohnE"));
    }

    @Test
    public void testNameStartingWithCough() {
        assertEquals("KF11111111", caverphone2.encode("cough"));
    }

    @Test
    public void testNameStartingWithRough() {
        assertEquals("RF11111111", caverphone2.encode("rough"));
    }

    @Test
    public void testNameStartingWithTough() {
        assertEquals("TF11111111", caverphone2.encode("tough"));
    }

    @Test
    public void testNameStartingWithEnough() {
        assertEquals("ANF1111111", caverphone2.encode("enough"));
    }

    @Test
    public void testNameStartingWithTrough() {
        assertEquals("TRF1111111", caverphone2.encode("trough"));
    }

    @Test
    public void testNameStartingWithGn() {
        assertEquals("NT11111111", caverphone2.encode("gnat"));
    }

    @Test
    public void testNameEndingWithMb() {
        assertEquals("AM11111111", caverphone2.encode("amb"));
    }

    @Test
    public void testNameWithCq() {
        assertEquals("K111111111", caverphone2.encode("cq"));
    }

    @Test
    public void testNameWithCi() {
        assertEquals("SA11111111", caverphone2.encode("ci"));
    }

    @Test
    public void testNameWithCe() {
        assertEquals("K111111111", caverphone2.encode("ce"));
    }

    @Test
    public void testNameWithCy() {
        assertEquals("SA11111111", caverphone2.encode("cy"));
    }

    @Test
    public void testNameWithTch() {
        assertEquals("K111111111", caverphone2.encode("tch"));
    }

    @Test
    public void testNameWithC() {
        assertEquals("K111111111", caverphone2.encode("c"));
    }

    @Test
    public void testNameWithQ() {
        assertEquals("K111111111", caverphone2.encode("q"));
    }

    @Test
    public void testNameWithX() {
        assertEquals("K111111111", caverphone2.encode("x"));
    }

    @Test
    public void testNameWithV() {
        assertEquals("F111111111", caverphone2.encode("v"));
    }

    @Test
    public void testNameWithDg() {
        assertEquals("K111111111", caverphone2.encode("dg"));
    }

    @Test
    public void testNameWithTio() {
        assertEquals("SA11111111", caverphone2.encode("tio"));
    }

    @Test
    public void testNameWithTia() {
        assertEquals("SA11111111", caverphone2.encode("tia"));
    }

    @Test
    public void testNameWithD() {
        assertEquals("T111111111", caverphone2.encode("d"));
    }

    @Test
    public void testNameWithPh() {
        assertEquals("F111111111", caverphone2.encode("ph"));
    }

    @Test
    public void testNameWithB() {
        assertEquals("P111111111", caverphone2.encode("b"));
    }

    @Test
    public void testNameWithSh() {
        assertEquals("S111111111", caverphone2.encode("sh"));
    }

    @Test
    public void testNameWithZ() {
        assertEquals("S111111111", caverphone2.encode("z"));
    }

    @Test
    public void testNameStartingWithVowel() {
        assertEquals("APA1111111", caverphone2.encode("apple"));
    }

    @Test
    public void testNameWithJ() {
        assertEquals("YN11111111", caverphone2.encode("john"));
    }

    @Test
    public void testNameStartingWithY3() {
        assertEquals("A111111111", caverphone2.encode("y3"));
    }

    @Test
    public void testNameStartingWithY() {
        assertEquals("A111111111", caverphone2.encode("y"));
    }

    @Test
    public void testNameWithY() {
        assertEquals("A111111111", caverphone2.encode("y"));
    }

    @Test
    public void testNameWith3gh3() {
        assertEquals("1111111111", caverphone2.encode("3gh3"));
    }

    @Test
    public void testNameWithGh() {
        assertEquals("1111111111", caverphone2.encode("gh"));
    }

    @Test
    public void testNameWithG() {
        assertEquals("K111111111", caverphone2.encode("g"));
    }

    @Test
    public void testNameWithMultipleS() {
        assertEquals("S111111111", caverphone2.encode("ssss"));
    }

    @Test
    public void testNameWithMultipleT() {
        assertEquals("T111111111", caverphone2.encode("tttt"));
    }

    @Test
    public void testNameWithMultipleP() {
        assertEquals("P111111111", caverphone2.encode("pppp"));
    }

    @Test
    public void testNameWithMultipleK() {
        assertEquals("K111111111", caverphone2.encode("kkkk"));
    }

    @Test
    public void testNameWithMultipleF() {
        assertEquals("F111111111", caverphone2.encode("ffff"));
    }

    @Test
    public void testNameWithMultipleM() {
        assertEquals("M111111111", caverphone2.encode("mmmm"));
    }

    @Test
    public void testNameWithMultipleN() {
        assertEquals("N111111111", caverphone2.encode("nnnn"));
    }

    @Test
    public void testNameWithW3() {
        assertEquals("A111111111", caverphone2.encode("w3"));
    }

    @Test
    public void testNameWithWh3() {
        assertEquals("1111111111", caverphone2.encode("wh3"));
    }

    @Test
    public void testNameEndingWithW() {
        assertEquals("A111111111", caverphone2.encode("w"));
    }

    @Test
    public void testNameWithW() {
        assertEquals("A111111111", caverphone2.encode("w"));
    }

    @Test
    public void testNameStartingWithH() {
        assertEquals("A111111111", caverphone2.encode("h"));
    }

    @Test
    public void testNameWithH() {
        assertEquals("A111111111", caverphone2.encode("h"));
    }

    @Test
    public void testNameWithR3() {
        assertEquals("A111111111", caverphone2.encode("r3"));
    }

    @Test
    public void testNameEndingWithR() {
        assertEquals("A111111111", caverphone2.encode("r"));
    }

    @Test
    public void testNameWithR() {
        assertEquals("A111111111", caverphone2.encode("r"));
    }

    @Test
    public void testNameWithL3() {
        assertEquals("A111111111", caverphone2.encode("l3"));
    }

    @Test
    public void testNameEndingWithL() {
        assertEquals("A111111111", caverphone2.encode("l"));
    }

    @Test
    public void testNameWithL() {
        assertEquals("A111111111", caverphone2.encode("l"));
    }
}
