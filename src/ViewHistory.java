import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
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


public class ViewHistory extends Dialog {

	protected Object result;
	protected Shell shell;
	private Table table_historyList;
	Ladder ldr;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public ViewHistory(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}
	
	public static void center(Shell shell) {
	    Rectangle bds = shell.getDisplay().getBounds();
	    Point p = shell.getSize();
	
	    int nLeft = (bds.width - p.x) / 2;
	    int nTop = (bds.height - p.y) / 2;
	
	    shell.setBounds(nLeft, nTop, p.x, p.y);
	}

	public Object open(Ladder l) {
		ldr = l;		
		createContents();
		loadRound();
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

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), SWT.CLOSE | SWT.MIN | SWT.TITLE | SWT.PRIMARY_MODAL);
		shell.setSize(1280, 800);
		shell.setText("View History");
		shell.setMinimumSize(1280,800);										//Set shell dimension
		center(shell);
		
		Button btn_back = new Button(shell, SWT.NONE);
		btn_back.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});
		btn_back.setFont(SWTResourceManager.getFont("Lucida Grande", 36, SWT.BOLD));
		btn_back.setBounds(95, 600, 300, 150);
		btn_back.setText("Back");
		
		Group grp_fixtureList = new Group(shell, SWT.NONE);
		grp_fixtureList.setText("Fixture List");
		grp_fixtureList.setBounds(100, 20, 1080, 330);
		
		table_historyList = new Table(grp_fixtureList, SWT.BORDER | SWT.FULL_SELECTION);
		table_historyList.setEnabled(false);
		table_historyList.setFont(SWTResourceManager.getFont("Lucida Grande", 14, SWT.NORMAL));
		table_historyList.setBounds(10, 10, 1056, 293);
		table_historyList.setHeaderVisible(true);
		table_historyList.setLinesVisible(true);
		initTable(table_historyList);
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
	
	private void populateTable(Ladder newLadder) {
		int noOfMatches = newLadder.getCurrNoOfRounds();
		
		for(int i=0; i<noOfMatches; i++) {
			String date = newLadder.getMatchDate(i);
			String time = newLadder.getMatchTime(i);
			String firstTeam = newLadder.getFirstTeamOfTheMatch(i);
			String secondTeam = newLadder.getSecondTeamOfTheMatch(i);
			System.out.println("here1");
			String dateTime = date + " " + time;
			String versus = firstTeam + " vs " + secondTeam;
			System.out.println("here2");

			String winner = newLadder.getWinner(i);
			int[] tempScores = newLadder.getScores(i);
			System.out.println("Winner = " + winner);
			System.out.println("firstteam = " + firstTeam);

			String scores;
			if(winner.equals(firstTeam))
				scores = tempScores[0] + " - " + tempScores[1];
		
			else	scores = tempScores[1] + " - " + tempScores[0];
			System.out.println("here4");


			addTableItem(table_historyList, dateTime, versus, scores, winner);
		}
	}
	
	private void loadRound() {
		int firstNoOfRound = ldr.getFirstNoOfRounds();
		String eventName = ldr.getEventName();
	
		while(firstNoOfRound != ldr.getCurrNoOfRounds()) {
			Ladder newLadder = new Ladder(eventName);
			newLadder.loadLadder(eventName, firstNoOfRound);
			
			populateTable(newLadder);
			
			firstNoOfRound /= 2;
		}
	}

	
}
