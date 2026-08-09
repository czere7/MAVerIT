package tools.jackson.core.io;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.Arrays;

import org.junit.Test;

import static org.junit.Assert.*;

public class SerializedStringTest {

    /** Simple helper to create a serialized string with given value */
    private SerializedString ss(String v) {
        return new SerializedString(v);
    }

    @Test(expected = NullPointerException.class)
    public void constructorNullThrows() {
        new SerializedString(null);
    }

    @Test
    public void getValueAndCharLength() {
        String val = "test";
        SerializedString s = ss(val);
        assertEquals("value", val, s.getValue());
        assertEquals("char length", val.length(), s.charLength());
    }

    @Test
    public void cachingAsQuotedChars() {
        String v = "a\nb";
        SerializedString s = ss(v);
        char[] first = s.asQuotedChars();
        char[] second = s.asQuotedChars();
        assertSame("quoted chars cached", first, second);
    }

    @Test
    public void quotingSpecialCharacters() {
        // string containing newline, double-quote and backslash
        String v = "a\nb\"c\\d";
        SerializedString s = ss(v);

        char[] quoted = s.asQuotedChars();
        assertEquals("quoted length", 10, quoted.length);
        // expected: a \ n b \" c \\ d
        char[] expected = new char[]{'a', '\\', 'n', 'b', '\\', '"', 'c', '\\', '\\', 'd'};
        assertArrayEquals(expected, quoted);

        byte[] quotedBytes = s.asQuotedUTF8();
        // same bytes as UTF-8 representation of the quoted chars
        byte[] expectedBytes = new byte[]{'a', '\\', 'n', 'b', '\\', '"', 'c', '\\', '\\', 'd'};
        assertArrayEquals(expectedBytes, quotedBytes);
    }

    @Test
    public void unquotedUtf8Encoding() {
        String v = "αβγ"; // non-ASCII characters
        SerializedString s = ss(v);
        byte[] unq = s.asUnquotedUTF8();
        // Verify that bytes match Java's UTF-8 encoding
        assertArrayEquals("unquoted utf8", v.getBytes(java.nio.charset.StandardCharsets.UTF_8), unq);
    }

    @Test
    public void appendMethodsSuccessAndFailure() {
        String v = "abcd";
        SerializedString s = ss(v);

        // quoted append success
        char[] buf1 = new char[10];
        int len1 = s.appendQuoted(buf1, 0);
        assertEquals("quoted append length", v.length(), len1);
        char[] fromBuf = new char[len1];
        System.arraycopy(buf1, 0, fromBuf, 0, len1);
        assertArrayEquals("quoted content", s.asQuotedChars(), fromBuf);

        // quoted append failure due to insufficient space
        int fail1 = s.appendQuoted(new char[2], 0);
        assertEquals(-1, fail1);

        // unquoted UTF8 append success
        byte[] buf2 = new byte[10];
        int len2 = s.appendUnquotedUTF8(buf2, 3); // offset 3
        assertEquals("unquoted utf8 length", v.length(), len2);
        byte[] expected = v.getBytes(java.nio.charset.StandardCharsets.UTF_8);
        for (int i = 0; i < len2; ++i) {
            assertEquals(expected[i], buf2[3 + i]);
        }

        // unquoted UTF8 append failure
        int fail2 = s.appendUnquotedUTF8(new byte[1], 0);
        assertEquals(-1, fail2);
    }

    @Test
    public void writeMethodsCorrectness() throws IOException {
        String v = "xyz";
        SerializedString s = ss(v);

        ByteArrayOutputStream out1 = new ByteArrayOutputStream();
        int written1 = s.writeQuotedUTF8(out1);
        assertEquals("write quoted length", s.asQuotedUTF8().length, written1);
        assertArrayEquals("written quoted bytes", s.asQuotedUTF8(), out1.toByteArray());

        ByteArrayOutputStream out2 = new ByteArrayOutputStream();
        int written2 = s.writeUnquotedUTF8(out2);
        assertEquals("write unquoted length", s.asUnquotedUTF8().length, written2);
        assertArrayEquals("written unquoted bytes", s.asUnquotedUTF8(), out2.toByteArray());
    }

    @Test
    public void putMethodsCapacityCheck() {
        String v = "hello";
        SerializedString s = ss(v);

        // sufficient capacity
        ByteBuffer buf1 = ByteBuffer.allocate(20);
        int putLen1 = s.putQuotedUTF8(buf1);
        assertEquals("put quoted length", s.asQuotedUTF8().length, putLen1);
        byte[] expected1 = new byte[putLen1];
        buf1.flip();
        buf1.get(expected1);
        assertArrayEquals(expected1, s.asQuotedUTF8());

        // insufficient capacity
        ByteBuffer buf2 = ByteBuffer.allocate(3); // too small
        int putFail = s.putUnquotedUTF8(buf2);
        assertEquals(-1, putFail);
    }

    @Test
    public void equalsAndHashCode() {
        SerializedString a = ss("same");
        SerializedString b = ss("same");
        SerializedString c = ss("different");

        assertTrue(a.equals(b));
        assertTrue(b.equals(a));
        assertFalse(a.equals(c));
        assertFalse(a.equals(null));
        assertFalse(a.equals(new Object()));

        assertEquals(a.hashCode(), b.hashCode());
        assertNotEquals(a.hashCode(), c.hashCode());
    }

    @Test
    public void serializationRoundTrip() throws IOException, ClassNotFoundException {
        SerializedString original = ss("serialization test");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(original);
        oos.flush();

        byte[] data = baos.toByteArray();
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
        SerializedString roundTrip = (SerializedString) ois.readObject();

        assertEquals("round-trip value", original.getValue(), roundTrip.getValue());
        assertTrue(original.equals(roundTrip));
    }

    @Test
    public void emptyStringBehavior() {
        String v = "";
        SerializedString s = ss(v);

        // quoted and unquoted representations should be empty arrays
        assertArrayEquals("empty quoted", new char[0], s.asQuotedChars());
        assertArrayEquals("empty quoted UTF8", new byte[0], s.asQuotedUTF8());
        assertArrayEquals("empty unquoted UTF8", new byte[0], s.asUnquotedUTF8());

        // append should return length 0 without modifying buffer
        char[] buf = new char[1];
        int len = s.appendQuoted(buf, 0);
        assertEquals(0, len);

        // writing to stream yields empty content
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            int wlen = s.writeQuotedUTF8(out);
            assertEquals(0, wlen);
            assertEquals("", out.toString());
        } catch (IOException e) {
            fail("Unexpected IOException");
        }
    }

    /* ----- NEW TESTS FOR UNREACHABLE BRANCHES --------------------------------- */

    @Test
    public void appendQuotedUtf8SuccessAndFailure() {
        String v = "ab";
        SerializedString s = ss(v);

        // Success: buffer large enough
        byte[] buf1 = new byte[10];
        int len1 = s.appendQuotedUTF8(buf1, 0);
        assertEquals("quoted utf8 length", s.asQuotedUTF8().length, len1);
        // Verify that bytes match the cached quoted UTF-8 representation
        byte[] expected = s.asQuotedUTF8();
        assertArrayEquals(expected, Arrays.copyOfRange(buf1, 0, len1));

        // Failure: buffer too small
        int fail = s.appendQuotedUTF8(new byte[1], 0);
        assertEquals(-1, fail);
    }

    @Test
    public void putQuotedUtf8FailureDueToCapacity() {
        String v = "xyz";
        SerializedString s = ss(v);

        // Buffer too small for quoted UTF-8 representation
        ByteBuffer buf = ByteBuffer.allocate(2); // intentionally tiny
        int result = s.putQuotedUTF8(buf);
        assertEquals(-1, result);
    }

    @Test
    public void appendUnquotedSuccessAndFailure() {
        String v = "hello";
        SerializedString s = ss(v);

        // Success: buffer large enough for raw characters
        char[] buf1 = new char[10];
        int len1 = s.appendUnquoted(buf1, 0);
        assertEquals("unquoted chars length", v.length(), len1);
        assertArrayEquals(v.toCharArray(), Arrays.copyOfRange(buf1, 0, len1));

        // Failure: buffer too small
        int fail = s.appendUnquoted(new char[2], 0);
        assertEquals(-1, fail);
    }

    @Test
    public void equalsSameInstanceReturnsTrue() {
        SerializedString s = ss("foo");
        // The equals method should return true when comparing the same instance.
        assertTrue(s.equals(s));
    }

    @Test
    public void toStringMatchesValue() {
        String val = "bar";
        SerializedString s = ss(val);
        assertEquals(val, s.toString());
    }

    /* ----- COVERAGE GAPS: CACHE HIT PATHS ----------------------------------- */

    @Test
    public void quotedUtf8CacheHit() {
        String v = "abc";
        SerializedString s = ss(v);
        byte[] first = s.asQuotedUTF8();
        byte[] second = s.asQuotedUTF8();
        assertSame("quoted utf8 cached", first, second);
    }

    @Test
    public void unquotedUtf8CacheHit() {
        String v = "def";
        SerializedString s = ss(v);
        byte[] first = s.asUnquotedUTF8();
        byte[] second = s.asUnquotedUTF8();
        assertSame("unquoted utf8 cached", first, second);
    }

    @Test
    public void appendQuotedCaching() {
        String v = "xyz";
        SerializedString s = ss(v);
        char[] buf = new char[10];
        // First call initializes and caches _quotedChars
        int len1 = s.appendQuoted(buf, 0);
        assertEquals(v.length(), len1);

        // Second call uses cached array; no re-encoding
        int len2 = s.appendQuoted(buf, 0);
        assertEquals(v.length(), len2);
    }

    @Test
    public void putUnquotedUtf8FailureDueToCapacity() {
        String v = "test";
        SerializedString s = ss(v);
        ByteBuffer buf = ByteBuffer.allocate(3); // less than needed
        int res = s.putUnquotedUTF8(buf);
        assertEquals(-1, res);
    }

    /* ---------- NEW BOUNDARY CONDITION TESTS -------------------- */

    @Test
    public void appendQuotedUtf8ExactFitSuccess() {
        String v = "ab";
        SerializedString s = ss(v);

        // buffer exactly fits the quoted UTF-8 representation
        byte[] bufExact = new byte[2];
        int lenExact = s.appendQuotedUTF8(bufExact, 0);
        assertEquals(2, lenExact);
        assertArrayEquals(s.asQuotedUTF8(), bufExact);

        // buffer with non-zero offset exactly fits the quoted UTF-8 representation
        byte[] bufOffset = new byte[3];
        int lenOffset = s.appendQuotedUTF8(bufOffset, 1); // offset+len==buffer.length
        assertEquals(2, lenOffset);
        assertArrayEquals(s.asQuotedUTF8(),
                Arrays.copyOfRange(bufOffset, 1, 3));
    }
}
