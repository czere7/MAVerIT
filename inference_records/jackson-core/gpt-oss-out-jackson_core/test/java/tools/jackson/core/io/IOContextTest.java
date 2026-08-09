package tools.jackson.core.io;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.Test;

import static org.junit.Assert.*;

import tools.jackson.core.ErrorReportConfiguration;
import tools.jackson.core.JsonEncoding;
import tools.jackson.core.StreamReadConstraints;
import tools.jackson.core.StreamWriteConstraints;
import tools.jackson.core.util.BufferRecycler;
import tools.jackson.core.util.ReadConstrainedTextBuffer;

/**
 * Unit tests for {@link IOContext}.
 */
public class IOContextTest {

    /**
     * Subclass of {@code StreamReadConstraints} that exposes the protected constructor.
     */
    private static final class TestStreamReadConstraints extends StreamReadConstraints {
        public TestStreamReadConstraints(int maxNestingDepth, long maxDocLen,
                                         long maxTokenCount, int maxNumLen,
                                         int maxStringLen, int maxNameLen) {
            super(maxNestingDepth, maxDocLen, maxTokenCount,
                  maxNumLen, maxStringLen, maxNameLen);
        }
    }

    /**
     * Subclass of {@code StreamWriteConstraints} that exposes the protected constructor.
     */
    private static final class TestStreamWriteConstraints extends StreamWriteConstraints {
        public TestStreamWriteConstraints(int maxNestingDepth) {
            super(maxNestingDepth);
        }
    }

    /**
     * Subclass of {@code ErrorReportConfiguration} that exposes the protected constructor.
     */
    private static final class TestErrorReportConfiguration extends ErrorReportConfiguration {
        public TestErrorReportConfiguration(int maxErrorTokenLength, int maxRawContentLength) {
            super(maxErrorTokenLength, maxRawContentLength);
        }
    }

    private StreamReadConstraints srConstr = new TestStreamReadConstraints(
            100, Long.MAX_VALUE, Long.MAX_VALUE,
            1000, 2000, 500);
    private StreamWriteConstraints swc = new TestStreamWriteConstraints(100);
    private ErrorReportConfiguration erc = new TestErrorReportConfiguration(256, 1024);

    @Test
    public void constructorRejectsNullParameters() {
        try {
            new IOContext(null, swc, erc,
                    null, null, false, null);
            fail("Expected NullPointerException for null StreamReadConstraints");
        } catch (NullPointerException e) {
            // expected
        }

        try {
            new IOContext(srConstr, null, erc,
                    null, null, false, null);
            fail("Expected NullPointerException for null StreamWriteConstraints");
        } catch (NullPointerException e) {
            // expected
        }

        try {
            new IOContext(srConstr, swc, null,
                    null, null, false, null);
            fail("Expected NullPointerException for null ErrorReportConfiguration");
        } catch (NullPointerException e) {
            // expected
        }
    }

    @Test
    public void bufferAllocationAndRelease() throws Exception {
        BufferRecycler br = new BufferRecycler();
        IOContext ctx = new IOContext(srConstr, swc, erc,
                br, null, false, null);

        byte[] buf1 = ctx.allocReadIOBuffer();
        assertNotNull(buf1);
        int size1 = buf1.length;

        // Second allocation should throw
        try {
            ctx.allocReadIOBuffer();
            fail("Expected IllegalStateException on second alloc");
        } catch (IllegalStateException e) {
            // expected
        }

        // Release buffer and ensure we can allocate again
        ctx.releaseReadIOBuffer(buf1);
        byte[] buf2 = ctx.allocReadIOBuffer();
        assertNotNull(buf2);

        // Releasing a smaller buffer should throw
        try {
            ctx.releaseReadIOBuffer(new byte[1]);
            fail("Expected IllegalArgumentException for releasing smaller buffer");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    @Test
    public void allocWithMinSizeHonorsMinimum() {
        BufferRecycler br = new BufferRecycler();
        IOContext ctx = new IOContext(srConstr, swc, erc,
                br, null, false, null);

        int minSize = 5000;
        byte[] buf = ctx.allocReadIOBuffer(minSize);
        assertNotNull(buf);
        assertTrue("Allocated buffer smaller than requested", buf.length >= minSize);
    }

    @Test
    public void encodingSetterGetter() {
        BufferRecycler br = new BufferRecycler();
        IOContext ctx = new IOContext(srConstr, swc, erc,
                br, null, false, JsonEncoding.UTF16_LE);

        assertEquals(JsonEncoding.UTF16_LE, ctx.getEncoding());
        ctx.setEncoding(JsonEncoding.UTF8);
        assertEquals(JsonEncoding.UTF8, ctx.getEncoding());
    }

    @Test
    public void closeReleasesBufferRecyclerByDefault() {
        AtomicBoolean released = new AtomicBoolean(false);
        BufferRecycler br = new BufferRecycler() {
            @Override
            public void releaseToPool() {
                released.set(true);
            }
        };

        IOContext ctx = new IOContext(srConstr, swc, erc,
                br, null, false, null);

        assertFalse(released.get());
        ctx.close();
        assertTrue("BufferRecycler should be released on close", released.get());

        // Subsequent close() should not change state
        ctx.close();
        assertTrue(released.get());
    }

    @Test
    public void markBufferRecyclerReleasedPreventsReleaseOnClose() {
        AtomicBoolean released = new AtomicBoolean(false);
        BufferRecycler br = new BufferRecycler() {
            @Override
            public void releaseToPool() {
                released.set(true);
            }
        };

        IOContext ctx = new IOContext(srConstr, swc, erc,
                br, null, false, null);

        ctx.markBufferRecyclerReleased();
        assertFalse(released.get());
        ctx.close();
        assertFalse("BufferRecycler should NOT be released after mark", released.get());
    }

    @Test
    public void contentReferenceAccess() {
        BufferRecycler br = new BufferRecycler();
        IOContext ctx = new IOContext(srConstr, swc, erc,
                br, null, false, null);

        assertNull(ctx.contentReference());
    }

    /* --------------------------------------------------------------------- */
    /*                     Additional tests for remaining branches             */
    /* --------------------------------------------------------------------- */

    @Test
    public void streamWriteConstraintsAccessor() {
        BufferRecycler br = new BufferRecycler();
        IOContext ctx = new IOContext(srConstr, swc, erc,
                br, null, false, null);
        assertSame(swc, ctx.streamWriteConstraints());
    }

    @Test
    public void errorReportConfigurationAccessor() {
        BufferRecycler br = new BufferRecycler();
        IOContext ctx = new IOContext(srConstr, swc, erc,
                br, null, false, null);
        assertSame(erc, ctx.errorReportConfiguration());
    }

    @Test
    public void isResourceManagedFlag() {
        BufferRecycler br = new BufferRecycler();
        IOContext ctx = new IOContext(srConstr, swc, erc,
                br, null, true, null);
        assertTrue(ctx.isResourceManaged());

        IOContext ctx2 = new IOContext(srConstr, swc, erc,
                br, null, false, null);
        assertFalse(ctx2.isResourceManaged());
    }

    @Test
    public void bufferRecyclerAccessor() {
        BufferRecycler br = new BufferRecycler();
        IOContext ctx = new IOContext(srConstr, swc, erc,
                br, null, false, null);
        assertSame(br, ctx.bufferRecycler());
    }

    @Test
    public void constructTextBufferUsesProvidedRecycler() {
        BufferRecycler br = new BufferRecycler();
        IOContext ctx = new IOContext(srConstr, swc, erc,
                br, null, false, null);
        tools.jackson.core.util.TextBuffer tb = ctx.constructTextBuffer();
        assertSame(br, tb.bufferRecycler());
    }

    @Test
    public void allocReleaseCycleWriteEncodingBuffer() {
        BufferRecycler br = new BufferRecycler();
        IOContext ctx = new IOContext(srConstr, swc, erc,
                br, null, false, null);

        byte[] buf1 = ctx.allocWriteEncodingBuffer();
        assertNotNull(buf1);

        // Second allocation should throw
        try {
            ctx.allocWriteEncodingBuffer();
            fail("Expected IllegalStateException on second alloc of write encoding buffer");
        } catch (IllegalStateException e) { }

        ctx.releaseWriteEncodingBuffer(buf1);
        byte[] buf2 = ctx.allocWriteEncodingBuffer();
        assertNotNull(buf2);
    }

    @Test
    public void allocReleaseCycleBase64Buffer() {
        BufferRecycler br = new BufferRecycler();
        IOContext ctx = new IOContext(srConstr, swc, erc,
                br, null, false, null);

        byte[] buf1 = ctx.allocBase64Buffer();
        assertNotNull(buf1);
        try {
            ctx.allocBase64Buffer();
            fail("Expected IllegalStateException on second alloc of base64 buffer");
        } catch (IllegalStateException e) { }

        ctx.releaseBase64Buffer(buf1);
        byte[] buf2 = ctx.allocBase64Buffer();
        assertNotNull(buf2);
    }

    @Test
    public void allocReleaseCycleTokenBuffer() {
        BufferRecycler br = new BufferRecycler();
        IOContext ctx = new IOContext(srConstr, swc, erc,
                br, null, false, null);

        char[] buf1 = ctx.allocTokenBuffer();
        assertNotNull(buf1);
        try {
            ctx.allocTokenBuffer();
            fail("Expected IllegalStateException on second alloc of token buffer");
        } catch (IllegalStateException e) { }

        ctx.releaseTokenBuffer(buf1);
        char[] buf2 = ctx.allocTokenBuffer();
        assertNotNull(buf2);
    }

    @Test
    public void allocReleaseCycleConcatBuffer() {
        BufferRecycler br = new BufferRecycler();
        IOContext ctx = new IOContext(srConstr, swc, erc,
                br, null, false, null);

        char[] buf1 = ctx.allocConcatBuffer();
        assertNotNull(buf1);
        try {
            ctx.allocConcatBuffer();
            fail("Expected IllegalStateException on second alloc of concat buffer");
        } catch (IllegalStateException e) { }

        ctx.releaseConcatBuffer(buf1);
        char[] buf2 = ctx.allocConcatBuffer();
        assertNotNull(buf2);
    }

    @Test
    public void allocReleaseCycleNameCopyBuffer() {
        BufferRecycler br = new BufferRecycler();
        IOContext ctx = new IOContext(srConstr, swc, erc,
                br, null, false, null);

        char[] buf1 = ctx.allocNameCopyBuffer(10);
        assertNotNull(buf1);
        try {
            ctx.allocNameCopyBuffer(20);
            fail("Expected IllegalStateException on second alloc of name copy buffer");
        } catch (IllegalStateException e) { }

        ctx.releaseNameCopyBuffer(buf1);
        char[] buf2 = ctx.allocNameCopyBuffer(5);
        assertNotNull(buf2);
    }

    @Test
    public void verifyReleaseRejectsSmallerCharBuffers() {
        BufferRecycler br = new BufferRecycler();
        IOContext ctx = new IOContext(srConstr, swc, erc,
                br, null, false, null);

        // Token buffer
        char[] tokenBuf = ctx.allocTokenBuffer();
        assertNotNull(tokenBuf);
        char[] smallerToken = new char[tokenBuf.length - 1];
        try {
            ctx.releaseTokenBuffer(smallerToken);
            fail("Expected IllegalArgumentException for smaller token buffer");
        } catch (IllegalArgumentException e) { }

        // Concat buffer
        char[] concatBuf = ctx.allocConcatBuffer();
        assertNotNull(concatBuf);
        char[] smallerConcat = new char[concatBuf.length - 1];
        try {
            ctx.releaseConcatBuffer(smallerConcat);
            fail("Expected IllegalArgumentException for smaller concat buffer");
        } catch (IllegalArgumentException e) { }

        // Name copy buffer
        char[] nameBuf = ctx.allocNameCopyBuffer(10);
        assertNotNull(nameBuf);
        char[] smallerName = new char[nameBuf.length - 1];
        try {
            ctx.releaseNameCopyBuffer(smallerName);
            fail("Expected IllegalArgumentException for smaller name copy buffer");
        } catch (IllegalArgumentException e) { }
    }

    /* --------------------------------------------------------------------- */
    /*                     NEW TESTS TO COVER BOUNDARY CONDITIONS           */
    /* --------------------------------------------------------------------- */

    @Test
    public void allocWithMinSizeBranchesForAllBuffers() {
        BufferRecycler br = new BufferRecycler();
        IOContext ctx = new IOContext(srConstr, swc, erc,
                br, null, false, null);

        // Read buffer: small min size triggers default path
        byte[] readSmall = ctx.allocReadIOBuffer(1);
        assertNotNull(readSmall);
        assertTrue("Size should be at least 1", readSmall.length >= 1);
        ctx.releaseReadIOBuffer(readSmall);

        byte[] readBig = ctx.allocReadIOBuffer(10000);
        assertNotNull(readBig);
        assertTrue("Size should be at least 1000", readBig.length >= 1000);
        ctx.releaseReadIOBuffer(readBig);

        // Write encoding buffer
        byte[] writeSmall = ctx.allocWriteEncodingBuffer(2);
        assertNotNull(writeSmall);
        assertTrue(writeSmall.length >= 2);
        ctx.releaseWriteEncodingBuffer(writeSmall);

        byte[] writeBig = ctx.allocWriteEncodingBuffer(5000);
        assertNotNull(writeBig);
        assertTrue(writeBig.length >= 5000);
        ctx.releaseWriteEncodingBuffer(writeBig);

        // Base64 buffer
        byte[] baseSmall = ctx.allocBase64Buffer(3);
        assertNotNull(baseSmall);
        assertTrue(baseSmall.length >= 3);
        ctx.releaseBase64Buffer(baseSmall);

        byte[] baseBig = ctx.allocBase64Buffer(8000);
        assertNotNull(baseBig);
        assertTrue(baseBig.length >= 8000);
        ctx.releaseBase64Buffer(baseBig);
    }

    @Test
    public void releaseMethodsHandleNullBuffersGracefully() {
        BufferRecycler br = new BufferRecycler();
        IOContext ctx = new IOContext(srConstr, swc, erc,
                br, null, false, null);

        // These should not throw exceptions
        ctx.releaseReadIOBuffer(null);
        ctx.releaseWriteEncodingBuffer(null);
        ctx.releaseBase64Buffer(null);
        ctx.releaseTokenBuffer(null);
        ctx.releaseConcatBuffer(null);
        ctx.releaseNameCopyBuffer(null);
    }

    @Test
    public void releaseMethodsAcceptLargerBuffersWithoutException() {
        BufferRecycler br = new BufferRecycler();
        IOContext ctx = new IOContext(srConstr, swc, erc,
                br, null, false, null);

        byte[] readBuf = ctx.allocReadIOBuffer();
        char[] tokenBuf = ctx.allocTokenBuffer();

        // Larger buffers (different references but larger size)
        byte[] biggerRead = new byte[readBuf.length + 10];
        char[] biggerToken = new char[tokenBuf.length + 5];

        // No exception should be thrown
        ctx.releaseReadIOBuffer(biggerRead);
        ctx.releaseTokenBuffer(biggerToken);
    }

    @Test
    public void constructorAllowsNullEncoding() {
        BufferRecycler br = new BufferRecycler();
        IOContext ctx = new IOContext(srConstr, swc, erc,
                br, null, false, null);
        assertNull(ctx.getEncoding());
    }

    @Test(expected = NullPointerException.class)
    public void allocationWithNullBufferRecyclerThrowsNPE() {
        IOContext ctx = new IOContext(srConstr, swc, erc,
                null, null, false, null);
        // Attempt to allocate a buffer; should throw NPE due to null BufferRecycler
        ctx.allocReadIOBuffer();
    }

    @Test
    public void constructReadConstrainedTextBufferUsesCorrectConstraints() throws Exception {
        BufferRecycler br = new BufferRecycler();
        IOContext ctx = new IOContext(srConstr, swc, erc,
                br, null, false, null);
        ReadConstrainedTextBuffer rcTb =
            (ReadConstrainedTextBuffer) ctx.constructReadConstrainedTextBuffer();

        Field f = ReadConstrainedTextBuffer.class.getDeclaredField("_streamReadConstraints");
        f.setAccessible(true);
        assertSame(srConstr, f.get(rcTb));
    }

    /* --------------------------------------------------------------------- */
    /*                     Additional tests for remaining branches             */
    /* --------------------------------------------------------------------- */

    @Test
    public void contentReferenceNonNull() {
        BufferRecycler br = new BufferRecycler();
        ContentReference ref = ContentReference.construct(false, "test", erc);
        IOContext ctx = new IOContext(srConstr, swc, erc,
                br, ref, false, null);
        assertSame(ref, ctx.contentReference());
    }

    @Test
    public void verifyReleaseRejectsSmallerByteBuffers() {
        BufferRecycler br = new BufferRecycler();
        IOContext ctx = new IOContext(srConstr, swc, erc,
                br, null, false, null);

        // Read buffer
        byte[] readBuf = ctx.allocReadIOBuffer();
        int sizeRead = readBuf.length;
        try {
            ctx.releaseReadIOBuffer(new byte[sizeRead - 1]);
            fail("Expected IllegalArgumentException for smaller read buffer");
        } catch (IllegalArgumentException e) { }

        // Write encoding buffer
        byte[] writeBuf = ctx.allocWriteEncodingBuffer();
        int sizeWrite = writeBuf.length;
        try {
            ctx.releaseWriteEncodingBuffer(new byte[sizeWrite - 1]);
            fail("Expected IllegalArgumentException for smaller write buffer");
        } catch (IllegalArgumentException e) { }

        // Base64 buffer
        byte[] baseBuf = ctx.allocBase64Buffer();
        int sizeBase = baseBuf.length;
        try {
            ctx.releaseBase64Buffer(new byte[sizeBase - 1]);
            fail("Expected IllegalArgumentException for smaller base64 buffer");
        } catch (IllegalArgumentException e) { }
    }

    /* --------------------------------------------------------------------- */
    /*                     NEW TESTS TO COVER BOUNDARY CONDITIONS           */
    /* --------------------------------------------------------------------- */

    @Test
    public void releaseByteBuffersSameSizeDifferentReferenceDoesNotThrow() {
        BufferRecycler br = new BufferRecycler();
        IOContext ctx = new IOContext(srConstr, swc, erc,
                br, null, false, null);

        // Read buffer
        byte[] originalRead = ctx.allocReadIOBuffer();
        int lenRead = originalRead.length;
        byte[] sameSizeRead = new byte[lenRead];
        try {
            ctx.releaseReadIOBuffer(sameSizeRead);
        } catch (IllegalArgumentException e) {
            fail("releaseReadIOBuffer should not throw for equal-size different reference");
        }

        // Write encoding buffer
        byte[] originalWrite = ctx.allocWriteEncodingBuffer();
        int lenWrite = originalWrite.length;
        byte[] sameSizeWrite = new byte[lenWrite];
        try {
            ctx.releaseWriteEncodingBuffer(sameSizeWrite);
        } catch (IllegalArgumentException e) {
            fail("releaseWriteEncodingBuffer should not throw for equal-size different reference");
        }

        // Base64 buffer
        byte[] originalBase = ctx.allocBase64Buffer();
        int lenBase = originalBase.length;
        byte[] sameSizeBase = new byte[lenBase];
        try {
            ctx.releaseBase64Buffer(sameSizeBase);
        } catch (IllegalArgumentException e) {
            fail("releaseBase64Buffer should not throw for equal-size different reference");
        }
    }

    @Test
    public void releaseCharBuffersSameSizeDifferentReferenceDoesNotThrow() {
        BufferRecycler br = new BufferRecycler();
        IOContext ctx = new IOContext(srConstr, swc, erc,
                br, null, false, null);

        // Token buffer
        char[] originalToken = ctx.allocTokenBuffer();
        int lenToken = originalToken.length;
        char[] sameSizeToken = new char[lenToken];
        try {
            ctx.releaseTokenBuffer(sameSizeToken);
        } catch (IllegalArgumentException e) {
            fail("releaseTokenBuffer should not throw for equal-size different reference");
        }

        // Concat buffer
        char[] originalConcat = ctx.allocConcatBuffer();
        int lenConcat = originalConcat.length;
        char[] sameSizeConcat = new char[lenConcat];
        try {
            ctx.releaseConcatBuffer(sameSizeConcat);
        } catch (IllegalArgumentException e) {
            fail("releaseConcatBuffer should not throw for equal-size different reference");
        }

        // Name copy buffer
        char[] originalName = ctx.allocNameCopyBuffer(10);
        int lenName = originalName.length;
        char[] sameSizeName = new char[lenName];
        try {
            ctx.releaseNameCopyBuffer(sameSizeName);
        } catch (IllegalArgumentException e) {
            fail("releaseNameCopyBuffer should not throw for equal-size different reference");
        }
    }

    /* --------------------------------------------------------------------- */
    /*                     NEW TESTS TO COVER MIN SIZE ALLOCATIONS          */
    /* --------------------------------------------------------------------- */

    @Test
    public void allocReadIOBufferMinSizeDoubleAllocShouldThrow() {
        BufferRecycler br = new BufferRecycler();
        IOContext ctx = new IOContext(srConstr, swc, erc,
                br, null, false, null);

        ctx.allocReadIOBuffer(10);
        try {
            ctx.allocReadIOBuffer(20);
            fail("Expected IllegalStateException on second alloc of read buffer with minSize");
        } catch (IllegalStateException e) { }
    }

    @Test
    public void allocWriteEncodingBufferMinSizeDoubleAllocShouldThrow() {
        BufferRecycler br = new BufferRecycler();
        IOContext ctx = new IOContext(srConstr, swc, erc,
                br, null, false, null);

        ctx.allocWriteEncodingBuffer(10);
        try {
            ctx.allocWriteEncodingBuffer(20);
            fail("Expected IllegalStateException on second alloc of write encoding buffer with minSize");
        } catch (IllegalStateException e) { }
    }

    @Test
    public void allocBase64BufferMinSizeDoubleAllocShouldThrow() {
        BufferRecycler br = new BufferRecycler();
        IOContext ctx = new IOContext(srConstr, swc, erc,
                br, null, false, null);

        ctx.allocBase64Buffer(10);
        try {
            ctx.allocBase64Buffer(20);
            fail("Expected IllegalStateException on second alloc of base64 buffer with minSize");
        } catch (IllegalStateException e) { }
    }

    @Test
    public void allocNameCopyBufferIntDoubleAllocShouldThrow() {
        BufferRecycler br = new BufferRecycler();
        IOContext ctx = new IOContext(srConstr, swc, erc,
                br, null, false, null);

        ctx.allocNameCopyBuffer(10);
        try {
            ctx.allocNameCopyBuffer(20);
            fail("Expected IllegalStateException on second alloc of name copy buffer");
        } catch (IllegalStateException e) { }
    }

    /* --------------------------------------------------------------------- */
    /*                     NEW TESTS TO COVER RETURN VALUES                 */
    /* --------------------------------------------------------------------- */

    @Test
    public void setEncodingReturnsThisInstance() {
        BufferRecycler br = new BufferRecycler();
        IOContext ctx = new IOContext(srConstr, swc, erc,
                br, null, false, JsonEncoding.UTF16_LE);

        IOContext returned = ctx.setEncoding(JsonEncoding.UTF8);
        assertSame("setEncoding should return the same instance", ctx, returned);
        assertEquals(JsonEncoding.UTF8, ctx.getEncoding());
    }

    @Test
    public void markBufferRecyclerReleasedReturnsSameInstance() {
        BufferRecycler br = new BufferRecycler();
        IOContext ctx = new IOContext(srConstr, swc, erc,
                br, null, false, null);

        IOContext returned = ctx.markBufferRecyclerReleased();
        assertSame("markBufferRecyclerReleased should return the same instance", ctx, returned);
    }
}
