package tools.jackson.core.util;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.io.Writer;

import org.junit.Test;

import tools.jackson.core.Base64Variant;
import tools.jackson.core.FormatSchema;
import tools.jackson.core.JsonParser;
import tools.jackson.core.JsonToken;
import tools.jackson.core.ObjectReadContext;
import tools.jackson.core.StreamReadCapability;
import tools.jackson.core.StreamReadConstraints;
import tools.jackson.core.TokenStreamLocation;
import tools.jackson.core.TokenStreamContext;
import tools.jackson.core.exc.InputCoercionException;
import tools.jackson.core.sym.PropertyNameMatcher;
import tools.jackson.core.type.ResolvedType;
import tools.jackson.core.type.TypeReference;
import tools.jackson.core.util.JacksonFeatureSet;
import tools.jackson.core.async.NonBlockingInputFeeder;
import tools.jackson.core.SerializableString;
import tools.jackson.core.Version;

/**
 * Unit tests for {@link JsonParserDelegate}.
 */
public class JsonParserDelegateTest {

    // Existing fields and setup omitted for brevity

    @Test
    public void testCanParseAsyncTrue() {
        JsonParser mock = mock(JsonParser.class);
        when(mock.canParseAsync()).thenReturn(true);
        JsonParserDelegate delegate = new JsonParserDelegate(mock);
        assertTrue("canParseAsync should return true from delegate", delegate.canParseAsync());
    }

    @Test
    public void testCanReadObjectIdFalse() {
        JsonParser mock = mock(JsonParser.class);
        when(mock.canReadObjectId()).thenReturn(false);
        JsonParserDelegate delegate = new JsonParserDelegate(mock);
        assertFalse("canReadObjectId should return false from delegate", delegate.canReadObjectId());
    }

    @Test
    public void testCanReadTypeIdTrue() {
        JsonParser mock = mock(JsonParser.class);
        when(mock.canReadTypeId()).thenReturn(true);
        JsonParserDelegate delegate = new JsonParserDelegate(mock);
        assertTrue("canReadTypeId should return true from delegate", delegate.canReadTypeId());
    }

    @Test
    public void testFinishTokenDelegates() {
        JsonParser mock = mock(JsonParser.class);
        JsonParserDelegate delegate = new JsonParserDelegate(mock);
        delegate.finishToken();
        verify(mock).finishToken(); // ensure underlying method was called
    }

    @Test
    public void testGetStringOffsetNonZero() {
        JsonParser mock = mock(JsonParser.class);
        when(mock.getStringOffset()).thenReturn(7);
        JsonParserDelegate delegate = new JsonParserDelegate(mock);
        assertEquals("getStringOffset should forward value", 7, delegate.getStringOffset());
    }

    @Test
    public void testCurrentLocationNonNull() {
        TokenStreamLocation loc = mock(TokenStreamLocation.class);
        JsonParser mock = mock(JsonParser.class);
        when(mock.currentLocation()).thenReturn(loc);
        JsonParserDelegate delegate = new JsonParserDelegate(mock);
        assertSame("currentLocation should return underlying location", loc, delegate.currentLocation());
    }

    @Test
    public void testCurrentTokenLocationNonNull() {
        TokenStreamLocation loc = mock(TokenStreamLocation.class);
        JsonParser mock = mock(JsonParser.class);
        when(mock.currentTokenLocation()).thenReturn(loc);
        JsonParserDelegate delegate = new JsonParserDelegate(mock);
        assertSame("currentTokenLocation should return underlying location", loc, delegate.currentTokenLocation());
    }

    @Test
    public void testCurrentValueNonNull() {
        Object value = new Object();
        JsonParser mock = mock(JsonParser.class);
        when(mock.currentValue()).thenReturn(value);
        JsonParserDelegate delegate = new JsonParserDelegate(mock);
        assertSame("currentValue should return underlying value", value, delegate.currentValue());
    }

    @Test
    public void testHasCurrentTokenFalse() {
        JsonParser mock = mock(JsonParser.class);
        when(mock.hasCurrentToken()).thenReturn(false);
        JsonParserDelegate delegate = new JsonParserDelegate(mock);
        assertFalse("hasCurrentToken should forward false", delegate.hasCurrentToken());
    }

    @Test
    public void testHasTokenIdTrue() {
        JsonParser mock = mock(JsonParser.class);
        when(mock.hasTokenId(5)).thenReturn(true);
        JsonParserDelegate delegate = new JsonParserDelegate(mock);
        assertTrue("hasTokenId should forward true", delegate.hasTokenId(5));
    }

    @Test
    public void testHasTokenTrue() {
        JsonParser mock = mock(JsonParser.class);
        when(mock.hasToken(JsonToken.VALUE_STRING)).thenReturn(true);
        JsonParserDelegate delegate = new JsonParserDelegate(mock);
        assertTrue("hasToken should forward true", delegate.hasToken(JsonToken.VALUE_STRING));
    }

    @Test
    public void testIsClosedTrue() {
        JsonParser mock = mock(JsonParser.class);
        when(mock.isClosed()).thenReturn(true);
        JsonParserDelegate delegate = new JsonParserDelegate(mock);
        assertTrue("isClosed should forward true", delegate.isClosed());
    }
}
