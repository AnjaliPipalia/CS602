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
	Date date, time;
	FoodIntakeType intakeType;

	public FoodIntake(String name, Date date) {
		this.name = name;
		this.intakeID = 0;
		this.weight = 0;
		this.calories = 0;
		this.fat = 0;
		this.carbohydrates = 0;
		this.proteins = 0;
		this.comments = "";
		this.date = date;
		this.time = Utils.getTodaysTime();;
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
		this.time = Utils.getTodaysTime();
		this.intakeType = FoodIntakeType.LUNCH;
	}

	/*
	 * Food IntakeID - Table FoodIntake
	 */
	public int getIntakeID() {
		return this.intakeID;
	}

	public void setIntakeID(int i) {
		this.intakeID = i;
	}

	/*
	 * FoodName - Table FoodIntake
	 */
	public String getName() {
		return this.name;
	}

	public void setName(String name) throws Exception {
		if (name == null || name.trim().equals("")) {
			throw new Exception("Please enter food name");
		}
		// if (!(name.matches("[a-zA-Z\\s]"))) {
		// throw new Exception("Please enter characters only");
		// }
		this.name = name;
	}

	/*
	 * Date - Table FoodIntake
	 */
	public void setDate(Date date) throws Exception {
		if (Utils.getTodaysDate().compareTo(date) < 0) {
			throw new Exception("Incorrect date");
		}
		this.date = date;
	}

	public Date getDate() {
		return this.date;
	}

	/**
	 * @return the weight
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * @param weight
	 *            the weight to set
	 * @throws Exception
	 */
	public void setWeight(int weight) throws Exception {
		this.weight = weight;

	}

	/**
	 * @return the calories
	 */
	public int getCalories() {
		return calories;
	}

	/**
	 * @param calories
	 *            the calories to set
	 * @throws Exception
	 */
	public void setCalories(int calories) throws Exception {
		this.calories = calories;
	}

	/**
	 * @return the fat
	 */
	public int getFat() {
		return fat;
	}

	/**
	 * @param fat
	 *            the fat to set
	 * @throws Exception
	 */
	public void setFat(int fat) throws Exception {
		this.fat = fat;
	}

	/**
	 * @return the carbohydrates
	 */
	public int getCarbohydrates() {
		return carbohydrates;
	}

	/**
	 * @param carbohydrates
	 *            the carbohydrates to set
	 * @throws Exception
	 */
	public void setCarbohydrates(int carbohydrates) throws Exception {
		this.carbohydrates = carbohydrates;
	}

	/**
	 * @return the proteins
	 */
	public int getProteins() {
		return proteins;
	}

	/**
	 * @param proteins
	 *            the proteins to set
	 * @throws Exception
	 */
	public void setProteins(int proteins) throws Exception {
		this.proteins = proteins;
	}

	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments
	 *            the comments to set
	 * @throws Exception
	 */
	public void setComments(String comments) throws Exception {
		this.comments = comments;
	}

	/**
	 * @return the time
	 */
	public Date getTime() {
		return time;
	}

	/**TODO - Verify SetTIME
	 * @param time
	 *            the time to set
	 * @throws Exception 
	 */
	public void setTime(Date time) throws Exception {
		if (Utils.getTodaysTime().compareTo(time) < 0) {
			throw new Exception("Incorrect time");
		}
		this.time = time;
	}

}
