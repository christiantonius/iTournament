import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;


public class CreateNewEvent_2 extends Dialog {

	protected Object result;
	protected Shell shell;
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private Text text_refereeName;
	private Table table_refereeList;
	private Text text_eventName;
	private Spinner spinner_fees;
	private Combo combo_sportTypes;
	private DateTime dateTime_date;
	private Combo combo_venue;
	private Spinner spinner_noOfTeams;
	private Spinner spinner_registrationFees;
	private Label lblEventName;
	private Label lbl_remark;
	private Event newEvent;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public CreateNewEvent_2(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open(Event event, int sportTypes, String eventName, int year, int month, int day, int venue, String noOfTeams, String registrationFees) {
		newEvent = event;
		createContents();
		updateEventDetails(sportTypes, eventName, year, month, day, venue, noOfTeams, registrationFees);
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}
	
	public static void center(Shell shell) {
	    Rectangle bds = shell.getDisplay().getBounds();
	    Point p = shell.getSize();
	
	    int nLeft = (bds.width - p.x) / 2;
	    int nTop = (bds.height - p.y) / 2;
	
	    shell.setBounds(nLeft, nTop, p.x, p.y);
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), getStyle());
		shell.setSize(1280, 800);
		shell.setText("Create New Event");
		shell.setMinimumSize(1280,800);										//Set shell dimension
		center(shell);
		
		Group grpEventDetails = new Group(shell, SWT.NONE);
		grpEventDetails.setText("Event Details");
		grpEventDetails.setBounds(50, 30, 390, 267);
		
		Label lblSportType = new Label(grpEventDetails, SWT.NONE);
		lblSportType.setFont(SWTResourceManager.getFont("Lucida Grande", 14, SWT.NORMAL));
		lblSportType.setBounds(10, 19, 130, 20);
		lblSportType.setText("Sport Types");
		
		Label lblDate = new Label(grpEventDetails, SWT.NONE);
		lblDate.setFont(SWTResourceManager.getFont("Lucida Grande", 14, SWT.NORMAL));
		lblDate.setBounds(10, 97, 130, 20);
		lblDate.setText("Date");
		
		Label lblVenue = new Label(grpEventDetails, SWT.NONE);
		lblVenue.setFont(SWTResourceManager.getFont("Lucida Grande", 14, SWT.NORMAL));
		lblVenue.setBounds(10, 136, 130, 20);
		lblVenue.setText("Venue");
		
		Label lblNoOfTeams = new Label(grpEventDetails, SWT.NONE);
		lblNoOfTeams.setFont(SWTResourceManager.getFont("Lucida Grande", 14, SWT.NORMAL));
		lblNoOfTeams.setBounds(10, 175, 130, 20);
		lblNoOfTeams.setText("No.of Teams");
		
		Label lblRegistrationFees = new Label(grpEventDetails, SWT.NONE);
		lblRegistrationFees.setFont(SWTResourceManager.getFont("Lucida Grande", 14, SWT.NORMAL));
		lblRegistrationFees.setBounds(10, 214, 130, 20);
		lblRegistrationFees.setText("Registration Fees");
		
		combo_sportTypes = new Combo(grpEventDetails, SWT.READ_ONLY);
		combo_sportTypes.setFont(SWTResourceManager.getFont("Lucida Grande", 14, SWT.NORMAL));
		combo_sportTypes.setBounds(156, 14, 220, 25);
		combo_sportTypes.add("Soccer");
		combo_sportTypes.select(0);
		
		combo_venue = new Combo(grpEventDetails, SWT.READ_ONLY);
		combo_venue.setItems(new String[] {"Basketball Courts", "Dance Studio", "Gym (MPSH 3) ", "Gym (Bukit Timah Campus)", "Handball Courts", "MPSH 1", "MPSH 2", "MPSH 4", "MPSH 5", "MPSH 6", "Multi-Purpose Courts", "Multi-Purpose Field ", "Netball Courts", "Sepak Takraw Court", "Sports Climbing Gym", "Squash Courts ", "Tennis Courts", "Volleyball Courts"});
		combo_venue.setFont(SWTResourceManager.getFont("Lucida Grande", 14, SWT.NORMAL));
		combo_venue.setBounds(156, 128, 220, 25);
		combo_venue.select(0);
		
		spinner_noOfTeams = new Spinner(grpEventDetails, SWT.BORDER);
		spinner_noOfTeams.setEnabled(false);
		spinner_noOfTeams.setMaximum(100000);
		spinner_noOfTeams.setFont(SWTResourceManager.getFont("Lucida Grande", 14, SWT.NORMAL));
		spinner_noOfTeams.setBounds(156, 167, 220, 22);
		
		spinner_registrationFees = new Spinner(grpEventDetails, SWT.BORDER);
		spinner_registrationFees.setMaximum(1000000000);
		spinner_registrationFees.setDigits(2);
		spinner_registrationFees.setFont(SWTResourceManager.getFont("Lucida Grande", 14, SWT.NORMAL));
		spinner_registrationFees.setBounds(156, 203, 220, 22);
		
		dateTime_date = new DateTime(grpEventDetails, SWT.BORDER | SWT.DROP_DOWN);
		dateTime_date.setFont(SWTResourceManager.getFont("Lucida Grande", 14, SWT.NORMAL));
		dateTime_date.setBounds(156, 89, 220, 25);
		formToolkit.adapt(dateTime_date);
		formToolkit.paintBordersFor(dateTime_date);
		
		text_eventName = new Text(grpEventDetails, SWT.BORDER);
		text_eventName.setFont(SWTResourceManager.getFont("Lucida Grande", 14, SWT.NORMAL));
		text_eventName.setBounds(156, 53, 220, 22);
		formToolkit.adapt(text_eventName, true, true);
		
		lblEventName = new Label(grpEventDetails, SWT.NONE);
		lblEventName.setBackground(SWTResourceManager.getColor(212, 212, 212));
		lblEventName.setFont(SWTResourceManager.getFont("Lucida Grande", 14, SWT.NORMAL));
		lblEventName.setBounds(10, 56, 130, 20);
		formToolkit.adapt(lblEventName, true, true);
		lblEventName.setText("Event Name");
		
		Group grpRefereeDetails = new Group(shell, SWT.NONE);
		grpRefereeDetails.setText("Referee Details");
		grpRefereeDetails.setBounds(510, 30, 720, 540);
		
		Group grpReferee = new Group(grpRefereeDetails, SWT.NONE);
		grpReferee.setText("Referee");
		grpReferee.setBounds(40, 25, 320, 130);
		
		Label lblRefereeName = new Label(grpReferee, SWT.NONE);
		lblRefereeName.setFont(SWTResourceManager.getFont("Lucida Grande", 14, SWT.NORMAL));
		lblRefereeName.setBounds(10, 15, 105, 20);
		lblRefereeName.setText("Referee Name");
		
		Label lbl_fees = new Label(grpReferee, SWT.NONE);
		lbl_fees.setFont(SWTResourceManager.getFont("Lucida Grande", 14, SWT.NORMAL));
		lbl_fees.setBounds(10, 50, 125, 20);
		lbl_fees.setText("Fees");
		
		spinner_fees = new Spinner(grpReferee, SWT.BORDER);
		spinner_fees.setDigits(2);
		spinner_fees.setFont(SWTResourceManager.getFont("Lucida Grande", 14, SWT.NORMAL));
		spinner_fees.setBounds(141, 46, 165, 22);
		
		Button btnAddReferee = new Button(grpReferee, SWT.NONE);
		btnAddReferee.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(text_refereeName.getText().equals("") == false) {
					String refereeName = text_refereeName.getText();
					String fees = spinner_fees.getText();
					
		   			addTableItem(table_refereeList, refereeName, fees);
		   			clearInput();
				}
				
				else lbl_remark.setText("Enter referee's details to add referee.");
			}
		});
		btnAddReferee.setFont(SWTResourceManager.getFont("Lucida Grande", 14, SWT.NORMAL));
		btnAddReferee.setText("Add Referee");
		btnAddReferee.setBounds(200, 74, 106, 30);
		
		text_refereeName = new Text(grpReferee, SWT.BORDER);
		text_refereeName.setFont(SWTResourceManager.getFont("Lucida Grande", 14, SWT.NORMAL));
		text_refereeName.setBounds(141, 15, 165, 22);
		formToolkit.adapt(text_refereeName, true, true);

		
		Button btn_remove = formToolkit.createButton(grpRefereeDetails, "Remove", SWT.NONE);
		btn_remove.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
		   		if(table_refereeList.getItemCount() != 0) {	
			   		int[] indices = table_refereeList.getSelectionIndices();
			   	
			   		table_refereeList.remove(indices);
			   		if(indices[0] != 0)										table_refereeList.setSelection(indices[0]-1);
			   		else	if(table_refereeList.getItemCount() != 0)		table_refereeList.setSelection(0);
			   	
			   		lbl_remark.setText("Referee removed.");			   		
		   		}	
		   		
		   		else
		   			lbl_remark.setText("There is no refeee to be removed.");
			}
		});
		btn_remove.setFont(SWTResourceManager.getFont("Lucida Grande", 14, SWT.NORMAL));
		btn_remove.setBounds(600, 483, 80, 30);
		
		table_refereeList = new Table(grpRefereeDetails, SWT.BORDER | SWT.FULL_SELECTION);
		table_refereeList.setBounds(40, 165, 640, 300);
		formToolkit.adapt(table_refereeList);
		formToolkit.paintBordersFor(table_refereeList);
		table_refereeList.setHeaderVisible(true);
		table_refereeList.setLinesVisible(true);
		initTable(table_refereeList);
		
		Button btnBack = formToolkit.createButton(shell, "Back", SWT.BORDER);
		btnBack.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});
		btnBack.setFont(SWTResourceManager.getFont("Lucida Grande", 36, SWT.BOLD));
		btnBack.setBounds(100, 600, 300, 150);
		
		Button btnFinish = formToolkit.createButton(shell, "Finish", SWT.NONE);
		btnFinish.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				if(text_eventName.getText().equals("") == false) {
					if(table_refereeList.getItemCount() != 0) {
						int noOfReferees = table_refereeList.getItemCount();
						newEvent.setNumberOfReferees(noOfReferees);
						newEvent.setEventName(text_eventName.getText());
						
			   			String[] refereeList = new String[noOfReferees];
			   			double[] feeList = new double[noOfReferees];
			   			
			   			getTableItems(noOfReferees, refereeList, feeList);
	
			   			newEvent.createReferee(refereeList, feeList);
			   			newEvent.saveReferee();
			   			newEvent.saveEvent();
			   			
			   			newEvent.setIsCreateEventFinish();
			   			shell.dispose();
					}
					
					else lbl_remark.setText("Enter referee details to proceed.");
				}
				
				else lbl_remark.setText("Enter event name to proceed");
				
			}
		});
		btnFinish.setFont(SWTResourceManager.getFont("Lucida Grande", 36, SWT.BOLD));
		btnFinish.setBounds(880, 600, 300, 150);
		
		lbl_remark = new Label(shell, SWT.NONE);
		lbl_remark.setBounds(510, 582, 720, 14);
		formToolkit.adapt(lbl_remark, true, true);
	}
	
	/*	Initialize table with columns.	*/
	private void initTable(Table table) {
		String[] titles = { "Referees", "Fees"};
		 
		for (int i = 0; i < titles.length; i++) {
			TableColumn column = new TableColumn(table, SWT.NONE);
			column.setText(titles[i]);
			column.setAlignment(SWT.CENTER);
			
			if(titles[i].equals("Referees"))				column.setWidth(500);
			else if(titles[i].equals("Fees"))			column.setWidth(128);
		}
		
	}
	
	/*	Add items to table.	*/
	private static void addTableItem(Table table, String name, String fees) {
		boolean isEmpty = false;
		if(table.getItemCount() == 0)	isEmpty =true;
		
		 TableItem item = new TableItem(table, SWT.NONE);
		 item.setText(0, name);
		 item.setText(1, fees);
		 
		 if(isEmpty)		table.setSelection(0);
	}
	
	private void clearInput() {
		text_refereeName.setText("");
		spinner_fees.setSelection(0);
		
		text_refereeName.setFocus();															
	}
	
	private void updateEventDetails(int sportTypes, String eventName, int year, int month, int day, int venue, String noOfTeams, String registrationFees) {

		combo_sportTypes.select(sportTypes);
		text_eventName.setText(eventName);
		dateTime_date.setDate(year, month, day);		
		combo_venue.select(venue);		
		
		spinner_noOfTeams.setSelection(Integer.parseInt(noOfTeams));

		double fees = Double.parseDouble(registrationFees);
		spinner_registrationFees.setSelection((int)(fees*100));		
	}
	
	private void getTableItems(int count, String[] refereeList, double[] feeList) {
		TableItem[] tableItems = {};
	
		tableItems = table_refereeList.getItems();
		
		for(int i=0; i<count; i++) {
			refereeList[i] = tableItems[i].getText(0);
			feeList[i] = Double.parseDouble(tableItems[i].getText(1));
		}
	}
}

