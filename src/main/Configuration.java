package main;

import java.util.Properties;

public class Configuration {
	private static Properties properties;

	public static void setProperties(Properties p) {
			properties = p;
	}

	public  static String getDBUserName() {

		return properties.getProperty("database.user");

	}

	public static String getDBPassword() {

		return properties.getProperty("database.password");

	}

	public static String getDBUrl() {

		return properties.getProperty("database.url");

	}

}
