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
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;


public class CreateNewEvent_1 extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text_teamName;
	private Text text_playerName;
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private Spinner spinner_jerseyNumber;
	private Table table_playerList;
	private Text text_eventName;
	private Combo combo_captain;
	private Label lbl_remark;
	private Event newEvent = new Event();
	private Spinner spinner_noOfTeams;
	private DateTime dateTime_date;
	private Combo combo_sportTypes;
	private Combo combo_venue;
	private Group grpEventDetails;
	private Spinner spinner_registrationFees;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public CreateNewEvent_1(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
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
		shell.setText("Create New Event");
		shell.setMinimumSize(1280,800);										//Set shell dimension
		center(shell);
		
		grpEventDetails = new Group(shell, SWT.NONE);
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
		
		Label lblEventName = new Label(grpEventDetails, SWT.NONE);
		lblEventName.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		lblEventName.setFont(SWTResourceManager.getFont("Lucida Grande", 14, SWT.NORMAL));
		lblEventName.setBounds(10, 56, 130, 20);
		formToolkit.adapt(lblEventName, true, true);
		lblEventName.setText("Event Name");
		
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
		text_teamName.setBounds(109, 15, 180, 22);
		
		Label lblCaptain = new Label(grpTeam_1, SWT.NONE);
		lblCaptain.setFont(SWTResourceManager.getFont("Lucida Grande", 14, SWT.NORMAL));
		lblCaptain.setBounds(10, 50, 95, 20);
		lblCaptain.setText("Captain");
		
		combo_captain = new Combo(grpTeam_1, SWT.READ_ONLY);
		combo_captain.setFont(SWTResourceManager.getFont("Lucida Grande", 14, SWT.NORMAL));
		combo_captain.setBounds(109, 46, 180, 25);
		combo_captain.select(0);
		
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
		text_playerName.setBounds(121, 15, 165, 22);
		
		spinner_jerseyNumber = new Spinner(grpPlayer, SWT.BORDER);
		spinner_jerseyNumber.setMaximum(99);
		spinner_jerseyNumber.setMinimum(1);
		spinner_jerseyNumber.setFont(SWTResourceManager.getFont("Lucida Grande", 14, SWT.NORMAL));
		spinner_jerseyNumber.setBounds(121, 46, 165, 28);
		
		Button btnAddPlayer = new Button(grpPlayer, SWT.BORDER);
		btnAddPlayer.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(text_playerName.getText().equals("") == false) {
					String playerName = text_playerName.getText();
					String jerseyNumber = spinner_jerseyNumber.getText();
					
		   			addTableItem(table_playerList, playerName, jerseyNumber);
		   			clearInput();
		   			updateCaptainList(true);
		   			
					lbl_remark.setText("Player " + playerName + " , " + jerseyNumber + " added.");
				}
				
				else 
					lbl_remark.setText("Enter player name to add player.");
			}
		});
		btnAddPlayer.setFont(SWTResourceManager.getFont("Lucida Grande", 14, SWT.NORMAL));
		btnAddPlayer.setText("Add Player");
		btnAddPlayer.setBounds(183, 75, 103, 30);
		
		table_playerList = formToolkit.createTable(grpTeam, SWT.BORDER | SWT.FULL_SELECTION);
		table_playerList.setFont(SWTResourceManager.getFont("Lucida Grande", 14, SWT.NORMAL));
		table_playerList.setBounds(40, 165, 640, 300);
		formToolkit.paintBordersFor(table_playerList);
		table_playerList.setHeaderVisible(true);
		table_playerList.setLinesVisible(true);
		initTable(table_playerList);
		
		Button btnRemove = formToolkit.createButton(grpTeam, "Remove", SWT.BORDER);
		btnRemove.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
		   		if(table_playerList.getItemCount() != 0) {	
			   		int[] indices = table_playerList.getSelectionIndices();
			   	
			   		table_playerList.remove(indices);
			   		if(indices[0] != 0)										table_playerList.setSelection(indices[0]-1);
			   		else	if(table_playerList.getItemCount() != 0)		table_playerList.setSelection(0);
			   		
			   		if(table_playerList.getItemCount() != 0)		updateCaptainList(true);
			   		else											updateCaptainList(false);
		   			
			   		lbl_remark.setText("Player removed.");			   		
		   		}	
		   		
		   		else
		   			lbl_remark.setText("There is no player to be removed.");
			}
		});
		btnRemove.setFont(SWTResourceManager.getFont("Lucida Grande", 14, SWT.NORMAL));
		btnRemove.setBounds(494, 476, 80, 30);
		
		Button btnAddTeam = formToolkit.createButton(grpTeam, "Add Team", SWT.BORDER);
		btnAddTeam.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
		   		if(table_playerList.getItemCount() != 0 && text_teamName.getText().equals("") == false) {	
		   			String teamName = text_teamName.getText();
		   			//String captain = combo_captain.getText();
		   			
		   			int teamSize = table_playerList.getItemCount();
		   			
		   			String[] playerList = new String[teamSize];
		   			int[] jerseyList = new int[teamSize];
		   			
		   			getTableItems(teamSize, playerList, jerseyList);

		   			newEvent.createTeam(teamName, teamSize, playerList, jerseyList);
		   			
		   			text_teamName.setText("");
		   			clearInput();
		   			text_teamName.setFocus();
		   			table_playerList.removeAll();
		   			updateCaptainList(false);
		   			lbl_remark.setText("Team " + teamName + " added with " + teamSize + " players.");

		   		}
		   		
		   		else 
		   			lbl_remark.setText("Enter team name and players to add team.");
		   		
			}
		});
		btnAddTeam.setFont(SWTResourceManager.getFont("Lucida Grande", 14, SWT.NORMAL));
		btnAddTeam.setBounds(588, 476, 80, 30);
		
		Button btnCancel = formToolkit.createButton(shell, "Cancel", SWT.BORDER);
		btnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int eventSize = newEvent.getTeamList().size();
				String[] teamList = new String[eventSize];
				
				for(int i=0; i<eventSize; i++) {
					teamList[i] = newEvent.getTeamList().get(i).getTeamName();
					String filename = teamList[i] + ".itm";
					newEvent.deleteFile(filename);
				}
				
				shell.dispose();
			}
		});
		btnCancel.setFont(SWTResourceManager.getFont("Lucida Grande", 36, SWT.BOLD));
		btnCancel.setBounds(100, 600, 300, 150);
		
		Button btnNext = formToolkit.createButton(shell, "Next", SWT.NONE);
		btnNext.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String eventName = text_eventName.getText();
				int noOfTeams = Integer.parseInt(spinner_noOfTeams.getText());
				
				if(eventName.equals("") == false && noOfTeams != 0) {
					if(noOfTeams != newEvent.getTeamList().size())
						lbl_remark.setText("Number of teams allowed in event and number of teams registered do not match.");
						
					else {
						lbl_remark.setText("");
						newEvent.setNumberOfTeams(noOfTeams);
						CreateNewEvent_2 newEvent_2 = new CreateNewEvent_2(shell, shell.getStyle());

						newEvent_2.open(newEvent, combo_sportTypes.getSelectionIndex(), text_eventName.getText(), 
								dateTime_date.getYear(), dateTime_date.getMonth(), dateTime_date.getDay(), 
								combo_venue.getSelectionIndex(), spinner_noOfTeams.getText(), spinner_registrationFees.getText());
					
						if(newEvent.getIsCreateEventFinish() == true)
							shell.dispose();
					}
				}
				
				else
					lbl_remark.setText("Enter all event details to proceed.");
			}
		});
		btnNext.setFont(SWTResourceManager.getFont("Lucida Grande", 36, SWT.BOLD));
		btnNext.setBounds(880, 600, 300, 150);
		
		lbl_remark = new Label(shell, SWT.NONE);
		lbl_remark.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND));
		lbl_remark.setForeground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND));
		lbl_remark.setBounds(510, 582, 720, 14);
		formToolkit.adapt(lbl_remark, true, true);
	}
	
	/*	Initialize table with columns.	*/
	private void initTable(Table table) {
		String[] titles = { "Player", "Number"};
		 
		for (int i = 0; i < titles.length; i++) {
			TableColumn column = new TableColumn(table, SWT.NONE);
			column.setText(titles[i]);
			column.setAlignment(SWT.CENTER);
			
			if(titles[i].equals("Player"))				column.setWidth(500);
			else if(titles[i].equals("Number"))			column.setWidth(128);
		}
		
	}
	
	/*	Add items to table.	*/
	private static void addTableItem(Table table, String name, String number) {
		boolean isEmpty = false;
		if(table.getItemCount() == 0)	isEmpty =true;
		
		 TableItem item = new TableItem(table, SWT.NONE);
		 item.setText(0, name);
		 item.setText(1, number);
		 
		 if(isEmpty)		table.setSelection(0);
	}
	
	private void clearInput() {
		text_playerName.setText("");
		spinner_jerseyNumber.setSelection(0);
		
		text_playerName.setFocus();															
	}
	
	private void updateCaptainList(boolean havePlayers) {
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
	}
	
	private void getTableItems(int count, String[] playerList, int[] jerseyList) {
		TableItem[] tableItems = {};
	
		tableItems = table_playerList.getItems();
		
		for(int i=0; i<count; i++) {
			playerList[i] = tableItems[i].getText(0);
			jerseyList[i] = Integer.parseInt(tableItems[i].getText(1));
		}
	}
}
