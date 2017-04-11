/**
 * 
 */
package database;


import java.sql.Date;
import java.util.List;

import exception.DatabaseException;
import food.FoodIntake;
import food.FoodIntakeType;

/**
 * @author arp226
 *
 */
public interface Database {
	 public boolean create(FoodIntake foodIntake);
	//public boolean create(FoodIntakeType foodIntakeType);
	 public List<FoodIntake> read(String foodName, Date from, Date to);
	 public boolean update(FoodIntake foodIntake);
	 public boolean delete(FoodIntake foodIntake);
	 public boolean open();
	 public boolean close();
	public void createTables() throws DatabaseException;

}
