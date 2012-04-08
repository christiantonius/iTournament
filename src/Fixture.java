import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionAdapter;


public class Fixture extends Dialog {

	protected Object result;
	protected Shell shell;
	private Table table_fixtureList;
	private Event existingEvent = new Event();
	Ladder ldr;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public Fixture(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open(Event e) {
		existingEvent = e;
		
		ldr = new Ladder(existingEvent.getEventName());
		ldr.setFirstTime(existingEvent.getYear(), existingEvent.getMonth(), existingEvent.getDay(), existingEvent.getHour(), existingEvent.getMin());
		ldr.setSchedule();
		ldr.printSchedule();
		
		createContents();
		populateTable(false);
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

	public Object open(Event e, Ladder l) {
		existingEvent = e;
		ldr = l;
		
		createContents();
		populateTable(true);
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
	
	private void createContents() {
		shell = new Shell(getParent(), SWT.CLOSE | SWT.MIN | SWT.TITLE | SWT.PRIMARY_MODAL);
		shell.setSize(1280, 800);
		shell.setText("Fixture");
		shell.setMinimumSize(1280,800);										//Set shell dimension
		center(shell);

		Button btn_viewHistory = new Button(shell, SWT.NONE);
		btn_viewHistory.setFont(SWTResourceManager.getFont("Lucida Grande", 36, SWT.BOLD));
		btn_viewHistory.setBounds(95, 370, 300, 150);
		btn_viewHistory.setText("View History");
		btn_viewHistory.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int firstNoOfRounds = ldr.getFirstNoOfRounds();
				
				if(firstNoOfRounds != ldr.getCurrNoOfRounds()) {
					ViewHistory viewHistory = new ViewHistory(shell, shell.getStyle());
					viewHistory.open(ldr);
				}
				
				else
					MessageDialog.openError(shell, "Error", "No history to be viewed.");
			}
		});

		Button btn_enterScore = new Button(shell, SWT.NONE);
		btn_enterScore.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(table_fixtureList.getItemCount() != 0) {	
					int index = table_fixtureList.getSelectionIndex();
					
					if(table_fixtureList.getItem(index).getText(2).equals("") == true) {
						boolean isHomeWin = true;
						String firstTeam = ldr.getFirstTeamOfTheMatch(index);
						String secondTeam = ldr.getSecondTeamOfTheMatch(index);
						
						EnterScore enterScore = new EnterScore(shell, shell.getStyle());
						int[] scores = enterScore.open(firstTeam, secondTeam);
						
						if(scores != null) {
							if(scores[1] > scores[0]) {
								isHomeWin = false; 
								ldr.saveWinner(secondTeam, scores[1], scores[0]);
							}
						
							else		ldr.saveWinner(firstTeam, scores[0], scores[1]);				
					
							updateScoreTable(index, isHomeWin);
						}
					}
				}
			}
		});
		btn_enterScore.setFont(SWTResourceManager.getFont("Lucida Grande", 36, SWT.BOLD));
		btn_enterScore.setBounds(490, 370, 300, 150);
		btn_enterScore.setText("Enter Score");
		
		Button btn_createFixture = new Button(shell, SWT.NONE);
		btn_createFixture.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean isAllScoresEntered = isAllScoresEntered();
				
				if(table_fixtureList.getItemCount() != 0 && isAllScoresEntered == true) {	
					ldr.createFixtures();
					ldr.saveLadder();

					if(table_fixtureList.getItemCount() != 1) {
						ldr.setSchedule();
						ldr.printSchedule();
						populateTable(false);
					}
				}
			}
		});
		btn_createFixture.setFont(SWTResourceManager.getFont("Lucida Grande", 36, SWT.BOLD));
		btn_createFixture.setBounds(885, 370, 300, 150);
		btn_createFixture.setText("Create Fixture");
		
		Button btn_back = new Button(shell, SWT.NONE);
		btn_back.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ldr.saveLadder();
				shell.dispose();
			}
		});
		btn_back.setFont(SWTResourceManager.getFont("Lucida Grande", 36, SWT.BOLD));
		btn_back.setBounds(95, 600, 300, 150);
		btn_back.setText("Back");
		
		Group grp_fixtureList = new Group(shell, SWT.NONE);
		grp_fixtureList.setText("Fixture List");
		grp_fixtureList.setBounds(100, 20, 1080, 330);
		
		table_fixtureList = new Table(grp_fixtureList, SWT.BORDER | SWT.FULL_SELECTION);
		table_fixtureList.setFont(SWTResourceManager.getFont("Lucida Grande", 14, SWT.NORMAL));
		table_fixtureList.setBounds(10, 10, 1056, 293);
		table_fixtureList.setHeaderVisible(true);
		table_fixtureList.setLinesVisible(true);
		initTable(table_fixtureList);
		
		Button btn_printFixtures = new Button(shell, SWT.NONE);
		btn_printFixtures.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(ldr.getCurrNoOfRounds() != 1)
					MessageDialog.openError(shell, "Error", "The tournament has not finish yet.");
				
				else {
					PrintFixtures printFixtures = new PrintFixtures(shell, shell.getStyle());
					printFixtures.open(ldr);
				}
			}
		});
		btn_printFixtures.setText("Print Fixtures");
		btn_printFixtures.setFont(SWTResourceManager.getFont("Lucida Grande", 36, SWT.BOLD));
		btn_printFixtures.setBounds(885, 600, 300, 150);
	}

	private void initTable(Table table) {
		String[] titles = { "Date and Time", "Match", "Score", "Winner"};
		 
		for (int i = 0; i < titles.length; i++) {
			TableColumn column = new TableColumn(table, SWT.NONE);
			column.setText(titles[i]);
			column.setAlignment(SWT.CENTER);
			
			if(titles[i].equals("Date and Time"))				column.setWidth(190);
			else if(titles[i].equals("Match"))					column.setWidth(380);
			else if(titles[i].equals("Score"))					column.setWidth(150);
			else if(titles[i].equals("Winner"))					column.setWidth(334);
		}
	}
	
	private static void addTableItem(Table table, String dateTime, String match) {
		boolean isEmpty = false;
		if(table.getItemCount() == 0)	isEmpty =true;
		
		 TableItem item = new TableItem(table, SWT.NONE);
		 item.setText(0, dateTime);
		 item.setText(1, match);
		 
		 if(isEmpty)		table.setSelection(0);
	}
	
	private static void addTableItem(Table table, String dateTime, String match, String score, String winner) {
		boolean isEmpty = false;
		if(table.getItemCount() == 0)	isEmpty =true;
		
		 TableItem item = new TableItem(table, SWT.NONE);
		 item.setText(0, dateTime);
		 item.setText(1, match);
		 item.setText(2, score);
		 item.setText(3, winner);
		 
		 if(isEmpty)		table.setSelection(0);
	}
	
	private void populateTable(boolean isExistingEvent) {
		table_fixtureList.removeAll();
		
		int noOfMatches = ldr.getCurrNoOfRounds();
		
		for(int i=0; i<noOfMatches; i++) {
			String date = ldr.getMatchDate(i);
			String time = ldr.getMatchTime(i);
			String firstTeam = ldr.getFirstTeamOfTheMatch(i);
			String secondTeam = ldr.getSecondTeamOfTheMatch(i);
			
			String dateTime = date + " " + time;
			String versus = firstTeam + " vs " + secondTeam;
			
			if(isExistingEvent == true) {
				String winner = ldr.getWinner(i);
				int[] tempScores = ldr.getScores(i);
				
				if(winner != null) {
					String scores;
					if(winner.equals(ldr.getFirstTeamOfTheMatch(i)))
						scores = tempScores[0] + " - " + tempScores[1];
				
					else	scores = tempScores[1] + " - " + tempScores[0];

					addTableItem(table_fixtureList, dateTime, versus, scores, winner);
				}
				
				else
					addTableItem(table_fixtureList, dateTime, versus);
			}
			
			else
				addTableItem(table_fixtureList, dateTime, versus);
		}
	}
	
	private void updateScoreTable(int index, boolean isHomeWin) {
		int[] scores = ldr.getScores(index);
		String winner = ldr.getWinner(index);
		String tempScore;
		
		if(isHomeWin)		tempScore = scores[0] + " - " + scores[1];
		else						tempScore = scores[1] + " - " + scores[0];
		
		TableItem match = table_fixtureList.getItem(index);
		
		match.setText(2, tempScore);
		match.setText(3, winner);
	}
	
	private boolean isAllScoresEntered() {
		TableItem[] items = table_fixtureList.getItems();

		for(int i=0; i<items.length; i++) {
			if(items[i].getText(2).equals(""))
				return false;
		}
		
		return true;
	}
}
