import java.io.File;
import java.io.IOException;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;

public class HomePage {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			HomePage window = new HomePage();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();	
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/*	Center the shell in the window	*/
	public static void center(Shell shell) {
	    Rectangle bds = shell.getDisplay().getBounds();
	    Point p = shell.getSize();
	
	    int nLeft = (bds.width - p.x) / 2;
	    int nTop = (bds.height - p.y) / 2;
	
	    shell.setBounds(nLeft, nTop, p.x, p.y);
	}
	
	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell(Display.getCurrent(), SWT.CLOSE | SWT.TITLE | SWT.MIN | SWT.PRIMARY_MODAL);
		shell.setSize(1280, 800);
		shell.setText("iTournament");
		shell.setMinimumSize(1280,800);										//Set shell dimension
		center(shell);
		
		Image iTournament = new Image(Display.getCurrent(), HomePage.class.getResourceAsStream("homepage.png"));
		Label lbl_iTournament = new Label(shell, SWT.NONE);
		lbl_iTournament.setBounds(0, 0, 1280, 350);
		lbl_iTournament.setImage(iTournament);
		
		Button btnCreateNewEvent = new Button(shell, SWT.NONE);
		btnCreateNewEvent.setFont(SWTResourceManager.getFont("Lucida Grande", 36, SWT.BOLD));
		btnCreateNewEvent.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				CreateNewEvent_1 newEvent_1 = new CreateNewEvent_1(shell, shell.getStyle());
				newEvent_1.open();
			}
		});
		btnCreateNewEvent.setBounds(50, 430, 360, 200);
		btnCreateNewEvent.setText("Create New Event");
		
		Button btnManageEvent = new Button(shell, SWT.BORDER);
		btnManageEvent.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ManageEvent manageEvent = new ManageEvent(shell, shell.getStyle());
				try {
					manageEvent.open();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnManageEvent.setFont(SWTResourceManager.getFont("Lucida Grande", 36, SWT.BOLD));
		btnManageEvent.setBounds(460, 430, 360, 200);
		btnManageEvent.setText("Manage Event");
		
		Button btManageFinance = new Button(shell, SWT.NONE);
		btManageFinance.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ManageFinance manageFinance = new ManageFinance(shell, shell.getStyle());
				manageFinance.open();
			}
		});
		btManageFinance.setFont(SWTResourceManager.getFont("Lucida Grande", 36, SWT.BOLD));
		btManageFinance.setBounds(870, 430, 360, 200);
		btManageFinance.setText("Manage Finance");
		
		Image image = new Image(Display.getCurrent(), HomePage.class.getResourceAsStream("wood.jpg"));
		Label lbl_wood = new Label(shell, SWT.NONE);
		lbl_wood.setBounds(0, 0, 1280, 800);
		lbl_wood.setImage(image);
	}
}
