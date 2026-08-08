package org.apache.commons.codec;

import org.apache.commons.codec.language.Soundex;
import org.junit.Test;
import static org.junit.Assert.*;

public class StringEncoderComparatorTest {

    @Test(expected = NullPointerException.class)
    public void testConstructorWithNullStringEncoder() {
        new StringEncoderComparator(null);
    }

    @Test
    public void testDeprecatedConstructor() {
        StringEncoderComparator comparator = new StringEncoderComparator();
    }

    @Test
    public void testCompareWithSoundex() {
        StringEncoder encoder = new Soundex();
        StringEncoderComparator comparator = new StringEncoderComparator(encoder);
        
        assertEquals(0, comparator.compare("Robert", "Robert"));
        assertEquals(0, comparator.compare("Rupert", "Robert"));
    }

    @Test
    public void testCompareWithEncoderException() {
        StringEncoder errorEncoder = new StringEncoder() {
            @Override
            public Object encode(Object source) throws EncoderException {
                throw new EncoderException("Test exception");
            }
            
            @Override
            public String encode(String source) throws EncoderException {
                throw new EncoderException("Test exception");
            }
        };
        
        StringEncoderComparator comparator = new StringEncoderComparator(errorEncoder);
        assertEquals(0, comparator.compare("test", "test"));
    }

    @Test
    public void testCompareWithNullInputs() throws EncoderException {
        StringEncoder encoder = new Soundex();
        StringEncoderComparator comparator = new StringEncoderComparator(encoder);
        
        assertEquals(0, comparator.compare(null, "test"));
        assertEquals(0, comparator.compare("test", null));
        assertEquals(0, comparator.compare(null, null));
    }

    @Test
    public void testCompareWithDifferentEncodedValues() throws EncoderException {
        StringEncoder encoder = new Soundex();
        StringEncoderComparator comparator = new StringEncoderComparator(encoder);
        
        int result = comparator.compare("Robert", "Mary");
        assertTrue(result != 0);
    }

    @Test
    public void testCompareWithReverseOrder() throws EncoderException {
        StringEncoder encoder = new Soundex();
        StringEncoderComparator comparator = new StringEncoderComparator(encoder);
        
        int result1 = comparator.compare("Robert", "Rupert");
        int result2 = comparator.compare("Rupert", "Robert");
        
        assertTrue(result1 == -result2 || result1 == 0);
    }

    @Test
    public void testCompareWithNonStringObjects() {
        StringEncoder encoder = new Soundex();
        StringEncoderComparator comparator = new StringEncoderComparator(encoder);
        
        assertEquals(0, comparator.compare(Integer.valueOf(1), Integer.valueOf(2)));
    }

    @Test
    public void testCompareWithEncoderExceptionOnSecondEncode() throws EncoderException {
        StringEncoder selectiveEncoder = new StringEncoder() {
            private int callCount = 0;
            
            @Override
            public Object encode(Object source) throws EncoderException {
                callCount++;
                if (callCount == 2) {
                    throw new EncoderException("Error on second encode");
                }
                return new Soundex().encode(source);
            }
            
            @Override
            public String encode(String source) throws EncoderException {
                callCount++;
                if (callCount == 2) {
                    throw new EncoderException("Error on second encode");
                }
                return new Soundex().encode(source);
            }
        };
        
        StringEncoderComparator comparator = new StringEncoderComparator(selectiveEncoder);
        assertEquals(0, comparator.compare("test1", "test2"));
    }

    @Test
    public void testCompareWithEmptyStrings() throws EncoderException {
        StringEncoder encoder = new Soundex();
        StringEncoderComparator comparator = new StringEncoderComparator(encoder);
        
        assertEquals(0, comparator.compare("", ""));
    }
}
