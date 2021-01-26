package com.jacksonFeatures.objectMapper;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class PersonSerializer extends StdSerializer<Person> {
	protected PersonSerializer(Class<Person> clazz) {
		super(clazz);
	}

	public void serialize(Person person, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
			throws IOException {
		jsonGenerator.writeStartObject();
		jsonGenerator.writeStringField("name", person.getName());
		jsonGenerator.writeNumberField("age", person.getAge());
		jsonGenerator.writeEndObject();
	}
}
