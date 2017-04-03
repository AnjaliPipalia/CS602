/**
 * 
 */
package controller;

import java.sql.Date;
import java.util.Calendar;

import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
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
		addFoodNameValidation();
		addCaloriesValidation();
		addFatValidation();
		addCarbsValidation();
		addProteinsValidation();
		addWeightValidation();
	}

	private void addFoodNameValidation() {
		window.addFoodNameValidation(ensureCharsOnly());
	}

	private VerifyListener ensureCharsOnly() {
		return new VerifyListener() {

			public void verifyText(VerifyEvent event) {
				if (event.character != '\u0008' && event.character != '\u007F'
						&& !event.text.matches("[a-zA-Z\\s_,]")) {
					event.doit = false;

				}
			}
		};
	}

	private void addWeightValidation() {
		window.addWeightValidation(ensureDigitsOnly());
	}

	private void addProteinsValidation() {
		window.addProteinsValidation(ensureDigitsOnly());
	}

	private void addCaloriesValidation() {
		window.addCaloriesValidation(ensureDigitsOnly());
	}

	private void addFatValidation() {
		window.addFatValidation(ensureDigitsOnly());
	}

	private void addCarbsValidation() {
		window.addCarbsValidation(ensureDigitsOnly());
	}

	private VerifyListener ensureDigitsOnly() {
		return new VerifyListener() {

			@Override
			public void verifyText(VerifyEvent event) {
				if (event.character != '\u0008' && event.character != '\u007F' && !event.text.matches("[0-9]")) {
					event.doit = false;
					return;
				}
			}
		};
	}

	private void defineSaveAction() {
		window.defineSaveAction(new Listener() {

			@Override
			public void handleEvent(Event event) {
				String fdName = window.getNewFoodName();
				Date date = window.getNewFoodDate();
				Date time = window.getNewTime();
				int calories = window.getNewCalories();
				int fat = window.getNewFat();
				int carbohydrates = window.getNewCarbohydrates();
				int weight = window.getNewWeight();
				int proteins = window.getNewProteins();
				String comments = window.getNewComments();
				
				FoodIntake foodIntake = new FoodIntake();
				try {
					foodIntake.setName(fdName);
					foodIntake.setDate(date);
					foodIntake.setCalories(calories);
					foodIntake.setFat(fat);
					foodIntake.setCarbohydrates(carbohydrates);
					foodIntake.setWeight(weight);
					foodIntake.setProteins(proteins);
					foodIntake.setComments(comments);
					foodIntake.setTime(time);
					
					database.create(foodIntake);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});

	}

}
