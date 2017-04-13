/**
 * Interface UI for interacting with the view
 * @author arp226
 */

package view;

import java.sql.Date;
import java.sql.Time;

import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.widgets.Listener;

import exception.MandatoryFieldMissingException;
import food.FoodIntakeType;

public interface UI {
	/**
	 * action to take place on entering NEW mode
	 */
	void enteredNewMode();

	/**
	 * action to perform on click Reset Button
	 */
	void defineResetAction(Listener listener);

	/**
	 * action to perform on click Update Button
	 */
	void defineUpdateAction(Listener listener);

	/**
	 * Get new food Name
	 * 
	 * @return
	 * @throws MandatoryFieldMissingException
	 */
	String getNewFoodName() throws MandatoryFieldMissingException;

	/**
	 * get new Date
	 * 
	 * @return
	 * @throws MandatoryFieldMissingException
	 */
	Date getNewFoodDate() throws MandatoryFieldMissingException;

	/**
	 * get new Time
	 * 
	 * @return
	 * @throws MandatoryFieldMissingException
	 */
	Time getNewTime() throws MandatoryFieldMissingException;

	int getNewCalories();

	int getNewFat();

	int getNewCarbohydrates();

	int getNewWeight();

	int getNewProteins();

	/**
	 * get new comments
	 * 
	 * @return
	 */
	String getNewComments();

	/**
	 * get new FoodIntake type
	 * 
	 * @return
	 * @throws MandatoryFieldMissingException
	 */
	String getFoodIntakeType() throws MandatoryFieldMissingException;

	/**
	 * action to take place on entering EDIT mode
	 */
	void enteredEditMode();

	/**
	 * action to perform on click New Button
	 * 
	 * @param listener
	 */
	void defineNewAction(Listener listener);

	void setNewFoodName(String string);

	void setToCurrDate();

	void setNewCalories(String string);

	void setNewFat(String string);

	void setNewCarbohydrates(String string);

	void setNewWeight(String string);

	void setNewProteins(String string);

	void setNewComments(String string);

	void setToCurrTime();

	void setToMealType();

	/**
	 * action to perform on click Delete Button
	 * 
	 * @param listener
	 */
	void defineDeleteAction(Listener listener);

	int getSelectedRow();

	/**
	 * Delete selected row
	 * 
	 * @param index
	 */
	void deleteRow(int index);

	void defineEditAction(Listener listener);

	void setNewDate(Date date);

	void setNewTime(Time time);

	void setNewMealType(FoodIntakeType intakeType);

	/**
	 * action to perform on click Search Button
	 * 
	 * @param listener
	 */
	void defineSearchAction(Listener listener);

	String getSearchFoodName();

	Date getSearchFromDate() throws MandatoryFieldMissingException;

	Date getSearchToDate() throws MandatoryFieldMissingException;

	/**
	 * remove rows from the table
	 */
	void clearTable();

	/**
	 * create table row
	 * 
	 * @param name
	 * @param typeID
	 * @param date
	 * @param time
	 * @param weight
	 * @param cal
	 * @param fat
	 * @param carbs
	 * @param proteins
	 * @param comment
	 */
	void createSearchResultRow(String name, String typeID, Date date, Time time, int weight, int cal, int fat,
			int carbs, int proteins, String comment);

	void addFoodNameValidation(VerifyListener ensureCharsOnly);

	void addWeightValidation(VerifyListener ensureDigitsOnly);

	void addProteinsValidation(VerifyListener ensureDigitsOnly);

	void addCaloriesValidation(VerifyListener ensureDigitsOnly);

	void addFatValidation(VerifyListener ensureDigitsOnly);

	void addCarbsValidation(VerifyListener ensureDigitsOnly);
/**
 * action to perform on click Save Button
 * @param listener
 */
	void defineSaveAction(Listener listener);

	void open();

}
