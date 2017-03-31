/**
 * 
 */
package database;

import java.util.Date;
import java.util.List;

import food.FoodIntake;
import food.FoodIntakeType;

/**
 * @author arp226
 *
 */
public class MySqlDB implements Database {

	/* (non-Javadoc)
	 * @see database.Database#create(food.FoodIntake)
	 */
	@Override
	public boolean create(FoodIntake foodIntake) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see database.Database#create(food.FoodIntakeType)
	 */
	@Override
	public boolean create(FoodIntakeType foodIntakeType) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see database.Database#read(java.lang.String, java.util.Date, java.util.Date)
	 */
	@Override
	public List<FoodIntake> read(String foodName, Date from, Date to) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see database.Database#update(food.FoodIntake)
	 */
	@Override
	public boolean update(FoodIntake foodIntake) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see database.Database#delete(food.FoodIntake)
	 */
	@Override
	public boolean delete(FoodIntake foodIntake) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see database.Database#open()
	 */
	@Override
	public boolean open() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see database.Database#close()
	 */
	@Override
	public boolean close() {
		// TODO Auto-generated method stub
		return false;
	}

}
