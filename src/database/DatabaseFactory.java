/**
 *
 *Factory class for creating Database object
 * @author arp226
 */

package database;

public class DatabaseFactory {

	public static Database getDatabase() {
		/*
		 * Will help integrating other databases with ease As of now only
		 * MySqlDB is supported
		 */
		return new MySqlDB();
	}
}
