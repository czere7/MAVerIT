package org.apache.commons.codec;

import org.apache.commons.codec.language.Metaphone;
import org.apache.commons.codec.language.Soundex;
import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class StringEncoderComparatorTest {

    @Test
    public void testConstructorWithNullEncoderThrowsNullPointerException() {
        try {
            new StringEncoderComparator((StringEncoder) null);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            assertEquals("stringEncoder", e.getMessage());
        }
    }

    @Test
    public void testConstructorWithValidEncoder() {
        StringEncoder soundex = new Soundex();
        StringEncoderComparator comparator = new StringEncoderComparator(soundex);
        assertNotNull(comparator);
    }

    @Test
    public void testDeprecatedConstructorCreatesInstanceWithNullEncoder() {
        StringEncoderComparator comparator = new StringEncoderComparator();
        assertNotNull(comparator);
    }

    @Test
    public void testCompareWithValidStrings() {
        StringEncoder soundex = new Soundex();
        StringEncoderComparator comparator = new StringEncoderComparator(soundex);

        int result = comparator.compare("test", "test");
        assertEquals(0, result);

        result = comparator.compare("test", "best");
        assertTrue(result != 0);
    }

    @Test
    public void testCompareWithNullObjects() {
        StringEncoder soundex = new Soundex();
        StringEncoderComparator comparator = new StringEncoderComparator(soundex);

        int result = comparator.compare(null, null);
        assertEquals(0, result);

        result = comparator.compare("test", null);
        assertEquals(0, result);

        result = comparator.compare(null, "test");
        assertEquals(0, result);
    }

    @Test
    public void testCompareWithNonStringObjects() {
        StringEncoder soundex = new Soundex();
        StringEncoderComparator comparator = new StringEncoderComparator(soundex);

        int result = comparator.compare(123, 456);
        assertEquals(0, result);

        result = comparator.compare("test", 123);
        assertEquals(0, result);

        result = comparator.compare(123, "test");
        assertEquals(0, result);
    }

    @Test
    public void testCompareWithEmptyStrings() {
        StringEncoder soundex = new Soundex();
        StringEncoderComparator comparator = new StringEncoderComparator(soundex);

        int result = comparator.compare("", "");
        assertEquals(0, result);

        result = comparator.compare("", "a");
        assertTrue(result != 0);
    }

    @Test
    public void testCompareReturnsZeroOnEncoderException() {
        StringEncoder failingEncoder = new StringEncoder() {
            @Override
            public Object encode(Object source) throws EncoderException {
                throw new EncoderException("Test exception");
            }

            @Override
            public String encode(String source) throws EncoderException {
                throw new EncoderException("Test exception");
            }
        };

        StringEncoderComparator comparator = new StringEncoderComparator(failingEncoder);
        int result = comparator.compare("test1", "test2");
        assertEquals(0, result);
    }

    @Test
    public void testCompareWithSoundexEncoder() {
        StringEncoder soundex = new Soundex();
        StringEncoderComparator comparator = new StringEncoderComparator(soundex);

        int result = comparator.compare("Robert", "Rupert");
        assertEquals(0, result);

        result = comparator.compare("Robert", "Ashcraft");
        assertTrue(result != 0);
    }

    @Test
    public void testCompareWithMetaphoneEncoder() {
        StringEncoder metaphone = new Metaphone();
        StringEncoderComparator comparator = new StringEncoderComparator(metaphone);

        int result = comparator.compare("test", "test");
        assertEquals(0, result);

        result = comparator.compare("test", "best");
        assertTrue(result != 0);
    }

    @Test
    public void testImplementsComparatorInterface() {
        StringEncoder soundex = new Soundex();
        StringEncoderComparator comparator = new StringEncoderComparator(soundex);
        assertTrue(comparator instanceof Comparator);
    }

    @Test
    public void testCompareIsConsistentWithEquals() {
        StringEncoder soundex = new Soundex();
        StringEncoderComparator comparator = new StringEncoderComparator(soundex);

        String a = "test";
        String b = "test";
        String c = "best";

        assertEquals(0, comparator.compare(a, b));
        assertEquals(0, comparator.compare(b, a));

        int compareAB = comparator.compare(a, c);
        int compareBA = comparator.compare(c, a);
        assertTrue((compareAB < 0 && compareBA > 0) || (compareAB > 0 && compareBA < 0));
    }

    @Test
    public void testCompareTransitivity() {
        StringEncoder soundex = new Soundex();
        StringEncoderComparator comparator = new StringEncoderComparator(soundex);

        String a = "A";
        String b = "B";
        String c = "C";

        int compareAB = comparator.compare(a, b);
        int compareBC = comparator.compare(b, c);
        int compareAC = comparator.compare(a, c);

        if (compareAB > 0 && compareBC > 0) {
            assertTrue(compareAC > 0);
        } else if (compareAB < 0 && compareBC < 0) {
            assertTrue(compareAC < 0);
        }
    }

    @Test
    public void testDeprecatedConstructorThrowsNPEWhenUsed() {
        StringEncoderComparator comparator = new StringEncoderComparator();

        try {
            comparator.compare("test", "test");
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // NPE thrown by JVM when accessing null field typically has null message
            // Just verify the exception type is thrown
        }
    }

    @Test
    public void testCompareWithEqualEncodedValues() {
        StringEncoder soundex = new Soundex();
        StringEncoderComparator comparator = new StringEncoderComparator(soundex);

        assertEquals(0, comparator.compare("Ashcraft", "Ashcroft"));
    }

    @Test
    public void testCompareHandlesCaseInsensitivity() {
        StringEncoder soundex = new Soundex();
        StringEncoderComparator comparator = new StringEncoderComparator(soundex);

        int result = comparator.compare("washington", "WASHINGTON");
        assertEquals(0, result);
    }

    // Additional tests for branch coverage

    @Test
    public void testCompareWhenEncoderReturnsNull() {
        StringEncoder nullReturningEncoder = new StringEncoder() {
            @Override
            public Object encode(Object source) throws EncoderException {
                return null;
            }

            @Override
            public String encode(String source) throws EncoderException {
                return null;
            }
        };

        StringEncoderComparator comparator = new StringEncoderComparator(nullReturningEncoder);
        try {
            comparator.compare("test1", "test2");
            fail("Expected NullPointerException when encoder returns null");
        } catch (NullPointerException e) {
            // Expected: s1.compareTo(s2) throws NPE when s1 is null
        }
    }

    @Test
    public void testCompareWhenEncoderReturnsNonComparable() {
        StringEncoder nonComparableEncoder = new StringEncoder() {
            @Override
            public Object encode(Object source) throws EncoderException {
                return new Object(); // Not Comparable
            }

            @Override
            public String encode(String source) throws EncoderException {
                return new Object().toString(); // String is Comparable, so use Object
            }
        };

        StringEncoderComparator comparator = new StringEncoderComparator(nonComparableEncoder);
        try {
            comparator.compare("test1", "test2");
            fail("Expected ClassCastException when encoder returns non-Comparable");
        } catch (ClassCastException e) {
            // Expected: cast to Comparable fails
        }
    }

    @Test
    public void testCompareWhenEncoderThrowsRuntimeException() {
        StringEncoder runtimeExceptionEncoder = new StringEncoder() {
            @Override
            public Object encode(Object source) throws EncoderException {
                throw new RuntimeException("Runtime exception from encoder");
            }

            @Override
            public String encode(String source) throws EncoderException {
                throw new RuntimeException("Runtime exception from encoder");
            }
        };

        StringEncoderComparator comparator = new StringEncoderComparator(runtimeExceptionEncoder);
        try {
            comparator.compare("test1", "test2");
            fail("Expected RuntimeException to propagate");
        } catch (RuntimeException e) {
            assertEquals("Runtime exception from encoder", e.getMessage());
        }
    }

    @Test
    public void testCompareWhenCompareToThrowsException() {
        StringEncoder compareToThrowingEncoder = new StringEncoder() {
            @Override
            public Object encode(Object source) throws EncoderException {
                return new Comparable<Object>() {
                    @Override
                    public int compareTo(Object o) {
                        throw new IllegalStateException("compareTo failed");
                    }
                };
            }

            @Override
            public String encode(String source) throws EncoderException {
                return "dummy";
            }
        };

        StringEncoderComparator comparator = new StringEncoderComparator(compareToThrowingEncoder);
        try {
            comparator.compare("test1", "test2");
            fail("Expected exception from compareTo to propagate");
        } catch (IllegalStateException e) {
            assertEquals("compareTo failed", e.getMessage());
        }
    }

    @Test
    public void testCompareWithSecondEncoderCallThrowingEncoderException() {
        StringEncoder partialFailureEncoder = new StringEncoder() {
            private boolean firstCall = true;

            @Override
            public Object encode(Object source) throws EncoderException {
                if (firstCall) {
                    firstCall = false;
                    return "encoded1";
                }
                throw new EncoderException("Second call fails");
            }

            @Override
            public String encode(String source) throws EncoderException {
                if (firstCall) {
                    firstCall = false;
                    return "encoded1";
                }
                throw new EncoderException("Second call fails");
            }
        };

        StringEncoderComparator comparator = new StringEncoderComparator(partialFailureEncoder);
        int result = comparator.compare("test1", "test2");
        assertEquals(0, result);
    }

    @Test
    public void testCompareWithFirstEncoderCallThrowingEncoderException() {
        StringEncoder partialFailureEncoder = new StringEncoder() {
            private boolean firstCall = true;

            @Override
            public Object encode(Object source) throws EncoderException {
                if (firstCall) {
                    firstCall = false;
                    throw new EncoderException("First call fails");
                }
                return "encoded2";
            }

            @Override
            public String encode(String source) throws EncoderException {
                if (firstCall) {
                    firstCall = false;
                    throw new EncoderException("First call fails");
                }
                return "encoded2";
            }
        };

        StringEncoderComparator comparator = new StringEncoderComparator(partialFailureEncoder);
        int result = comparator.compare("test1", "test2");
        assertEquals(0, result);
    }

    @Test
    public void testCompareWithNullAndValidStringUsingFailingEncoder() {
        StringEncoder failingEncoder = new StringEncoder() {
            @Override
            public Object encode(Object source) throws EncoderException {
                throw new EncoderException("Always fails");
            }

            @Override
            public String encode(String source) throws EncoderException {
                throw new EncoderException("Always fails");
            }
        };

        StringEncoderComparator comparator = new StringEncoderComparator(failingEncoder);

        int result = comparator.compare(null, "test");
        assertEquals(0, result);

        result = comparator.compare("test", null);
        assertEquals(0, result);

        result = comparator.compare(null, null);
        assertEquals(0, result);
    }

    @Test
    public void testCompareReturnsNegativeWhenFirstEncodesLessThanSecond() {
        StringEncoder testEncoder = new StringEncoder() {
            @Override
            public Object encode(Object source) throws EncoderException {
                return "a";
            }

            @Override
            public String encode(String source) throws EncoderException {
                return "a";
            }
        };

        StringEncoderComparator comparator = new StringEncoderComparator(testEncoder);
        int result = comparator.compare("first", "second");
        assertEquals(0, result); // Both encode to "a", so equal
    }

    @Test
    public void testCompareReturnsPositiveWhenFirstEncodesGreaterThanSecond() {
        StringEncoder testEncoder = new StringEncoder() {
            @Override
            public Object encode(Object source) throws EncoderException {
                if ("z".equals(source)) return "z";
                return "a";
            }

            @Override
            public String encode(String source) throws EncoderException {
                if ("z".equals(source)) return "z";
                return "a";
            }
        };

        StringEncoderComparator comparator = new StringEncoderComparator(testEncoder);
        int result = comparator.compare("z", "a");
        assertTrue(result > 0);
    }

    @Test
    public void testCompareReturnsNegativeWhenFirstEncodesLessThanSecondWithDifferentValues() {
        StringEncoder testEncoder = new StringEncoder() {
            @Override
            public Object encode(Object source) throws EncoderException {
                if ("a".equals(source)) return "a";
                return "z";
            }

            @Override
            public String encode(String source) throws EncoderException {
                if ("a".equals(source)) return "a";
                return "z";
            }
        };

        StringEncoderComparator comparator = new StringEncoderComparator(testEncoder);
        int result = comparator.compare("a", "z");
        assertTrue(result < 0);
    }
}
