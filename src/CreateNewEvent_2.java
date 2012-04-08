import java.io.IOException;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
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
import org.eclipse.swt.widgets.Control;

public class CreateNewEvent_2 extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text_refereeName;
	private Table table_refereeList;
	private Text text_eventName;
	private Spinner spinner_fees;
	private Combo combo_sportTypes;
	private DateTime dateTime_date;
	private Combo combo_venue;
	private Spinner spinner_registrationFees;
	private Label lbl_remark;
	private Event newEvent;
	private Group grpEventDetails;
	private Spinner spinner_eventFunds;
	private Combo combo_noOfTeams;
	private DateTime dateTime_time;
	private boolean isEditingDetails = false;

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
	public Object open(Event event, int sportTypes, String eventName, int year, int month, int day, int hour, int minute, int venue, int noOfTeams, String registrationFees, String eventFunds) {
		newEvent = event;
		createContents();
		updateEventDetails(sportTypes, eventName, year, month, day, hour, minute, venue, noOfTeams, registrationFees, eventFunds);
		if(isEditingDetails == true)
			loadRefereeIntoTable();
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
	
	public void setIsEditingDetailsTrue() {
		isEditingDetails = true;
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
		
		grpEventDetails = new Group(shell, SWT.SHADOW_IN);
		grpEventDetails.setText("Event Details");
		grpEventDetails.setBounds(50, 30, 390, 344);
		
		Label lblSportType = new Label(grpEventDetails, SWT.NONE);
		lblSportType.setFont(SWTResourceManager.getFont("Lucida Grande", 14, SWT.NORMAL));
		lblSportType.setBounds(10, 19, 130, 20);
		lblSportType.setText("Sport Types");
		
		Label lblDate = new Label(grpEventDetails, SWT.NONE);
		lblDate.setFont(SWTResourceManager.getFont("Lucida Grande", 14, SWT.NORMAL));
		lblDate.setBounds(10, 97, 130, 20);
		lblDate.setText("Date");
		
		Label lblTime = new Label(grpEventDetails, SWT.NONE);
		lblTime.setText("Time");
		lblTime.setFont(SWTResourceManager.getFont("Lucida Grande", 14, SWT.NORMAL));
		lblTime.setBounds(10, 138, 130, 20);
		
		Label lblVenue = new Label(grpEventDetails, SWT.NONE);
		lblVenue.setFont(SWTResourceManager.getFont("Lucida Grande", 14, SWT.NORMAL));
		lblVenue.setBounds(10, 180, 130, 20);
		lblVenue.setText("Venue");
		
		Label lblNoOfTeams = new Label(grpEventDetails, SWT.NONE);
		lblNoOfTeams.setFont(SWTResourceManager.getFont("Lucida Grande", 14, SWT.NORMAL));
		lblNoOfTeams.setBounds(10, 219, 130, 20);
		lblNoOfTeams.setText("No.of Teams");
		
		Label lblRegistrationFees = new Label(grpEventDetails, SWT.NONE);
		lblRegistrationFees.setFont(SWTResourceManager.getFont("Lucida Grande", 14, SWT.NORMAL));
		lblRegistrationFees.setBounds(10, 258, 130, 20);
		lblRegistrationFees.setText("Registration Fees");
		
		Label lblEventName = new Label(grpEventDetails, SWT.NONE);
		lblEventName.setFont(SWTResourceManager.getFont("Lucida Grande", 14, SWT.NORMAL));
		lblEventName.setBounds(10, 56, 130, 20);
		lblEventName.setText("Event Name");
		
		Label lblFunds = new Label(grpEventDetails, SWT.NONE);
		lblFunds.setText("Event Funds");
		lblFunds.setFont(SWTResourceManager.getFont("Lucida Grande", 14, SWT.NORMAL));
		lblFunds.setBounds(10, 294, 130, 20);
		
		combo_sportTypes = new Combo(grpEventDetails, SWT.READ_ONLY);
		combo_sportTypes.setFont(SWTResourceManager.getFont("Lucida Grande", 14, SWT.NORMAL));
		combo_sportTypes.setBounds(156, 14, 220, 30);
		combo_sportTypes.add("Soccer");
		combo_sportTypes.select(0);
		combo_sportTypes.setFocus();
		
		text_eventName = new Text(grpEventDetails, SWT.BORDER);
		text_eventName.setFont(SWTResourceManager.getFont("Lucida Grande", 14, SWT.NORMAL));
		text_eventName.setBounds(156, 53, 220, 22);
		
		dateTime_date = new DateTime(grpEventDetails, SWT.BORDER | SWT.DROP_DOWN);
		dateTime_date.setFont(SWTResourceManager.getFont("Lucida Grande", 14, SWT.NORMAL));
		dateTime_date.setBounds(156, 89, 220, 25);	
		
		dateTime_time = new DateTime(grpEventDetails, SWT.BORDER | SWT.DROP_DOWN | SWT.TIME | SWT.SHORT);
		dateTime_time.setFont(SWTResourceManager.getFont("Lucida Grande", 14, SWT.NORMAL));
		dateTime_time.setBounds(156, 133, 220, 25);
		dateTime_time.setTime(8, 0, 0);

		combo_venue = new Combo(grpEventDetails, SWT.READ_ONLY);
		combo_venue.setFont(SWTResourceManager.getFont("Lucida Grande", 14, SWT.NORMAL));
		combo_venue.setBounds(156, 176, 220, 25);
		initVenueCombo();
		
		combo_noOfTeams = new Combo(grpEventDetails, SWT.BORDER);
		combo_noOfTeams.setItems(new String[] {"2", "4", "8", "16", "32"});
		combo_noOfTeams.setTextLimit(10000);
		combo_noOfTeams.setFont(SWTResourceManager.getFont("Lucida Grande", 14, SWT.NORMAL));
		combo_noOfTeams.setBounds(156, 215, 220, 25);
		combo_noOfTeams.select(0);
		
		spinner_registrationFees = new Spinner(grpEventDetails, SWT.BORDER);
		spinner_registrationFees.setPageIncrement(1000);
		spinner_registrationFees.setIncrement(100);
		spinner_registrationFees.setMaximum(1000000000);
		spinner_registrationFees.setDigits(2);
		spinner_registrationFees.setFont(SWTResourceManager.getFont("Lucida Grande", 14, SWT.NORMAL));
		spinner_registrationFees.setBounds(156, 255, 220, 25);
		
		spinner_eventFunds = new Spinner(grpEventDetails, SWT.BORDER);
		spinner_eventFunds.setPageIncrement(1000);
		spinner_eventFunds.setMaximum(1000000000);
		spinner_eventFunds.setIncrement(100);
		spinner_eventFunds.setFont(SWTResourceManager.getFont("Lucida Grande", 14, SWT.NORMAL));
		spinner_eventFunds.setDigits(2);
		spinner_eventFunds.setBounds(156, 292, 220, 25);
		
		grpEventDetails.setTabList(new Control[]{combo_sportTypes, text_eventName, dateTime_date, dateTime_time, combo_venue, combo_noOfTeams, spinner_registrationFees, spinner_eventFunds});
		
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
		spinner_fees.setPageIncrement(1000);
		spinner_fees.setIncrement(100);
		spinner_fees.setMaximum(100000);
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
					
					newEvent.createReferee(refereeName, Double.parseDouble(fees));
		   			addRefereeListTableItem(refereeName, fees);
		   			clearRefereeInput();
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
		
		Button btn_remove =new Button(grpRefereeDetails, SWT.NONE);
		btn_remove.setText("Remove");
		btn_remove.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
		   		if(table_refereeList.getItemCount() != 0) {	
			   		int[] indices = table_refereeList.getSelectionIndices();
			   		TableItem[] referee = table_refereeList.getSelection();
			   		
			   		String refereeName = referee[0].getText(0);
			   		double fees = Double.parseDouble(referee[0].getText(1));
			   		
			   		newEvent.removeReferee(refereeName, fees);
			   		
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
		table_refereeList.setHeaderVisible(true);
		table_refereeList.setLinesVisible(true);
		initTable(table_refereeList);
		
		Button btnBack = new Button(shell, SWT.BORDER);
		btnBack.setText("Back");
		btnBack.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});
		btnBack.setFont(SWTResourceManager.getFont("Lucida Grande", 36, SWT.BOLD));
		btnBack.setBounds(100, 600, 300, 150);
		
		Button btnFinish = new Button(shell, SWT.NONE);
		if(isEditingDetails == true)
			btnFinish.setText("Save");
		else
			btnFinish.setText("Finish");
		btnFinish.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				if(text_eventName.getText().equals("") == false) {
					if(table_refereeList.getItemCount() != 0) {
						newEvent.setEventName(text_eventName.getText());
						newEvent.setTimeStart(dateTime_date.getYear(), dateTime_date.getMonth() + 1, dateTime_date.getDay(),
												dateTime_time.getHours(), dateTime_time.getMinutes());
						
						newEvent.setEventFund(Double.parseDouble(spinner_eventFunds.getText()));
						newEvent.setRegFee(Double.parseDouble(spinner_registrationFees.getText()));
						newEvent.setVenue(combo_venue.getText());
			   			
						if(isEditingDetails == true) {
							try {
								newEvent.deleteAllEventRelatedFile();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
						
						newEvent.saveEvent();
						newEvent.saveAllTeams();
			   			newEvent.saveReferee();

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
	
	private void initVenueCombo() {
		if(combo_sportTypes.getItemCount() != 0) {
			String sportType = combo_sportTypes.getItem(0);
			
			if(sportType.equals("Soccer")) {
				String[] venues = {"Multi-Purpose Field"};
				combo_venue.setItems(venues);
				combo_venue.select(0);
			}
		}
	}
	
	/*	Add items to table.	*/
	private void addRefereeListTableItem(String name, String fees) {
		boolean isEmpty = false;
		if(table_refereeList.getItemCount() == 0)	isEmpty =true;
		
		 TableItem item = new TableItem(table_refereeList, SWT.NONE);
		 item.setText(0, name);
		 item.setText(1, fees);
		 
		 if(isEmpty)		table_refereeList.setSelection(0);
	}
	
	private void clearRefereeInput() {
		text_refereeName.setText("");
		spinner_fees.setSelection(0);
		
		text_refereeName.setFocus();															
	}
	
	private void updateEventDetails(int sportTypes, String eventName, int year, int month, int day, int hour, int minute, int venue, int noOfTeams, String registrationFees, String eventFunds) {

		combo_sportTypes.select(sportTypes);
		text_eventName.setText(eventName);
		dateTime_date.setDate(year, month, day);
		dateTime_time.setTime(hour, minute, 0);
		combo_venue.select(venue);		
		
		combo_noOfTeams.select(noOfTeams);

		double fees = Double.parseDouble(registrationFees);
		double funds = Double.parseDouble(eventFunds);

		spinner_registrationFees.setSelection((int)(fees*100));	
		spinner_eventFunds.setSelection((int)(funds*100));	
	}
	
	/*private void getTableItems(int count, String[] refereeList, double[] feeList) {
		TableItem[] tableItems = {};
	
		tableItems = table_refereeList.getItems();
		
		for(int i=0; i<count; i++) {
			refereeList[i] = tableItems[i].getText(0);
			feeList[i] = Double.parseDouble(tableItems[i].getText(1));
		}
	}*/
	
	private void loadRefereeIntoTable() {
		
		int noOfReferees = newEvent.getRefSize();
		
		for(int i=0; i<noOfReferees; i++) {
			String refereeName = newEvent.getRefereeList().get(i).getName();
			double fee = newEvent.getRefereeList().get(i).getCost();
			
			addRefereeListTableItem(refereeName, String.valueOf(fee));
		}
	}
}

