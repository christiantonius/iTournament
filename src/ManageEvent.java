import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Vector;

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
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;


public class ManageEvent extends Dialog {

	protected Object result;
	protected Shell shell;
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private Table table_eventList;
	private Event existingEvent = new Event();

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public ManageEvent(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		populateTable();
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
		shell.setText("Manage Event");
		shell.setMinimumSize(1280,800);										//Set shell dimension
		center(shell);
		
		Button btn_editDetails = new Button(shell, SWT.NONE);
		btn_editDetails.setEnabled(false);
		btn_editDetails.setFont(SWTResourceManager.getFont("Lucida Grande", 36, SWT.BOLD));
		btn_editDetails.setBounds(95, 370, 300, 150);
		btn_editDetails.setText("Edit Details");
		
		Button btn_viewSchedule = new Button(shell, SWT.NONE);
		btn_viewSchedule.setEnabled(false);
		btn_viewSchedule.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(table_eventList.getItemCount() != 0) {	
					TableItem[] tableItems = table_eventList.getSelection();
					String eventName = tableItems[0].getText(0);
					
					try {
						Pair pair = new Pair(eventName);
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btn_viewSchedule.setFont(SWTResourceManager.getFont("Lucida Grande", 36, SWT.BOLD));
		btn_viewSchedule.setBounds(490, 370, 300, 150);
		btn_viewSchedule.setText("View Schedule");
		
		Button btn_cancelEvent = new Button(shell, SWT.NONE);
		btn_cancelEvent.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(table_eventList.getItemCount() != 0) {	
			   		int index = table_eventList.getSelectionIndex();
			   	
					TableItem[] tableItems = table_eventList.getSelection();
					String eventFileName = tableItems[0].getText(0) + ".ivt";
					
					try {
						existingEvent.loadEvent(eventFileName);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
									
					Vector<Team> teamList = existingEvent.getTeamList();
					for(int i=0; i<teamList.size(); i++) {
						String teamFileName = teamList.get(i).getTeamName() + ".itm";
						existingEvent.deleteFile(teamFileName);		//delete team file
					}
					
					existingEvent.deleteFile(eventFileName);		//delete event file
								
					table_eventList.remove(index);
					if(index != 0)										table_eventList.setSelection(index-1);
				   	else if(table_eventList.getItemCount() != 0)		table_eventList.setSelection(0);
				}
			}
		});
		btn_cancelEvent.setFont(SWTResourceManager.getFont("Lucida Grande", 36, SWT.BOLD));
		btn_cancelEvent.setBounds(885, 370, 300, 150);
		btn_cancelEvent.setText("Cancel Event");
		
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
		
		Group grp_eventList = new Group(shell, SWT.NONE);
		grp_eventList.setText("Event List");
		grp_eventList.setBounds(100, 20, 1080, 330);
		
		table_eventList = formToolkit.createTable(grp_eventList, SWT.BORDER | SWT.FULL_SELECTION);
		table_eventList.setFont(SWTResourceManager.getFont("Lucida Grande", 14, SWT.NORMAL));
		table_eventList.setBounds(10, 10, 1056, 293);
		formToolkit.paintBordersFor(table_eventList);
		table_eventList.setHeaderVisible(true);
		table_eventList.setLinesVisible(true);
		initTable(table_eventList);

	}
	
	private void initTable(Table table) {
		String[] titles = { "Event", "Venue", "Date"};
		 
		for (int i = 0; i < titles.length; i++) {
			TableColumn column = new TableColumn(table, SWT.NONE);
			column.setText(titles[i]);
			column.setAlignment(SWT.CENTER);
			
			if(titles[i].equals("Event"))				column.setWidth(380);
			else if(titles[i].equals("Venue"))			column.setWidth(380);
			else if(titles[i].equals("Date"))			column.setWidth(294);
		}
	}
	
	private static void addTableItem(Table table, String name) {
		boolean isEmpty = false;
		if(table.getItemCount() == 0)	isEmpty =true;
		
		 TableItem item = new TableItem(table, SWT.NONE);
		 item.setText(0, name);
		 
		 if(isEmpty)		table.setSelection(0);
	}
	
	private void populateTable() {
		String[] eventList = getEventFiles();
		
		int noOfEvents = eventList.length;
		
		for(int i=0; i<noOfEvents; i++) {
			String event = eventList[i].substring(0, eventList[i].length()-4);
			addTableItem(table_eventList, event);
		}
	}
	
	private String[] getEventFiles() {
		
		ExtensionFilter filter = new ExtensionFilter(".ivt");
		File dir = new File("data/");

		String[] list = dir.list(filter);

		return list;
	}
	

	class ExtensionFilter implements FilenameFilter {
	
		private String extension;
	
		public ExtensionFilter( String extension ) {
			this.extension = extension;             
		}
		public boolean accept(File dir, String name) {
			return (name.endsWith(extension));
		}
	}	  
}
