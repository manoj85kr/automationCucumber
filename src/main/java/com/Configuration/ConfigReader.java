package com.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	public static Properties properties;

	public static void load() throws IOException {
		FileInputStream fis = new FileInputStream("./src/test/resources/config.properties");
		properties = new Properties();
		properties.load(fis);
	}

	public static String getProperty(String key) {
		return properties.getProperty(key);
	}
}
