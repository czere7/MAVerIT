import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegressionTest0 {

    public static boolean debug = false;

    public void assertBooleanArrayEquals(boolean[] expectedArray, boolean[] actualArray) {
        if (expectedArray.length != actualArray.length) {
            throw new AssertionError("Array lengths differ: " + expectedArray.length + " != " + actualArray.length);
        }
        for (int i = 0; i < expectedArray.length; i++) {
            if (expectedArray[i] != actualArray[i]) {
                throw new AssertionError("Arrays differ at index " + i + ": " + expectedArray[i] + " != " + actualArray[i]);
            }
        }
    }

    @Test
    public void test001() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test001");
        // The following exception was thrown during execution in test generation
        try {
            org.apache.commons.codec.digest.HmacUtils hmacUtils2 = new org.apache.commons.codec.digest.HmacUtils("hi!", "");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Empty key");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test002() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test002");
        java.lang.String[] strArray0 = org.apache.commons.codec.digest.MessageDigestAlgorithms.values();
        org.junit.Assert.assertNotNull(strArray0);
        org.junit.Assert.assertArrayEquals(strArray0, new java.lang.String[] { "MD2", "MD5", "SHA-1", "SHA-224", "SHA-256", "SHA-384", "SHA-512", "SHA-512/224", "SHA-512/256", "SHA3-224", "SHA3-256", "SHA3-384", "SHA3-512", "SHAKE128-256", "SHAKE256-512" });
    }

    @Test
    public void test003() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test003");
        byte[] byteArray0 = null;
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean1 = org.apache.commons.codec.binary.Base64.isBase64(byteArray0);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test004() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test004");
        org.apache.commons.codec.digest.Crc16 crc16_0 = org.apache.commons.codec.digest.Crc16.ccitt();
        org.junit.Assert.assertNotNull(crc16_0);
    }

    @Test
    public void test005() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test005");
        byte[] byteArray2 = new byte[] { (byte) -1, (byte) 10 };
        java.util.Random random4 = null;
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str5 = org.apache.commons.codec.digest.Sha2Crypt.sha256Crypt(byteArray2, "hi!", random4);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Invalid salt value: hi!");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray2);
        org.junit.Assert.assertArrayEquals(byteArray2, new byte[] { (byte) -1, (byte) 10 });
    }

    @Test
    public void test006() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test006");
        // The following exception was thrown during execution in test generation
        try {
            java.security.MessageDigest messageDigest0 = org.apache.commons.codec.digest.DigestUtils.getSha512_256Digest();
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: java.security.NoSuchAlgorithmException: SHA-512/256 MessageDigest not available");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test007() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test007");
        javax.crypto.Mac mac0 = null;
        byte[] byteArray7 = new byte[] { (byte) 0, (byte) 100, (byte) 0, (byte) 1, (byte) 1, (byte) 100 };
        // The following exception was thrown during execution in test generation
        try {
            javax.crypto.Mac mac8 = org.apache.commons.codec.digest.HmacUtils.updateHmac(mac0, byteArray7);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray7);
        org.junit.Assert.assertArrayEquals(byteArray7, new byte[] { (byte) 0, (byte) 100, (byte) 0, (byte) 1, (byte) 1, (byte) 100 });
    }

    @Test
    public void test008() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test008");
        byte[] byteArray0 = null;
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str1 = org.apache.commons.codec.digest.Crypt.crypt(byteArray0);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test009() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test009");
        byte[] byteArray6 = new byte[] { (byte) 0, (byte) 10, (byte) 10, (byte) 10, (byte) 10, (byte) 1 };
        // The following exception was thrown during execution in test generation
        try {
            int int8 = org.apache.commons.codec.digest.MurmurHash3.hash32(byteArray6, 100);
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: 6");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray6);
        org.junit.Assert.assertArrayEquals(byteArray6, new byte[] { (byte) 0, (byte) 10, (byte) 10, (byte) 10, (byte) 10, (byte) 1 });
    }

    @Test
    public void test010() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test010");
        java.lang.String str0 = org.apache.commons.codec.digest.MessageDigestAlgorithms.MD2;
        org.junit.Assert.assertEquals("'" + str0 + "' != '" + "MD2" + "'", str0, "MD2");
    }

    @Test
    public void test011() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test011");
        byte[] byteArray4 = new byte[] { (byte) 100, (byte) 10, (byte) 0, (byte) 0 };
        java.io.InputStream inputStream5 = null;
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str6 = org.apache.commons.codec.digest.HmacUtils.hmacSha384Hex(byteArray4, inputStream5);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray4);
        org.junit.Assert.assertArrayEquals(byteArray4, new byte[] { (byte) 100, (byte) 10, (byte) 0, (byte) 0 });
    }

    @Test
    public void test012() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test012");
        org.apache.commons.codec.digest.GitIdentifiers.FileMode fileMode0 = org.apache.commons.codec.digest.GitIdentifiers.FileMode.DIRECTORY;
        org.junit.Assert.assertTrue("'" + fileMode0 + "' != '" + org.apache.commons.codec.digest.GitIdentifiers.FileMode.DIRECTORY + "'", fileMode0.equals(org.apache.commons.codec.digest.GitIdentifiers.FileMode.DIRECTORY));
    }

    @Test
    public void test013() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test013");
        org.apache.commons.codec.digest.Crc16.Builder builder0 = org.apache.commons.codec.digest.Crc16.builder();
        org.junit.Assert.assertNotNull(builder0);
    }

    @Test
    public void test014() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test014");
        long long1 = org.apache.commons.codec.digest.MurmurHash3.hash64((int) (byte) 100);
        org.junit.Assert.assertTrue("'" + long1 + "' != '" + 6577096405996463154L + "'", long1 == 6577096405996463154L);
    }

    @Test
    public void test015() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test015");
        java.nio.ByteBuffer byteBuffer0 = null;
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str1 = org.apache.commons.codec.binary.Hex.encodeHexString(byteBuffer0);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test016() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test016");
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str1 = org.apache.commons.codec.digest.DigestUtils.sha3_256Hex("");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: java.security.NoSuchAlgorithmException: SHA3-256 MessageDigest not available");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test017() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test017");
        java.io.InputStream inputStream0 = null;
        // The following exception was thrown during execution in test generation
        try {
            byte[] byteArray1 = org.apache.commons.codec.digest.DigestUtils.sha3_512(inputStream0);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: java.security.NoSuchAlgorithmException: SHA3-512 MessageDigest not available");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test018() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test018");
        java.lang.String str0 = org.apache.commons.codec.digest.MessageDigestAlgorithms.SHA_512;
        org.junit.Assert.assertEquals("'" + str0 + "' != '" + "SHA-512" + "'", str0, "SHA-512");
    }

    @Test
    public void test019() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test019");
        java.io.InputStream inputStream0 = null;
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str1 = org.apache.commons.codec.digest.DigestUtils.sha512Hex(inputStream0);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test020() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test020");
        byte[] byteArray0 = new byte[] {};
        // The following exception was thrown during execution in test generation
        try {
            long long4 = org.apache.commons.codec.digest.MurmurHash3.hash64(byteArray0, 100, (int) (byte) 1, 10);
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: 100");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray0);
        org.junit.Assert.assertArrayEquals(byteArray0, new byte[] {});
    }

    @Test
    public void test021() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test021");
        org.apache.commons.codec.Resources resources0 = new org.apache.commons.codec.Resources();
    }

    @Test
    public void test022() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test022");
        byte[] byteArray0 = null;
        byte[] byteArray1 = org.apache.commons.codec.binary.Base64.encodeBase64(byteArray0);
        org.junit.Assert.assertNull(byteArray1);
    }

    @Test
    public void test023() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test023");
        java.nio.charset.Charset charset0 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.apache.commons.codec.net.QCodec qCodec1 = new org.apache.commons.codec.net.QCodec(charset0);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: charset");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test024() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test024");
        byte[] byteArray2 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str3 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray2);
        java.io.InputStream inputStream4 = null;
        // The following exception was thrown during execution in test generation
        try {
            byte[] byteArray5 = org.apache.commons.codec.digest.HmacUtils.hmacSha256(byteArray2, inputStream4);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray2);
        org.junit.Assert.assertArrayEquals(byteArray2, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str3 + "' != '" + "\000" + "'", str3, "\000");
    }

    @Test
    public void test025() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test025");
        java.lang.String str2 = org.apache.commons.codec.digest.HmacUtils.hmacSha512Hex("\000", "SHA-512");
        org.junit.Assert.assertEquals("'" + str2 + "' != '" + "201853cbefef57ae7964e7c7c9d45f5a6181e885bb6ac9fd9219405e1d002b4c840dd2971f2b03900c9a6531eb470a5031425b4a8b14c684174cd97402968242" + "'", str2, "201853cbefef57ae7964e7c7c9d45f5a6181e885bb6ac9fd9219405e1d002b4c840dd2971f2b03900c9a6531eb470a5031425b4a8b14c684174cd97402968242");
    }

    @Test
    public void test026() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test026");
        java.io.InputStream inputStream0 = null;
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str1 = org.apache.commons.codec.digest.DigestUtils.shaHex(inputStream0);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test027() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test027");
        // The following exception was thrown during execution in test generation
        try {
            java.security.MessageDigest messageDigest0 = org.apache.commons.codec.digest.DigestUtils.getSha3_224Digest();
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: java.security.NoSuchAlgorithmException: SHA3-224 MessageDigest not available");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test028() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test028");
        // The following exception was thrown during execution in test generation
        try {
            int int3 = org.apache.commons.codec.digest.MurmurHash2.hash32("MD2", (int) (short) 1, (int) (byte) 10);
            org.junit.Assert.fail("Expected exception of type java.lang.StringIndexOutOfBoundsException; message: String index out of range: 11");
        } catch (java.lang.StringIndexOutOfBoundsException e) {
            // Expected exception.
        }
    }

    @Test
    public void test029() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test029");
        org.apache.commons.codec.binary.Base32 base32_0 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray3 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str4 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray3);
        boolean boolean6 = base32_0.isInAlphabet(byteArray3, false);
        org.apache.commons.codec.binary.Base32 base32_7 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray10 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str11 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray10);
        boolean boolean13 = base32_7.isInAlphabet(byteArray10, false);
        byte[] byteArray14 = org.apache.commons.codec.digest.HmacUtils.hmacMd5(byteArray3, byteArray10);
        org.apache.commons.codec.binary.Base32 base32_15 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray18 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str19 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray18);
        boolean boolean21 = base32_15.isInAlphabet(byteArray18, false);
        char[] charArray22 = null;
        byte[] byteArray23 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray22);
        boolean boolean25 = base32_15.isInAlphabet(byteArray23, true);
        // The following exception was thrown during execution in test generation
        try {
            byte[] byteArray26 = org.apache.commons.codec.digest.Blake3.keyedHash(byteArray14, byteArray23);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Blake3 keys must be 32 bytes");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertArrayEquals(byteArray3, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "\000" + "'", str4, "\000");
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNotNull(byteArray10);
        org.junit.Assert.assertArrayEquals(byteArray10, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "\000" + "'", str11, "\000");
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
        org.junit.Assert.assertNotNull(byteArray14);
        org.junit.Assert.assertArrayEquals(byteArray14, new byte[] { (byte) -53, (byte) -62, (byte) 109, (byte) 108, (byte) 16, (byte) 34, (byte) -87, (byte) -93, (byte) 59, (byte) 95, (byte) 90, (byte) 76, (byte) 18, (byte) -71, (byte) -99, (byte) -51 });
        org.junit.Assert.assertNotNull(byteArray18);
        org.junit.Assert.assertArrayEquals(byteArray18, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str19 + "' != '" + "\000" + "'", str19, "\000");
        org.junit.Assert.assertTrue("'" + boolean21 + "' != '" + false + "'", boolean21 == false);
        org.junit.Assert.assertNotNull(byteArray23);
        org.junit.Assert.assertArrayEquals(byteArray23, new byte[] {});
        org.junit.Assert.assertTrue("'" + boolean25 + "' != '" + true + "'", boolean25 == true);
    }

    @Test
    public void test030() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test030");
        org.apache.commons.codec.binary.Base32 base32_0 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray3 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str4 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray3);
        boolean boolean6 = base32_0.isInAlphabet(byteArray3, false);
        org.apache.commons.codec.binary.Base32 base32_7 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray10 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str11 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray10);
        boolean boolean13 = base32_7.isInAlphabet(byteArray10, false);
        byte[] byteArray14 = org.apache.commons.codec.digest.HmacUtils.hmacMd5(byteArray3, byteArray10);
        java.util.Random random17 = null;
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str18 = org.apache.commons.codec.digest.Md5Crypt.md5Crypt(byteArray10, "hi!", "h4V99TZaVFEkk", random17);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Invalid prefix value: h4V99TZaVFEkk");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertArrayEquals(byteArray3, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "\000" + "'", str4, "\000");
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNotNull(byteArray10);
        org.junit.Assert.assertArrayEquals(byteArray10, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "\000" + "'", str11, "\000");
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
        org.junit.Assert.assertNotNull(byteArray14);
        org.junit.Assert.assertArrayEquals(byteArray14, new byte[] { (byte) -53, (byte) -62, (byte) 109, (byte) 108, (byte) 16, (byte) 34, (byte) -87, (byte) -93, (byte) 59, (byte) 95, (byte) 90, (byte) 76, (byte) 18, (byte) -71, (byte) -99, (byte) -51 });
    }

    @Test
    public void test031() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test031");
        org.apache.commons.codec.binary.Base32 base32_1 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray4 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str5 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray4);
        boolean boolean7 = base32_1.isInAlphabet(byteArray4, false);
        org.apache.commons.codec.binary.Base32 base32_8 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray11 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str12 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray11);
        boolean boolean14 = base32_8.isInAlphabet(byteArray11, false);
        byte[] byteArray15 = org.apache.commons.codec.digest.HmacUtils.hmacMd5(byteArray4, byteArray11);
        org.apache.commons.codec.CodecPolicy codecPolicy18 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.apache.commons.codec.binary.Base32 base32_19 = new org.apache.commons.codec.binary.Base32(100, byteArray15, false, (byte) 1, codecPolicy18);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: lineSeparator must not contain Base32 characters: [??ml?\"??;_ZL????]");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray4);
        org.junit.Assert.assertArrayEquals(byteArray4, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "\000" + "'", str5, "\000");
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertNotNull(byteArray11);
        org.junit.Assert.assertArrayEquals(byteArray11, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str12 + "' != '" + "\000" + "'", str12, "\000");
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + false + "'", boolean14 == false);
        org.junit.Assert.assertNotNull(byteArray15);
        org.junit.Assert.assertArrayEquals(byteArray15, new byte[] { (byte) -53, (byte) -62, (byte) 109, (byte) 108, (byte) 16, (byte) 34, (byte) -87, (byte) -93, (byte) 59, (byte) 95, (byte) 90, (byte) 76, (byte) 18, (byte) -71, (byte) -99, (byte) -51 });
    }

    @Test
    public void test032() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test032");
        java.security.MessageDigest messageDigest0 = org.apache.commons.codec.digest.DigestUtils.getShaDigest();
        java.nio.ByteBuffer byteBuffer1 = null;
        // The following exception was thrown during execution in test generation
        try {
            java.security.MessageDigest messageDigest2 = org.apache.commons.codec.digest.DigestUtils.updateDigest(messageDigest0, byteBuffer1);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(messageDigest0);
        org.junit.Assert.assertEquals(messageDigest0.toString(), "SHA-1 Message Digest from SUN, <initialized>\r\n");
    }

    @Test
    public void test033() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test033");
        java.lang.String str0 = org.apache.commons.codec.CharEncoding.ISO_8859_1;
        org.junit.Assert.assertEquals("'" + str0 + "' != '" + "ISO-8859-1" + "'", str0, "ISO-8859-1");
    }

    @Test
    public void test034() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test034");
        java.io.InputStream inputStream0 = null;
        org.apache.commons.codec.binary.Base16InputStream base16InputStream1 = new org.apache.commons.codec.binary.Base16InputStream(inputStream0);
        // The following exception was thrown during execution in test generation
        try {
            byte[] byteArray2 = org.apache.commons.codec.digest.DigestUtils.sha3_224((java.io.InputStream) base16InputStream1);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: java.security.NoSuchAlgorithmException: SHA3-224 MessageDigest not available");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test035() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test035");
        long long1 = org.apache.commons.codec.digest.MurmurHash3.hash64((long) ' ');
        org.junit.Assert.assertTrue("'" + long1 + "' != '" + (-2181777162901928215L) + "'", long1 == (-2181777162901928215L));
    }

    @Test
    public void test036() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test036");
        java.security.MessageDigest messageDigest0 = org.apache.commons.codec.digest.DigestUtils.getShaDigest();
        java.io.RandomAccessFile randomAccessFile1 = null;
        // The following exception was thrown during execution in test generation
        try {
            byte[] byteArray2 = org.apache.commons.codec.digest.DigestUtils.digest(messageDigest0, randomAccessFile1);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(messageDigest0);
        org.junit.Assert.assertEquals(messageDigest0.toString(), "SHA-1 Message Digest from SUN, <initialized>\r\n");
    }

    @Test
    public void test037() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test037");
        boolean boolean1 = org.apache.commons.codec.digest.DigestUtils.isAvailable("ISO-8859-1");
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
    }

    @Test
    public void test038() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test038");
        byte[] byteArray3 = org.apache.commons.codec.digest.HmacUtils.hmacSha384("\000", "SHA-512");
        org.apache.commons.codec.CodecPolicy codecPolicy5 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.apache.commons.codec.binary.Base64 base64_6 = new org.apache.commons.codec.binary.Base64(0, byteArray3, false, codecPolicy5);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: lineSeparator must not contain base64 characters: [??F?81??}?V???????q????I??;????M-?g??i$?+Kv?\"{4]");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray3);
    }

    @Test
    public void test039() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test039");
        java.nio.ByteBuffer byteBuffer0 = null;
        // The following exception was thrown during execution in test generation
        try {
            char[] charArray1 = org.apache.commons.codec.binary.Hex.encodeHex(byteBuffer0);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test040() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test040");
        // The following exception was thrown during execution in test generation
        try {
            int int3 = org.apache.commons.codec.digest.MurmurHash2.hash32("0000000000000000", 0, (int) (byte) 100);
            org.junit.Assert.fail("Expected exception of type java.lang.StringIndexOutOfBoundsException; message: String index out of range: 100");
        } catch (java.lang.StringIndexOutOfBoundsException e) {
            // Expected exception.
        }
    }

    @Test
    public void test041() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test041");
        // The following exception was thrown during execution in test generation
        try {
            byte[] byteArray1 = org.apache.commons.codec.digest.DigestUtils.shake256_512("Crc16 [init=0xFFFF, crc=0xFFFF, xorOut=0x0000, crc^xorOut=0xFFFF]");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: java.security.NoSuchAlgorithmException: SHAKE256-512 MessageDigest not available");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test042() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test042");
        java.lang.String str1 = org.apache.commons.codec.digest.DigestUtils.md2Hex("ISO-8859-1");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "c39394a96ebd177a239a817fe8453ab0" + "'", str1, "c39394a96ebd177a239a817fe8453ab0");
    }

    @Test
    public void test043() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test043");
        java.io.InputStream inputStream0 = null;
        org.apache.commons.codec.binary.Base16InputStream base16InputStream1 = new org.apache.commons.codec.binary.Base16InputStream(inputStream0);
        boolean boolean2 = base16InputStream1.markSupported();
        // The following exception was thrown during execution in test generation
        try {
            long long4 = base16InputStream1.skip((long) '#');
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
    }

    @Test
    public void test044() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test044");
        java.lang.String str0 = org.apache.commons.codec.digest.MessageDigestAlgorithms.SHA_224;
        org.junit.Assert.assertEquals("'" + str0 + "' != '" + "SHA-224" + "'", str0, "SHA-224");
    }

    @Test
    public void test045() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test045");
        // The following exception was thrown during execution in test generation
        try {
            org.apache.commons.codec.net.QuotedPrintableCodec quotedPrintableCodec1 = new org.apache.commons.codec.net.QuotedPrintableCodec("MD2");
            org.junit.Assert.fail("Expected exception of type java.nio.charset.UnsupportedCharsetException; message: MD2");
        } catch (java.nio.charset.UnsupportedCharsetException e) {
            // Expected exception.
        }
    }

    @Test
    public void test046() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test046");
        int int2 = org.apache.commons.codec.digest.MurmurHash3.hash32((-1L), 0L);
        org.junit.Assert.assertTrue("'" + int2 + "' != '" + (-342794719) + "'", int2 == (-342794719));
    }

    @Test
    public void test047() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test047");
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str1 = org.apache.commons.codec.digest.DigestUtils.shake128_256Hex("h4V99TZaVFEkk");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: java.security.NoSuchAlgorithmException: SHAKE128-256 MessageDigest not available");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test048() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test048");
        org.apache.commons.codec.binary.Base32 base32_1 = new org.apache.commons.codec.binary.Base32((byte) 0);
    }

    @Test
    public void test049() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test049");
        java.lang.String str2 = org.apache.commons.codec.digest.HmacUtils.hmacSha1Hex("\000", "c39394a96ebd177a239a817fe8453ab0");
        org.junit.Assert.assertEquals("'" + str2 + "' != '" + "8404a7262f45838faa05f26c92d87d10524773d9" + "'", str2, "8404a7262f45838faa05f26c92d87d10524773d9");
    }

    @Test
    public void test050() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test050");
        org.apache.commons.codec.language.bm.Rule.Phoneme phoneme0 = null;
        org.apache.commons.codec.language.bm.Rule.Phoneme phoneme1 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.apache.commons.codec.language.bm.Rule.Phoneme phoneme2 = new org.apache.commons.codec.language.bm.Rule.Phoneme(phoneme0, phoneme1);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test051() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test051");
        org.apache.commons.codec.binary.Base32 base32_0 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray3 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str4 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray3);
        boolean boolean6 = base32_0.isInAlphabet(byteArray3, false);
        char[] charArray7 = null;
        byte[] byteArray8 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray7);
        boolean boolean10 = base32_0.isInAlphabet(byteArray8, true);
        boolean boolean11 = base32_0.isStrictDecoding();
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertArrayEquals(byteArray3, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "\000" + "'", str4, "\000");
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNotNull(byteArray8);
        org.junit.Assert.assertArrayEquals(byteArray8, new byte[] {});
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
    }

    @Test
    public void test052() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test052");
        java.security.MessageDigest messageDigest0 = org.apache.commons.codec.digest.DigestUtils.getShaDigest();
        org.apache.commons.codec.digest.DigestUtils digestUtils1 = new org.apache.commons.codec.digest.DigestUtils(messageDigest0);
        byte[] byteArray3 = digestUtils1.digest("8404a7262f45838faa05f26c92d87d10524773d9");
        org.apache.commons.codec.binary.Base32 base32_4 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray7 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str8 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray7);
        boolean boolean10 = base32_4.isInAlphabet(byteArray7, false);
        org.apache.commons.codec.binary.Base32 base32_11 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray14 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str15 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray14);
        boolean boolean17 = base32_11.isInAlphabet(byteArray14, false);
        java.lang.String str18 = org.apache.commons.codec.digest.UnixCrypt.crypt(byteArray14);
        java.lang.String str19 = org.apache.commons.codec.digest.HmacUtils.hmacSha1Hex(byteArray7, byteArray14);
        java.lang.String str20 = org.apache.commons.codec.binary.BinaryCodec.toAsciiString(byteArray14);
        // The following exception was thrown during execution in test generation
        try {
            byte[] byteArray21 = org.apache.commons.codec.digest.Blake3.keyedHash(byteArray3, byteArray14);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Blake3 keys must be 32 bytes");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(messageDigest0);
        org.junit.Assert.assertEquals(messageDigest0.toString(), "SHA-1 Message Digest from SUN, <initialized>\r\n");
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertArrayEquals(byteArray3, new byte[] { (byte) 18, (byte) -86, (byte) 24, (byte) -94, (byte) 7, (byte) -126, (byte) -37, (byte) -81, (byte) 92, (byte) -41, (byte) 35, (byte) -91, (byte) -65, (byte) 115, (byte) 41, (byte) -106, (byte) 2, (byte) -107, (byte) 125, (byte) -21 });
        org.junit.Assert.assertNotNull(byteArray7);
        org.junit.Assert.assertArrayEquals(byteArray7, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "\000" + "'", str8, "\000");
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        org.junit.Assert.assertNotNull(byteArray14);
        org.junit.Assert.assertArrayEquals(byteArray14, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str15 + "' != '" + "\000" + "'", str15, "\000");
        org.junit.Assert.assertTrue("'" + boolean17 + "' != '" + false + "'", boolean17 == false);
// flaky "1) test052(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str18 + "' != '" + "N33RwWpTbQvLA" + "'", str18, "N33RwWpTbQvLA");
        org.junit.Assert.assertEquals("'" + str19 + "' != '" + "310354661a5962d5b8cb76032d5a97e8aed7cf9f" + "'", str19, "310354661a5962d5b8cb76032d5a97e8aed7cf9f");
        org.junit.Assert.assertEquals("'" + str20 + "' != '" + "0000000000000000" + "'", str20, "0000000000000000");
    }

    @Test
    public void test053() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test053");
        java.lang.String str1 = org.apache.commons.codec.digest.DigestUtils.sha1Hex("8404a7262f45838faa05f26c92d87d10524773d9");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "12aa18a20782dbaf5cd723a5bf73299602957deb" + "'", str1, "12aa18a20782dbaf5cd723a5bf73299602957deb");
    }

    @Test
    public void test054() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test054");
        int int3 = org.apache.commons.codec.digest.MurmurHash3.hash32((-2181777162901928215L), (long) (byte) 10, (int) (byte) 1);
        org.junit.Assert.assertTrue("'" + int3 + "' != '" + (-559363818) + "'", int3 == (-559363818));
    }

    @Test
    public void test055() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test055");
        java.nio.charset.Charset charset0 = null;
        org.apache.commons.codec.net.QuotedPrintableCodec quotedPrintableCodec2 = new org.apache.commons.codec.net.QuotedPrintableCodec(charset0, true);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj4 = quotedPrintableCodec2.encode((java.lang.Object) 10);
            org.junit.Assert.fail("Expected exception of type org.apache.commons.codec.EncoderException; message: Objects of type java.lang.Integer cannot be quoted-printable encoded");
        } catch (org.apache.commons.codec.EncoderException e) {
            // Expected exception.
        }
    }

    @Test
    public void test056() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test056");
        org.apache.commons.codec.binary.Base32 base32_0 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray3 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str4 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray3);
        boolean boolean6 = base32_0.isInAlphabet(byteArray3, false);
        org.apache.commons.codec.binary.Base32 base32_7 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray10 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str11 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray10);
        boolean boolean13 = base32_7.isInAlphabet(byteArray10, false);
        byte[] byteArray14 = org.apache.commons.codec.digest.HmacUtils.hmacMd5(byteArray3, byteArray10);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str16 = org.apache.commons.codec.digest.Md5Crypt.md5Crypt(byteArray3, "8404a7262f45838faa05f26c92d87d10524773d9");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Invalid salt value: 8404a7262f45838faa05f26c92d87d10524773d9");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertArrayEquals(byteArray3, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "\000" + "'", str4, "\000");
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNotNull(byteArray10);
        org.junit.Assert.assertArrayEquals(byteArray10, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "\000" + "'", str11, "\000");
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
        org.junit.Assert.assertNotNull(byteArray14);
        org.junit.Assert.assertArrayEquals(byteArray14, new byte[] { (byte) -53, (byte) -62, (byte) 109, (byte) 108, (byte) 16, (byte) 34, (byte) -87, (byte) -93, (byte) 59, (byte) 95, (byte) 90, (byte) 76, (byte) 18, (byte) -71, (byte) -99, (byte) -51 });
    }

    @Test
    public void test057() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test057");
        byte[] byteArray2 = org.apache.commons.codec.digest.HmacUtils.hmacSha512("310354661a5962d5b8cb76032d5a97e8aed7cf9f", "");
        java.lang.String str3 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Le(byteArray2);
        org.junit.Assert.assertNotNull(byteArray2);
        org.junit.Assert.assertEquals("'" + str3 + "' != '" + "\u1edd\u0e62\ua78f\uf062\u2618\uf49a\uef13\u8aff\uf770\uad34\u84d0\u786d\u8c34\ue9e4\u2e84\ub8c0\u1124\uead2\ubf02\u0e25\ufffd\u1fbd\u7ca4\u9ac3\ueddd\ua971\u9bbd\uc267\u2d48\u55f3\u825d" + "'", str3, "\u1edd\u0e62\ua78f\uf062\u2618\uf49a\uef13\u8aff\uf770\uad34\u84d0\u786d\u8c34\ue9e4\u2e84\ub8c0\u1124\uead2\ubf02\u0e25\ufffd\u1fbd\u7ca4\u9ac3\ueddd\ua971\u9bbd\uc267\u2d48\u55f3\u825d");
    }

    @Test
    public void test058() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test058");
        byte[] byteArray0 = null;
        byte[] byteArray7 = new byte[] { (byte) 10, (byte) 0, (byte) -1, (byte) 0, (byte) 1, (byte) 100 };
        java.lang.String str8 = org.apache.commons.codec.digest.Md5Crypt.md5Crypt(byteArray7);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str9 = org.apache.commons.codec.digest.HmacUtils.hmacSha512Hex(byteArray0, byteArray7);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Null key");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray7);
        org.junit.Assert.assertArrayEquals(byteArray7, new byte[] { (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0 });
// flaky "2) test058(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str8 + "' != '" + "$1$3TjYSHEN$BHbr08pgf5W/.TQGudY4X/" + "'", str8, "$1$3TjYSHEN$BHbr08pgf5W/.TQGudY4X/");
    }

    @Test
    public void test059() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test059");
        org.apache.commons.codec.digest.Crc16 crc16_0 = org.apache.commons.codec.digest.Crc16.usb();
        crc16_0.reset();
        org.junit.Assert.assertNotNull(crc16_0);
    }

    @Test
    public void test060() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test060");
        org.apache.commons.codec.binary.Base32 base32_1 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray4 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str5 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray4);
        boolean boolean7 = base32_1.isInAlphabet(byteArray4, false);
        org.apache.commons.codec.binary.Base32 base32_8 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray11 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str12 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray11);
        boolean boolean14 = base32_8.isInAlphabet(byteArray11, false);
        java.lang.String str15 = org.apache.commons.codec.digest.UnixCrypt.crypt(byteArray11);
        java.lang.String str16 = org.apache.commons.codec.digest.HmacUtils.hmacSha1Hex(byteArray4, byteArray11);
        java.lang.String str17 = org.apache.commons.codec.digest.DigestUtils.shaHex(byteArray11);
        org.apache.commons.codec.CodecPolicy codecPolicy20 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.apache.commons.codec.binary.Base32 base32_21 = new org.apache.commons.codec.binary.Base32((int) (short) 1, byteArray11, true, (byte) 0, codecPolicy20);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: lineSeparator must not contain Base32 characters: [??]");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray4);
        org.junit.Assert.assertArrayEquals(byteArray4, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "\000" + "'", str5, "\000");
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertNotNull(byteArray11);
        org.junit.Assert.assertArrayEquals(byteArray11, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str12 + "' != '" + "\000" + "'", str12, "\000");
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + false + "'", boolean14 == false);
// flaky "3) test060(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str15 + "' != '" + "4BY/jGMUsyrOo" + "'", str15, "4BY/jGMUsyrOo");
        org.junit.Assert.assertEquals("'" + str16 + "' != '" + "310354661a5962d5b8cb76032d5a97e8aed7cf9f" + "'", str16, "310354661a5962d5b8cb76032d5a97e8aed7cf9f");
        org.junit.Assert.assertEquals("'" + str17 + "' != '" + "1489f923c4dca729178b3e3233458550d8dddf29" + "'", str17, "1489f923c4dca729178b3e3233458550d8dddf29");
    }

    @Test
    public void test061() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test061");
        org.apache.commons.codec.language.Soundex soundex1 = new org.apache.commons.codec.language.Soundex("310354661a5962d5b8cb76032d5a97e8aed7cf9f");
        org.apache.commons.codec.digest.Crc16 crc16_2 = org.apache.commons.codec.digest.Crc16.modbus();
        java.lang.String str3 = crc16_2.toString();
        java.lang.Object obj4 = soundex1.encode((java.lang.Object) str3);
        org.junit.Assert.assertNotNull(crc16_2);
        org.junit.Assert.assertEquals("'" + str3 + "' != '" + "Crc16 [init=0xFFFF, crc=0xFFFF, xorOut=0x0000, crc^xorOut=0xFFFF]" + "'", str3, "Crc16 [init=0xFFFF, crc=0xFFFF, xorOut=0x0000, crc^xorOut=0xFFFF]");
        org.junit.Assert.assertEquals("'" + obj4 + "' != '" + "C812" + "'", obj4, "C812");
    }

    @Test
    public void test062() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test062");
        byte[] byteArray1 = org.apache.commons.codec.binary.StringUtils.getBytesIso8859_1("Crc16 [init=0xFFFF, crc=0xFFFF, xorOut=0x0000, crc^xorOut=0xFFFF]");
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str2 = org.apache.commons.codec.digest.DigestUtils.sha3_224Hex(byteArray1);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: java.security.NoSuchAlgorithmException: SHA3-224 MessageDigest not available");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray1);
    }

    @Test
    public void test063() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test063");
        org.apache.commons.codec.binary.Base32 base32_0 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray3 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str4 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray3);
        boolean boolean6 = base32_0.isInAlphabet(byteArray3, false);
        char[] charArray7 = null;
        byte[] byteArray8 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray7);
        boolean boolean10 = base32_0.isInAlphabet(byteArray8, true);
        java.util.Random random11 = null;
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str12 = org.apache.commons.codec.digest.Md5Crypt.apr1Crypt(byteArray8, random11);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertArrayEquals(byteArray3, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "\000" + "'", str4, "\000");
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNotNull(byteArray8);
        org.junit.Assert.assertArrayEquals(byteArray8, new byte[] {});
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
    }

    @Test
    public void test064() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test064");
        java.io.InputStream inputStream0 = null;
        org.apache.commons.codec.binary.Base16InputStream base16InputStream1 = new org.apache.commons.codec.binary.Base16InputStream(inputStream0);
        char[] charArray4 = null;
        byte[] byteArray5 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray4);
        char[] charArray6 = org.apache.commons.codec.binary.Hex.encodeHex(byteArray5);
        byte[] byteArray7 = org.apache.commons.codec.binary.Hex.decodeHex(charArray6);
        org.apache.commons.codec.CodecPolicy codecPolicy8 = null;
        org.apache.commons.codec.binary.Base64InputStream base64InputStream9 = new org.apache.commons.codec.binary.Base64InputStream((java.io.InputStream) base16InputStream1, true, (int) (short) 100, byteArray7, codecPolicy8);
        byte[] byteArray11 = org.apache.commons.codec.binary.StringUtils.getBytesIso8859_1("c39394a96ebd177a239a817fe8453ab0");
        // The following exception was thrown during execution in test generation
        try {
            int int14 = base64InputStream9.read(byteArray11, (-342794719), (int) ' ');
            org.junit.Assert.fail("Expected exception of type java.lang.IndexOutOfBoundsException; message: null");
        } catch (java.lang.IndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray5);
        org.junit.Assert.assertArrayEquals(byteArray5, new byte[] {});
        org.junit.Assert.assertNotNull(charArray6);
        org.junit.Assert.assertArrayEquals(charArray6, new char[] {});
        org.junit.Assert.assertNotNull(byteArray7);
        org.junit.Assert.assertArrayEquals(byteArray7, new byte[] {});
        org.junit.Assert.assertNotNull(byteArray11);
    }

    @Test
    public void test065() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test065");
        byte[] byteArray2 = org.apache.commons.codec.digest.HmacUtils.hmacSha512("310354661a5962d5b8cb76032d5a97e8aed7cf9f", "");
        // The following exception was thrown during execution in test generation
        try {
            char[] charArray6 = org.apache.commons.codec.binary.Hex.encodeHex(byteArray2, (int) (short) -1, (int) (short) 10, true);
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: -1");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray2);
    }

    @Test
    public void test066() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test066");
        byte[] byteArray6 = new byte[] { (byte) 10, (byte) 0, (byte) -1, (byte) 0, (byte) 1, (byte) 100 };
        java.lang.String str7 = org.apache.commons.codec.digest.Md5Crypt.md5Crypt(byteArray6);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str8 = org.apache.commons.codec.digest.DigestUtils.sha3_384Hex(byteArray6);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: java.security.NoSuchAlgorithmException: SHA3-384 MessageDigest not available");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray6);
        org.junit.Assert.assertArrayEquals(byteArray6, new byte[] { (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0 });
// flaky "4) test066(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str7 + "' != '" + "$1$W7jLeKhg$Gay9ezbooPAkGjSBArdP./" + "'", str7, "$1$W7jLeKhg$Gay9ezbooPAkGjSBArdP./");
    }

    @Test
    public void test067() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test067");
        byte[] byteArray0 = org.apache.commons.codec.binary.BaseNCodec.getChunkSeparator();
        org.junit.Assert.assertNotNull(byteArray0);
        org.junit.Assert.assertArrayEquals(byteArray0, new byte[] { (byte) 13, (byte) 10 });
    }

    @Test
    public void test068() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test068");
        java.security.MessageDigest messageDigest0 = org.apache.commons.codec.digest.DigestUtils.getShaDigest();
        org.apache.commons.codec.digest.DigestUtils digestUtils1 = new org.apache.commons.codec.digest.DigestUtils(messageDigest0);
        java.nio.ByteBuffer byteBuffer2 = null;
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str3 = digestUtils1.digestAsHex(byteBuffer2);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(messageDigest0);
        org.junit.Assert.assertEquals(messageDigest0.toString(), "SHA-1 Message Digest from SUN, <initialized>\r\n");
    }

    @Test
    public void test069() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test069");
        int int1 = org.apache.commons.codec.digest.MurmurHash3.hash32((long) (byte) 1);
        org.junit.Assert.assertTrue("'" + int1 + "' != '" + (-913662660) + "'", int1 == (-913662660));
    }

    @Test
    public void test070() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test070");
        java.lang.String str0 = org.apache.commons.codec.digest.MessageDigestAlgorithms.SHA_384;
        org.junit.Assert.assertEquals("'" + str0 + "' != '" + "SHA-384" + "'", str0, "SHA-384");
    }

    @Test
    public void test071() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test071");
        org.apache.commons.codec.binary.Base32 base32_0 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray3 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str4 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray3);
        boolean boolean6 = base32_0.isInAlphabet(byteArray3, false);
        java.lang.String str7 = org.apache.commons.codec.digest.UnixCrypt.crypt(byteArray3);
        byte[] byteArray8 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(byteArray3);
        java.io.InputStream inputStream9 = null;
        org.apache.commons.codec.binary.Base16InputStream base16InputStream10 = new org.apache.commons.codec.binary.Base16InputStream(inputStream9);
        boolean boolean11 = base16InputStream10.markSupported();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str12 = org.apache.commons.codec.digest.HmacUtils.hmacMd5Hex(byteArray8, (java.io.InputStream) base16InputStream10);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Empty key");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertArrayEquals(byteArray3, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "\000" + "'", str4, "\000");
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
// flaky "5) test071(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str7 + "' != '" + ".pOKOjsxhoGHA" + "'", str7, ".pOKOjsxhoGHA");
        org.junit.Assert.assertNotNull(byteArray8);
        org.junit.Assert.assertArrayEquals(byteArray8, new byte[] {});
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
    }

    @Test
    public void test072() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test072");
        org.apache.commons.codec.language.Soundex soundex1 = new org.apache.commons.codec.language.Soundex("310354661a5962d5b8cb76032d5a97e8aed7cf9f");
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj3 = soundex1.encode((java.lang.Object) (byte) 10);
            org.junit.Assert.fail("Expected exception of type org.apache.commons.codec.EncoderException; message: Parameter supplied to Soundex encode is not of type java.lang.String");
        } catch (org.apache.commons.codec.EncoderException e) {
            // Expected exception.
        }
    }

    @Test
    public void test073() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test073");
        java.io.InputStream inputStream0 = null;
        org.apache.commons.codec.binary.Base16InputStream base16InputStream1 = new org.apache.commons.codec.binary.Base16InputStream(inputStream0);
        boolean boolean2 = base16InputStream1.markSupported();
        org.apache.commons.codec.binary.Base32InputStream base32InputStream4 = new org.apache.commons.codec.binary.Base32InputStream((java.io.InputStream) base16InputStream1, false);
        org.apache.commons.codec.binary.Base32 base32_5 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray8 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str9 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray8);
        boolean boolean11 = base32_5.isInAlphabet(byteArray8, false);
        org.apache.commons.codec.binary.Base32 base32_12 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray15 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str16 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray15);
        boolean boolean18 = base32_12.isInAlphabet(byteArray15, false);
        java.lang.String str19 = org.apache.commons.codec.digest.UnixCrypt.crypt(byteArray15);
        java.lang.String str20 = org.apache.commons.codec.digest.HmacUtils.hmacSha1Hex(byteArray8, byteArray15);
        java.lang.String str21 = org.apache.commons.codec.binary.BinaryCodec.toAsciiString(byteArray15);
        byte[] byteArray22 = org.apache.commons.codec.binary.Base64.encodeBase64Chunked(byteArray15);
        // The following exception was thrown during execution in test generation
        try {
            int int25 = base32InputStream4.read(byteArray15, 0, (-913662660));
            org.junit.Assert.fail("Expected exception of type java.lang.IndexOutOfBoundsException; message: null");
        } catch (java.lang.IndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(byteArray8);
        org.junit.Assert.assertArrayEquals(byteArray8, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str9 + "' != '" + "\000" + "'", str9, "\000");
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
        org.junit.Assert.assertNotNull(byteArray15);
        org.junit.Assert.assertArrayEquals(byteArray15, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str16 + "' != '" + "\000" + "'", str16, "\000");
        org.junit.Assert.assertTrue("'" + boolean18 + "' != '" + false + "'", boolean18 == false);
// flaky "6) test073(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str19 + "' != '" + "d3Mans5FeAYM." + "'", str19, "d3Mans5FeAYM.");
        org.junit.Assert.assertEquals("'" + str20 + "' != '" + "310354661a5962d5b8cb76032d5a97e8aed7cf9f" + "'", str20, "310354661a5962d5b8cb76032d5a97e8aed7cf9f");
        org.junit.Assert.assertEquals("'" + str21 + "' != '" + "0000000000000000" + "'", str21, "0000000000000000");
        org.junit.Assert.assertNotNull(byteArray22);
        org.junit.Assert.assertArrayEquals(byteArray22, new byte[] { (byte) 65, (byte) 65, (byte) 65, (byte) 61, (byte) 13, (byte) 10 });
    }

    @Test
    public void test074() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test074");
        org.apache.commons.codec.binary.Base32 base32_0 = new org.apache.commons.codec.binary.Base32();
        org.apache.commons.codec.net.PercentCodec percentCodec1 = new org.apache.commons.codec.net.PercentCodec();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj2 = base32_0.decode((java.lang.Object) percentCodec1);
            org.junit.Assert.fail("Expected exception of type org.apache.commons.codec.DecoderException; message: Parameter supplied to Base-N decode is not a byte[] or a String");
        } catch (org.apache.commons.codec.DecoderException e) {
            // Expected exception.
        }
    }

    @Test
    public void test075() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test075");
        java.io.InputStream inputStream0 = null;
        org.apache.commons.codec.binary.Base16InputStream base16InputStream1 = new org.apache.commons.codec.binary.Base16InputStream(inputStream0);
        byte[] byteArray10 = new byte[] { (byte) 10, (byte) 0, (byte) -1, (byte) 0, (byte) 1, (byte) 100 };
        java.lang.String str11 = org.apache.commons.codec.digest.Md5Crypt.md5Crypt(byteArray10);
        org.apache.commons.codec.CodecPolicy codecPolicy12 = null;
        org.apache.commons.codec.binary.Base64InputStream base64InputStream13 = new org.apache.commons.codec.binary.Base64InputStream((java.io.InputStream) base16InputStream1, false, (int) 'a', byteArray10, codecPolicy12);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str14 = org.apache.commons.codec.digest.DigestUtils.sha3_224Hex((java.io.InputStream) base16InputStream1);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: java.security.NoSuchAlgorithmException: SHA3-224 MessageDigest not available");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray10);
        org.junit.Assert.assertArrayEquals(byteArray10, new byte[] { (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0 });
// flaky "7) test075(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str11 + "' != '" + "$1$ze.soYRi$FkHArrWpz750NVrjon4AT." + "'", str11, "$1$ze.soYRi$FkHArrWpz750NVrjon4AT.");
    }

    @Test
    public void test076() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test076");
        org.apache.commons.codec.binary.Base32 base32_0 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray3 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str4 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray3);
        boolean boolean6 = base32_0.isInAlphabet(byteArray3, false);
        java.io.InputStream inputStream7 = null;
        org.apache.commons.codec.binary.Base16InputStream base16InputStream8 = new org.apache.commons.codec.binary.Base16InputStream(inputStream7);
        base16InputStream8.mark(0);
        // The following exception was thrown during execution in test generation
        try {
            byte[] byteArray11 = org.apache.commons.codec.digest.HmacUtils.hmacSha512(byteArray3, (java.io.InputStream) base16InputStream8);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertArrayEquals(byteArray3, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "\000" + "'", str4, "\000");
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
    }

    @Test
    public void test077() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test077");
        long long1 = org.apache.commons.codec.digest.MurmurHash3.hash64((short) (byte) 10);
        org.junit.Assert.assertTrue("'" + long1 + "' != '" + (-8350299967407043051L) + "'", long1 == (-8350299967407043051L));
    }

    @Test
    public void test078() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test078");
        java.io.InputStream inputStream0 = null;
        org.apache.commons.codec.binary.Base16InputStream base16InputStream1 = new org.apache.commons.codec.binary.Base16InputStream(inputStream0);
        char[] charArray4 = null;
        byte[] byteArray5 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray4);
        char[] charArray6 = org.apache.commons.codec.binary.Hex.encodeHex(byteArray5);
        byte[] byteArray7 = org.apache.commons.codec.binary.Hex.decodeHex(charArray6);
        org.apache.commons.codec.CodecPolicy codecPolicy8 = null;
        org.apache.commons.codec.binary.Base64InputStream base64InputStream9 = new org.apache.commons.codec.binary.Base64InputStream((java.io.InputStream) base16InputStream1, true, (int) (short) 100, byteArray7, codecPolicy8);
        char[] charArray10 = null;
        byte[] byteArray11 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray10);
        long[] longArray12 = org.apache.commons.codec.digest.MurmurHash3.hash128x64(byteArray11);
        // The following exception was thrown during execution in test generation
        try {
            int int15 = base16InputStream1.read(byteArray11, (int) (short) 100, 10);
            org.junit.Assert.fail("Expected exception of type java.lang.IndexOutOfBoundsException; message: null");
        } catch (java.lang.IndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray5);
        org.junit.Assert.assertArrayEquals(byteArray5, new byte[] {});
        org.junit.Assert.assertNotNull(charArray6);
        org.junit.Assert.assertArrayEquals(charArray6, new char[] {});
        org.junit.Assert.assertNotNull(byteArray7);
        org.junit.Assert.assertArrayEquals(byteArray7, new byte[] {});
        org.junit.Assert.assertNotNull(byteArray11);
        org.junit.Assert.assertArrayEquals(byteArray11, new byte[] {});
        org.junit.Assert.assertNotNull(longArray12);
        org.junit.Assert.assertArrayEquals(longArray12, new long[] { 0L, 0L });
    }

    @Test
    public void test079() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test079");
        java.io.OutputStream outputStream0 = null;
        org.apache.commons.codec.binary.Base64OutputStream base64OutputStream1 = new org.apache.commons.codec.binary.Base64OutputStream(outputStream0);
        org.apache.commons.codec.binary.Base32 base32_4 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray7 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str8 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray7);
        boolean boolean10 = base32_4.isInAlphabet(byteArray7, false);
        byte[] byteArray13 = org.apache.commons.codec.digest.HmacUtils.hmacSha512("310354661a5962d5b8cb76032d5a97e8aed7cf9f", "");
        byte[] byteArray14 = base32_4.encode(byteArray13);
        // The following exception was thrown during execution in test generation
        try {
            org.apache.commons.codec.binary.Base32OutputStream base32OutputStream15 = new org.apache.commons.codec.binary.Base32OutputStream(outputStream0, true, (int) (byte) 100, byteArray14);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: lineSeparator must not contain Base32 characters: [3UPGEDUPU5RPAGBGTL2BH377RJYPONFN2CCG26BURTSOTBBOYC4CIEOS5IBL6JIOAXNX2SF5D6SHZQ423XWXDKN5TNT4ESBN6NKV3AQ=]");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray7);
        org.junit.Assert.assertArrayEquals(byteArray7, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "\000" + "'", str8, "\000");
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        org.junit.Assert.assertNotNull(byteArray13);
        org.junit.Assert.assertNotNull(byteArray14);
    }

    @Test
    public void test080() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test080");
        java.security.MessageDigest messageDigest0 = org.apache.commons.codec.digest.DigestUtils.getShaDigest();
        java.io.RandomAccessFile randomAccessFile1 = null;
        // The following exception was thrown during execution in test generation
        try {
            java.security.MessageDigest messageDigest2 = org.apache.commons.codec.digest.DigestUtils.updateDigest(messageDigest0, randomAccessFile1);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(messageDigest0);
        org.junit.Assert.assertEquals(messageDigest0.toString(), "SHA-1 Message Digest from SUN, <initialized>\r\n");
    }

    @Test
    public void test081() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test081");
        java.io.InputStream inputStream0 = null;
        org.apache.commons.codec.binary.Base16InputStream base16InputStream1 = new org.apache.commons.codec.binary.Base16InputStream(inputStream0);
        // The following exception was thrown during execution in test generation
        try {
            byte[] byteArray2 = org.apache.commons.codec.digest.DigestUtils.md2(inputStream0);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test082() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test082");
        org.apache.commons.codec.digest.GitIdentifiers.FileMode fileMode0 = org.apache.commons.codec.digest.GitIdentifiers.FileMode.REGULAR;
        org.junit.Assert.assertTrue("'" + fileMode0 + "' != '" + org.apache.commons.codec.digest.GitIdentifiers.FileMode.REGULAR + "'", fileMode0.equals(org.apache.commons.codec.digest.GitIdentifiers.FileMode.REGULAR));
    }

    @Test
    public void test083() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test083");
        // The following exception was thrown during execution in test generation
        try {
            java.security.MessageDigest messageDigest0 = org.apache.commons.codec.digest.DigestUtils.getSha3_512Digest();
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: java.security.NoSuchAlgorithmException: SHA3-512 MessageDigest not available");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test084() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test084");
        java.io.InputStream inputStream0 = null;
        org.apache.commons.codec.binary.Base16InputStream base16InputStream1 = new org.apache.commons.codec.binary.Base16InputStream(inputStream0);
        boolean boolean2 = base16InputStream1.markSupported();
        org.apache.commons.codec.binary.Base32InputStream base32InputStream4 = new org.apache.commons.codec.binary.Base32InputStream((java.io.InputStream) base16InputStream1, false);
        // The following exception was thrown during execution in test generation
        try {
            byte[] byteArray5 = org.apache.commons.codec.digest.DigestUtils.sha1((java.io.InputStream) base32InputStream4);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
    }

    @Test
    public void test085() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test085");
        org.apache.commons.codec.binary.Hex hex0 = new org.apache.commons.codec.binary.Hex();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj2 = hex0.decode((java.lang.Object) "hi!");
            org.junit.Assert.fail("Expected exception of type org.apache.commons.codec.DecoderException; message: Odd number of characters 3.");
        } catch (org.apache.commons.codec.DecoderException e) {
            // Expected exception.
        }
    }

    @Test
    public void test086() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test086");
        java.lang.String str0 = org.apache.commons.codec.digest.MessageDigestAlgorithms.SHA_1;
        org.junit.Assert.assertEquals("'" + str0 + "' != '" + "SHA-1" + "'", str0, "SHA-1");
    }

    @Test
    public void test087() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test087");
        java.io.InputStream inputStream0 = null;
        org.apache.commons.codec.binary.Base16InputStream base16InputStream1 = new org.apache.commons.codec.binary.Base16InputStream(inputStream0);
        boolean boolean2 = base16InputStream1.markSupported();
        org.apache.commons.codec.binary.Base32InputStream base32InputStream4 = new org.apache.commons.codec.binary.Base32InputStream((java.io.InputStream) base16InputStream1, false);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str5 = org.apache.commons.codec.digest.DigestUtils.sha512Hex((java.io.InputStream) base32InputStream4);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
    }

    @Test
    public void test088() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test088");
        byte[] byteArray1 = org.apache.commons.codec.binary.StringUtils.getBytesIso8859_1("Crc16 [init=0xFFFF, crc=0xFFFF, xorOut=0x0000, crc^xorOut=0xFFFF]");
        // The following exception was thrown during execution in test generation
        try {
            byte[] byteArray2 = org.apache.commons.codec.digest.DigestUtils.sha3_256(byteArray1);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: java.security.NoSuchAlgorithmException: SHA3-256 MessageDigest not available");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray1);
    }

    @Test
    public void test089() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test089");
        org.apache.commons.codec.binary.Base32 base32_1 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray4 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str5 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray4);
        boolean boolean7 = base32_1.isInAlphabet(byteArray4, false);
        org.apache.commons.codec.binary.Base32 base32_8 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray11 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str12 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray11);
        boolean boolean14 = base32_8.isInAlphabet(byteArray11, false);
        java.lang.String str15 = org.apache.commons.codec.digest.UnixCrypt.crypt(byteArray11);
        java.lang.String str16 = org.apache.commons.codec.digest.HmacUtils.hmacSha1Hex(byteArray4, byteArray11);
        java.lang.String str17 = org.apache.commons.codec.binary.BinaryCodec.toAsciiString(byteArray11);
        byte[] byteArray18 = org.apache.commons.codec.binary.Base64.encodeBase64Chunked(byteArray11);
        // The following exception was thrown during execution in test generation
        try {
            javax.crypto.Mac mac19 = org.apache.commons.codec.digest.HmacUtils.getInitializedMac("$1$o2wObY1P$1FFjPqFKp4Qa0VfAPDD2T.", byteArray18);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: java.security.NoSuchAlgorithmException: Algorithm $1$o2wObY1P$1FFjPqFKp4Qa0VfAPDD2T. not available");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray4);
        org.junit.Assert.assertArrayEquals(byteArray4, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "\000" + "'", str5, "\000");
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertNotNull(byteArray11);
        org.junit.Assert.assertArrayEquals(byteArray11, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str12 + "' != '" + "\000" + "'", str12, "\000");
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + false + "'", boolean14 == false);
// flaky "8) test089(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str15 + "' != '" + "o8qyh.Rv6Y/2U" + "'", str15, "o8qyh.Rv6Y/2U");
        org.junit.Assert.assertEquals("'" + str16 + "' != '" + "310354661a5962d5b8cb76032d5a97e8aed7cf9f" + "'", str16, "310354661a5962d5b8cb76032d5a97e8aed7cf9f");
        org.junit.Assert.assertEquals("'" + str17 + "' != '" + "0000000000000000" + "'", str17, "0000000000000000");
        org.junit.Assert.assertNotNull(byteArray18);
        org.junit.Assert.assertArrayEquals(byteArray18, new byte[] { (byte) 65, (byte) 65, (byte) 65, (byte) 61, (byte) 13, (byte) 10 });
    }

    @Test
    public void test090() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test090");
        org.apache.commons.codec.binary.Base32 base32_0 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray3 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str4 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray3);
        boolean boolean6 = base32_0.isInAlphabet(byteArray3, false);
        org.apache.commons.codec.binary.Base32 base32_7 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray10 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str11 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray10);
        boolean boolean13 = base32_7.isInAlphabet(byteArray10, false);
        byte[] byteArray14 = base32_0.encode(byteArray10);
        byte[] byteArray15 = org.apache.commons.codec.binary.BinaryCodec.toAsciiBytes(byteArray14);
        java.io.InputStream inputStream16 = null;
        org.apache.commons.codec.binary.Base16InputStream base16InputStream17 = new org.apache.commons.codec.binary.Base16InputStream(inputStream16);
        boolean boolean18 = base16InputStream17.markSupported();
        org.apache.commons.codec.binary.Base32InputStream base32InputStream20 = new org.apache.commons.codec.binary.Base32InputStream((java.io.InputStream) base16InputStream17, false);
        // The following exception was thrown during execution in test generation
        try {
            byte[] byteArray21 = org.apache.commons.codec.digest.HmacUtils.hmacMd5(byteArray14, (java.io.InputStream) base16InputStream17);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertArrayEquals(byteArray3, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "\000" + "'", str4, "\000");
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNotNull(byteArray10);
        org.junit.Assert.assertArrayEquals(byteArray10, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "\000" + "'", str11, "\000");
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
        org.junit.Assert.assertNotNull(byteArray14);
        org.junit.Assert.assertArrayEquals(byteArray14, new byte[] { (byte) 65, (byte) 65, (byte) 65, (byte) 65, (byte) 61, (byte) 61, (byte) 61, (byte) 61 });
        org.junit.Assert.assertNotNull(byteArray15);
        org.junit.Assert.assertTrue("'" + boolean18 + "' != '" + false + "'", boolean18 == false);
    }

    @Test
    public void test091() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test091");
        java.lang.String str0 = org.apache.commons.codec.digest.MessageDigestAlgorithms.SHA3_224;
        org.junit.Assert.assertEquals("'" + str0 + "' != '" + "SHA3-224" + "'", str0, "SHA3-224");
    }

    @Test
    public void test092() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test092");
        java.io.InputStream inputStream0 = null;
        org.apache.commons.codec.binary.Base16InputStream base16InputStream1 = new org.apache.commons.codec.binary.Base16InputStream(inputStream0);
        base16InputStream1.mark(0);
        // The following exception was thrown during execution in test generation
        try {
            base16InputStream1.close();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test093() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test093");
        org.apache.commons.codec.digest.MurmurHash3.IncrementalHash32x86 incrementalHash32x86_0 = new org.apache.commons.codec.digest.MurmurHash3.IncrementalHash32x86();
    }

    @Test
    public void test094() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test094");
        org.apache.commons.codec.binary.Base58.Builder builder0 = org.apache.commons.codec.binary.Base58.builder();
        org.apache.commons.codec.binary.Base58 base58_1 = new org.apache.commons.codec.binary.Base58(builder0);
        org.apache.commons.codec.CodecPolicy codecPolicy2 = null;
        org.apache.commons.codec.binary.Base58.Builder builder3 = builder0.setDecodingPolicy(codecPolicy2);
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(builder3);
    }

    @Test
    public void test095() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test095");
        org.apache.commons.codec.language.Caverphone caverphone0 = new org.apache.commons.codec.language.Caverphone();
        java.lang.String str2 = caverphone0.encode("OsQTcDyHcPmog");
        org.junit.Assert.assertEquals("'" + str2 + "' != '" + "ASKTKTKPMK" + "'", str2, "ASKTKTKPMK");
    }

    @Test
    public void test096() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test096");
        java.lang.String str1 = org.apache.commons.codec.digest.DigestUtils.sha1Hex("$1$2bZpHv3e$KuegH6jWeAAwJFSY6saCF0");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "d4615270d8c34a6731a7fcc0241ec5bafc726b4c" + "'", str1, "d4615270d8c34a6731a7fcc0241ec5bafc726b4c");
    }

    @Test
    public void test097() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test097");
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str1 = org.apache.commons.codec.digest.DigestUtils.sha512_224Hex("AA11111111");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: java.security.NoSuchAlgorithmException: SHA-512/224 MessageDigest not available");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test098() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test098");
        org.apache.commons.codec.binary.Base32 base32_0 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray3 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str4 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray3);
        boolean boolean6 = base32_0.isInAlphabet(byteArray3, false);
        org.apache.commons.codec.binary.Base32 base32_7 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray10 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str11 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray10);
        boolean boolean13 = base32_7.isInAlphabet(byteArray10, false);
        java.lang.String str14 = org.apache.commons.codec.digest.UnixCrypt.crypt(byteArray10);
        java.lang.String str15 = org.apache.commons.codec.digest.HmacUtils.hmacSha1Hex(byteArray3, byteArray10);
        java.lang.String str16 = org.apache.commons.codec.digest.DigestUtils.shaHex(byteArray10);
        java.lang.String str17 = org.apache.commons.codec.digest.Sha2Crypt.sha512Crypt(byteArray10);
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertArrayEquals(byteArray3, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "\000" + "'", str4, "\000");
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNotNull(byteArray10);
        org.junit.Assert.assertArrayEquals(byteArray10, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "\000" + "'", str11, "\000");
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
// flaky "9) test098(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str14 + "' != '" + "c7x1syRbCUeX." + "'", str14, "c7x1syRbCUeX.");
        org.junit.Assert.assertEquals("'" + str15 + "' != '" + "310354661a5962d5b8cb76032d5a97e8aed7cf9f" + "'", str15, "310354661a5962d5b8cb76032d5a97e8aed7cf9f");
        org.junit.Assert.assertEquals("'" + str16 + "' != '" + "1489f923c4dca729178b3e3233458550d8dddf29" + "'", str16, "1489f923c4dca729178b3e3233458550d8dddf29");
// flaky "1) test098(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str17 + "' != '" + "$6$oWTfCqM4$pqjKIRdRH35mCsiHnWoGmZXh7pxYgNcyOULjqnI2WivTynTt2UmWPdeQeDpK11BYRoTIeq.WEFe3E1xL/r10L1" + "'", str17, "$6$oWTfCqM4$pqjKIRdRH35mCsiHnWoGmZXh7pxYgNcyOULjqnI2WivTynTt2UmWPdeQeDpK11BYRoTIeq.WEFe3E1xL/r10L1");
    }

    @Test
    public void test099() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test099");
        org.apache.commons.codec.digest.Crc16 crc16_0 = org.apache.commons.codec.digest.Crc16.maxim();
        crc16_0.update((int) (byte) 10);
        java.util.BitSet bitSet3 = null;
        org.apache.commons.codec.binary.Base32 base32_4 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray7 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str8 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray7);
        boolean boolean10 = base32_4.isInAlphabet(byteArray7, false);
        org.apache.commons.codec.binary.Base32 base32_11 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray14 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str15 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray14);
        boolean boolean17 = base32_11.isInAlphabet(byteArray14, false);
        java.lang.String str18 = org.apache.commons.codec.digest.UnixCrypt.crypt(byteArray14);
        java.lang.String str19 = org.apache.commons.codec.digest.HmacUtils.hmacSha1Hex(byteArray7, byteArray14);
        java.lang.String str20 = org.apache.commons.codec.binary.BinaryCodec.toAsciiString(byteArray14);
        byte[] byteArray21 = org.apache.commons.codec.binary.Base64.encodeBase64Chunked(byteArray14);
        byte[] byteArray23 = org.apache.commons.codec.net.QuotedPrintableCodec.encodeQuotedPrintable(bitSet3, byteArray14, false);
        // The following exception was thrown during execution in test generation
        try {
            crc16_0.update(byteArray23, (int) ' ', (int) (short) 10);
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: 32");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(crc16_0);
        org.junit.Assert.assertNotNull(byteArray7);
        org.junit.Assert.assertArrayEquals(byteArray7, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "\000" + "'", str8, "\000");
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        org.junit.Assert.assertNotNull(byteArray14);
        org.junit.Assert.assertArrayEquals(byteArray14, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str15 + "' != '" + "\000" + "'", str15, "\000");
        org.junit.Assert.assertTrue("'" + boolean17 + "' != '" + false + "'", boolean17 == false);
// flaky "10) test099(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str18 + "' != '" + "tJtiYmH8qLLpc" + "'", str18, "tJtiYmH8qLLpc");
        org.junit.Assert.assertEquals("'" + str19 + "' != '" + "310354661a5962d5b8cb76032d5a97e8aed7cf9f" + "'", str19, "310354661a5962d5b8cb76032d5a97e8aed7cf9f");
        org.junit.Assert.assertEquals("'" + str20 + "' != '" + "0000000000000000" + "'", str20, "0000000000000000");
        org.junit.Assert.assertNotNull(byteArray21);
        org.junit.Assert.assertArrayEquals(byteArray21, new byte[] { (byte) 65, (byte) 65, (byte) 65, (byte) 61, (byte) 13, (byte) 10 });
        org.junit.Assert.assertNotNull(byteArray23);
        org.junit.Assert.assertArrayEquals(byteArray23, new byte[] { (byte) 61, (byte) 48, (byte) 48, (byte) 61, (byte) 48, (byte) 48 });
    }

    @Test
    public void test100() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test100");
        java.io.InputStream inputStream0 = null;
        org.apache.commons.codec.binary.Base16InputStream base16InputStream1 = new org.apache.commons.codec.binary.Base16InputStream(inputStream0);
        char[] charArray4 = null;
        byte[] byteArray5 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray4);
        char[] charArray6 = org.apache.commons.codec.binary.Hex.encodeHex(byteArray5);
        byte[] byteArray7 = org.apache.commons.codec.binary.Hex.decodeHex(charArray6);
        org.apache.commons.codec.CodecPolicy codecPolicy8 = null;
        org.apache.commons.codec.binary.Base64InputStream base64InputStream9 = new org.apache.commons.codec.binary.Base64InputStream((java.io.InputStream) base16InputStream1, true, (int) (short) 100, byteArray7, codecPolicy8);
        // The following exception was thrown during execution in test generation
        try {
            int int10 = base64InputStream9.read();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray5);
        org.junit.Assert.assertArrayEquals(byteArray5, new byte[] {});
        org.junit.Assert.assertNotNull(charArray6);
        org.junit.Assert.assertArrayEquals(charArray6, new char[] {});
        org.junit.Assert.assertNotNull(byteArray7);
        org.junit.Assert.assertArrayEquals(byteArray7, new byte[] {});
    }

    @Test
    public void test101() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test101");
        org.apache.commons.codec.net.QCodec qCodec0 = new org.apache.commons.codec.net.QCodec();
        boolean boolean1 = qCodec0.isEncodeBlanks();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj3 = qCodec0.decode((java.lang.Object) "");
            org.junit.Assert.fail("Expected exception of type org.apache.commons.codec.DecoderException; message: RFC 1522 violation: malformed encoded content");
        } catch (org.apache.commons.codec.DecoderException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
    }

    @Test
    public void test102() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test102");
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str1 = org.apache.commons.codec.digest.DigestUtils.sha3_256Hex("sqxhKYbUNfTzw");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: java.security.NoSuchAlgorithmException: SHA3-256 MessageDigest not available");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test103() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test103");
        org.apache.commons.codec.net.PercentCodec percentCodec0 = new org.apache.commons.codec.net.PercentCodec();
        org.apache.commons.codec.net.PercentCodec percentCodec1 = new org.apache.commons.codec.net.PercentCodec();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj2 = percentCodec0.encode((java.lang.Object) percentCodec1);
            org.junit.Assert.fail("Expected exception of type org.apache.commons.codec.EncoderException; message: Objects of type org.apache.commons.codec.net.PercentCodec cannot be Percent encoded");
        } catch (org.apache.commons.codec.EncoderException e) {
            // Expected exception.
        }
    }

    @Test
    public void test104() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test104");
        java.lang.String str0 = org.apache.commons.codec.CharEncoding.US_ASCII;
        org.junit.Assert.assertEquals("'" + str0 + "' != '" + "US-ASCII" + "'", str0, "US-ASCII");
    }

    @Test
    public void test105() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test105");
        java.security.MessageDigest messageDigest0 = org.apache.commons.codec.digest.DigestUtils.getShaDigest();
        java.nio.file.Path path1 = null;
        // The following exception was thrown during execution in test generation
        try {
            byte[] byteArray2 = org.apache.commons.codec.digest.GitIdentifiers.treeId(messageDigest0, path1);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(messageDigest0);
        org.junit.Assert.assertEquals(messageDigest0.toString(), "SHA-1 Message Digest from SUN, <initialized>\r\n");
    }

    @Test
    public void test106() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test106");
        java.io.InputStream inputStream0 = null;
        org.apache.commons.codec.binary.Base16InputStream base16InputStream1 = new org.apache.commons.codec.binary.Base16InputStream(inputStream0);
        char[] charArray4 = null;
        byte[] byteArray5 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray4);
        char[] charArray6 = org.apache.commons.codec.binary.Hex.encodeHex(byteArray5);
        byte[] byteArray7 = org.apache.commons.codec.binary.Hex.decodeHex(charArray6);
        org.apache.commons.codec.CodecPolicy codecPolicy8 = null;
        org.apache.commons.codec.binary.Base64InputStream base64InputStream9 = new org.apache.commons.codec.binary.Base64InputStream((java.io.InputStream) base16InputStream1, true, (int) (short) 100, byteArray7, codecPolicy8);
        int int10 = base64InputStream9.available();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str11 = org.apache.commons.codec.digest.DigestUtils.sha256Hex((java.io.InputStream) base64InputStream9);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray5);
        org.junit.Assert.assertArrayEquals(byteArray5, new byte[] {});
        org.junit.Assert.assertNotNull(charArray6);
        org.junit.Assert.assertArrayEquals(charArray6, new char[] {});
        org.junit.Assert.assertNotNull(byteArray7);
        org.junit.Assert.assertArrayEquals(byteArray7, new byte[] {});
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 1 + "'", int10 == 1);
    }

    @Test
    public void test107() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test107");
        org.apache.commons.codec.binary.Base32 base32_0 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray3 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str4 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray3);
        boolean boolean6 = base32_0.isInAlphabet(byteArray3, false);
        byte[] byteArray9 = org.apache.commons.codec.digest.HmacUtils.hmacSha512("310354661a5962d5b8cb76032d5a97e8aed7cf9f", "");
        byte[] byteArray10 = base32_0.encode(byteArray9);
        org.apache.commons.codec.digest.Crc16 crc16_11 = org.apache.commons.codec.digest.Crc16.modbus();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj12 = base32_0.decode((java.lang.Object) crc16_11);
            org.junit.Assert.fail("Expected exception of type org.apache.commons.codec.DecoderException; message: Parameter supplied to Base-N decode is not a byte[] or a String");
        } catch (org.apache.commons.codec.DecoderException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertArrayEquals(byteArray3, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "\000" + "'", str4, "\000");
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNotNull(byteArray9);
        org.junit.Assert.assertNotNull(byteArray10);
        org.junit.Assert.assertNotNull(crc16_11);
    }

    @Test
    public void test108() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test108");
        org.apache.commons.codec.binary.Hex hex0 = new org.apache.commons.codec.binary.Hex();
        java.nio.ByteBuffer byteBuffer1 = null;
        // The following exception was thrown during execution in test generation
        try {
            byte[] byteArray2 = hex0.encode(byteBuffer1);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test109() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test109");
        org.apache.commons.codec.language.bm.NameType nameType0 = null;
        org.apache.commons.codec.language.bm.RuleType ruleType1 = null;
        org.apache.commons.codec.language.bm.PhoneticEngine phoneticEngine4 = new org.apache.commons.codec.language.bm.PhoneticEngine(nameType0, ruleType1, true, (-1));
        org.apache.commons.codec.language.bm.Languages.LanguageSet languageSet6 = null;
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str7 = phoneticEngine4.encode("c39394a96ebd177a239a817fe8453ab0", languageSet6);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test110() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test110");
        java.lang.String str0 = org.apache.commons.codec.digest.MessageDigestAlgorithms.MD5;
        org.junit.Assert.assertEquals("'" + str0 + "' != '" + "MD5" + "'", str0, "MD5");
    }

    @Test
    public void test111() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test111");
        java.io.OutputStream outputStream0 = null;
        org.apache.commons.codec.binary.Base64OutputStream base64OutputStream1 = new org.apache.commons.codec.binary.Base64OutputStream(outputStream0);
        org.apache.commons.codec.binary.Base16OutputStream base16OutputStream2 = new org.apache.commons.codec.binary.Base16OutputStream(outputStream0);
    }

    @Test
    public void test112() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test112");
        java.security.MessageDigest messageDigest0 = org.apache.commons.codec.digest.DigestUtils.getShaDigest();
        byte[] byteArray1 = null;
        // The following exception was thrown during execution in test generation
        try {
            byte[] byteArray2 = org.apache.commons.codec.digest.DigestUtils.digest(messageDigest0, byteArray1);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(messageDigest0);
        org.junit.Assert.assertEquals(messageDigest0.toString(), "SHA-1 Message Digest from SUN, <initialized>\r\n");
    }

    @Test
    public void test113() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test113");
        org.apache.commons.codec.binary.Base32 base32_0 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray3 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str4 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray3);
        boolean boolean6 = base32_0.isInAlphabet(byteArray3, false);
        org.apache.commons.codec.binary.Base32 base32_7 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray10 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str11 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray10);
        boolean boolean13 = base32_7.isInAlphabet(byteArray10, false);
        java.lang.String str14 = org.apache.commons.codec.digest.UnixCrypt.crypt(byteArray10);
        java.lang.String str15 = org.apache.commons.codec.digest.HmacUtils.hmacSha1Hex(byteArray3, byteArray10);
        java.lang.String str16 = org.apache.commons.codec.binary.BinaryCodec.toAsciiString(byteArray10);
        javax.crypto.Mac mac17 = org.apache.commons.codec.digest.HmacUtils.getHmacSha384(byteArray10);
        java.lang.String str18 = org.apache.commons.codec.binary.BinaryCodec.toAsciiString(byteArray10);
        // The following exception was thrown during execution in test generation
        try {
            long long20 = org.apache.commons.codec.digest.MurmurHash2.hash64(byteArray10, (-342794719));
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: -342794720");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertArrayEquals(byteArray3, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "\000" + "'", str4, "\000");
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNotNull(byteArray10);
        org.junit.Assert.assertArrayEquals(byteArray10, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "\000" + "'", str11, "\000");
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
// flaky "11) test113(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str14 + "' != '" + "wP1OR2GiZack2" + "'", str14, "wP1OR2GiZack2");
        org.junit.Assert.assertEquals("'" + str15 + "' != '" + "310354661a5962d5b8cb76032d5a97e8aed7cf9f" + "'", str15, "310354661a5962d5b8cb76032d5a97e8aed7cf9f");
        org.junit.Assert.assertEquals("'" + str16 + "' != '" + "0000000000000000" + "'", str16, "0000000000000000");
        org.junit.Assert.assertNotNull(mac17);
        org.junit.Assert.assertEquals("'" + str18 + "' != '" + "0000000000000000" + "'", str18, "0000000000000000");
    }

    @Test
    public void test114() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test114");
        org.apache.commons.codec.language.Soundex soundex0 = new org.apache.commons.codec.language.Soundex();
    }

    @Test
    public void test115() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test115");
        // The following exception was thrown during execution in test generation
        try {
            byte[] byteArray1 = org.apache.commons.codec.digest.DigestUtils.sha3_224("d4615270d8c34a6731a7fcc0241ec5bafc726b4c");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: java.security.NoSuchAlgorithmException: SHA3-224 MessageDigest not available");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test116() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test116");
        org.apache.commons.codec.binary.Base64.DecodeTableFormat decodeTableFormat0 = org.apache.commons.codec.binary.Base64.DecodeTableFormat.URL_SAFE;
        org.junit.Assert.assertTrue("'" + decodeTableFormat0 + "' != '" + org.apache.commons.codec.binary.Base64.DecodeTableFormat.URL_SAFE + "'", decodeTableFormat0.equals(org.apache.commons.codec.binary.Base64.DecodeTableFormat.URL_SAFE));
    }

    @Test
    public void test117() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test117");
        // The following exception was thrown during execution in test generation
        try {
            byte[] byteArray1 = org.apache.commons.codec.digest.DigestUtils.sha3_384("ASKTKTKPMK");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: java.security.NoSuchAlgorithmException: SHA3-384 MessageDigest not available");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test118() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test118");
        java.security.MessageDigest messageDigest0 = org.apache.commons.codec.digest.DigestUtils.getShaDigest();
        org.apache.commons.codec.digest.DigestUtils digestUtils1 = new org.apache.commons.codec.digest.DigestUtils(messageDigest0);
        byte[] byteArray3 = digestUtils1.digest("8404a7262f45838faa05f26c92d87d10524773d9");
        java.lang.String str5 = digestUtils1.digestAsHex("ISO-8859-1");
        byte[] byteArray6 = null;
        // The following exception was thrown during execution in test generation
        try {
            byte[] byteArray7 = digestUtils1.digest(byteArray6);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(messageDigest0);
        org.junit.Assert.assertEquals(messageDigest0.toString(), "SHA-1 Message Digest from SUN, <initialized>\r\n");
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertArrayEquals(byteArray3, new byte[] { (byte) 18, (byte) -86, (byte) 24, (byte) -94, (byte) 7, (byte) -126, (byte) -37, (byte) -81, (byte) 92, (byte) -41, (byte) 35, (byte) -91, (byte) -65, (byte) 115, (byte) 41, (byte) -106, (byte) 2, (byte) -107, (byte) 125, (byte) -21 });
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "e4d773805cf8a24c47b008dd036ad6936efd068b" + "'", str5, "e4d773805cf8a24c47b008dd036ad6936efd068b");
    }

    @Test
    public void test119() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test119");
        org.apache.commons.codec.binary.Base64.Builder builder0 = new org.apache.commons.codec.binary.Base64.Builder();
        java.io.InputStream inputStream1 = null;
        org.apache.commons.codec.binary.Base16InputStream base16InputStream2 = new org.apache.commons.codec.binary.Base16InputStream(inputStream1);
        byte[] byteArray11 = new byte[] { (byte) 10, (byte) 0, (byte) -1, (byte) 0, (byte) 1, (byte) 100 };
        java.lang.String str12 = org.apache.commons.codec.digest.Md5Crypt.md5Crypt(byteArray11);
        org.apache.commons.codec.CodecPolicy codecPolicy13 = null;
        org.apache.commons.codec.binary.Base64InputStream base64InputStream14 = new org.apache.commons.codec.binary.Base64InputStream((java.io.InputStream) base16InputStream2, false, (int) 'a', byteArray11, codecPolicy13);
        org.apache.commons.codec.binary.Base64.Builder builder15 = builder0.setDecodeTable(byteArray11);
        byte[] byteArray16 = org.apache.commons.codec.binary.Base64.decodeBase64UrlSafe(byteArray11);
        // The following exception was thrown during execution in test generation
        try {
            byte[] byteArray17 = org.apache.commons.codec.digest.DigestUtils.sha3_224(byteArray11);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: java.security.NoSuchAlgorithmException: SHA3-224 MessageDigest not available");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray11);
        org.junit.Assert.assertArrayEquals(byteArray11, new byte[] { (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0 });
// flaky "12) test119(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str12 + "' != '" + "$1$HUXqWQoM$FBDwePzm7RLikDsAjP8cT0" + "'", str12, "$1$HUXqWQoM$FBDwePzm7RLikDsAjP8cT0");
        org.junit.Assert.assertNotNull(builder15);
        org.junit.Assert.assertNotNull(byteArray16);
        org.junit.Assert.assertArrayEquals(byteArray16, new byte[] {});
    }

    @Test
    public void test120() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test120");
        org.apache.commons.codec.binary.Base32 base32_0 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray3 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str4 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray3);
        boolean boolean6 = base32_0.isInAlphabet(byteArray3, false);
        java.lang.String str7 = org.apache.commons.codec.digest.UnixCrypt.crypt(byteArray3);
        byte[] byteArray8 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(byteArray3);
        boolean boolean9 = org.apache.commons.codec.binary.Base64.isBase64(byteArray3);
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertArrayEquals(byteArray3, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "\000" + "'", str4, "\000");
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
// flaky "13) test120(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str7 + "' != '" + "WGJU3Y88YVchU" + "'", str7, "WGJU3Y88YVchU");
        org.junit.Assert.assertNotNull(byteArray8);
        org.junit.Assert.assertArrayEquals(byteArray8, new byte[] {});
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
    }

    @Test
    public void test121() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test121");
        org.apache.commons.codec.binary.Base64InputStream.Builder builder0 = org.apache.commons.codec.binary.Base64InputStream.builder();
        org.apache.commons.codec.binary.Base64 base64_1 = null;
        org.apache.commons.codec.binary.Base64InputStream.Builder builder2 = builder0.setBaseNCodec(base64_1);
        org.apache.commons.codec.binary.Base64 base64_3 = new org.apache.commons.codec.binary.Base64();
        org.apache.commons.codec.binary.Base64InputStream.Builder builder4 = builder2.setBaseNCodec(base64_3);
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(builder2);
        org.junit.Assert.assertNotNull(builder4);
    }

    @Test
    public void test122() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test122");
        org.apache.commons.codec.language.Nysiis nysiis1 = new org.apache.commons.codec.language.Nysiis(false);
        java.lang.String str3 = nysiis1.nysiis("5574c9b84a224d6efecd951eb4462a84c4c1fe92");
        org.junit.Assert.assertEquals("'" + str3 + "' != '" + "CBADAFACDABACF" + "'", str3, "CBADAFACDABACF");
    }

    @Test
    public void test123() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test123");
        org.apache.commons.codec.net.QCodec qCodec0 = new org.apache.commons.codec.net.QCodec();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str2 = qCodec0.decode("PCqE1NnZAFIA2");
            org.junit.Assert.fail("Expected exception of type org.apache.commons.codec.DecoderException; message: RFC 1522 violation: malformed encoded content");
        } catch (org.apache.commons.codec.DecoderException e) {
            // Expected exception.
        }
    }

    @Test
    public void test124() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test124");
        java.io.InputStream inputStream0 = null;
        org.apache.commons.codec.binary.Base16InputStream base16InputStream1 = new org.apache.commons.codec.binary.Base16InputStream(inputStream0);
        boolean boolean2 = base16InputStream1.markSupported();
        org.apache.commons.codec.binary.Base32InputStream base32InputStream4 = new org.apache.commons.codec.binary.Base32InputStream((java.io.InputStream) base16InputStream1, false);
        boolean boolean5 = base16InputStream1.markSupported();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str6 = org.apache.commons.codec.digest.DigestUtils.sha3_256Hex((java.io.InputStream) base16InputStream1);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: java.security.NoSuchAlgorithmException: SHA3-256 MessageDigest not available");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
    }

    @Test
    public void test125() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test125");
        byte[] byteArray6 = new byte[] { (byte) 10, (byte) 0, (byte) -1, (byte) 0, (byte) 1, (byte) 100 };
        java.lang.String str7 = org.apache.commons.codec.digest.Md5Crypt.md5Crypt(byteArray6);
        java.lang.String str8 = org.apache.commons.codec.digest.Crypt.crypt(byteArray6);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str9 = org.apache.commons.codec.digest.DigestUtils.sha512_224Hex(byteArray6);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: java.security.NoSuchAlgorithmException: SHA-512/224 MessageDigest not available");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray6);
        org.junit.Assert.assertArrayEquals(byteArray6, new byte[] { (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0 });
// flaky "14) test125(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str7 + "' != '" + "$1$bgSbpf0t$ApxRPIVGRWa2Ez0bcoJFs0" + "'", str7, "$1$bgSbpf0t$ApxRPIVGRWa2Ez0bcoJFs0");
// flaky "2) test125(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str8 + "' != '" + "$6$Q2dbRwRw$j7TjQqbIVUVvWX9mkNyUnZ2vL6XposkReL/TkiRGBJpaTxicURAVVSO/./FAlJ/1lAk9DOQM1Wmv0RVCg98YD." + "'", str8, "$6$Q2dbRwRw$j7TjQqbIVUVvWX9mkNyUnZ2vL6XposkReL/TkiRGBJpaTxicURAVVSO/./FAlJ/1lAk9DOQM1Wmv0RVCg98YD.");
    }

    @Test
    public void test126() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test126");
        java.lang.String str0 = org.apache.commons.codec.language.RefinedSoundex.US_ENGLISH_MAPPING_STRING;
        org.junit.Assert.assertEquals("'" + str0 + "' != '" + "01360240043788015936020505" + "'", str0, "01360240043788015936020505");
    }

    @Test
    public void test127() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test127");
        char[] charArray1 = null;
        byte[] byteArray2 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray1);
        org.apache.commons.codec.binary.Base64 base64_3 = new org.apache.commons.codec.binary.Base64((int) '4', byteArray2);
        char[] charArray4 = null;
        byte[] byteArray5 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray4);
        char[] charArray6 = org.apache.commons.codec.binary.Hex.encodeHex(byteArray5);
        byte[] byteArray7 = org.apache.commons.codec.binary.Hex.decodeHex(charArray6);
        byte[] byteArray10 = base64_3.encode(byteArray7, (int) (byte) 10, 100);
        boolean boolean11 = base64_3.isUrlSafe();
        org.junit.Assert.assertNotNull(byteArray2);
        org.junit.Assert.assertArrayEquals(byteArray2, new byte[] {});
        org.junit.Assert.assertNotNull(byteArray5);
        org.junit.Assert.assertArrayEquals(byteArray5, new byte[] {});
        org.junit.Assert.assertNotNull(charArray6);
        org.junit.Assert.assertArrayEquals(charArray6, new char[] {});
        org.junit.Assert.assertNotNull(byteArray7);
        org.junit.Assert.assertArrayEquals(byteArray7, new byte[] {});
        org.junit.Assert.assertNotNull(byteArray10);
        org.junit.Assert.assertArrayEquals(byteArray10, new byte[] {});
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
    }

    @Test
    public void test128() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test128");
        java.lang.String str2 = org.apache.commons.codec.digest.UnixCrypt.crypt("C812", "201853cbefef57ae7964e7c7c9d45f5a6181e885bb6ac9fd9219405e1d002b4c840dd2971f2b03900c9a6531eb470a5031425b4a8b14c684174cd97402968242");
        org.junit.Assert.assertEquals("'" + str2 + "' != '" + "20IlD34ySuMqY" + "'", str2, "20IlD34ySuMqY");
    }

    @Test
    public void test129() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test129");
        org.apache.commons.codec.digest.MurmurHash3.IncrementalHash32 incrementalHash32_0 = new org.apache.commons.codec.digest.MurmurHash3.IncrementalHash32();
        int int1 = incrementalHash32_0.end();
        org.junit.Assert.assertTrue("'" + int1 + "' != '" + 0 + "'", int1 == 0);
    }

    @Test
    public void test130() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test130");
        byte[] byteArray0 = null;
        java.lang.String str1 = org.apache.commons.codec.binary.StringUtils.newStringUsAscii(byteArray0);
        org.junit.Assert.assertNull(str1);
    }

    @Test
    public void test131() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test131");
        java.io.InputStream inputStream0 = null;
        org.apache.commons.codec.binary.Base16InputStream base16InputStream1 = new org.apache.commons.codec.binary.Base16InputStream(inputStream0);
        boolean boolean2 = base16InputStream1.markSupported();
        // The following exception was thrown during execution in test generation
        try {
            int int3 = base16InputStream1.read();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
    }

    @Test
    public void test132() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test132");
        java.io.InputStream inputStream0 = null;
        org.apache.commons.codec.binary.Base16InputStream base16InputStream1 = new org.apache.commons.codec.binary.Base16InputStream(inputStream0);
        boolean boolean2 = base16InputStream1.markSupported();
        org.apache.commons.codec.binary.Base32InputStream base32InputStream4 = new org.apache.commons.codec.binary.Base32InputStream((java.io.InputStream) base16InputStream1, false);
        boolean boolean5 = base16InputStream1.markSupported();
        // The following exception was thrown during execution in test generation
        try {
            int int6 = base16InputStream1.read();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
    }

    @Test
    public void test133() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test133");
        java.io.OutputStream outputStream0 = null;
        org.apache.commons.codec.binary.Base64OutputStream base64OutputStream1 = new org.apache.commons.codec.binary.Base64OutputStream(outputStream0);
        org.apache.commons.codec.binary.Base16OutputStream base16OutputStream3 = new org.apache.commons.codec.binary.Base16OutputStream(outputStream0, false);
        // The following exception was thrown during execution in test generation
        try {
            base16OutputStream3.write((int) (byte) 100);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Invalid octet in encoded value: 100");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test134() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test134");
        java.io.InputStream inputStream0 = null;
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str1 = org.apache.commons.codec.digest.DigestUtils.shake256_512Hex(inputStream0);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: java.security.NoSuchAlgorithmException: SHAKE256-512 MessageDigest not available");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test135() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test135");
        org.apache.commons.codec.StringEncoderComparator stringEncoderComparator0 = new org.apache.commons.codec.StringEncoderComparator();
    }

    @Test
    public void test136() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test136");
        // The following exception was thrown during execution in test generation
        try {
            int int3 = org.apache.commons.codec.digest.MurmurHash2.hash32("", 10, (int) (short) -1);
            org.junit.Assert.fail("Expected exception of type java.lang.StringIndexOutOfBoundsException; message: String index out of range: 9");
        } catch (java.lang.StringIndexOutOfBoundsException e) {
            // Expected exception.
        }
    }

    @Test
    public void test137() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test137");
        java.io.InputStream inputStream0 = null;
        org.apache.commons.codec.binary.Base16InputStream base16InputStream1 = new org.apache.commons.codec.binary.Base16InputStream(inputStream0);
        char[] charArray4 = null;
        byte[] byteArray5 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray4);
        char[] charArray6 = org.apache.commons.codec.binary.Hex.encodeHex(byteArray5);
        byte[] byteArray7 = org.apache.commons.codec.binary.Hex.decodeHex(charArray6);
        org.apache.commons.codec.CodecPolicy codecPolicy8 = null;
        org.apache.commons.codec.binary.Base64InputStream base64InputStream9 = new org.apache.commons.codec.binary.Base64InputStream((java.io.InputStream) base16InputStream1, true, (int) (short) 100, byteArray7, codecPolicy8);
        char[] charArray12 = null;
        byte[] byteArray13 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray12);
        char[] charArray14 = org.apache.commons.codec.binary.Hex.encodeHex(byteArray13);
        java.lang.String str15 = org.apache.commons.codec.binary.BinaryCodec.toAsciiString(byteArray13);
        org.apache.commons.codec.CodecPolicy codecPolicy16 = null;
        org.apache.commons.codec.binary.Base32InputStream base32InputStream17 = new org.apache.commons.codec.binary.Base32InputStream((java.io.InputStream) base16InputStream1, false, (int) (byte) 100, byteArray13, codecPolicy16);
        // The following exception was thrown during execution in test generation
        try {
            int int18 = base16InputStream1.read();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray5);
        org.junit.Assert.assertArrayEquals(byteArray5, new byte[] {});
        org.junit.Assert.assertNotNull(charArray6);
        org.junit.Assert.assertArrayEquals(charArray6, new char[] {});
        org.junit.Assert.assertNotNull(byteArray7);
        org.junit.Assert.assertArrayEquals(byteArray7, new byte[] {});
        org.junit.Assert.assertNotNull(byteArray13);
        org.junit.Assert.assertArrayEquals(byteArray13, new byte[] {});
        org.junit.Assert.assertNotNull(charArray14);
        org.junit.Assert.assertArrayEquals(charArray14, new char[] {});
        org.junit.Assert.assertEquals("'" + str15 + "' != '" + "" + "'", str15, "");
    }

    @Test
    public void test138() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test138");
        org.apache.commons.codec.language.bm.NameType nameType0 = null;
        org.apache.commons.codec.language.bm.Lang lang1 = org.apache.commons.codec.language.bm.Lang.instance(nameType0);
        org.junit.Assert.assertNull(lang1);
    }

    @Test
    public void test139() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test139");
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str1 = org.apache.commons.codec.digest.DigestUtils.sha3_512Hex("Ysd1ffrudfNPM");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: java.security.NoSuchAlgorithmException: SHA3-512 MessageDigest not available");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test140() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test140");
        // The following exception was thrown during execution in test generation
        try {
            byte[] byteArray1 = org.apache.commons.codec.digest.DigestUtils.sha3_384("207b9c1af4dbe7359a718262cce9251a");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: java.security.NoSuchAlgorithmException: SHA3-384 MessageDigest not available");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test141() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test141");
        char[] charArray0 = null;
        byte[] byteArray1 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray0);
        // The following exception was thrown during execution in test generation
        try {
            byte[] byteArray2 = org.apache.commons.codec.digest.DigestUtils.shake256_512(byteArray1);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: java.security.NoSuchAlgorithmException: SHAKE256-512 MessageDigest not available");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray1);
        org.junit.Assert.assertArrayEquals(byteArray1, new byte[] {});
    }

    @Test
    public void test142() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test142");
        java.security.MessageDigest messageDigest0 = org.apache.commons.codec.digest.DigestUtils.getShaDigest();
        org.apache.commons.codec.digest.DigestUtils digestUtils1 = new org.apache.commons.codec.digest.DigestUtils(messageDigest0);
        char[] charArray3 = null;
        byte[] byteArray4 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray3);
        org.apache.commons.codec.binary.Base64 base64_5 = new org.apache.commons.codec.binary.Base64((int) '4', byteArray4);
        byte[] byteArray6 = digestUtils1.digest(byteArray4);
        boolean boolean7 = org.apache.commons.codec.binary.Base64.isBase64(byteArray6);
        org.junit.Assert.assertNotNull(messageDigest0);
        org.junit.Assert.assertEquals(messageDigest0.toString(), "SHA-1 Message Digest from SUN, <initialized>\r\n");
        org.junit.Assert.assertNotNull(byteArray4);
        org.junit.Assert.assertArrayEquals(byteArray4, new byte[] {});
        org.junit.Assert.assertNotNull(byteArray6);
        org.junit.Assert.assertArrayEquals(byteArray6, new byte[] { (byte) -38, (byte) 57, (byte) -93, (byte) -18, (byte) 94, (byte) 107, (byte) 75, (byte) 13, (byte) 50, (byte) 85, (byte) -65, (byte) -17, (byte) -107, (byte) 96, (byte) 24, (byte) -112, (byte) -81, (byte) -40, (byte) 7, (byte) 9 });
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
    }

    @Test
    public void test143() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test143");
        org.apache.commons.codec.binary.Base32 base32_0 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray3 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str4 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray3);
        boolean boolean6 = base32_0.isInAlphabet(byteArray3, false);
        org.apache.commons.codec.binary.Base32 base32_7 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray10 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str11 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray10);
        boolean boolean13 = base32_7.isInAlphabet(byteArray10, false);
        java.lang.String str14 = org.apache.commons.codec.digest.UnixCrypt.crypt(byteArray10);
        java.lang.String str15 = org.apache.commons.codec.digest.HmacUtils.hmacSha1Hex(byteArray3, byteArray10);
        java.lang.String str16 = org.apache.commons.codec.binary.BinaryCodec.toAsciiString(byteArray10);
        byte[] byteArray17 = org.apache.commons.codec.binary.Base64.encodeBase64Chunked(byteArray10);
        java.lang.String str19 = org.apache.commons.codec.digest.Sha2Crypt.sha512Crypt(byteArray17, "$6$oWTfCqM4$pqjKIRdRH35mCsiHnWoGmZXh7pxYgNcyOULjqnI2WivTynTt2UmWPdeQeDpK11BYRoTIeq.WEFe3E1xL/r10L1");
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertArrayEquals(byteArray3, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "\000" + "'", str4, "\000");
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNotNull(byteArray10);
        org.junit.Assert.assertArrayEquals(byteArray10, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "\000" + "'", str11, "\000");
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
// flaky "15) test143(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str14 + "' != '" + "j5bT9KV4jZHV6" + "'", str14, "j5bT9KV4jZHV6");
        org.junit.Assert.assertEquals("'" + str15 + "' != '" + "310354661a5962d5b8cb76032d5a97e8aed7cf9f" + "'", str15, "310354661a5962d5b8cb76032d5a97e8aed7cf9f");
        org.junit.Assert.assertEquals("'" + str16 + "' != '" + "0000000000000000" + "'", str16, "0000000000000000");
        org.junit.Assert.assertNotNull(byteArray17);
        org.junit.Assert.assertArrayEquals(byteArray17, new byte[] { (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str19 + "' != '" + "$6$oWTfCqM4$VIqBhSK7v23O4tnjI9G0.ySCEVf2c5vaGxRmZoxTWiiZvbzGzEkGo0Wpjyh6Ih.x1txvfCdlGUL4IodLMR6a6/" + "'", str19, "$6$oWTfCqM4$VIqBhSK7v23O4tnjI9G0.ySCEVf2c5vaGxRmZoxTWiiZvbzGzEkGo0Wpjyh6Ih.x1txvfCdlGUL4IodLMR6a6/");
    }

    @Test
    public void test144() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test144");
        java.util.Comparator<org.apache.commons.codec.language.bm.Rule.Phoneme> phonemeComparator0 = org.apache.commons.codec.language.bm.Rule.Phoneme.COMPARATOR;
        org.junit.Assert.assertNotNull(phonemeComparator0);
    }

    @Test
    public void test145() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test145");
        byte[] byteArray7 = new byte[] { (byte) 10, (byte) 0, (byte) -1, (byte) 0, (byte) 1, (byte) 100 };
        java.lang.String str8 = org.apache.commons.codec.digest.Md5Crypt.md5Crypt(byteArray7);
        org.apache.commons.codec.binary.Base32 base32_9 = new org.apache.commons.codec.binary.Base32((int) (short) 1, byteArray7);
        org.junit.Assert.assertNotNull(byteArray7);
        org.junit.Assert.assertArrayEquals(byteArray7, new byte[] { (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0 });
// flaky "16) test145(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str8 + "' != '" + "$1$iWJESpnB$vDap.SotgdmY.f0Rjgzf3/" + "'", str8, "$1$iWJESpnB$vDap.SotgdmY.f0Rjgzf3/");
    }

    @Test
    public void test146() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test146");
        org.apache.commons.codec.binary.Base32OutputStream.Builder builder0 = org.apache.commons.codec.binary.Base32OutputStream.builder();
        java.io.OutputStream outputStream1 = null;
        org.apache.commons.codec.binary.Base64OutputStream base64OutputStream2 = new org.apache.commons.codec.binary.Base64OutputStream(outputStream1);
        org.apache.commons.codec.binary.Base32 base32_5 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray8 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str9 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray8);
        boolean boolean11 = base32_5.isInAlphabet(byteArray8, false);
        org.apache.commons.codec.binary.Base32 base32_12 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray15 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str16 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray15);
        boolean boolean18 = base32_12.isInAlphabet(byteArray15, false);
        byte[] byteArray19 = org.apache.commons.codec.digest.HmacUtils.hmacMd5(byteArray8, byteArray15);
        org.apache.commons.codec.binary.Base32OutputStream base32OutputStream20 = new org.apache.commons.codec.binary.Base32OutputStream((java.io.OutputStream) base64OutputStream2, false, 1, byteArray15);
        org.apache.commons.codec.binary.Base32OutputStream.Builder builder21 = builder0.setOutputStream((java.io.OutputStream) base32OutputStream20);
        org.apache.commons.codec.binary.Base32OutputStream.Builder builder23 = builder21.setEncode(false);
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(byteArray8);
        org.junit.Assert.assertArrayEquals(byteArray8, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str9 + "' != '" + "\000" + "'", str9, "\000");
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
        org.junit.Assert.assertNotNull(byteArray15);
        org.junit.Assert.assertArrayEquals(byteArray15, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str16 + "' != '" + "\000" + "'", str16, "\000");
        org.junit.Assert.assertTrue("'" + boolean18 + "' != '" + false + "'", boolean18 == false);
        org.junit.Assert.assertNotNull(byteArray19);
        org.junit.Assert.assertArrayEquals(byteArray19, new byte[] { (byte) -53, (byte) -62, (byte) 109, (byte) 108, (byte) 16, (byte) 34, (byte) -87, (byte) -93, (byte) 59, (byte) 95, (byte) 90, (byte) 76, (byte) 18, (byte) -71, (byte) -99, (byte) -51 });
        org.junit.Assert.assertNotNull(builder21);
        org.junit.Assert.assertNotNull(builder23);
    }

    @Test
    public void test147() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test147");
        java.io.InputStream inputStream0 = null;
        org.apache.commons.codec.binary.Base16InputStream base16InputStream1 = new org.apache.commons.codec.binary.Base16InputStream(inputStream0);
        char[] charArray4 = null;
        byte[] byteArray5 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray4);
        char[] charArray6 = org.apache.commons.codec.binary.Hex.encodeHex(byteArray5);
        byte[] byteArray7 = org.apache.commons.codec.binary.Hex.decodeHex(charArray6);
        org.apache.commons.codec.CodecPolicy codecPolicy8 = null;
        org.apache.commons.codec.binary.Base64InputStream base64InputStream9 = new org.apache.commons.codec.binary.Base64InputStream((java.io.InputStream) base16InputStream1, true, (int) (short) 100, byteArray7, codecPolicy8);
        boolean boolean10 = base16InputStream1.markSupported();
        org.junit.Assert.assertNotNull(byteArray5);
        org.junit.Assert.assertArrayEquals(byteArray5, new byte[] {});
        org.junit.Assert.assertNotNull(charArray6);
        org.junit.Assert.assertArrayEquals(charArray6, new char[] {});
        org.junit.Assert.assertNotNull(byteArray7);
        org.junit.Assert.assertArrayEquals(byteArray7, new byte[] {});
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
    }

    @Test
    public void test148() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test148");
        char[] charArray0 = null;
        byte[] byteArray1 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray0);
        byte[] byteArray3 = org.apache.commons.codec.binary.StringUtils.getBytesIso8859_1("c39394a96ebd177a239a817fe8453ab0");
        java.lang.String str4 = org.apache.commons.codec.digest.DigestUtils.sha384Hex(byteArray3);
        // The following exception was thrown during execution in test generation
        try {
            byte[] byteArray5 = org.apache.commons.codec.digest.HmacUtils.hmacSha1(byteArray1, byteArray3);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Empty key");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray1);
        org.junit.Assert.assertArrayEquals(byteArray1, new byte[] {});
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "e8d8123bcec18f5e308b0e5219338abba38bdeef310c42c2503cbd593fcf1fae3dfd1d38d64c108f9f299fde729e5f13" + "'", str4, "e8d8123bcec18f5e308b0e5219338abba38bdeef310c42c2503cbd593fcf1fae3dfd1d38d64c108f9f299fde729e5f13");
    }

    @Test
    public void test149() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test149");
        java.lang.String str1 = org.apache.commons.codec.digest.Md5Crypt.apr1Crypt("$1$PGGOlCMN$93x1SWmjd7FfTFj/z.PVu/");
// flaky "17) test149(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str1 + "' != '" + "$apr1$ZNSQf4Yn$QXEJNwxaUnpKp.vGYnwis/" + "'", str1, "$apr1$ZNSQf4Yn$QXEJNwxaUnpKp.vGYnwis/");
    }

    @Test
    public void test150() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test150");
        org.apache.commons.codec.language.bm.Languages.LanguageSet languageSet1 = null;
        org.apache.commons.codec.language.bm.Rule.Phoneme phoneme2 = new org.apache.commons.codec.language.bm.Rule.Phoneme((java.lang.CharSequence) "0000000000000000", languageSet1);
        org.apache.commons.codec.language.bm.Languages.LanguageSet languageSet4 = null;
        org.apache.commons.codec.language.bm.Rule.Phoneme phoneme5 = new org.apache.commons.codec.language.bm.Rule.Phoneme((java.lang.CharSequence) "0000000000000000", languageSet4);
        org.apache.commons.codec.language.bm.Rule.Phoneme phoneme6 = new org.apache.commons.codec.language.bm.Rule.Phoneme(phoneme2, phoneme5);
    }

    @Test
    public void test151() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test151");
        java.lang.String str0 = org.apache.commons.codec.CharEncoding.UTF_8;
        org.junit.Assert.assertEquals("'" + str0 + "' != '" + "UTF-8" + "'", str0, "UTF-8");
    }

    @Test
    public void test152() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test152");
        org.apache.commons.codec.binary.Base64.Builder builder0 = new org.apache.commons.codec.binary.Base64.Builder();
        java.io.InputStream inputStream1 = null;
        org.apache.commons.codec.binary.Base16InputStream base16InputStream2 = new org.apache.commons.codec.binary.Base16InputStream(inputStream1);
        byte[] byteArray11 = new byte[] { (byte) 10, (byte) 0, (byte) -1, (byte) 0, (byte) 1, (byte) 100 };
        java.lang.String str12 = org.apache.commons.codec.digest.Md5Crypt.md5Crypt(byteArray11);
        org.apache.commons.codec.CodecPolicy codecPolicy13 = null;
        org.apache.commons.codec.binary.Base64InputStream base64InputStream14 = new org.apache.commons.codec.binary.Base64InputStream((java.io.InputStream) base16InputStream2, false, (int) 'a', byteArray11, codecPolicy13);
        org.apache.commons.codec.binary.Base64.Builder builder15 = builder0.setDecodeTable(byteArray11);
        org.apache.commons.codec.binary.Base64.Builder builder16 = new org.apache.commons.codec.binary.Base64.Builder();
        java.io.InputStream inputStream17 = null;
        org.apache.commons.codec.binary.Base16InputStream base16InputStream18 = new org.apache.commons.codec.binary.Base16InputStream(inputStream17);
        byte[] byteArray27 = new byte[] { (byte) 10, (byte) 0, (byte) -1, (byte) 0, (byte) 1, (byte) 100 };
        java.lang.String str28 = org.apache.commons.codec.digest.Md5Crypt.md5Crypt(byteArray27);
        org.apache.commons.codec.CodecPolicy codecPolicy29 = null;
        org.apache.commons.codec.binary.Base64InputStream base64InputStream30 = new org.apache.commons.codec.binary.Base64InputStream((java.io.InputStream) base16InputStream18, false, (int) 'a', byteArray27, codecPolicy29);
        org.apache.commons.codec.binary.Base64.Builder builder31 = builder16.setDecodeTable(byteArray27);
        byte[] byteArray32 = org.apache.commons.codec.binary.Base64.decodeBase64UrlSafe(byteArray27);
        org.apache.commons.codec.binary.Base64.Builder builder33 = builder15.setDecodeTable(byteArray27);
        org.apache.commons.codec.binary.Base64.Builder builder34 = new org.apache.commons.codec.binary.Base64.Builder();
        java.io.InputStream inputStream35 = null;
        org.apache.commons.codec.binary.Base16InputStream base16InputStream36 = new org.apache.commons.codec.binary.Base16InputStream(inputStream35);
        byte[] byteArray45 = new byte[] { (byte) 10, (byte) 0, (byte) -1, (byte) 0, (byte) 1, (byte) 100 };
        java.lang.String str46 = org.apache.commons.codec.digest.Md5Crypt.md5Crypt(byteArray45);
        org.apache.commons.codec.CodecPolicy codecPolicy47 = null;
        org.apache.commons.codec.binary.Base64InputStream base64InputStream48 = new org.apache.commons.codec.binary.Base64InputStream((java.io.InputStream) base16InputStream36, false, (int) 'a', byteArray45, codecPolicy47);
        org.apache.commons.codec.binary.Base64.Builder builder49 = builder34.setDecodeTable(byteArray45);
        byte[] byteArray50 = org.apache.commons.codec.binary.Base64.decodeBase64UrlSafe(byteArray45);
        // The following exception was thrown during execution in test generation
        try {
            org.apache.commons.codec.binary.Base64.Builder builder51 = builder15.setEncodeTable(byteArray45);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: encodeTable must have exactly 64 entries.");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray11);
        org.junit.Assert.assertArrayEquals(byteArray11, new byte[] { (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0 });
// flaky "18) test152(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str12 + "' != '" + "$1$cNHBv15H$7ISwrJvJDeDefcrCwcncz/" + "'", str12, "$1$cNHBv15H$7ISwrJvJDeDefcrCwcncz/");
        org.junit.Assert.assertNotNull(builder15);
        org.junit.Assert.assertNotNull(byteArray27);
        org.junit.Assert.assertArrayEquals(byteArray27, new byte[] { (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0 });
// flaky "3) test152(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str28 + "' != '" + "$1$DyS1Ptck$7C9t6iNBMXs2AoXWbBT.30" + "'", str28, "$1$DyS1Ptck$7C9t6iNBMXs2AoXWbBT.30");
        org.junit.Assert.assertNotNull(builder31);
        org.junit.Assert.assertNotNull(byteArray32);
        org.junit.Assert.assertArrayEquals(byteArray32, new byte[] {});
        org.junit.Assert.assertNotNull(builder33);
        org.junit.Assert.assertNotNull(byteArray45);
        org.junit.Assert.assertArrayEquals(byteArray45, new byte[] { (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0 });
// flaky "1) test152(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str46 + "' != '" + "$1$V0ol98wL$YrNXnW1Idv6qaLkG6j3fD1" + "'", str46, "$1$V0ol98wL$YrNXnW1Idv6qaLkG6j3fD1");
        org.junit.Assert.assertNotNull(builder49);
        org.junit.Assert.assertNotNull(byteArray50);
        org.junit.Assert.assertArrayEquals(byteArray50, new byte[] {});
    }

    @Test
    public void test153() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test153");
        java.lang.String str0 = org.apache.commons.codec.digest.MessageDigestAlgorithms.SHAKE256_512;
        org.junit.Assert.assertEquals("'" + str0 + "' != '" + "SHAKE256-512" + "'", str0, "SHAKE256-512");
    }

    @Test
    public void test154() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test154");
        org.apache.commons.codec.binary.Base32 base32_0 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray3 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str4 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray3);
        boolean boolean6 = base32_0.isInAlphabet(byteArray3, false);
        org.apache.commons.codec.binary.Base32 base32_7 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray10 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str11 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray10);
        boolean boolean13 = base32_7.isInAlphabet(byteArray10, false);
        org.apache.commons.codec.binary.Base32 base32_14 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray17 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str18 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray17);
        boolean boolean20 = base32_14.isInAlphabet(byteArray17, false);
        byte[] byteArray21 = base32_7.encode(byteArray17);
        java.lang.String str22 = org.apache.commons.codec.digest.HmacUtils.hmacMd5Hex(byteArray3, byteArray21);
        java.lang.String str23 = org.apache.commons.codec.binary.StringUtils.newStringIso8859_1(byteArray21);
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertArrayEquals(byteArray3, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "\000" + "'", str4, "\000");
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNotNull(byteArray10);
        org.junit.Assert.assertArrayEquals(byteArray10, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "\000" + "'", str11, "\000");
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
        org.junit.Assert.assertNotNull(byteArray17);
        org.junit.Assert.assertArrayEquals(byteArray17, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str18 + "' != '" + "\000" + "'", str18, "\000");
        org.junit.Assert.assertTrue("'" + boolean20 + "' != '" + false + "'", boolean20 == false);
        org.junit.Assert.assertNotNull(byteArray21);
        org.junit.Assert.assertArrayEquals(byteArray21, new byte[] { (byte) 65, (byte) 65, (byte) 65, (byte) 65, (byte) 61, (byte) 61, (byte) 61, (byte) 61 });
        org.junit.Assert.assertEquals("'" + str22 + "' != '" + "207b9c1af4dbe7359a718262cce9251a" + "'", str22, "207b9c1af4dbe7359a718262cce9251a");
        org.junit.Assert.assertEquals("'" + str23 + "' != '" + "AAAA====" + "'", str23, "AAAA====");
    }

    @Test
    public void test155() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test155");
        org.apache.commons.codec.binary.Base32 base32_0 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray3 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str4 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray3);
        boolean boolean6 = base32_0.isInAlphabet(byteArray3, false);
        long[] longArray7 = org.apache.commons.codec.digest.MurmurHash3.hash128(byteArray3);
        java.lang.String str8 = org.apache.commons.codec.binary.StringUtils.newStringUtf16(byteArray3);
        java.lang.String str10 = org.apache.commons.codec.digest.Md5Crypt.apr1Crypt(byteArray3, "c39394a96ebd177a239a817fe8453ab0");
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertArrayEquals(byteArray3, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "\000" + "'", str4, "\000");
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNotNull(longArray7);
        org.junit.Assert.assertArrayEquals(longArray7, new long[] { 8478934883742226405L, (-1017287513161072006L) });
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "\000" + "'", str8, "\000");
        org.junit.Assert.assertEquals("'" + str10 + "' != '" + "$apr1$c39394a9$.hXMeHoW3z5i4CIZe8sNI/" + "'", str10, "$apr1$c39394a9$.hXMeHoW3z5i4CIZe8sNI/");
    }

    @Test
    public void test156() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test156");
        java.io.OutputStream outputStream0 = null;
        org.apache.commons.codec.binary.Base64OutputStream base64OutputStream1 = new org.apache.commons.codec.binary.Base64OutputStream(outputStream0);
        org.apache.commons.codec.binary.Base32 base32_4 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray7 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str8 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray7);
        boolean boolean10 = base32_4.isInAlphabet(byteArray7, false);
        org.apache.commons.codec.binary.Base32 base32_11 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray14 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str15 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray14);
        boolean boolean17 = base32_11.isInAlphabet(byteArray14, false);
        byte[] byteArray18 = org.apache.commons.codec.digest.HmacUtils.hmacMd5(byteArray7, byteArray14);
        org.apache.commons.codec.binary.Base32OutputStream base32OutputStream19 = new org.apache.commons.codec.binary.Base32OutputStream((java.io.OutputStream) base64OutputStream1, false, 1, byteArray14);
        org.apache.commons.codec.binary.Base32OutputStream base32OutputStream20 = new org.apache.commons.codec.binary.Base32OutputStream((java.io.OutputStream) base64OutputStream1);
        // The following exception was thrown during execution in test generation
        try {
            base64OutputStream1.flush();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray7);
        org.junit.Assert.assertArrayEquals(byteArray7, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "\000" + "'", str8, "\000");
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        org.junit.Assert.assertNotNull(byteArray14);
        org.junit.Assert.assertArrayEquals(byteArray14, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str15 + "' != '" + "\000" + "'", str15, "\000");
        org.junit.Assert.assertTrue("'" + boolean17 + "' != '" + false + "'", boolean17 == false);
        org.junit.Assert.assertNotNull(byteArray18);
        org.junit.Assert.assertArrayEquals(byteArray18, new byte[] { (byte) -53, (byte) -62, (byte) 109, (byte) 108, (byte) 16, (byte) 34, (byte) -87, (byte) -93, (byte) 59, (byte) 95, (byte) 90, (byte) 76, (byte) 18, (byte) -71, (byte) -99, (byte) -51 });
    }

    @Test
    public void test157() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test157");
        org.apache.commons.codec.digest.Crc16.Builder builder0 = new org.apache.commons.codec.digest.Crc16.Builder();
        int[] intArray1 = org.apache.commons.codec.digest.Crc16.getDnpTable();
        org.apache.commons.codec.digest.Crc16.Builder builder2 = builder0.setTable(intArray1);
        org.apache.commons.codec.digest.Crc16 crc16_3 = builder0.get();
        java.lang.String str4 = crc16_3.toString();
        org.junit.Assert.assertNotNull(intArray1);
        org.junit.Assert.assertNotNull(builder2);
        org.junit.Assert.assertNotNull(crc16_3);
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "Crc16 [init=0x0000, crc=0x0000, xorOut=0x0000, crc^xorOut=0x0000]" + "'", str4, "Crc16 [init=0x0000, crc=0x0000, xorOut=0x0000, crc^xorOut=0x0000]");
    }

    @Test
    public void test158() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test158");
        byte[] byteArray1 = org.apache.commons.codec.binary.StringUtils.getBytesIso8859_1("c39394a96ebd177a239a817fe8453ab0");
        org.apache.commons.codec.binary.Base32 base32_2 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray5 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str6 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray5);
        boolean boolean8 = base32_2.isInAlphabet(byteArray5, false);
        org.apache.commons.codec.binary.Base32 base32_9 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray12 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str13 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray12);
        boolean boolean15 = base32_9.isInAlphabet(byteArray12, false);
        java.lang.String str16 = org.apache.commons.codec.digest.UnixCrypt.crypt(byteArray12);
        java.lang.String str17 = org.apache.commons.codec.digest.HmacUtils.hmacSha1Hex(byteArray5, byteArray12);
        java.lang.String str18 = org.apache.commons.codec.binary.BinaryCodec.toAsciiString(byteArray12);
        java.lang.String str19 = org.apache.commons.codec.digest.HmacUtils.hmacSha1Hex(byteArray1, byteArray12);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str20 = org.apache.commons.codec.digest.DigestUtils.sha3_384Hex(byteArray12);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: java.security.NoSuchAlgorithmException: SHA3-384 MessageDigest not available");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray1);
        org.junit.Assert.assertNotNull(byteArray5);
        org.junit.Assert.assertArrayEquals(byteArray5, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "\000" + "'", str6, "\000");
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertNotNull(byteArray12);
        org.junit.Assert.assertArrayEquals(byteArray12, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "\000" + "'", str13, "\000");
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
// flaky "19) test158(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str16 + "' != '" + "2BX70nN5APgl2" + "'", str16, "2BX70nN5APgl2");
        org.junit.Assert.assertEquals("'" + str17 + "' != '" + "310354661a5962d5b8cb76032d5a97e8aed7cf9f" + "'", str17, "310354661a5962d5b8cb76032d5a97e8aed7cf9f");
        org.junit.Assert.assertEquals("'" + str18 + "' != '" + "0000000000000000" + "'", str18, "0000000000000000");
        org.junit.Assert.assertEquals("'" + str19 + "' != '" + "55351564fecb450a30b6ab9f075f0e63a5f492b6" + "'", str19, "55351564fecb450a30b6ab9f075f0e63a5f492b6");
    }

    @Test
    public void test159() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test159");
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str2 = org.apache.commons.codec.digest.UnixCrypt.crypt("nh11rN6ZAfNOQ", "US-ASCII");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Invalid salt value: US-ASCII");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test160() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test160");
        java.io.InputStream inputStream0 = null;
        org.apache.commons.codec.binary.Base16InputStream base16InputStream1 = new org.apache.commons.codec.binary.Base16InputStream(inputStream0);
        boolean boolean2 = base16InputStream1.markSupported();
        org.apache.commons.codec.binary.Base32InputStream base32InputStream4 = new org.apache.commons.codec.binary.Base32InputStream((java.io.InputStream) base16InputStream1, false);
        boolean boolean5 = base16InputStream1.markSupported();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str6 = org.apache.commons.codec.digest.DigestUtils.shaHex((java.io.InputStream) base16InputStream1);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
    }

    @Test
    public void test161() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test161");
        org.apache.commons.codec.net.URLCodec uRLCodec1 = new org.apache.commons.codec.net.URLCodec("PCqE1NnZAFIA2");
        org.apache.commons.codec.binary.Base32 base32_2 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray5 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str6 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray5);
        boolean boolean8 = base32_2.isInAlphabet(byteArray5, false);
        org.apache.commons.codec.binary.Base32 base32_9 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray12 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str13 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray12);
        boolean boolean15 = base32_9.isInAlphabet(byteArray12, false);
        java.lang.String str16 = org.apache.commons.codec.digest.UnixCrypt.crypt(byteArray12);
        java.lang.String str17 = org.apache.commons.codec.digest.HmacUtils.hmacSha1Hex(byteArray5, byteArray12);
        byte[] byteArray18 = uRLCodec1.decode(byteArray5);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str21 = uRLCodec1.decode("MD5", "SHA-224");
            org.junit.Assert.fail("Expected exception of type java.io.UnsupportedEncodingException; message: SHA-224");
        } catch (java.io.UnsupportedEncodingException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray5);
        org.junit.Assert.assertArrayEquals(byteArray5, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "\000" + "'", str6, "\000");
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertNotNull(byteArray12);
        org.junit.Assert.assertArrayEquals(byteArray12, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "\000" + "'", str13, "\000");
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
// flaky "20) test161(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str16 + "' != '" + "a5Dr3S/4WJFTw" + "'", str16, "a5Dr3S/4WJFTw");
        org.junit.Assert.assertEquals("'" + str17 + "' != '" + "310354661a5962d5b8cb76032d5a97e8aed7cf9f" + "'", str17, "310354661a5962d5b8cb76032d5a97e8aed7cf9f");
        org.junit.Assert.assertNotNull(byteArray18);
        org.junit.Assert.assertArrayEquals(byteArray18, new byte[] { (byte) 0, (byte) 0 });
    }

    @Test
    public void test162() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test162");
        byte[] byteArray1 = org.apache.commons.codec.binary.StringUtils.getBytesUsAscii("c7x1syRbCUeX.");
        org.junit.Assert.assertNotNull(byteArray1);
        org.junit.Assert.assertArrayEquals(byteArray1, new byte[] { (byte) 99, (byte) 55, (byte) 120, (byte) 49, (byte) 115, (byte) 121, (byte) 82, (byte) 98, (byte) 67, (byte) 85, (byte) 101, (byte) 88, (byte) 46 });
    }

    @Test
    public void test163() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test163");
        org.apache.commons.codec.binary.Base16 base16_0 = new org.apache.commons.codec.binary.Base16();
    }

    @Test
    public void test164() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test164");
        org.apache.commons.codec.DecoderException decoderException1 = new org.apache.commons.codec.DecoderException("12aa18a20782dbaf5cd723a5bf73299602957deb");
        java.lang.String str2 = decoderException1.toString();
        org.junit.Assert.assertEquals("'" + str2 + "' != '" + "org.apache.commons.codec.DecoderException: 12aa18a20782dbaf5cd723a5bf73299602957deb" + "'", str2, "org.apache.commons.codec.DecoderException: 12aa18a20782dbaf5cd723a5bf73299602957deb");
    }

    @Test
    public void test165() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test165");
        org.apache.commons.codec.DecoderException decoderException0 = new org.apache.commons.codec.DecoderException();
    }

    @Test
    public void test166() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test166");
        org.apache.commons.codec.net.QuotedPrintableCodec quotedPrintableCodec1 = new org.apache.commons.codec.net.QuotedPrintableCodec(true);
        java.lang.String str3 = quotedPrintableCodec1.decode("207b9c1af4dbe7359a718262cce9251a");
        java.lang.Class<?> wildcardClass4 = quotedPrintableCodec1.getClass();
        org.junit.Assert.assertEquals("'" + str3 + "' != '" + "207b9c1af4dbe7359a718262cce9251a" + "'", str3, "207b9c1af4dbe7359a718262cce9251a");
        org.junit.Assert.assertNotNull(wildcardClass4);
    }

    @Test
    public void test167() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test167");
        boolean boolean1 = org.apache.commons.codec.binary.Base64.isBase64Url("5574c9b84a224d6efecd951eb4462a84c4c1fe92");
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + true + "'", boolean1 == true);
    }

    @Test
    public void test168() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test168");
        org.apache.commons.codec.binary.Base32 base32_0 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray3 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str4 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray3);
        boolean boolean6 = base32_0.isInAlphabet(byteArray3, false);
        java.lang.String str7 = org.apache.commons.codec.digest.UnixCrypt.crypt(byteArray3);
        byte[] byteArray8 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(byteArray3);
        // The following exception was thrown during execution in test generation
        try {
            int int12 = org.apache.commons.codec.digest.MurmurHash3.hash32(byteArray3, (-559363818), (-559363818), (int) (byte) -1);
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: -1118727637");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertArrayEquals(byteArray3, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "\000" + "'", str4, "\000");
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
// flaky "21) test168(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str7 + "' != '" + "V8pk92xMxNKBg" + "'", str7, "V8pk92xMxNKBg");
        org.junit.Assert.assertNotNull(byteArray8);
        org.junit.Assert.assertArrayEquals(byteArray8, new byte[] {});
    }

    @Test
    public void test169() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test169");
        char[] charArray0 = null;
        byte[] byteArray1 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray0);
        char[] charArray2 = org.apache.commons.codec.binary.Hex.encodeHex(byteArray1);
        byte[] byteArray3 = org.apache.commons.codec.binary.Hex.decodeHex(charArray2);
        org.apache.commons.codec.language.Soundex soundex4 = new org.apache.commons.codec.language.Soundex(charArray2);
        byte[] byteArray5 = org.apache.commons.codec.binary.Hex.decodeHex(charArray2);
        org.junit.Assert.assertNotNull(byteArray1);
        org.junit.Assert.assertArrayEquals(byteArray1, new byte[] {});
        org.junit.Assert.assertNotNull(charArray2);
        org.junit.Assert.assertArrayEquals(charArray2, new char[] {});
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertArrayEquals(byteArray3, new byte[] {});
        org.junit.Assert.assertNotNull(byteArray5);
        org.junit.Assert.assertArrayEquals(byteArray5, new byte[] {});
    }

    @Test
    public void test170() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test170");
        org.apache.commons.codec.digest.GitIdentifiers.FileMode fileMode0 = org.apache.commons.codec.digest.GitIdentifiers.FileMode.EXECUTABLE;
        org.junit.Assert.assertTrue("'" + fileMode0 + "' != '" + org.apache.commons.codec.digest.GitIdentifiers.FileMode.EXECUTABLE + "'", fileMode0.equals(org.apache.commons.codec.digest.GitIdentifiers.FileMode.EXECUTABLE));
    }

    @Test
    public void test171() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test171");
        byte[] byteArray6 = new byte[] { (byte) 10, (byte) 0, (byte) -1, (byte) 0, (byte) 1, (byte) 100 };
        java.lang.String str7 = org.apache.commons.codec.digest.Md5Crypt.md5Crypt(byteArray6);
        byte[] byteArray8 = org.apache.commons.codec.binary.Base64.decodeBase64(byteArray6);
        org.junit.Assert.assertNotNull(byteArray6);
        org.junit.Assert.assertArrayEquals(byteArray6, new byte[] { (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0 });
// flaky "22) test171(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str7 + "' != '" + "$1$t4rkfBdW$KLJuNIXj1vXn0VXL04Ucd1" + "'", str7, "$1$t4rkfBdW$KLJuNIXj1vXn0VXL04Ucd1");
        org.junit.Assert.assertNotNull(byteArray8);
        org.junit.Assert.assertArrayEquals(byteArray8, new byte[] {});
    }

    @Test
    public void test172() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test172");
        org.apache.commons.codec.binary.Base58InputStream.Builder builder0 = org.apache.commons.codec.binary.Base58InputStream.builder();
        org.apache.commons.codec.binary.Base58.Builder builder1 = org.apache.commons.codec.binary.Base58.builder();
        org.apache.commons.codec.binary.Base58 base58_2 = new org.apache.commons.codec.binary.Base58(builder1);
        org.apache.commons.codec.binary.Base58InputStream.Builder builder3 = builder0.setBaseNCodec(base58_2);
        org.apache.commons.codec.binary.Base58.Builder builder4 = org.apache.commons.codec.binary.Base58.builder();
        org.apache.commons.codec.binary.Base58 base58_5 = new org.apache.commons.codec.binary.Base58(builder4);
        org.apache.commons.codec.binary.Base58InputStream.Builder builder6 = builder3.setBaseNCodec(base58_5);
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(builder1);
        org.junit.Assert.assertNotNull(builder3);
        org.junit.Assert.assertNotNull(builder4);
        org.junit.Assert.assertNotNull(builder6);
    }

    @Test
    public void test173() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test173");
        java.io.OutputStream outputStream0 = null;
        org.apache.commons.codec.binary.Base64OutputStream base64OutputStream1 = new org.apache.commons.codec.binary.Base64OutputStream(outputStream0);
        org.apache.commons.codec.binary.Base16OutputStream base16OutputStream3 = new org.apache.commons.codec.binary.Base16OutputStream(outputStream0, false);
        org.apache.commons.codec.binary.Base64OutputStream base64OutputStream4 = new org.apache.commons.codec.binary.Base64OutputStream((java.io.OutputStream) base16OutputStream3);
        org.apache.commons.codec.binary.Base16OutputStream base16OutputStream6 = new org.apache.commons.codec.binary.Base16OutputStream((java.io.OutputStream) base16OutputStream3, true);
    }

    @Test
    public void test174() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test174");
        org.apache.commons.codec.binary.Base32 base32_0 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray3 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str4 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray3);
        boolean boolean6 = base32_0.isInAlphabet(byteArray3, false);
        char[] charArray7 = null;
        byte[] byteArray8 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray7);
        boolean boolean10 = base32_0.isInAlphabet(byteArray8, true);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str12 = org.apache.commons.codec.binary.StringUtils.newString(byteArray8, "$1$iWJESpnB$vDap.SotgdmY.f0Rjgzf3/");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: $1$iWJESpnB$vDap.SotgdmY.f0Rjgzf3/: java.io.UnsupportedEncodingException: $1$iWJESpnB$vDap.SotgdmY.f0Rjgzf3/");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertArrayEquals(byteArray3, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "\000" + "'", str4, "\000");
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNotNull(byteArray8);
        org.junit.Assert.assertArrayEquals(byteArray8, new byte[] {});
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
    }

    @Test
    public void test175() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test175");
        long[] longArray1 = org.apache.commons.codec.digest.MurmurHash3.hash128("55351564fecb450a30b6ab9f075f0e63a5f492b6");
        org.junit.Assert.assertNotNull(longArray1);
        org.junit.Assert.assertArrayEquals(longArray1, new long[] { (-3453338468313787936L), 8811311123916480283L });
    }

    @Test
    public void test176() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test176");
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str1 = org.apache.commons.codec.digest.DigestUtils.sha3_256Hex("55351564fecb450a30b6ab9f075f0e63a5f492b6");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: java.security.NoSuchAlgorithmException: SHA3-256 MessageDigest not available");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test177() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test177");
        int[] intArray0 = org.apache.commons.codec.digest.Crc16.getNrsc5Table();
        org.junit.Assert.assertNotNull(intArray0);
    }

    @Test
    public void test178() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test178");
        int int0 = org.apache.commons.codec.binary.BaseNCodec.PEM_CHUNK_SIZE;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 64 + "'", int0 == 64);
    }

    @Test
    public void test179() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test179");
        org.apache.commons.codec.binary.Base32 base32_0 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray3 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str4 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray3);
        boolean boolean6 = base32_0.isInAlphabet(byteArray3, false);
        org.apache.commons.codec.binary.Base32 base32_7 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray10 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str11 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray10);
        boolean boolean13 = base32_7.isInAlphabet(byteArray10, false);
        byte[] byteArray14 = base32_0.encode(byteArray10);
        byte[] byteArray15 = org.apache.commons.codec.digest.DigestUtils.md2(byteArray10);
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertArrayEquals(byteArray3, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "\000" + "'", str4, "\000");
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNotNull(byteArray10);
        org.junit.Assert.assertArrayEquals(byteArray10, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "\000" + "'", str11, "\000");
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
        org.junit.Assert.assertNotNull(byteArray14);
        org.junit.Assert.assertArrayEquals(byteArray14, new byte[] { (byte) 65, (byte) 65, (byte) 65, (byte) 65, (byte) 61, (byte) 61, (byte) 61, (byte) 61 });
        org.junit.Assert.assertNotNull(byteArray15);
        org.junit.Assert.assertArrayEquals(byteArray15, new byte[] { (byte) 7, (byte) 14, (byte) 48, (byte) 47, (byte) 73, (byte) 25, (byte) 85, (byte) -75, (byte) -99, (byte) -125, (byte) -126, (byte) -103, (byte) 80, (byte) 41, (byte) -100, (byte) -17 });
    }

    @Test
    public void test180() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test180");
        java.io.InputStream inputStream0 = null;
        org.apache.commons.codec.binary.Base16InputStream base16InputStream1 = new org.apache.commons.codec.binary.Base16InputStream(inputStream0);
        boolean boolean2 = base16InputStream1.markSupported();
        org.apache.commons.codec.binary.Base32InputStream base32InputStream4 = new org.apache.commons.codec.binary.Base32InputStream((java.io.InputStream) base16InputStream1, false);
        // The following exception was thrown during execution in test generation
        try {
            byte[] byteArray5 = org.apache.commons.codec.digest.DigestUtils.sha384((java.io.InputStream) base32InputStream4);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
    }

    @Test
    public void test181() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test181");
        org.apache.commons.codec.DecoderException decoderException1 = new org.apache.commons.codec.DecoderException("12aa18a20782dbaf5cd723a5bf73299602957deb");
        java.lang.Throwable[] throwableArray2 = decoderException1.getSuppressed();
        org.junit.Assert.assertNotNull(throwableArray2);
        org.junit.Assert.assertArrayEquals(throwableArray2, new java.lang.Throwable[] {});
    }

    @Test
    public void test182() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test182");
        org.apache.commons.codec.language.bm.Languages.LanguageSet languageSet0 = org.apache.commons.codec.language.bm.Languages.NO_LANGUAGES;
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str1 = languageSet0.getAny();
            org.junit.Assert.fail("Expected exception of type java.util.NoSuchElementException; message: Can't fetch any language from the empty language set.");
        } catch (java.util.NoSuchElementException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(languageSet0);
    }

    @Test
    public void test183() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test183");
        java.io.InputStream inputStream0 = null;
        org.apache.commons.codec.binary.Base16InputStream base16InputStream1 = new org.apache.commons.codec.binary.Base16InputStream(inputStream0);
        base16InputStream1.mark((int) (byte) -1);
        java.security.MessageDigest messageDigest4 = org.apache.commons.codec.digest.DigestUtils.getShaDigest();
        byte[] byteArray6 = org.apache.commons.codec.binary.StringUtils.getBytesIso8859_1("c39394a96ebd177a239a817fe8453ab0");
        byte[] byteArray7 = org.apache.commons.codec.digest.GitIdentifiers.blobId(messageDigest4, byteArray6);
        // The following exception was thrown during execution in test generation
        try {
            int int10 = base16InputStream1.read(byteArray7, 0, (int) (short) 1);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(messageDigest4);
        org.junit.Assert.assertEquals(messageDigest4.toString(), "SHA-1 Message Digest from SUN, <initialized>\r\n");
        org.junit.Assert.assertNotNull(byteArray6);
        org.junit.Assert.assertNotNull(byteArray7);
        org.junit.Assert.assertArrayEquals(byteArray7, new byte[] { (byte) -7, (byte) -108, (byte) -7, (byte) 62, (byte) 70, (byte) -82, (byte) -11, (byte) 83, (byte) 103, (byte) 91, (byte) 61, (byte) -112, (byte) 48, (byte) -94, (byte) -84, (byte) -12, (byte) 43, (byte) 7, (byte) 93, (byte) 69 });
    }

    @Test
    public void test184() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test184");
        org.apache.commons.codec.language.bm.NameType nameType0 = null;
        org.apache.commons.codec.language.bm.RuleType ruleType1 = null;
        org.apache.commons.codec.language.bm.PhoneticEngine phoneticEngine3 = new org.apache.commons.codec.language.bm.PhoneticEngine(nameType0, ruleType1, false);
        org.apache.commons.codec.language.bm.Languages.LanguageSet languageSet5 = org.apache.commons.codec.language.bm.Languages.NO_LANGUAGES;
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str6 = phoneticEngine3.encode("$1$iWJESpnB$vDap.SotgdmY.f0Rjgzf3/", languageSet5);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(languageSet5);
    }

    @Test
    public void test185() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test185");
        org.apache.commons.codec.binary.Base32 base32_0 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray3 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str4 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray3);
        boolean boolean6 = base32_0.isInAlphabet(byteArray3, false);
        byte[] byteArray9 = org.apache.commons.codec.digest.HmacUtils.hmacSha512("310354661a5962d5b8cb76032d5a97e8aed7cf9f", "");
        byte[] byteArray10 = base32_0.encode(byteArray9);
        org.apache.commons.codec.digest.Blake3 blake3_11 = org.apache.commons.codec.digest.Blake3.initKeyDerivationFunction(byteArray10);
        javax.crypto.Mac mac12 = org.apache.commons.codec.digest.HmacUtils.getHmacSha256(byteArray10);
        // The following exception was thrown during execution in test generation
        try {
            byte[] byteArray13 = org.apache.commons.codec.digest.DigestUtils.shake128_256(byteArray10);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: java.security.NoSuchAlgorithmException: SHAKE128-256 MessageDigest not available");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertArrayEquals(byteArray3, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "\000" + "'", str4, "\000");
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNotNull(byteArray9);
        org.junit.Assert.assertNotNull(byteArray10);
        org.junit.Assert.assertNotNull(blake3_11);
        org.junit.Assert.assertNotNull(mac12);
    }

    @Test
    public void test186() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test186");
        java.security.MessageDigest messageDigest0 = org.apache.commons.codec.digest.DigestUtils.getMd2Digest();
        java.nio.file.Path path1 = null;
        java.nio.file.OpenOption[] openOptionArray2 = new java.nio.file.OpenOption[] {};
        // The following exception was thrown during execution in test generation
        try {
            java.security.MessageDigest messageDigest3 = org.apache.commons.codec.digest.DigestUtils.updateDigest(messageDigest0, path1, openOptionArray2);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(messageDigest0);
        org.junit.Assert.assertEquals(messageDigest0.toString(), "MD2 Message Digest from SUN, <initialized>\r\n");
        org.junit.Assert.assertNotNull(openOptionArray2);
        org.junit.Assert.assertArrayEquals(openOptionArray2, new java.nio.file.OpenOption[] {});
    }

    @Test
    public void test187() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test187");
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str1 = org.apache.commons.codec.digest.DigestUtils.shake128_256Hex("201853cbefef57ae7964e7c7c9d45f5a6181e885bb6ac9fd9219405e1d002b4c840dd2971f2b03900c9a6531eb470a5031425b4a8b14c684174cd97402968242");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: java.security.NoSuchAlgorithmException: SHAKE128-256 MessageDigest not available");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test188() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test188");
        java.lang.String str0 = org.apache.commons.codec.language.bm.Languages.ANY;
        org.junit.Assert.assertEquals("'" + str0 + "' != '" + "any" + "'", str0, "any");
    }

    @Test
    public void test189() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test189");
        org.apache.commons.codec.net.QCodec qCodec0 = new org.apache.commons.codec.net.QCodec();
        java.nio.charset.Charset charset2 = org.apache.commons.codec.Charsets.UTF_8;
        java.lang.String str3 = qCodec0.encode("$1$gUdz5yQC$LzF0PPk8FItQxPDDbufAf.", charset2);
        org.apache.commons.codec.CodecPolicy codecPolicy4 = null;
        org.apache.commons.codec.net.BCodec bCodec5 = new org.apache.commons.codec.net.BCodec(charset2, codecPolicy4);
        java.nio.charset.Charset charset6 = bCodec5.getCharset();
        org.junit.Assert.assertNotNull(charset2);
        org.junit.Assert.assertEquals("'" + str3 + "' != '" + "=?UTF-8?Q?$1$gUdz5yQC$LzF0PPk8FItQxPDDbufAf.?=" + "'", str3, "=?UTF-8?Q?$1$gUdz5yQC$LzF0PPk8FItQxPDDbufAf.?=");
        org.junit.Assert.assertNotNull(charset6);
    }

    @Test
    public void test190() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test190");
        org.apache.commons.codec.net.QuotedPrintableCodec quotedPrintableCodec1 = new org.apache.commons.codec.net.QuotedPrintableCodec(true);
        java.lang.String str3 = quotedPrintableCodec1.decode("207b9c1af4dbe7359a718262cce9251a");
        org.apache.commons.codec.language.Soundex soundex5 = new org.apache.commons.codec.language.Soundex("1489f923c4dca729178b3e3233458550d8dddf29");
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj6 = quotedPrintableCodec1.encode((java.lang.Object) soundex5);
            org.junit.Assert.fail("Expected exception of type org.apache.commons.codec.EncoderException; message: Objects of type org.apache.commons.codec.language.Soundex cannot be quoted-printable encoded");
        } catch (org.apache.commons.codec.EncoderException e) {
            // Expected exception.
        }
        org.junit.Assert.assertEquals("'" + str3 + "' != '" + "207b9c1af4dbe7359a718262cce9251a" + "'", str3, "207b9c1af4dbe7359a718262cce9251a");
    }

    @Test
    public void test191() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test191");
        org.apache.commons.codec.Charsets charsets0 = new org.apache.commons.codec.Charsets();
    }

    @Test
    public void test192() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test192");
        java.security.MessageDigest messageDigest1 = org.apache.commons.codec.digest.DigestUtils.getShaDigest();
        java.security.MessageDigest messageDigest2 = org.apache.commons.codec.digest.DigestUtils.getDigest("$1$KKLeGZ7T$cdbkvlhiX5MOwboeaxwMJ/", messageDigest1);
        org.junit.Assert.assertNotNull(messageDigest1);
        org.junit.Assert.assertEquals(messageDigest1.toString(), "SHA-1 Message Digest from SUN, <initialized>\r\n");
        org.junit.Assert.assertNotNull(messageDigest2);
        org.junit.Assert.assertEquals(messageDigest2.toString(), "SHA-1 Message Digest from SUN, <initialized>\r\n");
    }

    @Test
    public void test193() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test193");
        org.apache.commons.codec.digest.Crypt crypt0 = new org.apache.commons.codec.digest.Crypt();
    }

    @Test
    public void test194() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test194");
        char[] charArray0 = null;
        byte[] byteArray1 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray0);
        char[] charArray2 = org.apache.commons.codec.binary.Hex.encodeHex(byteArray1);
        org.apache.commons.codec.language.RefinedSoundex refinedSoundex3 = new org.apache.commons.codec.language.RefinedSoundex(charArray2);
        org.apache.commons.codec.language.Caverphone2 caverphone2_4 = new org.apache.commons.codec.language.Caverphone2();
        boolean boolean7 = caverphone2_4.isEncodeEqual("aQ3oIH6Lsvh.U", "e4d773805cf8a24c47b008dd036ad6936efd068b");
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj8 = refinedSoundex3.encode((java.lang.Object) caverphone2_4);
            org.junit.Assert.fail("Expected exception of type org.apache.commons.codec.EncoderException; message: Parameter supplied to RefinedSoundex encode is not of type java.lang.String");
        } catch (org.apache.commons.codec.EncoderException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray1);
        org.junit.Assert.assertArrayEquals(byteArray1, new byte[] {});
        org.junit.Assert.assertNotNull(charArray2);
        org.junit.Assert.assertArrayEquals(charArray2, new char[] {});
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
    }

    @Test
    public void test195() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test195");
        org.apache.commons.codec.language.Caverphone caverphone0 = new org.apache.commons.codec.language.Caverphone();
        boolean boolean3 = caverphone0.isCaverphoneEqual("vS9z8aDFF2nHI", "207b9c1af4dbe7359a718262cce9251a");
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
    }

    @Test
    public void test196() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test196");
        org.apache.commons.codec.net.QuotedPrintableCodec quotedPrintableCodec1 = new org.apache.commons.codec.net.QuotedPrintableCodec(true);
        java.lang.String str3 = quotedPrintableCodec1.decode("207b9c1af4dbe7359a718262cce9251a");
        java.lang.String str5 = quotedPrintableCodec1.decode("G30LGL3wppDqc");
        org.junit.Assert.assertEquals("'" + str3 + "' != '" + "207b9c1af4dbe7359a718262cce9251a" + "'", str3, "207b9c1af4dbe7359a718262cce9251a");
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "G30LGL3wppDqc" + "'", str5, "G30LGL3wppDqc");
    }

    @Test
    public void test197() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test197");
        org.apache.commons.codec.net.URLCodec uRLCodec1 = new org.apache.commons.codec.net.URLCodec("PCqE1NnZAFIA2");
        org.apache.commons.codec.binary.Base32 base32_2 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray5 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str6 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray5);
        boolean boolean8 = base32_2.isInAlphabet(byteArray5, false);
        org.apache.commons.codec.binary.Base32 base32_9 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray12 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str13 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray12);
        boolean boolean15 = base32_9.isInAlphabet(byteArray12, false);
        java.lang.String str16 = org.apache.commons.codec.digest.UnixCrypt.crypt(byteArray12);
        java.lang.String str17 = org.apache.commons.codec.digest.HmacUtils.hmacSha1Hex(byteArray5, byteArray12);
        byte[] byteArray18 = uRLCodec1.decode(byteArray5);
        java.lang.String str19 = uRLCodec1.getDefaultCharset();
        org.junit.Assert.assertNotNull(byteArray5);
        org.junit.Assert.assertArrayEquals(byteArray5, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "\000" + "'", str6, "\000");
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertNotNull(byteArray12);
        org.junit.Assert.assertArrayEquals(byteArray12, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "\000" + "'", str13, "\000");
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
// flaky "23) test197(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str16 + "' != '" + "c.LWg1bcyXAbA" + "'", str16, "c.LWg1bcyXAbA");
        org.junit.Assert.assertEquals("'" + str17 + "' != '" + "310354661a5962d5b8cb76032d5a97e8aed7cf9f" + "'", str17, "310354661a5962d5b8cb76032d5a97e8aed7cf9f");
        org.junit.Assert.assertNotNull(byteArray18);
        org.junit.Assert.assertArrayEquals(byteArray18, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str19 + "' != '" + "PCqE1NnZAFIA2" + "'", str19, "PCqE1NnZAFIA2");
    }

    @Test
    public void test198() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test198");
        java.lang.String str0 = org.apache.commons.codec.digest.MessageDigestAlgorithms.SHA_512_256;
        org.junit.Assert.assertEquals("'" + str0 + "' != '" + "SHA-512/256" + "'", str0, "SHA-512/256");
    }

    @Test
    public void test199() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test199");
        org.apache.commons.codec.binary.Base32 base32_0 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray3 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str4 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray3);
        boolean boolean6 = base32_0.isInAlphabet(byteArray3, false);
        byte[] byteArray8 = org.apache.commons.codec.binary.StringUtils.getBytesIso8859_1("c39394a96ebd177a239a817fe8453ab0");
        org.apache.commons.codec.binary.Base32 base32_9 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray12 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str13 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray12);
        boolean boolean15 = base32_9.isInAlphabet(byteArray12, false);
        org.apache.commons.codec.binary.Base32 base32_16 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray19 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str20 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray19);
        boolean boolean22 = base32_16.isInAlphabet(byteArray19, false);
        java.lang.String str23 = org.apache.commons.codec.digest.UnixCrypt.crypt(byteArray19);
        java.lang.String str24 = org.apache.commons.codec.digest.HmacUtils.hmacSha1Hex(byteArray12, byteArray19);
        java.lang.String str25 = org.apache.commons.codec.binary.BinaryCodec.toAsciiString(byteArray19);
        java.lang.String str26 = org.apache.commons.codec.digest.HmacUtils.hmacSha1Hex(byteArray8, byteArray19);
        // The following exception was thrown during execution in test generation
        try {
            byte[] byteArray29 = base32_0.encode(byteArray19, (int) (byte) 100, 64);
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: 100");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertArrayEquals(byteArray3, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "\000" + "'", str4, "\000");
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNotNull(byteArray8);
        org.junit.Assert.assertNotNull(byteArray12);
        org.junit.Assert.assertArrayEquals(byteArray12, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "\000" + "'", str13, "\000");
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertNotNull(byteArray19);
        org.junit.Assert.assertArrayEquals(byteArray19, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str20 + "' != '" + "\000" + "'", str20, "\000");
        org.junit.Assert.assertTrue("'" + boolean22 + "' != '" + false + "'", boolean22 == false);
// flaky "24) test199(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str23 + "' != '" + "CIZt7Q.dPGYV6" + "'", str23, "CIZt7Q.dPGYV6");
        org.junit.Assert.assertEquals("'" + str24 + "' != '" + "310354661a5962d5b8cb76032d5a97e8aed7cf9f" + "'", str24, "310354661a5962d5b8cb76032d5a97e8aed7cf9f");
        org.junit.Assert.assertEquals("'" + str25 + "' != '" + "0000000000000000" + "'", str25, "0000000000000000");
        org.junit.Assert.assertEquals("'" + str26 + "' != '" + "55351564fecb450a30b6ab9f075f0e63a5f492b6" + "'", str26, "55351564fecb450a30b6ab9f075f0e63a5f492b6");
    }

    @Test
    public void test200() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test200");
        org.apache.commons.codec.language.bm.NameType nameType0 = null;
        org.apache.commons.codec.language.bm.RuleType ruleType1 = null;
        org.apache.commons.codec.language.bm.PhoneticEngine phoneticEngine3 = new org.apache.commons.codec.language.bm.PhoneticEngine(nameType0, ruleType1, false);
        org.apache.commons.codec.language.bm.Languages.LanguageSet languageSet5 = org.apache.commons.codec.language.bm.Languages.NO_LANGUAGES;
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str6 = phoneticEngine3.encode("G30LGL3wppDqc", languageSet5);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(languageSet5);
    }

    @Test
    public void test201() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test201");
        org.apache.commons.codec.DecoderException decoderException1 = new org.apache.commons.codec.DecoderException("12aa18a20782dbaf5cd723a5bf73299602957deb");
        org.apache.commons.codec.DecoderException decoderException3 = new org.apache.commons.codec.DecoderException("12aa18a20782dbaf5cd723a5bf73299602957deb");
        decoderException1.addSuppressed((java.lang.Throwable) decoderException3);
        org.apache.commons.codec.DecoderException decoderException6 = new org.apache.commons.codec.DecoderException("12aa18a20782dbaf5cd723a5bf73299602957deb");
        org.apache.commons.codec.DecoderException decoderException8 = new org.apache.commons.codec.DecoderException("12aa18a20782dbaf5cd723a5bf73299602957deb");
        decoderException6.addSuppressed((java.lang.Throwable) decoderException8);
        decoderException1.addSuppressed((java.lang.Throwable) decoderException8);
    }

    @Test
    public void test202() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test202");
        org.apache.commons.codec.net.QCodec qCodec0 = new org.apache.commons.codec.net.QCodec();
        java.nio.charset.Charset charset2 = org.apache.commons.codec.Charsets.UTF_8;
        java.lang.String str3 = qCodec0.encode("$1$gUdz5yQC$LzF0PPk8FItQxPDDbufAf.", charset2);
        java.nio.charset.Charset charset5 = org.apache.commons.codec.Charsets.UTF_8;
        java.lang.String str6 = qCodec0.encode("01360240043788015936020505", charset5);
        org.junit.Assert.assertNotNull(charset2);
        org.junit.Assert.assertEquals("'" + str3 + "' != '" + "=?UTF-8?Q?$1$gUdz5yQC$LzF0PPk8FItQxPDDbufAf.?=" + "'", str3, "=?UTF-8?Q?$1$gUdz5yQC$LzF0PPk8FItQxPDDbufAf.?=");
        org.junit.Assert.assertNotNull(charset5);
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "=?UTF-8?Q?01360240043788015936020505?=" + "'", str6, "=?UTF-8?Q?01360240043788015936020505?=");
    }

    @Test
    public void test203() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test203");
        int int1 = org.apache.commons.codec.digest.MurmurHash2.hash32("=?UTF-8?Q?01360240043788015936020505?=");
        org.junit.Assert.assertTrue("'" + int1 + "' != '" + 1245495215 + "'", int1 == 1245495215);
    }

    @Test
    public void test204() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test204");
        java.io.InputStream inputStream0 = null;
        org.apache.commons.codec.binary.Base16InputStream base16InputStream1 = new org.apache.commons.codec.binary.Base16InputStream(inputStream0);
        char[] charArray4 = null;
        byte[] byteArray5 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray4);
        char[] charArray6 = org.apache.commons.codec.binary.Hex.encodeHex(byteArray5);
        byte[] byteArray7 = org.apache.commons.codec.binary.Hex.decodeHex(charArray6);
        org.apache.commons.codec.CodecPolicy codecPolicy8 = null;
        org.apache.commons.codec.binary.Base64InputStream base64InputStream9 = new org.apache.commons.codec.binary.Base64InputStream((java.io.InputStream) base16InputStream1, true, (int) (short) 100, byteArray7, codecPolicy8);
        int int10 = base64InputStream9.available();
        org.apache.commons.codec.binary.Base32 base32_13 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray16 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str17 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray16);
        boolean boolean19 = base32_13.isInAlphabet(byteArray16, false);
        org.apache.commons.codec.binary.Base32 base32_20 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray23 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str24 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray23);
        boolean boolean26 = base32_20.isInAlphabet(byteArray23, false);
        java.lang.String str27 = org.apache.commons.codec.digest.UnixCrypt.crypt(byteArray23);
        java.lang.String str28 = org.apache.commons.codec.digest.HmacUtils.hmacSha1Hex(byteArray16, byteArray23);
        java.lang.String str29 = org.apache.commons.codec.binary.BinaryCodec.toAsciiString(byteArray23);
        javax.crypto.Mac mac30 = org.apache.commons.codec.digest.HmacUtils.getHmacSha384(byteArray23);
        java.lang.String str31 = org.apache.commons.codec.binary.BinaryCodec.toAsciiString(byteArray23);
        org.apache.commons.codec.CodecPolicy codecPolicy34 = null;
        org.apache.commons.codec.binary.Base16 base16_35 = new org.apache.commons.codec.binary.Base16(true, codecPolicy34);
        boolean boolean37 = base16_35.isInAlphabet((byte) -1);
        org.apache.commons.codec.CodecPolicy codecPolicy38 = base16_35.getCodecPolicy();
        org.apache.commons.codec.binary.Base16 base16_39 = new org.apache.commons.codec.binary.Base16(true, codecPolicy38);
        org.apache.commons.codec.binary.Base64InputStream base64InputStream40 = new org.apache.commons.codec.binary.Base64InputStream((java.io.InputStream) base64InputStream9, false, (int) (byte) -1, byteArray23, codecPolicy38);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str41 = org.apache.commons.codec.digest.DigestUtils.sha384Hex((java.io.InputStream) base64InputStream9);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray5);
        org.junit.Assert.assertArrayEquals(byteArray5, new byte[] {});
        org.junit.Assert.assertNotNull(charArray6);
        org.junit.Assert.assertArrayEquals(charArray6, new char[] {});
        org.junit.Assert.assertNotNull(byteArray7);
        org.junit.Assert.assertArrayEquals(byteArray7, new byte[] {});
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 1 + "'", int10 == 1);
        org.junit.Assert.assertNotNull(byteArray16);
        org.junit.Assert.assertArrayEquals(byteArray16, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str17 + "' != '" + "\000" + "'", str17, "\000");
        org.junit.Assert.assertTrue("'" + boolean19 + "' != '" + false + "'", boolean19 == false);
        org.junit.Assert.assertNotNull(byteArray23);
        org.junit.Assert.assertArrayEquals(byteArray23, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str24 + "' != '" + "\000" + "'", str24, "\000");
        org.junit.Assert.assertTrue("'" + boolean26 + "' != '" + false + "'", boolean26 == false);
// flaky "25) test204(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str27 + "' != '" + ".GQfnkquv7jY2" + "'", str27, ".GQfnkquv7jY2");
        org.junit.Assert.assertEquals("'" + str28 + "' != '" + "310354661a5962d5b8cb76032d5a97e8aed7cf9f" + "'", str28, "310354661a5962d5b8cb76032d5a97e8aed7cf9f");
        org.junit.Assert.assertEquals("'" + str29 + "' != '" + "0000000000000000" + "'", str29, "0000000000000000");
        org.junit.Assert.assertNotNull(mac30);
        org.junit.Assert.assertEquals("'" + str31 + "' != '" + "0000000000000000" + "'", str31, "0000000000000000");
        org.junit.Assert.assertTrue("'" + boolean37 + "' != '" + false + "'", boolean37 == false);
        org.junit.Assert.assertTrue("'" + codecPolicy38 + "' != '" + org.apache.commons.codec.CodecPolicy.LENIENT + "'", codecPolicy38.equals(org.apache.commons.codec.CodecPolicy.LENIENT));
    }

    @Test
    public void test205() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test205");
        java.lang.String str1 = org.apache.commons.codec.digest.DigestUtils.sha384Hex("");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "38b060a751ac96384cd9327eb1b1e36a21fdb71114be07434c0cc7bf63f6e1da274edebfe76f65fbd51ad2f14898b95b" + "'", str1, "38b060a751ac96384cd9327eb1b1e36a21fdb71114be07434c0cc7bf63f6e1da274edebfe76f65fbd51ad2f14898b95b");
    }

    @Test
    public void test206() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test206");
        java.security.MessageDigest messageDigest0 = org.apache.commons.codec.digest.DigestUtils.getSha512Digest();
        java.nio.file.Path path1 = null;
        java.nio.file.OpenOption openOption2 = null;
        java.nio.file.OpenOption[] openOptionArray3 = new java.nio.file.OpenOption[] { openOption2 };
        // The following exception was thrown during execution in test generation
        try {
            byte[] byteArray4 = org.apache.commons.codec.digest.DigestUtils.digest(messageDigest0, path1, openOptionArray3);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(messageDigest0);
        org.junit.Assert.assertEquals(messageDigest0.toString(), "SHA-512 Message Digest from SUN, <initialized>\r\n");
        org.junit.Assert.assertNotNull(openOptionArray3);
        org.junit.Assert.assertArrayEquals(openOptionArray3, new java.nio.file.OpenOption[] { null });
    }

    @Test
    public void test207() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test207");
        java.nio.charset.Charset charset0 = org.apache.commons.codec.Charsets.UTF_16BE;
        org.junit.Assert.assertNotNull(charset0);
    }

    @Test
    public void test208() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test208");
        org.apache.commons.codec.binary.Base58.Builder builder0 = org.apache.commons.codec.binary.Base58.builder();
        org.apache.commons.codec.binary.Base58 base58_1 = new org.apache.commons.codec.binary.Base58(builder0);
        boolean boolean3 = base58_1.isInAlphabet("01360240043788015936020505");
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
    }

    @Test
    public void test209() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test209");
        org.apache.commons.codec.binary.Base64.Builder builder0 = org.apache.commons.codec.binary.Base64.builder();
        org.junit.Assert.assertNotNull(builder0);
    }

    @Test
    public void test210() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test210");
        java.io.InputStream inputStream0 = null;
        org.apache.commons.codec.binary.Base16InputStream base16InputStream1 = new org.apache.commons.codec.binary.Base16InputStream(inputStream0);
        boolean boolean2 = base16InputStream1.markSupported();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str3 = org.apache.commons.codec.digest.DigestUtils.sha3_384Hex((java.io.InputStream) base16InputStream1);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: java.security.NoSuchAlgorithmException: SHA3-384 MessageDigest not available");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
    }

    @Test
    public void test211() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test211");
        java.nio.charset.Charset charset0 = org.apache.commons.codec.binary.Hex.DEFAULT_CHARSET;
        org.junit.Assert.assertNotNull(charset0);
    }

    @Test
    public void test212() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test212");
        org.apache.commons.codec.binary.Base32 base32_0 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray3 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str4 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray3);
        boolean boolean6 = base32_0.isInAlphabet(byteArray3, false);
        long[] longArray7 = org.apache.commons.codec.digest.MurmurHash3.hash128(byteArray3);
        java.lang.String str8 = org.apache.commons.codec.binary.StringUtils.newStringUtf16(byteArray3);
        int int9 = org.apache.commons.codec.digest.MurmurHash3.hash32x86(byteArray3);
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertArrayEquals(byteArray3, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "\000" + "'", str4, "\000");
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNotNull(longArray7);
        org.junit.Assert.assertArrayEquals(longArray7, new long[] { 8478934883742226405L, (-1017287513161072006L) });
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "\000" + "'", str8, "\000");
        org.junit.Assert.assertTrue("'" + int9 + "' != '" + 821347078 + "'", int9 == 821347078);
    }

    @Test
    public void test213() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test213");
        org.apache.commons.codec.binary.Base32 base32_0 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray3 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str4 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray3);
        boolean boolean6 = base32_0.isInAlphabet(byteArray3, false);
        char[] charArray7 = null;
        byte[] byteArray8 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray7);
        boolean boolean10 = base32_0.isInAlphabet(byteArray8, true);
        byte[] byteArray11 = org.apache.commons.codec.digest.DigestUtils.sha1(byteArray8);
        // The following exception was thrown during execution in test generation
        try {
            byte[] byteArray12 = org.apache.commons.codec.digest.DigestUtils.sha512_256(byteArray8);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: java.security.NoSuchAlgorithmException: SHA-512/256 MessageDigest not available");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertArrayEquals(byteArray3, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "\000" + "'", str4, "\000");
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNotNull(byteArray8);
        org.junit.Assert.assertArrayEquals(byteArray8, new byte[] {});
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
        org.junit.Assert.assertNotNull(byteArray11);
        org.junit.Assert.assertArrayEquals(byteArray11, new byte[] { (byte) -38, (byte) 57, (byte) -93, (byte) -18, (byte) 94, (byte) 107, (byte) 75, (byte) 13, (byte) 50, (byte) 85, (byte) -65, (byte) -17, (byte) -107, (byte) 96, (byte) 24, (byte) -112, (byte) -81, (byte) -40, (byte) 7, (byte) 9 });
    }

    @Test
    public void test214() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test214");
        org.apache.commons.codec.binary.Base32InputStream.Builder builder0 = new org.apache.commons.codec.binary.Base32InputStream.Builder();
        org.apache.commons.codec.binary.Base32InputStream base32InputStream1 = builder0.get();
        boolean boolean2 = base32InputStream1.markSupported();
        int int3 = base32InputStream1.available();
        java.io.InputStream inputStream4 = null;
        org.apache.commons.codec.binary.Base16InputStream base16InputStream5 = new org.apache.commons.codec.binary.Base16InputStream(inputStream4);
        char[] charArray8 = null;
        byte[] byteArray9 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray8);
        char[] charArray10 = org.apache.commons.codec.binary.Hex.encodeHex(byteArray9);
        byte[] byteArray11 = org.apache.commons.codec.binary.Hex.decodeHex(charArray10);
        org.apache.commons.codec.CodecPolicy codecPolicy12 = null;
        org.apache.commons.codec.binary.Base64InputStream base64InputStream13 = new org.apache.commons.codec.binary.Base64InputStream((java.io.InputStream) base16InputStream5, true, (int) (short) 100, byteArray11, codecPolicy12);
        byte[] byteArray14 = org.apache.commons.codec.digest.DigestUtils.sha256(byteArray11);
        // The following exception was thrown during execution in test generation
        try {
            int int17 = base32InputStream1.read(byteArray14, (int) (short) 0, (int) 'a');
            org.junit.Assert.fail("Expected exception of type java.lang.IndexOutOfBoundsException; message: null");
        } catch (java.lang.IndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(base32InputStream1);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertTrue("'" + int3 + "' != '" + 1 + "'", int3 == 1);
        org.junit.Assert.assertNotNull(byteArray9);
        org.junit.Assert.assertArrayEquals(byteArray9, new byte[] {});
        org.junit.Assert.assertNotNull(charArray10);
        org.junit.Assert.assertArrayEquals(charArray10, new char[] {});
        org.junit.Assert.assertNotNull(byteArray11);
        org.junit.Assert.assertArrayEquals(byteArray11, new byte[] {});
        org.junit.Assert.assertNotNull(byteArray14);
    }

    @Test
    public void test215() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test215");
        java.io.InputStream inputStream0 = null;
        org.apache.commons.codec.binary.Base16InputStream base16InputStream1 = new org.apache.commons.codec.binary.Base16InputStream(inputStream0);
        char[] charArray4 = null;
        byte[] byteArray5 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray4);
        char[] charArray6 = org.apache.commons.codec.binary.Hex.encodeHex(byteArray5);
        byte[] byteArray7 = org.apache.commons.codec.binary.Hex.decodeHex(charArray6);
        org.apache.commons.codec.CodecPolicy codecPolicy8 = null;
        org.apache.commons.codec.binary.Base64InputStream base64InputStream9 = new org.apache.commons.codec.binary.Base64InputStream((java.io.InputStream) base16InputStream1, true, (int) (short) 100, byteArray7, codecPolicy8);
        char[] charArray12 = null;
        byte[] byteArray13 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray12);
        char[] charArray14 = org.apache.commons.codec.binary.Hex.encodeHex(byteArray13);
        java.lang.String str15 = org.apache.commons.codec.binary.BinaryCodec.toAsciiString(byteArray13);
        org.apache.commons.codec.CodecPolicy codecPolicy16 = null;
        org.apache.commons.codec.binary.Base32InputStream base32InputStream17 = new org.apache.commons.codec.binary.Base32InputStream((java.io.InputStream) base16InputStream1, false, (int) (byte) 100, byteArray13, codecPolicy16);
        java.io.InputStream inputStream18 = null;
        org.apache.commons.codec.binary.Base16InputStream base16InputStream19 = new org.apache.commons.codec.binary.Base16InputStream(inputStream18);
        char[] charArray22 = null;
        byte[] byteArray23 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray22);
        char[] charArray24 = org.apache.commons.codec.binary.Hex.encodeHex(byteArray23);
        byte[] byteArray25 = org.apache.commons.codec.binary.Hex.decodeHex(charArray24);
        org.apache.commons.codec.CodecPolicy codecPolicy26 = null;
        org.apache.commons.codec.binary.Base64InputStream base64InputStream27 = new org.apache.commons.codec.binary.Base64InputStream((java.io.InputStream) base16InputStream19, true, (int) (short) 100, byteArray25, codecPolicy26);
        int int28 = base64InputStream27.available();
        org.apache.commons.codec.binary.Base32 base32_31 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray34 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str35 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray34);
        boolean boolean37 = base32_31.isInAlphabet(byteArray34, false);
        org.apache.commons.codec.binary.Base32 base32_38 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray41 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str42 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray41);
        boolean boolean44 = base32_38.isInAlphabet(byteArray41, false);
        java.lang.String str45 = org.apache.commons.codec.digest.UnixCrypt.crypt(byteArray41);
        java.lang.String str46 = org.apache.commons.codec.digest.HmacUtils.hmacSha1Hex(byteArray34, byteArray41);
        java.lang.String str47 = org.apache.commons.codec.binary.BinaryCodec.toAsciiString(byteArray41);
        javax.crypto.Mac mac48 = org.apache.commons.codec.digest.HmacUtils.getHmacSha384(byteArray41);
        java.lang.String str49 = org.apache.commons.codec.binary.BinaryCodec.toAsciiString(byteArray41);
        org.apache.commons.codec.CodecPolicy codecPolicy52 = null;
        org.apache.commons.codec.binary.Base16 base16_53 = new org.apache.commons.codec.binary.Base16(true, codecPolicy52);
        boolean boolean55 = base16_53.isInAlphabet((byte) -1);
        org.apache.commons.codec.CodecPolicy codecPolicy56 = base16_53.getCodecPolicy();
        org.apache.commons.codec.binary.Base16 base16_57 = new org.apache.commons.codec.binary.Base16(true, codecPolicy56);
        org.apache.commons.codec.binary.Base64InputStream base64InputStream58 = new org.apache.commons.codec.binary.Base64InputStream((java.io.InputStream) base64InputStream27, false, (int) (byte) -1, byteArray41, codecPolicy56);
        // The following exception was thrown during execution in test generation
        try {
            byte[] byteArray59 = org.apache.commons.codec.digest.HmacUtils.hmacSha1(byteArray13, (java.io.InputStream) base64InputStream58);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Empty key");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray5);
        org.junit.Assert.assertArrayEquals(byteArray5, new byte[] {});
        org.junit.Assert.assertNotNull(charArray6);
        org.junit.Assert.assertArrayEquals(charArray6, new char[] {});
        org.junit.Assert.assertNotNull(byteArray7);
        org.junit.Assert.assertArrayEquals(byteArray7, new byte[] {});
        org.junit.Assert.assertNotNull(byteArray13);
        org.junit.Assert.assertArrayEquals(byteArray13, new byte[] {});
        org.junit.Assert.assertNotNull(charArray14);
        org.junit.Assert.assertArrayEquals(charArray14, new char[] {});
        org.junit.Assert.assertEquals("'" + str15 + "' != '" + "" + "'", str15, "");
        org.junit.Assert.assertNotNull(byteArray23);
        org.junit.Assert.assertArrayEquals(byteArray23, new byte[] {});
        org.junit.Assert.assertNotNull(charArray24);
        org.junit.Assert.assertArrayEquals(charArray24, new char[] {});
        org.junit.Assert.assertNotNull(byteArray25);
        org.junit.Assert.assertArrayEquals(byteArray25, new byte[] {});
        org.junit.Assert.assertTrue("'" + int28 + "' != '" + 1 + "'", int28 == 1);
        org.junit.Assert.assertNotNull(byteArray34);
        org.junit.Assert.assertArrayEquals(byteArray34, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str35 + "' != '" + "\000" + "'", str35, "\000");
        org.junit.Assert.assertTrue("'" + boolean37 + "' != '" + false + "'", boolean37 == false);
        org.junit.Assert.assertNotNull(byteArray41);
        org.junit.Assert.assertArrayEquals(byteArray41, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str42 + "' != '" + "\000" + "'", str42, "\000");
        org.junit.Assert.assertTrue("'" + boolean44 + "' != '" + false + "'", boolean44 == false);
// flaky "26) test215(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str45 + "' != '" + "bUDSL4Sd.ZCzE" + "'", str45, "bUDSL4Sd.ZCzE");
        org.junit.Assert.assertEquals("'" + str46 + "' != '" + "310354661a5962d5b8cb76032d5a97e8aed7cf9f" + "'", str46, "310354661a5962d5b8cb76032d5a97e8aed7cf9f");
        org.junit.Assert.assertEquals("'" + str47 + "' != '" + "0000000000000000" + "'", str47, "0000000000000000");
        org.junit.Assert.assertNotNull(mac48);
        org.junit.Assert.assertEquals("'" + str49 + "' != '" + "0000000000000000" + "'", str49, "0000000000000000");
        org.junit.Assert.assertTrue("'" + boolean55 + "' != '" + false + "'", boolean55 == false);
        org.junit.Assert.assertTrue("'" + codecPolicy56 + "' != '" + org.apache.commons.codec.CodecPolicy.LENIENT + "'", codecPolicy56.equals(org.apache.commons.codec.CodecPolicy.LENIENT));
    }

    @Test
    public void test216() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test216");
        java.util.BitSet bitSet0 = null;
        org.apache.commons.codec.binary.Base32 base32_1 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray4 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str5 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray4);
        boolean boolean7 = base32_1.isInAlphabet(byteArray4, false);
        byte[] byteArray9 = org.apache.commons.codec.binary.StringUtils.getBytesIso8859_1("c39394a96ebd177a239a817fe8453ab0");
        org.apache.commons.codec.binary.Base32 base32_10 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray13 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str14 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray13);
        boolean boolean16 = base32_10.isInAlphabet(byteArray13, false);
        org.apache.commons.codec.binary.Base32 base32_17 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray20 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str21 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray20);
        boolean boolean23 = base32_17.isInAlphabet(byteArray20, false);
        java.lang.String str24 = org.apache.commons.codec.digest.UnixCrypt.crypt(byteArray20);
        java.lang.String str25 = org.apache.commons.codec.digest.HmacUtils.hmacSha1Hex(byteArray13, byteArray20);
        java.lang.String str26 = org.apache.commons.codec.binary.BinaryCodec.toAsciiString(byteArray20);
        java.lang.String str27 = org.apache.commons.codec.digest.HmacUtils.hmacSha1Hex(byteArray9, byteArray20);
        byte[] byteArray30 = base32_1.encode(byteArray20, (int) '#', 0);
        byte[] byteArray32 = org.apache.commons.codec.net.QuotedPrintableCodec.encodeQuotedPrintable(bitSet0, byteArray30, false);
        org.junit.Assert.assertNotNull(byteArray4);
        org.junit.Assert.assertArrayEquals(byteArray4, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "\000" + "'", str5, "\000");
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertNotNull(byteArray9);
        org.junit.Assert.assertNotNull(byteArray13);
        org.junit.Assert.assertArrayEquals(byteArray13, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str14 + "' != '" + "\000" + "'", str14, "\000");
        org.junit.Assert.assertTrue("'" + boolean16 + "' != '" + false + "'", boolean16 == false);
        org.junit.Assert.assertNotNull(byteArray20);
        org.junit.Assert.assertArrayEquals(byteArray20, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str21 + "' != '" + "\000" + "'", str21, "\000");
        org.junit.Assert.assertTrue("'" + boolean23 + "' != '" + false + "'", boolean23 == false);
// flaky "27) test216(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str24 + "' != '" + "B78Te0Lj1zc0U" + "'", str24, "B78Te0Lj1zc0U");
        org.junit.Assert.assertEquals("'" + str25 + "' != '" + "310354661a5962d5b8cb76032d5a97e8aed7cf9f" + "'", str25, "310354661a5962d5b8cb76032d5a97e8aed7cf9f");
        org.junit.Assert.assertEquals("'" + str26 + "' != '" + "0000000000000000" + "'", str26, "0000000000000000");
        org.junit.Assert.assertEquals("'" + str27 + "' != '" + "55351564fecb450a30b6ab9f075f0e63a5f492b6" + "'", str27, "55351564fecb450a30b6ab9f075f0e63a5f492b6");
        org.junit.Assert.assertNotNull(byteArray30);
        org.junit.Assert.assertArrayEquals(byteArray30, new byte[] {});
        org.junit.Assert.assertNotNull(byteArray32);
        org.junit.Assert.assertArrayEquals(byteArray32, new byte[] {});
    }

    @Test
    public void test217() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test217");
        org.apache.commons.codec.binary.Hex hex0 = new org.apache.commons.codec.binary.Hex();
        org.apache.commons.codec.binary.Base32 base32_1 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray4 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str5 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray4);
        boolean boolean7 = base32_1.isInAlphabet(byteArray4, false);
        java.lang.String str8 = org.apache.commons.codec.digest.UnixCrypt.crypt(byteArray4);
        byte[] byteArray9 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(byteArray4);
        // The following exception was thrown during execution in test generation
        try {
            byte[] byteArray10 = hex0.decode(byteArray4);
            org.junit.Assert.fail("Expected exception of type org.apache.commons.codec.DecoderException; message: Illegal hexadecimal character 0x00 at index 0.");
        } catch (org.apache.commons.codec.DecoderException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray4);
        org.junit.Assert.assertArrayEquals(byteArray4, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "\000" + "'", str5, "\000");
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
// flaky "28) test217(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str8 + "' != '" + "1cEtGgBoRJqUg" + "'", str8, "1cEtGgBoRJqUg");
        org.junit.Assert.assertNotNull(byteArray9);
        org.junit.Assert.assertArrayEquals(byteArray9, new byte[] {});
    }

    @Test
    public void test218() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test218");
        org.apache.commons.codec.binary.Base58OutputStream.Builder builder0 = new org.apache.commons.codec.binary.Base58OutputStream.Builder();
        org.apache.commons.codec.binary.Base58OutputStream.Builder builder2 = builder0.setEncode(false);
        org.junit.Assert.assertNotNull(builder2);
    }

    @Test
    public void test219() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test219");
        java.security.MessageDigest messageDigest0 = org.apache.commons.codec.digest.DigestUtils.getMd2Digest();
        org.apache.commons.codec.digest.DigestUtils digestUtils1 = new org.apache.commons.codec.digest.DigestUtils(messageDigest0);
        org.junit.Assert.assertNotNull(messageDigest0);
        org.junit.Assert.assertEquals(messageDigest0.toString(), "MD2 Message Digest from SUN, <initialized>\r\n");
    }

    @Test
    public void test220() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test220");
        boolean boolean1 = org.apache.commons.codec.digest.DigestUtils.isAvailable("E87odzLw3LPbI");
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
    }

    @Test
    public void test221() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test221");
        org.apache.commons.codec.binary.Base58InputStream.Builder builder0 = org.apache.commons.codec.binary.Base58InputStream.builder();
        org.apache.commons.codec.binary.Base58.Builder builder1 = org.apache.commons.codec.binary.Base58.builder();
        org.apache.commons.codec.binary.Base58 base58_2 = new org.apache.commons.codec.binary.Base58(builder1);
        org.apache.commons.codec.binary.Base58InputStream.Builder builder3 = builder0.setBaseNCodec(base58_2);
        org.apache.commons.codec.binary.Base58InputStream.Builder builder5 = builder3.setEncode(false);
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(builder1);
        org.junit.Assert.assertNotNull(builder3);
        org.junit.Assert.assertNotNull(builder5);
    }

    @Test
    public void test222() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test222");
        java.io.InputStream inputStream0 = null;
        org.apache.commons.codec.binary.Base16InputStream base16InputStream1 = new org.apache.commons.codec.binary.Base16InputStream(inputStream0);
        char[] charArray4 = null;
        byte[] byteArray5 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray4);
        char[] charArray6 = org.apache.commons.codec.binary.Hex.encodeHex(byteArray5);
        byte[] byteArray7 = org.apache.commons.codec.binary.Hex.decodeHex(charArray6);
        org.apache.commons.codec.CodecPolicy codecPolicy8 = null;
        org.apache.commons.codec.binary.Base64InputStream base64InputStream9 = new org.apache.commons.codec.binary.Base64InputStream((java.io.InputStream) base16InputStream1, true, (int) (short) 100, byteArray7, codecPolicy8);
        // The following exception was thrown during execution in test generation
        try {
            byte[] byteArray10 = org.apache.commons.codec.digest.DigestUtils.md2((java.io.InputStream) base16InputStream1);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray5);
        org.junit.Assert.assertArrayEquals(byteArray5, new byte[] {});
        org.junit.Assert.assertNotNull(charArray6);
        org.junit.Assert.assertArrayEquals(charArray6, new char[] {});
        org.junit.Assert.assertNotNull(byteArray7);
        org.junit.Assert.assertArrayEquals(byteArray7, new byte[] {});
    }

    @Test
    public void test223() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test223");
        org.apache.commons.codec.net.URLCodec uRLCodec0 = new org.apache.commons.codec.net.URLCodec();
        java.lang.String str2 = uRLCodec0.encode("$1$hAQ8T6Eg$KVefjtCJjE3tsL8Q/uIi.0");
        org.junit.Assert.assertEquals("'" + str2 + "' != '" + "%241%24hAQ8T6Eg%24KVefjtCJjE3tsL8Q%2FuIi.0" + "'", str2, "%241%24hAQ8T6Eg%24KVefjtCJjE3tsL8Q%2FuIi.0");
    }

    @Test
    public void test224() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test224");
        // The following exception was thrown during execution in test generation
        try {
            byte[] byteArray1 = org.apache.commons.codec.digest.DigestUtils.sha3_256("8404a7262f45838faa05f26c92d87d10524773d9");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: java.security.NoSuchAlgorithmException: SHA3-256 MessageDigest not available");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test225() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test225");
        org.apache.commons.codec.CharEncoding charEncoding0 = new org.apache.commons.codec.CharEncoding();
    }

    @Test
    public void test226() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test226");
        org.apache.commons.codec.binary.Base32 base32_1 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray4 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str5 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray4);
        boolean boolean7 = base32_1.isInAlphabet(byteArray4, false);
        char[] charArray8 = null;
        byte[] byteArray9 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray8);
        boolean boolean11 = base32_1.isInAlphabet(byteArray9, true);
        // The following exception was thrown during execution in test generation
        try {
            javax.crypto.Mac mac12 = org.apache.commons.codec.digest.HmacUtils.getInitializedMac("$1$Y3HBlwwb$Gr02BB3yxBffi8KpVlcaC.", byteArray9);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Empty key");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray4);
        org.junit.Assert.assertArrayEquals(byteArray4, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "\000" + "'", str5, "\000");
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertNotNull(byteArray9);
        org.junit.Assert.assertArrayEquals(byteArray9, new byte[] {});
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + true + "'", boolean11 == true);
    }

    @Test
    public void test227() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test227");
        org.apache.commons.codec.binary.Base32 base32_0 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray3 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str4 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray3);
        boolean boolean6 = base32_0.isInAlphabet(byteArray3, false);
        byte[] byteArray8 = org.apache.commons.codec.binary.StringUtils.getBytesIso8859_1("c39394a96ebd177a239a817fe8453ab0");
        org.apache.commons.codec.binary.Base32 base32_9 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray12 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str13 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray12);
        boolean boolean15 = base32_9.isInAlphabet(byteArray12, false);
        org.apache.commons.codec.binary.Base32 base32_16 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray19 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str20 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray19);
        boolean boolean22 = base32_16.isInAlphabet(byteArray19, false);
        java.lang.String str23 = org.apache.commons.codec.digest.UnixCrypt.crypt(byteArray19);
        java.lang.String str24 = org.apache.commons.codec.digest.HmacUtils.hmacSha1Hex(byteArray12, byteArray19);
        java.lang.String str25 = org.apache.commons.codec.binary.BinaryCodec.toAsciiString(byteArray19);
        java.lang.String str26 = org.apache.commons.codec.digest.HmacUtils.hmacSha1Hex(byteArray8, byteArray19);
        byte[] byteArray29 = base32_0.encode(byteArray19, (int) '#', 0);
        // The following exception was thrown during execution in test generation
        try {
            byte[] byteArray30 = org.apache.commons.codec.digest.DigestUtils.shake256_512(byteArray29);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: java.security.NoSuchAlgorithmException: SHAKE256-512 MessageDigest not available");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertArrayEquals(byteArray3, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "\000" + "'", str4, "\000");
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNotNull(byteArray8);
        org.junit.Assert.assertNotNull(byteArray12);
        org.junit.Assert.assertArrayEquals(byteArray12, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "\000" + "'", str13, "\000");
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertNotNull(byteArray19);
        org.junit.Assert.assertArrayEquals(byteArray19, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str20 + "' != '" + "\000" + "'", str20, "\000");
        org.junit.Assert.assertTrue("'" + boolean22 + "' != '" + false + "'", boolean22 == false);
// flaky "29) test227(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str23 + "' != '" + "wJusRP6u3iHZw" + "'", str23, "wJusRP6u3iHZw");
        org.junit.Assert.assertEquals("'" + str24 + "' != '" + "310354661a5962d5b8cb76032d5a97e8aed7cf9f" + "'", str24, "310354661a5962d5b8cb76032d5a97e8aed7cf9f");
        org.junit.Assert.assertEquals("'" + str25 + "' != '" + "0000000000000000" + "'", str25, "0000000000000000");
        org.junit.Assert.assertEquals("'" + str26 + "' != '" + "55351564fecb450a30b6ab9f075f0e63a5f492b6" + "'", str26, "55351564fecb450a30b6ab9f075f0e63a5f492b6");
        org.junit.Assert.assertNotNull(byteArray29);
        org.junit.Assert.assertArrayEquals(byteArray29, new byte[] {});
    }

    @Test
    public void test228() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test228");
        java.io.InputStream inputStream0 = null;
        org.apache.commons.codec.binary.Base16InputStream base16InputStream1 = new org.apache.commons.codec.binary.Base16InputStream(inputStream0);
        char[] charArray4 = null;
        byte[] byteArray5 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray4);
        char[] charArray6 = org.apache.commons.codec.binary.Hex.encodeHex(byteArray5);
        byte[] byteArray7 = org.apache.commons.codec.binary.Hex.decodeHex(charArray6);
        org.apache.commons.codec.CodecPolicy codecPolicy8 = null;
        org.apache.commons.codec.binary.Base64InputStream base64InputStream9 = new org.apache.commons.codec.binary.Base64InputStream((java.io.InputStream) base16InputStream1, true, (int) (short) 100, byteArray7, codecPolicy8);
        char[] charArray12 = null;
        byte[] byteArray13 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray12);
        char[] charArray14 = org.apache.commons.codec.binary.Hex.encodeHex(byteArray13);
        java.lang.String str15 = org.apache.commons.codec.binary.BinaryCodec.toAsciiString(byteArray13);
        org.apache.commons.codec.CodecPolicy codecPolicy16 = null;
        org.apache.commons.codec.binary.Base32InputStream base32InputStream17 = new org.apache.commons.codec.binary.Base32InputStream((java.io.InputStream) base16InputStream1, false, (int) (byte) 100, byteArray13, codecPolicy16);
        org.apache.commons.codec.binary.Base64InputStream base64InputStream19 = new org.apache.commons.codec.binary.Base64InputStream((java.io.InputStream) base32InputStream17, false);
        org.junit.Assert.assertNotNull(byteArray5);
        org.junit.Assert.assertArrayEquals(byteArray5, new byte[] {});
        org.junit.Assert.assertNotNull(charArray6);
        org.junit.Assert.assertArrayEquals(charArray6, new char[] {});
        org.junit.Assert.assertNotNull(byteArray7);
        org.junit.Assert.assertArrayEquals(byteArray7, new byte[] {});
        org.junit.Assert.assertNotNull(byteArray13);
        org.junit.Assert.assertArrayEquals(byteArray13, new byte[] {});
        org.junit.Assert.assertNotNull(charArray14);
        org.junit.Assert.assertArrayEquals(charArray14, new char[] {});
        org.junit.Assert.assertEquals("'" + str15 + "' != '" + "" + "'", str15, "");
    }

    @Test
    public void test229() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test229");
        java.io.InputStream inputStream0 = null;
        org.apache.commons.codec.binary.Base16InputStream base16InputStream1 = new org.apache.commons.codec.binary.Base16InputStream(inputStream0);
        char[] charArray4 = null;
        byte[] byteArray5 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray4);
        char[] charArray6 = org.apache.commons.codec.binary.Hex.encodeHex(byteArray5);
        byte[] byteArray7 = org.apache.commons.codec.binary.Hex.decodeHex(charArray6);
        org.apache.commons.codec.CodecPolicy codecPolicy8 = null;
        org.apache.commons.codec.binary.Base64InputStream base64InputStream9 = new org.apache.commons.codec.binary.Base64InputStream((java.io.InputStream) base16InputStream1, true, (int) (short) 100, byteArray7, codecPolicy8);
        int int10 = base64InputStream9.available();
        org.apache.commons.codec.binary.Base16InputStream base16InputStream11 = new org.apache.commons.codec.binary.Base16InputStream((java.io.InputStream) base64InputStream9);
        // The following exception was thrown during execution in test generation
        try {
            base16InputStream11.reset();
            org.junit.Assert.fail("Expected exception of type java.io.IOException; message: mark/reset not supported");
        } catch (java.io.IOException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray5);
        org.junit.Assert.assertArrayEquals(byteArray5, new byte[] {});
        org.junit.Assert.assertNotNull(charArray6);
        org.junit.Assert.assertArrayEquals(charArray6, new char[] {});
        org.junit.Assert.assertNotNull(byteArray7);
        org.junit.Assert.assertArrayEquals(byteArray7, new byte[] {});
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 1 + "'", int10 == 1);
    }

    @Test
    public void test230() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test230");
        org.apache.commons.codec.binary.Base32 base32_0 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray3 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str4 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray3);
        boolean boolean6 = base32_0.isInAlphabet(byteArray3, false);
        char[] charArray7 = null;
        byte[] byteArray8 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray7);
        boolean boolean10 = base32_0.isInAlphabet(byteArray8, true);
        byte[] byteArray13 = org.apache.commons.codec.digest.HmacUtils.hmacSha512("310354661a5962d5b8cb76032d5a97e8aed7cf9f", "");
        boolean boolean15 = base32_0.isInAlphabet(byteArray13, true);
        org.apache.commons.codec.binary.Base32InputStream.Builder builder16 = new org.apache.commons.codec.binary.Base32InputStream.Builder();
        org.apache.commons.codec.binary.Base32InputStream base32InputStream17 = builder16.get();
        // The following exception was thrown during execution in test generation
        try {
            byte[] byteArray18 = org.apache.commons.codec.digest.HmacUtils.hmacMd5(byteArray13, (java.io.InputStream) base32InputStream17);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertArrayEquals(byteArray3, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "\000" + "'", str4, "\000");
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNotNull(byteArray8);
        org.junit.Assert.assertArrayEquals(byteArray8, new byte[] {});
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
        org.junit.Assert.assertNotNull(byteArray13);
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertNotNull(base32InputStream17);
    }

    @Test
    public void test231() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test231");
        java.nio.ByteBuffer byteBuffer0 = null;
        // The following exception was thrown during execution in test generation
        try {
            char[] charArray2 = org.apache.commons.codec.binary.Hex.encodeHex(byteBuffer0, false);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test232() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test232");
        java.lang.String str0 = org.apache.commons.codec.digest.MessageDigestAlgorithms.SHA_512_224;
        org.junit.Assert.assertEquals("'" + str0 + "' != '" + "SHA-512/224" + "'", str0, "SHA-512/224");
    }

    @Test
    public void test233() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test233");
        org.apache.commons.codec.binary.Base32 base32_0 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray3 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str4 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray3);
        boolean boolean6 = base32_0.isInAlphabet(byteArray3, false);
        org.apache.commons.codec.binary.Base32 base32_7 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray10 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str11 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray10);
        boolean boolean13 = base32_7.isInAlphabet(byteArray10, false);
        java.lang.String str14 = org.apache.commons.codec.digest.UnixCrypt.crypt(byteArray10);
        java.lang.String str15 = org.apache.commons.codec.digest.HmacUtils.hmacSha1Hex(byteArray3, byteArray10);
        java.lang.String str16 = org.apache.commons.codec.binary.BinaryCodec.toAsciiString(byteArray10);
        javax.crypto.Mac mac17 = org.apache.commons.codec.digest.HmacUtils.getHmacSha384(byteArray10);
        javax.crypto.Mac mac19 = org.apache.commons.codec.digest.HmacUtils.updateHmac(mac17, "SHA-512/224");
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertArrayEquals(byteArray3, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "\000" + "'", str4, "\000");
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNotNull(byteArray10);
        org.junit.Assert.assertArrayEquals(byteArray10, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "\000" + "'", str11, "\000");
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
// flaky "30) test233(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str14 + "' != '" + "/IwbDonZ0VAww" + "'", str14, "/IwbDonZ0VAww");
        org.junit.Assert.assertEquals("'" + str15 + "' != '" + "310354661a5962d5b8cb76032d5a97e8aed7cf9f" + "'", str15, "310354661a5962d5b8cb76032d5a97e8aed7cf9f");
        org.junit.Assert.assertEquals("'" + str16 + "' != '" + "0000000000000000" + "'", str16, "0000000000000000");
        org.junit.Assert.assertNotNull(mac17);
        org.junit.Assert.assertNotNull(mac19);
    }

    @Test
    public void test234() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test234");
        java.io.OutputStream outputStream0 = null;
        org.apache.commons.codec.binary.Base64OutputStream base64OutputStream1 = new org.apache.commons.codec.binary.Base64OutputStream(outputStream0);
        org.apache.commons.codec.binary.Base32 base32_4 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray7 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str8 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray7);
        boolean boolean10 = base32_4.isInAlphabet(byteArray7, false);
        org.apache.commons.codec.binary.Base32 base32_11 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray14 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str15 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray14);
        boolean boolean17 = base32_11.isInAlphabet(byteArray14, false);
        byte[] byteArray18 = org.apache.commons.codec.digest.HmacUtils.hmacMd5(byteArray7, byteArray14);
        org.apache.commons.codec.binary.Base32OutputStream base32OutputStream19 = new org.apache.commons.codec.binary.Base32OutputStream((java.io.OutputStream) base64OutputStream1, false, 1, byteArray14);
        base32OutputStream19.eof();
        org.junit.Assert.assertNotNull(byteArray7);
        org.junit.Assert.assertArrayEquals(byteArray7, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "\000" + "'", str8, "\000");
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        org.junit.Assert.assertNotNull(byteArray14);
        org.junit.Assert.assertArrayEquals(byteArray14, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str15 + "' != '" + "\000" + "'", str15, "\000");
        org.junit.Assert.assertTrue("'" + boolean17 + "' != '" + false + "'", boolean17 == false);
        org.junit.Assert.assertNotNull(byteArray18);
        org.junit.Assert.assertArrayEquals(byteArray18, new byte[] { (byte) -53, (byte) -62, (byte) 109, (byte) 108, (byte) 16, (byte) 34, (byte) -87, (byte) -93, (byte) 59, (byte) 95, (byte) 90, (byte) 76, (byte) 18, (byte) -71, (byte) -99, (byte) -51 });
    }

    @Test
    public void test235() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test235");
        org.apache.commons.codec.binary.Base58OutputStream.Builder builder0 = org.apache.commons.codec.binary.Base58OutputStream.builder();
        org.junit.Assert.assertNotNull(builder0);
    }

    @Test
    public void test236() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test236");
        // The following exception was thrown during execution in test generation
        try {
            java.io.InputStream inputStream1 = org.apache.commons.codec.Resources.getInputStream("SHA-224");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Unable to resolve required resource: SHA-224");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test237() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test237");
        java.io.InputStream inputStream0 = null;
        org.apache.commons.codec.binary.Base16InputStream base16InputStream1 = new org.apache.commons.codec.binary.Base16InputStream(inputStream0);
        char[] charArray4 = null;
        byte[] byteArray5 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray4);
        char[] charArray6 = org.apache.commons.codec.binary.Hex.encodeHex(byteArray5);
        byte[] byteArray7 = org.apache.commons.codec.binary.Hex.decodeHex(charArray6);
        org.apache.commons.codec.CodecPolicy codecPolicy8 = null;
        org.apache.commons.codec.binary.Base64InputStream base64InputStream9 = new org.apache.commons.codec.binary.Base64InputStream((java.io.InputStream) base16InputStream1, true, (int) (short) 100, byteArray7, codecPolicy8);
        int int10 = base64InputStream9.available();
        org.apache.commons.codec.binary.Base16InputStream base16InputStream11 = new org.apache.commons.codec.binary.Base16InputStream((java.io.InputStream) base64InputStream9);
        // The following exception was thrown during execution in test generation
        try {
            byte[] byteArray12 = org.apache.commons.codec.digest.DigestUtils.shake128_256((java.io.InputStream) base64InputStream9);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: java.security.NoSuchAlgorithmException: SHAKE128-256 MessageDigest not available");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray5);
        org.junit.Assert.assertArrayEquals(byteArray5, new byte[] {});
        org.junit.Assert.assertNotNull(charArray6);
        org.junit.Assert.assertArrayEquals(charArray6, new char[] {});
        org.junit.Assert.assertNotNull(byteArray7);
        org.junit.Assert.assertArrayEquals(byteArray7, new byte[] {});
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 1 + "'", int10 == 1);
    }

    @Test
    public void test238() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test238");
        char[] charArray0 = null;
        byte[] byteArray1 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray0);
        char[] charArray2 = org.apache.commons.codec.binary.Hex.encodeHex(byteArray1);
        org.apache.commons.codec.language.RefinedSoundex refinedSoundex3 = new org.apache.commons.codec.language.RefinedSoundex(charArray2);
        int int6 = refinedSoundex3.difference("WGJU3Y88YVchU", "I3HMbnG3q1U2Y");
        org.junit.Assert.assertNotNull(byteArray1);
        org.junit.Assert.assertArrayEquals(byteArray1, new byte[] {});
        org.junit.Assert.assertNotNull(charArray2);
        org.junit.Assert.assertArrayEquals(charArray2, new char[] {});
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
    }

    @Test
    public void test239() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test239");
        org.apache.commons.codec.binary.Base32InputStream.Builder builder0 = new org.apache.commons.codec.binary.Base32InputStream.Builder();
        java.io.InputStream inputStream1 = null;
        org.apache.commons.codec.binary.Base16InputStream base16InputStream2 = new org.apache.commons.codec.binary.Base16InputStream(inputStream1);
        byte[] byteArray11 = new byte[] { (byte) 10, (byte) 0, (byte) -1, (byte) 0, (byte) 1, (byte) 100 };
        java.lang.String str12 = org.apache.commons.codec.digest.Md5Crypt.md5Crypt(byteArray11);
        org.apache.commons.codec.CodecPolicy codecPolicy13 = null;
        org.apache.commons.codec.binary.Base64InputStream base64InputStream14 = new org.apache.commons.codec.binary.Base64InputStream((java.io.InputStream) base16InputStream2, false, (int) 'a', byteArray11, codecPolicy13);
        org.apache.commons.codec.binary.Base32InputStream.Builder builder15 = builder0.setInputStream((java.io.InputStream) base64InputStream14);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str16 = org.apache.commons.codec.digest.DigestUtils.sha3_384Hex((java.io.InputStream) base64InputStream14);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: java.security.NoSuchAlgorithmException: SHA3-384 MessageDigest not available");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray11);
        org.junit.Assert.assertArrayEquals(byteArray11, new byte[] { (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0 });
// flaky "31) test239(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str12 + "' != '" + "$1$JyLtBwKb$YKgCiuTWnK8BzPUaxKp8a." + "'", str12, "$1$JyLtBwKb$YKgCiuTWnK8BzPUaxKp8a.");
        org.junit.Assert.assertNotNull(builder15);
    }

    @Test
    public void test240() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test240");
        org.apache.commons.codec.binary.Base58.Builder builder0 = new org.apache.commons.codec.binary.Base58.Builder();
    }

    @Test
    public void test241() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test241");
        char[] charArray6 = new char[] { '#', '4', '4', '4', '#', ' ' };
        // The following exception was thrown during execution in test generation
        try {
            byte[] byteArray7 = org.apache.commons.codec.binary.Hex.decodeHex(charArray6);
            org.junit.Assert.fail("Expected exception of type org.apache.commons.codec.DecoderException; message: Illegal hexadecimal character 0x23 at index 0.");
        } catch (org.apache.commons.codec.DecoderException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(charArray6);
        org.junit.Assert.assertArrayEquals(charArray6, new char[] { '#', '4', '4', '4', '#', ' ' });
    }

    @Test
    public void test242() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test242");
        org.apache.commons.codec.language.RefinedSoundex refinedSoundex1 = new org.apache.commons.codec.language.RefinedSoundex("SHA-512/256");
    }

    @Test
    public void test243() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test243");
        org.apache.commons.codec.language.bm.NameType nameType0 = null;
        org.apache.commons.codec.language.bm.RuleType ruleType1 = null;
        org.apache.commons.codec.language.bm.Languages.LanguageSet languageSet2 = org.apache.commons.codec.language.bm.Languages.NO_LANGUAGES;
        // The following exception was thrown during execution in test generation
        try {
            java.util.List<org.apache.commons.codec.language.bm.Rule> ruleList3 = org.apache.commons.codec.language.bm.Rule.getInstance(nameType0, ruleType1, languageSet2);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(languageSet2);
    }

    @Test
    public void test244() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test244");
        java.security.MessageDigest messageDigest1 = org.apache.commons.codec.digest.DigestUtils.getShaDigest();
        org.apache.commons.codec.digest.DigestUtils digestUtils2 = new org.apache.commons.codec.digest.DigestUtils(messageDigest1);
        java.security.MessageDigest messageDigest3 = org.apache.commons.codec.digest.DigestUtils.getDigest("e8d8123bcec18f5e308b0e5219338abba38bdeef310c42c2503cbd593fcf1fae3dfd1d38d64c108f9f299fde729e5f13", messageDigest1);
        org.apache.commons.codec.digest.DigestUtils digestUtils4 = new org.apache.commons.codec.digest.DigestUtils(messageDigest1);
        java.nio.file.Path path5 = null;
        java.nio.file.OpenOption openOption6 = null;
        java.nio.file.OpenOption[] openOptionArray7 = new java.nio.file.OpenOption[] { openOption6 };
        // The following exception was thrown during execution in test generation
        try {
            byte[] byteArray8 = digestUtils4.digest(path5, openOptionArray7);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(messageDigest1);
        org.junit.Assert.assertEquals(messageDigest1.toString(), "SHA-1 Message Digest from SUN, <initialized>\r\n");
        org.junit.Assert.assertNotNull(messageDigest3);
        org.junit.Assert.assertEquals(messageDigest3.toString(), "SHA-1 Message Digest from SUN, <initialized>\r\n");
        org.junit.Assert.assertNotNull(openOptionArray7);
        org.junit.Assert.assertArrayEquals(openOptionArray7, new java.nio.file.OpenOption[] { null });
    }

    @Test
    public void test245() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test245");
        java.io.InputStream inputStream0 = null;
        org.apache.commons.codec.binary.Base16InputStream base16InputStream1 = new org.apache.commons.codec.binary.Base16InputStream(inputStream0);
        char[] charArray4 = null;
        byte[] byteArray5 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray4);
        char[] charArray6 = org.apache.commons.codec.binary.Hex.encodeHex(byteArray5);
        byte[] byteArray7 = org.apache.commons.codec.binary.Hex.decodeHex(charArray6);
        org.apache.commons.codec.CodecPolicy codecPolicy8 = null;
        org.apache.commons.codec.binary.Base64InputStream base64InputStream9 = new org.apache.commons.codec.binary.Base64InputStream((java.io.InputStream) base16InputStream1, true, (int) (short) 100, byteArray7, codecPolicy8);
        int int10 = base64InputStream9.available();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str11 = org.apache.commons.codec.digest.DigestUtils.md2Hex((java.io.InputStream) base64InputStream9);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray5);
        org.junit.Assert.assertArrayEquals(byteArray5, new byte[] {});
        org.junit.Assert.assertNotNull(charArray6);
        org.junit.Assert.assertArrayEquals(charArray6, new char[] {});
        org.junit.Assert.assertNotNull(byteArray7);
        org.junit.Assert.assertArrayEquals(byteArray7, new byte[] {});
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 1 + "'", int10 == 1);
    }

    @Test
    public void test246() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test246");
        org.apache.commons.codec.binary.Base58.Builder builder0 = org.apache.commons.codec.binary.Base58.builder();
        org.apache.commons.codec.binary.Base58 base58_1 = new org.apache.commons.codec.binary.Base58(builder0);
        org.apache.commons.codec.binary.Base32 base32_2 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray5 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str6 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray5);
        boolean boolean8 = base32_2.isInAlphabet(byteArray5, false);
        char[] charArray9 = null;
        byte[] byteArray10 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray9);
        boolean boolean12 = base32_2.isInAlphabet(byteArray10, true);
        byte[] byteArray13 = org.apache.commons.codec.digest.DigestUtils.sha1(byteArray10);
        org.apache.commons.codec.binary.Base58.Builder builder14 = builder0.setDecodeTable(byteArray10);
        org.apache.commons.codec.binary.Base58 base58_15 = builder14.get();
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(byteArray5);
        org.junit.Assert.assertArrayEquals(byteArray5, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "\000" + "'", str6, "\000");
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertNotNull(byteArray10);
        org.junit.Assert.assertArrayEquals(byteArray10, new byte[] {});
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + true + "'", boolean12 == true);
        org.junit.Assert.assertNotNull(byteArray13);
        org.junit.Assert.assertArrayEquals(byteArray13, new byte[] { (byte) -38, (byte) 57, (byte) -93, (byte) -18, (byte) 94, (byte) 107, (byte) 75, (byte) 13, (byte) 50, (byte) 85, (byte) -65, (byte) -17, (byte) -107, (byte) 96, (byte) 24, (byte) -112, (byte) -81, (byte) -40, (byte) 7, (byte) 9 });
        org.junit.Assert.assertNotNull(builder14);
        org.junit.Assert.assertNotNull(base58_15);
    }

    @Test
    public void test247() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test247");
        long long1 = org.apache.commons.codec.digest.MurmurHash2.hash64("any");
        org.junit.Assert.assertTrue("'" + long1 + "' != '" + (-3378770129006245479L) + "'", long1 == (-3378770129006245479L));
    }

    @Test
    public void test248() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test248");
        java.io.OutputStream outputStream0 = null;
        org.apache.commons.codec.binary.Base64OutputStream base64OutputStream1 = new org.apache.commons.codec.binary.Base64OutputStream(outputStream0);
        org.apache.commons.codec.binary.Base32 base32_4 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray7 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str8 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray7);
        boolean boolean10 = base32_4.isInAlphabet(byteArray7, false);
        org.apache.commons.codec.binary.Base32 base32_11 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray14 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str15 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray14);
        boolean boolean17 = base32_11.isInAlphabet(byteArray14, false);
        byte[] byteArray18 = org.apache.commons.codec.digest.HmacUtils.hmacMd5(byteArray7, byteArray14);
        org.apache.commons.codec.binary.Base32OutputStream base32OutputStream19 = new org.apache.commons.codec.binary.Base32OutputStream((java.io.OutputStream) base64OutputStream1, false, 1, byteArray14);
        base64OutputStream1.eof();
        base64OutputStream1.eof();
        org.junit.Assert.assertNotNull(byteArray7);
        org.junit.Assert.assertArrayEquals(byteArray7, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "\000" + "'", str8, "\000");
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        org.junit.Assert.assertNotNull(byteArray14);
        org.junit.Assert.assertArrayEquals(byteArray14, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str15 + "' != '" + "\000" + "'", str15, "\000");
        org.junit.Assert.assertTrue("'" + boolean17 + "' != '" + false + "'", boolean17 == false);
        org.junit.Assert.assertNotNull(byteArray18);
        org.junit.Assert.assertArrayEquals(byteArray18, new byte[] { (byte) -53, (byte) -62, (byte) 109, (byte) 108, (byte) 16, (byte) 34, (byte) -87, (byte) -93, (byte) 59, (byte) 95, (byte) 90, (byte) 76, (byte) 18, (byte) -71, (byte) -99, (byte) -51 });
    }

    @Test
    public void test249() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test249");
        org.apache.commons.codec.net.BCodec bCodec0 = new org.apache.commons.codec.net.BCodec();
        boolean boolean1 = bCodec0.isStrictDecoding();
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
    }

    @Test
    public void test250() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test250");
        int int0 = org.apache.commons.codec.binary.BaseNCodec.MIME_CHUNK_SIZE;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 76 + "'", int0 == 76);
    }

    @Test
    public void test251() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test251");
        java.io.OutputStream outputStream0 = null;
        org.apache.commons.codec.binary.Base64OutputStream base64OutputStream1 = new org.apache.commons.codec.binary.Base64OutputStream(outputStream0);
        org.apache.commons.codec.binary.Base32 base32_4 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray7 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str8 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray7);
        boolean boolean10 = base32_4.isInAlphabet(byteArray7, false);
        org.apache.commons.codec.binary.Base32 base32_11 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray14 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str15 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray14);
        boolean boolean17 = base32_11.isInAlphabet(byteArray14, false);
        byte[] byteArray18 = org.apache.commons.codec.digest.HmacUtils.hmacMd5(byteArray7, byteArray14);
        org.apache.commons.codec.binary.Base32OutputStream base32OutputStream19 = new org.apache.commons.codec.binary.Base32OutputStream((java.io.OutputStream) base64OutputStream1, false, 1, byteArray14);
        org.apache.commons.codec.binary.Base64.Builder builder20 = new org.apache.commons.codec.binary.Base64.Builder();
        java.io.InputStream inputStream21 = null;
        org.apache.commons.codec.binary.Base16InputStream base16InputStream22 = new org.apache.commons.codec.binary.Base16InputStream(inputStream21);
        byte[] byteArray31 = new byte[] { (byte) 10, (byte) 0, (byte) -1, (byte) 0, (byte) 1, (byte) 100 };
        java.lang.String str32 = org.apache.commons.codec.digest.Md5Crypt.md5Crypt(byteArray31);
        org.apache.commons.codec.CodecPolicy codecPolicy33 = null;
        org.apache.commons.codec.binary.Base64InputStream base64InputStream34 = new org.apache.commons.codec.binary.Base64InputStream((java.io.InputStream) base16InputStream22, false, (int) 'a', byteArray31, codecPolicy33);
        org.apache.commons.codec.binary.Base64.Builder builder35 = builder20.setDecodeTable(byteArray31);
        byte[] byteArray36 = org.apache.commons.codec.binary.Base64.decodeBase64UrlSafe(byteArray31);
        java.lang.String str37 = org.apache.commons.codec.digest.Md5Crypt.md5Crypt(byteArray31);
        javax.crypto.Mac mac38 = org.apache.commons.codec.digest.HmacUtils.getHmacSha384(byteArray31);
        // The following exception was thrown during execution in test generation
        try {
            base32OutputStream19.write(byteArray31, 10, 821347078);
            org.junit.Assert.fail("Expected exception of type java.lang.IndexOutOfBoundsException; message: null");
        } catch (java.lang.IndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray7);
        org.junit.Assert.assertArrayEquals(byteArray7, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "\000" + "'", str8, "\000");
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        org.junit.Assert.assertNotNull(byteArray14);
        org.junit.Assert.assertArrayEquals(byteArray14, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str15 + "' != '" + "\000" + "'", str15, "\000");
        org.junit.Assert.assertTrue("'" + boolean17 + "' != '" + false + "'", boolean17 == false);
        org.junit.Assert.assertNotNull(byteArray18);
        org.junit.Assert.assertArrayEquals(byteArray18, new byte[] { (byte) -53, (byte) -62, (byte) 109, (byte) 108, (byte) 16, (byte) 34, (byte) -87, (byte) -93, (byte) 59, (byte) 95, (byte) 90, (byte) 76, (byte) 18, (byte) -71, (byte) -99, (byte) -51 });
        org.junit.Assert.assertNotNull(byteArray31);
        org.junit.Assert.assertArrayEquals(byteArray31, new byte[] { (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0 });
// flaky "32) test251(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str32 + "' != '" + "$1$DVFDDvBt$9t3Jx0jBtxnkTdgDvVzH01" + "'", str32, "$1$DVFDDvBt$9t3Jx0jBtxnkTdgDvVzH01");
        org.junit.Assert.assertNotNull(builder35);
        org.junit.Assert.assertNotNull(byteArray36);
        org.junit.Assert.assertArrayEquals(byteArray36, new byte[] {});
// flaky "4) test251(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str37 + "' != '" + "$1$YO2TFV8k$xHeap4WFG7C1JKKvs6EQ71" + "'", str37, "$1$YO2TFV8k$xHeap4WFG7C1JKKvs6EQ71");
        org.junit.Assert.assertNotNull(mac38);
    }

    @Test
    public void test252() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test252");
        org.apache.commons.codec.binary.Base32 base32_0 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray3 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str4 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray3);
        boolean boolean6 = base32_0.isInAlphabet(byteArray3, false);
        java.lang.String str7 = org.apache.commons.codec.digest.UnixCrypt.crypt(byteArray3);
        byte[] byteArray8 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(byteArray3);
        org.apache.commons.codec.binary.Base32 base32_9 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray12 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str13 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray12);
        boolean boolean15 = base32_9.isInAlphabet(byteArray12, false);
        byte[] byteArray18 = org.apache.commons.codec.digest.HmacUtils.hmacSha512("310354661a5962d5b8cb76032d5a97e8aed7cf9f", "");
        byte[] byteArray19 = base32_9.encode(byteArray18);
        // The following exception was thrown during execution in test generation
        try {
            byte[] byteArray20 = org.apache.commons.codec.digest.HmacUtils.hmacMd5(byteArray8, byteArray18);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Empty key");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertArrayEquals(byteArray3, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "\000" + "'", str4, "\000");
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
// flaky "33) test252(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str7 + "' != '" + "szYERNwXW3VOo" + "'", str7, "szYERNwXW3VOo");
        org.junit.Assert.assertNotNull(byteArray8);
        org.junit.Assert.assertArrayEquals(byteArray8, new byte[] {});
        org.junit.Assert.assertNotNull(byteArray12);
        org.junit.Assert.assertArrayEquals(byteArray12, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "\000" + "'", str13, "\000");
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertNotNull(byteArray18);
        org.junit.Assert.assertNotNull(byteArray19);
    }

    @Test
    public void test253() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test253");
        org.apache.commons.codec.binary.Base32OutputStream.Builder builder0 = org.apache.commons.codec.binary.Base32OutputStream.builder();
        java.io.OutputStream outputStream1 = null;
        org.apache.commons.codec.binary.Base64OutputStream base64OutputStream2 = new org.apache.commons.codec.binary.Base64OutputStream(outputStream1);
        org.apache.commons.codec.binary.Base32 base32_5 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray8 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str9 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray8);
        boolean boolean11 = base32_5.isInAlphabet(byteArray8, false);
        org.apache.commons.codec.binary.Base32 base32_12 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray15 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str16 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray15);
        boolean boolean18 = base32_12.isInAlphabet(byteArray15, false);
        byte[] byteArray19 = org.apache.commons.codec.digest.HmacUtils.hmacMd5(byteArray8, byteArray15);
        org.apache.commons.codec.binary.Base32OutputStream base32OutputStream20 = new org.apache.commons.codec.binary.Base32OutputStream((java.io.OutputStream) base64OutputStream2, false, 1, byteArray15);
        org.apache.commons.codec.binary.Base32OutputStream.Builder builder21 = builder0.setOutputStream((java.io.OutputStream) base32OutputStream20);
        org.apache.commons.codec.binary.Base32OutputStream.Builder builder23 = builder0.setEncode(true);
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(byteArray8);
        org.junit.Assert.assertArrayEquals(byteArray8, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str9 + "' != '" + "\000" + "'", str9, "\000");
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
        org.junit.Assert.assertNotNull(byteArray15);
        org.junit.Assert.assertArrayEquals(byteArray15, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str16 + "' != '" + "\000" + "'", str16, "\000");
        org.junit.Assert.assertTrue("'" + boolean18 + "' != '" + false + "'", boolean18 == false);
        org.junit.Assert.assertNotNull(byteArray19);
        org.junit.Assert.assertArrayEquals(byteArray19, new byte[] { (byte) -53, (byte) -62, (byte) 109, (byte) 108, (byte) 16, (byte) 34, (byte) -87, (byte) -93, (byte) 59, (byte) 95, (byte) 90, (byte) 76, (byte) 18, (byte) -71, (byte) -99, (byte) -51 });
        org.junit.Assert.assertNotNull(builder21);
        org.junit.Assert.assertNotNull(builder23);
    }

    @Test
    public void test254() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test254");
        java.security.MessageDigest messageDigest0 = org.apache.commons.codec.digest.DigestUtils.getShaDigest();
        org.apache.commons.codec.digest.DigestUtils digestUtils1 = new org.apache.commons.codec.digest.DigestUtils(messageDigest0);
        char[] charArray3 = null;
        byte[] byteArray4 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray3);
        org.apache.commons.codec.binary.Base64 base64_5 = new org.apache.commons.codec.binary.Base64((int) '4', byteArray4);
        byte[] byteArray6 = digestUtils1.digest(byteArray4);
        char[] charArray8 = org.apache.commons.codec.binary.Hex.encodeHex(byteArray6, false);
        org.junit.Assert.assertNotNull(messageDigest0);
        org.junit.Assert.assertEquals(messageDigest0.toString(), "SHA-1 Message Digest from SUN, <initialized>\r\n");
        org.junit.Assert.assertNotNull(byteArray4);
        org.junit.Assert.assertArrayEquals(byteArray4, new byte[] {});
        org.junit.Assert.assertNotNull(byteArray6);
        org.junit.Assert.assertArrayEquals(byteArray6, new byte[] { (byte) -38, (byte) 57, (byte) -93, (byte) -18, (byte) 94, (byte) 107, (byte) 75, (byte) 13, (byte) 50, (byte) 85, (byte) -65, (byte) -17, (byte) -107, (byte) 96, (byte) 24, (byte) -112, (byte) -81, (byte) -40, (byte) 7, (byte) 9 });
        org.junit.Assert.assertNotNull(charArray8);
    }

    @Test
    public void test255() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test255");
        java.lang.String str0 = org.apache.commons.codec.digest.MessageDigestAlgorithms.SHA3_384;
        org.junit.Assert.assertEquals("'" + str0 + "' != '" + "SHA3-384" + "'", str0, "SHA3-384");
    }

    @Test
    public void test256() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test256");
        char[] charArray1 = null;
        byte[] byteArray2 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray1);
        org.apache.commons.codec.binary.Base64 base64_3 = new org.apache.commons.codec.binary.Base64((int) '4', byteArray2);
        org.apache.commons.codec.binary.Base32 base32_4 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray7 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str8 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray7);
        boolean boolean10 = base32_4.isInAlphabet(byteArray7, false);
        long[] longArray11 = org.apache.commons.codec.digest.MurmurHash3.hash128(byteArray7);
        byte[] byteArray12 = base64_3.decode(byteArray7);
        byte[] byteArray14 = base64_3.decode("AAA");
        java.lang.String str15 = org.apache.commons.codec.digest.DigestUtils.md2Hex(byteArray14);
        org.junit.Assert.assertNotNull(byteArray2);
        org.junit.Assert.assertArrayEquals(byteArray2, new byte[] {});
        org.junit.Assert.assertNotNull(byteArray7);
        org.junit.Assert.assertArrayEquals(byteArray7, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "\000" + "'", str8, "\000");
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        org.junit.Assert.assertNotNull(longArray11);
        org.junit.Assert.assertArrayEquals(longArray11, new long[] { 8478934883742226405L, (-1017287513161072006L) });
        org.junit.Assert.assertNotNull(byteArray12);
        org.junit.Assert.assertArrayEquals(byteArray12, new byte[] {});
        org.junit.Assert.assertNotNull(byteArray14);
        org.junit.Assert.assertArrayEquals(byteArray14, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str15 + "' != '" + "070e302f491955b59d83829950299cef" + "'", str15, "070e302f491955b59d83829950299cef");
    }

    @Test
    public void test257() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test257");
        java.lang.String str0 = org.apache.commons.codec.digest.MessageDigestAlgorithms.SHAKE128_256;
        org.junit.Assert.assertEquals("'" + str0 + "' != '" + "SHAKE128-256" + "'", str0, "SHAKE128-256");
    }

    @Test
    public void test258() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test258");
        java.io.InputStream inputStream0 = null;
        org.apache.commons.codec.binary.Base16InputStream base16InputStream1 = new org.apache.commons.codec.binary.Base16InputStream(inputStream0);
        char[] charArray4 = null;
        byte[] byteArray5 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray4);
        char[] charArray6 = org.apache.commons.codec.binary.Hex.encodeHex(byteArray5);
        byte[] byteArray7 = org.apache.commons.codec.binary.Hex.decodeHex(charArray6);
        org.apache.commons.codec.CodecPolicy codecPolicy8 = null;
        org.apache.commons.codec.binary.Base64InputStream base64InputStream9 = new org.apache.commons.codec.binary.Base64InputStream((java.io.InputStream) base16InputStream1, true, (int) (short) 100, byteArray7, codecPolicy8);
        byte[] byteArray13 = org.apache.commons.codec.binary.Base64.encodeBase64(byteArray7, false, false, (int) '4');
        org.junit.Assert.assertNotNull(byteArray5);
        org.junit.Assert.assertArrayEquals(byteArray5, new byte[] {});
        org.junit.Assert.assertNotNull(charArray6);
        org.junit.Assert.assertArrayEquals(charArray6, new char[] {});
        org.junit.Assert.assertNotNull(byteArray7);
        org.junit.Assert.assertArrayEquals(byteArray7, new byte[] {});
        org.junit.Assert.assertNotNull(byteArray13);
        org.junit.Assert.assertArrayEquals(byteArray13, new byte[] {});
    }

    @Test
    public void test259() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test259");
        java.lang.String str0 = org.apache.commons.codec.digest.MessageDigestAlgorithms.SHA_256;
        org.junit.Assert.assertEquals("'" + str0 + "' != '" + "SHA-256" + "'", str0, "SHA-256");
    }

    @Test
    public void test260() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test260");
        char[] charArray0 = null;
        byte[] byteArray1 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray0);
        org.apache.commons.codec.binary.Base32 base32_2 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray5 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str6 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray5);
        boolean boolean8 = base32_2.isInAlphabet(byteArray5, false);
        char[] charArray9 = null;
        byte[] byteArray10 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray9);
        boolean boolean12 = base32_2.isInAlphabet(byteArray10, true);
        byte[] byteArray15 = org.apache.commons.codec.digest.HmacUtils.hmacSha512("310354661a5962d5b8cb76032d5a97e8aed7cf9f", "");
        boolean boolean17 = base32_2.isInAlphabet(byteArray15, true);
        byte[] byteArray18 = org.apache.commons.codec.digest.DigestUtils.md5(byteArray15);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str19 = org.apache.commons.codec.digest.HmacUtils.hmacSha384Hex(byteArray1, byteArray18);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Empty key");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray1);
        org.junit.Assert.assertArrayEquals(byteArray1, new byte[] {});
        org.junit.Assert.assertNotNull(byteArray5);
        org.junit.Assert.assertArrayEquals(byteArray5, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "\000" + "'", str6, "\000");
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertNotNull(byteArray10);
        org.junit.Assert.assertArrayEquals(byteArray10, new byte[] {});
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + true + "'", boolean12 == true);
        org.junit.Assert.assertNotNull(byteArray15);
        org.junit.Assert.assertTrue("'" + boolean17 + "' != '" + false + "'", boolean17 == false);
        org.junit.Assert.assertNotNull(byteArray18);
        org.junit.Assert.assertArrayEquals(byteArray18, new byte[] { (byte) -104, (byte) -88, (byte) -7, (byte) -67, (byte) 109, (byte) 48, (byte) -26, (byte) 75, (byte) 93, (byte) -93, (byte) -75, (byte) -48, (byte) 7, (byte) 118, (byte) 106, (byte) 87 });
    }

    @Test
    public void test261() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test261");
        org.apache.commons.codec.binary.Base32InputStream.Builder builder0 = new org.apache.commons.codec.binary.Base32InputStream.Builder();
        java.io.InputStream inputStream1 = null;
        org.apache.commons.codec.binary.Base16InputStream base16InputStream2 = new org.apache.commons.codec.binary.Base16InputStream(inputStream1);
        byte[] byteArray11 = new byte[] { (byte) 10, (byte) 0, (byte) -1, (byte) 0, (byte) 1, (byte) 100 };
        java.lang.String str12 = org.apache.commons.codec.digest.Md5Crypt.md5Crypt(byteArray11);
        org.apache.commons.codec.CodecPolicy codecPolicy13 = null;
        org.apache.commons.codec.binary.Base64InputStream base64InputStream14 = new org.apache.commons.codec.binary.Base64InputStream((java.io.InputStream) base16InputStream2, false, (int) 'a', byteArray11, codecPolicy13);
        org.apache.commons.codec.binary.Base32InputStream.Builder builder15 = builder0.setInputStream((java.io.InputStream) base64InputStream14);
        org.apache.commons.codec.binary.Base32 base32_16 = new org.apache.commons.codec.binary.Base32();
        org.apache.commons.codec.binary.Base32InputStream.Builder builder17 = builder0.setBaseNCodec(base32_16);
        org.apache.commons.codec.binary.Base32InputStream base32InputStream18 = builder0.get();
        org.junit.Assert.assertNotNull(byteArray11);
        org.junit.Assert.assertArrayEquals(byteArray11, new byte[] { (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0 });
// flaky "34) test261(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str12 + "' != '" + "$1$EdFYrmbn$nDeiTd8h3tTEnn4L.l1EK0" + "'", str12, "$1$EdFYrmbn$nDeiTd8h3tTEnn4L.l1EK0");
        org.junit.Assert.assertNotNull(builder15);
        org.junit.Assert.assertNotNull(builder17);
        org.junit.Assert.assertNotNull(base32InputStream18);
    }

    @Test
    public void test262() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test262");
        java.io.OutputStream outputStream0 = null;
        org.apache.commons.codec.binary.Base64OutputStream base64OutputStream1 = new org.apache.commons.codec.binary.Base64OutputStream(outputStream0);
        org.apache.commons.codec.binary.Base16OutputStream base16OutputStream3 = new org.apache.commons.codec.binary.Base16OutputStream(outputStream0, false);
        boolean boolean4 = base16OutputStream3.isStrictDecoding();
        base16OutputStream3.eof();
        org.apache.commons.codec.binary.Base32 base32_6 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray9 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str10 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray9);
        boolean boolean12 = base32_6.isInAlphabet(byteArray9, false);
        char[] charArray13 = null;
        byte[] byteArray14 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray13);
        boolean boolean16 = base32_6.isInAlphabet(byteArray14, true);
        byte[] byteArray19 = org.apache.commons.codec.digest.HmacUtils.hmacSha512("310354661a5962d5b8cb76032d5a97e8aed7cf9f", "");
        boolean boolean21 = base32_6.isInAlphabet(byteArray19, true);
        byte[] byteArray22 = org.apache.commons.codec.digest.DigestUtils.md5(byteArray19);
        // The following exception was thrown during execution in test generation
        try {
            base16OutputStream3.write(byteArray22, (-1), (int) (byte) 0);
            org.junit.Assert.fail("Expected exception of type java.lang.IndexOutOfBoundsException; message: null");
        } catch (java.lang.IndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertNotNull(byteArray9);
        org.junit.Assert.assertArrayEquals(byteArray9, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str10 + "' != '" + "\000" + "'", str10, "\000");
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
        org.junit.Assert.assertNotNull(byteArray14);
        org.junit.Assert.assertArrayEquals(byteArray14, new byte[] {});
        org.junit.Assert.assertTrue("'" + boolean16 + "' != '" + true + "'", boolean16 == true);
        org.junit.Assert.assertNotNull(byteArray19);
        org.junit.Assert.assertTrue("'" + boolean21 + "' != '" + false + "'", boolean21 == false);
        org.junit.Assert.assertNotNull(byteArray22);
        org.junit.Assert.assertArrayEquals(byteArray22, new byte[] { (byte) -104, (byte) -88, (byte) -7, (byte) -67, (byte) 109, (byte) 48, (byte) -26, (byte) 75, (byte) 93, (byte) -93, (byte) -75, (byte) -48, (byte) 7, (byte) 118, (byte) 106, (byte) 87 });
    }

    @Test
    public void test263() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test263");
        org.apache.commons.codec.digest.HmacAlgorithms hmacAlgorithms0 = null;
        java.security.MessageDigest messageDigest1 = org.apache.commons.codec.digest.DigestUtils.getShaDigest();
        org.apache.commons.codec.digest.DigestUtils digestUtils2 = new org.apache.commons.codec.digest.DigestUtils(messageDigest1);
        char[] charArray4 = null;
        byte[] byteArray5 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray4);
        org.apache.commons.codec.binary.Base64 base64_6 = new org.apache.commons.codec.binary.Base64((int) '4', byteArray5);
        byte[] byteArray7 = digestUtils2.digest(byteArray5);
        // The following exception was thrown during execution in test generation
        try {
            org.apache.commons.codec.digest.HmacUtils hmacUtils8 = new org.apache.commons.codec.digest.HmacUtils(hmacAlgorithms0, byteArray5);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(messageDigest1);
        org.junit.Assert.assertEquals(messageDigest1.toString(), "SHA-1 Message Digest from SUN, <initialized>\r\n");
        org.junit.Assert.assertNotNull(byteArray5);
        org.junit.Assert.assertArrayEquals(byteArray5, new byte[] {});
        org.junit.Assert.assertNotNull(byteArray7);
        org.junit.Assert.assertArrayEquals(byteArray7, new byte[] { (byte) -38, (byte) 57, (byte) -93, (byte) -18, (byte) 94, (byte) 107, (byte) 75, (byte) 13, (byte) 50, (byte) 85, (byte) -65, (byte) -17, (byte) -107, (byte) 96, (byte) 24, (byte) -112, (byte) -81, (byte) -40, (byte) 7, (byte) 9 });
    }

    @Test
    public void test264() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test264");
        org.apache.commons.codec.binary.Base16.Builder builder0 = org.apache.commons.codec.binary.Base16.builder();
        org.apache.commons.codec.binary.Base16.Builder builder2 = builder0.setLowerCase(false);
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(builder2);
    }

    @Test
    public void test265() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test265");
        byte[] byteArray2 = org.apache.commons.codec.digest.HmacUtils.hmacSha1("SHAKE256-512", "$6$oWTfCqM4$pqjKIRdRH35mCsiHnWoGmZXh7pxYgNcyOULjqnI2WivTynTt2UmWPdeQeDpK11BYRoTIeq.WEFe3E1xL/r10L1");
        java.lang.String str3 = org.apache.commons.codec.binary.Base64.encodeBase64String(byteArray2);
        org.junit.Assert.assertNotNull(byteArray2);
        org.junit.Assert.assertArrayEquals(byteArray2, new byte[] { (byte) 12, (byte) 13, (byte) -75, (byte) 1, (byte) -128, (byte) -42, (byte) 89, (byte) 55, (byte) 43, (byte) 118, (byte) 5, (byte) -19, (byte) 119, (byte) 96, (byte) 47, (byte) 14, (byte) 73, (byte) -63, (byte) 75, (byte) 87 });
        org.junit.Assert.assertEquals("'" + str3 + "' != '" + "DA21AYDWWTcrdgXtd2AvDknBS1c=" + "'", str3, "DA21AYDWWTcrdgXtd2AvDknBS1c=");
    }

    @Test
    public void test266() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test266");
        org.apache.commons.codec.binary.Base58.Builder builder0 = org.apache.commons.codec.binary.Base58.builder();
        org.apache.commons.codec.binary.Base58 base58_1 = new org.apache.commons.codec.binary.Base58(builder0);
        org.apache.commons.codec.binary.Base58 base58_2 = builder0.get();
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(base58_2);
    }

    @Test
    public void test267() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test267");
        org.apache.commons.codec.binary.Base64InputStream.Builder builder0 = new org.apache.commons.codec.binary.Base64InputStream.Builder();
    }

    @Test
    public void test268() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test268");
        org.apache.commons.codec.binary.Base32InputStream.Builder builder0 = new org.apache.commons.codec.binary.Base32InputStream.Builder();
        java.io.InputStream inputStream1 = null;
        org.apache.commons.codec.binary.Base16InputStream base16InputStream2 = new org.apache.commons.codec.binary.Base16InputStream(inputStream1);
        byte[] byteArray11 = new byte[] { (byte) 10, (byte) 0, (byte) -1, (byte) 0, (byte) 1, (byte) 100 };
        java.lang.String str12 = org.apache.commons.codec.digest.Md5Crypt.md5Crypt(byteArray11);
        org.apache.commons.codec.CodecPolicy codecPolicy13 = null;
        org.apache.commons.codec.binary.Base64InputStream base64InputStream14 = new org.apache.commons.codec.binary.Base64InputStream((java.io.InputStream) base16InputStream2, false, (int) 'a', byteArray11, codecPolicy13);
        org.apache.commons.codec.binary.Base32InputStream.Builder builder15 = builder0.setInputStream((java.io.InputStream) base64InputStream14);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str16 = org.apache.commons.codec.digest.DigestUtils.sha256Hex((java.io.InputStream) base64InputStream14);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray11);
        org.junit.Assert.assertArrayEquals(byteArray11, new byte[] { (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0 });
// flaky "35) test268(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str12 + "' != '" + "$1$9Ib/GNb2$Uet5x.7LHsOaJjAEF/TnG/" + "'", str12, "$1$9Ib/GNb2$Uet5x.7LHsOaJjAEF/TnG/");
        org.junit.Assert.assertNotNull(builder15);
    }

    @Test
    public void test269() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test269");
        org.apache.commons.codec.net.PercentCodec percentCodec0 = new org.apache.commons.codec.net.PercentCodec();
        char[] charArray2 = null;
        byte[] byteArray3 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray2);
        org.apache.commons.codec.binary.Base64 base64_4 = new org.apache.commons.codec.binary.Base64((int) '4', byteArray3);
        org.apache.commons.codec.binary.Base32 base32_5 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray8 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str9 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray8);
        boolean boolean11 = base32_5.isInAlphabet(byteArray8, false);
        long[] longArray12 = org.apache.commons.codec.digest.MurmurHash3.hash128(byteArray8);
        byte[] byteArray13 = base64_4.decode(byteArray8);
        byte[] byteArray14 = percentCodec0.encode(byteArray8);
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertArrayEquals(byteArray3, new byte[] {});
        org.junit.Assert.assertNotNull(byteArray8);
        org.junit.Assert.assertArrayEquals(byteArray8, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str9 + "' != '" + "\000" + "'", str9, "\000");
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
        org.junit.Assert.assertNotNull(longArray12);
        org.junit.Assert.assertArrayEquals(longArray12, new long[] { 8478934883742226405L, (-1017287513161072006L) });
        org.junit.Assert.assertNotNull(byteArray13);
        org.junit.Assert.assertArrayEquals(byteArray13, new byte[] {});
        org.junit.Assert.assertNotNull(byteArray14);
        org.junit.Assert.assertArrayEquals(byteArray14, new byte[] { (byte) 0, (byte) 0 });
    }

    @Test
    public void test270() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test270");
        org.apache.commons.codec.digest.Crc16 crc16_0 = org.apache.commons.codec.digest.Crc16.mcrf4xx();
        org.junit.Assert.assertNotNull(crc16_0);
    }

    @Test
    public void test271() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test271");
        java.io.InputStream inputStream0 = null;
        org.apache.commons.codec.binary.Base16InputStream base16InputStream1 = new org.apache.commons.codec.binary.Base16InputStream(inputStream0);
        base16InputStream1.mark(0);
        // The following exception was thrown during execution in test generation
        try {
            int int4 = base16InputStream1.read();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test272() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test272");
        org.apache.commons.codec.net.QCodec qCodec0 = new org.apache.commons.codec.net.QCodec();
        boolean boolean1 = qCodec0.isEncodeBlanks();
        java.lang.String str2 = qCodec0.getDefaultCharset();
        java.nio.charset.Charset charset3 = qCodec0.getCharset();
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
        org.junit.Assert.assertEquals("'" + str2 + "' != '" + "UTF-8" + "'", str2, "UTF-8");
        org.junit.Assert.assertNotNull(charset3);
    }

    @Test
    public void test273() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test273");
        boolean boolean1 = org.apache.commons.codec.binary.Base64.isBase64((byte) 0);
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
    }

    @Test
    public void test274() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test274");
        char[] charArray0 = null;
        byte[] byteArray1 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray0);
        char[] charArray2 = org.apache.commons.codec.binary.Hex.encodeHex(byteArray1);
        char[] charArray6 = null;
        byte[] byteArray7 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray6);
        char[] charArray8 = org.apache.commons.codec.binary.Hex.encodeHex(byteArray7);
        org.apache.commons.codec.language.RefinedSoundex refinedSoundex9 = new org.apache.commons.codec.language.RefinedSoundex(charArray8);
        org.apache.commons.codec.binary.Hex.encodeHex(byteArray1, 1, (-913662660), true, charArray8, (int) (short) 1);
        org.junit.Assert.assertNotNull(byteArray1);
        org.junit.Assert.assertArrayEquals(byteArray1, new byte[] {});
        org.junit.Assert.assertNotNull(charArray2);
        org.junit.Assert.assertArrayEquals(charArray2, new char[] {});
        org.junit.Assert.assertNotNull(byteArray7);
        org.junit.Assert.assertArrayEquals(byteArray7, new byte[] {});
        org.junit.Assert.assertNotNull(charArray8);
        org.junit.Assert.assertArrayEquals(charArray8, new char[] {});
    }

    @Test
    public void test275() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test275");
        // The following exception was thrown during execution in test generation
        try {
            org.apache.commons.codec.digest.DigestUtils digestUtils1 = new org.apache.commons.codec.digest.DigestUtils("ASKTKTKPMK");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: java.security.NoSuchAlgorithmException: ASKTKTKPMK MessageDigest not available");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test276() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test276");
        java.security.MessageDigest messageDigest0 = org.apache.commons.codec.digest.DigestUtils.getShaDigest();
        byte[] byteArray2 = org.apache.commons.codec.binary.StringUtils.getBytesIso8859_1("c39394a96ebd177a239a817fe8453ab0");
        byte[] byteArray3 = org.apache.commons.codec.digest.GitIdentifiers.blobId(messageDigest0, byteArray2);
        java.security.MessageDigest messageDigest5 = org.apache.commons.codec.digest.DigestUtils.updateDigest(messageDigest0, ".mrwjp1phS/9A");
        byte[] byteArray6 = null;
        // The following exception was thrown during execution in test generation
        try {
            byte[] byteArray7 = org.apache.commons.codec.digest.DigestUtils.digest(messageDigest5, byteArray6);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(messageDigest0);
        org.junit.Assert.assertEquals(messageDigest0.toString(), "SHA-1 Message Digest from SUN, <in progress>\r\n");
        org.junit.Assert.assertNotNull(byteArray2);
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertArrayEquals(byteArray3, new byte[] { (byte) -7, (byte) -108, (byte) -7, (byte) 62, (byte) 70, (byte) -82, (byte) -11, (byte) 83, (byte) 103, (byte) 91, (byte) 61, (byte) -112, (byte) 48, (byte) -94, (byte) -84, (byte) -12, (byte) 43, (byte) 7, (byte) 93, (byte) 69 });
        org.junit.Assert.assertNotNull(messageDigest5);
        org.junit.Assert.assertEquals(messageDigest5.toString(), "SHA-1 Message Digest from SUN, <in progress>\r\n");
    }

    @Test
    public void test277() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test277");
        org.apache.commons.codec.binary.Base32 base32_0 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray3 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str4 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray3);
        boolean boolean6 = base32_0.isInAlphabet(byteArray3, false);
        byte[] byteArray8 = org.apache.commons.codec.binary.StringUtils.getBytesIso8859_1("c39394a96ebd177a239a817fe8453ab0");
        org.apache.commons.codec.binary.Base32 base32_9 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray12 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str13 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray12);
        boolean boolean15 = base32_9.isInAlphabet(byteArray12, false);
        org.apache.commons.codec.binary.Base32 base32_16 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray19 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str20 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray19);
        boolean boolean22 = base32_16.isInAlphabet(byteArray19, false);
        java.lang.String str23 = org.apache.commons.codec.digest.UnixCrypt.crypt(byteArray19);
        java.lang.String str24 = org.apache.commons.codec.digest.HmacUtils.hmacSha1Hex(byteArray12, byteArray19);
        java.lang.String str25 = org.apache.commons.codec.binary.BinaryCodec.toAsciiString(byteArray19);
        java.lang.String str26 = org.apache.commons.codec.digest.HmacUtils.hmacSha1Hex(byteArray8, byteArray19);
        byte[] byteArray29 = base32_0.encode(byteArray19, (int) '#', 0);
        java.lang.String str30 = org.apache.commons.codec.binary.Base64.encodeBase64URLSafeString(byteArray19);
        // The following exception was thrown during execution in test generation
        try {
            byte[] byteArray31 = org.apache.commons.codec.digest.DigestUtils.shake128_256(byteArray19);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: java.security.NoSuchAlgorithmException: SHAKE128-256 MessageDigest not available");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertArrayEquals(byteArray3, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "\000" + "'", str4, "\000");
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNotNull(byteArray8);
        org.junit.Assert.assertNotNull(byteArray12);
        org.junit.Assert.assertArrayEquals(byteArray12, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "\000" + "'", str13, "\000");
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertNotNull(byteArray19);
        org.junit.Assert.assertArrayEquals(byteArray19, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str20 + "' != '" + "\000" + "'", str20, "\000");
        org.junit.Assert.assertTrue("'" + boolean22 + "' != '" + false + "'", boolean22 == false);
// flaky "36) test277(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str23 + "' != '" + "tizZm89nduftw" + "'", str23, "tizZm89nduftw");
        org.junit.Assert.assertEquals("'" + str24 + "' != '" + "310354661a5962d5b8cb76032d5a97e8aed7cf9f" + "'", str24, "310354661a5962d5b8cb76032d5a97e8aed7cf9f");
        org.junit.Assert.assertEquals("'" + str25 + "' != '" + "0000000000000000" + "'", str25, "0000000000000000");
        org.junit.Assert.assertEquals("'" + str26 + "' != '" + "55351564fecb450a30b6ab9f075f0e63a5f492b6" + "'", str26, "55351564fecb450a30b6ab9f075f0e63a5f492b6");
        org.junit.Assert.assertNotNull(byteArray29);
        org.junit.Assert.assertArrayEquals(byteArray29, new byte[] {});
        org.junit.Assert.assertEquals("'" + str30 + "' != '" + "AAA" + "'", str30, "AAA");
    }

    @Test
    public void test278() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test278");
        org.apache.commons.codec.binary.Base16 base16_1 = new org.apache.commons.codec.binary.Base16(false);
    }

    @Test
    public void test279() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test279");
        java.io.OutputStream outputStream0 = null;
        org.apache.commons.codec.binary.Base64OutputStream base64OutputStream1 = new org.apache.commons.codec.binary.Base64OutputStream(outputStream0);
        org.apache.commons.codec.binary.Base32 base32_4 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray7 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str8 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray7);
        boolean boolean10 = base32_4.isInAlphabet(byteArray7, false);
        org.apache.commons.codec.binary.Base32 base32_11 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray14 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str15 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray14);
        boolean boolean17 = base32_11.isInAlphabet(byteArray14, false);
        byte[] byteArray18 = org.apache.commons.codec.digest.HmacUtils.hmacMd5(byteArray7, byteArray14);
        org.apache.commons.codec.binary.Base32OutputStream base32OutputStream19 = new org.apache.commons.codec.binary.Base32OutputStream((java.io.OutputStream) base64OutputStream1, false, 1, byteArray14);
        byte[] byteArray20 = org.apache.commons.codec.binary.Base64.encodeBase64Chunked(byteArray14);
        java.util.Random random22 = null;
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str23 = org.apache.commons.codec.digest.Sha2Crypt.sha512Crypt(byteArray20, "SHA-384", random22);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Invalid salt value: SHA-384");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray7);
        org.junit.Assert.assertArrayEquals(byteArray7, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "\000" + "'", str8, "\000");
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        org.junit.Assert.assertNotNull(byteArray14);
        org.junit.Assert.assertArrayEquals(byteArray14, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str15 + "' != '" + "\000" + "'", str15, "\000");
        org.junit.Assert.assertTrue("'" + boolean17 + "' != '" + false + "'", boolean17 == false);
        org.junit.Assert.assertNotNull(byteArray18);
        org.junit.Assert.assertArrayEquals(byteArray18, new byte[] { (byte) -53, (byte) -62, (byte) 109, (byte) 108, (byte) 16, (byte) 34, (byte) -87, (byte) -93, (byte) 59, (byte) 95, (byte) 90, (byte) 76, (byte) 18, (byte) -71, (byte) -99, (byte) -51 });
        org.junit.Assert.assertNotNull(byteArray20);
        org.junit.Assert.assertArrayEquals(byteArray20, new byte[] { (byte) 65, (byte) 65, (byte) 65, (byte) 61, (byte) 13, (byte) 10 });
    }

    @Test
    public void test280() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test280");
        org.apache.commons.codec.binary.Base32 base32_0 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray3 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str4 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray3);
        boolean boolean6 = base32_0.isInAlphabet(byteArray3, false);
        char[] charArray7 = null;
        byte[] byteArray8 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray7);
        boolean boolean10 = base32_0.isInAlphabet(byteArray8, true);
        byte[] byteArray11 = org.apache.commons.codec.digest.DigestUtils.sha1(byteArray8);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str13 = org.apache.commons.codec.binary.StringUtils.newString(byteArray11, "38b060a751ac96384cd9327eb1b1e36a21fdb71114be07434c0cc7bf63f6e1da274edebfe76f65fbd51ad2f14898b95b");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: 38b060a751ac96384cd9327eb1b1e36a21fdb71114be07434c0cc7bf63f6e1da274edebfe76f65fbd51ad2f14898b95b: java.io.UnsupportedEncodingException: 38b060a751ac96384cd9327eb1b1e36a21fdb71114be07434c0cc7bf63f6e1da274edebfe76f65fbd51ad2f14898b95b");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertArrayEquals(byteArray3, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "\000" + "'", str4, "\000");
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNotNull(byteArray8);
        org.junit.Assert.assertArrayEquals(byteArray8, new byte[] {});
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
        org.junit.Assert.assertNotNull(byteArray11);
        org.junit.Assert.assertArrayEquals(byteArray11, new byte[] { (byte) -38, (byte) 57, (byte) -93, (byte) -18, (byte) 94, (byte) 107, (byte) 75, (byte) 13, (byte) 50, (byte) 85, (byte) -65, (byte) -17, (byte) -107, (byte) 96, (byte) 24, (byte) -112, (byte) -81, (byte) -40, (byte) 7, (byte) 9 });
    }

    @Test
    public void test281() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test281");
        org.apache.commons.codec.language.bm.NameType nameType0 = null;
        org.apache.commons.codec.language.bm.Languages languages1 = org.apache.commons.codec.language.bm.Languages.getInstance(nameType0);
        org.junit.Assert.assertNull(languages1);
    }

    @Test
    public void test282() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test282");
        org.apache.commons.codec.digest.HmacAlgorithms hmacAlgorithms0 = null;
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean1 = org.apache.commons.codec.digest.HmacUtils.isAvailable(hmacAlgorithms0);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test283() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test283");
        // The following exception was thrown during execution in test generation
        try {
            byte[] byteArray1 = org.apache.commons.codec.digest.DigestUtils.sha512_256("$1$PGGOlCMN$93x1SWmjd7FfTFj/z.PVu/");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: java.security.NoSuchAlgorithmException: SHA-512/256 MessageDigest not available");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test284() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test284");
        java.nio.charset.Charset charset0 = org.apache.commons.codec.Charsets.UTF_8;
        org.apache.commons.codec.CodecPolicy codecPolicy1 = null;
        org.apache.commons.codec.net.BCodec bCodec2 = new org.apache.commons.codec.net.BCodec(charset0, codecPolicy1);
        java.lang.String str3 = bCodec2.getDefaultCharset();
        org.junit.Assert.assertNotNull(charset0);
        org.junit.Assert.assertEquals("'" + str3 + "' != '" + "UTF-8" + "'", str3, "UTF-8");
    }

    @Test
    public void test285() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test285");
        java.nio.charset.Charset charset0 = org.apache.commons.codec.Charsets.ISO_8859_1;
        org.junit.Assert.assertNotNull(charset0);
    }

    @Test
    public void test286() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test286");
        org.apache.commons.codec.binary.Base32 base32_0 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray3 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str4 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray3);
        boolean boolean6 = base32_0.isInAlphabet(byteArray3, false);
        org.apache.commons.codec.binary.Base32 base32_7 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray10 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str11 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray10);
        boolean boolean13 = base32_7.isInAlphabet(byteArray10, false);
        byte[] byteArray14 = org.apache.commons.codec.digest.HmacUtils.hmacMd5(byteArray3, byteArray10);
        java.io.InputStream inputStream15 = null;
        org.apache.commons.codec.binary.Base16InputStream base16InputStream16 = new org.apache.commons.codec.binary.Base16InputStream(inputStream15);
        char[] charArray19 = null;
        byte[] byteArray20 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray19);
        char[] charArray21 = org.apache.commons.codec.binary.Hex.encodeHex(byteArray20);
        byte[] byteArray22 = org.apache.commons.codec.binary.Hex.decodeHex(charArray21);
        org.apache.commons.codec.CodecPolicy codecPolicy23 = null;
        org.apache.commons.codec.binary.Base64InputStream base64InputStream24 = new org.apache.commons.codec.binary.Base64InputStream((java.io.InputStream) base16InputStream16, true, (int) (short) 100, byteArray22, codecPolicy23);
        int int25 = base64InputStream24.available();
        org.apache.commons.codec.binary.Base32 base32_28 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray31 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str32 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray31);
        boolean boolean34 = base32_28.isInAlphabet(byteArray31, false);
        org.apache.commons.codec.binary.Base32 base32_35 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray38 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str39 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray38);
        boolean boolean41 = base32_35.isInAlphabet(byteArray38, false);
        java.lang.String str42 = org.apache.commons.codec.digest.UnixCrypt.crypt(byteArray38);
        java.lang.String str43 = org.apache.commons.codec.digest.HmacUtils.hmacSha1Hex(byteArray31, byteArray38);
        java.lang.String str44 = org.apache.commons.codec.binary.BinaryCodec.toAsciiString(byteArray38);
        javax.crypto.Mac mac45 = org.apache.commons.codec.digest.HmacUtils.getHmacSha384(byteArray38);
        java.lang.String str46 = org.apache.commons.codec.binary.BinaryCodec.toAsciiString(byteArray38);
        org.apache.commons.codec.CodecPolicy codecPolicy49 = null;
        org.apache.commons.codec.binary.Base16 base16_50 = new org.apache.commons.codec.binary.Base16(true, codecPolicy49);
        boolean boolean52 = base16_50.isInAlphabet((byte) -1);
        org.apache.commons.codec.CodecPolicy codecPolicy53 = base16_50.getCodecPolicy();
        org.apache.commons.codec.binary.Base16 base16_54 = new org.apache.commons.codec.binary.Base16(true, codecPolicy53);
        org.apache.commons.codec.binary.Base64InputStream base64InputStream55 = new org.apache.commons.codec.binary.Base64InputStream((java.io.InputStream) base64InputStream24, false, (int) (byte) -1, byteArray38, codecPolicy53);
        // The following exception was thrown during execution in test generation
        try {
            byte[] byteArray56 = org.apache.commons.codec.digest.HmacUtils.hmacSha512(byteArray10, (java.io.InputStream) base64InputStream24);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertArrayEquals(byteArray3, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "\000" + "'", str4, "\000");
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNotNull(byteArray10);
        org.junit.Assert.assertArrayEquals(byteArray10, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "\000" + "'", str11, "\000");
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
        org.junit.Assert.assertNotNull(byteArray14);
        org.junit.Assert.assertArrayEquals(byteArray14, new byte[] { (byte) -53, (byte) -62, (byte) 109, (byte) 108, (byte) 16, (byte) 34, (byte) -87, (byte) -93, (byte) 59, (byte) 95, (byte) 90, (byte) 76, (byte) 18, (byte) -71, (byte) -99, (byte) -51 });
        org.junit.Assert.assertNotNull(byteArray20);
        org.junit.Assert.assertArrayEquals(byteArray20, new byte[] {});
        org.junit.Assert.assertNotNull(charArray21);
        org.junit.Assert.assertArrayEquals(charArray21, new char[] {});
        org.junit.Assert.assertNotNull(byteArray22);
        org.junit.Assert.assertArrayEquals(byteArray22, new byte[] {});
        org.junit.Assert.assertTrue("'" + int25 + "' != '" + 1 + "'", int25 == 1);
        org.junit.Assert.assertNotNull(byteArray31);
        org.junit.Assert.assertArrayEquals(byteArray31, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str32 + "' != '" + "\000" + "'", str32, "\000");
        org.junit.Assert.assertTrue("'" + boolean34 + "' != '" + false + "'", boolean34 == false);
        org.junit.Assert.assertNotNull(byteArray38);
        org.junit.Assert.assertArrayEquals(byteArray38, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str39 + "' != '" + "\000" + "'", str39, "\000");
        org.junit.Assert.assertTrue("'" + boolean41 + "' != '" + false + "'", boolean41 == false);
// flaky "37) test286(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str42 + "' != '" + "lb5G58PAaeDhk" + "'", str42, "lb5G58PAaeDhk");
        org.junit.Assert.assertEquals("'" + str43 + "' != '" + "310354661a5962d5b8cb76032d5a97e8aed7cf9f" + "'", str43, "310354661a5962d5b8cb76032d5a97e8aed7cf9f");
        org.junit.Assert.assertEquals("'" + str44 + "' != '" + "0000000000000000" + "'", str44, "0000000000000000");
        org.junit.Assert.assertNotNull(mac45);
        org.junit.Assert.assertEquals("'" + str46 + "' != '" + "0000000000000000" + "'", str46, "0000000000000000");
        org.junit.Assert.assertTrue("'" + boolean52 + "' != '" + false + "'", boolean52 == false);
        org.junit.Assert.assertTrue("'" + codecPolicy53 + "' != '" + org.apache.commons.codec.CodecPolicy.LENIENT + "'", codecPolicy53.equals(org.apache.commons.codec.CodecPolicy.LENIENT));
    }

    @Test
    public void test287() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test287");
        org.apache.commons.codec.binary.Base32 base32_0 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray3 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str4 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray3);
        boolean boolean6 = base32_0.isInAlphabet(byteArray3, false);
        java.lang.String str7 = org.apache.commons.codec.digest.UnixCrypt.crypt(byteArray3);
        byte[] byteArray8 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(byteArray3);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str9 = org.apache.commons.codec.digest.DigestUtils.shake128_256Hex(byteArray8);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: java.security.NoSuchAlgorithmException: SHAKE128-256 MessageDigest not available");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertArrayEquals(byteArray3, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "\000" + "'", str4, "\000");
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
// flaky "38) test287(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str7 + "' != '" + "FGhwfJvTzh/rE" + "'", str7, "FGhwfJvTzh/rE");
        org.junit.Assert.assertNotNull(byteArray8);
        org.junit.Assert.assertArrayEquals(byteArray8, new byte[] {});
    }

    @Test
    public void test288() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test288");
        char[] charArray0 = null;
        byte[] byteArray1 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray0);
        long[] longArray2 = org.apache.commons.codec.digest.MurmurHash3.hash128x64(byteArray1);
        long long3 = org.apache.commons.codec.digest.MurmurHash3.hash64(byteArray1);
        org.junit.Assert.assertNotNull(byteArray1);
        org.junit.Assert.assertArrayEquals(byteArray1, new byte[] {});
        org.junit.Assert.assertNotNull(longArray2);
        org.junit.Assert.assertArrayEquals(longArray2, new long[] { 0L, 0L });
        org.junit.Assert.assertTrue("'" + long3 + "' != '" + 8404154273843829576L + "'", long3 == 8404154273843829576L);
    }

    @Test
    public void test289() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test289");
        // The following exception was thrown during execution in test generation
        try {
            byte[] byteArray1 = org.apache.commons.codec.digest.DigestUtils.sha512_224("SHA3-384");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: java.security.NoSuchAlgorithmException: SHA-512/224 MessageDigest not available");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test290() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test290");
        org.apache.commons.codec.binary.Base64.Builder builder0 = new org.apache.commons.codec.binary.Base64.Builder();
        java.io.InputStream inputStream1 = null;
        org.apache.commons.codec.binary.Base16InputStream base16InputStream2 = new org.apache.commons.codec.binary.Base16InputStream(inputStream1);
        char[] charArray5 = null;
        byte[] byteArray6 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray5);
        char[] charArray7 = org.apache.commons.codec.binary.Hex.encodeHex(byteArray6);
        byte[] byteArray8 = org.apache.commons.codec.binary.Hex.decodeHex(charArray7);
        org.apache.commons.codec.CodecPolicy codecPolicy9 = null;
        org.apache.commons.codec.binary.Base64InputStream base64InputStream10 = new org.apache.commons.codec.binary.Base64InputStream((java.io.InputStream) base16InputStream2, true, (int) (short) 100, byteArray8, codecPolicy9);
        int int11 = base64InputStream10.available();
        org.apache.commons.codec.binary.Base32 base32_14 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray17 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str18 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray17);
        boolean boolean20 = base32_14.isInAlphabet(byteArray17, false);
        org.apache.commons.codec.binary.Base32 base32_21 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray24 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str25 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray24);
        boolean boolean27 = base32_21.isInAlphabet(byteArray24, false);
        java.lang.String str28 = org.apache.commons.codec.digest.UnixCrypt.crypt(byteArray24);
        java.lang.String str29 = org.apache.commons.codec.digest.HmacUtils.hmacSha1Hex(byteArray17, byteArray24);
        java.lang.String str30 = org.apache.commons.codec.binary.BinaryCodec.toAsciiString(byteArray24);
        javax.crypto.Mac mac31 = org.apache.commons.codec.digest.HmacUtils.getHmacSha384(byteArray24);
        java.lang.String str32 = org.apache.commons.codec.binary.BinaryCodec.toAsciiString(byteArray24);
        org.apache.commons.codec.CodecPolicy codecPolicy35 = null;
        org.apache.commons.codec.binary.Base16 base16_36 = new org.apache.commons.codec.binary.Base16(true, codecPolicy35);
        boolean boolean38 = base16_36.isInAlphabet((byte) -1);
        org.apache.commons.codec.CodecPolicy codecPolicy39 = base16_36.getCodecPolicy();
        org.apache.commons.codec.binary.Base16 base16_40 = new org.apache.commons.codec.binary.Base16(true, codecPolicy39);
        org.apache.commons.codec.binary.Base64InputStream base64InputStream41 = new org.apache.commons.codec.binary.Base64InputStream((java.io.InputStream) base64InputStream10, false, (int) (byte) -1, byteArray24, codecPolicy39);
        org.apache.commons.codec.binary.Base64.Builder builder42 = builder0.setDecodingPolicy(codecPolicy39);
        org.junit.Assert.assertNotNull(byteArray6);
        org.junit.Assert.assertArrayEquals(byteArray6, new byte[] {});
        org.junit.Assert.assertNotNull(charArray7);
        org.junit.Assert.assertArrayEquals(charArray7, new char[] {});
        org.junit.Assert.assertNotNull(byteArray8);
        org.junit.Assert.assertArrayEquals(byteArray8, new byte[] {});
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 1 + "'", int11 == 1);
        org.junit.Assert.assertNotNull(byteArray17);
        org.junit.Assert.assertArrayEquals(byteArray17, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str18 + "' != '" + "\000" + "'", str18, "\000");
        org.junit.Assert.assertTrue("'" + boolean20 + "' != '" + false + "'", boolean20 == false);
        org.junit.Assert.assertNotNull(byteArray24);
        org.junit.Assert.assertArrayEquals(byteArray24, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str25 + "' != '" + "\000" + "'", str25, "\000");
        org.junit.Assert.assertTrue("'" + boolean27 + "' != '" + false + "'", boolean27 == false);
// flaky "39) test290(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str28 + "' != '" + "Zn6vHU2xttauI" + "'", str28, "Zn6vHU2xttauI");
        org.junit.Assert.assertEquals("'" + str29 + "' != '" + "310354661a5962d5b8cb76032d5a97e8aed7cf9f" + "'", str29, "310354661a5962d5b8cb76032d5a97e8aed7cf9f");
        org.junit.Assert.assertEquals("'" + str30 + "' != '" + "0000000000000000" + "'", str30, "0000000000000000");
        org.junit.Assert.assertNotNull(mac31);
        org.junit.Assert.assertEquals("'" + str32 + "' != '" + "0000000000000000" + "'", str32, "0000000000000000");
        org.junit.Assert.assertTrue("'" + boolean38 + "' != '" + false + "'", boolean38 == false);
        org.junit.Assert.assertTrue("'" + codecPolicy39 + "' != '" + org.apache.commons.codec.CodecPolicy.LENIENT + "'", codecPolicy39.equals(org.apache.commons.codec.CodecPolicy.LENIENT));
        org.junit.Assert.assertNotNull(builder42);
    }

    @Test
    public void test291() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test291");
        org.apache.commons.codec.digest.HmacUtils hmacUtils0 = new org.apache.commons.codec.digest.HmacUtils();
        java.nio.ByteBuffer byteBuffer1 = null;
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str2 = hmacUtils0.hmacHex(byteBuffer1);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test292() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test292");
        org.apache.commons.codec.digest.HmacUtils hmacUtils0 = new org.apache.commons.codec.digest.HmacUtils();
        java.util.BitSet bitSet1 = null;
        org.apache.commons.codec.binary.Base32 base32_2 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray5 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str6 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray5);
        boolean boolean8 = base32_2.isInAlphabet(byteArray5, false);
        org.apache.commons.codec.binary.Base32 base32_9 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray12 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str13 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray12);
        boolean boolean15 = base32_9.isInAlphabet(byteArray12, false);
        java.lang.String str16 = org.apache.commons.codec.digest.UnixCrypt.crypt(byteArray12);
        java.lang.String str17 = org.apache.commons.codec.digest.HmacUtils.hmacSha1Hex(byteArray5, byteArray12);
        java.lang.String str18 = org.apache.commons.codec.binary.BinaryCodec.toAsciiString(byteArray12);
        javax.crypto.Mac mac19 = org.apache.commons.codec.digest.HmacUtils.getHmacSha384(byteArray12);
        byte[] byteArray20 = org.apache.commons.codec.net.URLCodec.encodeUrl(bitSet1, byteArray12);
        // The following exception was thrown during execution in test generation
        try {
            byte[] byteArray21 = hmacUtils0.hmac(byteArray20);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray5);
        org.junit.Assert.assertArrayEquals(byteArray5, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "\000" + "'", str6, "\000");
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertNotNull(byteArray12);
        org.junit.Assert.assertArrayEquals(byteArray12, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "\000" + "'", str13, "\000");
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
// flaky "40) test292(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str16 + "' != '" + "hZb.QsGKA7lRE" + "'", str16, "hZb.QsGKA7lRE");
        org.junit.Assert.assertEquals("'" + str17 + "' != '" + "310354661a5962d5b8cb76032d5a97e8aed7cf9f" + "'", str17, "310354661a5962d5b8cb76032d5a97e8aed7cf9f");
        org.junit.Assert.assertEquals("'" + str18 + "' != '" + "0000000000000000" + "'", str18, "0000000000000000");
        org.junit.Assert.assertNotNull(mac19);
        org.junit.Assert.assertNotNull(byteArray20);
        org.junit.Assert.assertArrayEquals(byteArray20, new byte[] { (byte) 37, (byte) 48, (byte) 48, (byte) 37, (byte) 48, (byte) 48 });
    }

    @Test
    public void test293() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test293");
        org.apache.commons.codec.language.bm.Languages languages1 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.apache.commons.codec.language.bm.Lang lang2 = org.apache.commons.codec.language.bm.Lang.loadFromResource("US-ASCII", languages1);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Unable to resolve required resource: US-ASCII");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test294() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test294");
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str2 = org.apache.commons.codec.digest.Md5Crypt.apr1Crypt("G30LGL3wppDqc", "$1$2bZpHv3e$KuegH6jWeAAwJFSY6saCF0");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Invalid salt value: $apr1$$1$2bZpHv3e$KuegH6jWeAAwJFSY6saCF0");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test295() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test295");
        byte[] byteArray1 = org.apache.commons.codec.binary.Base64.decodeBase64("$1$gUdz5yQC$LzF0PPk8FItQxPDDbufAf.");
        org.junit.Assert.assertNotNull(byteArray1);
        org.junit.Assert.assertArrayEquals(byteArray1, new byte[] { (byte) -42, (byte) 5, (byte) 29, (byte) -49, (byte) -100, (byte) -112, (byte) 8, (byte) -68, (byte) -59, (byte) -48, (byte) -13, (byte) -28, (byte) -16, (byte) 82, (byte) 45, (byte) 67, (byte) 19, (byte) -61, (byte) 13, (byte) -69, (byte) -97, (byte) 1 });
    }

    @Test
    public void test296() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test296");
        char[] charArray1 = null;
        byte[] byteArray2 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray1);
        org.apache.commons.codec.binary.Base64 base64_3 = new org.apache.commons.codec.binary.Base64((int) '4', byteArray2);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str5 = org.apache.commons.codec.digest.Md5Crypt.md5Crypt(byteArray2, "B78Te0Lj1zc0U");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Invalid salt value: B78Te0Lj1zc0U");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray2);
        org.junit.Assert.assertArrayEquals(byteArray2, new byte[] {});
    }

    @Test
    public void test297() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test297");
        org.apache.commons.codec.binary.Base64OutputStream.Builder builder0 = org.apache.commons.codec.binary.Base64OutputStream.builder();
        org.junit.Assert.assertNotNull(builder0);
    }

    @Test
    public void test298() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test298");
        java.io.OutputStream outputStream0 = null;
        org.apache.commons.codec.binary.Base64OutputStream base64OutputStream1 = new org.apache.commons.codec.binary.Base64OutputStream(outputStream0);
        org.apache.commons.codec.binary.Base32 base32_4 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray7 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str8 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray7);
        boolean boolean10 = base32_4.isInAlphabet(byteArray7, false);
        org.apache.commons.codec.binary.Base32 base32_11 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray14 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str15 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray14);
        boolean boolean17 = base32_11.isInAlphabet(byteArray14, false);
        byte[] byteArray18 = org.apache.commons.codec.digest.HmacUtils.hmacMd5(byteArray7, byteArray14);
        org.apache.commons.codec.binary.Base32OutputStream base32OutputStream19 = new org.apache.commons.codec.binary.Base32OutputStream((java.io.OutputStream) base64OutputStream1, false, 1, byteArray14);
        byte[] byteArray20 = org.apache.commons.codec.binary.Base64.encodeBase64Chunked(byteArray14);
        // The following exception was thrown during execution in test generation
        try {
            long long22 = org.apache.commons.codec.digest.MurmurHash2.hash64(byteArray14, (int) '#');
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: 2");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray7);
        org.junit.Assert.assertArrayEquals(byteArray7, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "\000" + "'", str8, "\000");
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        org.junit.Assert.assertNotNull(byteArray14);
        org.junit.Assert.assertArrayEquals(byteArray14, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str15 + "' != '" + "\000" + "'", str15, "\000");
        org.junit.Assert.assertTrue("'" + boolean17 + "' != '" + false + "'", boolean17 == false);
        org.junit.Assert.assertNotNull(byteArray18);
        org.junit.Assert.assertArrayEquals(byteArray18, new byte[] { (byte) -53, (byte) -62, (byte) 109, (byte) 108, (byte) 16, (byte) 34, (byte) -87, (byte) -93, (byte) 59, (byte) 95, (byte) 90, (byte) 76, (byte) 18, (byte) -71, (byte) -99, (byte) -51 });
        org.junit.Assert.assertNotNull(byteArray20);
        org.junit.Assert.assertArrayEquals(byteArray20, new byte[] { (byte) 65, (byte) 65, (byte) 65, (byte) 61, (byte) 13, (byte) 10 });
    }

    @Test
    public void test299() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test299");
        org.apache.commons.codec.digest.MurmurHash3.IncrementalHash32 incrementalHash32_0 = new org.apache.commons.codec.digest.MurmurHash3.IncrementalHash32();
        byte[] byteArray1 = null;
        incrementalHash32_0.add(byteArray1, (-1), (-342794719));
    }

    @Test
    public void test300() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test300");
        org.apache.commons.codec.binary.Base64 base64_0 = new org.apache.commons.codec.binary.Base64();
        boolean boolean1 = base64_0.isUrlSafe();
        byte[] byteArray8 = new byte[] { (byte) 10, (byte) 0, (byte) -1, (byte) 0, (byte) 1, (byte) 100 };
        java.lang.String str9 = org.apache.commons.codec.digest.Md5Crypt.md5Crypt(byteArray8);
        java.lang.String str10 = org.apache.commons.codec.digest.Crypt.crypt(byteArray8);
        boolean boolean12 = base64_0.isInAlphabet(byteArray8, false);
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
        org.junit.Assert.assertNotNull(byteArray8);
        org.junit.Assert.assertArrayEquals(byteArray8, new byte[] { (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0 });
// flaky "41) test300(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str9 + "' != '" + "$1$Ah8mPb89$8SrfTZxCWqtnlH5mtD61N/" + "'", str9, "$1$Ah8mPb89$8SrfTZxCWqtnlH5mtD61N/");
// flaky "5) test300(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str10 + "' != '" + "$6$afPjND9u$Vjm9uTzKAFNM6eLxyCYznJMIAnREH83b0jSKxt4Ue7Lgo2cVGsIk7KT8uMpoUeBsQqzfP08pJMn2LE0sEYRa0." + "'", str10, "$6$afPjND9u$Vjm9uTzKAFNM6eLxyCYznJMIAnREH83b0jSKxt4Ue7Lgo2cVGsIk7KT8uMpoUeBsQqzfP08pJMn2LE0sEYRa0.");
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
    }

    @Test
    public void test301() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test301");
        java.io.InputStream inputStream0 = null;
        org.apache.commons.codec.binary.Base16InputStream base16InputStream1 = new org.apache.commons.codec.binary.Base16InputStream(inputStream0);
        char[] charArray4 = null;
        byte[] byteArray5 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray4);
        char[] charArray6 = org.apache.commons.codec.binary.Hex.encodeHex(byteArray5);
        byte[] byteArray7 = org.apache.commons.codec.binary.Hex.decodeHex(charArray6);
        org.apache.commons.codec.CodecPolicy codecPolicy8 = null;
        org.apache.commons.codec.binary.Base64InputStream base64InputStream9 = new org.apache.commons.codec.binary.Base64InputStream((java.io.InputStream) base16InputStream1, true, (int) (short) 100, byteArray7, codecPolicy8);
        int int10 = base64InputStream9.available();
        org.apache.commons.codec.binary.Base32 base32_13 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray16 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str17 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray16);
        boolean boolean19 = base32_13.isInAlphabet(byteArray16, false);
        org.apache.commons.codec.binary.Base32 base32_20 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray23 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str24 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray23);
        boolean boolean26 = base32_20.isInAlphabet(byteArray23, false);
        java.lang.String str27 = org.apache.commons.codec.digest.UnixCrypt.crypt(byteArray23);
        java.lang.String str28 = org.apache.commons.codec.digest.HmacUtils.hmacSha1Hex(byteArray16, byteArray23);
        java.lang.String str29 = org.apache.commons.codec.binary.BinaryCodec.toAsciiString(byteArray23);
        javax.crypto.Mac mac30 = org.apache.commons.codec.digest.HmacUtils.getHmacSha384(byteArray23);
        java.lang.String str31 = org.apache.commons.codec.binary.BinaryCodec.toAsciiString(byteArray23);
        org.apache.commons.codec.CodecPolicy codecPolicy34 = null;
        org.apache.commons.codec.binary.Base16 base16_35 = new org.apache.commons.codec.binary.Base16(true, codecPolicy34);
        boolean boolean37 = base16_35.isInAlphabet((byte) -1);
        org.apache.commons.codec.CodecPolicy codecPolicy38 = base16_35.getCodecPolicy();
        org.apache.commons.codec.binary.Base16 base16_39 = new org.apache.commons.codec.binary.Base16(true, codecPolicy38);
        org.apache.commons.codec.binary.Base64InputStream base64InputStream40 = new org.apache.commons.codec.binary.Base64InputStream((java.io.InputStream) base64InputStream9, false, (int) (byte) -1, byteArray23, codecPolicy38);
        org.apache.commons.codec.binary.Base32 base32_41 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray44 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str45 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray44);
        boolean boolean47 = base32_41.isInAlphabet(byteArray44, false);
        byte[] byteArray50 = org.apache.commons.codec.digest.HmacUtils.hmacSha512("310354661a5962d5b8cb76032d5a97e8aed7cf9f", "");
        byte[] byteArray51 = base32_41.encode(byteArray50);
        org.apache.commons.codec.digest.Blake3 blake3_52 = org.apache.commons.codec.digest.Blake3.initKeyDerivationFunction(byteArray51);
        javax.crypto.Mac mac53 = org.apache.commons.codec.digest.HmacUtils.getHmacSha256(byteArray51);
        java.lang.String str54 = org.apache.commons.codec.digest.DigestUtils.sha1Hex(byteArray51);
        // The following exception was thrown during execution in test generation
        try {
            int int57 = base64InputStream9.read(byteArray51, (int) (short) 100, (int) (short) 100);
            org.junit.Assert.fail("Expected exception of type java.lang.IndexOutOfBoundsException; message: null");
        } catch (java.lang.IndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray5);
        org.junit.Assert.assertArrayEquals(byteArray5, new byte[] {});
        org.junit.Assert.assertNotNull(charArray6);
        org.junit.Assert.assertArrayEquals(charArray6, new char[] {});
        org.junit.Assert.assertNotNull(byteArray7);
        org.junit.Assert.assertArrayEquals(byteArray7, new byte[] {});
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 1 + "'", int10 == 1);
        org.junit.Assert.assertNotNull(byteArray16);
        org.junit.Assert.assertArrayEquals(byteArray16, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str17 + "' != '" + "\000" + "'", str17, "\000");
        org.junit.Assert.assertTrue("'" + boolean19 + "' != '" + false + "'", boolean19 == false);
        org.junit.Assert.assertNotNull(byteArray23);
        org.junit.Assert.assertArrayEquals(byteArray23, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str24 + "' != '" + "\000" + "'", str24, "\000");
        org.junit.Assert.assertTrue("'" + boolean26 + "' != '" + false + "'", boolean26 == false);
// flaky "42) test301(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str27 + "' != '" + "Jpgt/TPkC27SM" + "'", str27, "Jpgt/TPkC27SM");
        org.junit.Assert.assertEquals("'" + str28 + "' != '" + "310354661a5962d5b8cb76032d5a97e8aed7cf9f" + "'", str28, "310354661a5962d5b8cb76032d5a97e8aed7cf9f");
        org.junit.Assert.assertEquals("'" + str29 + "' != '" + "0000000000000000" + "'", str29, "0000000000000000");
        org.junit.Assert.assertNotNull(mac30);
        org.junit.Assert.assertEquals("'" + str31 + "' != '" + "0000000000000000" + "'", str31, "0000000000000000");
        org.junit.Assert.assertTrue("'" + boolean37 + "' != '" + false + "'", boolean37 == false);
        org.junit.Assert.assertTrue("'" + codecPolicy38 + "' != '" + org.apache.commons.codec.CodecPolicy.LENIENT + "'", codecPolicy38.equals(org.apache.commons.codec.CodecPolicy.LENIENT));
        org.junit.Assert.assertNotNull(byteArray44);
        org.junit.Assert.assertArrayEquals(byteArray44, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str45 + "' != '" + "\000" + "'", str45, "\000");
        org.junit.Assert.assertTrue("'" + boolean47 + "' != '" + false + "'", boolean47 == false);
        org.junit.Assert.assertNotNull(byteArray50);
        org.junit.Assert.assertNotNull(byteArray51);
        org.junit.Assert.assertNotNull(blake3_52);
        org.junit.Assert.assertNotNull(mac53);
        org.junit.Assert.assertEquals("'" + str54 + "' != '" + "cf7c649cdd8f45951ef6458cc0e075b3b774e813" + "'", str54, "cf7c649cdd8f45951ef6458cc0e075b3b774e813");
    }

    @Test
    public void test302() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test302");
        java.io.InputStream inputStream0 = null;
        org.apache.commons.codec.binary.Base16InputStream base16InputStream1 = new org.apache.commons.codec.binary.Base16InputStream(inputStream0);
        char[] charArray4 = null;
        byte[] byteArray5 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray4);
        char[] charArray6 = org.apache.commons.codec.binary.Hex.encodeHex(byteArray5);
        byte[] byteArray7 = org.apache.commons.codec.binary.Hex.decodeHex(charArray6);
        org.apache.commons.codec.CodecPolicy codecPolicy8 = null;
        org.apache.commons.codec.binary.Base64InputStream base64InputStream9 = new org.apache.commons.codec.binary.Base64InputStream((java.io.InputStream) base16InputStream1, true, (int) (short) 100, byteArray7, codecPolicy8);
        char[] charArray12 = null;
        byte[] byteArray13 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray12);
        char[] charArray14 = org.apache.commons.codec.binary.Hex.encodeHex(byteArray13);
        java.lang.String str15 = org.apache.commons.codec.binary.BinaryCodec.toAsciiString(byteArray13);
        org.apache.commons.codec.CodecPolicy codecPolicy16 = null;
        org.apache.commons.codec.binary.Base32InputStream base32InputStream17 = new org.apache.commons.codec.binary.Base32InputStream((java.io.InputStream) base16InputStream1, false, (int) (byte) 100, byteArray13, codecPolicy16);
        org.apache.commons.codec.binary.Base64.Builder builder18 = new org.apache.commons.codec.binary.Base64.Builder();
        java.io.InputStream inputStream19 = null;
        org.apache.commons.codec.binary.Base16InputStream base16InputStream20 = new org.apache.commons.codec.binary.Base16InputStream(inputStream19);
        byte[] byteArray29 = new byte[] { (byte) 10, (byte) 0, (byte) -1, (byte) 0, (byte) 1, (byte) 100 };
        java.lang.String str30 = org.apache.commons.codec.digest.Md5Crypt.md5Crypt(byteArray29);
        org.apache.commons.codec.CodecPolicy codecPolicy31 = null;
        org.apache.commons.codec.binary.Base64InputStream base64InputStream32 = new org.apache.commons.codec.binary.Base64InputStream((java.io.InputStream) base16InputStream20, false, (int) 'a', byteArray29, codecPolicy31);
        org.apache.commons.codec.binary.Base64.Builder builder33 = builder18.setDecodeTable(byteArray29);
        byte[] byteArray34 = org.apache.commons.codec.binary.Base64.decodeBase64UrlSafe(byteArray29);
        java.lang.String str35 = org.apache.commons.codec.digest.Md5Crypt.md5Crypt(byteArray29);
        // The following exception was thrown during execution in test generation
        try {
            int int38 = base32InputStream17.read(byteArray29, (int) (byte) -1, (int) '4');
            org.junit.Assert.fail("Expected exception of type java.lang.IndexOutOfBoundsException; message: null");
        } catch (java.lang.IndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray5);
        org.junit.Assert.assertArrayEquals(byteArray5, new byte[] {});
        org.junit.Assert.assertNotNull(charArray6);
        org.junit.Assert.assertArrayEquals(charArray6, new char[] {});
        org.junit.Assert.assertNotNull(byteArray7);
        org.junit.Assert.assertArrayEquals(byteArray7, new byte[] {});
        org.junit.Assert.assertNotNull(byteArray13);
        org.junit.Assert.assertArrayEquals(byteArray13, new byte[] {});
        org.junit.Assert.assertNotNull(charArray14);
        org.junit.Assert.assertArrayEquals(charArray14, new char[] {});
        org.junit.Assert.assertEquals("'" + str15 + "' != '" + "" + "'", str15, "");
        org.junit.Assert.assertNotNull(byteArray29);
        org.junit.Assert.assertArrayEquals(byteArray29, new byte[] { (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0 });
// flaky "43) test302(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str30 + "' != '" + "$1$56mU8vX9$H2YUQF8n0XQWcjl4eLXnY." + "'", str30, "$1$56mU8vX9$H2YUQF8n0XQWcjl4eLXnY.");
        org.junit.Assert.assertNotNull(builder33);
        org.junit.Assert.assertNotNull(byteArray34);
        org.junit.Assert.assertArrayEquals(byteArray34, new byte[] {});
// flaky "6) test302(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str35 + "' != '" + "$1$T5nLzKN3$6aaeK7szpL08dvp6y8pDg1" + "'", str35, "$1$T5nLzKN3$6aaeK7szpL08dvp6y8pDg1");
    }

    @Test
    public void test303() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test303");
        org.apache.commons.codec.language.Soundex soundex1 = new org.apache.commons.codec.language.Soundex("310354661a5962d5b8cb76032d5a97e8aed7cf9f");
        java.lang.String str3 = soundex1.encode("MD2");
        org.junit.Assert.assertEquals("'" + str3 + "' != '" + "M300" + "'", str3, "M300");
    }

    @Test
    public void test304() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test304");
        java.io.InputStream inputStream0 = null;
        org.apache.commons.codec.binary.Base16InputStream base16InputStream1 = new org.apache.commons.codec.binary.Base16InputStream(inputStream0);
        boolean boolean2 = base16InputStream1.markSupported();
        org.apache.commons.codec.binary.Base32InputStream base32InputStream4 = new org.apache.commons.codec.binary.Base32InputStream((java.io.InputStream) base16InputStream1, false);
        // The following exception was thrown during execution in test generation
        try {
            base16InputStream1.reset();
            org.junit.Assert.fail("Expected exception of type java.io.IOException; message: mark/reset not supported");
        } catch (java.io.IOException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
    }

    @Test
    public void test305() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test305");
        org.apache.commons.codec.binary.Base32 base32_0 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray3 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str4 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray3);
        boolean boolean6 = base32_0.isInAlphabet(byteArray3, false);
        org.apache.commons.codec.binary.Base32 base32_7 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray10 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str11 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray10);
        boolean boolean13 = base32_7.isInAlphabet(byteArray10, false);
        java.lang.String str14 = org.apache.commons.codec.digest.UnixCrypt.crypt(byteArray10);
        java.lang.String str15 = org.apache.commons.codec.digest.HmacUtils.hmacSha1Hex(byteArray3, byteArray10);
        java.lang.String str16 = org.apache.commons.codec.digest.DigestUtils.shaHex(byteArray10);
        byte[] byteArray17 = org.apache.commons.codec.net.URLCodec.decodeUrl(byteArray10);
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertArrayEquals(byteArray3, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "\000" + "'", str4, "\000");
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNotNull(byteArray10);
        org.junit.Assert.assertArrayEquals(byteArray10, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "\000" + "'", str11, "\000");
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
// flaky "44) test305(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str14 + "' != '" + "kCtR0YtZR2o36" + "'", str14, "kCtR0YtZR2o36");
        org.junit.Assert.assertEquals("'" + str15 + "' != '" + "310354661a5962d5b8cb76032d5a97e8aed7cf9f" + "'", str15, "310354661a5962d5b8cb76032d5a97e8aed7cf9f");
        org.junit.Assert.assertEquals("'" + str16 + "' != '" + "1489f923c4dca729178b3e3233458550d8dddf29" + "'", str16, "1489f923c4dca729178b3e3233458550d8dddf29");
        org.junit.Assert.assertNotNull(byteArray17);
        org.junit.Assert.assertArrayEquals(byteArray17, new byte[] { (byte) 0, (byte) 0 });
    }

    @Test
    public void test306() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test306");
        org.apache.commons.codec.language.bm.NameType nameType0 = null;
        org.apache.commons.codec.language.bm.RuleType ruleType1 = null;
        org.apache.commons.codec.language.bm.Languages.LanguageSet languageSet2 = org.apache.commons.codec.language.bm.Languages.NO_LANGUAGES;
        boolean boolean4 = languageSet2.contains("Crc16 [init=0x0000, crc=0x0000, xorOut=0x0000, crc^xorOut=0x0000]");
        // The following exception was thrown during execution in test generation
        try {
            java.util.Map<java.lang.String, java.util.List<org.apache.commons.codec.language.bm.Rule>> strMap5 = org.apache.commons.codec.language.bm.Rule.getInstanceMap(nameType0, ruleType1, languageSet2);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(languageSet2);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
    }

    @Test
    public void test307() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test307");
        byte[] byteArray1 = org.apache.commons.codec.digest.DigestUtils.sha256("PCqE1NnZAFIA2");
        org.junit.Assert.assertNotNull(byteArray1);
    }

    @Test
    public void test308() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test308");
        org.apache.commons.codec.language.bm.NameType nameType0 = null;
        org.apache.commons.codec.language.bm.RuleType ruleType1 = null;
        org.apache.commons.codec.language.bm.PhoneticEngine phoneticEngine3 = new org.apache.commons.codec.language.bm.PhoneticEngine(nameType0, ruleType1, false);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str5 = phoneticEngine3.encode("$1$6Ol7ARtC$6M2x6pcRYE8xqRHhnMSwE.");
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test309() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test309");
        byte[] byteArray2 = org.apache.commons.codec.digest.HmacUtils.hmacSha512("PCqE1NnZAFIA2", "ISO-8859-1");
        org.junit.Assert.assertNotNull(byteArray2);
    }

    @Test
    public void test310() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test310");
        java.security.MessageDigest messageDigest1 = org.apache.commons.codec.digest.DigestUtils.getShaDigest();
        org.apache.commons.codec.digest.DigestUtils digestUtils2 = new org.apache.commons.codec.digest.DigestUtils(messageDigest1);
        java.security.MessageDigest messageDigest3 = org.apache.commons.codec.digest.DigestUtils.getDigest("e8d8123bcec18f5e308b0e5219338abba38bdeef310c42c2503cbd593fcf1fae3dfd1d38d64c108f9f299fde729e5f13", messageDigest1);
        org.apache.commons.codec.digest.DigestUtils digestUtils4 = new org.apache.commons.codec.digest.DigestUtils(messageDigest1);
        byte[] byteArray6 = org.apache.commons.codec.binary.StringUtils.getBytesIso8859_1("c39394a96ebd177a239a817fe8453ab0");
        byte[] byteArray7 = digestUtils4.digest(byteArray6);
        org.junit.Assert.assertNotNull(messageDigest1);
        org.junit.Assert.assertEquals(messageDigest1.toString(), "SHA-1 Message Digest from SUN, <initialized>\r\n");
        org.junit.Assert.assertNotNull(messageDigest3);
        org.junit.Assert.assertEquals(messageDigest3.toString(), "SHA-1 Message Digest from SUN, <initialized>\r\n");
        org.junit.Assert.assertNotNull(byteArray6);
        org.junit.Assert.assertNotNull(byteArray7);
        org.junit.Assert.assertArrayEquals(byteArray7, new byte[] { (byte) -44, (byte) -119, (byte) -20, (byte) -76, (byte) 115, (byte) 119, (byte) -23, (byte) -3, (byte) 74, (byte) 19, (byte) 44, (byte) 85, (byte) -123, (byte) -34, (byte) 51, (byte) 12, (byte) 61, (byte) 55, (byte) 122, (byte) 42 });
    }

    @Test
    public void test311() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test311");
        org.apache.commons.codec.language.DaitchMokotoffSoundex daitchMokotoffSoundex1 = new org.apache.commons.codec.language.DaitchMokotoffSoundex(false);
        org.apache.commons.codec.digest.Crc16.Builder builder2 = new org.apache.commons.codec.digest.Crc16.Builder();
        int[] intArray3 = org.apache.commons.codec.digest.Crc16.getDnpTable();
        org.apache.commons.codec.digest.Crc16.Builder builder4 = builder2.setTable(intArray3);
        org.apache.commons.codec.digest.Crc16.Builder builder5 = new org.apache.commons.codec.digest.Crc16.Builder();
        int[] intArray6 = org.apache.commons.codec.digest.Crc16.getDnpTable();
        org.apache.commons.codec.digest.Crc16.Builder builder7 = builder5.setTable(intArray6);
        org.apache.commons.codec.digest.Crc16.Builder builder8 = builder2.setTable(intArray6);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj9 = daitchMokotoffSoundex1.encode((java.lang.Object) intArray6);
            org.junit.Assert.fail("Expected exception of type org.apache.commons.codec.EncoderException; message: Parameter supplied to DaitchMokotoffSoundex encode is not of type java.lang.String");
        } catch (org.apache.commons.codec.EncoderException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(intArray3);
        org.junit.Assert.assertNotNull(builder4);
        org.junit.Assert.assertNotNull(intArray6);
        org.junit.Assert.assertNotNull(builder7);
        org.junit.Assert.assertNotNull(builder8);
    }

    @Test
    public void test312() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test312");
        java.security.MessageDigest messageDigest1 = org.apache.commons.codec.digest.DigestUtils.getShaDigest();
        org.apache.commons.codec.digest.DigestUtils digestUtils2 = new org.apache.commons.codec.digest.DigestUtils(messageDigest1);
        java.security.MessageDigest messageDigest3 = org.apache.commons.codec.digest.DigestUtils.getDigest("e8d8123bcec18f5e308b0e5219338abba38bdeef310c42c2503cbd593fcf1fae3dfd1d38d64c108f9f299fde729e5f13", messageDigest1);
        java.nio.file.Path path4 = null;
        java.nio.file.OpenOption[] openOptionArray5 = new java.nio.file.OpenOption[] {};
        // The following exception was thrown during execution in test generation
        try {
            byte[] byteArray6 = org.apache.commons.codec.digest.DigestUtils.digest(messageDigest3, path4, openOptionArray5);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(messageDigest1);
        org.junit.Assert.assertEquals(messageDigest1.toString(), "SHA-1 Message Digest from SUN, <initialized>\r\n");
        org.junit.Assert.assertNotNull(messageDigest3);
        org.junit.Assert.assertEquals(messageDigest3.toString(), "SHA-1 Message Digest from SUN, <initialized>\r\n");
        org.junit.Assert.assertNotNull(openOptionArray5);
        org.junit.Assert.assertArrayEquals(openOptionArray5, new java.nio.file.OpenOption[] {});
    }

    @Test
    public void test313() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test313");
        byte[] byteArray1 = org.apache.commons.codec.binary.Hex.decodeHex("cf7c649cdd8f45951ef6458cc0e075b3b774e813");
        org.junit.Assert.assertNotNull(byteArray1);
        org.junit.Assert.assertArrayEquals(byteArray1, new byte[] { (byte) -49, (byte) 124, (byte) 100, (byte) -100, (byte) -35, (byte) -113, (byte) 69, (byte) -107, (byte) 30, (byte) -10, (byte) 69, (byte) -116, (byte) -64, (byte) -32, (byte) 117, (byte) -77, (byte) -73, (byte) 116, (byte) -24, (byte) 19 });
    }

    @Test
    public void test314() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test314");
        int int1 = org.apache.commons.codec.digest.MurmurHash3.hash32("SHAKE128-256");
        org.junit.Assert.assertTrue("'" + int1 + "' != '" + 689063039 + "'", int1 == 689063039);
    }

    @Test
    public void test315() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test315");
        // The following exception was thrown during execution in test generation
        try {
            byte[] byteArray1 = org.apache.commons.codec.digest.DigestUtils.shake256_512("YWazuH2sTUZzQ");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: java.security.NoSuchAlgorithmException: SHAKE256-512 MessageDigest not available");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test316() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test316");
        org.apache.commons.codec.language.MatchRatingApproachEncoder matchRatingApproachEncoder0 = new org.apache.commons.codec.language.MatchRatingApproachEncoder();
        org.apache.commons.codec.binary.Base16.Builder builder1 = org.apache.commons.codec.binary.Base16.builder();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj2 = matchRatingApproachEncoder0.encode((java.lang.Object) builder1);
            org.junit.Assert.fail("Expected exception of type org.apache.commons.codec.EncoderException; message: Parameter supplied to Match Rating Approach encoder is not of type java.lang.String");
        } catch (org.apache.commons.codec.EncoderException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(builder1);
    }

    @Test
    public void test317() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test317");
        org.apache.commons.codec.digest.UnixCrypt unixCrypt0 = new org.apache.commons.codec.digest.UnixCrypt();
    }

    @Test
    public void test318() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test318");
        org.apache.commons.codec.digest.GitIdentifiers.FileMode fileMode0 = org.apache.commons.codec.digest.GitIdentifiers.FileMode.SYMBOLIC_LINK;
        org.junit.Assert.assertTrue("'" + fileMode0 + "' != '" + org.apache.commons.codec.digest.GitIdentifiers.FileMode.SYMBOLIC_LINK + "'", fileMode0.equals(org.apache.commons.codec.digest.GitIdentifiers.FileMode.SYMBOLIC_LINK));
    }

    @Test
    public void test319() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test319");
        org.apache.commons.codec.language.Caverphone2 caverphone2_0 = new org.apache.commons.codec.language.Caverphone2();
        java.lang.String str2 = caverphone2_0.encode("MD2");
        org.apache.commons.codec.language.bm.Rule.PhonemeExpr phonemeExpr6 = null;
        org.apache.commons.codec.language.bm.Rule rule7 = new org.apache.commons.codec.language.bm.Rule("1489f923c4dca729178b3e3233458550d8dddf29", "SHA-1", "AA11111111", phonemeExpr6);
        org.apache.commons.codec.language.bm.Rule.RPattern rPattern8 = rule7.getRContext();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj9 = caverphone2_0.encode((java.lang.Object) rule7);
            org.junit.Assert.fail("Expected exception of type org.apache.commons.codec.EncoderException; message: Parameter supplied to Caverphone encode is not of type java.lang.String");
        } catch (org.apache.commons.codec.EncoderException e) {
            // Expected exception.
        }
        org.junit.Assert.assertEquals("'" + str2 + "' != '" + "MT11111111" + "'", str2, "MT11111111");
        org.junit.Assert.assertNotNull(rPattern8);
    }

    @Test
    public void test320() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test320");
        byte[] byteArray1 = org.apache.commons.codec.digest.DigestUtils.sha512("");
        java.security.MessageDigest messageDigest3 = org.apache.commons.codec.digest.DigestUtils.getDigest("MD5");
        org.apache.commons.codec.net.URLCodec uRLCodec5 = new org.apache.commons.codec.net.URLCodec("PCqE1NnZAFIA2");
        org.apache.commons.codec.binary.Base32 base32_6 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray9 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str10 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray9);
        boolean boolean12 = base32_6.isInAlphabet(byteArray9, false);
        org.apache.commons.codec.binary.Base32 base32_13 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray16 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str17 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray16);
        boolean boolean19 = base32_13.isInAlphabet(byteArray16, false);
        java.lang.String str20 = org.apache.commons.codec.digest.UnixCrypt.crypt(byteArray16);
        java.lang.String str21 = org.apache.commons.codec.digest.HmacUtils.hmacSha1Hex(byteArray9, byteArray16);
        byte[] byteArray22 = uRLCodec5.decode(byteArray9);
        byte[] byteArray23 = org.apache.commons.codec.digest.DigestUtils.digest(messageDigest3, byteArray9);
        // The following exception was thrown during execution in test generation
        try {
            byte[] byteArray24 = org.apache.commons.codec.digest.Blake3.keyedHash(byteArray1, byteArray9);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Blake3 keys must be 32 bytes");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray1);
        org.junit.Assert.assertNotNull(messageDigest3);
        org.junit.Assert.assertEquals(messageDigest3.toString(), "MD5 Message Digest from SUN, <initialized>\r\n");
        org.junit.Assert.assertNotNull(byteArray9);
        org.junit.Assert.assertArrayEquals(byteArray9, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str10 + "' != '" + "\000" + "'", str10, "\000");
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
        org.junit.Assert.assertNotNull(byteArray16);
        org.junit.Assert.assertArrayEquals(byteArray16, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str17 + "' != '" + "\000" + "'", str17, "\000");
        org.junit.Assert.assertTrue("'" + boolean19 + "' != '" + false + "'", boolean19 == false);
// flaky "45) test320(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str20 + "' != '" + "Kdi3hqoNq8hZg" + "'", str20, "Kdi3hqoNq8hZg");
        org.junit.Assert.assertEquals("'" + str21 + "' != '" + "310354661a5962d5b8cb76032d5a97e8aed7cf9f" + "'", str21, "310354661a5962d5b8cb76032d5a97e8aed7cf9f");
        org.junit.Assert.assertNotNull(byteArray22);
        org.junit.Assert.assertArrayEquals(byteArray22, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertNotNull(byteArray23);
        org.junit.Assert.assertArrayEquals(byteArray23, new byte[] { (byte) -60, (byte) 16, (byte) 63, (byte) 18, (byte) 45, (byte) 39, (byte) 103, (byte) 124, (byte) -99, (byte) -79, (byte) 68, (byte) -54, (byte) -31, (byte) 57, (byte) 74, (byte) 102 });
    }

    @Test
    public void test321() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test321");
        int int1 = org.apache.commons.codec.digest.MurmurHash3.hash32((-8350299967407043051L));
        org.junit.Assert.assertTrue("'" + int1 + "' != '" + 1035661600 + "'", int1 == 1035661600);
    }

    @Test
    public void test322() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test322");
        java.io.InputStream inputStream0 = null;
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str1 = org.apache.commons.codec.digest.DigestUtils.sha3_256Hex(inputStream0);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: java.security.NoSuchAlgorithmException: SHA3-256 MessageDigest not available");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test323() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test323");
        int int3 = org.apache.commons.codec.digest.MurmurHash3.hash32(8404154273843829576L, (long) (short) 100, 76);
        org.junit.Assert.assertTrue("'" + int3 + "' != '" + 1464544180 + "'", int3 == 1464544180);
    }

    @Test
    public void test324() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test324");
        char[] charArray1 = null;
        byte[] byteArray2 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray1);
        org.apache.commons.codec.binary.Base64 base64_3 = new org.apache.commons.codec.binary.Base64((int) '4', byteArray2);
        org.apache.commons.codec.binary.Base32 base32_4 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray7 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str8 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray7);
        boolean boolean10 = base32_4.isInAlphabet(byteArray7, false);
        long[] longArray11 = org.apache.commons.codec.digest.MurmurHash3.hash128(byteArray7);
        byte[] byteArray12 = base64_3.decode(byteArray7);
        byte[] byteArray14 = base64_3.decode("AAA");
        boolean boolean15 = org.apache.commons.codec.binary.Base64.isBase64(byteArray14);
        org.junit.Assert.assertNotNull(byteArray2);
        org.junit.Assert.assertArrayEquals(byteArray2, new byte[] {});
        org.junit.Assert.assertNotNull(byteArray7);
        org.junit.Assert.assertArrayEquals(byteArray7, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "\000" + "'", str8, "\000");
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        org.junit.Assert.assertNotNull(longArray11);
        org.junit.Assert.assertArrayEquals(longArray11, new long[] { 8478934883742226405L, (-1017287513161072006L) });
        org.junit.Assert.assertNotNull(byteArray12);
        org.junit.Assert.assertArrayEquals(byteArray12, new byte[] {});
        org.junit.Assert.assertNotNull(byteArray14);
        org.junit.Assert.assertArrayEquals(byteArray14, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
    }

    @Test
    public void test325() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test325");
        org.apache.commons.codec.language.RefinedSoundex refinedSoundex0 = new org.apache.commons.codec.language.RefinedSoundex();
    }

    @Test
    public void test326() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test326");
        java.security.MessageDigest messageDigest0 = org.apache.commons.codec.digest.DigestUtils.getSha384Digest();
        org.junit.Assert.assertNotNull(messageDigest0);
        org.junit.Assert.assertEquals(messageDigest0.toString(), "SHA-384 Message Digest from SUN, <initialized>\r\n");
    }

    @Test
    public void test327() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test327");
        org.apache.commons.codec.binary.Base64.Builder builder0 = new org.apache.commons.codec.binary.Base64.Builder();
        java.io.InputStream inputStream1 = null;
        org.apache.commons.codec.binary.Base16InputStream base16InputStream2 = new org.apache.commons.codec.binary.Base16InputStream(inputStream1);
        byte[] byteArray11 = new byte[] { (byte) 10, (byte) 0, (byte) -1, (byte) 0, (byte) 1, (byte) 100 };
        java.lang.String str12 = org.apache.commons.codec.digest.Md5Crypt.md5Crypt(byteArray11);
        org.apache.commons.codec.CodecPolicy codecPolicy13 = null;
        org.apache.commons.codec.binary.Base64InputStream base64InputStream14 = new org.apache.commons.codec.binary.Base64InputStream((java.io.InputStream) base16InputStream2, false, (int) 'a', byteArray11, codecPolicy13);
        org.apache.commons.codec.binary.Base64.Builder builder15 = builder0.setDecodeTable(byteArray11);
        org.apache.commons.codec.binary.Base64.Builder builder16 = new org.apache.commons.codec.binary.Base64.Builder();
        java.io.InputStream inputStream17 = null;
        org.apache.commons.codec.binary.Base16InputStream base16InputStream18 = new org.apache.commons.codec.binary.Base16InputStream(inputStream17);
        byte[] byteArray27 = new byte[] { (byte) 10, (byte) 0, (byte) -1, (byte) 0, (byte) 1, (byte) 100 };
        java.lang.String str28 = org.apache.commons.codec.digest.Md5Crypt.md5Crypt(byteArray27);
        org.apache.commons.codec.CodecPolicy codecPolicy29 = null;
        org.apache.commons.codec.binary.Base64InputStream base64InputStream30 = new org.apache.commons.codec.binary.Base64InputStream((java.io.InputStream) base16InputStream18, false, (int) 'a', byteArray27, codecPolicy29);
        org.apache.commons.codec.binary.Base64.Builder builder31 = builder16.setDecodeTable(byteArray27);
        byte[] byteArray32 = org.apache.commons.codec.binary.Base64.decodeBase64UrlSafe(byteArray27);
        org.apache.commons.codec.binary.Base64.Builder builder33 = builder15.setDecodeTable(byteArray27);
        org.apache.commons.codec.binary.Base64.Builder builder35 = builder15.setPadding((byte) 1);
        org.apache.commons.codec.binary.Base64 base64_36 = builder15.get();
        org.junit.Assert.assertNotNull(byteArray11);
        org.junit.Assert.assertArrayEquals(byteArray11, new byte[] { (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0 });
// flaky "46) test327(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str12 + "' != '" + "$1$1UMyDKZy$zYHj0omtLmiLR.K2p/AU0." + "'", str12, "$1$1UMyDKZy$zYHj0omtLmiLR.K2p/AU0.");
        org.junit.Assert.assertNotNull(builder15);
        org.junit.Assert.assertNotNull(byteArray27);
        org.junit.Assert.assertArrayEquals(byteArray27, new byte[] { (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0 });
// flaky "7) test327(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str28 + "' != '" + "$1$YbA2pxfr$dWRu4c7pbcYBhHZvgjRNL/" + "'", str28, "$1$YbA2pxfr$dWRu4c7pbcYBhHZvgjRNL/");
        org.junit.Assert.assertNotNull(builder31);
        org.junit.Assert.assertNotNull(byteArray32);
        org.junit.Assert.assertArrayEquals(byteArray32, new byte[] {});
        org.junit.Assert.assertNotNull(builder33);
        org.junit.Assert.assertNotNull(builder35);
        org.junit.Assert.assertNotNull(base64_36);
    }

    @Test
    public void test328() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test328");
        org.apache.commons.codec.language.bm.Languages.LanguageSet languageSet0 = org.apache.commons.codec.language.bm.Languages.NO_LANGUAGES;
        org.apache.commons.codec.language.bm.Languages.LanguageSet languageSet1 = org.apache.commons.codec.language.bm.Languages.NO_LANGUAGES;
        boolean boolean3 = languageSet1.contains("Crc16 [init=0x0000, crc=0x0000, xorOut=0x0000, crc^xorOut=0x0000]");
        org.apache.commons.codec.language.bm.Languages.LanguageSet languageSet4 = languageSet0.restrictTo(languageSet1);
        org.junit.Assert.assertNotNull(languageSet0);
        org.junit.Assert.assertNotNull(languageSet1);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
        org.junit.Assert.assertNotNull(languageSet4);
    }

    @Test
    public void test329() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test329");
        org.apache.commons.codec.binary.Base16 base16_1 = new org.apache.commons.codec.binary.Base16(true);
    }

    @Test
    public void test330() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test330");
        org.apache.commons.codec.net.BCodec bCodec0 = new org.apache.commons.codec.net.BCodec();
        java.lang.String str1 = bCodec0.getDefaultCharset();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str3 = bCodec0.decode("=?UTF-8?Q?$1$gUdz5yQC$LzF0PPk8FItQxPDDbufAf.?=");
            org.junit.Assert.fail("Expected exception of type org.apache.commons.codec.DecoderException; message: This codec cannot decode Q encoded content");
        } catch (org.apache.commons.codec.DecoderException e) {
            // Expected exception.
        }
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "UTF-8" + "'", str1, "UTF-8");
    }

    @Test
    public void test331() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test331");
        org.apache.commons.codec.binary.Base32 base32_0 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray3 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str4 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray3);
        boolean boolean6 = base32_0.isInAlphabet(byteArray3, false);
        byte[] byteArray9 = org.apache.commons.codec.digest.HmacUtils.hmacSha512("310354661a5962d5b8cb76032d5a97e8aed7cf9f", "");
        byte[] byteArray10 = base32_0.encode(byteArray9);
        org.apache.commons.codec.digest.Blake3 blake3_11 = org.apache.commons.codec.digest.Blake3.initKeyDerivationFunction(byteArray10);
        javax.crypto.Mac mac12 = org.apache.commons.codec.digest.HmacUtils.getHmacSha256(byteArray10);
        java.lang.String str13 = org.apache.commons.codec.digest.DigestUtils.sha1Hex(byteArray10);
        java.lang.String str15 = org.apache.commons.codec.binary.Hex.encodeHexString(byteArray10, true);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str16 = org.apache.commons.codec.digest.DigestUtils.sha3_384Hex(byteArray10);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: java.security.NoSuchAlgorithmException: SHA3-384 MessageDigest not available");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertArrayEquals(byteArray3, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "\000" + "'", str4, "\000");
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNotNull(byteArray9);
        org.junit.Assert.assertNotNull(byteArray10);
        org.junit.Assert.assertNotNull(blake3_11);
        org.junit.Assert.assertNotNull(mac12);
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "cf7c649cdd8f45951ef6458cc0e075b3b774e813" + "'", str13, "cf7c649cdd8f45951ef6458cc0e075b3b774e813");
        org.junit.Assert.assertEquals("'" + str15 + "' != '" + "33555047454455505535525041474247544c324248333737524a59504f4e464e32434347323642555254534f5442424f5943344349454f533549424c364a494f41584e5832534635443653485a51343233585758444b4e35544e54344553424e364e4b563341513d" + "'", str15, "33555047454455505535525041474247544c324248333737524a59504f4e464e32434347323642555254534f5442424f5943344349454f533549424c364a494f41584e5832534635443653485a51343233585758444b4e35544e54344553424e364e4b563341513d");
    }

    @Test
    public void test332() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test332");
        java.nio.charset.Charset charset0 = null;
        org.apache.commons.codec.net.QuotedPrintableCodec quotedPrintableCodec2 = new org.apache.commons.codec.net.QuotedPrintableCodec(charset0, true);
        byte[] byteArray5 = org.apache.commons.codec.digest.HmacUtils.hmacSha384("\000", "SHA-512");
        java.lang.Object obj6 = quotedPrintableCodec2.encode((java.lang.Object) byteArray5);
        org.junit.Assert.assertNotNull(byteArray5);
        org.junit.Assert.assertNotNull(obj6);
    }

    @Test
    public void test333() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test333");
        org.apache.commons.codec.language.bm.Rule.PhonemeExpr phonemeExpr3 = null;
        org.apache.commons.codec.language.bm.Rule rule4 = new org.apache.commons.codec.language.bm.Rule("1489f923c4dca729178b3e3233458550d8dddf29", "SHA-1", "AA11111111", phonemeExpr3);
        org.apache.commons.codec.language.bm.Rule.RPattern rPattern5 = rule4.getRContext();
        org.apache.commons.codec.language.bm.Rule.RPattern rPattern6 = rule4.getLContext();
        org.junit.Assert.assertNotNull(rPattern5);
        org.junit.Assert.assertNotNull(rPattern6);
    }

    @Test
    public void test334() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test334");
        org.apache.commons.codec.binary.Base32 base32_0 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray3 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str4 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray3);
        boolean boolean6 = base32_0.isInAlphabet(byteArray3, false);
        org.apache.commons.codec.binary.Base32 base32_7 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray10 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str11 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray10);
        boolean boolean13 = base32_7.isInAlphabet(byteArray10, false);
        java.lang.String str14 = org.apache.commons.codec.digest.UnixCrypt.crypt(byteArray10);
        java.lang.String str15 = org.apache.commons.codec.digest.HmacUtils.hmacSha1Hex(byteArray3, byteArray10);
        java.lang.String str16 = org.apache.commons.codec.binary.BinaryCodec.toAsciiString(byteArray10);
        byte[] byteArray17 = org.apache.commons.codec.binary.Base64.encodeBase64Chunked(byteArray10);
        // The following exception was thrown during execution in test generation
        try {
            int int21 = org.apache.commons.codec.digest.MurmurHash3.hash32(byteArray10, (-559363818), (int) (byte) 10, 1035661600);
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: -559363818");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertArrayEquals(byteArray3, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "\000" + "'", str4, "\000");
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNotNull(byteArray10);
        org.junit.Assert.assertArrayEquals(byteArray10, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "\000" + "'", str11, "\000");
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
// flaky "47) test334(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str14 + "' != '" + "h4V99TZaVFEkk" + "'", str14, "h4V99TZaVFEkk");
        org.junit.Assert.assertEquals("'" + str15 + "' != '" + "310354661a5962d5b8cb76032d5a97e8aed7cf9f" + "'", str15, "310354661a5962d5b8cb76032d5a97e8aed7cf9f");
        org.junit.Assert.assertEquals("'" + str16 + "' != '" + "0000000000000000" + "'", str16, "0000000000000000");
        org.junit.Assert.assertNotNull(byteArray17);
        org.junit.Assert.assertArrayEquals(byteArray17, new byte[] { (byte) 65, (byte) 65, (byte) 65, (byte) 61, (byte) 13, (byte) 10 });
    }

    @Test
    public void test335() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test335");
        java.security.MessageDigest messageDigest0 = org.apache.commons.codec.digest.DigestUtils.getShaDigest();
        org.apache.commons.codec.digest.DigestUtils digestUtils1 = new org.apache.commons.codec.digest.DigestUtils(messageDigest0);
        char[] charArray3 = null;
        byte[] byteArray4 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray3);
        org.apache.commons.codec.binary.Base64 base64_5 = new org.apache.commons.codec.binary.Base64((int) '4', byteArray4);
        byte[] byteArray6 = digestUtils1.digest(byteArray4);
        java.lang.String str8 = digestUtils1.digestAsHex("12aa18a20782dbaf5cd723a5bf73299602957deb");
        org.junit.Assert.assertNotNull(messageDigest0);
        org.junit.Assert.assertEquals(messageDigest0.toString(), "SHA-1 Message Digest from SUN, <initialized>\r\n");
        org.junit.Assert.assertNotNull(byteArray4);
        org.junit.Assert.assertArrayEquals(byteArray4, new byte[] {});
        org.junit.Assert.assertNotNull(byteArray6);
        org.junit.Assert.assertArrayEquals(byteArray6, new byte[] { (byte) -38, (byte) 57, (byte) -93, (byte) -18, (byte) 94, (byte) 107, (byte) 75, (byte) 13, (byte) 50, (byte) 85, (byte) -65, (byte) -17, (byte) -107, (byte) 96, (byte) 24, (byte) -112, (byte) -81, (byte) -40, (byte) 7, (byte) 9 });
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "ea2673180b979cac14ac4402e0f1af5a4c44b237" + "'", str8, "ea2673180b979cac14ac4402e0f1af5a4c44b237");
    }

    @Test
    public void test336() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test336");
        java.security.MessageDigest messageDigest0 = org.apache.commons.codec.digest.DigestUtils.getShaDigest();
        org.apache.commons.codec.digest.DigestUtils digestUtils1 = new org.apache.commons.codec.digest.DigestUtils(messageDigest0);
        byte[] byteArray3 = digestUtils1.digest("8404a7262f45838faa05f26c92d87d10524773d9");
        java.lang.String str5 = digestUtils1.digestAsHex("ISO-8859-1");
        java.nio.file.Path path6 = null;
        java.nio.file.OpenOption[] openOptionArray7 = new java.nio.file.OpenOption[] {};
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str8 = digestUtils1.digestAsHex(path6, openOptionArray7);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(messageDigest0);
        org.junit.Assert.assertEquals(messageDigest0.toString(), "SHA-1 Message Digest from SUN, <initialized>\r\n");
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertArrayEquals(byteArray3, new byte[] { (byte) 18, (byte) -86, (byte) 24, (byte) -94, (byte) 7, (byte) -126, (byte) -37, (byte) -81, (byte) 92, (byte) -41, (byte) 35, (byte) -91, (byte) -65, (byte) 115, (byte) 41, (byte) -106, (byte) 2, (byte) -107, (byte) 125, (byte) -21 });
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "e4d773805cf8a24c47b008dd036ad6936efd068b" + "'", str5, "e4d773805cf8a24c47b008dd036ad6936efd068b");
        org.junit.Assert.assertNotNull(openOptionArray7);
        org.junit.Assert.assertArrayEquals(openOptionArray7, new java.nio.file.OpenOption[] {});
    }

    @Test
    public void test337() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test337");
        java.io.OutputStream outputStream0 = null;
        org.apache.commons.codec.binary.Base64OutputStream base64OutputStream1 = new org.apache.commons.codec.binary.Base64OutputStream(outputStream0);
        org.apache.commons.codec.binary.Base32 base32_4 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray7 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str8 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray7);
        boolean boolean10 = base32_4.isInAlphabet(byteArray7, false);
        org.apache.commons.codec.binary.Base32 base32_11 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray14 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str15 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray14);
        boolean boolean17 = base32_11.isInAlphabet(byteArray14, false);
        byte[] byteArray18 = org.apache.commons.codec.digest.HmacUtils.hmacMd5(byteArray7, byteArray14);
        org.apache.commons.codec.binary.Base32OutputStream base32OutputStream19 = new org.apache.commons.codec.binary.Base32OutputStream((java.io.OutputStream) base64OutputStream1, false, 1, byteArray14);
        java.lang.String str20 = org.apache.commons.codec.binary.StringUtils.newStringUsAscii(byteArray14);
        org.junit.Assert.assertNotNull(byteArray7);
        org.junit.Assert.assertArrayEquals(byteArray7, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "\000" + "'", str8, "\000");
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        org.junit.Assert.assertNotNull(byteArray14);
        org.junit.Assert.assertArrayEquals(byteArray14, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str15 + "' != '" + "\000" + "'", str15, "\000");
        org.junit.Assert.assertTrue("'" + boolean17 + "' != '" + false + "'", boolean17 == false);
        org.junit.Assert.assertNotNull(byteArray18);
        org.junit.Assert.assertArrayEquals(byteArray18, new byte[] { (byte) -53, (byte) -62, (byte) 109, (byte) 108, (byte) 16, (byte) 34, (byte) -87, (byte) -93, (byte) 59, (byte) 95, (byte) 90, (byte) 76, (byte) 18, (byte) -71, (byte) -99, (byte) -51 });
        org.junit.Assert.assertEquals("'" + str20 + "' != '" + "\000\000" + "'", str20, "\000\000");
    }

    @Test
    public void test338() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test338");
        java.io.InputStream inputStream0 = null;
        org.apache.commons.codec.binary.Base16InputStream base16InputStream1 = new org.apache.commons.codec.binary.Base16InputStream(inputStream0);
        char[] charArray4 = null;
        byte[] byteArray5 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray4);
        char[] charArray6 = org.apache.commons.codec.binary.Hex.encodeHex(byteArray5);
        byte[] byteArray7 = org.apache.commons.codec.binary.Hex.decodeHex(charArray6);
        org.apache.commons.codec.CodecPolicy codecPolicy8 = null;
        org.apache.commons.codec.binary.Base64InputStream base64InputStream9 = new org.apache.commons.codec.binary.Base64InputStream((java.io.InputStream) base16InputStream1, true, (int) (short) 100, byteArray7, codecPolicy8);
        int int10 = base64InputStream9.available();
        org.apache.commons.codec.binary.Base16InputStream base16InputStream11 = new org.apache.commons.codec.binary.Base16InputStream((java.io.InputStream) base64InputStream9);
        // The following exception was thrown during execution in test generation
        try {
            long long13 = base16InputStream11.skip((long) (-913662660));
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Negative skip length: -913662660");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray5);
        org.junit.Assert.assertArrayEquals(byteArray5, new byte[] {});
        org.junit.Assert.assertNotNull(charArray6);
        org.junit.Assert.assertArrayEquals(charArray6, new char[] {});
        org.junit.Assert.assertNotNull(byteArray7);
        org.junit.Assert.assertArrayEquals(byteArray7, new byte[] {});
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 1 + "'", int10 == 1);
    }

    @Test
    public void test339() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test339");
        byte[] byteArray2 = org.apache.commons.codec.digest.HmacUtils.hmacSha512("310354661a5962d5b8cb76032d5a97e8aed7cf9f", "");
        byte[] byteArray4 = org.apache.commons.codec.binary.StringUtils.getBytesIso8859_1("c39394a96ebd177a239a817fe8453ab0");
        java.lang.String str5 = org.apache.commons.codec.digest.DigestUtils.sha384Hex(byteArray4);
        java.lang.String str6 = org.apache.commons.codec.digest.HmacUtils.hmacSha256Hex(byteArray2, byteArray4);
        org.apache.commons.codec.binary.Base32InputStream.Builder builder7 = new org.apache.commons.codec.binary.Base32InputStream.Builder();
        java.io.InputStream inputStream8 = null;
        org.apache.commons.codec.binary.Base16InputStream base16InputStream9 = new org.apache.commons.codec.binary.Base16InputStream(inputStream8);
        byte[] byteArray18 = new byte[] { (byte) 10, (byte) 0, (byte) -1, (byte) 0, (byte) 1, (byte) 100 };
        java.lang.String str19 = org.apache.commons.codec.digest.Md5Crypt.md5Crypt(byteArray18);
        org.apache.commons.codec.CodecPolicy codecPolicy20 = null;
        org.apache.commons.codec.binary.Base64InputStream base64InputStream21 = new org.apache.commons.codec.binary.Base64InputStream((java.io.InputStream) base16InputStream9, false, (int) 'a', byteArray18, codecPolicy20);
        org.apache.commons.codec.binary.Base32InputStream.Builder builder22 = builder7.setInputStream((java.io.InputStream) base64InputStream21);
        // The following exception was thrown during execution in test generation
        try {
            byte[] byteArray23 = org.apache.commons.codec.digest.HmacUtils.hmacSha384(byteArray2, (java.io.InputStream) base64InputStream21);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray2);
        org.junit.Assert.assertNotNull(byteArray4);
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "e8d8123bcec18f5e308b0e5219338abba38bdeef310c42c2503cbd593fcf1fae3dfd1d38d64c108f9f299fde729e5f13" + "'", str5, "e8d8123bcec18f5e308b0e5219338abba38bdeef310c42c2503cbd593fcf1fae3dfd1d38d64c108f9f299fde729e5f13");
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "99d492b0a5a364dfecb159f2b1d1a3355d9269ddac27f53a77dc398c8663c1de" + "'", str6, "99d492b0a5a364dfecb159f2b1d1a3355d9269ddac27f53a77dc398c8663c1de");
        org.junit.Assert.assertNotNull(byteArray18);
        org.junit.Assert.assertArrayEquals(byteArray18, new byte[] { (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0 });
// flaky "48) test339(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str19 + "' != '" + "$1$b/UR9iXY$U8h5755XA.2I/tqEaW2T41" + "'", str19, "$1$b/UR9iXY$U8h5755XA.2I/tqEaW2T41");
        org.junit.Assert.assertNotNull(builder22);
    }

    @Test
    public void test340() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test340");
        java.io.InputStream inputStream0 = null;
        org.apache.commons.codec.binary.Base16InputStream base16InputStream1 = new org.apache.commons.codec.binary.Base16InputStream(inputStream0);
        char[] charArray4 = null;
        byte[] byteArray5 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray4);
        char[] charArray6 = org.apache.commons.codec.binary.Hex.encodeHex(byteArray5);
        byte[] byteArray7 = org.apache.commons.codec.binary.Hex.decodeHex(charArray6);
        org.apache.commons.codec.CodecPolicy codecPolicy8 = null;
        org.apache.commons.codec.binary.Base64InputStream base64InputStream9 = new org.apache.commons.codec.binary.Base64InputStream((java.io.InputStream) base16InputStream1, true, (int) (short) 100, byteArray7, codecPolicy8);
        int int10 = base64InputStream9.available();
        org.apache.commons.codec.binary.Base16InputStream base16InputStream11 = new org.apache.commons.codec.binary.Base16InputStream((java.io.InputStream) base64InputStream9);
        boolean boolean12 = base16InputStream11.markSupported();
        org.junit.Assert.assertNotNull(byteArray5);
        org.junit.Assert.assertArrayEquals(byteArray5, new byte[] {});
        org.junit.Assert.assertNotNull(charArray6);
        org.junit.Assert.assertArrayEquals(charArray6, new char[] {});
        org.junit.Assert.assertNotNull(byteArray7);
        org.junit.Assert.assertArrayEquals(byteArray7, new byte[] {});
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 1 + "'", int10 == 1);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
    }

    @Test
    public void test341() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test341");
        java.security.MessageDigest messageDigest1 = org.apache.commons.codec.digest.DigestUtils.getDigest("MD5");
        org.apache.commons.codec.net.URLCodec uRLCodec3 = new org.apache.commons.codec.net.URLCodec("PCqE1NnZAFIA2");
        org.apache.commons.codec.binary.Base32 base32_4 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray7 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str8 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray7);
        boolean boolean10 = base32_4.isInAlphabet(byteArray7, false);
        org.apache.commons.codec.binary.Base32 base32_11 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray14 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str15 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray14);
        boolean boolean17 = base32_11.isInAlphabet(byteArray14, false);
        java.lang.String str18 = org.apache.commons.codec.digest.UnixCrypt.crypt(byteArray14);
        java.lang.String str19 = org.apache.commons.codec.digest.HmacUtils.hmacSha1Hex(byteArray7, byteArray14);
        byte[] byteArray20 = uRLCodec3.decode(byteArray7);
        byte[] byteArray21 = org.apache.commons.codec.digest.DigestUtils.digest(messageDigest1, byteArray7);
        byte[] byteArray23 = org.apache.commons.codec.binary.StringUtils.getBytesUsAscii("ISO-8859-1");
        byte[] byteArray24 = org.apache.commons.codec.digest.DigestUtils.digest(messageDigest1, byteArray23);
        org.junit.Assert.assertNotNull(messageDigest1);
        org.junit.Assert.assertEquals(messageDigest1.toString(), "MD5 Message Digest from SUN, <initialized>\r\n");
        org.junit.Assert.assertNotNull(byteArray7);
        org.junit.Assert.assertArrayEquals(byteArray7, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "\000" + "'", str8, "\000");
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        org.junit.Assert.assertNotNull(byteArray14);
        org.junit.Assert.assertArrayEquals(byteArray14, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str15 + "' != '" + "\000" + "'", str15, "\000");
        org.junit.Assert.assertTrue("'" + boolean17 + "' != '" + false + "'", boolean17 == false);
// flaky "49) test341(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str18 + "' != '" + "fOLvLwTUZovHA" + "'", str18, "fOLvLwTUZovHA");
        org.junit.Assert.assertEquals("'" + str19 + "' != '" + "310354661a5962d5b8cb76032d5a97e8aed7cf9f" + "'", str19, "310354661a5962d5b8cb76032d5a97e8aed7cf9f");
        org.junit.Assert.assertNotNull(byteArray20);
        org.junit.Assert.assertArrayEquals(byteArray20, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertNotNull(byteArray21);
        org.junit.Assert.assertArrayEquals(byteArray21, new byte[] { (byte) -60, (byte) 16, (byte) 63, (byte) 18, (byte) 45, (byte) 39, (byte) 103, (byte) 124, (byte) -99, (byte) -79, (byte) 68, (byte) -54, (byte) -31, (byte) 57, (byte) 74, (byte) 102 });
        org.junit.Assert.assertNotNull(byteArray23);
        org.junit.Assert.assertArrayEquals(byteArray23, new byte[] { (byte) 73, (byte) 83, (byte) 79, (byte) 45, (byte) 56, (byte) 56, (byte) 53, (byte) 57, (byte) 45, (byte) 49 });
        org.junit.Assert.assertNotNull(byteArray24);
        org.junit.Assert.assertArrayEquals(byteArray24, new byte[] { (byte) 14, (byte) 75, (byte) -76, (byte) -17, (byte) -61, (byte) 14, (byte) -63, (byte) 41, (byte) -27, (byte) 0, (byte) 58, (byte) 24, (byte) -22, (byte) 33, (byte) 3, (byte) -49 });
    }

    @Test
    public void test342() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test342");
        org.apache.commons.codec.binary.Base32 base32_0 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray3 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str4 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray3);
        boolean boolean6 = base32_0.isInAlphabet(byteArray3, false);
        char[] charArray7 = null;
        byte[] byteArray8 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray7);
        boolean boolean10 = base32_0.isInAlphabet(byteArray8, true);
        byte[] byteArray11 = org.apache.commons.codec.digest.DigestUtils.sha1(byteArray8);
        long long12 = org.apache.commons.codec.digest.MurmurHash3.hash64(byteArray8);
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertArrayEquals(byteArray3, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "\000" + "'", str4, "\000");
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNotNull(byteArray8);
        org.junit.Assert.assertArrayEquals(byteArray8, new byte[] {});
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
        org.junit.Assert.assertNotNull(byteArray11);
        org.junit.Assert.assertArrayEquals(byteArray11, new byte[] { (byte) -38, (byte) 57, (byte) -93, (byte) -18, (byte) 94, (byte) 107, (byte) 75, (byte) 13, (byte) 50, (byte) 85, (byte) -65, (byte) -17, (byte) -107, (byte) 96, (byte) 24, (byte) -112, (byte) -81, (byte) -40, (byte) 7, (byte) 9 });
        org.junit.Assert.assertTrue("'" + long12 + "' != '" + 8404154273843829576L + "'", long12 == 8404154273843829576L);
    }

    @Test
    public void test343() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test343");
        org.apache.commons.codec.language.bm.Languages.LanguageSet languageSet1 = null;
        org.apache.commons.codec.language.bm.Rule.Phoneme phoneme2 = new org.apache.commons.codec.language.bm.Rule.Phoneme((java.lang.CharSequence) "0000000000000000", languageSet1);
        org.apache.commons.codec.language.bm.Languages.LanguageSet languageSet3 = phoneme2.getLanguages();
        int int4 = phoneme2.size();
        int int5 = phoneme2.size();
        org.junit.Assert.assertNull(languageSet3);
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 1 + "'", int4 == 1);
        org.junit.Assert.assertTrue("'" + int5 + "' != '" + 1 + "'", int5 == 1);
    }

    @Test
    public void test344() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test344");
        java.io.OutputStream outputStream0 = null;
        org.apache.commons.codec.binary.Base64OutputStream base64OutputStream1 = new org.apache.commons.codec.binary.Base64OutputStream(outputStream0);
        base64OutputStream1.eof();
        org.apache.commons.codec.binary.Base32OutputStream base32OutputStream4 = new org.apache.commons.codec.binary.Base32OutputStream((java.io.OutputStream) base64OutputStream1, true);
        char[] charArray7 = null;
        byte[] byteArray8 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray7);
        org.apache.commons.codec.CodecPolicy codecPolicy10 = null;
        org.apache.commons.codec.binary.Base16 base16_11 = new org.apache.commons.codec.binary.Base16(true, codecPolicy10);
        boolean boolean13 = base16_11.isInAlphabet((byte) -1);
        org.apache.commons.codec.CodecPolicy codecPolicy14 = base16_11.getCodecPolicy();
        org.apache.commons.codec.binary.Base64OutputStream base64OutputStream15 = new org.apache.commons.codec.binary.Base64OutputStream((java.io.OutputStream) base32OutputStream4, true, (int) '#', byteArray8, codecPolicy14);
        org.apache.commons.codec.binary.Base16OutputStream base16OutputStream16 = new org.apache.commons.codec.binary.Base16OutputStream((java.io.OutputStream) base64OutputStream15);
        org.junit.Assert.assertNotNull(byteArray8);
        org.junit.Assert.assertArrayEquals(byteArray8, new byte[] {});
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
        org.junit.Assert.assertTrue("'" + codecPolicy14 + "' != '" + org.apache.commons.codec.CodecPolicy.LENIENT + "'", codecPolicy14.equals(org.apache.commons.codec.CodecPolicy.LENIENT));
    }

    @Test
    public void test345() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test345");
        org.apache.commons.codec.binary.Base32InputStream.Builder builder0 = new org.apache.commons.codec.binary.Base32InputStream.Builder();
        java.io.InputStream inputStream1 = null;
        org.apache.commons.codec.binary.Base16InputStream base16InputStream2 = new org.apache.commons.codec.binary.Base16InputStream(inputStream1);
        byte[] byteArray11 = new byte[] { (byte) 10, (byte) 0, (byte) -1, (byte) 0, (byte) 1, (byte) 100 };
        java.lang.String str12 = org.apache.commons.codec.digest.Md5Crypt.md5Crypt(byteArray11);
        org.apache.commons.codec.CodecPolicy codecPolicy13 = null;
        org.apache.commons.codec.binary.Base64InputStream base64InputStream14 = new org.apache.commons.codec.binary.Base64InputStream((java.io.InputStream) base16InputStream2, false, (int) 'a', byteArray11, codecPolicy13);
        org.apache.commons.codec.binary.Base32InputStream.Builder builder15 = builder0.setInputStream((java.io.InputStream) base64InputStream14);
        base64InputStream14.mark((int) (short) 10);
        boolean boolean18 = base64InputStream14.isStrictDecoding();
        // The following exception was thrown during execution in test generation
        try {
            byte[] byteArray19 = org.apache.commons.codec.digest.DigestUtils.sha3_256((java.io.InputStream) base64InputStream14);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: java.security.NoSuchAlgorithmException: SHA3-256 MessageDigest not available");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray11);
        org.junit.Assert.assertArrayEquals(byteArray11, new byte[] { (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0 });
// flaky "50) test345(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str12 + "' != '" + "$1$Y0JqQ7s3$/V8TCaNDHKHBBadeDkdMh1" + "'", str12, "$1$Y0JqQ7s3$/V8TCaNDHKHBBadeDkdMh1");
        org.junit.Assert.assertNotNull(builder15);
        org.junit.Assert.assertTrue("'" + boolean18 + "' != '" + false + "'", boolean18 == false);
    }

    @Test
    public void test346() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test346");
        org.apache.commons.codec.binary.Base32 base32_0 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray3 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str4 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray3);
        boolean boolean6 = base32_0.isInAlphabet(byteArray3, false);
        org.apache.commons.codec.binary.Base32 base32_7 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray10 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str11 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray10);
        boolean boolean13 = base32_7.isInAlphabet(byteArray10, false);
        java.lang.String str14 = org.apache.commons.codec.digest.UnixCrypt.crypt(byteArray10);
        java.lang.String str15 = org.apache.commons.codec.digest.HmacUtils.hmacSha1Hex(byteArray3, byteArray10);
        java.lang.String str16 = org.apache.commons.codec.digest.DigestUtils.shaHex(byteArray10);
        java.lang.String str18 = org.apache.commons.codec.digest.Crypt.crypt(byteArray10, "8404a7262f45838faa05f26c92d87d10524773d9");
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertArrayEquals(byteArray3, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "\000" + "'", str4, "\000");
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNotNull(byteArray10);
        org.junit.Assert.assertArrayEquals(byteArray10, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "\000" + "'", str11, "\000");
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
// flaky "51) test346(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str14 + "' != '" + "KoFFaeBwyIBRM" + "'", str14, "KoFFaeBwyIBRM");
        org.junit.Assert.assertEquals("'" + str15 + "' != '" + "310354661a5962d5b8cb76032d5a97e8aed7cf9f" + "'", str15, "310354661a5962d5b8cb76032d5a97e8aed7cf9f");
        org.junit.Assert.assertEquals("'" + str16 + "' != '" + "1489f923c4dca729178b3e3233458550d8dddf29" + "'", str16, "1489f923c4dca729178b3e3233458550d8dddf29");
        org.junit.Assert.assertEquals("'" + str18 + "' != '" + "849/k2zW95mmQ" + "'", str18, "849/k2zW95mmQ");
    }

    @Test
    public void test347() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test347");
        java.nio.charset.Charset charset0 = org.apache.commons.codec.Charsets.UTF_16;
        org.junit.Assert.assertNotNull(charset0);
    }

    @Test
    public void test348() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test348");
        org.apache.commons.codec.binary.Hex hex0 = new org.apache.commons.codec.binary.Hex();
        java.lang.String str1 = hex0.getCharsetName();
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "UTF-8" + "'", str1, "UTF-8");
    }

    @Test
    public void test349() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test349");
        org.apache.commons.codec.binary.Base16OutputStream.Builder builder0 = new org.apache.commons.codec.binary.Base16OutputStream.Builder();
        org.apache.commons.codec.binary.Base16OutputStream base16OutputStream1 = builder0.get();
        org.junit.Assert.assertNotNull(base16OutputStream1);
    }

    @Test
    public void test350() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test350");
        org.apache.commons.codec.binary.Base32 base32_0 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray3 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str4 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray3);
        boolean boolean6 = base32_0.isInAlphabet(byteArray3, false);
        org.apache.commons.codec.binary.Base32 base32_7 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray10 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str11 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray10);
        boolean boolean13 = base32_7.isInAlphabet(byteArray10, false);
        java.lang.String str14 = org.apache.commons.codec.digest.UnixCrypt.crypt(byteArray10);
        java.lang.String str15 = org.apache.commons.codec.digest.HmacUtils.hmacSha1Hex(byteArray3, byteArray10);
        java.lang.String str16 = org.apache.commons.codec.binary.BinaryCodec.toAsciiString(byteArray10);
        byte[] byteArray17 = org.apache.commons.codec.binary.Base64.encodeBase64Chunked(byteArray10);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str19 = org.apache.commons.codec.digest.UnixCrypt.crypt(byteArray17, "=?UTF-8?B?JDEkZ1VkejV5UUMkTHpGMFBQazhGSXRReFBERGJ1ZkFmLg==?=");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Invalid salt value: =?UTF-8?B?JDEkZ1VkejV5UUMkTHpGMFBQazhGSXRReFBERGJ1ZkFmLg==?=");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertArrayEquals(byteArray3, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "\000" + "'", str4, "\000");
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNotNull(byteArray10);
        org.junit.Assert.assertArrayEquals(byteArray10, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "\000" + "'", str11, "\000");
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
// flaky "52) test350(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str14 + "' != '" + "60o6R5kJuA6Yo" + "'", str14, "60o6R5kJuA6Yo");
        org.junit.Assert.assertEquals("'" + str15 + "' != '" + "310354661a5962d5b8cb76032d5a97e8aed7cf9f" + "'", str15, "310354661a5962d5b8cb76032d5a97e8aed7cf9f");
        org.junit.Assert.assertEquals("'" + str16 + "' != '" + "0000000000000000" + "'", str16, "0000000000000000");
        org.junit.Assert.assertNotNull(byteArray17);
        org.junit.Assert.assertArrayEquals(byteArray17, new byte[] { (byte) 65, (byte) 65, (byte) 65, (byte) 61, (byte) 13, (byte) 10 });
    }

    @Test
    public void test351() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test351");
        org.apache.commons.codec.language.DaitchMokotoffSoundex daitchMokotoffSoundex0 = new org.apache.commons.codec.language.DaitchMokotoffSoundex();
        java.lang.String str2 = daitchMokotoffSoundex0.soundex("WGJU3Y88YVchU");
        org.junit.Assert.assertEquals("'" + str2 + "' != '" + "757400|757500|754740|754750" + "'", str2, "757400|757500|754740|754750");
    }

    @Test
    public void test352() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test352");
        java.lang.String str1 = org.apache.commons.codec.digest.UnixCrypt.crypt("$6$1vzqoCiJ$A212BzF35c0jDi1KwWmzemA.TsJnhreCMH0g9.3jYzK.cRdMF8RWejAlfAruffnPQAyJBAM5aXR98OA.q7jrV.");
// flaky "53) test352(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str1 + "' != '" + "ksdk7Mr8.Ex6o" + "'", str1, "ksdk7Mr8.Ex6o");
    }

    @Test
    public void test353() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test353");
        org.apache.commons.codec.binary.Base32 base32_0 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray3 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str4 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray3);
        boolean boolean6 = base32_0.isInAlphabet(byteArray3, false);
        org.apache.commons.codec.binary.Base32 base32_7 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray10 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str11 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray10);
        boolean boolean13 = base32_7.isInAlphabet(byteArray10, false);
        org.apache.commons.codec.binary.Base32 base32_14 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray17 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str18 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray17);
        boolean boolean20 = base32_14.isInAlphabet(byteArray17, false);
        byte[] byteArray21 = base32_7.encode(byteArray17);
        java.lang.String str22 = org.apache.commons.codec.digest.HmacUtils.hmacMd5Hex(byteArray3, byteArray21);
        java.io.InputStream inputStream23 = null;
        org.apache.commons.codec.binary.Base16InputStream base16InputStream24 = new org.apache.commons.codec.binary.Base16InputStream(inputStream23);
        char[] charArray27 = null;
        byte[] byteArray28 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray27);
        char[] charArray29 = org.apache.commons.codec.binary.Hex.encodeHex(byteArray28);
        byte[] byteArray30 = org.apache.commons.codec.binary.Hex.decodeHex(charArray29);
        org.apache.commons.codec.CodecPolicy codecPolicy31 = null;
        org.apache.commons.codec.binary.Base64InputStream base64InputStream32 = new org.apache.commons.codec.binary.Base64InputStream((java.io.InputStream) base16InputStream24, true, (int) (short) 100, byteArray30, codecPolicy31);
        // The following exception was thrown during execution in test generation
        try {
            byte[] byteArray33 = org.apache.commons.codec.digest.HmacUtils.hmacSha256(byteArray3, (java.io.InputStream) base16InputStream24);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertArrayEquals(byteArray3, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "\000" + "'", str4, "\000");
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNotNull(byteArray10);
        org.junit.Assert.assertArrayEquals(byteArray10, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "\000" + "'", str11, "\000");
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
        org.junit.Assert.assertNotNull(byteArray17);
        org.junit.Assert.assertArrayEquals(byteArray17, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str18 + "' != '" + "\000" + "'", str18, "\000");
        org.junit.Assert.assertTrue("'" + boolean20 + "' != '" + false + "'", boolean20 == false);
        org.junit.Assert.assertNotNull(byteArray21);
        org.junit.Assert.assertArrayEquals(byteArray21, new byte[] { (byte) 65, (byte) 65, (byte) 65, (byte) 65, (byte) 61, (byte) 61, (byte) 61, (byte) 61 });
        org.junit.Assert.assertEquals("'" + str22 + "' != '" + "207b9c1af4dbe7359a718262cce9251a" + "'", str22, "207b9c1af4dbe7359a718262cce9251a");
        org.junit.Assert.assertNotNull(byteArray28);
        org.junit.Assert.assertArrayEquals(byteArray28, new byte[] {});
        org.junit.Assert.assertNotNull(charArray29);
        org.junit.Assert.assertArrayEquals(charArray29, new char[] {});
        org.junit.Assert.assertNotNull(byteArray30);
        org.junit.Assert.assertArrayEquals(byteArray30, new byte[] {});
    }

    @Test
    public void test354() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test354");
        byte[] byteArray1 = org.apache.commons.codec.binary.StringUtils.getBytesUtf16("nh11rN6ZAfNOQ");
        org.junit.Assert.assertNotNull(byteArray1);
    }

    @Test
    public void test355() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test355");
        org.apache.commons.codec.language.bm.Languages.LanguageSet languageSet1 = null;
        org.apache.commons.codec.language.bm.Rule.Phoneme phoneme2 = new org.apache.commons.codec.language.bm.Rule.Phoneme((java.lang.CharSequence) "0000000000000000", languageSet1);
        org.apache.commons.codec.language.bm.Languages.LanguageSet languageSet3 = phoneme2.getLanguages();
        java.lang.Iterable<org.apache.commons.codec.language.bm.Rule.Phoneme> phonemeIterable4 = phoneme2.getPhonemes();
        org.junit.Assert.assertNull(languageSet3);
        org.junit.Assert.assertNotNull(phonemeIterable4);
    }

    @Test
    public void test356() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test356");
        org.apache.commons.codec.binary.Base64InputStream.Builder builder0 = org.apache.commons.codec.binary.Base64InputStream.builder();
        org.apache.commons.codec.binary.Base64 base64_1 = null;
        org.apache.commons.codec.binary.Base64InputStream.Builder builder2 = builder0.setBaseNCodec(base64_1);
        org.apache.commons.codec.binary.Base64InputStream.Builder builder4 = builder0.setEncode(true);
        org.apache.commons.codec.binary.Base64InputStream.Builder builder6 = builder0.setEncode(false);
        java.io.InputStream inputStream7 = null;
        org.apache.commons.codec.binary.Base16InputStream base16InputStream8 = new org.apache.commons.codec.binary.Base16InputStream(inputStream7);
        char[] charArray11 = null;
        byte[] byteArray12 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray11);
        char[] charArray13 = org.apache.commons.codec.binary.Hex.encodeHex(byteArray12);
        byte[] byteArray14 = org.apache.commons.codec.binary.Hex.decodeHex(charArray13);
        org.apache.commons.codec.CodecPolicy codecPolicy15 = null;
        org.apache.commons.codec.binary.Base64InputStream base64InputStream16 = new org.apache.commons.codec.binary.Base64InputStream((java.io.InputStream) base16InputStream8, true, (int) (short) 100, byteArray14, codecPolicy15);
        int int17 = base64InputStream16.available();
        org.apache.commons.codec.binary.Base32 base32_20 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray23 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str24 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray23);
        boolean boolean26 = base32_20.isInAlphabet(byteArray23, false);
        org.apache.commons.codec.binary.Base32 base32_27 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray30 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str31 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray30);
        boolean boolean33 = base32_27.isInAlphabet(byteArray30, false);
        java.lang.String str34 = org.apache.commons.codec.digest.UnixCrypt.crypt(byteArray30);
        java.lang.String str35 = org.apache.commons.codec.digest.HmacUtils.hmacSha1Hex(byteArray23, byteArray30);
        java.lang.String str36 = org.apache.commons.codec.binary.BinaryCodec.toAsciiString(byteArray30);
        javax.crypto.Mac mac37 = org.apache.commons.codec.digest.HmacUtils.getHmacSha384(byteArray30);
        java.lang.String str38 = org.apache.commons.codec.binary.BinaryCodec.toAsciiString(byteArray30);
        org.apache.commons.codec.CodecPolicy codecPolicy41 = null;
        org.apache.commons.codec.binary.Base16 base16_42 = new org.apache.commons.codec.binary.Base16(true, codecPolicy41);
        boolean boolean44 = base16_42.isInAlphabet((byte) -1);
        org.apache.commons.codec.CodecPolicy codecPolicy45 = base16_42.getCodecPolicy();
        org.apache.commons.codec.binary.Base16 base16_46 = new org.apache.commons.codec.binary.Base16(true, codecPolicy45);
        org.apache.commons.codec.binary.Base64InputStream base64InputStream47 = new org.apache.commons.codec.binary.Base64InputStream((java.io.InputStream) base64InputStream16, false, (int) (byte) -1, byteArray30, codecPolicy45);
        org.apache.commons.codec.binary.Base64InputStream.Builder builder48 = builder6.setInputStream((java.io.InputStream) base64InputStream16);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str49 = org.apache.commons.codec.digest.DigestUtils.sha256Hex((java.io.InputStream) base64InputStream16);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(builder2);
        org.junit.Assert.assertNotNull(builder4);
        org.junit.Assert.assertNotNull(builder6);
        org.junit.Assert.assertNotNull(byteArray12);
        org.junit.Assert.assertArrayEquals(byteArray12, new byte[] {});
        org.junit.Assert.assertNotNull(charArray13);
        org.junit.Assert.assertArrayEquals(charArray13, new char[] {});
        org.junit.Assert.assertNotNull(byteArray14);
        org.junit.Assert.assertArrayEquals(byteArray14, new byte[] {});
        org.junit.Assert.assertTrue("'" + int17 + "' != '" + 1 + "'", int17 == 1);
        org.junit.Assert.assertNotNull(byteArray23);
        org.junit.Assert.assertArrayEquals(byteArray23, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str24 + "' != '" + "\000" + "'", str24, "\000");
        org.junit.Assert.assertTrue("'" + boolean26 + "' != '" + false + "'", boolean26 == false);
        org.junit.Assert.assertNotNull(byteArray30);
        org.junit.Assert.assertArrayEquals(byteArray30, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str31 + "' != '" + "\000" + "'", str31, "\000");
        org.junit.Assert.assertTrue("'" + boolean33 + "' != '" + false + "'", boolean33 == false);
// flaky "54) test356(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str34 + "' != '" + "hHib67vcpT76Y" + "'", str34, "hHib67vcpT76Y");
        org.junit.Assert.assertEquals("'" + str35 + "' != '" + "310354661a5962d5b8cb76032d5a97e8aed7cf9f" + "'", str35, "310354661a5962d5b8cb76032d5a97e8aed7cf9f");
        org.junit.Assert.assertEquals("'" + str36 + "' != '" + "0000000000000000" + "'", str36, "0000000000000000");
        org.junit.Assert.assertNotNull(mac37);
        org.junit.Assert.assertEquals("'" + str38 + "' != '" + "0000000000000000" + "'", str38, "0000000000000000");
        org.junit.Assert.assertTrue("'" + boolean44 + "' != '" + false + "'", boolean44 == false);
        org.junit.Assert.assertTrue("'" + codecPolicy45 + "' != '" + org.apache.commons.codec.CodecPolicy.LENIENT + "'", codecPolicy45.equals(org.apache.commons.codec.CodecPolicy.LENIENT));
        org.junit.Assert.assertNotNull(builder48);
    }

    @Test
    public void test357() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test357");
        // The following exception was thrown during execution in test generation
        try {
            byte[] byteArray1 = org.apache.commons.codec.digest.DigestUtils.shake128_256("MT11111111");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: java.security.NoSuchAlgorithmException: SHAKE128-256 MessageDigest not available");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test358() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test358");
        org.apache.commons.codec.binary.Base32 base32_0 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray3 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str4 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray3);
        boolean boolean6 = base32_0.isInAlphabet(byteArray3, false);
        byte[] byteArray8 = org.apache.commons.codec.binary.StringUtils.getBytesIso8859_1("c39394a96ebd177a239a817fe8453ab0");
        org.apache.commons.codec.binary.Base32 base32_9 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray12 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str13 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray12);
        boolean boolean15 = base32_9.isInAlphabet(byteArray12, false);
        org.apache.commons.codec.binary.Base32 base32_16 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray19 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str20 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray19);
        boolean boolean22 = base32_16.isInAlphabet(byteArray19, false);
        java.lang.String str23 = org.apache.commons.codec.digest.UnixCrypt.crypt(byteArray19);
        java.lang.String str24 = org.apache.commons.codec.digest.HmacUtils.hmacSha1Hex(byteArray12, byteArray19);
        java.lang.String str25 = org.apache.commons.codec.binary.BinaryCodec.toAsciiString(byteArray19);
        java.lang.String str26 = org.apache.commons.codec.digest.HmacUtils.hmacSha1Hex(byteArray8, byteArray19);
        byte[] byteArray29 = base32_0.encode(byteArray19, (int) '#', 0);
        byte[] byteArray30 = org.apache.commons.codec.net.URLCodec.decodeUrl(byteArray29);
        java.io.InputStream inputStream31 = null;
        org.apache.commons.codec.binary.Base16InputStream base16InputStream32 = new org.apache.commons.codec.binary.Base16InputStream(inputStream31);
        base16InputStream32.mark(0);
        // The following exception was thrown during execution in test generation
        try {
            byte[] byteArray35 = org.apache.commons.codec.digest.HmacUtils.hmacSha512(byteArray29, (java.io.InputStream) base16InputStream32);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Empty key");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertArrayEquals(byteArray3, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "\000" + "'", str4, "\000");
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNotNull(byteArray8);
        org.junit.Assert.assertNotNull(byteArray12);
        org.junit.Assert.assertArrayEquals(byteArray12, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "\000" + "'", str13, "\000");
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertNotNull(byteArray19);
        org.junit.Assert.assertArrayEquals(byteArray19, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str20 + "' != '" + "\000" + "'", str20, "\000");
        org.junit.Assert.assertTrue("'" + boolean22 + "' != '" + false + "'", boolean22 == false);
// flaky "55) test358(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str23 + "' != '" + "HASyQdPwO7eIg" + "'", str23, "HASyQdPwO7eIg");
        org.junit.Assert.assertEquals("'" + str24 + "' != '" + "310354661a5962d5b8cb76032d5a97e8aed7cf9f" + "'", str24, "310354661a5962d5b8cb76032d5a97e8aed7cf9f");
        org.junit.Assert.assertEquals("'" + str25 + "' != '" + "0000000000000000" + "'", str25, "0000000000000000");
        org.junit.Assert.assertEquals("'" + str26 + "' != '" + "55351564fecb450a30b6ab9f075f0e63a5f492b6" + "'", str26, "55351564fecb450a30b6ab9f075f0e63a5f492b6");
        org.junit.Assert.assertNotNull(byteArray29);
        org.junit.Assert.assertArrayEquals(byteArray29, new byte[] {});
        org.junit.Assert.assertNotNull(byteArray30);
        org.junit.Assert.assertArrayEquals(byteArray30, new byte[] {});
    }

    @Test
    public void test359() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test359");
        org.apache.commons.codec.digest.PureJavaCrc32C pureJavaCrc32C0 = new org.apache.commons.codec.digest.PureJavaCrc32C();
        long long1 = pureJavaCrc32C0.getValue();
        org.junit.Assert.assertTrue("'" + long1 + "' != '" + 0L + "'", long1 == 0L);
    }

    @Test
    public void test360() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test360");
        org.apache.commons.codec.binary.Base32 base32_0 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray3 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str4 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray3);
        boolean boolean6 = base32_0.isInAlphabet(byteArray3, false);
        org.apache.commons.codec.binary.Base32 base32_7 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray10 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str11 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray10);
        boolean boolean13 = base32_7.isInAlphabet(byteArray10, false);
        byte[] byteArray14 = base32_0.encode(byteArray10);
        java.lang.String str15 = org.apache.commons.codec.digest.Sha2Crypt.sha512Crypt(byteArray10);
        byte[] byteArray16 = org.apache.commons.codec.binary.Base64.encodeBase64(byteArray10);
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertArrayEquals(byteArray3, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "\000" + "'", str4, "\000");
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNotNull(byteArray10);
        org.junit.Assert.assertArrayEquals(byteArray10, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "\000" + "'", str11, "\000");
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
        org.junit.Assert.assertNotNull(byteArray14);
        org.junit.Assert.assertArrayEquals(byteArray14, new byte[] { (byte) 65, (byte) 65, (byte) 65, (byte) 65, (byte) 61, (byte) 61, (byte) 61, (byte) 61 });
// flaky "56) test360(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str15 + "' != '" + "$6$ftyhpPpN$dQxIqfl72aCi8ek3qLJGIbIuNIn21RkyvmG.5xvTjNrezU60NKlkQOCOM7izfzxH.IrUI83YJJgRXPdPlZI3r/" + "'", str15, "$6$ftyhpPpN$dQxIqfl72aCi8ek3qLJGIbIuNIn21RkyvmG.5xvTjNrezU60NKlkQOCOM7izfzxH.IrUI83YJJgRXPdPlZI3r/");
        org.junit.Assert.assertNotNull(byteArray16);
        org.junit.Assert.assertArrayEquals(byteArray16, new byte[] { (byte) 65, (byte) 65, (byte) 65, (byte) 61 });
    }

    @Test
    public void test361() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test361");
        java.nio.charset.Charset charset0 = null;
        org.apache.commons.codec.net.QuotedPrintableCodec quotedPrintableCodec1 = new org.apache.commons.codec.net.QuotedPrintableCodec(charset0);
    }

    @Test
    public void test362() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test362");
        org.apache.commons.codec.digest.Crc16.Builder builder0 = new org.apache.commons.codec.digest.Crc16.Builder();
        int[] intArray1 = org.apache.commons.codec.digest.Crc16.getDnpTable();
        org.apache.commons.codec.digest.Crc16.Builder builder2 = builder0.setTable(intArray1);
        org.apache.commons.codec.digest.Crc16 crc16_3 = builder0.get();
        int[] intArray4 = org.apache.commons.codec.digest.Crc16.getMcrf4xxTable();
        org.apache.commons.codec.digest.Crc16.Builder builder5 = builder0.setTable(intArray4);
        org.junit.Assert.assertNotNull(intArray1);
        org.junit.Assert.assertNotNull(builder2);
        org.junit.Assert.assertNotNull(crc16_3);
        org.junit.Assert.assertNotNull(intArray4);
        org.junit.Assert.assertNotNull(builder5);
    }

    @Test
    public void test363() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test363");
        java.lang.String str1 = org.apache.commons.codec.digest.DigestUtils.sha512Hex("A6fKTL2blvInc");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "da13fb08f28e123196a9c326536e6f8f6037e34eb6e68dbc96cc3f67958593f9073f65fca64389228646d0b60fe610c5dc99e95a1e30c86b873f3820a7ce3c96" + "'", str1, "da13fb08f28e123196a9c326536e6f8f6037e34eb6e68dbc96cc3f67958593f9073f65fca64389228646d0b60fe610c5dc99e95a1e30c86b873f3820a7ce3c96");
    }

    @Test
    public void test364() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test364");
        java.io.InputStream inputStream0 = null;
        org.apache.commons.codec.binary.Base16InputStream base16InputStream1 = new org.apache.commons.codec.binary.Base16InputStream(inputStream0);
        base16InputStream1.mark((int) (byte) -1);
        // The following exception was thrown during execution in test generation
        try {
            base16InputStream1.reset();
            org.junit.Assert.fail("Expected exception of type java.io.IOException; message: mark/reset not supported");
        } catch (java.io.IOException e) {
            // Expected exception.
        }
    }

    @Test
    public void test365() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test365");
        java.io.OutputStream outputStream0 = null;
        org.apache.commons.codec.binary.Base64OutputStream base64OutputStream1 = new org.apache.commons.codec.binary.Base64OutputStream(outputStream0);
        org.apache.commons.codec.binary.Base32 base32_4 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray7 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str8 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray7);
        boolean boolean10 = base32_4.isInAlphabet(byteArray7, false);
        org.apache.commons.codec.binary.Base32 base32_11 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray14 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str15 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray14);
        boolean boolean17 = base32_11.isInAlphabet(byteArray14, false);
        byte[] byteArray18 = org.apache.commons.codec.digest.HmacUtils.hmacMd5(byteArray7, byteArray14);
        org.apache.commons.codec.binary.Base32OutputStream base32OutputStream19 = new org.apache.commons.codec.binary.Base32OutputStream((java.io.OutputStream) base64OutputStream1, false, 1, byteArray14);
        base64OutputStream1.eof();
        boolean boolean21 = base64OutputStream1.isStrictDecoding();
        org.junit.Assert.assertNotNull(byteArray7);
        org.junit.Assert.assertArrayEquals(byteArray7, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "\000" + "'", str8, "\000");
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        org.junit.Assert.assertNotNull(byteArray14);
        org.junit.Assert.assertArrayEquals(byteArray14, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str15 + "' != '" + "\000" + "'", str15, "\000");
        org.junit.Assert.assertTrue("'" + boolean17 + "' != '" + false + "'", boolean17 == false);
        org.junit.Assert.assertNotNull(byteArray18);
        org.junit.Assert.assertArrayEquals(byteArray18, new byte[] { (byte) -53, (byte) -62, (byte) 109, (byte) 108, (byte) 16, (byte) 34, (byte) -87, (byte) -93, (byte) 59, (byte) 95, (byte) 90, (byte) 76, (byte) 18, (byte) -71, (byte) -99, (byte) -51 });
        org.junit.Assert.assertTrue("'" + boolean21 + "' != '" + false + "'", boolean21 == false);
    }

    @Test
    public void test366() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test366");
        java.lang.Object[] objArray1 = null;
        org.apache.commons.codec.DecoderException decoderException2 = new org.apache.commons.codec.DecoderException("$6$oWTfCqM4$VIqBhSK7v23O4tnjI9G0.ySCEVf2c5vaGxRmZoxTWiiZvbzGzEkGo0Wpjyh6Ih.x1txvfCdlGUL4IodLMR6a6/", objArray1);
    }

    @Test
    public void test367() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test367");
        java.io.OutputStream outputStream0 = null;
        org.apache.commons.codec.binary.Base64OutputStream base64OutputStream1 = new org.apache.commons.codec.binary.Base64OutputStream(outputStream0);
        org.apache.commons.codec.binary.Base16OutputStream base16OutputStream3 = new org.apache.commons.codec.binary.Base16OutputStream(outputStream0, false);
        // The following exception was thrown during execution in test generation
        try {
            base16OutputStream3.close();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test368() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test368");
        java.io.InputStream inputStream0 = null;
        org.apache.commons.codec.binary.Base16InputStream base16InputStream1 = new org.apache.commons.codec.binary.Base16InputStream(inputStream0);
        char[] charArray4 = null;
        byte[] byteArray5 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray4);
        char[] charArray6 = org.apache.commons.codec.binary.Hex.encodeHex(byteArray5);
        byte[] byteArray7 = org.apache.commons.codec.binary.Hex.decodeHex(charArray6);
        org.apache.commons.codec.CodecPolicy codecPolicy8 = null;
        org.apache.commons.codec.binary.Base64InputStream base64InputStream9 = new org.apache.commons.codec.binary.Base64InputStream((java.io.InputStream) base16InputStream1, true, (int) (short) 100, byteArray7, codecPolicy8);
        org.apache.commons.codec.binary.Base32 base32_10 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray13 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str14 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray13);
        boolean boolean16 = base32_10.isInAlphabet(byteArray13, false);
        byte[] byteArray18 = org.apache.commons.codec.binary.StringUtils.getBytesIso8859_1("c39394a96ebd177a239a817fe8453ab0");
        org.apache.commons.codec.binary.Base32 base32_19 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray22 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str23 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray22);
        boolean boolean25 = base32_19.isInAlphabet(byteArray22, false);
        org.apache.commons.codec.binary.Base32 base32_26 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray29 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str30 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray29);
        boolean boolean32 = base32_26.isInAlphabet(byteArray29, false);
        java.lang.String str33 = org.apache.commons.codec.digest.UnixCrypt.crypt(byteArray29);
        java.lang.String str34 = org.apache.commons.codec.digest.HmacUtils.hmacSha1Hex(byteArray22, byteArray29);
        java.lang.String str35 = org.apache.commons.codec.binary.BinaryCodec.toAsciiString(byteArray29);
        java.lang.String str36 = org.apache.commons.codec.digest.HmacUtils.hmacSha1Hex(byteArray18, byteArray29);
        byte[] byteArray39 = base32_10.encode(byteArray29, (int) '#', 0);
        byte[] byteArray40 = org.apache.commons.codec.net.URLCodec.decodeUrl(byteArray39);
        // The following exception was thrown during execution in test generation
        try {
            int int43 = base16InputStream1.read(byteArray40, (int) (byte) 10, 64);
            org.junit.Assert.fail("Expected exception of type java.lang.IndexOutOfBoundsException; message: null");
        } catch (java.lang.IndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray5);
        org.junit.Assert.assertArrayEquals(byteArray5, new byte[] {});
        org.junit.Assert.assertNotNull(charArray6);
        org.junit.Assert.assertArrayEquals(charArray6, new char[] {});
        org.junit.Assert.assertNotNull(byteArray7);
        org.junit.Assert.assertArrayEquals(byteArray7, new byte[] {});
        org.junit.Assert.assertNotNull(byteArray13);
        org.junit.Assert.assertArrayEquals(byteArray13, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str14 + "' != '" + "\000" + "'", str14, "\000");
        org.junit.Assert.assertTrue("'" + boolean16 + "' != '" + false + "'", boolean16 == false);
        org.junit.Assert.assertNotNull(byteArray18);
        org.junit.Assert.assertNotNull(byteArray22);
        org.junit.Assert.assertArrayEquals(byteArray22, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str23 + "' != '" + "\000" + "'", str23, "\000");
        org.junit.Assert.assertTrue("'" + boolean25 + "' != '" + false + "'", boolean25 == false);
        org.junit.Assert.assertNotNull(byteArray29);
        org.junit.Assert.assertArrayEquals(byteArray29, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str30 + "' != '" + "\000" + "'", str30, "\000");
        org.junit.Assert.assertTrue("'" + boolean32 + "' != '" + false + "'", boolean32 == false);
// flaky "57) test368(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str33 + "' != '" + "pKF7DLyVVSc4E" + "'", str33, "pKF7DLyVVSc4E");
        org.junit.Assert.assertEquals("'" + str34 + "' != '" + "310354661a5962d5b8cb76032d5a97e8aed7cf9f" + "'", str34, "310354661a5962d5b8cb76032d5a97e8aed7cf9f");
        org.junit.Assert.assertEquals("'" + str35 + "' != '" + "0000000000000000" + "'", str35, "0000000000000000");
        org.junit.Assert.assertEquals("'" + str36 + "' != '" + "55351564fecb450a30b6ab9f075f0e63a5f492b6" + "'", str36, "55351564fecb450a30b6ab9f075f0e63a5f492b6");
        org.junit.Assert.assertNotNull(byteArray39);
        org.junit.Assert.assertArrayEquals(byteArray39, new byte[] {});
        org.junit.Assert.assertNotNull(byteArray40);
        org.junit.Assert.assertArrayEquals(byteArray40, new byte[] {});
    }

    @Test
    public void test369() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test369");
        org.apache.commons.codec.net.PercentCodec percentCodec0 = new org.apache.commons.codec.net.PercentCodec();
        java.lang.Object obj1 = null;
        java.lang.Object obj2 = percentCodec0.encode(obj1);
        org.apache.commons.codec.binary.Base32 base32_3 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray6 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str7 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray6);
        boolean boolean9 = base32_3.isInAlphabet(byteArray6, false);
        java.lang.String str10 = org.apache.commons.codec.digest.UnixCrypt.crypt(byteArray6);
        byte[] byteArray11 = percentCodec0.decode(byteArray6);
        org.junit.Assert.assertNull(obj2);
        org.junit.Assert.assertNotNull(byteArray6);
        org.junit.Assert.assertArrayEquals(byteArray6, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "\000" + "'", str7, "\000");
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
// flaky "58) test369(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str10 + "' != '" + "5PeLmK3Zu.FuM" + "'", str10, "5PeLmK3Zu.FuM");
        org.junit.Assert.assertNotNull(byteArray11);
        org.junit.Assert.assertArrayEquals(byteArray11, new byte[] { (byte) 0, (byte) 0 });
    }

    @Test
    public void test370() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test370");
        java.io.OutputStream outputStream0 = null;
        org.apache.commons.codec.binary.Base64OutputStream base64OutputStream1 = new org.apache.commons.codec.binary.Base64OutputStream(outputStream0);
        org.apache.commons.codec.binary.Base32 base32_4 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray7 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str8 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray7);
        boolean boolean10 = base32_4.isInAlphabet(byteArray7, false);
        org.apache.commons.codec.binary.Base32 base32_11 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray14 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str15 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray14);
        boolean boolean17 = base32_11.isInAlphabet(byteArray14, false);
        byte[] byteArray18 = org.apache.commons.codec.digest.HmacUtils.hmacMd5(byteArray7, byteArray14);
        org.apache.commons.codec.binary.Base32OutputStream base32OutputStream19 = new org.apache.commons.codec.binary.Base32OutputStream((java.io.OutputStream) base64OutputStream1, false, 1, byteArray14);
        base64OutputStream1.eof();
        org.apache.commons.codec.binary.Base16OutputStream base16OutputStream22 = new org.apache.commons.codec.binary.Base16OutputStream((java.io.OutputStream) base64OutputStream1, false);
        org.apache.commons.codec.binary.Base16OutputStream base16OutputStream24 = new org.apache.commons.codec.binary.Base16OutputStream((java.io.OutputStream) base16OutputStream22, false);
        org.junit.Assert.assertNotNull(byteArray7);
        org.junit.Assert.assertArrayEquals(byteArray7, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "\000" + "'", str8, "\000");
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        org.junit.Assert.assertNotNull(byteArray14);
        org.junit.Assert.assertArrayEquals(byteArray14, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str15 + "' != '" + "\000" + "'", str15, "\000");
        org.junit.Assert.assertTrue("'" + boolean17 + "' != '" + false + "'", boolean17 == false);
        org.junit.Assert.assertNotNull(byteArray18);
        org.junit.Assert.assertArrayEquals(byteArray18, new byte[] { (byte) -53, (byte) -62, (byte) 109, (byte) 108, (byte) 16, (byte) 34, (byte) -87, (byte) -93, (byte) 59, (byte) 95, (byte) 90, (byte) 76, (byte) 18, (byte) -71, (byte) -99, (byte) -51 });
    }

    @Test
    public void test371() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test371");
        java.lang.String str2 = org.apache.commons.codec.digest.HmacUtils.hmacSha1Hex("j5bT9KV4jZHV6", "j5bT9KV4jZHV6");
        org.junit.Assert.assertEquals("'" + str2 + "' != '" + "7811fcb3c48faefb68c83c81703495bdecc83958" + "'", str2, "7811fcb3c48faefb68c83c81703495bdecc83958");
    }

    @Test
    public void test372() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test372");
        org.apache.commons.codec.binary.Base32 base32_0 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray3 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str4 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray3);
        boolean boolean6 = base32_0.isInAlphabet(byteArray3, false);
        org.apache.commons.codec.binary.Base32 base32_7 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray10 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str11 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray10);
        boolean boolean13 = base32_7.isInAlphabet(byteArray10, false);
        byte[] byteArray14 = base32_0.encode(byteArray10);
        java.lang.String str15 = org.apache.commons.codec.binary.StringUtils.newStringUtf16(byteArray14);
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertArrayEquals(byteArray3, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "\000" + "'", str4, "\000");
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNotNull(byteArray10);
        org.junit.Assert.assertArrayEquals(byteArray10, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "\000" + "'", str11, "\000");
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
        org.junit.Assert.assertNotNull(byteArray14);
        org.junit.Assert.assertArrayEquals(byteArray14, new byte[] { (byte) 65, (byte) 65, (byte) 65, (byte) 65, (byte) 61, (byte) 61, (byte) 61, (byte) 61 });
        org.junit.Assert.assertEquals("'" + str15 + "' != '" + "\u4141\u4141\u3d3d\u3d3d" + "'", str15, "\u4141\u4141\u3d3d\u3d3d");
    }

    @Test
    public void test373() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test373");
        org.apache.commons.codec.binary.Base32InputStream.Builder builder0 = new org.apache.commons.codec.binary.Base32InputStream.Builder();
        org.apache.commons.codec.binary.Base32InputStream base32InputStream1 = builder0.get();
        boolean boolean2 = base32InputStream1.markSupported();
        org.apache.commons.codec.binary.Base32InputStream base32InputStream4 = new org.apache.commons.codec.binary.Base32InputStream((java.io.InputStream) base32InputStream1, true);
        java.io.InputStream inputStream7 = null;
        org.apache.commons.codec.binary.Base16InputStream base16InputStream8 = new org.apache.commons.codec.binary.Base16InputStream(inputStream7);
        char[] charArray11 = null;
        byte[] byteArray12 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray11);
        char[] charArray13 = org.apache.commons.codec.binary.Hex.encodeHex(byteArray12);
        byte[] byteArray14 = org.apache.commons.codec.binary.Hex.decodeHex(charArray13);
        org.apache.commons.codec.CodecPolicy codecPolicy15 = null;
        org.apache.commons.codec.binary.Base64InputStream base64InputStream16 = new org.apache.commons.codec.binary.Base64InputStream((java.io.InputStream) base16InputStream8, true, (int) (short) 100, byteArray14, codecPolicy15);
        int int17 = base64InputStream16.available();
        org.apache.commons.codec.binary.Base32 base32_20 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray23 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str24 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray23);
        boolean boolean26 = base32_20.isInAlphabet(byteArray23, false);
        org.apache.commons.codec.binary.Base32 base32_27 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray30 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str31 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray30);
        boolean boolean33 = base32_27.isInAlphabet(byteArray30, false);
        java.lang.String str34 = org.apache.commons.codec.digest.UnixCrypt.crypt(byteArray30);
        java.lang.String str35 = org.apache.commons.codec.digest.HmacUtils.hmacSha1Hex(byteArray23, byteArray30);
        java.lang.String str36 = org.apache.commons.codec.binary.BinaryCodec.toAsciiString(byteArray30);
        javax.crypto.Mac mac37 = org.apache.commons.codec.digest.HmacUtils.getHmacSha384(byteArray30);
        java.lang.String str38 = org.apache.commons.codec.binary.BinaryCodec.toAsciiString(byteArray30);
        org.apache.commons.codec.CodecPolicy codecPolicy41 = null;
        org.apache.commons.codec.binary.Base16 base16_42 = new org.apache.commons.codec.binary.Base16(true, codecPolicy41);
        boolean boolean44 = base16_42.isInAlphabet((byte) -1);
        org.apache.commons.codec.CodecPolicy codecPolicy45 = base16_42.getCodecPolicy();
        org.apache.commons.codec.binary.Base16 base16_46 = new org.apache.commons.codec.binary.Base16(true, codecPolicy45);
        org.apache.commons.codec.binary.Base64InputStream base64InputStream47 = new org.apache.commons.codec.binary.Base64InputStream((java.io.InputStream) base64InputStream16, false, (int) (byte) -1, byteArray30, codecPolicy45);
        org.apache.commons.codec.binary.Base16InputStream base16InputStream48 = new org.apache.commons.codec.binary.Base16InputStream((java.io.InputStream) base32InputStream1, false, true, codecPolicy45);
        org.junit.Assert.assertNotNull(base32InputStream1);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(byteArray12);
        org.junit.Assert.assertArrayEquals(byteArray12, new byte[] {});
        org.junit.Assert.assertNotNull(charArray13);
        org.junit.Assert.assertArrayEquals(charArray13, new char[] {});
        org.junit.Assert.assertNotNull(byteArray14);
        org.junit.Assert.assertArrayEquals(byteArray14, new byte[] {});
        org.junit.Assert.assertTrue("'" + int17 + "' != '" + 1 + "'", int17 == 1);
        org.junit.Assert.assertNotNull(byteArray23);
        org.junit.Assert.assertArrayEquals(byteArray23, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str24 + "' != '" + "\000" + "'", str24, "\000");
        org.junit.Assert.assertTrue("'" + boolean26 + "' != '" + false + "'", boolean26 == false);
        org.junit.Assert.assertNotNull(byteArray30);
        org.junit.Assert.assertArrayEquals(byteArray30, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str31 + "' != '" + "\000" + "'", str31, "\000");
        org.junit.Assert.assertTrue("'" + boolean33 + "' != '" + false + "'", boolean33 == false);
// flaky "59) test373(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str34 + "' != '" + "y6R5JKnXPoauE" + "'", str34, "y6R5JKnXPoauE");
        org.junit.Assert.assertEquals("'" + str35 + "' != '" + "310354661a5962d5b8cb76032d5a97e8aed7cf9f" + "'", str35, "310354661a5962d5b8cb76032d5a97e8aed7cf9f");
        org.junit.Assert.assertEquals("'" + str36 + "' != '" + "0000000000000000" + "'", str36, "0000000000000000");
        org.junit.Assert.assertNotNull(mac37);
        org.junit.Assert.assertEquals("'" + str38 + "' != '" + "0000000000000000" + "'", str38, "0000000000000000");
        org.junit.Assert.assertTrue("'" + boolean44 + "' != '" + false + "'", boolean44 == false);
        org.junit.Assert.assertTrue("'" + codecPolicy45 + "' != '" + org.apache.commons.codec.CodecPolicy.LENIENT + "'", codecPolicy45.equals(org.apache.commons.codec.CodecPolicy.LENIENT));
    }

    @Test
    public void test374() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test374");
        org.apache.commons.codec.binary.Base32InputStream.Builder builder0 = new org.apache.commons.codec.binary.Base32InputStream.Builder();
        java.io.InputStream inputStream1 = null;
        org.apache.commons.codec.binary.Base16InputStream base16InputStream2 = new org.apache.commons.codec.binary.Base16InputStream(inputStream1);
        byte[] byteArray11 = new byte[] { (byte) 10, (byte) 0, (byte) -1, (byte) 0, (byte) 1, (byte) 100 };
        java.lang.String str12 = org.apache.commons.codec.digest.Md5Crypt.md5Crypt(byteArray11);
        org.apache.commons.codec.CodecPolicy codecPolicy13 = null;
        org.apache.commons.codec.binary.Base64InputStream base64InputStream14 = new org.apache.commons.codec.binary.Base64InputStream((java.io.InputStream) base16InputStream2, false, (int) 'a', byteArray11, codecPolicy13);
        org.apache.commons.codec.binary.Base32InputStream.Builder builder15 = builder0.setInputStream((java.io.InputStream) base64InputStream14);
        org.apache.commons.codec.binary.Base32 base32_16 = new org.apache.commons.codec.binary.Base32();
        org.apache.commons.codec.binary.Base32InputStream.Builder builder17 = builder0.setBaseNCodec(base32_16);
        org.apache.commons.codec.binary.Base32 base32_19 = new org.apache.commons.codec.binary.Base32(0);
        org.apache.commons.codec.binary.Base32InputStream.Builder builder20 = builder0.setBaseNCodec(base32_19);
        org.junit.Assert.assertNotNull(byteArray11);
        org.junit.Assert.assertArrayEquals(byteArray11, new byte[] { (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0 });
// flaky "60) test374(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str12 + "' != '" + "$1$f2IXyfrw$k3eDyIekmMgV.Oi7MLLqP1" + "'", str12, "$1$f2IXyfrw$k3eDyIekmMgV.Oi7MLLqP1");
        org.junit.Assert.assertNotNull(builder15);
        org.junit.Assert.assertNotNull(builder17);
        org.junit.Assert.assertNotNull(builder20);
    }

    @Test
    public void test375() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test375");
        int int3 = org.apache.commons.codec.digest.MurmurHash3.hash32((-1L), 0L, (int) '#');
        org.junit.Assert.assertTrue("'" + int3 + "' != '" + 272995458 + "'", int3 == 272995458);
    }

    @Test
    public void test376() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test376");
        org.apache.commons.codec.net.QCodec qCodec0 = new org.apache.commons.codec.net.QCodec();
        java.nio.charset.Charset charset2 = org.apache.commons.codec.Charsets.UTF_8;
        java.lang.String str3 = qCodec0.encode("$1$gUdz5yQC$LzF0PPk8FItQxPDDbufAf.", charset2);
        java.lang.String str4 = qCodec0.getDefaultCharset();
        org.apache.commons.codec.net.URLCodec uRLCodec6 = new org.apache.commons.codec.net.URLCodec("PCqE1NnZAFIA2");
        org.apache.commons.codec.binary.Base32 base32_7 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray10 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str11 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray10);
        boolean boolean13 = base32_7.isInAlphabet(byteArray10, false);
        org.apache.commons.codec.binary.Base32 base32_14 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray17 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str18 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray17);
        boolean boolean20 = base32_14.isInAlphabet(byteArray17, false);
        java.lang.String str21 = org.apache.commons.codec.digest.UnixCrypt.crypt(byteArray17);
        java.lang.String str22 = org.apache.commons.codec.digest.HmacUtils.hmacSha1Hex(byteArray10, byteArray17);
        byte[] byteArray23 = uRLCodec6.decode(byteArray10);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj24 = qCodec0.decode((java.lang.Object) byteArray10);
            org.junit.Assert.fail("Expected exception of type org.apache.commons.codec.DecoderException; message: Objects of type [B cannot be decoded using Q codec");
        } catch (org.apache.commons.codec.DecoderException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(charset2);
        org.junit.Assert.assertEquals("'" + str3 + "' != '" + "=?UTF-8?Q?$1$gUdz5yQC$LzF0PPk8FItQxPDDbufAf.?=" + "'", str3, "=?UTF-8?Q?$1$gUdz5yQC$LzF0PPk8FItQxPDDbufAf.?=");
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "UTF-8" + "'", str4, "UTF-8");
        org.junit.Assert.assertNotNull(byteArray10);
        org.junit.Assert.assertArrayEquals(byteArray10, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "\000" + "'", str11, "\000");
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
        org.junit.Assert.assertNotNull(byteArray17);
        org.junit.Assert.assertArrayEquals(byteArray17, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str18 + "' != '" + "\000" + "'", str18, "\000");
        org.junit.Assert.assertTrue("'" + boolean20 + "' != '" + false + "'", boolean20 == false);
// flaky "61) test376(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str21 + "' != '" + "usuRDa9RuIEck" + "'", str21, "usuRDa9RuIEck");
        org.junit.Assert.assertEquals("'" + str22 + "' != '" + "310354661a5962d5b8cb76032d5a97e8aed7cf9f" + "'", str22, "310354661a5962d5b8cb76032d5a97e8aed7cf9f");
        org.junit.Assert.assertNotNull(byteArray23);
        org.junit.Assert.assertArrayEquals(byteArray23, new byte[] { (byte) 0, (byte) 0 });
    }

    @Test
    public void test377() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test377");
        byte[] byteArray1 = org.apache.commons.codec.digest.DigestUtils.sha512("CBADAFACDABACF");
        java.lang.String str2 = org.apache.commons.codec.binary.StringUtils.newStringUtf16(byteArray1);
        org.junit.Assert.assertNotNull(byteArray1);
        org.junit.Assert.assertEquals("'" + str2 + "' != '" + "\u1cd7\u0159\u697f\u9bda\u51b1\uff86\ud386\u2cfc\uf880\u4d79\ub32f\u031a\u9a73\ud006\u3f06\u5924\u627d\ua007\u241c\u8136\u0b65\ufeb8\u6350\uc8f9\u7da6\u96ae\ueb6e\ue5ab\u937a\u4cf1\ud5da\u2436" + "'", str2, "\u1cd7\u0159\u697f\u9bda\u51b1\uff86\ud386\u2cfc\uf880\u4d79\ub32f\u031a\u9a73\ud006\u3f06\u5924\u627d\ua007\u241c\u8136\u0b65\ufeb8\u6350\uc8f9\u7da6\u96ae\ueb6e\ue5ab\u937a\u4cf1\ud5da\u2436");
    }

    @Test
    public void test378() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test378");
        java.io.InputStream inputStream0 = null;
        org.apache.commons.codec.binary.Base16InputStream base16InputStream1 = new org.apache.commons.codec.binary.Base16InputStream(inputStream0);
        byte[] byteArray10 = new byte[] { (byte) 10, (byte) 0, (byte) -1, (byte) 0, (byte) 1, (byte) 100 };
        java.lang.String str11 = org.apache.commons.codec.digest.Md5Crypt.md5Crypt(byteArray10);
        org.apache.commons.codec.CodecPolicy codecPolicy12 = null;
        org.apache.commons.codec.binary.Base64InputStream base64InputStream13 = new org.apache.commons.codec.binary.Base64InputStream((java.io.InputStream) base16InputStream1, false, (int) 'a', byteArray10, codecPolicy12);
        // The following exception was thrown during execution in test generation
        try {
            byte[] byteArray14 = org.apache.commons.codec.digest.DigestUtils.md5((java.io.InputStream) base64InputStream13);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray10);
        org.junit.Assert.assertArrayEquals(byteArray10, new byte[] { (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0 });
// flaky "62) test378(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str11 + "' != '" + "$1$GEFktotG$FMiTw4lkaECvytvVRL12g." + "'", str11, "$1$GEFktotG$FMiTw4lkaECvytvVRL12g.");
    }

    @Test
    public void test379() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test379");
        org.apache.commons.codec.digest.PureJavaCrc32C pureJavaCrc32C0 = new org.apache.commons.codec.digest.PureJavaCrc32C();
        pureJavaCrc32C0.update((int) (byte) 0);
    }

    @Test
    public void test380() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test380");
        byte[] byteArray3 = org.apache.commons.codec.digest.HmacUtils.hmacSha1("ISO-8859-1", "ASKTKTKPMK");
        // The following exception was thrown during execution in test generation
        try {
            org.apache.commons.codec.binary.Base64 base64_4 = new org.apache.commons.codec.binary.Base64(0, byteArray3);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: lineSeparator must not contain base64 characters: [?PRBz?????)???? ;]");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertArrayEquals(byteArray3, new byte[] { (byte) -50, (byte) 80, (byte) 82, (byte) 66, (byte) 122, (byte) -66, (byte) 13, (byte) -50, (byte) -77, (byte) -21, (byte) -123, (byte) -24, (byte) 41, (byte) 5, (byte) -36, (byte) -123, (byte) 11, (byte) 15, (byte) 32, (byte) 59 });
    }

    @Test
    public void test381() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test381");
        org.apache.commons.codec.binary.Base58InputStream.Builder builder0 = org.apache.commons.codec.binary.Base58InputStream.builder();
        org.apache.commons.codec.binary.Base58.Builder builder1 = org.apache.commons.codec.binary.Base58.builder();
        org.apache.commons.codec.binary.Base58 base58_2 = new org.apache.commons.codec.binary.Base58(builder1);
        org.apache.commons.codec.binary.Base58InputStream.Builder builder3 = builder0.setBaseNCodec(base58_2);
        org.apache.commons.codec.binary.Base58InputStream base58InputStream4 = builder0.get();
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(builder1);
        org.junit.Assert.assertNotNull(builder3);
        org.junit.Assert.assertNotNull(base58InputStream4);
    }

    @Test
    public void test382() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test382");
        java.io.OutputStream outputStream0 = null;
        org.apache.commons.codec.binary.Base64OutputStream base64OutputStream1 = new org.apache.commons.codec.binary.Base64OutputStream(outputStream0);
        base64OutputStream1.eof();
        org.apache.commons.codec.binary.Base32OutputStream base32OutputStream4 = new org.apache.commons.codec.binary.Base32OutputStream((java.io.OutputStream) base64OutputStream1, true);
        boolean boolean5 = base64OutputStream1.isStrictDecoding();
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
    }

    @Test
    public void test383() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test383");
        java.security.MessageDigest messageDigest0 = org.apache.commons.codec.digest.DigestUtils.getSha512Digest();
        java.nio.file.Path path1 = null;
        // The following exception was thrown during execution in test generation
        try {
            byte[] byteArray2 = org.apache.commons.codec.digest.GitIdentifiers.blobId(messageDigest0, path1);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(messageDigest0);
        org.junit.Assert.assertEquals(messageDigest0.toString(), "SHA-512 Message Digest from SUN, <initialized>\r\n");
    }

    @Test
    public void test384() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test384");
        java.security.MessageDigest messageDigest1 = org.apache.commons.codec.digest.DigestUtils.getShaDigest();
        byte[] byteArray3 = org.apache.commons.codec.binary.StringUtils.getBytesIso8859_1("c39394a96ebd177a239a817fe8453ab0");
        byte[] byteArray4 = org.apache.commons.codec.digest.GitIdentifiers.blobId(messageDigest1, byteArray3);
        // The following exception was thrown during execution in test generation
        try {
            org.apache.commons.codec.binary.Base64 base64_5 = new org.apache.commons.codec.binary.Base64(0, byteArray4);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: lineSeparator must not contain base64 characters: [???>F??Sg[=?0???+?]E]");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(messageDigest1);
        org.junit.Assert.assertEquals(messageDigest1.toString(), "SHA-1 Message Digest from SUN, <initialized>\r\n");
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertNotNull(byteArray4);
        org.junit.Assert.assertArrayEquals(byteArray4, new byte[] { (byte) -7, (byte) -108, (byte) -7, (byte) 62, (byte) 70, (byte) -82, (byte) -11, (byte) 83, (byte) 103, (byte) 91, (byte) 61, (byte) -112, (byte) 48, (byte) -94, (byte) -84, (byte) -12, (byte) 43, (byte) 7, (byte) 93, (byte) 69 });
    }

    @Test
    public void test385() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test385");
        org.apache.commons.codec.digest.DigestUtils digestUtils0 = new org.apache.commons.codec.digest.DigestUtils();
        java.nio.file.Path path1 = null;
        java.nio.file.OpenOption openOption2 = null;
        java.nio.file.OpenOption[] openOptionArray3 = new java.nio.file.OpenOption[] { openOption2 };
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str4 = digestUtils0.digestAsHex(path1, openOptionArray3);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(openOptionArray3);
        org.junit.Assert.assertArrayEquals(openOptionArray3, new java.nio.file.OpenOption[] { null });
    }

    @Test
    public void test386() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test386");
        byte[] byteArray1 = org.apache.commons.codec.binary.StringUtils.getBytesIso8859_1("h4V99TZaVFEkk");
        long long5 = org.apache.commons.codec.digest.MurmurHash3.hash64(byteArray1, (-342794719), 0, (int) (short) 1);
        boolean boolean6 = org.apache.commons.codec.binary.Base64.isBase64Standard(byteArray1);
        org.junit.Assert.assertNotNull(byteArray1);
        org.junit.Assert.assertArrayEquals(byteArray1, new byte[] { (byte) 104, (byte) 52, (byte) 86, (byte) 57, (byte) 57, (byte) 84, (byte) 90, (byte) 97, (byte) 86, (byte) 70, (byte) 69, (byte) 107, (byte) 107 });
        org.junit.Assert.assertTrue("'" + long5 + "' != '" + (-5451962507482445012L) + "'", long5 == (-5451962507482445012L));
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + true + "'", boolean6 == true);
    }

    @Test
    public void test387() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test387");
        org.apache.commons.codec.language.Nysiis nysiis0 = new org.apache.commons.codec.language.Nysiis();
    }

    @Test
    public void test388() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test388");
        org.apache.commons.codec.language.bm.Languages languages1 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.apache.commons.codec.language.bm.Lang lang2 = org.apache.commons.codec.language.bm.Lang.loadFromResource("", languages1);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Malformed line 'binary' in language resource ''");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test389() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test389");
        org.apache.commons.codec.net.QuotedPrintableCodec quotedPrintableCodec1 = new org.apache.commons.codec.net.QuotedPrintableCodec(true);
        org.apache.commons.codec.binary.Base58InputStream.Builder builder2 = new org.apache.commons.codec.binary.Base58InputStream.Builder();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj3 = quotedPrintableCodec1.decode((java.lang.Object) builder2);
            org.junit.Assert.fail("Expected exception of type org.apache.commons.codec.DecoderException; message: Objects of type org.apache.commons.codec.binary.Base58InputStream$Builder cannot be quoted-printable decoded");
        } catch (org.apache.commons.codec.DecoderException e) {
            // Expected exception.
        }
    }

    @Test
    public void test390() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test390");
        org.apache.commons.codec.binary.Hex hex0 = new org.apache.commons.codec.binary.Hex();
        byte[] byteArray2 = org.apache.commons.codec.binary.StringUtils.getBytesIso8859_1("c39394a96ebd177a239a817fe8453ab0");
        org.apache.commons.codec.binary.Base32 base32_3 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray6 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str7 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray6);
        boolean boolean9 = base32_3.isInAlphabet(byteArray6, false);
        org.apache.commons.codec.binary.Base32 base32_10 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray13 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str14 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray13);
        boolean boolean16 = base32_10.isInAlphabet(byteArray13, false);
        java.lang.String str17 = org.apache.commons.codec.digest.UnixCrypt.crypt(byteArray13);
        java.lang.String str18 = org.apache.commons.codec.digest.HmacUtils.hmacSha1Hex(byteArray6, byteArray13);
        java.lang.String str19 = org.apache.commons.codec.binary.BinaryCodec.toAsciiString(byteArray13);
        java.lang.String str20 = org.apache.commons.codec.digest.HmacUtils.hmacSha1Hex(byteArray2, byteArray13);
        int int23 = org.apache.commons.codec.digest.MurmurHash3.hash32(byteArray13, 0, (int) (byte) 0);
        byte[] byteArray24 = hex0.encode(byteArray13);
        org.junit.Assert.assertNotNull(byteArray2);
        org.junit.Assert.assertNotNull(byteArray6);
        org.junit.Assert.assertArrayEquals(byteArray6, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "\000" + "'", str7, "\000");
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertNotNull(byteArray13);
        org.junit.Assert.assertArrayEquals(byteArray13, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str14 + "' != '" + "\000" + "'", str14, "\000");
        org.junit.Assert.assertTrue("'" + boolean16 + "' != '" + false + "'", boolean16 == false);
// flaky "63) test390(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str17 + "' != '" + "phN7RY5s163nE" + "'", str17, "phN7RY5s163nE");
        org.junit.Assert.assertEquals("'" + str18 + "' != '" + "310354661a5962d5b8cb76032d5a97e8aed7cf9f" + "'", str18, "310354661a5962d5b8cb76032d5a97e8aed7cf9f");
        org.junit.Assert.assertEquals("'" + str19 + "' != '" + "0000000000000000" + "'", str19, "0000000000000000");
        org.junit.Assert.assertEquals("'" + str20 + "' != '" + "55351564fecb450a30b6ab9f075f0e63a5f492b6" + "'", str20, "55351564fecb450a30b6ab9f075f0e63a5f492b6");
        org.junit.Assert.assertTrue("'" + int23 + "' != '" + 0 + "'", int23 == 0);
        org.junit.Assert.assertNotNull(byteArray24);
        org.junit.Assert.assertArrayEquals(byteArray24, new byte[] { (byte) 48, (byte) 48, (byte) 48, (byte) 48 });
    }

    @Test
    public void test391() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test391");
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str1 = org.apache.commons.codec.digest.DigestUtils.sha3_512Hex("aQ3oIH6Lsvh.U");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: java.security.NoSuchAlgorithmException: SHA3-512 MessageDigest not available");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test392() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test392");
        java.lang.String[] strArray41 = new java.lang.String[] { "e8d8123bcec18f5e308b0e5219338abba38bdeef310c42c2503cbd593fcf1fae3dfd1d38d64c108f9f299fde729e5f13", "$1$EdFYrmbn$nDeiTd8h3tTEnn4L.l1EK0", "j5bT9KV4jZHV6", "%241%24hAQ8T6Eg%24KVefjtCJjE3tsL8Q%2FuIi.0", "PCqE1NnZAFIA2", "55351564fecb450a30b6ab9f075f0e63a5f492b6", "5mEC1Zd5kEgRk", "SHA3-384", "$1$iWJESpnB$vDap.SotgdmY.f0Rjgzf3/", "jH7s6cd4L9P9.", "4f71fccac43c73545ddd9cd772f37598", "5mEC1Zd5kEgRk", "sqxhKYbUNfTzw", "/IwbDonZ0VAww", "A6fKTL2blvInc", "ajUG1rGJ.j9xk", "SHA3-384", "$1$2bZpHv3e$KuegH6jWeAAwJFSY6saCF0", "G30LGL3wppDqc", "aQ3oIH6Lsvh.U", "c39394a96ebd177a239a817fe8453ab0", "20IlD34ySuMqY", "4f71fccac43c73545ddd9cd772f37598", "c7x1syRbCUeX.", "UTF-8", "$6$oWTfCqM4$VIqBhSK7v23O4tnjI9G0.ySCEVf2c5vaGxRmZoxTWiiZvbzGzEkGo0Wpjyh6Ih.x1txvfCdlGUL4IodLMR6a6/", "55351564fecb450a30b6ab9f075f0e63a5f492b6", "kK37euxiszvNU", "d4615270d8c34a6731a7fcc0241ec5bafc726b4c", "20IlD34ySuMqY", "XlrVAMfz2VTUM", "hi!", "$1$6Ol7ARtC$6M2x6pcRYE8xqRHhnMSwE.", "UTF-8", "SHAKE256-512", "$1$upso8pvN$0pmnmS6eBsS.QRWuud41M0", "da13fb08f28e123196a9c326536e6f8f6037e34eb6e68dbc96cc3f67958593f9073f65fca64389228646d0b60fe610c5dc99e95a1e30c86b873f3820a7ce3c96", "ISO-8859-1", "SHA-256", "I3HMbnG3q1U2Y", "B78Te0Lj1zc0U" };
        java.util.LinkedHashSet<java.lang.String> strSet42 = new java.util.LinkedHashSet<java.lang.String>();
        boolean boolean43 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strSet42, strArray41);
        org.apache.commons.codec.language.bm.Languages.LanguageSet languageSet44 = org.apache.commons.codec.language.bm.Languages.LanguageSet.from((java.util.Set<java.lang.String>) strSet42);
        org.junit.Assert.assertNotNull(strArray41);
        org.junit.Assert.assertTrue("'" + boolean43 + "' != '" + true + "'", boolean43 == true);
        org.junit.Assert.assertNotNull(languageSet44);
    }

    @Test
    public void test393() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test393");
        // The following exception was thrown during execution in test generation
        try {
            java.nio.charset.Charset charset1 = org.apache.commons.codec.Charsets.toCharset("4f71fccac43c73545ddd9cd772f37598");
            org.junit.Assert.fail("Expected exception of type java.nio.charset.UnsupportedCharsetException; message: 4f71fccac43c73545ddd9cd772f37598");
        } catch (java.nio.charset.UnsupportedCharsetException e) {
            // Expected exception.
        }
    }

    @Test
    public void test394() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test394");
        org.apache.commons.codec.binary.Base32 base32_0 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray3 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str4 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray3);
        boolean boolean6 = base32_0.isInAlphabet(byteArray3, false);
        byte[] byteArray8 = org.apache.commons.codec.binary.StringUtils.getBytesIso8859_1("c39394a96ebd177a239a817fe8453ab0");
        org.apache.commons.codec.binary.Base32 base32_9 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray12 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str13 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray12);
        boolean boolean15 = base32_9.isInAlphabet(byteArray12, false);
        org.apache.commons.codec.binary.Base32 base32_16 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray19 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str20 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray19);
        boolean boolean22 = base32_16.isInAlphabet(byteArray19, false);
        java.lang.String str23 = org.apache.commons.codec.digest.UnixCrypt.crypt(byteArray19);
        java.lang.String str24 = org.apache.commons.codec.digest.HmacUtils.hmacSha1Hex(byteArray12, byteArray19);
        java.lang.String str25 = org.apache.commons.codec.binary.BinaryCodec.toAsciiString(byteArray19);
        java.lang.String str26 = org.apache.commons.codec.digest.HmacUtils.hmacSha1Hex(byteArray8, byteArray19);
        byte[] byteArray29 = base32_0.encode(byteArray19, (int) '#', 0);
        // The following exception was thrown during execution in test generation
        try {
            int int31 = org.apache.commons.codec.digest.MurmurHash3.hash32(byteArray29, (int) (byte) 10);
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: 0");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertArrayEquals(byteArray3, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "\000" + "'", str4, "\000");
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNotNull(byteArray8);
        org.junit.Assert.assertNotNull(byteArray12);
        org.junit.Assert.assertArrayEquals(byteArray12, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "\000" + "'", str13, "\000");
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertNotNull(byteArray19);
        org.junit.Assert.assertArrayEquals(byteArray19, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str20 + "' != '" + "\000" + "'", str20, "\000");
        org.junit.Assert.assertTrue("'" + boolean22 + "' != '" + false + "'", boolean22 == false);
// flaky "64) test394(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str23 + "' != '" + "X2hVQjYHMaERo" + "'", str23, "X2hVQjYHMaERo");
        org.junit.Assert.assertEquals("'" + str24 + "' != '" + "310354661a5962d5b8cb76032d5a97e8aed7cf9f" + "'", str24, "310354661a5962d5b8cb76032d5a97e8aed7cf9f");
        org.junit.Assert.assertEquals("'" + str25 + "' != '" + "0000000000000000" + "'", str25, "0000000000000000");
        org.junit.Assert.assertEquals("'" + str26 + "' != '" + "55351564fecb450a30b6ab9f075f0e63a5f492b6" + "'", str26, "55351564fecb450a30b6ab9f075f0e63a5f492b6");
        org.junit.Assert.assertNotNull(byteArray29);
        org.junit.Assert.assertArrayEquals(byteArray29, new byte[] {});
    }

    @Test
    public void test395() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test395");
        org.apache.commons.codec.binary.Base32 base32_0 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray3 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str4 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray3);
        boolean boolean6 = base32_0.isInAlphabet(byteArray3, false);
        char[] charArray7 = null;
        byte[] byteArray8 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray7);
        boolean boolean10 = base32_0.isInAlphabet(byteArray8, true);
        java.math.BigInteger bigInteger11 = org.apache.commons.codec.binary.Base64.decodeInteger(byteArray8);
        java.lang.String str12 = org.apache.commons.codec.digest.Md5Crypt.apr1Crypt(byteArray8);
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertArrayEquals(byteArray3, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "\000" + "'", str4, "\000");
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNotNull(byteArray8);
        org.junit.Assert.assertArrayEquals(byteArray8, new byte[] {});
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
        org.junit.Assert.assertNotNull(bigInteger11);
// flaky "65) test395(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str12 + "' != '" + "$apr1$jZPlQ2vN$T9ClkHlmmhKL2Wd43AXoO." + "'", str12, "$apr1$jZPlQ2vN$T9ClkHlmmhKL2Wd43AXoO.");
    }

    @Test
    public void test396() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test396");
        org.apache.commons.codec.binary.Base32 base32_0 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray3 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str4 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray3);
        boolean boolean6 = base32_0.isInAlphabet(byteArray3, false);
        char[] charArray7 = null;
        byte[] byteArray8 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray7);
        boolean boolean10 = base32_0.isInAlphabet(byteArray8, true);
        byte[] byteArray11 = org.apache.commons.codec.digest.DigestUtils.sha1(byteArray8);
        java.lang.String str12 = org.apache.commons.codec.digest.Crypt.crypt(byteArray11);
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertArrayEquals(byteArray3, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "\000" + "'", str4, "\000");
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNotNull(byteArray8);
        org.junit.Assert.assertArrayEquals(byteArray8, new byte[] {});
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
        org.junit.Assert.assertNotNull(byteArray11);
        org.junit.Assert.assertArrayEquals(byteArray11, new byte[] { (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0 });
// flaky "66) test396(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str12 + "' != '" + "$6$455wDvBw$6KObQ0wS4t0KkoClbVS7uJwAfhZ8EEK0tRtDjm3WklgmdvvknhsJerUoTbQhuaJLjlB5YtkrStmev7UhkmY4l1" + "'", str12, "$6$455wDvBw$6KObQ0wS4t0KkoClbVS7uJwAfhZ8EEK0tRtDjm3WklgmdvvknhsJerUoTbQhuaJLjlB5YtkrStmev7UhkmY4l1");
    }

    @Test
    public void test397() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test397");
        java.security.MessageDigest messageDigest1 = null;
        java.security.MessageDigest messageDigest2 = org.apache.commons.codec.digest.DigestUtils.getDigest("\000", messageDigest1);
        org.junit.Assert.assertNull(messageDigest2);
    }

    @Test
    public void test398() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test398");
        java.io.InputStream inputStream0 = null;
        org.apache.commons.codec.binary.Base16InputStream base16InputStream1 = new org.apache.commons.codec.binary.Base16InputStream(inputStream0);
        char[] charArray4 = null;
        byte[] byteArray5 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray4);
        char[] charArray6 = org.apache.commons.codec.binary.Hex.encodeHex(byteArray5);
        byte[] byteArray7 = org.apache.commons.codec.binary.Hex.decodeHex(charArray6);
        org.apache.commons.codec.CodecPolicy codecPolicy8 = null;
        org.apache.commons.codec.binary.Base64InputStream base64InputStream9 = new org.apache.commons.codec.binary.Base64InputStream((java.io.InputStream) base16InputStream1, true, (int) (short) 100, byteArray7, codecPolicy8);
        int int10 = base64InputStream9.available();
        org.apache.commons.codec.binary.Base16InputStream base16InputStream11 = new org.apache.commons.codec.binary.Base16InputStream((java.io.InputStream) base64InputStream9);
        boolean boolean12 = base16InputStream11.isStrictDecoding();
        org.junit.Assert.assertNotNull(byteArray5);
        org.junit.Assert.assertArrayEquals(byteArray5, new byte[] {});
        org.junit.Assert.assertNotNull(charArray6);
        org.junit.Assert.assertArrayEquals(charArray6, new char[] {});
        org.junit.Assert.assertNotNull(byteArray7);
        org.junit.Assert.assertArrayEquals(byteArray7, new byte[] {});
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 1 + "'", int10 == 1);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
    }

    @Test
    public void test399() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test399");
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str1 = org.apache.commons.codec.digest.DigestUtils.sha3_512Hex("$1$OUdBhZTw$baFTEhdkCgJNYh/Jqm07./");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: java.security.NoSuchAlgorithmException: SHA3-512 MessageDigest not available");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test400() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test400");
        org.apache.commons.codec.language.Soundex soundex2 = new org.apache.commons.codec.language.Soundex("55351564fecb450a30b6ab9f075f0e63a5f492b6", true);
        soundex2.setMaxLength(100);
    }

    @Test
    public void test401() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test401");
        org.apache.commons.codec.net.BCodec bCodec0 = new org.apache.commons.codec.net.BCodec();
        java.lang.String str1 = bCodec0.getDefaultCharset();
        java.lang.Object obj3 = bCodec0.encode((java.lang.Object) "$1$gUdz5yQC$LzF0PPk8FItQxPDDbufAf.");
        boolean boolean4 = bCodec0.isStrictDecoding();
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "UTF-8" + "'", str1, "UTF-8");
        org.junit.Assert.assertEquals("'" + obj3 + "' != '" + "=?UTF-8?B?JDEkZ1VkejV5UUMkTHpGMFBQazhGSXRReFBERGJ1ZkFmLg==?=" + "'", obj3, "=?UTF-8?B?JDEkZ1VkejV5UUMkTHpGMFBQazhGSXRReFBERGJ1ZkFmLg==?=");
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
    }

    @Test
    public void test402() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test402");
        java.io.OutputStream outputStream0 = null;
        org.apache.commons.codec.binary.Base64OutputStream base64OutputStream1 = new org.apache.commons.codec.binary.Base64OutputStream(outputStream0);
        org.apache.commons.codec.binary.Base16OutputStream base16OutputStream3 = new org.apache.commons.codec.binary.Base16OutputStream(outputStream0, false);
        boolean boolean4 = base16OutputStream3.isStrictDecoding();
        char[] charArray6 = null;
        byte[] byteArray7 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray6);
        org.apache.commons.codec.binary.Base64 base64_8 = new org.apache.commons.codec.binary.Base64((int) '4', byteArray7);
        // The following exception was thrown during execution in test generation
        try {
            base16OutputStream3.write(byteArray7, (int) ' ', 64);
            org.junit.Assert.fail("Expected exception of type java.lang.IndexOutOfBoundsException; message: null");
        } catch (java.lang.IndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertNotNull(byteArray7);
        org.junit.Assert.assertArrayEquals(byteArray7, new byte[] {});
    }

    @Test
    public void test403() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test403");
        java.io.InputStream inputStream0 = null;
        org.apache.commons.codec.binary.Base16InputStream base16InputStream1 = new org.apache.commons.codec.binary.Base16InputStream(inputStream0);
        byte[] byteArray10 = new byte[] { (byte) 10, (byte) 0, (byte) -1, (byte) 0, (byte) 1, (byte) 100 };
        java.lang.String str11 = org.apache.commons.codec.digest.Md5Crypt.md5Crypt(byteArray10);
        org.apache.commons.codec.CodecPolicy codecPolicy12 = null;
        org.apache.commons.codec.binary.Base64InputStream base64InputStream13 = new org.apache.commons.codec.binary.Base64InputStream((java.io.InputStream) base16InputStream1, false, (int) 'a', byteArray10, codecPolicy12);
        org.apache.commons.codec.binary.Base58InputStream base58InputStream14 = new org.apache.commons.codec.binary.Base58InputStream((java.io.InputStream) base64InputStream13);
        org.junit.Assert.assertNotNull(byteArray10);
        org.junit.Assert.assertArrayEquals(byteArray10, new byte[] { (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0 });
// flaky "67) test403(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str11 + "' != '" + "$1$PU7l2P2b$3oC9dzvBKa0EalTK2nJXB0" + "'", str11, "$1$PU7l2P2b$3oC9dzvBKa0EalTK2nJXB0");
    }

    @Test
    public void test404() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test404");
        org.apache.commons.codec.digest.HmacUtils hmacUtils0 = new org.apache.commons.codec.digest.HmacUtils();
        java.io.InputStream inputStream1 = null;
        org.apache.commons.codec.binary.Base16InputStream base16InputStream2 = new org.apache.commons.codec.binary.Base16InputStream(inputStream1);
        char[] charArray5 = null;
        byte[] byteArray6 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray5);
        char[] charArray7 = org.apache.commons.codec.binary.Hex.encodeHex(byteArray6);
        byte[] byteArray8 = org.apache.commons.codec.binary.Hex.decodeHex(charArray7);
        org.apache.commons.codec.CodecPolicy codecPolicy9 = null;
        org.apache.commons.codec.binary.Base64InputStream base64InputStream10 = new org.apache.commons.codec.binary.Base64InputStream((java.io.InputStream) base16InputStream2, true, (int) (short) 100, byteArray8, codecPolicy9);
        int int11 = base64InputStream10.available();
        // The following exception was thrown during execution in test generation
        try {
            byte[] byteArray12 = hmacUtils0.hmac((java.io.InputStream) base64InputStream10);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray6);
        org.junit.Assert.assertArrayEquals(byteArray6, new byte[] {});
        org.junit.Assert.assertNotNull(charArray7);
        org.junit.Assert.assertArrayEquals(charArray7, new char[] {});
        org.junit.Assert.assertNotNull(byteArray8);
        org.junit.Assert.assertArrayEquals(byteArray8, new byte[] {});
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 1 + "'", int11 == 1);
    }

    @Test
    public void test405() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test405");
        org.apache.commons.codec.binary.Base32 base32_1 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray4 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str5 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray4);
        boolean boolean7 = base32_1.isInAlphabet(byteArray4, false);
        java.lang.String str8 = org.apache.commons.codec.digest.UnixCrypt.crypt(byteArray4);
        org.apache.commons.codec.binary.Base32 base32_10 = new org.apache.commons.codec.binary.Base32((int) (short) 10, byteArray4, true);
        java.lang.String str11 = org.apache.commons.codec.binary.Base64.encodeBase64URLSafeString(byteArray4);
        org.junit.Assert.assertNotNull(byteArray4);
        org.junit.Assert.assertArrayEquals(byteArray4, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "\000" + "'", str5, "\000");
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
// flaky "68) test405(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str8 + "' != '" + "zICM/JBQp.1hc" + "'", str8, "zICM/JBQp.1hc");
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "AAA" + "'", str11, "AAA");
    }

    @Test
    public void test406() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test406");
        org.apache.commons.codec.binary.Base58.Builder builder0 = org.apache.commons.codec.binary.Base58.builder();
        org.apache.commons.codec.binary.Base58 base58_1 = builder0.get();
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(base58_1);
    }

    @Test
    public void test407() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test407");
        org.apache.commons.codec.binary.Base32 base32_0 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray3 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str4 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray3);
        boolean boolean6 = base32_0.isInAlphabet(byteArray3, false);
        byte[] byteArray9 = org.apache.commons.codec.digest.HmacUtils.hmacSha512("310354661a5962d5b8cb76032d5a97e8aed7cf9f", "");
        byte[] byteArray10 = base32_0.encode(byteArray9);
        org.apache.commons.codec.digest.Blake3 blake3_11 = org.apache.commons.codec.digest.Blake3.initKeyDerivationFunction(byteArray10);
        javax.crypto.Mac mac12 = org.apache.commons.codec.digest.HmacUtils.getHmacSha256(byteArray10);
        javax.crypto.Mac mac14 = org.apache.commons.codec.digest.HmacUtils.updateHmac(mac12, "sqxhKYbUNfTzw");
        java.io.InputStream inputStream15 = null;
        org.apache.commons.codec.binary.Base16InputStream base16InputStream16 = new org.apache.commons.codec.binary.Base16InputStream(inputStream15);
        char[] charArray19 = null;
        byte[] byteArray20 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray19);
        char[] charArray21 = org.apache.commons.codec.binary.Hex.encodeHex(byteArray20);
        byte[] byteArray22 = org.apache.commons.codec.binary.Hex.decodeHex(charArray21);
        org.apache.commons.codec.CodecPolicy codecPolicy23 = null;
        org.apache.commons.codec.binary.Base64InputStream base64InputStream24 = new org.apache.commons.codec.binary.Base64InputStream((java.io.InputStream) base16InputStream16, true, (int) (short) 100, byteArray22, codecPolicy23);
        int int25 = base64InputStream24.available();
        // The following exception was thrown during execution in test generation
        try {
            javax.crypto.Mac mac26 = org.apache.commons.codec.digest.HmacUtils.updateHmac(mac14, (java.io.InputStream) base64InputStream24);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertArrayEquals(byteArray3, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "\000" + "'", str4, "\000");
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNotNull(byteArray9);
        org.junit.Assert.assertNotNull(byteArray10);
        org.junit.Assert.assertNotNull(blake3_11);
        org.junit.Assert.assertNotNull(mac12);
        org.junit.Assert.assertNotNull(mac14);
        org.junit.Assert.assertNotNull(byteArray20);
        org.junit.Assert.assertArrayEquals(byteArray20, new byte[] {});
        org.junit.Assert.assertNotNull(charArray21);
        org.junit.Assert.assertArrayEquals(charArray21, new char[] {});
        org.junit.Assert.assertNotNull(byteArray22);
        org.junit.Assert.assertArrayEquals(byteArray22, new byte[] {});
        org.junit.Assert.assertTrue("'" + int25 + "' != '" + 1 + "'", int25 == 1);
    }

    @Test
    public void test408() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test408");
        org.apache.commons.codec.language.bm.NameType nameType0 = null;
        org.apache.commons.codec.language.bm.RuleType ruleType1 = null;
        org.apache.commons.codec.language.bm.PhoneticEngine phoneticEngine4 = new org.apache.commons.codec.language.bm.PhoneticEngine(nameType0, ruleType1, false, (int) (byte) -1);
    }

    @Test
    public void test409() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test409");
        java.io.InputStream inputStream0 = null;
        org.apache.commons.codec.binary.Base16InputStream base16InputStream1 = new org.apache.commons.codec.binary.Base16InputStream(inputStream0);
        boolean boolean2 = base16InputStream1.markSupported();
        org.apache.commons.codec.binary.Base32InputStream base32InputStream4 = new org.apache.commons.codec.binary.Base32InputStream((java.io.InputStream) base16InputStream1, false);
        boolean boolean5 = base16InputStream1.markSupported();
        base16InputStream1.mark((int) (byte) 1);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
    }

    @Test
    public void test410() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test410");
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str1 = org.apache.commons.codec.digest.DigestUtils.shake256_512Hex("01360240043788015936020505");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: java.security.NoSuchAlgorithmException: SHAKE256-512 MessageDigest not available");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test411() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test411");
        org.apache.commons.codec.binary.Base32 base32_0 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray3 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str4 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray3);
        boolean boolean6 = base32_0.isInAlphabet(byteArray3, false);
        org.apache.commons.codec.binary.Base32 base32_7 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray10 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str11 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray10);
        boolean boolean13 = base32_7.isInAlphabet(byteArray10, false);
        java.lang.String str14 = org.apache.commons.codec.digest.UnixCrypt.crypt(byteArray10);
        java.lang.String str15 = org.apache.commons.codec.digest.HmacUtils.hmacSha1Hex(byteArray3, byteArray10);
        java.lang.String str16 = org.apache.commons.codec.binary.Base64.encodeBase64URLSafeString(byteArray3);
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertArrayEquals(byteArray3, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "\000" + "'", str4, "\000");
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNotNull(byteArray10);
        org.junit.Assert.assertArrayEquals(byteArray10, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "\000" + "'", str11, "\000");
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
// flaky "69) test411(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str14 + "' != '" + "A/UdrEyrQ1IHE" + "'", str14, "A/UdrEyrQ1IHE");
        org.junit.Assert.assertEquals("'" + str15 + "' != '" + "310354661a5962d5b8cb76032d5a97e8aed7cf9f" + "'", str15, "310354661a5962d5b8cb76032d5a97e8aed7cf9f");
        org.junit.Assert.assertEquals("'" + str16 + "' != '" + "AAA" + "'", str16, "AAA");
    }

    @Test
    public void test412() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test412");
        org.apache.commons.codec.binary.Base64OutputStream.Builder builder0 = new org.apache.commons.codec.binary.Base64OutputStream.Builder();
        java.io.OutputStream outputStream1 = null;
        org.apache.commons.codec.binary.Base64OutputStream base64OutputStream2 = new org.apache.commons.codec.binary.Base64OutputStream(outputStream1);
        org.apache.commons.codec.binary.Base32 base32_5 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray8 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str9 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray8);
        boolean boolean11 = base32_5.isInAlphabet(byteArray8, false);
        org.apache.commons.codec.binary.Base32 base32_12 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray15 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str16 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray15);
        boolean boolean18 = base32_12.isInAlphabet(byteArray15, false);
        byte[] byteArray19 = org.apache.commons.codec.digest.HmacUtils.hmacMd5(byteArray8, byteArray15);
        org.apache.commons.codec.binary.Base32OutputStream base32OutputStream20 = new org.apache.commons.codec.binary.Base32OutputStream((java.io.OutputStream) base64OutputStream2, false, 1, byteArray15);
        org.apache.commons.codec.binary.Base64OutputStream.Builder builder21 = builder0.setOutputStream((java.io.OutputStream) base64OutputStream2);
        org.junit.Assert.assertNotNull(byteArray8);
        org.junit.Assert.assertArrayEquals(byteArray8, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str9 + "' != '" + "\000" + "'", str9, "\000");
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
        org.junit.Assert.assertNotNull(byteArray15);
        org.junit.Assert.assertArrayEquals(byteArray15, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str16 + "' != '" + "\000" + "'", str16, "\000");
        org.junit.Assert.assertTrue("'" + boolean18 + "' != '" + false + "'", boolean18 == false);
        org.junit.Assert.assertNotNull(byteArray19);
        org.junit.Assert.assertArrayEquals(byteArray19, new byte[] { (byte) -53, (byte) -62, (byte) 109, (byte) 108, (byte) 16, (byte) 34, (byte) -87, (byte) -93, (byte) 59, (byte) 95, (byte) 90, (byte) 76, (byte) 18, (byte) -71, (byte) -99, (byte) -51 });
        org.junit.Assert.assertNotNull(builder21);
    }

    @Test
    public void test413() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test413");
        org.apache.commons.codec.binary.Base32 base32_0 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray3 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str4 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray3);
        boolean boolean6 = base32_0.isInAlphabet(byteArray3, false);
        java.lang.String str7 = org.apache.commons.codec.digest.UnixCrypt.crypt(byteArray3);
        // The following exception was thrown during execution in test generation
        try {
            byte[] byteArray8 = org.apache.commons.codec.digest.DigestUtils.shake128_256(byteArray3);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: java.security.NoSuchAlgorithmException: SHAKE128-256 MessageDigest not available");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertArrayEquals(byteArray3, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "\000" + "'", str4, "\000");
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
// flaky "70) test413(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str7 + "' != '" + "vk3Wz.r.i9LS2" + "'", str7, "vk3Wz.r.i9LS2");
    }

    @Test
    public void test414() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test414");
        org.apache.commons.codec.digest.XXHash32 xXHash32_0 = new org.apache.commons.codec.digest.XXHash32();
        xXHash32_0.reset();
    }

    @Test
    public void test415() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test415");
        java.security.MessageDigest messageDigest0 = org.apache.commons.codec.digest.DigestUtils.getShaDigest();
        org.apache.commons.codec.digest.DigestUtils digestUtils1 = new org.apache.commons.codec.digest.DigestUtils(messageDigest0);
        java.io.RandomAccessFile randomAccessFile2 = null;
        // The following exception was thrown during execution in test generation
        try {
            java.security.MessageDigest messageDigest3 = org.apache.commons.codec.digest.DigestUtils.updateDigest(messageDigest0, randomAccessFile2);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(messageDigest0);
        org.junit.Assert.assertEquals(messageDigest0.toString(), "SHA-1 Message Digest from SUN, <initialized>\r\n");
    }

    @Test
    public void test416() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test416");
        org.apache.commons.codec.binary.Base32 base32_0 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray3 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str4 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray3);
        boolean boolean6 = base32_0.isInAlphabet(byteArray3, false);
        long[] longArray7 = org.apache.commons.codec.digest.MurmurHash3.hash128(byteArray3);
        java.lang.String str8 = org.apache.commons.codec.binary.StringUtils.newStringUtf16(byteArray3);
        byte[] byteArray9 = org.apache.commons.codec.net.QuotedPrintableCodec.decodeQuotedPrintable(byteArray3);
        byte[] byteArray10 = org.apache.commons.codec.binary.BinaryCodec.toAsciiBytes(byteArray9);
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertArrayEquals(byteArray3, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "\000" + "'", str4, "\000");
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNotNull(longArray7);
        org.junit.Assert.assertArrayEquals(longArray7, new long[] { 8478934883742226405L, (-1017287513161072006L) });
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "\000" + "'", str8, "\000");
        org.junit.Assert.assertNotNull(byteArray9);
        org.junit.Assert.assertArrayEquals(byteArray9, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertNotNull(byteArray10);
        org.junit.Assert.assertArrayEquals(byteArray10, new byte[] { (byte) 48, (byte) 48, (byte) 48, (byte) 48, (byte) 48, (byte) 48, (byte) 48, (byte) 48, (byte) 48, (byte) 48, (byte) 48, (byte) 48, (byte) 48, (byte) 48, (byte) 48, (byte) 48 });
    }

    @Test
    public void test417() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test417");
        org.apache.commons.codec.language.Caverphone caverphone0 = new org.apache.commons.codec.language.Caverphone();
        java.lang.String str2 = caverphone0.caverphone("hi!");
        java.lang.String str4 = caverphone0.caverphone("h4V99TZaVFEkk");
        org.junit.Assert.assertEquals("'" + str2 + "' != '" + "AA11111111" + "'", str2, "AA11111111");
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "AFTSFK1111" + "'", str4, "AFTSFK1111");
    }

    @Test
    public void test418() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test418");
        org.apache.commons.codec.binary.Base32 base32_0 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray3 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str4 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray3);
        boolean boolean6 = base32_0.isInAlphabet(byteArray3, false);
        org.apache.commons.codec.binary.Base32 base32_7 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray10 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str11 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray10);
        boolean boolean13 = base32_7.isInAlphabet(byteArray10, false);
        byte[] byteArray14 = org.apache.commons.codec.digest.HmacUtils.hmacMd5(byteArray3, byteArray10);
        char[] charArray18 = null;
        byte[] byteArray19 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray18);
        char[] charArray20 = org.apache.commons.codec.binary.Hex.encodeHex(byteArray19);
        byte[] byteArray21 = org.apache.commons.codec.binary.Hex.decodeHex(charArray20);
        org.apache.commons.codec.language.Soundex soundex22 = new org.apache.commons.codec.language.Soundex(charArray20);
        org.apache.commons.codec.binary.Hex.encodeHex(byteArray10, (int) ' ', (-913662660), false, charArray20, 689063039);
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertArrayEquals(byteArray3, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "\000" + "'", str4, "\000");
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNotNull(byteArray10);
        org.junit.Assert.assertArrayEquals(byteArray10, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "\000" + "'", str11, "\000");
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
        org.junit.Assert.assertNotNull(byteArray14);
        org.junit.Assert.assertArrayEquals(byteArray14, new byte[] { (byte) -53, (byte) -62, (byte) 109, (byte) 108, (byte) 16, (byte) 34, (byte) -87, (byte) -93, (byte) 59, (byte) 95, (byte) 90, (byte) 76, (byte) 18, (byte) -71, (byte) -99, (byte) -51 });
        org.junit.Assert.assertNotNull(byteArray19);
        org.junit.Assert.assertArrayEquals(byteArray19, new byte[] {});
        org.junit.Assert.assertNotNull(charArray20);
        org.junit.Assert.assertArrayEquals(charArray20, new char[] {});
        org.junit.Assert.assertNotNull(byteArray21);
        org.junit.Assert.assertArrayEquals(byteArray21, new byte[] {});
    }

    @Test
    public void test419() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test419");
        org.apache.commons.codec.binary.Base32InputStream.Builder builder0 = new org.apache.commons.codec.binary.Base32InputStream.Builder();
        org.apache.commons.codec.binary.Base32InputStream base32InputStream1 = builder0.get();
        boolean boolean2 = base32InputStream1.markSupported();
        org.apache.commons.codec.binary.Base32InputStream base32InputStream4 = new org.apache.commons.codec.binary.Base32InputStream((java.io.InputStream) base32InputStream1, true);
        org.apache.commons.codec.binary.Base58InputStream base58InputStream5 = new org.apache.commons.codec.binary.Base58InputStream((java.io.InputStream) base32InputStream4);
        int int6 = base58InputStream5.available();
        org.junit.Assert.assertNotNull(base32InputStream1);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 1 + "'", int6 == 1);
    }

    @Test
    public void test420() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test420");
        org.apache.commons.codec.language.bm.NameType nameType0 = null;
        org.apache.commons.codec.language.bm.RuleType ruleType1 = null;
        org.apache.commons.codec.language.bm.PhoneticEngine phoneticEngine4 = new org.apache.commons.codec.language.bm.PhoneticEngine(nameType0, ruleType1, true, (-1));
        boolean boolean5 = phoneticEngine4.isConcat();
        org.apache.commons.codec.language.bm.Lang lang6 = phoneticEngine4.getLang();
        org.apache.commons.codec.language.bm.RuleType ruleType7 = phoneticEngine4.getRuleType();
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertNull(lang6);
        org.junit.Assert.assertNull(ruleType7);
    }

    @Test
    public void test421() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test421");
        org.apache.commons.codec.language.bm.Languages.LanguageSet languageSet0 = org.apache.commons.codec.language.bm.Languages.ANY_LANGUAGE;
        org.junit.Assert.assertNotNull(languageSet0);
    }

    @Test
    public void test422() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test422");
        byte[] byteArray1 = org.apache.commons.codec.digest.DigestUtils.sha512("CBADAFACDABACF");
        // The following exception was thrown during execution in test generation
        try {
            org.apache.commons.codec.net.PercentCodec percentCodec3 = new org.apache.commons.codec.net.PercentCodec(byteArray1, false);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: byte must be >= 0");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray1);
    }

    @Test
    public void test423() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test423");
        org.apache.commons.codec.binary.Base16OutputStream.Builder builder0 = org.apache.commons.codec.binary.Base16OutputStream.builder();
        org.apache.commons.codec.binary.Base16OutputStream base16OutputStream1 = builder0.get();
        org.apache.commons.codec.binary.Base32 base32_2 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray5 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str6 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray5);
        boolean boolean8 = base32_2.isInAlphabet(byteArray5, false);
        char[] charArray9 = null;
        byte[] byteArray10 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray9);
        boolean boolean12 = base32_2.isInAlphabet(byteArray10, true);
        byte[] byteArray13 = org.apache.commons.codec.digest.DigestUtils.sha1(byteArray10);
        java.lang.String str14 = org.apache.commons.codec.digest.DigestUtils.md5Hex(byteArray13);
        // The following exception was thrown during execution in test generation
        try {
            base16OutputStream1.write(byteArray13);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(builder0);
        org.junit.Assert.assertNotNull(base16OutputStream1);
        org.junit.Assert.assertNotNull(byteArray5);
        org.junit.Assert.assertArrayEquals(byteArray5, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "\000" + "'", str6, "\000");
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertNotNull(byteArray10);
        org.junit.Assert.assertArrayEquals(byteArray10, new byte[] {});
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + true + "'", boolean12 == true);
        org.junit.Assert.assertNotNull(byteArray13);
        org.junit.Assert.assertArrayEquals(byteArray13, new byte[] { (byte) -38, (byte) 57, (byte) -93, (byte) -18, (byte) 94, (byte) 107, (byte) 75, (byte) 13, (byte) 50, (byte) 85, (byte) -65, (byte) -17, (byte) -107, (byte) 96, (byte) 24, (byte) -112, (byte) -81, (byte) -40, (byte) 7, (byte) 9 });
        org.junit.Assert.assertEquals("'" + str14 + "' != '" + "4f71fccac43c73545ddd9cd772f37598" + "'", str14, "4f71fccac43c73545ddd9cd772f37598");
    }

    @Test
    public void test424() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test424");
        org.apache.commons.codec.language.bm.Languages.LanguageSet languageSet1 = null;
        org.apache.commons.codec.language.bm.Rule.Phoneme phoneme2 = new org.apache.commons.codec.language.bm.Rule.Phoneme((java.lang.CharSequence) "0000000000000000", languageSet1);
        org.apache.commons.codec.language.bm.Rule.Phoneme[] phonemeArray3 = new org.apache.commons.codec.language.bm.Rule.Phoneme[] { phoneme2 };
        java.util.ArrayList<org.apache.commons.codec.language.bm.Rule.Phoneme> phonemeList4 = new java.util.ArrayList<org.apache.commons.codec.language.bm.Rule.Phoneme>();
        boolean boolean5 = java.util.Collections.addAll((java.util.Collection<org.apache.commons.codec.language.bm.Rule.Phoneme>) phonemeList4, phonemeArray3);
        org.apache.commons.codec.language.bm.Rule.PhonemeList phonemeList6 = new org.apache.commons.codec.language.bm.Rule.PhonemeList((java.util.List<org.apache.commons.codec.language.bm.Rule.Phoneme>) phonemeList4);
        int int7 = phonemeList6.size();
        java.util.List<org.apache.commons.codec.language.bm.Rule.Phoneme> phonemeList8 = phonemeList6.getPhonemes();
        org.junit.Assert.assertNotNull(phonemeArray3);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 1 + "'", int7 == 1);
        org.junit.Assert.assertNotNull(phonemeList8);
    }

    @Test
    public void test425() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test425");
        org.apache.commons.codec.net.URLCodec uRLCodec1 = new org.apache.commons.codec.net.URLCodec("PCqE1NnZAFIA2");
        org.apache.commons.codec.binary.Base32 base32_2 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray5 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str6 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray5);
        boolean boolean8 = base32_2.isInAlphabet(byteArray5, false);
        org.apache.commons.codec.binary.Base32 base32_9 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray12 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str13 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray12);
        boolean boolean15 = base32_9.isInAlphabet(byteArray12, false);
        java.lang.String str16 = org.apache.commons.codec.digest.UnixCrypt.crypt(byteArray12);
        java.lang.String str17 = org.apache.commons.codec.digest.HmacUtils.hmacSha1Hex(byteArray5, byteArray12);
        byte[] byteArray18 = uRLCodec1.decode(byteArray5);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str20 = org.apache.commons.codec.digest.Sha2Crypt.sha256Crypt(byteArray18, "UTF-8");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Invalid salt value: UTF-8");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray5);
        org.junit.Assert.assertArrayEquals(byteArray5, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "\000" + "'", str6, "\000");
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertNotNull(byteArray12);
        org.junit.Assert.assertArrayEquals(byteArray12, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "\000" + "'", str13, "\000");
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
// flaky "71) test425(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str16 + "' != '" + "0OpdjXmE8Vndw" + "'", str16, "0OpdjXmE8Vndw");
        org.junit.Assert.assertEquals("'" + str17 + "' != '" + "310354661a5962d5b8cb76032d5a97e8aed7cf9f" + "'", str17, "310354661a5962d5b8cb76032d5a97e8aed7cf9f");
        org.junit.Assert.assertNotNull(byteArray18);
        org.junit.Assert.assertArrayEquals(byteArray18, new byte[] { (byte) 0, (byte) 0 });
    }

    @Test
    public void test426() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test426");
        java.nio.charset.Charset charset0 = org.apache.commons.codec.Charsets.UTF_16LE;
        org.junit.Assert.assertNotNull(charset0);
    }

    @Test
    public void test427() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test427");
        org.apache.commons.codec.binary.Base16.Builder builder0 = new org.apache.commons.codec.binary.Base16.Builder();
        char[] charArray2 = null;
        byte[] byteArray3 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray2);
        org.apache.commons.codec.binary.Base64 base64_4 = new org.apache.commons.codec.binary.Base64((int) '4', byteArray3);
        char[] charArray5 = null;
        byte[] byteArray6 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray5);
        char[] charArray7 = org.apache.commons.codec.binary.Hex.encodeHex(byteArray6);
        byte[] byteArray8 = org.apache.commons.codec.binary.Hex.decodeHex(charArray7);
        byte[] byteArray11 = base64_4.encode(byteArray8, (int) (byte) 10, 100);
        org.apache.commons.codec.binary.Base16.Builder builder12 = builder0.setLineSeparator(byteArray11);
        java.lang.String str13 = org.apache.commons.codec.digest.DigestUtils.md5Hex(byteArray11);
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertArrayEquals(byteArray3, new byte[] {});
        org.junit.Assert.assertNotNull(byteArray6);
        org.junit.Assert.assertArrayEquals(byteArray6, new byte[] {});
        org.junit.Assert.assertNotNull(charArray7);
        org.junit.Assert.assertArrayEquals(charArray7, new char[] {});
        org.junit.Assert.assertNotNull(byteArray8);
        org.junit.Assert.assertArrayEquals(byteArray8, new byte[] {});
        org.junit.Assert.assertNotNull(byteArray11);
        org.junit.Assert.assertArrayEquals(byteArray11, new byte[] {});
        org.junit.Assert.assertNotNull(builder12);
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "d41d8cd98f00b204e9800998ecf8427e" + "'", str13, "d41d8cd98f00b204e9800998ecf8427e");
    }

    @Test
    public void test428() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test428");
        java.io.OutputStream outputStream0 = null;
        org.apache.commons.codec.binary.Base64OutputStream base64OutputStream1 = new org.apache.commons.codec.binary.Base64OutputStream(outputStream0);
        org.apache.commons.codec.binary.Base32 base32_4 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray7 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str8 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray7);
        boolean boolean10 = base32_4.isInAlphabet(byteArray7, false);
        org.apache.commons.codec.binary.Base32 base32_11 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray14 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str15 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray14);
        boolean boolean17 = base32_11.isInAlphabet(byteArray14, false);
        byte[] byteArray18 = org.apache.commons.codec.digest.HmacUtils.hmacMd5(byteArray7, byteArray14);
        org.apache.commons.codec.binary.Base32OutputStream base32OutputStream19 = new org.apache.commons.codec.binary.Base32OutputStream((java.io.OutputStream) base64OutputStream1, false, 1, byteArray14);
        // The following exception was thrown during execution in test generation
        try {
            base64OutputStream1.flush();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray7);
        org.junit.Assert.assertArrayEquals(byteArray7, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "\000" + "'", str8, "\000");
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        org.junit.Assert.assertNotNull(byteArray14);
        org.junit.Assert.assertArrayEquals(byteArray14, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str15 + "' != '" + "\000" + "'", str15, "\000");
        org.junit.Assert.assertTrue("'" + boolean17 + "' != '" + false + "'", boolean17 == false);
        org.junit.Assert.assertNotNull(byteArray18);
        org.junit.Assert.assertArrayEquals(byteArray18, new byte[] { (byte) -53, (byte) -62, (byte) 109, (byte) 108, (byte) 16, (byte) 34, (byte) -87, (byte) -93, (byte) 59, (byte) 95, (byte) 90, (byte) 76, (byte) 18, (byte) -71, (byte) -99, (byte) -51 });
    }

    @Test
    public void test429() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test429");
        org.apache.commons.codec.net.QCodec qCodec0 = new org.apache.commons.codec.net.QCodec();
        boolean boolean1 = qCodec0.isEncodeBlanks();
        java.nio.charset.Charset charset2 = qCodec0.getCharset();
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
        org.junit.Assert.assertNotNull(charset2);
    }

    @Test
    public void test430() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test430");
        java.io.InputStream inputStream0 = null;
        org.apache.commons.codec.binary.Base16InputStream base16InputStream1 = new org.apache.commons.codec.binary.Base16InputStream(inputStream0);
        char[] charArray4 = null;
        byte[] byteArray5 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray4);
        char[] charArray6 = org.apache.commons.codec.binary.Hex.encodeHex(byteArray5);
        byte[] byteArray7 = org.apache.commons.codec.binary.Hex.decodeHex(charArray6);
        org.apache.commons.codec.CodecPolicy codecPolicy8 = null;
        org.apache.commons.codec.binary.Base64InputStream base64InputStream9 = new org.apache.commons.codec.binary.Base64InputStream((java.io.InputStream) base16InputStream1, true, (int) (short) 100, byteArray7, codecPolicy8);
        int int10 = base64InputStream9.available();
        byte[] byteArray13 = org.apache.commons.codec.digest.HmacUtils.hmacSha1("ISO-8859-1", "ASKTKTKPMK");
        // The following exception was thrown during execution in test generation
        try {
            int int16 = base64InputStream9.read(byteArray13, (int) (byte) -1, (int) (short) 100);
            org.junit.Assert.fail("Expected exception of type java.lang.IndexOutOfBoundsException; message: null");
        } catch (java.lang.IndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray5);
        org.junit.Assert.assertArrayEquals(byteArray5, new byte[] {});
        org.junit.Assert.assertNotNull(charArray6);
        org.junit.Assert.assertArrayEquals(charArray6, new char[] {});
        org.junit.Assert.assertNotNull(byteArray7);
        org.junit.Assert.assertArrayEquals(byteArray7, new byte[] {});
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 1 + "'", int10 == 1);
        org.junit.Assert.assertNotNull(byteArray13);
        org.junit.Assert.assertArrayEquals(byteArray13, new byte[] { (byte) -50, (byte) 80, (byte) 82, (byte) 66, (byte) 122, (byte) -66, (byte) 13, (byte) -50, (byte) -77, (byte) -21, (byte) -123, (byte) -24, (byte) 41, (byte) 5, (byte) -36, (byte) -123, (byte) 11, (byte) 15, (byte) 32, (byte) 59 });
    }

    @Test
    public void test431() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test431");
        org.apache.commons.codec.binary.Base16InputStream.Builder builder0 = new org.apache.commons.codec.binary.Base16InputStream.Builder();
        org.apache.commons.codec.binary.Base16InputStream base16InputStream1 = builder0.get();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str2 = org.apache.commons.codec.digest.DigestUtils.sha512_256Hex((java.io.InputStream) base16InputStream1);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: java.security.NoSuchAlgorithmException: SHA-512/256 MessageDigest not available");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(base16InputStream1);
    }

    @Test
    public void test432() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test432");
        java.io.InputStream inputStream0 = null;
        org.apache.commons.codec.binary.Base16InputStream base16InputStream1 = new org.apache.commons.codec.binary.Base16InputStream(inputStream0);
        char[] charArray4 = null;
        byte[] byteArray5 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray4);
        char[] charArray6 = org.apache.commons.codec.binary.Hex.encodeHex(byteArray5);
        byte[] byteArray7 = org.apache.commons.codec.binary.Hex.decodeHex(charArray6);
        org.apache.commons.codec.CodecPolicy codecPolicy8 = null;
        org.apache.commons.codec.binary.Base64InputStream base64InputStream9 = new org.apache.commons.codec.binary.Base64InputStream((java.io.InputStream) base16InputStream1, true, (int) (short) 100, byteArray7, codecPolicy8);
        int int10 = base64InputStream9.available();
        // The following exception was thrown during execution in test generation
        try {
            byte[] byteArray11 = org.apache.commons.codec.digest.DigestUtils.sha384((java.io.InputStream) base64InputStream9);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray5);
        org.junit.Assert.assertArrayEquals(byteArray5, new byte[] {});
        org.junit.Assert.assertNotNull(charArray6);
        org.junit.Assert.assertArrayEquals(charArray6, new char[] {});
        org.junit.Assert.assertNotNull(byteArray7);
        org.junit.Assert.assertArrayEquals(byteArray7, new byte[] {});
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 1 + "'", int10 == 1);
    }

    @Test
    public void test433() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test433");
        byte[] byteArray1 = org.apache.commons.codec.binary.StringUtils.getBytesIso8859_1("Crc16 [init=0xFFFF, crc=0xFFFF, xorOut=0x0000, crc^xorOut=0xFFFF]");
        java.lang.String str2 = org.apache.commons.codec.digest.DigestUtils.shaHex(byteArray1);
        byte[] byteArray3 = org.apache.commons.codec.binary.Base64.decodeBase64UrlSafe(byteArray1);
        org.junit.Assert.assertNotNull(byteArray1);
        org.junit.Assert.assertEquals("'" + str2 + "' != '" + "5574c9b84a224d6efecd951eb4462a84c4c1fe92" + "'", str2, "5574c9b84a224d6efecd951eb4462a84c4c1fe92");
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertArrayEquals(byteArray3, new byte[] { (byte) 10, (byte) -73, (byte) 53, (byte) -22, (byte) 41, (byte) -30 });
    }

    @Test
    public void test434() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test434");
        byte[] byteArray2 = org.apache.commons.codec.digest.HmacUtils.hmacMd5("$6$oWTfCqM4$VIqBhSK7v23O4tnjI9G0.ySCEVf2c5vaGxRmZoxTWiiZvbzGzEkGo0Wpjyh6Ih.x1txvfCdlGUL4IodLMR6a6/", "kK37euxiszvNU");
        org.junit.Assert.assertNotNull(byteArray2);
        org.junit.Assert.assertArrayEquals(byteArray2, new byte[] { (byte) -79, (byte) -46, (byte) 9, (byte) -19, (byte) 74, (byte) 43, (byte) 32, (byte) 92, (byte) 44, (byte) 97, (byte) -92, (byte) 51, (byte) 120, (byte) 62, (byte) -54, (byte) 91 });
    }

    @Test
    public void test435() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test435");
        org.apache.commons.codec.binary.Base64.Builder builder0 = new org.apache.commons.codec.binary.Base64.Builder();
        java.io.InputStream inputStream1 = null;
        org.apache.commons.codec.binary.Base16InputStream base16InputStream2 = new org.apache.commons.codec.binary.Base16InputStream(inputStream1);
        byte[] byteArray11 = new byte[] { (byte) 10, (byte) 0, (byte) -1, (byte) 0, (byte) 1, (byte) 100 };
        java.lang.String str12 = org.apache.commons.codec.digest.Md5Crypt.md5Crypt(byteArray11);
        org.apache.commons.codec.CodecPolicy codecPolicy13 = null;
        org.apache.commons.codec.binary.Base64InputStream base64InputStream14 = new org.apache.commons.codec.binary.Base64InputStream((java.io.InputStream) base16InputStream2, false, (int) 'a', byteArray11, codecPolicy13);
        org.apache.commons.codec.binary.Base64.Builder builder15 = builder0.setDecodeTable(byteArray11);
        org.apache.commons.codec.binary.Base32 base32_17 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray20 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str21 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray20);
        boolean boolean23 = base32_17.isInAlphabet(byteArray20, false);
        org.apache.commons.codec.binary.Base32 base32_24 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray27 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str28 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray27);
        boolean boolean30 = base32_24.isInAlphabet(byteArray27, false);
        java.lang.String str31 = org.apache.commons.codec.digest.UnixCrypt.crypt(byteArray27);
        java.lang.String str32 = org.apache.commons.codec.digest.HmacUtils.hmacSha1Hex(byteArray20, byteArray27);
        org.apache.commons.codec.binary.Base32 base32_33 = new org.apache.commons.codec.binary.Base32((int) '4', byteArray20);
        org.apache.commons.codec.binary.Base64.Builder builder34 = builder15.setDecodeTable(byteArray20);
        org.apache.commons.codec.binary.Base32 base32_35 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray38 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str39 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray38);
        boolean boolean41 = base32_35.isInAlphabet(byteArray38, false);
        org.apache.commons.codec.binary.Base32 base32_42 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray45 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str46 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray45);
        boolean boolean48 = base32_42.isInAlphabet(byteArray45, false);
        byte[] byteArray49 = base32_35.encode(byteArray45);
        org.apache.commons.codec.binary.Base64.Builder builder50 = builder34.setDecodeTable(byteArray45);
        org.apache.commons.codec.binary.Base64.DecodeTableFormat decodeTableFormat51 = org.apache.commons.codec.binary.Base64.DecodeTableFormat.MIXED;
        org.apache.commons.codec.binary.Base64.Builder builder52 = builder50.setDecodeTableFormat(decodeTableFormat51);
        org.junit.Assert.assertNotNull(byteArray11);
        org.junit.Assert.assertArrayEquals(byteArray11, new byte[] { (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0 });
// flaky "72) test435(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str12 + "' != '" + "$1$9IAUNG19$XFqfV8wvDOoHZq5BoEZl10" + "'", str12, "$1$9IAUNG19$XFqfV8wvDOoHZq5BoEZl10");
        org.junit.Assert.assertNotNull(builder15);
        org.junit.Assert.assertNotNull(byteArray20);
        org.junit.Assert.assertArrayEquals(byteArray20, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str21 + "' != '" + "\000" + "'", str21, "\000");
        org.junit.Assert.assertTrue("'" + boolean23 + "' != '" + false + "'", boolean23 == false);
        org.junit.Assert.assertNotNull(byteArray27);
        org.junit.Assert.assertArrayEquals(byteArray27, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str28 + "' != '" + "\000" + "'", str28, "\000");
        org.junit.Assert.assertTrue("'" + boolean30 + "' != '" + false + "'", boolean30 == false);
// flaky "8) test435(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str31 + "' != '" + "dGqinoylct8gc" + "'", str31, "dGqinoylct8gc");
        org.junit.Assert.assertEquals("'" + str32 + "' != '" + "310354661a5962d5b8cb76032d5a97e8aed7cf9f" + "'", str32, "310354661a5962d5b8cb76032d5a97e8aed7cf9f");
        org.junit.Assert.assertNotNull(builder34);
        org.junit.Assert.assertNotNull(byteArray38);
        org.junit.Assert.assertArrayEquals(byteArray38, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str39 + "' != '" + "\000" + "'", str39, "\000");
        org.junit.Assert.assertTrue("'" + boolean41 + "' != '" + false + "'", boolean41 == false);
        org.junit.Assert.assertNotNull(byteArray45);
        org.junit.Assert.assertArrayEquals(byteArray45, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str46 + "' != '" + "\000" + "'", str46, "\000");
        org.junit.Assert.assertTrue("'" + boolean48 + "' != '" + false + "'", boolean48 == false);
        org.junit.Assert.assertNotNull(byteArray49);
        org.junit.Assert.assertArrayEquals(byteArray49, new byte[] { (byte) 65, (byte) 65, (byte) 65, (byte) 65, (byte) 61, (byte) 61, (byte) 61, (byte) 61 });
        org.junit.Assert.assertNotNull(builder50);
        org.junit.Assert.assertTrue("'" + decodeTableFormat51 + "' != '" + org.apache.commons.codec.binary.Base64.DecodeTableFormat.MIXED + "'", decodeTableFormat51.equals(org.apache.commons.codec.binary.Base64.DecodeTableFormat.MIXED));
        org.junit.Assert.assertNotNull(builder52);
    }

    @Test
    public void test436() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test436");
        org.apache.commons.codec.language.bm.NameType nameType0 = null;
        org.apache.commons.codec.language.bm.RuleType ruleType1 = null;
        org.apache.commons.codec.language.bm.PhoneticEngine phoneticEngine4 = new org.apache.commons.codec.language.bm.PhoneticEngine(nameType0, ruleType1, true, (-1));
        org.apache.commons.codec.language.bm.Lang lang5 = phoneticEngine4.getLang();
        int int6 = phoneticEngine4.getMaxPhonemes();
        org.junit.Assert.assertNull(lang5);
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + (-1) + "'", int6 == (-1));
    }

    @Test
    public void test437() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test437");
        org.apache.commons.codec.binary.Base32 base32_0 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray3 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str4 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray3);
        boolean boolean6 = base32_0.isInAlphabet(byteArray3, false);
        byte[] byteArray9 = org.apache.commons.codec.digest.HmacUtils.hmacSha512("310354661a5962d5b8cb76032d5a97e8aed7cf9f", "");
        byte[] byteArray10 = base32_0.encode(byteArray9);
        org.apache.commons.codec.digest.Blake3 blake3_11 = org.apache.commons.codec.digest.Blake3.initKeyDerivationFunction(byteArray10);
        byte[] byteArray12 = org.apache.commons.codec.binary.Base64.encodeBase64(byteArray10);
        java.lang.String str13 = org.apache.commons.codec.binary.StringUtils.newStringUtf8(byteArray12);
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertArrayEquals(byteArray3, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "\000" + "'", str4, "\000");
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNotNull(byteArray9);
        org.junit.Assert.assertNotNull(byteArray10);
        org.junit.Assert.assertNotNull(blake3_11);
        org.junit.Assert.assertNotNull(byteArray12);
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "M1VQR0VEVVBVNVJQQUdCR1RMMkJIMzc3UkpZUE9ORk4yQ0NHMjZCVVJUU09UQkJPWUM0Q0lFT1M1SUJMNkpJT0FYTlgyU0Y1RDZTSFpRNDIzWFdYREtONVROVDRFU0JONk5LVjNBUT0=" + "'", str13, "M1VQR0VEVVBVNVJQQUdCR1RMMkJIMzc3UkpZUE9ORk4yQ0NHMjZCVVJUU09UQkJPWUM0Q0lFT1M1SUJMNkpJT0FYTlgyU0Y1RDZTSFpRNDIzWFdYREtONVROVDRFU0JONk5LVjNBUT0=");
    }

    @Test
    public void test438() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test438");
        org.apache.commons.codec.net.QuotedPrintableCodec quotedPrintableCodec0 = new org.apache.commons.codec.net.QuotedPrintableCodec();
    }

    @Test
    public void test439() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test439");
        org.apache.commons.codec.language.bm.Languages.LanguageSet languageSet1 = null;
        org.apache.commons.codec.language.bm.Rule.Phoneme phoneme2 = new org.apache.commons.codec.language.bm.Rule.Phoneme((java.lang.CharSequence) "0000000000000000", languageSet1);
        org.apache.commons.codec.language.bm.Rule.Phoneme[] phonemeArray3 = new org.apache.commons.codec.language.bm.Rule.Phoneme[] { phoneme2 };
        java.util.ArrayList<org.apache.commons.codec.language.bm.Rule.Phoneme> phonemeList4 = new java.util.ArrayList<org.apache.commons.codec.language.bm.Rule.Phoneme>();
        boolean boolean5 = java.util.Collections.addAll((java.util.Collection<org.apache.commons.codec.language.bm.Rule.Phoneme>) phonemeList4, phonemeArray3);
        org.apache.commons.codec.language.bm.Rule.PhonemeList phonemeList6 = new org.apache.commons.codec.language.bm.Rule.PhonemeList((java.util.List<org.apache.commons.codec.language.bm.Rule.Phoneme>) phonemeList4);
        int int7 = phonemeList6.size();
        int int8 = phonemeList6.size();
        org.junit.Assert.assertNotNull(phonemeArray3);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 1 + "'", int7 == 1);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 1 + "'", int8 == 1);
    }

    @Test
    public void test440() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test440");
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str1 = org.apache.commons.codec.digest.DigestUtils.sha3_384Hex("KoFFaeBwyIBRM");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: java.security.NoSuchAlgorithmException: SHA3-384 MessageDigest not available");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test441() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test441");
        int[] intArray0 = org.apache.commons.codec.digest.Crc16.getModbusTable();
        org.junit.Assert.assertNotNull(intArray0);
    }

    @Test
    public void test442() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test442");
        org.apache.commons.codec.binary.Base32 base32_0 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray3 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str4 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray3);
        boolean boolean6 = base32_0.isInAlphabet(byteArray3, false);
        byte[] byteArray9 = org.apache.commons.codec.digest.HmacUtils.hmacSha512("310354661a5962d5b8cb76032d5a97e8aed7cf9f", "");
        byte[] byteArray10 = base32_0.encode(byteArray9);
        org.apache.commons.codec.digest.Blake3 blake3_11 = org.apache.commons.codec.digest.Blake3.initKeyDerivationFunction(byteArray10);
        org.apache.commons.codec.digest.Blake3 blake3_12 = blake3_11.reset();
        org.apache.commons.codec.binary.Base32 base32_13 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray16 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str17 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray16);
        boolean boolean19 = base32_13.isInAlphabet(byteArray16, false);
        char[] charArray20 = null;
        byte[] byteArray21 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray20);
        boolean boolean23 = base32_13.isInAlphabet(byteArray21, true);
        byte[] byteArray26 = org.apache.commons.codec.digest.HmacUtils.hmacSha512("310354661a5962d5b8cb76032d5a97e8aed7cf9f", "");
        boolean boolean28 = base32_13.isInAlphabet(byteArray26, true);
        byte[] byteArray29 = org.apache.commons.codec.digest.DigestUtils.md5(byteArray26);
        org.apache.commons.codec.digest.Blake3 blake3_30 = blake3_12.doFinalize(byteArray26);
        org.apache.commons.codec.binary.Base32InputStream.Builder builder31 = new org.apache.commons.codec.binary.Base32InputStream.Builder();
        org.apache.commons.codec.binary.Base32InputStream base32InputStream32 = builder31.get();
        boolean boolean33 = base32InputStream32.markSupported();
        org.apache.commons.codec.binary.Base32InputStream base32InputStream35 = new org.apache.commons.codec.binary.Base32InputStream((java.io.InputStream) base32InputStream32, true);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str36 = org.apache.commons.codec.digest.HmacUtils.hmacSha512Hex(byteArray26, (java.io.InputStream) base32InputStream35);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertArrayEquals(byteArray3, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "\000" + "'", str4, "\000");
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNotNull(byteArray9);
        org.junit.Assert.assertNotNull(byteArray10);
        org.junit.Assert.assertNotNull(blake3_11);
        org.junit.Assert.assertNotNull(blake3_12);
        org.junit.Assert.assertNotNull(byteArray16);
        org.junit.Assert.assertArrayEquals(byteArray16, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str17 + "' != '" + "\000" + "'", str17, "\000");
        org.junit.Assert.assertTrue("'" + boolean19 + "' != '" + false + "'", boolean19 == false);
        org.junit.Assert.assertNotNull(byteArray21);
        org.junit.Assert.assertArrayEquals(byteArray21, new byte[] {});
        org.junit.Assert.assertTrue("'" + boolean23 + "' != '" + true + "'", boolean23 == true);
        org.junit.Assert.assertNotNull(byteArray26);
        org.junit.Assert.assertTrue("'" + boolean28 + "' != '" + false + "'", boolean28 == false);
        org.junit.Assert.assertNotNull(byteArray29);
        org.junit.Assert.assertArrayEquals(byteArray29, new byte[] { (byte) -104, (byte) -88, (byte) -7, (byte) -67, (byte) 109, (byte) 48, (byte) -26, (byte) 75, (byte) 93, (byte) -93, (byte) -75, (byte) -48, (byte) 7, (byte) 118, (byte) 106, (byte) 87 });
        org.junit.Assert.assertNotNull(blake3_30);
        org.junit.Assert.assertNotNull(base32InputStream32);
        org.junit.Assert.assertTrue("'" + boolean33 + "' != '" + false + "'", boolean33 == false);
    }

    @Test
    public void test443() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test443");
        org.apache.commons.codec.language.RefinedSoundex refinedSoundex1 = new org.apache.commons.codec.language.RefinedSoundex("SHA3-384");
    }

    @Test
    public void test444() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test444");
        org.apache.commons.codec.binary.Base32 base32_0 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray3 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str4 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray3);
        boolean boolean6 = base32_0.isInAlphabet(byteArray3, false);
        org.apache.commons.codec.binary.Base32 base32_7 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray10 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str11 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray10);
        boolean boolean13 = base32_7.isInAlphabet(byteArray10, false);
        java.lang.String str14 = org.apache.commons.codec.digest.UnixCrypt.crypt(byteArray10);
        java.lang.String str15 = org.apache.commons.codec.digest.HmacUtils.hmacSha1Hex(byteArray3, byteArray10);
        java.lang.String str16 = org.apache.commons.codec.binary.BinaryCodec.toAsciiString(byteArray10);
        javax.crypto.Mac mac17 = org.apache.commons.codec.digest.HmacUtils.getHmacSha384(byteArray10);
        java.lang.String str18 = org.apache.commons.codec.binary.BinaryCodec.toAsciiString(byteArray10);
        org.apache.commons.codec.digest.Blake3 blake3_19 = org.apache.commons.codec.digest.Blake3.initKeyDerivationFunction(byteArray10);
        org.apache.commons.codec.binary.Base32 base32_20 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray23 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str24 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray23);
        boolean boolean26 = base32_20.isInAlphabet(byteArray23, false);
        org.apache.commons.codec.binary.Base32 base32_27 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray30 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str31 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray30);
        boolean boolean33 = base32_27.isInAlphabet(byteArray30, false);
        java.lang.String str34 = org.apache.commons.codec.digest.UnixCrypt.crypt(byteArray30);
        java.lang.String str35 = org.apache.commons.codec.digest.HmacUtils.hmacSha1Hex(byteArray23, byteArray30);
        java.lang.String str36 = org.apache.commons.codec.binary.BinaryCodec.toAsciiString(byteArray30);
        javax.crypto.Mac mac37 = org.apache.commons.codec.digest.HmacUtils.getHmacSha384(byteArray30);
        java.lang.String str38 = org.apache.commons.codec.binary.BinaryCodec.toAsciiString(byteArray30);
        org.apache.commons.codec.digest.Blake3 blake3_39 = org.apache.commons.codec.digest.Blake3.initKeyDerivationFunction(byteArray30);
        org.apache.commons.codec.digest.Blake3 blake3_40 = blake3_19.update(byteArray30);
        byte[] byteArray42 = org.apache.commons.codec.binary.StringUtils.getBytesIso8859_1("c39394a96ebd177a239a817fe8453ab0");
        org.apache.commons.codec.binary.Base32 base32_43 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray46 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str47 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray46);
        boolean boolean49 = base32_43.isInAlphabet(byteArray46, false);
        org.apache.commons.codec.binary.Base32 base32_50 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray53 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str54 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray53);
        boolean boolean56 = base32_50.isInAlphabet(byteArray53, false);
        java.lang.String str57 = org.apache.commons.codec.digest.UnixCrypt.crypt(byteArray53);
        java.lang.String str58 = org.apache.commons.codec.digest.HmacUtils.hmacSha1Hex(byteArray46, byteArray53);
        java.lang.String str59 = org.apache.commons.codec.binary.BinaryCodec.toAsciiString(byteArray53);
        java.lang.String str60 = org.apache.commons.codec.digest.HmacUtils.hmacSha1Hex(byteArray42, byteArray53);
        org.apache.commons.codec.digest.Blake3 blake3_61 = blake3_19.doFinalize(byteArray42);
        byte[] byteArray63 = blake3_19.doFinalize((int) (byte) 100);
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertArrayEquals(byteArray3, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "\000" + "'", str4, "\000");
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNotNull(byteArray10);
        org.junit.Assert.assertArrayEquals(byteArray10, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "\000" + "'", str11, "\000");
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
// flaky "73) test444(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str14 + "' != '" + "XzoUM//pXAvA." + "'", str14, "XzoUM//pXAvA.");
        org.junit.Assert.assertEquals("'" + str15 + "' != '" + "310354661a5962d5b8cb76032d5a97e8aed7cf9f" + "'", str15, "310354661a5962d5b8cb76032d5a97e8aed7cf9f");
        org.junit.Assert.assertEquals("'" + str16 + "' != '" + "0000000000000000" + "'", str16, "0000000000000000");
        org.junit.Assert.assertNotNull(mac17);
        org.junit.Assert.assertEquals("'" + str18 + "' != '" + "0000000000000000" + "'", str18, "0000000000000000");
        org.junit.Assert.assertNotNull(blake3_19);
        org.junit.Assert.assertNotNull(byteArray23);
        org.junit.Assert.assertArrayEquals(byteArray23, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str24 + "' != '" + "\000" + "'", str24, "\000");
        org.junit.Assert.assertTrue("'" + boolean26 + "' != '" + false + "'", boolean26 == false);
        org.junit.Assert.assertNotNull(byteArray30);
        org.junit.Assert.assertArrayEquals(byteArray30, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str31 + "' != '" + "\000" + "'", str31, "\000");
        org.junit.Assert.assertTrue("'" + boolean33 + "' != '" + false + "'", boolean33 == false);
// flaky "9) test444(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str34 + "' != '" + "S8BnwY8B4Xqs." + "'", str34, "S8BnwY8B4Xqs.");
        org.junit.Assert.assertEquals("'" + str35 + "' != '" + "310354661a5962d5b8cb76032d5a97e8aed7cf9f" + "'", str35, "310354661a5962d5b8cb76032d5a97e8aed7cf9f");
        org.junit.Assert.assertEquals("'" + str36 + "' != '" + "0000000000000000" + "'", str36, "0000000000000000");
        org.junit.Assert.assertNotNull(mac37);
        org.junit.Assert.assertEquals("'" + str38 + "' != '" + "0000000000000000" + "'", str38, "0000000000000000");
        org.junit.Assert.assertNotNull(blake3_39);
        org.junit.Assert.assertNotNull(blake3_40);
        org.junit.Assert.assertNotNull(byteArray42);
        org.junit.Assert.assertNotNull(byteArray46);
        org.junit.Assert.assertArrayEquals(byteArray46, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str47 + "' != '" + "\000" + "'", str47, "\000");
        org.junit.Assert.assertTrue("'" + boolean49 + "' != '" + false + "'", boolean49 == false);
        org.junit.Assert.assertNotNull(byteArray53);
        org.junit.Assert.assertArrayEquals(byteArray53, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str54 + "' != '" + "\000" + "'", str54, "\000");
        org.junit.Assert.assertTrue("'" + boolean56 + "' != '" + false + "'", boolean56 == false);
// flaky "2) test444(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str57 + "' != '" + "VCcAyVxWP9KII" + "'", str57, "VCcAyVxWP9KII");
        org.junit.Assert.assertEquals("'" + str58 + "' != '" + "310354661a5962d5b8cb76032d5a97e8aed7cf9f" + "'", str58, "310354661a5962d5b8cb76032d5a97e8aed7cf9f");
        org.junit.Assert.assertEquals("'" + str59 + "' != '" + "0000000000000000" + "'", str59, "0000000000000000");
        org.junit.Assert.assertEquals("'" + str60 + "' != '" + "55351564fecb450a30b6ab9f075f0e63a5f492b6" + "'", str60, "55351564fecb450a30b6ab9f075f0e63a5f492b6");
        org.junit.Assert.assertNotNull(blake3_61);
        org.junit.Assert.assertNotNull(byteArray63);
    }

    @Test
    public void test445() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test445");
        java.security.MessageDigest messageDigest0 = org.apache.commons.codec.digest.DigestUtils.getSha256Digest();
        org.junit.Assert.assertNotNull(messageDigest0);
        org.junit.Assert.assertEquals(messageDigest0.toString(), "SHA-256 Message Digest from SUN, <initialized>\r\n");
    }

    @Test
    public void test446() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test446");
        byte[] byteArray0 = null;
        java.lang.String str2 = org.apache.commons.codec.binary.StringUtils.newString(byteArray0, "$1$HI8xDvr2$942Qiq7J2dVKsyWVxEqpg.");
        org.junit.Assert.assertNull(str2);
    }

    @Test
    public void test447() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test447");
        java.nio.charset.Charset charset0 = null;
        org.apache.commons.codec.net.QuotedPrintableCodec quotedPrintableCodec2 = new org.apache.commons.codec.net.QuotedPrintableCodec(charset0, true);
        org.apache.commons.codec.net.QuotedPrintableCodec quotedPrintableCodec5 = new org.apache.commons.codec.net.QuotedPrintableCodec(true);
        java.lang.String str7 = quotedPrintableCodec5.decode("207b9c1af4dbe7359a718262cce9251a");
        org.apache.commons.codec.StringEncoderComparator stringEncoderComparator8 = new org.apache.commons.codec.StringEncoderComparator((org.apache.commons.codec.StringEncoder) quotedPrintableCodec5);
        java.nio.charset.Charset charset9 = quotedPrintableCodec5.getCharset();
        java.lang.String str10 = quotedPrintableCodec2.decode("fOLvLwTUZovHA", charset9);
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "207b9c1af4dbe7359a718262cce9251a" + "'", str7, "207b9c1af4dbe7359a718262cce9251a");
        org.junit.Assert.assertNotNull(charset9);
        org.junit.Assert.assertEquals("'" + str10 + "' != '" + "fOLvLwTUZovHA" + "'", str10, "fOLvLwTUZovHA");
    }

    @Test
    public void test448() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test448");
        org.apache.commons.codec.digest.Crc16 crc16_0 = org.apache.commons.codec.digest.Crc16.modbus();
        java.lang.String str1 = crc16_0.toString();
        crc16_0.update((int) (short) 100);
        org.apache.commons.codec.binary.Base32 base32_4 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray7 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str8 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray7);
        boolean boolean10 = base32_4.isInAlphabet(byteArray7, false);
        byte[] byteArray13 = org.apache.commons.codec.digest.HmacUtils.hmacSha512("310354661a5962d5b8cb76032d5a97e8aed7cf9f", "");
        byte[] byteArray14 = base32_4.encode(byteArray13);
        org.apache.commons.codec.digest.Blake3 blake3_15 = org.apache.commons.codec.digest.Blake3.initKeyDerivationFunction(byteArray14);
        crc16_0.update(byteArray14, 100, 0);
        crc16_0.reset();
        char[] charArray20 = null;
        byte[] byteArray21 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(charArray20);
        char[] charArray22 = org.apache.commons.codec.binary.Hex.encodeHex(byteArray21);
        java.lang.String str23 = org.apache.commons.codec.binary.BinaryCodec.toAsciiString(byteArray21);
        crc16_0.update(byteArray21, 0, (int) (byte) -1);
        byte[] byteArray27 = org.apache.commons.codec.digest.DigestUtils.md5(byteArray21);
        org.junit.Assert.assertNotNull(crc16_0);
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "Crc16 [init=0xFFFF, crc=0xFFFF, xorOut=0x0000, crc^xorOut=0xFFFF]" + "'", str1, "Crc16 [init=0xFFFF, crc=0xFFFF, xorOut=0x0000, crc^xorOut=0xFFFF]");
        org.junit.Assert.assertNotNull(byteArray7);
        org.junit.Assert.assertArrayEquals(byteArray7, new byte[] { (byte) 0, (byte) 0 });
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "\000" + "'", str8, "\000");
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        org.junit.Assert.assertNotNull(byteArray13);
        org.junit.Assert.assertNotNull(byteArray14);
        org.junit.Assert.assertNotNull(blake3_15);
        org.junit.Assert.assertNotNull(byteArray21);
        org.junit.Assert.assertArrayEquals(byteArray21, new byte[] {});
        org.junit.Assert.assertNotNull(charArray22);
        org.junit.Assert.assertArrayEquals(charArray22, new char[] {});
        org.junit.Assert.assertEquals("'" + str23 + "' != '" + "" + "'", str23, "");
        org.junit.Assert.assertNotNull(byteArray27);
        org.junit.Assert.assertArrayEquals(byteArray27, new byte[] { (byte) -44, (byte) 29, (byte) -116, (byte) -39, (byte) -113, (byte) 0, (byte) -78, (byte) 4, (byte) -23, (byte) -128, (byte) 9, (byte) -104, (byte) -20, (byte) -8, (byte) 66, (byte) 126 });
    }
}
