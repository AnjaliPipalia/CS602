package main;

import java.sql.Date;
import java.util.Calendar;

public class Utils {
	public static Date getTodaysDate() {
		Calendar instance = Calendar.getInstance();
		return new Date(instance.getTime().getTime());

	}

	public static Date getTodaysTime() {
		Calendar instance = Calendar.getInstance();
		return new Date(instance.getTime().getTime());	}
}
