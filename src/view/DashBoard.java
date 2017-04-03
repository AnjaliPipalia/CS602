/**
 * 
 */
package view;

import java.sql.Date;
import java.util.Calendar;

//import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

/**
 * @author arp226
 *
 */
public class DashBoard {
	protected Shell shell;
	Display display;
	private Button btnSave;
	private Text tFoodName;
	private DateTime tDate;
	private Text tCalories;
	private Text tFat;
	private Text tCarbohydrates;
	private Text tProteins;
	private Text tWeight;
	private Text tComments;
	private DateTime tTime;

	// protected Shell shlDietTracker;
	public DashBoard() {
		display = Display.getDefault();
		createContents();
	}

	/**
	 * Open the window.
	 */
	public void open() {
		// display = Display.getDefault();
		// createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {

		initializeShell();
		searchGroupInitializer();
		searchResultsInitializer();
		modifyDiet();
	}

	private void initializeShell() {
		shell = new Shell(display);
		// Image image = new Image(display,
		// "C:\\Users\\arp226\\Desktop\\DIET.jpg");
		Image image = new Image(display, getClass().getClassLoader().getResource("resources/DIET.jpg").getPath());

		shell.setImage(image);
		shell.setSize(1215, 739);
		shell.setText("Diet Tracker");
	}

	private void modifyDiet() {
		Group grpDietDetails = new Group(shell, SWT.NONE);
		grpDietDetails.setText("Diet Details");
		grpDietDetails.setBounds(10, 398, 1177, 284);

		Label label = new Label(grpDietDetails, SWT.NONE);
		label.setBounds(new Rectangle(0, 0, 100, 0));
		label.setText("Food Name");
		label.setBounds(14, 35, 78, 20);

		tFoodName = new Text(grpDietDetails, SWT.BORDER);
		tFoodName.setBounds(98, 26, 530, 29);

		Label lblMealType = new Label(grpDietDetails, SWT.CENTER);
		lblMealType.setBounds(new Rectangle(0, 0, 100, 0));
		lblMealType.setBounds(858, 35, 68, 20);
		lblMealType.setText("Meal Type");

		Combo combo = new Combo(grpDietDetails, SWT.NONE);
		combo.setBounds(932, 32, 235, 28);

		Label lblDate = new Label(grpDietDetails, SWT.CENTER);
		lblDate.setBounds(new Rectangle(0, 0, 100, 0));
		lblDate.setText("Date");
		lblDate.setAlignment(SWT.CENTER);
		lblDate.setBounds(60, 69, 32, 20);

		tDate = new DateTime(grpDietDetails, SWT.DROP_DOWN);
		tDate.setBounds(98, 61, 107, 28);

		Label lblTime = new Label(grpDietDetails, SWT.CENTER);
		lblTime.setBounds(new Rectangle(0, 0, 100, 0));
		lblTime.setText("Time");
		lblTime.setAlignment(SWT.CENTER);
		lblTime.setBounds(272, 69, 33, 20);

		tTime = new DateTime(grpDietDetails, SWT.TIME);
		tTime.setBounds(311, 61, 107, 28);

		Label lblWeightkg = new Label(grpDietDetails, SWT.NONE);
		lblWeightkg.setBounds(new Rectangle(0, 0, 100, 0));
		lblWeightkg.setText("Weight (kg)");
		lblWeightkg.setBounds(15, 96, 77, 20);

		tWeight = new Text(grpDietDetails, SWT.BORDER);
		tWeight.setBounds(98, 93, 107, 26);

		Label lblCalories = new Label(grpDietDetails, SWT.CENTER);
		lblCalories.setBounds(new Rectangle(0, 0, 100, 0));
		lblCalories.setText("Calories");
		lblCalories.setBounds(252, 96, 53, 20);

		tCalories = new Text(grpDietDetails, SWT.BORDER);
		tCalories.setBounds(311, 93, 107, 26);

		Label lblFatg = new Label(grpDietDetails, SWT.CENTER);
		lblFatg.setBounds(new Rectangle(0, 0, 100, 0));
		lblFatg.setText("Fat (g)");
		lblFatg.setBounds(502, 96, 42, 20);

		tFat = new Text(grpDietDetails, SWT.BORDER);
		tFat.setBounds(550, 93, 107, 26);

		Label lblCarbohydrates = new Label(grpDietDetails, SWT.CENTER);
		lblCarbohydrates.setBounds(new Rectangle(0, 0, 100, 0));
		lblCarbohydrates.setText("Carbohydrates");
		lblCarbohydrates.setBounds(724, 96, 100, 29);

		tCarbohydrates = new Text(grpDietDetails, SWT.BORDER);
		tCarbohydrates.setBounds(830, 93, 107, 26);

		Label lblProteinsg = new Label(grpDietDetails, SWT.CENTER);
		lblProteinsg.setBounds(new Rectangle(0, 0, 100, 0));
		lblProteinsg.setText("Proteins (g)");
		lblProteinsg.setBounds(978, 96, 76, 20);

		tProteins = new Text(grpDietDetails, SWT.BORDER);
		tProteins.setBounds(1060, 93, 107, 26);

		Label lComments = new Label(grpDietDetails, SWT.CENTER);
		lComments.setBounds(new Rectangle(0, 0, 100, 0));
		lComments.setText("Comments");
		lComments.setBounds(21, 218, 71, 20);

		tComments = new Text(grpDietDetails, SWT.BORDER | SWT.MULTI);
		tComments.setBounds(98, 131, 1069, 107);

		btnSave = new Button(grpDietDetails, SWT.NONE);
		btnSave.setBounds(885, 244, 90, 30);
		btnSave.setText("Save");

		Button btnUpdate = new Button(grpDietDetails, SWT.NONE);
		btnUpdate.setBounds(981, 244, 90, 30);
		btnUpdate.setText("Update");

		Button btnReset = new Button(grpDietDetails, SWT.NONE);
		btnReset.setBounds(1077, 244, 90, 30);
		btnReset.setText("Reset");
	}

	private void searchResultsInitializer() {
		Group grpSearchResults = new Group(shell, SWT.NONE);
		grpSearchResults.setText("Search Results");
		grpSearchResults.setBounds(10, 94, 1177, 298);

		Table table = new Table(grpSearchResults, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(10, 25, 1157, 227);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		setTableHeaders(table);

		Button btnEdit = new Button(grpSearchResults, SWT.NONE);
		btnEdit.setBounds(981, 258, 90, 30);
		btnEdit.setText("Edit");

		Button btnDelete = new Button(grpSearchResults, SWT.NONE);
		btnDelete.setLocation(1077, 258);
		btnDelete.setSize(90, 30);
		btnDelete.setText("Delete");
	}

	private void setTableHeaders(Table table) {
		addColumn(table, "", 52);
		addColumn(table, "Meal Type");
		addColumn(table, "Food Name");
		addColumn(table, "Date");
		addColumn(table, "Time");
		addColumn(table, "Weight (kg)");
		addColumn(table, "Calories");
		addColumn(table, "Fat (g)");
		addColumn(table, "Carbohydrates");
		addColumn(table, "Proteins (g)");
		addColumn(table, "Comments", 125);
	}

	private void addColumn(Table table, String header, int i) {
		TableColumn column = new TableColumn(table, SWT.CENTER);
		column.setWidth(i);
		column.setText(header);

	}

	private void addColumn(Table table, String header) {

		addColumn(table, header, 108);
	}

	private void searchGroupInitializer() {
		Group grpSearch = new Group(shell, SWT.NONE);
		grpSearch.setText("Search Criteria");
		grpSearch.setBounds(10, 10, 1177, 78);

		Label lblFoodNameLabel = new Label(grpSearch, SWT.NONE);
		lblFoodNameLabel.setBounds(10, 31, 82, 29);
		lblFoodNameLabel.setText("Food Name");

		Text txtFoodName = new Text(grpSearch, SWT.BORDER);
		txtFoodName.setBounds(98, 31, 530, 29);

		Label lblFromDateLabel = new Label(grpSearch, SWT.CENTER);
		lblFromDateLabel.setBounds(new Rectangle(0, 0, 100, 0));
		lblFromDateLabel.setAlignment(SWT.CENTER);
		lblFromDateLabel.setBounds(634, 31, 70, 29);
		lblFromDateLabel.setText("From Date");

		DateTime FromDateTime = new DateTime(grpSearch, SWT.DROP_DOWN);
		FromDateTime.setBounds(710, 31, 102, 28);

		Label lblToDateLabel = new Label(grpSearch, SWT.CENTER);
		lblToDateLabel.setBounds(new Rectangle(0, 0, 100, 0));
		lblToDateLabel.setAlignment(SWT.LEFT);
		lblToDateLabel.setBounds(818, 31, 52, 29);
		lblToDateLabel.setText("To Date");

		DateTime ToDateTime = new DateTime(grpSearch, SWT.DROP_DOWN);
		ToDateTime.setBounds(876, 31, 102, 28);

		Button btnSearchButton = new Button(grpSearch, SWT.NONE);
		btnSearchButton.setBounds(984, 31, 183, 29);
		btnSearchButton.setText("Search");
	}

	public void defineSaveAction(Listener listener) {
		btnSave.addListener(SWT.Selection, listener);
	}

	public String getNewFoodName() {

		return tFoodName.getText();
	}

	public Date getNewFoodDate() {
		Calendar instance = Calendar.getInstance();
		instance.set(Calendar.DAY_OF_MONTH, tDate.getDay());
		instance.set(Calendar.MONTH, tDate.getMonth());
		instance.set(Calendar.YEAR, tDate.getYear());
		return new Date(instance.getTime().getTime());
	}
	

	public void addCaloriesValidation(VerifyListener verifyListener) {
		tCalories.addVerifyListener(verifyListener);

	}

	public void addFatValidation(VerifyListener verifyListener) {
		tFat.addVerifyListener(verifyListener);
	}

	public void addCarbsValidation(VerifyListener verifyListener) {
		tCarbohydrates.addVerifyListener(verifyListener);
	}

	public void addWeightValidation(VerifyListener verifyListener) {
		tWeight.addVerifyListener(verifyListener);
	}

	public void addProteinsValidation(VerifyListener verifyListener) {
		tProteins.addVerifyListener(verifyListener);
	}

	public void addFoodNameValidation(VerifyListener ensureCharsOnly) {
		tFoodName.addVerifyListener(ensureCharsOnly);
	}

	public int getNewCalories() {
		return Integer.parseInt(tCalories.getText());
	}

	public int getNewFat() {
		 return Integer.parseInt(tFat.getText());
	}

	public int getNewCarbohydrates() {
		return Integer.parseInt(tCarbohydrates.getText());
	}

	public int getNewWeight() {
		return Integer.parseInt(tWeight.getText());
	}

	public int getNewProteins() {
		return Integer.parseInt(tProteins.getText());
	}

	public String getNewComments() {
		return tComments.getText();
	}

	public Date getNewTime() {
		Calendar instance = Calendar.getInstance();
		instance.set(Calendar.HOUR_OF_DAY, tTime.getHours());
		instance.set(Calendar.MINUTE, tTime.getMinutes());
		instance.set(Calendar.SECOND, tTime.getSeconds());
		return new Date(instance.getTime().getTime());
	
	}

}