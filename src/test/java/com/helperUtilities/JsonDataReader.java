package com.helperUtilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONObject;

public class JsonDataReader {
	public static JSONObject getData(String key) throws IOException {
		String path = "E:\\Automation\\automationCucumber\\loginData.json";
		String json = new String(Files.readAllBytes(Paths.get(path)));
		JSONObject obj = new JSONObject(json);
		return obj.getJSONObject(key);
	}
}
