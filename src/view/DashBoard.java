/**
 * 
 */
package view;

//import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
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
	//protected Shell shlDietTracker;

	/**
	 * Open the window.
	 */
	public void open() {
		display = Display.getDefault();
		createContents();
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
			
		shell = new Shell(display);
		//Image image = new Image(display, "C:\\Users\\arp226\\Desktop\\DIET.jpg");
		Image image = new Image(display, getClass().getClassLoader().getResource("resources/DIET.jpg").getPath());
		
		shell.setImage(image);
		shell.setSize(1215, 739);
		shell.setText("Diet Tracker");
		
		Group grpSearch = new Group(shell, SWT.NONE);
		grpSearch.setText("Search Criteria");
		grpSearch.setBounds(10, 10, 1177, 78);
		
		Label lblFoodNameLabel = new Label(grpSearch, SWT.NONE);
		lblFoodNameLabel.setBounds(10, 31, 82, 29);
		lblFoodNameLabel.setText("Food Name");
		
		Text txtFoodName = new Text(grpSearch, SWT.BORDER);
		txtFoodName.setBounds(98, 31, 530, 29);
		
		Label lblFromeDateLabel = new Label(grpSearch, SWT.CENTER);
		lblFromeDateLabel.setBounds(new Rectangle(0, 0, 100, 0));
		lblFromeDateLabel.setAlignment(SWT.CENTER);
		lblFromeDateLabel.setBounds(634, 31, 70, 29);
		lblFromeDateLabel.setText("From Date");
		
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
		
		Group grpSearchResults = new Group(shell, SWT.NONE);
		grpSearchResults.setText("Search Results");
		grpSearchResults.setBounds(10, 94, 1177, 298);
		
		//CheckboxTableViewer checkboxTableViewer = CheckboxTableViewer.newCheckList(grpSearchResults, SWT.BORDER | SWT.FULL_SELECTION);
		Table table = new Table(grpSearchResults, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(10, 25, 1157, 227);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(52);
		
		TableColumn tblclmnDescription = new TableColumn(table, SWT.CENTER);
		tblclmnDescription.setWidth(110);
		tblclmnDescription.setText("Meal Type");
		
		TableColumn tblclmnFoodName = new TableColumn(table, SWT.CENTER);
		tblclmnFoodName.setWidth(110);
		tblclmnFoodName.setText("Food Name");
		
		TableColumn tblclmnDate = new TableColumn(table, SWT.CENTER);
		tblclmnDate.setWidth(108);
		tblclmnDate.setText("Date");
		
		TableColumn tblclmnTime = new TableColumn(table, SWT.CENTER);
		tblclmnTime.setWidth(108);
		tblclmnTime.setText("Time");
		
		TableColumn tblclmnWeightkg = new TableColumn(table, SWT.CENTER);
		tblclmnWeightkg.setWidth(108);
		tblclmnWeightkg.setText("Weight (kg)");
		
		TableColumn tblclmnCalories = new TableColumn(table, SWT.CENTER);
		tblclmnCalories.setWidth(108);
		tblclmnCalories.setText("Calories");
		
		TableColumn tblclmnFatg = new TableColumn(table, SWT.CENTER);
		tblclmnFatg.setWidth(108);
		tblclmnFatg.setText("Fat (g)");
		
		TableColumn tblclmnCarbohydrates = new TableColumn(table, SWT.CENTER);
		tblclmnCarbohydrates.setWidth(108);
		tblclmnCarbohydrates.setText("Carbohydrates");
		
		TableColumn tblclmnProteinsg = new TableColumn(table, SWT.CENTER);
		tblclmnProteinsg.setWidth(108);
		tblclmnProteinsg.setText("Proteins (g)");
		
		TableColumn tblclmnComments = new TableColumn(table, SWT.CENTER);
		tblclmnComments.setWidth(125);
		tblclmnComments.setText("Comments");
		
		Button btnEdit = new Button(grpSearchResults, SWT.NONE);
		btnEdit.setBounds(981, 258, 90, 30);
		btnEdit.setText("Edit");
		
		Button btnDelete = new Button(grpSearchResults, SWT.NONE);
		btnDelete.setLocation(1077, 258);
		btnDelete.setSize(90, 30);
		btnDelete.setText("Delete");
		
		Group grpDietDetails = new Group(shell, SWT.NONE);
		grpDietDetails.setText("Diet Details");
		grpDietDetails.setBounds(10, 398, 1177, 284);
		
		Label label = new Label(grpDietDetails, SWT.NONE);
		label.setBounds(new Rectangle(0, 0, 100, 0));
		label.setText("Food Name");
		label.setBounds(14, 35, 78, 20);
		
		Text tFoodName = new Text(grpDietDetails, SWT.BORDER);
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
		
		DateTime tDate = new DateTime(grpDietDetails, SWT.DROP_DOWN);
		tDate.setBounds(98, 61, 107, 28);
		
		Label lblTime = new Label(grpDietDetails, SWT.CENTER);
		lblTime.setBounds(new Rectangle(0, 0, 100, 0));
		lblTime.setText("Time");
		lblTime.setAlignment(SWT.CENTER);
		lblTime.setBounds(272, 69, 33, 20);
		
		DateTime tTime = new DateTime(grpDietDetails, SWT.TIME);
		tTime.setBounds(311, 61, 107, 28);
		
		Label lblWeightkg = new Label(grpDietDetails, SWT.NONE);
		lblWeightkg.setBounds(new Rectangle(0, 0, 100, 0));
		lblWeightkg.setText("Weight (kg)");
		lblWeightkg.setBounds(15, 96, 77, 20);
		
		Text tWeight = new Text(grpDietDetails, SWT.BORDER);
		tWeight.setBounds(98, 93, 107, 26);
		
		Label lblCalories = new Label(grpDietDetails, SWT.CENTER);
		lblCalories.setBounds(new Rectangle(0, 0, 100, 0));
		lblCalories.setText("Calories");
		lblCalories.setBounds(252, 96, 53, 20);
		
		Text tCalories = new Text(grpDietDetails, SWT.BORDER);
		tCalories.setBounds(311, 93, 107, 26);
		
		Label lblFatg = new Label(grpDietDetails, SWT.CENTER);
		lblFatg.setBounds(new Rectangle(0, 0, 100, 0));
		lblFatg.setText("Fat (g)");
		lblFatg.setBounds(502, 96, 42, 20);
		
		Text tFat = new Text(grpDietDetails, SWT.BORDER);
		tFat.setBounds(550, 93, 107, 26);
		
		Label lblCarbohydrates = new Label(grpDietDetails, SWT.CENTER);
		lblCarbohydrates.setBounds(new Rectangle(0, 0, 100, 0));
		lblCarbohydrates.setText("Carbohydrates");
		lblCarbohydrates.setBounds(724, 96, 100, 29);
		
		Text tCarbohydrates = new Text(grpDietDetails, SWT.BORDER);
		tCarbohydrates.setBounds(830, 93, 107, 26);
		
		Label lblProteinsg = new Label(grpDietDetails, SWT.CENTER);
		lblProteinsg.setBounds(new Rectangle(0, 0, 100, 0));
		lblProteinsg.setText("Proteins (g)");
		lblProteinsg.setBounds(978, 96, 76, 20);
		
		Control tProteins = new Text(grpDietDetails, SWT.BORDER);
		tProteins.setBounds(1060, 93, 107, 26);
		
		Label lComments = new Label(grpDietDetails, SWT.CENTER);
		lComments.setBounds(new Rectangle(0, 0, 100, 0));
		lComments.setText("Comments");
		lComments.setBounds(21, 218, 71, 20);
		
		Text tComments = new Text(grpDietDetails, SWT.BORDER | SWT.MULTI);
		tComments.setBounds(98, 131, 1069, 107);
		
		Button btnSave = new Button(grpDietDetails, SWT.NONE);
		btnSave.setBounds(885, 244, 90, 30);
		btnSave.setText("Save");
		
		Button btnUpdate = new Button(grpDietDetails, SWT.NONE);
		btnUpdate.setBounds(981, 244, 90, 30);
		btnUpdate.setText("Update");
		
		Button btnReset = new Button(grpDietDetails, SWT.NONE);
		btnReset.setBounds(1077, 244, 90, 30);
		btnReset.setText("Reset");
	}
}