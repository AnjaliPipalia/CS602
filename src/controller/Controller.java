/**
 * 
 */
package controller;

import java.awt.Window;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JOptionPane;

import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import database.Database;
import exception.MandatoryFieldMissingException;
import food.FoodIntake;
import food.FoodIntakeType;
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

		defineSearchAction();
		// defineSearchResults();

	}

	// private void defineSearchResults() {
	// window.de
	//
	// }

	private void defineSearchAction() {
		window.defineSearchAction(new Listener() {

			@Override
			public void handleEvent(Event event) {
				List<FoodIntake> foodList = new ArrayList<>();
				try {

					String fdName = window.getSearchFoodName();
					// System.out.println(fdName);
					Date fromDate = window.getSearchFromDate();
					// System.out.println(fromDate);
					Date toDate = window.getSearchToDate();
					// System.out.println(toDate);
					foodList = database.read(fdName, fromDate, toDate);
					for (int i = 0; i < foodList.size(); i++) {
						String name = foodList.get(i).getName();
						String typeID = foodList.get(i).getIntakeType().mealName();
						Date date = foodList.get(i).getDate();
						Time time = foodList.get(i).getTime();
						int weight = foodList.get(i).getWeight();
						int cal = foodList.get(i).getCalories();
						int fat = foodList.get(i).getFat();
						int carbs = foodList.get(i).getCarbohydrates();
						int proteins = foodList.get(i).getProteins();
						String comment = foodList.get(i).getComments();
						window.createSearchResultRow(name,typeID,date,time,weight,cal,fat,carbs,proteins,comment);
					}

					// System.out.println(foodList);
				} catch (MandatoryFieldMissingException e) {
					e.printStackTrace();
				}

			}
		});

	}

	private void addFoodNameValidation() {
		window.addFoodNameValidation(ensureCharsOnly());
	}

	private VerifyListener ensureCharsOnly() {
		return new VerifyListener() {

			public void verifyText(VerifyEvent event) {
				if (event.character != '\u0008' && event.character != '\u007F' && !event.text.matches("[\\D]")) {
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
				try {
					String fdName = window.getNewFoodName();
					Date date = window.getNewFoodDate();
					Time time = window.getNewTime();
					int calories = window.getNewCalories();
					int fat = window.getNewFat();
					int carbohydrates = window.getNewCarbohydrates();
					int weight = window.getNewWeight();
					int proteins = window.getNewProteins();
					String comments = window.getNewComments();
					FoodIntakeType foodIntakeType = FoodIntakeType.valueOf(window.getFoodIntakeType().toUpperCase());

					FoodIntake foodIntake = new FoodIntake();
					foodIntake.setName(fdName);
					foodIntake.setDate(date);
					foodIntake.setCalories(calories);
					foodIntake.setFat(fat);
					foodIntake.setCarbohydrates(carbohydrates);
					foodIntake.setWeight(weight);
					foodIntake.setProteins(proteins);
					foodIntake.setComments(comments);
					foodIntake.setTime(time);
					foodIntake.setIntakeType(foodIntakeType);
					database.create(foodIntake);

					if (showMessageDialog()) {
						window.setNewFoodName(" ");
						window.setToCurrDate();
						window.setNewCalories("0");
						window.setNewFat("0");
						window.setNewCarbohydrates("0");
						window.setNewWeight("0");
						window.setNewProteins("0");
						window.setNewComments("");
						window.setToCurrTime();
						window.setToMealType();

					}
				}

				catch (MandatoryFieldMissingException me) {
					JOptionPane.showMessageDialog(null, me.getMessage());
					me.printStackTrace();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Failed to save!");
					e.printStackTrace();
				}

			}
		});

	}

	protected boolean showMessageDialog() {
		JOptionPane.showMessageDialog(null, "Saved Successfully!");
		return true;
	}

}
