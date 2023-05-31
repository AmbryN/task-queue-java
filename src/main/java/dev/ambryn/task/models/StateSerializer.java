package dev.ambryn.task.models;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import dev.ambryn.task.interfaces.State;

import java.io.IOException;

public class StateSerializer extends StdSerializer<State> {

    public StateSerializer() {
        this(null);
    }

    public StateSerializer(Class<State> t) {
        super(t);
    }

    @Override
    public void serialize(State state, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws
                                                                                                           IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("status",
                                       state.getStatus()
                                            .toString());
        jsonGenerator.writeEndObject();
    }
}
