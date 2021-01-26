package com.jacksonFeatures.objectMapper;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class GenerateJSON {

	public static void main(String[] args) {
		GenerateJSON generateJSON = new GenerateJSON();
		generateJSON.generateJSONAsFileStringAndByteArray(new File("./files/JSONFile2.json"));
		generateJSON.generateJSONUsingCustomSerialization();
	}

	public void generateJSONAsFileStringAndByteArray(File file) {
		try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
			Person person = new Person();
			person.setName("Raj");
			person.setAge(41);
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.writeValue(fileOutputStream, person);
			String jsonString = objectMapper.writeValueAsString(person);
			System.out.println("JSON String: " + jsonString);
			byte[] jsonBytearray = objectMapper.writeValueAsBytes(person);
			System.out.println(Arrays.toString(jsonBytearray));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void generateJSONUsingCustomSerialization() {
		try {
			PersonSerializer personSerializer = new PersonSerializer(Person.class);
			ObjectMapper objectMapper = new ObjectMapper();
			SimpleModule module = new SimpleModule("PersonSerializer");
			module.addSerializer(Person.class, personSerializer);
			objectMapper.registerModule(module);
			Person person = new Person();
			person.setName("Steve");
			person.setAge(55);
			String jsonString = objectMapper.writeValueAsString(person);
			System.out.println(jsonString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
