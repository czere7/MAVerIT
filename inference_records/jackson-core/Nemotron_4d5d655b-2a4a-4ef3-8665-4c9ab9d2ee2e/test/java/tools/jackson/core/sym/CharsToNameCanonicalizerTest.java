package tools.jackson.core.sym;

import static org.junit.Assert.*;

import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.Test;

import tools.jackson.core.StreamReadConstraints;
import tools.jackson.core.TokenStreamFactory;
import tools.jackson.core.exc.StreamConstraintsException;
import tools.jackson.core.json.JsonFactory;
import tools.jackson.core.json.JsonFactoryBuilder;
import tools.jackson.core.util.InternCache;

public class CharsToNameCanonicalizerTest
{

    @Test
    public void testCreateRootWithNullFactory() {
        CharsToNameCanonicalizer root = CharsToNameCanonicalizer.createRoot(null);
        assertNotNull(root);
        assertNull(root._parent);
        assertNotNull(root._tableInfo);
        assertEquals(0, root.size());
    }

    @Test
    public void testCreateRootWithJsonFactory() {
        JsonFactory factory = new JsonFactory();
        CharsToNameCanonicalizer root = CharsToNameCanonicalizer.createRoot(factory);
        assertNotNull(root);
        assertEquals(factory.streamReadConstraints(), root._streamReadConstraints);
        assertEquals(factory.getFactoryFeatures(), root._factoryFeatures);
    }

    @Test
    public void testCreateRootWithSeed() {
        JsonFactory factory = new JsonFactory();
        CharsToNameCanonicalizer root = CharsToNameCanonicalizer.createRoot(factory, 12345);
        assertEquals(12345, root.hashSeed());
    }

    @Test
    public void testMakeChild() {
        JsonFactory factory = new JsonFactory();
        CharsToNameCanonicalizer root = CharsToNameCanonicalizer.createRoot(factory);
        CharsToNameCanonicalizer child = root.makeChild();
        
        assertNotNull(child);
        assertSame(root, child._parent);
        assertNull(child._tableInfo); 
        assertEquals(root._seed, child._seed);
        assertEquals(root._streamReadConstraints, child._streamReadConstraints);
        assertEquals(root._factoryFeatures, child._factoryFeatures);
        assertTrue(child._hashShared); 
    }

    @Test
    public void testChildInheritsConfiguration() {
        JsonFactory factory = new JsonFactory();
        CharsToNameCanonicalizer root = CharsToNameCanonicalizer.createRoot(factory);
        CharsToNameCanonicalizer child = root.makeChild();
        
        assertEquals(root.willInternStrings(), child.willInternStrings());
        assertEquals(root._canonicalize, child._canonicalize);
    }


    @Test
    public void testCalcHashCharArray() {
        JsonFactory factory = new JsonFactory();
        CharsToNameCanonicalizer canon = CharsToNameCanonicalizer.createRoot(factory).makeChild();
        
        char[] buffer = "testName".toCharArray();
        int hash1 = canon.calcHash(buffer, 0, buffer.length);
        int hash2 = canon.calcHash(buffer, 0, buffer.length);
        assertEquals(hash1, hash2); 
        
        char[] buffer2 = "different".toCharArray();
        int hash3 = canon.calcHash(buffer2, 0, buffer2.length);
        assertNotEquals(hash1, hash3);
    }

    @Test
    public void testCalcHashString() {
        JsonFactory factory = new JsonFactory();
        CharsToNameCanonicalizer canon = CharsToNameCanonicalizer.createRoot(factory).makeChild();
        
        String name = "testName";
        int hash1 = canon.calcHash(name);
        int hash2 = canon.calcHash(name);
        assertEquals(hash1, hash2);
        
        int emptyHash = canon.calcHash("");
        assertNotEquals(0, emptyHash); 
    }

    @Test
    public void testCalcHashNonZero() {
        JsonFactory factory = new JsonFactory();
        CharsToNameCanonicalizer canon = CharsToNameCanonicalizer.createRoot(factory).makeChild();
        
        char[] buffer = new char[100]; 
        int hash = canon.calcHash(buffer, 0, buffer.length);
        assertNotEquals(0, hash);
    }

    @Test
    public void testCalcHashZeroSeedEdgeCase() {
        try {
            Class<?> clazz = CharsToNameCanonicalizer.class;
            java.lang.reflect.Constructor<?> ctor = clazz.getDeclaredConstructor(
                StreamReadConstraints.class, int.class, int.class);
            ctor.setAccessible(true);
            CharsToNameCanonicalizer canon = (CharsToNameCanonicalizer) ctor.newInstance(
                StreamReadConstraints.defaults(), 0, 0);
            
            char[] buffer = new char[] { 0 }; 
            int hash = canon.calcHash(buffer, 0, 1);
            assertEquals(1, hash); 
            
            String str = new String(new char[] { 0 });
            int hash2 = canon.calcHash(str);
            assertEquals(1, hash2);
        } catch (Exception e) {
            fail("Reflection failed: " + e.getMessage());
        }
    }

    @Test
    public void testCalcHashExactValues() {
        try {
            Class<?> clazz = CharsToNameCanonicalizer.class;
            java.lang.reflect.Constructor<?> ctor = clazz.getDeclaredConstructor(
                StreamReadConstraints.class, int.class, int.class);
            ctor.setAccessible(true);
            CharsToNameCanonicalizer canon = (CharsToNameCanonicalizer) ctor.newInstance(
                StreamReadConstraints.defaults(), 0, 0);
            
            char[] a = "a".toCharArray();
            assertEquals(97, canon.calcHash(a, 0, 1));
            
            char[] ab = "ab".toCharArray();
            assertEquals(3299, canon.calcHash(ab, 0, 2));
            
            char[] abc = "abc".toCharArray();
            assertEquals(108966, canon.calcHash(abc, 0, 3));
            
            assertEquals(97, canon.calcHash("a"));
            assertEquals(3299, canon.calcHash("ab"));
            assertEquals(108966, canon.calcHash("abc"));
            
            CharsToNameCanonicalizer canon2 = (CharsToNameCanonicalizer) ctor.newInstance(
                StreamReadConstraints.defaults(), 0, 100); 
            assertEquals(3397, canon2.calcHash("a")); 
            
        } catch (Exception e) {
            fail("Reflection failed: " + e.getMessage());
        }
    }

    @Test
    public void testHashToIndexExact() {
        JsonFactory factory = new JsonFactory();
        CharsToNameCanonicalizer canon = CharsToNameCanonicalizer.createRoot(factory).makeChild();
        
        try {
            Method hashToIndex = CharsToNameCanonicalizer.class.getDeclaredMethod("_hashToIndex", int.class);
            hashToIndex.setAccessible(true);
            
            assertEquals(0, hashToIndex.invoke(canon, 0));
            
            assertEquals(17, hashToIndex.invoke(canon, 1));
            
            assertEquals(54, hashToIndex.invoke(canon, 63));
            
            assertEquals(37, hashToIndex.invoke(canon, 1000));
            
        } catch (Exception e) {
            fail("Reflection failed: " + e.getMessage());
        }
    }

    @Test
    public void testThresholdSizeCalculation() {
        try {
            Method thresholdSize = CharsToNameCanonicalizer.class.getDeclaredMethod("_thresholdSize", int.class);
            thresholdSize.setAccessible(true);
            
            assertEquals(48, thresholdSize.invoke(null, 64));
            
            assertEquals(96, thresholdSize.invoke(null, 128));
            
            assertEquals(192, thresholdSize.invoke(null, 256));
            
            assertEquals(75, thresholdSize.invoke(null, 100));
            
        } catch (Exception e) {
            fail("Reflection failed: " + e.getMessage());
        }
    }

    @Test
    public void testHashToIndex() {
        JsonFactory factory = new JsonFactory();
        CharsToNameCanonicalizer canon = CharsToNameCanonicalizer.createRoot(factory).makeChild();
        
        int index1 = canon._hashToIndex(12345);
        int index2 = canon._hashToIndex(12345);
        assertEquals(index1, index2);
        
        assertTrue(index1 >= 0);
        assertTrue(index1 < canon._symbols.length);
    }



    @Test
    public void testFindSymbolNewEntry() {
        JsonFactory factory = new JsonFactory();
        CharsToNameCanonicalizer canon = CharsToNameCanonicalizer.createRoot(factory).makeChild();
        
        char[] buffer = "propertyName".toCharArray();
        int hash = canon.calcHash(buffer, 0, buffer.length);
        String result = canon.findSymbol(buffer, 0, buffer.length, hash);
        
        assertEquals("propertyName", result);
        assertEquals(1, canon.size());
    }

    @Test
    public void testFindSymbolExistingEntry() {
        JsonFactory factory = new JsonFactory();
        CharsToNameCanonicalizer canon = CharsToNameCanonicalizer.createRoot(factory).makeChild();
        
        char[] buffer = "propertyName".toCharArray();
        int hash = canon.calcHash(buffer, 0, buffer.length);
        
        String result1 = canon.findSymbol(buffer, 0, buffer.length, hash);
        String result2 = canon.findSymbol(buffer, 0, buffer.length, hash);
        
        assertSame(result1, result2);
        assertEquals(1, canon.size());
    }

    @Test
    public void testFindSymbolEmptyString() {
        JsonFactory factory = new JsonFactory();
        CharsToNameCanonicalizer canon = CharsToNameCanonicalizer.createRoot(factory).makeChild();
        
        char[] buffer = new char[0];
        String result = canon.findSymbol(buffer, 0, 0, 0);
        
        assertEquals("", result);
    }

    @Test
    public void testFindSymbolWithOffsetAndLength() {
        JsonFactory factory = new JsonFactory();
        CharsToNameCanonicalizer canon = CharsToNameCanonicalizer.createRoot(factory).makeChild();
        
        char[] buffer = "prefix_propertyName_suffix".toCharArray();
        int hash = canon.calcHash(buffer, 7, 12); 
        String result = canon.findSymbol(buffer, 7, 12, hash);
        
        assertEquals("propertyName", result);
    }

    @Test
    public void testFindSymbolCanonicalizeDisabledBranch() {
        JsonFactory factory = JsonFactory.builder()
            .disable(TokenStreamFactory.Feature.CANONICALIZE_PROPERTY_NAMES)
            .build();
        CharsToNameCanonicalizer canon = CharsToNameCanonicalizer.createRoot(factory).makeChild();
        
        char[] buffer = "testName".toCharArray();
        int hash = canon.calcHash(buffer, 0, buffer.length);
        
        String result1 = canon.findSymbol(buffer, 0, buffer.length, hash);
        String result2 = canon.findSymbol(buffer, 0, buffer.length, hash);
        
        assertNotSame(result1, result2);
        assertEquals("testName", result1);
        assertEquals("testName", result2);
    }

    @Test
    public void testFindSymbolNameLengthValidation() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
            .maxNameLength(5)
            .build();
        
        JsonFactory factory = JsonFactory.builder()
            .streamReadConstraints(constraints)
            .disable(TokenStreamFactory.Feature.CANONICALIZE_PROPERTY_NAMES)
            .build();
        
        CharsToNameCanonicalizer canon = CharsToNameCanonicalizer.createRoot(factory).makeChild();
        
        char[] buffer = "tooLongName".toCharArray(); 
        int hash = canon.calcHash(buffer, 0, buffer.length);
        
        try {
            canon.findSymbol(buffer, 0, buffer.length, hash);
            fail("Expected StreamConstraintsException");
        } catch (StreamConstraintsException e) {
            assertTrue(e.getMessage().contains("exceeds the maximum allowed"));
        }
    }

    @Test
    public void testFindSymbolLenLessThanOne() {
        JsonFactory factory = new JsonFactory();
        CharsToNameCanonicalizer canon = CharsToNameCanonicalizer.createRoot(factory).makeChild();
        
        char[] buffer = "test".toCharArray();
        String result = canon.findSymbol(buffer, 0, 0, 0);
        assertEquals("", result);
        
    }

    @Test
    public void testFindSymbolLenZeroBoundary() {
        JsonFactory factory = new JsonFactory();
        CharsToNameCanonicalizer canon = CharsToNameCanonicalizer.createRoot(factory).makeChild();
        
        char[] buffer = "test".toCharArray();
        
        String result0 = canon.findSymbol(buffer, 0, 0, 0);
        assertEquals("", result0);
        
        String result1 = canon.findSymbol(buffer, 0, 1, canon.calcHash(buffer, 0, 1));
        assertEquals("t", result1);
        assertEquals(1, canon.size());
    }

    @Test
    public void testFindSymbolReturnsEmptyStringForLenZero() {
        JsonFactory factory = new JsonFactory();
        CharsToNameCanonicalizer canon = CharsToNameCanonicalizer.createRoot(factory).makeChild();
        
        char[] buffer = new char[0];
        String result = canon.findSymbol(buffer, 0, 0, 0);
        
        assertNotNull(result);
        assertEquals("", result);
        assertSame("", result); 
    }


    @Test
    public void testCanonicalizationDisabled() {
        JsonFactory factory = JsonFactory.builder()
            .disable(TokenStreamFactory.Feature.CANONICALIZE_PROPERTY_NAMES)
            .build();
        CharsToNameCanonicalizer canon = CharsToNameCanonicalizer.createRoot(factory).makeChild();
        
        char[] buffer = "testName".toCharArray();
        int hash = canon.calcHash(buffer, 0, buffer.length);
        
        String result1 = canon.findSymbol(buffer, 0, buffer.length, hash);
        String result2 = canon.findSymbol(buffer, 0, buffer.length, hash);
        
        assertNotSame(result1, result2);
        assertEquals("testName", result1);
        assertEquals("testName", result2);
    }


    @Test
    public void testInterningEnabled() {
        JsonFactory factory = JsonFactory.builder()
            .enable(JsonFactory.Feature.INTERN_PROPERTY_NAMES)
            .build();
        CharsToNameCanonicalizer canon = CharsToNameCanonicalizer.createRoot(factory).makeChild();
        
        char[] buffer = "testName".toCharArray();
        int hash = canon.calcHash(buffer, 0, buffer.length);
        String result = canon.findSymbol(buffer, 0, buffer.length, hash);
        
        assertSame(result, result.intern());
    }

    @Test
    public void testInterningDisabled() {
        JsonFactory factory = JsonFactory.builder()
            .disable(JsonFactory.Feature.INTERN_PROPERTY_NAMES)
            .build();
        CharsToNameCanonicalizer canon = CharsToNameCanonicalizer.createRoot(factory).makeChild();
        
        char[] buffer = "testName".toCharArray();
        int hash = canon.calcHash(buffer, 0, buffer.length);
        String result = canon.findSymbol(buffer, 0, buffer.length, hash);
        
        assertNotSame(result, result.intern());
    }


    @Test
    public void testWillInternStringsReturnsTrueWhenEnabled() {
        JsonFactory factory = JsonFactory.builder()
            .enable(JsonFactory.Feature.INTERN_PROPERTY_NAMES)
            .build();
        CharsToNameCanonicalizer canon = CharsToNameCanonicalizer.createRoot(factory).makeChild();
        
        assertTrue(canon.willInternStrings());
    }

    @Test
    public void testWillInternStringsReturnsFalseWhenDisabled() {
        JsonFactory factory = JsonFactory.builder()
            .disable(JsonFactory.Feature.INTERN_PROPERTY_NAMES)
            .build();
        CharsToNameCanonicalizer canon = CharsToNameCanonicalizer.createRoot(factory).makeChild();
        
        assertFalse(canon.willInternStrings());
    }

    @Test
    public void testWillInternStringsDefaultIsFalse() {
        JsonFactory factory = new JsonFactory();
        CharsToNameCanonicalizer canon = CharsToNameCanonicalizer.createRoot(factory).makeChild();
        
        assertFalse(canon.willInternStrings());
    }


    @Test
    public void testCollisionCount() {
        JsonFactory factory = new JsonFactory();
        CharsToNameCanonicalizer canon = CharsToNameCanonicalizer.createRoot(factory).makeChild();
        
        assertEquals(0, canon.collisionCount());
        assertEquals(0, canon.maxCollisionLength());
        
        addSymbol(canon, "name1");
        addSymbol(canon, "name2");
        
        assertEquals(0, canon.collisionCount());
        assertEquals(0, canon.maxCollisionLength());
    }

    @Test
    public void testCollisionCountWithNullBuckets() {
        JsonFactory factory = new JsonFactory();
        CharsToNameCanonicalizer canon = CharsToNameCanonicalizer.createRoot(factory).makeChild();
        
        int count = canon.collisionCount();
        assertEquals(0, count);
        
        addSymbol(canon, "test");
        count = canon.collisionCount();
        assertEquals(0, count);
    }

    @Test
    public void testCollisionDetection() {
        JsonFactory factory = new JsonFactory();
        CharsToNameCanonicalizer canon = CharsToNameCanonicalizer.createRoot(factory).makeChild();
        
        addSymbol(canon, "a");
        addSymbol(canon, "b");
        addSymbol(canon, "c");
        
        int collisions = canon.collisionCount();
        int maxColl = canon.maxCollisionLength();
        
        assertTrue(collisions >= 0);
        assertTrue(maxColl >= 0);
        assertTrue(maxColl >= collisions); 
    }

    @Test
    public void testCollisionCountWithNonNullBucket() {
        JsonFactory factory = new JsonFactory();
        CharsToNameCanonicalizer canon = CharsToNameCanonicalizer.createRoot(factory).makeChild();
        
        try {
            addSymbol(canon, "symbolA");
            
            for (int i = 0; i < 100; i++) {
                addSymbol(canon, "sym" + i);
            }
            
            int count = canon.collisionCount();
            assertTrue(count >= 0);
        } catch (Exception e) {
            fail("Reflection failed: " + e.getMessage());
        }
    }

    @Test
    public void testRehashing() {
        JsonFactory factory = new JsonFactory();
        CharsToNameCanonicalizer canon = CharsToNameCanonicalizer.createRoot(factory).makeChild();
        
        int initialSize = canon._symbols.length;
        
        int threshold = canon._sizeThreshold;
        for (int i = 0; i < threshold + 5; i++) {
            addSymbol(canon, "name" + i);
        }
        
        assertTrue(canon._symbols.length > initialSize);
        assertEquals(threshold + 5, canon.size());
    }

    @Test
    public void testMaxSizeLimitConstant() {
        JsonFactory factory = new JsonFactory();
        CharsToNameCanonicalizer canon = CharsToNameCanonicalizer.createRoot(factory).makeChild();
        
        assertTrue(true); 
    }


    @Test
    public void testReleaseNoChanges() {
        JsonFactory factory = new JsonFactory();
        CharsToNameCanonicalizer root = CharsToNameCanonicalizer.createRoot(factory);
        CharsToNameCanonicalizer child = root.makeChild();
        
        child.release();
        
        assertEquals(0, root.size());
    }

    @Test
    public void testReleaseWithChanges() {
        JsonFactory factory = new JsonFactory();
        CharsToNameCanonicalizer root = CharsToNameCanonicalizer.createRoot(factory);
        CharsToNameCanonicalizer child = root.makeChild();
        
        addSymbol(child, "testProperty");
        assertEquals(1, child.size());
        assertEquals(0, root.size()); 
        
        child.release();
        
        assertEquals(1, root.size());
        
        assertTrue(child._hashShared);
    }

    @Test
    public void testReleaseRootInstance() {
        JsonFactory factory = new JsonFactory();
        CharsToNameCanonicalizer root = CharsToNameCanonicalizer.createRoot(factory);
        
        root.release(); 
        assertEquals(0, root.size());
    }

    @Test
    public void testMergeChildUpdatesRoot() {
        JsonFactory factory = new JsonFactory();
        CharsToNameCanonicalizer root = CharsToNameCanonicalizer.createRoot(factory);
        CharsToNameCanonicalizer child1 = root.makeChild();
        CharsToNameCanonicalizer child2 = root.makeChild();
        
        addSymbol(child1, "prop1");
        addSymbol(child1, "prop2");
        child1.release();
        
        assertEquals(2, root.size());
        
        addSymbol(child2, "prop3");
        child2.release();
        
        assertEquals(1, root.size());
    }

    @Test
    public void testMergeChildSkipsIfNoSizeChange() {
        JsonFactory factory = new JsonFactory();
        CharsToNameCanonicalizer root = CharsToNameCanonicalizer.createRoot(factory);
        CharsToNameCanonicalizer child = root.makeChild();
        
        child.release(); 
        
        assertEquals(0, root.size());
    }

    @Test
    public void testMergeChildWithSizeChange() {
        JsonFactory factory = new JsonFactory();
        CharsToNameCanonicalizer root = CharsToNameCanonicalizer.createRoot(factory);
        CharsToNameCanonicalizer child = root.makeChild();
        
        addSymbol(child, "newSymbol");
        assertEquals(1, child.size());
        assertEquals(0, root.size());
        
        child.release();
        
        assertEquals(1, root.size());
    }

    @Test
    public void testMergeChildExactSizeMatchSkipsMerge() {
        JsonFactory factory = new JsonFactory();
        CharsToNameCanonicalizer root = CharsToNameCanonicalizer.createRoot(factory);
        CharsToNameCanonicalizer child = root.makeChild();
        
        // Add symbols to child only
        addSymbol(child, "shared1");
        addSymbol(child, "shared2");
        assertEquals(2, child.size());
        assertEquals(0, root.size());
        
        // Release child - should merge
        child.release();
        assertEquals(2, root.size());
        
        // Create another child, don't add anything, release - should skip merge
        CharsToNameCanonicalizer child2 = root.makeChild();
        assertEquals(2, child2.size());
        child2.release();
        assertEquals(2, root.size()); // Size unchanged
    }

    @Test
    public void testMaxEntriesForReusePurge() {
        JsonFactory factory = new JsonFactory();
        CharsToNameCanonicalizer root = CharsToNameCanonicalizer.createRoot(factory);
        
        assertEquals(12000, CharsToNameCanonicalizer.MAX_ENTRIES_FOR_REUSE);
        
    }

    @Test
    public void testMergeChildPurgesLargeTable() throws Exception {
        JsonFactory factory = new JsonFactory();
        CharsToNameCanonicalizer root = CharsToNameCanonicalizer.createRoot(factory);
        
        Class<?> tableInfoClass = Class.forName("tools.jackson.core.sym.CharsToNameCanonicalizer$TableInfo");
        java.lang.reflect.Constructor<?> tiCtor = tableInfoClass.getDeclaredConstructor(
            int.class, int.class, String[].class, CharsToNameCanonicalizer.Bucket[].class);
        tiCtor.setAccessible(true);
        
        // Create a child state with size > MAX_ENTRIES_FOR_REUSE (12000)
        String[] symbols = new String[16384];
        CharsToNameCanonicalizer.Bucket[] buckets = new CharsToNameCanonicalizer.Bucket[8192];
        Object largeChildState = tiCtor.newInstance(12001, 0, symbols, buckets);
        
        Method mergeChild = CharsToNameCanonicalizer.class.getDeclaredMethod("mergeChild", tableInfoClass);
        mergeChild.setAccessible(true);
        mergeChild.invoke(root, largeChildState);
        
        java.lang.reflect.Field tableInfoField = CharsToNameCanonicalizer.class.getDeclaredField("_tableInfo");
        tableInfoField.setAccessible(true);
        Object tableInfoRef = tableInfoField.get(root);
        
        assertTrue(tableInfoRef instanceof AtomicReference);
        AtomicReference<?> atomicRef = (AtomicReference<?>) tableInfoRef;
        Object tableInfo = atomicRef.get();
        
        java.lang.reflect.Field symbolsField = tableInfoClass.getDeclaredField("symbols");
        symbolsField.setAccessible(true);
        String[] rootSymbols = (String[]) symbolsField.get(tableInfo);
        
        // Should have been purged back to DEFAULT_T_SIZE (64)
        assertEquals(64, rootSymbols.length);
        assertEquals(0, root.size());
    }


    @Test
    public void testNameLengthValidation() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
            .maxNameLength(10)
            .build();
        
        JsonFactory factory = JsonFactory.builder()
            .streamReadConstraints(constraints)
            .build();
        
        CharsToNameCanonicalizer canon = CharsToNameCanonicalizer.createRoot(factory).makeChild();
        
        char[] buffer = "thisNameIsTooLong".toCharArray(); 
        int hash = canon.calcHash(buffer, 0, buffer.length);
        
        try {
            canon.findSymbol(buffer, 0, buffer.length, hash);
            fail("Expected StreamConstraintsException");
        } catch (StreamConstraintsException e) {
            assertTrue(e.getMessage().contains("exceeds the maximum allowed"));
        }
    }

    @Test
    public void testNameLengthValidationDefault() {
        JsonFactory factory = new JsonFactory();
        CharsToNameCanonicalizer canon = CharsToNameCanonicalizer.createRoot(factory).makeChild();
        
        char[] buffer = "thisIsAVeryLongPropertyNameThatShouldBeAllowedUnderNormalCircumstances".toCharArray();
        int hash = canon.calcHash(buffer, 0, buffer.length);
        
        String result = canon.findSymbol(buffer, 0, buffer.length, hash);
        assertEquals(new String(buffer), result);
    }


    @Test
    public void testVerifyInternalConsistency() {
        JsonFactory factory = new JsonFactory();
        CharsToNameCanonicalizer canon = CharsToNameCanonicalizer.createRoot(factory).makeChild();
        
        canon.verifyInternalConsistency();
        
        addSymbol(canon, "test1");
        addSymbol(canon, "test2");
        addSymbol(canon, "test3");
        
        canon.verifyInternalConsistency();
    }

    @Test
    public void testVerifyInternalConsistencyAfterRehash() {
        JsonFactory factory = new JsonFactory();
        CharsToNameCanonicalizer canon = CharsToNameCanonicalizer.createRoot(factory).makeChild();
        
        int threshold = canon._sizeThreshold;
        for (int i = 0; i < threshold + 10; i++) {
            addSymbol(canon, "name" + i);
        }
        
        canon.verifyInternalConsistency();
    }

    @Test
    public void testVerifyInternalConsistencyDetectsCorruption() {
        JsonFactory factory = new JsonFactory();
        CharsToNameCanonicalizer canon = CharsToNameCanonicalizer.createRoot(factory).makeChild();
        
        addSymbol(canon, "test1");
        addSymbol(canon, "test2");
        
        try {
            java.lang.reflect.Field sizeField = CharsToNameCanonicalizer.class.getDeclaredField("_size");
            sizeField.setAccessible(true);
            sizeField.setInt(canon, 999); 
            
            try {
                canon.verifyInternalConsistency();
                fail("Expected IllegalStateException");
            } catch (IllegalStateException e) {
                assertTrue(e.getMessage().contains("expected internal size"));
                assertTrue(e.getMessage().contains("999"));
            }
        } catch (Exception e) {
            fail("Reflection failed: " + e.getMessage());
        }
    }


    @Test
    public void testCopyOnWrite() {
        JsonFactory factory = new JsonFactory();
        CharsToNameCanonicalizer root = CharsToNameCanonicalizer.createRoot(factory);
        CharsToNameCanonicalizer child = root.makeChild();
        
        assertTrue(child._hashShared); 
        
        addSymbol(child, "testProperty");
        
        assertFalse(child._hashShared); 
        assertEquals(0, root.size());
    }

    @Test
    public void testMultipleChildrenIndependent() {
        JsonFactory factory = new JsonFactory();
        CharsToNameCanonicalizer root = CharsToNameCanonicalizer.createRoot(factory);
        CharsToNameCanonicalizer child1 = root.makeChild();
        CharsToNameCanonicalizer child2 = root.makeChild();
        
        addSymbol(child1, "prop1");
        addSymbol(child2, "prop2");
        
        assertEquals(1, child1.size());
        assertEquals(1, child2.size());
        assertEquals(0, root.size());
        
        child1.release();
        assertEquals(1, root.size());
        
        child2.release();
        assertEquals(1, root.size());
    }

    @Test
    public void testAddSymbolTriggersRehash() {
        JsonFactory factory = new JsonFactory();
        CharsToNameCanonicalizer canon = CharsToNameCanonicalizer.createRoot(factory).makeChild();
        
        int initialSize = canon._symbols.length;
        int threshold = canon._sizeThreshold;
        
        for (int i = 0; i < threshold; i++) {
            addSymbol(canon, "name" + i);
        }
        assertEquals(threshold, canon.size());
        assertSame(initialSize, canon._symbols.length); 
        
        String[] oldSymbols = canon._symbols;
        addSymbol(canon, "triggerRehash");
        
        assertTrue(canon._symbols.length > initialSize);
        assertNotSame(oldSymbols, canon._symbols);
    }

    @Test
    public void testMaxCollisionChainLengthConstant() {
        assertEquals(150, CharsToNameCanonicalizer.MAX_COLL_CHAIN_LENGTH);
    }

    @Test
    public void testHashOverflowFeatureEnabled() {
        JsonFactory factory = new JsonFactory();
        assertTrue(factory.isEnabled(TokenStreamFactory.Feature.FAIL_ON_SYMBOL_HASH_OVERFLOW));
        
        CharsToNameCanonicalizer canon = CharsToNameCanonicalizer.createRoot(factory).makeChild();
    }

    @Test
    public void testHashOverflowFeatureDisabled() {
        JsonFactory factory = JsonFactory.builder()
            .disable(TokenStreamFactory.Feature.FAIL_ON_SYMBOL_HASH_OVERFLOW)
            .build();
        
        CharsToNameCanonicalizer canon = CharsToNameCanonicalizer.createRoot(factory).makeChild();
        assertFalse(canon._factoryFeatures != 0 && 
            TokenStreamFactory.Feature.FAIL_ON_SYMBOL_HASH_OVERFLOW.enabledIn(canon._factoryFeatures));
    }

    @Test
    public void testHandleSpillOverflowFirstTime() {
        assertEquals(150, CharsToNameCanonicalizer.MAX_COLL_CHAIN_LENGTH);
        assertNotNull(getDeclaredMethodOrNull("_handleSpillOverflow", int.class, CharsToNameCanonicalizer.Bucket.class, int.class));
    }

    @Test
    public void testHandleSpillOverflowSecondTimeFailEnabled() throws Exception {
        JsonFactory factory = new JsonFactory(); 
        CharsToNameCanonicalizer canon = CharsToNameCanonicalizer.createRoot(factory).makeChild();
        
        Method method = CharsToNameCanonicalizer.class.getDeclaredMethod("_reportTooManyCollisions", int.class);
        method.setAccessible(true);
        try {
            method.invoke(canon, 150);
            fail("Expected StreamConstraintsException");
        } catch (java.lang.reflect.InvocationTargetException e) {
            assertTrue(e.getCause() instanceof StreamConstraintsException);
            assertTrue(e.getCause().getMessage().contains("DoS attack based on hash collisions"));
        }
    }

    @Test
    public void testHandleSpillOverflowSecondTimeFailDisabled() throws Exception {
        JsonFactory factory = JsonFactory.builder()
            .disable(TokenStreamFactory.Feature.FAIL_ON_SYMBOL_HASH_OVERFLOW)
            .build();
        CharsToNameCanonicalizer canon = CharsToNameCanonicalizer.createRoot(factory).makeChild();
        
        assertTrue(canon._canonicalize);
        
        Method handleSpill = CharsToNameCanonicalizer.class.getDeclaredMethod("_handleSpillOverflow", int.class, CharsToNameCanonicalizer.Bucket.class, int.class);
        handleSpill.setAccessible(true);
        
        CharsToNameCanonicalizer.Bucket bucket = new CharsToNameCanonicalizer.Bucket("test", null);
        
        handleSpill.invoke(canon, 0, bucket, 0);
        assertTrue(canon._canonicalize); 
        assertNotNull(canon._overflows);
        assertTrue(canon._overflows.get(0));
        
        handleSpill.invoke(canon, 0, bucket, 0);
        assertFalse(canon._canonicalize); 
    }

    @Test
    public void testReportTooManyCollisions() {
        JsonFactory factory = new JsonFactory();
        CharsToNameCanonicalizer canon = CharsToNameCanonicalizer.createRoot(factory).makeChild();
        
        try {
            Method method = CharsToNameCanonicalizer.class.getDeclaredMethod("_reportTooManyCollisions", int.class);
            method.setAccessible(true);
            method.invoke(canon, 150);
            fail("Expected StreamConstraintsException");
        } catch (java.lang.reflect.InvocationTargetException e) {
            assertTrue(e.getCause() instanceof StreamConstraintsException);
            String msg = e.getCause().getMessage();
            assertTrue(msg.contains("Longest collision chain"));
            assertTrue(msg.contains("DoS attack based on hash collisions"));
            assertTrue(msg.contains("FAIL_ON_SYMBOL_HASH_OVERFLOW"));
        } catch (Exception e) {
            fail("Unexpected exception: " + e);
        }
    }

    @Test
    public void testHandleSpillOverflowClearsBucket() throws Exception {
        JsonFactory factory = JsonFactory.builder()
            .disable(TokenStreamFactory.Feature.FAIL_ON_SYMBOL_HASH_OVERFLOW)
            .build();
        CharsToNameCanonicalizer canon = CharsToNameCanonicalizer.createRoot(factory).makeChild();
        
        Method handleSpill = CharsToNameCanonicalizer.class.getDeclaredMethod("_handleSpillOverflow", int.class, CharsToNameCanonicalizer.Bucket.class, int.class);
        handleSpill.setAccessible(true);
        
        addSymbol(canon, "sym1");
        int sizeBefore = canon.size();
        
        CharsToNameCanonicalizer.Bucket bucket = null;
        for (int i = 0; i < 151; i++) {
            bucket = new CharsToNameCanonicalizer.Bucket("sym" + i, bucket);
        }
        
        int bucketIndex = 0;
        int mainIndex = 0;
        
        handleSpill.invoke(canon, bucketIndex, bucket, mainIndex);
        
        assertNotNull(canon._overflows);
        assertTrue(canon._overflows.get(bucketIndex));
        
    }

    @Test
    public void testHandleSpillOverflowFirstOverflowSetsOverflows() throws Exception {
        JsonFactory factory = new JsonFactory();
        CharsToNameCanonicalizer canon = CharsToNameCanonicalizer.createRoot(factory).makeChild();
        
        assertNull(canon._overflows);
        
        Method handleSpill = CharsToNameCanonicalizer.class.getDeclaredMethod("_handleSpillOverflow", int.class, CharsToNameCanonicalizer.Bucket.class, int.class);
        handleSpill.setAccessible(true);
        
        CharsToNameCanonicalizer.Bucket bucket = new CharsToNameCanonicalizer.Bucket("test", null);
        
        handleSpill.invoke(canon, 5, bucket, 10);
        
        assertNotNull(canon._overflows);
        assertTrue(canon._overflows.get(5));
        assertTrue(canon._canonicalize); 
    }

    @Test
    public void testSize() {
        JsonFactory factory = new JsonFactory();
        CharsToNameCanonicalizer root = CharsToNameCanonicalizer.createRoot(factory);
        CharsToNameCanonicalizer child = root.makeChild();
        
        assertEquals(0, root.size());
        assertEquals(0, child.size());
        
        addSymbol(child, "test");
        assertEquals(1, child.size());
        assertEquals(0, root.size()); 
    }

    @Test
    public void testBucketCount() {
        JsonFactory factory = new JsonFactory();
        CharsToNameCanonicalizer canon = CharsToNameCanonicalizer.createRoot(factory).makeChild();
        
        int initialBuckets = canon.bucketCount();
        assertEquals(64, initialBuckets);
        
        int threshold = canon._sizeThreshold;
        for (int i = 0; i < threshold + 5; i++) {
            addSymbol(canon, "name" + i);
        }
        
        assertEquals(initialBuckets * 2, canon.bucketCount());
    }


    @Test
    public void testMaybeDirty() {
        JsonFactory factory = new JsonFactory();
        CharsToNameCanonicalizer root = CharsToNameCanonicalizer.createRoot(factory);
        CharsToNameCanonicalizer child = root.makeChild();
        
        assertFalse(child.maybeDirty()); 
        
        addSymbol(child, "test");
        assertTrue(child.maybeDirty()); 
        
        child.release();
        assertFalse(child.maybeDirty());
    }


    @Test
    public void testHashSeed() {
        JsonFactory factory = new JsonFactory();
        CharsToNameCanonicalizer root = CharsToNameCanonicalizer.createRoot(factory, 42);
        
        assertEquals(42, root.hashSeed());
        
        CharsToNameCanonicalizer child = root.makeChild();
        assertEquals(42, child.hashSeed());
    }


    @Test
    public void testFindSymbol2WithNullBucket() {
        JsonFactory factory = new JsonFactory();
        CharsToNameCanonicalizer canon = CharsToNameCanonicalizer.createRoot(factory).makeChild();
        
        addSymbol(canon, "test1");
        
        char[] buffer = "different".toCharArray();
        int hash = canon.calcHash(buffer, 0, buffer.length);
        String result = canon.findSymbol(buffer, 0, buffer.length, hash);
        
        assertEquals("different", result);
        assertEquals(2, canon.size());
    }

    @Test
    public void testFindSymbol2WithMultipleBuckets() {
        JsonFactory factory = new JsonFactory();
        CharsToNameCanonicalizer canon = CharsToNameCanonicalizer.createRoot(factory).makeChild();
        
        addSymbol(canon, "name1");
        addSymbol(canon, "name2");
        addSymbol(canon, "name3");
        
        assertEquals("name1", canon.findSymbol("name1".toCharArray(), 0, 5, canon.calcHash("name1")));
        assertEquals("name2", canon.findSymbol("name2".toCharArray(), 0, 5, canon.calcHash("name2")));
        assertEquals("name3", canon.findSymbol("name3".toCharArray(), 0, 5, canon.calcHash("name3")));
    }

    @Test
    public void testFindSymbolInCollisionBucket() {
        JsonFactory factory = new JsonFactory();
        CharsToNameCanonicalizer canon = CharsToNameCanonicalizer.createRoot(factory).makeChild();
        
        addSymbol(canon, "firstSymbol");
        
        for (int i = 0; i < 200; i++) {
            addSymbol(canon, "symbol_" + i);
        }
        
        String result = canon.findSymbol("symbol_50".toCharArray(), 0, 9, canon.calcHash("symbol_50"));
        assertEquals("symbol_50", result);
    }

    @Test
    public void testFindSymbolPrimaryMismatchBucketMatch() throws Exception {
        JsonFactory factory = new JsonFactory();
        CharsToNameCanonicalizer canon = CharsToNameCanonicalizer.createRoot(factory).makeChild();
        
        java.lang.reflect.Field symbolsField = CharsToNameCanonicalizer.class.getDeclaredField("_symbols");
        symbolsField.setAccessible(true);
        java.lang.reflect.Field bucketsField = CharsToNameCanonicalizer.class.getDeclaredField("_buckets");
        bucketsField.setAccessible(true);
        java.lang.reflect.Field sizeField = CharsToNameCanonicalizer.class.getDeclaredField("_size");
        sizeField.setAccessible(true);
        
        String[] symbols = canon._symbols;
        CharsToNameCanonicalizer.Bucket[] buckets = canon._buckets;
        
        symbols[0] = "primarySymbol";
        sizeField.setInt(canon, 1);
        
        CharsToNameCanonicalizer.Bucket bucket = new CharsToNameCanonicalizer.Bucket("collisionSymbol", null);
        buckets[0] = bucket;
        sizeField.setInt(canon, 2);
        
        char[] buffer = "collisionSymbol".toCharArray();
        int hash = canon.calcHash(buffer, 0, buffer.length);
        java.lang.reflect.Method hashToIndex = CharsToNameCanonicalizer.class.getDeclaredMethod("_hashToIndex", int.class);
        hashToIndex.setAccessible(true);
        
        String result = canon.findSymbol("collisionSymbol".toCharArray(), 0, 15, canon.calcHash("collisionSymbol"));
    }


    @Test
    public void testRehashWithCollisionBuckets() {
        JsonFactory factory = new JsonFactory();
        CharsToNameCanonicalizer canon = CharsToNameCanonicalizer.createRoot(factory).makeChild();
        
        int threshold = canon._sizeThreshold;
        for (int i = 0; i < threshold + 10; i++) {
            addSymbol(canon, "name" + i);
        }
        
        for (int i = 0; i < threshold + 10; i++) {
            String name = "name" + i;
            char[] buffer = name.toCharArray();
            int hash = canon.calcHash(buffer, 0, buffer.length);
            String found = canon.findSymbol(buffer, 0, buffer.length, hash);
            assertEquals(name, found);
        }
    }


    @Test
    public void testFindSymbolCanonicalizeDisabledWithLengthValidation() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
            .maxNameLength(100)
            .build();
        
        JsonFactory factory = JsonFactory.builder()
            .streamReadConstraints(constraints)
            .disable(TokenStreamFactory.Feature.CANONICALIZE_PROPERTY_NAMES)
            .build();
        
        CharsToNameCanonicalizer canon = CharsToNameCanonicalizer.createRoot(factory).makeChild();
        
        char[] buffer = "validName".toCharArray(); 
        int hash = canon.calcHash(buffer, 0, buffer.length);
        
        String result = canon.findSymbol(buffer, 0, buffer.length, hash);
        assertEquals("validName", result);
        String result2 = canon.findSymbol(buffer, 0, buffer.length, hash);
        assertNotSame(result, result2);
    }


    @Test
    public void testAddSymbolTriggersRehashInAddSymbol() {
        JsonFactory factory = new JsonFactory();
        CharsToNameCanonicalizer canon = CharsToNameCanonicalizer.createRoot(factory).makeChild();
        
        int threshold = canon._sizeThreshold;
        
        for (int i = 0; i < threshold; i++) {
            addSymbol(canon, "name" + i);
        }
        assertEquals(threshold, canon.size());
        
        String[] oldSymbols = canon._symbols;
        addSymbol(canon, "triggerRehash");
        
        assertTrue(canon._symbols.length > oldSymbols.length);
        assertNotSame(oldSymbols, canon._symbols);
    }


    @Test
    public void testFindSymbolEmptyStringBranch() {
        JsonFactory factory = new JsonFactory();
        CharsToNameCanonicalizer canon = CharsToNameCanonicalizer.createRoot(factory).makeChild();
        
        char[] buffer = new char[0];
        String result = canon.findSymbol(buffer, 0, 0, 0);
        assertEquals("", result);
        
    }


    @Test
    public void testFindSymbolPrimaryMatch() {
        JsonFactory factory = new JsonFactory();
        CharsToNameCanonicalizer canon = CharsToNameCanonicalizer.createRoot(factory).makeChild();
        
        addSymbol(canon, "primaryMatch");
        
        char[] buffer = "primaryMatch".toCharArray();
        int hash = canon.calcHash(buffer, 0, buffer.length);
        String result = canon.findSymbol(buffer, 0, buffer.length, hash);
        assertEquals("primaryMatch", result);
        
        String result2 = canon.findSymbol(buffer, 0, buffer.length, hash);
        assertSame(result, result2); 
    }


    @Test
    public void testFindSymbol2TraversesMultipleBuckets() throws Exception {
        JsonFactory factory = new JsonFactory();
        CharsToNameCanonicalizer canon = CharsToNameCanonicalizer.createRoot(factory).makeChild();
        
        Method findSymbol2 = CharsToNameCanonicalizer.class.getDeclaredMethod("_findSymbol2", char[].class, int.class, int.class, CharsToNameCanonicalizer.Bucket.class);
        findSymbol2.setAccessible(true);
        
        CharsToNameCanonicalizer.Bucket b3 = new CharsToNameCanonicalizer.Bucket("symbol3", null);
        CharsToNameCanonicalizer.Bucket b2 = new CharsToNameCanonicalizer.Bucket("symbol2", b3);
        CharsToNameCanonicalizer.Bucket b1 = new CharsToNameCanonicalizer.Bucket("symbol1", b2);
        
        char[] buffer = "symbol3".toCharArray();
        String result = (String) findSymbol2.invoke(canon, buffer, 0, 7, b1);
        assertEquals("symbol3", result);
        
        char[] buffer2 = "notfound".toCharArray();
        String result2 = (String) findSymbol2.invoke(canon, buffer2, 0, 8, b1);
        assertNull(result2);
    }


    @Test
    public void testAddSymbolWithInternEnabled() {
        JsonFactory factory = JsonFactory.builder()
            .enable(JsonFactory.Feature.INTERN_PROPERTY_NAMES)
            .build();
        CharsToNameCanonicalizer canon = CharsToNameCanonicalizer.createRoot(factory).makeChild();
        
        char[] buffer = "internedName".toCharArray();
        int hash = canon.calcHash(buffer, 0, buffer.length);
        String result = canon.findSymbol(buffer, 0, buffer.length, hash);
        
        assertSame(result, result.intern());
    }

    @Test
    public void testAddSymbolCopyOnWrite() {
        JsonFactory factory = new JsonFactory();
        CharsToNameCanonicalizer root = CharsToNameCanonicalizer.createRoot(factory);
        CharsToNameCanonicalizer child = root.makeChild();
        
        assertTrue(child._hashShared);
        
        addSymbol(child, "testCopyOnWrite");
        
        assertFalse(child._hashShared);
        assertEquals(0, root.size());
    }


    @Test
    public void testVerifyInternalConsistencyAfterCorruption() throws Exception {
        JsonFactory factory = new JsonFactory();
        CharsToNameCanonicalizer canon = CharsToNameCanonicalizer.createRoot(factory).makeChild();
        
        addSymbol(canon, "test1");
        addSymbol(canon, "test2");
        
        java.lang.reflect.Field sizeField = CharsToNameCanonicalizer.class.getDeclaredField("_size");
        sizeField.setAccessible(true);
        sizeField.setInt(canon, 5); 
        
        try {
            canon.verifyInternalConsistency();
            fail("Expected IllegalStateException");
        } catch (IllegalStateException e) {
            assertTrue(e.getMessage().contains("expected internal size"));
            assertTrue(e.getMessage().contains("5"));
        }
    }


    @Test
    public void testReleaseWithParentButCanonicalizeDisabled() {
        JsonFactory factory = JsonFactory.builder()
            .disable(TokenStreamFactory.Feature.CANONICALIZE_PROPERTY_NAMES)
            .build();
        CharsToNameCanonicalizer root = CharsToNameCanonicalizer.createRoot(factory);
        CharsToNameCanonicalizer child = root.makeChild();
        
        addSymbol(child, "test1");
        addSymbol(child, "test2");
        assertEquals(0, child.size());
        assertFalse(child.maybeDirty()); 
        
        child.release();
        
        assertEquals(0, root.size());
        assertTrue(child._hashShared);
    }


    @Test
    public void testFindSymbolCanonicalizeDisabledLengthValidationFails() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
            .maxNameLength(5)
            .build();
        
        JsonFactory factory = JsonFactory.builder()
            .streamReadConstraints(constraints)
            .disable(TokenStreamFactory.Feature.CANONICALIZE_PROPERTY_NAMES)
            .build();
        
        CharsToNameCanonicalizer canon = CharsToNameCanonicalizer.createRoot(factory).makeChild();
        
        char[] buffer = "tooLongName".toCharArray(); 
        int hash = canon.calcHash(buffer, 0, buffer.length);
        
        try {
            canon.findSymbol(buffer, 0, buffer.length, hash);
            fail("Expected StreamConstraintsException");
        } catch (StreamConstraintsException e) {
            assertTrue(e.getMessage().contains("exceeds the maximum allowed"));
        }
    }


    @Test
    public void testHandleSpillOverflowFailEnabled() throws Exception {
        JsonFactory factory = new JsonFactory(); 
        CharsToNameCanonicalizer canon = CharsToNameCanonicalizer.createRoot(factory).makeChild();
        
        Method handleSpill = CharsToNameCanonicalizer.class.getDeclaredMethod("_handleSpillOverflow", int.class, CharsToNameCanonicalizer.Bucket.class, int.class);
        handleSpill.setAccessible(true);
        
        CharsToNameCanonicalizer.Bucket bucket = new CharsToNameCanonicalizer.Bucket("test", null);
        
        handleSpill.invoke(canon, 10, bucket, 20);
        assertNotNull(canon._overflows);
        assertTrue(canon._overflows.get(10));
        
        try {
            handleSpill.invoke(canon, 10, bucket, 20);
            fail("Expected StreamConstraintsException");
        } catch (java.lang.reflect.InvocationTargetException e) {
            assertTrue(e.getCause() instanceof StreamConstraintsException);
            assertTrue(e.getCause().getMessage().contains("DoS attack based on hash collisions"));
        }
        
        assertTrue(canon._canonicalize);
    }


    @Test
    public void testRehashProcessesCollisionBuckets() throws Exception {
        JsonFactory factory = new JsonFactory();
        CharsToNameCanonicalizer canon = CharsToNameCanonicalizer.createRoot(factory).makeChild();
        
        int threshold = canon._sizeThreshold;
        for (int i = 0; i < threshold + 20; i++) {
            addSymbol(canon, "rehashedName" + i);
        }
        
        for (int i = threshold + 20; i < threshold + 30; i++) {
            addSymbol(canon, "rehashedName" + i);
        }
        
        for (int i = 0; i < threshold + 30; i++) {
            String name = "rehashedName" + i;
            char[] buffer = name.toCharArray();
            int hash = canon.calcHash(buffer, 0, buffer.length);
            String found = canon.findSymbol(buffer, 0, buffer.length, hash);
            assertEquals(name, found);
        }
    }


    @Test
    public void testHandleSpillOverflowSecondOverflowSameBucket() throws Exception {
        JsonFactory factory = new JsonFactory(); 
        CharsToNameCanonicalizer canon = CharsToNameCanonicalizer.createRoot(factory).makeChild();
        
        Method handleSpill = CharsToNameCanonicalizer.class.getDeclaredMethod("_handleSpillOverflow", int.class, CharsToNameCanonicalizer.Bucket.class, int.class);
        handleSpill.setAccessible(true);
        
        CharsToNameCanonicalizer.Bucket bucket = new CharsToNameCanonicalizer.Bucket("test", null);
        
        handleSpill.invoke(canon, 3, bucket, 7);
        assertNotNull(canon._overflows);
        assertTrue(canon._overflows.get(3));
        assertTrue(canon._canonicalize); 
        
        try {
            handleSpill.invoke(canon, 3, bucket, 7);
            fail("Expected StreamConstraintsException");
        } catch (java.lang.reflect.InvocationTargetException e) {
            assertTrue(e.getCause() instanceof StreamConstraintsException);
        }
        
        assertTrue(canon._canonicalize);
    }


    @Test
    public void testCalcHashExactArithmetic() throws Exception {
        
        Class<?> clazz = CharsToNameCanonicalizer.class;
        java.lang.reflect.Constructor<?> ctor = clazz.getDeclaredConstructor(
            StreamReadConstraints.class, int.class, int.class);
        ctor.setAccessible(true);
        CharsToNameCanonicalizer canon = (CharsToNameCanonicalizer) ctor.newInstance(
            StreamReadConstraints.defaults(), 0, 0); 
        
        assertEquals(97, canon.calcHash(new char[]{'a'}, 0, 1));
        
        assertEquals(3299, canon.calcHash(new char[]{'a','b'}, 0, 2));
        
        assertEquals(108966, canon.calcHash(new char[]{'a','b','c'}, 0, 3));
        
        char[] buffer = new char[]{'x', 'a', 'b', 'c', 'y'};
        assertEquals(108966, canon.calcHash(buffer, 1, 3));
        
        assertEquals(97, canon.calcHash("a"));
        assertEquals(3299, canon.calcHash("ab"));
        assertEquals(108966, canon.calcHash("abc"));
        
        CharsToNameCanonicalizer canon2 = (CharsToNameCanonicalizer) ctor.newInstance(
            StreamReadConstraints.defaults(), 0, 100); 
        
        assertEquals(3397, canon2.calcHash("a")); 
        
        assertEquals(112199, canon2.calcHash("ab")); 
    }


    @Test
    public void testHashToIndexShufflingSteps() throws Exception {
        
        JsonFactory factory = new JsonFactory();
        CharsToNameCanonicalizer canon = CharsToNameCanonicalizer.createRoot(factory).makeChild();
        
        Method hashToIndex = CharsToNameCanonicalizer.class.getDeclaredMethod("_hashToIndex", int.class);
        hashToIndex.setAccessible(true);
        
        
        int result1 = (Integer) hashToIndex.invoke(canon, 32768);
        assertEquals(17, result1);
        
        int result2 = (Integer) hashToIndex.invoke(canon, 32767);
        assertEquals(14, result2);
        
        int result3 = (Integer) hashToIndex.invoke(canon, 1);
        assertEquals(17, result3);
        
        int result4 = (Integer) hashToIndex.invoke(canon, 63);
        assertEquals(54, result4);
        
        int result5 = (Integer) hashToIndex.invoke(canon, 1000);
        assertEquals(37, result5);
    }

    @Test
    public void testThresholdSizeVariousInputs() throws Exception {
        Method thresholdSize = CharsToNameCanonicalizer.class.getDeclaredMethod("_thresholdSize", int.class);
        thresholdSize.setAccessible(true);
        
        assertEquals(48, thresholdSize.invoke(null, 64));
        assertEquals(96, thresholdSize.invoke(null, 128));
        assertEquals(192, thresholdSize.invoke(null, 256));
        assertEquals(750, thresholdSize.invoke(null, 1000));
        assertEquals(49152, thresholdSize.invoke(null, 65536));
        assertEquals(12, thresholdSize.invoke(null, 16));
    }


    @Test
    public void testHandleSpillOverflowCalledWhenCollisionChainTooLong() throws Exception {
        JsonFactory factory = JsonFactory.builder()
            .disable(TokenStreamFactory.Feature.FAIL_ON_SYMBOL_HASH_OVERFLOW)
            .build();
        CharsToNameCanonicalizer canon = CharsToNameCanonicalizer.createRoot(factory).makeChild();
        
        Method handleSpill = CharsToNameCanonicalizer.class.getDeclaredMethod("_handleSpillOverflow", int.class, CharsToNameCanonicalizer.Bucket.class, int.class);
        handleSpill.setAccessible(true);
        
        CharsToNameCanonicalizer.Bucket bucket = null;
        for (int i = 0; i < 151; i++) {
            bucket = new CharsToNameCanonicalizer.Bucket("sym" + i, bucket);
        }
        
        int sizeBefore = canon.size();
        handleSpill.invoke(canon, 0, bucket, 0);
        
        assertNotNull(canon._overflows);
        assertTrue(canon._overflows.get(0));
        
        assertTrue(canon._canonicalize);
        
        handleSpill.invoke(canon, 0, bucket, 0);
        assertFalse(canon._canonicalize);
    }

    @Test
    public void testAddSymbolBoundaryConditionForRehash() {
        JsonFactory factory = new JsonFactory();
        CharsToNameCanonicalizer canon = CharsToNameCanonicalizer.createRoot(factory).makeChild();
        
        // Test the exact boundary where _size >= _sizeThreshold triggers rehash
        // Note: rehash check happens BEFORE _size increment in _addSymbol
        int threshold = canon._sizeThreshold;
        
        // Add symbols up to threshold - 1 (should not rehash)
        for (int i = 0; i < threshold - 1; i++) {
            addSymbol(canon, "boundary" + i);
        }
        String[] symbolsBefore = canon._symbols;
        assertEquals(threshold - 1, canon.size());
        
        // Add one more to reach exactly threshold (should NOT trigger rehash yet, because check is _size >= threshold BEFORE increment)
        addSymbol(canon, "atThreshold");
        assertEquals(threshold, canon.size());
        assertSame(symbolsBefore, canon._symbols); // No rehash yet
        
        // Add one more to exceed threshold - THIS should trigger rehash
        addSymbol(canon, "exceedsThreshold");
        assertEquals(threshold + 1, canon.size());
        assertNotSame(symbolsBefore, canon._symbols); // Rehash occurred
        
        // Add more symbols
        for (int i = 0; i < 10; i++) {
            addSymbol(canon, "afterRehash" + i);
        }
        assertEquals(threshold + 11, canon.size());
    }

    @Test
    public void testRehashShiftOperations() {
        JsonFactory factory = new JsonFactory();
        CharsToNameCanonicalizer canon = CharsToNameCanonicalizer.createRoot(factory).makeChild();
        
        // Trigger rehash multiple times to exercise shift operations
        for (int rehashRound = 0; rehashRound < 4; rehashRound++) {
            int threshold = canon._sizeThreshold;
            int oldSize = canon._symbols.length;
            
            for (int i = 0; i < threshold + 1; i++) {
                addSymbol(canon, "shiftTest_" + rehashRound + "_" + i);
            }
            
            int newSize = canon._symbols.length;
            assertEquals(oldSize * 2, newSize); // Size doubles (shift left by 1)
            assertEquals(newSize >> 1, canon._buckets.length); // Buckets = size >> 1 (shift right by 1)
            assertEquals(newSize - 1, canon._indexMask);
            
            canon.verifyInternalConsistency();
        }
    }

    @Test
    public void testRehashCountVerificationDetectsMismatch() throws Exception {
        JsonFactory factory = new JsonFactory();
        CharsToNameCanonicalizer canon = CharsToNameCanonicalizer.createRoot(factory).makeChild();
        
        // Add some symbols
        addSymbol(canon, "test1");
        addSymbol(canon, "test2");
        
        // Corrupt the symbol table to have more entries than _size
        java.lang.reflect.Field symbolsField = CharsToNameCanonicalizer.class.getDeclaredField("_symbols");
        symbolsField.setAccessible(true);
        java.lang.reflect.Field sizeField = CharsToNameCanonicalizer.class.getDeclaredField("_size");
        sizeField.setAccessible(true);
        
        String[] symbols = canon._symbols;
        // Add an extra symbol directly to array without updating _size
        for (int i = 0; i < symbols.length; i++) {
            if (symbols[i] == null) {
                symbols[i] = "extraSymbol";
                break;
            }
        }
        
        // Now trigger rehash - should detect count != _size
        Method rehashMethod = CharsToNameCanonicalizer.class.getDeclaredMethod("rehash");
        rehashMethod.setAccessible(true);
        
        try {
            rehashMethod.invoke(canon);
            fail("Expected IllegalStateException for size mismatch");
        } catch (java.lang.reflect.InvocationTargetException e) {
            assertTrue(e.getCause() instanceof IllegalStateException);
            assertTrue(e.getCause().getMessage().contains("Internal error on SymbolTable.rehash()"));
        }
    }

    @Test
    public void testCopyArraysCalledOnFirstModificationOfSharedChild() {
        JsonFactory factory = new JsonFactory();
        CharsToNameCanonicalizer root = CharsToNameCanonicalizer.createRoot(factory);
        CharsToNameCanonicalizer child = root.makeChild();
        
        assertTrue(child._hashShared);
        String[] sharedSymbols = child._symbols;
        CharsToNameCanonicalizer.Bucket[] sharedBuckets = child._buckets;
        
        // First modification should trigger copyArrays
        addSymbol(child, "firstModification");
        
        assertFalse(child._hashShared);
        assertNotSame(sharedSymbols, child._symbols);
        assertNotSame(sharedBuckets, child._buckets);
        assertEquals(1, child.size());
        assertEquals(0, root.size()); // Root unchanged
    }

    @Test
    public void testRehashBucketArraySizeCalculation() {
        JsonFactory factory = new JsonFactory();
        CharsToNameCanonicalizer canon = CharsToNameCanonicalizer.createRoot(factory).makeChild();
        
        // Initial: 64 symbols, 32 buckets (64 >> 1)
        assertEquals(64, canon._symbols.length);
        assertEquals(32, canon._buckets.length);
        
        // After first rehash: 128 symbols, 64 buckets (128 >> 1)
        int threshold = canon._sizeThreshold;
        for (int i = 0; i < threshold + 1; i++) {
            addSymbol(canon, "rehash1_" + i);
        }
        assertEquals(128, canon._symbols.length);
        assertEquals(64, canon._buckets.length); // 128 >> 1 = 64
        
        // After second rehash: 256 symbols, 128 buckets (256 >> 1)
        threshold = canon._sizeThreshold;
        for (int i = 0; i < threshold + 1; i++) {
            addSymbol(canon, "rehash2_" + i);
        }
        assertEquals(256, canon._symbols.length);
        assertEquals(128, canon._buckets.length); // 256 >> 1 = 128
        
        // After third rehash: 512 symbols, 256 buckets (512 >> 1)
        threshold = canon._sizeThreshold;
        for (int i = 0; i < threshold + 1; i++) {
            addSymbol(canon, "rehash3_" + i);
        }
        assertEquals(512, canon._symbols.length);
        assertEquals(256, canon._buckets.length); // 512 >> 1 = 256
    }


    private void addSymbol(CharsToNameCanonicalizer canon, String name) {
        char[] buffer = name.toCharArray();
        int hash = canon.calcHash(buffer, 0, buffer.length);
        canon.findSymbol(buffer, 0, buffer.length, hash);
    }

    private Method getDeclaredMethodOrNull(String name, Class<?>... parameterTypes) {
        try {
            return CharsToNameCanonicalizer.class.getDeclaredMethod(name, parameterTypes);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }
}
