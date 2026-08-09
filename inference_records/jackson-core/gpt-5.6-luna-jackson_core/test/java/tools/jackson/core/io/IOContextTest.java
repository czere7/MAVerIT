package tools.jackson.core.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;

import org.junit.Test;

import tools.jackson.core.ErrorReportConfiguration;
import tools.jackson.core.JsonEncoding;
import tools.jackson.core.StreamReadConstraints;
import tools.jackson.core.StreamWriteConstraints;
import tools.jackson.core.util.BufferRecycler;
import tools.jackson.core.util.ReadConstrainedTextBuffer;
import tools.jackson.core.util.TextBuffer;

public class IOContextTest
{
    private IOContext newContext(BufferRecycler recycler) {
        return new IOContext(
                mock(StreamReadConstraints.class),
                mock(StreamWriteConstraints.class),
                mock(ErrorReportConfiguration.class),
                recycler,
                mock(ContentReference.class),
                true,
                JsonEncoding.UTF8);
    }

    @Test
    public void constructorStoresConfigurationAndState() {
        StreamReadConstraints readConstraints = mock(StreamReadConstraints.class);
        StreamWriteConstraints writeConstraints = mock(StreamWriteConstraints.class);
        ErrorReportConfiguration errorConfiguration = mock(ErrorReportConfiguration.class);
        BufferRecycler recycler = new BufferRecycler();
        ContentReference contentReference = mock(ContentReference.class);

        IOContext context = new IOContext(
                readConstraints,
                writeConstraints,
                errorConfiguration,
                recycler,
                contentReference,
                false,
                JsonEncoding.UTF16_BE);

        assertSame(readConstraints, context.streamReadConstraints());
        assertSame(writeConstraints, context.streamWriteConstraints());
        assertSame(errorConfiguration, context.errorReportConfiguration());
        assertSame(recycler, context.bufferRecycler());
        assertSame(contentReference, context.contentReference());
        assertFalse(context.isResourceManaged());
        assertEquals(JsonEncoding.UTF16_BE, context.getEncoding());
    }

    @Test
    public void managedResourceStateIsReported() {
        IOContext context = newContext(new BufferRecycler());

        assertTrue(context.isResourceManaged());
    }

    @Test(expected = NullPointerException.class)
    public void constructorRejectsNullReadConstraints() {
        new IOContext(
                null,
                mock(StreamWriteConstraints.class),
                mock(ErrorReportConfiguration.class),
                new BufferRecycler(),
                null,
                false,
                null);
    }

    @Test(expected = NullPointerException.class)
    public void constructorRejectsNullWriteConstraints() {
        new IOContext(
                mock(StreamReadConstraints.class),
                null,
                mock(ErrorReportConfiguration.class),
                new BufferRecycler(),
                null,
                false,
                null);
    }

    @Test(expected = NullPointerException.class)
    public void constructorRejectsNullErrorReportConfiguration() {
        new IOContext(
                mock(StreamReadConstraints.class),
                mock(StreamWriteConstraints.class),
                null,
                new BufferRecycler(),
                null,
                false,
                null);
    }

    @Test
    public void encodingCanBeChangedAndSetterIsFluent() {
        IOContext context = newContext(new BufferRecycler());

        assertSame(context, context.setEncoding(JsonEncoding.UTF32_LE));
        assertEquals(JsonEncoding.UTF32_LE, context.getEncoding());

        context.setEncoding(null);
        assertEquals(null, context.getEncoding());
    }

    @Test
    public void constructsTextBuffersWithConfiguredRecycler() {
        BufferRecycler recycler = new BufferRecycler();
        IOContext context = newContext(recycler);

        TextBuffer textBuffer = context.constructTextBuffer();
        TextBuffer constrainedBuffer = context.constructReadConstrainedTextBuffer();

        assertEquals(TextBuffer.class, textBuffer.getClass());
        assertSame(recycler, textBuffer.bufferRecycler());
        assertTrue(constrainedBuffer instanceof ReadConstrainedTextBuffer);
        assertSame(recycler, constrainedBuffer.bufferRecycler());
    }

    @Test
    public void allocatesAndReleasesAllProcessingBuffers() {
        BufferRecycler recycler = new BufferRecycler();
        IOContext context = newContext(recycler);

        byte[] read = context.allocReadIOBuffer(12345);
        byte[] write = context.allocWriteEncodingBuffer(12346);
        byte[] base64 = context.allocBase64Buffer(12347);
        char[] token = context.allocTokenBuffer(12348);
        char[] concat = context.allocConcatBuffer();
        char[] name = context.allocNameCopyBuffer(12349);

        assertTrue(read.length >= 12345);
        assertTrue(write.length >= 12346);
        assertTrue(base64.length >= 12347);
        assertTrue(token.length >= 12348);
        assertTrue(concat.length > 0);
        assertTrue(name.length >= 12349);

        context.releaseReadIOBuffer(read);
        context.releaseWriteEncodingBuffer(write);
        context.releaseBase64Buffer(base64);
        context.releaseTokenBuffer(token);
        context.releaseConcatBuffer(concat);
        context.releaseNameCopyBuffer(name);

        assertSame(read, context.allocReadIOBuffer());
        assertSame(write, context.allocWriteEncodingBuffer());
        assertSame(base64, context.allocBase64Buffer());
        assertSame(token, context.allocTokenBuffer());
        assertSame(concat, context.allocConcatBuffer());
        assertSame(name, context.allocNameCopyBuffer(0));
    }

    @Test
    public void everyAllocationVariantRejectsDuplicateAllocation() {
        IOContext context = newContext(new BufferRecycler());
        context.allocReadIOBuffer();
        assertDuplicateReadAllocationRejected(context);

        context = newContext(new BufferRecycler());
        context.allocReadIOBuffer(1);
        assertDuplicateReadAllocationWithMinimumRejected(context);

        context = newContext(new BufferRecycler());
        context.allocWriteEncodingBuffer();
        assertDuplicateWriteAllocationRejected(context);

        context = newContext(new BufferRecycler());
        context.allocWriteEncodingBuffer(1);
        assertDuplicateWriteAllocationWithMinimumRejected(context);

        context = newContext(new BufferRecycler());
        context.allocBase64Buffer();
        assertDuplicateBase64AllocationRejected(context);

        context = newContext(new BufferRecycler());
        context.allocBase64Buffer(1);
        assertDuplicateBase64AllocationWithMinimumRejected(context);

        context = newContext(new BufferRecycler());
        context.allocTokenBuffer();
        assertDuplicateTokenAllocationRejected(context);

        context = newContext(new BufferRecycler());
        context.allocTokenBuffer(1);
        assertDuplicateTokenAllocationWithMinimumRejected(context);

        context = newContext(new BufferRecycler());
        context.allocConcatBuffer();
        try {
            context.allocConcatBuffer();
            fail("Expected duplicate concat allocation to fail");
        } catch (IllegalStateException e) {
            assertEquals("Trying to call same allocXxx() method second time", e.getMessage());
        }

        context = newContext(new BufferRecycler());
        context.allocNameCopyBuffer(1);
        try {
            context.allocNameCopyBuffer(1);
            fail("Expected duplicate name-copy allocation to fail");
        } catch (IllegalStateException e) {
            assertEquals("Trying to call same allocXxx() method second time", e.getMessage());
        }
    }

    private void assertDuplicateReadAllocationRejected(IOContext context) {
        try {
            context.allocReadIOBuffer();
            fail("Expected duplicate read allocation to fail");
        } catch (IllegalStateException e) {
            assertEquals("Trying to call same allocXxx() method second time", e.getMessage());
        }
    }

    private void assertDuplicateReadAllocationWithMinimumRejected(IOContext context) {
        try {
            context.allocReadIOBuffer(1);
            fail("Expected duplicate read allocation to fail");
        } catch (IllegalStateException e) {
            assertEquals("Trying to call same allocXxx() method second time", e.getMessage());
        }
    }

    private void assertDuplicateWriteAllocationRejected(IOContext context) {
        try {
            context.allocWriteEncodingBuffer();
            fail("Expected duplicate write allocation to fail");
        } catch (IllegalStateException e) {
            assertEquals("Trying to call same allocXxx() method second time", e.getMessage());
        }
    }

    private void assertDuplicateWriteAllocationWithMinimumRejected(IOContext context) {
        try {
            context.allocWriteEncodingBuffer(1);
            fail("Expected duplicate write allocation to fail");
        } catch (IllegalStateException e) {
            assertEquals("Trying to call same allocXxx() method second time", e.getMessage());
        }
    }

    private void assertDuplicateBase64AllocationRejected(IOContext context) {
        try {
            context.allocBase64Buffer();
            fail("Expected duplicate base64 allocation to fail");
        } catch (IllegalStateException e) {
            assertEquals("Trying to call same allocXxx() method second time", e.getMessage());
        }
    }

    private void assertDuplicateBase64AllocationWithMinimumRejected(IOContext context) {
        try {
            context.allocBase64Buffer(1);
            fail("Expected duplicate base64 allocation to fail");
        } catch (IllegalStateException e) {
            assertEquals("Trying to call same allocXxx() method second time", e.getMessage());
        }
    }

    private void assertDuplicateTokenAllocationRejected(IOContext context) {
        try {
            context.allocTokenBuffer();
            fail("Expected duplicate token allocation to fail");
        } catch (IllegalStateException e) {
            assertEquals("Trying to call same allocXxx() method second time", e.getMessage());
        }
    }

    private void assertDuplicateTokenAllocationWithMinimumRejected(IOContext context) {
        try {
            context.allocTokenBuffer(1);
            fail("Expected duplicate token allocation to fail");
        } catch (IllegalStateException e) {
            assertEquals("Trying to call same allocXxx() method second time", e.getMessage());
        }
    }

    @Test
    public void equalSizedReplacementBuffersAreAllowedForEveryBufferType() {
        IOContext context = newContext(new BufferRecycler());
        byte[] read = context.allocReadIOBuffer();
        byte[] readReplacement = new byte[read.length];
        context.releaseReadIOBuffer(readReplacement);
        assertSame(readReplacement, context.allocReadIOBuffer());

        context = newContext(new BufferRecycler());
        byte[] write = context.allocWriteEncodingBuffer();
        byte[] writeReplacement = new byte[write.length];
        context.releaseWriteEncodingBuffer(writeReplacement);
        assertSame(writeReplacement, context.allocWriteEncodingBuffer());

        context = newContext(new BufferRecycler());
        byte[] base64 = context.allocBase64Buffer();
        byte[] base64Replacement = new byte[base64.length];
        context.releaseBase64Buffer(base64Replacement);
        assertSame(base64Replacement, context.allocBase64Buffer());

        context = newContext(new BufferRecycler());
        char[] token = context.allocTokenBuffer();
        char[] tokenReplacement = new char[token.length];
        context.releaseTokenBuffer(tokenReplacement);
        assertSame(tokenReplacement, context.allocTokenBuffer());

        context = newContext(new BufferRecycler());
        char[] concat = context.allocConcatBuffer();
        char[] concatReplacement = new char[concat.length];
        context.releaseConcatBuffer(concatReplacement);
        assertSame(concatReplacement, context.allocConcatBuffer());

        context = newContext(new BufferRecycler());
        char[] name = context.allocNameCopyBuffer(1);
        char[] nameReplacement = new char[name.length];
        context.releaseNameCopyBuffer(nameReplacement);
        assertSame(nameReplacement, context.allocNameCopyBuffer(1));
    }

    @Test
    public void nullBuffersCanBeReleasedWithoutEffect() {
        IOContext context = newContext(new BufferRecycler());

        context.releaseReadIOBuffer(null);
        context.releaseWriteEncodingBuffer(null);
        context.releaseBase64Buffer(null);
        context.releaseTokenBuffer(null);
        context.releaseConcatBuffer(null);
        context.releaseNameCopyBuffer(null);
    }

    @Test
    public void allocationOfSameBufferTypeTwiceIsRejected() {
        IOContext context = newContext(new BufferRecycler());

        context.allocReadIOBuffer();
        try {
            context.allocReadIOBuffer();
            fail("Expected duplicate allocation to fail");
        } catch (IllegalStateException e) {
            assertTrue(e.getMessage().contains("same allocXxx"));
        }
    }

    @Test
    public void releasingSmallerReplacementBufferIsRejected() {
        IOContext context = newContext(new BufferRecycler());

        byte[] original = context.allocReadIOBuffer();
        try {
            context.releaseReadIOBuffer(new byte[original.length - 1]);
            fail("Expected smaller replacement buffer to fail");
        } catch (IllegalArgumentException e) {
            assertEquals("Trying to release buffer smaller than original", e.getMessage());
        }

        char[] originalChars = context.allocTokenBuffer();
        try {
            context.releaseTokenBuffer(new char[originalChars.length - 1]);
            fail("Expected smaller replacement buffer to fail");
        } catch (IllegalArgumentException e) {
            assertEquals("Trying to release buffer smaller than original", e.getMessage());
        }
    }

    @Test
    public void releasingSmallerReplacementBufferIsRejectedForEveryBufferType() {
        IOContext context = newContext(new BufferRecycler());
        byte[] read = context.allocReadIOBuffer();
        assertSmallerByteReleaseRejected(context, read, true);

        context = newContext(new BufferRecycler());
        byte[] write = context.allocWriteEncodingBuffer();
        assertSmallerByteReleaseRejected(context, write, false);

        context = newContext(new BufferRecycler());
        byte[] base64 = context.allocBase64Buffer();
        try {
            context.releaseBase64Buffer(new byte[base64.length - 1]);
            fail("Expected smaller base64 buffer to fail");
        } catch (IllegalArgumentException e) {
            assertEquals("Trying to release buffer smaller than original", e.getMessage());
        }

        context = newContext(new BufferRecycler());
        char[] token = context.allocTokenBuffer();
        assertSmallerTokenReleaseRejected(context, token);

        context = newContext(new BufferRecycler());
        char[] concat = context.allocConcatBuffer();
        try {
            context.releaseConcatBuffer(new char[concat.length - 1]);
            fail("Expected smaller concat buffer to fail");
        } catch (IllegalArgumentException e) {
            assertEquals("Trying to release buffer smaller than original", e.getMessage());
        }

        context = newContext(new BufferRecycler());
        char[] name = context.allocNameCopyBuffer(1);
        try {
            context.releaseNameCopyBuffer(new char[name.length - 1]);
            fail("Expected smaller name-copy buffer to fail");
        } catch (IllegalArgumentException e) {
            assertEquals("Trying to release buffer smaller than original", e.getMessage());
        }
    }

    private void assertSmallerByteReleaseRejected(IOContext context, byte[] original,
            boolean readBuffer) {
        try {
            if (readBuffer) {
                context.releaseReadIOBuffer(new byte[original.length - 1]);
            } else {
                context.releaseWriteEncodingBuffer(new byte[original.length - 1]);
            }
            fail("Expected smaller byte buffer to fail");
        } catch (IllegalArgumentException e) {
            assertEquals("Trying to release buffer smaller than original", e.getMessage());
        }
    }

    private void assertSmallerTokenReleaseRejected(IOContext context, char[] original) {
        try {
            context.releaseTokenBuffer(new char[original.length - 1]);
            fail("Expected smaller token buffer to fail");
        } catch (IllegalArgumentException e) {
            assertEquals("Trying to release buffer smaller than original", e.getMessage());
        }
    }

    @Test
    public void releasingLargerReplacementBufferIsAllowed() {
        BufferRecycler recycler = new BufferRecycler();
        IOContext context = newContext(recycler);

        byte[] original = context.allocReadIOBuffer();
        byte[] larger = new byte[original.length + 1];
        context.releaseReadIOBuffer(larger);
        assertSame(larger, context.allocReadIOBuffer());

        char[] originalChars = context.allocTokenBuffer();
        char[] largerChars = new char[originalChars.length + 1];
        context.releaseTokenBuffer(largerChars);
        assertSame(largerChars, context.allocTokenBuffer());
    }

    @Test
    public void closeReleasesRecyclerOnlyOnce() {
        BufferRecycler recycler = mock(BufferRecycler.class);
        IOContext context = newContext(recycler);

        context.close();
        context.close();

        verify(recycler).releaseToPool();
    }

    @Test
    public void markedRecyclerIsNotReleasedOnClose() {
        BufferRecycler recycler = mock(BufferRecycler.class);
        IOContext context = newContext(recycler);

        assertSame(context, context.markBufferRecyclerReleased());
        context.close();
        context.close();

        verify(recycler, never()).releaseToPool();
    }
}
