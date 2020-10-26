package com.capg.jsonutility;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Json {
	private static final String PATH = "./src/test/resources/JsonFiles";

	public <E> void writeList(String fileName, String JsonString) {
		String FILE_NAME = PATH + "\\" + fileName + ".json";
		try {
			Writer writer = new FileWriter(FILE_NAME);
			new Gson().toJson(JsonString, writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String readList(String fileName) {
		String jsonString = new String();
		try {
			Reader reader = Files.newBufferedReader(Paths.get(PATH + "\\" + fileName));
			jsonString= new Gson().fromJson(reader, new TypeToken<String>() {
			}.getType());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonString;
	}
}

