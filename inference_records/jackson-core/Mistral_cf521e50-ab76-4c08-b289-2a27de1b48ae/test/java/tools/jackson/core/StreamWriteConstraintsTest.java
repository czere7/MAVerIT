package tools.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;
import tools.jackson.core.exc.StreamConstraintsException;

public class StreamWriteConstraintsTest {

    @Test
    public void testDefaultMaxDepth() {
        assertEquals(500, StreamWriteConstraints.DEFAULT_MAX_DEPTH);
    }

    @Test
    public void testDefaultConstraints() {
        StreamWriteConstraints defaults = StreamWriteConstraints.defaults();
        assertEquals(500, defaults.getMaxNestingDepth());
    }

    @Test
    public void testBuilderWithDefaultDepth() {
        StreamWriteConstraints constraints = StreamWriteConstraints.builder().build();
        assertEquals(500, constraints.getMaxNestingDepth());
    }

    @Test
    public void testBuilderWithCustomDepth() {
        StreamWriteConstraints constraints = StreamWriteConstraints.builder()
                .maxNestingDepth(1000)
                .build();
        assertEquals(1000, constraints.getMaxNestingDepth());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBuilderWithNegativeDepth() {
        StreamWriteConstraints.builder().maxNestingDepth(-1).build();
    }

    @Test
    public void testRebuild() {
        StreamWriteConstraints original = StreamWriteConstraints.builder()
                .maxNestingDepth(1000)
                .build();
        StreamWriteConstraints rebuilt = original.rebuild().build();
        assertEquals(original.getMaxNestingDepth(), rebuilt.getMaxNestingDepth());
    }

    @Test
    public void testValidateNestingDepthWithinLimit() {
        StreamWriteConstraints constraints = StreamWriteConstraints.builder()
                .maxNestingDepth(1000)
                .build();
        constraints.validateNestingDepth(500);
    }

    @Test(expected = StreamConstraintsException.class)
    public void testValidateNestingDepthExceedsLimit() {
        StreamWriteConstraints constraints = StreamWriteConstraints.builder()
                .maxNestingDepth(1000)
                .build();
        constraints.validateNestingDepth(1001);
    }

    @Test
    public void testOverrideDefaultConstraints() {
        StreamWriteConstraints custom = StreamWriteConstraints.builder()
                .maxNestingDepth(1000)
                .build();
        StreamWriteConstraints.overrideDefaultStreamWriteConstraints(custom);
        assertEquals(1000, StreamWriteConstraints.defaults().getMaxNestingDepth());
        StreamWriteConstraints.overrideDefaultStreamWriteConstraints(null);
        assertEquals(500, StreamWriteConstraints.defaults().getMaxNestingDepth());
    }

    @Test
    public void testSerialization() throws Exception {
        StreamWriteConstraints original = StreamWriteConstraints.builder()
                .maxNestingDepth(1000)
                .build();
        byte[] serialized = serialize(original);
        StreamWriteConstraints deserialized = deserialize(serialized);
        assertEquals(original.getMaxNestingDepth(), deserialized.getMaxNestingDepth());
    }

    @Test
    public void testSerializationWithDefaultConstraints() throws Exception {
        StreamWriteConstraints original = StreamWriteConstraints.defaults();
        byte[] serialized = serialize(original);
        StreamWriteConstraints deserialized = deserialize(serialized);
        assertEquals(original.getMaxNestingDepth(), deserialized.getMaxNestingDepth());
    }

    @Test
    public void testSerializationWithMaxIntDepth() throws Exception {
        StreamWriteConstraints original = StreamWriteConstraints.builder()
                .maxNestingDepth(Integer.MAX_VALUE)
                .build();
        byte[] serialized = serialize(original);
        StreamWriteConstraints deserialized = deserialize(serialized);
        assertEquals(original.getMaxNestingDepth(), deserialized.getMaxNestingDepth());
    }

    @Test
    public void testBuilderWithBoundaryDepth() {
        StreamWriteConstraints constraints = StreamWriteConstraints.builder()
                .maxNestingDepth(Integer.MAX_VALUE - 1)
                .build();
        assertEquals(Integer.MAX_VALUE - 1, constraints.getMaxNestingDepth());
    }

    @Test
    public void testBuilderWithLargeDepth() {
        StreamWriteConstraints constraints = StreamWriteConstraints.builder()
                .maxNestingDepth(1000000)
                .build();
        assertEquals(1000000, constraints.getMaxNestingDepth());
    }

    private byte[] serialize(StreamWriteConstraints constraints) throws Exception {
        java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
        java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(baos);
        oos.writeObject(constraints);
        oos.close();
        return baos.toByteArray();
    }

    private StreamWriteConstraints deserialize(byte[] data) throws Exception {
        java.io.ByteArrayInputStream bais = new java.io.ByteArrayInputStream(data);
        java.io.ObjectInputStream ois = new java.io.ObjectInputStream(bais);
        return (StreamWriteConstraints) ois.readObject();
    }
}
