/**
 * The  Main Application - DietTracker_v1.0
 * @author arp226
 */
package main;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import javax.swing.JOptionPane;

import controller.Controller;
import database.Database;
import database.DatabaseFactory;
import exception.DatabaseException;
import view.DashBoard;
import view.UI;

public class DietTrackerApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			if (isRunning()) {
				JOptionPane.showMessageDialog(null, "Already Running...");
				System.exit(1);
			} else {

				Configuration.setProperties("DietTracker.properties");
				Database database = DatabaseFactory.getDatabase();
				database.createTables();
				UI window = new DashBoard();
				Controller controller = new Controller(database, window);
				controller.initialize();
				window.open();
			}
		} catch (DatabaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Validating single instance of the application
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
