/**
 * 
 */
package food;

import java.sql.Date;
import java.util.Calendar;

import main.Utils;

/**
 * @author arp226
 *
 */
public class FoodIntake {
	int intakeID, weight, calories, fat, carbohydrates, proteins;
	String name, comments;
	Date date;
	FoodIntakeType intakeType;

	public FoodIntake(String name, Date date) {
		// TODO make unique combination for Name-type-date
		this.name = name;
		this.intakeID = 0;
		this.weight = 0;
		this.calories = 0;
		this.fat = 0;
		this.carbohydrates = 0;
		this.proteins = 0;
		this.comments = "";
		this.date = date;
		this.intakeType = FoodIntakeType.LUNCH;
	}

	public FoodIntake() {
		this.name = "";
		this.intakeID = 0;
		this.weight = 0;
		this.calories = 0;
		this.fat = 0;
		this.carbohydrates = 0;
		this.proteins = 0;
		this.comments = "";
		this.date = Utils.getTodaysDate();
		this.intakeType = FoodIntakeType.LUNCH;
	}

	public void setDate(Date date) throws Exception {
		if (date.compareTo(Utils.getTodaysDate()) < 0) {
			throw new Exception("incorrect date");
		}
		this.date = date;
	}

	public Date getDate() {
		return this.date;
	}

	public String getName() {
		return this.name;
	}

	public int getIntakeID() {
		return this.intakeID;
	}

	public void setIntakeID(int i) {
		this.intakeID = i;
	}

	public void setName(String name) {
		this.name = name;
	}

}
