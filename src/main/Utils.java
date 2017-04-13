/**
 * Class for Utility functions
 * @author arp226
 */

package main;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;

public class Utils {
	public static Date getTodaysDate() {
		Calendar instance = Calendar.getInstance();
		return new Date(instance.getTime().getTime());

	}

	public static Time getTodaysTime() {
		Calendar instance = Calendar.getInstance();
		return new Time(instance.getTime().getTime());
	}
}
