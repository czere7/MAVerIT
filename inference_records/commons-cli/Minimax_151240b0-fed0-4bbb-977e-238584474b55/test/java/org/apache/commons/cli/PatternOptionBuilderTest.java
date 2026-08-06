/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * https://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.commons.cli;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Date;

import org.junit.Test;

/**
 * Tests for {@link PatternOptionBuilder}.
 */
public class PatternOptionBuilderTest {

    @Test
    public void testGetValueTypeAtSign() {
        assertEquals(PatternOptionBuilder.OBJECT_VALUE, PatternOptionBuilder.getValueType('@'));
    }

    @Test
    public void testGetValueTypeColon() {
        assertEquals(PatternOptionBuilder.STRING_VALUE, PatternOptionBuilder.getValueType(':'));
    }

    @Test
    public void testGetValueTypePercent() {
        assertEquals(PatternOptionBuilder.NUMBER_VALUE, PatternOptionBuilder.getValueType('%'));
    }

    @Test
    public void testGetValueTypePlus() {
        assertEquals(PatternOptionBuilder.CLASS_VALUE, PatternOptionBuilder.getValueType('+'));
    }

    @Test
    public void testGetValueTypeHash() {
        assertEquals(PatternOptionBuilder.DATE_VALUE, PatternOptionBuilder.getValueType('#'));
    }

    @Test
    public void testGetValueTypeLessThan() {
        assertEquals(PatternOptionBuilder.EXISTING_FILE_VALUE, PatternOptionBuilder.getValueType('<'));
    }

    @Test
    public void testGetValueTypeGreaterThan() {
        assertEquals(PatternOptionBuilder.FILE_VALUE, PatternOptionBuilder.getValueType('>'));
    }

    @Test
    public void testGetValueTypeAsterisk() {
        assertEquals(PatternOptionBuilder.FILES_VALUE, PatternOptionBuilder.getValueType('*'));
    }

    @Test
    public void testGetValueTypeSlash() {
        assertEquals(PatternOptionBuilder.URL_VALUE, PatternOptionBuilder.getValueType('/'));
    }

    @Test
    public void testGetValueTypeInvalidCharacter() {
        assertNull(PatternOptionBuilder.getValueType('a'));
        assertNull(PatternOptionBuilder.getValueType('X'));
        assertNull(PatternOptionBuilder.getValueType('0'));
        assertNull(PatternOptionBuilder.getValueType(' '));
    }

    @Test
    public void testIsValueCodeValid() {
        assertTrue(PatternOptionBuilder.isValueCode('@'));
        assertTrue(PatternOptionBuilder.isValueCode(':'));
        assertTrue(PatternOptionBuilder.isValueCode('%'));
        assertTrue(PatternOptionBuilder.isValueCode('+'));
        assertTrue(PatternOptionBuilder.isValueCode('#'));
        assertTrue(PatternOptionBuilder.isValueCode('<'));
        assertTrue(PatternOptionBuilder.isValueCode('>'));
        assertTrue(PatternOptionBuilder.isValueCode('*'));
        assertTrue(PatternOptionBuilder.isValueCode('/'));
        assertTrue(PatternOptionBuilder.isValueCode('!'));
    }

    @Test
    public void testIsValueCodeInvalid() {
        assertFalse(PatternOptionBuilder.isValueCode('a'));
        assertFalse(PatternOptionBuilder.isValueCode('Z'));
        assertFalse(PatternOptionBuilder.isValueCode('0'));
        assertFalse(PatternOptionBuilder.isValueCode(' '));
        assertFalse(PatternOptionBuilder.isValueCode('-'));
    }

    @Test
    public void testParsePatternSimpleOption() {
        final Options options = PatternOptionBuilder.parsePattern("a");
        assertNotNull(options);
        assertNotNull(options.getOption("a"));
        assertFalse(options.getOption("a").hasArg());
        assertFalse(options.getOption("a").isRequired());
    }

    @Test
    public void testParsePatternOptionWithStringValue() {
        final Options options = PatternOptionBuilder.parsePattern("v:");
        assertNotNull(options);
        assertNotNull(options.getOption("v"));
        assertTrue(options.getOption("v").hasArg());
        assertEquals(PatternOptionBuilder.STRING_VALUE, options.getOption("v").getType());
    }

    @Test
    public void testParsePatternOptionWithNumberValue() {
        final Options options = PatternOptionBuilder.parsePattern("n%");
        assertNotNull(options);
        assertNotNull(options.getOption("n"));
        assertTrue(options.getOption("n").hasArg());
        assertEquals(PatternOptionBuilder.NUMBER_VALUE, options.getOption("n").getType());
    }

    @Test
    public void testParsePatternOptionWithClassValue() {
        final Options options = PatternOptionBuilder.parsePattern("c+");
        assertNotNull(options);
        assertNotNull(options.getOption("c"));
        assertTrue(options.getOption("c").hasArg());
        assertEquals(PatternOptionBuilder.CLASS_VALUE, options.getOption("c").getType());
    }

    @Test
    public void testParsePatternOptionWithObjectValue() {
        final Options options = PatternOptionBuilder.parsePattern("o@");
        assertNotNull(options);
        assertNotNull(options.getOption("o"));
        assertTrue(options.getOption("o").hasArg());
        assertEquals(PatternOptionBuilder.OBJECT_VALUE, options.getOption("o").getType());
    }

    @Test
    public void testParsePatternOptionWithDateValue() {
        final Options options = PatternOptionBuilder.parsePattern("d#");
        assertNotNull(options);
        assertNotNull(options.getOption("d"));
        assertTrue(options.getOption("d").hasArg());
        assertEquals(PatternOptionBuilder.DATE_VALUE, options.getOption("d").getType());
    }

    @Test
    public void testParsePatternOptionWithFileValue() {
        final Options options = PatternOptionBuilder.parsePattern("f>");
        assertNotNull(options);
        assertNotNull(options.getOption("f"));
        assertTrue(options.getOption("f").hasArg());
        assertEquals(PatternOptionBuilder.FILE_VALUE, options.getOption("f").getType());
    }

    @Test
    public void testParsePatternOptionWithExistingFileValue() {
        final Options options = PatternOptionBuilder.parsePattern("e<");
        assertNotNull(options);
        assertNotNull(options.getOption("e"));
        assertTrue(options.getOption("e").hasArg());
        assertEquals(PatternOptionBuilder.EXISTING_FILE_VALUE, options.getOption("e").getType());
    }

    @Test
    public void testParsePatternOptionWithFilesValue() {
        final Options options = PatternOptionBuilder.parsePattern("f*");
        assertNotNull(options);
        assertNotNull(options.getOption("f"));
        assertTrue(options.getOption("f").hasArg());
        assertEquals(PatternOptionBuilder.FILES_VALUE, options.getOption("f").getType());
    }

    @Test
    public void testParsePatternOptionWithUrlValue() {
        final Options options = PatternOptionBuilder.parsePattern("u/");
        assertNotNull(options);
        assertNotNull(options.getOption("u"));
        assertTrue(options.getOption("u").hasArg());
        assertEquals(PatternOptionBuilder.URL_VALUE, options.getOption("u").getType());
    }

    @Test
    public void testParsePatternRequiredOption() {
        final Options options = PatternOptionBuilder.parsePattern("v!");
        assertNotNull(options);
        assertNotNull(options.getOption("v"));
        assertTrue(options.getOption("v").isRequired());
    }

    @Test
    public void testParsePatternMultipleOptions() {
        final Options options = PatternOptionBuilder.parsePattern("abc");
        assertNotNull(options);
        assertNotNull(options.getOption("a"));
        assertNotNull(options.getOption("b"));
        assertNotNull(options.getOption("c"));
    }

    @Test
    public void testParsePatternMixedOptionsAndValues() {
        final Options options = PatternOptionBuilder.parsePattern("a:b%c+");
        assertNotNull(options);
        
        assertNotNull(options.getOption("a"));
        assertTrue(options.getOption("a").hasArg());
        assertEquals(PatternOptionBuilder.STRING_VALUE, options.getOption("a").getType());
        
        assertNotNull(options.getOption("b"));
        assertTrue(options.getOption("b").hasArg());
        assertEquals(PatternOptionBuilder.NUMBER_VALUE, options.getOption("b").getType());
        
        assertNotNull(options.getOption("c"));
        assertTrue(options.getOption("c").hasArg());
        assertEquals(PatternOptionBuilder.CLASS_VALUE, options.getOption("c").getType());
    }

    @Test
    public void testParsePatternComplexPattern() {
        final Options options = PatternOptionBuilder.parsePattern("vp:!f/");
        assertNotNull(options);
        
        assertNotNull(options.getOption("v"));
        assertFalse(options.getOption("v").hasArg());
        
        assertNotNull(options.getOption("p"));
        assertTrue(options.getOption("p").hasArg());
        assertEquals(PatternOptionBuilder.STRING_VALUE, options.getOption("p").getType());
        
        assertNotNull(options.getOption("f"));
        assertTrue(options.getOption("f").hasArg());
        assertEquals(PatternOptionBuilder.URL_VALUE, options.getOption("f").getType());
    }

    @Test
    public void testParsePatternEmptyString() {
        final Options options = PatternOptionBuilder.parsePattern("");
        assertNotNull(options);
    }

    @Test
    public void testParsePatternOnlyValueCodes() {
        final Options options = PatternOptionBuilder.parsePattern("@:%+#<>*/");
        assertNotNull(options);
        // All value codes at the start should result in no options
        assertNull(options.getOption("@"));
    }

    @Test
    public void testParsePatternRequiredAndValueCode() {
        final Options options = PatternOptionBuilder.parsePattern("a:!b%");
        assertNotNull(options);
        
        assertNotNull(options.getOption("a"));
        assertTrue(options.getOption("a").hasArg());
        assertEquals(PatternOptionBuilder.STRING_VALUE, options.getOption("a").getType());
        
        assertNotNull(options.getOption("b"));
        assertTrue(options.getOption("b").hasArg());
        assertEquals(PatternOptionBuilder.NUMBER_VALUE, options.getOption("b").getType());
    }

    @Test
    public void testGetValueClassDeprecated() {
        // The deprecated method should delegate to getValueType
        assertEquals(PatternOptionBuilder.getValueType('a'), PatternOptionBuilder.getValueClass('a'));
        assertEquals(PatternOptionBuilder.getValueType(':'), PatternOptionBuilder.getValueClass(':'));
    }

    @Test
    public void testConstants() {
        assertEquals(String.class, PatternOptionBuilder.STRING_VALUE);
        assertEquals(Object.class, PatternOptionBuilder.OBJECT_VALUE);
        assertEquals(Number.class, PatternOptionBuilder.NUMBER_VALUE);
        assertEquals(Date.class, PatternOptionBuilder.DATE_VALUE);
        assertEquals(Class.class, PatternOptionBuilder.CLASS_VALUE);
        assertEquals(FileInputStream.class, PatternOptionBuilder.EXISTING_FILE_VALUE);
        assertEquals(File.class, PatternOptionBuilder.FILE_VALUE);
        assertEquals(File[].class, PatternOptionBuilder.FILES_VALUE);
        assertEquals(URL.class, PatternOptionBuilder.URL_VALUE);
    }

    @Test
    public void testUnsupportedMethodReturnsNonNull() {
        // This test kills the NullReturnValsMutator mutation on unsupported() method
        // The mutation replaces the return value with null, this test verifies the method does not return null
        try {
            final java.lang.reflect.Method unsupportedMethod = PatternOptionBuilder.class.getDeclaredMethod("unsupported");
            unsupportedMethod.setAccessible(true);
            final Converter<?, ?> result = (Converter<?, ?>) unsupportedMethod.invoke(null);
            assertNotNull("unsupported() should not return null", result);
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void testUnsupportedConverterThrowsOnApply() {
        // Verify that the returned converter throws UnsupportedOperationException
        try {
            final java.lang.reflect.Method unsupportedMethod = PatternOptionBuilder.class.getDeclaredMethod("unsupported");
            unsupportedMethod.setAccessible(true);
            final Converter<?, ?> converter = (Converter<?, ?>) unsupportedMethod.invoke(null);
            assertNotNull("Converter should not be null", converter);
            converter.apply("test");
            fail("Expected UnsupportedOperationException to be thrown");
        } catch (java.lang.reflect.InvocationTargetException e) {
            assertTrue("Expected UnsupportedOperationException", e.getCause() instanceof UnsupportedOperationException);
        } catch (UnsupportedOperationException e) {
            // This catches the exception directly thrown by converter.apply()
            assertNotNull(e.getMessage());
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }
}
