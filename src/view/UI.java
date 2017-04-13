package view;

import java.sql.Date;
import java.sql.Time;

import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.widgets.Listener;

import exception.MandatoryFieldMissingException;
import food.FoodIntakeType;

public interface UI {

	void enteredNewMode();

	void defineResetAction(Listener listener);

	void defineUpdateAction(Listener listener);

	String getNewFoodName() throws MandatoryFieldMissingException;

	Date getNewFoodDate() throws MandatoryFieldMissingException;

	Time getNewTime() throws MandatoryFieldMissingException;

	int getNewCalories();

	int getNewFat();

	int getNewCarbohydrates();

	int getNewWeight();

	int getNewProteins();

	String getNewComments();

	String getFoodIntakeType() throws MandatoryFieldMissingException;

	void enteredEditMode();

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

	void defineDeleteAction(Listener listener);

	int getSelectedRow();

	void deleteRow(int index);

	void defineEditAction(Listener listener);

	void setNewDate(Date date);

	void setNewTime(Time time);

	void setNewMealType(FoodIntakeType intakeType);

	void defineSearchAction(Listener listener);

	String getSearchFoodName();

	Date getSearchFromDate() throws MandatoryFieldMissingException;

	Date getSearchToDate() throws MandatoryFieldMissingException;

	void clearTable();

	void createSearchResultRow(String name, String typeID, Date date, Time time, int weight, int cal, int fat,
			int carbs, int proteins, String comment);

	void addFoodNameValidation(VerifyListener ensureCharsOnly);

	void addWeightValidation(VerifyListener ensureDigitsOnly);

	void addProteinsValidation(VerifyListener ensureDigitsOnly);

	void addCaloriesValidation(VerifyListener ensureDigitsOnly);

	void addFatValidation(VerifyListener ensureDigitsOnly);

	void addCarbsValidation(VerifyListener ensureDigitsOnly);

	void defineSaveAction(Listener listener);

	void open();

}
