package com.jacksonFeatures.objectMapper;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class PersonDeserializer extends StdDeserializer<Person> {

	public PersonDeserializer(Class<?> clazz) {
		super(clazz);
	}

	@Override
	public Person deserialize(JsonParser jsonParser, DeserializationContext deserializer) throws IOException {
		Person person = new Person();
		while (!jsonParser.isClosed()) {
			JsonToken jsonToken = jsonParser.nextToken();
			if (JsonToken.FIELD_NAME.equals(jsonToken)) {
				String fieldName = jsonParser.getCurrentName();
				jsonToken = jsonParser.nextToken();
				if ("name".equals(fieldName)) {
					person.setName(jsonParser.getValueAsString());
				} else if ("age".equals(fieldName)) {
					person.setAge(jsonParser.getValueAsInt());
				}
			}
		}
		return person;
	}
}
