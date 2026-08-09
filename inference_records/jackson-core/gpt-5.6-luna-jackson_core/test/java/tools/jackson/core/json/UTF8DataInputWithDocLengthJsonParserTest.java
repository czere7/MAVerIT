package tools.jackson.core.json;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import tools.jackson.core.JsonToken;
import tools.jackson.core.ObjectReadContext;
import tools.jackson.core.StreamReadConstraints;
import tools.jackson.core.exc.StreamConstraintsException;
import tools.jackson.core.io.IOContext;
import tools.jackson.core.sym.ByteQuadsCanonicalizer;

public class UTF8DataInputWithDocLengthJsonParserTest
{
    private static final int FEATURES = 0;

    private static class ExposedParser
        extends UTF8DataInputWithDocLengthJsonParser
    {
        ExposedParser(ObjectReadContext readCtxt, IOContext ctxt,
                DataInput inputData, ByteQuadsCanonicalizer symbols, int firstByte) {
            super(readCtxt, ctxt, FEATURES, FEATURES, inputData, symbols, firstByte);
        }

        int readByte() throws IOException {
            return readUnsignedByte();
        }

        long bytesRead() {
            return _bytesRead;
        }
    }

    private ExposedParser newParser(StreamReadConstraints constraints,
            DataInput input, ByteQuadsCanonicalizer symbols, int firstByte) {
        IOContext ioContext = org.mockito.Mockito.mock(IOContext.class);
        ObjectReadContext readContext = org.mockito.Mockito.mock(ObjectReadContext.class);

        when(ioContext.streamReadConstraints()).thenReturn(constraints);
        when(readContext.streamReadConstraints()).thenReturn(constraints);

        return new ExposedParser(readContext, ioContext, input, symbols, firstByte);
    }

    @Test
    public void readUnsignedByteDelegatesAndTracksBytes() throws Exception {
        DataInput input = org.mockito.Mockito.mock(DataInput.class);
        ByteQuadsCanonicalizer symbols = org.mockito.Mockito.mock(ByteQuadsCanonicalizer.class);
        StreamReadConstraints constraints = org.mockito.Mockito.mock(StreamReadConstraints.class);

        when(input.readUnsignedByte()).thenReturn(0xAB);

        ExposedParser parser = newParser(constraints, input, symbols, 't');

        assertEquals(0xAB, parser.readByte());
        assertEquals(1L, parser.bytesRead());
        verify(input).readUnsignedByte();
    }

    @Test
    public void readUnsignedByteTracksAttemptWhenInputFails() throws Exception {
        DataInput input = org.mockito.Mockito.mock(DataInput.class);
        ByteQuadsCanonicalizer symbols = org.mockito.Mockito.mock(ByteQuadsCanonicalizer.class);
        StreamReadConstraints constraints = org.mockito.Mockito.mock(StreamReadConstraints.class);
        IOException failure = new IOException("input failure");

        when(input.readUnsignedByte()).thenThrow(failure);

        ExposedParser parser = newParser(constraints, input, symbols, 't');

        try {
            parser.readByte();
        } catch (IOException e) {
            assertEquals(failure, e);
        }

        assertEquals(1L, parser.bytesRead());
    }

    @Test
    public void nextTokenParsesValueAndValidatesDocumentLength() throws Exception {
        StreamReadConstraints constraints = org.mockito.Mockito.mock(StreamReadConstraints.class);
        ByteQuadsCanonicalizer symbols = org.mockito.Mockito.mock(ByteQuadsCanonicalizer.class);
        DataInput input = new DataInputStream(
                new ByteArrayInputStream("rue ".getBytes(StandardCharsets.UTF_8)));

        ExposedParser parser = newParser(constraints, input, symbols, 't');

        assertEquals(JsonToken.VALUE_TRUE, parser.nextToken());

        ArgumentCaptor<Long> lengthCaptor = ArgumentCaptor.forClass(Long.class);
        verify(constraints).validateDocumentLength(lengthCaptor.capture());
        assertTrue(lengthCaptor.getValue() > 0L);
    }

    @Test(expected = StreamConstraintsException.class)
    public void nextTokenRejectsDocumentExceedingConfiguredLength() throws Exception {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxDocumentLength(1L)
                .build();
        ByteQuadsCanonicalizer symbols = org.mockito.Mockito.mock(ByteQuadsCanonicalizer.class);
        DataInput input = new DataInputStream(
                new ByteArrayInputStream("rue ".getBytes(StandardCharsets.UTF_8)));

        ExposedParser parser = newParser(constraints, input, symbols, 't');

        parser.nextToken();
    }
}
