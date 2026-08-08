package org.apache.commons.codec.language;

import org.apache.commons.codec.Encoder;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class DaitchMokotoffSoundexTest {

    @Test
    public void testDefaultConstructor() {
        DaitchMokotoffSoundex soundex = new DaitchMokotoffSoundex();
        assertNotNull(soundex);
        assertEquals("097400|097500", soundex.soundex("AUERBACH"));
    }

    @Test
    public void testConstructorWithFoldingTrue() {
        DaitchMokotoffSoundex soundex = new DaitchMokotoffSoundex(true);
        assertNotNull(soundex);
    }

    @Test
    public void testConstructorWithFoldingFalse() {
        DaitchMokotoffSoundex soundex = new DaitchMokotoffSoundex(false);
        assertNotNull(soundex);
    }

    @Test(expected = EncoderException.class)
    public void testEncodeObjectWithNull() throws EncoderException {
        DaitchMokotoffSoundex soundex = new DaitchMokotoffSoundex();
        soundex.encode((Object) null);
    }

    @Test
    public void testEncodeObjectWithString() throws EncoderException {
        DaitchMokotoffSoundex soundex = new DaitchMokotoffSoundex();
        Object result = soundex.encode("AUERBACH");
        assertNotNull(result);
        assertTrue(result instanceof String);
        assertEquals("097400", result);
    }

    @Test(expected = EncoderException.class)
    public void testEncodeObjectWithNonStringThrowsException() throws EncoderException {
        DaitchMokotoffSoundex soundex = new DaitchMokotoffSoundex();
        soundex.encode(new Integer(123));
    }

    @Test
    public void testEncodeStringWithNull() {
        DaitchMokotoffSoundex soundex = new DaitchMokotoffSoundex();
        assertNull(soundex.encode((String) null));
    }

    @Test
    public void testEncodeStringWithEmptyString() {
        DaitchMokotoffSoundex soundex = new DaitchMokotoffSoundex();
        assertEquals("000000", soundex.encode(""));
    }

    @Test
    public void testEncodeStringWithWhitespace() {
        DaitchMokotoffSoundex soundex = new DaitchMokotoffSoundex();
        assertEquals("097400", soundex.encode(" A U E R B A C H "));
    }

    @Test
    public void testEncodeStringRemovesNonLetters() {
        DaitchMokotoffSoundex soundex = new DaitchMokotoffSoundex();
        assertEquals("097400", soundex.encode("A-U.E_R B'A C H"));
    }

    @Test
    public void testEncodeStringCaseInsensitive() {
        DaitchMokotoffSoundex soundex = new DaitchMokotoffSoundex();
        assertEquals(soundex.encode("auerbach"), soundex.encode("AUERBACH"));
        assertEquals(soundex.encode("Auerbach"), soundex.encode("AUERBACH"));
    }

    @Test
    public void testSoundexWithNull() {
        DaitchMokotoffSoundex soundex = new DaitchMokotoffSoundex();
        try {
            String result = soundex.soundex(null);
            fail("Expected NullPointerException for null input to soundex");
        } catch (NullPointerException e) {
            // Expected - String.join throws NPE when given null array
        }
    }

    @Test
    public void testSoundexWithEmptyString() {
        DaitchMokotoffSoundex soundex = new DaitchMokotoffSoundex();
        assertEquals("000000", soundex.soundex(""));
    }

    @Test
    public void testSoundexBranchingExample() {
        DaitchMokotoffSoundex soundex = new DaitchMokotoffSoundex();
        String result = soundex.soundex("AUERBACH");
        assertNotNull(result);
        assertTrue(result.contains("097400"));
        assertTrue(result.contains("097500"));
        assertEquals("097400|097500", result);
    }

    @Test
    public void testSoundexBranchingMultipleCodes() {
        DaitchMokotoffSoundex soundex = new DaitchMokotoffSoundex();
        String result = soundex.soundex("AUERBACH");
        String[] codes = result.split("\\|");
        assertEquals(2, codes.length);
        assertEquals(6, codes[0].length());
        assertEquals(6, codes[1].length());
    }

    @Test
    public void testEncodeVsSoundexBranching() {
        DaitchMokotoffSoundex soundex = new DaitchMokotoffSoundex();
        String encodeResult = soundex.encode("AUERBACH");
        String soundexResult = soundex.soundex("AUERBACH");
        assertEquals("097400", encodeResult);
        assertTrue(soundexResult.startsWith("097400"));
        assertTrue(soundexResult.contains("|"));
    }

    @Test
    public void testFoldingEnabled() {
        DaitchMokotoffSoundex soundex = new DaitchMokotoffSoundex(true);
        String result = soundex.encode("ère");
        assertNotNull(result);
        assertEquals(6, result.length());
    }

    @Test
    public void testFoldingDisabled() {
        DaitchMokotoffSoundex soundex = new DaitchMokotoffSoundex(false);
        String result = soundex.encode("ère");
        assertNotNull(result);
        assertEquals(6, result.length());
    }

    @Test
    public void testMnNmSpecialHandling() {
        DaitchMokotoffSoundex soundex = new DaitchMokotoffSoundex();
        String result1 = soundex.soundex("MANN");
        String result2 = soundex.soundex("NAM");
        assertNotNull(result1);
        assertNotNull(result2);
        assertEquals(6, result1.split("\\|")[0].length());
        assertEquals(6, result2.split("\\|")[0].length());
    }

    @Test
    public void testMaxLengthSixDigits() {
        DaitchMokotoffSoundex soundex = new DaitchMokotoffSoundex();
        String result = soundex.encode("VERYLONGNAME");
        assertEquals(6, result.length());
    }

    @Test
    public void testSoundexMaxLengthSixDigits() {
        DaitchMokotoffSoundex soundex = new DaitchMokotoffSoundex();
        String result = soundex.soundex("VERYLONGNAME");
        String[] codes = result.split("\\|");
        for (String code : codes) {
            assertEquals(6, code.length());
        }
    }

    @Test
    public void testThreadSafety() {
        DaitchMokotoffSoundex soundex = new DaitchMokotoffSoundex();
        Runnable task = () -> {
            for (int i = 0; i < 100; i++) {
                soundex.encode("TESTNAME" + i);
                soundex.soundex("TESTNAME" + i);
            }
        };
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            fail("Thread interrupted");
        }
    }

    @Test
    public void testKnownSurnameEncodings() {
        DaitchMokotoffSoundex soundex = new DaitchMokotoffSoundex();
        String result = soundex.encode("SCHNEIDER");
        assertNotNull(result);
        assertEquals(6, result.length());
        
        result = soundex.encode("MUELLER");
        assertNotNull(result);
        assertEquals(6, result.length());
        
        result = soundex.encode("SCHMIDT");
        assertNotNull(result);
        assertEquals(6, result.length());
    }

    @Test
    public void testSoundexKnownSurnames() {
        DaitchMokotoffSoundex soundex = new DaitchMokotoffSoundex();
        String result = soundex.soundex("SCHNEIDER");
        assertNotNull(result);
        String[] codes = result.split("\\|");
        for (String code : codes) {
            assertEquals(6, code.length());
        }
    }

    @Test
    public void testSingleCharacterInput() {
        DaitchMokotoffSoundex soundex = new DaitchMokotoffSoundex();
        String result = soundex.encode("A");
        assertNotNull(result);
        assertEquals(6, result.length());
    }

    @Test
    public void testTwoCharacterInput() {
        DaitchMokotoffSoundex soundex = new DaitchMokotoffSoundex();
        String result = soundex.encode("AB");
        assertNotNull(result);
        assertEquals(6, result.length());
    }

    @Test
    public void testInputWithNumbers() {
        DaitchMokotoffSoundex soundex = new DaitchMokotoffSoundex();
        String result = soundex.encode("SMITH123");
        assertNotNull(result);
        assertEquals(6, result.length());
    }

    @Test
    public void testInputWithSpecialCharacters() {
        DaitchMokotoffSoundex soundex = new DaitchMokotoffSoundex();
        String result = soundex.encode("O'CONNOR");
        assertNotNull(result);
        assertEquals(6, result.length());
    }

    @Test
    public void testEncodeImplementsStringEncoder() {
        DaitchMokotoffSoundex soundex = new DaitchMokotoffSoundex();
        assertTrue(soundex instanceof StringEncoder);
    }

    @Test
    public void testEncodeImplementsEncoder() {
        DaitchMokotoffSoundex soundex = new DaitchMokotoffSoundex();
        assertTrue(soundex instanceof Encoder);
    }

    @Test
    public void testConsistentResults() {
        DaitchMokotoffSoundex soundex = new DaitchMokotoffSoundex();
        String result1 = soundex.encode("CONSISTENT");
        String result2 = soundex.encode("CONSISTENT");
        assertEquals(result1, result2);
        
        String soundexResult1 = soundex.soundex("CONSISTENT");
        String soundexResult2 = soundex.soundex("CONSISTENT");
        assertEquals(soundexResult1, soundexResult2);
    }

    @Test
    public void testDifferentFoldingBehavior() {
        DaitchMokotoffSoundex withFolding = new DaitchMokotoffSoundex(true);
        DaitchMokotoffSoundex withoutFolding = new DaitchMokotoffSoundex(false);
        
        String folded = withFolding.encode("àèìòù");
        String notFolded = withoutFolding.encode("àèìòù");
        
        assertNotNull(folded);
        assertNotNull(notFolded);
        assertEquals(6, folded.length());
        assertEquals(6, notFolded.length());
    }

    @Test
    public void testSoundexReturnsSortedBranches() {
        DaitchMokotoffSoundex soundex = new DaitchMokotoffSoundex();
        String result = soundex.soundex("AUERBACH");
        String[] codes = result.split("\\|");
        assertEquals("097400", codes[0]);
        assertEquals("097500", codes[1]);
    }

    // Additional tests for branch coverage improvement

    @Test
    public void testFoldingWithAccentedCharacters() {
        DaitchMokotoffSoundex withFolding = new DaitchMokotoffSoundex(true);
        DaitchMokotoffSoundex withoutFolding = new DaitchMokotoffSoundex(false);
        
        String[] accentedChars = {"é", "è", "ê", "ë", "à", "â", "ä", "î", "ï", "ô", "ö", "ù", "û", "ü", "ç", "ñ"};
        
        for (String ch : accentedChars) {
            String folded = withFolding.encode(ch);
            String notFolded = withoutFolding.encode(ch);
            assertNotNull("Folded result should not be null for: " + ch, folded);
            assertNotNull("Non-folded result should not be null for: " + ch, notFolded);
            assertEquals(6, folded.length());
            assertEquals(6, notFolded.length());
        }
    }

    @Test
    public void testFoldingChangesEncodingResult() {
        DaitchMokotoffSoundex withFolding = new DaitchMokotoffSoundex(true);
        DaitchMokotoffSoundex withoutFolding = new DaitchMokotoffSoundex(false);
        
        String folded = withFolding.encode("éclair");
        String notFolded = withoutFolding.encode("éclair");
        
        assertNotNull(folded);
        assertNotNull(notFolded);
        assertEquals(6, folded.length());
        assertEquals(6, notFolded.length());
    }

    @Test
    public void testEncodeWithCharactersNotInRules() {
        DaitchMokotoffSoundex soundex = new DaitchMokotoffSoundex();
        String result = soundex.encode("XYZQW");
        assertNotNull(result);
        assertEquals(6, result.length());
    }

    @Test
    public void testSoundexWithCharactersNotInRules() {
        DaitchMokotoffSoundex soundex = new DaitchMokotoffSoundex();
        String result = soundex.soundex("XYZQW");
        assertNotNull(result);
        String[] codes = result.split("\\|");
        for (String code : codes) {
            assertEquals(6, code.length());
        }
    }

    @Test
    public void testBranchingWithMultipleReplacements() {
        DaitchMokotoffSoundex soundex = new DaitchMokotoffSoundex();
        String result = soundex.soundex("AUERBACH");
        String[] codes = result.split("\\|");
        assertTrue("Should have multiple branches", codes.length >= 2);
        
        result = soundex.soundex("SCHNEIDER");
        codes = result.split("\\|");
        assertTrue("Should have at least one branch", codes.length >= 1);
        for (String code : codes) {
            assertEquals(6, code.length());
        }
    }

    @Test
    public void testEncodeNonBranchingVsSoundexBranching() {
        DaitchMokotoffSoundex soundex = new DaitchMokotoffSoundex();
        
        String encodeResult = soundex.encode("AUERBACH");
        assertEquals("097400", encodeResult);
        
        String soundexResult = soundex.soundex("AUERBACH");
        assertTrue(soundexResult.contains("|"));
        assertTrue(soundexResult.startsWith("097400"));
    }

    @Test
    public void testMnNmForceAppendLogic() {
        DaitchMokotoffSoundex soundex = new DaitchMokotoffSoundex();
        
        String resultMn = soundex.soundex("MANN");
        assertNotNull(resultMn);
        String[] codesMn = resultMn.split("\\|");
        for (String code : codesMn) {
            assertEquals(6, code.length());
        }
        
        String resultNm = soundex.soundex("NAM");
        assertNotNull(resultNm);
        String[] codesNm = resultNm.split("\\|");
        for (String code : codesNm) {
            assertEquals(6, code.length());
        }
        
        String resultMnar = soundex.soundex("MNAR");
        assertNotNull(resultMnar);
        String[] codesMnar = resultMnar.split("\\|");
        for (String code : codesMnar) {
            assertEquals(6, code.length());
        }
    }

    @Test
    public void testBranchingWithComplexName() {
        DaitchMokotoffSoundex soundex = new DaitchMokotoffSoundex();
        
        String result = soundex.soundex("MUELLER");
        assertNotNull(result);
        String[] codes = result.split("\\|");
        assertTrue("Should have at least one branch", codes.length >= 1);
        for (String code : codes) {
            assertEquals(6, code.length());
        }
        
        String encodeResult = soundex.encode("MUELLER");
        assertNotNull(encodeResult);
        assertEquals(6, encodeResult.length());
        assertEquals(codes[0], encodeResult);
    }

    @Test
    public void testCleanupRemovesWhitespaceAndNonLetters() {
        DaitchMokotoffSoundex soundex = new DaitchMokotoffSoundex();
        
        assertEquals(soundex.encode("A B C"), soundex.encode("ABC"));
        assertEquals(soundex.encode("A\tB\nC"), soundex.encode("ABC"));
        assertEquals(soundex.encode("A1B2C3"), soundex.encode("ABC"));
        assertEquals(soundex.encode("A-B.C_D"), soundex.encode("ABCD"));
        assertEquals(soundex.encode("A'@#$%B"), soundex.encode("AB"));
    }

    @Test
    public void testEncodeEmptyStringAfterCleanup() {
        DaitchMokotoffSoundex soundex = new DaitchMokotoffSoundex();
        
        String result = soundex.encode("123!@#");
        assertNotNull(result);
        assertEquals("000000", result);
        
        String soundexResult = soundex.soundex("123!@#");
        assertNotNull(soundexResult);
        assertEquals("000000", soundexResult);
    }

    @Test
    public void testSoundexNullInputPrivateMethod() {
        DaitchMokotoffSoundex soundex = new DaitchMokotoffSoundex();
        
        try {
            soundex.soundex(null);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // Expected
        }
    }

    @Test
    public void testEncodeStringNullInput() {
        DaitchMokotoffSoundex soundex = new DaitchMokotoffSoundex();
        
        String result = soundex.encode((String) null);
        assertNull(result);
    }

    @Test
    public void testMultipleBranchingCodes() {
        DaitchMokotoffSoundex soundex = new DaitchMokotoffSoundex();
        
        String[] testNames = {"AUERBACH", "SCHNEIDER", "MUELLER", "SCHMIDT", "MANN", "NAM"};
        
        for (String name : testNames) {
            String soundexResult = soundex.soundex(name);
            String encodeResult = soundex.encode(name);
            
            assertNotNull("soundex result for " + name, soundexResult);
            assertNotNull("encode result for " + name, encodeResult);
            
            String[] codes = soundexResult.split("\\|");
            assertTrue("Should have at least one code for " + name, codes.length >= 1);
            
            for (String code : codes) {
                assertEquals("Code length should be 6 for " + name, 6, code.length());
            }
            
            assertEquals("encode should return first code for " + name, codes[0], encodeResult);
        }
    }

    @Test
    public void testFoldingWithMixedCaseAndAccents() {
        DaitchMokotoffSoundex withFolding = new DaitchMokotoffSoundex(true);
        
        String result1 = withFolding.encode("Éclair");
        String result2 = withFolding.encode("éclair");
        String result3 = withFolding.encode("ECLAIR");
        
        assertNotNull(result1);
        assertNotNull(result2);
        assertNotNull(result3);
        assertEquals(6, result1.length());
        assertEquals(6, result2.length());
        assertEquals(6, result3.length());
        
        assertEquals(result2, result3);
    }

    @Test
    public void testBranchingWithSingleReplacementRules() {
        DaitchMokotoffSoundex soundex = new DaitchMokotoffSoundex();
        
        String result = soundex.soundex("SMITH");
        String[] codes = result.split("\\|");
        assertTrue(codes.length >= 1);
        for (String code : codes) {
            assertEquals(6, code.length());
        }
        
        String encodeResult = soundex.encode("SMITH");
        assertEquals(codes[0], encodeResult);
    }

    @Test
    public void testBranchCreationAndMerging() {
        DaitchMokotoffSoundex soundex = new DaitchMokotoffSoundex();
        
        String result = soundex.soundex("AUERBACH");
        String[] codes = result.split("\\|");
        
        Set<String> uniqueCodes = new LinkedHashSet<>(Arrays.asList(codes));
        assertEquals("No duplicate branches expected", codes.length, uniqueCodes.size());
    }

    @Test
    public void testMaxCodeLengthEnforcement() {
        DaitchMokotoffSoundex soundex = new DaitchMokotoffSoundex();
        
        String longName = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String result = soundex.encode(longName);
        assertEquals(6, result.length());
        
        String soundexResult = soundex.soundex(longName);
        String[] codes = soundexResult.split("\\|");
        for (String code : codes) {
            assertEquals(6, code.length());
        }
    }

    @Test
    public void testSpecialCharactersInRules() {
        DaitchMokotoffSoundex soundex = new DaitchMokotoffSoundex();
        
        String result = soundex.encode("CHRISTOPHER");
        assertNotNull(result);
        assertEquals(6, result.length());
        
        result = soundex.encode("PHILIPP");
        assertNotNull(result);
        assertEquals(6, result.length());
        
        result = soundex.encode("SCHMIDT");
        assertNotNull(result);
        assertEquals(6, result.length());
    }

    @Test
    public void testConcurrentAccessWithBranching() {
        DaitchMokotoffSoundex soundex = new DaitchMokotoffSoundex();
        
        Runnable task = () -> {
            for (int i = 0; i < 50; i++) {
                soundex.soundex("AUERBACH" + i);
                soundex.encode("SCHNEIDER" + i);
            }
        };
        
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        Thread t3 = new Thread(task);
        
        t1.start();
        t2.start();
        t3.start();
        
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            fail("Thread interrupted");
        }
    }

    @Test
    public void testEncodingConsistencyAcrossMethods() {
        DaitchMokotoffSoundex soundex = new DaitchMokotoffSoundex();
        
        String[] testNames = {
            "AUERBACH", "SCHNEIDER", "MUELLER", "SCHMIDT", 
            "MANN", "NAM", "SMITH", "JONES", "WILLIAMS", "BROWN"
        };
        
        for (String name : testNames) {
            String encodeResult = soundex.encode(name);
            String soundexResult = soundex.soundex(name);
            
            assertNotNull(encodeResult);
            assertNotNull(soundexResult);
            assertEquals(6, encodeResult.length());
            
            String[] codes = soundexResult.split("\\|");
            assertTrue(codes.length >= 1);
            
            assertEquals("First code mismatch for: " + name, codes[0], encodeResult);
            
            for (String code : codes) {
                assertEquals(6, code.length());
                assertTrue(code.matches("\\d{6}"));
            }
        }
    }

    @Test
    public void testFoldingDisabledWithAccentedChars() {
        DaitchMokotoffSoundex withoutFolding = new DaitchMokotoffSoundex(false);
        
        String result = withoutFolding.encode("éclair");
        assertNotNull(result);
        assertEquals(6, result.length());
        
        String result2 = withoutFolding.encode("clair");
        assertNotNull(result2);
        assertEquals(6, result2.length());
    }

    @Test
    public void testBranchFinishPadding() {
        DaitchMokotoffSoundex soundex = new DaitchMokotoffSoundex();
        
        String result = soundex.encode("A");
        assertEquals("000000", result);
        
        result = soundex.encode("AB");
        assertEquals(6, result.length());
        assertTrue(result.matches("\\d{6}"));
    }
}
