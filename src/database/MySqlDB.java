/**
 * The MySqlDB program implements Database.java that simply 
 * runs query for create tables/Add/Update/Delete and also Open 
 * and Close database connection
 * 
 * @author arp226
 * @version 1.0
 */
package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exception.DatabaseException;
import food.FoodIntake;
import food.FoodIntakeType;
import main.Configuration;

public class MySqlDB implements Database {

	Connection connection = null;

	/**
	 * Insert details into table FoodIntake
	 * 
	 * @param foodIntake
	 *            FoodIntake object being inserted into database
	 * 
	 * @returns boolean success value of the method
	 * 
	 * @see database.Database#create(food.FoodIntake)
	 */

	@Override
	public boolean save(FoodIntake foodIntake) {
		open();
		// the mysql insert statement
		String query = " insert into FoodIntake (Name, Date,Time,Weight,Calories,Fat,Carbohydrates,Proteins,Comments,IntakeTypeID)"
				+ " values (?, ?,?,?,?,?,?,?,?,?)";

		// create the mysql insert preparedstatement
		PreparedStatement preparedStmt;
		try {
			preparedStmt = connection.prepareStatement(query);
			preparedStmt.setString(1, foodIntake.getName());
			preparedStmt.setDate(2, foodIntake.getDate());
			preparedStmt.setTime(3, foodIntake.getTime());
			preparedStmt.setInt(4, foodIntake.getWeight());
			preparedStmt.setInt(5, foodIntake.getCalories());
			preparedStmt.setInt(6, foodIntake.getFat());
			preparedStmt.setInt(7, foodIntake.getCarbohydrates());
			preparedStmt.setInt(8, foodIntake.getProteins());
			preparedStmt.setString(9, foodIntake.getComments());
			preparedStmt.setInt(10, foodIntake.getIntakeType().ordinal());
			preparedStmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			close();
			return false;

		}

		return close();
	}

	/**
	 *
	 * Reads details from table FoodIntake
	 * 
	 * @param foodName,from
	 *            and to
	 * @return List of type FoodIntake
	 * @see database.Database#read(java.lang.String, java.util.Date,
	 *      java.util.Date)
	 */
	@Override
	public List<FoodIntake> read(String foodName, Date from, Date to) {
		List<FoodIntake> foodList = new ArrayList<>();
		open();
		try {

			Statement stmt = connection.createStatement();
			ResultSet rs;
			String query = getSearchQuery(foodName, from, to);

			rs = stmt.executeQuery(query);

			while (rs.next()) {
				FoodIntake foodIntake = new FoodIntake();

				foodIntake.setName(rs.getString("Name"));
				foodIntake.setIntakeID(rs.getInt("IntakeID"));
				foodIntake.setIntakeType(FoodIntakeType.values()[rs.getInt("IntakeTypeID")]);
				foodIntake.setDate(rs.getDate("Date"));
				foodIntake.setTime(rs.getTime("Time"));
				foodIntake.setWeight(rs.getInt("Weight"));
				foodIntake.setCalories(rs.getInt("Calories"));
				foodIntake.setFat(rs.getInt("Fat"));
				foodIntake.setCarbohydrates(rs.getInt("Carbohydrates"));
				foodIntake.setProteins(rs.getInt("Proteins"));
				foodIntake.setComments(rs.getString("Comments"));

				foodList.add(foodIntake);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			
		}

		close();
		return foodList;

	}

	/**
	 * Search query based on foodName,fromDate and toDate
	 * 
	 * @param foodName
	 * @param from
	 * @param to
	 * @return String
	 */
	private String getSearchQuery(String foodName, Date from, Date to) {
		String query = "select * from FoodIntake where ";
		if (foodName != null && !foodName.trim().equals("")) {
			query += "Name like '%" + foodName + "%'";
		}
		if ((foodName != null && !foodName.trim().equals("")) && (from != null || to != null)) {
			query += " AND ";
		}
		if (from != null && to == null) {
			query += "Date > '" + from + "' ";
		} else if (from == null && to != null) {
			query += "Date < '" + to + "' ";
		} else if (from != null && to != null) {
			query += "Date Between '" + from + "' and '" + to + "' ";
		}
		query += "order by IntakeID desc";
		return query;
	}

	/**
	 * Updates database values for the FoodIntake object
	 * 
	 * @param foodIntake
	 *            FoodIntake
	 * @return boolean
	 * @see database.Database#update(food.FoodIntake)
	 */
	@Override
	public boolean update(FoodIntake foodIntake) {
		open();
		try {
			// using a sql update query
			PreparedStatement preparedStmt = connection
					.prepareStatement("UPDATE FoodIntake SET Name = ?,Date = ?,Time=?,Weight=?,"
							+ "Calories=?,Fat=?,Carbohydrates=?,Proteins=?,"
							+ "Comments=?,IntakeTypeID=? WHERE IntakeID = ?; ");

			preparedStmt.setString(1, foodIntake.getName());
			preparedStmt.setDate(2, foodIntake.getDate());
			preparedStmt.setTime(3, foodIntake.getTime());
			preparedStmt.setInt(4, foodIntake.getWeight());
			preparedStmt.setInt(5, foodIntake.getCalories());
			preparedStmt.setInt(6, foodIntake.getFat());
			preparedStmt.setInt(7, foodIntake.getCarbohydrates());
			preparedStmt.setInt(8, foodIntake.getProteins());
			preparedStmt.setString(9, foodIntake.getComments());
			preparedStmt.setInt(10, foodIntake.getIntakeType().ordinal());
			preparedStmt.setInt(11, foodIntake.getIntakeID());
			// call executeUpdate to execute our sql update statement
			preparedStmt.executeUpdate();
			preparedStmt.close();
		} catch (SQLException se) {
			se.printStackTrace();
			close();
			return false;
		}

		return close();
	}

	/**
	 * Delete values from the database
	 * 
	 * @param foodIntake
	 *            FoodIntake
	 * @return boolean
	 * 
	 * @see database.Database#delete(food.FoodIntake)
	 */
	@Override
	public boolean delete(FoodIntake foodIntake) {
		open();
		try {
			String query = "delete from FoodIntake where IntakeID = ?";
			PreparedStatement preparedStmt = connection.prepareStatement(query);
			preparedStmt.setInt(1, foodIntake.getIntakeID());

			// execute the preparedstatement
			preparedStmt.execute();
			preparedStmt.close();

		} catch (SQLException se) {
			se.printStackTrace();
			close();
			return false;
		}

		return close();
	}

	/**
	 *
	 * Open connection to the database
	 * 
	 * @return boolean
	 * @see database.Database#open()
	 */
	@Override
	public boolean open() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("MySql JDBC driver not found");
			e.printStackTrace();
			return false;
		}

		try {
			connection = DriverManager.getConnection(Configuration.getDBUrl(), Configuration.getDBUserName(),
					Configuration.getDBPassword());
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();

			return false;
		}
		return connection != null;
	}

	/**
	 * Close the database connection
	 * 
	 * @return boolean
	 * @see database.Database#close()
	 */
	@Override
	public boolean close() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Create tables FoodIntake and FoodIntakeType Insert values into table
	 * FoodIntakeType
	 */
	@Override
	public void createTables() throws DatabaseException {
		open();

		try {
			// Query for table FoodIntakeType
			String sqlCreate2 = "Create table IF NOT EXISTS FoodIntakeType(IntakeTypeID INT AUTO_INCREMENT PRIMARY KEY NOT NULL , Name VARCHAR(20) NOT NULL)";
			String sqlInsert2 = "INSERT IGNORE INTO FoodIntakeType (IntakeTypeID,Name) VAlUES ('1','Breakfast'),('2','Lunch'),('3','Dinner'),('4','Snacks'),('5','PartyMeal'),('6','Meal'),('7','Others')";
			// Query for table FoodIntake
			String sqlCreate = "Create table IF NOT EXISTS FoodIntake (IntakeID INT AUTO_INCREMENT PRIMARY KEY NOT NULL ,"
					+ " Name VARCHAR(20) NOT NULL, IntakeTypeID INT ,Date DATE, Time TIME, Calories INT,Carbohydrates "
					+ "INT, Comments VARCHAR(100), Fat INT, Proteins INT, Weight INT,CONSTRAINT FK_FoodIntakeType FOREIGN KEY (IntakeTypeID) REFERENCES FoodIntakeType(IntakeTypeID) )";

			Statement stmt = connection.createStatement();

			stmt.executeUpdate(sqlCreate2);
			stmt.execute(sqlInsert2);
			stmt.executeUpdate(sqlCreate);

		} catch (Exception e) {
			e.printStackTrace();
			close();
			throw new DatabaseException("Connection to database failed!");

		}
		close();
	}

}
