package de.sollder1.webchess.backend.api.lobby;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class ByteArray2DSerializer extends JsonSerializer<byte[][]> {

    @Override
    public void serialize(byte[][] bytes, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeStartArray();
        for (byte[] array : bytes) {
            jgen.writeStartArray();
            for (byte b : array) {
                jgen.writeNumber(b);
            }
            jgen.writeEndArray();
        }
        jgen.writeEndArray();
    }


}
