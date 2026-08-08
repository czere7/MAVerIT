package tools.jackson.core.json;

import static org.junit.Assert.*;

import java.io.OutputStream;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Before;
import org.junit.Test;

import tools.jackson.core.*;
import tools.jackson.core.exc.StreamReadException;
import tools.jackson.core.io.ContentReference;
import tools.jackson.core.json.DupDetector;
import tools.jackson.core.sym.PropertyNameMatcher;
import tools.jackson.core.type.ResolvedType;
import tools.jackson.core.type.TypeReference;
import tools.jackson.core.util.JacksonFeatureSet;

public class JsonReadContextTest {

    private JsonReadContext rootContext;
    private MinimalJsonParser minimalParser;
    private DupDetector realDupDetector;

    // Minimal JsonParser implementation for creating DupDetector instances
    private static class MinimalJsonParser extends JsonParser {
        protected MinimalJsonParser() { super(); }
        @Override public Version version() { return Version.unknownVersion(); }
        @Override public TokenStreamContext streamReadContext() { return null; }
        @Override public ObjectReadContext objectReadContext() { return null; }
        @Override public TokenStreamLocation currentTokenLocation() { return null; }
        @Override public TokenStreamLocation currentLocation() { return null; }
        @Override public long currentTokenCount() { return 0; }
        @Override public Object streamReadInputSource() { return null; }
        @Override public boolean isEnabled(StreamReadFeature f) { return false; }
        @Override public int streamReadFeatures() { return 0; }
        @Override public JacksonFeatureSet<StreamReadCapability> streamReadCapabilities() { return null; }
        @Override public StreamReadConstraints streamReadConstraints() { return null; }
        @Override public JsonToken nextToken() { return null; }
        @Override public JsonToken nextValue() { return null; }
        @Override public JsonParser skipChildren() { return this; }
        @Override public void finishToken() { }
        @Override public String nextName() { return null; }
        @Override public boolean nextName(SerializableString str) { return false; }
        @Override public int nextNameMatch(PropertyNameMatcher matcher) { return 0; }
        @Override public int currentNameMatch(PropertyNameMatcher matcher) { return 0; }
        @Override public JsonToken currentToken() { return null; }
        @Override public int currentTokenId() { return 0; }
        @Override public boolean hasCurrentToken() { return false; }
        @Override public boolean hasTokenId(int id) { return false; }
        @Override public boolean hasToken(JsonToken t) { return false; }
        @Override public boolean isExpectedStartArrayToken() { return false; }
        @Override public boolean isExpectedStartObjectToken() { return false; }
        @Override public boolean isExpectedNumberIntToken() { return false; }
        @Override public boolean isNaN() { return false; }
        @Override public void clearCurrentToken() { }
        @Override public JsonToken getLastClearedToken() { return null; }
        @Override public String currentName() { return null; }
        @Override public String getString() { return null; }
        @Override public int getString(Writer writer) { return 0; }
        @Override public char[] getStringCharacters() { return null; }
        @Override public int getStringLength() { return 0; }
        @Override public int getStringOffset() { return 0; }
        @Override public boolean hasStringCharacters() { return false; }
        @Override public Number getNumberValue() { return null; }
        @Override public Number getNumberValueExact() { return null; }
        @Override public Object getNumberValueDeferred() { return null; }
        @Override public NumberType getNumberType() { return null; }
        @Override public NumberTypeFP getNumberTypeFP() { return NumberTypeFP.UNKNOWN; }
        @Override public byte getByteValue() { return 0; }
        @Override public short getShortValue() { return 0; }
        @Override public int getIntValue() { return 0; }
        @Override public long getLongValue() { return 0L; }
        @Override public BigInteger getBigIntegerValue() { return null; }
        @Override public float getFloatValue() { return 0f; }
        @Override public double getDoubleValue() { return 0.0; }
        @Override public BigDecimal getDecimalValue() { return null; }
        @Override public boolean getBooleanValue() { return false; }
        @Override public Object getEmbeddedObject() { return null; }
        @Override public byte[] getBinaryValue(Base64Variant bv) { return null; }
        @Override public int readBinaryValue(Base64Variant bv, OutputStream out) { return 0; }
        @Override public <T> T readValueAs(Class<T> valueType) { return null; }
        @Override public <T> T readValueAs(TypeReference<T> valueTypeRef) { return null; }
        @Override public <T> T readValueAs(ResolvedType type) { return null; }
        @Override public <T extends TreeNode> T readValueAsTree() { return null; }
        @Override public void close() { }
        @Override public boolean isClosed() { return false; }
        @Override public String getValueAsString(String def) { return def; }
        @Override public boolean getValueAsBoolean(boolean def) { return def; }
        @Override public void assignCurrentValue(Object v) { }
        @Override public Object currentValue() { return null; }
    }

    @Before
    public void setUp() {
        minimalParser = new MinimalJsonParser();
        realDupDetector = DupDetector.rootDetector(minimalParser);
        rootContext = JsonReadContext.createRootContext(1, 0, realDupDetector);
    }

    @Test
    public void testCreateRootContextWithLineAndColumn() {
        JsonReadContext ctx = JsonReadContext.createRootContext(5, 10, null);
        assertNotNull(ctx);
        assertNull(ctx.getParent());
        assertTrue(ctx.inRoot());
        assertEquals(0, ctx.getNestingDepth());
        assertEquals(0, ctx.getCurrentIndex());
    }

    @Test
    public void testCreateRootContextWithDefaults() {
        JsonReadContext ctx = JsonReadContext.createRootContext(realDupDetector);
        assertNotNull(ctx);
        assertNull(ctx.getParent());
        assertTrue(ctx.inRoot());
    }

    @Test
    public void testCreateChildArrayContext() {
        JsonReadContext child = rootContext.createChildArrayContext(10, 5);
        assertNotNull(child);
        assertSame(rootContext, child.getParent());
        assertTrue(child.inArray());
        assertEquals(1, child.getNestingDepth());
        assertEquals(10, child._lineNr);
        assertEquals(5, child._columnNr);
        assertEquals(0, child.getCurrentIndex());
    }

    @Test
    public void testCreateChildObjectContext() {
        JsonReadContext child = rootContext.createChildObjectContext(20, 15);
        assertNotNull(child);
        assertSame(rootContext, child.getParent());
        assertTrue(child.inObject());
        assertEquals(1, child.getNestingDepth());
        assertEquals(20, child._lineNr);
        assertEquals(15, child._columnNr);
    }

    @Test
    public void testChildContextReuse() {
        JsonReadContext child1 = rootContext.createChildArrayContext(10, 5);
        JsonReadContext child2 = rootContext.createChildArrayContext(20, 10);
        assertSame(child1, child2);
        assertTrue(child2.inArray());
        assertEquals(20, child2._lineNr);
        assertEquals(10, child2._columnNr);
        assertEquals(0, child2.getCurrentIndex());
        assertNull(child2.currentName());
        assertNull(child2.currentValue());
    }

    @Test
    public void testChildObjectContextReuse() {
        JsonReadContext child1 = rootContext.createChildObjectContext(10, 5);
        JsonReadContext child2 = rootContext.createChildObjectContext(20, 10);
        assertSame(child1, child2);
        assertTrue(child2.inObject());
        assertEquals(20, child2._lineNr);
        assertEquals(10, child2._columnNr);
    }

    @Test
    public void testReset() {
        JsonReadContext child = rootContext.createChildArrayContext(10, 5);
        child.setCurrentName("testName");
        child.assignCurrentValue("testValue");
        child.expectComma();
        child.expectComma();

        JsonReadContext reset = child.reset(TokenStreamContext.TYPE_OBJECT, 30, 15);
        assertSame(child, reset);
        assertTrue(child.inObject());
        assertEquals(30, child._lineNr);
        assertEquals(15, child._columnNr);
        assertEquals(0, child.getCurrentIndex());
        assertNull(child.currentName());
        assertNull(child.currentValue());
        // Verify dup detector was reset by checking it can be used again
        child.setCurrentName("newName");
        assertEquals("newName", child.currentName());
    }

    @Test
    public void testResetWithoutDupDetector() {
        JsonReadContext ctx = JsonReadContext.createRootContext(1, 0, null);
        ctx.setCurrentName("name");
        ctx.assignCurrentValue("value");
        ctx.expectComma();

        JsonReadContext reset = ctx.reset(TokenStreamContext.TYPE_ARRAY, 5, 5);
        assertSame(ctx, reset);
        assertTrue(ctx.inArray());
        assertEquals(0, ctx.getCurrentIndex());
        assertNull(ctx.currentName());
        assertNull(ctx.currentValue());
    }

    @Test
    public void testWithDupDetector() {
        JsonReadContext ctx = JsonReadContext.createRootContext(1, 0, null);
        assertNull(ctx.getDupDetector());

        DupDetector newDetector = DupDetector.rootDetector(minimalParser);
        JsonReadContext result = ctx.withDupDetector(newDetector);
        assertSame(ctx, result);
        assertSame(newDetector, ctx.getDupDetector());
    }

    @Test
    public void testCurrentNameAndHasCurrentName() {
        assertNull(rootContext.currentName());
        assertFalse(rootContext.hasCurrentName());

        rootContext.setCurrentName("propertyName");
        assertEquals("propertyName", rootContext.currentName());
        assertTrue(rootContext.hasCurrentName());
    }

    @Test
    public void testSetCurrentNameWithoutDupDetector() {
        JsonReadContext ctx = JsonReadContext.createRootContext(1, 0, null);
        ctx.setCurrentName("testName");
        assertEquals("testName", ctx.currentName());
    }

    @Test
    public void testSetCurrentNameWithDupDetectorNoDuplicate() throws StreamReadException {
        JsonReadContext ctx = JsonReadContext.createRootContext(1, 0, DupDetector.rootDetector(minimalParser));
        ctx.setCurrentName("uniqueName");
        assertEquals("uniqueName", ctx.currentName());
    }

    @Test(expected = StreamReadException.class)
    public void testSetCurrentNameWithDuplicate() throws StreamReadException {
        DupDetector detector = DupDetector.rootDetector(minimalParser);
        detector.isDup("duplicateName"); // first call - not a duplicate
        detector.isDup("duplicateName"); // second call - duplicate
        
        JsonReadContext ctx = JsonReadContext.createRootContext(1, 0, detector);
        ctx.setCurrentName("duplicateName"); // first time - ok
        ctx.setCurrentName("duplicateName"); // second time - should throw
    }

    @Test
    public void testSetCurrentNameWithDuplicateAndParserSource() throws StreamReadException {
        DupDetector detectorWithParser = DupDetector.rootDetector(minimalParser);
        
        JsonReadContext ctx = JsonReadContext.createRootContext(1, 0, detectorWithParser);
        
        try {
            ctx.setCurrentName("duplicateName");
            ctx.setCurrentName("duplicateName");
            fail("Expected StreamReadException");
        } catch (StreamReadException e) {
            assertTrue(e.getMessage().contains("Duplicate Object property \"duplicateName\""));
        }
    }

    @Test
    public void testCurrentValue() {
        assertNull(rootContext.currentValue());
        
        Object testValue = new Object();
        rootContext.assignCurrentValue(testValue);
        assertSame(testValue, rootContext.currentValue());
        
        rootContext.assignCurrentValue(null);
        assertNull(rootContext.currentValue());
    }

    @Test
    public void testGetParent() {
        assertNull(rootContext.getParent());
        
        JsonReadContext child = rootContext.createChildArrayContext(10, 5);
        assertSame(rootContext, child.getParent());
        
        JsonReadContext grandChild = child.createChildObjectContext(15, 10);
        assertSame(child, grandChild.getParent());
    }

    @Test
    public void testClearAndGetParent() {
        rootContext.assignCurrentValue("testValue");
        rootContext.setCurrentName("testName");
        
        JsonReadContext parent = rootContext.clearAndGetParent();
        assertNull(parent);
        assertNull(rootContext.currentValue());
        assertEquals("testName", rootContext.currentName());
        
        JsonReadContext child = rootContext.createChildArrayContext(10, 5);
        child.assignCurrentValue("childValue");
        child.setCurrentName("childName");
        
        JsonReadContext returnedParent = child.clearAndGetParent();
        assertSame(rootContext, returnedParent);
        assertNull(child.currentValue());
        assertEquals("childName", child.currentName());
    }

    @Test
    public void testExpectCommaRootContext() {
        assertFalse(rootContext.expectComma());
        assertEquals(0, rootContext.getCurrentIndex());
        assertFalse(rootContext.expectComma());
        assertEquals(1, rootContext.getCurrentIndex());
    }

    @Test
    public void testExpectCommaArrayContext() {
        JsonReadContext arrayCtx = rootContext.createChildArrayContext(10, 5);
        
        assertFalse(arrayCtx.expectComma());
        assertEquals(0, arrayCtx.getCurrentIndex());
        
        assertTrue(arrayCtx.expectComma());
        assertEquals(1, arrayCtx.getCurrentIndex());
        
        assertTrue(arrayCtx.expectComma());
        assertEquals(2, arrayCtx.getCurrentIndex());
    }

    @Test
    public void testExpectCommaObjectContext() {
        JsonReadContext objectCtx = rootContext.createChildObjectContext(10, 5);
        
        assertFalse(objectCtx.expectComma());
        assertEquals(0, objectCtx.getCurrentIndex());
        
        assertTrue(objectCtx.expectComma());
        assertEquals(1, objectCtx.getCurrentIndex());
    }

    @Test
    public void testStartLocation() {
        ContentReference srcRef = ContentReference.rawReference("test-source");
        TokenStreamLocation location = rootContext.startLocation(srcRef);
        
        assertNotNull(location);
        assertEquals(1, location.getLineNr());
        assertEquals(0, location.getColumnNr());
    }

    @Test
    public void testStartLocationWithNullContentReference() {
        TokenStreamLocation location = rootContext.startLocation(null);
        assertNotNull(location);
        assertEquals(1, location.getLineNr());
        assertEquals(0, location.getColumnNr());
    }

    @Test
    public void testChildContextStartLocation() {
        JsonReadContext child = rootContext.createChildArrayContext(25, 10);
        ContentReference srcRef = ContentReference.rawReference("test-source");
        TokenStreamLocation location = child.startLocation(srcRef);
        
        assertEquals(25, location.getLineNr());
        assertEquals(10, location.getColumnNr());
    }

    @Test
    public void testGetDupDetector() {
        assertSame(realDupDetector, rootContext.getDupDetector());
        
        JsonReadContext noDupCtx = JsonReadContext.createRootContext(1, 0, null);
        assertNull(noDupCtx.getDupDetector());
        
        JsonReadContext child = rootContext.createChildArrayContext(10, 5);
        assertNotNull(child.getDupDetector());
        // Child detector should be a different instance but with same source
        assertSame(minimalParser, child.getDupDetector().getSource());
    }

    @Test
    public void testNestingDepth() {
        assertEquals(0, rootContext.getNestingDepth());
        
        JsonReadContext child1 = rootContext.createChildArrayContext(10, 5);
        assertEquals(1, child1.getNestingDepth());
        
        JsonReadContext child2 = child1.createChildObjectContext(15, 10);
        assertEquals(2, child2.getNestingDepth());
        
        JsonReadContext child3 = child2.createChildArrayContext(20, 15);
        assertEquals(3, child3.getNestingDepth());
    }

    @Test
    public void testInArrayInObjectInRoot() {
        assertTrue(rootContext.inRoot());
        assertFalse(rootContext.inArray());
        assertFalse(rootContext.inObject());
        
        JsonReadContext arrayCtx = rootContext.createChildArrayContext(10, 5);
        assertTrue(arrayCtx.inArray());
        assertFalse(arrayCtx.inRoot());
        assertFalse(arrayCtx.inObject());
        
        JsonReadContext objectCtx = rootContext.createChildObjectContext(10, 5);
        assertTrue(objectCtx.inObject());
        assertFalse(objectCtx.inRoot());
        assertFalse(objectCtx.inArray());
    }

    @Test
    public void testTypeDesc() {
        assertEquals("root", rootContext.typeDesc());
        
        JsonReadContext arrayCtx = rootContext.createChildArrayContext(10, 5);
        assertEquals("Array", arrayCtx.typeDesc());
        
        JsonReadContext objectCtx = rootContext.createChildObjectContext(10, 5);
        assertEquals("Object", objectCtx.typeDesc());
    }

    @Test
    public void testGetEntryCountAndCurrentIndex() {
        assertEquals(0, rootContext.getEntryCount());
        assertEquals(0, rootContext.getCurrentIndex());
        assertFalse(rootContext.hasCurrentIndex());
        
        rootContext.expectComma();
        assertEquals(1, rootContext.getEntryCount());
        assertEquals(0, rootContext.getCurrentIndex());
        assertTrue(rootContext.hasCurrentIndex());
        
        rootContext.expectComma();
        assertEquals(2, rootContext.getEntryCount());
        assertEquals(1, rootContext.getCurrentIndex());
    }

    @Test
    public void testHasPathSegment() {
        assertFalse(rootContext.hasPathSegment());
        
        JsonReadContext arrayCtx = rootContext.createChildArrayContext(10, 5);
        assertFalse(arrayCtx.hasPathSegment());
        arrayCtx.expectComma();
        assertTrue(arrayCtx.hasPathSegment());
        
        JsonReadContext objectCtx = rootContext.createChildObjectContext(10, 5);
        assertFalse(objectCtx.hasPathSegment());
        objectCtx.setCurrentName("prop");
        assertTrue(objectCtx.hasPathSegment());
    }

    @Test
    public void testToString() {
        String rootStr = rootContext.toString();
        assertTrue(rootStr.contains("/"));
        
        JsonReadContext arrayCtx = rootContext.createChildArrayContext(10, 5);
        arrayCtx.expectComma();
        String arrayStr = arrayCtx.toString();
        assertTrue(arrayStr.contains("[0]"));
        
        JsonReadContext objectCtx = rootContext.createChildObjectContext(10, 5);
        objectCtx.setCurrentName("testProp");
        String objectStr = objectCtx.toString();
        assertTrue(objectStr.contains("\"testProp\""));
    }

    @Test
    public void testDupDetectorChildPropagation() {
        DupDetector parentDetector = DupDetector.rootDetector(minimalParser);
        JsonReadContext root = JsonReadContext.createRootContext(1, 0, parentDetector);
        JsonReadContext child1 = root.createChildArrayContext(10, 5);
        JsonReadContext child2 = child1.createChildObjectContext(15, 10);
        
        assertSame(parentDetector, root.getDupDetector());
        assertNotNull(child1.getDupDetector());
        assertNotNull(child2.getDupDetector());
        // All should share the same source
        assertSame(minimalParser, root.getDupDetector().getSource());
        assertSame(minimalParser, child1.getDupDetector().getSource());
        assertSame(minimalParser, child2.getDupDetector().getSource());
    }

    @Test
    public void testConstructorDirect() {
        JsonReadContext parent = JsonReadContext.createRootContext(1, 0, null);
        JsonReadContext child = new JsonReadContext(parent, 1, realDupDetector, 
            TokenStreamContext.TYPE_ARRAY, 20, 10);
        
        assertSame(parent, child.getParent());
        assertTrue(child.inArray());
        assertEquals(1, child.getNestingDepth());
        assertEquals(20, child._lineNr);
        assertEquals(10, child._columnNr);
        assertEquals(0, child.getCurrentIndex());
        assertSame(realDupDetector, child.getDupDetector());
    }

    @Test
    public void testMultipleChildrenCreation() {
        JsonReadContext arrayChild = rootContext.createChildArrayContext(10, 5);
        JsonReadContext objectChild = rootContext.createChildObjectContext(20, 10);
        
        // Implementation reuses the same child slot, so they are the same instance
        assertSame(arrayChild, objectChild);
        // After reuse, the context is reset to OBJECT type
        assertTrue(objectChild.inObject());
        assertFalse(objectChild.inArray());
        assertSame(rootContext, arrayChild.getParent());
        assertSame(rootContext, objectChild.getParent());
    }

    @Test
    public void testResetPreservesParentAndDupDetector() {
        JsonReadContext child = rootContext.createChildArrayContext(10, 5);
        child.setCurrentName("name");
        child.assignCurrentValue("value");
        child.expectComma();
        child.expectComma();
        
        child.reset(TokenStreamContext.TYPE_OBJECT, 30, 15);
        
        assertSame(rootContext, child.getParent());
        assertNotNull(child.getDupDetector());
        assertTrue(child.inObject());
        assertEquals(30, child._lineNr);
        assertEquals(15, child._columnNr);
        assertEquals(0, child.getCurrentIndex());
        assertNull(child.currentName());
        assertNull(child.currentValue());
    }

    // Added test to cover _checkDup true branch with real DupDetector using JsonParser source
    @Test
    public void testSetCurrentNameDuplicateDetectionDirect() throws StreamReadException {
        DupDetector detector = DupDetector.rootDetector(minimalParser);
        JsonReadContext ctx = JsonReadContext.createRootContext(1, 0, detector);
        
        // First call - should not throw
        ctx.setCurrentName("testName");
        assertEquals("testName", ctx.currentName());
        
        // Second call with same name - should throw
        try {
            ctx.setCurrentName("testName");
            fail("Expected StreamReadException for duplicate name");
        } catch (StreamReadException e) {
            assertTrue(e.getMessage().contains("Duplicate Object property \"testName\""));
        }
    }

    // New test to verify _checkDup conditional branch when isDup returns false (no exception)
    @Test
    public void testSetCurrentNameNoDuplicateWithDupDetector() throws StreamReadException {
        DupDetector detector = DupDetector.rootDetector(minimalParser);
        JsonReadContext ctx = JsonReadContext.createRootContext(1, 0, detector);
        
        // First name - should not be duplicate
        ctx.setCurrentName("name1");
        assertEquals("name1", ctx.currentName());
        
        // Different name - should not be duplicate
        ctx.setCurrentName("name2");
        assertEquals("name2", ctx.currentName());
        
        // Another different name - should not be duplicate
        ctx.setCurrentName("name3");
        assertEquals("name3", ctx.currentName());
        
        // Verify no exception was thrown for any of the unique names
        assertTrue(true); // If we reach here, all calls succeeded
    }

    // New test to verify reset() calls DupDetector.reset() - targets VoidMethodCallMutator survival
    @Test
    public void testResetCallsDupDetectorReset() throws StreamReadException {
        DupDetector detector = DupDetector.rootDetector(minimalParser);
        JsonReadContext ctx = JsonReadContext.createRootContext(1, 0, detector);
        
        // Add some names to the detector to populate its state
        ctx.setCurrentName("prop1");
        ctx.setCurrentName("prop2");
        
        // Reset the context - this should call detector.reset()
        ctx.reset(TokenStreamContext.TYPE_OBJECT, 10, 10);
        
        // After reset, the detector should be cleared, so adding the same names again should not throw
        ctx.setCurrentName("prop1");
        assertEquals("prop1", ctx.currentName());
        
        ctx.setCurrentName("prop2");
        assertEquals("prop2", ctx.currentName());
        
        // Now adding them again should throw (detector is working again after reset)
        try {
            ctx.setCurrentName("prop1");
            fail("Expected StreamReadException for duplicate after reset");
        } catch (StreamReadException e) {
            assertTrue(e.getMessage().contains("Duplicate Object property \"prop1\""));
        }
    }

    // Fixed test to verify _checkDup conditional branch coverage - isDup false path
    @Test
    public void testCheckDupFalseBranch() throws StreamReadException {
        // Create a fresh detector and context for this test
        DupDetector detector = DupDetector.rootDetector(minimalParser);
        JsonReadContext ctx = JsonReadContext.createRootContext(1, 0, detector);
        
        // First call with a unique name - should hit false branch of isDup (returns false)
        ctx.setCurrentName("name1");
        assertEquals("name1", ctx.currentName());
        
        // Second call with a different unique name - should hit false branch again
        ctx.setCurrentName("name2");
        assertEquals("name2", ctx.currentName());
        
        // Third call with another different unique name - should hit false branch again
        ctx.setCurrentName("name3");
        assertEquals("name3", ctx.currentName());
        
        // All calls succeeded without throwing StreamReadException, 
        // meaning _checkDup's false branch (isDup returned false) was exercised
        assertTrue(true);
    }
}
