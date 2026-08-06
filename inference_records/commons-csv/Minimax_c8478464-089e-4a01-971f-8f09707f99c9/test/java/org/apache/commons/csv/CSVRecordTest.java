/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.commons.csv;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for {@link CSVRecord}.
 */
public class CSVRecordTest {

    private CSVParser parser;

    @Before
    public void setUp() {
        try {
            // Create a real parser with standard headers A, B, C, D
            // Fix: Added .withHeader(...) to ensure header mapping is available for tests
            parser = CSVParser.parse("a,b,c,d", CSVFormat.DEFAULT.withHeader("A", "B", "C", "D"));
        } catch (IOException e) {
            throw new RuntimeException("Failed to create CSVParser for testing", e);
        }
    }

    @After
    public void tearDown() throws IOException {
        if (parser != null) {
            parser.close();
        }
    }

    private CSVRecord createRecord(String[] values) {
        return new CSVRecord(parser, values, null, 1, 0, 0);
    }
    
    private CSVRecord createRecordWithComment(String[] values, String comment) {
        return new CSVRecord(parser, values, comment, 1, 0, 0);
    }

    @Test
    public void testGetByIndex() {
        CSVRecord record = createRecord(new String[]{"a", "b", "c"});
        assertEquals("a", record.get(0));
        assertEquals("b", record.get(1));
        assertEquals("c", record.get(2));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testGetByIndexOutOfBounds() {
        CSVRecord record = createRecord(new String[]{"a", "b"});
        record.get(5);
    }

    @Test
    public void testGetByName() {
        CSVRecord record = createRecord(new String[]{"a", "b", "c"});
        assertEquals("a", record.get("A"));
        assertEquals("b", record.get("B"));
        assertEquals("c", record.get("C"));
    }

    @Test
    public void testGetByNameWithMultipleOccurrences() {
        CSVRecord record = createRecord(new String[]{"a", "b", "c"});
        assertEquals("a", record.get("A"));
    }

    @Test(expected = IllegalStateException.class)
    public void testGetByNameNoHeaderMap() {
        // Pass null parser to simulate no header map scenario
        CSVRecord record = new CSVRecord(null, new String[]{"a", "b"}, null, 1, 0, 0);
        record.get("A");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetByNameMissingHeader() {
        CSVRecord record = createRecord(new String[]{"a", "b"});
        record.get("NotFound");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetByNameInconsistentTooFewValues() {
        // Header "D" maps to index 3, but we only have values for indices 0, 1, 2.
        CSVRecord record = createRecord(new String[]{"a", "b", "c"});
        record.get("D"); // Should throw because index 3 >= size 3
    }

    @Test
    public void testGetByEnum() {
        CSVRecord record = createRecord(new String[]{"a", "b"});
        assertEquals("a", record.get(TestEnum.A));
        assertEquals("b", record.get(TestEnum.B));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetByEnumNull() {
        CSVRecord record = createRecord(new String[]{"a"});
        record.get((Enum<?>) null);
    }

    @Test
    public void testGetBytePosition() {
        CSVRecord record = new CSVRecord(parser, new String[]{"a"}, null, 1, 10, 20);
        assertEquals(20, record.getBytePosition());
    }

    @Test
    public void testGetCharacterPosition() {
        CSVRecord record = new CSVRecord(parser, new String[]{"a"}, null, 1, 10, 20);
        assertEquals(10, record.getCharacterPosition());
    }

    @Test
    public void testGetComment() {
        CSVRecord record = createRecordWithComment(new String[]{"a"}, "comment");
        assertEquals("comment", record.getComment());
    }

    @Test
    public void testHasComment() {
        CSVRecord recordWithComment = createRecordWithComment(new String[]{"a"}, "comment");
        assertTrue(recordWithComment.hasComment());

        CSVRecord recordWithoutComment = createRecord(new String[]{"a"});
        assertFalse(recordWithoutComment.hasComment());
    }

    @Test
    public void testGetParser() {
        CSVRecord record = createRecord(new String[]{"a"});
        assertSame(parser, record.getParser());
    }

    @Test
    public void testGetRecordNumber() {
        CSVRecord record = new CSVRecord(parser, new String[]{"a"}, null, 42, 0, 0);
        assertEquals(42, record.getRecordNumber());
    }

    @Test
    public void testIsConsistent() {
        // Header size 4, values size 4
        CSVRecord record = createRecord(new String[]{"a", "b", "c", "d"});
        assertTrue(record.isConsistent());

        // Header size 4, values size 3
        CSVRecord recordShort = createRecord(new String[]{"a", "b", "c"});
        assertFalse(recordShort.isConsistent());
    }

    @Test
    public void testIsConsistentNoHeaderMap() {
        // Pass null parser to simulate no header map scenario
        CSVRecord record = new CSVRecord(null, new String[]{"a", "b"}, null, 1, 0, 0);
        assertTrue(record.isConsistent()); // Returns true if headerMap is null
    }

    @Test
    public void testIsMapped() {
        CSVRecord record = createRecord(new String[]{"a", "b"});
        // A and B are mapped (exist in header map)
        assertTrue(record.isMapped("A"));
        assertTrue(record.isMapped("B"));
        // C and D are also mapped in header, but no values for them
        assertTrue(record.isMapped("C"));
        assertTrue(record.isMapped("D"));
        // null is not mapped
        assertFalse(record.isMapped(null));
    }

    @Test
    public void testIsSetByIndex() {
        CSVRecord record = createRecord(new String[]{"a", "b"});
        assertTrue(record.isSet(0));
        assertTrue(record.isSet(1));
        assertFalse(record.isSet(2));
        assertFalse(record.isSet(-1));
    }

    @Test
    public void testIsSetByName() {
        CSVRecord record = createRecord(new String[]{"a", "b"}); // A=0, B=1, C=2, D=3
        assertTrue(record.isSet("A"));
        assertTrue(record.isSet("B"));
        // C maps to 2, size is 2 (0,1). 2 is out of bounds.
        assertFalse(record.isSet("C"));
        assertFalse(record.isSet("D")); // D maps to 3, out of bounds
        
        assertFalse(record.isSet("NotMapped"));
    }

    @Test
    public void testIsSetByNameNoHeaderMap() {
        // Pass null parser to simulate no header map scenario
        CSVRecord record = new CSVRecord(null, new String[]{"a"}, null, 1, 0, 0);
        assertFalse(record.isSet("A"));
    }

    @Test
    public void testIterator() {
        CSVRecord record = createRecord(new String[]{"a", "b", "c"});
        Iterator<String> iterator = record.iterator();
        assertTrue(iterator.hasNext());
        assertEquals("a", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("b", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("c", iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testStream() {
        CSVRecord record = createRecord(new String[]{"a", "b", "c"});
        Stream<String> stream = record.stream();
        assertNotNull(stream);
        // Collect to verify content
        assertArrayEquals(new String[]{"a", "b", "c"}, stream.toArray(String[]::new));
    }

    @Test
    public void testPutIn() {
        CSVRecord record = createRecord(new String[]{"a", "b"});
        Map<String, String> map = new HashMap<>();
        record.putIn(map);
        assertEquals("a", map.get("A"));
        assertEquals("b", map.get("B"));
    }

    @Test
    public void testPutInReturnsSameMap() {
        // Test that putIn returns the exact same map instance, not an empty map
        CSVRecord record = createRecord(new String[]{"a", "b"});
        Map<String, String> map = new HashMap<>();
        Map<String, String> result = record.putIn(map);
        assertSame("putIn should return the same map instance", map, result);
        assertFalse("Returned map should not be empty", result.isEmpty());
    }

    @Test
    public void testPutInNullHeaderMap() {
        // Pass null parser to simulate no header map scenario
        CSVRecord record = new CSVRecord(null, new String[]{"a"}, null, 1, 0, 0);
        Map<String, String> map = new HashMap<>();
        map.put("X", "Y");
        Map<String, String> result = record.putIn(map);
        assertSame("putIn should return the same map instance even with null headerMap", map, result);
        assertEquals("Y", map.get("X")); // Map unchanged
        assertNull(map.get("A"));
    }

    @Test
    public void testPutInSkipsInconsistent() {
        // Header "C" maps to 2, but size is 2 (0,1). Should skip.
        CSVRecord record = createRecord(new String[]{"a", "b"});
        Map<String, String> map = new HashMap<>();
        record.putIn(map);
        assertNull(map.get("C"));
    }

    @Test
    public void testPutInEmptyValues() {
        // Test with empty values array
        CSVRecord record = createRecord(new String[]{});
        Map<String, String> map = new HashMap<>();
        Map<String, String> result = record.putIn(map);
        assertSame("putIn should return the same map instance", map, result);
        assertTrue("Map should be empty when no values", map.isEmpty());
    }

    @Test
    public void testSize() {
        CSVRecord record = createRecord(new String[]{"a", "b", "c"});
        assertEquals(3, record.size());
    }
    
    @Test
    public void testSizeNullValues() {
        // The constructor handles null values by assigning an empty array
        CSVRecord record = new CSVRecord(parser, null, null, 1, 0, 0);
        assertEquals(0, record.size());
    }

    @Test
    public void testToList() {
        CSVRecord record = createRecord(new String[]{"a", "b"});
        assertEquals(Arrays.asList("a", "b"), record.toList());
    }

    @Test
    public void testToMap() {
        CSVRecord record = createRecord(new String[]{"a", "b", "c"});
        Map<String, String> map = record.toMap();
        assertEquals("a", map.get("A"));
        assertEquals("b", map.get("B"));
        assertEquals("c", map.get("C"));
    }
    
    @Test
    public void testToMapNoHeaderMap() {
        // Pass null parser to simulate no header map scenario
        CSVRecord record = new CSVRecord(null, new String[]{"a"}, null, 1, 0, 0);
        Map<String, String> map = record.toMap();
        assertTrue(map.isEmpty());
    }

    @Test
    public void testToString() {
        CSVRecord record = createRecordWithComment(new String[]{"a", "b"}, "comment");
        String str = record.toString();
        assertTrue(str.contains("comment"));
        assertTrue(str.contains("recordNumber=1"));
        assertTrue(str.contains("values=[a, b]"));
    }

    @Test
    public void testValues() {
        String[] values = new String[]{"a", "b"};
        CSVRecord record = createRecord(values);
        assertSame(values, record.values());
    }

    private enum TestEnum {
        A, B
    }
}
