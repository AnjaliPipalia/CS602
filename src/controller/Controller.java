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
import org.eclipse.swt.widgets.Table;

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
	boolean inEditMode = false;
	private int index = -1;
	List<FoodIntake> foodList = new ArrayList<>();
	protected String searchName;
	protected Date fromDate;
	protected Date toDate;
	protected FoodIntake editFood;

	public Controller(Database database, DashBoard window) {
		this.database = database;
		this.window = window;

	}

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

	private void defineResetAction() {
		window.defineResetAction(new Listener(){

			@Override
			public void handleEvent(Event arg0) {
				setFoodEditDetails();
			}
			
		});
		
	}

	private void defineUpdateAction() {
		window.defineUpdateAction(new Listener() {

			@Override
			public void handleEvent(Event arg0) {
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
					window.enteredEditMode();
					
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

	private void defineNewAction() {
		window.defineNewAction(new Listener() {

			@Override
			public void handleEvent(Event arg0) {
				window.enteredNewMode();
				index = -1;
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
				setFoodEditDetails();
			}

			
		});
	}
	private void setFoodEditDetails() {
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
	private void defineSearchAction() {
		window.defineSearchAction(new Listener() {

			@Override
			public void handleEvent(Event event) {

				try {

					searchName = window.getSearchFoodName();
					fromDate = window.getSearchFromDate();
					toDate = window.getSearchToDate();
					showSearchLists();
					
				} catch (MandatoryFieldMissingException e) {
					JOptionPane.showMessageDialog(null,e.getMessage());
					e.printStackTrace();
				}

			}

		});

	}

	private void showSearchLists() {
		window.clearTable();
		index = -1;
		foodList = database.read(searchName, fromDate, toDate);
		if(foodList.isEmpty())
		{
			JOptionPane.showMessageDialog(null,"No search results to display.");
		}
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
			window.createSearchResultRow(name, typeID, date, time, weight, cal, fat, carbs, proteins, comment);
			
		}
	}

	private void addFoodNameValidation() {
		window.addFoodNameValidation(ensureCharsOnly());
	}

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

	private void defineSaveAction() {
		window.defineSaveAction(new Listener() {

			@Override
			public void handleEvent(Event event) {
				try {
					index = -1;
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
