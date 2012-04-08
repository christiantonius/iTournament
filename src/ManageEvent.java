import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
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
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import twitter4j.TwitterException;


public class ManageEvent extends Dialog {

	protected Object result;
	protected Shell shell;
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
	 * @throws IOException 
	 */
	public Object open() throws IOException {
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
		btn_editDetails.setFont(SWTResourceManager.getFont("Lucida Grande", 36, SWT.BOLD));
		btn_editDetails.setBounds(95, 370, 300, 150);
		btn_editDetails.setText("Edit Details");
		btn_editDetails.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(table_eventList.getItemCount() != 0) {
					TableItem[] tableItems = table_eventList.getSelection();
					String eventName = tableItems[0].getText(0);
				    boolean hasResultFile = false, hasTimingFile = false;
					
				    hasTimingFile = hasTimingFiles(eventName);
				    hasResultFile = hasResultFiles(eventName);
				    
					//if no .timings and .result file exist
					if(hasTimingFile == false && hasResultFile == false)	{			
						Event existingEvent = new Event();
						
						try {
							existingEvent.loadEvent(eventName + ".event");
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						try {
							existingEvent.loadReferee(eventName + ".ref");
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						
						CreateNewEvent_1 editEventDetails = new CreateNewEvent_1(shell, shell.getStyle());
						editEventDetails.open(existingEvent);		
						try {
							populateTable();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
					else {
						String dialogMessage = eventName + " has started, no editing of details is allowed.";
						MessageDialog.openError(shell, "Error", dialogMessage);
					}
				}
			}
		});

		
		Button btn_viewSchedule = new Button(shell, SWT.NONE);
		btn_viewSchedule.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(table_eventList.getItemCount() != 0) {	
					TableItem[] tableItems = table_eventList.getSelection();
					String eventName = tableItems[0].getText(0);
				    boolean hasResultFile = false, hasTimingFile = false;
					
				    hasTimingFile = hasTimingFiles(eventName);
				    hasResultFile = hasResultFiles(eventName);

					//if no .timings and .result file exist
					if(hasTimingFile == false && hasResultFile == false)	{
						String dialogMessage = "Confirm Create Pairing for " + eventName + "?";
						String[] dialogButtonLabels = {"Cancel", "Ok"};
						MessageDialog newDialog = new MessageDialog(shell, null, null, dialogMessage, MessageDialog.WARNING, dialogButtonLabels, 0);
						int returnCode = newDialog.open();
						
						//if user press OK
						if(returnCode == 1) {
							Pair pair = new Pair(eventName);
							pair.createPairing();
							
							Event currentEvent = new Event();
							try {
								currentEvent.loadEvent(eventName + ".event");
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							Fixture fixture = new Fixture(shell, shell.getStyle());
							fixture.open(currentEvent);
						}
					}
					
					else {
						Ladder currentLadder = new Ladder(eventName);
						currentLadder.loadLadder(eventName);
						
						Event currentEvent = new Event();
						try {
							currentEvent.loadEvent(eventName + ".event");
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						Fixture fixture = new Fixture(shell, shell.getStyle());
						fixture.open(currentEvent, currentLadder);
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
					String eventName = tableItems[0].getText(0) ;
					String eventFileName = eventName + ".event";
					
					String dialogMessage = "Confirm cancel the Event \"" + eventName + " and delete all relevant data?";
					boolean isConfirm = MessageDialog.openConfirm(shell, "Confirmation", dialogMessage);

					if(isConfirm == true) {
						try {
							existingEvent.loadEvent(eventFileName);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						try {
							existingEvent.deleteAllEventRelatedFile();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
							
						table_eventList.remove(index);
						if(index != 0)													table_eventList.setSelection(index-1);
					   	else if(table_eventList.getItemCount() != 0)		table_eventList.setSelection(0);
						
						try {
							populateTable();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
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
		
		table_eventList = new Table(grp_eventList, SWT.BORDER | SWT.FULL_SELECTION);
		table_eventList.setFont(SWTResourceManager.getFont("Lucida Grande", 14, SWT.NORMAL));
		table_eventList.setBounds(10, 10, 1056, 293);
		table_eventList.setHeaderVisible(true);
		table_eventList.setLinesVisible(true);
		initTable(table_eventList);
		
		Image twitterLogo = new Image(Display.getCurrent(), HomePage.class.getResourceAsStream("twitter-button.png"));
		Button btn_tweet = new Button(shell, SWT.NONE);
		btn_tweet.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TwitterDialog twitterDialog = new TwitterDialog(shell, shell.getStyle());
				twitterDialog.open();
			}
		});
		btn_tweet.setBounds(1135, 600, 50, 50);
		btn_tweet.setImage(twitterLogo);

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
	
	private static void addTableItem(Table table, String name, String venue, String dateTime) {
		boolean isEmpty = false;
		if(table.getItemCount() == 0)	isEmpty =true;
		
		 TableItem item = new TableItem(table, SWT.NONE);
		 item.setText(0, name);
		 item.setText(1, venue);
		 item.setText(2, dateTime);
		 
		 if(isEmpty)		table.setSelection(0);
	}
	
	private void populateTable() throws IOException {
		table_eventList.removeAll();
		String[] eventList = getFiles(".event");
		
		if(eventList == null)	
			return;

		int noOfEvents = eventList.length;
		
		for(int i=0; i<noOfEvents; i++) {
			Event currentEvent = new Event();
			String eventName = eventList[i].substring(0, eventList[i].length()-6);

			currentEvent.loadEvent(eventName + ".event");
			
			String dateTime = currentEvent.getFormattedDate() + " " + currentEvent.getFormattedTime();

			addTableItem(table_eventList, currentEvent.getEventName(), currentEvent.getVenue(), dateTime);
		}
	}
	
	private boolean hasTimingFiles(String eventName) {
		int timingListCount = 0;
		String[] timingList = getFiles(".timings");

		if(timingList.length != 0) {
			for(int i=0; i<timingList.length; i++) {
				if(timingList[i].startsWith(eventName))
					timingListCount++;
			}
			
			if(timingListCount > 0)	return true;
		}
		
		return false;		
	}
	
	private boolean hasResultFiles(String eventName) {
		int resultListCount = 0;
		String[] resultList = getFiles(".results");

		if(resultList.length != 0) {
			for(int i=0; i<resultList.length; i++) {
				if(resultList[i].startsWith(eventName))
					resultListCount++;
			}
			
			if(resultListCount > 0)	return true;
		}
		
		return false;		
	}
	
	
	private String[] getFiles(String ext) {
		
		ExtensionFilter filter = new ExtensionFilter(ext);
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
