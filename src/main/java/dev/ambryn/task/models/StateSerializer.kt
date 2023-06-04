package dev.ambryn.task.models

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import dev.ambryn.task.interfaces.State
import java.io.IOException

class StateSerializer @JvmOverloads constructor(t: Class<State?>? = null) : StdSerializer<State>(t) {
    @Throws(IOException::class)
    override fun serialize(state: State, jsonGenerator: JsonGenerator, serializerProvider: SerializerProvider) {
        jsonGenerator.writeStartObject()
        jsonGenerator.writeStringField(
            "status",
            state.status
                .toString()
        )
        jsonGenerator.writeEndObject()
    }
}
