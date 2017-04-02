/**
 * 
 */
package controller;

import java.sql.Date;
import java.util.Calendar;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import database.Database;
import food.FoodIntake;
import view.DashBoard;

/**
 * @author arp226
 *
 */
public class Controller {

	private Database database;
	private DashBoard window;

	public Controller(Database database, DashBoard window) {
		this.database = database;
		this.window = window;

	}

	public void initialize() {
		defineSaveAction();
	}

	private void defineSaveAction() {
		window.defineSaveAction(new Listener() {

			@Override
			public void handleEvent(Event event) {
				String fdName = window.getNewFoodName();
				Date date = window.getNewFoodDate();
				FoodIntake foodIntake = new FoodIntake();
				try {
					foodIntake.setName(fdName);
					foodIntake.setDate(date);
					database.create(foodIntake);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
	}

}
