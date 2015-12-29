package com.viatt.zhjtpro.common;

import java.util.Properties;

public class PropertiesReader {

	private static Properties properties = null;

	private PropertiesReader() {
	}

	public static String getProperty(String key) {
		if (properties == null)
			try {
				java.io.InputStream is = PropertiesReader.class
						.getResourceAsStream("/app.properties");
				properties = new Properties();
				properties.load(is);
				return properties.getProperty(key);
			} catch (Exception e) {
				properties = null;
			}
		return properties.getProperty(key);
	}
	 
}