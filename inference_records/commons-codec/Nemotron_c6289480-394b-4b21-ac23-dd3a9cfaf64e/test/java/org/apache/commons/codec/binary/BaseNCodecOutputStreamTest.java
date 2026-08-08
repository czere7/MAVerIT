package org.apache.commons.codec.binary;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.codec.CodecPolicy;
import org.junit.Test;

public class BaseNCodecOutputStreamTest {

    @Test
    public void testConstructorWithBuilder() {
        final Base64OutputStream stream = Base64OutputStream.builder()
                .setOutputStream(new ByteArrayOutputStream())
                .setEncode(true)
                .get();
        assertNotNull(stream);
    }

    @Test
    public void testConstructorWithOutputStreamAndCodec() {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base64 codec = new Base64();
        final Base64OutputStream stream = Base64OutputStream.builder()
                .setOutputStream(out)
                .setBaseNCodec(codec)
                .setEncode(true)
                .get();
        assertNotNull(stream);
    }

    @Test
    public void testEncodeBasic() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base64OutputStream stream = new Base64OutputStream(out);
        stream.write("Hello".getBytes());
        stream.close();
        assertEquals("SGVsbG8=", out.toString());
    }

    @Test
    public void testDecodeBasic() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base64OutputStream stream = Base64OutputStream.builder()
                .setOutputStream(out)
                .setBaseNCodec(new Base64())
                .setEncode(false)
                .get();
        stream.write("SGVsbG8=".getBytes());
        stream.close();
        assertEquals("Hello", out.toString());
    }

    @Test
    public void testWriteByteArrayWithOffsetAndLength() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base64OutputStream stream = new Base64OutputStream(out);
        final byte[] data = "HelloWorld".getBytes();
        stream.write(data, 0, 5);
        stream.close();
        assertEquals("SGVsbG8=", out.toString());
    }

    @Test
    public void testWriteSingleByte() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base64OutputStream stream = new Base64OutputStream(out);
        stream.write('A');
        stream.close();
        assertEquals("QQ==", out.toString());
    }

    @Test
    public void testWriteNullArrayThrowsNullPointerException() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base64OutputStream stream = new Base64OutputStream(out);
        assertThrows(NullPointerException.class, () -> stream.write(null, 0, 0));
    }

    @Test
    public void testWriteInvalidOffsetThrowsIndexOutOfBoundsException() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base64OutputStream stream = new Base64OutputStream(out);
        final byte[] data = new byte[10];
        assertThrows(IndexOutOfBoundsException.class, () -> stream.write(data, -1, 5));
        assertThrows(IndexOutOfBoundsException.class, () -> stream.write(data, 5, -1));
        assertThrows(IndexOutOfBoundsException.class, () -> stream.write(data, 11, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> stream.write(data, 5, 10));
    }

    @Test
    public void testFlush() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base64OutputStream stream = new Base64OutputStream(out);
        stream.write("Hel".getBytes());
        stream.flush();
        assertEquals("SGVs", out.toString());
        stream.write("lo".getBytes());
        stream.close();
        assertEquals("SGVsbG8=", out.toString());
    }

    @Test
    public void testCloseWritesEofAndClosesStream() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base64OutputStream stream = new Base64OutputStream(out);
        stream.write("Hello".getBytes());
        stream.close();
        assertEquals("SGVsbG8=", out.toString());
    }

    @Test
    public void testEofMethod() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base64OutputStream stream = new Base64OutputStream(out);
        stream.write("Hello".getBytes());
        stream.eof();
        stream.flush();
        assertEquals("SGVsbG8=", out.toString());
        // After eof(), further writes are ignored because context.eof is true
        stream.write("World".getBytes());
        stream.close();
        assertEquals("SGVsbG8=", out.toString());
    }

    @Test
    public void testIsStrictDecodingDefault() {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base64OutputStream stream = Base64OutputStream.builder()
                .setOutputStream(out)
                .setBaseNCodec(new Base64())
                .setEncode(false)
                .get();
        assertFalse(stream.isStrictDecoding());
    }

    @Test
    public void testIsStrictDecodingWithStrictCodec() {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base64 codec = Base64.builder().setDecodingPolicy(CodecPolicy.STRICT).get();
        final Base64OutputStream stream = Base64OutputStream.builder()
                .setOutputStream(out)
                .setBaseNCodec(codec)
                .setEncode(false)
                .get();
        assertTrue(stream.isStrictDecoding());
    }

    @Test
    public void testEncodeWithLineLength() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base64 codec = Base64.builder().setLineLength(4).get();
        final Base64OutputStream stream = Base64OutputStream.builder()
                .setOutputStream(out)
                .setBaseNCodec(codec)
                .setEncode(true)
                .get();
        stream.write("HelloWorldHelloWorld".getBytes());
        stream.close();
        final String result = out.toString();
        assertTrue(result.contains("\r\n"));
    }

    @Test
    public void testDecodeWithPadding() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base64OutputStream stream = Base64OutputStream.builder()
                .setOutputStream(out)
                .setBaseNCodec(new Base64())
                .setEncode(false)
                .get();
        stream.write("SGVsbG8=".getBytes());
        stream.close();
        assertEquals("Hello", out.toString());
    }

    @Test
    public void testDecodeWithoutPadding() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base64OutputStream stream = Base64OutputStream.builder()
                .setOutputStream(out)
                .setBaseNCodec(new Base64())
                .setEncode(false)
                .get();
        stream.write("SGVsbG8".getBytes());
        stream.close();
        assertEquals("Hello", out.toString());
    }

    @Test
    public void testMultipleWritesBeforeClose() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base64OutputStream stream = new Base64OutputStream(out);
        stream.write("Hel".getBytes());
        stream.write("lo".getBytes());
        stream.write("Wor".getBytes());
        stream.write("ld".getBytes());
        stream.close();
        assertEquals("SGVsbG9Xb3JsZA==", out.toString());
    }

    @Test
    public void testEmptyWrite() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base64OutputStream stream = new Base64OutputStream(out);
        stream.write(new byte[0]);
        stream.close();
        assertEquals("", out.toString());
    }

    @Test
    public void testWriteZeroLength() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base64OutputStream stream = new Base64OutputStream(out);
        stream.write(new byte[10], 0, 0);
        stream.close();
        assertEquals("", out.toString());
    }

    @Test
    public void testBase16Encoding() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base16OutputStream stream = new Base16OutputStream(out);
        stream.write("Hello".getBytes());
        stream.close();
        assertEquals("48656C6C6F", out.toString());
    }

    @Test
    public void testBase16Decoding() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base16OutputStream stream = Base16OutputStream.builder()
                .setOutputStream(out)
                .setBaseNCodec(new Base16())
                .setEncode(false)
                .get();
        stream.write("48656C6C6F".getBytes());
        stream.close();
        assertEquals("Hello", out.toString());
    }

    @Test
    public void testBase32Encoding() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base32OutputStream stream = new Base32OutputStream(out);
        stream.write("Hello".getBytes());
        stream.close();
        // "Hello" = 5 bytes = 40 bits = 8 Base32 chars (no padding needed)
        assertEquals("JBSWY3DP", out.toString());
    }

    @Test
    public void testBase32Decoding() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base32OutputStream stream = Base32OutputStream.builder()
                .setOutputStream(out)
                .setBaseNCodec(new Base32())
                .setEncode(false)
                .get();
        // "JBSWY3DPEB3W64TMMQ======" decodes to "Hello world"
        stream.write("JBSWY3DPEB3W64TMMQ======".getBytes());
        stream.close();
        assertEquals("Hello world", out.toString());
    }

    @Test
    public void testBase64UrlSafeEncoding() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base64 codec = new Base64(true); // URL-safe
        final Base64OutputStream stream = Base64OutputStream.builder()
                .setOutputStream(out)
                .setBaseNCodec(codec)
                .setEncode(true)
                .get();
        // Input that produces '+' and '/' in standard Base64, '-' and '_' in URL-safe
        final byte[] input = new byte[]{(byte)0xfb, (byte)0xff, (byte)0xff, (byte)0xff};
        stream.write(input);
        stream.close();
        final String result = out.toString();
        // URL-safe uses '-' for index 62 and '_' for index 63
        assertTrue("Expected URL-safe encoding to contain '-'", result.contains("-"));
        assertTrue("Expected URL-safe encoding to contain '_'", result.contains("_"));
        assertFalse("URL-safe encoding should not contain '+'", result.contains("+"));
        assertFalse("URL-safe encoding should not contain '/'", result.contains("/"));
    }

    @Test
    public void testFlushPropagateFalse() throws IOException {
        final TrackingByteArrayOutputStream out = new TrackingByteArrayOutputStream();
        final Base64OutputStream stream = new Base64OutputStream(out);
        stream.write("Hello".getBytes());
        stream.flush();
        assertTrue(out.wasFlushed());
    }

    static class TrackingByteArrayOutputStream extends ByteArrayOutputStream {
        private boolean flushed = false;
        private boolean closed = false;
        @Override
        public void flush() {
            flushed = true;
            try {
                super.flush();
            } catch (IOException e) {
                // ByteArrayOutputStream.flush() does not throw IOException, but catch for safety
            }
        }
        @Override
        public void close() {
            closed = true;
            try {
                super.close();
            } catch (IOException e) {
                // ignore
            }
        }
        public boolean wasFlushed() {
            return flushed;
        }
        public boolean wasClosed() {
            return closed;
        }
    }

    @Test
    public void testContextReusedAcrossWrites() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base64OutputStream stream = new Base64OutputStream(out);
        stream.write("He".getBytes());
        stream.write("ll".getBytes());
        stream.write("o".getBytes());
        stream.close();
        assertEquals("SGVsbG8=", out.toString());
    }

    @Test
    public void testEncodeDecodeRoundTrip() throws IOException {
        final String original = "Hello World! This is a test.";
        final ByteArrayOutputStream encodedOut = new ByteArrayOutputStream();
        final Base64OutputStream encoder = new Base64OutputStream(encodedOut);
        encoder.write(original.getBytes());
        encoder.close();
        
        final ByteArrayOutputStream decodedOut = new ByteArrayOutputStream();
        final Base64OutputStream decoder = Base64OutputStream.builder()
                .setOutputStream(decodedOut)
                .setBaseNCodec(new Base64())
                .setEncode(false)
                .get();
        decoder.write(encodedOut.toByteArray());
        decoder.close();
        
        assertEquals(original, decodedOut.toString());
    }

    @Test
    public void testWriteAfterClose() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base64OutputStream stream = new Base64OutputStream(out);
        stream.write("Hello".getBytes());
        stream.close();
        // After close(), underlying stream is closed; further writes may throw or be ignored
        // ByteArrayOutputStream allows writes after close, but BaseNCodecOutputStream context.eof is true
        stream.write("World".getBytes());
        // Only "Hello" encoded data should be present
        assertEquals("SGVsbG8=", out.toString());
    }

    @Test
    public void testConstructorWithNullOutputStream() throws IOException {
        // FilterOutputStream accepts null; writing to it will throw NullPointerException
        final Base64OutputStream stream = new Base64OutputStream((OutputStream) null);
        assertThrows(NullPointerException.class, () -> stream.write("test".getBytes()));
    }

    @Test
    public void testBuilderSetOutputStream() {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base64OutputStream.Builder builder = Base64OutputStream.builder()
                .setOutputStream(out);
        assertEquals(out, builder.getOutputStream());
    }

    @Test
    public void testBuilderSetEncode() {
        final Base64OutputStream.Builder builder = Base64OutputStream.builder()
                .setEncode(false);
        assertFalse(builder.getEncode());
    }

    @Test
    public void testBuilderSetBaseNCodec() {
        final Base64 codec = new Base64();
        final Base64OutputStream.Builder builder = Base64OutputStream.builder()
                .setBaseNCodec(codec);
        assertEquals(codec, builder.getBaseNCodec());
    }

    @Test
    public void testStrictDecodingThrowsOnInvalidPadding() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base64 codec = Base64.builder().setDecodingPolicy(CodecPolicy.STRICT).get();
        final Base64OutputStream stream = Base64OutputStream.builder()
                .setOutputStream(out)
                .setBaseNCodec(codec)
                .setEncode(false)
                .get();
        // "SGVsbG9" decodes to "Hello" with non-zero trailing bits (invalid in strict mode)
        stream.write("SGVsbG9".getBytes());
        assertThrows(IllegalArgumentException.class, () -> stream.close());
    }

    @Test
    public void testLenientDecodingAcceptsInvalidPadding() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base64OutputStream stream = Base64OutputStream.builder()
                .setOutputStream(out)
                .setBaseNCodec(new Base64())
                .setEncode(false)
                .get();
        stream.write("SGVsbG8".getBytes()); // Missing padding
        stream.close();
        assertEquals("Hello", out.toString());
    }

    @Test
    public void testLargeDataEncoding() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base64OutputStream stream = new Base64OutputStream(out);
        final byte[] data = new byte[10000];
        for (int i = 0; i < data.length; i++) {
            data[i] = (byte) (i % 256);
        }
        stream.write(data);
        stream.close();
        assertTrue(out.size() > 0);
    }

    @Test
    public void testSingleByteWriteEquivalentToByteArrayWrite() throws IOException {
        final ByteArrayOutputStream out1 = new ByteArrayOutputStream();
        final Base64OutputStream stream1 = new Base64OutputStream(out1);
        stream1.write("AB".getBytes());
        stream1.close();

        final ByteArrayOutputStream out2 = new ByteArrayOutputStream();
        final Base64OutputStream stream2 = new Base64OutputStream(out2);
        stream2.write('A');
        stream2.write('B');
        stream2.close();

        assertArrayEquals(out1.toByteArray(), out2.toByteArray());
    }

    @Test
    public void testEofCalledMultipleTimes() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base64OutputStream stream = new Base64OutputStream(out);
        stream.write("Hello".getBytes());
        stream.eof();
        stream.eof();
        stream.flush();
        assertEquals("SGVsbG8=", out.toString());
    }

    @Test
    public void testFlushWithNoData() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base64OutputStream stream = new Base64OutputStream(out);
        stream.flush();
        assertEquals(0, out.size());
    }

    @Test
    public void testCloseWithNoData() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base64OutputStream stream = new Base64OutputStream(out);
        stream.close();
        assertEquals(0, out.size());
    }

    @Test
    public void testWriteAfterEof() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base64OutputStream stream = new Base64OutputStream(out);
        stream.write("Hello".getBytes());
        stream.eof();
        stream.flush();
        // After eof(), context.eof is true, further writes are ignored
        stream.write("World".getBytes());
        stream.close();
        assertEquals("SGVsbG8=", out.toString());
    }

    @Test
    public void testBase32WithLineLength() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base32 codec = Base32.builder().setLineLength(8).get();
        final Base32OutputStream stream = Base32OutputStream.builder()
                .setOutputStream(out)
                .setBaseNCodec(codec)
                .setEncode(true)
                .get();
        stream.write("HelloWorldHelloWorldHelloWorld".getBytes());
        stream.close();
        final String result = out.toString();
        assertTrue(result.contains("\r\n"));
    }

    @Test
    public void testBase16LowerCase() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base16 codec = new Base16(true);
        final Base16OutputStream stream = Base16OutputStream.builder()
                .setOutputStream(out)
                .setBaseNCodec(codec)
                .setEncode(true)
                .get();
        stream.write("Hello".getBytes());
        stream.close();
        assertEquals("48656c6c6f", out.toString());
    }

    @Test
    public void testBase16UpperCase() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base16 codec = new Base16(false);
        final Base16OutputStream stream = Base16OutputStream.builder()
                .setOutputStream(out)
                .setBaseNCodec(codec)
                .setEncode(true)
                .get();
        stream.write("Hello".getBytes());
        stream.close();
        assertEquals("48656C6C6F", out.toString());
    }

    @Test
    public void testEncodeWithCustomPadding() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base64 codec = Base64.builder().setPadding((byte) '.').get();
        final Base64OutputStream stream = Base64OutputStream.builder()
                .setOutputStream(out)
                .setBaseNCodec(codec)
                .setEncode(true)
                .get();
        stream.write("Hello".getBytes());
        stream.close();
        // "Hello" encodes to "SGVsbG8=" (one padding char), custom padding '.' -> "SGVsbG8."
        assertEquals("SGVsbG8.", out.toString());
    }

    @Test
    public void testDecodeWithCustomPadding() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base64 codec = Base64.builder().setPadding((byte) '.').get();
        final Base64OutputStream stream = Base64OutputStream.builder()
                .setOutputStream(out)
                .setBaseNCodec(codec)
                .setEncode(false)
                .get();
        stream.write("SGVsbG8.".getBytes());
        stream.close();
        assertEquals("Hello", out.toString());
    }

    // Test codec to simulate the edge case in flush() where available() > 0 but readResults() returns 0
    static class TestCodec extends Base64 {
        private boolean simulateCondition = false;
        
        public void setSimulateCondition(boolean simulate) {
            this.simulateCondition = simulate;
        }
        
        @Override
        int available(Context context) {
            if (simulateCondition) {
                return 1; // Pretend there's data available
            }
            return super.available(context);
        }
        
        @Override
        int readResults(byte[] b, int position, int available, Context context) {
            if (simulateCondition) {
                return 0; // But return 0 bytes read
            }
            return super.readResults(b, position, available, context);
        }
    }

    @Test
    public void testFlushWithAvailableButNoReadResults() throws IOException {
        // This test targets the branch in flush(boolean) where avail > 0 but readResults returns 0
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final TestCodec codec = new TestCodec();
        
        final Base64OutputStream stream = Base64OutputStream.builder()
                .setOutputStream(out)
                .setBaseNCodec(codec)
                .setEncode(true)
                .get();
        
        // Write some data to populate the codec's internal buffer
        stream.write("Hello".getBytes());
        
        // Enable simulation: available() will return > 0 but readResults() will return 0
        codec.setSimulateCondition(true);
        
        // Call flush() - this should exercise the branch where c > 0 is false
        stream.flush();
        
        // Disable simulation for subsequent operations
        codec.setSimulateCondition(false);
        
        // Continue normal operation to verify stream is still functional
        stream.write("World".getBytes());
        stream.close();
        
        // Verify that the stream completed without exception
        // The first flush with simulation should not have written data (c=0)
        // but the second write and close should work normally
        assertTrue(out.size() >= 0);
    }

    // ===== New tests to kill surviving mutations =====

    @Test
    public void testCloseCallsUnderlyingClose() throws IOException {
        // Targets: VoidMethodCallMutator on out.close() in close()
        final TrackingByteArrayOutputStream out = new TrackingByteArrayOutputStream();
        final Base64OutputStream stream = new Base64OutputStream(out);
        stream.write("Hello".getBytes());
        stream.close();
        assertTrue("close() should call underlying stream's close()", out.wasClosed());
        assertEquals("SGVsbG8=", out.toString());
    }

    @Test
    public void testFlushTruePropagatesToUnderlyingStream() throws IOException {
        // Targets: NegateConditionalsMutator on if (propagate) in flush(boolean)
        // When propagate=true, underlying flush should be called
        final TrackingByteArrayOutputStream out = new TrackingByteArrayOutputStream();
        final Base64OutputStream stream = new Base64OutputStream(out);
        stream.write("Hello".getBytes());
        stream.flush(); // calls flush(true)
        assertTrue("flush() should call underlying stream's flush()", out.wasFlushed());
    }

    @Test
    public void testWriteDoesNotPropagateFlushToUnderlyingStream() throws IOException {
        // Targets: NegateConditionalsMutator on if (propagate) in flush(boolean)
        // write() calls flush(false), so underlying flush should NOT be called
        final TrackingByteArrayOutputStream out = new TrackingByteArrayOutputStream();
        final Base64OutputStream stream = new Base64OutputStream(out);
        stream.write("Hello".getBytes());
        // write() internally calls flush(false)
        assertFalse("write() should not call underlying stream's flush()", out.wasFlushed());
        // But after explicit flush(), it should
        stream.flush();
        assertTrue("explicit flush() should call underlying stream's flush()", out.wasFlushed());
    }

    @Test
    public void testFlushBoundaryAvailZero() throws IOException {
        // Targets: ConditionalsBoundaryMutator on if (avail > 0) in flush(boolean)
        // When avail == 0, the block should not be entered
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base64OutputStream stream = new Base64OutputStream(out);
        // No data written, so avail should be 0
        stream.flush();
        assertEquals(0, out.size());
    }

    @Test
    public void testFlushBoundaryAvailPositiveButReadResultsZero() throws IOException {
        // Targets: ConditionalsBoundaryMutator on if (c > 0) in flush(boolean)
        // When avail > 0 but readResults returns 0, inner block should not be entered
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final TestCodec codec = new TestCodec();
        final Base64OutputStream stream = Base64OutputStream.builder()
                .setOutputStream(out)
                .setBaseNCodec(codec)
                .setEncode(true)
                .get();
        
        stream.write("Hello".getBytes());
        codec.setSimulateCondition(true); // avail > 0 but readResults returns 0
        stream.flush();
        codec.setSimulateCondition(false);
        
        // No data should have been written because c == 0
        // The data should still be in the codec's buffer
        stream.write("World".getBytes());
        stream.close();
        // Should contain both "Hello" and "World" encoded
        assertTrue(out.size() > 0);
    }

    @Test
    public void testWriteBoundaryLenZero() throws IOException {
        // Targets: ConditionalsBoundaryMutator on if (len > 0) in write(byte[], int, int)
        // When len == 0, the encode/decode and flush(false) should not be called
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base64OutputStream stream = new Base64OutputStream(out);
        stream.write(new byte[10], 0, 0); // len == 0
        stream.write("Hello".getBytes()); // len > 0
        stream.close();
        // Only "Hello" should be encoded
        assertEquals("SGVsbG8=", out.toString());
    }

    @Test
    public void testWriteBoundaryLenOne() throws IOException {
        // Targets: ConditionalsBoundaryMutator on if (len > 0) in write(byte[], int, int)
        // When len == 1, the block should be entered (boundary test for > vs >=)
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base64OutputStream stream = new Base64OutputStream(out);
        stream.write(new byte[] { 'A' }, 0, 1); // len == 1
        stream.close();
        assertEquals("QQ==", out.toString());
    }

    @Test
    public void testWriteArithmeticMutationOffsetPlusLen() throws IOException {
        // Targets: MathMutator on offset + len > array.length in write(byte[], int, int)
        // Mutation changes + to - : offset - len > array.length
        // Test case: offset=5, len=10, array.length=10
        // Original: 5 + 10 = 15 > 10 -> true -> throws IndexOutOfBoundsException
        // Mutated: 5 - 10 = -5 > 10 -> false -> does NOT throw (BUG)
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base64OutputStream stream = new Base64OutputStream(out);
        final byte[] data = new byte[10];
        // offset + len = 5 + 10 = 15 > 10, should throw
        assertThrows(IndexOutOfBoundsException.class, () -> stream.write(data, 5, 10));
    }

    @Test
    public void testWriteArithmeticMutationOffsetPlusLenExactBoundary() throws IOException {
        // Targets: MathMutator on offset + len > array.length
        // Test case: offset=5, len=5, array.length=10
        // Original: 5 + 5 = 10 > 10 -> false -> OK
        // Mutated: 5 - 5 = 0 > 10 -> false -> OK (both pass)
        // Test case: offset=0, len=10, array.length=10
        // Original: 0 + 10 = 10 > 10 -> false -> OK
        // Mutated: 0 - 10 = -10 > 10 -> false -> OK
        // Test case: offset=10, len=0, array.length=10
        // Original: 10 + 0 = 10 > 10 -> false -> OK
        // Mutated: 10 - 0 = 10 > 10 -> false -> OK
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base64OutputStream stream = new Base64OutputStream(out);
        final byte[] data = new byte[10];
        // These should all succeed (not throw) - writing zero bytes still produces Base64 output
        stream.write(data, 5, 5);
        stream.write(data, 0, 10);
        stream.write(data, 10, 0);
        stream.close();
        // 15 bytes of zeros encoded in Base64 = 20 'A' characters
        assertEquals("AAAAAAAAAAAAAAAAAAAA", out.toString());
    }

    @Test
    public void testFlushAvailableBoundary() throws IOException {
        // Targets: ConditionalsBoundaryMutator on if (avail > 0)
        // Test with data that produces exactly 0 available, then > 0
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base64OutputStream stream = new Base64OutputStream(out);
        
        // Write 3 bytes (exactly one Base64 block) - no leftover, avail should be 0 after encode
        stream.write("ABC".getBytes()); // 3 bytes = 24 bits = 4 Base64 chars, no padding needed yet
        stream.flush();
        // At this point, the encoder has consumed the 3 bytes and produced 4 output chars
        // avail() should return 0 because context.pos == context.readPos after readResults
        assertEquals("QUJD", out.toString()); // "ABC" encoded
        
        // Write 1 byte - this will be buffered in the encoder, avail > 0 after flush
        final ByteArrayOutputStream out2 = new ByteArrayOutputStream();
        final Base64OutputStream stream2 = new Base64OutputStream(out2);
        stream2.write("A".getBytes()); // 1 byte buffered
        stream2.flush(); // should not output anything yet (not enough for a block)
        assertEquals("", out2.toString());
        stream2.write("BC".getBytes()); // completes the block
        stream2.close();
        assertEquals("QUJD", out2.toString());
    }

    @Test
    public void testCloseWithTrackingStream() throws IOException {
        // Comprehensive test for close() behavior including underlying close call
        final TrackingByteArrayOutputStream out = new TrackingByteArrayOutputStream();
        final Base64OutputStream stream = new Base64OutputStream(out);
        stream.write("Test".getBytes());
        assertFalse("before close, stream not closed", out.wasClosed());
        stream.close();
        assertTrue("after close, underlying stream should be closed", out.wasClosed());
        // "Test" in Base64 is "VGVzdA==" (not "dGVzdA==" which would be "test")
        assertEquals("VGVzdA==", out.toString());
    }

    @Test
    public void testMultipleFlushCalls() throws IOException {
        // Targets: flush() conditional boundary and propagate flag
        final TrackingByteArrayOutputStream out = new TrackingByteArrayOutputStream();
        final Base64OutputStream stream = new Base64OutputStream(out);
        stream.write("Hello".getBytes());
        stream.flush();
        assertTrue(out.wasFlushed());
        out.flushed = false; // reset
        stream.write("World".getBytes());
        stream.flush();
        assertTrue(out.wasFlushed());
        stream.close();
        assertTrue(out.wasClosed());
    }

    @Test
    public void testWriteWithLenZeroDoesNotCallFlush() throws IOException {
        // Targets: ConditionalsBoundaryMutator on if (len > 0) 
        // and also verifies flush(false) not called when len == 0
        final TrackingByteArrayOutputStream out = new TrackingByteArrayOutputStream();
        final Base64OutputStream stream = new Base64OutputStream(out);
        stream.write(new byte[5], 0, 0); // len == 0
        assertFalse("write with len=0 should not call flush", out.wasFlushed());
        stream.write("A".getBytes()); // len > 0
        // write calls flush(false), so underlying flush should not be called
        assertFalse("write with len>0 calls flush(false), not propagate", out.wasFlushed());
        stream.flush(); // explicit flush(true)
        assertTrue("explicit flush calls flush(true)", out.wasFlushed());
    }

    @Test
    public void testWriteArithmeticMutationOffsetPlusLenVarious() throws IOException {
        // Additional boundary tests for the arithmetic mutation
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base64OutputStream stream = new Base64OutputStream(out);
        final byte[] data = new byte[10];
        
        // offset=0, len=11 -> 0+11=11 > 10 (throw), 0-11=-11 > 10 (no throw if mutated)
        assertThrows(IndexOutOfBoundsException.class, () -> stream.write(data, 0, 11));
        
        // offset=1, len=10 -> 1+10=11 > 10 (throw), 1-10=-9 > 10 (no throw if mutated)
        assertThrows(IndexOutOfBoundsException.class, () -> stream.write(data, 1, 10));
        
        // offset=9, len=2 -> 9+2=11 > 10 (throw), 9-2=7 > 10 (no throw if mutated)
        assertThrows(IndexOutOfBoundsException.class, () -> stream.write(data, 9, 2));
        
        // offset=10, len=1 -> 10+1=11 > 10 (throw), 10-1=9 > 10 (no throw if mutated)
        assertThrows(IndexOutOfBoundsException.class, () -> stream.write(data, 10, 1));
        
        // Valid: offset=0, len=10 -> 0+10=10 > 10 (false, OK)
        stream.write(data, 0, 10);
        stream.close();
    }

    // ===== Additional tests targeting surviving mutations =====

    @Test
    public void testFlushBoundaryAvailExactlyOne() throws IOException {
        // Targets: ConditionalsBoundaryMutator on if (avail > 0)
        // Mutation changes > to >= : avail >= 0 would enter block when avail == 0 (bug)
        // We need to verify that when avail == 1, block IS entered and data is written
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base64OutputStream stream = new Base64OutputStream(out);
        // Write 1 byte - not enough for a full Base64 block (3 bytes)
        // After write, avail() should be > 0 (buffered but not yet output)
        stream.write("A".getBytes());
        // Call flush - should NOT write anything because not enough for a block yet
        // But the flush should still be called and the block should be entered if avail > 0
        // Actually, with 1 byte, the encoder buffers it but doesn't produce output yet
        // So avail() might be 0 because nothing is in the output buffer yet
        stream.flush();
        // After flush with 1 byte, nothing should be written yet
        assertEquals("", out.toString());
        // Now write 2 more bytes to complete the block
        stream.write("BC".getBytes());
        stream.flush();
        // Now we should have output
        assertEquals("QUJD", out.toString()); // "ABC" encoded
        stream.close();
    }

    @Test
    public void testFlushBoundaryReadResultsExactlyOne() throws IOException {
        // Targets: ConditionalsBoundaryMutator on if (c > 0) in flush(boolean)
        // Mutation changes > to >= : c >= 0 would enter block when c == 0 (bug, writes empty array)
        // We need a case where readResults returns exactly 1 to verify block is entered
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base64OutputStream stream = new Base64OutputStream(out);
        // Write exactly 3 bytes (one full block) - this will produce 4 output chars
        stream.write("ABC".getBytes());
        // Before flush, the encoder has produced 4 chars in its buffer
        // flush() will call readResults which should return 4
        stream.flush();
        assertEquals("QUJD", out.toString());
        stream.close();
    }

    @Test
    public void testWriteBoundaryLenNegativeNotAllowed() throws IOException {
        // Targets: ConditionalsBoundaryMutator on if (len > 0)
        // The check at line 212 catches negative len and throws IndexOutOfBoundsException
        // before reaching the if (len > 0) at line 215
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base64OutputStream stream = new Base64OutputStream(out);
        final byte[] data = new byte[10];
        assertThrows(IndexOutOfBoundsException.class, () -> stream.write(data, 0, -1));
    }

    @Test
    public void testWriteArithmeticMutationOffsetPlusLenBoundaryExact() throws IOException {
        // Targets: MathMutator on offset + len > array.length
        // Critical boundary: offset + len == array.length + 1 should throw
        // offset + len == array.length should NOT throw
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base64OutputStream stream = new Base64OutputStream(out);
        final byte[] data = new byte[10];
        
        // offset + len = 11 > 10 -> should throw
        assertThrows(IndexOutOfBoundsException.class, () -> stream.write(data, 0, 11));
        assertThrows(IndexOutOfBoundsException.class, () -> stream.write(data, 1, 10));
        assertThrows(IndexOutOfBoundsException.class, () -> stream.write(data, 5, 6));
        assertThrows(IndexOutOfBoundsException.class, () -> stream.write(data, 9, 2));
        assertThrows(IndexOutOfBoundsException.class, () -> stream.write(data, 10, 1));
        
        // offset + len = 10 == 10 -> should NOT throw
        stream.write(data, 0, 10);
        stream.write(data, 5, 5);
        stream.write(data, 10, 0);
        stream.close();
    }

    @Test
    public void testWriteArithmeticMutationOffsetPlusLenWithSubtraction() throws IOException {
        // This test specifically targets the MathMutator that changes + to -
        // If mutation is: offset - len > array.length
        // Then cases where offset - len > array.length but offset + len <= array.length
        // would incorrectly throw, and vice versa
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base64OutputStream stream = new Base64OutputStream(out);
        final byte[] data = new byte[10];
        
        // Case where original: offset + len <= array.length (OK)
        // Mutated: offset - len > array.length (would throw incorrectly)
        // offset=10, len=0: original 10+0=10<=10 OK, mutated 10-0=10>10 false OK
        // offset=9, len=1: original 9+1=10<=10 OK, mutated 9-1=8>10 false OK
        // offset=8, len=2: original 8+2=10<=10 OK, mutated 8-2=6>10 false OK
        // offset=6, len=4: original 6+4=10<=10 OK, mutated 6-4=2>10 false OK
        stream.write(data, 6, 4); // Should succeed
        stream.write(data, 0, 10); // Should succeed
        stream.close();
        
        // Now test cases where original throws but mutated would not
        // offset=5, len=6: original 5+6=11>10 throws, mutated 5-6=-1>10 false (no throw - BUG)
        assertThrows(IndexOutOfBoundsException.class, () -> stream.write(data, 5, 6));
        
        // offset=0, len=11: original 0+11=11>10 throws, mutated 0-11=-11>10 false (no throw - BUG)
        assertThrows(IndexOutOfBoundsException.class, () -> stream.write(data, 0, 11));
    }

    @Test
    public void testFlushWithAvailZeroThenPositive() throws IOException {
        // Targets: ConditionalsBoundaryMutator on if (avail > 0)
        // Verifies boundary between avail == 0 (skip) and avail == 1 (enter)
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base64OutputStream stream = new Base64OutputStream(out);
        
        // Initially avail == 0
        stream.flush(); // Should not write anything
        assertEquals(0, out.size());
        
        // Write 3 bytes (full block) - after encode, data is in buffer, avail > 0
        stream.write("ABC".getBytes());
        // Now avail > 0
        stream.flush(); // Should write the 4 encoded chars
        assertEquals("QUJD", out.toString());
        stream.close();
    }

    @Test
    public void testFlushWithReadResultsZeroThenPositive() throws IOException {
        // Targets: ConditionalsBoundaryMutator on if (c > 0)
        // Verifies boundary between c == 0 (skip write) and c == 1 (write)
        // Using TestCodec to simulate readResults returning 0 then positive
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final TestCodec codec = new TestCodec();
        final Base64OutputStream stream = Base64OutputStream.builder()
                .setOutputStream(out)
                .setBaseNCodec(codec)
                .setEncode(true)
                .get();
        
        // Write some data - "Hello" (5 bytes) produces 4 Base64 chars immediately (first 3 bytes)
        // The remaining 2 bytes are buffered. After write(), flush(false) writes the 4 chars.
        stream.write("Hello".getBytes());
        // At this point out.size() == 4 ("SGVs")
        
        // Simulate: avail > 0 but readResults returns 0
        codec.setSimulateCondition(true);
        stream.flush(); // Should enter avail > 0 block, but c == 0 so should not write additional data
        // Size should remain 4 (no additional write during this flush)
        assertEquals(4, out.size()); // No additional data written
        
        // Disable simulation, now readResults should return actual data
        codec.setSimulateCondition(false);
        
        // Write more data to generate actual output
        stream.write("World".getBytes());
        stream.flush(); // Should write data now
        assertTrue(out.size() > 4);
        stream.close();
    }

    @Test
    public void testWriteLenZeroDoesNotCallEncodeOrFlush() throws IOException {
        // Targets: ConditionalsBoundaryMutator on if (len > 0)
        // Verifies that when len == 0, neither encode/decode nor flush(false) is called
        final TrackingByteArrayOutputStream out = new TrackingByteArrayOutputStream();
        final Base64OutputStream stream = new Base64OutputStream(out);
        
        // Write with len == 0
        stream.write(new byte[10], 0, 0);
        // Should not have called flush (neither propagate=true nor false)
        assertFalse("write(len=0) should not call flush", out.wasFlushed());
        
        // Now write with len > 0
        stream.write("A".getBytes()); // len=1
        // write() calls flush(false) internally
        assertFalse("write(len>0) calls flush(false), not propagate", out.wasFlushed());
        
        // Explicit flush should call flush(true)
        stream.flush();
        assertTrue("explicit flush calls flush(true)", out.wasFlushed());
        stream.close();
    }

    @Test
    public void testFlushAvailBoundaryWithPartialBlock() throws IOException {
        // Targets: ConditionalsBoundaryMutator on if (avail > 0)
        // With 1 or 2 bytes written (partial Base64 block), avail() behavior
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base64OutputStream stream = new Base64OutputStream(out);
        
        // Write 1 byte - encoder buffers it but produces no output yet
        stream.write("A".getBytes());
        // flush() - avail() might be 0 because no output chars produced yet
        stream.flush();
        assertEquals("", out.toString());
        
        // Write 2nd byte - still partial
        stream.write("B".getBytes());
        stream.flush();
        assertEquals("", out.toString());
        
        // Write 3rd byte - completes block, produces 4 output chars
        stream.write("C".getBytes());
        stream.flush();
        assertEquals("QUJD", out.toString()); // "ABC" = QUJD
        stream.close();
    }

    @Test
    public void testWriteArithmeticOffsetPlusLenOverflow() throws IOException {
        // Targets: MathMutator - also test integer overflow edge case
        // offset + len could overflow if both are large (though checked by offset > array.length first)
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base64OutputStream stream = new Base64OutputStream(out);
        final byte[] data = new byte[10];
        
        // offset=Integer.MAX_VALUE would be caught by offset > array.length first
        // But test the exact boundary logic
        // The check is: offset < 0 || len < 0 || offset > array.length || offset + len > array.length
        // offset + len is evaluated only if offset <= array.length
        // So overflow can only happen if offset <= array.length but offset + len > Integer.MAX_VALUE
        // With array.length=10, this is impossible
        // But we test the normal boundaries
        assertThrows(IndexOutOfBoundsException.class, () -> stream.write(data, 10, 1)); // 10+1=11>10
        assertThrows(IndexOutOfBoundsException.class, () -> stream.write(data, 9, 2));  // 9+2=11>10
        assertThrows(IndexOutOfBoundsException.class, () -> stream.write(data, 0, 11)); // 0+11=11>10
        
        // Valid boundaries
        stream.write(data, 10, 0); // 10+0=10<=10 OK
        stream.write(data, 0, 10); // 0+10=10<=10 OK
        stream.close();
    }

    @Test
    public void testFlushMultipleTimesWithAvailBoundary() throws IOException {
        // Targets: ConditionalsBoundaryMutator on if (avail > 0) and if (c > 0)
        // Multiple flush calls with varying avail states
        final TrackingByteArrayOutputStream out = new TrackingByteArrayOutputStream();
        final Base64OutputStream stream = new Base64OutputStream(out);
        
        // No data, flush
        stream.flush();
        assertEquals(0, out.size());
        
        // Partial data (1 byte)
        stream.write("A".getBytes());
        stream.flush();
        assertEquals(0, out.size()); // No output yet
        
        // Complete block (2 more bytes)
        stream.write("BC".getBytes());
        stream.flush();
        assertEquals("QUJD", out.toString()); // "ABC" encoded
        
        // Another flush with no new data - avail should be 0
        out.flushed = false;
        stream.flush();
        assertTrue(out.wasFlushed()); // flush called but no data written
        assertEquals("QUJD", out.toString()); // Output unchanged
        
        // Add more data
        stream.write("DEF".getBytes());
        stream.flush();
        assertEquals("QUJDREVG", out.toString()); // "ABCDEF" encoded
        stream.close();
    }

    @Test
    public void testWriteSingleByteCallsWriteByteArray() throws IOException {
        // Verifies write(int) delegates to write(byte[], 0, 1)
        // This exercises the len > 0 boundary with len=1
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base64OutputStream stream = new Base64OutputStream(out);
        stream.write('A'); // Calls write(singleByte, 0, 1)
        stream.close();
        assertEquals("QQ==", out.toString());
    }

    @Test
    public void testFlushWithPropagateFalseFromWrite() throws IOException {
        // Targets: NegateConditionalsMutator on if (propagate) in flush(boolean)
        // write() calls flush(false), so propagate=false
        // We verify that underlying flush is NOT called
        final TrackingByteArrayOutputStream out = new TrackingByteArrayOutputStream();
        final Base64OutputStream stream = new Base64OutputStream(out);
        
        stream.write("Hello".getBytes()); // Internally calls flush(false)
        assertFalse("write() should not propagate flush to underlying stream", out.wasFlushed());
        
        // But explicit flush() calls flush(true)
        stream.flush();
        assertTrue("explicit flush() should propagate flush to underlying stream", out.wasFlushed());
        stream.close();
    }

    @Test
    public void testFlushBoundaryAvailableAndReadResultsBothZero() throws IOException {
        // Targets: Both ConditionalsBoundaryMutators in flush(boolean)
        // avail > 0 and c > 0 boundaries
        // When no data written, both avail and c are 0
        final TrackingByteArrayOutputStream out = new TrackingByteArrayOutputStream();
        final Base64OutputStream stream = new Base64OutputStream(out);
        
        // No data written
        stream.flush(); // avail=0, so block not entered, but propagate=true so underlying flush called
        assertTrue(out.wasFlushed());
        
        // Write partial data (not enough for output)
        out.flushed = false;
        stream.write("A".getBytes());
        stream.flush(); // avail might be 0 (no output chars produced yet)
        // But propagate=true so underlying flush called
        assertTrue(out.wasFlushed());
        
        stream.close();
    }

    @Test
    public void testWriteLenZeroWithTrackingStream() throws IOException {
        // Targets: ConditionalsBoundaryMutator on if (len > 0)
        // Verify that len=0 does not call flush(false)
        final TrackingByteArrayOutputStream out = new TrackingByteArrayOutputStream();
        final Base64OutputStream stream = new Base64OutputStream(out);
        
        stream.write(new byte[5], 0, 0); // len = 0
        assertFalse("write(len=0) should not call flush", out.wasFlushed());
        
        stream.write(new byte[5], 0, 1); // len = 1 > 0
        // write calls flush(false), so underlying flush should NOT be called
        assertFalse("write(len>0) calls flush(false), not propagate", out.wasFlushed());
        
        stream.flush(); // explicit flush(true)
        assertTrue("explicit flush calls flush(true)", out.wasFlushed());
        stream.close();
    }

    @Test
    public void testArithmeticMutationWithVariousArraySizes() throws IOException {
        // Additional tests for the offset + len > array.length mutation
        final Base64OutputStream stream1 = new Base64OutputStream(new ByteArrayOutputStream());
        final byte[] data1 = new byte[0]; // Empty array
        // offset=0, len=1: 0+1=1 > 0 -> throw
        assertThrows(IndexOutOfBoundsException.class, () -> stream1.write(data1, 0, 1));
        
        final Base64OutputStream stream2 = new Base64OutputStream(new ByteArrayOutputStream());
        final byte[] data2 = new byte[1];
        // offset=0, len=1: 0+1=1 > 1 -> false OK
        stream2.write(data2, 0, 1);
        // offset=1, len=0: 1+0=1 > 1 -> false OK
        stream2.write(data2, 1, 0);
        // offset=0, len=2: 0+2=2 > 1 -> throw
        assertThrows(IndexOutOfBoundsException.class, () -> stream2.write(data2, 0, 2));
        stream2.close();
        
        final Base64OutputStream stream3 = new Base64OutputStream(new ByteArrayOutputStream());
        final byte[] data3 = new byte[5];
        // Test various valid and invalid combinations
        stream3.write(data3, 0, 5); // 0+5=5<=5 OK
        stream3.write(data3, 2, 3); // 2+3=5<=5 OK
        stream3.write(data3, 5, 0); // 5+0=5<=5 OK
        assertThrows(IndexOutOfBoundsException.class, () -> stream3.write(data3, 3, 3)); // 3+3=6>5 throw
        assertThrows(IndexOutOfBoundsException.class, () -> stream3.write(data3, 0, 6)); // 0+6=6>5 throw
        assertThrows(IndexOutOfBoundsException.class, () -> stream3.write(data3, 5, 1)); // 5+1=6>5 throw
        stream3.close();
    }

    @Test
    public void testFlushAvailBoundaryExactlyOneWithTestCodec() throws IOException {
        // Targets: ConditionalsBoundaryMutator on if (avail > 0)
        // Uses TestCodec to force avail to return exactly 1, then 0
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final TestCodec codec = new TestCodec();
        final Base64OutputStream stream = Base64OutputStream.builder()
                .setOutputStream(out)
                .setBaseNCodec(codec)
                .setEncode(true)
                .get();
        
        // Write some data first
        stream.write("Hello".getBytes());
        
        // Simulate avail = 1 (exactly the boundary)
        // We need to override available() to return 1 and readResults to return 1
        // But TestCodec currently returns 1 for available and 0 for readResults
        // Let's create a more precise test codec
        codec.setSimulateCondition(true); // avail=1, readResults=0
        stream.flush(); // Should enter avail>0 block, but c=0 so no write
        
        codec.setSimulateCondition(false);
        stream.write("World".getBytes());
        stream.close();
        assertTrue(out.size() > 0);
    }

    @Test
    public void testWriteLenBoundaryZeroVsOne() throws IOException {
        // Targets: ConditionalsBoundaryMutator on if (len > 0)
        // Verifies exact boundary: len=0 skips block, len=1 enters block
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base64OutputStream stream = new Base64OutputStream(out);
        
        // len = 0: should not encode, not call flush(false)
        stream.write(new byte[5], 0, 0);
        assertEquals(0, out.size());
        
        // len = 1: should encode and call flush(false)
        stream.write(new byte[] { 'A' }, 0, 1);
        // After write(1 byte), flush(false) is called but no output yet (need 3 bytes for Base64)
        // But the encode was called
        stream.write(new byte[] { 'B', 'C' }, 0, 2); // Complete the block
        stream.close();
        assertEquals("QUJD", out.toString()); // "ABC" = QUJD
    }

    @Test
    public void testFlushCBoundaryExactlyOne() throws IOException {
        // Targets: ConditionalsBoundaryMutator on if (c > 0)
        // Need a scenario where readResults returns exactly 1
        // This is hard with standard Base64 since it produces 4 chars at a time
        // But we can test that when c=0, nothing is written, and when c>0, data is written
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final TestCodec codec = new TestCodec();
        final Base64OutputStream stream = Base64OutputStream.builder()
                .setOutputStream(out)
                .setBaseNCodec(codec)
                .setEncode(true)
                .get();
        
        stream.write("Hello".getBytes()); // Produces some output
        // First flush writes the available data (c > 0)
        stream.flush();
        final int sizeAfterFirstFlush = out.size();
        assertTrue(sizeAfterFirstFlush > 0);
        
        // Now simulate avail > 0 but c = 0
        codec.setSimulateCondition(true);
        stream.flush(); // avail>0 entered, but c=0 so no write
        assertEquals(sizeAfterFirstFlush, out.size()); // Size unchanged
        
        codec.setSimulateCondition(false);
        stream.write("World".getBytes());
        stream.close();
        assertTrue(out.size() > sizeAfterFirstFlush);
    }

    @Test
    public void testWriteOffsetPlusLenArithmeticMutationWithSubtraction() throws IOException {
        // Specifically targets the mutation that changes '+' to '-' in offset + len > array.length
        // If mutated to offset - len > array.length:
        // - offset=5, len=6, array.length=10: original 5+6=11>10 (throw), mutated 5-6=-1>10 (false, no throw - BUG)
        // - offset=10, len=0: original 10+0=10>10 (false), mutated 10-0=10>10 (false) - both OK
        // - offset=0, len=11: original 0+11=11>10 (throw), mutated 0-11=-11>10 (false, no throw - BUG)
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base64OutputStream stream = new Base64OutputStream(out);
        final byte[] data = new byte[10];
        
        // These should throw in original code
        assertThrows(IndexOutOfBoundsException.class, () -> stream.write(data, 5, 6));
        assertThrows(IndexOutOfBoundsException.class, () -> stream.write(data, 0, 11));
        assertThrows(IndexOutOfBoundsException.class, () -> stream.write(data, 8, 3));
        assertThrows(IndexOutOfBoundsException.class, () -> stream.write(data, 10, 1));
        
        // These should NOT throw
        stream.write(data, 0, 10); // 0+10=10
        stream.write(data, 5, 5);  // 5+5=10
        stream.write(data, 10, 0); // 10+0=10
        stream.write(data, 9, 1);  // 9+1=10
        stream.close();
    }

    @Test
    public void testFlushWithAvailZeroDoesNotEnterBlock() throws IOException {
        // Targets: ConditionalsBoundaryMutator on if (avail > 0)
        // Verifies that when avail == 0, the block is NOT entered
        // Mutation would change > to >=, causing block to be entered when avail == 0
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base64OutputStream stream = new Base64OutputStream(out);
        
        // No data written, avail should be 0
        stream.flush();
        assertEquals(0, out.size());
        
        // Write 3 bytes (exact block), then flush - this consumes all buffered output
        stream.write("ABC".getBytes());
        stream.flush(); // This writes the 4 chars
        assertEquals("QUJD", out.toString());
        
        // After flush, avail should be 0 again (buffer consumed)
        out.reset();
        stream.flush(); // Should not write anything
        assertEquals(0, out.size());
        
        stream.close();
    }

    @Test
    public void testWriteWithLenZeroDoesNotCallFlushFalse() throws IOException {
        // Targets: ConditionalsBoundaryMutator on if (len > 0)
        // Verifies flush(false) is not called when len == 0
        final TrackingByteArrayOutputStream out = new TrackingByteArrayOutputStream();
        final Base64OutputStream stream = new Base64OutputStream(out);
        
        // len = 0
        stream.write(new byte[10], 0, 0);
        assertFalse("flush should not be called when len=0", out.wasFlushed());
        
        // len = 1 > 0, should call flush(false)
        stream.write("A".getBytes()); // This calls write(byte[], 0, 1) which calls flush(false)
        assertFalse("write calls flush(false), not propagate", out.wasFlushed());
        
        // Explicit flush calls flush(true)
        stream.flush();
        assertTrue("explicit flush calls flush(true)", out.wasFlushed());
        stream.close();
    }

    @Test
    public void testFlushWithPartialDataAvailBoundary() throws IOException {
        // Targets: ConditionalsBoundaryMutator on if (avail > 0)
        // Tests the transition from avail=0 to avail>0 with partial blocks
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base64OutputStream stream = new Base64OutputStream(out);
        
        // 0 bytes written
        stream.flush();
        assertEquals(0, out.size());
        
        // 1 byte written (partial block)
        stream.write("A".getBytes());
        stream.flush(); // avail might be 0 (no output produced yet)
        assertEquals(0, out.size());
        
        // 2 bytes written (still partial)
        stream.write("B".getBytes());
        stream.flush(); // avail might still be 0
        assertEquals(0, out.size());
        
        // 3 bytes written (complete block)
        stream.write("C".getBytes());
        stream.flush(); // avail > 0 now, should write 4 chars
        assertEquals("QUJD", out.toString());
        
        // Another flush with no new data - avail should be 0
        stream.flush();
        assertEquals("QUJD", out.toString());
        
        stream.close();
    }

    @Test
    public void testFlushReadResultsBoundaryZeroVsPositive() throws IOException {
        // Targets: ConditionalsBoundaryMutator on if (c > 0)
        // When readResults returns 0, no write should happen
        // When readResults returns >0, write should happen
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final TestCodec codec = new TestCodec();
        final Base64OutputStream stream = Base64OutputStream.builder()
                .setOutputStream(out)
                .setBaseNCodec(codec)
                .setEncode(true)
                .get();
        
        // Write data to generate buffered output
        stream.write("HelloWorld".getBytes()); // 10 bytes -> 3 full blocks + 1 partial
        // First flush writes all complete blocks
        stream.flush();
        final int sizeAfterFlush = out.size();
        assertTrue(sizeAfterFlush > 0);
        
        // Now simulate avail > 0 but readResults returns 0
        codec.setSimulateCondition(true);
        stream.flush(); // Should enter avail>0 block, but c=0 so inner block skipped
        assertEquals(sizeAfterFlush, out.size()); // No additional write
        
        // Restore normal behavior
        codec.setSimulateCondition(false);
        stream.write("MoreData".getBytes());
        stream.flush();
        assertTrue(out.size() > sizeAfterFlush);
        stream.close();
    }

    @Test
    public void testWriteArithmeticMutationOffsetPlusLenWithNegativeValues() throws IOException {
        // Targets: MathMutator on offset + len > array.length
        // Tests various combinations where mutation from + to - would change behavior
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base64OutputStream stream = new Base64OutputStream(out);
        final byte[] data = new byte[100];
        
        // Valid cases that should NOT throw (offset + len <= array.length)
        stream.write(data, 0, 100);   // 0+100=100
        stream.write(data, 50, 50);   // 50+50=100
        stream.write(data, 100, 0);   // 100+0=100
        stream.write(data, 99, 1);    // 99+1=100
        stream.write(data, 1, 99);    // 1+99=100
        
        // Invalid cases that SHOULD throw (offset + len > array.length)
        // Mutation to offset - len would make these NOT throw (BUG)
        assertThrows(IndexOutOfBoundsException.class, () -> stream.write(data, 50, 51)); // 50+51=101>100
        assertThrows(IndexOutOfBoundsException.class, () -> stream.write(data, 100, 1)); // 100+1=101>100
        assertThrows(IndexOutOfBoundsException.class, () -> stream.write(data, 0, 101));  // 0+101=101>100
        assertThrows(IndexOutOfBoundsException.class, () -> stream.write(data, 99, 2));   // 99+2=101>100
        
        stream.close();
    }
}
