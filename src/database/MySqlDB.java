/**
 * 
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
import java.util.Calendar;

import java.util.List;

import food.FoodIntake;
import food.FoodIntakeType;
import main.Configuration;

/**
 * @author arp226
 *
 */
public class MySqlDB implements Database {

	Connection connection = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see database.Database#create(food.FoodIntake)
	 */

	@Override
	public boolean create(FoodIntake foodIntake) {
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
			return false;
		}

		return close();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see database.Database#create(food.FoodIntakeType)
	 */
	@Override
	public boolean create(FoodIntakeType foodIntakeType) {
		open();
		// the mysql insert statement
		String query = " insert into FoodIntakeType (IntakeType)" + " values (?)";

		// create the mysql insert preparedstatement
		PreparedStatement preparedStmt;
		try {
			// TODO
			preparedStmt = connection.prepareStatement(query);
			preparedStmt.setString(1, "LUNCH");
			preparedStmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return close();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see database.Database#read(java.lang.String, java.util.Date,
	 * java.util.Date)
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

	private String getSearchQuery(String foodName, Date from, Date to) {
		String query = "select * from FoodIntake where ";
		if (foodName != null && !foodName.trim().equals("")) {
			query += "Name like '%"+ foodName + "%'";
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see database.Database#update(food.FoodIntake)
	 */
	@Override
	public boolean update(FoodIntake foodIntake) {
		open();
		try {
			// create our java preparedstatement using a sql update query
			PreparedStatement preparedStmt = connection.prepareStatement("UPDATE FoodIntake SET Name = ?,Date = ?,Time=?,Weight=?,"
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
			return false;
		}

		return close();
	}

	/*
	 * (non-Javadoc)
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
			return false;
		}

		return close();
	}

	/*
	 * (non-Javadoc)
	 * 
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
			//connection = DriverManager.getConnection("jdbc:mysql://sql2.njit.edu/arp226", "arp226", "5WucrXW9e");
			connection = DriverManager.getConnection(Configuration.getDBUrl(),Configuration.getDBUserName(),Configuration.getDBPassword());
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return false;
		}
		return connection != null;
	}

	/*
	 * (non-Javadoc)
	 * 
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

}
