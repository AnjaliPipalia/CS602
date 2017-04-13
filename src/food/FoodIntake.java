/**
 * FoodIntake data class
 * @author arp226
 */
package food;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;

import main.Utils;

public class FoodIntake {
	int intakeID, weight, calories, fat, carbohydrates, proteins;
	String name, comments;
	Date date;
	Time time;
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
		this.time = Utils.getTodaysTime();
		this.intakeType = FoodIntakeType.OTHERS;
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
		this.intakeType = FoodIntakeType.OTHERS;
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

	/**
	 * @return the intakeType
	 */
	public FoodIntakeType getIntakeType() {
		return intakeType;
	}

	/**
	 * @param foodIntakeType
	 *            the intakeType to set
	 */
	public void setIntakeType(FoodIntakeType foodIntakeType) {
		this.intakeType = foodIntakeType;
	}

	/*
	 * FoodName - Table FoodIntake
	 */
	public String getName() {
		return this.name;
	}

	public void setName(String name) {

		this.name = name;
	}

	/*
	 * Date - Table FoodIntake
	 */
	public void setDate(Date date) {

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
	public void setWeight(int weight) {
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
	public void setCalories(int calories) {

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
	public void setFat(int fat) {
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
	public void setCarbohydrates(int carbohydrates) {
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
	public void setProteins(int proteins) {
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
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * @return the time
	 */
	public Time getTime() {
		return time;
	}

	/**
	 * 
	 * @param time2
	 *            the time to set
	 * @throws Exception
	 */
	public void setTime(Time time2) {

		this.time = time2;
	}

}
