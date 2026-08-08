/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.commons.codec.language;

import static org.junit.Assert.assertEquals;

import org.apache.commons.codec.EncoderException;
import org.junit.Test;

/**
 * Unit tests for {@link Caverphone1}.
 */
public class Caverphone1Test {

    private final Caverphone1 caverphone = new Caverphone1();

    @Test
    public void testEncodeNull() {
        assertEquals("111111", caverphone.encode(null));
    }

    @Test
    public void testEncodeEmptyString() {
        assertEquals("111111", caverphone.encode(""));
    }

    @Test
    public void testEncodeSimpleWord() {
        assertEquals("TST111", caverphone.encode("test"));
    }

    @Test
    public void testEncodeCaseInsensitivity() {
        assertEquals(caverphone.encode("TEST"), caverphone.encode("test"));
        assertEquals(caverphone.encode("Hello"), caverphone.encode("HELLO"));
    }

    @Test
    public void testEncodeStartWithCough() {
        assertEquals("KF1111", caverphone.encode("cough"));
    }

    @Test
    public void testEncodeStartWithRough() {
        assertEquals("RF1111", caverphone.encode("rough"));
    }

    @Test
    public void testEncodeStartWithTough() {
        assertEquals("TF1111", caverphone.encode("tough"));
    }

    @Test
    public void testEncodeStartWithEnough() {
        assertEquals("ANF111", caverphone.encode("enough"));
    }

    @Test
    public void testEncodeStartWithGn() {
        assertEquals("NM1111", caverphone.encode("gnome"));
    }

    @Test
    public void testEncodeEndWithMb() {
        assertEquals("KM1111", caverphone.encode("comb"));
    }

    @Test
    public void testEncodeCqReplacement() {
        assertEquals("AK1111", caverphone.encode("acq"));
    }

    @Test
    public void testEncodeCiCeCyReplacement() {
        assertEquals("S11111", caverphone.encode("cia"));
        assertEquals("S11111", caverphone.encode("ce"));
        assertEquals("S11111", caverphone.encode("cy"));
    }

    @Test
    public void testEncodeTchReplacement() {
        assertEquals("AK1111", caverphone.encode("etch"));
    }

    @Test
    public void testEncodePhReplacement() {
        assertEquals("FN1111", caverphone.encode("phone"));
    }

    @Test
    public void testEncodeShReplacement() {
        assertEquals("S11111", caverphone.encode("shel"));
    }

    @Test
    public void testEncodeZReplacement() {
        assertEquals("SPR111", caverphone.encode("zebra"));
    }

    @Test
    public void testEncodeNonAlphabetCharacters() {
        assertEquals("TST111", caverphone.encode("test123"));
        assertEquals("TST111", caverphone.encode("test!@#"));
        assertEquals("TST111", caverphone.encode("t e s t"));
    }

    @Test
    public void testEncodeWithNumbersAndSpecialChars() {
        assertEquals("111111", caverphone.encode("123"));
        assertEquals("111111", caverphone.encode("!@#$"));
    }

    @Test
    public void testEncodeVowels() {
        assertEquals("A11111", caverphone.encode("aeiou"));
    }

    @Test
    public void testEncodeMultipleConsonants() {
        assertEquals("K11111", caverphone.encode("ck"));
    }

    @Test
    public void testEncodePeter() {
        assertEquals("PT1111", caverphone.encode("Peter"));
    }

    @Test
    public void testEncodeParker() {
        assertEquals("PK1111", caverphone.encode("Parker"));
    }

    @Test
    public void testEncodeEmptyAfterProcessing() {
        assertEquals("P11111", caverphone.encode("b"));
    }

    @Test
    public void testIsEncodeEqual() throws EncoderException {
        assertEquals(true, caverphone.isEncodeEqual("test", "TEST"));
        assertEquals(true, caverphone.isEncodeEqual("Peter", "peter"));
        assertEquals(false, caverphone.isEncodeEqual("test", "text"));
    }

    @Test
    public void testIsEncodeEqualWithNull() throws EncoderException {
        assertEquals(true, caverphone.isEncodeEqual(null, null));
        assertEquals(true, caverphone.isEncodeEqual("", ""));
        assertEquals(false, caverphone.isEncodeEqual("test", null));
    }

    @Test
    public void testEncodeLongWord() {
        String result = caverphone.encode("revolutionary");
        assertEquals(6, result.length());
    }

    @Test
    public void testEncodeSingleCharacter() {
        assertEquals("A11111", caverphone.encode("a"));
        assertEquals("P11111", caverphone.encode("b"));
    }

    @Test
    public void testEncodeKnownCaverphoneValues() {
        assertEquals("K11111", caverphone.encode("ka"));
        assertEquals("K11111", caverphone.encode("kaa"));
    }
}
