package tools.jackson.core;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import tools.jackson.core.exc.StreamConstraintsException;

import static org.junit.Assert.*;

public class StreamWriteConstraintsTest {

    @Test
    public void testDefaultMaxDepth() {
        assertEquals(500, StreamWriteConstraints.DEFAULT_MAX_DEPTH);
    }

    @Test
    public void testDefaultsReturnsInstance() {
        StreamWriteConstraints defaults = StreamWriteConstraints.defaults();
        assertNotNull(defaults);
        assertEquals(StreamWriteConstraints.DEFAULT_MAX_DEPTH, defaults.getMaxNestingDepth());
    }

    @Test
    public void testOverrideDefaultStreamWriteConstraintsWithNull() {
        StreamWriteConstraints custom = StreamWriteConstraints.builder().maxNestingDepth(100).build();
        StreamWriteConstraints.overrideDefaultStreamWriteConstraints(custom);
        assertEquals(100, StreamWriteConstraints.defaults().getMaxNestingDepth());

        StreamWriteConstraints.overrideDefaultStreamWriteConstraints(null);
        assertEquals(StreamWriteConstraints.DEFAULT_MAX_DEPTH, StreamWriteConstraints.defaults().getMaxNestingDepth());
    }

    @Test
    public void testOverrideDefaultStreamWriteConstraintsWithInstance() {
        StreamWriteConstraints custom = StreamWriteConstraints.builder().maxNestingDepth(200).build();
        StreamWriteConstraints.overrideDefaultStreamWriteConstraints(custom);
        assertEquals(200, StreamWriteConstraints.defaults().getMaxNestingDepth());
        StreamWriteConstraints.overrideDefaultStreamWriteConstraints(null);
    }

    @Test
    public void testBuilderDefaultConstructor() {
        StreamWriteConstraints.Builder builder = new StreamWriteConstraints.Builder();
        StreamWriteConstraints constraints = builder.build();
        assertEquals(StreamWriteConstraints.DEFAULT_MAX_DEPTH, constraints.getMaxNestingDepth());
    }

    @Test
    public void testBuilderWithMaxNestingDepth() {
        StreamWriteConstraints.Builder builder = new StreamWriteConstraints.Builder();
        StreamWriteConstraints constraints = builder.maxNestingDepth(1000).build();
        assertEquals(1000, constraints.getMaxNestingDepth());
    }

    @Test
    public void testBuilderWithZeroMaxNestingDepth() {
        StreamWriteConstraints.Builder builder = new StreamWriteConstraints.Builder();
        StreamWriteConstraints constraints = builder.maxNestingDepth(0).build();
        assertEquals(0, constraints.getMaxNestingDepth());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBuilderWithNegativeMaxNestingDepthThrows() {
        new StreamWriteConstraints.Builder().maxNestingDepth(-1);
    }

    @Test
    public void testBuilderCopyConstructor() {
        StreamWriteConstraints original = StreamWriteConstraints.builder().maxNestingDepth(750).build();
        StreamWriteConstraints.Builder builder = new StreamWriteConstraints.Builder(original);
        StreamWriteConstraints copy = builder.build();
        assertEquals(original.getMaxNestingDepth(), copy.getMaxNestingDepth());
    }

    @Test
    public void testStaticBuilderMethod() {
        StreamWriteConstraints.Builder builder = StreamWriteConstraints.builder();
        assertNotNull(builder);
        StreamWriteConstraints constraints = builder.maxNestingDepth(300).build();
        assertEquals(300, constraints.getMaxNestingDepth());
    }

    @Test
    public void testRebuild() {
        StreamWriteConstraints original = StreamWriteConstraints.builder().maxNestingDepth(400).build();
        StreamWriteConstraints.Builder rebuilt = original.rebuild();
        assertEquals(original.getMaxNestingDepth(), rebuilt.build().getMaxNestingDepth());
    }

    @Test
    public void testGetMaxNestingDepth() {
        StreamWriteConstraints constraints = StreamWriteConstraints.builder().maxNestingDepth(600).build();
        assertEquals(600, constraints.getMaxNestingDepth());
    }

    @Test
    public void testValidateNestingDepthWithinLimit() {
        StreamWriteConstraints constraints = StreamWriteConstraints.builder().maxNestingDepth(10).build();
        constraints.validateNestingDepth(5);
        constraints.validateNestingDepth(10);
    }

    @Test(expected = StreamConstraintsException.class)
    public void testValidateNestingDepthExceedsLimit() {
        StreamWriteConstraints constraints = StreamWriteConstraints.builder().maxNestingDepth(10).build();
        constraints.validateNestingDepth(11);
    }

    @Test(expected = StreamConstraintsException.class)
    public void testValidateNestingDepthZeroLimit() {
        StreamWriteConstraints constraints = StreamWriteConstraints.builder().maxNestingDepth(0).build();
        constraints.validateNestingDepth(1);
    }

    @Test
    public void testValidateNestingDepthZeroLimitWithZeroDepth() {
        StreamWriteConstraints constraints = StreamWriteConstraints.builder().maxNestingDepth(0).build();
        constraints.validateNestingDepth(0);
    }

    @Test
    public void testSerialization() throws Exception {
        StreamWriteConstraints original = StreamWriteConstraints.builder().maxNestingDepth(800).build();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(original);
        }

        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        StreamWriteConstraints deserialized;
        try (ObjectInputStream ois = new ObjectInputStream(bais)) {
            deserialized = (StreamWriteConstraints) ois.readObject();
        }

        assertEquals(original.getMaxNestingDepth(), deserialized.getMaxNestingDepth());
    }

    @Test
    public void testImplementsSerializable() {
        StreamWriteConstraints constraints = StreamWriteConstraints.builder().maxNestingDepth(100).build();
        assertTrue(constraints instanceof Serializable);
    }

    @Test
    public void testExceptionMessageContainsDepthValues() {
        StreamWriteConstraints constraints = StreamWriteConstraints.builder().maxNestingDepth(5).build();
        try {
            constraints.validateNestingDepth(10);
            fail("Expected StreamConstraintsException");
        } catch (StreamConstraintsException e) {
            String message = e.getMessage();
            assertTrue("Message should contain actual depth", message.contains("10"));
            assertTrue("Message should contain max depth", message.contains("5"));
            assertTrue("Message should contain method reference", message.contains("getMaxNestingDepth"));
        }
    }

    @Test
    public void testBuilderChaining() {
        StreamWriteConstraints constraints = StreamWriteConstraints.builder()
                .maxNestingDepth(123)
                .build();
        assertEquals(123, constraints.getMaxNestingDepth());
    }

    @Test
    public void testMultipleBuildCalls() {
        StreamWriteConstraints.Builder builder = StreamWriteConstraints.builder().maxNestingDepth(50);
        StreamWriteConstraints first = builder.build();
        StreamWriteConstraints second = builder.build();
        assertEquals(first.getMaxNestingDepth(), second.getMaxNestingDepth());
        assertNotSame(first, second);
    }

    @Test
    public void testRebuildModification() {
        StreamWriteConstraints original = StreamWriteConstraints.builder().maxNestingDepth(100).build();
        StreamWriteConstraints modified = original.rebuild().maxNestingDepth(200).build();
        assertEquals(100, original.getMaxNestingDepth());
        assertEquals(200, modified.getMaxNestingDepth());
    }
}
