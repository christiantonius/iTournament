import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Control;


public class CreateNewEvent_1 extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text_teamName;
	private Text text_playerName;
	private Spinner spinner_jerseyNumber;
	private Table table_playerList;
	private Text text_eventName;
	private Label lbl_remark;
	private Event newEvent = new Event();
	private Combo combo_noOfTeams;
	private DateTime dateTime_date;
	private Combo combo_sportTypes;
	private Group grpEventDetails;
	private Spinner spinner_registrationFees;
	private Combo combo_venue;
	private Table table_teamList;
	private Spinner spinner_eventFunds;
	private boolean isEditingDetails = false;
	private DateTime dateTime_time;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */	
	//this constructor is for Create New Event
	public CreateNewEvent_1(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	//this open is for Create New Event
	public Object open() {
		createContents();
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
	
	//this open is for Edit Details
	public Object open(Event e) {
		newEvent = e;						
		isEditingDetails = true;				//flag to modify UI
		
		createContents();
		loadDataIntoContents();
		
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
		shell = new Shell(getParent(), SWT.CLOSE | SWT.MIN | SWT.TITLE | SWT.PRIMARY_MODAL);
		shell.setSize(1280, 800);
		if(isEditingDetails == false)		shell.setText("Create New Event");
		else											shell.setText("Edit Existing Event ( " + newEvent.getEventName() + ")");
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
		
		Group grpTeam = new Group(shell, SWT.NONE);
		grpTeam.setText("Teams/Players Details");
		grpTeam.setBounds(510, 30, 720, 540);
		
		Group grpTeam_1 = new Group(grpTeam, SWT.NONE);
		grpTeam_1.setText("Team");
		grpTeam_1.setBounds(40, 25, 300, 130);
		grpTeam_1.setLayout(null);
		
		Label lblTeamName = new Label(grpTeam_1, SWT.NONE);
		lblTeamName.setFont(SWTResourceManager.getFont("Lucida Grande", 14, SWT.NORMAL));
		lblTeamName.setBounds(10, 15, 95, 20);
		lblTeamName.setText("Team Name");
		
		text_teamName = new Text(grpTeam_1, SWT.BORDER);
		text_teamName.setFont(SWTResourceManager.getFont("Lucida Grande", 14, SWT.NORMAL));
		text_teamName.setBounds(109, 15, 180, 25);
		
		Button btnAddTeam = new Button(grpTeam_1, SWT.BORDER);
		btnAddTeam.setBounds(186, 73, 100, 30);
		btnAddTeam.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
		   		if(text_teamName.getText().equals("") == false) {
					int noOfTeams = Integer.parseInt(combo_noOfTeams.getText());
					
					if(newEvent.getTeamList().size() == noOfTeams) {
						lbl_remark.setText("Maximum number of teams allowed is " + noOfTeams + ".");
						return;
					}		
			
		   			String teamName = text_teamName.getText();

		   			TableItem[] teamList = table_teamList.getItems();
		   			
		   			for(TableItem team : teamList) {
		   				if(team.getText(0).equals(teamName)) {		
		   					lbl_remark.setText("Team name already exist.");
		   					return;
		   				}
		   			}
		   					   			
		   			newEvent.createTeam(teamName);
		   			addTeamListTableItem(teamName);
		   			clearTeamInput();
		   			table_teamList.select(table_teamList.getItemCount() - 1);
		   			updatePlayerList();
		   		}
		   		
		   		else 
		   			lbl_remark.setText("Enter team name to add team.");	   		
			}
		});
		btnAddTeam.setText("Add Team");
		btnAddTeam.setFont(SWTResourceManager.getFont("Lucida Grande", 14, SWT.NORMAL));
		
		Group grpPlayer = new Group(grpTeam, SWT.NONE);
		grpPlayer.setText("Player");
		grpPlayer.setBounds(380, 25, 300, 130);
		
		Label lblPlayerName = new Label(grpPlayer, SWT.NONE);
		lblPlayerName.setFont(SWTResourceManager.getFont("Lucida Grande", 14, SWT.NORMAL));
		lblPlayerName.setBounds(10, 15, 105, 20);
		lblPlayerName.setText("Player Name");
		
		Label lblJerseyNumber = new Label(grpPlayer, SWT.NONE);
		lblJerseyNumber.setFont(SWTResourceManager.getFont("Lucida Grande", 14, SWT.NORMAL));
		lblJerseyNumber.setBounds(10, 50, 105, 20);
		lblJerseyNumber.setText("Jersey Number");
		
		text_playerName = new Text(grpPlayer, SWT.BORDER);
		text_playerName.setFont(SWTResourceManager.getFont("Lucida Grande", 14, SWT.NORMAL));
		text_playerName.setBounds(121, 15, 165, 25);
		
		spinner_jerseyNumber = new Spinner(grpPlayer, SWT.BORDER);
		spinner_jerseyNumber.setMaximum(99);
		spinner_jerseyNumber.setMinimum(1);
		spinner_jerseyNumber.setFont(SWTResourceManager.getFont("Lucida Grande", 14, SWT.NORMAL));
		spinner_jerseyNumber.setBounds(121, 46, 165, 25);
		
		Button btnAddPlayer = new Button(grpPlayer, SWT.BORDER);
		btnAddPlayer.setFont(SWTResourceManager.getFont("Lucida Grande", 14, SWT.NORMAL));
		btnAddPlayer.setText("Add Player");
		btnAddPlayer.setBounds(186, 73, 100, 30);
		btnAddPlayer.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
	
				if(table_teamList.getItemCount() != 0 && text_playerName.getText().equals("") == false) {			
			   		TableItem[] tableItem = table_teamList.getSelection();
			   		String playerName = text_playerName.getText();
					String jerseyNumber = spinner_jerseyNumber.getText();
					
					boolean isJerseyDuplicated = checkJerseyDuplication(jerseyNumber);
					
					if(isJerseyDuplicated == false) {
			   		
						newEvent.addPlayer(tableItem[0].getText(), playerName, Integer.parseInt(jerseyNumber));		//add player to the team
				   		
						addPlayerListTableItem(playerName, jerseyNumber);													//add player to the table
			   			clearPlayerInput();
			   			
						lbl_remark.setText("Player " + playerName + " , " + jerseyNumber + " added.");
					}
					
					else lbl_remark.setText("Duplicated jersey number.");
				}
		
				else lbl_remark.setText("Enter Team and player name to add player.");
			}
		});
		
		table_playerList = new Table(grpTeam, SWT.BORDER | SWT.FULL_SELECTION);
		table_playerList.setFont(SWTResourceManager.getFont("Lucida Grande", 14, SWT.NORMAL));
		table_playerList.setBounds(259, 165, 421, 300);
		table_playerList.setHeaderVisible(true);
		table_playerList.setLinesVisible(true);
		initPlayerListTable(table_playerList);
		
		Button btnRemove = new Button(grpTeam, SWT.BORDER);
		btnRemove.setText("Remove");
		btnRemove.setFont(SWTResourceManager.getFont("Lucida Grande", 14, SWT.NORMAL));
		btnRemove.setBounds(580, 471, 100, 30);
		btnRemove.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(table_teamList.isFocusControl() == true) {
					if(table_teamList.getItemCount() != 0) {	 
						int[] indices = table_teamList.getSelectionIndices();
						
						TableItem[] team = table_teamList.getSelection();
						String teamName = team[0].getText();
			
						newEvent.removeTeam(teamName);
						
						table_teamList.remove(indices);
				   		if(indices[0] != 0)												table_teamList.setSelection(indices[0]-1);//System.out.print("here5");
				   		else	if(table_teamList.getItemCount() != 0)		table_teamList.setSelection(0);//System.out.print("here6");
				   		
						updatePlayerList();

				   		lbl_remark.setText("Team removed.");			 
					}	
		   		
					else lbl_remark.setText("There is no team to be removed.");
				}

				if(table_playerList.isFocusControl() == true) {
					if(table_playerList.getItemCount() != 0) {	
						int[] indices = table_playerList.getSelectionIndices();
						
						TableItem[] player = table_playerList.getSelection();
						int jerseyNumber = Integer.parseInt(player[0].getText(1));
						
						TableItem[] team = table_teamList.getSelection();
						String teamName = team[0].getText();
						
						newEvent.getTeam(teamName).removePlayer(jerseyNumber);
		
				   		table_playerList.remove(indices);
				   		if(indices[0] != 0)										table_playerList.setSelection(indices[0]-1);
				   		else	if(table_playerList.getItemCount() != 0)		table_playerList.setSelection(0);
				   		
				   		/*if(table_playerList.getItemCount() != 0)		updateCaptainList(true);
				   		else											updateCaptainList(false);*/
				   		
				   		lbl_remark.setText("Player removed.");			   		
					}	
		   		
					else lbl_remark.setText("There is no player to be removed.");
				}
			}
		});
		
		table_teamList =  new Table(grpTeam, SWT.BORDER | SWT.FULL_SELECTION);
		table_teamList.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				updatePlayerList();
			}
		});
		table_teamList.setFont(SWTResourceManager.getFont("Lucida Grande", 14, SWT.NORMAL));
		table_teamList.setBounds(40, 165, 200, 300);
		table_teamList.setHeaderVisible(true);
		table_teamList.setLinesVisible(true);
		initTeamListTable(table_teamList);
		
		Button btnCancel = new Button(shell, SWT.BORDER);
		btnCancel.setText("Cancel");
		btnCancel.setFont(SWTResourceManager.getFont("Lucida Grande", 36, SWT.BOLD));
		btnCancel.setBounds(100, 600, 300, 150);
		btnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {			
				shell.dispose();
			}
		});
		
		Button btnNext =  new Button(shell, SWT.NONE);
		btnNext.setText("Next");
		btnNext.setFont(SWTResourceManager.getFont("Lucida Grande", 36, SWT.BOLD));
		btnNext.setBounds(880, 600, 300, 150);
		btnNext.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String eventName = text_eventName.getText();
				int noOfTeams = Integer.parseInt(combo_noOfTeams.getText());
				
				if(eventName.equals("") == false && noOfTeams != 0) {
					if(noOfTeams != newEvent.getTeamList().size()) {
						if(noOfTeams > newEvent.getTeamList().size())
							lbl_remark.setText("Please register " + (noOfTeams - newEvent.getTeamList().size()) + " more teams to proceed."); 
					}
					
					else {
						//check that every team has at least 1 player
						for(int i=0; i<noOfTeams; i++) {
							if(newEvent.getTeamList().get(i).getTeamSize() < 1) {
								lbl_remark.setText("Every team must have at least 1 player.");
								return;
							}
						}
						
						lbl_remark.setText("");
						
						CreateNewEvent_2 newEvent_2 = new CreateNewEvent_2(shell, shell.getStyle());
						
						if(isEditingDetails == true)
							newEvent_2.setIsEditingDetailsTrue();		//if this is editing details, then newEvent_2 also in editing details mode

						newEvent_2.open(newEvent, combo_sportTypes.getSelectionIndex(), text_eventName.getText(), 
								dateTime_date.getYear(), dateTime_date.getMonth(), dateTime_date.getDay(), dateTime_time.getHours(), dateTime_time.getMinutes(),
								combo_venue.getSelectionIndex(), combo_noOfTeams.getSelectionIndex(), spinner_registrationFees.getText(),
								spinner_eventFunds.getText());
					
						if(newEvent.getIsCreateEventFinish() == true)
							shell.dispose();
					}
				}
				
				else
					lbl_remark.setText("Enter all event details to proceed.");
			}
		});
		
		lbl_remark = new Label(shell, SWT.NONE);
		lbl_remark.setBounds(510, 582, 720, 14);
		
		shell.setTabList(new Control[]{grpEventDetails, grpTeam, btnNext});
	}
	
	/*	Initialize table with columns.	*/
	private void initPlayerListTable(Table table) {
		String[] titles = { "Player", "Number"};
		 
		for (int i = 0; i < titles.length; i++) {
			TableColumn column = new TableColumn(table, SWT.NONE);
			column.setText(titles[i]);
			column.setAlignment(SWT.CENTER);
			
			if(titles[i].equals("Player"))						column.setWidth(320);
			else if(titles[i].equals("Number"))			column.setWidth(98);
		}
	}
	
	private void initTeamListTable(Table table) {
		TableColumn column = new TableColumn(table, SWT.NONE);
		column.setText("Team");
		column.setAlignment(SWT.CENTER);
		column.setWidth(198);
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
	private void addPlayerListTableItem(String name, String number) {
		boolean isEmpty = false;
		if(table_playerList.getItemCount() == 0)	isEmpty =true;
		
		 TableItem item = new TableItem(table_playerList, SWT.NONE);
		 item.setText(0, name);
		 item.setText(1, number);
		 
		 if(isEmpty)		table_playerList.setSelection(0);
	}
	
	private void addTeamListTableItem(String name) {
		boolean isEmpty = false;
		if(table_teamList.getItemCount() == 0)	isEmpty =true;
		
		 TableItem item = new TableItem(table_teamList, SWT.NONE);
		 item.setText(0, name);
		 
		 if(isEmpty)		table_teamList.setSelection(0);
	}
	
	private void clearPlayerInput() {
		text_playerName.setText("");
		spinner_jerseyNumber.setSelection(0);
		
		text_playerName.setFocus();															
	}
	
	private void clearTeamInput() {
		text_teamName.setText("");
		text_playerName.setFocus();															
	}
	
	/*private void updateCaptainList(boolean havePlayers) {
		combo_captain.removeAll();

		if(havePlayers == true) {
			TableItem[] tableItems = {};
			String tempName;
			
			tableItems = table_playerList.getItems();

			for(TableItem item : tableItems) {
				tempName = item.getText(0);
				combo_captain.add(tempName);				
			}
			
			combo_captain.select(0);			
		}
	}*/
	
	private void getTableItems(int count, String[] playerList, int[] jerseyList) {
		TableItem[] tableItems = {};
	
		tableItems = table_playerList.getItems();
		
		for(int i=0; i<count; i++) {
			playerList[i] = tableItems[i].getText(0);
			jerseyList[i] = Integer.parseInt(tableItems[i].getText(1));
		}
	}
	
	private boolean checkJerseyDuplication(String jerseyNumber) {
		TableItem[] tableItems = {};
		String tempJersey;
		
		tableItems = table_playerList.getItems();
		
		for(int i=0; i<tableItems.length; i++) {
			tempJersey = tableItems[i].getText(1);
			if(tempJersey.equals(jerseyNumber))
				return true;
		}
		
		return false;
	}
	
	public void updatePlayerList() {
		table_playerList.removeAll();
		
		if(table_teamList.getItemCount() == 0) 
			return;
		
		TableItem[] tableItem = table_teamList.getSelection();

		String[] playerNames = newEvent.getTeam(tableItem[0].getText()).getAllPlayerNames(); 
		int[] jerseyNumbers = newEvent.getTeam(tableItem[0].getText()).getAllJerseyNumbers();
		
		for(int i=0; i<playerNames.length; i++)
			addPlayerListTableItem(playerNames[i], String.valueOf(jerseyNumbers[i]));
	}
	
	public void loadDataIntoContents() {
		int noOfTeams = newEvent.getEventSize();
		String[] items = combo_noOfTeams.getItems();
				
		text_eventName.setText(newEvent.getEventName());

		dateTime_date.setDate(newEvent.getYear(), newEvent.getMonth() - 1, newEvent.getDay());
		dateTime_time.setTime(newEvent.getHour(), newEvent.getMin(), 0);
		
		//load noOfTeams into combo_noOfTeams
		for(int i=0; i<items.length; i++) {
			if(items[i].equals(String.valueOf(noOfTeams)))
				combo_noOfTeams.select(i);
		}		
		
		double fees = newEvent.getRegFee();
		double funds = newEvent.getEventFund();
		
		spinner_registrationFees.setSelection((int)(fees*100));	
		spinner_eventFunds.setSelection((int)(funds*100));	
		
		//add Teams into table_teamList
		for(int i=0; i<newEvent.getEventSize(); i++) {
			String teamName = newEvent.getTeamList().get(i).getTeamName();
			addTeamListTableItem(teamName);
		}
	}
}
