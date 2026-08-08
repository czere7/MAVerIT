/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.commons.codec.binary;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.commons.codec.CodecPolicy;
import org.apache.commons.codec.binary.Base16;
import org.apache.commons.codec.binary.Base16OutputStream;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Tests {@link Base16OutputStream}.
 */
public class Base16OutputStreamTest {

    @Test
    public void testEncodeSingleByte() throws IOException {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final Base16OutputStream out = new Base16OutputStream(baos);
        out.write(0x01);
        out.close();
        assertEquals("01", baos.toString());
    }

    @Test
    public void testEncodeMultipleBytes() throws IOException {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final Base16OutputStream out = new Base16OutputStream(baos);
        out.write(new byte[] { 0x01, 0x10, (byte) 0xFF });
        out.close();
        assertEquals("0110FF", baos.toString());
    }

    @Test
    public void testEncodeEmpty() throws IOException {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final Base16OutputStream out = new Base16OutputStream(baos);
        out.write(new byte[] {});
        out.close();
        assertEquals("", baos.toString());
    }

    @Test
    public void testWriteByteArrayOffsetLength() throws IOException {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final Base16OutputStream out = new Base16OutputStream(baos);
        final byte[] data = new byte[] { 0x00, 0x01, 0x02, 0x03 };
        out.write(data, 1, 2); // Write 0x01, 0x02 -> "0102"
        out.close();
        assertEquals("0102", baos.toString());
    }

    @Test
    public void testEncodeWithBuilder() throws IOException {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final Base16OutputStream out = Base16OutputStream.builder().setOutputStream(baos).get();
        out.write(new byte[] { 0x48, 0x65 }); // "He"
        out.close();
        assertEquals("4865", baos.toString());
    }

    @Test
    public void testDecode() throws IOException {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // Write hex string "0102" (bytes [48, 49, 48, 50])
        final Base16OutputStream out = Base16OutputStream.builder().setOutputStream(baos).setEncode(false).get();
        out.write("0102".getBytes());
        out.close();
        // Should decode to bytes [0x01, 0x02]
        assertArrayEquals(new byte[] { 0x01, 0x02 }, baos.toByteArray());
    }

    @Test
    public void testEncodeLowerCase() throws IOException {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // Builder defaults to UpperCase. Use setBaseNCodec to override.
        final Base16OutputStream out = Base16OutputStream.builder()
            .setOutputStream(baos)
            .setBaseNCodec(new Base16(true))
            .get();
        // 0x0A -> '0' 'A' (upper), '0' 'a' (lower)
        out.write(0x0A);
        out.close();
        assertEquals("0a", baos.toString());
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testConstructorWithOutputStream() throws IOException {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final Base16OutputStream out = new Base16OutputStream(baos);
        out.write(0x01);
        out.close();
        assertEquals("01", baos.toString());
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testConstructorWithOutputStreamAndEncode() throws IOException {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // Test encode=true (default behavior)
        final Base16OutputStream outEncode = new Base16OutputStream(baos, true);
        outEncode.write(0x01);
        outEncode.close();
        assertEquals("01", baos.toString());
        
        // Test encode=false
        final ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
        final Base16OutputStream outDecode = new Base16OutputStream(baos2, false);
        outDecode.write("0102".getBytes());
        outDecode.close();
        assertArrayEquals(new byte[] { 0x01, 0x02 }, baos2.toByteArray());
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testConstructorWithOutputStreamEncodeLowerCase() throws IOException {
        // Test encode=true, lowerCase=true
        final ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
        final Base16OutputStream out1 = new Base16OutputStream(baos1, true, true);
        out1.write(0x0A);
        out1.close();
        assertEquals("0a", baos1.toString());
        
        // Test encode=true, lowerCase=false
        final ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
        final Base16OutputStream out2 = new Base16OutputStream(baos2, true, false);
        out2.write(0x0A);
        out2.close();
        assertEquals("0A", baos2.toString());
        
        // Test encode=false, lowerCase=true - must use lowercase hex for decoding
        final ByteArrayOutputStream baos3 = new ByteArrayOutputStream();
        final Base16OutputStream out3 = new Base16OutputStream(baos3, false, true);
        out3.write("0a0a".getBytes()); // lowercase only when lowerCase=true
        out3.close();
        assertArrayEquals(new byte[] { 0x0A, 0x0A }, baos3.toByteArray());
        
        // Test encode=false, lowerCase=false - must use uppercase hex for decoding
        final ByteArrayOutputStream baos4 = new ByteArrayOutputStream();
        final Base16OutputStream out4 = new Base16OutputStream(baos4, false, false);
        out4.write("0A0A".getBytes()); // uppercase only when lowerCase=false
        out4.close();
        assertArrayEquals(new byte[] { 0x0A, 0x0A }, baos4.toByteArray());
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testConstructorWithOutputStreamEncodeLowerCaseDecodingPolicy() throws IOException {
        // Test with STRICT decoding policy
        final ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
        final Base16OutputStream out1 = new Base16OutputStream(baos1, true, true, CodecPolicy.STRICT);
        out1.write(0x0A);
        out1.close();
        assertEquals("0a", baos1.toString());
        
        // Test with LENIENT decoding policy
        final ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
        final Base16OutputStream out2 = new Base16OutputStream(baos2, true, false, CodecPolicy.LENIENT);
        out2.write(0x0A);
        out2.close();
        assertEquals("0A", baos2.toString());
        
        // Test decode with STRICT policy (uppercase)
        final ByteArrayOutputStream baos3 = new ByteArrayOutputStream();
        final Base16OutputStream out3 = new Base16OutputStream(baos3, false, false, CodecPolicy.STRICT);
        out3.write("0102".getBytes());
        out3.close();
        assertArrayEquals(new byte[] { 0x01, 0x02 }, baos3.toByteArray());
        
        // Test decode with LENIENT policy (uppercase)
        final ByteArrayOutputStream baos4 = new ByteArrayOutputStream();
        final Base16OutputStream out4 = new Base16OutputStream(baos4, false, true, CodecPolicy.LENIENT);
        out4.write("0102".getBytes());
        out4.close();
        assertArrayEquals(new byte[] { 0x01, 0x02 }, baos4.toByteArray());
    }
}
