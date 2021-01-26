package com.jacksonFeatures.objectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class ParseJSON {

	public static void main(String[] args) {
		ParseJSON parseJSONObject = new ParseJSON();
		Person person = parseJSONObject.parseJSONFromJSONString();
		System.out.println("Name: " + person.getName());
		System.out.println("Age: " + person.getAge());
		System.out.println("**********************************************");
		person = parseJSONObject.parseJSONFromJSONFile();
		System.out.println("Name: " + person.getName());
		System.out.println("Age: " + person.getAge());
		System.out.println("**********************************************");
		parseJSONObject.parseJSONFromJSONArrayString();
		System.out.println("**********************************************");
		person = parseJSONObject.parseJSONUsingCustomDeserialization();
		System.out.println("Name: " + person.getName());
		System.out.println("Age: " + person.getAge());
		System.out.println("**********************************************");
	}

	public Person parseJSONFromJSONString() {
		Person person = null;
		try {
			String jsonString = "{ \"name\" : \"Sam\", \"age\" : 45 }";
			ObjectMapper objectMapper = new ObjectMapper();
			person = objectMapper.readValue(jsonString, Person.class);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return person;
	}

	public Person parseJSONFromJSONFile() {
		Person person = null;
		try {
			File file = new File("./files/JSONFile1.json");
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			ObjectMapper objectMapper = new ObjectMapper();
			person = objectMapper.readValue(bufferedReader, Person.class);
			bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return person;
	}

	public void parseJSONFromJSONArrayString() {
		try {
			String jsonArrayString = "[{\"name\":\"Richard\", \"age\" : 22 }, {\"name\":\"Harry\", \"age\" : 29 }]";
			ObjectMapper objectMapper = new ObjectMapper();
			Person[] personArray = objectMapper.readValue(jsonArrayString, Person[].class);
			for (Person person : personArray) {
				System.out.println("Name: " + person.getName());
				System.out.println("Age: " + person.getAge());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Person parseJSONUsingCustomDeserialization() {
		Person person = null;
		try {
			String json = "{ \"name\" : \"Charlie\", \"age\" : 32 }";
			SimpleModule simpleModule = new SimpleModule("PersonDeserializer");
			simpleModule.addDeserializer(Person.class, new PersonDeserializer(Person.class));
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.registerModule(simpleModule);
			person = objectMapper.readValue(json, Person.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return person;
	}

}
