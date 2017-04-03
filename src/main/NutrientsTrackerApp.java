/**
 * 
 */
package main;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import controller.Controller;
import database.Database;
import database.DatabaseFactory;
import food.FoodIntake;
import view.DashBoard;

/**
 * @author arp226
 *
 */
public class NutrientsTrackerApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Database database = DatabaseFactory.getDatabase();
		try {
			DashBoard window = new DashBoard();
			Controller controller = new Controller(database, window);
			controller.initialize();
			window.open();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
