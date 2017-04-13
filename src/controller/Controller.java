/**
 * The Controller acts as an bridge between UI and Database
 * 
 * @author arp226
 */
package controller;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
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
import view.UI;

public class Controller {

	private Database database;
	private UI window;
	boolean inEditMode = false;
	private int index = -1;
	List<FoodIntake> foodList = new ArrayList<>();
	protected String searchName;
	protected Date fromDate;
	protected Date toDate;
	protected FoodIntake editFood;

	public Controller(Database database, UI window) {
		this.database = database;
		this.window = window;

	}

	/**
	 * It initializes the behavior of UI
	 */
	public void initialize() {
		window.enteredNewMode();
		defineSearchAction();
		defineEditAction();
		defineNewAction();
		defineSaveAction();

		addFoodNameValidation();
		addCaloriesValidation();
		addFatValidation();
		addCarbsValidation();
		addProteinsValidation();
		addWeightValidation();

		defineUpdateAction();
		defineResetAction();
		defineDeleteAction();

	}

	/**
	 * On click event for button Reset
	 */
	private void defineResetAction() {
		window.defineResetAction(new Listener() {

			@Override
			public void handleEvent(Event arg0) {
				setToEditFoodDetails();
			}

		});

	}

	/**
	 * On click event for button Update
	 */
	private void defineUpdateAction() {
		window.defineUpdateAction(new Listener() {

			@Override
			public void handleEvent(Event arg0) {
				try {
					// get values from UI
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

					window.enteredEditMode();

					// Stores all the values in FoodIntake object
					editFood.setName(fdName);
					editFood.setDate(date);
					editFood.setCalories(calories);
					editFood.setFat(fat);
					editFood.setCarbohydrates(carbohydrates);
					editFood.setWeight(weight);
					editFood.setProteins(proteins);
					editFood.setComments(comments);
					editFood.setTime(time);
					editFood.setIntakeType(foodIntakeType);
					// updates the database
					if (database.update(editFood)) {

						JOptionPane.showMessageDialog(null, "Updated successfully!");
						showSearchLists();
					}
				}

				catch (MandatoryFieldMissingException me) {
					JOptionPane.showMessageDialog(null, me.getMessage());
					me.printStackTrace();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Failed to update!");
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * On click event for button New
	 */
	private void defineNewAction() {
		window.defineNewAction(new Listener() {

			@Override
			public void handleEvent(Event arg0) {

				window.enteredNewMode();
				index = -1;
				// makes all the fields blank
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

		});

	}

	/**
	 * On click event for button Delete
	 */
	private void defineDeleteAction() {
		window.defineDeleteAction(new Listener() {

			@Override
			public void handleEvent(Event arg0) {
				index = window.getSelectedRow();
				if (index < 0) {
					JOptionPane.showMessageDialog(null, "No entries selected");
					return;
				}
				FoodIntake foodIntake = foodList.get(index);
				// delete the FoodIntake object
				if (database.delete(foodIntake)) {
					JOptionPane.showMessageDialog(null, "Deleted Successfully");
					foodList.remove(index);
					window.deleteRow(index);
					index = -1;
				} else
					JOptionPane.showMessageDialog(null, "Failed to delete!");

			}

		});
	}

	/**
	 * On click event for button Edit
	 */
	private void defineEditAction() {
		window.defineEditAction(new Listener() {

			@Override
			public void handleEvent(Event arg0) {
				index = window.getSelectedRow();
				if (index < 0) {
					JOptionPane.showMessageDialog(null, "No entries selected");
					return;
				}
				window.enteredEditMode();
				editFood = foodList.get(index);
				setToEditFoodDetails();
			}

		});
	}

	/**
	 * sets all diet details values with editfood FoodIntake object
	 */
	private void setToEditFoodDetails() {
		window.setNewFoodName(editFood.getName());
		window.setNewDate(editFood.getDate());
		window.setNewCalories(editFood.getCalories() + "");
		window.setNewFat(editFood.getFat() + "");
		window.setNewCarbohydrates(editFood.getCarbohydrates() + "");
		window.setNewWeight(editFood.getWeight() + "");
		window.setNewProteins(editFood.getProteins() + "");
		window.setNewComments(editFood.getComments());
		window.setNewTime(editFood.getTime());
		window.setNewMealType(editFood.getIntakeType());
	}

	/**
	 * On click event for button Search
	 */
	private void defineSearchAction() {
		window.defineSearchAction(new Listener() {

			@Override
			public void handleEvent(Event event) {

				try {
					// search by foodName,fromDate,toDate
					searchName = window.getSearchFoodName();
					fromDate = window.getSearchFromDate();
					toDate = window.getSearchToDate();
					showSearchLists();

				} catch (MandatoryFieldMissingException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					e.printStackTrace();
				}

			}

		});

	}

	/**
	 * fetches all the required data from Database
	 */
	private void showSearchLists() {
		window.clearTable();
		index = -1;
		foodList = database.read(searchName, fromDate, toDate);
		if (foodList.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No search results to display.");
		}
		for (int i = 0; i < foodList.size(); i++) {
			FoodIntake temp = foodList.get(i);
			String name = temp.getName();
			String typeID = temp.getIntakeType().mealName();
			Date date = temp.getDate();
			Time time = temp.getTime();
			int weight = temp.getWeight();
			int cal = temp.getCalories();
			int fat = temp.getFat();
			int carbs = temp.getCarbohydrates();
			int proteins = temp.getProteins();
			String comment = temp.getComments();
			window.createSearchResultRow(name, typeID, date, time, weight, cal, fat, carbs, proteins, comment);

		}
	}

	/**
	 * Add validation for foodName
	 */
	private void addFoodNameValidation() {
		window.addFoodNameValidation(ensureCharsOnly());
	}

	/**
	 * Creates VerifyListener for characters,delete,backspace,space,comma and
	 * hyphen
	 * 
	 * @return VerifyListener
	 */
	private VerifyListener ensureCharsOnly() {
		return new VerifyListener() {

			public void verifyText(VerifyEvent e) {
				String text = e.text;
				for (int i = 0; i < text.length(); i++) {
					char ch = text.charAt(i);
					if (!Character.isLetter(ch) && (ch != '\u0008' || ch != '\u007F') && ch != ' ' && ch != ','
							&& ch != '-') {
						e.doit = false;
						return;
					}
				}

			}

		};
	}

	/**
	 * Add validation for weight
	 */
	private void addWeightValidation() {
		window.addWeightValidation(ensureDigitsOnly());
	}

	/**
	 * Add validation for Proteins
	 */
	private void addProteinsValidation() {
		window.addProteinsValidation(ensureDigitsOnly());
	}

	/**
	 * Add validation for calories
	 */
	private void addCaloriesValidation() {
		window.addCaloriesValidation(ensureDigitsOnly());
	}

	/**
	 * Add validation for fat
	 */
	private void addFatValidation() {
		window.addFatValidation(ensureDigitsOnly());
	}

	/**
	 * Add validation for carbohydrates
	 */
	private void addCarbsValidation() {
		window.addCarbsValidation(ensureDigitsOnly());
	}

	/**
	 * Creates VerifyListener for digits,delete,backspace
	 * 
	 * @return VerifyListener
	 */
	private VerifyListener ensureDigitsOnly() {
		return new VerifyListener() {

			@Override
			public void verifyText(VerifyEvent e) {
				String text = e.text;
				for (int i = 0; i < text.length(); i++) {
					char ch = text.charAt(i);
					if (!Character.isDigit(ch) && (ch != '\u0008' || ch != '\u007F')) {
						e.doit = false;
						return;
					}
				}

			}
		};
	}

	/**
	 * On click event for button Save
	 */
	private void defineSaveAction() {
		window.defineSaveAction(new Listener() {

			@Override
			public void handleEvent(Event event) {
				try {
					index = -1;
					// gets all the values from UI
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

					// Stores all the values in FoodIntake object
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
					// makes all the fields blank on saving successfully
					if (database.save(foodIntake)) {
						JOptionPane.showMessageDialog(null, "Saved Successfully!");
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

}
