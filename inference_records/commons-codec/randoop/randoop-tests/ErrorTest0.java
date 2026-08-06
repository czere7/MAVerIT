import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ErrorTest0 {

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
    public void test01() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ErrorTest0.test01");
        org.apache.commons.codec.digest.HmacUtils hmacUtils0 = new org.apache.commons.codec.digest.HmacUtils();
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        java.lang.String str2 = hmacUtils0.hmacHex("");
    }

    @Test
    public void test02() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ErrorTest0.test02");
        org.apache.commons.codec.digest.HmacUtils hmacUtils0 = new org.apache.commons.codec.digest.HmacUtils();
        byte[] byteArray3 = org.apache.commons.codec.digest.HmacUtils.hmacSha384("\000", "SHA-512");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        java.lang.String str4 = hmacUtils0.hmacHex(byteArray3);
    }

    @Test
    public void test03() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ErrorTest0.test03");
        org.apache.commons.codec.digest.HmacUtils hmacUtils0 = new org.apache.commons.codec.digest.HmacUtils();
        org.apache.commons.codec.binary.Base32 base32_1 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray4 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str5 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray4);
        boolean boolean7 = base32_1.isInAlphabet(byteArray4, false);
        org.apache.commons.codec.binary.Base32 base32_8 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray11 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str12 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray11);
        boolean boolean14 = base32_8.isInAlphabet(byteArray11, false);
        org.apache.commons.codec.binary.Base32 base32_15 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray18 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str19 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray18);
        boolean boolean21 = base32_15.isInAlphabet(byteArray18, false);
        byte[] byteArray22 = base32_8.encode(byteArray18);
        java.lang.String str23 = org.apache.commons.codec.digest.HmacUtils.hmacMd5Hex(byteArray4, byteArray22);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        byte[] byteArray24 = hmacUtils0.hmac(byteArray22);
    }

    @Test
    public void test04() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ErrorTest0.test04");
        org.apache.commons.codec.digest.HmacUtils hmacUtils0 = new org.apache.commons.codec.digest.HmacUtils();
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
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        java.lang.String str17 = hmacUtils0.hmacHex(byteArray4);
    }

    @Test
    public void test05() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ErrorTest0.test05");
        org.apache.commons.codec.digest.HmacUtils hmacUtils0 = new org.apache.commons.codec.digest.HmacUtils();
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        byte[] byteArray2 = hmacUtils0.hmac("G30LGL3wppDqc");
    }

    @Test
    public void test06() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ErrorTest0.test06");
        org.apache.commons.codec.digest.Crc16.Builder builder0 = new org.apache.commons.codec.digest.Crc16.Builder();
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.commons.codec.digest.Crc16 crc16_1 = builder0.get();
    }

    @Test
    public void test07() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ErrorTest0.test07");
        org.apache.commons.codec.digest.HmacUtils hmacUtils0 = new org.apache.commons.codec.digest.HmacUtils();
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        java.lang.String str2 = hmacUtils0.hmacHex("SHAKE256-512");
    }

    @Test
    public void test08() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ErrorTest0.test08");
        org.apache.commons.codec.StringEncoderComparator stringEncoderComparator0 = new org.apache.commons.codec.StringEncoderComparator();
        org.apache.commons.codec.language.bm.Languages.LanguageSet languageSet2 = org.apache.commons.codec.language.bm.Languages.NO_LANGUAGES;
        boolean boolean4 = languageSet2.contains("Crc16 [init=0x0000, crc=0x0000, xorOut=0x0000, crc^xorOut=0x0000]");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        int int5 = stringEncoderComparator0.compare((java.lang.Object) "OsQTcDyHcPmog", (java.lang.Object) boolean4);
    }

    @Test
    public void test09() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ErrorTest0.test09");
        org.apache.commons.codec.binary.Base32InputStream.Builder builder0 = new org.apache.commons.codec.binary.Base32InputStream.Builder();
        org.apache.commons.codec.binary.Base32InputStream base32InputStream1 = builder0.get();
        boolean boolean2 = base32InputStream1.markSupported();
        org.apache.commons.codec.binary.Base32InputStream base32InputStream4 = new org.apache.commons.codec.binary.Base32InputStream((java.io.InputStream) base32InputStream1, true);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        byte[] byteArray5 = org.apache.commons.codec.digest.DigestUtils.sha((java.io.InputStream) base32InputStream1);
    }

    @Test
    public void test10() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ErrorTest0.test10");
        org.apache.commons.codec.binary.Base16InputStream.Builder builder0 = new org.apache.commons.codec.binary.Base16InputStream.Builder();
        org.apache.commons.codec.binary.Base16InputStream base16InputStream1 = builder0.get();
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        java.lang.String str2 = org.apache.commons.codec.digest.DigestUtils.sha512Hex((java.io.InputStream) base16InputStream1);
    }

    @Test
    public void test11() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ErrorTest0.test11");
        org.apache.commons.codec.binary.Base32InputStream.Builder builder0 = new org.apache.commons.codec.binary.Base32InputStream.Builder();
        org.apache.commons.codec.binary.Base32InputStream base32InputStream1 = builder0.get();
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        byte[] byteArray2 = org.apache.commons.codec.digest.DigestUtils.md5((java.io.InputStream) base32InputStream1);
    }

    @Test
    public void test12() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ErrorTest0.test12");
        org.apache.commons.codec.digest.HmacUtils hmacUtils0 = new org.apache.commons.codec.digest.HmacUtils();
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        java.lang.String str2 = hmacUtils0.hmacHex("$1$rG7hrafq$ciRUdzVGvUfnSO66QafX10");
    }

    @Test
    public void test13() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ErrorTest0.test13");
        org.apache.commons.codec.binary.Base32 base32_0 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray3 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str4 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray3);
        boolean boolean6 = base32_0.isInAlphabet(byteArray3, false);
        byte[] byteArray9 = org.apache.commons.codec.digest.HmacUtils.hmacSha512("310354661a5962d5b8cb76032d5a97e8aed7cf9f", "");
        byte[] byteArray10 = base32_0.encode(byteArray9);
        org.apache.commons.codec.binary.Base16InputStream.Builder builder11 = new org.apache.commons.codec.binary.Base16InputStream.Builder();
        org.apache.commons.codec.binary.Base16InputStream base16InputStream12 = builder11.get();
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        byte[] byteArray13 = org.apache.commons.codec.digest.HmacUtils.hmacSha1(byteArray10, (java.io.InputStream) base16InputStream12);
    }

    @Test
    public void test14() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ErrorTest0.test14");
        org.apache.commons.codec.binary.Base32 base32_0 = new org.apache.commons.codec.binary.Base32();
        byte[] byteArray3 = new byte[] { (byte) 0, (byte) 0 };
        java.lang.String str4 = org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byteArray3);
        boolean boolean6 = base32_0.isInAlphabet(byteArray3, false);
        java.lang.String str7 = org.apache.commons.codec.digest.UnixCrypt.crypt(byteArray3);
        byte[] byteArray8 = org.apache.commons.codec.binary.BinaryCodec.fromAscii(byteArray3);
        boolean boolean9 = org.apache.commons.codec.binary.Base64.isBase64(byteArray3);
        org.apache.commons.codec.binary.Base16InputStream.Builder builder10 = new org.apache.commons.codec.binary.Base16InputStream.Builder();
        org.apache.commons.codec.binary.Base16InputStream base16InputStream11 = builder10.get();
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        java.lang.String str12 = org.apache.commons.codec.digest.HmacUtils.hmacSha1Hex(byteArray3, (java.io.InputStream) base16InputStream11);
    }
}

