/**
 * 
 */
package database;

/**
 * @author arp226
 *
 */
public class DatabaseFactory {

	public Database getDatabase(){
		return new MySqlDB();
	}
}

