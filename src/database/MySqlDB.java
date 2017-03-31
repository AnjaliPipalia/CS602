/**
 * 
 */
package database;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import food.FoodIntake;
import food.FoodIntakeType;

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
		String query = " insert into FoodIntake (Name, Date)" + " values (?, ?)";


		// create the mysql insert preparedstatement
		PreparedStatement preparedStmt;
		try {
			preparedStmt = connection.prepareStatement(query);
			preparedStmt.setString(1, foodIntake.getName());
			preparedStmt.setDate(2, foodIntake.getDate());
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
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see database.Database#read(java.lang.String, java.util.Date,
	 * java.util.Date)
	 */
	@Override
	public List<FoodIntake> read(String foodName, Date from, Date to) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see database.Database#update(food.FoodIntake)
	 */
	@Override
	public boolean update(FoodIntake foodIntake) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see database.Database#delete(food.FoodIntake)
	 */
	@Override
	public boolean delete(FoodIntake foodIntake) {
		// TODO Auto-generated method stub
		return false;
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
			connection = DriverManager.getConnection("jdbc:mysql://sql2.njit.edu/arp226", "arp226", "5WucrXW9e");

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
