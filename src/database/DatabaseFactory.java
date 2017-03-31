/**
 * 
 */
package database;

/**
 * @author arp226
 *
 */
public class DatabaseFactory {

	public static Database getDatabase(){
		return new MySqlDB();
	}
}

