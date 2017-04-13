/**
 * Configuration class is used to fetch the data from .properties file
 * @author arp226
 */
package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {
	private static Properties properties;

	public static void setProperties(String p) throws FileNotFoundException {
		properties = new Properties();
		InputStream input = null;
		input = new FileInputStream(p);

		// load a properties file
		try {
			properties.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * get username for database
	 * 
	 * @return string
	 */
	public static String getDBUserName() {

		return properties.getProperty("database.user");

	}

	/**
	 * get user password for database
	 * 
	 * @return String
	 */
	public static String getDBPassword() {

		return properties.getProperty("database.password");

	}

	/**
	 * get database url from the user
	 * 
	 * @return String
	 */

	public static String getDBUrl() {

		return properties.getProperty("database.url");

	}

}
