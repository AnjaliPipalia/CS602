/**
 * 
 */
package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import javax.swing.JOptionPane;

import controller.Controller;
import database.Database;
import database.DatabaseFactory;
import food.FoodIntake;
import view.DashBoard;

/**
 * @author arp226
 *
 */
public class NutrientsTrackerApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
			if (isRunning()) {
				JOptionPane.showMessageDialog(null, "Already Running...");
				System.exit(1);
			} else {
				Properties prop = new Properties();
				InputStream input = null;
				input = new FileInputStream("DietTracker.properties");

				// load a properties file
				prop.load(input);
				
				Configuration.setProperties(prop);
				Database database = DatabaseFactory.getDatabase();
				DashBoard window = new DashBoard();
				Controller controller = new Controller(database, window);
				controller.initialize();
				window.open();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static FileLock lock;
	private static FileChannel channel;
	private static File file;

	public static boolean isRunning() {
		try {
			file = new File(System.getProperty("user.home"), "NutrientsTrackerApp.tmp");
			RandomAccessFile raf = new RandomAccessFile(file, "rw");
			channel = raf.getChannel();

			try {
				lock = channel.tryLock();
			} catch (OverlappingFileLockException e) {
				raf.close();
				closeLock();
				return true;
			}

			if (lock == null) {
				raf.close();
				closeLock();
				return true;
			}

			Runtime.getRuntime().addShutdownHook(new Thread() {
				// destroy the lock when the JVM is closing
				public void run() {
					closeLock();
					deleteFile();
				}
			});

			return false;
		} catch (Exception e) {

			closeLock();
			return true;
		}
	}

	private static void closeLock() {
		try {
			lock.release();
		} catch (Exception e) {
		}
		try {

			channel.close();
		} catch (Exception e) {
		}
	}

	private static void deleteFile() {
		try {
			file.delete();
		} catch (Exception e) {
		}
	}

}
